package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPEEvaluarCargaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface ProcesoAPEEvaluarCargaService extends Service{

	/**
	 * Obtiene el numero de Zonas de la SubLinea
	 * @param criteria
	 * @return
	 */
	public int getNumeroZonaSubLinea(Map criteria);
	
	/**
	 * Devueleve el oid del Mapa Zona
	 * @param criteria
	 * @return
	 */
	public String getMapaZonaCabecera(Map criteria);
	
	/**
	 * Devuelve el oid de la Asignacion de Producto Anaquel Cabecera
	 * @param criteria
	 * @return
	 */
	public String getAsignacionProductoAnaquelCabecera(Map criteria);
	
	/**
	 * Obtiene el Numero Total de Estimados por SubLinea
	 * @param criteria
	 * @return
	 */
	public int getTotalEstimadoSubLinea(Map criteria);
	
	/**
	 * Devuelve la lista de ZOnas de la SubLinea
	 * @param criteria
	 * @return
	 */
	public List getEvaluarCargaList(Map criteria);
}
