package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.RolDAO;

/**
 * @author Danny Amaro
 *
 */
public class RolDAOTest extends BaseDAOTest{
	
	@Autowired
	private RolDAO rolDAO;
	
	@Test
	public void getRolesByCriteriaTest(){		
		List lista = rolDAO.getRolesByCriteria(null);
		log.debug("lista size : "+lista.size());
		assertNotNull(lista);
		assertEquals(lista.size(),948);
	} 
	
}
