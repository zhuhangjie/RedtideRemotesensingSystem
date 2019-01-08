package com.zhuhangjie.pojo;

import java.util.Date;

public class Pic {
    private Integer id;

    private String name;

    private Date date;

    private Boolean nc;

    private Boolean chl;

    private Boolean cloud;

    private Boolean shp;

    private Integer redtidepoint;

    private Double area;

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

    public Boolean getNc() {
        return nc;
    }

    public void setNc(Boolean nc) {
        this.nc = nc;
    }

    public Boolean getChl() {
        return chl;
    }

    public void setChl(Boolean chl) {
        this.chl = chl;
    }

    public Boolean getCloud() {
        return cloud;
    }

    public void setCloud(Boolean cloud) {
        this.cloud = cloud;
    }

    public Boolean getShp() {
        return shp;
    }

    public void setShp(Boolean shp) {
        this.shp = shp;
    }

    public Integer getRedtidepoint() {
        return redtidepoint;
    }

    public void setRedtidepoint(Integer redtidepoint) {
        this.redtidepoint = redtidepoint;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

	@Override
	public String toString() {
		return "Pic [id=" + id + ", name=" + name + ", date=" + date + ", nc=" + nc + ", chl=" + chl + ", cloud="
				+ cloud + ", shp=" + shp + ", redtidepoint=" + redtidepoint + ", area=" + area + "]";
	}
    
    
}