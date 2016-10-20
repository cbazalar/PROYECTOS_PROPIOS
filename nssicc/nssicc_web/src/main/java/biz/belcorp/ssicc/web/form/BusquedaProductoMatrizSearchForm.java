/**
 * 
 */
package biz.belcorp.ssicc.web.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author peextrramirez - Rosalvina Ramirez
 * 
 * <p>
 * <a href="BusquedaProductoMatrizSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @struts.form name="busquedaProductoMatrizSearchForm"

 *
 */
public class BusquedaProductoMatrizSearchForm extends BaseSearchForm implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoSap;
	
    private String codigoPais;
	
    private String descripcionCorta;   
    
    private String codigoVenta;
    
    private String codigoPeriodo;
    
    /**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public void inicializar()    {	
        this.codigoSap="";                
        this.codigoPais="";       
        this.descripcionCorta="";
        this.codigoPeriodo="";
        this.codigoVenta="";
    }

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSap.
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap The codigoSap to set.
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return Returns the descripcionCorta.
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta The descripcionCorta to set.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
}
