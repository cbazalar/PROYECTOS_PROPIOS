package biz.belcorp.ssicc.dao.spusicc.sicc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;

/**
 * @author Danny Amaro
 *
 */
public class MantenimientoSICCDAOTest extends BaseDAOTest{
	
	@Autowired
	private MantenimientoSICCDAO mantenimientoSICCDAO;
	
	@Test
	public void getListaSICCUsuarioByCriteriaTest(){
		List lista = this.mantenimientoSICCDAO.getListaSICCUsuarioByCriteria(null);
		log.debug("lista size : "+lista.size());
		assertNotNull(lista);
	}

}
