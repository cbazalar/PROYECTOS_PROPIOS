package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 *
 */
public class ConsultaFacturasCajas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 674311481184006564L;
	private String codigoPais;
	private String numeroEtiqueta;
	private String numeroUnicoCaja;

	private String numeroConsolidado;
	private String numeroCaja;
	private String tipoCaja;
	private String numeroOla;
	private String numeroSecuencia;
	private String fechaFactura;
	private String consultora;
	private String chequeo;
	private String regionZona;

	/**
	 * @return numeroUnicoCaja
	 */
	public String getNumeroUnicoCaja() {
		return numeroUnicoCaja;
	}

	/**
	 * @param numeroUnicoCaja
	 */
	public void setNumeroUnicoCaja(String numeroUnicoCaja) {
		this.numeroUnicoCaja = numeroUnicoCaja;
	}
	
	/**
	 * @return regionZona
	 */
	public String getRegionZona() {
		return regionZona;
	}

	/**
	 * @param regionZona
	 */
	public void setRegionZona(String regionZona) {
		this.regionZona = regionZona;
	}

	/**
	 * @return fechaFactura
	 */
	public String getFechaFactura() {
		return fechaFactura;
	}

	/**
	 * @param fechaFactura
	 */
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	/**
	 * @return consultora
	 */
	public String getConsultora() {
		return consultora;
	}

	/**
	 * @param consultora
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	/**
	 * @return chequeo
	 */
	public String getChequeo() {
		return chequeo;
	}

	/**
	 * @param chequeo
	 */
	public void setChequeo(String chequeo) {
		this.chequeo = chequeo;
	}

	/**
	 * @return numeroEtiqueta
	 */
	public String getNumeroEtiqueta() {
		return numeroEtiqueta;
	}

	/**
	 * @param numeroEtiqueta
	 */
	public void setNumeroEtiqueta(String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}

	/**
	 * @return numeroConsolidado
	 */
	public String getNumeroConsolidado() {
		return numeroConsolidado;
	}

	/**
	 * @param numeroConsolidado
	 */
	public void setNumeroConsolidado(String numeroConsolidado) {
		this.numeroConsolidado = numeroConsolidado;
	}

	/** 
	 * @return numeroCaja
	 */
	public String getNumeroCaja() {
		return numeroCaja;
	}

	/**
	 * @param numeroCaja
	 */
	public void setNumeroCaja(String numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	/**
	 * @return tipoCaja
	 */
	public String getTipoCaja() {
		return tipoCaja;
	}

	/**
	 * @param tipoCaja
	 */
	public void setTipoCaja(String tipoCaja) {
		this.tipoCaja = tipoCaja;
	}

	/**
	 * @return numeroOla
	 */
	public String getNumeroOla() {
		return numeroOla;
	}

	/**
	 * @param numeroOla
	 */
	public void setNumeroOla(String numeroOla) {
		this.numeroOla = numeroOla;
	}

	/**
	 * @return numeroSecuencia
	 */ 
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	/**
	 * @param numeroSecuencia
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
