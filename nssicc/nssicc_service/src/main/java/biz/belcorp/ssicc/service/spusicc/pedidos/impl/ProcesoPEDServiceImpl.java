package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;


@Service("spusicc.procesoPEDService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDServiceImpl extends BaseService implements ProcesoPEDService{

	@Resource(name="spusicc.procesoPEDDAO")
	ProcesoPEDDAO procesoPEDDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getPedidosDigitadosByCriteria(java.util.Map)
	 */
	public List getPedidosDigitadosByCriteria(Map criteria) {
		return procesoPEDDAO.getPedidosDigitadosByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#executeConsolidadoPedidos(java.util.Map)
	 */
	public void executeConsolidadoPedidos(Map params){
		procesoPEDDAO.executeConsolidadoPedidos(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getIndicadorActividadPROL(java.util.Map)
	 */
	public String getIndicadorActividadPROL(Map params){
		return procesoPEDDAO.getIndicadorActividadPROL(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#executeInicializaStock()
	 */
	public void executeInicializaStock(){
		procesoPEDDAO.executeInicializaStock();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#executeActivaFlagControlActividadPROL(java.util.Map)
	 */
	public void executeEnvioPortalSICCFinDia(Map params){
		procesoPEDDAO.executeEnvioPortalSICCFinDia(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#insertaArchivoEntradaPROL(java.util.Map)
	 */
	public void insertaArchivoEntradaPROL(Map archivoEntrada){
		procesoPEDDAO.insertaArchivoEntradaPROL(archivoEntrada);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getCadenaResultadoPROL(java.util.Map)
	 */
	public String getCadenaResultadoPROL(Map params){
		return procesoPEDDAO.getCadenaResultadoPROL(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#generaArchivoSalidaPROL(java.util.Map)
	 */
	public void generaArchivoSalidaPROL(Map criteria){
		procesoPEDDAO.generaArchivoSalidaPROL(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getPaisFecha(java.util.Map)
	 */
	public List getPaisFecha(Map criteria) {
		return procesoPEDDAO.getPaisFecha(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getTotalesPROL(java.util.Map)
	 */
	public List getTotalesPROL(Map criteria){
		return procesoPEDDAO.getTotalesPROL(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getDetallePedidoPROL(java.util.Map)
	 */
	public List getDetallePedidoPROL(Map criteria){
		return procesoPEDDAO.getDetallePedidoPROL(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getPedidoTemporalById(java.util.Map)
	 */
	public List getPedidoTemporalById(Map criteria) {
		return procesoPEDDAO.getPedidoTemporalById(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getOidSolicitudPROL(java.util.Map)
	 */
	public String getOidSolicitudPROL(Map criteria) {
		return procesoPEDDAO.getOidSolicitudPROL(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getPedidoDocumentoDigitadoPKByCriteria(java.util.Map)
	 */
	public List getPedidoDocumentoDigitadoPKByCriteria(Map criteria) {
		return procesoPEDDAO.getPedidoDocumentoDigitadoPKByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#executeActualizaIndicadorPROL(java.util.Map)
	 */
	public void executeActualizaIndicadorPROL(Map criteria){
		procesoPEDDAO.executeActualizaIndicadorPROL(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getIndicadorActividadPROL2()
	 */
	public String getIndicadorActividadPROL2(){
		return procesoPEDDAO.getIndicadorActividadPROL2();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getTiposCargaStock()
	 */
	public List getTiposCargaStock() {
		return procesoPEDDAO.getTiposCargaStock();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#insertarAuditoriaPROL(java.util.Map)
	 */
	public void insertarAuditoriaPROL(Map criteria) {
		procesoPEDDAO.insertarAuditoriaPROL(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#updateAuditoriaPROL(java.util.Map)
	 */
	public void updateAuditoriaPROL(Map criteria) {
		procesoPEDDAO.updateAuditoriaPROL(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getValidarProcesoPROL(java.util.Map)
	 */
	public String getValidarProcesoPROL(Map criteria) {
		String msg="";
		synchronized(this){			
			//VALIDACION DEL MISMO IDPROOCESO Y COLA
			Map params = new HashMap();
			params.put("oidProceso", criteria.get("oidProceso"));
			params.put("oidCola", criteria.get("oidCola"));
			
			int count = getProcesoPROL(params);
			if(count > 0){
				msg ="Por favor, espere!!... se esta enviando un proceso repetido id Proceso "+criteria.get("oidProceso")+ " id Cola "+criteria.get("oidCola");
				return msg;
			}
			
			//validacion de u  pedido cliente que se esta procesando
			params.remove("oidProceso");
			params.remove("oidCola");
			params.put("codigoCliente", criteria.get("codigoCliente"));			
			count = getProcesoPROL(params);
			if(count > 0){
				msg ="Por favor, espere!!... ya se esta procesando un pedido para la consultora "+criteria.get("codigoCliente");
				return msg;
			}
			
			params.remove("codigoCliente");
			count = getProcesoPROL(params);
			Integer numeroProcesWs =(Integer) criteria.get("numeroProcesWs");
			if(count <= numeroProcesWs){
				insertarAuditoriaPROL(criteria);
				BigDecimal oidAuditoriaPROL = (BigDecimal) criteria.get("oidAuditoriaPROL");
				criteria.put("oidAuditoriaPROL", oidAuditoriaPROL);
			}else{
				msg ="Por favor, espere!!... se esta procesando el mximo de solicitudes";
			}
									
		}
		return msg;
	}

	/**
	 * Retorna el numero de procesos aun no termiando via filtros 
	 * @param params
	 * @return
	 */
	public int getProcesoPROL(Map params) {
		return procesoPEDDAO.getProcesoPROL(params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getProcesoColaPROL(java.util.Map)
	 */
	public Map getProcesoColaPROL(Map criteria) {
		return procesoPEDDAO.getProcesoColaPROL(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#sendMailOcr(java.util.Map)
	 */
	public void sendMailOcr(Map params) {
		procesoPEDDAO.sendMailOcr(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#insertaArchivoEntradaNuevoPROL(biz.belcorp.ssicc.spusicc.pedido.web.ws.beans.PedidoCabeceraEntrada)
	 */
	public void insertaArchivoEntradaNuevoPROL(Map criteria) {
		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getProcesosNuevoPROL(java.util.Map)
	 */
	public List getProcesosNuevoPROL(Map criteria) {
		return procesoPEDDAO.getProcesosNuevoPROL(criteria);
	}	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#executeProceso(java.util.Map)
	 */
	public void executeProceso(Map criteria, Map queryParams) {
		procesoPEDDAO.executeProceso(criteria, queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#getSubacceso()
	 */
	public List getSubacceso() {
		return procesoPEDDAO.getSubacceso();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDService#eliminaRegistroRUV(java.util.Map)
	 */
	public void eliminaRegistroRUV(Map criteria) {
		procesoPEDDAO.eliminaRegistroRUV(criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#executeProcesoPEDPedidoInventariado(java.util.Map)
	 */
	public void executeProcesoPEDPedidoInventariado(Map criteria) {
		procesoPEDDAO.executeProcesoPEDPedidoInventariado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#executeProcesoPEDServicioGenerico(java.util.Map)
	 */
	public void executeProcesoPEDServicioGenerico(Map criteria) {
		procesoPEDDAO.executeProcesoPEDServicioGenerico(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getNextCorrelativoPedidoRechazadas()
	 */
	@Override
	public String getNextCorrelativoPedidoRechazadas() {
		return procesoPEDDAO.getNextCorrelativoPedidoRechazadas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#insertaCabeceraEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaCabeceraEntradaPedidoRechazado(Map criteria) {
		procesoPEDDAO.insertaCabeceraEntradaPedidoRechazado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#insertaDetalleEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaDetalleEntradaPedidoRechazado(Map criteria) {
		procesoPEDDAO.insertaDetalleEntradaPedidoRechazado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#insertaAlternativosEntradaPedidoRechazado(java.util.Map)
	 */
	@Override
	public void insertaAlternativosEntradaPedidoRechazado(Map criteria) {
		procesoPEDDAO.insertaAlternativosEntradaPedidoRechazado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#executeProcesoPEDPedidoRechazado(java.util.Map)
	 */
	@Override
	public void executeProcesoPEDPedidoRechazado(Map criteria) {
		procesoPEDDAO.executeProcesoPEDPedidoRechazado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getPedidosCabeceraRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosCabeceraRechazados(Map criteria) {
		return procesoPEDDAO.getPedidosCabeceraRechazados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getPedidosDetalleRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosDetalleRechazados(Map criteria) {
		return procesoPEDDAO.getPedidosDetalleRechazados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getPedidosAlternativosRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosAlternativosRechazados(Map criteria) {
		return procesoPEDDAO.getPedidosAlternativosRechazados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getPedidosErroresRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosErroresRechazados(Map criteria) {
		return procesoPEDDAO.getPedidosErroresRechazados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService#getPedidosMensajesRechazados(java.util.Map)
	 */
	@Override
	public List getPedidosMensajesRechazados(Map criteria) {
		return procesoPEDDAO.getPedidosMensajesRechazados(criteria);
	}
}