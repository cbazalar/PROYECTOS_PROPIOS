package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author David Ramos
 */

public class EstimadoProducto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1881217470567377364L;
	private String codigoPais;
	private String codCentro;
	private String codMarca;
	private String codCanal;
	private String codPeri;
	private String oidPeri;
	private String oidProd;	
	private String codigoSap;
	private String indEstProd;
	private String codSap;
	private String oidSap;
	private String desSap;
	private String flagFuePed;	
	private String flagLinAfp;
	
	
	public String getFlagLinAfp() {
		return flagLinAfp;
	}
	public void setFlagLinAfp(String flagLinAfp) {
		this.flagLinAfp = flagLinAfp;
	}
	public String getOidSap() {
		return oidSap;
	}
	public void setOidSap(String oidSap) {
		this.oidSap = oidSap;
	}
	public String getDesSap() {
		return desSap;
	}
	public void setDesSap(String desSap) {
		this.desSap = desSap;
	}
	public String getFlagFuePed() {
		return flagFuePed;
	}
	public void setFlagFuePed(String flagFuePed) {
		this.flagFuePed = flagFuePed;
	}

	
	
	public String getIndEstProd() {
		return indEstProd;
	}
	public void setIndEstProd(String indEstProd) {
		this.indEstProd = indEstProd;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getCodPeri() {
		return codPeri;
	}
	public void setCodPeri(String codPeri) {
		this.codPeri = codPeri;
	}
	public String getOidPeri() {
		return oidPeri;
	}
	public void setOidPeri(String oidPeri) {
		this.oidPeri = oidPeri;
	}

	public String getOidProd() {
		return oidProd;
	}
	public void setOidProd(String oidProd) {
		this.oidProd = oidProd;
	}
	public String getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
	}
	public String getCodMarca() {
		return codMarca;
	}
	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}
	public String getCodCanal() {
		return codCanal;
	}
	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
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
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}
	public String getCodSap() {
		return codSap;
	}

	
		
	
}