package biz.belcorp.ssicc.web.scsicc.action;



import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSAPFiDescuentoVolumenForm;



@ManagedBean
@SessionScoped
public class ReporteSAPFiDescuentoVolumenAction extends BaseReporteAbstractAction{
	
	
	private static final long serialVersionUID = 3437152646168135396L;
	
	
	private String formatoReporte;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSAPFiDescuentoVolumenForm reporteForm = new ReporteSAPFiDescuentoVolumenForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		
		ReporteSAPFiDescuentoVolumenForm f = (ReporteSAPFiDescuentoVolumenForm) this.formReporte;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		        
		f.setCodigoTipoReporte(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);
        f.setFechaInicio("");
        f.setFechaFin("");
        
        
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ( "XLS".equals(formatoReporte))	
			return  "reporteSAPFiDescuentoVolumenXLS";
		else 
			return "reporteMaestroVertical";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSAPFiDescuentoVolumenPDF";
	}
	
	
	public String setValidarReporte() {
		ReporteSAPFiDescuentoVolumenForm form = (ReporteSAPFiDescuentoVolumenForm) this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}
		
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteSAPFiDescuentoVolumenForm f = (ReporteSAPFiDescuentoVolumenForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();
		
		String descripcionReporte = "";
		String condicionTipoReporte = "";
		String codigoPais =  f.getCodigoPais();
		
		
		String nInicio=DateUtil.convertDateToString(f.getFechaInicioD());
		String nFin=DateUtil.convertDateToString(f.getFechaFinD());
		
		params.put("NroReporte", " ");		
		params.put("titulo", getResourceMessage("reporteSAPFiDescuentoVolumenForm.titulo"));
		params.put("codigoPais", codigoPais);
		

		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.matPromocional");
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);	
			condicionTipoReporte += " and I.OID_TIPO_OFER <>'2085'";
		}

		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_PROMO_USUARIO)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.promUsuario"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_PROMO_USUARIO);			
		}
		
		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_VENTA_LINEA)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.ventaLinea"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_VENTA_LINEA);			
		}		

		params.put("descripcionTRepor", descripcionReporte);		
		params.put("fechaInicio", nInicio);
		params.put("fechaFin", nFin);
		params.put("condicionTipoReporte", condicionTipoReporte);
		
		return params;
		
	}	
}
