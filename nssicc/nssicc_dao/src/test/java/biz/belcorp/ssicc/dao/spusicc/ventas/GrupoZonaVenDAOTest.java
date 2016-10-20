package biz.belcorp.ssicc.dao.spusicc.ventas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.dao.BaseDAOTest;

public class GrupoZonaVenDAOTest extends BaseDAOTest{
	
	@Autowired
	private GrupoZonaVenDAO grupoZonaVenDAO;
	
	@Test
	public void getRegionTest(){
		List lista = this.grupoZonaVenDAO.getRegion(null);
		log.debug("Lista size : "+lista.size());
		assertNotNull(lista);
	}
	
}
