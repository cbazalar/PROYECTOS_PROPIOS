package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoClienteForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String codigoPais;
	private String nombreEntidad;
	private String codigo;
	private String descripcion;
	private String indicadorEvaluarStatus;
	private String indicadorEmpleado;
	private String indicadorEstado;
			
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
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
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the indicadorEvaluarStatus
	 */
	public String getIndicadorEvaluarStatus() {
		return indicadorEvaluarStatus;
	}
	/**
	 * @param indicadorEvaluarStatus the indicadorEvaluarStatus to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorEvaluarStatus(String indicadorEvaluarStatus) {
		this.indicadorEvaluarStatus = indicadorEvaluarStatus;
	}
	/**
	 * @return the indicadorEmpleado
	 */
	public String getIndicadorEmpleado() {
		return indicadorEmpleado;
	}
	/**
	 * @param indicadorEmpleado the indicadorEmpleado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorEmpleado(String indicadorEmpleado) {
		this.indicadorEmpleado = indicadorEmpleado;
	}
	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
}