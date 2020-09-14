package it.lastminute.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Good;
import it.lastminute.beans.Price;
import it.lastminute.builder.GoodBuilder;
import it.lastminute.builder.PriceBuilder;
import it.lastminute.enumerations.GoodTypeList;
import it.lastminute.exceptions.NoInputGoodException;
import it.lastminute.exceptions.NoMatchInputException;

public class GoodsHelper {

	private static final Logger _log = LoggerFactory.getLogger(GoodsHelper.class);
	public static final String PATTERN_REGEXP = "(\\d)\\s([a-zA-z\\s]*)\\sat\\s(\\d+\\.(\\d){2})";
	public static final String IMPORTED_STRING = "imported ";

	/**
	 * @param String aGood
	 * @return The Good bean created from the input aGood
	 * @throws NoInputGoodException
	 * @throws NoMatchInputException
	 */
	public static Good getGood(String aGood) throws NoInputGoodException, NoMatchInputException {
		if(aGood == null || aGood.isEmpty()) {
			throw new NoInputGoodException();
		}
		_log.debug(">> getGood(aGood={})", aGood);
		final Pattern pattern = Pattern.compile(PATTERN_REGEXP);
		final Matcher matcher = pattern.matcher(aGood);
		boolean isMatch = matcher.matches();
		if(!isMatch) {
			throw new NoMatchInputException();
		}

		int quantity = Integer.parseInt(matcher.group(1));
		String productName = matcher.group(2);
		BigDecimal netPrice = new BigDecimal(matcher.group(3));

		GoodTypeList type = GoodTypeList.fromValue(productName);
		Good tmpGood = GoodBuilder.newBuilder()
				.quantity(quantity)
				.type(type)
				.isImported(isImported(productName))
				.productName(formatProductName(productName))
				.build();

		Price price = PriceBuilder.newBuilder()
				.originalPrice(netPrice)
				.rate(tmpGood)
				.salesTaxAmount(tmpGood)
				.build();

		tmpGood.setPrice(price);
		_log.debug("<< getGood tmpGood={})", tmpGood.toString());
		return tmpGood;
	}

	/**
	 * @param String productName
	 * @return true if the product is imported, false otherwise
	 */
	private static boolean isImported(String productName) {
		_log.debug(">> isImported(productName={})", productName);
		boolean isImported = false;
		if(productName != null && !productName.isEmpty()) {
			isImported=productName.contains(IMPORTED_STRING);
		}
		_log.debug("<< isImported: isImported={}", isImported);
		return isImported;
	}

	/**
	 * @param String productName
	 * @return A String properly formatted for imported goods
	 */
	public static String formatProductName(String productName) {
		_log.debug(">> formatProductName(productName={})", productName);
		String formattedProductName = productName;
		if(productName != null && !productName.isEmpty()) {
			if(isImported(productName) && productName.indexOf(IMPORTED_STRING) != 0) {
				formattedProductName = IMPORTED_STRING + productName.replace(IMPORTED_STRING, "");
			}
		}
		_log.debug("<< formatProductName: formattedProductName={}", formattedProductName);
		return formattedProductName;
	}

	/**
	 * @param List<Good> goodsList
	 * @return The number of imported goods
	 */
	public static int countImportedGoods(List<Good> goodsList) {
		_log.debug(">> countImportedGoods(goodsList={})", goodsList);
		int importedGood = 0;
		if(goodsList != null && goodsList.size() > 0) {
			for(Good aGood : goodsList) {
				if(aGood.isImported()) {
					importedGood = importedGood + 1;
				}
			}
		}
		_log.debug("<< countImportedGoods: importedGood={}", importedGood);
		return importedGood;
	}
}
