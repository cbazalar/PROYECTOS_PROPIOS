package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;


/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class CodigoVentaPedido implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoVenta;
    private String codigoSAP;
    private String descripcion;
    private String precioCatalogo; 
    private String precioFactura;
    private String unidadesAtendidas;
    private String unidadesFaltantes;
    private String porcentajeDescuento;
    private String importe;
    private String tipoSolicitud;
    private String recProcesados;
    private String recGestion;
    private String desOrigen;
    private String oidSoli;
	
	/**
	 * @return tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	/**
	 * @param tipoSolicitud
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	/**
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	 * @return precioCatalogo
	 */
	public String getPrecioCatalogo() {
		return precioCatalogo;
	}
	/**
	 * @param precioCatalogo
	 */
	public void setPrecioCatalogo(String precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}
	/**
	 * @return precioFactura
	 */
	public String getPrecioFactura() {
		return precioFactura;
	}
	/**
	 * @param precioFactura
	 */
	public void setPrecioFactura(String precioFactura) {
		this.precioFactura = precioFactura;
	}
	/**
	 * @return unidadesAtendidas
	 */
	public String getUnidadesAtendidas() {
		return unidadesAtendidas;
	}
	/**
	 * @param unidadesAtendidas
	 */
	public void setUnidadesAtendidas(String unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}
	/**
	 * @return unidadesFaltantes
	 */
	public String getUnidadesFaltantes() {
		return unidadesFaltantes;
	}
	/**
	 * @param unidadesFaltantes
	 */
	public void setUnidadesFaltantes(String unidadesFaltantes) {
		this.unidadesFaltantes = unidadesFaltantes;
	}
	/**
	 * @return porcentajeDescuento
	 */ 
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	/**
	 * @param porcentajeDescuento
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	/**
	 * @return importe
	 */
	public String getImporte() {
		return importe;
	}
	/**
	 * @param importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	/**
	 * @return recProcesados
	 */
	public String getRecProcesados() {
		return recProcesados;
	}
	/**
	 * @param recProcesados
	 */
	public void setRecProcesados(String recProcesados) {
		this.recProcesados = recProcesados;
	}
	/**
	 * @return recGestion
	 */
	public String getRecGestion() {
		return recGestion;
	}
	/**
	 * @param recGestion
	 */
	public void setRecGestion(String recGestion) {
		this.recGestion = recGestion;
	}
	/**
	 * @return the oidSoli
	 */
	public String getOidSoli() {
		return oidSoli;
	}
	/**
	 * @param oidSoli the oidSoli to set
	 */
	public void setOidSoli(String oidSoli) {
		this.oidSoli = oidSoli;
	}
	/**
	 * @return the desOrigen
	 */
	public String getDesOrigen() {
		return desOrigen;
	}
	/**
	 * @param desOrigen the desOrigen to set
	 */
	public void setDesOrigen(String desOrigen) {
		this.desOrigen = desOrigen;
	}
	
}
