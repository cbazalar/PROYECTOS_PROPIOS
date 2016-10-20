package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;
import java.math.BigDecimal;



public class GestionDocumento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codTipoDocu;
	private String codigoPais;
	private String tipoDocumento;
    private String lote;
    private String numeroDocumento;
    private String validacion; 
    private String detalle;
    private String indGestionable;
    private String indAprobado;
    private String fecha;
    private String fechaModificacion;
	private String documento;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZonaActual;
	private String codigoZona;
	private String codigoCliente;
	private String indCabecera;
	private String numSecPadre;
	private String codDocuCabec;
	private String numDocumento;
	private String desValidacion;
	private String desValidacionLarga;
	private String numDocu;
	private String codigoOperacion;
	private String documentoReferencia;
	private String periodoReferencia;
	private String codigoPeriodo;
	private String exeProcApro;
	private String exeProcDesa;
	private String exeProcRech;
	private String descripcionOrigen;
	private String descripcionOrigenDetalle;
	private BigDecimal totalEstimado;
	private BigDecimal saldo;
	private String nombreCliente;
	private String indGestionRechazo;
	private String tipClasi;
	private String ped1;
	private String ped2;
	private String ped3;
	private String ped4;
	private String ped5;
	private String ped6;
	private String ped7;
	private String ped8;
	private String cdr1;
	private String cdr2;
	private String cdr3;
	private String cdr4;
	private String cdr5;
	private String cdr6;
	private String cdr7;
	private String cdr8;
	private String numDocuIden;
	private String telefono1;
	private String telefono2;
	private String estatus;
	
	private BigDecimal unidadesDevuelve;
	private BigDecimal unidadesEnvia;
	private BigDecimal precioCatalogo;
	private BigDecimal precioContable;	
	
	private String codigoZonaArribo;
	
	private String codigoRechazo;
	
	private String descripcionRechazo;
	private String montoMeta;
	private String seccion;
	private String codSeccion;
	private String totalPedido;
	private String indDocumentoIdentidad;

	private String indRechazoOrigen;

	private String codVenta;
	
	private String indicadorPROL;
	
	private String fechaIngreso;
	
	private String rechazo;
	
	private String montoPedido;
	
	private String campanyaPrimerPedido;
	
	private String mensaje;
	
	private String motivo;
	
	private String porcentajeDesviacion;
	
	private String promedioPedido;
	
	private String montoReal;
	
	private String indViewMotiGest;
	
	private String motivoRechazo;
	private String observaciones;	          
	
	
	/**
	 * @return
	 */
	public String getCodTipoDocu() {
		return codTipoDocu;
	}

	/**
	 * @param codTipoDocu
	 */
	public void setCodTipoDocu(String codTipoDocu) {
		this.codTipoDocu = codTipoDocu;
	}

	/**
	 * @return the codVenta
	 */
	public String getCodVenta() {
		return codVenta;
	}

	/**
	 * @param codVenta the codVenta to set
	 */
	public void setCodVenta(String codVenta) {
		this.codVenta = codVenta;
	}

	
	
    /**
	 * @return the indRechazoOrigen
	 */
	public String getIndRechazoOrigen() {
		return indRechazoOrigen;
	}

	/**
	 * @param indRechazoOrigen the indRechazoOrigen to set
	 */
	public void setIndRechazoOrigen(String indRechazoOrigen) {
		this.indRechazoOrigen = indRechazoOrigen;
	}

	/**
	 * @return the indDocumentoIdentidad
	 */
	public String getIndDocumentoIdentidad() {
		return indDocumentoIdentidad;
	}

	/**
	 * @param indDocumentoIdentidad the indDocumentoIdentidad to set
	 */
	public void setIndDocumentoIdentidad(String indDocumentoIdentidad) {
		this.indDocumentoIdentidad = indDocumentoIdentidad;
	}
	
    /**
     * @return montoMeta
     */
    public String getMontoMeta() {
		return montoMeta;
	}

	/**
	 * @param montoMeta
	 */
	public void setMontoMeta(String montoMeta) {
		this.montoMeta = montoMeta;
	}

	/**
     * @return
     */
    public String getCodigoRechazo() {
		return codigoRechazo;
	}

	/**
	 * @param codigoRechazo
	 */
	public void setCodigoRechazo(String codigoRechazo) {
		this.codigoRechazo = codigoRechazo;
	}

	/**
	 * @return
	 */
	public String getDescripcionRechazo() {
		return descripcionRechazo;
	}

	/**
	 * @param descripcionRechazo
	 */
	public void setDescripcionRechazo(String descripcionRechazo) {
		this.descripcionRechazo = descripcionRechazo;
	}

	/**
     * @return
     */
    public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
     * @return
     */
    public String getTelefono1() {
		return telefono1;
	}
    
	/**
	 * @param telefono1
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	
	/**
	 * @return
	 */
	public String getTelefono2() {
		return telefono2;
	}
	
	/**
	 * @param telefono2
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	
	/**
     * @return
     */
    public String getTipClasi() {
		return tipClasi;
	}
    
	/**
	 * @param tipClasi
	 */
	public void setTipClasi(String tipClasi) {
		this.tipClasi = tipClasi;
	}
	
	/**
	 * @return
	 */
	public String getPed1() {
		return ped1;
	}
	
	/**
	 * @param ped1
	 */
	public void setPed1(String ped1) {
		this.ped1 = ped1;
	}
	
	/**
	 * @return
	 */
	public String getPed2() {
		return ped2;
	}
	
	/**
	 * @param ped2
	 */
	public void setPed2(String ped2) {
		this.ped2 = ped2;
	}
	
	/**
	 * @return
	 */
	public String getPed3() {
		return ped3;
	}
	
	/**
	 * @param ped3
	 */
	public void setPed3(String ped3) {
		this.ped3 = ped3;
	}
	
	/**
	 * @return
	 */
	public String getPed4() {
		return ped4;
	}
	
	/**
	 * @param ped4
	 */
	public void setPed4(String ped4) {
		this.ped4 = ped4;
	}
	
	/**
	 * @return
	 */
	public String getPed5() {
		return ped5;
	}
	
	/**
	 * @param ped5
	 */
	public void setPed5(String ped5) {
		this.ped5 = ped5;
	}
	
	/**
	 * @return
	 */
	public String getPed6() {
		return ped6;
	}
	
	/**
	 * @param ped6
	 */
	public void setPed6(String ped6) {
		this.ped6 = ped6;
	}
	
	/**
	 * @return
	 */
	public String getPed7() {
		return ped7;
	}
	
	/**
	 * @param ped7
	 */
	public void setPed7(String ped7) {
		this.ped7 = ped7;
	}
	
	/**
	 * @return
	 */
	public String getPed8() {
		return ped8;
	}
	
	/**
	 * @param ped8
	 */
	public void setPed8(String ped8) {
		this.ped8 = ped8;
	}
	
	/**
	 * @return
	 */
	public String getCdr1() {
		return cdr1;
	}
	
	/**
	 * @param cdr1
	 */
	public void setCdr1(String cdr1) {
		this.cdr1 = cdr1;
	}
	
	/**
	 * @return
	 */
	public String getCdr2() {
		return cdr2;
	}
	
	/**
	 * @param cdr2
	 */
	public void setCdr2(String cdr2) {
		this.cdr2 = cdr2;
	}
	
	/**
	 * @return
	 */
	public String getCdr3() {
		return cdr3;
	}
	
	/**
	 * @param cdr3
	 */
	public void setCdr3(String cdr3) {
		this.cdr3 = cdr3;
	}
	
	/**
	 * @return
	 */
	public String getCdr4() {
		return cdr4;
	}
	
	/**
	 * @param cdr4
	 */
	public void setCdr4(String cdr4) {
		this.cdr4 = cdr4;
	}
	
	/**
	 * @return
	 */
	public String getCdr5() {
		return cdr5;
	}
	
	/**
	 * @param cdr5
	 */
	public void setCdr5(String cdr5) {
		this.cdr5 = cdr5;
	}
	
	/**
	 * @return
	 */
	public String getCdr6() {
		return cdr6;
	}
	
	/**
	 * @param cdr6
	 */
	public void setCdr6(String cdr6) {
		this.cdr6 = cdr6;
	}
	
	/**
	 * @return
	 */
	public String getCdr7() {
		return cdr7;
	}
	
	/**
	 * @param cdr7
	 */
	public void setCdr7(String cdr7) {
		this.cdr7 = cdr7;
	}
	
	/**
	 * @return
	 */
	public String getCdr8() {
		return cdr8;
	}
	
	/**
	 * @param cdr8
	 */
	public void setCdr8(String cdr8) {
		this.cdr8 = cdr8;
	}
	
	/**
	 * @return the indGestionRechazo
	 */
	public String getIndGestionRechazo() {
		return indGestionRechazo;
	}
	
	/**
	 * @param indGestionRechazo the indGestionRechazo to set
	 */
	public void setIndGestionRechazo(String indGestionRechazo) {
		this.indGestionRechazo = indGestionRechazo;
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
	 * @return the exeProcRech
	 */
	public String getExeProcRech() {
		return exeProcRech;
	}
	
	/**
	 * @param exeProcRech the exeProcRech to set
	 */
	public void setExeProcRech(String exeProcRech) {
		this.exeProcRech = exeProcRech;
	}
	/**
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	
	/**
	 * @param detalle The detalle to set.
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
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
	 * @return Returns the indAprobado.
	 */
	public String getIndAprobado() {
		return indAprobado;
	}
	
	/**
	 * @param indAprobado The indAprobado to set.
	 */
	public void setIndAprobado(String indAprobado) {
		this.indAprobado = indAprobado;
	}
	
	/**
	 * @return Returns the indGestionable.
	 */
	public String getIndGestionable() {
		return indGestionable;
	}
	
	/**
	 * @param indGestionable The indGestionable to set.
	 */
	public void setIndGestionable(String indGestionable) {
		this.indGestionable = indGestionable;
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
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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
	 * @return Returns the validacion.
	 */
	public String getValidacion() {
		return validacion;
	}
	
	/**
	 * @param validacion The validacion to set.
	 */
	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}
	
	/**
	 * @return Returns the documento.
	 */
	public String getDocumento() {
		return documento;
	}
	
	/**
	 * @param documento The documento to set.
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	
	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	
	/**
	 * @return Returns the indCabecera.
	 */
	public String getIndCabecera() {
		return indCabecera;
	}
	
	/**
	 * @param indCabecera The indCabecera to set.
	 */
	public void setIndCabecera(String indCabecera) {
		this.indCabecera = indCabecera;
	}
	
	/**
	 * @return Returns the numSecPadre.
	 */
	public String getNumSecPadre() {
		return numSecPadre;
	}
	
	/**
	 * @param numSecPadre The numSecPadre to set.
	 */
	public void setNumSecPadre(String numSecPadre) {
		this.numSecPadre = numSecPadre;
	}
	
	/**
	 * @return Returns the codDocuCabec.
	 */
	public String getCodDocuCabec() {
		return codDocuCabec;
	}
	
	/**
	 * @param codDocuCabec The codDocuCabec to set.
	 */
	public void setCodDocuCabec(String codDocuCabec) {
		this.codDocuCabec = codDocuCabec;
	}
	
	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	
	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	/**
	 * @return Returns the desValidacion.
	 */
	public String getDesValidacion() {
		return desValidacion;
	}
	
	/**
	 * @param desValidacion The desValidacion to set.
	 */
	public void setDesValidacion(String desValidacion) {
		this.desValidacion = desValidacion;
	}
	
	/**
	 * @return Returns the desValidacionLarga.
	 */
	public String getDesValidacionLarga() {
		return desValidacionLarga;
	}
	
	/**
	 * @param desValidacionLarga The desValidacionLarga to set.
	 */
	public void setDesValidacionLarga(String desValidacionLarga) {
		this.desValidacionLarga = desValidacionLarga;
	}
	
	/**
	 * @return Returns the numDocu.
	 */
	public String getNumDocu() {
		return numDocu;
	}
	
	/**
	 * @param numDocu The numDocu to set.
	 */
	public void setNumDocu(String numDocu) {
		this.numDocu = numDocu;
	}
	
	/**
	 * @return Returns the codigoOperacion.
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	
	/**
	 * @param codigoOperacion The codigoOperacion to set.
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	
	/**
	 * @return Returns the documentoReferencia.
	 */
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	
	/**
	 * @param documentoReferencia The documentoReferencia to set.
	 */
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	
	/**
	 * @return Returns the periodoReferencia.
	 */
	public String getPeriodoReferencia() {
		return periodoReferencia;
	}
	
	/**
	 * @param periodoReferencia The periodoReferencia to set.
	 */
	public void setPeriodoReferencia(String periodoReferencia) {
		this.periodoReferencia = periodoReferencia;
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
	 * @return the exeProcApro
	 */
	public String getExeProcApro() {
		return exeProcApro;
	}
	
	/**
	 * @param exeProcApro the exeProcApro to set
	 */
	public void setExeProcApro(String exeProcApro) {
		this.exeProcApro = exeProcApro;
	}
	
	/**
	 * @return the exeProcDesa
	 */
	public String getExeProcDesa() {
		return exeProcDesa;
	}
	
	/**
	 * @param exeProcDesa the exeProcDesa to set
	 */
	public void setExeProcDesa(String exeProcDesa) {
		this.exeProcDesa = exeProcDesa;
	}
	
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	 * @return the descripcionOrigenDetalle
	 */
	public String getDescripcionOrigenDetalle() {
		return descripcionOrigenDetalle;
	}

	/**
	 * @param descripcionOrigenDetalle the descripcionOrigenDetalle to set
	 */
	public void setDescripcionOrigenDetalle(String descripcionOrigenDetalle) {
		this.descripcionOrigenDetalle = descripcionOrigenDetalle;
	}

	/**
	 * @return the totalEstimado
	 */
	public BigDecimal getTotalEstimado() {
		return totalEstimado;
	}
	
	/**
	 * @param totalEstimado the totalEstimado to set
	 */
	public void setTotalEstimado(BigDecimal totalEstimado) {
		this.totalEstimado = totalEstimado;
	}
	
	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * @return the numDocuIden
	 */
	public String getNumDocuIden() {
		return numDocuIden;
	}
	
	/**
	 * @param numDocuIden the numDocuIden to set
	 */
	public void setNumDocuIden(String numDocuIden) {
		this.numDocuIden = numDocuIden;
	}

	/**
	 * @return the unidadesDevuelve
	 */
	public BigDecimal getUnidadesDevuelve() {
		return unidadesDevuelve;
	}

	/**
	 * @param unidadesDevuelve the unidadesDevuelve to set
	 */
	public void setUnidadesDevuelve(BigDecimal unidadesDevuelve) {
		this.unidadesDevuelve = unidadesDevuelve;
	}

	/**
	 * @return the unidadesEnvia
	 */
	public BigDecimal getUnidadesEnvia() {
		return unidadesEnvia;
	}

	/**
	 * @param unidadesEnvia the unidadesEnvia to set
	 */
	public void setUnidadesEnvia(BigDecimal unidadesEnvia) {
		this.unidadesEnvia = unidadesEnvia;
	}

	/**
	 * @return the precioCatalogo
	 */
	public BigDecimal getPrecioCatalogo() {
		return precioCatalogo;
	}

	/**
	 * @param precioCatalogo the precioCatalogo to set
	 */
	public void setPrecioCatalogo(BigDecimal precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}

	/**
	 * @return the precioContable
	 */
	public BigDecimal getPrecioContable() {
		return precioContable;
	}

	/**
	 * @param precioContable the precioContable to set
	 */
	public void setPrecioContable(BigDecimal precioContable) {
		this.precioContable = precioContable;
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
	 * @return seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return codSeccion
	 */
	public String getCodSeccion() {
		return codSeccion;
	}

	/**
	 * @param codSeccion
	 */
	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	/**
	 * @return totalPedido
	 */
	public String getTotalPedido() {
		return totalPedido;
	}

	/**
	 * @param totalPedido
	 */
	public void setTotalPedido(String totalPedido) {
		this.totalPedido = totalPedido;
	}

	/**
	 * @return codigoZonaActual
	 */
	public String getCodigoZonaActual() {
		return codigoZonaActual;
	}

	/**
	 * @param codigoZonaActual
	 */
	public void setCodigoZonaActual(String codigoZonaActual) {
		this.codigoZonaActual = codigoZonaActual;
	}
	
	/**
	 * @return the indicadorPROL
	 */
	public String getIndicadorPROL() {
		return indicadorPROL;
	}
	
	/**
	 * @param indicadorPROL the indicadorPROL to set
	 */
	public void setIndicadorPROL(String indicadorPROL) {
		this.indicadorPROL = indicadorPROL;
	}
	
	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the rechazo
	 */
	public String getRechazo() {
		return rechazo;
	}

	/**
	 * @param rechazo the rechazo to set
	 */
	public void setRechazo(String rechazo) {
		this.rechazo = rechazo;
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
	 * @return the campanyaPrimerPedido
	 */
	public String getCampanyaPrimerPedido() {
		return campanyaPrimerPedido;
	}

	/**
	 * @param campanyaPrimerPedido the campanyaPrimerPedido to set
	 */
	public void setCampanyaPrimerPedido(String campanyaPrimerPedido) {
		this.campanyaPrimerPedido = campanyaPrimerPedido;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the porcentajeDesviacion
	 */
	public String getPorcentajeDesviacion() {
		return porcentajeDesviacion;
	}

	/**
	 * @param porcentajeDesviacion the porcentajeDesviacion to set
	 */
	public void setPorcentajeDesviacion(String porcentajeDesviacion) {
		this.porcentajeDesviacion = porcentajeDesviacion;
	}

	/**
	 * @return the promedioPedido
	 */
	public String getPromedioPedido() {
		return promedioPedido;
	}

	/**
	 * @param promedioPedido the promedioPedido to set
	 */
	public void setPromedioPedido(String promedioPedido) {
		this.promedioPedido = promedioPedido;
	}

	/**
	 * @return the montoReal
	 */
	public String getMontoReal() {
		return montoReal;
	}

	/**
	 * @param montoReal the montoReal to set
	 */
	public void setMontoReal(String montoReal) {
		this.montoReal = montoReal;
	}

	/**
	 * @return the indViewMotiGest
	 */
	public String getIndViewMotiGest() {
		return indViewMotiGest;
	}

	/**
	 * @param indViewMotiGest the indViewMotiGest to set
	 */
	public void setIndViewMotiGest(String indViewMotiGest) {
		this.indViewMotiGest = indViewMotiGest;
	}

	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
}