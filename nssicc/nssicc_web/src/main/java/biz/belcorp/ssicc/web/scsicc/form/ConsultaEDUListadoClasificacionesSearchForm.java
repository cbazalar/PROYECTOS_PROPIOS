package biz.belcorp.ssicc.web.scsicc.form;



import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ConsultaEDUListadoClasificacionesSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * @struts.form name = "consultaEDUListadoClasificacionesSearchForm"
 */
public class ConsultaEDUListadoClasificacionesSearchForm extends BaseSearchForm  implements Serializable{

	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;	

	
	private String codigoConsultora;
	private String descripcionConsultora;
	private String codigoPeriodo;
	private String codigoClasificacion;
	private String codigoCurso;
	
	private String indicadorEnvioComercial;
	
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
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
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

	public void reset() {
		this.codigoEmpresa = null;
		this.nombreEmpresa = null;	
		this.codigoCurso = null;
		this.codigoConsultora = null;
		this.descripcionConsultora = null;
		this.codigoPeriodo = null;
		this.codigoClasificacion = null ;
		this.indicadorEnvioComercial= Constants.ESTADO_ACTIVO;

	}

	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the indicadorEnvioComercial.
	 */
	public String getIndicadorEnvioComercial() {
		return indicadorEnvioComercial;
	}

	/**
	 * @param indicadorEnvioComercial The indicadorEnvioComercial to set.
	 */
	public void setIndicadorEnvioComercial(String indicadorEnvioComercial) {
		this.indicadorEnvioComercial = indicadorEnvioComercial;
	}

	
}
