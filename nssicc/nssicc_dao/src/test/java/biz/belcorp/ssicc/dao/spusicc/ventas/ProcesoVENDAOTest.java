package biz.belcorp.ssicc.dao.spusicc.ventas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;

/**
 * @author Danny Amaro
 *
 */
public class ProcesoVENDAOTest extends BaseDAOTest{
	
	@Autowired
	private ProcesoVENDAO procesoVENDAO;

	@Test
	public void getFeriadoZonaTest(){
		List lista = this.procesoVENDAO.getFeriadoZona(null);
		log.info("Lista size : "+lista.size());
		assertNotNull(lista);
	}
}
