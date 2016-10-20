package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class EmitirAlarma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5476912281340708767L;
	private String codigoPais;
	private String codigoMapaZona;
	
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

	
}
