package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPREValidacionMatrizForm extends BaseEditForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	
	private String oid;
	private String descripcion;
	private String mensaje;
	private String indActividad;

	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

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

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the indActividad
	 */
	public String getIndActividad() {
		return indActividad;
	}

	/**
	 * @param indActividad the indActividad to set
	 */
	public void setIndActividad(String indActividad) {
		this.indActividad = indActividad;
	}
}