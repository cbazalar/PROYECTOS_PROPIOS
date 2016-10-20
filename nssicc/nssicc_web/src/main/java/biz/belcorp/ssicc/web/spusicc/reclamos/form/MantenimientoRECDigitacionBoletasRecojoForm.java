package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRECDigitacionBoletasRecojoForm extends BaseEditForm{
	
	private static final long serialVersionUID = 1088627693723035837L;
	
	
	private String codigoPais;
	private String codigoCabecera;
	private String boletaRecojo;
	private String numeroRecojo;
	private String codigoCliente;
	private String nombreCliente;
	private String region;
	private String zona;
	private String seccion;
	private String territorio;
	private String boletaDespacho;
	private String periodoProceso;
	private String codigoEstado;
	private String estado;
	private String codigoResultado;
	private String descripcionResultado;
	private String fechaEmision1;	
	private String fechaRecojo1;
	private Date fechaEmision1Date;	
	private Date fechaRecojo1Date;
	private String horaRecojo1;
	private String nombreTercero1;
	private String codigoMotivoNoRecojo1;
	private String motivoNoRecojo1;
	private String fechaEmision2;
	private String fechaRecojo2;
	private Date fechaEmision2Date;
	private Date fechaRecojo2Date;
	private String horaRecojo2;
	private String nombreTercero2;
	private String codigoMotivoNoRecojo2;
	private String motivoNoRecojo2;
	private String bloqueoPostVenta;
	private String indicadorAlmacenFisico;
	private String indicadorOCSProcesado;

	private boolean viewCabecera;
	private boolean editCabecera;
	
	private boolean viewEditRecojo1;
	private boolean viewEditRecojo2;
		
	private boolean viewDetalle;
	private boolean editDetalle;
	
	private boolean editResultado;

	private boolean viewDiscrepante;
	private boolean editDiscrepante;
	
	private String[] selectedDiscItems = {};
	private String selectedDiscItem = null;
	
	private String codigoDetalle;
    private String codigoVenta;
    private String descripcionProducto;
    private String codigoPeriodoReferencia;
    private String unidadesReclamadas;
    private String codigoProducto;
    private String unidadesEliminadas;
    private String precio;
    
    private String porcentajeDescuento;
    private String codigoVentaDiscrepante;
    private String descripcionProductoDisc;
    private String codigoPeriodoReferenciaDiscrepante;
    private String unidadesRecogidas;
    private String codigoProductoDiscrepante;    
    private String precioDiscrepante;
    
    private String[] listaUnidadesEliminadas;
    private String[] listaUnidadesRecogidas;
    private String[] listaUnidadesReclamadas;
    
    private String valorPedido;
    
    private String indicadorPGRBR;
    
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	public String getBoletaRecojo() {
		return boletaRecojo;
	}

	public void setBoletaRecojo(String boletaRecojo) {
		this.boletaRecojo = boletaRecojo;
	}

	public String getNumeroRecojo() {
		return numeroRecojo;
	}

	public void setNumeroRecojo(String numeroRecojo) {
		this.numeroRecojo = numeroRecojo;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getBoletaDespacho() {
		return boletaDespacho;
	}

	public void setBoletaDespacho(String boletaDespacho) {
		this.boletaDespacho = boletaDespacho;
	}

	public String getPeriodoProceso() {
		return periodoProceso;
	}

	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getDescripcionResultado() {
		return descripcionResultado;
	}

	public void setDescripcionResultado(String descripcionResultado) {
		this.descripcionResultado = descripcionResultado;
	}

	public String getFechaEmision1() {
		return fechaEmision1;
	}

	public void setFechaEmision1(String fechaEmision1) {
		this.fechaEmision1 = fechaEmision1;
	}

	public String getFechaRecojo1() {
		return fechaRecojo1;
	}

	public void setFechaRecojo1(String fechaRecojo1) {
		this.fechaRecojo1 = fechaRecojo1;
	}

	public String getHoraRecojo1() {
		return horaRecojo1;
	}

	public void setHoraRecojo1(String horaRecojo1) {
		this.horaRecojo1 = horaRecojo1;
	}

	public String getNombreTercero1() {
		return nombreTercero1;
	}

	public void setNombreTercero1(String nombreTercero1) {
		this.nombreTercero1 = nombreTercero1;
	}

	public String getCodigoMotivoNoRecojo1() {
		return codigoMotivoNoRecojo1;
	}

	public void setCodigoMotivoNoRecojo1(String codigoMotivoNoRecojo1) {
		this.codigoMotivoNoRecojo1 = codigoMotivoNoRecojo1;
	}

	public String getMotivoNoRecojo1() {
		return motivoNoRecojo1;
	}

	public void setMotivoNoRecojo1(String motivoNoRecojo1) {
		this.motivoNoRecojo1 = motivoNoRecojo1;
	}

	public String getFechaEmision2() {
		return fechaEmision2;
	}

	public void setFechaEmision2(String fechaEmision2) {
		this.fechaEmision2 = fechaEmision2;
	}

	public String getFechaRecojo2() {
		return fechaRecojo2;
	}

	public void setFechaRecojo2(String fechaRecojo2) {
		this.fechaRecojo2 = fechaRecojo2;
	}

	public String getHoraRecojo2() {
		return horaRecojo2;
	}

	public void setHoraRecojo2(String horaRecojo2) {
		this.horaRecojo2 = horaRecojo2;
	}

	public String getNombreTercero2() {
		return nombreTercero2;
	}
	
	public void setNombreTercero2(String nombreTercero2) {
		this.nombreTercero2 = nombreTercero2;
	}

	public String getCodigoMotivoNoRecojo2() {
		return codigoMotivoNoRecojo2;
	}

	public void setCodigoMotivoNoRecojo2(String codigoMotivoNoRecojo2) {
		this.codigoMotivoNoRecojo2 = codigoMotivoNoRecojo2;
	}

	public String getMotivoNoRecojo2() {
		return motivoNoRecojo2;
	}

	public void setMotivoNoRecojo2(String motivoNoRecojo2) {
		this.motivoNoRecojo2 = motivoNoRecojo2;
	}

	public String getCodigoDetalle() {
		return codigoDetalle;
	}

	public void setCodigoDetalle(String codigoDetalle) {
		this.codigoDetalle = codigoDetalle;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}
	
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getUnidadesReclamadas() {
		return unidadesReclamadas;
	}

	public void setUnidadesReclamadas(String unidadesReclamadas) {
		this.unidadesReclamadas = unidadesReclamadas;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCodigoProductoDiscrepante() {
		return codigoProductoDiscrepante;
	}

	public void setCodigoProductoDiscrepante(String codigoProductoDiscrepante) {
		this.codigoProductoDiscrepante = codigoProductoDiscrepante;
	}

	public String getDescripcionProductoDisc() {
		return descripcionProductoDisc;
	}

	public void setDescripcionProductoDisc(String descripcionProductoDisc) {
		this.descripcionProductoDisc = descripcionProductoDisc;
	}

	public String[] getSelectedDiscItems() {
		return selectedDiscItems;
	}

	public void setSelectedDiscItems(String[] selectedDiscItems) {
		this.selectedDiscItems = selectedDiscItems;
	}

	public String getSelectedDiscItem() {
		return selectedDiscItem;
	}

	public void setSelectedDiscItem(String selectedDiscItem) {
		this.selectedDiscItem = selectedDiscItem;
	}

	public boolean isViewEditRecojo1() {
		return viewEditRecojo1;
	}

	public void setViewEditRecojo1(boolean viewEditRecojo1) {
		this.viewEditRecojo1 = viewEditRecojo1;
	}

	public boolean isViewEditRecojo2() {
		return viewEditRecojo2;
	}

	public void setViewEditRecojo2(boolean viewEditRecojo2) {
		this.viewEditRecojo2 = viewEditRecojo2;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getUnidadesEliminadas() {
		return unidadesEliminadas;
	}

	public void setUnidadesEliminadas(String unidadesEliminadas) {
		this.unidadesEliminadas = unidadesEliminadas;
	}

	public String getCodigoPeriodoReferencia() {
		return codigoPeriodoReferencia;
	}

	public void setCodigoPeriodoReferencia(String codigoPeriodoReferencia) {
		this.codigoPeriodoReferencia = codigoPeriodoReferencia;
	}

	public String getBloqueoPostVenta() {
		return bloqueoPostVenta;
	}

	public void setBloqueoPostVenta(String bloqueoPostVenta) {
		this.bloqueoPostVenta = bloqueoPostVenta;
	}

	public String getCodigoVentaDiscrepante() {
		return codigoVentaDiscrepante;
	}

	public void setCodigoVentaDiscrepante(String codigoVentaDiscrepante) {
		this.codigoVentaDiscrepante = codigoVentaDiscrepante;
	}

	public String getCodigoPeriodoReferenciaDiscrepante() {
		return codigoPeriodoReferenciaDiscrepante;
	}

	public void setCodigoPeriodoReferenciaDiscrepante(
			String codigoPeriodoReferenciaDiscrepante) {
		this.codigoPeriodoReferenciaDiscrepante = codigoPeriodoReferenciaDiscrepante;
	}

	public String getUnidadesRecogidas() {
		return unidadesRecogidas;
	}

	public void setUnidadesRecogidas(String unidadesRecogidas) {
		this.unidadesRecogidas = unidadesRecogidas;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getPrecioDiscrepante() {
		return precioDiscrepante;
	}

	public void setPrecioDiscrepante(String precioDiscrepante) {
		this.precioDiscrepante = precioDiscrepante;
	}

	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String[] getListaUnidadesEliminadas() {
		return listaUnidadesEliminadas;
	}

	public void setListaUnidadesEliminadas(String[] listaUnidadesEliminadas) {
		this.listaUnidadesEliminadas = listaUnidadesEliminadas;
	}

	public boolean isViewCabecera() {
		return viewCabecera;
	}

	public void setViewCabecera(boolean viewCabecera) {
		this.viewCabecera = viewCabecera;
	}

	public boolean isEditCabecera() {
		return editCabecera;
	}

	public void setEditCabecera(boolean editCabecera) {
		this.editCabecera = editCabecera;
	}

	public boolean isViewDetalle() {
		return viewDetalle;
	}

	public void setViewDetalle(boolean viewDetalle) {
		this.viewDetalle = viewDetalle;
	}

	public boolean isEditDetalle() {
		return editDetalle;
	}

	public void setEditDetalle(boolean editDetalle) {
		this.editDetalle = editDetalle;
	}

	public boolean isViewDiscrepante() {
		return viewDiscrepante;
	}

	public void setViewDiscrepante(boolean viewDiscrepante) {
		this.viewDiscrepante = viewDiscrepante;
	}

	public boolean isEditDiscrepante() {
		return editDiscrepante;
	}

	public void setEditDiscrepante(boolean editDiscrepante) {
		this.editDiscrepante = editDiscrepante;
	}

	public String[] getListaUnidadesRecogidas() {
		return listaUnidadesRecogidas;
	}

	public void setListaUnidadesRecogidas(String[] listaUnidadesRecogidas) {
		this.listaUnidadesRecogidas = listaUnidadesRecogidas;
	}

	public String[] getListaUnidadesReclamadas() {
		return listaUnidadesReclamadas;
	}

	public void setListaUnidadesReclamadas(String[] listaUnidadesReclamadas) {
		this.listaUnidadesReclamadas = listaUnidadesReclamadas;
	}

	public String getIndicadorAlmacenFisico() {
		return indicadorAlmacenFisico;
	}

	public void setIndicadorAlmacenFisico(String indicadorAlmacenFisico) {
		this.indicadorAlmacenFisico = indicadorAlmacenFisico;
	}

	public String getIndicadorOCSProcesado() {
		return indicadorOCSProcesado;
	}

	public void setIndicadorOCSProcesado(String indicadorOCSProcesado) {
		this.indicadorOCSProcesado = indicadorOCSProcesado;
	}
	
	public boolean isEditResultado() {
		return editResultado;
	}

	public void setEditResultado(boolean editResultado) {
		this.editResultado = editResultado;
	}

	public Date getFechaEmision1Date() {
		return fechaEmision1Date;
	}

	public void setFechaEmision1Date(Date fechaEmision1Date) {
		this.fechaEmision1Date = fechaEmision1Date;
	}

	public Date getFechaRecojo1Date() {
		return fechaRecojo1Date;
	}

	public void setFechaRecojo1Date(Date fechaRecojo1Date) {
		this.fechaRecojo1Date = fechaRecojo1Date;
	}

	public Date getFechaEmision2Date() {
		return fechaEmision2Date;
	}

	public void setFechaEmision2Date(Date fechaEmision2Date) {
		this.fechaEmision2Date = fechaEmision2Date;
	}

	public Date getFechaRecojo2Date() {
		return fechaRecojo2Date;
	}

	public void setFechaRecojo2Date(Date fechaRecojo2Date) {
		this.fechaRecojo2Date = fechaRecojo2Date;
	}

	/**
	 * @return the valorPedido
	 */
	public String getValorPedido() {
		return valorPedido;
	}

	/**
	 * @param valorPedido the valorPedido to set
	 */
	public void setValorPedido(String valorPedido) {
		this.valorPedido = valorPedido;
	}
	
	/**
	 * @param indicadorPGRBR the indicadorPGRBR to set
	 */
	public void setIndicadorPGRBR(String indicadorPGRBR) {
		this.indicadorPGRBR = indicadorPGRBR;
	}
	
	/**
	 * @return the indicadorPGRBR
	 */
	public String getIndicadorPGRBR() {
		return indicadorPGRBR;
	}	
}