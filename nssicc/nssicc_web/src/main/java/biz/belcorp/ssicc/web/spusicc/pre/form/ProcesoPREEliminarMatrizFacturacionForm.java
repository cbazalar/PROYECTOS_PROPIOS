package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPREEliminarMatrizFacturacionForm extends BaseProcesoForm implements Serializable{

	private static final long serialVersionUID = -8958072896586572507L;
	
	private String oidPeriodo;
	private String codigoPeriodo;
	private String codigoCatalogo;
	
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
	/**
	 * @return the codigoCatalogo
	 */
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	/**
	 * @param codigoCatalogo the codigoCatalogo to set
	 */
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
}