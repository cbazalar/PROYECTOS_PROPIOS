
package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * 
 * <p>
 * <a href="MantenimientoRECRecepcionCDRForm.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 * 
 */
public class MantenimientoRECRecepcionCDRForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String numeroCDR;
	private String codigoConsultora;
	private String nombreConsultora;
	private String codigoProducto;
	
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	private String descripcionZona;
	
	private String codigoPeriodoRecepcion;
	private String numeroPedido;
	private String codigoPeriodoPedido;
	private String codigoPeriodoAtencion;
	private String indicadorOrigen;
	private String contrcd;
	private String oidCabeReclamo;
	private String numeroReclamo;
	private String oidPeriodoPedido;
	private String oidPeriodoReclamo;
	private String indicadorEstado;
	
	private boolean flagMostrarLista;
	private boolean flagMostrarInsertar;
	
	private String[] listaCodigoCUV= {};
	private String[] listaCodigoSAP= {};
	private String[] listaDescripcion= {};
	private String[] listaMotivo= {};
	private String[] listaIndicadorExistencia= {};
	private String[] listaIndicadorBorrado= {};
	
	private Long[] listaOidCorrelativo= {};
	private Long[] listaOidProducto= {};
	private Long[] listaOidCabeReclamo= {};
	private Long[] listaOidOperReclamo= {};
	private Long[] listaOidLineaOperReclamo= {};
	private Long[] listaUnidRecibidas= {};
	private Long[] listaUnidDisponible= {};
	private Long[] listaUnidDestruccion= {};
	private Long[] listaUnidAprovec= {};
	private Integer[] listaNumeroRegistro = {};
	private Integer contadorRegistros;
	private Boolean habilitarSave;
	private String codigoSap;
	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	private String descripcionCorta;
	
	/**
	 * @return the listaNumeroRegistro
	 */
	public Integer[] getListaNumeroRegistro() {
		return listaNumeroRegistro;
	}

	/**
	 * @param listaNumeroRegistro the listaNumeroRegistro to set
	 */
	public void setListaNumeroRegistro(Integer[] listaNumeroRegistro) {
		this.listaNumeroRegistro = listaNumeroRegistro;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.numeroCDR = "";
		this.codigoConsultora = "";
		this.nombreConsultora = "";
		this.codigoProducto = "";
		this.codigoRegion = "";
		this.codigoZona = "";
		this.descripcionRegion = "";
		this.descripcionZona = "";
		
		this.codigoPeriodoRecepcion = "";
		this.numeroPedido= "";
		this.codigoPeriodoPedido = "";
		this.codigoPeriodoAtencion = "";
		this.indicadorOrigen= "";
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
	 * @return the numeroCDR
	 */
	public String getNumeroCDR() {
		return numeroCDR;
	}
	/**
	 * @param numeroCDR the numeroCDR to set
	 */
	public void setNumeroCDR(String numeroCDR) {
		this.numeroCDR = numeroCDR;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
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
	 * @return the codigoPeriodoRecepcion
	 */
	public String getCodigoPeriodoRecepcion() {
		return codigoPeriodoRecepcion;
	}
	/**
	 * @param codigoPeriodoRecepcion the codigoPeriodoRecepcion to set
	 */
	public void setCodigoPeriodoRecepcion(String codigoPeriodoRecepcion) {
		this.codigoPeriodoRecepcion = codigoPeriodoRecepcion;
	}
	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}
	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	/**
	 * @return the codigoPeriodoPedido
	 */
	public String getCodigoPeriodoPedido() {
		return codigoPeriodoPedido;
	}
	/**
	 * @param codigoPeriodoPedido the codigoPeriodoPedido to set
	 */
	public void setCodigoPeriodoPedido(String codigoPeriodoPedido) {
		this.codigoPeriodoPedido = codigoPeriodoPedido;
	}
	/**
	 * @return the codigoPeriodoAtencion
	 */
	public String getCodigoPeriodoAtencion() {
		return codigoPeriodoAtencion;
	}
	/**
	 * @param codigoPeriodoAtencion the codigoPeriodoAtencion to set
	 */
	public void setCodigoPeriodoAtencion(String codigoPeriodoAtencion) {
		this.codigoPeriodoAtencion = codigoPeriodoAtencion;
	}
	/**
	 * @return the flagMostrarLista
	 */
	public boolean isFlagMostrarLista() {
		return flagMostrarLista;
	}
	/**
	 * @param flagMostrarLista the flagMostrarLista to set
	 */
	public void setFlagMostrarLista(boolean flagMostrarLista) {
		this.flagMostrarLista = flagMostrarLista;
	}
	/**
	 * @return the flagMostrarInsertar
	 */
	public boolean isFlagMostrarInsertar() {
		return flagMostrarInsertar;
	}
	/**
	 * @param flagMostrarInsertar the flagMostrarInsertar to set
	 */
	public void setFlagMostrarInsertar(boolean flagMostrarInsertar) {
		this.flagMostrarInsertar = flagMostrarInsertar;
	}
	/**
	 * @return the listaCodigoCUV
	 */
	public String[] getListaCodigoCUV() {
		return listaCodigoCUV;
	}
	/**
	 * @param listaCodigoCUV the listaCodigoCUV to set
	 */
	public void setListaCodigoCUV(String[] listaCodigoCUV) {
		this.listaCodigoCUV = listaCodigoCUV;
	}
	/**
	 * @return the listaCodigoSAP
	 */
	public String[] getListaCodigoSAP() {
		return listaCodigoSAP;
	}
	/**
	 * @param listaCodigoSAP the listaCodigoSAP to set
	 */
	public void setListaCodigoSAP(String[] listaCodigoSAP) {
		this.listaCodigoSAP = listaCodigoSAP;
	}
	/**
	 * @return the listaDescripcion
	 */
	public String[] getListaDescripcion() {
		return listaDescripcion;
	}
	/**
	 * @param listaDescripcion the listaDescripcion to set
	 */
	public void setListaDescripcion(String[] listaDescripcion) {
		this.listaDescripcion = listaDescripcion;
	}
	/**
	 * @return the listaMotivo
	 */
	public String[] getListaMotivo() {
		return listaMotivo;
	}
	/**
	 * @param listaMotivo the listaMotivo to set
	 */
	public void setListaMotivo(String[] listaMotivo) {
		this.listaMotivo = listaMotivo;
	}
	
	

	/**
	 * @return the listaUnidRecibidas
	 */
	public Long[] getListaUnidRecibidas() {
		return listaUnidRecibidas;
	}

	/**
	 * @param listaUnidRecibidas the listaUnidRecibidas to set
	 */
	public void setListaUnidRecibidas(Long[] listaUnidRecibidas) {
		this.listaUnidRecibidas = listaUnidRecibidas;
	}

	/**
	 * @return the listaUnidDisponible
	 */
	public Long[] getListaUnidDisponible() {
		return listaUnidDisponible;
	}

	/**
	 * @param listaUnidDisponible the listaUnidDisponible to set
	 */
	public void setListaUnidDisponible(Long[] listaUnidDisponible) {
		this.listaUnidDisponible = listaUnidDisponible;
	}

	/**
	 * @return the listaUnidDestruccion
	 */
	public Long[] getListaUnidDestruccion() {
		return listaUnidDestruccion;
	}

	/**
	 * @param listaUnidDestruccion the listaUnidDestruccion to set
	 */
	public void setListaUnidDestruccion(Long[] listaUnidDestruccion) {
		this.listaUnidDestruccion = listaUnidDestruccion;
	}

	/**
	 * @return the listaUnidAprovec
	 */
	public Long[] getListaUnidAprovec() {
		return listaUnidAprovec;
	}

	/**
	 * @param listaUnidAprovec the listaUnidAprovec to set
	 */
	public void setListaUnidAprovec(Long[] listaUnidAprovec) {
		this.listaUnidAprovec = listaUnidAprovec;
	}

	/**
	 * @return the listaIndicadorBorrado
	 */
	public String[] getListaIndicadorBorrado() {
		return listaIndicadorBorrado;
	}

	/**
	 * @param listaIndicadorBorrado the listaIndicadorBorrado to set
	 */
	public void setListaIndicadorBorrado(String[] listaIndicadorBorrado) {
		this.listaIndicadorBorrado = listaIndicadorBorrado;
	}

	

	/**
	 * @return the listaIndicadorExistencia
	 */
	public String[] getListaIndicadorExistencia() {
		return listaIndicadorExistencia;
	}

	/**
	 * @param listaIndicadorExistencia the listaIndicadorExistencia to set
	 */
	public void setListaIndicadorExistencia(String[] listaIndicadorExistencia) {
		this.listaIndicadorExistencia = listaIndicadorExistencia;
	}

	/**
	 * @return the listaOidCorrelativo
	 */
	public Long[] getListaOidCorrelativo() {
		return listaOidCorrelativo;
	}

	/**
	 * @param listaOidCorrelativo the listaOidCorrelativo to set
	 */
	public void setListaOidCorrelativo(Long[] listaOidCorrelativo) {
		this.listaOidCorrelativo = listaOidCorrelativo;
	}

	/**
	 * @return the listaOidProducto
	 */
	public Long[] getListaOidProducto() {
		return listaOidProducto;
	}

	/**
	 * @param listaOidProducto the listaOidProducto to set
	 */
	public void setListaOidProducto(Long[] listaOidProducto) {
		this.listaOidProducto = listaOidProducto;
	}

	/**
	 * @return the listaOidCabeReclamo
	 */
	public Long[] getListaOidCabeReclamo() {
		return listaOidCabeReclamo;
	}

	/**
	 * @param listaOidCabeReclamo the listaOidCabeReclamo to set
	 */
	public void setListaOidCabeReclamo(Long[] listaOidCabeReclamo) {
		this.listaOidCabeReclamo = listaOidCabeReclamo;
	}

	/**
	 * @return the listaOidOperReclamo
	 */
	public Long[] getListaOidOperReclamo() {
		return listaOidOperReclamo;
	}

	/**
	 * @param listaOidOperReclamo the listaOidOperReclamo to set
	 */
	public void setListaOidOperReclamo(Long[] listaOidOperReclamo) {
		this.listaOidOperReclamo = listaOidOperReclamo;
	}

	/**
	 * @return the listaOidLineaOperReclamo
	 */
	public Long[] getListaOidLineaOperReclamo() {
		return listaOidLineaOperReclamo;
	}

	/**
	 * @param listaOidLineaOperReclamo the listaOidLineaOperReclamo to set
	 */
	public void setListaOidLineaOperReclamo(Long[] listaOidLineaOperReclamo) {
		this.listaOidLineaOperReclamo = listaOidLineaOperReclamo;
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
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}

	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
	}

	/**
	 * @return the contrcd
	 */
	public String getContrcd() {
		return contrcd;
	}

	/**
	 * @param contrcd the contrcd to set
	 */
	public void setContrcd(String contrcd) {
		this.contrcd = contrcd;
	}

	/**
	 * @return the oidCabeReclamo
	 */
	public String getOidCabeReclamo() {
		return oidCabeReclamo;
	}

	/**
	 * @param oidCabeReclamo the oidCabeReclamo to set
	 */
	public void setOidCabeReclamo(String oidCabeReclamo) {
		this.oidCabeReclamo = oidCabeReclamo;
	}

	/**
	 * @return the numeroReclamo
	 */
	public String getNumeroReclamo() {
		return numeroReclamo;
	}

	/**
	 * @param numeroReclamo the numeroReclamo to set
	 */
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}

	/**
	 * @return the oidPeriodoPedido
	 */
	public String getOidPeriodoPedido() {
		return oidPeriodoPedido;
	}

	/**
	 * @param oidPeriodoPedido the oidPeriodoPedido to set
	 */
	public void setOidPeriodoPedido(String oidPeriodoPedido) {
		this.oidPeriodoPedido = oidPeriodoPedido;
	}

	/**
	 * @return the oidPeriodoReclamo
	 */
	public String getOidPeriodoReclamo() {
		return oidPeriodoReclamo;
	}

	/**
	 * @param oidPeriodoReclamo the oidPeriodoReclamo to set
	 */
	public void setOidPeriodoReclamo(String oidPeriodoReclamo) {
		this.oidPeriodoReclamo = oidPeriodoReclamo;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the contadorRegistros
	 */
	public Integer getContadorRegistros() {
		return contadorRegistros;
	}

	/**
	 * @param contadorRegistros the contadorRegistros to set
	 */
	public void setContadorRegistros(Integer contadorRegistros) {
		this.contadorRegistros = contadorRegistros;
	}

	/**
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public Boolean getHabilitarSave() {
		return habilitarSave;
	}

	public void setHabilitarSave(Boolean habilitarSave) {
		this.habilitarSave = habilitarSave;
	}

	
	
}
