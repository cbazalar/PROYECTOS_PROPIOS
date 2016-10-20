/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionCDRDAO;


/**
 * @author peextdoliva
 *
 */
@Repository("spusicc.mantenimientoRECDigitacionCDRDAO")
public class MantenimientoRECDigitacionCDRDAOIbatis extends	BaseDAOiBatis implements MantenimientoRECDigitacionCDRDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertCabeceraReclamoDigitado(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera)
	 */
	public void insertCabeceraReclamoDigitado(ReclamoDigitadoCabecera recDigitCabec,Usuario usuario){
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertCabeceraReclamoDigitado", recDigitCabec);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertDetalleReclamoDigitado(biz.belcorp.ssicc.model.ReclamoDigitadoDetalle)
	 */
	public void insertDetalleReclamoDigitado(ReclamoDigitadoDetalle recDigitDetal,Usuario usuario){
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertDetalleReclamoDigitado", recDigitDetal);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getOperacionesReclamos(java.util.Map)
	 */
	public List getOperacionesReclamos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesReclamos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getOperacionesMotivos(java.util.Map)
	 */
	public List getMotivosReclamos(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMotivosReclamos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getConsultoraReclamoByCodigo(java.util.Map)
	 */
	public LabelValueCDR getConsultoraReclamoByCodigo(Map criteria){
		List l = new ArrayList(); 
		LabelValueCDR lb = new LabelValueCDR();
		l=getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getConsultoraReclamoByCodigo", criteria);		
		if(l.size()!=0)
			lb = (LabelValueCDR)l.get(0);		
		return lb;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getPeriodoReclamo(java.util.Map)
	 */
	public String getPeriodoReclamo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getPeriodoReclamo", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getCodigoOperacion(java.util.Map)
	 */
	public String getCodigoOperacion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoOperacion", criteria).get(0).toString();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#executeEnviarReclamosDigitados(java.util.Map)
	 */
	public void executeEnviarReclamosDigitados(Map params){
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeEnviarReclamosDigitados",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getExisteReclamo(java.util.Map)
	 */
	public String getExisteReclamo(Map criteria){		
		String existe = Constants.NO;
		try {
			existe = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getExisteReclamo", criteria).get(0).toString();
		} 
		catch (Exception e) {			
		}
		return existe;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaDetallesDigitados(java.util.Map)
	 */
	public List getListaDetallesDigitados(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaDetallesDigitados", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#deleteDetallesCDR(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera)
	 */
	public void deleteDetallesCDR(ReclamoDigitadoCabecera recDigitCabec){
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteDetallesCDR", recDigitCabec);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorExpressBoletaRecojo(java.util.Map)
	 */
	public String getIndicadorExpressBoletaRecojo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getIndicadorExpressBoletaRecojo", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaCodigosVentaCDR(java.util.Map)
	 */
	public List getListaCodigosVentaCDR(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaCodigosVentaCDR", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertReclamoDigitadoOnline(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera, java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertReclamoDigitadoOnline(ReclamoDigitadoCabecera reclamoDigitCabec, List detallesList,Map params) {
		
		String numeroLote = (String)params.get("numLoteSTO");
		
		reclamoDigitCabec.setNumeroLote(numeroLote);
		//Inserta la cabecera
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertCabeceraReclamoDigitadoOnline", reclamoDigitCabec);
		
		for (int i = 0; i < detallesList.size(); i++) {
			ReclamoDigitadoDetalle recDigitDetal = (ReclamoDigitadoDetalle)detallesList.get(i);		
			
			recDigitDetal.setNumeroLote(numeroLote);
			//Inserto los detalles
			if((!recDigitDetal.getCodigoVentaDevuelve().equals("")) || (!recDigitDetal.getCodigoVentaDesea().equals(""))){
				recDigitDetal.setNumeroLinea(i+1);
				getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertDetalleReclamoDigitadoOnline", recDigitDetal);
			}	
		}
		
		getSqlMapClientTemplate().insert("sisicc.InterfazOCRSQL.executeConsolidadoPostVenta", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorCentroServicio(java.util.Map)
	 */
	public List getIndicadorCentroServicio(Map criteria){
		List l = new ArrayList();
		l = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getIndicadorCentroServicio", criteria);
		if (l.size() == 0){
			Base base = new Base();
			base.setCodigo(Constants.NUMERO_CERO);
			base.setDescripcion("");
			l.add(base);
		}			
		return l;    	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getParametrosOperacionesReclamos()
	 */
	public List getParametrosOperacionesReclamos(){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getParametrosOperacionesReclamos", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getMontoPedidoDevolucion(java.util.Map)
	 */
	public Map getMontoPedidoDevolucion(Map criteria){
		getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMontoPedidoDevolucion", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getPorcentajeMontoDevolucion(java.util.Map)
	 */
	public String getPorcentajeMontoDevolucion(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getPorcentajeMontoDevolucion", criteria).toString();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getMontoPedidoFaltantes(java.util.Map)
	 */
	public Map getMontoPedidoFaltantes(Map criteria){
		getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMontoPedidoFaltantes", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getPorcentajeMontoFaltantes(java.util.Map)
	 */
	public String getPorcentajeMontoFaltantes(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getPorcentajeMontoFaltantes", criteria).toString();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getMontoPedidoCambios(java.util.Map)
	 */
	public Map getMontoPedidoCambios(Map criteria){
		getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMontoPedidoCambios", criteria);
		return criteria;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getPorcentajeMontoCambios(java.util.Map)
	 */
	public String getPorcentajeMontoCambios(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getPorcentajeMontoCambios", criteria).toString();		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorPedidoChequeado()
	 */
	public String getIndicadorPedidoChequeado(){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndicadorPedidoChequeado", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getCodigoResultadoChequeo(java.util.Map)
	 */
	public String getCodigoResultadoChequeo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getCodigoResultadoChequeo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorCDRRechazo(java.util.Map)
	 */
	public String getIndicadorCDRRechazo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndicadorCDRRechazo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getUbicacionConsultora(java.util.Map)
	 */
	public Map getUbicacionConsultora(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getUbicacionConsultora", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getSaldoProducto(java.lang.String, java.lang.String)
	 */
	public Integer getSaldoProducto(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getSaldoProducto", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorValorStock(java.util.Map)
	 */
	public String getIndicadorValorStock(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndicadorValorStock", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getPeriodoCDR(java.util.Map)
	 */
	public String getPeriodoCDR(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getPeriodoCDR", criteria).get(0).toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getDescripcionPeriodo(java.util.Map)
	 */
	public String getDescripcionPeriodo(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDescripcionPeriodo", criteria).get(0).toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getCodigoOperacionCorrecto(java.util.Map)
	 */
	public void getCodigoOperacionCorrecto(Map criteria) {
		 getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.validTipoOperacionCantidad", criteria);
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValExcepProduGanador(java.util.Map)
	 */
	public String getValExcepProduGanador(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.valiExcepProduGan", criteria);
		return (String) criteria.get("mensaje");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValiAceptaCDR(java.util.Map)
	 */
	public String getValiAceptaCDR(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.valiAceptaCDR", criteria);
		return (String) criteria.get("mensaje");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getVerificaOperacion(java.util.Map)
	 */
	public String getVerificaOperacion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getVerificaOperacion", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValUnidadReclamo(java.util.Map)
	 */
	public String getValUnidadReclamo(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.valiUnidadReclamo", criteria);
		return (String) criteria.get("mensaje");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValExcepProduDesea(java.util.Map)
	 */
	public String getValExcepProduDesea(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getValExcepProduDesea", criteria);
		return (String) criteria.get("mensaje");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValUnidadDesea(java.util.Map)
	 */
	public String getValUnidadDesea(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getValUnidadDesea", criteria);
		return (String) criteria.get("mensaje");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getCodigoMotivoRechazo()
	 */
	public List getCodigoMotivoRechazo() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListCodMotivoRechazo");
		 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValorParam(java.util.Map)
	 */
	public String getValorParam(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValorParam", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getNuevoNumeroPedido(java.util.Map)
	 */
	public String getNuevoNumeroPedido(Map criteria) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNuevoNumeroPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#validaPedido(java.lang.String)
	 */
	public void getMensajeValidaPedido(Map criteria) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMensajeValidaPedido", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#ofertaDevolucion(java.util.Map)
	 */
	public void ofertaDevolucion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.ofertaDevolucion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#mensajeError()
	 */
	public String mensajeError() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMensajeError");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#mensajeOferta()
	 */
	public String mensajeOferta() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMensajeOferta");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getDetalleMensaje()
	 */
	public List getDetalleMensaje() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDetalleMensaje");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getGttOferta()
	 */
	public List getGttOferta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getGttOferta",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#updateGttOferta(java.util.Map)
	 */
	public void updateGttOferta(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateGttOferta", criteria);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionInicialRecepcionCDR(java.util.Map)
	 */
	public Map getValidacionInicialRecepcionCDR(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidacionInicialRecepcionCDR", criteria);
        }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDR(java.util.Map)
	 */
	public Map getValidacionRecepcionCDR(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidacionRecepcionCDR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaRecepcionCDRDetalle(java.util.Map)
	 */
	public List getListaRecepcionCDRDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaRecepcionCDRDetalle", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDRAnuladoDetalle(java.util.Map)
	 */
	public Integer getValidacionRecepcionCDRAnuladoDetalle(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidacionRecepcionCDRAnuladoDetalle", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDRDetalle(java.util.Map)
	 */
	public Integer getValidacionRecepcionCDRDetalle(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidacionRecepcionCDRDetalle", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaRecepcionCDRReclamos(java.util.Map)
	 */
	public List getListaRecepcionCDRReclamos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getListaRecepcionCDRReclamos", criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getOidDatosCliente(java.util.Map)
	 */
	public Map getOidDatosCliente(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOidDatosCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertCabeceraRecepcionCDR(java.util.Map)
	 */
	public void insertCabeceraRecepcionCDR(Map criteria) {
    	        getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertCabeceraRecepcionCDR", criteria);	
        }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#updateCabeceraRecepcionCDR(java.util.Map)
	 */
	public void updateCabeceraRecepcionCDR(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateCabeceraRecepcionCDR", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#deleteDetalleRecepcionCDR(java.util.Map)
	 */
	public void deleteDetalleRecepcionCDR(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteDetalleRecepcionCDR", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertDetalleRecepcionCDR(java.util.Map)
	 */
	public void insertDetalleRecepcionCDR(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertDetalleRecepcionCDR", criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getDevuelveSecuencialDetalleRecepcionCDR(java.util.Map)
	 */
	public Long getDevuelveSecuencialDetalleRecepcionCDR(Map criteria) {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getDevuelveSecuencialDetalleRecepcionCDR", criteria);
	}
	
	public ProductoAgregacion getDevuelveProductoRecepcionCDR(Map criteria) {
		return (ProductoAgregacion) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getDevuelveProductoRecepcionCDR", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getIndicadorPGRBR()
	 */	
	public String getIndicadorPGRBR() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndicadorPGRBR", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionCDRDAO#getValExcepMotivo(java.util.Map)
	 */
	@Override
	public String getValExcepMotivo(Map criteria) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.valExcepMotivo", criteria);
		return (String) criteria.get("mensaje");
	}
}
 