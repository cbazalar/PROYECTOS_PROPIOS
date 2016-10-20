/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazREUPeriodosFacturacion.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazREUPeriodosFacturacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7028059754681628322L;
	private String codigoPais;
	private String codigoPeriodo;
	private Timestamp fechaInicio;
    private Timestamp fechaFin;
   
    
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }
    public Timestamp getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
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
    
    
    
    }
