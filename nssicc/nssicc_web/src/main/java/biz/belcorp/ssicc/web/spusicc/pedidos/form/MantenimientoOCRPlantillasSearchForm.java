package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRPlantillasSearchForm extends BaseSearchForm
		implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574688881702387877L;
	private String codigoPais;
	private String codigoPlantilla;
	private String grupoProceso;
	private String codigoRegion;
	private String tipoSolicitud;
	private String exclusionSolicitud;
	private String flagBorrado;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	public String getGrupoProceso() {
		return grupoProceso;
	}


	public void setGrupoProceso(String grupoProceso) {
		this.grupoProceso = grupoProceso;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getExclusionSolicitud() {
		return exclusionSolicitud;
	}

	public void setExclusionSolicitud(String exclusionSolicitud) {
		this.exclusionSolicitud = exclusionSolicitud;
	}

	public String getFlagBorrado() {
		return flagBorrado;
	}

	public void setFlagBorrado(String flagBorrado) {
		this.flagBorrado = flagBorrado;
	}

}
