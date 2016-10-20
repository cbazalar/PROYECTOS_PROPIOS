package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



public class MantenimientoLETLideresForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoRegion;
	private String codigoZona;
	
	private String codigoClienteBuscar;
	private boolean permitirIngresoCodigoCliente;
	
	private String indicadorReingreso;
	private boolean mostrarMensajeReingreo;
	private String mensajeReingreo;
	//sb 
	private String indicadorUnicoLiderSeccion;
	private String mensajeUnicoLiderSeccion;
	private String indicadorNoValidaUnicoLider;
	
	private String indicadorAsignarLider;
	private String indicadorNombramiento;
	
	
	private String codigoPeriodo;
	
	private String tipo;
	
	private String codigoMotivoDesvinculacion;
	
	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the indicadorNoValidaUnicoLider
	 */
	public String getIndicadorNoValidaUnicoLider() {
		return indicadorNoValidaUnicoLider;
	}
	/**
	 * @param indicadorNoValidaUnicoLider the indicadorNoValidaUnicoLider to set
	 */
	public void setIndicadorNoValidaUnicoLider(String indicadorNoValidaUnicoLider) {
		this.indicadorNoValidaUnicoLider = indicadorNoValidaUnicoLider;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @return the indicadorUnicoLiderSeccion
	 */
	public String getIndicadorUnicoLiderSeccion() {
		return indicadorUnicoLiderSeccion;
	}
	/**
	 * @param indicadorUnicoLiderSeccion the indicadorUnicoLiderSeccion to set
	 */
	public void setIndicadorUnicoLiderSeccion(String indicadorUnicoLiderSeccion) {
		this.indicadorUnicoLiderSeccion = indicadorUnicoLiderSeccion;
	}
	/**
	 * @return the mensajeUnicoLiderSeccion
	 */
	public String getMensajeUnicoLiderSeccion() {
		return mensajeUnicoLiderSeccion;
	}
	/**
	 * @param mensajeUnicoLiderSeccion the mensajeUnicoLiderSeccion to set
	 */
	public void setMensajeUnicoLiderSeccion(String mensajeUnicoLiderSeccion) {
		this.mensajeUnicoLiderSeccion = mensajeUnicoLiderSeccion;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca the codigoMarca to set
     *
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal the codigoCanal to set
     *
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
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
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}
	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}
	/**
	 * @return the permitirIngresoCodigoCliente
	 */
	public boolean isPermitirIngresoCodigoCliente() {
		return permitirIngresoCodigoCliente;
	}
	/**
	 * @param permitirIngresoCodigoCliente the permitirIngresoCodigoCliente to set
	 */
	public void setPermitirIngresoCodigoCliente(boolean permitirIngresoCodigoCliente) {
		this.permitirIngresoCodigoCliente = permitirIngresoCodigoCliente;
	}
	/**
	 * @return the indicadorReingreso
	 */
	public String getIndicadorReingreso() {
		return indicadorReingreso;
	}
	/**
	 * @param indicadorReingreso the indicadorReingreso to set
	 */
	public void setIndicadorReingreso(String indicadorReingreso) {
		this.indicadorReingreso = indicadorReingreso;
	}
	/**
	 * @return the mostrarMensajeReingreo
	 */
	public boolean isMostrarMensajeReingreo() {
		return mostrarMensajeReingreo;
	}
	/**
	 * @param mostrarMensajeReingreo the mostrarMensajeReingreo to set
	 */
	public void setMostrarMensajeReingreo(boolean mostrarMensajeReingreo) {
		this.mostrarMensajeReingreo = mostrarMensajeReingreo;
	}
	/**
	 * @return the mensajeReingreo
	 */
	public String getMensajeReingreo() {
		return mensajeReingreo;
	}
	/**
	 * @param mensajeReingreo the mensajeReingreo to set
	 */
	public void setMensajeReingreo(String mensajeReingreo) {
		this.mensajeReingreo = mensajeReingreo;
	}
	/**
	 * @return the indicadorAsignarLider
	 */
	public String getIndicadorAsignarLider() {
		return indicadorAsignarLider;
	}
	/**
	 * @param indicadorAsignarLider the indicadorAsignarLider to set
	 */
	public void setIndicadorAsignarLider(String indicadorAsignarLider) {
		this.indicadorAsignarLider = indicadorAsignarLider;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the indicadorNombramiento
	 */
	public String getIndicadorNombramiento() {
		return indicadorNombramiento;
	}

	/**
	 * @param indicadorNombramiento the indicadorNombramiento to set
	 */
	public void setIndicadorNombramiento(String indicadorNombramiento) {
		this.indicadorNombramiento = indicadorNombramiento;
	}

	/**
	 * @return the codigoMotivoDesvinculacion
	 */
	public String getCodigoMotivoDesvinculacion() {
		return codigoMotivoDesvinculacion;
	}

	/**
	 * @param codigoMotivoDesvinculacion the codigoMotivoDesvinculacion to set
	 */
	public void setCodigoMotivoDesvinculacion(String codigoMotivoDesvinculacion) {
		this.codigoMotivoDesvinculacion = codigoMotivoDesvinculacion;
	}
	
	
}

