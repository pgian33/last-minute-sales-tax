package it.lastminute.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Item;
import it.lastminute.beans.Price;
import it.lastminute.builder.ItemBuilder;
import it.lastminute.builder.PriceBuilder;
import it.lastminute.enumerations.ItemTypeList;
import it.lastminute.exceptions.NoInputItemException;
import it.lastminute.exceptions.NoMatchInputException;

public class ItemHelper {

	private static final Logger _log = LoggerFactory.getLogger(ItemHelper.class);
	public static final String PATTERN_REGEXP = "(\\d)\\s([a-zA-z\\s]*)\\sat\\s(\\d+\\.(\\d){2})";
	public static final String IMPORTED_STRING = "imported ";

	/**
	 * @param String anItem
	 * @return The Item bean created from the input anItem
	 * @throws NoInputItemException
	 * @throws NoMatchInputException
	 */
	public static Item getItem(String anItem) throws NoInputItemException, NoMatchInputException {
		if(anItem == null || anItem.isEmpty()) {
			throw new NoInputItemException();
		}
		_log.debug(">> getItem(anItem={})", anItem);
		final Pattern pattern = Pattern.compile(PATTERN_REGEXP);
		final Matcher matcher = pattern.matcher(anItem);
		boolean isMatch = matcher.matches();
		if(!isMatch) {
			throw new NoMatchInputException();
		}

		int quantity = Integer.parseInt(matcher.group(1));
		String productName = matcher.group(2);
		BigDecimal netPrice = new BigDecimal(matcher.group(3));

		ItemTypeList type = ItemTypeList.fromValue(productName);
		Item tmpItem = ItemBuilder.newBuilder()
				.quantity(quantity)
				.type(type)
				.isImported(isImported(productName))
				.productName(formatProductName(productName))
				.build();

		Price price = PriceBuilder.newBuilder()
				.originalPrice(netPrice)
				.rate(tmpItem)
				.salesTaxAmount(tmpItem)
				.build();

		tmpItem.setPrice(price);
		_log.debug("<< getItem tmpItem={})", tmpItem.toString());
		return tmpItem;
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
	 * @return A String properly formatted for imported items
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
	 * @param List<Item> itemsList
	 * @return The number of imported items
	 */
	public static int countImportedItems(List<Item> itemsList) {
		_log.debug(">> countImportedItems(itemsList={})", itemsList);
		int importedItems = 0;
		if(itemsList != null && itemsList.size() > 0) {
			for(Item anItem : itemsList) {
				if(anItem.isImported()) {
					importedItems = importedItems + 1;
				}
			}
		}
		_log.debug("<< countImportedItems: importedItems={}", importedItems);
		return importedItems;
	}
}
