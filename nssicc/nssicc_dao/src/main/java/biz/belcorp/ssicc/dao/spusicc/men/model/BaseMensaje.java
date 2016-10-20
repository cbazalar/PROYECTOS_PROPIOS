/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.spusicc.men.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseMensaje.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class BaseMensaje extends AuditableBaseObject implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String id;
	private String valor;
	private String tipoDato;
	private String valorTabla;
	private String descripcion;
	private String valorSeleccionado;
	private String indicadorObligatorio;
	private String indicadorModificado;
	private String indicadorTabla;
	private List arrProducto=new ArrayList();//solo se llena si se trata de MAE_PRODU
	
	
	/**
	 * @return the indicadorObligatorio
	 */
	public String getIndicadorObligatorio() {
		return indicadorObligatorio;
	}

	/**
	 * @param indicadorObligatorio the indicadorObligatorio to set
	 */
	public void setIndicadorObligatorio(String indicadorObligatorio) {
		this.indicadorObligatorio = indicadorObligatorio;
	}

	/**
	 * @return the indicadorModificado
	 */
	public String getIndicadorModificado() {
		return indicadorModificado;
	}

	/**
	 * @param indicadorModificado the indicadorModificado to set
	 */
	public void setIndicadorModificado(String indicadorModificado) {
		this.indicadorModificado = indicadorModificado;
	}

	/**
	 * @return the valorSeleccionado
	 */
	public String getValorSeleccionado() {
		return valorSeleccionado;
	}

	/**
	 * @param valorSeleccionado the valorSeleccionado to set
	 */
	public void setValorSeleccionado(String valorSeleccionado) {
		this.valorSeleccionado = valorSeleccionado;
	}

	/**
	 * @return the arrProducto
	 */
	public List getArrProducto() {
		return arrProducto;
	}

	/**
	 * @param arrProducto the arrProducto to set
	 */
	public void setArrProducto(List arrProducto) {
		this.arrProducto = arrProducto;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tipoDato
	 */
	public String getTipoDato() {
		return tipoDato;
	}

	/**
	 * @param tipoDato the tipoDato to set
	 */
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/**
	 * @return the valorTabla
	 */
	public String getValorTabla() {
		return valorTabla;
	}

	/**
	 * @param valorTabla the valorTabla to set
	 */
	public void setValorTabla(String valorTabla) {
		this.valorTabla = valorTabla;
	}

	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		 return new ToStringBuilder(this).
         append("id", this.id).
         append("valor", this.valor).
         append("tipodato", this.tipoDato).
         append("valorTabla", this.valorTabla).
         append("desc", this.descripcion).
         append("listMap",this.arrProducto).
         toString();		 
	}
		
	/**
	 * @return the indicadorTabla
	 */
	public String getIndicadorTabla() {
		return indicadorTabla;
	}

	/**
	 * @param indicadorTabla the indicadorTabla to set
	 */
	public void setIndicadorTabla(String indicadorTabla) {
		this.indicadorTabla = indicadorTabla;
	}
	
	
		
}
