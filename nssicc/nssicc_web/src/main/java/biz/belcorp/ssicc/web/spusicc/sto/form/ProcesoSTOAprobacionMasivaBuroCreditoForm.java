package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 * 
 */
public class ProcesoSTOAprobacionMasivaBuroCreditoForm extends BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private boolean flagProcesar;
	
	private UploadedFile numeroDocIdentidad;

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

	public UploadedFile getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}

	public void setNumeroDocIdentidad(UploadedFile numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}

}
