package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECVentasMensualesForm;


/**
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@ManagedBean
@SessionScoped
public class ReporteRECVentasMensualesAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECVentasMensualesForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECVentasMensualesForm form = (ReporteRECVentasMensualesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteRECVentasMensualesXLS";
		else
		   return "reporteMaestroHorizontal";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECVentasMensualesForm form = (ReporteRECVentasMensualesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteRECVentasMensualesPDF";
	}

	@Override
	public String setValidarReporte() {
		ReporteRECVentasMensualesForm form = (ReporteRECVentasMensualesForm)this.formReporte;
		AjaxService ajaxService  =  (AjaxService) getBean("ajaxService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Integer periodoIncial = Integer.parseInt(form.getCodigoPeriodoInicial());
		Integer periodoFinal = Integer.parseInt(form.getCodigoPeriodoFinal());

		Date fechaDesde=form.getFechaInicio();
		Date fechaHasta=form.getFechaFin();	
		
		if (StringUtils.isNotBlank(form.getCodigoPeriodoInicial()) && StringUtils.isNotBlank(form.getCodigoPeriodoFinal())) {
			if (periodoIncial > periodoFinal){
				String mensaje = getResourceMessage("reporteRECVentasMensualesForm.validacionPeriodo");
				return mensaje;
			}
		}
		if(fechaDesde != null && fechaHasta != null){
			if(fechaDesde.compareTo(fechaHasta) > 0){
				String mensaje = getResourceMessage("reporteRECVentasMensualesForm.validacionFechas");
				return mensaje;
			}
			
		}

		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(form.getCodigoPais(),Constants.CODIGO_CANAL_DEFAULT);
	  
		try {
			String strFechaInicial = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(form.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo); 		
			Date fechaInicioPeriodoInicial = DateUtils.parseDate(strFechaInicial, "dd/MM/yyyy"); 
			String strFechaFinal = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(form.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
			Date fechaFinalPeriodoInicial = DateUtils.parseDate(strFechaFinal, "dd/MM/yyyy");
			
		   if (fechaDesde != null){
	    	
	    	int resultado  = fechaDesde.compareTo(fechaInicioPeriodoInicial);    
	    	//int resultado1 = fechaFinalPeriodoInicial.compareTo(fechaDesde);  

	    	if ( resultado == -1) 
	        {	
	            return "La fecha inicial debe de encontrarse en los siguientes intervalos ".concat(strFechaInicial)
	            		.concat(" - ").concat(strFechaFinal) ; 
	        }	    	
	    }
		
		    if (fechaHasta != null){

			    	int resultado  = fechaFinalPeriodoInicial.compareTo(fechaHasta);    

			    	if ( resultado == -1) 
			        {		
			        	return "La fecha final debe de encontrarse en los siguientes intervalos ".concat(strFechaInicial).concat(" - ").concat(strFechaFinal) ; 
			        }  
			 }
		} catch (ParseException e) {}
	
      return null;
	}
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECVentasMensualesForm reporteRECForm = (ReporteRECVentasMensualesForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
	
		Map criteria = params;
		
		StringBuffer condicion = new StringBuffer();
		
		if(StringUtils.isNotBlank(reporteRECForm.getCodigoPeriodoInicial())){
			criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicial());
			String oidPeriodoInicio = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);	
			params.put("codigoPeriodoInicio", reporteRECForm.getCodigoPeriodoInicial());
			params.put("oidPeriodoInicio", oidPeriodoInicio);
			
			condicion.append(" AND periactual.oid_peri >= "+oidPeriodoInicio+" ");
		}
		
		if(StringUtils.isNotBlank(reporteRECForm.getCodigoPeriodoFinal())){
			criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFinal());
			String oidPeriodoFin = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
			params.put("codigoPeriodoFin", reporteRECForm.getCodigoPeriodoFinal());
			params.put("oidPeriodoFin", oidPeriodoFin);
			
			condicion.append(" AND periactual.oid_peri <= "+oidPeriodoFin+" ");
		}
		
		Date fechaInicio = reporteRECForm.getFechaInicio();
		if (fechaInicio != null){
			String strFechaInicio = DateFormatUtils.format(fechaInicio,"dd/MM/yyyy");
			params.put("fechaInicio", strFechaInicio);
			
			condicion.append(" AND sc.FEC_FACT >= to_date('"+strFechaInicio+"', 'DD/MM/yyyy') ");
		}
		
		Date fechaFin = reporteRECForm.getFechaFin();
		if (fechaFin != null){
			String strFechaFin = DateFormatUtils.format(fechaFin,"dd/MM/yyyy");
			params.put("fechaFin", strFechaFin);
			condicion.append(" and sc.FEC_FACT <= to_date('"+strFechaFin+"', 'DD/MM/yyyy') ");
		}
		
		params.put("condicion", condicion.toString());
		params.put("tipoSolicitud", Constants.REPORTE_SIC_PEDIDOS_DIGITADOS_SOLICITUD);
		
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteRECVentasMensualesForm.titulo"));
		
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		ReporteRECVentasMensualesForm reporteRECform = (ReporteRECVentasMensualesForm) this.formReporte;
		reporteRECform.setCodigoPais(pais.getCodigo());
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajaxService  =  (AjaxService) getBean("ajaxService");
		
		/* colocando periodos */
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
		reporteRECform.setCodigoPeriodoInicial(codigoPeriodo);
		reporteRECform.setCodigoPeriodoFinal(codigoPeriodo);
		
		/* colocando fechas */
		String date = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo); 		
		reporteRECform.setFechaInicio(DateUtils.parseDate(date, "dd/MM/yyyy")); 
		date = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
		reporteRECform.setFechaFin(DateUtils.parseDate(date, "dd/MM/yyyy"));
	}

	

	
}