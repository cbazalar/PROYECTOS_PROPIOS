package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano</a>
 * 
 */
public class ConsultaHIPEjecutivasForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;

	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;

	private String nivel;
	private String numeroContrato;
	private String periodoNombramiento;
	private String fechaIngreso;
	private String coberturaTerritorioDe;
	private String coberturaTerritorioAl;
	private String ciudad;
	private String etapa;

	private String sumMetaPedido;
	private String sumRealPedido;
	private String sumMetaIngreso;
	private String sumRealIngreso;
	private String sumMetaReca;
	private String sumRealReca;
	private String sumComisionBruta;
	private String sumComisionNeta;

	public String getCodConsultora() {
		return codConsultora;
	}

	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}

	public String getNomConsultora() {
		return nomConsultora;
	}

	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}

	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}

	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getPeriodoNombramiento() {
		return periodoNombramiento;
	}

	public void setPeriodoNombramiento(String periodoNombramiento) {
		this.periodoNombramiento = periodoNombramiento;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCoberturaTerritorioDe() {
		return coberturaTerritorioDe;
	}

	public void setCoberturaTerritorioDe(String coberturaTerritorioDe) {
		this.coberturaTerritorioDe = coberturaTerritorioDe;
	}

	public String getCoberturaTerritorioAl() {
		return coberturaTerritorioAl;
	}

	public void setCoberturaTerritorioAl(String coberturaTerritorioAl) {
		this.coberturaTerritorioAl = coberturaTerritorioAl;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getSumMetaPedido() {
		return sumMetaPedido;
	}

	public void setSumMetaPedido(String sumMetaPedido) {
		this.sumMetaPedido = sumMetaPedido;
	}

	public String getSumRealPedido() {
		return sumRealPedido;
	}

	public void setSumRealPedido(String sumRealPedido) {
		this.sumRealPedido = sumRealPedido;
	}

	public String getSumMetaIngreso() {
		return sumMetaIngreso;
	}

	public void setSumMetaIngreso(String sumMetaIngreso) {
		this.sumMetaIngreso = sumMetaIngreso;
	}

	public String getSumRealIngreso() {
		return sumRealIngreso;
	}

	public void setSumRealIngreso(String sumRealIngreso) {
		this.sumRealIngreso = sumRealIngreso;
	}

	public String getSumMetaReca() {
		return sumMetaReca;
	}

	public void setSumMetaReca(String sumMetaReca) {
		this.sumMetaReca = sumMetaReca;
	}

	public String getSumRealReca() {
		return sumRealReca;
	}

	public void setSumRealReca(String sumRealReca) {
		this.sumRealReca = sumRealReca;
	}

	public String getSumComisionBruta() {
		return sumComisionBruta;
	}

	public void setSumComisionBruta(String sumComisionBruta) {
		this.sumComisionBruta = sumComisionBruta;
	}

	public String getSumComisionNeta() {
		return sumComisionNeta;
	}

	public void setSumComisionNeta(String sumComisionNeta) {
		this.sumComisionNeta = sumComisionNeta;
	}

}