package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.PasesSsiccDAO;

/**
 * @author Danny Amaro
 *
 */
public class PasesSsiccDAOTest extends BaseDAOTest{

	@Autowired
	private PasesSsiccDAO pasesSsiccDAO;
	
	@Test
	public void getListaPaisMarcaTest(){
		List lista = this.pasesSsiccDAO.getListaPaisMarca();
		log.debug("lista size : "+lista.size());
		assertNotNull(lista);
	}
	
}
