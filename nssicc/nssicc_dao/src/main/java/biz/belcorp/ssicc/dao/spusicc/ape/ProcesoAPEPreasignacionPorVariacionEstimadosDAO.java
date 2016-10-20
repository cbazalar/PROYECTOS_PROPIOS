package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Christian Gonzales Komiya
 *
 */

public interface ProcesoAPEPreasignacionPorVariacionEstimadosDAO extends DAO{
	
	/**
	 * Retorna las fuentes de los periodos de preasignacion
	 * @return 
	 */
	public List getFuentePeriodoPreasignacionList();

	
	
	/**
	 * Ejecuta la preasignacion por variacion de estimados
	 * @param criteria
	 */
	
	public void executePreasignacionPorVariacionEstimados(Map criteria);

	/**
	 * Se obtiene el codigo de funcion de distribucion por el oid de la sublinea
	 * @param oidSublinea
	 * @return codigo de la funcion de distribucion
	 */
	public String getCodigoFuncionDistribucionPorOidSublinea(String oidSublinea);



	/**
	 * Ejecuta la preasignacion Aframe
	 * @param criteria
	 */
	public void executePreasignacionAframe(Map criteria);

}