package biz.belcorp.ssicc.service.sisicc.framework.form;

import java.io.Serializable;


/**
 * Form base para Procesos SiSiCC sin Interfaces
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class BaseProcesoForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -316180477294676950L;

	private String codigoPais;
	
	private String codigoProcesoBatch;
	
	private String codigoSistema;
	
	private String mostrarPaginaConsultaBatch = "S";
		
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	public String getMostrarPaginaConsultaBatch() {
		return mostrarPaginaConsultaBatch;
	}

	public void setMostrarPaginaConsultaBatch(String mostrarPaginaConsultaBatch) {
		this.mostrarPaginaConsultaBatch = mostrarPaginaConsultaBatch;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

		
}
