package eu.theindra.minecraft.authenticator;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.JsonObject;

import eu.theindra.minecraft.authenticator.auth.Authenticator;

public class Main {

	public static void main(String[] args){
		if(args.length < 4){
			System.out.println("Please provide the following arguments: -username <email> -password <pass>");
			return;
		}
		
		String username = null;
		String password = null;
		
		// shitty way
		int count = 0;
		for(String arg : args){
			if(arg.equalsIgnoreCase("-username")){
				username = args[count + 1];
			}else if(arg.equalsIgnoreCase("-password")){
				password = args[count + 1];
			}
			
			count++;
		}
		
		if(username == null || password == null){
			System.out.println("Please provide the following arguments: -username <email> -password <pass>");
			return;
		}
		
		Authenticator auth = new Authenticator(username, password);
		System.out.println("Making POST request to mojang authservers...");
		
		JsonObject json = null;
		
		try {
			json = auth.Authenticate();
		} catch (MalformedURLException e) {
			// should never happen
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
		
		// return some info :)
		System.out.println( "accesToken: " + json.get("accessToken") + System.lineSeparator() + "clientToken: " + json.get("clientToken"));
				
	}
	
}
