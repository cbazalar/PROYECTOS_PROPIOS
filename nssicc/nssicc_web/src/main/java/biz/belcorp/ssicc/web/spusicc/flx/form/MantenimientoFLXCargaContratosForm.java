package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXCargaContratosForm extends BaseSearchForm implements Serializable {
			 
	private static final long serialVersionUID = 8492896988959869980L;
	private String codigoPais;
	private String campanyaFacturacion;
	private String codigoCliente;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the campanyaFacturacion
	 */
	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}
	/**
	 * @param campanyaFacturacion the campanyaFacturacion to set
	 */
	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
}
