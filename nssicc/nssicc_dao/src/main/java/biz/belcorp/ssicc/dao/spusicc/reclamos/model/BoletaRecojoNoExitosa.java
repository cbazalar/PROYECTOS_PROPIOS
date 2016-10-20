package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextjcairampoma@belcorp.biz
 * 
 */
public class BoletaRecojoNoExitosa extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCabecera;
    private String boletaRecojo;             
    private String codigoCliente;    
    private String region;                   
    private String zona;                     
    private String seccion;                  
    private String territorio;               
    private String periodoProceso;
    private String fechaEmision1;
    private String fechaRecojo1;
    private String motivoNoRecojo1;
    private String fechaEmision2;
    private String fechaRecojo2;
    private String motivoNoRecojo2; 
    private String monto;
    

	public boolean equals(Object o) {
		
		return false;
	}

	public int hashCode() {
		
		return 0;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * @param codigoCabecera the codigoCabecera to set
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * @return the boletaRecojo
	 */
	public String getBoletaRecojo() {
		return boletaRecojo;
	}

	/**
	 * @param boletaRecojo the boletaRecojo to set
	 */
	public void setBoletaRecojo(String boletaRecojo) {
		this.boletaRecojo = boletaRecojo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

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

	/**
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the territorio
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * @param territorio the territorio to set
	 */
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	/**
	 * @return the fechaEmision1
	 */
	public String getFechaEmision1() {
		return fechaEmision1;
	}

	/**
	 * @param fechaEmision1 the fechaEmision1 to set
	 */
	public void setFechaEmision1(String fechaEmision1) {
		this.fechaEmision1 = fechaEmision1;
	}

	/**
	 * @return the fechaRecojo1
	 */
	public String getFechaRecojo1() {
		return fechaRecojo1;
	}

	/**
	 * @param fechaRecojo1 the fechaRecojo1 to set
	 */
	public void setFechaRecojo1(String fechaRecojo1) {
		this.fechaRecojo1 = fechaRecojo1;
	}

	/**
	 * @return the motivoNoRecojo1
	 */
	public String getMotivoNoRecojo1() {
		return motivoNoRecojo1;
	}

	/**
	 * @param motivoNoRecojo1 the motivoNoRecojo1 to set
	 */
	public void setMotivoNoRecojo1(String motivoNoRecojo1) {
		this.motivoNoRecojo1 = motivoNoRecojo1;
	}

	/**
	 * @return the fechaEmision2
	 */
	public String getFechaEmision2() {
		return fechaEmision2;
	}

	/**
	 * @param fechaEmision2 the fechaEmision2 to set
	 */
	public void setFechaEmision2(String fechaEmision2) {
		this.fechaEmision2 = fechaEmision2;
	}

	/**
	 * @return the fechaRecojo2
	 */
	public String getFechaRecojo2() {
		return fechaRecojo2;
	}

	/**
	 * @param fechaRecojo2 the fechaRecojo2 to set
	 */
	public void setFechaRecojo2(String fechaRecojo2) {
		this.fechaRecojo2 = fechaRecojo2;
	}

	/**
	 * @return the motivoNoRecojo2
	 */
	public String getMotivoNoRecojo2() {
		return motivoNoRecojo2;
	}

	/**
	 * @param motivoNoRecojo2 the motivoNoRecojo2 to set
	 */
	public void setMotivoNoRecojo2(String motivoNoRecojo2) {
		this.motivoNoRecojo2 = motivoNoRecojo2;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BoletaRecojoNoExitosa [codigoPais=" + codigoPais
				+ ", codigoCabecera=" + codigoCabecera + ", boletaRecojo="
				+ boletaRecojo + ", codigoCliente=" + codigoCliente
				+ ", region=" + region + ", zona=" + zona + ", seccion="
				+ seccion + ", territorio=" + territorio + ", periodoProceso="
				+ periodoProceso + ", fechaEmision1=" + fechaEmision1
				+ ", fechaRecojo1=" + fechaRecojo1 + ", motivoNoRecojo1="
				+ motivoNoRecojo1 + ", fechaEmision2=" + fechaEmision2
				+ ", fechaRecojo2=" + fechaRecojo2 + ", motivoNoRecojo2="
				+ motivoNoRecojo2 + ", monto=" + monto + "]";
	}
}