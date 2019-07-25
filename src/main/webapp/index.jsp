<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>表单模块 - layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="static/layui/css/layui.css">

    <style>
        body{padding: 10px;}
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>设置测温对象</legend>
</fieldset>
<form class="layui-form layui-form-pane1" name="ProtocolType1" action="" lay-filter="first">
    <input type="hidden" name="ProtocolType" value="1">
    <div class="layui-form-item">
        <label class="layui-form-label">编号</label>
        <div class="layui-input-inline">
            <input type="number" name="Num" lay-verify="required|number" lay-reqText="编号不能为空" required placeholder="请输入编号" autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">矩形</label>
        <div class="layui-input-inline">
            <input type="number" name="Rect1" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-input-inline">
            <input type="number" name="Rect2" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-input-inline">
            <input type="number" name="Rect3" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-input-inline">
            <input type="number" name="Rect4" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">距离</label>
        <div class="layui-input-inline">
            <input type="number" name="Dist" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">报警类型</label>
        <div class="layui-input-inline">
            <select name="CheckType" >
                <option value="Max">Max</option>
                <option value="Min">Min</option>
                <option value="Avg">Avg</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">报警温度</label>
        <div class="layui-input-inline">
            <input type="number" name="AlarmValue" lay-verify="required|number"  required  autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ProtocolType1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>设置测温对象上报规则</legend>
</fieldset>
<form class="layui-form layui-form-pane1" name="ProtocolType2" action="" lay-filter="first">
    <input type="hidden" name="ProtocolType" value="2">
    <div class="layui-form-item">
        <label class="layui-form-label">上报测温数据间隔</label>
        <div class="layui-input-inline">
            <input type="number" name="Type" lay-verify="required|number" lay-reqText="间隔不能为空" required placeholder="请输入间隔" autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-form-mid layui-word-aux">0每帧，其他表示时间间隔，单位ms</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ProtocolType2">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>主动开始获取测温数据</legend>
</fieldset>
<form class="layui-form layui-form-pane1" name="ProtocolType2" action="" lay-filter="first">
    <input type="hidden" name="ProtocolType" value="2">
    <div class="layui-form-item">
        <label class="layui-form-label">获取数据时长</label>
        <div class="layui-input-inline">
            <input type="number" name="Time" lay-verify="required|number" lay-reqText="时长不能为空" required placeholder="请输入时长" autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-form-mid layui-word-aux">持续获取图像数据时长，单位s</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ProtocolType10">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>获取焦距</legend>
</fieldset>
<form class="layui-form layui-form-pane1" name="ProtocolType2" action="" lay-filter="first">
    <input type="hidden" name="ProtocolType" value="8">
    <div class="layui-form-item">
        <label class="layui-form-label">当前焦距</label>
        <div class="layui-input-inline">
            <input type="number" id="_8Value"  autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">最小焦距</label>
        <div class="layui-input-inline">
            <input type="number" id="_8Max" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">最大焦距</label>
        <div class="layui-input-inline">
            <input type="number" id="_8Min" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ProtocolType8">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>获取焦距</legend>
</fieldset>
<form class="layui-form layui-form-pane1" name="ProtocolType2" action="" lay-filter="first">
    <input type="hidden" name="ProtocolType" value="7">
    <div class="layui-form-item">
        <label class="layui-form-label">设置焦距</label>
        <div class="layui-input-inline">
            <input type="number" id="_7Value" lay-verify="required|number" lay-reqText="焦距不能为空" required placeholder="请输入焦距"  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ProtocolType7_3">设置焦距</button>
        </div>
    </div>
</form>
        <!--(0,拉近1拉远, 2自动，3，具体数据，9，停止)-->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="ProtocolType7_0" onmousedown="ProtocolType7_down(0)" onmouseup="ProtocolType7_up(9)" >拉近</button>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="ProtocolType7_1" onmousedown="ProtocolType7_down(1)" onmouseup="ProtocolType7_up(9)">拉远</button>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="ProtocolType7_2">自动</button>
            </div>
        </div>





<script src="static/layui/layui.all.js"></script>
<script src="static/js/jquery-1.10.1.min.js"></script>
<!-- <script src="../build/lay/dest/layui.all.js"></script> -->

<script>

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题也太短了吧';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,money: [
                /^\d+\.\b\d{2}\b$/
                ,'金额必须为小数保留两位'
            ]
        });

        //日期
        laydate.render({
            elem: '#date'
        });

        //初始赋值
        form.val('first', {
            'title': '测试'
            ,'phone': 11111111111
            ,'email': 'xu@sentsin.com'
            ,'password': 123123
            //,'quiz': 2
            ,'interest': 3
            ,'like[write]': true
            //,'open': false
            ,'sex': '男'
            ,'desc': 'form 是我们非常看重的一块'
            ,xxxxxxxxx: 123
        });


        //事件监听
        form.on('select', function(data){
            console.log('select: ', this, data);
        });

        form.on('select(quiz)', function(data){
            console.log('select.quiz：', this, data);
        });

        form.on('select(interest)', function(data){
            console.log('select.interest: ', this, data);
        });



        form.on('checkbox', function(data){
            console.log(this.checked, data.elem.checked);
        });

        form.on('switch', function(data){
            console.log(data);
        });

        form.on('radio', function(data){
            console.log(data);
        });

        //监听提交
        form.on('submit(ProtocolType1)', function(data){
            $.ajax({
                url: 'test/sendRect',
                type: 'POST',
                data: data.field,
                dataType : "json",
                success: function (result) {
                    console.log(result)
                }
            });
            console.log(data)
            alert(JSON.stringify(data.field));
            return false;
        });

        //监听提交
        form.on('submit(ProtocolType2)', function(data){
            $.ajax({
                url: 'test/sendProtocolType2',
                type: 'POST',
                data: data.field,
                dataType : "json",
                success: function (result) {
                    console.log(result)
                }
            });
            console.log(data)
            alert(JSON.stringify(data.field));
            return false;
        });


        //监听提交
        form.on('submit(ProtocolType10)', function(data){
            $.ajax({
                url: 'test/sendProtocolType10',
                type: 'POST',
                data: data.field,
                dataType : "json",
                success: function (result) {
                    console.log(result)
                }
            });
            console.log(data)
            alert(JSON.stringify(data.field));
            return false;
        });

        //监听提交
        form.on('submit(ProtocolType8)', function(data){
            $.ajax({
                url: 'test/ProtocolType8',
                type: 'POST',
                data: data.field,
                dataType : "json",
                success: function (result) {
                    console.log(result)
                }
            });
            console.log(data)
            alert(JSON.stringify(data.field));
            return false;
        });


        $(document).on('mousedown','#ProtocolType7_2',function(){
            layer.msg('onmousedown');
        });

        $(document).on('mouseup','#ProtocolType7_2',function(){
            layer.msg('onmouseup');
        });
    });

    var downFlag = true;
    function  ProtocolType7_down(Type) {
        if (downFlag) {
            layer.msg('Type：'+Type);
            downFlag = false;
        }else{
            downFlag = false;
            myVar = setTimeout(function() {
                layer.msg('down点击太频繁了~~');
                downFlag = true;
            }, 500);
        }

        // $.ajax({
        //     url: 'test/ProtocolType7',
        //     type: 'POST',
        //     data: {
        //         "ProtocolType":7,
        //         "Type":Type
        //     },
        //     dataType : "json",
        //     success: function (result) {
        //         console.log(result)
        //     }
        // });
    }

    var upFlag = true;
    function  ProtocolType7_up(Type) {
        if (upFlag) {
            layer.msg('Type：' + Type);
            upFlag = false;
        } else {
            upFlag = false;
            myVar1 = setTimeout(function () {
                layer.msg('up点击太频繁了~~');
                upFlag = true;
            }, 500);
        }
    }

</script>
</body>
</html>