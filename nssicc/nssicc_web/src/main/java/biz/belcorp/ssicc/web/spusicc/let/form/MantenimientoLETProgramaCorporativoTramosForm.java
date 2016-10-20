package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLETProgramaCorporativoTramosForm extends BaseEditForm{	
	
	private static final long serialVersionUID = 1335388722567710119L;
	
	private String codigoTramo;
	private String periodoInicioTramo;
	private String periodoFinTramo;
	private String retencion22Tramo;
	private String retencion33Tramo;
	private String retencion44Tramo;
	private String numeroCampanasCambiarNivel;
	private String evaluacionNivelExito;
	
	private String correlativo;
	private String estado;
	
	
	public String getCodigoTramo() {
		return codigoTramo;
	}
	
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	
	public String getPeriodoInicioTramo() {
		return periodoInicioTramo;
	}
	
	public void setPeriodoInicioTramo(String periodoInicioTramo) {
		this.periodoInicioTramo = periodoInicioTramo;
	}
	
	public String getPeriodoFinTramo() {
		return periodoFinTramo;
	}
	
	public void setPeriodoFinTramo(String periodoFinTramo) {
		this.periodoFinTramo = periodoFinTramo;
	}
	
	public String getRetencion22Tramo() {
		return retencion22Tramo;
	}
	
	public void setRetencion22Tramo(String retencion22Tramo) {
		this.retencion22Tramo = retencion22Tramo;
	}
	
	public String getRetencion33Tramo() {
		return retencion33Tramo;
	}
	
	public void setRetencion33Tramo(String retencion33Tramo) {
		this.retencion33Tramo = retencion33Tramo;
	}
	
	public String getRetencion44Tramo() {
		return retencion44Tramo;
	}
	
	public void setRetencion44Tramo(String retencion44Tramo) {
		this.retencion44Tramo = retencion44Tramo;
	}
	
	public String getCorrelativo() {
		return correlativo;
	}
	
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getNumeroCampanasCambiarNivel() {
		return numeroCampanasCambiarNivel;
	}
	
	public void setNumeroCampanasCambiarNivel(String numeroCampanasCambiarNivel) {
		this.numeroCampanasCambiarNivel = numeroCampanasCambiarNivel;
	}
	
	public String getEvaluacionNivelExito() {
		return evaluacionNivelExito;
	}
	
	public void setEvaluacionNivelExito(String evaluacionNivelExito) {
		this.evaluacionNivelExito = evaluacionNivelExito;
	}	

}
