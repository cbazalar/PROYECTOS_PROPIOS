/**
 * 
 */
package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.IdiomaDAO;

/**
 * @author Danny Amaro
 *
 */
public class IdiomaDAOTest extends BaseDAOTest{
	
	@Autowired
	private IdiomaDAO idiomaDAO;
	
	@Test
	public void getIdiomasByCriteriaTest(){
		assert(true);
	}
}
