package com.buccodev.bookstore.entity.enuns;

public enum StatusPayment {
	
	PAID(0), WAINTING_PAYMENT(1), SHIPPED(3), DELIVERED(4), CANCELED(5);

	private Integer code;

	StatusPayment(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static StatusPayment valueOf(Integer code){

		for (StatusPayment value : StatusPayment.values()){
			if(value.getCode() == code){
				return value;
			}
		}
		throw  new IllegalArgumentException("invalid Category code!");
	}

}
