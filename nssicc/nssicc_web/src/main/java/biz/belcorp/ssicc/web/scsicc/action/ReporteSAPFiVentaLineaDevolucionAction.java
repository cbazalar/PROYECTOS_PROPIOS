package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSAPFiVentaLineaDevolucionForm;

@ManagedBean
@SessionScoped
public class ReporteSAPFiVentaLineaDevolucionAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = -2732852402559804633L;
	
	
	private String formatoReporte;	
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSAPFiVentaLineaDevolucionForm reporteForm = new ReporteSAPFiVentaLineaDevolucionForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		
		ReporteSAPFiVentaLineaDevolucionForm f = (ReporteSAPFiVentaLineaDevolucionForm) this.formReporte;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		        
		f.setCodigoTipoReporte(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);
        f.setFechaInicio("");
        f.setFechaFin("");
		
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))	
			return  "reporteSAPFiVentaLineaDevXLS";
		else
			return "reporteMaestroVertical";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSAPFiVentaLineaDevPDF";
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteSAPFiVentaLineaDevolucionForm f = (ReporteSAPFiVentaLineaDevolucionForm) this.formReporte;		
		formatoReporte = f.getFormatoExportacion();
		
		String descripcionReporte = "";
		String descTipoOperacion = "";
		String condicionTipoReporte = "";
		String codigoPais =  f.getCodigoPais();
		
		String nInicio=DateUtil.convertDateToString(f.getFechaInicioD());
		String nFin=DateUtil.convertDateToString(f.getFechaFinD());
		
		params.put("NroReporte", " ");
		params.put("titulo", getResourceMessage("reporteSAPFiVentaLineaDevolucionForm.titulo"));
		params.put("codigoPais", codigoPais);
		
		
		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_MATERIAL_PROM)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.matPromocional");
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_MATERIAL_PROM);
			
			// Venta
			if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_VENTA)){
			  condicionTipoReporte += " AND H.VAL_GRUP_ARTI IN ('0503','0504')";
			}	
			//
			condicionTipoReporte += " AND I.OID_TIPO_OFER <> '2085'"; //087
		}
		

		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_PROMO_USUARIO)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.promUsuario"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_PROMO_USUARIO);	
			
			// Venta
			if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_VENTA)){
			  condicionTipoReporte += " AND H.VAL_GRUP_ARTI IN ('0501')";
			}			
		}
		
	
		if (f.getCodigoTipoReporte().equals(Constants.SAP_TIPO_REPORTE_VENTA_LINEA)){
			descripcionReporte = getResourceMessage("reporteSAPFiDescuentoVolumenForm.ventaLinea"); 
			params.put("tipoReporte", Constants.SAP_TIPO_REPORTE_VENTA_LINEA);	
			
			// se agrego
			if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_VENTA)){
				condicionTipoReporte += " AND H.VAL_GRUP_ARTI NOT IN ('0501','0503','0504')";
			}			
		}		

		params.put("descripcionTRepor", descripcionReporte);

		if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_VENTA)){
			descTipoOperacion = getResourceMessage("reporteSAPFiVentaLineaDevolucionForm.tipoOperaVenta");
			params.put("tipoOperacion", Constants.SAP_TIPO_OPERACION_VENTA);
			condicionTipoReporte += " AND A.TIDO_OID_TIPO_DOCU IN (1,29,34,41)"; 
		}
		
		if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_DEVOLUCION)){
			descTipoOperacion = getResourceMessage("reporteSAPFiVentaLineaDevolucionForm.tipoOperaDevol");
			params.put("tipoOperacion", Constants.SAP_TIPO_OPERACION_DEVOLUCION);
			
			condicionTipoReporte += " AND A.VAL_TOTA_PAGA_LOCA < 0";
		}		

		if (f.getCodigoTipoOperacion().equals(Constants.SAP_TIPO_OPERACION_VENTA_TO87)){
			descTipoOperacion = getResourceMessage("reporteSAPFiVentaLineaDevolucionForm.tipoOperaVenta");
			params.put("tipoOperacion", Constants.SAP_TIPO_OPERACION_VENTA);
			
			condicionTipoReporte += "AND I.OID_TIPO_OFER = '2085'"; //087
			condicionTipoReporte += " AND A.TIDO_OID_TIPO_DOCU IN (1,29,34,41)"; 
		}
		
		params.put("descripcionTOper", descTipoOperacion);		
		params.put("fechaInicio", nInicio);
		params.put("fechaFin", nFin);
		params.put("condicionTipoReporte", condicionTipoReporte);
		
		return params;
	}	
	
	public String setValidarReporte() {
		ReporteSAPFiVentaLineaDevolucionForm form = (ReporteSAPFiVentaLineaDevolucionForm) this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}
}
