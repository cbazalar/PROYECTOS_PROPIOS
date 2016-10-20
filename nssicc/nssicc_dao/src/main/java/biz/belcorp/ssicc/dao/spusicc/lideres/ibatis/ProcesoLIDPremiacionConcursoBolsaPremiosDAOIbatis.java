package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDPremiacionConcursoBolsaPremiosDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Solicitudes de premiacion de los 
 * concursos con tipo de premiacion Bolsa de Premios
 * <p>
 * <a href="ProcesoLIDPremiacionConcursoBolsaPremiosDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDPremiacionConcursoBolsaPremiosDAO")
public class ProcesoLIDPremiacionConcursoBolsaPremiosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDPremiacionConcursoBolsaPremiosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDPremiacionConcursoBolsaPremiosDAO#executePremiacionConcursoBolsaPremios(java.util.Map)
	 */
	public void executePremiacionConcursoBolsaPremios(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executePremiacionConcursosBolsaPremios",params);
		
	}

}

