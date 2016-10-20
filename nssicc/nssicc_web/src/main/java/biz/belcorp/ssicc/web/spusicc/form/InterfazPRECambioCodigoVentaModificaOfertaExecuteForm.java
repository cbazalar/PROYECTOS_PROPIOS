package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazPRECambioCodigoVentaModificaOfertaExecuteForm extends BaseInterfazForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3738167715040433626L;
	private String codigoPais;
	private String codigoPaisExportar;
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
	 * @return the codigoPaisExportar
	 */
	public String getCodigoPaisExportar() {
		return codigoPaisExportar;
	}
	/**
	 * @param codigoPaisExportar the codigoPaisExportar to set
	 */
	public void setCodigoPaisExportar(String codigoPaisExportar) {
		this.codigoPaisExportar = codigoPaisExportar;
	}
	
	
}
