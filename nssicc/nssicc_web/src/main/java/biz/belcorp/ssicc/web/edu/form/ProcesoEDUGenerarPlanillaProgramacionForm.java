package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDUGenerarPlanillaProgramacionForm extends BaseProcesoForm
implements Serializable{


	private static final long serialVersionUID = 8060634826047583240L;
	
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
	
	
	public String getIndicadorConsultoraRezagada() {
		return indicadorConsultoraRezagada;
	}

	public void setIndicadorConsultoraRezagada(String indicadorConsultoraRezagada) {
		this.indicadorConsultoraRezagada = indicadorConsultoraRezagada;
	}

	public String[] getRegiones() {
		return regiones;
	}

	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	public String getTipoProceso() {
		return tipoProceso;
	}

	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}

	public String getIndicadorEnvioProgramadas() {
		return indicadorEnvioProgramadas;
	}

	public void setIndicadorEnvioProgramadas(String indicadorEnvioProgramadas) {
		this.indicadorEnvioProgramadas = indicadorEnvioProgramadas;
	}

	public String getIndicadorEnvioResumen() {
		return indicadorEnvioResumen;
	}

	public void setIndicadorEnvioResumen(String indicadorEnvioResumen) {
		this.indicadorEnvioResumen = indicadorEnvioResumen;
	}

	public String getNivelUnidadAdm() {
		return nivelUnidadAdm;
	}

	public void setNivelUnidadAdm(String nivelUnidadAdm) {
		this.nivelUnidadAdm = nivelUnidadAdm;
	}
}
