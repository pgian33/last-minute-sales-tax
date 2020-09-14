package it.lastminute.beans;

import it.lastminute.enumerations.ItemTypeList;

public class Item {
	
	private boolean isImported;
	private ItemTypeList type;
	private Price price;
	private int quantity;
	private String productName;
	
	public Item(boolean isImported, ItemTypeList type, int quantity, String productName) {
		super();
		this.isImported = isImported;
		this.type = type;
		this.quantity = quantity;
		this.productName = productName;
	}
	
	public boolean isImported() {
		return isImported;
	}
	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
	public ItemTypeList getType() {
		return type;
	}
	public void setType(ItemTypeList type) {
		this.type = type;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Item [isImported=" + isImported + ", type=" + type + ", price=" + price.toString() + ", quantity=" + quantity
				+ "]";
	}
}
