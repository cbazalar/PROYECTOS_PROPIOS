package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCUPProductoPrimerPedidoForm extends BaseEditForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descripcionPais;
	private String codigoValida;
	private String codigoPrograma;
	private String codigoPeriodo;
	private String codigoVenta;
	private String valorUnitario;
	private String descripcionProducto;
	private String codigoProducto;

	private String estado;

	/**
	 * @return the descripcionPais
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}

	/**
	 * @param descripcionPais
	 *            the descripcionPais to set
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @return the codigoValida
	 */
	public String getCodigoValida() {
		return codigoValida;
	}

	/**
	 * @param codigoValida
	 *            the codigoValida to set
	 */
	public void setCodigoValida(String codigoValida) {
		this.codigoValida = codigoValida;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 *            the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 *            the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the valorUnitario
	 */
	public String getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario
	 *            the valorUnitario to set
	 */
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto
	 *            the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}