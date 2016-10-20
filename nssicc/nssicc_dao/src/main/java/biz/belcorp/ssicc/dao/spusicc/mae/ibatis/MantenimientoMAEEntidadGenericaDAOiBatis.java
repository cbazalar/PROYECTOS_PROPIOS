package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEEntidadGenericaDAO;
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

/**
 * <p>
 * <a href="MantenimientoMAEEntidadGenericaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
@Repository("spusicc.mantenimientoMAEEntidadGenericaDAO")
public class MantenimientoMAEEntidadGenericaDAOiBatis extends BaseDAOiBatis implements MantenimientoMAEEntidadGenericaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getEntidades(java.lang.String)
	 */
	public List getEntidades(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getEntidades", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getDatosEntidadGenericaByCriteria(java.util.Map)
	 */
	public List getDatosEntidadGenericaByCriteria(Map params) {
		
		String codigoEntidad = MapUtils.getString(params, "codigoEntidad", "");
		String query = "";
		
		if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO))
		{
			query = "spusicc.MantenimientoMAESQL.getTipoBloqueoByCriteria";
		}else{
			if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE))
			{
				query = "spusicc.MantenimientoMAESQL.getTipoClienteByCriteria";
			}else{
				if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE))
				{
					query = "spusicc.MantenimientoMAESQL.getSubtipoClienteByCriteria";
				}else{
					if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE))
					{
						query = "spusicc.MantenimientoMAESQL.getEstatusClienteByCriteria";
					}
					else{
						if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS))
						{
							query = "spusicc.MantenimientoMAESQL.getGarantiaPremiosByCriteria";
						}else{
							
							if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION)){
								query = "spusicc.MantenimientoMAESQL.getClasificacionesGrid";
							}else{
								if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION)){
									query = "spusicc.MantenimientoMAESQL.getTipoClasificacionesGrid";
								}else{
									if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE)){
										query = "spusicc.MantenimientoMAESQL.getEstatusClienteGrid";
									}else{
										if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL)){
											query = "spusicc.MantenimientoMAESQL.getEstadoCivilGrid";
										}else{
											if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION)){
												query = "spusicc.MantenimientoMAESQL.getTipoDirecGrid";
											}else{
												if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION)){
													query = "spusicc.MantenimientoMAESQL.getTipoComuGrid";
												}else{
													if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO)){
														query = "spusicc.MantenimientoMAESQL.getTipoVinculoGrid";
													}else{
														if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO)){
															query = "spusicc.MantenimientoMAESQL.getTipoDocumentoGrid";
														}else{
															if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA)){
																query = "spusicc.MantenimientoMAESQL.getCriterioBusquedaGrid";
															}else{
																if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO)){
																	query = "spusicc.MantenimientoMAESQL.getAccionesProcesoBloqueoByCriteria";
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return getSqlMapClientTemplate().queryForList(query, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getMotivosRechazo()
	 */
	public List getMotivosRechazo() {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getMotivosRechazo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getFormasBloqueo()
	 */
	public List getFormasBloqueo() {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getFormasBloqueo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#insertTipoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.TipoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario) {
		
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoBloqueo");
		tipoBloqueo.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoBloqueo", tipoBloqueo);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_BLOQU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoBloqueo.getDescripcion());
		params.put("valOidAtributo", tipoBloqueo.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#updateTipoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.TipoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoBloqueo", tipoBloqueo);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_BLOQU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoBloqueo.getDescripcion());
		params.put("valOidAtributo", tipoBloqueo.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getTipoBloqueo(java.util.Map)
	 */
	public TipoBloqueo getTipoBloqueo(Map params) {
		
		TipoBloqueo tipoBloqueo = (TipoBloqueo) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getTipoBloqueo", params);
		
        if (tipoBloqueo == null) {
            throw new ObjectRetrievalFailureException(TipoBloqueo.class, params);
        }
        
        return tipoBloqueo;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#insertTipoCliente(biz.belcorp.ssicc.spusicc.mae.model.TipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoCliente(TipoCliente tipoCliente, Usuario usuario) {
		
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoClienteSeq");
		tipoCliente.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoCliente", tipoCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoCliente.getDescripcion());
		params.put("valOidAtributo", tipoCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#updateTipoCliente(biz.belcorp.ssicc.spusicc.mae.model.TipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoCliente(TipoCliente tipoCliente, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoCliente", tipoCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoCliente.getDescripcion());
		params.put("valOidAtributo", tipoCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getTipoCliente(java.util.Map)
	 */
	public TipoCliente getTipoCliente(Map params) {
		
		TipoCliente tipoCliente = (TipoCliente) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getTipoCliente", params);
		
        if (tipoCliente == null) {
            throw new ObjectRetrievalFailureException(TipoCliente.class, params);
        }
        
        return tipoCliente;
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#insertSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.SubtipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario) {
		
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidSubtipoClienteSeq");
		subtipoCliente.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertSubtipoCliente", subtipoCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_SUBTI_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", subtipoCliente.getDescripcion());
		params.put("valOidAtributo", subtipoCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);		
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#updateSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.SubtipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateSubtipoCliente", subtipoCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_SUBTI_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", subtipoCliente.getDescripcion());
		params.put("valOidAtributo", subtipoCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);		
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getSubtipoCliente(java.util.Map)
	 */
	public SubtipoCliente getSubtipoCliente(Map params) {
		
		SubtipoCliente subtipoCliente = (SubtipoCliente) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getSubtipoCliente", params);
		
        if (subtipoCliente == null) {
            throw new ObjectRetrievalFailureException(SubtipoCliente.class, params);
        }
        
        return subtipoCliente;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#insertEstatusCliente(biz.belcorp.ssicc.spusicc.mae.model.EstatusCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstatusCliente(EstatusCliente estatusCliente, Usuario usuario) {
		
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidEstatusCliente");
		estatusCliente.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertEstatusCliente", estatusCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_ESTAT_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", estatusCliente.getDescripcion());
		params.put("valOidAtributo", estatusCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#updateEstatusCliente(biz.belcorp.ssicc.spusicc.mae.model.EstatusCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstatusCliente(EstatusCliente estatusCliente, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateEstatusCliente", estatusCliente);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_ESTAT_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", estatusCliente.getDescripcion());
		params.put("valOidAtributo", estatusCliente.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);		
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getEstatusCliente(java.util.Map)
	 */
	public EstatusCliente getEstatusCliente(Map params) {
		
		EstatusCliente estatusCliente = (EstatusCliente) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getEstatusCliente", params);
		
        if (estatusCliente == null) {
            throw new ObjectRetrievalFailureException(EstatusCliente.class, params);
        }
        
        return estatusCliente;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getEstadoPosteriorPosible()
	 */
	public List getEstadoPosteriorPosible(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getEstadoPosteriorPosible", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getTipoEstadosCliente()
	 */
	public List getTipoEstadosCliente() {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTipoEstadosCliente");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTipoClienteList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getExisteOidSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.SubtipoCliente)
	 */
	public String getExisteOidSubtipoCliente(SubtipoCliente subtipoCliente){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteOidSubtipoCliente", subtipoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getExisteOidTipoCliente(biz.belcorp.ssicc.spusicc.mae.model.TipoCliente)
	 */
	public String getExisteOidTipoCliente(TipoCliente tipoCliente){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteOidTipoCliente", tipoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getExisteOidEstatusCliente(biz.belcorp.ssicc.spusicc.mae.model.EstatusCliente)
	 */
	public String getExisteOidEstatusCliente(EstatusCliente estatusCliente){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteOidEstatusCliente", estatusCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#deleteTipoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.TipoBloqueo)
	 */
	public void deleteTipoBloqueo(TipoBloqueo tipoBloqueo) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteTipoBloqueo", tipoBloqueo);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_BLOQU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoBloqueo.getDescripcion());
		params.put("valOidAtributo", tipoBloqueo.getOid());
		
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteDescripcion", params);
	}

	public void insertGarantiasPremio(GarantiasPremio garantiasPremio,
			Usuario usuario) {
		// TODO Auto-generated method stub
		//String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidEstatusCliente");
		//garantiasPremio.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertGarantiasPremio", garantiasPremio);
		/*
		Map params = new HashMap();
		params.put("codigoPais", garantiasPremio.getCodigoPais());
		params.put("codigo", garantiasPremio.getCodigo());
		params.put("numDias", garantiasPremio.getNumDias());
		params.put("indicaRegistro", garantiasPremio.getIndicadorRegistro());
		params.put("usuario", usuario.getLogin());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);	 */
	}

	public void updateGarantiasPremio(GarantiasPremio garantiasPremio,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateGarantiasPremio", garantiasPremio);
	}

	public GarantiasPremio getGarantiasPremio(Map params) {
		// TODO Auto-generated method stub
		GarantiasPremio garantiasPremio = (GarantiasPremio) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getGarantiasPremio", params);
		
        if (garantiasPremio == null) {
            throw new ObjectRetrievalFailureException(GarantiasPremio.class, params);
        }
        
        return garantiasPremio;
		
	}

	public String getExisteGarantiasPremio(GarantiasPremio garantiasPremio) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteGarantiasPremio", garantiasPremio);
	}
	
	public List getTipoSubClienteList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getSubTipoClienteList", params);
	}

	public List getTipoClasificacionesList(Map params) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTipoClasificacion", params);
	}

	public void insertClasificacion(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClasificacion", params);
	}

	public void updateClasificacion(Clasificacion clasificacion, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateClasificacion", clasificacion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_CLASI");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", clasificacion.getDescripcionClasificacion());
		params.put("valOidAtributo", clasificacion.getOidClasificacion());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
		
	}


	public Clasificacion getClasificacion(Map params) {
		// TODO Auto-generated method stub
		Clasificacion clasificacion = (Clasificacion) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getClasificacion", params);
		
        if (clasificacion == null) {
            throw new ObjectRetrievalFailureException(Clasificacion.class, params);
        }
        
        return clasificacion;
	}
		

	public String getExisteClasificacion(Clasificacion clasificacion) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteClasificacion", clasificacion);
	}

	public void insertTipoClasificacion(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoClasificacion", params);
	}

	public void updateTipoClasificacion(TipoClasificacion tipoClasificacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoClasificacion", tipoClasificacion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_CLASI_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoClasificacion.getDescripcionTipoClasificacion());
		params.put("valOidAtributo", tipoClasificacion.getOidTipoClasificacion());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
		
	}

	public TipoClasificacion getTipoClasificacion(Map params) {
		// TODO Auto-generated method stub
		TipoClasificacion tipoClasificacion = (TipoClasificacion) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoClasificacion", params);
				
	        if (tipoClasificacion == null) {
	            throw new ObjectRetrievalFailureException(TipoClasificacion.class, params);
	        }
	        
	        return tipoClasificacion;
	}

	public String getExisteTipoClasificacion(TipoClasificacion tipoClasificacion) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoClasificacion", tipoClasificacion);
	}

	public void insertEstatusCliente(TipoEstatus tipoEstatus, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoEstatusCliente");
		tipoEstatus.setOidEstatus(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoEstatusCliente", tipoEstatus);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_ESTAT_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoEstatus.getDescripcion());
		params.put("valOidAtributo", tipoEstatus.getOidEstatus());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);
	}

	public void updateTipoEstatusCliente(TipoEstatus tipoEstatus,
			Usuario usuario) {
		// TODO Auto-generated method stub		
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoEstatusCliente", tipoEstatus);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_ESTAT_CLIEN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoEstatus.getDescripcion());
		params.put("valOidAtributo", tipoEstatus.getOidEstatus());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
		
	}

	public TipoEstatus getTipoEstatusCliente(Map params) {
		// TODO Auto-generated method stub
		TipoEstatus tipoEstatus = (TipoEstatus) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoEstatusCliente", params);
		
        if (tipoEstatus == null) {
            throw new ObjectRetrievalFailureException(TipoEstatus.class, params);
        }
        
        return tipoEstatus;
	}

	public String getExisteTipoEstatusCliente(TipoEstatus tipoEstatus) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoEstatusCliente", tipoEstatus);
	}

	public List getMarcas() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getMarcaList");
	}

	public void insertEstadoCivil(EstadoCivil estadoCivil, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidObtieneEstadoCivil");
		estadoCivil.setOidEstadoCivil(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertEstadoCivil", estadoCivil);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_ESTAD_CIVIL");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", estadoCivil.getDescripcion());
		params.put("valOidAtributo", estadoCivil.getOidEstadoCivil());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);
	}

	public void updateEstadoCivil(EstadoCivil estadoCivil, Usuario usuario) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateEstadoCivil", estadoCivil);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_ESTAD_CIVIL");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", estadoCivil.getDescripcion());
		params.put("valOidAtributo", estadoCivil.getOidEstadoCivil());
	
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
		
	}

	public EstadoCivil  getTipoEstadoCivil(Map params) {
		// TODO Auto-generated method stub
		EstadoCivil estadoCivil = (EstadoCivil) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneEstadoCivil", params);
		
        if (estadoCivil == null) {
            throw new ObjectRetrievalFailureException(EstadoCivil.class, params);
        }
        
        return estadoCivil;
	}

	public String getExisteTipoEstadoCivil(EstadoCivil estadoCivil) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteEstadoCivil", estadoCivil);
	}

	public void insertTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoDirec");
		tipoDireccion.setOidTipoDirec(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoDirec", tipoDireccion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_DIREC");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoDireccion.getDescripcion());
		params.put("valOidAtributo", tipoDireccion.getOidTipoDirec());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);
	}

	public void updateTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoDirec", tipoDireccion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_DIREC");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoDireccion.getDescripcion());
		params.put("valOidAtributo", tipoDireccion.getOidTipoDirec());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
	
	}

	public TipoDireccion getTipoDireccion(Map params) {
		// TODO Auto-generated method stub
		TipoDireccion tipoDireccion = (TipoDireccion) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoDirec", params);
		
        if (tipoDireccion == null) {
            throw new ObjectRetrievalFailureException(TipoDireccion.class, params);
        }
        
        return tipoDireccion;	
	}

	public String getExisteTipoDireccion(TipoDireccion tipoDireccion) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoDirec", tipoDireccion);
	}

	public void insertTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidObtieneTipoVinculo");
		tipoVinculo.setOidTipoVinc(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoVinculo", tipoVinculo);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_VINCU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoVinculo.getDescripcion());
		params.put("valOidAtributo", tipoVinculo.getOidTipoVinc());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcionSiccPais", params);
	}

	public void updateTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoVinculo", tipoVinculo);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_VINCU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoVinculo.getDescripcion());
		params.put("valOidAtributo", tipoVinculo.getOidTipoVinc());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcionSiccPais", params);
	
	}

	public TipoVinculo getTipoVinculo(Map params) {
		// TODO Auto-generated method stub
		TipoVinculo tipoVinculo = (TipoVinculo) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoVinculo", params);
		
        if (tipoVinculo == null) {
            throw new ObjectRetrievalFailureException(TipoVinculo.class, params);
        }
        
        return tipoVinculo;	
	}

	public String getExisteTipoVinculo(TipoVinculo tipoVinculo) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoVinculo", tipoVinculo);
	}

	public void insertTipoComunicacion(TipoComunicacion tipoComunicacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoComu");
		tipoComunicacion.setOidTipoComu(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoComu", tipoComunicacion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_COMUN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoComunicacion.getDescripcion());
		params.put("valOidAtributo", tipoComunicacion.getOidTipoComu());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);
	}

	public void updateTipoComunicacion(TipoComunicacion tipoComunicacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoComu", tipoComunicacion);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_COMUN");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoComunicacion.getDescripcion());
		params.put("valOidAtributo", tipoComunicacion.getOidTipoComu());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcion", params);
	
	}

	public TipoComunicacion getTipoComunicacion(Map params) {
		// TODO Auto-generated method stub
		TipoComunicacion tipoComunicacion = (TipoComunicacion) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoComu", params);
		
        if (tipoComunicacion == null) {
            throw new ObjectRetrievalFailureException(TipoComunicacion.class, params);
        }
        
        return tipoComunicacion;	
	}

	public String getExisteTipoComunicacion(TipoComunicacion tipoComunicacion) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoComu", tipoComunicacion);
	}

	public void insertTipoDocumento(TipoDocumento tipoDocumento, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidObtieneTipoDocumento");
		tipoDocumento.setOidTipoDoc(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertTipoDocumento", tipoDocumento);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_DOCUM");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoDocumento.getDescripcion());
		params.put("valOidAtributo", tipoDocumento.getOidTipoDoc());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcionSiccPais", params);
	}

	public void updateTipoDocumento(TipoDocumento tipoDocumento, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoDocumento", tipoDocumento);
		
		Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_DOCUM");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", tipoDocumento.getDescripcion());
		params.put("valOidAtributo", tipoDocumento.getOidTipoDoc());
	
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDescripcionSiccPais", params);
	
	}

	public TipoDocumento getTipoDocumento(Map params) {
		// TODO Auto-generated method stub
		TipoDocumento tipoDocumento = (TipoDocumento) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoDocumento", params);
		
        if (tipoDocumento == null) {
            throw new ObjectRetrievalFailureException(TipoDocumento.class, params);
        }
        
        return tipoDocumento;
	}

	public String getExisteTipoDocumento(TipoDocumento tipoDocumento) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteTipoDocumento", tipoDocumento);
	}

	public void insertCriterioBusqueda(CriterioBusqueda criterioBusqueda,
			Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidObtieneCriterioBusqueda");
		criterioBusqueda.setOidCriterioBus(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertCriteriosBusqueda", criterioBusqueda);
		
	}

	public void updateCriterioBusqueda(CriterioBusqueda criterioBusqueda,
			Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateCriterioBusqueda", criterioBusqueda);
		
	}

	public CriterioBusqueda getCriterioBusqueda(Map params) {
		// TODO Auto-generated method stub
		CriterioBusqueda  criterioBusqueda = (CriterioBusqueda) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneCriterioBusqueda", params);
		
        if (criterioBusqueda == null) {
            throw new ObjectRetrievalFailureException(CriterioBusqueda.class, params);
        }
        
        return criterioBusqueda;
	}

	public String getExisteCriterioBusqueda(CriterioBusqueda criterioBusqueda) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteCriterioBusqueda", criterioBusqueda);
	}

	public List getCriterios() {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getCriteriosBusqueda");
	}

	public List getTipoDocumentosLista() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTiposDocumentosListado");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getAccionBloqueoList()
	 */
	public List getAccionBloqueoList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getAccionBloqueoList",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getProcesoBloqueoList()
	 */
	public List getProcesoBloqueoList() {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getProcesoBloqueoList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getTipoBloqueoList(java.util.Map)
	 */
	public List getTipoBloqueoList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTipoBloqueoList",params);
	}

	public void insertAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario){
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidAccionesProcesoBloqueo");
		accionesProcesoBloqueo.setOid(oid);		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertAccionesProcesoBloqueo", accionesProcesoBloqueo);
		
		/*Map params = new HashMap();
		params.put("nombreTabla", "MAE_TIPO_BLOQU");
		params.put("numAtributo", Constants.ESTADO_ACTIVO);
		params.put("oidIdioma", Constants.ESTADO_ACTIVO);
		params.put("valAtributo", accionesProcesoBloqueo.getDescripcion());
		params.put("valOidAtributo", accionesProcesoBloqueo.getOid());
		
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertDescripcion", params);*/
			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#updateAccionesProcesoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.AccionesProcesoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateAccionesProcesoBloqueo", accionesProcesoBloqueo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getAccionesProcesoBloqueo(java.util.Map)
	 */
	public AccionesProcesoBloqueo getAccionesProcesoBloqueo(Map params) {
		AccionesProcesoBloqueo accionesProcesoBloqueo = (AccionesProcesoBloqueo) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getAccionesProcesoBloqueo", params);
		
        if (accionesProcesoBloqueo == null) {
            throw new ObjectRetrievalFailureException(AccionesProcesoBloqueo.class, params);
        }
        
        return accionesProcesoBloqueo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEEntidadGenericaDAO#getExisteOidAccionesProcesoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.AccionesProcesoBloqueo)
	 */
	public String getExisteOidAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteOidAccionesProcesoBloqueo", accionesProcesoBloqueo);
	}
	
}