package com.coorp.rob.model;

import org.springframework.data.mongodb.core.mapping.Document;

public class UserInfo {
	
	private String name;
	
	private String surname;
	
	private String idListItem;
	
	private String citta;
	
	private String cap;
	
	public UserInfo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdListItem() {
		return idListItem;
	}

	public void setIdListItem(String idListItem) {
		this.idListItem = idListItem;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", surname=" + surname + ", idListItem=" + idListItem + ", citta=" + citta
				+ ", cap=" + cap + "]";
	}
}
