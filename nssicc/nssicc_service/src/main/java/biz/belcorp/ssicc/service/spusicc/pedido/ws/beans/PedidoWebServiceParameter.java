package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class PedidoWebServiceParameter implements Serializable {
	
	private static final long serialVersionUID = -6480085629480704141L;
	
	private String accion;
	private Integer oidSicc;
	private String consultora;
	private String periodo;
	
	private PedidoDetalleWebServiceParameter[] detalle;
	private PedidoAlternativosWebServiceParameter[] alternativos;
	
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
	 * @return the oidSicc
	 */
	public Integer getOidSicc() {
		return oidSicc;
	}
	/**
	 * @param oidSicc the oidSicc to set
	 */
	public void setOidSicc(Integer oidSicc) {
		this.oidSicc = oidSicc;
	}
	/**
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}
	/**
	 * @param consultora the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the detalle
	 */
	public PedidoDetalleWebServiceParameter[] getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(PedidoDetalleWebServiceParameter[] detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return the alternativos
	 */
	public PedidoAlternativosWebServiceParameter[] getAlternativos() {
		return alternativos;
	}
	/**
	 * @param alternativos the alternativos to set
	 */
	public void setAlternativos(PedidoAlternativosWebServiceParameter[] alternativos) {
		this.alternativos = alternativos;
	}
}