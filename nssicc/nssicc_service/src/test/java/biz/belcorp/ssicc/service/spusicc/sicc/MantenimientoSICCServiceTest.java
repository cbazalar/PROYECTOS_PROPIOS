package biz.belcorp.ssicc.service.spusicc.sicc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.service.BaseServiceTest;

public class MantenimientoSICCServiceTest extends BaseServiceTest{

	@Autowired
	private MantenimientoSICCService mantenimientoSICCService;
	
	@Test
	public void getListaSICCUsuarioByCriteriaTest(){
		List lista = this.mantenimientoSICCService.getListaSICCUsuarioByCriteria(null);				
		log.debug("lista size : "+lista.size());
		assertNotNull(lista);		
	}
	
	@Test
	public void testResources(){		
		String key = "confirm.execute.process";
		System.out.println(this.mantenimientoSICCService.getKeyMessage(key));
		assertNotNull(this.mantenimientoSICCService.getKeyMessage(key));		
	}
	
}
