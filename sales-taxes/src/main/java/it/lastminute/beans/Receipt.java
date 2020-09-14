package it.lastminute.beans;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

	private List<Good> goodsList;
	private BigDecimal totalPrice;
	private BigDecimal totalSalesTax;

	public List<Good> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Good> goodsList) {
		this.goodsList = goodsList;
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

	public Receipt(List<Good> goodsList, BigDecimal totalPrice, BigDecimal totalSalesTax) {
		super();
		this.goodsList = goodsList;
		this.totalPrice = totalPrice;
		this.totalSalesTax = totalSalesTax;
	}

	public String getReceipt() {
		StringBuilder str = new StringBuilder(); 

		for (Good good : goodsList) {
			str.append(getAGoodReceipt(good));
		}
		str.append("Sales Taxes: ")
		.append(this.totalSalesTax)
		.append("\r\n")
		.append("Total: ")
		.append(this.totalPrice);

		return str.toString();
	}

	private String getAGoodReceipt(Good aGood) {
		StringBuilder str = new StringBuilder(); 
		str.append(aGood.getQuantity())
		.append(" ")
		.append(aGood.getProductName())
		.append(": ")
		.append(aGood.getPrice().getNetPrice())
		.append("\r\n");

		return str.toString();		
	}
}
