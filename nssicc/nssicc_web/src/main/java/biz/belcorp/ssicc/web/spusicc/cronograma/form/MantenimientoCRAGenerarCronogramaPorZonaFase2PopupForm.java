package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCRAGenerarCronogramaPorZonaFase2PopupForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887568676476409138L;
	private String codigoPais;
	private String codigoPeriodo;
	private String oidGrupoZona;
	private String nombreGrupoZona;
	private String zonaReferencia;
	private String[] zonaRegenerar;
	private String[] actividades;
	
	private String salirPantalla = "N";

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getOidGrupoZona() {
		return oidGrupoZona;
	}

	public void setOidGrupoZona(String oidGrupoZona) {
		this.oidGrupoZona = oidGrupoZona;
	}

	public String getNombreGrupoZona() {
		return nombreGrupoZona;
	}

	public void setNombreGrupoZona(String nombreGrupoZona) {
		this.nombreGrupoZona = nombreGrupoZona;
	}

	public String getZonaReferencia() {
		return zonaReferencia;
	}

	public void setZonaReferencia(String zonaReferencia) {
		this.zonaReferencia = zonaReferencia;
	}

	public String[] getZonaRegenerar() {
		return zonaRegenerar;
	}

	public void setZonaRegenerar(String[] zonaRegenerar) {
		this.zonaRegenerar = zonaRegenerar;
	}

	public String[] getActividades() {
		return actividades;
	}

	public void setActividades(String[] actividades) {
		this.actividades = actividades;
	}

	public String getSalirPantalla() {
		return salirPantalla;
	}

	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}
	
	

}
