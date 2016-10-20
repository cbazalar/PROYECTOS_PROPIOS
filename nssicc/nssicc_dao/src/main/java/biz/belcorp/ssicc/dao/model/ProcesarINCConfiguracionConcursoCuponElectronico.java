package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

public class ProcesarINCConfiguracionConcursoCuponElectronico implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoConcurso;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String cuponInicial;
	private String ultimoCupon;
	private Integer estado;
	private String descripcionConcurso;
	
	private String numeroConcurso;
	private String codigoPais;
	private String codigoUsuario;
	private Integer indicadorActivo;
	private String flagExiste;
	
	public ProcesarINCConfiguracionConcursoCuponElectronico() {}

	/**
	 * @return the codigoConcurso
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso the codigoConcurso to set
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the cuponInicial
	 */
	public String getCuponInicial() {
		return cuponInicial;
	}

	/**
	 * @param cuponInicial the cuponInicial to set
	 */
	public void setCuponInicial(String cuponInicial) {
		this.cuponInicial = cuponInicial;
	}

	/**
	 * @return the ultimoCupon
	 */
	public String getUltimoCupon() {
		return ultimoCupon;
	}

	/**
	 * @param ultimoCupon the ultimoCupon to set
	 */
	public void setUltimoCupon(String ultimoCupon) {
		this.ultimoCupon = ultimoCupon;
	}

	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the descripcionConcurso
	 */
	public String getDescripcionConcurso() {
		return descripcionConcurso;
	}

	/**
	 * @param descripcionConcurso the descripcionConcurso to set
	 */
	public void setDescripcionConcurso(String descripcionConcurso) {
		this.descripcionConcurso = descripcionConcurso;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
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
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	/**
	 * @return the indicadorActivo
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the flagExiste
	 */
	public String getFlagExiste() {
		return flagExiste;
	}

	/**
	 * @param flagExiste the flagExiste to set
	 */
	public void setFlagExiste(String flagExiste) {
		this.flagExiste = flagExiste;
	}
	
}