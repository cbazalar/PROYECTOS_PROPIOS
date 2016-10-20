/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoAtencionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;

@Service("spusicc.mantenimientoRECIngresoAtencionesService")
public class MantenimientoRECIngresoAtencionesServiceImpl extends BaseService implements MantenimientoRECIngresoAtencionesService{

	@Resource(name="spusicc.mantenimientoRECIngresoAtencionesDAO")
	MantenimientoRECIngresoAtencionesDAO mantenimientoRECIngresoAtencionesDAO;

	/**
	 * @return Returns the mantenimientoRECIngresoAtencionesDAO.
	 */
	public MantenimientoRECIngresoAtencionesDAO getMantenimientoRECIngresoAtencionesDAO() {
		return mantenimientoRECIngresoAtencionesDAO;
	}

	/**
	 * @param mantenimientoRECIngresoAtencionesDAO The mantenimientoRECIngresoAtencionesDAO to set.
	 */
	public void setMantenimientoRECIngresoAtencionesDAO(
			MantenimientoRECIngresoAtencionesDAO mantenimientoRECIngresoAtencionesDAO) {
		this.mantenimientoRECIngresoAtencionesDAO = mantenimientoRECIngresoAtencionesDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getCodigoConsultora(java.util.Map)
	 */
	public String getCodigoConsultora(Map criteria){
		return mantenimientoRECIngresoAtencionesDAO.getCodigoConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getReclamosPremio(java.util.Map)
	 */
	public List getReclamosPremio(Map criteria){
		return mantenimientoRECIngresoAtencionesDAO.getReclamosPremio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getReclamosMatriz(java.util.Map)
	 */
	public List getReclamosMatriz(Map criteria){
		return mantenimientoRECIngresoAtencionesDAO.getReclamosMatriz(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#procesarAtenciones(java.util.Map, java.util.List)
	 */
	public String procesarAtenciones(Map criteria, List procesarList){
		// Cargo la tabla temporal con los detalles a procesar
		for (int i = 0; i < procesarList.size(); i++) {
			BoletaRecojoDetalle brDetalle = new BoletaRecojoDetalle(); 
			brDetalle = (BoletaRecojoDetalle)procesarList.get(i);
			mantenimientoRECIngresoAtencionesDAO.insertTemporalDetallesIngresoAtencion(brDetalle);					
		}
		// Ejecuto el proceso
		String numeroLote =(String)criteria.get("numeroLote"); 
		if(StringUtils.isEmpty(numeroLote))	
			numeroLote=mantenimientoRECIngresoAtencionesDAO.getNumeroLote();
		criteria.put("numeroLote", numeroLote);
		return mantenimientoRECIngresoAtencionesDAO.procesarIngresoAtenciones(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getCodigosParamIngreAtenc(java.util.Map)
	 */
	public List getCodigosParamIngreAtenc(Map params) {
		return this.mantenimientoRECIngresoAtencionesDAO.getCodigosParamIngreAtenc(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getTipoOperacionList(java.util.Map)
	 */
	public List getTipoOperacionList(Map params) {
		return this.mantenimientoRECIngresoAtencionesDAO.getTipoOperacionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getAnulacion()
	 */
	public List getAnulacion(Map params) {		
		return mantenimientoRECIngresoAtencionesDAO.getAnulacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getAtencion()
	 */
	public List getAtencion(Map params) {
		return mantenimientoRECIngresoAtencionesDAO.getAtencion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getDetalleAnulacion(java.util.Map)
	 */
	public List getDetalleAnulacion(Map map) {
		return mantenimientoRECIngresoAtencionesDAO.getDetalleAnulacion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getDetalleAtencion(java.util.Map)
	 */
	public List getDetalleAtencion(Map map) {
		return mantenimientoRECIngresoAtencionesDAO.getDetalleAtencion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#validarEliminacionLote(java.util.Map)
	 */
	public void validarEliminacionLote(Map map) {
		mantenimientoRECIngresoAtencionesDAO.validarEliminacionLote(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#eliminarLoteAtencion(java.util.Map)
	 */
	public void eliminarLoteAtencion(Map map) {
		mantenimientoRECIngresoAtencionesDAO.eliminarLoteAtencion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getObtenerCampahniaActual(java.util.Map)
	 */
	public String getObtenerCampahniaActual(Map criteria) {
		return this.mantenimientoRECIngresoAtencionesDAO.getObtenerCampahniaActual(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#insertaClienteFile(java.util.List, java.util.Map)
	 */
	public void insertaClienteFile(List clienteList, Map criteria){
		LabelValue bean = new LabelValue();
		String codigo = "";
		String nombre = "";
		String indicador = "1";

		for (int i = 0; i < clienteList.size(); i++) {
			bean = (LabelValue)clienteList.get(i);
			codigo = bean.getLabel();
			nombre = bean.getValue();

			criteria.put("codigo", codigo);
			if (nombre == null){
				indicador = "0";
				criteria.put("oidConsultora", Constants.NUMERO_CERO);
			}
			else{
				indicador = "1";
				criteria.put("oidConsultora", mantenimientoRECIngresoAtencionesDAO.getOidCliente(criteria));
			}

			criteria.put("indicador", indicador);

			mantenimientoRECIngresoAtencionesDAO.insertaClienteFile(criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getOidCargaCliente()
	 */
	public String getOidCargaCliente(){
		 return mantenimientoRECIngresoAtencionesDAO.getOidCargaCliente();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getCargaClienteList(java.util.Map)
	 */
	public List getCargaClienteList(Map criteria){
		return mantenimientoRECIngresoAtencionesDAO.getCargaClienteList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getResumenCargaClienteList(java.util.Map)
	 */
	public List getResumenCargaClienteList(Map criteria){
		return mantenimientoRECIngresoAtencionesDAO.getResumenCargaClienteList(criteria);
	}

	public void actualizarRegistroSinError(Map criteria, List procesarList) {
		// Cargo la tabla temporal con los detalles a procesar
				double idProducto;
				String codPais;
				String codCliente;
				String usuario;
				Map map;
				
				String numeroLote =(String)criteria.get("numeroLote"); 
				if(StringUtils.isEmpty(numeroLote))	
					numeroLote=mantenimientoRECIngresoAtencionesDAO.getNumeroLote();
				
				codPais = (String)criteria.get("codigoPais");
				codCliente = (String)criteria.get("codigoCliente");					
				numeroLote = (String)criteria.get("numeroLote");
				usuario = (String)criteria.get("codigoUsuario");
				
				for (int i = 0; i < procesarList.size(); i++) {
					map = new HashMap();
					BoletaRecojoDetalle brDetalle = (BoletaRecojoDetalle)procesarList.get(i);					
					idProducto = Double.parseDouble(brDetalle.getIdProducto().trim());					
					map.put("idProducto",idProducto);
					map.put("codPais",codPais);
					map.put("codCliente",codCliente);
					map.put("numLote",numeroLote);
					map.put("usuario",usuario);
					mantenimientoRECIngresoAtencionesDAO.updateRegistroSinError(map);					
				}				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getCodigoConsultoraPorDocumentoIdentidad(java.util.Map)
	 */
	public String getCodigoConsultoraPorDocumentoIdentidad(Map criteria) {
		return mantenimientoRECIngresoAtencionesDAO.getCodigoConsultoraPorDocumentoIdentidad(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoAtencionesService#getLongitudTipoDocumento(java.util.Map)
	 */
	public String getLongitudTipoDocumento(Map criteria) {
		return mantenimientoRECIngresoAtencionesDAO.getLongitudTipoDocumento(criteria);
	}
}