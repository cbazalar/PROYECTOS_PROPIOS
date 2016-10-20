package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSTOCuponSearchForm extends BaseSearchForm  implements Serializable {
			 
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCliente;
	private String fechaRegistro;
	private Date fechaRegistroD;
	private String numeroDocumentoIdentidad;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}
	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}
	/**
	 * @return the fechaRegistroD
	 */
	public Date getFechaRegistroD() {
		return fechaRegistroD;
	}
	/**
	 * @param fechaRegistroD the fechaRegistroD to set
	 */
	public void setFechaRegistroD(Date fechaRegistroD) {
		this.fechaRegistroD = fechaRegistroD;
	}
}