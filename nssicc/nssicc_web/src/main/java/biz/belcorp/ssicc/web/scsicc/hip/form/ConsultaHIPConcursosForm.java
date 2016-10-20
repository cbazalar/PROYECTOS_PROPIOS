package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.math.BigDecimal;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */

public class ConsultaHIPConcursosForm extends BaseSearchForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 666725814923021207L;

	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String selectedItem;
	
	private String nroConcurso;
	private String descripcion;
	private String periodoInicio;
	private String periodoFin;
	private String nivelAlcanzado;
	private String situacion;
	private String saldoPuntaje;
	private String codigoDupla;
	private String nombreDupla;
	private String ventaBase;
	
	private String clasificacionLove;
	private boolean mostrarClasificacionLove;
	
	private BigDecimal puntajeTotal;
	private BigDecimal puntajeUtilizado;
	private BigDecimal saldo;
	private BigDecimal reservaCanjes;
	private BigDecimal saldoReservas;
	
	private boolean mostrarConsolidado;
	
	private String indicadorBasparampais;
	
	/**
	 * @return Returns the codConsultora.
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora The codConsultora to set.
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return Returns the desRegZonTerri.
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri The desRegZonTerri to set.
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @return Returns the nomConsultora.
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora The nomConsultora to set.
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return Returns the codigoDupla.
	 */
	public String getCodigoDupla() {
		return codigoDupla;
	}
	/**
	 * @param codigoDupla The codigoDupla to set.
	 */
	public void setCodigoDupla(String codigoDupla) {
		this.codigoDupla = codigoDupla;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Returns the nivelAlcanzado.
	 */
	public String getNivelAlcanzado() {
		return nivelAlcanzado;
	}
	/**
	 * @param nivelAlcanzado The nivelAlcanzado to set.
	 */
	public void setNivelAlcanzado(String nivelAlcanzado) {
		this.nivelAlcanzado = nivelAlcanzado;
	}
	/**
	 * @return Returns the nombreDupla.
	 */
	public String getNombreDupla() {
		return nombreDupla;
	}
	/**
	 * @param nombreDupla The nombreDupla to set.
	 */
	public void setNombreDupla(String nombreDupla) {
		this.nombreDupla = nombreDupla;
	}
	/**
	 * @return Returns the nroConcurso.
	 */
	public String getNroConcurso() {
		return nroConcurso;
	}
	/**
	 * @param nroConcurso The nroConcurso to set.
	 */
	public void setNroConcurso(String nroConcurso) {
		this.nroConcurso = nroConcurso;
	}
	/**
	 * @return Returns the periodoFin.
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}
	/**
	 * @param periodoFin The periodoFin to set.
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	/**
	 * @return Returns the periodoInicio.
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	/**
	 * @param periodoInicio The periodoInicio to set.
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	/**
	 * @return Returns the saldoPuntaje.
	 */
	public String getSaldoPuntaje() {
		return saldoPuntaje;
	}
	/**
	 * @param saldoPuntaje The saldoPuntaje to set.
	 */
	public void setSaldoPuntaje(String saldoPuntaje) {
		this.saldoPuntaje = saldoPuntaje;
	}
	/**
	 * @return Returns the situacion.
	 */
	public String getSituacion() {
		return situacion;
	}
	/**
	 * @param situacion The situacion to set.
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	/**
	 * @return Returns the ventaBase.
	 */
	public String getVentaBase() {
		return ventaBase;
	}
	/**
	 * @param ventaBase The ventaBase to set.
	 */
	public void setVentaBase(String ventaBase) {
		this.ventaBase = ventaBase;
	}
	/**
	 * @return the clasificacionLove
	 */
	public String getClasificacionLove() {
		return clasificacionLove;
	}
	/**
	 * @param clasificacionLove the clasificacionLove to set
	 */
	public void setClasificacionLove(String clasificacionLove) {
		this.clasificacionLove = clasificacionLove;
	}
	/**
	 * @return the mostrarClasificacionLove
	 */
	public boolean isMostrarClasificacionLove() {
		return mostrarClasificacionLove;
	}
	/**
	 * @param mostrarClasificacionLove the mostrarClasificacionLove to set
	 */
	public void setMostrarClasificacionLove(boolean mostrarClasificacionLove) {
		this.mostrarClasificacionLove = mostrarClasificacionLove;
	}
	/**
	 * @return the puntajeTotal
	 */
	public BigDecimal getPuntajeTotal() {
		return puntajeTotal;
	}
	/**
	 * @param puntajeTotal the puntajeTotal to set
	 */
	public void setPuntajeTotal(BigDecimal puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}
	/**
	 * @return the puntajeUtilizado
	 */
	public BigDecimal getPuntajeUtilizado() {
		return puntajeUtilizado;
	}
	/**
	 * @param puntajeUtilizado the puntajeUtilizado to set
	 */
	public void setPuntajeUtilizado(BigDecimal puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
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
	 * @return the mostrarConsolidado
	 */
	public boolean isMostrarConsolidado() {
		return mostrarConsolidado;
	}
	/**
	 * @param mostrarConsolidado the mostrarConsolidado to set
	 */
	public void setMostrarConsolidado(boolean mostrarConsolidado) {
		this.mostrarConsolidado = mostrarConsolidado;
	}
	/**
	 * @return the indicadorBasparampais
	 */
	public String getIndicadorBasparampais() {
		return indicadorBasparampais;
	}
	/**
	 * @param indicadorBasparampais the indicadorBasparampais to set
	 */
	public void setIndicadorBasparampais(String indicadorBasparampais) {
		this.indicadorBasparampais = indicadorBasparampais;
	}
	/**
	 * @return the reservaCanjes
	 */
	public BigDecimal getReservaCanjes() {
		return reservaCanjes;
	}
	/**
	 * @param reservaCanjes the reservaCanjes to set
	 */
	public void setReservaCanjes(BigDecimal reservaCanjes) {
		this.reservaCanjes = reservaCanjes;
	}
	/**
	 * @return the saldoReservas
	 */
	public BigDecimal getSaldoReservas() {
		return saldoReservas;
	}
	/**
	 * @param saldoReservas the saldoReservas to set
	 */
	public void setSaldoReservas(BigDecimal saldoReservas) {
		this.saldoReservas = saldoReservas;
	}
	
}
