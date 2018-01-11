package co.uk.app.commerce.catalog.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Association {

	private String name;

	private String identifier;

	private String url;

	private String type;

	private Double sequence;
}
