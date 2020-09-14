package it.lastminute.builder;

import java.math.BigDecimal;
import java.util.List;

import it.lastminute.beans.Good;
import it.lastminute.beans.Receipt;

public class ReceiptBuilder {

	private List<Good> goodsList;
	private BigDecimal totalPrice;
	private BigDecimal totalSalesTax;

	public static ReceiptBuilder newBuilder() {
		return new ReceiptBuilder();
	}

	public ReceiptBuilder goodsList(List<Good> goodsList) {
		this.goodsList = goodsList;
		return this;
	}

	public Receipt build() {
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalSalesTax = new BigDecimal(0);

		for(Good aGood : goodsList) {
			totalPrice = totalPrice.add(aGood.getPrice().getNetPrice().multiply(new BigDecimal(aGood.getQuantity())));
			totalSalesTax = totalSalesTax.add(aGood.getPrice().getSalesTaxAmount().multiply(new BigDecimal(aGood.getQuantity())));
		}
		this.totalPrice = totalPrice;
		this.totalSalesTax = totalSalesTax;
		return new Receipt(goodsList, this.totalPrice, this.totalSalesTax);
	}



}
