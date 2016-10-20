package biz.belcorp.ssicc.dao.framework.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @author David Hinostroza Vintes
 *
 */

public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	protected Object determineCurrentLookupKey() {
	      return CustomerContextHolder.getCustomerType();
	   }

}

