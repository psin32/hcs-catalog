package co.uk.app.commerce.catalog.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attributes {

	private String name;

	private String value;

	private AttributesType type;

	private Double sequence;

	private AttributeDisplayType displayType;
}
