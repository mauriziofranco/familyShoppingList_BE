package com.coorp.rob.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Roberto
 * 
 * */
@Document(value = "ShopList")
public class ShopList {

	@Id
	private String id;
	
	private List<Item> itemList;
	
	public ShopList() {
		super();
		this.itemList = new ArrayList<Item>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "ShopList [id=" + id + ", itemList=" + itemList + "]";
	}	
}