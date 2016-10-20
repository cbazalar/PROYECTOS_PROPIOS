package biz.belcorp.ssicc.web.edu.form;
import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * 
 * 
 */
public class ConsultaEDUStatusConsultoraSearchForm extends BaseSearchForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7293098650230722733L;
	private String codigoPais;
	private String codigoEmpresa;
	private String codigoConsultora;
	private String descripcionConsultora;
	private Integer longitudConsultora;
	
	//situacion
	private String nivelCapacitacion;//nivel alcanzado
	private String descripcionNivelCapacitacion;
	private String codigoSituacion;
	private String descripcionSituacion;
	private String codigoCurso;//este es el curso del apta, cual e sel ultimo curso
	private String descripcionCurso;
	//son llenados solamnete cuando la consulta lo hace una consultora
	private String descripcionMensajeEspecifico;
	private String descripcionMensajeGeneral;
	//
	private String codigoPlanillaProgramacion;	
	private String codigoRegion;
	private String codigoZona;
	private String campanhaIngreso;
	//
	private String status;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}
	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
	/**
	 * @return the longitudConsultora
	 */
	public Integer getLongitudConsultora() {
		return longitudConsultora;
	}
	/**
	 * @param longitudConsultora the longitudConsultora to set
	 */
	public void setLongitudConsultora(Integer longitudConsultora) {
		this.longitudConsultora = longitudConsultora;
	}
	/**
	 * @return the nivelCapacitacion
	 */
	public String getNivelCapacitacion() {
		return nivelCapacitacion;
	}
	/**
	 * @param nivelCapacitacion the nivelCapacitacion to set
	 */
	public void setNivelCapacitacion(String nivelCapacitacion) {
		this.nivelCapacitacion = nivelCapacitacion;
	}
	/**
	 * @return the descripcionNivelCapacitacion
	 */
	public String getDescripcionNivelCapacitacion() {
		return descripcionNivelCapacitacion;
	}
	/**
	 * @param descripcionNivelCapacitacion the descripcionNivelCapacitacion to set
	 */
	public void setDescripcionNivelCapacitacion(String descripcionNivelCapacitacion) {
		this.descripcionNivelCapacitacion = descripcionNivelCapacitacion;
	}
	/**
	 * @return the codigoSituacion
	 */
	public String getCodigoSituacion() {
		return codigoSituacion;
	}
	/**
	 * @param codigoSituacion the codigoSituacion to set
	 */
	public void setCodigoSituacion(String codigoSituacion) {
		this.codigoSituacion = codigoSituacion;
	}
	/**
	 * @return the descripcionSituacion
	 */
	public String getDescripcionSituacion() {
		return descripcionSituacion;
	}
	/**
	 * @param descripcionSituacion the descripcionSituacion to set
	 */
	public void setDescripcionSituacion(String descripcionSituacion) {
		this.descripcionSituacion = descripcionSituacion;
	}
	/**
	 * @return the codigoCurso
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso the codigoCurso to set
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return the descripcionCurso
	 */
	public String getDescripcionCurso() {
		return descripcionCurso;
	}
	/**
	 * @param descripcionCurso the descripcionCurso to set
	 */
	public void setDescripcionCurso(String descripcionCurso) {
		this.descripcionCurso = descripcionCurso;
	}
	/**
	 * @return the descripcionMensajeEspecifico
	 */
	public String getDescripcionMensajeEspecifico() {
		return descripcionMensajeEspecifico;
	}
	/**
	 * @param descripcionMensajeEspecifico the descripcionMensajeEspecifico to set
	 */
	public void setDescripcionMensajeEspecifico(String descripcionMensajeEspecifico) {
		this.descripcionMensajeEspecifico = descripcionMensajeEspecifico;
	}
	/**
	 * @return the descripcionMensajeGeneral
	 */
	public String getDescripcionMensajeGeneral() {
		return descripcionMensajeGeneral;
	}
	/**
	 * @param descripcionMensajeGeneral the descripcionMensajeGeneral to set
	 */
	public void setDescripcionMensajeGeneral(String descripcionMensajeGeneral) {
		this.descripcionMensajeGeneral = descripcionMensajeGeneral;
	}
	/**
	 * @return the codigoPlanillaProgramacion
	 */
	public String getCodigoPlanillaProgramacion() {
		return codigoPlanillaProgramacion;
	}
	/**
	 * @param codigoPlanillaProgramacion the codigoPlanillaProgramacion to set
	 */
	public void setCodigoPlanillaProgramacion(String codigoPlanillaProgramacion) {
		this.codigoPlanillaProgramacion = codigoPlanillaProgramacion;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the campanhaIngreso
	 */
	public String getCampanhaIngreso() {
		return campanhaIngreso;
	}
	/**
	 * @param campanhaIngreso the campanhaIngreso to set
	 */
	public void setCampanhaIngreso(String campanhaIngreso) {
		this.campanhaIngreso = campanhaIngreso;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}


