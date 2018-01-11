package co.uk.app.commerce.catalog.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferPrice {

	private String currency;
	
	private Double price;
	
	private String status;
	
	private String startdate;
	
	private String enddate;
}
