/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DatosComision;
import biz.belcorp.ssicc.dao.spusicc.comision.model.OrdenEstadisticoPorZona;



/**
 * @author peextllizana
 *
 */
public interface MantenimientoCOMCalificacionComisionDAO extends DAO {

	public List getCalificacionesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera);

	public void deleteCalificacionComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);

	public void deleteCalificacionComisionDetalle(CalificacionComisionCabecera calificacionComisionCabecera);

	public List getNivelList(String codigo);

	public void deleteCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);

	public String getSecuenciaNextValue();

	public void insertCalificacionComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);

	public void insertCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);

	public List getCalificacionComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera);

	public void updateCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);

	public void updateCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera);

	public List getDatosComisionList(Map params);

	public DatosComision getDatosComision(DatosComision datos);

	/**
	 * Metodo que devuelve la lista de envios SAP
	 * @param criteria
	 * @return
	 */
	public List getEnviosSAP(Map criteria);

	/**
	 * Metodo que desmarca el envio SAP
	 * @param criteria
	 * @return
	 */
	public void desmarcarEnvio(Map criteria);

	/**
	 * Metodo que devuelve la lista de Orden Estadistico Por Zona
	 * @param ordenEstadisticoPorZona
	 */
	public List getOrdenEstadisticoPorZonaList(OrdenEstadisticoPorZona ordenEstadisticoPorZona);

	/**
	 * Metodo que actualiza el registro Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario 
	 */
	public void updateOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);	

	/**
	 * Metodo que inserta el registro Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario 
	 */
	public void insertOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);

	/**
	 * Metodo que borra un registro Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario
	 */
	public void deleteOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);

	/**
	 * Retorna el nivel Actual ejecutiva
	 * @param params
	 * @return
	 */
	public String getNivelActualEjecutiva(Map params);

}
