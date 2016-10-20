package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class VersionProducto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6308857670597611438L;
	private String codigoPais;
	private String codigoMapaZona;
	private String codigoVersion;
	
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
	 * @return the codigoMapaZona
	 */
	public String getCodigoMapaZona() {
		return codigoMapaZona;
	}

	/**
	 * @param codigoMapaZona the codigoMapaZona to set
	 */
	public void setCodigoMapaZona(String codigoMapaZona) {
		this.codigoMapaZona = codigoMapaZona;
	}

	/**
	 * @return the codigoVersion
	 */
	public String getCodigoVersion() {
		return codigoVersion;
	}

	/**
	 * @param codigoVersion the codigoVersion to set
	 */
	public void setCodigoVersion(String codigoVersion) {
		this.codigoVersion = codigoVersion;
	}
	
}
