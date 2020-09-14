package it.lastminute.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Good;
import it.lastminute.enumerations.GoodTypeList;

public class GoodBuilder {
	
	private static final Logger _log = LoggerFactory.getLogger(GoodBuilder.class);
	
	private boolean isImported;
	private GoodTypeList type;
	private int quantity;
	private String productName;

	public static GoodBuilder newBuilder() {
		return new GoodBuilder();
	}
	
	public GoodBuilder isImported(boolean isImported) {
		_log.trace(">> isImported(isImported={})", isImported);
		this.isImported = isImported;
		return this;
	}
	
	public GoodBuilder type(GoodTypeList type) {
		_log.trace(">> type(type={}", type);
		this.type = type;
		return this;
	}
	
	public GoodBuilder quantity(int quantity) {
		_log.trace(">> quantity(quantity={}", quantity);
		this.quantity = quantity;
		return this;
	}
	
	public GoodBuilder productName(String productName) {
		_log.trace(">> productName(productName={}", productName);
		this.productName = productName;
		return this;
	}
	
	public Good build() {
		return new Good(isImported, type, quantity, productName);
	}
}

