package com.buccodev.bookstore.entity.enuns;

public enum PayMethod {
	
	DEBIT(0), CREDIT(1);

	private Integer code;

	PayMethod(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static PayMethod valueOf(Integer code){

		for(PayMethod value : PayMethod.values()){
			if(value.getCode() == code){
				return value;
			}
		}
		throw new IllegalArgumentException("invalid Category code");
	}
}
