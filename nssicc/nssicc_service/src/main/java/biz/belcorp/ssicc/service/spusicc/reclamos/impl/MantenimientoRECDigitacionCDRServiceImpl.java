package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionCDRDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;

@Service("spusicc.mantenimientoRECDigitacionCDRService")
public class MantenimientoRECDigitacionCDRServiceImpl extends BaseService implements MantenimientoRECDigitacionCDRService {
	
	@Resource(name="spusicc.mantenimientoRECDigitacionCDRDAO")
	MantenimientoRECDigitacionCDRDAO mantenimientoRECDigitacionCDRDAO;
	
	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#insertReclamoDigitado(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera, java.util.List)
	 */
	public void insertReclamoDigitado(ReclamoDigitadoCabecera recDigitCabec, List detallesList, Usuario usuario, int indicadorModificar){
		// Evaluo si es un nuevo registro o es un modificar
		if(indicadorModificar == 0){
			//Inserta la cabecera
			mantenimientoRECDigitacionCDRDAO.insertCabeceraReclamoDigitado(recDigitCabec, usuario);
		}
		else{
			//Elimino todos los detalles para volver a grabarlos luego
			mantenimientoRECDigitacionCDRDAO.deleteDetallesCDR(recDigitCabec);
		}
		//Recorro los detalles
		for (int i = 0; i < detallesList.size(); i++) {
			ReclamoDigitadoDetalle recDigitDetal = new ReclamoDigitadoDetalle();
			recDigitDetal = (ReclamoDigitadoDetalle)detallesList.get(i);			
			//Inserto los detalles
			if((!recDigitDetal.getCodigoVentaDevuelve().equals(""))||(!recDigitDetal.getCodigoVentaDesea().equals(""))){
				recDigitDetal.setNumeroLinea(i+1);
				mantenimientoRECDigitacionCDRDAO.insertDetalleReclamoDigitado(recDigitDetal,usuario);
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getOperacionesReclamos(java.util.Map)
	 */
	public List getOperacionesReclamos(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getOperacionesReclamos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getOperacionesMotivos(java.util.Map)
	 */
	public List getMotivosReclamos(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getMotivosReclamos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getCodigoOperacion(java.util.Map)
	 */
	public String getCodigoOperacion(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getCodigoOperacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#executeEnviarReclamosDigitados(java.util.Map)
	 */
	public void executeEnviarReclamosDigitados(Map params){
		mantenimientoRECDigitacionCDRDAO.executeEnviarReclamosDigitados(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getListaDetallesDigitados(java.util.Map)
	 */
	public List getListaDetallesDigitados(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getListaDetallesDigitados(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getIndicadorExpressBoletaRecojo(java.util.Map)
	 */
	public String getIndicadorExpressBoletaRecojo(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getIndicadorExpressBoletaRecojo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#insertReclamoDigitadoOnline(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera, java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertReclamoDigitadoOnline(ReclamoDigitadoCabecera reclamoDigitCabec, List detallesList,Map params) {
		
		mantenimientoRECDigitacionCDRDAO.insertReclamoDigitadoOnline(reclamoDigitCabec,detallesList,params);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getParametrosOperacionesReclamos()
	 */
	public List getParametrosOperacionesReclamos(){
		return mantenimientoRECDigitacionCDRDAO.getParametrosOperacionesReclamos();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getIndicadorPedidoChequeado()
	 */
	public String getIndicadorPedidoChequeado(){
		return mantenimientoRECDigitacionCDRDAO.getIndicadorPedidoChequeado();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getConsultoraReclamoByCodigo(java.util.Map)
	 */
	public LabelValueCDR getConsultoraReclamoByCodigo(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getConsultoraReclamoByCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getIndicadorCDRRechazo(java.util.Map)
	 */
	public String getIndicadorCDRRechazo(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getIndicadorCDRRechazo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getCodigoMotivoRechazo()
	 */
	public List getCodigoMotivoRechazo() {
		// TODO Auto-generated method stub
		return mantenimientoRECDigitacionCDRDAO.getCodigoMotivoRechazo() ;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getDetalleMensaje()
	 */
	public List getDetalleMensaje() {
		return mantenimientoRECDigitacionCDRDAO.getDetalleMensaje() ;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getGttOferta()
	 */
	public List getGttOferta(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getGttOferta(criteria) ;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#updateGttOferta(java.util.Map)
	 */
	public void updateGttOferta(Map criteria) {
		mantenimientoRECDigitacionCDRDAO.updateGttOferta(criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getValorParam(java.util.Map)
	 */
	public String getValorParam(Map criteria){
		return mantenimientoRECDigitacionCDRDAO.getValorParam(criteria);
	}
	
	public Map getValidacionInicialRecepcionCDR(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getValidacionInicialRecepcionCDR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDR(java.util.Map)
	 */
	public Map getValidacionRecepcionCDR(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getValidacionRecepcionCDR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaRecepcionCDRDetalle(java.util.Map)
	 */
	public List getListaRecepcionCDRDetalle(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getListaRecepcionCDRDetalle(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDRAnuladoDetalle(java.util.Map)
	 */
	public Integer getValidacionRecepcionCDRAnuladoDetalle(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getValidacionRecepcionCDRAnuladoDetalle(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getValidacionRecepcionCDRDetalle(java.util.Map)
	 */
	public Integer getValidacionRecepcionCDRDetalle(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getValidacionRecepcionCDRDetalle(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#getListaRecepcionCDRReclamos(java.util.Map)
	 */
	public List getListaRecepcionCDRReclamos(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getListaRecepcionCDRReclamos(criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getOidDatosCliente(java.util.Map)
	 */
	public Map getOidDatosCliente(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getOidDatosCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#getDevuelveSecuencialDetalleRecepcionCDR(java.util.Map)
	 */
	public Long getDevuelveSecuencialDetalleRecepcionCDR(Map criteria) {
		return mantenimientoRECDigitacionCDRDAO.getDevuelveSecuencialDetalleRecepcionCDR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionCDRService#executeInsertUpdateRecepcionCDR(java.util.Map)
	 */
	public void executeInsertUpdateRecepcionCDR(Map criteria) throws Exception {
		String contrcd = (String) criteria.get("contrcd");
		String oidCabeReclamo = (String) criteria.get("oidCabeReclamo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		
		if (contrcd.equals("0")) {
			mantenimientoRECDigitacionCDRDAO.insertCabeceraRecepcionCDR(criteria);
		}
		else  {
			mantenimientoRECDigitacionCDRDAO.updateCabeceraRecepcionCDR(criteria);
			mantenimientoRECDigitacionCDRDAO.deleteDetalleRecepcionCDR(criteria);
		}
		String[] listaCodigoCUV = (String[]) criteria.get("listaCodigoCUV");
		Long[] listaOidProducto = (Long[]) criteria.get("listaOidProducto");
		String[] listaCodigoSAP = (String[]) criteria.get("listaCodigoSAP");
		Long[] listaUnidRecibidas = (Long[]) criteria.get("listaUnidRecibidas");
		Long[] listaUnidDisponible = (Long[]) criteria.get("listaUnidDisponible");
		Long[] listaUnidDestruccion = (Long[]) criteria.get("listaUnidDestruccion");
		Long[] listaUnidAprovec = (Long[]) criteria.get("listaUnidAprovec");
		String[] listaIndicadorExistencia = (String[]) criteria.get("listaIndicadorExistencia");
		Long[] listaOidOperReclamo = (Long[]) criteria.get("listaOidOperReclamo");
		Long[] listaOidLineaOperReclamo = (Long[]) criteria.get("listaOidLineaOperReclamo");
		String[] listaIndicadorBorrado = (String[]) criteria.get("listaIndicadorBorrado");
		String mensajeError = null;
		
		for(int i=0; i<listaCodigoCUV.length ; i++) {
			String codigoCUV = listaCodigoCUV[i];
			Long oidProducto = listaOidProducto[i];
			String codigoSAP = listaCodigoSAP[i];
			String indicadorBorrado = listaIndicadorBorrado[i];
			Long unidRecibidas = listaUnidRecibidas[i];
			Long unidDisponible = listaUnidDisponible[i];
			Long unidDestruccion = listaUnidDestruccion[i];
			Long unidAprovec = listaUnidAprovec[i];
			String indicadorExistencia = listaIndicadorExistencia[i];
			Long oidOperReclamo = listaOidOperReclamo[i];
			Long oidLineaOperReclamo = listaOidLineaOperReclamo[i];
			
			if (indicadorExistencia.equalsIgnoreCase("0")) {
				long sumaCantidad = unidDisponible.longValue() + unidDestruccion.longValue() + unidAprovec.longValue();
				if (sumaCantidad <= 0) {
					mensajeError = "Registro " + (i+1) + ": " + messageSource.getMessage("mantenimientoRECRecepcionCDRForm.error.sumaCantidades", null, getLocale(usuario));
					throw new Exception(mensajeError);
				}
			}
			
			if (indicadorBorrado.equalsIgnoreCase(Constants.NO)) {
				Map criteriaDetalle = new HashMap();
				criteriaDetalle.put("codigoCUV", codigoCUV);
				criteriaDetalle.put("oidProducto", oidProducto);
				criteriaDetalle.put("codigoSAP", codigoSAP);
				criteriaDetalle.put("unidRecibidas", unidRecibidas);
				criteriaDetalle.put("unidDisponible", unidDisponible);
				criteriaDetalle.put("unidDestruccion", unidDestruccion);
				criteriaDetalle.put("unidAprovec", unidAprovec);
				criteriaDetalle.put("indicadorExistencia", indicadorExistencia);
				criteriaDetalle.put("oidCabeReclamo", oidCabeReclamo);
				criteriaDetalle.put("oidOperReclamo", oidOperReclamo);
				criteriaDetalle.put("oidLineaOperReclamo", oidLineaOperReclamo);
				
				Long oidCorrelativo = this.getDevuelveSecuencialDetalleRecepcionCDR(criteriaDetalle);
				criteriaDetalle.put("oidCorrelativo", oidCorrelativo);
				
				mantenimientoRECDigitacionCDRDAO.insertDetalleRecepcionCDR(criteriaDetalle);
			}
		}
	}
	
	
	
	public String getIndicadorPGRBR() {
		return mantenimientoRECDigitacionCDRDAO.getIndicadorPGRBR();
	}

	public String getSTOParametroGeneralOCR(Map criteria) {
		return interfazOCRDAO.getSTOParametroGeneralOCR(criteria);
	}

}
