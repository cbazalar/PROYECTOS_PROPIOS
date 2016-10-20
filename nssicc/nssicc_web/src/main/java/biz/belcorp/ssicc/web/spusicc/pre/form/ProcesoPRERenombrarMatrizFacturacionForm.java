package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPRERenombrarMatrizFacturacionForm extends BaseProcesoForm implements Serializable{

	private static final long serialVersionUID = -8958072896586572507L;
	
	private String oidPeriodoOrigen;
	private String oidPeriodoDestino;
	private String codigoPeriodoOrigen;
	private String codigoPeriodoDestino;
	
	/**
	 * @return the oidPeriodoOrigen
	 */
	public String getOidPeriodoOrigen() {
		return oidPeriodoOrigen;
	}
	/**
	 * @param oidPeriodoOrigen the oidPeriodoOrigen to set
	 */
	public void setOidPeriodoOrigen(String oidPeriodoOrigen) {
		this.oidPeriodoOrigen = oidPeriodoOrigen;
	}
	/**
	 * @return the oidPeriodoDestino
	 */
	public String getOidPeriodoDestino() {
		return oidPeriodoDestino;
	}
	/**
	 * @param oidPeriodoDestino the oidPeriodoDestino to set
	 */
	public void setOidPeriodoDestino(String oidPeriodoDestino) {
		this.oidPeriodoDestino = oidPeriodoDestino;
	}
	/**
	 * @return the codigoPeriodoOrigen
	 */
	public String getCodigoPeriodoOrigen() {
		return codigoPeriodoOrigen;
	}
	/**
	 * @param codigoPeriodoOrigen the codigoPeriodoOrigen to set
	 */
	public void setCodigoPeriodoOrigen(String codigoPeriodoOrigen) {
		this.codigoPeriodoOrigen = codigoPeriodoOrigen;
	}
	/**
	 * @return the codigoPeriodoDestino
	 */
	public String getCodigoPeriodoDestino() {
		return codigoPeriodoDestino;
	}
	/**
	 * @param codigoPeriodoDestino the codigoPeriodoDestino to set
	 */
	public void setCodigoPeriodoDestino(String codigoPeriodoDestino) {
		this.codigoPeriodoDestino = codigoPeriodoDestino;
	}
}