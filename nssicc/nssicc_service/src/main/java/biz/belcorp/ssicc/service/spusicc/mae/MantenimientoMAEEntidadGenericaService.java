package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.AccionesProcesoBloqueo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Clasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.CriterioBusqueda;
import biz.belcorp.ssicc.dao.spusicc.mae.model.EstadoCivil;
import biz.belcorp.ssicc.dao.spusicc.mae.model.EstatusCliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.GarantiasPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SubtipoCliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoBloqueo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoCliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.TipoVinculo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoMAEEntidadGenericaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public interface MantenimientoMAEEntidadGenericaService extends Service {

	/**
	 * Obtiene el listado de entidades gen�ricas
	 * 
	 * @return
	 */
	List getEntidades(Map params);

	/**
	 * Obtiene datos de una entidad generica en base a los parametros
	 * @param params
	 * @return
	 */
	List getDatosEntidadGenericaByCriteria(Map params);

	/**
	 * Obtiene los motivos de rechazo
	 * @return
	 */
	List getMotivosRechazo();

	/**
	 * Obtiene las formas de bloqueo
	 * @return
	 */
	List getFormasBloqueo();

	/**
	 * Inserta un registro en la tabla TipoBloqueo
	 * @param tipoBloqueo
	 * @param usuario
	 */
	void insertTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla TipoBloqueo
	 * @param tipoBloqueo
	 * @param usuario
	 */
	void updateTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario);

	/**
	 * Obtiene el tipo de bloqueo por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoBloqueo getTipoBloqueo(Map params);

	/**
	 * Elimina en forma l�gica un registro de la tabla Tipobloqueo
	 * @param id
	 * @param usuario
	 */
	void removeTipoBloqueo(String id, Usuario usuario);
	
	/**
	 * Inserta un registro en la tabla TipoCliente
	 * @param tipoCliente
	 * @param usuario
	 */
	void insertTipoCliente(TipoCliente tipoCliente, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla TipoCliente
	 * @param tipoCliente
	 * @param usuario
	 */
	void updateTipoCliente(TipoCliente tipoCliente, Usuario usuario);

	/**
	 * Obtiene el tipo de Cliente por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoCliente getTipoCliente(Map params);	
	
	/**
	 *  Inserta un registro en la tabla SubtipoCliente
	 * @param subtipoCliente
	 * @param usuario
	 */
	void insertSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla SubtipoCliente
	 * @param subtipoCliente
	 * @param usuario
	 */
	void updateSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario);
	
	/**
	 * Obtiene el subtipo de Cliente por el oid
	 * @param params
	 * @return
	 */
	SubtipoCliente getSubtipoCliente(Map params);
	
	/**
	 *  Inserta un registro en la tabla EstatusCliente
	 * @param subtipoCliente
	 * @param usuario
	 */
	void insertEstatusCliente(EstatusCliente estatusCliente, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla EstatusCliente
	 * @param subtipoCliente
	 * @param usuario
	 */
	void updateEstatusCliente(EstatusCliente estatusCliente, Usuario usuario);
	
	/**
	 * Obtiene el Estatus de Cliente por el oid
	 * @param params
	 * @return
	 */
	EstatusCliente getEstatusCliente(Map params);
	
	/**
	 * Obtiene los tipos de estados de cliente
	 * @return
	 */
	List getTipoEstadosCliente();
	
	/**
	 * Obtiene los estados posteriores posibles
	 * @return
	 */
	List getEstadoPosteriorPosible(Map params);
	
	/**
	 * Devuelve lista de tipo de clientes
	 * @param params
	 * @return
	 */
	List getTipoClienteList(Map params);
	
	/**
	 * Elimina en forma l�gica un registro de la tabla TipoCliente
	 * @param id
	 * @param usuario
	 */
	void removeTipoCliente(String id, Usuario usuario);
	/**
	 * Elimina en forma l�gica un registro de la tabla SubtipoCliente
	 * @param id
	 * @param usuario
	 */
	void removeSubtipoCliente(String id, Usuario usuario);
	/**
	 * Elimina en forma l�gica un registro de la tabla EstatusCliente
	 * @param id
	 * @param usuario
	 */
	void removeEstatusCliente(String id, Usuario usuario);
	
	/**
	 * Devuelve si existe el OID
	 * @param subtipoCliente
	 * @return
	 */
	boolean getExisteOidSubtipoCliente(SubtipoCliente subtipoCliente);
	
	/**
	 * Devuelve si existe el OID
	 * @param tipoCliente
	 * @return
	 */
	boolean getExisteOidTipoCliente(TipoCliente tipoCliente);
	
	/**
	 * Devuelve si existe el OID
	 * @param estatusCliente
	 * @return
	 */
	public boolean getExisteOidEstatusCliente(EstatusCliente estatusCliente);
	
	
	/**
	 *  Inserta un registro en la tabla Garantias de Premios
	 * @param garantiasPremio
	 * @param usuario
	 */
	void insertGarantiasPremio(GarantiasPremio garantiasPremio, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla Garantias de Premios
	 * @param garantiasPremio
	 * @param usuario
	 */
	void updateGarantiasPremio(GarantiasPremio garantiasPremio, Usuario usuario);
	
	/**
	 * Obtiene la Garantias de Premios
	 * @param params
	 * @return
	 */
	GarantiasPremio getGarantiasPremio(Map params);
	
	/**
	 * Devuelve si existe el cod_sap
	 * @param garantiasPremio
	 * @return
	 */
	public boolean getExisteGarantiasPremio(GarantiasPremio garantiasPremio);
	
	/**
	 * Devuelve lista de tipo de Subclientes
	 * @param params
	 * @return
	 */
	List getTipoSubClienteList(Map params);
	
	/**
	 * Devuelve lista de tipo de clasificaciones
	 * @param params
	 * @return
	 */
	List getTipoClasificacionesList(Map params);
	
	
	/**
	 *  Inserta un registro en la tabla Clasificacion
	 * @param clasificacion
	 * @param usuario
	 */
	void insertClasificacion(Map params, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla Clasificacion
	 * @param clasificacion
	 * @param usuario
	 */
	void updateClasificacion(Clasificacion clasificacion, Usuario usuario);
	
	/**
	 * Obtiene las Clasificaciones
	 * @param params
	 * @return
	 */
	Clasificacion getClasificacion(Map params);
	
	/**
	 * Devuelve si existe el cod_sap
	 * @param clasificacion
	 * @return
	 */
	public boolean getExisteClasificacion(Clasificacion clasificacion);
	
	/**
	 * Devuelve los SubTipoClientes por TipoCliente
	 * @param criteria
	 * @return
	 */
	public List getSubTiposClientesMultipleByCriteriaOID(Map criteria);
	
	/**
	 * Devuelve los TiposClasificaciones por SubTipoCliente
	 * @param params
	 * @return
	 */
	public List getLista(Map params);
	
	/**
	 *  Inserta un registro en la tabla Tipo Clasificacion
	 * @param params
	 * @param usuario
	 */
	void insertTipoClasificacion(Map params, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla Tipo Clasificacion
	 * @param tipoClasificacion
	 * @param usuario
	 */
	void updateTipoClasificacion(TipoClasificacion tipoClasificacion, Usuario usuario);
	
	/**
	 * Obtiene los Tipos Clasificaciones
	 * @param params
	 * @return
	 */
	TipoClasificacion getTipoClasificacion(Map params);
	
	/**
	 * Devuelve si existe el cod_tipo_clas
	 * @param tipoClasificacion
	 * @return
	 */
	public boolean getExisteTipoClasificacion(TipoClasificacion tipoClasificacion);


	/**
	 * Inserta un registro en la tabla tipoEstatus
	 * @param tipoEstatus
	 * @param usuario
	 */
	void insertEstatusCliente(TipoEstatus tipoEstatus, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla tipoEstatus
	 * @param tipoEstatus
	 * @param usuario
	 */
	void updateTipoEstatusCliente(TipoEstatus tipoEstatus, Usuario usuario);

	/**
	 * Obtiene el tipo Estatus por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoEstatus getTipoEstatusCliente(Map params);
	
	/**
	 * Devuelve si existe el cod_tipo_est
	 * @param tipoEstatus
	 * @return
	 */
	public boolean getExisteTipoEstatusCliente(TipoEstatus tipoEstatus);

	/**
	 * Obtiene el listado de las Marcas
	 * 
	 * @return
	 */
	List getMarcas();
	
	//ESTADO CIVIL
	
	/**
	 * Inserta un registro en la tabla estadoCivil
	 * @param estadoCivil
	 * @param usuario
	 */
	void insertEstadoCivil(EstadoCivil estadoCivil, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla estadoCivil
	 * @param estadoCivil
	 * @param usuario
	 */
	void updateEstadoCivil(EstadoCivil estadoCivil, Usuario usuario);

	/**
	 * Obtiene el tipo Estado Civil por el oid
	 * 
	 * @param params
	 * @return
	 */
	EstadoCivil getTipoEstadoCivil(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param estadoCivil
	 * @return
	 */
	public boolean getExisteTipoEstadoCivil(EstadoCivil estadoCivil);

	//TIPO DIRECCION
	
	
	/**
	 * Inserta un registro en la tabla tipoDireccion
	 * @param tipoDireccion
	 * @param usuario
	 */
	void insertTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla tipoDireccion
	 * @param tipoDireccion
	 * @param usuario
	 */
	void updateTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario);

	/**
	 * Obtiene el tipo tipoDireccion por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoDireccion getTipoDireccion(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param tipoDireccion
	 * @return
	 */
	public boolean getExisteTipoDireccion(TipoDireccion tipoDireccion);

	// TIPO VINCULO
	
	/**
	 * Inserta un registro en la tabla tipoVinculo
	 * @param tipoVinculo
	 * @param usuario
	 */
	void insertTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario);

	/**
	 * Actualiza un registro en la tabla tipoVinculo
	 * @param tipoVinculo
	 * @param usuario
	 */
	void updateTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario);

	/**
	 * Obtiene el tipo tipoVinculo por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoVinculo getTipoVinculo(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param tipoVinculo
	 * @return
	 */
	public boolean getExisteTipoVinculo(TipoVinculo tipoVinculo);

	//TIPO COMUNICACION
	
	
	/**
	 * Inserta un registro en la tabla tipoComunicacion
	 * @param tipoComunicacion
	 * @param usuario
	 */
	void insertTipoComunicacion(TipoComunicacion tipoComunicacion , Usuario usuario);

	/**
	 * Actualiza un registro en la tabla tipoComunicacion
	 * @param tipoComunicacion
	 * @param usuario
	 */
	void updateTipoComunicacion(TipoComunicacion tipoComunicacion, Usuario usuario);

	/**
	 * Obtiene el tipo tipoComunicacion por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoComunicacion getTipoComunicacion(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param tipoComunicacion
	 * @return
	 */
	public boolean getExisteTipoComunicacion(TipoComunicacion tipoComunicacion);
	
	
	//TIPO DOCUMENTO
	
	
	
	/**
	 * Inserta un registro en la tabla tipoDocumento
	 * @param tipoDocumento
	 * @param usuario
	 */
	void insertTipoDocumento(TipoDocumento tipoDocumento , Usuario usuario);

	/**
	 * Actualiza un registro en la tabla tipoDocumento
	 * @param tipoDocumento
	 * @param usuario
	 */
	void updateTipoDocumento(TipoDocumento tipoDocumento, Usuario usuario);

	/**
	 * Obtiene el tipo tipoDocumento por el oid
	 * 
	 * @param params
	 * @return
	 */
	TipoDocumento getTipoDocumento(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param tipoDocumento
	 * @return
	 */
	public boolean getExisteTipoDocumento(TipoDocumento tipoDocumento);
	
	
	// CRITERIOS DE BUSQUEDA

	
	/**
	 * Inserta un registro en la tabla criterioBusqueda
	 * @param criterioBusqueda
	 * @param usuario
	 */
	void insertCriterioBusqueda(CriterioBusqueda criterioBusqueda  , Usuario usuario);

	/**
	 * Actualiza un registro en la tabla criterioBusqueda
	 * @param criterioBusqueda
	 * @param usuario
	 */
	void updateCriterioBusqueda(CriterioBusqueda criterioBusqueda, Usuario usuario);

	/**
	 * Obtiene el tipo criterioBusqueda por el oid
	 * 
	 * @param params
	 * @return
	 */
	CriterioBusqueda getCriterioBusqueda(Map params);
	
	/**
	 * Devuelve si existe el codigo
	 * @param criterioBusqueda
	 * @return
	 */
	public boolean getExisteCriterioBusqueda(CriterioBusqueda criterioBusqueda);
	
	/**
	 * Obtiene el listado de Criterios
	 * 
	 * @return
	 */
	List getCriterios();
	
	/**
	 * Obtiene el listado de Tipos Documentos
	 * 
	 * @return
	 */
	List getTipoDocumentosLista();
	
	/**
	 * Obtiene los Tipos de bloqueo
	 * @param params
	 * @return
	 */
	List getTipoBloqueoList(Map params);
	
	/**
	 * Obtiene los Tipos de Proceso de bloqueo
	 * @return
	 */
	List getProcesoBloqueoList();
	
	/**
	 * Obtiene lista de acciones de bloqueo
	 * @return
	 */
	List getAccionBloqueoList(Map params);
	
	/**
	 * Inserta un registro en la tabla AccionesProcesoBloqueo
	 * @param tipoBloqueo
	 * @param usuario
	 */
	void insertAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla AccionesProcesoBloqueo
	 * @param tipoBloqueo
	 * @param usuario
	 */
	void updateAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario);
	
	/**
	 * Obtiene el AccionesProcesoBloqueo por el oid
	 * 
	 * @param params
	 * @return
	 */
	AccionesProcesoBloqueo getAccionesProcesoBloqueo(Map params);
	
	/**
	 * Devuelve si existe OID para actualizar o insertar 
	 * @param accionesProcesoBloqueo
	 * @return
	 */
	boolean getExisteOidAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo);
}