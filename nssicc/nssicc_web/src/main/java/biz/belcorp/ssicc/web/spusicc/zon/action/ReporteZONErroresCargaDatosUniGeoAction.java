package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ReporteZONErroresCargaDatosUniGeoForm;

@ManagedBean
@SessionScoped
public class ReporteZONErroresCargaDatosUniGeoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONErroresCargaDatosUniGeoForm f = new ReporteZONErroresCargaDatosUniGeoForm();
		
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
		return "reporteZONErroresCargaDatosUniGeoPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		params.put("NroReporte", "REP-ZON01");
		params.put("titulo","Errores en la Carga de datos de Unidades Geográficas");
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes()... - ReporteZONErroresCargaDatosUniGeoAction");
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