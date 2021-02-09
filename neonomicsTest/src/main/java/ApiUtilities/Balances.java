package ApiUtilities;

public class Balances {
	private String amount;

	private String currency;

	private String type;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ClassPojo [amount = " + amount + ", currency = " + currency + ", type = " + type + "]";
	}

}
