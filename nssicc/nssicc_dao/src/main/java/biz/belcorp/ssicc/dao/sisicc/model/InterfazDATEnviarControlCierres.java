/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazDATEnviarControlCierres.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius</a>
 * 
 */
public class InterfazDATEnviarControlCierres implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3708081977267276392L;
	private String codigoPeriodo;
	private String codigoZona;
	private String codigoRegion;
	private String resultado;
	private String flagImpresion;
	private String flagStatusFacturacion; 
	
	public String getCodigoPeriodo() {
        return codigoPeriodo;
    }
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }
	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the flagImpresion.
	 */
	public String getFlagImpresion() {
		return flagImpresion;
	}
	/**
	 * @param flagImpresion The flagImpresion to set.
	 */
	public void setFlagImpresion(String flagImpresion) {
		this.flagImpresion = flagImpresion;
	}
	/**
	 * @return Returns the flagStatusFacturacion.
	 */
	public String getFlagStatusFacturacion() {
		return flagStatusFacturacion;
	}
	/**
	 * @param flagStatusFacturacion The flagStatusFacturacion to set.
	 */
	public void setFlagStatusFacturacion(String flagStatusFacturacion) {
		this.flagStatusFacturacion = flagStatusFacturacion;
	}
	/**
	 * @return Returns the resultado.
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado The resultado to set.
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
    
    
    
    }
