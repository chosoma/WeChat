package com.thingtek.gil.entity;

import lombok.Data;

import java.util.Date;

public@Data
class RawData {
	private byte[] data;
	private Date time;

	public RawData(byte[] data, Date time) {
		this.data = data;
		this.time = time;
	}


}
