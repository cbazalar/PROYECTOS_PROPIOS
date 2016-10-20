package biz.belcorp.ssicc.web.spusicc.archi.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoArchiEntidadBorradoAntiguoFechaForm extends BaseEditForm
implements Serializable{
	
	private static final long serialVersionUID = 5567356342240318934L;
	
	private String codigoModulo;
	private String entidad;
	private String fechaAntigua;
	private Integer numeroDias;
	private Integer indicadorActivo;
	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}
	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return the fechaAntigua
	 */
	public String getFechaAntigua() {
		return fechaAntigua;
	}
	/**
	 * @param fechaAntigua the fechaAntigua to set
	 */
	public void setFechaAntigua(String fechaAntigua) {
		this.fechaAntigua = fechaAntigua;
	}
	/**
	 * @return the numeroDias
	 */
	public Integer getNumeroDias() {
		return numeroDias;
	}
	/**
	 * @param numeroDias the numeroDias to set
	 */
	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}
	/**
	 * @return the indicadorActivo
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	
	

}
