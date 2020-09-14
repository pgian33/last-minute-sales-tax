package it.lastminute.builder;

import java.math.BigDecimal;
import java.util.List;

import it.lastminute.beans.Item;
import it.lastminute.beans.Receipt;

public class ReceiptBuilder {

	private List<Item> itemsList;
	private BigDecimal totalPrice;
	private BigDecimal totalSalesTax;

	public static ReceiptBuilder newBuilder() {
		return new ReceiptBuilder();
	}

	public ReceiptBuilder itemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
		return this;
	}

	public Receipt build() {
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal totalSalesTax = new BigDecimal(0);

		for(Item anItem : itemsList) {
			totalPrice = totalPrice.add(anItem.getPrice().getNetPrice().multiply(new BigDecimal(anItem.getQuantity())));
			totalSalesTax = totalSalesTax.add(anItem.getPrice().getSalesTaxAmount().multiply(new BigDecimal(anItem.getQuantity())));
		}
		this.totalPrice = totalPrice;
		this.totalSalesTax = totalSalesTax;
		return new Receipt(itemsList, this.totalPrice, this.totalSalesTax);
	}



}
