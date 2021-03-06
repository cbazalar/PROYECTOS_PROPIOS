package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ReporteMAECargaClasificacionClientesForm;

/**
 * @author jpulido
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReporteMAECargaClasificacionClientesAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private String numeroCarga;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAECargaClasificacionClientesForm form = new ReporteMAECargaClasificacionClientesForm();
		return form;
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
		if (formatoReporte.equals("XLS")) {
			return "reporteMAECargaClasiClieXLS";
		}

		return "reporteMaestroHorizontal";
	}
	
	/**
	 * @param event
	 */
	public void salirPadre(ActionEvent event){
		try {
			String pagina = "procesoMAECargaClasificacionClienteForm";
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
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (formatoReporte.equals("PDF")) {
			return "reporteMAECargaClasificacionClientesPDF";
		}
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
		formatoReporte = this.formatoExportacion;
		String numeroCarga = this.numeroCarga;;

		params.put("numeroCarga", numeroCarga);
		params.put(
				"titulo",
				this.getResourceMessage("reporteMAECargaClasificacionClientesForm.titulo"));
		params.put("formatoExportacion", formatoReporte);

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
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the numeroCarga
	 */
	public String getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @param numeroCarga
	 *            the numeroCarga to set
	 */
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}

}
