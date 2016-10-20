/*
 * Created on 27-dic-2005
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
 * <a href="InterfazCOMReportePagoLideres.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazCOMReportePagoLideres implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8481344361392018322L;

	private String codigoPlanilla;

    private String nombresApellidos;
	
    private double importe;
    
    private String importeTexto;
    
    private String numeroCuenta;
    
    private String fechaCalculo;

    public String getCodigoPlanilla() {
        return codigoPlanilla;
    }

    public void setCodigoPlanilla(String codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }

    public String getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(String fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteTexto() {
        return importeTexto;
    }

    public void setImporteTexto(String importeTexto) {
        this.importeTexto = importeTexto;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
      
}
