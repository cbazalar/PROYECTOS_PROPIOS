package biz.belcorp.ssicc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/biz/belcorp/ssicc/dao/applicationContext-dao.xml",
								 "classpath:/biz/belcorp/ssicc/service/applicationContext-service.xml"})
public class BaseServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	public final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Method testApp.
	 */
	@Test
	public void testApp() {
		assert(true);
	}

}
