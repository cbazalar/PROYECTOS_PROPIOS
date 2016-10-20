package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoFLXConsultoraProcesoMasivoForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = -4159977510415518452L;
	private String tipoProceso;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @param tipoProceso the tipoProceso to set
	 * 
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
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
}