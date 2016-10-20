package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCReemplazoPremioBolsaFaltantesForm extends BaseProcesoForm  implements Serializable{
	
	private static final long serialVersionUID = 454171191952455035L;
	
	private String numeroConcurso;
	private String codigoVentaPremio;
	private String oidReemplazo;
	
	private String numeroUnidades;
	private String precio;
	
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
	 * @return the codigoVentaPremio
	 */
	public String getCodigoVentaPremio() {
		return codigoVentaPremio;
	}
	
	/**
	 * @param codigoVentaPremio the codigoVentaPremio to set
	 */
	public void setCodigoVentaPremio(String codigoVentaPremio) {
		this.codigoVentaPremio = codigoVentaPremio;
	}
	
	/**
	 * @return the oidReemplazo
	 */
	public String getOidReemplazo() {
		return oidReemplazo;
	}
	
	/**
	 * @param oidReemplazo the oidReemplazo to set
	 */
	public void setOidReemplazo(String oidReemplazo) {
		this.oidReemplazo = oidReemplazo;
	}

	/**
	 * @return the numeroUnidades
	 */
	public String getNumeroUnidades() {
		return numeroUnidades;
	}

	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
}