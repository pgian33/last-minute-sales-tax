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
import it.lastminute.exceptions.NoInputGoodException;
import it.lastminute.exceptions.NoMatchInputException;
import it.lastminute.helper.GoodsHelper;

public class GoodTest {

	private static final Logger _log = LoggerFactory.getLogger(GoodTest.class);

	public static final String INPUT_1 = "input/good/inputTest1";
	public static final String INPUT_2 = "input/good/inputTest2";
	public static final String INPUT_3 = "input/good/inputTest3";
	public static final String INPUT_4 = "input/good/inputTest4";

	@Test
	void testGood1() throws IOException, URISyntaxException {
		_log.debug("> Starting testGood1");

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
		_log.debug("> Checking size and imported item");
		assertEquals(3, goodsList.size());
		assertEquals(0, GoodsHelper.countImportedGoods(goodsList));

	}

	@Test
	void testGood2() throws IOException, URISyntaxException {
		_log.debug("> Starting testGood2");
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
		_log.debug("> Checking size and imported item");
		assertEquals(4, goodsList.size());
		assertEquals(2, GoodsHelper.countImportedGoods(goodsList));

	}

	@Test
	void testGood3() throws IOException, URISyntaxException {
		_log.debug("> Starting testGood3");
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
				assertEquals(NoInputGoodException.class, ex.getClass());
				assertEquals("No good found", ex.getMessage());
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(2, goodsList.size());
		assertEquals(2, GoodsHelper.countImportedGoods(goodsList));
	}

	@Test
	void testGood4() throws IOException, URISyntaxException {
		_log.debug("> Starting testGood4");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_4).toURI()), StandardCharsets.US_ASCII);
		List<Good> goodsList = new ArrayList<Good>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Good aGood = GoodsHelper.getGood(tmpLine);
				goodsList.add(aGood);
			}
			catch (NoInputGoodException | NoMatchInputException ex) {
				assertEquals(NoMatchInputException.class, ex.getClass());
				assertEquals("Format your input record properly", ex.getMessage());
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(2, goodsList.size());
		assertEquals(2, GoodsHelper.countImportedGoods(goodsList));
	}
}
