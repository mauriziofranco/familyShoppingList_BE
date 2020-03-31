package com.coorp.rob.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "ShopList")
public class ShopList {

	@Id
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Item> getListIem() {
		return listIem;
	}

	public void setListIem(List<Item> listIem) {
		this.listIem = listIem;
	}

	private List<Item> listIem;
	
	public ShopList() {
		super();
	}

	@Override
	public String toString() {
		return "ShopList [id=" + id + ", listIem=" + listIem + "]";
	}
}
