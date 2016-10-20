package biz.belcorp.ssicc.service.spusicc.ventas;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.service.BaseServiceTest;

public class GrupoZonaVENServiceTest extends BaseServiceTest{
	
	@Autowired
	private GrupoZonaVENService grupoZonaVENService;
	
	@Test
	public void getRegionTest(){
		List lista = this.grupoZonaVENService.getRegion(null);
		log.debug("Lista size : "+lista.size());
		assertNotNull(lista);
	}

}
