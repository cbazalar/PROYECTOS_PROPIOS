package biz.belcorp.ssicc.reportes.web.edu.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoEDUGenerarPlanillaProgramacionForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 * 
 */

public class ProcesoEDUGenerarPlanillaProgramacionForm extends BaseReporteForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1903575314897313393L;

	private String codigoPais;
	
	private String codigoEmpresa;
	
	private String codigoProcesoBatch;
	
	private String[] regiones;
	
	private String tipoProceso;
	
	private String campanhaProceso;
	
	private String indicadorEnvioProgramadas; 
	
	private String indicadorEnvioResumen;
	
	private String nivelUnidadAdm;//indica si genera planilla siguien el standar anterior
	
	private String indicadorConsultoraRezagada;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String indicadorReporte;
	
	private String nivelGenerarPlanilla;
	
	private List regionList;

	private String usuarioLogin;
	
	/**
	 * @param indicadorConsultoraRezagada The indicadorConsultoraRezagada to set.
	 */
	public void setIndicadorConsultoraRezagada(String indicadorConsultoraRezagada) {
		this.indicadorConsultoraRezagada = indicadorConsultoraRezagada;
	}

	/**
	 * @return Returns the regiones.
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones The regiones to set. 
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.	 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return Returns the tipoProceso.
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso The tipoProceso to set.
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/**
	 * @return Returns the campanhaProceso.
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	/**
	 * @param campanhaProceso The campanhaProceso to set.	 
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
		this.codigoPeriodo = campanhaProceso;
	}

	/**
	 * @return Returns the indicadorEnvioProgramadas.
	 */
	public String getIndicadorEnvioProgramadas() {
		return indicadorEnvioProgramadas;
	}

	/**
	 * @param indicadorEnvioProgramadas The indicadorEnvioProgramadas to set.
	 */
	public void setIndicadorEnvioProgramadas(String indicadorEnvioProgramadas) {
		this.indicadorEnvioProgramadas = indicadorEnvioProgramadas;
	}

	/**
	 * @return Returns the indicadorEnvioResumen.
	 */
	public String getIndicadorEnvioResumen() {
		return indicadorEnvioResumen;
	}

	/**
	 * @param indicadorEnvioResumen The indicadorEnvioResumen to set.
	 */
	public void setIndicadorEnvioResumen(String indicadorEnvioResumen) {
		this.indicadorEnvioResumen = indicadorEnvioResumen;
	}

	/**
	 * @return Returns the nivelUnidadAdm.
	 */
	public String getNivelUnidadAdm() {
		return nivelUnidadAdm;
	}

	/**
	 * @param nivelUnidadAdm The nivelUnidadAdm to set.
	 */
	public void setNivelUnidadAdm(String nivelUnidadAdm) {
		this.nivelUnidadAdm = nivelUnidadAdm;
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
	 * @return the indicadorReporte
	 */
	public String getIndicadorReporte() {
		return indicadorReporte;
	}

	/**
	 * @param indicadorReporte the indicadorReporte to set
	 */
	public void setIndicadorReporte(String indicadorReporte) {
		this.indicadorReporte = indicadorReporte;
	}
	
	/**
	 * @return the nivelGenerarPlanilla
	 */
	public String getNivelGenerarPlanilla() {
		return nivelGenerarPlanilla;
	}

	/**
	 * @param nivelGenerarPlanilla the nivelGenerarPlanilla to set
	 */
	public void setNivelGenerarPlanilla(String nivelGenerarPlanilla) {
		this.nivelGenerarPlanilla = nivelGenerarPlanilla;
	}
	
	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the regionList
	 */
	public List getRegionList() {
		return regionList;
	}

	/**
	 * @return Returns the indicadorConsultoraRezagada.
	 */
	public String getIndicadorConsultoraRezagada() {
		return indicadorConsultoraRezagada;
	}
	
	/**
	 * @return the usuarioLogin
	 */
	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
}
