package com.buccodev.bookstore.entity.enuns;

public enum Category {

	FICTION(0), NON_FICTION(1), ROMANCE(2), THRILLER(3), BIOGRAPHY(4), HISTORY(5), AUTOBIOGRAPH(6), POETRY(7),
	CHILDREN(8);

	private Integer code;

	Category(Integer id) {
		this.code = id;
	}

	public int getCode() {
		return code;
	}

	public static Category valueOf(Integer code) {
		for (Category value : Category.values()) {
			if (value.getCode() == code) {
				return value;
			}

		}
		throw new IllegalArgumentException("invalid Category code!");
	}

}
