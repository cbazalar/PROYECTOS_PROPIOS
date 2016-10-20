package biz.belcorp.ssicc.web.spusicc.ventas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BusquedaZonaPOPUPSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="jpescoran@gmail.com">Juan Pablo Pescoran</a>
 *
 */
public class MantenimientoVENGrupoZonaForm extends BaseEditForm {


	private static final long serialVersionUID = 2952224539984507241L;
	private String codigo; /**Codigo del Grupo Zonal : Codigo de Grupo*/
	private String descripcion; /**Descripcion del Grupo Zonal : Descripcion de Grupo*/
	private String codigoAgrupado;  /**Agrupado Grupo Zona*/
	private String descripcionAgrupado;
	
	
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return Returns the codigoAgrupado.
	 */
	public String getCodigoAgrupado() {
		return codigoAgrupado;
	}

	public void setCodigoAgrupado(String codigoAgrupado) {
		this.codigoAgrupado = codigoAgrupado;
	}
	/**
	 * @return Returns the descripcionAgrupado.
	 */
	public String getDescripcionAgrupado() {
		return descripcionAgrupado;
	}

	public void setDescripcionAgrupado(String descripcionAgrupado) {
		this.descripcionAgrupado = descripcionAgrupado;
	}
	
}
