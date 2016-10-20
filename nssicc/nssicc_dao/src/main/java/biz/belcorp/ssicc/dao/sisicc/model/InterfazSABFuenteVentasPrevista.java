/*
 * Created on 14-dic-2005
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
 * <a href="InterfazSABFuenteVentasPrevista.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazSABFuenteVentasPrevista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5564381604079540848L;

	private String codigoPais;

	private String codigoSociedad;

	private String codigoAlmacen;

	private String codigoMarca;

	private String codigoCanal;

	private String anio;

	private String codigoMoneda;

	private String periodo;

	private String codigoZona;

	private String codigoRegion;

	private String activasIniciales;

	private String activasFinales;

	private String ingresos;

	private String reingresos;

	private String egresos;

	private String rezonificadasRecibidas;

	private String rezonificadasEntregadas;

	private String numeroOrdenes;

	private String numeroPedidos;

	private String unidadesVendidas;

	private String numeroClientes;

	private String ventaNeta;

	private Timestamp fechaCierre;

	public String getActivasFinales() {
		return activasFinales;
	}

	public void setActivasFinales(String activasFinales) {
		this.activasFinales = activasFinales;
	}

	public String getActivasIniciales() {
		return activasIniciales;
	}

	public void setActivasIniciales(String activasIniciales) {
		this.activasIniciales = activasIniciales;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getEgresos() {
		return egresos;
	}

	public void setEgresos(String egresos) {
		this.egresos = egresos;
	}

	public Timestamp getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getIngresos() {
		return ingresos;
	}

	public void setIngresos(String ingresos) {
		this.ingresos = ingresos;
	}

	public String getNumeroClientes() {
		return numeroClientes;
	}

	public void setNumeroClientes(String numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public String getNumeroOrdenes() {
		return numeroOrdenes;
	}

	public void setNumeroOrdenes(String numeroOrdenes) {
		this.numeroOrdenes = numeroOrdenes;
	}

	public String getNumeroPedidos() {
		return numeroPedidos;
	}

	public void setNumeroPedidos(String numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getReingresos() {
		return reingresos;
	}

	public void setReingresos(String reingresos) {
		this.reingresos = reingresos;
	}

	public String getRezonificadasEntregadas() {
		return rezonificadasEntregadas;
	}

	public void setRezonificadasEntregadas(String rezonificadasEntregadas) {
		this.rezonificadasEntregadas = rezonificadasEntregadas;
	}

	public String getRezonificadasRecibidas() {
		return rezonificadasRecibidas;
	}

	public void setRezonificadasRecibidas(String rezonificadasRecibidas) {
		this.rezonificadasRecibidas = rezonificadasRecibidas;
	}

	public String getUnidadesVendidas() {
		return unidadesVendidas;
	}

	public void setUnidadesVendidas(String unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}

	public String getVentaNeta() {
		return ventaNeta;
	}

	public void setVentaNeta(String ventaNeta) {
		this.ventaNeta = ventaNeta;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

}