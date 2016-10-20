package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENFacturaNotaCreditoForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENFacturaNotaCreditoVenezuelaForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENRegistroVentasConsolidadoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteVENFacturaNotaCreditoVenezuelaAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 6696429149990276013L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENFacturaNotaCreditoVenezuelaForm form = new ReporteVENFacturaNotaCreditoVenezuelaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteVENFacturaNotaCreditoVenezuelaForm f = (ReporteVENFacturaNotaCreditoVenezuelaForm) this.formReporte;
		
		if(StringUtils.equals("XLS", f.getFormatoExportacion())){
			if(StringUtils.equals("FAC", f.getTipoReporte()))
				return  "reporteVENFacturaVenezuelaXLS";
			
			else if(StringUtils.equals("CRED", f.getTipoReporte()))
				return  "reporteVENNotaCreditoVenezuelaXLS";
			else 
				return null;			
		}else 
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteVENFacturaNotaCreditoVenezuelaForm f = (ReporteVENFacturaNotaCreditoVenezuelaForm) this.formReporte;
		if(StringUtils.equals("PDF", f.getFormatoExportacion())){
		if(StringUtils.equals("FAC", f.getTipoReporte()))
			return  "reporteVENFacturaVenezuelaPDF";
		
		else if(StringUtils.equals("CRED", f.getTipoReporte()))
			return  "reporteVENNotaCreditoVenezuelaPDF";
		else 
			return null;
		
		}else 
			return "reporteMaestroHorizontal";	
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENFacturaNotaCreditoVenezuelaForm f = (ReporteVENFacturaNotaCreditoVenezuelaForm) this.formReporte;
		ReporteService reporteService = (ReporteService)this.getBeanService("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		Map criteria= new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais=reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		f.setOidPais(oidPais);
		
		String fecha1 = DateUtil.getDate(f.getFechaInicioDate());
		String fecha2=DateUtil.getDate(f.getFechaFinDate());
		f.setFechaInicio(fecha1);
		f.setFechaFin(fecha2);
		String titulo="";
		if(StringUtils.equals(f.getTipoReporte(), "FAC"))
			titulo="reporteVENFacturaNotaCreditoVenezuelaForm.tituloFactura";
		else
			titulo="reporteVENFacturaNotaCreditoVenezuelaForm.tituloCredito";
		
		params.put("oidPais", f.getOidPais());
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		params.put("titulo", this.getResourceMessage(titulo));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=true;
		this.mostrarReporteXLS = true;
		
	}
	
	public String setValidarReporte() {
		ReporteVENFacturaNotaCreditoVenezuelaForm f = (ReporteVENFacturaNotaCreditoVenezuelaForm) this.formReporte;
	    if (f.getFechaFinDate().compareTo(f.getFechaInicioDate()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}

}
