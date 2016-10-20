package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BaseMantenimientoSTOGestionForm extends BaseSearchForm{

	
	private static final long serialVersionUID = 6649455647815986372L;
	
	private String codigoPais;
	private String tipoDocumento;
	private String lote;
	private String numeroDocumento;
	private String validacion;
	private String detalle;
	private String documento;
	private String codigoCliente;
	private String desValidacion;
	private String desValidacionLarga;
	private String mensaje;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDesValidacion() {
		return desValidacion;
	}

	public void setDesValidacion(String desValidacion) {
		this.desValidacion = desValidacion;
	}

	public String getDesValidacionLarga() {
		return desValidacionLarga;
	}

	public void setDesValidacionLarga(String desValidacionLarga) {
		this.desValidacionLarga = desValidacionLarga;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
