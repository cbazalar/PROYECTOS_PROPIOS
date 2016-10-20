package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMENPatronMensajeSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129712698075110873L;
	private String codigoPais;
	private String campanhaProceso;
	private String descripcion;
	private String codigoPatron;
	private String codigoDocumento;
	private String campanhaActual;
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
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}
	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
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
	 * @return the codigoPatron
	 */
	public String getCodigoPatron() {
		return codigoPatron;
	}
	/**
	 * @param codigoPatron the codigoPatron to set
	 */
	public void setCodigoPatron(String codigoPatron) {
		this.codigoPatron = codigoPatron;
	}
	/**
	 * @return the codigoDocumento
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	/**
	 * @param codigoDocumento the codigoDocumento to set
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	/**
	 * @return the campanhaActual
	 */
	public String getCampanhaActual() {
		return campanhaActual;
	}
	/**
	 * @param campanhaActual the campanhaActual to set
	 */
	public void setCampanhaActual(String campanhaActual) {
		this.campanhaActual = campanhaActual;
	}
	
	

}
