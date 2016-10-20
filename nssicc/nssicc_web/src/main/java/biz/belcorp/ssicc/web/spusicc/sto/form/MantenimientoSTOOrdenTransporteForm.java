package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOOrdenTransporteForm extends BaseMantenimientoSTOGestionForm
implements Serializable {
	
	private static final long serialVersionUID = -350850788971261330L;
	
	private String codigoPais;	
	private String codigoCompaniaTransporte;
	private String codigoCentroAcopio;
	private String calificacion;
	private String mensaje;
	private String direccionCliente;	
	private String codigoCliente;
	private String nombreCliente;
	private String codigoZona;	
	private String fechaFact;
	
	private String codigoEstadoEntrega;
	private String codigoNovedad;

	private String detalle;
	private String salirPantalla = "N";
	
	private String numeroLote;
	private String numeroDocumento;
	private String codigoDocumento;
	
	private String direccionClienteSearch;
	
	private String codigoZonaArribo;
	
	//PER-SiCC-2013-0894 @ghuertasa inicio
	
	private String fechaRecibo;	
	private String tipoOrden;
	private String nombreCentroAcopio;
	private String nombreCompanhiaTransporte;
	private String numeroOt;
	private String telefonoCliente;
	private String motivoRechazo;
	private String motivoRechazoDescripcion;
	private String correoElectronico;
	private String direccionDelCliente;
	
	//PER-SiCC-2013-0894 @ghuertasa fin
	
	private String indicadorComprobanteCaja;
	private String indicadorFueraCaja;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoCompaniaTransporte() {
		return codigoCompaniaTransporte;
	}
	/**
	 * @param codigoCompaniaTransporte the codigoCompaniaTransporte to set
	 */
	public void setCodigoCompaniaTransporte(String codigoCompaniaTransporte) {
		this.codigoCompaniaTransporte = codigoCompaniaTransporte;
	}
	/**
	 * @return the codigoCentroAcopio
	 */
	public String getCodigoCentroAcopio() {
		return codigoCentroAcopio;
	}
	/**
	 * @param codigoCentroAcopio the codigoCentroAcopio to set
	 */
	public void setCodigoCentroAcopio(String codigoCentroAcopio) {
		this.codigoCentroAcopio = codigoCentroAcopio;
	}
	
	/**
	 * @return the calificacion
	 */
	public String getCalificacion() {
		return calificacion;
	}
	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the direccionCliente
	 */
	public String getDireccionCliente() {
		return direccionCliente;
	}
	/**
	 * @param direccionCliente the direccionCliente to set
	 */
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
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
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the fechaFact
	 */
	public String getFechaFact() {
		return fechaFact;
	}
	/**
	 * @param fechaFact the fechaFact to set
	 */
	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}
	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}
	/**
	 * @param salirPantalla the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the codigoDocumento
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	/**
	 * @param codigoDocumento the codigoDocumento to set
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	/**
	 * @return the codigoEstadoEntrega
	 */
	public String getCodigoEstadoEntrega() {
		return codigoEstadoEntrega;
	}
	/**
	 * @param codigoEstadoEntrega the codigoEstadoEntrega to set
	 */
	public void setCodigoEstadoEntrega(String codigoEstadoEntrega) {
		this.codigoEstadoEntrega = codigoEstadoEntrega;
	}
	/**
	 * @return the codigoNovedad
	 */
	public String getCodigoNovedad() {
		return codigoNovedad;
	}
	/**
	 * @param codigoNovedad the codigoNovedad to set
	 */
	public void setCodigoNovedad(String codigoNovedad) {
		this.codigoNovedad = codigoNovedad;
	}
	/**
	 * @return the direccionClienteSearch
	 */
	public String getDireccionClienteSearch() {
		return direccionClienteSearch;
	}
	/**
	 * @param direccionClienteSearch the direccionClienteSearch to set
	 */
	public void setDireccionClienteSearch(String direccionClienteSearch) {
		this.direccionClienteSearch = direccionClienteSearch;
	}
	/**
	 * @return the codigoZonaArribo
	 */
	public String getCodigoZonaArribo() {
		return codigoZonaArribo;
	}
	/**
	 * @param codigoZonaArribo the codigoZonaArribo to set
	 */
	public void setCodigoZonaArribo(String codigoZonaArribo) {
		this.codigoZonaArribo = codigoZonaArribo;
	}
	/**
	 * @return
	 */
	public String getFechaRecibo() {
		return fechaRecibo;
	}
	/**
	 * @param fechaRecibo
	 */
	public void setFechaRecibo(String fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}
	/**
	 * @return
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}
	/**
	 * @param tipoOrden
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}
	/**
	 * @return
	 */
	public String getNombreCentroAcopio() {
		return nombreCentroAcopio;
	}
	/**
	 * @param nombreCentroAcopio
	 */
	public void setNombreCentroAcopio(String nombreCentroAcopio) {
		this.nombreCentroAcopio = nombreCentroAcopio;
	}
	/**
	 * @return
	 */
	public String getNombreCompanhiaTransporte() {
		return nombreCompanhiaTransporte;
	}
	/**
	 * @param nombreCompanhiaTransporte
	 */
	public void setNombreCompanhiaTransporte(String nombreCompanhiaTransporte) {
		this.nombreCompanhiaTransporte = nombreCompanhiaTransporte;
	}
	/**
	 * @return
	 */
	public String getNumeroOt() {
		return numeroOt;
	}
	/**
	 * @param numeroOt
	 */
	public void setNumeroOt(String numeroOt) {
		this.numeroOt = numeroOt;
	}
	/**
	 * @return
	 */
	public String getTelefonoCliente() {
		return telefonoCliente;
	}
	/**
	 * @param telefonoCliente
	 */
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	/**
	 * @return
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	/**
	 * @param motivoRechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	/**
	 * @return
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return
	 */
	public String getDireccionDelCliente() {
		return direccionDelCliente;
	}
	/**
	 * @param direccionDelCliente
	 */
	public void setDireccionDelCliente(String direccionDelCliente) {
		this.direccionDelCliente = direccionDelCliente;
	}
	/**
	 * @return
	 */
	public String getMotivoRechazoDescripcion() {
		return motivoRechazoDescripcion;
	}
	/**
	 * @param motivoRechazoDescripcion
	 */
	public void setMotivoRechazoDescripcion(String motivoRechazoDescripcion) {
		this.motivoRechazoDescripcion = motivoRechazoDescripcion;
	}
	/**
	 * @return the indicadorComprobanteCaja
	 */
	public String getIndicadorComprobanteCaja() {
		return indicadorComprobanteCaja;
	}
	/**
	 * @return the indicadorFueraCaja
	 */
	public String getIndicadorFueraCaja() {
		return indicadorFueraCaja;
	}
	/**
	 * @param indicadorComprobanteCaja the indicadorComprobanteCaja to set
	 */
	public void setIndicadorComprobanteCaja(String indicadorComprobanteCaja) {
		this.indicadorComprobanteCaja = indicadorComprobanteCaja;
	}
	/**
	 * @param indicadorFueraCaja the indicadorFueraCaja to set
	 */
	public void setIndicadorFueraCaja(String indicadorFueraCaja) {
		this.indicadorFueraCaja = indicadorFueraCaja;
	}
	

}
