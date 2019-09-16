package com.cdf.factory.entity;

import org.dom4j.tree.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tes_namespace")
public class Namespace extends AbstractEntity{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4531499444309419351L;
	
	@Field("id")
	private Integer id;
 
	@Field("name")
	private String name;
 
	@Field("code")
	private String code;
 
	@Field("description")
	private String description;
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getCode() {
		return code;
	}
 
	public void setCode(String code) {
		this.code = code;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

