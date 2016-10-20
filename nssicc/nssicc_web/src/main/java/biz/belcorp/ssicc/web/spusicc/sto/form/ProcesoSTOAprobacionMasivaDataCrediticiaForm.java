package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOAprobacionMasivaDataCrediticiaForm extends BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = -7150626738130466769L;
	
	private String codigoPais;
	private boolean flagProcesar;	
	private UploadedFile fileCliente;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the flagProcesar
	 */
	public boolean isFlagProcesar() {
		return flagProcesar;
	}
	/**
	 * @param flagProcesar the flagProcesar to set
	 */
	public void setFlagProcesar(boolean flagProcesar) {
		this.flagProcesar = flagProcesar;
	}
	/**
	 * @return the fileCliente
	 */
	public UploadedFile getFileCliente() {
		return fileCliente;
	}
	/**
	 * @param fileCliente the fileCliente to set
	 */
	public void setFileCliente(UploadedFile fileCliente) {
		this.fileCliente = fileCliente;
	}
	
	

}
