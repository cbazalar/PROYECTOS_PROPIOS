package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class UnidadAdministrativa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3422733318707557389L;
	private String codigoPais;
	private String codigoCD;
	private String codigoLineaArmado;
	private String codigoNivelAgrpOlas;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;

	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoCD() {
		return codigoCD;
	}
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	public String getCodigoNivelAgrpOlas() {
		return codigoNivelAgrpOlas;
	}
	public void setCodigoNivelAgrpOlas(String codigoNivelAgrpOlas) {
		this.codigoNivelAgrpOlas = codigoNivelAgrpOlas;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	
}
