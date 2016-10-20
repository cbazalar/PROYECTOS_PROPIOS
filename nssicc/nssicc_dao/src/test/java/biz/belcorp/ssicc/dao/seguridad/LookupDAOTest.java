package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.LookupDAO;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.model.Pais;

public class LookupDAOTest extends BaseDAOTest{

	@Autowired
	private LookupDAO lookupDAO;
		
	@Autowired
	private PaisDAO paisDAO;
		
	@Test
	public void getPaisesTest(){
		assert(true);
	}
	
	@Test
	public void insertPaisTest (){
		log.debug("Inicio metodo insert");		
		//paisDAO.insertPais(new Pais(), new Usuario());
		log.debug("Fin metodo insert");
	}

	@Test
	public void updatePaisTest (){
		log.debug("Inicio metodo update");		
		//paisDAO.updatePais(new Pais(), new Usuario());
		log.debug("Fin metodo update");
	}
	
}
