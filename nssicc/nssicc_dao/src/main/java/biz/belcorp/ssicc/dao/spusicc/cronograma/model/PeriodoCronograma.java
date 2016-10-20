package biz.belcorp.ssicc.dao.spusicc.cronograma.model;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="PeriodoCronograma.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * 
 */
public class PeriodoCronograma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oid;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoPeriodo;
	private String fechaInicio;
	private String fechaFin;
	private String nombrePeriodo;
	private String indicadorPeriodoCorto;
	private String indicadorPeriodoCruce;
	private String duracion;
	private String usuario;
	
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso the codigoAcceso to set
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the nombrePeriodo
	 */
	public String getNombrePeriodo() {
		return nombrePeriodo;
	}
	/**
	 * @param nombrePeriodo the nombrePeriodo to set
	 */
	public void setNombrePeriodo(String nombrePeriodo) {
		this.nombrePeriodo = nombrePeriodo;
	}
	/**
	 * @return the indicadorPeriodoCorto
	 */
	public String getIndicadorPeriodoCorto() {
		return indicadorPeriodoCorto;
	}
	/**
	 * @param indicadorPeriodoCorto the indicadorPeriodoCorto to set
	 */
	public void setIndicadorPeriodoCorto(String indicadorPeriodoCorto) {
		this.indicadorPeriodoCorto = indicadorPeriodoCorto;
	}
	/**
	 * @return the indicadorPeriodoCruce
	 */
	public String getIndicadorPeriodoCruce() {
		return indicadorPeriodoCruce;
	}
	/**
	 * @param indicadorPeriodoCruce the indicadorPeriodoCruce to set
	 */
	public void setIndicadorPeriodoCruce(String indicadorPeriodoCruce) {
		this.indicadorPeriodoCruce = indicadorPeriodoCruce;
	}
	/**
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	
		
}
