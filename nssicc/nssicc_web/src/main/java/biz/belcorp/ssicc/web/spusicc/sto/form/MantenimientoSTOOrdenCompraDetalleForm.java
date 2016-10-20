package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

public class MantenimientoSTOOrdenCompraDetalleForm extends BaseMantenimientoSTOGestionForm
			implements Serializable{

	
	private static final long serialVersionUID = 63191003906176044L;
	
	private String codigoPais;
	private String codPeriodo;
	private String codCliente;
	private String codVenta;
	private String tipoPosicion;
	private Integer valUniDemandadas;
	private String estadoProceso;
	private String numLote;
	private String numDocumento;
	private String codMotivoRechazo;
	private String numSecuencia;
	private String detalle;
	
	
	public String getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodMotivoRechazo() {
		return codMotivoRechazo;
	}
	
	public void setCodMotivoRechazo(String codMotivoRechazo) {
		this.codMotivoRechazo = codMotivoRechazo;
	}
	
	public String getCodPeriodo() {
		return codPeriodo;
	}
	
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	
	public String getCodVenta() {
		return codVenta;
	}
	
	public void setCodVenta(String codVenta) {
		this.codVenta = codVenta;
	}
	
	public String getEstadoProceso() {
		return estadoProceso;
	}
	
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public String getNumLote() {
		return numLote;
	}
	
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	
	public String getTipoPosicion() {
		return tipoPosicion;
	}
	
	public void setTipoPosicion(String tipoPosicion) {
		this.tipoPosicion = tipoPosicion;
	}
	
	public Integer getValUniDemandadas() {
		return valUniDemandadas;
	}
	
	public void setValUniDemandadas(Integer valUniDemandadas) {
		this.valUniDemandadas = valUniDemandadas;
	}
	
	public String getNumSecuencia() {
		return numSecuencia;
	}
	
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


}
