package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEnviarTransferenciaBoletasRecojoForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEnviarUnidadesAlmacenVirtualForm;

@ManagedBean
@SessionScoped
public class ReporteRECEnviarTransferenciaBoletasRecojoCabeceraAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 7496906172990857522L;

	private Map mapProperties;
	private String tipoOperacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		return new ReporteRECEnviarTransferenciaBoletasRecojoForm();
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
		return "reporteMaestroHorizontal";
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
		if (tipoOperacion.equals(Constants.REC_INDIC_TRANS_BOREC_ANULA))	return "reporteRECEnviarTransferenciaBoletasRecojoAnulacionZonaPDF";
		else return "reporteRECEnviarTransferenciaBoletasRecojoSinAnulacionCabeceraPDF";
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
		
		ReporteRECEnviarTransferenciaBoletasRecojoForm f = (ReporteRECEnviarTransferenciaBoletasRecojoForm) this.formReporte;
		f.setFormatoExportacion("VPDF");
		
		
		String titulo = null;	
		titulo= this.getResourceMessage("reporteRECEnviarTransferenciaBoletasRecojoAnulacionZona.titulo");
		params = getMapProperties();
		params.put("titulo", titulo);
		return params;
	}
	
	/**
	 * @param event
	 */
	public void salirPadre(ActionEvent event){
		try {
			String pagina = "interfazRECEnviarTransferenciaBoletasRecojoForm";
			this.redireccionarPagina(pagina);
				
		} catch (Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", error);
		}
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

	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
}