package com.zhuhangjie.pojo;

import java.util.Date;

public class Pic {
    private Integer id;

    private String name;

    private Date date;

    private String chl;

    private String rt;

    private String cloud;

    private Double area;

    private String isredtide;

    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChl() {
        return chl;
    }

    public void setChl(String chl) {
        this.chl = chl == null ? null : chl.trim();
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt == null ? null : rt.trim();
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud == null ? null : cloud.trim();
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getIsredtide() {
        return isredtide;
    }

    public void setIsredtide(String isredtide) {
        this.isredtide = isredtide == null ? null : isredtide.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}