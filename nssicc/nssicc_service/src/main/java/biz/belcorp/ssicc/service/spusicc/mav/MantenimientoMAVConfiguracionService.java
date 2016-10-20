package biz.belcorp.ssicc.service.spusicc.mav;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mav.model.ActividadTipoOferta;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoMAVConfiguracionService extends Service {

	/**
	 * retorna la lista de Actividades MAV
	 * 
	 * @return
	 */
	public List getActividades();
	
	/**
	 * retorna la lista de tipos de oferta
	 * 
	 * @param oidActividad
	 * @return
	 */
	public List getTiposOferta(String oidActividad);

	/**
	 *  retorna la lista de configuraciones MAV de acuerdo a determinados criterios
	 *  
	 * @param params
	 * @return
	 */
	public List getConfiguracionesByCriteria(Map criteria);
	
	/**
	 * Inserta registro a la entidad Configuracion MAV
	 * 
	 * @param params
	 */
	public void insertConfiguracion(Map params);
	
	/**
	 * Actualiza registro a la entidad Configuracion MAV
	 * 
	 * @param params
	 */
	public void updateConfiguracion(Map params);
	
	/**
	 * Elimina registro a la entidad Configuracion MAV
	 * 
	 * @param params
	 */
	public void deleteConfiguracion(Map params) throws Exception;
	
	/**
	 * recupera un registro de la entidad Configuracion MAV
	 * 
	 * @param criteria
	 */
	public Map getConfiguracion(Map criteria);
	
	/**
	 * Retorna las consideraciones
	 * @param criteria
	 * @return
	 */
	public List getConsideracion(Map criteria);

	/**
	 * Retorna las restricciones
	 * @param criteria
	 * @return
	 */
	public List getRestriccion(Map criteria);
	
	/**
	 * Devuelve la lista de consideraciones o restricciones adiccionadas
	 * @param map
	 * @return
	 */
	public List getRestConsideracion(Map map);

	/**
	 * Devuelve los detalles de consideracion /restriccion
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsRest(Map criteria);
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * @param criteria
	 * @return
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * retorna la lista de estados MAV
	 * 
	 * @return
	 */
	public List getEstados();

	/**
	 * Retorna los registros del excel del archivo cargado de Region/Zona
	 * @param criteria
	 * @return
	 */
	public List cargarRegionesArchivoExcel(Map criteria) throws Exception;

	/**
	 * retorna la lista de Catalogos
	 * 
	 * @return
	 */
	public List getCatalogos();

	/**
	 * retorna la lista de Actividades MAV para Gerentes
	 * 
	 * @return
	 */
	public List getActividadesGerente();

	/**
	 * Obtiene un listado de los detalles de la ocnsideracion con datos del envio
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsRestEnvio(Map criteria);

	/**
	 * Actualiza las UAS en la tabla de detalle de condiciones/restricciones de la configuracion MAV y en la tabla de envios
	 * @param listaUnidades
	 * @param usuario
	 */
	public void updateConfiguracionUnidadesListaRegionZona(List listaUnidades, Usuario usuario);
	
	
	/**
	 * Devuelve Lista de tipos de Venta
	 * @return
	 */
	public List getTiposVenta();

	/**
	 * Inserta lista de Region/Zona a una configuracion MAV
	 * 
	 * @param listaRegionZona
	 */
	public void insertListaRegionZona(List listaRegionZona);
	
	/**
	 * Obtiene un listado de los detalles de la consideracion con datos del envio de los clientes
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleClientesConsRestEnvio(Map criteria);
	
	/**
	 * Inserta lista de Consultoras a una configuracion MAV
	 * 
	 * @param listaConsultora
	 */
	public void insertListaConsultora(List listaConsultora);
	
	/**
	 * Obtiene un listado de las Actividades y tipos de oferta
	 * 
	 * @param criteria
	 * @return
	 */
	public List getActividadesTipoOferta(Map criteria);
	
	/**
	 *  Inserta un registro en la tabla Actividades Tipo Oferta
	 * @param params
	 * @param usuario
	 */
	void insertActividadesTipoOferta(Map params, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla Actividades Tipo Oferta
	 * @param params
	 * @param usuario
	 */
	void updateActividadesTipoOferta(Map params, Usuario usuario);
	
	/**
	 * Obtiene la Actividad Tipo Oferta
	 * @param params
	 * @return
	 */
	public ActividadTipoOferta getObtieneActividadesTipoOferta(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param params
	 * @return
	 */
	public boolean getExisteActividadesTipoOferta(Map params);

	/**
	 * Devuelve descripcion
	 * @param params
	 * @return
	 */
	public String getDescripcionTipoActividad(Map params);
	
	/**
	 * Devuelve descripcion
	 * @param params
	 * @return
	 */
	public String getDescripcionActividad(Map params);
	
	/**
	 * Devuelve descripcion
	 * @param params
	 * @return
	 */
	public String getDescripcionTipoOferta(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param params
	 * @return
	 */
	public boolean getValidaEditActividadOferta(Map params);
	
	/**
	 * Devuelve codigo
	 * @param params
	 * @return
	 */
	public String getCodigoTipoOferta(Map params);
	
	/**
	 * Desactiva o Activa
	 * @param params
	 */
	public void DesactivarActividadTipoOferta(Map params, Usuario usuario);
		
	public List getTiposOfertaId(String oidActividad);
	
	/**
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public int cargarArchivoCsv(Map criteria) throws Exception;
	
	/**
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public List executeValidarExternaConsultora(String codigoUsuario);
	
	/**
	 * 	
	 * @param codigoUsuario
	 * @return
	 */
	public Integer getErroresTempoExterConsul(String codigoUsuario);
	
	
	
	
	
	
	
}
	