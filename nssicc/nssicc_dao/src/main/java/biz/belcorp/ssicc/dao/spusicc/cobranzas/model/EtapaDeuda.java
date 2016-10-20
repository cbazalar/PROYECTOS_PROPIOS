package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jorge Florencio Arias
 *
 */
public class EtapaDeuda extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String codigoPais;
	private String codigoEtapaDeuda;	
	private String descripcionEtapaDeuda;
	private String valorEdadInicial;	
	private String valorEdadFinal;
	private String importeDesde;
	private String importeHasta;	
	private String numeroSecuenciaEtapa;	
	private String numeroDiasGestion;
	private String codigoUsuarioSupervisor;
	private String importeMinimoVisualizacion;
	private String indicadorTipoBalanceo;
	private String indicadorEtapaFinal;
	private String indicadorAsignacionEtapaAnterior;
	private String indicadorAsignacionEtapaPosterior;
	private String indicadorTelefono;
	private String indicadorGeneracionLunes;
	private String indicadorCierreQuincena;
	private String indicadorCierreFinMes;
	private String indicadorGeneracionArchivo;
	private String indicadorActivo;
	
	private String numeroDiasVencimiento;
	private String numeroDiasEspera;	
	
	public EtapaDeuda() {
	
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
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}

	/**
	 * @return the descripcionEtapaDeuda
	 */
	public String getDescripcionEtapaDeuda() {
		return descripcionEtapaDeuda;
	}

	/**
	 * @param descripcionEtapaDeuda the descripcionEtapaDeuda to set
	 */
	public void setDescripcionEtapaDeuda(String descripcionEtapaDeuda) {
		this.descripcionEtapaDeuda = descripcionEtapaDeuda;
	}

	/**
	 * @return the valorEdadInicial
	 */
	public String getValorEdadInicial() {
		return valorEdadInicial;
	}

	/**
	 * @param valorEdadInicial the valorEdadInicial to set
	 */
	public void setValorEdadInicial(String valorEdadInicial) {
		this.valorEdadInicial = valorEdadInicial;
	}

	/**
	 * @return the valorEdadFinal
	 */
	public String getValorEdadFinal() {
		return valorEdadFinal;
	}

	/**
	 * @param valorEdadFinal the valorEdadFinal to set
	 */
	public void setValorEdadFinal(String valorEdadFinal) {
		this.valorEdadFinal = valorEdadFinal;
	}

	/**
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde the importeDesde to set
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * @return the importeHasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * @param importeHasta the importeHasta to set
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * @return the numeroSecuenciaEtapa
	 */
	public String getNumeroSecuenciaEtapa() {
		return numeroSecuenciaEtapa;
	}

	/**
	 * @param numeroSecuenciaEtapa the numeroSecuenciaEtapa to set
	 */
	public void setNumeroSecuenciaEtapa(String numeroSecuenciaEtapa) {
		this.numeroSecuenciaEtapa = numeroSecuenciaEtapa;
	}

	/**
	 * @return the numeroDiasGestion
	 */
	public String getNumeroDiasGestion() {
		return numeroDiasGestion;
	}

	/**
	 * @param numeroDiasGestion the numeroDiasGestion to set
	 */
	public void setNumeroDiasGestion(String numeroDiasGestion) {
		this.numeroDiasGestion = numeroDiasGestion;
	}

	/**
	 * @return the codigoUsuarioSupervisor
	 */
	public String getCodigoUsuarioSupervisor() {
		return codigoUsuarioSupervisor;
	}

	/**
	 * @param codigoUsuarioSupervisor the codigoUsuarioSupervisor to set
	 */
	public void setCodigoUsuarioSupervisor(String codigoUsuarioSupervisor) {
		this.codigoUsuarioSupervisor = codigoUsuarioSupervisor;
	}

	/**
	 * @return the importeMinimoVisualizacion
	 */
	public String getImporteMinimoVisualizacion() {
		return importeMinimoVisualizacion;
	}

	/**
	 * @param importeMinimoVisualizacion the importeMinimoVisualizacion to set
	 */
	public void setImporteMinimoVisualizacion(String importeMinimoVisualizacion) {
		this.importeMinimoVisualizacion = importeMinimoVisualizacion;
	}

	/**
	 * @return the indicadorTipoBalanceo
	 */
	public String getIndicadorTipoBalanceo() {
		return indicadorTipoBalanceo;
	}

	/**
	 * @param indicadorTipoBalanceo the indicadorTipoBalanceo to set
	 */
	public void setIndicadorTipoBalanceo(String indicadorTipoBalanceo) {
		this.indicadorTipoBalanceo = indicadorTipoBalanceo;
	}

	/**
	 * @return the indicadorEtapaFinal
	 */
	public String getIndicadorEtapaFinal() {
		return indicadorEtapaFinal;
	}

	/**
	 * @param indicadorEtapaFinal the indicadorEtapaFinal to set
	 */
	public void setIndicadorEtapaFinal(String indicadorEtapaFinal) {
		this.indicadorEtapaFinal = indicadorEtapaFinal;
	}

	/**
	 * @return the indicadorAsignacionEtapaAnterior
	 */
	public String getIndicadorAsignacionEtapaAnterior() {
		return indicadorAsignacionEtapaAnterior;
	}

	/**
	 * @param indicadorAsignacionEtapaAnterior the indicadorAsignacionEtapaAnterior to set
	 */
	public void setIndicadorAsignacionEtapaAnterior(
			String indicadorAsignacionEtapaAnterior) {
		this.indicadorAsignacionEtapaAnterior = indicadorAsignacionEtapaAnterior;
	}

	/**
	 * @return the indicadorAsignacionEtapaPosterior
	 */
	public String getIndicadorAsignacionEtapaPosterior() {
		return indicadorAsignacionEtapaPosterior;
	}

	/**
	 * @param indicadorAsignacionEtapaPosterior the indicadorAsignacionEtapaPosterior to set
	 */
	public void setIndicadorAsignacionEtapaPosterior(
			String indicadorAsignacionEtapaPosterior) {
		this.indicadorAsignacionEtapaPosterior = indicadorAsignacionEtapaPosterior;
	}

	/**
	 * @return the indicadorTelefono
	 */
	public String getIndicadorTelefono() {
		return indicadorTelefono;
	}

	/**
	 * @param indicadorTelefono the indicadorTelefono to set
	 */
	public void setIndicadorTelefono(String indicadorTelefono) {
		this.indicadorTelefono = indicadorTelefono;
	}

	/**
	 * @return the indicadorGeneracionLunes
	 */
	public String getIndicadorGeneracionLunes() {
		return indicadorGeneracionLunes;
	}

	/**
	 * @param indicadorGeneracionLunes the indicadorGeneracionLunes to set
	 */
	public void setIndicadorGeneracionLunes(String indicadorGeneracionLunes) {
		this.indicadorGeneracionLunes = indicadorGeneracionLunes;
	}

	/**
	 * @return the indicadorCierreQuincena
	 */
	public String getIndicadorCierreQuincena() {
		return indicadorCierreQuincena;
	}

	/**
	 * @param indicadorCierreQuincena the indicadorCierreQuincena to set
	 */
	public void setIndicadorCierreQuincena(String indicadorCierreQuincena) {
		this.indicadorCierreQuincena = indicadorCierreQuincena;
	}

	/**
	 * @return the indicadorCierreFinMes
	 */
	public String getIndicadorCierreFinMes() {
		return indicadorCierreFinMes;
	}

	/**
	 * @param indicadorCierreFinMes the indicadorCierreFinMes to set
	 */
	public void setIndicadorCierreFinMes(String indicadorCierreFinMes) {
		this.indicadorCierreFinMes = indicadorCierreFinMes;
	}

	/**
	 * @return the indicadorGeneracionArchivo
	 */
	public String getIndicadorGeneracionArchivo() {
		return indicadorGeneracionArchivo;
	}

	/**
	 * @param indicadorGeneracionArchivo the indicadorGeneracionArchivo to set
	 */
	public void setIndicadorGeneracionArchivo(String indicadorGeneracionArchivo) {
		this.indicadorGeneracionArchivo = indicadorGeneracionArchivo;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the numeroDiasVencimiento
	 */
	public String getNumeroDiasVencimiento() {
		return numeroDiasVencimiento;
	}

	/**
	 * @param numeroDiasVencimiento the numeroDiasVencimiento to set
	 */
	public void setNumeroDiasVencimiento(String numeroDiasVencimiento) {
		this.numeroDiasVencimiento = numeroDiasVencimiento;
	}

	/**
	 * @return the numeroDiasEspera
	 */
	public String getNumeroDiasEspera() {
		return numeroDiasEspera;
	}

	/**
	 * @param numeroDiasEspera the numeroDiasEspera to set
	 */
	public void setNumeroDiasEspera(String numeroDiasEspera) {
		this.numeroDiasEspera = numeroDiasEspera;
	}

		
}
