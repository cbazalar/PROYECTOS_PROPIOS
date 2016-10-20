package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAEExcencionSobreFleteSearchForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 176408538953390483L;
	private String codPais;
	private String codTipoCliente;
	private String codSubTipoCliente;
	private String codTipoClasificacion;
	private String codClasificacion;
	private String indicadorExcepcionFlete;
	

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais the codPais to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getCodTipoCliente() {
		return codTipoCliente;
	}
	public void setCodTipoCliente(String codTipoCliente) {
		this.codTipoCliente = codTipoCliente;
	}
	public String getCodSubTipoCliente() {
		return codSubTipoCliente;
	}
	public void setCodSubTipoCliente(String codSubTipoCliente) {
		this.codSubTipoCliente = codSubTipoCliente;
	}
	public String getCodTipoClasificacion() {
		return codTipoClasificacion;
	}
	public void setCodTipoClasificacion(String codTipoClasificacion) {
		this.codTipoClasificacion = codTipoClasificacion;
	}
	public String getCodClasificacion() {
		return codClasificacion;
	}
	public void setCodClasificacion(String codClasificacion) {
		this.codClasificacion = codClasificacion;
	}
	/**
	 * @return the indicadorExcepcionFlete
	 */
	public String getIndicadorExcepcionFlete() {
		return indicadorExcepcionFlete;
	}
	/**
	 * @param indicadorExcepcionFlete the indicadorExcepcionFlete to set
	 */
	public void setIndicadorExcepcionFlete(String indicadorExcepcionFlete) {
		this.indicadorExcepcionFlete = indicadorExcepcionFlete;
	}
}