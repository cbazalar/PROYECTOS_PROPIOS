package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOperacionCDRUsuarioForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;	
	private String operacion;
	private String tipo;
	private String[] operaciones;
	private String[] tipos;
	
	private UploadedFile clienteFile;
	private String[] clienteList;
	private String codigosErradosFile;
	private int errados;
	private String indicadorClientes; 

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion	 
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the operaciones
	 */
	public String[] getOperaciones() {
		return operaciones;
	}
	/**
	 * @param operaciones the operaciones to set
	 * @struts.validator type="required"
	 */
	public void setOperaciones(String[] operaciones) {
		this.operaciones = operaciones;
	}
	/**
	 * @return the tipos
	 */
	public String[] getTipos() {
		return tipos;
	}
	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(String[] tipos) {
		this.tipos = tipos;
	}
	/**
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	/**
	 * @return the errados
	 */
	public int getErrados() {
		return errados;
	}
	/**
	 * @param errados the errados to set
	 */
	public void setErrados(int errados) {
		this.errados = errados;
	}
	/**
	 * @return the indicadorClientes
	 */
	public String getIndicadorClientes() {
		return indicadorClientes;
	}
	/**
	 * @param indicadorClientes the indicadorClientes to set
	 */
	public void setIndicadorClientes(String indicadorClientes) {
		this.indicadorClientes = indicadorClientes;
	}
	/**
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}
	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}
}
