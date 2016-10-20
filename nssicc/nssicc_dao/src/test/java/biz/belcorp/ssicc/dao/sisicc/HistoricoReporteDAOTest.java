package biz.belcorp.ssicc.dao.sisicc;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.util.DateUtil;


/**
 * @author Danny Amaro
 *
 */
public class HistoricoReporteDAOTest extends BaseDAOTest{

	@Autowired
	HistoricoReporteDAO historicoReporteDAO;
	
	@Test
	public void getDevuelveIdSgteCodHistoricoReporteTest(){
		Long id = this.historicoReporteDAO.getDevuelveIdSgteCodHistoricoReporte();
		log.debug("Id next :"+id);
		assertNotNull(id);
	}
	
	@Test
	public void getHistoricoReporteByUserTest(){
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", "ADMIN");
		log.debug("Size Reportes by Admin : "+this.historicoReporteDAO.getHistoricoReporteByUser(criteria).size());
		assertNotNull(this.historicoReporteDAO.getHistoricoReporteByUser(criteria).size());		
		String fecha = DateUtil.getDate(new Date());
		criteria.put("fechaInicio", fecha);
		log.debug("Size Reportes by Admin and Date : "+this.historicoReporteDAO.getHistoricoReporteByUser(criteria).size());
		assertNotNull(this.historicoReporteDAO.getHistoricoReporteByUser(criteria).size());
	}
	
}
