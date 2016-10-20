package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCUPProgramaNuevCuponesNivelForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8103616064784027584L;
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String campanhaInicial;

	private String campanhaFinal;

	private String codigoVentCupIni;

	private String codigoVentCupFin;

	private String codigoMarca;
	
	private String descripcionMarca;

	private String codigoCanal;
	
	private String descripcionCanal;

	private String nivel;

	private String[] cuponesAsignados;

	private String[] cuponesNoAsignados;

	private String indicadorVigencia;
	private String numVigencia;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the campanhaInicial
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}
	/**
	 * @param campanhaInicial the campanhaInicial to set
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}
	/**
	 * @return the campanhaFinal
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}
	/**
	 * @param campanhaFinal the campanhaFinal to set
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}
	/**
	 * @return the codigoVentCupIni
	 */
	public String getCodigoVentCupIni() {
		return codigoVentCupIni;
	}
	/**
	 * @param codigoVentCupIni the codigoVentCupIni to set
	 */
	public void setCodigoVentCupIni(String codigoVentCupIni) {
		this.codigoVentCupIni = codigoVentCupIni;
	}
	/**
	 * @return the codigoVentCupFin
	 */
	public String getCodigoVentCupFin() {
		return codigoVentCupFin;
	}
	/**
	 * @param codigoVentCupFin the codigoVentCupFin to set
	 */
	public void setCodigoVentCupFin(String codigoVentCupFin) {
		this.codigoVentCupFin = codigoVentCupFin;
	}
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}
	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}
	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}
	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the cuponesAsignados
	 */
	public String[] getCuponesAsignados() {
		return cuponesAsignados;
	}
	/**
	 * @param cuponesAsignados the cuponesAsignados to set
	 */
	public void setCuponesAsignados(String[] cuponesAsignados) {
		this.cuponesAsignados = cuponesAsignados;
	}
	/**
	 * @return the cuponesNoAsignados
	 */
	public String[] getCuponesNoAsignados() {
		return cuponesNoAsignados;
	}
	/**
	 * @param cuponesNoAsignados the cuponesNoAsignados to set
	 */
	public void setCuponesNoAsignados(String[] cuponesNoAsignados) {
		this.cuponesNoAsignados = cuponesNoAsignados;
	}
	/**
	 * @return the indicadorVigencia
	 */
	public String getIndicadorVigencia() {
		return indicadorVigencia;
	}
	/**
	 * @param indicadorVigencia the indicadorVigencia to set
	 */
	public void setIndicadorVigencia(String indicadorVigencia) {
		this.indicadorVigencia = indicadorVigencia;
	}
	/**
	 * @return the numVigencia
	 */
	public String getNumVigencia() {
		return numVigencia;
	}
	/**
	 * @param numVigencia the numVigencia to set
	 */
	public void setNumVigencia(String numVigencia) {
		this.numVigencia = numVigencia;
	}
	
	

}
