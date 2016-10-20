package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPEDAsignarCodigoVentaForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8958072896586572507L;
	private String oidCatalogo;
	private String oidPeriodo;
	private String codigoPeriodo;
	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}
	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}
	/**
	 * @return the oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}
	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
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
	
	

}
