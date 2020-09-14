package it.lastminute.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.lastminute.beans.Good;
import it.lastminute.beans.Receipt;
import it.lastminute.builder.ReceiptBuilder;
import it.lastminute.exceptions.NoInputGoodException;
import it.lastminute.exceptions.NoMatchInputException;
import it.lastminute.helper.GoodsHelper;

public class PriceTest {

	private static final Logger _log = LoggerFactory.getLogger(PriceTest.class);

	public static final String INPUT_1 = "input/price/inputTest1";


	@Test
	void testPrice1() throws IOException, URISyntaxException {
		_log.debug("> Starting testPrice1");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_1).toURI()), StandardCharsets.US_ASCII);
		List<Good> goodsList = new ArrayList<Good>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Good aGood = GoodsHelper.getGood(tmpLine);
				goodsList.add(aGood);
			}
			catch (NoInputGoodException | NoMatchInputException ex) {
				_log.error("!! An Exception occurred", ex);
			}
		}

		Receipt receipt = ReceiptBuilder.newBuilder()
				.goodsList(goodsList)
				.build();


		_log.debug("> Checking total price and total sales tax");
		assertEquals(new BigDecimal(260.75).setScale(2), receipt.getTotalPrice());
		assertEquals(new BigDecimal(13.50).setScale(2), receipt.getTotalSalesTax());
	}
}
