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
 * <a href="InterfazCOMLibretaAhorros.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazCOMLibretaAhorros implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5172975933133724324L;

	private String codigoPlanilla;
    
    private String dni;
    
    private String numeroLibretaAhorro;
    
    private String tipoCuenta;
    
    private String codigoBanco;
    
    public String getCodigoBanco() {
        return codigoBanco;
    }
    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
    public String getCodigoPlanilla() {
        return codigoPlanilla;
    }
    public void setCodigoPlanilla(String codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNumeroLibretaAhorro() {
        return numeroLibretaAhorro;
    }
    public void setNumeroLibretaAhorro(String numeroLibretaAhorro) {
        this.numeroLibretaAhorro = numeroLibretaAhorro;
    }
    public String getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    
    
    
}
