package ApiUtilities;

public class ConsentResponse {
	
	  private Links[] links;

	    private String message;

		public Links[] getLinks ()
	    {
	        return links;
	    }

	    public void setLinks (Links[] links)
	    {
	        this.links = links;
	    }

	    public String getMessage ()
	    {
	        return message;
	    }

	    public void setMessage (String message)
	    {
	        this.message = message;
	    }

	    @Override
	    public String toString()
	    {
	        return "ConsentResponse [links = "+links+", message = "+message+"]";
	    }

}
