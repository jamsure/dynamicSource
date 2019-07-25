package org.jydw.mqtt.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.core.ApplicationContext;
import org.jydw.mqtt.service.MqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ProtocolType3Handler
 * @Description TODO
 * @Date 2019/7/25 9:46
 * @Author fankai
 * @Version 1.0
 **/
@Component
public class ProtocolType3Handler implements ProtocolHandler {
    Logger logger = LoggerFactory.getLogger(ProtocolType3Handler.class);

    @Autowired
    private MqttService mqttService ;

    @Override
    public void handler(Map<String, Object> params) {
        //{"ProtocolType":3,"Preset":0,"TempValue":"xxxxxxxxxx","TempArray":[{"Num":1,"Type":"Line","Temp":{"Max":40.0,"Min":20.1,"Avg":30.2},"AlarmStu":0},{"Num":2,"Type":"Rect","Temp":{"Max":40.0,"Min":20.1,"Avg":30.2},"AlarmStu":0}]}
        String deviceId = params.get("deviceId").toString();
        JSONObject object = (JSONObject) params.get("jsonObject");
        String presetNo = object.getString("Preset");
        //1.解析测温数据，解析结果为  L01_Max=25,R01_Min=30
        JSONArray tempArray = object.getJSONArray("TempArray");
        //使用script 包  解析算术
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        Map<String,Object> tempMap = new HashMap();
        for (int i = 0;i<tempArray.size();i++){
            JSONObject obj = tempArray.getJSONObject(i);
            int Num = obj.getInteger("Num");
            String Type = obj.getString("Type");
            JSONObject Temp = obj.getJSONObject("Temp");
            float Max = Temp.getFloat("Max");
            float Min = Temp.getFloat("Min");
            float Avg = Temp.getFloat("Avg");
            String name = formatNum(Type,Num);
            tempMap.put(name+":Max",Max);
            engine.put(name+"_Max", Max);

            tempMap.put(name+":Min",Min);
            engine.put(name+"_Min", Min);

            tempMap.put(name+":Avg",Avg);
            engine.put(name+"_Avg", Avg);
        }
        System.out.println("测温数据："+tempMap);

        //2.获取测温规则
        Map<String,String> map = new HashMap();
        map.put("deviceId",deviceId);
        map.put("presetNo",presetNo);
        List<Map<String,String>> list= mqttService.getRulesByDeviceIdAndPresetNo(map);
        for (Map<String,String> rule:list) {
            String formula = rule.get("formula").toString();
            for (String key : tempMap.keySet()) {
                formula = formula.replaceAll(key,tempMap.get(key).toString());
            }
            //替换过的公式
            String formulaRule = formula.replaceAll(":","_").replaceAll("math","Math");
            try {
                Object result = engine.eval(formulaRule);
                logger.info("结果类型:{},计算结果:{},计算公式:{}",result.getClass().getName(),result,formula);
//                if (Boolean.valueOf(result.toString())){
                    logger.info("{}_{}:故障特征:{}:描述:{}:温度超限-当前区域设置上限温度公式:{}:当前温度:{}:formulaId:{}",
                            rule.get("faultDegreeId"),rule.get("keyId"),rule.get("faultFeature"),
                            rule.get("desOfFormula"),rule.get("formula"),formula,rule.get("keyId"));
//                }
            } catch (ScriptException e) {
//                e.printStackTrace();
            }
        }

    }

    /**
     * @Description 将type和序号拼接   "Num":1,"Type":"Line" 返回 L01
     * @Date  2019/7/25 10:00
     * @Author fankai
     * @Param [type, num]
     * @return java.lang.String
     **/
    public static String formatNum(String type,int num){
        StringBuilder sb = new StringBuilder();
        sb.append(type.substring(0,1));
        if (num<10){
            sb.append(0);
        }
        sb.append(num);
        return sb.toString();
    }

}
