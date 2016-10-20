/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

/**
 * @author sbuchelli
 *
 */
public class Bonos implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoConcepto;
	private String descripcionConcepto;
	private String campanhaInicial;
	private String campanhaFinal;
	//detalle
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	private String monto;
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
	 * @return the codigoConcepto
	 */
	public String getCodigoConcepto() {
		return codigoConcepto;
	}
	/**
	 * @param codigoConcepto the codigoConcepto to set
	 */
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}
	/**
	 * @return the descripcionConcepto
	 */
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}
	/**
	 * @param descripcionConcepto the descripcionConcepto to set
	 */
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	/**
	 * @return the campanhaInicial
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}
	/**
	 * @param campanhaInicial the campanhaInicial to set
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}
	/**
	 * @return the campanhaFinal
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}
	/**
	 * @param campanhaFinal the campanhaFinal to set
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

}	
	
