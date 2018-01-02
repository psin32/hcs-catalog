package co.uk.app.commerce.catalog.util;

import java.text.DecimalFormat;

public class PriceFormattingUtil {

	private static DecimalFormat df = new DecimalFormat("#0.00");

	public static String formatPrice(Double price) {
		String formattedPrice = df.format(price);
		return formattedPrice;
	}
}
