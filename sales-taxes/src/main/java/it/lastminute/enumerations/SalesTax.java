package it.lastminute.enumerations;

public enum SalesTax {

	STANDARD_TAX(10),
	IMPORTED_TAX(5);
	
	private int rate;
	
	public int getRate() {
		return rate;
	}

	private SalesTax(int rate) {
		this.rate = rate;
	}
}
