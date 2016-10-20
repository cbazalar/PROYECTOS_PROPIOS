/**
 * 
 */
package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.PaisDAO;

/**
 * @author Danny Amaro
 *
 */
public class PaisDAOTest extends BaseDAOTest{

	@Autowired
	PaisDAO paisDAO;
	
	@Test
	public void getPaisesByCriteriaTest(){
		List lista = paisDAO.getPaisesByCriteria(null);
		log.debug("Lista size : "+lista.size());
//		assertNotNull(lista);
//		assertEquals(lista.size(),21);
	}
}
