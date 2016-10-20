package biz.belcorp.ssicc.service.spusicc.ruv.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVDocumentosContablesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVDocumentosContablesService;

/**
 * @author peextdoliva
 */
@Service("spusicc.mantenimientoRUVDocumentosContablesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRUVDocumentosContablesServiceImpl extends BaseService implements MantenimientoRUVDocumentosContablesService{
	
	@Resource(name="spusicc.mantenimientoRUVDocumentosContablesDAO")
	private MantenimientoRUVDocumentosContablesDAO mantenimientoRUVDocumentosContablesDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getTipoDocumentoContable()
	 */
	public List getTipoDocumentoContable(){
		return mantenimientoRUVDocumentosContablesDAO.getTipoDocumentoContable();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getLimitesDocumentosLegales(java.util.Map)
	 */
	public List getLimitesDocumentosLegales(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getLimitesDocumentosLegales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getTotalDocumentosLegales(java.util.Map)
	 */
	public String getTotalDocumentosLegales(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getTotalDocumentosLegales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getSinImpresionSinUnidades(java.util.Map)
	 */
	public List getSinImpresionSinUnidades(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getSinImpresionSinUnidades(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#deleteRegistroUnicoVentasById(java.lang.String[])
	 */
	public void deleteRegistroUnicoVentasById(String[] oidRUVList){
		for (int i = 0; i < oidRUVList.length; i++) {
			mantenimientoRUVDocumentosContablesDAO.deleteRegistroUnicoVentasById(oidRUVList[i]);
		}				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getLegalesDuplicados(java.util.Map)
	 */
	public List getLegalesDuplicados(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getLegalesDuplicados(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getSinAsignarNumeroDocumento(java.util.Map)
	 */
	public List getSinAsignarNumeroDocumento(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getSinAsignarNumeroDocumento(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeGenerarDataDocumentosContables(java.util.Map)
	 */
	public void executeGenerarDataDocumentosContables(Map params) {
		mantenimientoRUVDocumentosContablesDAO.executeGenerarDataDocumentosContables(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosPorDias(java.util.Map)
	 */
	public String getAsignarNulosPorDias(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getAsignarNulosPorDias(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosPorRango(java.util.Map)
	 */
	public String getAsignarNulosPorRango(Map criteria){
		return mantenimientoRUVDocumentosContablesDAO.getAsignarNulosPorRango(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeAsignarNulosPorDias(java.util.Map)
	 */
	public void executeAsignarNulosPorDias(Map criteria){
		mantenimientoRUVDocumentosContablesDAO.executeAsignarNulosPorDias(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeAsignarNulosPorRango(java.util.Map)
	 */
	public void executeAsignarNulosPorRango(Map criteria){
		mantenimientoRUVDocumentosContablesDAO.executeAsignarNulosPorRango(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getDescripcionAcceso(java.util.Map)
	 */
	public String getDescripcionAcceso(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getDescripcionAcceso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getDescripcionSubAcceso(java.util.Map)
	 */
	public String getDescripcionSubAcceso(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getDescripcionSubAcceso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getOidSegAcceso(java.util.Map)
	 */
	public String getOidSegAcceso(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getOidSegAcceso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getOidSegSubAcceso(java.util.Map)
	 */
	public String getOidSegSubAcceso(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getOidSegSubAcceso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getDocContablesInternos(java.util.Map)
	 */
	public List getDocContablesInternos(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getDocContablesInternos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getDocContablesLegales(java.util.Map)
	 */
	public List getDocContablesLegales(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getDocContablesLegales(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeGenerarDataDocumentosLegalDuplicado(java.util.Map)
	 */
	public List executeGenerarDataDocumentosLegalDuplicado(Map params) {
		return this.mantenimientoRUVDocumentosContablesDAO.executeGenerarDataDocumentosLegalDuplicado(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getDescCanal(java.util.Map)
	 */
	public String getDescCanal(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getDescCanal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getOidCanalxCod(java.util.Map)
	 */
	public String getOidCanalxCod(Map criteria) {
		return this.mantenimientoRUVDocumentosContablesDAO.getOidCanalxCod(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#deleteDocumentoLegales(java.util.Map)
	 */
	public void deleteDocuLegalesLimites(Map criteria) {
		mantenimientoRUVDocumentosContablesDAO.deleteDocuLegalesLimites(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#deleteDocuLegales(java.util.Map)
	 */
	public void deleteDocuLegales(Map criteria) {
		mantenimientoRUVDocumentosContablesDAO.deleteDocuLegales(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#deleteDocuInternos(java.util.Map)
	 */
	public void deleteDocuInternos(Map criteria) {
		mantenimientoRUVDocumentosContablesDAO.deleteDocuInternos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getIndicadorRUVEliminarDocumentoContable(java.lang.String)
	 */
	public String getIndicadorRUVEliminarDocumentoContable(String codigoPais) {
		return mantenimientoRUVDocumentosContablesDAO.getIndicadorRUVEliminarDocumentoContable(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getIndicadorActivacionDocumentoContable(java.util.Map)
	 */
	public String getIndicadorActivacionDocumentoContable(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getIndicadorActivacionDocumentoContable(java.util.Map)");
		String resultado = mantenimientoRUVDocumentosContablesDAO.getIndicadorActivacionDocumentoContable(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getIndicadorActivacionDocumentoContable(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getLimitesControlDocumentosLegales(java.util.Map)
	 */
	public List getLimitesControlDocumentosLegales(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getLimitesControlDocumentosLegales(java.util.Map)");
		List resultado = mantenimientoRUVDocumentosContablesDAO.getLimitesControlDocumentosLegales(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getLimitesControlDocumentosLegales(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getTotalControlDocumentosLegales(java.util.Map)
	 */
	public String getTotalControlDocumentosLegales(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getTotalControlDocumentosLegales(java.util.Map)");
		String resultado = mantenimientoRUVDocumentosContablesDAO.getTotalControlDocumentosLegales(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getTotalControlDocumentosLegales(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getNumeroControlDocLeg(java.util.Map)
	 */
	public List getNumeroControlDocLeg(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getNumeroControlDocLeg(java.util.Map)");
		List resultado = mantenimientoRUVDocumentosContablesDAO.getNumeroControlDocLeg(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getNumeroControlDocLeg(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeGenerarDataNumeroControlDuplicado(java.util.Map)
	 */
	public List executeGenerarDataNumeroControlDuplicado(Map params) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - executeGenerarDataNumeroControlDuplicado(java.util.Map)");
		List resultado = mantenimientoRUVDocumentosContablesDAO.executeGenerarDataNumeroControlDuplicado(params);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - executeGenerarDataNumeroControlDuplicado(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getSinAsignarNumeroControl(java.util.Map)
	 */
	public List getSinAsignarNumeroControl(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getSinAsignarNumeroControl(java.util.Map)");
		List resultado = mantenimientoRUVDocumentosContablesDAO.getSinAsignarNumeroControl(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getSinAsignarNumeroControl(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosDiasPais(java.util.Map)
	 */
	public List getAsignarNulosDiasPais(Map criteria) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - getAsignarNulosDiasPais(java.util.Map)");
		List resultado = mantenimientoRUVDocumentosContablesDAO.getAsignarNulosDiasPais(criteria);
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - getAsignarNulosDiasPais(java.util.Map) - Resultado:" + resultado.size());
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#updateNulosDiasPais(java.util.List, java.lang.String[], java.lang.String)
	 */
	public void updateNulosDiasPais(List lista, String[] numeroControl, String codigoPais) {
		log.info("Entro a MantenimientoRUVDocumentosContablesServiceImpl - updateNulosDiasPais(java.util.List, java.lang.String[], java.lang.String)");
		
		Map criteria = null;
		
		for(int i=0;i<lista.size();i++){
			criteria = (HashMap) lista.get(i);
			criteria.put("numeroControl", Integer.parseInt(numeroControl[i]));
			criteria.put("codigoPais", codigoPais);
			
			mantenimientoRUVDocumentosContablesDAO.updateNulosDiasPais(criteria);
		}
		
		log.info("Salio a MantenimientoRUVDocumentosContablesServiceImpl - updateNulosDiasPais(java.util.List, java.lang.String[], java.lang.String)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosPorDiasVenezuela(java.util.Map)
	 */
	public String getAsignarNulosPorDiasVenezuela(Map criteria) {
		return mantenimientoRUVDocumentosContablesDAO.getAsignarNulosPorDiasVenezuela(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosPorRangoVenezuela(java.util.Map)
	 */
	public String getAsignarNulosPorRangoVenezuela(Map criteria) {
		return mantenimientoRUVDocumentosContablesDAO.getAsignarNulosPorRangoVenezuela(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeAsignarNulosPorDiasVenezuela(java.util.Map)
	 */
	public void executeAsignarNulosPorDiasVenezuela(Map criteria) {
		mantenimientoRUVDocumentosContablesDAO.executeAsignarNulosPorDiasVenezuela(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#executeAsignarNulosPorRangoVenezuela(java.util.Map)
	 */
	public void executeAsignarNulosPorRangoVenezuela(Map criteria) {
		mantenimientoRUVDocumentosContablesDAO.executeAsignarNulosPorRangoVenezuela(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#getAsignarNulosDiasVenezuela(java.util.Map)
	 */
	public List getAsignarNulosDiasVenezuela(Map criteria) {
		List resultado = mantenimientoRUVDocumentosContablesDAO.getAsignarNulosDiasVenezuela(criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ruv.service.MantenimientoRUVDocumentosContablesService#updateNulosDiasVenezuela(java.util.List, java.lang.String[], java.lang.String)
	 */
	public void updateNulosDiasVenezuela(List lista, String[] numeroControl, String codigoPais) {
		Map criteria = null;
		
		for(int i=0;i<lista.size();i++){
			criteria = (HashMap) lista.get(i);
			//criteria.put("numeroControl", Integer.parseInt(numeroControl[i]));
			criteria.put("numeroControl", numeroControl[i]);
			criteria.put("codigoPais", codigoPais);
			
			mantenimientoRUVDocumentosContablesDAO.updateNulosDiasVenezuela(criteria);
		}
	}
}