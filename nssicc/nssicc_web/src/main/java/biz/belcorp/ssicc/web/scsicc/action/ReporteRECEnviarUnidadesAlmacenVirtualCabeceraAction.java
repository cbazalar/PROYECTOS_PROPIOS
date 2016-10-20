package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEnviarUnidadesAlmacenVirtualForm;

@ManagedBean
@SessionScoped
public class ReporteRECEnviarUnidadesAlmacenVirtualCabeceraAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 7496906172990857522L;

	private Map mapProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		return new ReporteRECEnviarUnidadesAlmacenVirtualForm();
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
		return "reporteRECEnviarUnidadesAlmacenVirtualCabeceraXLS";
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
		return null;
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

		ReporteRECEnviarUnidadesAlmacenVirtualForm f = (ReporteRECEnviarUnidadesAlmacenVirtualForm) this.formReporte;
		f.setFormatoExportacion("XLS");

		params = getMapProperties();

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {

	}

	/**
	 * @return the mapProperties
	 */
	public Map getMapProperties() {
		return mapProperties;
	}

	/**
	 * @param mapProperties
	 *            the mapProperties to set
	 */
	public void setMapProperties(Map mapProperties) {
		this.mapProperties = mapProperties;
	}
}