package biz.belcorp.ssicc.web.spusicc.fdv.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFDVClusterizacionSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codigoPais;
	
	protected String nombreProceso;
	
	protected String fechaRegistro;
	
	protected Date fechaRegistroDate;
	
	public MantenimientoFDVClusterizacionSearchForm() {}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	/**
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
     */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaRegistroDate() {
		return fechaRegistroDate;
	}

	public void setFechaRegistroDate(Date fechaRegistroDate) {
		this.fechaRegistroDate = fechaRegistroDate;
	}
}
