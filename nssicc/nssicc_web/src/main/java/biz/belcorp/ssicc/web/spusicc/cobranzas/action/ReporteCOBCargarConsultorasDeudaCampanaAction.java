package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ReporteCOBCargarConsultorasDeudaCampanaForm;

@ManagedBean
@SessionScoped
public class ReporteCOBCargarConsultorasDeudaCampanaAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 7496906172990857522L;

	private String[] consultoras;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		return new ReporteCOBCargarConsultorasDeudaCampanaForm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOBCargarConsultorasDeudaCampanaXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBCargarConsultorasDeudaCampanaForm f = (ReporteCOBCargarConsultorasDeudaCampanaForm) this.formReporte;
		f.setFormatoExportacion("XLS");
		
		String condicionConsultoras = obtieneCondicion(this.consultoras, "mc.cod_clie", "'");
		params.put("condicionConsultoras", condicionConsultoras);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {

	}

	/**
	 * @return the consultoras
	 */
	public String[] getConsultoras() {
		return consultoras;
	}

	/**
	 * @param consultoras the consultoras to set
	 */
	public void setConsultoras(String[] consultoras) {
		this.consultoras = consultoras;
	}
}