package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEDistribucionRecuperacionDeFletesForm;


@ManagedBean
@SessionScoped
public class ReporteAPEDistribucionRecuperacionDeFletesAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEDistribucionRecuperacionDeFletesForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {

	   return "reporteAPEDistribucionRecuperacionDeFletesXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
	   return null;
	}
	

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteAPEDistribucionRecuperacionDeFletesForm reporteAPEForm = (ReporteAPEDistribucionRecuperacionDeFletesForm) this.formReporte;
	
		params.put("codigoPeriodo", reporteAPEForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteAPEDistribucionRecuperacionDeFletesForm.title"));
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("ReporteAPEDistribucionRecuperacionDeFletesAction - setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteAPEDistribucionRecuperacionDeFletesForm f = (ReporteAPEDistribucionRecuperacionDeFletesForm)this.formReporte;

		String codigoPeriodo = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		f.setCodigoPeriodo(codigoPeriodo);
		
		if (StringUtils.isEmpty(codigoPeriodo)){			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			f.setCodigoPeriodo(periodo);
		}
			
		
	}

	

	
}