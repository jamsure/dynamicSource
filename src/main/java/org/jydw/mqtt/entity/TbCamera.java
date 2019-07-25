package org.jydw.mqtt.entity;

/**
 * @ClassName TbCamera
 * @Description TODO
 * @Date 2019/6/3 16:02
 * @Author fankai
 * @Version 1.0
 **/
public class TbCamera extends BaseEntity {

    private String keyId ;
    private String code ;
    private String name ;
    private String aliasname ;
    private String deviceId ;
    private String channelNum;
    private String smsId;
    private String bakSmsId;
    private String rmsId;
    private String bakRmsId;
    private String streamType;
    private String protocol;
    private String url;
    private String reservered1;
    private String reservered2;
    private String streamFormat;

    public TbCamera(String keyId){
        this.keyId = keyId;
    }

    public TbCamera(){
        super();
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getBakSmsId() {
        return bakSmsId;
    }

    public void setBakSmsId(String bakSmsId) {
        this.bakSmsId = bakSmsId;
    }

    public String getRmsId() {
        return rmsId;
    }

    public void setRmsId(String rmsId) {
        this.rmsId = rmsId;
    }

    public String getBakRmsId() {
        return bakRmsId;
    }

    public void setBakRmsId(String bakRmsId) {
        this.bakRmsId = bakRmsId;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReservered1() {
        return reservered1;
    }

    public void setReservered1(String reservered1) {
        this.reservered1 = reservered1;
    }

    public String getReservered2() {
        return reservered2;
    }

    public void setReservered2(String reservered2) {
        this.reservered2 = reservered2;
    }

    public String getStreamFormat() {
        return streamFormat;
    }

    public void setStreamFormat(String streamFormat) {
        this.streamFormat = streamFormat;
    }

    @Override
    public String toString() {
        return "TbCamera{" +
                "keyId='" + keyId + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", aliasname='" + aliasname + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", channelNum='" + channelNum + '\'' +
                ", smsId='" + smsId + '\'' +
                ", bakSmsId='" + bakSmsId + '\'' +
                ", rmsId='" + rmsId + '\'' +
                ", bakRmsId='" + bakRmsId + '\'' +
                ", streamType='" + streamType + '\'' +
                ", protocol='" + protocol + '\'' +
                ", url='" + url + '\'' +
                ", reservered1='" + reservered1 + '\'' +
                ", reservered2='" + reservered2 + '\'' +
                ", streamFormat='" + streamFormat + '\'' +
                '}';
    }
}
