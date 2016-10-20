package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOCentroAcopioFacturadoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSTOCentroAcopioFacturadoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	private List listaParametros;
	
	
	public List getListaParametros() {
		return listaParametros;
	}
	
	public void setListaParametros(List listaParametros) {
		this.listaParametros = listaParametros;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOCentroAcopioFacturadoForm form = new ReporteSTOCentroAcopioFacturadoForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
		
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(this.getNroReporteProcesando()%2>0) {
			return "reporteSTOCentroAcopioFacturadoPDF";
		} else {
			return "reporteSTOCentroAcopioFacturadoDetallePDF";
		}	
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteMailXLS = false;
		this.mostrarReporteMailPDF = false;
		this.mostrarReportePDF = false;
		this.mostrarListaReporteLog = true;
		

		ReporteSTOCentroAcopioFacturadoForm f = (ReporteSTOCentroAcopioFacturadoForm) formReporte;
		f.setEnvioEmail(true);
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga el periodo actual
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOCentroAcopioFacturadoForm f = (ReporteSTOCentroAcopioFacturadoForm) formReporte;		
		params.put("NroReporte", "");
		params.put("formatoExportacion","PDF");			
		f.setFormatoExportacion("PDF");
		
		params.put("SUBREPORT_DIR1", "subReporteSTOCentroAcopioFueraPedidoPDF" + JASPER_EXTENSION);
		
		if(this.getNroReporteProcesando()%2>0) 
			params.put("titulo", getReportResourceMessage("reporteSTOCentroAcopioFacturado.titulo"));
		else {
			params.put("titulo", getReportResourceMessage("reporteSTOCentroAcopioFacturado.titulo.detalle"));
		}	
		
		if (!this.isVisualizarReporte()) {			
			Map map = new HashMap();
			map = (Map)listaParametros.get(this.getNroReporteProcesando() - 1);
			log.debug("map "+map);
			params.put("codigoCiaTransporte" , map.get("codCiaTransporte"));			
			params.put("codigoCentroAcopio"  , map.get("codCentroAcopio"));
			params.put("ciaTransporte"    	 , map.get("ciaTransporte"));
			params.put("nomCentroAcopio"  	 , map.get("nomCentroAcopio"));
			params.put("emailCentroAcopio"	 , map.get("emailCentroAcopio"));
		}
		f.setEnvioEmail(true);	
		return params;
	}

	@Override
	protected int getNroReportesAGenerar() {
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Map criteria = new HashMap();
		listaParametros = procesoSTOEjecucionValidacionesService.getCentrosDeAcopioFacturado(criteria);
		
		List listaParametrosAux = procesoSTOEjecucionValidacionesService.getCentrosDeAcopioFacturado(criteria);
		int posicion = 1;
		for(int i=0; i<listaParametrosAux.size(); i++) {
			Map mapCentroAcopio = (Map)listaParametrosAux.get(i);
			listaParametros.add(posicion, mapCentroAcopio);
			posicion = posicion + 2;
		}
				
		return listaParametros.size();
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;		

		Map map = new HashMap();
		map = (Map)listaParametros.get(this.getNroReporteProcesando() - 1);
		
		String codCentroAcopio = MapUtils.getString(map, "codCentroAcopio", ""); 
		String codCiaTransporte = MapUtils.getString(map, "codCiaTransporte", ""); 
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		if(this.getNroReporteProcesando()%2>0) {
			nombreArchivoReporte = this.getPrefijoArchivo() +  "_(CP)_" +
			   						StringUtils.trim(codCiaTransporte) + "_" +
			   						StringUtils.trim(codCentroAcopio) + "_" +
						           sdf.format(new Date(System.currentTimeMillis()));
		} else {
			nombreArchivoReporte = this.getPrefijoArchivo() +  "_(DP)_" +
						StringUtils.trim(codCiaTransporte) + "_" +
						StringUtils.trim(codCentroAcopio) + "_" +
		           sdf.format(new Date(System.currentTimeMillis()));
		}	

		return nombreArchivoReporte;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#beforeGrabarReporte()
	 */
	@Override
	protected void beforeGrabarReporte() {
		// TODO Auto-generated method stub
		super.beforeGrabarReporte();
		
		ReporteSTOCentroAcopioFacturadoForm f = (ReporteSTOCentroAcopioFacturadoForm) formReporte;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map params = new HashMap();
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaFacturacion", f.getFechaFacturacion());
		
		reporteService.executeCargaReporteCentroAcopioFacturado(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOSolucionesCentroAcopioAutomaticoService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "sto.mailReporteSTOCentroAcopioFacturadoService";
	}			
}
