/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */

public class SistemaForm extends BaseEditForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6806720004796199798L;
	private String codigo;	
	private String codigoPais;
	private String descripcion;
	private String estado;
	
	/**
	 * @return Returns the codigo.
	 */
	@Size(max = 10)
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 * 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the descripcion.
	 */
	@Size(max = 300)
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 * 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
