package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPECopiarOrdenAnaquelesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPECopiarOrdenAnaquelesService extends Service {
	
	/**
	 * @param criteria
	 * @return Lista de Orden Anaqueles Cabecera
	 */
	public List getOrdenAnaquelList(Map criteria);
	
	/**
	 * @param criteria
	 * @return OidOrdenAnaquel
	 */
	public String getOidOrdenAnaquel(Map criteria);
	
	/**
	 * @param criteria
	 * @return Cod Mapa Zona Default
	 */
	public String getMapaZonaDefault(Map criteria);
	
	/**
	 * Realiza la generacin de una Copia de Orden de Anaqueles a un nuevo registro de Orden de Anaqueles
	 * @param criteria
	 */
	public void executeGenerarOrdenAnaquelDetalle(Map criteria);

}
