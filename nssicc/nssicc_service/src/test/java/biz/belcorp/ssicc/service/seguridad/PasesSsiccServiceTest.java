package biz.belcorp.ssicc.service.seguridad;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.service.BaseServiceTest;
import biz.belcorp.ssicc.service.PasesSsiccService;

public class PasesSsiccServiceTest extends BaseServiceTest{

	@Autowired 
	private PasesSsiccService pasesSsiccService;
	
	@Test 
	public void getListaPaisMarcaTest(){
		List lista = this.pasesSsiccService.getListaPaisMarca();
		log.debug("lista size : "+lista.size());
		assertNotNull(lista);
	}
	
}
