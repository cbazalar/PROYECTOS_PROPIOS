/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoPEDNumerosFacturacionSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2692891441276235307L;
	
	
	private String codigoPais;
	
	private String oidTipoDocumento;
	
	private String oidSociedad;

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
	 * @return the oidTipoDocumento
	 */
	public String getOidTipoDocumento() {
		return oidTipoDocumento;
	}

	/**
	 * @param oidTipoDocumento the oidTipoDocumento to set
	 */
	public void setOidTipoDocumento(String oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}

	/**
	 * @return the oidSociedad
	 */
	public String getOidSociedad() {
		return oidSociedad;
	}

	/**
	 * @param oidSociedad the oidSociedad to set
	 */
	public void setOidSociedad(String oidSociedad) {
		this.oidSociedad = oidSociedad;
	}

}
