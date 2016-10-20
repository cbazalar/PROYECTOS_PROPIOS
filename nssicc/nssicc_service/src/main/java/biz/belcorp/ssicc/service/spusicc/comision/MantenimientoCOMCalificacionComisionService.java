package biz.belcorp.ssicc.service.spusicc.comision;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DatosComision;
import biz.belcorp.ssicc.dao.spusicc.comision.model.OrdenEstadisticoPorZona;
import biz.belcorp.ssicc.service.framework.Service;



public interface MantenimientoCOMCalificacionComisionService extends Service {

	public List getCalificacionesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera);

	public void deleteCalificacionComisionCabeceraAndChild(CalificacionComisionCabecera calificacionComisionCabecera);

	public List getNivelList(String codigo);

	public void deleteCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);

	public void updateCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List detalList);

	public void insertCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List detalList);

	public List getCalificacionComisionDetalleList(	CalificacionComisionCabecera calificacionComisionCabecera);

	public void updateCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);

	public void insertCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
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
	 */
	public void desmarcarEnvio(Map criteria);

	/**
	 * Metodo que devuelve la lista de Orden Estadistico Por Zona
	 * @param ordenEstadisticoPorZona
	 * @return
	 */
	public List getOrdenEstadisticoPorZonaList(OrdenEstadisticoPorZona ordenEstadisticoPorZona);

	/**
	 * Metodo que actualiza el registro Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario 
	 */
	public void updateOrdenEstadisticoPorZona(
			OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);	

	/**
	 * Metodo que inserta el registro Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario 
	 */
	public void insertOrdenEstadisticoPorZona(
			OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);

	/**
	 * Metodo que borra un registro de Orden Estadistico por Zona
	 * @param ordenEstadisticoPorZona
	 * @param usuario
	 */
	public void deleteOrdenEstadisticoPorZona(
			OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario);

}
