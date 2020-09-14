package it.lastminute.beans;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

	private List<Item> itemsList;
	private BigDecimal totalPrice;
	private BigDecimal totalSalesTax;
	
	public List<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public void setTotalSalesTax(BigDecimal totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}

	public Receipt(List<Item> itemsList, BigDecimal totalPrice, BigDecimal totalSalesTax) {
		super();
		this.itemsList = itemsList;
		this.totalPrice = totalPrice;
		this.totalSalesTax = totalSalesTax;
	}

	public String getReceipt() {
		StringBuilder str = new StringBuilder(); 

		for (Item anItem : itemsList) {
			str.append(getAnItemReceipt(anItem));
		}
		str.append("Sales Taxes: ")
		.append(this.totalSalesTax)
		.append("\r\n")
		.append("Total: ")
		.append(this.totalPrice);

		return str.toString();
	}

	private String getAnItemReceipt(Item anItem) {
		StringBuilder str = new StringBuilder(); 
		str.append(anItem.getQuantity())
		.append(" ")
		.append(anItem.getProductName())
		.append(": ")
		.append(anItem.getPrice().getNetPrice())
		.append("\r\n");

		return str.toString();		
	}
}
