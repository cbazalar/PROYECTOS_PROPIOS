package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/* @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

public class OrdenTransporte implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoCompania;
	private String numeroDocumento;
	private String codigoDocumento;
	private String codigoTipoDocumento;
	private String codigoCompaniaTransporte;
	private String codigoCentroAcopio;
	private String codigoEstadoEntrega;
	private String codigoEstadoRecojo;
	private String codigoNovedad;
	private String codigoRecibiConforme;
	private String oidPais;
	private String fechaProceso;
	private String horaProceso;
	private String minutosProceso;
	private String tipoHorario;
	private String estadoProceso;
	private String codigoMotivoRechazo;
	private String numeroLote;
	private String codigoEstadoEntrega2;
	private String mensaje;
	private String direccion;
	private String numeroSecuencia;
	private String indicadorNovedad;
	private String indicadorEnvio;
	private String tipoOrden;
	private String codigoZona;
	private String codigoCalificacion;
	private String codigoCliente;
	private String fechaFacturacion;
	private String nombreCliente;
	private String codigoPeriodo;
	private String codigoRegion;
	private String codigoSeccion;
	
	private String codigoZonaArribo;
	
	//PER-SiCC-2013-0894 @ghuertasa inicio
	private String nombreCentroAcopio;
	private String nombreCompanhiaTransporte;
	private String valEmail;
    private String valDescricpcion;
	private String direccionDelCliente;
	private String telefonoCliente;

	//PER-SiCC-2013-0894 @ghuertasa fin
	
	private String indicadorComprobanteCaja;
	private String indicadorFueraCaja;
	
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
	 * @return the codigoCompania
	 */
	public String getCodigoCompania() {
		return codigoCompania;
	}
	/**
	 * @param codigoCompania the codigoCompania to set
	 */
	public void setCodigoCompania(String codigoCompania) {
		this.codigoCompania = codigoCompania;
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
	 * @return the codigoTipoDocumento
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}
	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	/**
	 * @return the codigoCompaniaTransporte
	 */
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
	 * @return the codigoEstadoRecojo
	 */
	public String getCodigoEstadoRecojo() {
		return codigoEstadoRecojo;
	}
	/**
	 * @param codigoEstadoRecojo the codigoEstadoRecojo to set
	 */
	public void setCodigoEstadoRecojo(String codigoEstadoRecojo) {
		this.codigoEstadoRecojo = codigoEstadoRecojo;
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
	 * @return the codigoRecibiConforme
	 */
	public String getCodigoRecibiConforme() {
		return codigoRecibiConforme;
	}
	/**
	 * @param codigoRecibiConforme the codigoRecibiConforme to set
	 */
	public void setCodigoRecibiConforme(String codigoRecibiConforme) {
		this.codigoRecibiConforme = codigoRecibiConforme;
	}
	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return the horaProceso
	 */
	public String getHoraProceso() {
		return horaProceso;
	}
	/**
	 * @param horaProceso the horaProceso to set
	 */
	public void setHoraProceso(String horaProceso) {
		this.horaProceso = horaProceso;
	}
	/**
	 * @return the minutosProceso
	 */
	public String getMinutosProceso() {
		return minutosProceso;
	}
	/**
	 * @param minutosProceso the minutosProceso to set
	 */
	public void setMinutosProceso(String minutosProceso) {
		this.minutosProceso = minutosProceso;
	}
	/**
	 * @return the tipoHorario
	 */
	public String getTipoHorario() {
		return tipoHorario;
	}
	/**
	 * @param tipoHorario the tipoHorario to set
	 */
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	/**
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @return the codigoMotivoRechazo
	 */
	public String getCodigoMotivoRechazo() {
		return codigoMotivoRechazo;
	}
	/**
	 * @param codigoMotivoRechazo the codigoMotivoRechazo to set
	 */
	public void setCodigoMotivoRechazo(String codigoMotivoRechazo) {
		this.codigoMotivoRechazo = codigoMotivoRechazo;
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
	 * @return the codigoEstadoEntrega2
	 */
	public String getCodigoEstadoEntrega2() {
		return codigoEstadoEntrega2;
	}
	/**
	 * @param codigoEstadoEntrega2 the codigoEstadoEntrega2 to set
	 */
	public void setCodigoEstadoEntrega2(String codigoEstadoEntrega2) {
		this.codigoEstadoEntrega2 = codigoEstadoEntrega2;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the numeroSecuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}
	/**
	 * @param numeroSecuencia the numeroSecuencia to set
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}
	/**
	 * @return the indicadorNovedad
	 */
	public String getIndicadorNovedad() {
		return indicadorNovedad;
	}
	/**
	 * @param indicadorNovedad the indicadorNovedad to set
	 */
	public void setIndicadorNovedad(String indicadorNovedad) {
		this.indicadorNovedad = indicadorNovedad;
	}
	/**
	 * @return the indicadorEnvio
	 */
	public String getIndicadorEnvio() {
		return indicadorEnvio;
	}
	/**
	 * @param indicadorEnvio the indicadorEnvio to set
	 */
	public void setIndicadorEnvio(String indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}
	/**
	 * @return the tipoOrden
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}
	/**
	 * @param tipoOrden the tipoOrden to set
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
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
	 * @return the codigoCalificacion
	 */
	public String getCodigoCalificacion() {
		return codigoCalificacion;
	}
	/**
	 * @param codigoCalificacion the codigoCalificacion to set
	 */
	public void setCodigoCalificacion(String codigoCalificacion) {
		this.codigoCalificacion = codigoCalificacion;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
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
	public String getValEmail() {
		return valEmail;
	}
	/**
	 * @param valEmail
	 */
	public void setValEmail(String valEmail) {
		this.valEmail = valEmail;
	}
	/**
	 * @return
	 */
	public String getValDescricpcion() {
		return valDescricpcion;
	}
	/**
	 * @param valDescricpcion
	 */
	public void setValDescricpcion(String valDescricpcion) {
		this.valDescricpcion = valDescricpcion;
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
	public void setDireccionCliente(String direccionDelCliente) {
		this.direccionDelCliente = direccionDelCliente;
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
	@Override
	public String toString() {
		return "OrdenTransporte [codigoPais=" + codigoPais
				+ ", codigoCompania=" + codigoCompania + ", numeroDocumento="
				+ numeroDocumento + ", codigoDocumento=" + codigoDocumento
				+ ", codigoTipoDocumento=" + codigoTipoDocumento
				+ ", codigoCompaniaTransporte=" + codigoCompaniaTransporte
				+ ", codigoCentroAcopio=" + codigoCentroAcopio
				+ ", codigoEstadoEntrega=" + codigoEstadoEntrega
				+ ", codigoEstadoRecojo=" + codigoEstadoRecojo
				+ ", codigoNovedad=" + codigoNovedad
				+ ", codigoRecibiConforme=" + codigoRecibiConforme
				+ ", oidPais=" + oidPais + ", fechaProceso=" + fechaProceso
				+ ", horaProceso=" + horaProceso + ", minutosProceso="
				+ minutosProceso + ", tipoHorario=" + tipoHorario
				+ ", estadoProceso=" + estadoProceso + ", codigoMotivoRechazo="
				+ codigoMotivoRechazo + ", numeroLote=" + numeroLote
				+ ", codigoEstadoEntrega2=" + codigoEstadoEntrega2
				+ ", mensaje=" + mensaje + ", direccion=" + direccion
				+ ", numeroSecuencia=" + numeroSecuencia
				+ ", indicadorNovedad=" + indicadorNovedad
				+ ", indicadorEnvio=" + indicadorEnvio + ", tipoOrden="
				+ tipoOrden + ", codigoZona=" + codigoZona
				+ ", codigoCalificacion=" + codigoCalificacion
				+ ", codigoCliente=" + codigoCliente + ", fechaFacturacion="
				+ fechaFacturacion + ", nombreCliente=" + nombreCliente
				+ ", codigoPeriodo=" + codigoPeriodo + ", codigoRegion="
				+ codigoRegion + ", codigoSeccion=" + codigoSeccion
				+ ", codigoZonaArribo=" + codigoZonaArribo
				+ ", nombreCentroAcopio=" + nombreCentroAcopio
				+ ", nombreCompanhiaTransporte=" + nombreCompanhiaTransporte
				+ ", valEmail=" + valEmail + ", valDescricpcion="
				+ valDescricpcion + ", direccionDelCliente="
				+ direccionDelCliente + ", telefonoCliente=" + telefonoCliente
				+ ", indicadorComprobanteCaja=" + indicadorComprobanteCaja
				+ ", indicadorFueraCaja=" + indicadorFueraCaja + "]";
	}
	
	
}
