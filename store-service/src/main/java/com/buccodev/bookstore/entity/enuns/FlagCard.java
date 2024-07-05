package com.buccodev.bookstore.entity.enuns;

public enum FlagCard {

	VISA(0), MASTER_CARD(1), ELO(2);

	private Integer code;

	FlagCard(Integer code) {
	}

	public Integer getCode() {
		return code;
	}

	public static FlagCard valueOf(Integer code) {

		for (FlagCard value : FlagCard.values()) {

			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("invalid Category code!");
	}
}
