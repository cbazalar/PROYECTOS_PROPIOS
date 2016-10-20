package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 * @author Jos A. Cairampoma
 */
public class ConsultaPedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer facturadas;
	private Integer correctas;
	private Integer noValidadas;
	private Integer erradas;
	private Integer rechazadas;
	private Integer cargadas;
	private String codigoCliente;
	private String nombreCliente;
	private String numeroTelefono;
	private String codigoOrigen;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoTipoDocumento;
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	private String fechaCarga;
	private String fechaModificacion;
	private String fechaProgramadaFacturacion;
	private String numeroLote;
	private String descripcionOrigen;
	private Integer numGP2;
	private Integer numGP3;
	private Integer numGP4;
	private Integer numGP5;
	private String documentoIdentidad;
	
	private String descripcionSeccion;
	private String codigoTerritorio;
	
	
	private String montoPedido;
	
	private String deuda;
	/**
	 * @return the facturadas
	 */
	public Integer getFacturadas() {
		return facturadas;
	}
	/**
	 * @param facturadas the facturadas to set
	 */
	public void setFacturadas(Integer facturadas) {
		this.facturadas = facturadas;
	}
	/**
	 * @return the correctas
	 */
	public Integer getCorrectas() {
		return correctas;
	}
	/**
	 * @param correctas the correctas to set
	 */
	public void setCorrectas(Integer correctas) {
		this.correctas = correctas;
	}
	/**
	 * @return the noValidadas
	 */
	public Integer getNoValidadas() {
		return noValidadas;
	}
	/**
	 * @param noValidadas the noValidadas to set
	 */
	public void setNoValidadas(Integer noValidadas) {
		this.noValidadas = noValidadas;
	}
	/**
	 * @return the erradas
	 */
	public Integer getErradas() {
		return erradas;
	}
	/**
	 * @param erradas the erradas to set
	 */
	public void setErradas(Integer erradas) {
		this.erradas = erradas;
	}
	/**
	 * @return the rechazadas
	 */
	public Integer getRechazadas() {
		return rechazadas;
	}
	/**
	 * @param rechazadas the rechazadas to set
	 */
	public void setRechazadas(Integer rechazadas) {
		this.rechazadas = rechazadas;
	}
	/**
	 * @return the cargadas
	 */
	public Integer getCargadas() {
		return cargadas;
	}
	/**
	 * @param cargadas the cargadas to set
	 */
	public void setCargadas(Integer cargadas) {
		this.cargadas = cargadas;
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
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
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
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the fechaCarga
	 */
	public String getFechaCarga() {
		return fechaCarga;
	}
	/**
	 * @param fechaCarga the fechaCarga to set
	 */
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the fechaProgramadaFacturacion
	 */
	public String getFechaProgramadaFacturacion() {
		return fechaProgramadaFacturacion;
	}
	/**
	 * @param fechaProgramadaFacturacion the fechaProgramadaFacturacion to set
	 */
	public void setFechaProgramadaFacturacion(String fechaProgramadaFacturacion) {
		this.fechaProgramadaFacturacion = fechaProgramadaFacturacion;
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
	 * @return the descripcionOrigen
	 */
	public String getDescripcionOrigen() {
		return descripcionOrigen;
	}
	/**
	 * @param descripcionOrigen the descripcionOrigen to set
	 */
	public void setDescripcionOrigen(String descripcionOrigen) {
		this.descripcionOrigen = descripcionOrigen;
	}
	/**
	 * @return the numGP2
	 */
	public Integer getNumGP2() {
		return numGP2;
	}
	/**
	 * @param numGP2 the numGP2 to set
	 */
	public void setNumGP2(Integer numGP2) {
		this.numGP2 = numGP2;
	}
	/**
	 * @return the numGP3
	 */
	public Integer getNumGP3() {
		return numGP3;
	}
	/**
	 * @param numGP3 the numGP3 to set
	 */
	public void setNumGP3(Integer numGP3) {
		this.numGP3 = numGP3;
	}
	/**
	 * @return the numGP4
	 */
	public Integer getNumGP4() {
		return numGP4;
	}
	/**
	 * @param numGP4 the numGP4 to set
	 */
	public void setNumGP4(Integer numGP4) {
		this.numGP4 = numGP4;
	}
	/**
	 * @return the numGP5
	 */
	public Integer getNumGP5() {
		return numGP5;
	}
	/**
	 * @param numGP5 the numGP5 to set
	 */
	public void setNumGP5(Integer numGP5) {
		this.numGP5 = numGP5;
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
	 * @return the numeroTelefono
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	/**
	 * @param numeroTelefono the numeroTelefono to set
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	/**
	 * @return the documentoIdentidad
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	/**
	 * @return the descripcionSeccion
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}
	/**
	 * @param descripcionSeccion the descripcionSeccion to set
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}
	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
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
	 * @return the deuda
	 */
	public String getDeuda() {
		return deuda;
	}
	/**
	 * @param deuda the deuda to set
	 */
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	
	
}
