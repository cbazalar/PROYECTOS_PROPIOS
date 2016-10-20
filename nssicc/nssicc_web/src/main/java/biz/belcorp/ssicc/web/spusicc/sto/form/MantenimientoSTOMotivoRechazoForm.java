package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOMotivoRechazoForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoDocumento;
	private String descripcionTipoDocumento;
	private String codigoMotRechazo;
	private String descripcionMotRechazo;
	private String codigoModulo;
	private String indRechazo;
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getCodigoMotRechazo() {
		return codigoMotRechazo;
	}
	
	public void setCodigoMotRechazo(String codigoMotRechazo) {
		this.codigoMotRechazo = codigoMotRechazo;
	}
	
	public String getDescripcionMotRechazo() {
		return descripcionMotRechazo;
	}
	
	public void setDescripcionMotRechazo(String descripcionMotRechazo) {
		this.descripcionMotRechazo = descripcionMotRechazo;
	}
	
	public String getCodigoModulo() {
		return codigoModulo;
	}
	
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	
	public String getIndRechazo() {
		return indRechazo;
	}
	
	public void setIndRechazo(String indRechazo) {
		this.indRechazo = indRechazo;
	}

	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}

	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}
}
