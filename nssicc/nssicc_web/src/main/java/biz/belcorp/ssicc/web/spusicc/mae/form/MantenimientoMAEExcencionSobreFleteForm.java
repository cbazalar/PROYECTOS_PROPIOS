package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEExcencionSobreFleteForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codExenSfle;
	private String codPais;
	private String codClasificacion;
	private String indicadorExcepcionFlete;
	private String codSubTipoCliente;
	private String codTipoCliente;
	private String codTipoClasificacion;

	public String getCodExenSfle() {
		return codExenSfle;
	}
	
	public void setCodExenSfle(String codExenSfle) {
		this.codExenSfle = codExenSfle;
	}
	
	/**
	 * @return the indicadorExcepcionFlete
	 */
	public String getIndicadorExcepcionFlete() {
		return indicadorExcepcionFlete;
	}
	
	/**
	 * @param indicadorExcepcionFlete the indicadorExcepcionFlete to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setIndicadorExcepcionFlete(String indicadorExcepcionFlete) {
		this.indicadorExcepcionFlete = indicadorExcepcionFlete;
	}
	
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
	
	public String getCodClasificacion() {
		return codClasificacion;
	}
	
	public void setCodClasificacion(String codClasificacion) {
		this.codClasificacion = codClasificacion;
	}
	
	public String getCodSubTipoCliente() {
		return codSubTipoCliente;
	}
	
	public void setCodSubTipoCliente(String codSubTipoCliente) {
		this.codSubTipoCliente = codSubTipoCliente;
	}
	
	/**
	 * @return the codTipoCliente
	 */
	public String getCodTipoCliente() {
		return codTipoCliente;
	}
	
	/**
	 * @param codTipoCliente the codTipoCliente to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodTipoCliente(String codTipoCliente) {
		this.codTipoCliente = codTipoCliente;
	}
	
	public String getCodTipoClasificacion() {
		return codTipoClasificacion;
	}
	
	public void setCodTipoClasificacion(String codTipoClasificacion) {
		this.codTipoClasificacion = codTipoClasificacion;
	}	
}