package biz.belcorp.ssicc.service.spusicc.ventas;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;
import biz.belcorp.ssicc.service.BaseServiceTest;

public class PorcentajeIgvVENServiceTest extends BaseServiceTest{
	
	@Autowired
	private PorcentajeIgvVENService porcentajeIgvVENService;
	
	@Test
	public void getPorcentajeIgvTest(){		
		PorcentajeIgv porcentajeIgv = new PorcentajeIgv();
		porcentajeIgv.setCodigoPais("PE");
		List lista = this.porcentajeIgvVENService.getPorcentajeIgv(porcentajeIgv);
		log.debug("Lista size : " + lista.size());
		assertNotNull(lista);
		
	}

}
