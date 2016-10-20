package biz.belcorp.ssicc.web.spusicc.emprendedoras.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEMPVincularNuevasReactivadasCierreRegionForm extends BaseProcesoForm implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
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
}