package biz.belcorp.ssicc.service.spusicc.ventas;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.service.BaseServiceTest;

public class ProcesoVENServiceTest extends BaseServiceTest{
	
	@Autowired
	private ProcesoVENService procesoVENService;
	
	@Test
	public void getFeriadoZonaTest(){
		List lista = this.procesoVENService.getFeriadoZona(null);
		log.info("Lista size : "+lista.size());
		assertNotNull(lista);
	}

}
