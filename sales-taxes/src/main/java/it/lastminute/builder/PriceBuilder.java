package it.lastminute.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Item;
import it.lastminute.beans.Price;
import it.lastminute.enumerations.ItemTypeList;
import it.lastminute.enumerations.SalesTax;

public class PriceBuilder {
	
	private static final Logger _log = LoggerFactory.getLogger(PriceBuilder.class);
	
	private BigDecimal originalPrice;
	private BigDecimal netPrice;
	private int rate;
	private BigDecimal salesTaxAmount;

	public static PriceBuilder newBuilder() {
		return new PriceBuilder();
	}

	public PriceBuilder originalPrice(BigDecimal originalPrice) {
		_log.trace(">> originalPrice(originalPrice={})", originalPrice);
		this.originalPrice = originalPrice.setScale(2, RoundingMode.HALF_UP);
		_log.trace("<< originalPrice: this.originalPrice={})", this.originalPrice);
		return this;
	}

	public PriceBuilder rate(Item anItem) {
		_log.trace(">> rate(Item anItem)");
		if(anItem != null) {
			if(ItemTypeList.OTHER_TYPE.equals(anItem.getType())) {
				this.rate = SalesTax.STANDARD_TAX.getRate();
			}
			if(anItem.isImported()) {
				this.rate = this.rate + SalesTax.IMPORTED_TAX.getRate();
			}
		}
		_log.trace("<< rate: this.rate={}", this.rate);
		return this;
	}

	public PriceBuilder salesTaxAmount(Item anItem) {
		_log.trace(">> salesTaxAmount(Item anItem)");
		if(anItem != null) {
			this.salesTaxAmount = getRoundedAmount(originalPrice.multiply(new BigDecimal(rate)).divide(new BigDecimal(100)));
		}
		_log.trace("<< salesTaxAmount: this.salesTaxAmount={}", this.salesTaxAmount);
		return this;
	}

	private static BigDecimal getRoundedAmount(BigDecimal salesTaxAmount) {
		_log.trace(">> getRoundedAmount(BigDecimal salesTaxAmount={})", salesTaxAmount);
		final BigDecimal roundedAmount = salesTaxAmount.multiply(new BigDecimal(20.00)).setScale(0, RoundingMode.UP).divide(new BigDecimal(20.00));
		final BigDecimal ceilingRoundedAmount = roundedAmount.setScale(2, RoundingMode.CEILING);
		_log.trace("<< getRoundedAmount: ceilingRoundedAmount={}", ceilingRoundedAmount);
		return ceilingRoundedAmount;
	}

	public Price build() {
		final BigDecimal netPrice = originalPrice.add(salesTaxAmount);
		this.netPrice = netPrice.setScale(2, RoundingMode.HALF_UP);
		
		return new Price(originalPrice, this.netPrice, rate, salesTaxAmount);
	}
}
