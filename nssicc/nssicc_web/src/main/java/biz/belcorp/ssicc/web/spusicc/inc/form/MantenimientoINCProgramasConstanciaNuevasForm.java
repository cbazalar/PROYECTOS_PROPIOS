package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCProgramasConstanciaNuevasForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoProgramaConstancia;
	private String periodosExigidos;
	private String pedidosExigidos;
	private String puntosAbonar;
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoProgramaConstancia
	 */
	public String getCodigoProgramaConstancia() {
		return codigoProgramaConstancia;
	}

	/**
	 * @param codigoProgramaConstancia the codigoProgramaConstancia to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoProgramaConstancia(String codigoProgramaConstancia) {
		this.codigoProgramaConstancia = codigoProgramaConstancia;
	}

	/**
	 * @return the periodosExigidos
	 */
	public String getPeriodosExigidos() {
		return periodosExigidos;
	}

	/**
	 * @param periodosExigidos the periodosExigidos to set
	 */
	public void setPeriodosExigidos(String periodosExigidos) {
		this.periodosExigidos = periodosExigidos;
	}

	/**
	 * @return the pedidosExigidos
	 */
	public String getPedidosExigidos() {
		return pedidosExigidos;
	}

	/**
	 * @param pedidosExigidos the pedidosExigidos to set
	 */
	public void setPedidosExigidos(String pedidosExigidos) {
		this.pedidosExigidos = pedidosExigidos;
	}

	/**
	 * @return the puntosAbonar
	 */
	public String getPuntosAbonar() {
		return puntosAbonar;
	}

	/**
	 * @param puntosAbonar the puntosAbonar to set
	 */
	public void setPuntosAbonar(String puntosAbonar) {
		this.puntosAbonar = puntosAbonar;
	}

	
	
}