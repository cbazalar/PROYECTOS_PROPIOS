/*
 * Created on 30-nov-2005
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
 * <a href="InterfazREUDocumentosAnulados.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazREUDocumentosAnulados implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8407428873485867141L;
	public String codigoPais;
	public Long numeroBoleta;
    public String codigoPeriodo;
    public String codigoCliente;
    public Timestamp fechaAnulacion;
	
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
	 * @return Returns the numeroBoleta.
	 */
	public Long getNumeroBoleta() {
		return numeroBoleta;
	}
	/**
	 * @param numeroBoleta The numeroBoleta to set.
	 */
	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }
    public Timestamp getFechaAnulacion() {
        return fechaAnulacion;
    }
    public void setFechaAnulacion(Timestamp fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }
}
