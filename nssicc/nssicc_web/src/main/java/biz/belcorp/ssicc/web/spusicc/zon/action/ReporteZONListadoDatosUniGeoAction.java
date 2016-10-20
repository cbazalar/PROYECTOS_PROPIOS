package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ReporteZONListadoDatosUniGeoForm;

@ManagedBean
@SessionScoped
public class ReporteZONListadoDatosUniGeoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONListadoDatosUniGeoForm f = new ReporteZONListadoDatosUniGeoForm();
		
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteZONListadoDatosUniGeoPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		params.put("NroReporte", "REP-ZON02");
		params.put("titulo","Listado de Datos de Unidades Geográficas");
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes()... - ReporteZONListadoDatosUniGeoAction");
		}
		
		this.mostrarReportePDF = false;
		this.mostrarBotonLimpiar = false;
	}
	
	/**
	 * @param event
	 */
	public void salirPadre(ActionEvent event){
		try {
			this.redireccionarPagina("procesoZONActualizarUnidadesGeograficasForm");
		} catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", error);
		}
	}
}