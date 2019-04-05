package com.mance.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Product extends BaseModel {

	@Column(name = "remaining_stocks")
	int remainingStocks;

	public Product() {
		super();
	}

	public int getRemainingStocks() {
		return remainingStocks;
	}

	public void setRemainingStocks(int remainingStocks) {
		this.remainingStocks = remainingStocks;
	}

}
