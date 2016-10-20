/**
 * 
 */
package biz.belcorp.ssicc.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Danny Amaro
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/biz/belcorp/ssicc/dao/applicationContext-dao.xml"})
public class BaseDAOTest extends AbstractTransactionalJUnit4SpringContextTests{

	public final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Method testApp.
	 */
	@Test
	public void testApp() {
		assert(true);
	}
}