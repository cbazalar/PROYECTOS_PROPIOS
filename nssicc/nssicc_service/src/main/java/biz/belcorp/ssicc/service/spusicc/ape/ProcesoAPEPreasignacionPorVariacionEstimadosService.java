package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEGenerarEstimadoProductoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface ProcesoAPEPreasignacionPorVariacionEstimadosService extends Service {


	/**
	 * Retorna las fuentes de los periodos de preasignacion
	 * @return 
	 */
	public List getFuentePeriodoPreasignacionList();

	/**
	 * Ejectura la preasignacion por variacion de estimados
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
	 * Ejectura la preasignacion Aframe
	 * @param criteria
	 */
	public void executePreasignacionAframe(Map criteria);

}