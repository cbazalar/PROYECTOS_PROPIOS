package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRPlantillasBusquedaForm extends BaseSearchForm
		implements Serializable {


	private static final long serialVersionUID = -3208357567317506026L;
	
	
	private String codigoPais;
	private String oidPais;
	private String codigoPlantilla;
	private String descripcionPlantilla;
	

	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getOidPais() {
		return oidPais;
	}
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}
	public String getDescripcionPlantilla() {
		return descripcionPlantilla;
	}
	

	public void setDescripcionPlantilla(String descripcionPlantilla) {
		this.descripcionPlantilla = descripcionPlantilla;
	}


}
