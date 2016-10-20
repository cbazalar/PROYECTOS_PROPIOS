package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPedidosGP1;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;


/**
 *
 *  
 * <p>
 * <a href="ProcesoSTOServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@Service("spusicc.procesoSTOService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOServiceImpl extends BaseService implements ProcesoSTOService {

	@Resource(name="spusicc.procesoSTODAO")
	private ProcesoSTODAO procesoSTODAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getTipoDocumentoDigitado(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado(TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK) {
		return procesoSTODAO.getTipoDocumentoDigitado(tipoDocumentoDigitadoPK);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getDocumentoDigitadoPKByLote(java.util.Map)
	 */
	public List getDocumentoDigitadoPKByLote(Map params) {
		return procesoSTODAO.getDocumentoDigitadoPKByLote(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#updateDocumentoForValidate(java.util.Map)
	 */
	public void updateDocumentoForValidate(Map queryParams) {
		procesoSTODAO.updateDocumentoForValidate(queryParams);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getIndicadorModificacionCodigoCliente(java.util.Map)
	 */
	public String getIndicadorModificacionCodigoCliente(Map criteria){
		return procesoSTODAO.getIndicadorModificacionCodigoCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getCargaEjecucionByDocumento(java.util.Map)
	 */
	public List getCargaEjecucionByDocumento(Map criteria){
		return procesoSTODAO.getCargaEjecucionByDocumento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaPedidoPostVenta(java.util.Map)
	 */
	public List getConsultaPedidoPostVenta(Map criteria) {
		return procesoSTODAO.getConsultaPedidoPostVenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getSecuenciaConsultaPedidos()
	 */
	public String getSecuenciaConsultaDocumento() {
		return procesoSTODAO.getSecuenciaConsultaDocumento();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaPedidosList(java.util.Map)
	 */
	public List getConsultaPedidosList(Map criteria) {
 
		return procesoSTODAO.getConsultaPedidosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#executeEliminarPedido(java.util.Map)
	 */
	public void executeEliminarPedido(Map criteria) {
		procesoSTODAO.executeEliminarPedido(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#executeBloquearEliminarPedido(java.util.Map)
	 */
	public void executeBloquearEliminarDocumento(Map criteria) {
		procesoSTODAO.executeBloquearEliminarDocumento(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#updateEliminarPedido(java.util.Map)
	 */
	public void updateEliminarPedido(Map criteria) {
		procesoSTODAO.updateEliminarPedido(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaPedidosGP1List(java.util.Map)
	 */
	public List getConsultaPedidosGP1List(Map criteria) {
 
		return procesoSTODAO.getConsultaPedidosGP1List(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#reversarPedidosGP1(java.util.List)
	 */
	public void reversarPedidosGP1(List lista){
		Map map = new HashMap();
		Iterator it = lista.iterator();
		//String [] listaOid = new String[lista.size()];
		int i=0;
		/*while(it.hasNext()) {
			ConsultaPedidosGP1 consulta = (ConsultaPedidosGP1)it.next();
			//String codigoPeriodo = consulta.getCodigoPeriodo();
			//String codigoCliente = consulta.getCodigoCliente();
			String oidCabecera = consulta.getOidSolicabec();
			//map.put("codigoPeriodo", codigoPeriodo);
			//map.put("codigoCliente", codigoCliente);
			listaOid[i++] = oidCabecera;
			//procesoSTODAO.reversarPedidosGP1(map);
		}
		
		map.put("listaOid",listaOid);
		if(listaOid.length >0 )
		  procesoSTODAO.reversarPedidosGP1(map);*/
		while(it.hasNext()) {
			ConsultaPedidosGP1 consulta = (ConsultaPedidosGP1)it.next();
			String oidCabecera = consulta.getOidSolicabec();
			map.put("numPedido", oidCabecera);
			procesoSTODAO.reversarPedidosGP1(map);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getindicadorDesmarcarAgrupacion(java.util.Map)
	 */
	public String getindicadorDesmarcarAgrupacion(Map criteria){
		return procesoSTODAO.getindicadorDesmarcarAgrupacion(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#inicializeRegistrosProcesados(java.util.Map)
	 */
	public void inicializeRegistrosProcesados(Map criteria) {
		procesoSTODAO.inicializeRegistrosProcesados(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaPolizasFamiliaSeguraList(java.util.Map)
	 */
	public List getConsultaPolizasFamiliaSeguraList(Map criteria) {
		return procesoSTODAO.getConsultaPolizasFamiliaSeguraList(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#updateLoteSTO(biz.belcorp.ssicc.spusicc.sto.model.TipoDocumentoDigitadoPK)
	 */
	public String updateLoteSTO(TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK) {
		return procesoSTODAO.updateLoteSTO(tipoDocumentoDigitadoPK);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaPedidosOnlineList(java.util.Map)
	 */
	public List getConsultaPedidosOnlineList(Map criteria) {
		return procesoSTODAO.getConsultaPedidosOnlineList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#updateDocumentoForProcess(java.util.Map)
	 */
	public void updateDocumentoForProcess(Map queryParams) {
		procesoSTODAO.updateDocumentoForProcess(queryParams);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#updateDocumentoForDelete(java.util.List)
	 */
	public void updateDocumentoForDelete(List stoList,Map queryParams) {
		procesoSTODAO.updateDocumentoForDelete(stoList, queryParams);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getSTOListByPedidoList(java.util.List, java.util.Map)
	 */
	public List getSTOListByPedidoList(List pedidoList, Map queryParams) {		
		return procesoSTODAO.getSTOListByPedidoList(pedidoList,queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getSTOListByPedidoList(java.util.List, java.util.Map)
	 */
	public List getSTOListByPolizaList(List polizaList, Map queryParams) {		
		return procesoSTODAO.getSTOListByPolizaList(polizaList,queryParams);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getValidarCuponPeriodoCliente(java.util.Map)
	 */
	public Integer getValidarCuponPeriodoCliente(Map params) {
		return procesoSTODAO.getValidarCuponPeriodoCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getValidarDeudaCliente(java.util.Map)
	 */
	public Integer getValidarDeudaCliente(Map params) {
		return procesoSTODAO.getValidarDeudaCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaDetallePedidoGP1_GP2(java.util.Map)
	 */
	public List getConsultaDetallePedidoGP1_GP2(Map criteria) {
		return procesoSTODAO.getConsultaDetallePedidoGP1_GP2(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getConsultaDetallePedidoGP3_GP4_GP5(java.util.Map)
	 */
	public List getConsultaDetallePedidoGP3_GP4_GP5(Map criteria) {
		return procesoSTODAO.getConsultaDetallePedidoGP3_GP4_GP5(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getRegionZonaTemporal(java.util.Map)
	 */
	public String getRegionZonaTemporal(Map criteria) {
		return procesoSTODAO.getRegionZonaTemporal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getTipoDocumentoByValidacion(java.util.Map)
	 */
	public String getTipoDocumentoByValidacion(Map criteria) {		
		return procesoSTODAO.getTipoDocumentoByValidacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#executeCargaSeguimientoPedidos(java.util.Map)
	 */
	public void executeCargaSeguimientoPedidos(Map params) {
		this.procesoSTODAO.executeCargaSeguimientoPedidos(params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getFechaEntregaConfirmada(java.util.Map)
	 */
	public String getFechaEntregaConfirmada(Map criteria) {
		return this.procesoSTODAO.getFechaEntregaConfirmada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getPedidoSeguidoSTOList(java.util.Map)
	 */
	public List getPedidoSeguidoSTOList(Map criteria) {	
		return this.procesoSTODAO.getPedidoSeguidoSTOList(criteria);
	}	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOService#getDocumentoDigitadoPKByProceso(java.lang.String, java.lang.String)
	 */
	public List getDocumentoDigitadoPKByProceso(String codTipoDocu, String numProc){
		Map queryParams = new HashMap();
		queryParams.put("codTipoDocu", codTipoDocu);
		queryParams.put("numProc", numProc);
		return this.procesoSTODAO.getDocumentoDigitadoPKByProceso(queryParams);
	}
}