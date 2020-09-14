package it.lastminute.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Item;
import it.lastminute.enumerations.ItemTypeList;

public class ItemBuilder {
	
	private static final Logger _log = LoggerFactory.getLogger(ItemBuilder.class);
	
	private boolean isImported;
	private ItemTypeList type;
	private int quantity;
	private String productName;

	public static ItemBuilder newBuilder() {
		return new ItemBuilder();
	}
	
	public ItemBuilder isImported(boolean isImported) {
		_log.trace(">> isImported(isImported={})", isImported);
		this.isImported = isImported;
		return this;
	}
	
	public ItemBuilder type(ItemTypeList type) {
		_log.trace(">> type(type={}", type);
		this.type = type;
		return this;
	}
	
	public ItemBuilder quantity(int quantity) {
		_log.trace(">> quantity(quantity={}", quantity);
		this.quantity = quantity;
		return this;
	}
	
	public ItemBuilder productName(String productName) {
		_log.trace(">> productName(productName={}", productName);
		this.productName = productName;
		return this;
	}
	
	public Item build() {
		return new Item(isImported, type, quantity, productName);
	}
}

