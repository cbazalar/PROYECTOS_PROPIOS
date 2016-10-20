package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSAPFiDescuentoComercialForm;


@ManagedBean
@SessionScoped
public class ReporteSAPFiDescuentoComercialAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -6298138017254786444L;
	
	
	private String formatoReporte;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSAPFiDescuentoComercialForm reporteForm = new ReporteSAPFiDescuentoComercialForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
        
        this.mostrarReporteXLS = true;
		
        ReporteSAPFiDescuentoComercialForm f = (ReporteSAPFiDescuentoComercialForm) this.formReporte;        
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		     
		f.setCodigoTipoReporte(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);
        f.setFechaInicio("");
        f.setFechaFin("");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))	
			return  "reporteSAPFiDescuentoComercialXLS";
		else 
			return "reporteMaestroVertical";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSAPFiDescuentoComercialPDF";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteSAPFiDescuentoComercialForm f = (ReporteSAPFiDescuentoComercialForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();
		
		String descripcionReporte = "";
		String condicionTipoReporte = "";
		String codigoPais =  f.getCodigoPais();
		String nInicio=DateUtil.convertDateToString(f.getFechaInicioD());
		String nFin=DateUtil.convertDateToString(f.getFechaFinD());
		
		
		params.put("NroReporte", " ");
		params.put("titulo", getResourceMessage("reporteSAPFiDescuentoComercialForm.titulo"));
		params.put("codigoPais", codigoPais);
		
		
		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoComercialForm.matPromocional");
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);
			
			//condicionTipoReporte += " AND H.VAL_GRUP_ARTI IN ('0503','0504')";
			condicionTipoReporte += " AND NVL(I.OID_TIPO_OFER,'0') <> '2085'"; //087
		}

		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_PROMO_USUARIO)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoComercialForm.promUsuario"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_PROMO_USUARIO);	
			
			//condicionTipoReporte += " AND H.VAL_GRUP_ARTI = '0501'";
		}
		
		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_VENTA_LINEA)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoComercialForm.ventaLinea"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_VENTA_LINEA);	
				
			//condicionTipoReporte += " AND H.VAL_GRUP_ARTI NOT IN ('0501','0503','0504')";
		}		

		params.put("descripcionTRepor", descripcionReporte);
		params.put("fechaInicio", nInicio);
		params.put("fechaFin", nFin);
		params.put("condicionTipoReporte", condicionTipoReporte);
		
		return params;
	}
	
	public String setValidarReporte() {
		ReporteSAPFiDescuentoComercialForm form = (ReporteSAPFiDescuentoComercialForm) this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}
}
