package com.pegasus.domain;

import java.time.LocalDate;

public class Customer {

	private String id;
	private String name;
	private LocalDate creDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getCreDate() {
		return creDate;
	}
	public void setCreDate(LocalDate creDate) {
		this.creDate = creDate;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", creDate=" + creDate + "]";
	}
	
	
}
