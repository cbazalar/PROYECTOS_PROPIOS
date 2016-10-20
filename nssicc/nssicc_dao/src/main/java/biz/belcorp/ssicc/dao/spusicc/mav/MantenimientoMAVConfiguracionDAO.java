package biz.belcorp.ssicc.dao.spusicc.mav;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mav.model.ActividadTipoOferta;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoMAVConfiguracionDAO extends DAO {
	
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
	 *  retorna los datos relacionados a una actividad MAV
	 *  
	 * @param params
	 * @return
	 */
	public List getDatosActividad(String oidActividad);
	
	/**
	 * recupera el ultima secuencia + 1 para Configuracion MAV
	 * 
	 * @param params
	 */
	public String getMaxConfiguracion();

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
	public void deleteConfiguracion(Map params);
	
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
	 * guarda las consideraciones y/o restricciones
	 * @param params
	 */
	public void saveConRestCabecera(Map params);

	/**
	 * guarda los detalles consideraciones y/o restricciones
	 * @param params
	 */
	public void saveConRestDetalle(Map params);
	
	/**
	 * Actualiza cabecera de consideraciones y restricciones
	 * @param params
	 */
	public void updateConRestCabecera(Map params);

	/**
	 * Actualiza cabecera de consideraciones y restricciones
	 * @param params
	 */
	public void updateConRestDetalle(Map params);

	/**
	 * elimina fisicamente la cabecera
	 * @param bean
	 */
	public void deleteConRestCabecera(Map params);
	
	/**
	 * elimina fisicamente el detalle
	 * @param bean
	 */
	public void deleteConRestDetalle(Map params);

	/**
	 * Devuelve la lista de consideraciones o restricciones anhadidas
	 * @param map
	 * @return
	 */
	public List getRestConsideracion(Map criteria);
	
	/**
	 * Devuelve los detalles de consideracion /restriccion
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsRest(Map criteria);

	/**
	 * Devuelve los detalles de consideracion /restriccion Clientes
	 * @param criteria
	 * @return
	 */
	public List getDetalleClientesConsRest(Map criteria);

	/**
	 * retorna la lista de estados MAV
	 * 
	 * @return
	 */
	public List getEstados();

	/**
	 * Verifica si existe la Region
	 * 
	 * @param codigoRegion
	 * @return
	 */
	public boolean existeRegion(String codigoRegion);
	
	/**
	 * Verifica si existe la Zona
	 * 
	 * @param codigoZona
	 * @return
	 */
	public boolean existeZona(String codigoZona);
	
	/**
	 * Verifica si existe la Zona pertenece a la Region
	 * 
	 * @param codigoRegion
	 * @param codigoZona
	 * @return
	 */
	public boolean existeRelacionRegionZona(String codigoRegion, String codigoZona);

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
	 * Verifica si el MAV tiene algun envio despachado
	 * 
	 * @param correlativoMAV
	 * @return
	 */
	public boolean getTieneEnvioDespachado(Map criteria);
	
	/**
	 * Actualiza Envios como No Atendidos
	 * 
	 * @param bean
	 */
	public void updateEnviosNoAtendidos(Map params);

	/**
	 * Actualiza la campaa de los detalles de los mav
	 * @param bean
	 */
	public void updateConRestDetalleCampanya(Map bean);

	/**
	 * Obtiene un listado de los detalles de la ocnsideracion con datos del envio
	 * @param criteria
	 * @return
	 */
	public List getDetalleConsRestEnvio(Map criteria);

	/**
	 * Actualiza las unidades de las condiciones/restricciones del detalle del MAV
	 * @param params
	 */
	public void updateConRestDetalleUnidades(Map params);

	/**
	 * Actualiza las unidades de los envos del MAV
	 * @param params
	 */
	public void updateEnvioUnidades(Map params);

	/**
	 * Inserta MAV Envio para las Gerentes 
	 * @param params
	 */
	public void executeInsertarEnvioGerente(Map params);
	
	/**
	 * Obtiene un listado de los detalles de la consideracion con datos del envio de los clientes
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleClientesConsRestEnvio(Map criteria);
	
	/**
	 * Inserta MAV Envio para las Consultoras 
	 * @param params
	 */
	public void executeInsertarEnvioConsultora(Map params);
	
	
	/**
	 * Lista Actividades Tipo Oferta
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
	public String getExisteActividadesTipoOferta(Map params);

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
	 * Devuelve descripcion
	 * @param params
	 * @return
	 */
	public List getTiposOfertaId(String oidActividad);
	
	/**
	 * Devuelve Codigo
	 * @param params
	 * @return
	 */
	public String getValidaEditActividadOferta(Map params);
	
	/**
	 * Devuelve codigo
	 * @param params
	 * @return
	 */
	public String getCodigoTipoOferta(Map params);
	
	/**
	 * retorna la lista de Actividades MAV by Tipo Cliente
	 * 
	 * @return
	 */
	public List getActividadesbyTipoCliente(String oidTipoActividad);
	
	/**
	 * Desactiva o activa
	 * @param params
	 */
	public void DesactivarActividadTipoOferta(Map params, Usuario usuario);
	
	/**
	 * 
	 * @param params
	 */
	public void insertExternaConsultora(Map params);
	
	/**
	 * 
	 * @param codigoUsuario
	 */
	public void executeValidarExternaConsultora(String codigoUsuario);
	
	/**
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public List getCargarExternaConsultora(String codigoUsuario);
	
	/**
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public Integer getErroresTempoExterConsul(String codigoUsuario);	
	
	/**
	 * 
	 * @param codigoUsuario
	 */
	public void deleteExternaCultora(String codigoUsuario); 
	
}