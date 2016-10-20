package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMPagoLideresSuspendidaForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 2382275645015966524L;

	private String codigoPais;
	
	private String codigoComisionIngreso;
	
	private String codigoComisionRecu1;
	
	private String codigoComisionRecu2;
	
	private String codigoPeriodo;
	
	
	
	public ReporteCOMPagoLideresSuspendidaForm() {
		this.codigoPeriodo = null;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoComisionIngreso.
	 */
	public String getCodigoComisionIngreso() {
		return codigoComisionIngreso;
	}

	/**
	 * @param codigoComisionIngreso The codigoComisionIngreso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComisionIngreso(String codigoComisionIngreso) {
		this.codigoComisionIngreso = codigoComisionIngreso;
	}

	/**
	 * @return Returns the codigoComisionRecu1.
	 */
	public String getCodigoComisionRecu1() {
		return codigoComisionRecu1;
	}

	/**
	 * @param codigoComisionRecu1 The codigoComisionRecu1 to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComisionRecu1(String codigoComisionRecu1) {
		this.codigoComisionRecu1 = codigoComisionRecu1;
	}

	/**
	 * @return Returns the codigoComisionRecu2.
	 */
	public String getCodigoComisionRecu2() {
		return codigoComisionRecu2;
	}

	/**
	 * @param codigoComisionRecu2 The codigoComisionRecu2 to set.	 
	 */
	public void setCodigoComisionRecu2(String codigoComisionRecu2) {
		this.codigoComisionRecu2 = codigoComisionRecu2;
	}
	

	
}
