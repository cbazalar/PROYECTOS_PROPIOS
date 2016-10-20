/**
 * 
 */
package biz.belcorp.ssicc.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TODO Include class description here.
 * <p>
 * <a href="JdbcDaoSupport.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class JdbcDaoSupport {
	
    private JdbcTemplate jdbcTemplate;
   
    
    /**
	 * @return Returns the jdbcTemplate.
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate The jdbcTemplate to set.
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
