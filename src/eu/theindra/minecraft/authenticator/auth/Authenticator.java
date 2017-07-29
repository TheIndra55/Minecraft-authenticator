package eu.theindra.minecraft.authenticator.auth;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.theindra.minecraft.authenticator.objects.Payload;

public class Authenticator {

	/**
	 * This class can be used to authenticate at the mojang authservers,
	 * the authenticate method will return an response as an jsonobject
	 * 
	 * Special thanks to http://wiki.vg/Authentication
	 * 
	 * @author TheIndra
	 */
	
	private String username;
	private String password;
	
	public String authLink = "https://authserver.mojang.com/authenticate";
	
	/**
	 * @param username	Minecraft username (email)
	 * @param password	Minecraft password
	 */
	public Authenticator(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	
	/**
	 * Sends an request to authenticate to the minecraft authservers
	 * @return	Minecraft authserver response as jsonobject
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public JsonObject Authenticate() throws MalformedURLException, IOException{
		Gson gson = new Gson();
		String json = gson.toJson(new Payload(username, password));
		
		HttpsURLConnection con = (HttpsURLConnection) new URL(authLink).openConnection();
		
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        
        OutputStream out = con.getOutputStream();
        out.write(json.getBytes());
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String output = "";
        String line = null;
        while ((line = in.readLine()) != null)
            output += line;

        out.close();
        in.close();

        return gson.fromJson(output, JsonObject.class);
	}
	
}
