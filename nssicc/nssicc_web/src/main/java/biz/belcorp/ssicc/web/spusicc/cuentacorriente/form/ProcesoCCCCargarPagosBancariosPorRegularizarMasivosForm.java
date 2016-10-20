/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoCCCCargarPagosBancariosPorRegularizarMasivosForm extends BaseCargaArchivoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8987935514323060346L;	
	
	private String codigoPais;
	private String codigoSociedad;	
	private String codigoCuentaCorrienteBancaria;	
	private String codigoModulo;	
	private String codigoProceso;
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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the codigoCuentaCorrienteBancaria
	 */
	public String getCodigoCuentaCorrienteBancaria() {
		return codigoCuentaCorrienteBancaria;
	}
	/**
	 * @param codigoCuentaCorrienteBancaria the codigoCuentaCorrienteBancaria to set
	 */
	public void setCodigoCuentaCorrienteBancaria(
			String codigoCuentaCorrienteBancaria) {
		this.codigoCuentaCorrienteBancaria = codigoCuentaCorrienteBancaria;
	}
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
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	
	
}
