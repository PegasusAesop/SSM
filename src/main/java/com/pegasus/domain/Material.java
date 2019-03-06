package com.pegasus.domain;

import java.time.LocalDate;

public class Material {

	private String name;
	private String specification;
	private LocalDate prod_time;
	private Float price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public LocalDate getProd_time() {
		return prod_time;
	}
	public void setProd_time(LocalDate prod_time) {
		this.prod_time = prod_time;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Material [name=" + name + ", specification=" + specification + ", prod_time=" + prod_time + ", price="
				+ price + "]";
	}
	
	
}
