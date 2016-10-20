/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;

/**
 * @author Danny Amaro
 *
 */
public class HistoricoAuditoriaDAOTest extends BaseDAOTest{
	
	@Autowired
	HistoricoAuditoriaDAO historicoAuditoriaDAO;
	
	@Test
	public void getDevuelveIdSgteCodHistoricoReporteTest(){
		Long id = this.historicoAuditoriaDAO.getDevuelveIdSgteCodHistoricoAuditoria();				
		log.debug("Id next :"+id);
		assertNotNull(id);
	}
	
	@Test
	public void getHistoricoReporteByUserTest(){
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", "ADMIN");
		log.debug("Size Reportes by Admin : "+this.historicoAuditoriaDAO.getHistoricoAuditoriaByUser(criteria).size());
		assertEquals(this.historicoAuditoriaDAO.getHistoricoAuditoriaByUser(criteria).size(), 0);		
	}

}
