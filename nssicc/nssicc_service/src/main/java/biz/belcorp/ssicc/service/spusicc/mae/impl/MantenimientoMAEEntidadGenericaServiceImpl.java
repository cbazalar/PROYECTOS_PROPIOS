package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
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
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
@Service("spusicc.mantenimientoMAEEntidadGenericaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMAEEntidadGenericaServiceImpl extends BaseService implements MantenimientoMAEEntidadGenericaService {
	
	@Resource(name="spusicc.mantenimientoMAEEntidadGenericaDAO")
	private MantenimientoMAEEntidadGenericaDAO mantenimientoMAEEntidadGenericaDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getEntidades(java.lang.String)
	 */
	public List getEntidades(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getEntidades(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getDatosEntidadGenericaByCriteria(java.util.Map)
	 */
	public List getDatosEntidadGenericaByCriteria(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getDatosEntidadGenericaByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getMotivosRechazo()
	 */
	public List getMotivosRechazo() {
		return mantenimientoMAEEntidadGenericaDAO.getMotivosRechazo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getFormasBloqueo()
	 */
	public List getFormasBloqueo() {
		return mantenimientoMAEEntidadGenericaDAO.getFormasBloqueo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#insertTipoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.TipoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.insertTipoBloqueo(tipoBloqueo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#updateTipoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.TipoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoBloqueo(TipoBloqueo tipoBloqueo, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.updateTipoBloqueo(tipoBloqueo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getTipoBloqueo(java.util.Map)
	 */
	public TipoBloqueo getTipoBloqueo(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getTipoBloqueo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#removeTipoBloqueo(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeTipoBloqueo(String id, Usuario usuario) {
		Map params = new HashMap();
		params.put("oid", id);
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		TipoBloqueo tipoBloqueo = mantenimientoMAEEntidadGenericaDAO.getTipoBloqueo(params);
		
		mantenimientoMAEEntidadGenericaDAO.deleteTipoBloqueo(tipoBloqueo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#insertTipoCliente(biz.belcorp.ssicc.spusicc.mae.model.TipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoCliente(TipoCliente tipoCliente, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.insertTipoCliente(tipoCliente, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#updateTipoCliente(biz.belcorp.ssicc.spusicc.mae.model.TipoCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoCliente(TipoCliente tipoCliente, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.updateTipoCliente(tipoCliente, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getTipoCliente(java.util.Map)
	 */
	public TipoCliente getTipoCliente(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getTipoCliente(params);
	}
	
	public void insertSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario){
		mantenimientoMAEEntidadGenericaDAO.insertSubtipoCliente(subtipoCliente, usuario);
	}

	public void updateSubtipoCliente(SubtipoCliente subtipoCliente, Usuario usuario){
		mantenimientoMAEEntidadGenericaDAO.updateSubtipoCliente(subtipoCliente, usuario);
	}

	public SubtipoCliente getSubtipoCliente(Map params){
		return mantenimientoMAEEntidadGenericaDAO.getSubtipoCliente(params);
	}
	
	//EstatusCliente
	
	public void insertEstatusCliente(EstatusCliente estatusCliente, Usuario usuario){
		mantenimientoMAEEntidadGenericaDAO.insertEstatusCliente(estatusCliente, usuario);
	}

	public void updateEstatusCliente(EstatusCliente estatusCliente, Usuario usuario){
		mantenimientoMAEEntidadGenericaDAO.updateEstatusCliente(estatusCliente, usuario);
	}

	public EstatusCliente getEstatusCliente(Map params){
		return mantenimientoMAEEntidadGenericaDAO.getEstatusCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getEstadoPosteriorPosible(java.util.Map)
	 */
	public List getEstadoPosteriorPosible(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getEstadoPosteriorPosible(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getTipoEstadosCliente()
	 */
	public List getTipoEstadosCliente() {
		return mantenimientoMAEEntidadGenericaDAO.getTipoEstadosCliente();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoClienteList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#removeTipoCliente(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeTipoCliente(String id, Usuario usuario) {
		Map params = new HashMap();
		params.put("oid", id);
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		TipoCliente tipoCliente = mantenimientoMAEEntidadGenericaDAO.getTipoCliente(params);
		
		tipoCliente.setIndicadorEstado(Constants.NUMERO_CERO);
		
		mantenimientoMAEEntidadGenericaDAO.updateTipoCliente(tipoCliente, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#removeSubtipoCliente(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeSubtipoCliente(String id, Usuario usuario) {
		Map params = new HashMap();
		params.put("oid", id);
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		SubtipoCliente subtipoCliente = mantenimientoMAEEntidadGenericaDAO.getSubtipoCliente(params);
		
		subtipoCliente.setIndicadorEstado(Constants.NUMERO_CERO);
		
		mantenimientoMAEEntidadGenericaDAO.updateSubtipoCliente(subtipoCliente, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#removeEstatusCliente(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeEstatusCliente(String id, Usuario usuario) {
		Map params = new HashMap();
		params.put("oid", id);
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		EstatusCliente estatusCliente = mantenimientoMAEEntidadGenericaDAO.getEstatusCliente(params);
		
		estatusCliente.setIndicadorEstado(Constants.NUMERO_CERO);
		
		mantenimientoMAEEntidadGenericaDAO.updateEstatusCliente(estatusCliente, usuario);
	}
	
	public boolean getExisteOidTipoCliente(TipoCliente tipoCliente){
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteOidTipoCliente(tipoCliente);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(!StringUtils.equalsIgnoreCase(oid, tipoCliente.getOid())){
				existe = true;
			}
		}
		return existe;
	}
	
	public boolean getExisteOidSubtipoCliente(SubtipoCliente subtipoCliente){
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteOidSubtipoCliente(subtipoCliente);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(!StringUtils.equalsIgnoreCase(oid, subtipoCliente.getOid())){
				existe = true;
			}
		}
		return existe;
	}
	
	public boolean getExisteOidEstatusCliente(EstatusCliente estatusCliente){
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteOidEstatusCliente(estatusCliente);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(!StringUtils.equalsIgnoreCase(oid, estatusCliente.getOid())){
				existe = true;
			}
		}
		return existe;
	}

	public void insertGarantiasPremio(GarantiasPremio garantiasPremio,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertGarantiasPremio(garantiasPremio, usuario);
	}

	public void updateGarantiasPremio(GarantiasPremio garantiasPremio,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateGarantiasPremio(garantiasPremio, usuario);
	}

	public GarantiasPremio getGarantiasPremio(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getGarantiasPremio(params);
	}

	public boolean getExisteGarantiasPremio(GarantiasPremio garantiasPremio) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteGarantiasPremio(garantiasPremio);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, garantiasPremio.getCodigoSAP())){
				existe = true;
			}
		}
		return existe;
	}

	public List getTipoSubClienteList(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoSubClienteList(params);
	}

	public List getTipoClasificacionesList(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoClasificacionesList(params);
	}

	public void insertClasificacion(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertClasificacion(params, usuario);
	}

	public void updateClasificacion(Clasificacion clasificacion, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateClasificacion(clasificacion, usuario);
	}

	public Clasificacion getClasificacion(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getClasificacion(params);
	}

	public boolean getExisteClasificacion(Clasificacion clasificacion) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteClasificacion(clasificacion);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, clasificacion.getCodigoClasificacion())){
				existe = true;
			}
		}
		return existe;
	}

	public List getSubTiposClientesMultipleByCriteriaOID(Map criteria) {
		// TODO Auto-generated method stub
		return interfazDAO.getSubTiposClientesMultipleByCriteriaOID(criteria);
	}

	public List getLista(Map params) {
		// TODO Auto-generated method stub
		return interfazDAO.getLista("T", params);
	}

	public void insertTipoClasificacion(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertTipoClasificacion(params, usuario);
	}

	public void updateTipoClasificacion(TipoClasificacion tipoClasificacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoClasificacion(tipoClasificacion, usuario);
	}

	public TipoClasificacion getTipoClasificacion(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoClasificacion(params);
	}

	public boolean getExisteTipoClasificacion(
		TipoClasificacion tipoClasificacion) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoClasificacion(tipoClasificacion);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, tipoClasificacion.getCodigoTipoClasificacion())){
				existe = true;
			}
		}
		return existe;
	}

	public void insertEstatusCliente(TipoEstatus tipoEstatus, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertEstatusCliente(tipoEstatus, usuario);
	}

	public void updateTipoEstatusCliente(TipoEstatus tipoEstatus,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoEstatusCliente(tipoEstatus, usuario);
	}

	public TipoEstatus getTipoEstatusCliente(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoEstatusCliente(params);
	}

	public boolean getExisteTipoEstatusCliente(TipoEstatus tipoEstatus) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoEstatusCliente(tipoEstatus);
			boolean existe = false;
			if(StringUtils.isNotBlank(oid)){
				if(StringUtils.equalsIgnoreCase(oid, tipoEstatus.getCodigo())){
					existe = true;
				}
			}
		return existe;
	}

	public List getMarcas() {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getMarcas();
	}

	public void insertEstadoCivil(EstadoCivil estadoCivil, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertEstadoCivil(estadoCivil, usuario);
	}

	public void updateEstadoCivil(EstadoCivil estadoCivil, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateEstadoCivil(estadoCivil, usuario);
	}

	public EstadoCivil getTipoEstadoCivil(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoEstadoCivil(params);
	}

	public boolean getExisteTipoEstadoCivil(EstadoCivil estadoCivil) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoEstadoCivil(estadoCivil);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, estadoCivil.getCodigo())){
				existe = true;
			}
		}
	return existe;
	}

	public void insertTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertTipoDireccion(tipoDireccion, usuario);
	}

	public void updateTipoDireccion(TipoDireccion tipoDireccion, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoDireccion(tipoDireccion, usuario);
	}

	public TipoDireccion getTipoDireccion(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoDireccion(params);
	}

	public boolean getExisteTipoDireccion(TipoDireccion tipoDireccion) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoDireccion(tipoDireccion);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, tipoDireccion.getCodigo())){
				existe = true;
			}
		}
	return existe;
	}

	public void insertTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertTipoVinculo(tipoVinculo, usuario);
	}

	public void updateTipoVinculo(TipoVinculo tipoVinculo, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoVinculo(tipoVinculo, usuario);
	}

	public TipoVinculo getTipoVinculo(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoVinculo(params);
	}

	public boolean getExisteTipoVinculo(TipoVinculo tipoVinculo) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoVinculo(tipoVinculo);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, tipoVinculo.getCodigo())){
				existe = true;
			}
		}
	return existe;
	}

	public void insertTipoComunicacion(TipoComunicacion tipoComunicacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertTipoComunicacion(tipoComunicacion, usuario);
	}

	public void updateTipoComunicacion(TipoComunicacion tipoComunicacion,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoComunicacion(tipoComunicacion, usuario);
	}

	public TipoComunicacion getTipoComunicacion(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoComunicacion(params);
	}

	public boolean getExisteTipoComunicacion(TipoComunicacion tipoComunicacion) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoComunicacion(tipoComunicacion);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, tipoComunicacion.getCodigo())){
				existe = true;
			}
		}
	return existe;
	}

	public void insertTipoDocumento(TipoDocumento tipoDocumento, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertTipoDocumento(tipoDocumento, usuario);
	}

	public void updateTipoDocumento(TipoDocumento tipoDocumento, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateTipoDocumento(tipoDocumento, usuario);
	}

	public TipoDocumento getTipoDocumento(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoDocumento(params);
	}

	public boolean getExisteTipoDocumento(TipoDocumento tipoDocumento) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteTipoDocumento(tipoDocumento);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, tipoDocumento.getCodigo())){
				existe = true;
			}
		}
		return existe;
	}

	public void insertCriterioBusqueda(CriterioBusqueda criterioBusqueda,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.insertCriterioBusqueda(criterioBusqueda, usuario);
	}

	public void updateCriterioBusqueda(CriterioBusqueda criterioBusqueda,
			Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAEEntidadGenericaDAO.updateCriterioBusqueda(criterioBusqueda, usuario);
	}

	public CriterioBusqueda getCriterioBusqueda(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getCriterioBusqueda(params);
	}

	public boolean getExisteCriterioBusqueda(CriterioBusqueda criterioBusqueda) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteCriterioBusqueda(criterioBusqueda);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(StringUtils.equalsIgnoreCase(oid, criterioBusqueda.getOidCriterioBus())){
				existe = true;
			}
			existe = true;
		}
		return existe;
	}

	public List getCriterios() {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getCriterios();
	}

	public List getTipoDocumentosLista() {
		// TODO Auto-generated method stub
		return mantenimientoMAEEntidadGenericaDAO.getTipoDocumentosLista();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getAccionBloqueoList()
	 */
	public List getAccionBloqueoList(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getAccionBloqueoList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getProcesoBloqueoList()
	 */
	public List getProcesoBloqueoList() {
		return mantenimientoMAEEntidadGenericaDAO.getProcesoBloqueoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getTipoBloqueoList(java.util.Map)
	 */
	public List getTipoBloqueoList(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getTipoBloqueoList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#insertAccionesProcesoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.AccionesProcesoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.insertAccionesProcesoBloqueo(accionesProcesoBloqueo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#updateAccionesProcesoBloqueo(biz.belcorp.ssicc.spusicc.mae.model.AccionesProcesoBloqueo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo, Usuario usuario) {
		mantenimientoMAEEntidadGenericaDAO.updateAccionesProcesoBloqueo(accionesProcesoBloqueo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEEntidadGenericaService#getAccionesProcesoBloqueo(java.util.Map)
	 */
	public AccionesProcesoBloqueo getAccionesProcesoBloqueo(Map params) {
		return mantenimientoMAEEntidadGenericaDAO.getAccionesProcesoBloqueo(params);
	}

	public boolean getExisteOidAccionesProcesoBloqueo(AccionesProcesoBloqueo accionesProcesoBloqueo) {
		String oid = mantenimientoMAEEntidadGenericaDAO.getExisteOidAccionesProcesoBloqueo(accionesProcesoBloqueo);
		boolean existe = false;
		if(StringUtils.isNotBlank(oid)){
			if(!StringUtils.equalsIgnoreCase(oid, accionesProcesoBloqueo.getOid())){
				existe = true;
			}
		}
		return existe;
	}
}