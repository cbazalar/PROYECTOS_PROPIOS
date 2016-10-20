/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoCOBGenerarCronogramaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCOBGenerarCronogramaDAO extends DAO {

	
	
	/**
	 * Ejecuta proceso de Generar Cronograma (Cobranzas)
	 * @param criteria
	 */
	public void executeGenerarCronograma (Map criteria);
	
	/**
	 * Devuelve el Cronograma de Carteras (Cobranzas)
	 * @param criteria
	 */
	public List getCronogramaByPaisSociedadEtapaPeriodo(Map criteria);
	
	
}
