package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCClasificacionParaINCForm extends BaseEditForm implements Serializable {
	
	private static final long serialVersionUID = 4135298393354919449L;
	private Integer secuencial;
	private Integer valorGrupoCliente;
	private String  descripcion;
	private Integer oidDestinatario;
	private long oidPais;
	
	
	/**
	 * @return the secuencial
	 */
	public Integer getSecuencial() {
		return secuencial;
	}
	
	/**
	 * @param secuencial the secuencial to set
	 */
	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}
	
	/**
	 * @return the valorGrupoCliente
	 */
	public Integer getValorGrupoCliente() {
		return valorGrupoCliente;
	}
	
	/**
	 * @param valorGrupoCliente the valorGrupoCliente to set
	 */
	public void setValorGrupoCliente(Integer valorGrupoCliente) {
		this.valorGrupoCliente = valorGrupoCliente;
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
	 * @return the oidDestinatario
	 */
	public Integer getOidDestinatario() {
		return oidDestinatario;
	}
	
	/**
	 * @param oidDestinatario the oidDestinatario to set
	 */
	public void setOidDestinatario(Integer oidDestinatario) {
		this.oidDestinatario = oidDestinatario;
	}
	
	/**
	 * @return the oidPais
	 */
	public long getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(long oidPais) {
		this.oidPais = oidPais;
	}
	
	
	
	
}