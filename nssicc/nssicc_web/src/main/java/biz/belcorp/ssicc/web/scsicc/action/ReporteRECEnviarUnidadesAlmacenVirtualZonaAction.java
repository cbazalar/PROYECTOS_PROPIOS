package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEnviarUnidadesAlmacenVirtualForm;

@ManagedBean
@SessionScoped
public class ReporteRECEnviarUnidadesAlmacenVirtualZonaAction extends
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {

		return "reporteRECEnviarUnidadesAlmacenVirtualZonaPDF";
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

		return "reporteRECEnviarUnidadesAlmacenVirtualZonaPDF";
	}
	
	/**
	 * @param event
	 */
	public void salirPadre(ActionEvent event){
		try {
			String pagina = "interfazRECEnviarUnidadesAlmacenVirtualForm";
			this.redireccionarPagina(pagina);
				
		} catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", error);
		}
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
		f.setFormatoExportacion("PDF");

		params = getMapProperties();

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
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