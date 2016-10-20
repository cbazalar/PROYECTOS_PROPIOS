package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENNumeroLineasBoletaConsultoraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENNumeroLineasBoletaConsultoraAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENNumeroLineasBoletaConsultoraForm form = new ReporteVENNumeroLineasBoletaConsultoraForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion)){
			return "reporteVENNumeroLineasBoletaConsultoraXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.info("Entro a ReporteVENNumeroLineasBoletaConsultoraAction - setViewAttributes");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ReporteVENNumeroLineasBoletaConsultoraForm f = (ReporteVENNumeroLineasBoletaConsultoraForm) this.formReporte;

		f.setCodigoPais(pais.getCodigo());

		log.info("Salio a ReporteVENNumeroLineasBoletaConsultoraAction - setViewAttributes");
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.info("Entro a ReporteVENNumeroLineasBoletaConsultoraAction - prepareParameterMap");
		}
		
		ReporteVENNumeroLineasBoletaConsultoraForm f = (ReporteVENNumeroLineasBoletaConsultoraForm) this.formReporte;

		log.info("Salio a ReporteVENNumeroLineasBoletaConsultoraAction - prepareParameterMap");
		
		return params;
	}
}