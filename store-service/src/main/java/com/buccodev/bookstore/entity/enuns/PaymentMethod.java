package com.buccodev.bookstore.entity.enuns;

public enum PaymentMethod {
	
	DEBIT(0), CREDIT(1), PIX(2), TICKET(3);

	private Integer code;

	PaymentMethod(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static PaymentMethod valueOf(Integer code){

		for( PaymentMethod value : PaymentMethod.values()){
			if ( value.getCode() == code){
				return value;
			}
		}

		throw new IllegalArgumentException("invalid Category code!");

	}


}
