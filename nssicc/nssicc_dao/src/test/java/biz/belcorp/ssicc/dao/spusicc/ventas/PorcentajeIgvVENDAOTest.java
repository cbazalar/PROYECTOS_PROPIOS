package biz.belcorp.ssicc.dao.spusicc.ventas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;

public class PorcentajeIgvVENDAOTest extends BaseDAOTest{
	
	@Autowired
	private PorcentajeIgvVENDAO porcentajeIgvVENDAO;
	
	@Test
	public void getPorcentajeIgvTest(){
		PorcentajeIgv porcentajeIgv = new PorcentajeIgv();
		porcentajeIgv.setCodigoPais("PE");
		List lista = this.porcentajeIgvVENDAO.getPorcentajeIgv(porcentajeIgv);
		log.debug("Lista size : " + lista.size());
		assertNotNull(lista);
	}

}
