package ApiUtilities;

public class BankIdResponse {

	private String countryCode;

	private String bankingGroupName;

	private String personalIdentificationRequired;

	private String id;

	private String bankDisplayName;

	private String[] supportedServices;

	private String bic;

	private String bankOfficialName;

	private String status;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getBankingGroupName() {
		return bankingGroupName;
	}

	public void setBankingGroupName(String bankingGroupName) {
		this.bankingGroupName = bankingGroupName;
	}

	public String getPersonalIdentificationRequired() {
		return personalIdentificationRequired;
	}

	public void setPersonalIdentificationRequired(String personalIdentificationRequired) {
		this.personalIdentificationRequired = personalIdentificationRequired;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankDisplayName() {
		return bankDisplayName;
	}

	public void setBankDisplayName(String bankDisplayName) {
		this.bankDisplayName = bankDisplayName;
	}

	public String[] getSupportedServices() {
		return supportedServices;
	}

	public void setSupportedServices(String[] supportedServices) {
		this.supportedServices = supportedServices;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBankOfficialName() {
		return bankOfficialName;
	}

	public void setBankOfficialName(String bankOfficialName) {
		this.bankOfficialName = bankOfficialName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [countryCode = " + countryCode + ", bankingGroupName = " + bankingGroupName
				+ ", personalIdentificationRequired = " + personalIdentificationRequired + ", id = " + id
				+ ", bankDisplayName = " + bankDisplayName + ", supportedServices = " + supportedServices + ", bic = "
				+ bic + ", bankOfficialName = " + bankOfficialName + ", status = " + status + "]";
	}

}
