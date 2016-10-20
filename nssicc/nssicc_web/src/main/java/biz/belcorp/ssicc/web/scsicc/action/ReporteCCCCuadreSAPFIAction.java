package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCCuadreSAPFIForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCCCCuadreSAPFIAction extends
BaseReporteAbstractAction {
	
	
	private static final long serialVersionUID = 9171303446937780870L;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCCuadreSAPFIForm reporteForm = new ReporteCCCCuadreSAPFIForm();
		return reporteForm;
	}
	
	
	@Override	
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCCCCuadreSAPFIForm f = (ReporteCCCCuadreSAPFIForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		f.setCodigoPais(codpais);		
							
			
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return  "reporteCCCCuadreSAPFIXLS";	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {					
		return null;
	}
	

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
				        				                      				
		log.debug(" Imprimiendo parįmetros");
		log.debug(params);
		ReporteCCCCuadreSAPFIForm form = (ReporteCCCCuadreSAPFIForm) this.formReporte;

		String fechaDesde,fechaHasta;
		
		fechaDesde = DateUtil.convertDateToString(form.getFechaProcDesdeD());
		fechaHasta = DateUtil.convertDateToString(form.getFechaProcHastaD());
		form.setFechaProcDesde(fechaDesde);
		form.setFechaProcHasta(fechaHasta);
		params.put("fechaProcDesde", fechaDesde);
		params.put("fechaProcHasta", fechaHasta);
		
		log.debug("Fin parįmetros");
		return params;
		
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCCuadreSAPFIService";
	}
	
	public String setValidarReporte() {
		ReporteCCCCuadreSAPFIForm form = (ReporteCCCCuadreSAPFIForm) this.formReporte;
	    if (form.getFechaProcHastaD().compareTo(form.getFechaProcDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}
}
