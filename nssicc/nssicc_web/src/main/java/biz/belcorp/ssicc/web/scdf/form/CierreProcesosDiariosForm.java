package biz.belcorp.ssicc.web.scdf.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class CierreProcesosDiariosForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   /**
     * Holds value of property codigoPais.
     */
    protected String codigoPais;

	private String periodo ;
	private String fechaFact ;
    
    public String getFechaFact() {
		return fechaFact;
	}

	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

    /**
     * Default constructor
     */
    public CierreProcesosDiariosForm() {
        super();
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
     * @struts.validator type="required"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

}
