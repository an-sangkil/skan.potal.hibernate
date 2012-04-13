/*
 * $Id: KeyGenerator.java ,v 1.1 2010. 8. 18. 오후 5:12:57 smrscvs1 Exp $
 * created by    : ahn
 * creation-date : 2010. 8. 18.
 * =========================================================
 * Copyright (c) 2010 ManInSoft, Inc. All rights reserved.
 */
package com.dongbu.farm.common.utils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * <pre>
 * 중복되지 않는 자동 키생성
 * </pre>
 * 
 * @author ahn
 *
 */
public class KeyGenerators {
	
	/**
	 * UUID Create
	 * @return
	 */
	public static synchronized String getUUIDKey(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * TIME key Generator Create
	 * @return
	 */
	public static synchronized String getTIMEKey(){
		return "";
	}
	
	public static void main (String []args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException{
		//UUID TEST
		UUID uuid = UUID.randomUUID();
		String result = uuid.toString();
		System.out.println(result);
		
		//TIME TEST 4f39bbee8502ad945472658d076410c3 

		/*KeyGenerator kg = KeyGenerator.getInstance ("HmacMD5") ; 
	    //kg.init (10) ;  
		SecretKey key1 = kg.generateKey (  ) ; 
				      
		System.out.println ( "Generated Key:: " + key1.toString() ) ; 
		System.out.println ( key1.getEncoded() );
		System.out.println ( key1.getEncoded() );


		 FileOutputStream out = new FileOutputStream ( "secret.key" ) ; 
		     ObjectOutputStream oOs1 = new ObjectOutputStream ( out ) ; 
		     oOs1.writeObject ( key1 ) ; 
		     oOs1.flush (  ) ; 
		     oOs1.close (  ) ; 
		  
		  
		   System.out.println ( "Serialize key into secret.key"+oOs1 ) ; 
		  
		  
		   FileInputStream in = new FileInputStream ( "secret.key" ) ; 
		     ObjectInputStream oOs2 = new ObjectInputStream ( in ) ; 
		     SecretKey key2 =  ( SecretKey ) oOs2.readObject (  ) ; 
		  
		  
		   System.out.println ( "DeSerialize key from secret.key"+key2 );*/
		   
			// Generate a DES key
		    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		    SecretKey key = keyGen.generateKey();
		    System.out.println(key);
		    // Generate a Blowfish key
		    keyGen = KeyGenerator.getInstance("Blowfish");
		    key = keyGen.generateKey();
		    System.out.println(key);

		    // Generate a triple DES key
		    keyGen = KeyGenerator.getInstance("DESede");
		    key = keyGen.generateKey();
		    System.out.println(key);


		//Read more: http://kickjava.com/2658.htm#ixzz0wwskbei4

		
	}
}
