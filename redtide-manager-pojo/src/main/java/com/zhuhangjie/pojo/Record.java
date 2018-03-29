package com.zhuhangjie.pojo;

import java.util.Date;

public class Record {
    private Long id;

    private String pointName;

    private Long recordNumber;

    private Double positionX;

    private Double positionY;

    private String weather;

    private String waterPicture;

    private String waterDemo;

    private String chlDemo;

    private String cdomDemo;

    private String granuleDemo;

    private String spectrumDemo;

    private Date arriveTime;

    private Long voyageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public Long getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Long recordNumber) {
        this.recordNumber = recordNumber;
    }

    public Double getPositionX() {
        return positionX;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    public String getWaterPicture() {
        return waterPicture;
    }

    public void setWaterPicture(String waterPicture) {
        this.waterPicture = waterPicture == null ? null : waterPicture.trim();
    }

    public String getWaterDemo() {
        return waterDemo;
    }

    public void setWaterDemo(String waterDemo) {
        this.waterDemo = waterDemo == null ? null : waterDemo.trim();
    }

    public String getChlDemo() {
        return chlDemo;
    }

    public void setChlDemo(String chlDemo) {
        this.chlDemo = chlDemo == null ? null : chlDemo.trim();
    }

    public String getCdomDemo() {
        return cdomDemo;
    }

    public void setCdomDemo(String cdomDemo) {
        this.cdomDemo = cdomDemo == null ? null : cdomDemo.trim();
    }

    public String getGranuleDemo() {
        return granuleDemo;
    }

    public void setGranuleDemo(String granuleDemo) {
        this.granuleDemo = granuleDemo == null ? null : granuleDemo.trim();
    }

    public String getSpectrumDemo() {
        return spectrumDemo;
    }

    public void setSpectrumDemo(String spectrumDemo) {
        this.spectrumDemo = spectrumDemo == null ? null : spectrumDemo.trim();
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }
}