package it.lastminute.enumerations;

import java.util.Arrays;

public enum GoodTypeList {

	BOOK_TYPE("book", new String [] {"book"}),
	MEDICAL_TYPE("medical", new String [] {"headache pills"}),
	FOOD_TYPE("food", new String [] {"chocolate bar", "chocolates"}),
	OTHER_TYPE("other", new String [] {"music CD", "perfume"});

	private GoodTypeList(String type, String [] elements) {
		this.type = type;
		this.elements = elements;
	}

	private String type;
	private String [] elements;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String[] getElements() {
		return elements;
	}
	public void setElements(String[] elements) {
		this.elements = elements;
	}

	/**
	 * @param String text
	 * @return The type corresponding to the parameter input
	 */
	public static GoodTypeList fromValue(String text) {
		for (GoodTypeList aType : GoodTypeList.values()) {
			if(Arrays.stream(aType.elements).anyMatch(text::contains)) {
				return aType;
			}
		}
		return OTHER_TYPE;
	}
}
