package ApiUtilities;

public class AccountResponse {
	
	private Balances[] balances;

    private String accountName;

    private String iban;

    private String accountType;

    private String id;

    public Balances[] getBalances ()
    {
        return balances;
    }

    public void setBalances (Balances[] balances)
    {
        this.balances = balances;
    }

    public String getAccountName ()
    {
        return accountName;
    }

    public void setAccountName (String accountName)
    {
        this.accountName = accountName;
    }

    public String getIban ()
    {
        return iban;
    }

    public void setIban (String iban)
    {
        this.iban = iban;
    }

    public String getAccountType ()
    {
        return accountType;
    }

    public void setAccountType (String accountType)
    {
        this.accountType = accountType;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [balances = "+balances+", accountName = "+accountName+", iban = "+iban+", accountType = "+accountType+", id = "+id+"]";
    }

}
