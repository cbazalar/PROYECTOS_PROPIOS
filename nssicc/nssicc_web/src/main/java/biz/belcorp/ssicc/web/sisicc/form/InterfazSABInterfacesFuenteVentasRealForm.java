package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazSABInterfacesFuenteVentasRealForm extends
        BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = -2546712545743069164L;

	private String codigoMarca; 
	
    private String codigoCanal;

	private String codigoPeriodo;
	
	/* INI SA PER-SiCC-2012-0648 */
	private String fechaFacturacion;
	/* FIN SA PER-SiCC-2012-0648 */
	
   	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */   	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

    public String getCodigoCanal() {
        return codigoCanal;
    }

	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

   	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
}