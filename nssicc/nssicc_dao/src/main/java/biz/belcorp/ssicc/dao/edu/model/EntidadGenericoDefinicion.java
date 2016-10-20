package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.model.LabelValue;


public class EntidadGenericoDefinicion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private String longitudCodigo;
	private String codigoEntidadTipo;
	private LabelValue[] listaTipos;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLongitudCodigo() {
		return longitudCodigo;
	}
	public void setLongitudCodigo(String longitudCodigo) {
		this.longitudCodigo = longitudCodigo;
	}
	public LabelValue[] getListaTipos() {
		return listaTipos;
	}
	public void setListaTipos(LabelValue[] listaTipos) {
		this.listaTipos = listaTipos;
	}
	public String getCodigoEntidadTipo() {
		return codigoEntidadTipo;
	}
	public void setCodigoEntidadTipo(String codigoEntidadTipo) {
		this.codigoEntidadTipo = codigoEntidadTipo;
	}
	
}
