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
 * <a href="InterfazCOMPagoLideres.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazCOMPagoLider implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6205048512717282948L;

	public Timestamp fechaPago;
    
    public String codigoPlanilla;
    
    public String centroCosto;
    
    public Float montoPagar;
    
    public String flag;
    
    public String codigoRegion;

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCodigoPlanilla() {
        return codigoPlanilla;
    }

    public void setCodigoPlanilla(String codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Float getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Float montoPagar) {
        this.montoPagar = montoPagar;
    }
    
}
