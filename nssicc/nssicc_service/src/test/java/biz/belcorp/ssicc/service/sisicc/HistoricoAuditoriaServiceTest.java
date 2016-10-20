/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.BaseServiceTest;
import biz.belcorp.ssicc.service.HistoricoAuditoriaService;

/**
 * @author Danny Amaro
 *
 */
public class HistoricoAuditoriaServiceTest extends BaseServiceTest {
	
	@Autowired
	private HistoricoAuditoriaService historicoAuditoriaService;
	
	@Test
	public void getHistoricoReporteByUserTest(){
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", "ADMIN");						
		criteria.put("fechaInicio", DateUtil.getDate(new Date()));
		List list = this.historicoAuditoriaService.getHistoricoReporteByUser(criteria);
		log.debug("Size Process by Admin : "+list.size());				
		assertNotNull(list);		
	}

}
