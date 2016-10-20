package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteCOMComisionPagoEjecutivasSuspendidasForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = -9041197259075832587L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codigoComision;
	
	private String descripcionComision;

	private String codigoPeriodo;
	
	private String comisionIngresos;
	
	
	/**
	 * @return Returns the comisionIngresos.
	 */
	public String getComisionIngresos() {
		return comisionIngresos;
	}

	/**
	 * @param comisionIngresos The comisionIngresos to set.
	 */
	public void setComisionIngresos(String comisionIngresos) {
		this.comisionIngresos = comisionIngresos;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ReporteCOMComisionPagoEjecutivasSuspendidasForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoPeriodo = null;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the descripcionComision.
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision The descripcionComision to set.
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}


	
}
