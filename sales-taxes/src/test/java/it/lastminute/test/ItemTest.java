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

import it.lastminute.beans.Item;
import it.lastminute.exceptions.NoInputItemException;
import it.lastminute.exceptions.NoMatchInputException;
import it.lastminute.helper.ItemHelper;

public class ItemTest {

	private static final Logger _log = LoggerFactory.getLogger(ItemTest.class);

	public static final String INPUT_1 = "input/item/inputTest1";
	public static final String INPUT_2 = "input/item/inputTest2";
	public static final String INPUT_3 = "input/item/inputTest3";
	public static final String INPUT_4 = "input/item/inputTest4";

	@Test
	void testItem1() throws IOException, URISyntaxException {
		_log.debug("> Starting testItem1");

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_1).toURI()), StandardCharsets.US_ASCII);
		List<Item> itemsList = new ArrayList<Item>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Item anItem = ItemHelper.getItem(tmpLine);
				itemsList.add(anItem);
			}
			catch (NoInputItemException | NoMatchInputException ex) {
				_log.error("!! An Exception occurred", ex);
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(3, itemsList.size());
		assertEquals(0, ItemHelper.countImportedItems(itemsList));

	}

	@Test
	void testItem2() throws IOException, URISyntaxException {
		_log.debug("> Starting testItem2");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_2).toURI()), StandardCharsets.US_ASCII);
		List<Item> itemsList = new ArrayList<Item>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Item anItem = ItemHelper.getItem(tmpLine);
				itemsList.add(anItem);
			}
			catch (NoInputItemException | NoMatchInputException ex) {
				_log.error("!! An Exception occurred", ex);
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(4, itemsList.size());
		assertEquals(2, ItemHelper.countImportedItems(itemsList));

	}

	@Test
	void testItem3() throws IOException, URISyntaxException {
		_log.debug("> Starting testItem3");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_3).toURI()), StandardCharsets.US_ASCII);
		List<Item> itemsList = new ArrayList<Item>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Item anItem = ItemHelper.getItem(tmpLine);
				itemsList.add(anItem);
			}
			catch (NoInputItemException | NoMatchInputException ex) {
				assertEquals(NoInputItemException.class, ex.getClass());
				assertEquals("No item found", ex.getMessage());
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(2, itemsList.size());
		assertEquals(2, ItemHelper.countImportedItems(itemsList));
	}

	@Test
	void testItem4() throws IOException, URISyntaxException {
		_log.debug("> Starting testItem4");
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		List<String> lines = Files.readAllLines(Paths.get(classLoader.getResource(INPUT_4).toURI()), StandardCharsets.US_ASCII);
		List<Item> itemsList = new ArrayList<Item>();

		for(String aLine:lines) {
			String tmpLine = aLine.trim();
			try {
				Item anItem = ItemHelper.getItem(tmpLine);
				itemsList.add(anItem);
			}
			catch (NoInputItemException | NoMatchInputException ex) {
				assertEquals(NoMatchInputException.class, ex.getClass());
				assertEquals("Format your input record properly", ex.getMessage());
			}
		}
		_log.debug("> Checking size and imported item");
		assertEquals(2, itemsList.size());
		assertEquals(2, ItemHelper.countImportedItems(itemsList));
	}
}
