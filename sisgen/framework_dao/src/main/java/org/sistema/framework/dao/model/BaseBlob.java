package org.sistema.framework.dao.model;

import java.io.UnsupportedEncodingException;

/**
 * 
 * <p>
 * <a href="BaseBlob.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 */
public class BaseBlob {
    
    /**
     * Holds value of property data.
     */
    private byte[] data ;
    String result;


    //Standard accessors and mutators

   public byte[] getData() {
       return this.data;
   }

   public void setData(byte[] data) {
       this.data = data;
   }
   
   public void setBodyAsByteArray(byte[] b) {
	   try {
		   result = new String(b,"ISO-8859-1");
	   } 
	   catch (UnsupportedEncodingException e) {
		   result = new String(b);
		   e.printStackTrace();
	   } 
   }

	public byte[] getBodyAsByteArray() {
	   return result.getBytes();
   }

}