package co.uk.app.commerce.catalog.common.bean;

public class Association {

	private String name;

	private String identifier;

	private String url;

	private String type;

	private Double sequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

}
