package it.lastminute.beans;

import java.math.BigDecimal;

public class Price {

	private BigDecimal originalPrice;
	private BigDecimal netPrice;
	private int rate;
	private BigDecimal salesTaxAmount;

	public Price(BigDecimal originalPrice, BigDecimal netPrice, int rate, BigDecimal salesTaxAmount) {
		super();
		this.originalPrice = originalPrice;
		this.netPrice = netPrice;
		this.rate = rate;
		this.salesTaxAmount = salesTaxAmount;
	}
	
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	
	public int getRate() {
		return rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public BigDecimal getSalesTaxAmount() {
		return salesTaxAmount;
	}
	
	public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}

	@Override
	public String toString() {
		return "Price [originalPrice=" + originalPrice + ", netPrice=" + netPrice + ", rate=" + rate
				+ ", salesTaxAmount=" + salesTaxAmount + "]";
	}
	
	
}
