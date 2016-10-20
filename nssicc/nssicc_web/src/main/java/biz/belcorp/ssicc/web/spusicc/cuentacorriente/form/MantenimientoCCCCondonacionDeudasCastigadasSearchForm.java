package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCCondonacionDeudasCastigadasSearchForm extends BaseSearchForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6690814960314749391L;
	private String codigoPais;
	private String codigoConsultora;		
	private String cedulaIdentidad;
	private String tipoBusqueda;
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	public String getCedulaIdentidad() {
		return cedulaIdentidad;
	}
	public void setCedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	
	

}
