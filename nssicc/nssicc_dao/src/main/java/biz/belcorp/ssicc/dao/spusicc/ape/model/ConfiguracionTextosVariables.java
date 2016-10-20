package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author David Ramos
 */

public class ConfiguracionTextosVariables implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395813888023880822L;
	private String confTextVari;
	private String codigoPais;
	private String codigoTipoCliente;
	private String codigoSubTipoCliente;
	private String codigoTipoClasificacion;
	private String codigoClasificacion;
	private String valTextVari;

	public String getConfTextVari() {
		return confTextVari;
	}
	public void setConfTextVari(String confTextVari) {
		this.confTextVari = confTextVari;
	}
	
	public String getValTextVari() {
		return valTextVari;
	}
	public void setValTextVari(String valTextVari) {
		this.valTextVari = valTextVari;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	public String getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}
	public void setCodigoSubTipoCliente(String codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	
}