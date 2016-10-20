package biz.belcorp.ssicc.dao.framework.util;

import org.springframework.util.Assert;

/**
 * 
 * @author David Hinostroza Vintes
 *
 */

public class CustomerContextHolder {
	
	private static final ThreadLocal contextHolder = new ThreadLocal();
	
	 /**
	 * @param customerType
	 */
	public static void setCustomerType(TypesafeEnum customerType) {
	      Assert.notNull(customerType, "customerType cannot be null");
	      contextHolder.set(customerType);
     }
	 
	 /**
	 * @return
	 */
	public static TypesafeEnum getCustomerType() {
	      return (TypesafeEnum) contextHolder.get();
	 }
	 
	 
	/**
	 * 
	 */
	public static void clearCustomerType() {
	      contextHolder.set(null);
	 }



}

