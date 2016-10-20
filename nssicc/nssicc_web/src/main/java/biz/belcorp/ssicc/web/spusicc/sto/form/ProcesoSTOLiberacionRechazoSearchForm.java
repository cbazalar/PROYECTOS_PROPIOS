package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoSTOLiberacionRechazoSearchForm extends BaseSearchForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    private String fecha;
    private Date fechaD;
    private String usuario;
    private String codigoDocumento;
    private String accion;
    	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoDocumento.
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}

	/**
	 * @param codigoDocumento The codigoDocumento to set. 
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the fechaD
	 */
	public Date getFechaD() {
		return fechaD;
	}

	/**
	 * @param fechaD the fechaD to set
	 */
	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}
}