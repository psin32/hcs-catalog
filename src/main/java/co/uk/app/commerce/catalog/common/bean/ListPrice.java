package co.uk.app.commerce.catalog.common.bean;

import co.uk.app.commerce.catalog.util.PriceFormattingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListPrice {

	private String currency;

	private Double price;

	private String formattedPrice;

	public String getFormattedPrice() {
		formattedPrice = PriceFormattingUtil.formatPrice(this.price);
		return formattedPrice;
	}
}
