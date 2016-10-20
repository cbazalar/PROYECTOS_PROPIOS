package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

public class MantenimientoSTOSolicitudPostVentaDetalleForm extends BaseMantenimientoSTOGestionForm
implements Serializable {
	
	private static final long serialVersionUID = 297262793201796746L;
	
	private String codigoPais;
	private String codPeriodo;
	private String codCliente;
	private String numLote;
	private String codTipoDocumento;
	private String numDocumento;
	private String codCia;
	private String tipoReferencia;
	private Integer codVentaDevu;
	private Integer codVentaDese;
	private Integer canVentaDevu;
	private Integer canVentaDese;
	private String estadoProceso;
	private String codMotivoRechazo;
	private String codOperacion;
	private String codTipoOperacion;
	private String numSecuencia;
	private String codRegion;
	private String codZona;
	private String observaciones;
	private String detalle;
	private String nombre;
	private String descVentaDevu;
	private String descVentaDese;
	private String codMotDevolucion;
	private String descMotDevolucion;
	private String montoMinimo;
	private String valorDeseado;
	private String valorDevuelve;
	private String montoTotal;
	private String numeroCruce;
	private String periodoReferencia;
	private String oidSoliDevuelve;
	private String precioUniDevuelve;
	private String oidSoli;
	
	private String fechaRegistro;	
	private String usuarioRegistro;
	private String fechaModificacion;	;
	private String usuarioModificacion;
	private String origen;
	
	public String getCodMotDevolucion() {
		return codMotDevolucion;
	}
	public void setCodMotDevolucion(String codMotDevolucion) {
		this.codMotDevolucion = codMotDevolucion;
	}
	public String getDescMotDevolucion() {
		return descMotDevolucion;
	}
	public void setDescMotDevolucion(String descMotDevolucion) {
		this.descMotDevolucion = descMotDevolucion;
	}
	public String getDescVentaDevu() {
		return descVentaDevu;
	}
	public void setDescVentaDevu(String descVentaDevu) {
		this.descVentaDevu = descVentaDevu;
	}
	public String getDescVentaDese() {
		return descVentaDese;
	}
	public void setDescVentaDese(String descVentaDese) {
		this.descVentaDese = descVentaDese;
	}
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * 
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	/**
	 * @return Returns the codMotivoRechazo.
	 */
	public String getCodMotivoRechazo() {
		return codMotivoRechazo;
	}
	/**
	 * @param codMotivoRechazo The codMotivoRechazo to set.
	 */
	public void setCodMotivoRechazo(String codMotivoRechazo) {
		this.codMotivoRechazo = codMotivoRechazo;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
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
	 * @return Returns the codTipoDocumento.
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento The codTipoDocumento to set.
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
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
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
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
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the numSecuencia.
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia The numSecuencia to set.
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	
	/**
	 * @return Returns the codCia.
	 */
	public String getCodCia() {
		return codCia;
	}
	/**
	 * @param codCia The codCia to set.
	 */
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	/**
	 * @return Returns the canVentaDese.
	 */
	public Integer getCanVentaDese() {
		return canVentaDese;
	}
	/**
	 * @param canVentaDese The canVentaDese to set.
	 */
	public void setCanVentaDese(Integer canVentaDese) {
		this.canVentaDese = canVentaDese;
	}
	/**
	 * @return Returns the canVentaDevu.
	 */
	public Integer getCanVentaDevu() {
		return canVentaDevu;
	}
	/**
	 * @param canVentaDevu The canVentaDevu to set.
	 */
	public void setCanVentaDevu(Integer canVentaDevu) {
		this.canVentaDevu = canVentaDevu;
	}
	/**
	 * @return Returns the codOperacion.
	 */
	public String getCodOperacion() {
		return codOperacion;
	}
	/**
	 * @param codOperacion The codOperacion to set.
	 */
	public void setCodOperacion(String codOperacion) {
		this.codOperacion = codOperacion;
	}
	/**
	 * @return Returns the codTipoOperacion.
	 */
	public String getCodTipoOperacion() {
		return codTipoOperacion;
	}
	/**
	 * @param codTipoOperacion The codTipoOperacion to set.
	 */
	public void setCodTipoOperacion(String codTipoOperacion) {
		this.codTipoOperacion = codTipoOperacion;
	}
	/**
	 * @return Returns the codVentaDese.
	 */
	public Integer getCodVentaDese() {
		return codVentaDese;
	}
	/**
	 * @param codVentaDese The codVentaDese to set.
	 */
	public void setCodVentaDese(Integer codVentaDese) {
		this.codVentaDese = codVentaDese;
	}
	/**
	 * @return Returns the codVentaDevu.
	 */
	public Integer getCodVentaDevu() {
		return codVentaDevu;
	}
	/**
	 * @param codVentaDevu The codVentaDevu to set.
	 */
	public void setCodVentaDevu(Integer codVentaDevu) {
		this.codVentaDevu = codVentaDevu;
	}
	/**
	 * @return Returns the tipoReferencia.
	 */
	public String getTipoReferencia() {
		return tipoReferencia;
	}
	/**
	 * @param tipoReferencia The tipoReferencia to set.
	 */
	public void setTipoReferencia(String tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}
	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMontoMinimo() {
		return montoMinimo;
	}
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	public String getValorDeseado() {
		return valorDeseado;
	}
	public void setValorDeseado(String valorDeseado) {
		this.valorDeseado = valorDeseado;
	}
	public String getValorDevuelve() {
		return valorDevuelve;
	}
	public void setValorDevuelve(String valorDevuelve) {
		this.valorDevuelve = valorDevuelve;
	}
	public String getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getNumeroCruce() {
		return numeroCruce;
	}
	public void setNumeroCruce(String numeroCruce) {
		this.numeroCruce = numeroCruce;
	}
	public String getPeriodoReferencia() {
		return periodoReferencia;
	}
	public void setPeriodoReferencia(String periodoReferencia) {
		this.periodoReferencia = periodoReferencia;
	}
	public String getOidSoliDevuelve() {
		return oidSoliDevuelve;
	}
	public void setOidSoliDevuelve(String oidSoliDevuelve) {
		this.oidSoliDevuelve = oidSoliDevuelve;
	}
	public String getPrecioUniDevuelve() {
		return precioUniDevuelve;
	}
	public void setPrecioUniDevuelve(String precioUniDevuelve) {
		this.precioUniDevuelve = precioUniDevuelve;
	}
	public String getOidSoli() {
		return oidSoli;
	}
	public void setOidSoli(String oidSoli) {
		this.oidSoli = oidSoli;
	}
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the usuarioRegistro
	 */
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	public String getFechaModificacion() {
		return fechaModificacion;
	}	
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}	
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}	
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}	
	public String getOrigen() {
		return origen;
	}	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
