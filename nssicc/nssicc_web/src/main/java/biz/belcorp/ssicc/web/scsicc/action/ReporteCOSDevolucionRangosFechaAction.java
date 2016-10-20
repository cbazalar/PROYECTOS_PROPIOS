package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOSDevolucionRangosFechaForm;


/**
 * 
 * @author <a href="">Cristhian Roman</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOSDevolucionRangosFechaAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		  return new ReporteCOSDevolucionRangosFechaForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOSDevolucionRangosFechaForm form = (ReporteCOSDevolucionRangosFechaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteCOSDevolucionRangosFechaXLS";
		else
		   return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteCOSDevolucionRangosFechaForm form = (ReporteCOSDevolucionRangosFechaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if("PDF".equals(form.getFormatoExportacion()))
			 return "reporteCOSDevolucionRangosFechaPDF";
		 
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOSDevolucionRangosFechaForm f = (ReporteCOSDevolucionRangosFechaForm) this.formReporte;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaPeriodos = new HashMap();
		
		String fechaInicial = DateFormatUtils.format(f.getFechaInicial(),"dd/MM/yyyy");
		String fechaFinal= DateFormatUtils.format(f.getFechaFinal(),"dd/MM/yyyy");
		
		criteriaPeriodos.put("fechaInicial",fechaInicial);
		criteriaPeriodos.put("fechaFinal",fechaFinal);
		
		List listPeriodos=reporteService.devuelvePeriodosporFechas(criteriaPeriodos);
		IdiomaService service = (IdiomaService) getBean("idiomaService");

		String s = this.getmPantallaPrincipalBean().getLocale();
		Idioma idioma = service.getIdiomaByCodigoISO(s);
		String codigoISO = idioma.getCodigoISO();
		
		String[]periodos=new String[listPeriodos.size()];
		for(int i=0; i<listPeriodos.size();i++){
			Base base =(Base)listPeriodos.get(i);
			periodos[i]=base.getCodigo();
		}
		
		String condicionPeriodo = this.obtieneCondicion(periodos,"A.PERD_OID_PERI","");
		
		params.put("fechaInicial",fechaInicial);			
		params.put("fechaFinal",fechaFinal);
		params.put("condicionPeriodo",condicionPeriodo);
		params.put("idioma",codigoISO);
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteCOSDevolucionRangosFechaForm.title"));

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		 log.debug("----- in setViewAttributes");
		 
	     this.mostrarReporteXLS = true;
			
		 ReporteCOSDevolucionRangosFechaForm f = (ReporteCOSDevolucionRangosFechaForm) this.formReporte;

	/*	 f.setFechaInicial(sdf.format(new Date(System.currentTimeMillis()))); 
	     f.setFechaFinal(sdf.format(new Date(System.currentTimeMillis())));*/
		 
		 f.setFechaInicial(new Date(System.currentTimeMillis())); 
	     f.setFechaFinal(new Date(System.currentTimeMillis()));
	}
	
}