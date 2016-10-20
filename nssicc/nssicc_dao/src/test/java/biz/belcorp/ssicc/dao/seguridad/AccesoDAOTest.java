/**
 * 
 */
package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.AccesoDAO;
import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.model.Acceso;
import biz.belcorp.ssicc.dao.model.AccesoPK;

/**
 * @author Danny Amaro
 *
 */
public class AccesoDAOTest extends BaseDAOTest{
	
	@Autowired
	AccesoDAO accesoDAO;
	
	@Test
	public void getAccesoTest(){
		assert(true);
	}

}
