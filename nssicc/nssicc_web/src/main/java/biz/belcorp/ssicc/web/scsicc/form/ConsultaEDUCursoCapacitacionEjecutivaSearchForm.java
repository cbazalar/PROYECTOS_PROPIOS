package biz.belcorp.ssicc.web.scsicc.form;



import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ConsultaEDUCursoCapacitacionEjecutivaSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 * @struts.form name = "consultaEDUCursoCapacitacionEjecutivaSearchForm"
 */
public class ConsultaEDUCursoCapacitacionEjecutivaSearchForm extends BaseSearchForm  implements Serializable{

	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;	
	private String situacion;
	private String codigoCurso;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String campanhaInicio;
	private String campanhaFinal;
	private String codigoInstructora;
	private String hiddencodigoInstructora;
	private String descripcionInstructora;
	private String codigoPlanilla;
	private String codigoDictado;
	private String codigoPlanillaDictado;
	
	private String hiddenCodigoPlanilla;
	private String hiddenCodigoDictado;
	private String hiddenCodigoPlanillaDictado;
	private String cargaCombo;
	
	private String[] codigoRegion;
	private String[] codigoZona;
	
	private String campanhaIngreso;
	
	private String tipo;
	
	private String hiddenTipo;
	
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	
	/**
	 * @return Returns the codigoInstructora.
	 */
	public String getCodigoInstructora() {
		return codigoInstructora;
	}
	/**
	 * @param codigoInstructora The codigoInstructora to set.
	 */

	public void setCodigoInstructora(String codigoInstructora) {
		this.codigoInstructora = codigoInstructora;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the descripcionInstructora.
	 */
	public String getDescripcionInstructora() {
		return descripcionInstructora;
	}
	/**
	 * @param descripcionInstructora The descripcionInstructora to set.
	 */
	public void setDescripcionInstructora(String descripcionInstructora) {
		this.descripcionInstructora = descripcionInstructora;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return Returns the campanhaFinal.
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}
	/**
	 * @param campanhaFinal The campanhaFinal to set.
	 * @struts.validator type = "required"
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}
	/**
	 * @return Returns the campanhaInicio.
	 */
	public String getCampanhaInicio() {
		return campanhaInicio;
	}
	/**
	 * @param campanhaInicio The campanhaInicio to set.
	 * @struts.validator type = "required" 
	 */
	public void setCampanhaInicio(String campanhaInicio) {
		this.campanhaInicio = campanhaInicio;
	}
	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso The codigoCurso to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return Returns the codigoPlanilla.
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	/**
	 * @param codigoPlanilla The codigoPlanilla to set.
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	/**
	 * @return Returns the descripcionConsultora.
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}
	/**
	 * @param descripcionConsultora The descripcionConsultora to set.
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
	/**
	 * @return Returns the situacion.
	 */
	public String getSituacion() {
		return situacion;
	}
	/**
	 * @param situacion The situacion to set.
	 * @struts.validator type = "required"
	 */
	
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the codigoDictado.
	 */
	public String getCodigoDictado() {
		return codigoDictado;
	}

	/**
	 * @param codigoDictado The codigoDictado to set.
	 */
	public void setCodigoDictado(String codigoDictado) {
		this.codigoDictado = codigoDictado;
	}

	/**
	 * @return Returns the codigoPlanillaDictado.
	 */
	public String getCodigoPlanillaDictado() {
		return codigoPlanillaDictado;
	}

	/**
	 * @param codigoPlanillaDictado The codigoPlanillaDictado to set.
	 */
	public void setCodigoPlanillaDictado(String codigoPlanillaDictado) {
		this.codigoPlanillaDictado = codigoPlanillaDictado;
	}


	/**
	 * @return Returns the hiddencodigoInstructora.
	 */
	public String getHiddencodigoInstructora() {
		return hiddencodigoInstructora;
	}


	/**
	 * @param hiddencodigoInstructora The hiddencodigoInstructora to set.
	 */
	public void setHiddencodigoInstructora(String hiddencodigoInstructora) {
		this.hiddencodigoInstructora = hiddencodigoInstructora;
	}
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.codigoEmpresa = null;
		this.nombreEmpresa = null;	
		this.situacion = Constants.ESTADO_CAPACITADA_PENDIENTE;
		this.codigoCurso = null;
		this.codigoConsultora = null;
		this.descripcionConsultora = null;
		this.campanhaInicio = null;  
		this.campanhaFinal = null;   
		this.codigoPlanilla = null;  
		this.codigoDictado = null;   
		this.codigoPlanillaDictado = null;
		this.codigoRegion = null;    
		this.codigoZona = null;  
		this.hiddenCodigoPlanilla = null;  
		this.hiddenCodigoDictado = null;   
		this.hiddenCodigoPlanillaDictado = null;
	}

	/**
	 * @return Returns the hiddenCodigoDictado.
	 */
	public String getHiddenCodigoDictado() {
		return hiddenCodigoDictado;
	}

	/**
	 * @param hiddenCodigoDictado The hiddenCodigoDictado to set.
	 */
	public void setHiddenCodigoDictado(String hiddenCodigoDictado) {
		this.hiddenCodigoDictado = hiddenCodigoDictado;
	}

	/**
	 * @return Returns the hiddenCodigoPlanilla.
	 */
	public String getHiddenCodigoPlanilla() {
		return hiddenCodigoPlanilla;
	}

	/**
	 * @param hiddenCodigoPlanilla The hiddenCodigoPlanilla to set.
	 */
	public void setHiddenCodigoPlanilla(String hiddenCodigoPlanilla) {
		this.hiddenCodigoPlanilla = hiddenCodigoPlanilla;
	}

	/**
	 * @return Returns the hiddenCodigoPlanillaDictado.
	 */
	public String getHiddenCodigoPlanillaDictado() {
		return hiddenCodigoPlanillaDictado;
	}

	/**
	 * @param hiddenCodigoPlanillaDictado The hiddenCodigoPlanillaDictado to set.
	 */
	public void setHiddenCodigoPlanillaDictado(String hiddenCodigoPlanillaDictado) {
		this.hiddenCodigoPlanillaDictado = hiddenCodigoPlanillaDictado;
	}

	/**
	 * @return Returns the cargaCombo.
	 */
	public String getCargaCombo() {
		return cargaCombo;
	}

	/**
	 * @param cargaCombo The cargaCombo to set.
	 */
	public void setCargaCombo(String cargaCombo) {
		this.cargaCombo = cargaCombo;
	}

	/**
	 * @return Returns the campanhaIngreso.
	 */
	public String getCampanhaIngreso() {
		return campanhaIngreso;
	}

	/**
	 * @param campanhaIngreso The campanhaIngreso to set.
	 */
	public void setCampanhaIngreso(String campanhaIngreso) {
		this.campanhaIngreso = campanhaIngreso;
	}

	/**
	 * @return Returns the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The situacion to set.
	 * @struts.validator type = "required"
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the hiddenTipo
	 */
	public String getHiddenTipo() {
		return hiddenTipo;
	}

	public void setHiddenTipo(String hiddenTipo) {
		this.hiddenTipo = hiddenTipo;
	}

	
	
}
