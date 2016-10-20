package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelValueCDR implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8481419175535342210L;


	public LabelValueCDR() {
        super();
    }

    /**
     * Construct an instance with the supplied property values.
     * 
     * @param label
     *            The label to be displayed to the user.
     * @param value
     *            The value to be returned to the server.
     */
    public LabelValueCDR(String label, String value, List lista, String zona) {
        this.label = label;
        this.value = value;
        this.lista = lista;
        this.zona = zona;
    }

    // ------------------------------------------------------------- Properties

    private String montoPedido;
    private String montoDevolucion;
    private String porcentajeDevolucion;

    
    /**
	 * @return the montoPedido
	 */
	public String getMontoPedido() {
		return montoPedido;
	}

	/**
	 * @param montoPedido the montoPedido to set
	 */
	public void setMontoPedido(String montoPedido) {
		this.montoPedido = montoPedido;
	}

	/**
	 * @return the montoDevolucion
	 */
	public String getMontoDevolucion() {
		return montoDevolucion;
	}

	/**
	 * @param montoDevolucion the montoDevolucion to set
	 */
	public void setMontoDevolucion(String montoDevolucion) {
		this.montoDevolucion = montoDevolucion;
	}

	/**
	 * @return the porcentajeDevolucion
	 */
	public String getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	/**
	 * @param porcentajeDevolucion the porcentajeDevolucion to set
	 */
	public void setPorcentajeDevolucion(String porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}

	
	//---------para faltantes y cambios RCR COL-SiCC-2014-0115
    private String montoFaltantes;
    private String porcentajeFaltantes;
    
    private String montoCambios;
    private String porcentajeCambios;
    
    

	/**
	 * @return the montoFaltantes
	 */
	public String getMontoFaltantes() {
		return montoFaltantes;
	}

	/**
	 * @param montoFaltantes the montoFaltantes to set
	 */
	public void setMontoFaltantes(String montoFaltantes) {
		this.montoFaltantes = montoFaltantes;
	}

	/**
	 * @return the porcentajeFaltantes
	 */
	public String getPorcentajeFaltantes() {
		return porcentajeFaltantes;
	}

	/**
	 * @param porcentajeFaltantes the porcentajeFaltantes to set
	 */
	public void setPorcentajeFaltantes(String porcentajeFaltantes) {
		this.porcentajeFaltantes = porcentajeFaltantes;
	}

	/**
	 * @return the montoCambios
	 */
	public String getMontoCambios() {
		return montoCambios;
	}

	/**
	 * @param montoCambios the montoCambios to set
	 */
	public void setMontoCambios(String montoCambios) {
		this.montoCambios = montoCambios;
	}

	/**
	 * @return the porcentajeCambios
	 */
	public String getPorcentajeCambios() {
		return porcentajeCambios;
	}

	/**
	 * @param porcentajeCambios the porcentajeCambios to set
	 */
	public void setPorcentajeCambios(String porcentajeCambios) {
		this.porcentajeCambios = porcentajeCambios;
	}

	/**
     * The property which supplies the option label visible to the end user.
     */
    private String label = null;

    /**
     * 
     * @uml.property name="label"
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 
     * @uml.property name="label"
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * The property which supplies the value returned to the server.
     */
    private String value = null;

    /**
     * 
     * @uml.property name="value"
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 
     * @uml.property name="value"
     */
    public void setValue(String value) {
        this.value = value;
    }

    private List lista = null;

	/**
	 * @return the lista
	 */
	public List getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}
    
	private String zona = null;

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	//------- Direccion y ubicación
	private String direccionDomicilio;
	private String ubicacionGeografica;


	/**
	 * @return the direccionDomicilio
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	/**
	 * @param direccionDomicilio the direccionDomicilio to set
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	/**
	 * @return the ubicacionGeografica
	 */
	public String getUbicacionGeografica() {
		return ubicacionGeografica;
	}

	/**
	 * @param ubicacionGeografica the ubicacionGeografica to set
	 */
	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}
	
	private String fechaFactura;
	private String campana;
	private String periodoCDR;
	private String saldoUnico;
	/**
	 * @return the saldoUnico
	 */
	public String getSaldoUnico() {
		return saldoUnico;
	}

	/**
	 * @param saldoUnico the saldoUnico to set
	 */
	public void setSaldoUnico(String saldoUnico) {
		this.saldoUnico = saldoUnico;
	}

	/**
	 * @return the fechaFactura
	 */
	public String getFechaFactura() {
		return fechaFactura;
	}

	/**
	 * @param fechaFactura the fechaFactura to set
	 */
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	/**
	 * @return the campana
	 */
	public String getCampana() {
		return campana;
	}

	/**
	 * @param campana the campana to set
	 */
	public void setCampana(String campana) {
		this.campana = campana;
	}

	/**
	 * @return the periodoCDR
	 */
	public String getPeriodoCDR() {
		return periodoCDR;
	}

	/**
	 * @param periodoCDR the periodoCDR to set
	 */
	public void setPeriodoCDR(String periodoCDR) {
		this.periodoCDR = periodoCDR;
	}
	
	private String nuevoNumeroBoleta;


	/**
	 * @return the nuevoNumeroBoleta
	 */
	public String getNuevoNumeroBoleta() {
		return nuevoNumeroBoleta;
	}

	/**
	 * @param nuevoNumeroBoleta the nuevoNumeroBoleta to set
	 */
	public void setNuevoNumeroBoleta(String nuevoNumeroBoleta) {
		this.nuevoNumeroBoleta = nuevoNumeroBoleta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LabelValueCDR [montoPedido=" + montoPedido
				+ ", montoDevolucion=" + montoDevolucion
				+ ", porcentajeDevolucion=" + porcentajeDevolucion
				+ ", montoFaltantes=" + montoFaltantes
				+ ", porcentajeFaltantes=" + porcentajeFaltantes
				+ ", montoCambios=" + montoCambios + ", porcentajeCambios="
				+ porcentajeCambios + ", label=" + label + ", value=" + value
				+ ", lista=" + lista + ", zona=" + zona
				+ ", direccionDomicilio=" + direccionDomicilio
				+ ", ubicacionGeografica=" + ubicacionGeografica
				+ ", fechaFactura=" + fechaFactura + ", campana=" + campana
				+ ", periodoCDR=" + periodoCDR + ", saldoUnico=" + saldoUnico
				+ ", nuevoNumeroBoleta=" + nuevoNumeroBoleta
				+ ", getMontoPedido()=" + getMontoPedido()
				+ ", getMontoDevolucion()=" + getMontoDevolucion()
				+ ", getPorcentajeDevolucion()=" + getPorcentajeDevolucion()
				+ ", getMontoFaltantes()=" + getMontoFaltantes()
				+ ", getPorcentajeFaltantes()=" + getPorcentajeFaltantes()
				+ ", getMontoCambios()=" + getMontoCambios()
				+ ", getPorcentajeCambios()=" + getPorcentajeCambios()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", getLista()=" + getLista() + ", getZona()=" + getZona()
				+ ", getDireccionDomicilio()=" + getDireccionDomicilio()
				+ ", getUbicacionGeografica()=" + getUbicacionGeografica()
				+ ", getSaldoUnico()=" + getSaldoUnico()
				+ ", getFechaFactura()=" + getFechaFactura()
				+ ", getCampana()=" + getCampana() + ", getPeriodoCDR()="
				+ getPeriodoCDR() + ", getNuevoNumeroBoleta()="
				+ getNuevoNumeroBoleta() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}