package com.mance.model;

import java.util.ArrayList;
import java.util.List;

public class Seller {

	private String id;
	private String url;
	private List<Item> itemsForSale = new ArrayList<Item>();

	public Seller(String id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public List<Item> getItemsForSale() {
		return itemsForSale;
	}

	public void setItemsForSale(List<Item> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
