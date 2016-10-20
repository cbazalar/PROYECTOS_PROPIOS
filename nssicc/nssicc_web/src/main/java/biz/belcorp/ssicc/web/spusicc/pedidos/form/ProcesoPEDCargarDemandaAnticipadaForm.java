/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoPEDCargarDemandaAnticipadaForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670716405898989205L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private UploadedFile clienteFile;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
