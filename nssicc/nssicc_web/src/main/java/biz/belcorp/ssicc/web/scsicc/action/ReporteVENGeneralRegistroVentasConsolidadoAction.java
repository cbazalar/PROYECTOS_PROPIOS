package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENRegistroVentasConsolidadoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
public class ReporteVENGeneralRegistroVentasConsolidadoAction extends
BaseReporteAbstractAction{

	private static final long serialVersionUID = 8495136293425631895L;
	
	
	private String formatoReporte;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENRegistroVentasConsolidadoForm reporteForm = new ReporteVENRegistroVentasConsolidadoForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {	
		
		if ("XLS".equals(formatoReporte))
			return  "reporteVENRegistroVentasConsolidadoEsquemasXLS";
			
		else 
			return "reporteMaestroHorizontal";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteVENRegistroVentasConsolidadoEsquemasPDF";
	}

	@Override
	protected void setViewAtributes() throws Exception {
				
		this.mostrarReporteXLS = true;	
		ReporteVENRegistroVentasConsolidadoForm f = (ReporteVENRegistroVentasConsolidadoForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		f.setFechaDesde(fecha);
		f.setFechaHasta(fecha);
		f.setFechaInicioD(new Date(System.currentTimeMillis()));
		f.setFechaFinD(new Date(System.currentTimeMillis()));

	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENRegistroVentasConsolidadoForm f = (ReporteVENRegistroVentasConsolidadoForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		//Convertir fechas de date a string
		String ndesde=DateUtil.convertDateToString(f.getFechaInicioD());
		String nhasta=DateUtil.convertDateToString(f.getFechaFinD());
		
		f.setFechaDesde(ndesde);
		f.setFechaHasta(nhasta);


		params.put("NroReporte", "");
		params.put("condicionUsuario", "NO");
		params.put("condicionFechaHora", "NO");
		params.put("superiorIzquierda", getReportResourceMessage("reporte.maestro.cetco.ruc"));
		params.put("titulo", getReportResourceMessage("	.titulo")
				+ "\n("
				+ getReportResourceMessage("reporteVENGeneralRegistroVentasConsolidadoForm.desde")
				+ ndesde
				+ " "
				+ getReportResourceMessage("reporteVENGeneralRegistroVentasConsolidadoForm.al")
				+ " " + nhasta + ")");
		params.put("fechaDesde",ndesde);
		params.put("fechaHasta",nhasta);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
					
		params.put("codigoPaisLbel", pais.getCodigo().substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);		
		
		return params;
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteVENGeneralRegistroVentasConsolidadoService";
	}
	
	public String setValidarReporte() {
		ReporteVENRegistroVentasConsolidadoForm form = (ReporteVENRegistroVentasConsolidadoForm) this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}




	
	
}
