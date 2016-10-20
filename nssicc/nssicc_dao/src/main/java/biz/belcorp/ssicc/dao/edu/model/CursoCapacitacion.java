package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class CursoCapacitacion extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -858462478798731313L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String siglasCurso;
	private String objetivoCurso;
	private String estadoRegistro;
	private String codigoFrecuenciaCalificacion;
	private String descripcionFrecuenciaCalificacion;	
	private String campanhaInicial;
	private int	   numeroCampanhas;
	private int	   numeroCampanhasPreviaDictado;	
	private String codigoAmbitoDictado;
	private String descripcionAmbitoDictado;
	private int	   duracionCurso;
	private String prerequisitoCapacitacion;
	private String codigoSecuenciaPedido;
	private String descripcionSecuenciaPedido;	
	private int	   numeroCampanhasPrerequisito;
	private int    numeroCampanhasEvaluar;
	private int    numeroPedidosRequeridos;
	private String indicadorMorosidad;
	private String codigoNivelVenta;
	private String descripcionNivelVenta;	
	private String indicadorExoneracionAsistencia;
	private int	   numeroCampanhasCalificadasNueva;
	private String indicadorCostoCurso;
	private int	   numeroCampanhasMaximaAsistencia;
	private String indicadorInvitacionCurso;
	private String indicadorInvitacionCursoGlobal;
	private String indicadorDictadoExtemporaneo;
	private String estadoCurso;
	private String estadoCursoActual;
	private String indicadorRegaloAsistencia;
	private String indicadorEvaluacionCapacitada;
	private String indicadorEvaluacionCapacitadora;
	private String indicadorPrimerPedido;
	private String codigoProductoInvitacion;
	private String codigoProductoCurso;
	private String indicadorReporteGerenteZona;
	private String indicadorPaqueteDocumentario;
	private String numeroCampanhaInvitacion;
	private String codigoDocumentoImprimir;
	private Integer numeroCuotasPagar;
	private Double costoCurso;
	private String tipoCostoCurso;
	private String codigoClasificacion;
	private String indicadorCalificacionAmbito;//1:incluye ala calificacion 0:excluye ala calificacion
	private String indicadorCalificacionAmbitoRegion;//variables auxiliares del indicador ambito region
	private String indicadorCalificacionAmbitoZona;//variables auxiliares del indicador ambito zona
	//
	private String prerequisitoCapacitacionCondicion;//aca se caragaran los prerrequisitos de la nueva tabla como cadena
	//condiconal x example CURSO1 AND CURSO 2 OR CURSO3
	
	
	
	/**
	 * @return the prerequisitoCapacitacionCondicion
	 */
	public String getPrerequisitoCapacitacionCondicion() {
		return prerequisitoCapacitacionCondicion;
	}

	/**
	 * @param prerequisitoCapacitacionCondicion the prerequisitoCapacitacionCondicion to set
	 */
	public void setPrerequisitoCapacitacionCondicion(
			String prerequisitoCapacitacionCondicion) {
		this.prerequisitoCapacitacionCondicion = prerequisitoCapacitacionCondicion;
	}

	/**
	 * @return the indicadorCalificacionAmbitoRegion
	 */
	public String getIndicadorCalificacionAmbitoRegion() {
		return indicadorCalificacionAmbitoRegion;
	}

	/**
	 * @param indicadorCalificacionAmbitoRegion the indicadorCalificacionAmbitoRegion to set
	 */
	public void setIndicadorCalificacionAmbitoRegion(
			String indicadorCalificacionAmbitoRegion) {
		this.indicadorCalificacionAmbitoRegion = indicadorCalificacionAmbitoRegion;
	}

	/**
	 * @return the indicadorCalificacionAmbitoZona
	 */
	public String getIndicadorCalificacionAmbitoZona() {
		return indicadorCalificacionAmbitoZona;
	}

	/**
	 * @param indicadorCalificacionAmbitoZona the indicadorCalificacionAmbitoZona to set
	 */
	public void setIndicadorCalificacionAmbitoZona(
			String indicadorCalificacionAmbitoZona) {
		this.indicadorCalificacionAmbitoZona = indicadorCalificacionAmbitoZona;
	}

	/**
	 * @return the indicadorCalificacionAmbito
	 */
	public String getIndicadorCalificacionAmbito() {
		return indicadorCalificacionAmbito;
	}

	/**
	 * @param indicadorCalificacionAmbito the indicadorCalificacionAmbito to set
	 */
	public void setIndicadorCalificacionAmbito(String indicadorCalificacionAmbito) {
		this.indicadorCalificacionAmbito = indicadorCalificacionAmbito;
	}

	/**
	 * @return Returns the campanhaInicial.
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}

	/**
	 * @param campanhaInicial The campanhaInicial to set.
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}

	/**
	 * @return Returns the codigoAmbitoDictado.
	 */
	public String getCodigoAmbitoDictado() {
		return codigoAmbitoDictado;
	}

	/**
	 * @param codigoAmbitoDictado The codigoAmbitoDictado to set.
	 */
	public void setCodigoAmbitoDictado(String codigoAmbitoDictado) {
		this.codigoAmbitoDictado = codigoAmbitoDictado;
	}

	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
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
	 * @return Returns the codigoFrecuenciaCalificacion.
	 */
	public String getCodigoFrecuenciaCalificacion() {
		return codigoFrecuenciaCalificacion;
	}

	/**
	 * @param codigoFrecuenciaCalificacion The codigoFrecuenciaCalificacion to set.
	 */
	public void setCodigoFrecuenciaCalificacion(String codigoFrecuenciaCalificacion) {
		this.codigoFrecuenciaCalificacion = codigoFrecuenciaCalificacion;
	}

	/**
	 * @return Returns the codigoNivelVenta.
	 */
	public String getCodigoNivelVenta() {
		return codigoNivelVenta;
	}

	/**
	 * @param codigoNivelVenta The codigoNivelVenta to set.
	 */
	public void setCodigoNivelVenta(String codigoNivelVenta) {
		this.codigoNivelVenta = codigoNivelVenta;
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
	 * @return Returns the codigoSecuenciaPedido.
	 */
	public String getCodigoSecuenciaPedido() {
		return codigoSecuenciaPedido;
	}

	/**
	 * @param codigoSecuenciaPedido The codigoSecuenciaPedido to set.
	 */
	public void setCodigoSecuenciaPedido(String codigoSecuenciaPedido) {
		this.codigoSecuenciaPedido = codigoSecuenciaPedido;
	}

	/**
	 * @return Returns the duracionCurso.
	 */
	public int getDuracionCurso() {
		return duracionCurso;
	}

	/**
	 * @param duracionCurso The duracionCurso to set.
	 */
	public void setDuracionCurso(int duracionCurso) {
		this.duracionCurso = duracionCurso;
	}

	/**
	 * @return Returns the estadoCurso.
	 */
	public String getEstadoCurso() {
		return estadoCurso;
	}

	/**
	 * @param estadoCurso The estadoCurso to set.
	 */
	public void setEstadoCurso(String estadoCurso) {
		this.estadoCurso = estadoCurso;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the indicadorCostoCurso.
	 */
	public String getIndicadorCostoCurso() {
		return indicadorCostoCurso;
	}

	/**
	 * @param indicadorCostoCurso The indicadorCostoCurso to set.
	 */
	public void setIndicadorCostoCurso(String indicadorCostoCurso) {
		this.indicadorCostoCurso = indicadorCostoCurso;
	}

	/**
	 * @return Returns the indicadorDictadoExtemporaneo.
	 */
	public String getIndicadorDictadoExtemporaneo() {
		return indicadorDictadoExtemporaneo;
	}

	/**
	 * @param indicadorDictadoExtemporaneo The indicadorDictadoExtemporaneo to set.
	 */
	public void setIndicadorDictadoExtemporaneo(String indicadorDictadoExtemporaneo) {
		this.indicadorDictadoExtemporaneo = indicadorDictadoExtemporaneo;
	}

	/**
	 * @return Returns the indicadorEvaluacionCapacitada.
	 */
	public String getIndicadorEvaluacionCapacitada() {
		return indicadorEvaluacionCapacitada;
	}

	/**
	 * @param indicadorEvaluacionCapacitada The indicadorEvaluacionCapacitada to set.
	 */
	public void setIndicadorEvaluacionCapacitada(
			String indicadorEvaluacionCapacitada) {
		this.indicadorEvaluacionCapacitada = indicadorEvaluacionCapacitada;
	}

	/**
	 * @return Returns the indicadorExoneracionAsistencia.
	 */
	public String getIndicadorExoneracionAsistencia() {
		return indicadorExoneracionAsistencia;
	}

	/**
	 * @param indicadorExoneracionAsistencia The indicadorExoneracionAsistencia to set.
	 */
	public void setIndicadorExoneracionAsistencia(
			String indicadorExoneracionAsistencia) {
		this.indicadorExoneracionAsistencia = indicadorExoneracionAsistencia;
	}

	/**
	 * @return Returns the indicadorInvitacionCurso.
	 */
	public String getIndicadorInvitacionCurso() {
		return indicadorInvitacionCurso;
	}

	/**
	 * @param indicadorInvitacionCurso The indicadorInvitacionCurso to set.
	 */
	public void setIndicadorInvitacionCurso(String indicadorInvitacionCurso) {
		this.indicadorInvitacionCurso = indicadorInvitacionCurso;
	}

	/**
	 * @return Returns the indicadorMorosidad.
	 */
	public String getIndicadorMorosidad() {
		return indicadorMorosidad;
	}

	/**
	 * @param indicadorMorosidad The indicadorMorosidad to set.
	 */
	public void setIndicadorMorosidad(String indicadorMorosidad) {
		this.indicadorMorosidad = indicadorMorosidad;
	}

	

	/**
	 * @return Returns the indicadorPrimerPedido.
	 */
	public String getIndicadorPrimerPedido() {
		return indicadorPrimerPedido;
	}

	/**
	 * @param indicadorPrimerPedido The indicadorPrimerPedido to set.
	 */
	public void setIndicadorPrimerPedido(String indicadorPrimerPedido) {
		this.indicadorPrimerPedido = indicadorPrimerPedido;
	}

	/**
	 * @return Returns the indicadorRegaloAsistencia.
	 */
	public String getIndicadorRegaloAsistencia() {
		return indicadorRegaloAsistencia;
	}

	/**
	 * @param indicadorRegaloAsistencia The indicadorRegaloAsistencia to set.
	 */
	public void setIndicadorRegaloAsistencia(String indicadorRegaloAsistencia) {
		this.indicadorRegaloAsistencia = indicadorRegaloAsistencia;
	}

	/**
	 * @return Returns the nombreCurso.
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * @param nombreCurso The nombreCurso to set.
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * @return Returns the numeroCampanhas.
	 */
	public int getNumeroCampanhas() {
		return numeroCampanhas;
	}

	/**
	 * @param numeroCampanhas The numeroCampanhas to set.
	 */
	public void setNumeroCampanhas(int numeroCampanhas) {
		this.numeroCampanhas = numeroCampanhas;
	}

	/**
	 * @return Returns the numeroCampanhasCalificadasNueva.
	 */
	public int getNumeroCampanhasCalificadasNueva() {
		return numeroCampanhasCalificadasNueva;
	}

	/**
	 * @param numeroCampanhasCalificadasNueva The numeroCampanhasCalificadasNueva to set.
	 */
	public void setNumeroCampanhasCalificadasNueva(
			int numeroCampanhasCalificadasNueva) {
		this.numeroCampanhasCalificadasNueva = numeroCampanhasCalificadasNueva;
	}

	/**
	 * @return Returns the numeroCampanhasEvaluar.
	 */
	public int getNumeroCampanhasEvaluar() {
		return numeroCampanhasEvaluar;
	}

	/**
	 * @param numeroCampanhasEvaluar The numeroCampanhasEvaluar to set.
	 */
	public void setNumeroCampanhasEvaluar(int numeroCampanhasEvaluar) {
		this.numeroCampanhasEvaluar = numeroCampanhasEvaluar;
	}

	/**
	 * @return Returns the numeroCampanhasMaximaAsistencia.
	 */
	public int getNumeroCampanhasMaximaAsistencia() {
		return numeroCampanhasMaximaAsistencia;
	}

	/**
	 * @param numeroCampanhasMaximaAsistencia The numeroCampanhasMaximaAsistencia to set.
	 */
	public void setNumeroCampanhasMaximaAsistencia(
			int numeroCampanhasMaximaAsistencia) {
		this.numeroCampanhasMaximaAsistencia = numeroCampanhasMaximaAsistencia;
	}

	/**
	 * @return Returns the numeroCampanhasPrerequisito.
	 */
	public int getNumeroCampanhasPrerequisito() {
		return numeroCampanhasPrerequisito;
	}

	/**
	 * @param numeroCampanhasPrerequisito The numeroCampanhasPrerequisito to set.
	 */
	public void setNumeroCampanhasPrerequisito(int numeroCampanhasPrerequisito) {
		this.numeroCampanhasPrerequisito = numeroCampanhasPrerequisito;
	}

	/**
	 * @return Returns the numeroPedidosRequeridos.
	 */
	public int getNumeroPedidosRequeridos() {
		return numeroPedidosRequeridos;
	}

	/**
	 * @param numeroPedidosRequeridos The numeroPedidosRequeridos to set.
	 */
	public void setNumeroPedidosRequeridos(int numeroPedidosRequeridos) {
		this.numeroPedidosRequeridos = numeroPedidosRequeridos;
	}

	/**
	 * @return Returns the objetivoCurso.
	 */
	public String getObjetivoCurso() {
		return objetivoCurso;
	}

	/**
	 * @param objetivoCurso The objetivoCurso to set.
	 */
	public void setObjetivoCurso(String objetivoCurso) {
		this.objetivoCurso = objetivoCurso;
	}

	/**
	 * @return Returns the prerequisitoCapacitacion.
	 */
	public String getPrerequisitoCapacitacion() {
		return prerequisitoCapacitacion;
	}

	/**
	 * @param prerequisitoCapacitacion The prerequisitoCapacitacion to set.
	 */
	public void setPrerequisitoCapacitacion(String prerequisitoCapacitacion) {
		this.prerequisitoCapacitacion = prerequisitoCapacitacion;
	}

	/**
	 * @return Returns the siglasCurso.
	 */
	public String getSiglasCurso() {
		return siglasCurso;
	}

	/**
	 * @param siglasCurso The siglasCurso to set.
	 */
	public void setSiglasCurso(String siglasCurso) {
		this.siglasCurso = siglasCurso;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return Returns the descripcionAmbitoDictado.
	 */
	public String getDescripcionAmbitoDictado() {
		return descripcionAmbitoDictado;
	}

	/**
	 * @param descripcionAmbitoDictado The descripcionAmbitoDictado to set.
	 */
	public void setDescripcionAmbitoDictado(String descripcionAmbitoDictado) {
		this.descripcionAmbitoDictado = descripcionAmbitoDictado;
	}

	/**
	 * @return Returns the descripcionFrecuenciaCalificacion.
	 */
	public String getDescripcionFrecuenciaCalificacion() {
		return descripcionFrecuenciaCalificacion;
	}

	/**
	 * @param descripcionFrecuenciaCalificacion The descripcionFrecuenciaCalificacion to set.
	 */
	public void setDescripcionFrecuenciaCalificacion(
			String descripcionFrecuenciaCalificacion) {
		this.descripcionFrecuenciaCalificacion = descripcionFrecuenciaCalificacion;
	}

	/**
	 * @return Returns the descripcionNivelVenta.
	 */
	public String getDescripcionNivelVenta() {
		return descripcionNivelVenta;
	}

	/**
	 * @param descripcionNivelVenta The descripcionNivelVenta to set.
	 */
	public void setDescripcionNivelVenta(String descripcionNivelVenta) {
		this.descripcionNivelVenta = descripcionNivelVenta;
	}

	/**
	 * @return Returns the descripcionSecuenciaPedido.
	 */
	public String getDescripcionSecuenciaPedido() {
		return descripcionSecuenciaPedido;
	}

	/**
	 * @param descripcionSecuenciaPedido The descripcionSecuenciaPedido to set.
	 */
	public void setDescripcionSecuenciaPedido(String descripcionSecuenciaPedido) {
		this.descripcionSecuenciaPedido = descripcionSecuenciaPedido;
	}

	/**
	 * @return Returns the numeroCampanhasPreviaDictado.
	 */
	public int getNumeroCampanhasPreviaDictado() {
		return numeroCampanhasPreviaDictado;
	}

	/**
	 * @param numeroCampanhasPreviaDictado The numeroCampanhasPreviaDictado to set.
	 */
	public void setNumeroCampanhasPreviaDictado(int numeroCampanhasPreviaDictado) {
		this.numeroCampanhasPreviaDictado = numeroCampanhasPreviaDictado;
	}

	
	/**
	 * @return Returns the codigoProductoInvitacion.
	 */
	public String getCodigoProductoInvitacion() {
		return codigoProductoInvitacion;
	}

	/**
	 * @param codigoProductoInvitacion The codigoProductoInvitacion to set.
	 */
	public void setCodigoProductoInvitacion(String codigoProductoInvitacion) {
		this.codigoProductoInvitacion = codigoProductoInvitacion;
	}

	/**
	 * @return Returns the codigoProductoCurso.
	 */
	public String getCodigoProductoCurso() {
		return codigoProductoCurso;
	}

	/**
	 * @param codigoProductoCurso The codigoProductoCurso to set.
	 */
	public void setCodigoProductoCurso(String codigoProductoCurso) {
		this.codigoProductoCurso = codigoProductoCurso;
	}

	/**
	 * @return Returns the indicadorPaqueteDocumentario.
	 */
	public String getIndicadorPaqueteDocumentario() {
		return indicadorPaqueteDocumentario;
	}

	/**
	 * @param indicadorPaqueteDocumentario The indicadorPaqueteDocumentario to set.
	 */
	public void setIndicadorPaqueteDocumentario(String indicadorPaqueteDocumentario) {
		this.indicadorPaqueteDocumentario = indicadorPaqueteDocumentario;
	}

	/**
	 * @return Returns the indicadorReporteGerenteZona.
	 */
	public String getIndicadorReporteGerenteZona() {
		return indicadorReporteGerenteZona;
	}

	/**
	 * @param indicadorReporteGerenteZona The indicadorReporteGerenteZona to set.
	 */
	public void setIndicadorReporteGerenteZona(String indicadorReporteGerenteZona) {
		this.indicadorReporteGerenteZona = indicadorReporteGerenteZona;
	}

	/**
	 * @return Returns the numeroCampanhaInvitacion.
	 */
	public String getNumeroCampanhaInvitacion() {
		return numeroCampanhaInvitacion;
	}

	/**
	 * @param numeroCampanhaInvitacion The numeroCampanhaInvitacion to set.
	 */
	public void setNumeroCampanhaInvitacion(String numeroCampanhaInvitacion) {
		this.numeroCampanhaInvitacion = numeroCampanhaInvitacion;
	}

	/**
	 * @return Returns the codigoDocumentoImprimir.
	 */
	public String getCodigoDocumentoImprimir() {
		return codigoDocumentoImprimir;
	}

	/**
	 * @param codigoDocumentoImprimir The codigoDocumentoImprimir to set.
	 */
	public void setCodigoDocumentoImprimir(String codigoDocumentoImprimir) {
		this.codigoDocumentoImprimir = codigoDocumentoImprimir;
	}

	/**
	 * @return Returns the costoCurso.
	 */
	public Double getCostoCurso() {
		return costoCurso;
	}

	/**
	 * @param costoCurso The costoCurso to set.
	 */
	public void setCostoCurso(Double costoCurso) {
		this.costoCurso = costoCurso;
	}

	/**
	 * @return Returns the numeroCuotasPagar.
	 */
	public Integer getNumeroCuotasPagar() {
		return numeroCuotasPagar;
	}

	/**
	 * @param numeroCuotasPagar The numeroCuotasPagar to set.
	 */
	public void setNumeroCuotasPagar(Integer numeroCuotasPagar) {
		this.numeroCuotasPagar = numeroCuotasPagar;
	}

	/**
	 * @return Returns the tipoCostoCurso.
	 */
	public String getTipoCostoCurso() {
		return tipoCostoCurso;
	}

	/**
	 * @param tipoCostoCurso The tipoCostoCurso to set.
	 */
	public void setTipoCostoCurso(String tipoCostoCurso) {
		this.tipoCostoCurso = tipoCostoCurso;
	}

	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return Returns the estadoCursoActual.
	 */
	public String getEstadoCursoActual() {
		return estadoCursoActual;
	}

	/**
	 * @param estadoCursoActual The estadoCursoActual to set.
	 */
	public void setEstadoCursoActual(String estadoCursoActual) {
		this.estadoCursoActual = estadoCursoActual;
	}

	public String getIndicadorInvitacionCursoGlobal() {
		return indicadorInvitacionCursoGlobal;
	}

	public void setIndicadorInvitacionCursoGlobal(
			String indicadorInvitacionCursoGlobal) {
		this.indicadorInvitacionCursoGlobal = indicadorInvitacionCursoGlobal;
	}

	/**
	 * @return Returns the indicadorEvaluacionCapacitadora.
	 */
	public String getIndicadorEvaluacionCapacitadora() {
		return indicadorEvaluacionCapacitadora;
	}

	/**
	 * @param indicadorEvaluacionCapacitadora The indicadorEvaluacionCapacitadora to set.
	 */
	public void setIndicadorEvaluacionCapacitadora(
			String indicadorEvaluacionCapacitadora) {
		this.indicadorEvaluacionCapacitadora = indicadorEvaluacionCapacitadora;
	}
	
	

	
	
}
