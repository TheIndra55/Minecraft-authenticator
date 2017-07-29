package eu.theindra.minecraft.authenticator.objects;

public class Payload {

	/**
	 * http://wiki.vg/Authentication#Payload
	 */
	
	public Agent agent = new Agent();
	
	public String username;
	public String password;
	
	public Payload(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	
	
}
