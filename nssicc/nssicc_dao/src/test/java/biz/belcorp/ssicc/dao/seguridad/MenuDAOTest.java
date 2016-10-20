/**
 * 
 */
package biz.belcorp.ssicc.dao.seguridad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;
import biz.belcorp.ssicc.dao.MenuDAO;
import biz.belcorp.ssicc.dao.model.Menu;


/**
 * @author Danny Amaro
 *
 */
public class MenuDAOTest extends BaseDAOTest{

	@Autowired
	MenuDAO menuDAO;
	
	@Test
	public void getMenuesByCriteriaTest(){
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", "01");
		List lista = menuDAO.getMenuesByCriteria(criteria);
		log.debug("Lista size : "+lista.size());
		//assertNotNull(lista);
		//assertEquals(lista.size(),1194);
	}
	
	@Test
	public void getMenuTest(){
		Menu menu = menuDAO.getMenu("30020503", "01");
		assertNotNull(menu);
	}
	
	@Test
	public void getMenuOpcionesFaltantesTest(){
		//List listaAsignados = menuDAO.getMenuOpcionesAsignadas("60030101");
		//List listaFaltantes = menuDAO.getMenuOpcionesFaltantes("60030101");
		List listaAsignados = menuDAO.getMenuOpcionesAsignadas(null);
		List listaFaltantes = menuDAO.getMenuOpcionesFaltantes(null);
		log.debug("Opciones Asignadas : "+ listaAsignados.size());
		log.debug("Opciones Faltantes : "+ listaFaltantes.size());
	}

	
}
