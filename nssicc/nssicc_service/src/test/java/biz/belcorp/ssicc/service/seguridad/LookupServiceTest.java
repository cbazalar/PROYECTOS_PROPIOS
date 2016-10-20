package biz.belcorp.ssicc.service.seguridad;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.BaseServiceTest;
import biz.belcorp.ssicc.service.LookupService;

public class LookupServiceTest extends BaseServiceTest{

	@Autowired
	private LookupService lookupService;
	
	@Test
	public void getAllPaisesTest(){
		List<Pais> listPaises = (List<Pais>)lookupService.getAllPaises();
		logger.info("Lista de Paises : "+ listPaises.size());
		assertNotNull(listPaises);
	}
}
