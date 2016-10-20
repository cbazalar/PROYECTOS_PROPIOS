/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

/**
 * @author peextrramirez - Rosalvina Ramirez
 *
 */
public class EstructuraFuenteVentaPrevista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5654594561645606385L;
	private String codigoPais;
	private String codigoSociedad;
	private String codigoMarca;
	private String codigoAlmacen;
	private String canal;
	private String periodo;
	private String codigoRegion;
	private String codigoZona;
	private String ventaNetaCatalogo;
	private String pedidos;
	private String activasIniciales;
	private String ingresos;
	private String reingresos;
	private String egresos;
	private String consultorasPrivilege;
	private String clientePrivilege;
	private String transaccionesPrivilege;
	private String retencionSegPedido;
	private String retencionTerPedido;
	private String retencionCuarPedido;
	private String unidades;
	private String porcentajeRetencion;
	private String codigoSeccion;
	private String posibleEgresoCampaniaAnterior;
		
	private int fila;


	/**
	 * @return Returns the activasIniciales.
	 */
	public String getActivasIniciales() {
		return activasIniciales;
	}


	/**
	 * @param activasIniciales The activasIniciales to set.
	 */
	public void setActivasIniciales(String activasIniciales) {
		this.activasIniciales = activasIniciales;
	}


	/**
	 * @return Returns the canal.
	 */
	public String getCanal() {
		return canal;
	}


	/**
	 * @param canal The canal to set.
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}


	/**
	 * @return Returns the clientePrivilege.
	 */
	public String getClientePrivilege() {
		return clientePrivilege;
	}


	/**
	 * @param clientePrivilege The clientePrivilege to set.
	 */
	public void setClientePrivilege(String clientePrivilege) {
		this.clientePrivilege = clientePrivilege;
	}


	/**
	 * @return Returns the codgioMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	/**
	 * @param codgioMarca The codgioMarca to set.
	 */
	public void setCodgioMarca(String codgioMarca) {
		this.codigoMarca = codgioMarca;
	}


	/**
	 * @return Returns the codigoAlmacen.
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}


	/**
	 * @param codigoAlmacen The codigoAlmacen to set.
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}


	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}


	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}


	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}


	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}


	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}


	/**
	 * @return Returns the consultorasPrivilege.
	 */
	public String getConsultorasPrivilege() {
		return consultorasPrivilege;
	}


	/**
	 * @param consultorasPrivilege The consultorasPrivilege to set.
	 */
	public void setConsultorasPrivilege(String consultorasPrivilege) {
		this.consultorasPrivilege = consultorasPrivilege;
	}


	/**
	 * @return Returns the egresos.
	 */
	public String getEgresos() {
		return egresos;
	}


	/**
	 * @param egresos The egresos to set.
	 */
	public void setEgresos(String egresos) {
		this.egresos = egresos;
	}


	/**
	 * @return Returns the fila.
	 */
	public int getFila() {
		return fila;
	}


	/**
	 * @param fila The fila to set.
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}


	/**
	 * @return Returns the ingresos.
	 */
	public String getIngresos() {
		return ingresos;
	}


	/**
	 * @param ingresos The ingresos to set.
	 */
	public void setIngresos(String ingresos) {
		this.ingresos = ingresos;
	}


	/**
	 * @return Returns the pedidos.
	 */
	public String getPedidos() {
		return pedidos;
	}


	/**
	 * @param pedidos The pedidos to set.
	 */
	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}


	/**
	 * @return Returns the periodo.
	 */
	public String getPeriodo() {
		return periodo;
	}


	/**
	 * @param periodo The periodo to set.
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	/**
	 * @return Returns the porcentajeRetencion.
	 */
	public String getPorcentajeRetencion() {
		return porcentajeRetencion;
	}


	/**
	 * @param porcentajeRetencion The porcentajeRetencion to set.
	 */
	public void setPorcentajeRetencion(String porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}


	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}


	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}


	/**
	 * @return the posibleEgresoCampaniaAnterior
	 */
	public String getPosibleEgresoCampaniaAnterior() {
		return posibleEgresoCampaniaAnterior;
	}


	/**
	 * @param posibleEgresoCampaniaAnterior the posibleEgresoCampaniaAnterior to set
	 */
	public void setPosibleEgresoCampaniaAnterior(
			String posibleEgresoCampaniaAnterior) {
		this.posibleEgresoCampaniaAnterior = posibleEgresoCampaniaAnterior;
	}


	/**
	 * @return Returns the reingresos.
	 */
	public String getReingresos() {
		return reingresos;
	}


	/**
	 * @param reingresos The reingresos to set.
	 */
	public void setReingresos(String reingresos) {
		this.reingresos = reingresos;
	}


	/**
	 * @return Returns the retencionCuarPedido.
	 */
	public String getRetencionCuarPedido() {
		return retencionCuarPedido;
	}


	/**
	 * @param retencionCuarPedido The retencionCuarPedido to set.
	 */
	public void setRetencionCuarPedido(String retencionCuarPedido) {
		this.retencionCuarPedido = retencionCuarPedido;
	}


	/**
	 * @return Returns the retencionSegPedido.
	 */
	public String getRetencionSegPedido() {
		return retencionSegPedido;
	}


	/**
	 * @param retencionSegPedido The retencionSegPedido to set.
	 */
	public void setRetencionSegPedido(String retencionSegPedido) {
		this.retencionSegPedido = retencionSegPedido;
	}


	/**
	 * @return Returns the retencionTerPedido.
	 */
	public String getRetencionTerPedido() {
		return retencionTerPedido;
	}


	/**
	 * @param retencionTerPedido The retencionTerPedido to set.
	 */
	public void setRetencionTerPedido(String retencionTerPedido) {
		this.retencionTerPedido = retencionTerPedido;
	}


	/**
	 * @return Returns the transaccionesPrivilege.
	 */
	public String getTransaccionesPrivilege() {
		return transaccionesPrivilege;
	}


	/**
	 * @param transaccionesPrivilege The transaccionesPrivilege to set.
	 */
	public void setTransaccionesPrivilege(String transaccionesPrivilege) {
		this.transaccionesPrivilege = transaccionesPrivilege;
	}


	/**
	 * @return Returns the unidades.
	 */
	public String getUnidades() {
		return unidades;
	}


	/**
	 * @param unidades The unidades to set.
	 */
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}


	/**
	 * @return Returns the ventaNetaCatalogo.
	 */
	public String getVentaNetaCatalogo() {
		return ventaNetaCatalogo;
	}


	/**
	 * @param ventaNetaCatalogo The ventaNetaCatalogo to set.
	 */
	public void setVentaNetaCatalogo(String ventaNetaCatalogo) {
		this.ventaNetaCatalogo = ventaNetaCatalogo;
	}

}
