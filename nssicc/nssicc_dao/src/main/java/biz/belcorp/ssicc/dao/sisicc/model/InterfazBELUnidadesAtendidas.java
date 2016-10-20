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
 * <a href="InterfazBELUnidadesAtendidas.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazBELUnidadesAtendidas implements Serializable{
	
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 6618684540275567871L;
	private String periodo;
	private Long numeroFactura;
    private String codigoVenta;
    private Long unidadesAtendidas;
    
    public String getCodigoVenta() {
        return codigoVenta;
    }
    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }
    public Long getNumeroFactura() {
        return numeroFactura;
    }
    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public Long getUnidadesAtendidas() {
        return unidadesAtendidas;
    }
    public void setUnidadesAtendidas(Long unidadesAtendidas) {
        this.unidadesAtendidas = unidadesAtendidas;
    }
           
}
