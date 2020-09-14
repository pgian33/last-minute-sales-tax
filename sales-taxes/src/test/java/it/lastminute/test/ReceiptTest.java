package it.lastminute.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
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

public class ReceiptTest {

	private static final Logger _log = LoggerFactory.getLogger(ReceiptTest.class);
	public static final String INPUT_1 = "input/receipt/inputTest1";
	public static final String INPUT_2 = "input/receipt/inputTest2";
	public static final String INPUT_3 = "input/receipt/inputTest3";

	public static final String OUTPUT_1 = "output/outputTest1";
	public static final String OUTPUT_2 = "output/outputTest2";
	public static final String OUTPUT_3 = "output/outputTest3";

	@Test
	void testReceipt1() throws IOException, URISyntaxException {
		_log.debug("> Starting testReceipt1");
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

		String myReceipt = receipt.getReceipt();
		String content = new String (Files.readAllBytes(Paths.get(classLoader.getResource(OUTPUT_1).toURI())));
		_log.debug("Check equals:\nmyReceipt=\n{} \ncontent=\n{}",  myReceipt, content);
		assertEquals(myReceipt, content);

	}

	@Test
	void testReceipt2() throws IOException, URISyntaxException {
		_log.debug("> Starting testReceipt2");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_2).toURI()), StandardCharsets.US_ASCII);
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

		String myReceipt = receipt.getReceipt();
		String content = new String (Files.readAllBytes(Paths.get(classLoader.getResource(OUTPUT_2).toURI())));
		_log.debug("Check equals:\nmyReceipt=\n{} \ncontent=\n{}",  myReceipt, content);
		assertEquals(myReceipt, content);
	}

	@Test
	void testReceipt3() throws IOException, URISyntaxException {

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_3).toURI()), StandardCharsets.US_ASCII);
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

		String myReceipt = receipt.getReceipt();
		String content = new String (Files.readAllBytes(Paths.get(classLoader.getResource(OUTPUT_3).toURI())));
		_log.debug("Check equals:\nmyReceipt=\n{} \ncontent=\n{}",  myReceipt, content);
		assertEquals(myReceipt, content);
	}
}
