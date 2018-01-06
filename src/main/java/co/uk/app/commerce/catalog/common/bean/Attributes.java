package co.uk.app.commerce.catalog.common.bean;

public class Attributes {

	private String name;

	private String value;

	private AttributesType type;

	private Double sequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AttributesType getType() {
		return type;
	}

	public void setType(AttributesType type) {
		this.type = type;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

}
