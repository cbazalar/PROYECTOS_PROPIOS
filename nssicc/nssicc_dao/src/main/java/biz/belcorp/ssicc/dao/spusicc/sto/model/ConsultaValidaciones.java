package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;





public class ConsultaValidaciones implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoDocumento;
    private String lote;
   
   
    private String fecha;
    private String fechaModificacion;
    private String codRegion;
    private String codZonaActual;
    private String codZona;
    private String cliente;
    private String nomcliente;
	private Integer totalCarga;
	private Integer correctas;
	private Integer erradas;
	private Integer rechazadas;
	private Integer detallesOK;
	private String codigoPeriodo;
   
	private Integer cantidadGP1;
	private Integer cantidadFacturadas;
	
	private String codigoTipoDocumento;
	private String numeroSecuencia;
	
	private String codigoPais;
	
	private String origen;
	
	private String numeroDocumento;
	private String secNumeDocu;
	
	private String fechaProgramadaFacturacion;
	private String numeroPreimpreso;
	private String indicadorDNInuevoCliente;
	private String montoCupon;
	
	/**
	 * @return Returns the fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha The fecha to set.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Returns the fechaModificacion.
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion The fechaModificacion to set.
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	/**
	 * @return Returns the lote.
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote The lote to set.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Returns the cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente The cliente to set.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Returns the codRegion.
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion The codRegion to set.
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	/**
	 * @return Returns the codZona.
	 */
	public String getCodZona() {
		return codZona;
	}
	/**
	 * @param codZona The codZona to set.
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	/**
	 * @return Returns the correctas.
	 */
	public Integer getCorrectas() {
		return correctas;
	}
	/**
	 * @param correctas The correctas to set.
	 */
	public void setCorrectas(Integer correctas) {
		this.correctas = correctas;
	}
	/**
	 * @return Returns the erradas.
	 */
	public Integer getErradas() {
		return erradas;
	}
	/**
	 * @param erradas The erradas to set.
	 */
	public void setErradas(Integer erradas) {
		this.erradas = erradas;
	}
	/**
	 * @return Returns the rechazadas.
	 */
	public Integer getRechazadas() {
		return rechazadas;
	}
	/**
	 * @param rechazadas The rechazadas to set.
	 */
	public void setRechazadas(Integer rechazadas) {
		this.rechazadas = rechazadas;
	}
	/**
	 * @return Returns the totalCarga.
	 */
	public Integer getTotalCarga() {
		return totalCarga;
	}
	/**
	 * @param totalCarga The totalCarga to set.
	 */
	public void setTotalCarga(Integer totalCarga) {
		this.totalCarga = totalCarga;
	}
	/**
	 * @return Returns the detallesOK.
	 */
	public Integer getDetallesOK() {
		return detallesOK;
	}
	/**
	 * @param detallesOK The detallesOK to set.
	 */
	public void setDetallesOK(Integer detallesOK) {
		this.detallesOK = detallesOK;
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
	 * @return the cantidadGP1
	 */
	public Integer getCantidadGP1() {
		return cantidadGP1;
	}
	/**
	 * @param cantidadGP1 the cantidadGP1 to set
	 */
	public void setCantidadGP1(Integer cantidadGP1) {
		this.cantidadGP1 = cantidadGP1;
	}
	/**
	 * @return the cantidadFacturadas
	 */
	public Integer getCantidadFacturadas() {
		return cantidadFacturadas;
	}
	/**
	 * @param cantidadFacturadas the cantidadFacturadas to set
	 */
	public void setCantidadFacturadas(Integer cantidadFacturadas) {
		this.cantidadFacturadas = cantidadFacturadas;
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
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
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
	 * @return the secNumeDocu
	 */
	public String getSecNumeDocu() {
		return secNumeDocu;
	}
	/**
	 * @param secNumeDocu the secNumeDocu to set
	 */
	public void setSecNumeDocu(String secNumeDocu) {
		this.secNumeDocu = secNumeDocu;
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
	 * @return the numeroPreimpreso
	 */
	public String getNumeroPreimpreso() {
		return numeroPreimpreso;
	}
	/**
	 * @param numeroPreimpreso the numeroPreimpreso to set
	 */
	public void setNumeroPreimpreso(String numeroPreimpreso) {
		this.numeroPreimpreso = numeroPreimpreso;
	}
	/**
	 * @return the indicadorDNInuevoCliente
	 */
	public String getIndicadorDNInuevoCliente() {
		return indicadorDNInuevoCliente;
	}
	/**
	 * @param indicadorDNInuevoCliente the indicadorDNInuevoCliente to set
	 */
	public void setIndicadorDNInuevoCliente(String indicadorDNInuevoCliente) {
		this.indicadorDNInuevoCliente = indicadorDNInuevoCliente;
	}
	/**
	 * @return the montoCupon
	 */
	public String getMontoCupon() {
		return montoCupon;
	}
	/**
	 * @param montoCupon the montoCupon to set
	 */
	public void setMontoCupon(String montoCupon) {
		this.montoCupon = montoCupon;
	}
	/**
	 * @return codigoZonaActual
	 */
	public String getCodZonaActual() {
		return codZonaActual;
	}
	/**
	 * @param codigoZonaActual
	 */
	public void setCodZonaActual(String codZonaActual) {
		this.codZonaActual = codZonaActual;
	}
	/**
	 * @return nomcliente
	 */
	public String getNomcliente() {
		return nomcliente;
	}
	/**
	 * @param nomcliente
	 */
	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}
	
	
}
