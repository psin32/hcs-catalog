package co.uk.app.commerce.catalog.common.bean;

import co.uk.app.commerce.catalog.util.PriceFormattingUtil;

public class ListPrice {

	private String currency;

	private Double price;

	private String formattedPrice;

	public String getFormattedPrice() {
		formattedPrice = PriceFormattingUtil.formatPrice(this.price);
		return formattedPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
