package com.pegasus.domain;

import java.io.Serializable;

public class Author implements Serializable{

	private static final long serialVersionUID = -5057104403398430640L;
	private Integer id;
	private byte[] img;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	
}
