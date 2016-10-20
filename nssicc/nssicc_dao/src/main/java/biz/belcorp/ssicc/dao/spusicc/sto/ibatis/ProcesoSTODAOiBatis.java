package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidos;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPolizas;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;

/**
 * @author USER
 *
 */
@Repository("spusicc.procesoSTODAO")
public class ProcesoSTODAOiBatis extends BaseDAOiBatis implements ProcesoSTODAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getValidacionesTipoDocumento(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitado)
	 */
	public List getValidacionesProceso(HistoricoTipoDocumento historico) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getValidacionesProceso", historico);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#inicializeRegistrosProcesados(java.util.Map)
	 */
	public void inicializeRegistrosProcesados(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.inicializeRegistrosProcesados", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateDocumentoForValidate(java.util.Map)
	 */
	public void updateDocumentoForValidate(Map queryParams) {
		
		HashSet hashset = (HashSet)queryParams.get("HashSetSTO");
		
		Iterator iterador = hashset.iterator();
		
		getSqlMapClientTemplate().delete("spusicc.ProcesoSTOGenericoSQL.deleteTemporalDocumentoSTO", null);
		while(iterador.hasNext()){
		    DocumentoDigitadoPK documento = (DocumentoDigitadoPK)iterador.next();

		    getSqlMapClientTemplate().insert("spusicc.ProcesoSTOGenericoSQL.insertTemporalDocumentoSTO", documento);
		}
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateDocumentoForValidate", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeValidacion(java.util.Map)
	 */
	public void executeValidacion(Map queryParams, String namespaceSTO) {
		
		ValidacionDocumento validacionDocumento = (ValidacionDocumento)queryParams.get("validacionDocumento");
		String metodo = validacionDocumento.getExecuteProcedure();
		
		getSqlMapClientTemplate().update("spusicc.".concat(namespaceSTO).concat(".").concat(metodo), queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeCargaRegistrosValidos(java.util.Map)
	 */
	public void executeCargaRegistrosValidos(Map queryParams, String namespaceSTO) {
		TipoDocumentoDigitado tipoDocumento = (TipoDocumentoDigitado)queryParams.get("tipoDocumento");
		String metodo = tipoDocumento.getExeProcEnvi();
		if(metodo != null)
			getSqlMapClientTemplate().update("spusicc.".concat(namespaceSTO).concat(".").concat(metodo), queryParams);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getTipoDocumentoDigitado(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado(
			TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK) {
		return (TipoDocumentoDigitado)getSqlMapClientTemplate().queryForObject("spusicc.ProcesoSTOGenericoSQL.getTipoDocumentoDigitado", tipoDocumentoDigitadoPK);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#executeEnvioMailSCC(java.util.Map)
	 */
	public void executeEnvioMailSCC(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOSolicitudCreditoSQL.executeEnvioMailSCC", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getEnvioMailsSCCList(java.util.Map)
	 */
	public List getEnvioMailsSCCList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOSolicitudCreditoSQL.getEnvioMailsSCCList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#getEnvioMailsSCCParams(java.util.Map)
	 */
	public List getEnvioMailsSCCParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOSolicitudCreditoSQL.getEnvioMailsSCCParams",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEjecucionValidacionesDAO#deleteEnvioMailsSCC(java.util.Map)
	 */
	public void deleteEnvioMailsSCC(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ProcesoSTOSolicitudCreditoSQL.deleteEnvioMailsSCC", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getParametroSTO(java.util.Map)
	 */
	public String getParametroSTO(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesoSTOGenericoSQL.getParametroSTO", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#approveValidacionDocumentoSTO(java.util.Map, java.lang.String)
	 */
	public void approveValidacionDocumentoSTO(Map queryParams,String namespaceSTO) {
		GestionDocumento gestionDocumento = (GestionDocumento)queryParams.get("gestionDocumento");
		String metodo = gestionDocumento.getExeProcApro();
		
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateAprobarDocumentosSTO", queryParams);
		if(metodo!=null) getSqlMapClientTemplate().update("spusicc.".concat(namespaceSTO).concat(".").concat(metodo), queryParams);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#disapproveValidacionDocumentoSTO(java.util.Map, java.lang.String)
	 */
	public void disapproveValidacionDocumentoSTO(Map queryParams,String namespaceSTO) {
		GestionDocumento gestionDocumento = (GestionDocumento)queryParams.get("gestionDocumento");
		String metodo = gestionDocumento.getExeProcDesa();
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateDesaprobarDocumentosSTO", queryParams);
		if(metodo!=null) getSqlMapClientTemplate().update("spusicc.".concat(namespaceSTO).concat(".").concat(metodo), queryParams);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#rejectDocumentoSTO(java.util.Map, java.lang.String)
	 */
	public void rejectDocumentoSTO(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateRechazarDocumentosSTO",queryParams);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeEliminarPedidosOrdenCompra(java.util.Map)
	 */
	public void executeAuditoriaProcesoSTO(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.executeAuditoriaProcesoSTO", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getOrigenesDocumentoByCriteria(java.util.Map)
	 */
	public List getOrigenSTOByTipoDocumento(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getOrigenSTOByTipoDocumento", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getDocumentoDigitadoPKByLote(java.util.Map)
	 */
	public List getDocumentoDigitadoPKByLote(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getDocumentoDigitadoPKByLote", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeEliminarPedidosOrdenCompra(java.util.Map)
	 */
	public void executeEliminarPedidosOrdenCompra(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeEliminarPedidosOrdenCompra", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeEliminarCDR(java.lang.String[])
     */
    public void executeEliminarCDR(Map criteria){    	
   		getSqlMapClientTemplate().update("spusicc.ProcesoSTOPostVentaSQL.executeEliminarCDR", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getIndicadorModificacionCodigoCliente(java.util.Map)
     */
    public String getIndicadorModificacionCodigoCliente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesoSTOGenericoSQL.getIndicadorModificacionCodigoCliente", criteria);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getZonaArriboSTOByTipoDocumento(java.util.Map)
     */
    public List getZonaArriboSTOByTipoDocumento(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getZonaArriboSTOByTipoDocumento", params);
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getCargaEjecucionByDocumento(java.util.Map)
     */
    public List getCargaEjecucionByDocumento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getCargaEjecucionByDocumento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaPedidoPostVenta(java.util.Map)
	 */
	public List getConsultaPedidoPostVenta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getPostVentaCabecera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executePulirDataActualizada(java.util.Map)
	 */
	public void executePulirDataActualizada(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executePulirDataActualizada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getSecuenciaConsultaPedidos()
	 */
	public String getSecuenciaConsultaDocumento() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesoSTOGenericoSQL.getSecuenciaConsultaDocumento", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaPedidosList(java.util.Map)
	 */
	public List getConsultaPedidosList(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertTemporalEliminarPedido", criteria);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaPedidosList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeEliminarPedido(java.util.Map)
	 */
	public void executeEliminarPedido(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeEliminarPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeBloquearEliminarPedido(java.util.Map)
	 */
	public void executeBloquearEliminarDocumento(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeBloquearEliminarDocumento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateEliminarPedido(java.util.Map)
	 */
	public void updateEliminarPedido(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateEliminarPedido", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaPedidosGP1List(java.util.Map)
	 */
	public List getConsultaPedidosGP1List(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaPedidosGP1List", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#reversarPedidosGP1(java.util.Map)
	 */
	public void reversarPedidosGP1(Map criteria) {
		//getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deletePedidoGP1Det", criteria);
		//getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deletePedidoGP1Cab", criteria);
		//getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateReversarDocumentoDet", criteria);
		//getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateReversarDocumentoCab", criteria);
		//getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateReversarConsolidado", criteria);
		//sb
		//getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deletePremioElegidos", criteria);
		
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeReversarPedidosGP", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getindicadorDesmarcarAgrupacion(java.util.Map)
	 */
	public String getindicadorDesmarcarAgrupacion(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getindicadorDesmarcarAgrupacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaPolizasFamiliaSeguraList(java.util.Map)
	 */
	public List getConsultaPolizasFamiliaSeguraList(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertTemporalEliminarPolizasFamiliaSegura", criteria);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaPolizasFamiliaSeguraList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateEliminarPoliza(java.util.Map)
	 */
	public void updateEliminarPoliza(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateEliminarPoliza", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeEliminarPoliza(java.util.Map)
	 */
	public void executeEliminarPoliza(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOFamiliaSeguraSQL.executeEliminarPoliza", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getParametroGenericoSistema(java.util.Map)
	 */
	public String getParametroGenericoSistema(Map criteria) {
		return (String)getSqlMapClientTemplate(). queryForObject("spusicc.ProcesosSTOSQL.getParametroGenericoSistema", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateLoteSTO(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public String updateLoteSTO(TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK) {
		Map params = new HashMap();
		String numeroLoteSTO ="";
		params.put("codPais", tipoDocumentoDigitadoPK.getCodPais());
		params.put("codTipoDocu", tipoDocumentoDigitadoPK.getCodTipoDocu());
		params.put("numeroLoteSTO", numeroLoteSTO);		
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateLoteSTO", params);
		return (String)params.get("numeroLoteSTO");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaPedidosOnlineList(java.util.Map)
	 */
	public List getConsultaPedidosOnlineList(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertTemporalEliminarPedidoOnLine", criteria);
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaPedidosList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateDocumentoForProcess(java.util.Map)
	 */
	public void updateDocumentoForProcess(Map queryParams) {
        List stoList = (List)queryParams.get("stoList");
        getSqlMapClientTemplate().delete("spusicc.ProcesoSTOGenericoSQL.deleteTemporalDocumentoSTO", null);
	    for (int i = 0; i < stoList.size(); i++) {
	    	 Object stoItem =  stoList.get(i);
	    	 
	    	 if(stoItem instanceof DocumentoDigitadoPK) {
	    		 DocumentoDigitadoPK documento = (DocumentoDigitadoPK)stoItem;
	    		 getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.insertTemporalDocumentoSTO", documento);
	    	 }
		}
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateDocumentoForProcess", queryParams);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateDocumentoForDelete(java.util.List, java.util.Map)
	 */
	public void updateDocumentoForDelete(List stoList, Map queryParams) {
		for (int i = 0; i < stoList.size(); i++) {
			DocumentoDigitadoPK documento = (DocumentoDigitadoPK)stoList.get(i);
			queryParams.put("documento", documento);
			getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateDocumentoForDelete", queryParams);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeDeletePedido(java.util.Map)
	 */
	public void executeDeletePedido(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOOrdenCompraSQL.executeDeletePedido", queryParams);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getSTOListByPedidoList(java.util.List, java.util.Map)
	 */
	public List getSTOListByPedidoList(List pedidoList, Map queryParams) {

		for (int i = 0; i < pedidoList.size(); i++) {
			ConsultaPedidos pedido = (ConsultaPedidos) pedidoList.get(i);			
			queryParams.put("pedido", pedido);
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateEliminarPedidoList", queryParams);
		}
		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSTOListByPedidoList", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getSTOListByPedidoList(java.util.List, java.util.Map)
	 */
	public List getSTOListByPolizaList(List polizaList, Map queryParams) {

		for (int i = 0; i < polizaList.size(); i++) {
			ConsultaPolizas poliza = (ConsultaPolizas) polizaList.get(i);
			queryParams.put("poliza", poliza);
			getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateEliminarPolizaList", queryParams);
		}
		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getSTOListByPolizaList", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getValidarCuponPeriodoCliente(java.util.Map)
	 */
	public Integer getValidarCuponPeriodoCliente(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidarCuponPeriodoCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getValidarDeudaCliente(java.util.Map)
	 */
	public Integer getValidarDeudaCliente(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidarDeudaCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaDetallePedidoGP1_GP2(java.util.Map)
	 */
	public List getConsultaDetallePedidoGP1_GP2(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaDetallePedidoGP1_GP2", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getConsultaDetallePedidoGP3_GP4_GP5(java.util.Map)
	 */
	public List getConsultaDetallePedidoGP3_GP4_GP5(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getConsultaDetallePedidoGP3_GP4_GP5", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getRegionZonaTemporal(java.util.Map)
	 */
	public String getRegionZonaTemporal(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getRegionZonaTemporal", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getTipoDocumentoByValidacion(java.util.Map)
	 */
	public String getTipoDocumentoByValidacion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getTipoDocumentoByValidacion", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getPedidoSeguidoSTOList(java.util.Map)
	 */
	public List getPedidoSeguidoSTOList(Map criteria) {		
		getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.executeCargaSeguimientoPedidos",criteria);		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getPedidoSeguidoSTOList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getFechaEntregaConfirmada(java.util.Map)
	 */
	public String getFechaEntregaConfirmada(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getFechaEntregaConfirmada", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeCargaSeguimientoPedidos(java.util.Map)
	 */
	public void executeCargaSeguimientoPedidos(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.executeCargaSeguimientoPedidos",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeDeleteErrores(biz.belcorp.ssicc.spusicc.sto.model.HistoricoTipoDocumento)
	 */
	public void executeDeleteErrores(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.executeDeleteErrores", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getReclamosEliminados(java.util.Map)
	 */
	public List getReclamosEliminados(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getReclamosEliminados", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getConsultoraCuponExistenteByCriteria(java.util.Map)
	 */
	public String getPedidoCuponExistenteByCriteria(Map params) {
		String defecto = "";
		List aux = getSqlMapClientTemplate().queryForList(
				"spncd.CuponesSQL.getPedidoCuponExistenteByCriteria", params);
		if (aux.size() > 0) {
			defecto = (String) aux.get(0);
		}
		return defecto;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getMensajesRechazo(java.util.Map)
	 */
	public List getMensajesRechazo(Map criteria){
		
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getMensajesRechazo", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateRechazoOCC(java.util.Map)
	 */
	public void updateRechazoOCC(Map queryParams){
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOOrdenCompraSQL.updateRechazoOCC", queryParams);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#recoverRejectDocumentoSTO(java.util.Map)
	 */
	public void executeRecoverRejectDocumentoSTO(Map queryParams){
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.executeRecoverRejectDocumentoSTO",queryParams);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeRecoverRejectOCC(java.util.Map)
	 */
	public void executeRecoverRejectOCC(Map queryParams){
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOOrdenCompraSQL.executeRecoverRejectOCC", queryParams);
	}
	
	/**
	 * @param queryParama
	 * @return
	 */
	public List getDocumentoDigitadoPKByProceso(Map queryParama){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesoSTOGenericoSQL.getDocumentoDigitadoPKByProceso", queryParama);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeBeforeValidation(java.util.Map)
	 */
	public void executeBeforeValidation(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.executeBeforeValidation", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#executeAfterValidation(java.util.Map)
	 */
	public void executeAfterValidation(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.executeAfterValidation", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#getListForValidate(java.util.Map, java.lang.String)
	 */
	public List getListForValidate(String namespaceSTO, Map queryParams) {
		return getSqlMapClientTemplate().queryForList("spusicc."+namespaceSTO+".getListForValidate", queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateValidRecord(java.util.Map)
	 */
	public void updateValidRecord(Map queryParams) {
		getSqlMapClientTemplate().update("spusicc.ProcesoSTOGenericoSQL.updateValidRecord", queryParams);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTODAO#updateSTOData(java.lang.String, java.util.Map)
	 */
	public void updateSTOData(String namespaceSTO, Map row) {
		getSqlMapClientTemplate().update("spusicc."+namespaceSTO+".updateSTOData", row);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO#getListOCCDataCrediticiaConsultora(java.lang.String, java.util.Map)
	 */
	public List getListOCCDataCrediticiaConsultora(String namespaceSTO, Map queryParams) {
		return getSqlMapClientTemplate().queryForList("spusicc."+namespaceSTO+".getListOCCDataCrediticiaConsultora", queryParams);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO#updateOCCDataCrediticiaConsultora(java.lang.String, java.util.Map)
	 */
	public void updateOCCDataCrediticiaConsultora(String namespaceSTO, Map row) {
		getSqlMapClientTemplate().update("spusicc."+namespaceSTO+".updateOCCDataCrediticiaConsultora", row);		
	}
	

}