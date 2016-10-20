package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Cristhia Roman
 *
 */
public class CargosyAbonos extends AuditableBaseObject implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String codigoCliente;
	private String nombreCliente;
	private String oidTerritorio;
	private String oidZona;
	private String oidTerritorioAdmin;
	private String valorEstruGeop;	
	private String oidCliente;
	private String oidClienteDireccion;				
	private String oidTipoDocumento;
	private String oidTipoCliente;	
	private String oidSubTipoCliente;
	private String oidProducto;	
	private String codigoVenta;
	private String codigoTerritorio;	
	private String codigoZona;
	private String codigoSeccion;
	private String porcentajeDescuento;
	private String unidadesAtendidas;
	private String montoFactUnitario;
    private String montoFactTotal;
    private String montoFactRealUnitario;
    private String montoFactRealTotal;
    private String montoCorregir;
    private String resultado;
    private Integer totalCargados;
    private Integer totalOK;
    private Integer totalError;
	
	public CargosyAbonos() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getOidTerritorio() {
		return oidTerritorio;
	}

	public void setOidTerritorio(String oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
	}

	public String getOidZona() {
		return oidZona;
	}

	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}

	public String getOidTerritorioAdmin() {
		return oidTerritorioAdmin;
	}

	public void setOidTerritorioAdmin(String oidTerritorioAdmin) {
		this.oidTerritorioAdmin = oidTerritorioAdmin;
	}

	public String getValorEstruGeop() {
		return valorEstruGeop;
	}

	public void setValorEstruGeop(String valorEstruGeop) {
		this.valorEstruGeop = valorEstruGeop;
	}

	public String getOidCliente() {
		return oidCliente;
	}

	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	public String getOidClienteDireccion() {
		return oidClienteDireccion;
	}

	public void setOidClienteDireccion(String oidClienteDireccion) {
		this.oidClienteDireccion = oidClienteDireccion;
	}

	public String getOidTipoDocumento() {
		return oidTipoDocumento;
	}

	public void setOidTipoDocumento(String oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}

	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	public String getOidProducto() {
		return oidProducto;
	}

	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getUnidadesAtendidas() {
		return unidadesAtendidas;
	}

	public void setUnidadesAtendidas(String unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}

	public String getMontoFactUnitario() {
		return montoFactUnitario;
	}

	public void setMontoFactUnitario(String montoFactUnitario) {
		this.montoFactUnitario = montoFactUnitario;
	}

	public String getMontoFactTotal() {
		return montoFactTotal;
	}

	public void setMontoFactTotal(String montoFactTotal) {
		this.montoFactTotal = montoFactTotal;
	}

	public String getMontoFactRealUnitario() {
		return montoFactRealUnitario;
	}

	public void setMontoFactRealUnitario(String montoFactRealUnitario) {
		this.montoFactRealUnitario = montoFactRealUnitario;
	}

	public String getMontoFactRealTotal() {
		return montoFactRealTotal;
	}

	public void setMontoFactRealTotal(String montoFactRealTotal) {
		this.montoFactRealTotal = montoFactRealTotal;
	}

	public String getMontoCorregir() {
		return montoCorregir;
	}

	public void setMontoCorregir(String montoCorregir) {
		this.montoCorregir = montoCorregir;
	}

	public Integer getTotalCargados() {
		return totalCargados;
	}

	public void setTotalCargados(Integer totalCargados) {
		this.totalCargados = totalCargados;
	}

	public Integer getTotalOK() {
		return totalOK;
	}

	public void setTotalOK(Integer totalOK) {
		this.totalOK = totalOK;
	}

	public Integer getTotalError() {
		return totalError;
	}

	public void setTotalError(Integer totalError) {
		this.totalError = totalError;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}		
	
	
}