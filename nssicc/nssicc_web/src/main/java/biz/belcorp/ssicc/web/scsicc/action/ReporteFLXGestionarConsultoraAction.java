/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXGestionarConsultoraForm;

/**
 * @author fochoa
 *
 */

@ManagedBean
@SessionScoped
public class ReporteFLXGestionarConsultoraAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1799089804429284443L;
	
	private String formatoReporte;
	private String tipoReporte;
	
	private String campanyaComunicacion;
	private String campanyaFacturacion;
	private String codigoCliente;
	private String codigoCalificacionComportamiento;
	private String codigoCalificacionExperiencia;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReporteFLXGestionarConsultoraForm form = new ReporteFLXGestionarConsultoraForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		log.debug("ReporteFLXGestionarConsultoraAction - prepareParameterMap");
		
		ReporteFLXGestionarConsultoraForm f = (ReporteFLXGestionarConsultoraForm) this.formReporte;		
		this.formatoReporte = f.getFormatoExportacion();
		this.tipoReporte = f.getTipoReporteAMostrar();
		this.setGenerateTabsXLS(true);
		
		params.put("codigoPais",f.getCodigoPais());
		params.put("campanyaComunicacion",f.getCampanyaComunicacion());
		params.put("campanyaFacturacion",f.getCampanyaFacturacion());
		params.put("codigoCliente",f.getCodigoCliente());
		params.put("codigoCalificacionComportamiento",f.getCodigoCalificacionComportamiento());
		params.put("codigoCalificacionExperiencia",f.getCodigoCalificacionExperiencia());
		params.put("titulo", this.getResourceMessage("reporteFLXGestionarConsultoraForm.title"));		
		params.put("formatoExportacion", formatoReporte);
		
		return params;	
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		log.debug("ReporteFLXGestionarConsultoraAction - setViewAttributes");	
	}
	
	public void inicializarValores(){
		
		ReporteFLXGestionarConsultoraForm f = (ReporteFLXGestionarConsultoraForm) this.formReporte;	
		
		f.setCampanyaComunicacion(this.campanyaComunicacion);
		f.setCampanyaFacturacion(this.campanyaFacturacion);
		f.setCodigoCliente(this.codigoCliente);
		f.setCodigoCalificacionComportamiento(this.codigoCalificacionComportamiento);
		f.setCodigoCalificacionExperiencia(this.codigoCalificacionExperiencia);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteFLXGestionarConsultoraService";
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the campanyaComunicacion
	 */
	public String getCampanyaComunicacion() {
		return campanyaComunicacion;
	}

	/**
	 * @param campanyaComunicacion the campanyaComunicacion to set
	 */
	public void setCampanyaComunicacion(String campanyaComunicacion) {
		this.campanyaComunicacion = campanyaComunicacion;
	}

	/**
	 * @return the campanyaFacturacion
	 */
	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}

	/**
	 * @param campanyaFacturacion the campanyaFacturacion to set
	 */
	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the codigoCalificacionComportamiento
	 */
	public String getCodigoCalificacionComportamiento() {
		return codigoCalificacionComportamiento;
	}

	/**
	 * @param codigoCalificacionComportamiento the codigoCalificacionComportamiento to set
	 */
	public void setCodigoCalificacionComportamiento(
			String codigoCalificacionComportamiento) {
		this.codigoCalificacionComportamiento = codigoCalificacionComportamiento;
	}

	/**
	 * @return the codigoCalificacionExperiencia
	 */
	public String getCodigoCalificacionExperiencia() {
		return codigoCalificacionExperiencia;
	}

	/**
	 * @param codigoCalificacionExperiencia the codigoCalificacionExperiencia to set
	 */
	public void setCodigoCalificacionExperiencia(
			String codigoCalificacionExperiencia) {
		this.codigoCalificacionExperiencia = codigoCalificacionExperiencia;
	}
	
	
}
