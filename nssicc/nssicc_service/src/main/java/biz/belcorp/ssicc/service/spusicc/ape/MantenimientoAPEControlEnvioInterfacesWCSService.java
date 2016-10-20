package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.ControlEnvioWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstadoOlaWCS;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEControlEnvioInterfacesWCSService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEControlEnvioInterfacesWCSService extends Service{

	/**
	 * @param criteria
	 * @return La lista de Envo de Interfaces WCS
	 */
	public List getControlEnvioInterfacesList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Objeto Control de Envio
	 */
	public ControlEnvioWCS getControlEnvioWCSObject(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valor Estado de OLA
	 */
	public EstadoOlaWCS getValEstadoOla(Map criteria);
	
	/**
	 * @param criteria
	 * Actualiza el Estado de OLA con '0' y fec_hora_libe en Nulo
	 */
	public void updateEstadoOlasxDia(Map criteria);
	
}
