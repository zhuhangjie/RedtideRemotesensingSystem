package com.zhuhangjie.pojo;

public class RecordResult extends Record{
	private String voyageName;

	public RecordResult() {
		
	}

	public RecordResult(Record record) {
		this.setId(record.getId());
		this.setPointName(record.getPointName());
		this.setRecordNumber(record.getRecordNumber());
		this.setPositionX(record.getPositionX());
		this.setPositionY(record.getPositionY());
		this.setWeather(record.getWeather());
		this.setWaterPicture(record.getWaterPicture());
		this.setWaterDemo(record.getWaterDemo());
		this.setChlDemo(record.getChlDemo());
		this.setCdomDemo(record.getCdomDemo());
		this.setGranuleDemo(record.getGranuleDemo());
		this.setSpectrumDemo(record.getSpectrumDemo());
		this.setArriveTime(record.getArriveTime());
		this.setVoyageId(record.getVoyageId());
	}



	public String getVoyageName() {
		return voyageName;
	}

	public void setVoyageName(String voyageName) {
		this.voyageName = voyageName;
	}
	
}
