package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseSubReporteAbstractService;
import biz.belcorp.ssicc.service.spusicc.mae.ReporteMAENovedadesZonaService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;


/**
 * @author Sigcomt
 *
 */
@Service("spusicc.reporteMAENovedadesZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteMAENovedadesZonaServiceImpl extends BaseSubReporteAbstractService implements ReporteMAENovedadesZonaService {

	@Resource(name="mae.mailReporteMAENovedadesZona")
	private BaseMailService mailService;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
	private MantenimientoOCRPedidoControlFacturacionService  mantenimientoOCRPedidoControlFacturacionService;
    
  	protected String getReporteFileName() {
		return "reporteMaestroHorizontalMAENovedadesZona";
	}
	
	protected String getSubReporteFileName() {
		return "reporteMAENovedadesZonaPDF";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteEDUGenerarResumenProgramacion.error.nivelGenerarPlanilla";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected void prepareParameterMap(Map params) throws Exception{
		super.prepareParameterMap(params);
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		Pais pais = (Pais)params.get("pais");
		String titulo = messageSource.getMessage("reporteMAENovedadesZonaForm.titulo",null,getLocale(usuario));
		log.debug("titulo");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", "0");
		criteria.put("indicadorActiva", "1");
		
		List lista = mantenimientoOCRPedidoControlFacturacionService.getCampanhasActivasByCriteria(criteria);
		if (lista.size() == 1) {
			params.put("campania", String.valueOf(lista.get(0)));
		}
		
		params.put("NroReporte", "");
		params.put("titulo", titulo);
		params.put("formatoExportacion","VPDF");		
		log.debug("this.isVisualizarReporte() "+this.isVisualizarReporte());
       
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String codigoZona = (String)parameterMap.get("codigoZona");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		nombreArchivoReporte =getPrefijoArchivo()+ "_"+ codigoRegion+ codigoZona +
					sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}

	/**
	 * @return the mailService
	 */
	public BaseMailService getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	public void setMailService(BaseMailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * @return the mantenimientoOCRPedidoControlFacturacionService
	 */
	public MantenimientoOCRPedidoControlFacturacionService getMantenimientoOCRPedidoControlFacturacionService() {
		return mantenimientoOCRPedidoControlFacturacionService;
	}
	
	/**
	 * @param mantenimientoOCRPedidoControlFacturacionService the mantenimientoOCRPedidoControlFacturacionService to set
	 */
	public void setMantenimientoOCRPedidoControlFacturacionService(
			MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService) {
		this.mantenimientoOCRPedidoControlFacturacionService = mantenimientoOCRPedidoControlFacturacionService;
	}

	
}
