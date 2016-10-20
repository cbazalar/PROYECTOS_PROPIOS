package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoParametrosGenerales extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private String numeroConcurso;
	private Integer version;
	private Long oidPais;
	private String nombreConcurso;
	
	private Integer indicadorRanking;
	private Integer indicadorDevoluciones;
	private Integer indicadorAnulaciones;
	private Integer faltantesNoAnunciados;
	private String expresionPuntaje;
	private Integer indicadorPruebas;
	private String observaciones;
	private Integer indicadorActivo;
	private String estatusGeneracionMetas;
	private Integer indicadorComunicacion;
	private String codigoMensaje;
	private Long oidPlantilla;
	private Long oidTipoConcursoIVR;
	private Long oidPeriodoDesde;
	private Long oidPeriodoHasta;
	private Long oidTipoExigencia;
	private Long dirigidoA;
	private Long oidBaseCalculo;
	private Long oidMarca;
	private Long oidCanal;
	private Integer indicadorDuplaCyzone;
	private Long oidTipoPrograma;
    private Integer indicadorNoGeneraPuntaje;
    private Long oidClasificacionConcurso;
    private Long oidTipoOfertaConcurso;
    private String codigoUsuario;
    
    private Integer indicadorMultiMarca;
    private Long puntosAbonar;
    
    private String codigoPais;
    private String codigoMarca;
    private String codigoCanal;
    private String codigoAcceso;
    private String codigoPeriodoDesde;
    private String codigoPeriodoHasta;
    private String estadoConcurso;
    
    
    private ConcursoVersion concursoVersion;
    private ConcursoObtencionPuntos concursoObtencionPuntos;
    private ConcursoDespachoPremios concursoDespachoPremios;
    private List listConcursoAmbitoGeografico;
    
    private ConcursoParametrosConsultoras concursoParametrosConsultoras;
    private List listConcursoMontoVentas;
    private List listConcursoEstatusVenta;
    private List listConcursoClasificacionParticipante;
    private List listConcursoRecomendadaPeriodo;
    private List listConcursoBonificacionPeriodo;
    private List listConcursoPeriodoDespacho;
    private List listConcursoPuntajeExigido;
    
    private ConcursoParametrosPremiacion concursoParametrosPremiacion;
    private ConcursoRequisitoPremiacion concursoRequisitoPremiacion;
    
    private ConcursoProductos concursoProductos;
    
    private boolean indActualizarAmbitoGeografico;
    private boolean indActualizarEstatusVenta;
    private boolean indActualizarClasificacionParticipantes;
    private boolean indActualizarRecomendadaPeriodo;
    private boolean indActualizarBonificacionPeriodo;
    private boolean indActualizarPeriodoDespacho;
    private boolean indActualizarPuntajeExigido;
    
    private String estadoCerrado;
    
    private String descripcionPrograma;
        
	private String indicadorCPP;
	private String oidCPP;
	private String codigoCPP;
	private String descripcionCPP;
    
	public ConcursoParametrosGenerales() {
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
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the oidPais
	 */
	public Long getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(Long oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the nombreConcurso
	 */
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	/**
	 * @param nombreConcurso the nombreConcurso to set
	 */
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

	/**
	 * @return the indicadorRanking
	 */
	public Integer getIndicadorRanking() {
		return indicadorRanking;
	}

	/**
	 * @param indicadorRanking the indicadorRanking to set
	 */
	public void setIndicadorRanking(Integer indicadorRanking) {
		this.indicadorRanking = indicadorRanking;
	}

	/**
	 * @return the indicadorDevoluciones
	 */
	public Integer getIndicadorDevoluciones() {
		return indicadorDevoluciones;
	}

	/**
	 * @param indicadorDevoluciones the indicadorDevoluciones to set
	 */
	public void setIndicadorDevoluciones(Integer indicadorDevoluciones) {
		this.indicadorDevoluciones = indicadorDevoluciones;
	}

	/**
	 * @return the indicadorAnulaciones
	 */
	public Integer getIndicadorAnulaciones() {
		return indicadorAnulaciones;
	}

	/**
	 * @param indicadorAnulaciones the indicadorAnulaciones to set
	 */
	public void setIndicadorAnulaciones(Integer indicadorAnulaciones) {
		this.indicadorAnulaciones = indicadorAnulaciones;
	}

	/**
	 * @return the faltantesNoAnunciados
	 */
	public Integer getFaltantesNoAnunciados() {
		return faltantesNoAnunciados;
	}

	/**
	 * @param faltantesNoAnunciados the faltantesNoAnunciados to set
	 */
	public void setFaltantesNoAnunciados(Integer faltantesNoAnunciados) {
		this.faltantesNoAnunciados = faltantesNoAnunciados;
	}

	/**
	 * @return the expresionPuntaje
	 */
	public String getExpresionPuntaje() {
		return expresionPuntaje;
	}

	/**
	 * @param expresionPuntaje the expresionPuntaje to set
	 */
	public void setExpresionPuntaje(String expresionPuntaje) {
		this.expresionPuntaje = expresionPuntaje;
	}

	/**
	 * @return the indicadorPruebas
	 */
	public Integer getIndicadorPruebas() {
		return indicadorPruebas;
	}

	/**
	 * @param indicadorPruebas the indicadorPruebas to set
	 */
	public void setIndicadorPruebas(Integer indicadorPruebas) {
		this.indicadorPruebas = indicadorPruebas;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indicadorActivo
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the estatusGeneracionMetas
	 */
	public String getEstatusGeneracionMetas() {
		return estatusGeneracionMetas;
	}

	/**
	 * @param estatusGeneracionMetas the estatusGeneracionMetas to set
	 */
	public void setEstatusGeneracionMetas(String estatusGeneracionMetas) {
		this.estatusGeneracionMetas = estatusGeneracionMetas;
	}

	/**
	 * @return the indicadorComunicacion
	 */
	public Integer getIndicadorComunicacion() {
		return indicadorComunicacion;
	}

	/**
	 * @param indicadorComunicacion the indicadorComunicacion to set
	 */
	public void setIndicadorComunicacion(Integer indicadorComunicacion) {
		this.indicadorComunicacion = indicadorComunicacion;
	}

	/**
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje the codigoMensaje to set
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * @return the oidPlantilla
	 */
	public Long getOidPlantilla() {
		return oidPlantilla;
	}

	/**
	 * @param oidPlantilla the oidPlantilla to set
	 */
	public void setOidPlantilla(Long oidPlantilla) {
		this.oidPlantilla = oidPlantilla;
	}

	/**
	 * @return the oidTipoConcursoIVR
	 */
	public Long getOidTipoConcursoIVR() {
		return oidTipoConcursoIVR;
	}

	/**
	 * @param oidTipoConcursoIVR the oidTipoConcursoIVR to set
	 */
	public void setOidTipoConcursoIVR(Long oidTipoConcursoIVR) {
		this.oidTipoConcursoIVR = oidTipoConcursoIVR;
	}

	/**
	 * @return the oidPeriodoDesde
	 */
	public Long getOidPeriodoDesde() {
		return oidPeriodoDesde;
	}

	/**
	 * @param oidPeriodoDesde the oidPeriodoDesde to set
	 */
	public void setOidPeriodoDesde(Long oidPeriodoDesde) {
		this.oidPeriodoDesde = oidPeriodoDesde;
	}

	/**
	 * @return the oidPeriodoHasta
	 */
	public Long getOidPeriodoHasta() {
		return oidPeriodoHasta;
	}

	/**
	 * @param oidPeriodoHasta the oidPeriodoHasta to set
	 */
	public void setOidPeriodoHasta(Long oidPeriodoHasta) {
		this.oidPeriodoHasta = oidPeriodoHasta;
	}

	/**
	 * @return the oidTipoExigencia
	 */
	public Long getOidTipoExigencia() {
		return oidTipoExigencia;
	}

	/**
	 * @param oidTipoExigencia the oidTipoExigencia to set
	 */
	public void setOidTipoExigencia(Long oidTipoExigencia) {
		this.oidTipoExigencia = oidTipoExigencia;
	}

	/**
	 * @return the dirigidoA
	 */
	public Long getDirigidoA() {
		return dirigidoA;
	}

	/**
	 * @param dirigidoA the dirigidoA to set
	 */
	public void setDirigidoA(Long dirigidoA) {
		this.dirigidoA = dirigidoA;
	}

	/**
	 * @return the oidBaseCalculo
	 */
	public Long getOidBaseCalculo() {
		return oidBaseCalculo;
	}

	/**
	 * @param oidBaseCalculo the oidBaseCalculo to set
	 */
	public void setOidBaseCalculo(Long oidBaseCalculo) {
		this.oidBaseCalculo = oidBaseCalculo;
	}

	/**
	 * @return the oidMarca
	 */
	public Long getOidMarca() {
		return oidMarca;
	}

	/**
	 * @param oidMarca the oidMarca to set
	 */
	public void setOidMarca(Long oidMarca) {
		this.oidMarca = oidMarca;
	}

	/**
	 * @return the oidCanal
	 */
	public Long getOidCanal() {
		return oidCanal;
	}

	/**
	 * @param oidCanal the oidCanal to set
	 */
	public void setOidCanal(Long oidCanal) {
		this.oidCanal = oidCanal;
	}

	/**
	 * @return the indicadorDuplaCyzone
	 */
	public Integer getIndicadorDuplaCyzone() {
		return indicadorDuplaCyzone;
	}

	/**
	 * @param indicadorDuplaCyzone the indicadorDuplaCyzone to set
	 */
	public void setIndicadorDuplaCyzone(Integer indicadorDuplaCyzone) {
		this.indicadorDuplaCyzone = indicadorDuplaCyzone;
	}

	/**
	 * @return the oidTipoPrograma
	 */
	public Long getOidTipoPrograma() {
		return oidTipoPrograma;
	}

	/**
	 * @param oidTipoPrograma the oidTipoPrograma to set
	 */
	public void setOidTipoPrograma(Long oidTipoPrograma) {
		this.oidTipoPrograma = oidTipoPrograma;
	}

	/**
	 * @return the indicadorNoGeneraPuntaje
	 */
	public Integer getIndicadorNoGeneraPuntaje() {
		return indicadorNoGeneraPuntaje;
	}

	/**
	 * @param indicadorNoGeneraPuntaje the indicadorNoGeneraPuntaje to set
	 */
	public void setIndicadorNoGeneraPuntaje(Integer indicadorNoGeneraPuntaje) {
		this.indicadorNoGeneraPuntaje = indicadorNoGeneraPuntaje;
	}

	/**
	 * @return the oidClasificacionConcurso
	 */
	public Long getOidClasificacionConcurso() {
		return oidClasificacionConcurso;
	}

	/**
	 * @param oidClasificacionConcurso the oidClasificacionConcurso to set
	 */
	public void setOidClasificacionConcurso(Long oidClasificacionConcurso) {
		this.oidClasificacionConcurso = oidClasificacionConcurso;
	}

	/**
	 * @return the oidTipoOfertaConcurso
	 */
	public Long getOidTipoOfertaConcurso() {
		return oidTipoOfertaConcurso;
	}

	/**
	 * @param oidTipoOfertaConcurso the oidTipoOfertaConcurso to set
	 */
	public void setOidTipoOfertaConcurso(Long oidTipoOfertaConcurso) {
		this.oidTipoOfertaConcurso = oidTipoOfertaConcurso;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return the estadoConcurso
	 */
	public String getEstadoConcurso() {
		return estadoConcurso;
	}

	/**
	 * @param estadoConcurso the estadoConcurso to set
	 */
	public void setEstadoConcurso(String estadoConcurso) {
		this.estadoConcurso = estadoConcurso;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the concursoVersion
	 */
	public ConcursoVersion getConcursoVersion() {
		return concursoVersion;
	}

	/**
	 * @param concursoVersion the concursoVersion to set
	 */
	public void setConcursoVersion(ConcursoVersion concursoVersion) {
		this.concursoVersion = concursoVersion;
	}

	/**
	 * @return the concursoObtencionPuntos
	 */
	public ConcursoObtencionPuntos getConcursoObtencionPuntos() {
		return concursoObtencionPuntos;
	}

	/**
	 * @param concursoObtencionPuntos the concursoObtencionPuntos to set
	 */
	public void setConcursoObtencionPuntos(
			ConcursoObtencionPuntos concursoObtencionPuntos) {
		this.concursoObtencionPuntos = concursoObtencionPuntos;
	}

	/**
	 * @return the concursoDespachoPremios
	 */
	public ConcursoDespachoPremios getConcursoDespachoPremios() {
		return concursoDespachoPremios;
	}

	/**
	 * @param concursoDespachoPremios the concursoDespachoPremios to set
	 */
	public void setConcursoDespachoPremios(
			ConcursoDespachoPremios concursoDespachoPremios) {
		this.concursoDespachoPremios = concursoDespachoPremios;
	}

	/**
	 * @return the listConcursoAmbitoGeografico
	 */
	public List getListConcursoAmbitoGeografico() {
		return listConcursoAmbitoGeografico;
	}

	/**
	 * @param listConcursoAmbitoGeografico the listConcursoAmbitoGeografico to set
	 */
	public void setListConcursoAmbitoGeografico(List listConcursoAmbitoGeografico) {
		this.listConcursoAmbitoGeografico = listConcursoAmbitoGeografico;
	}

	/**
	 * @return the indActualizarAmbitoGeografico
	 */
	public boolean isIndActualizarAmbitoGeografico() {
		return indActualizarAmbitoGeografico;
	}

	/**
	 * @param indActualizarAmbitoGeografico the indActualizarAmbitoGeografico to set
	 */
	public void setIndActualizarAmbitoGeografico(
			boolean indActualizarAmbitoGeografico) {
		this.indActualizarAmbitoGeografico = indActualizarAmbitoGeografico;
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
	 * @return the concursoParametrosConsultoras
	 */
	public ConcursoParametrosConsultoras getConcursoParametrosConsultoras() {
		return concursoParametrosConsultoras;
	}

	/**
	 * @param concursoParametrosConsultoras the concursoParametrosConsultoras to set
	 */
	public void setConcursoParametrosConsultoras(
			ConcursoParametrosConsultoras concursoParametrosConsultoras) {
		this.concursoParametrosConsultoras = concursoParametrosConsultoras;
	}

	/**
	 * @return the listConcursoMontoVentas
	 */
	public List getListConcursoMontoVentas() {
		return listConcursoMontoVentas;
	}

	/**
	 * @param listConcursoMontoVentas the listConcursoMontoVentas to set
	 */
	public void setListConcursoMontoVentas(List listConcursoMontoVentas) {
		this.listConcursoMontoVentas = listConcursoMontoVentas;
	}

	/**
	 * @return the listConcursoEstatusVenta
	 */
	public List getListConcursoEstatusVenta() {
		return listConcursoEstatusVenta;
	}

	/**
	 * @param listConcursoEstatusVenta the listConcursoEstatusVenta to set
	 */
	public void setListConcursoEstatusVenta(List listConcursoEstatusVenta) {
		this.listConcursoEstatusVenta = listConcursoEstatusVenta;
	}

	/**
	 * @return the listConcursoClasificacionParticipante
	 */
	public List getListConcursoClasificacionParticipante() {
		return listConcursoClasificacionParticipante;
	}

	/**
	 * @param listConcursoClasificacionParticipante the listConcursoClasificacionParticipante to set
	 */
	public void setListConcursoClasificacionParticipante(
			List listConcursoClasificacionParticipante) {
		this.listConcursoClasificacionParticipante = listConcursoClasificacionParticipante;
	}

	/**
	 * @return the concursoParametrosPremiacion
	 */
	public ConcursoParametrosPremiacion getConcursoParametrosPremiacion() {
		return concursoParametrosPremiacion;
	}

	/**
	 * @param concursoParametrosPremiacion the concursoParametrosPremiacion to set
	 */
	public void setConcursoParametrosPremiacion(
			ConcursoParametrosPremiacion concursoParametrosPremiacion) {
		this.concursoParametrosPremiacion = concursoParametrosPremiacion;
	}

	/**
	 * @return the concursoRequisitoPremiacion
	 */
	public ConcursoRequisitoPremiacion getConcursoRequisitoPremiacion() {
		return concursoRequisitoPremiacion;
	}

	/**
	 * @param concursoRequisitoPremiacion the concursoRequisitoPremiacion to set
	 */
	public void setConcursoRequisitoPremiacion(
			ConcursoRequisitoPremiacion concursoRequisitoPremiacion) {
		this.concursoRequisitoPremiacion = concursoRequisitoPremiacion;
	}

	/**
	 * @return the indActualizarEstatusVenta
	 */
	public boolean isIndActualizarEstatusVenta() {
		return indActualizarEstatusVenta;
	}

	/**
	 * @param indActualizarEstatusVenta the indActualizarEstatusVenta to set
	 */
	public void setIndActualizarEstatusVenta(boolean indActualizarEstatusVenta) {
		this.indActualizarEstatusVenta = indActualizarEstatusVenta;
	}

	/**
	 * @return the indActualizarClasificacionParticipantes
	 */
	public boolean isIndActualizarClasificacionParticipantes() {
		return indActualizarClasificacionParticipantes;
	}

	/**
	 * @param indActualizarClasificacionParticipantes the indActualizarClasificacionParticipantes to set
	 */
	public void setIndActualizarClasificacionParticipantes(
			boolean indActualizarClasificacionParticipantes) {
		this.indActualizarClasificacionParticipantes = indActualizarClasificacionParticipantes;
	}

	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso the codigoAcceso to set
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return the concursoProductos
	 */
	public ConcursoProductos getConcursoProductos() {
		return concursoProductos;
	}

	/**
	 * @param concursoProductos the concursoProductos to set
	 */
	public void setConcursoProductos(ConcursoProductos concursoProductos) {
		this.concursoProductos = concursoProductos;
	}

	/**
	 * @return
	 */
	public String getEstadoCerrado() {
		return estadoCerrado;
	}

	/**
	 * @param estadoCerrado
	 */
	public void setEstadoCerrado(String estadoCerrado) {
		this.estadoCerrado = estadoCerrado;
	}

	/**
	 * @return the indicadorMultiMarca
	 */
	public Integer getIndicadorMultiMarca() {
		return indicadorMultiMarca;
	}

	/**
	 * @param indicadorMultiMarca the indicadorMultiMarca to set
	 */
	public void setIndicadorMultiMarca(Integer indicadorMultiMarca) {
		this.indicadorMultiMarca = indicadorMultiMarca;
	}

	/**
	 * @return the puntosAbonar
	 */
	public Long getPuntosAbonar() {
		return puntosAbonar;
	}

	/**
	 * @param puntosAbonar the puntosAbonar to set
	 */
	public void setPuntosAbonar(Long puntosAbonar) {
		this.puntosAbonar = puntosAbonar;
	}

	/**
	 * @return the listConcursoRecomendadaPeriodo
	 */
	public List getListConcursoRecomendadaPeriodo() {
		return listConcursoRecomendadaPeriodo;
	}

	/**
	 * @param listConcursoRecomendadaPeriodo the listConcursoRecomendadaPeriodo to set
	 */
	public void setListConcursoRecomendadaPeriodo(
			List listConcursoRecomendadaPeriodo) {
		this.listConcursoRecomendadaPeriodo = listConcursoRecomendadaPeriodo;
	}

	/**
	 * @return the indActualizarRecomendadaPeriodo
	 */
	public boolean isIndActualizarRecomendadaPeriodo() {
		return indActualizarRecomendadaPeriodo;
	}

	/**
	 * @param indActualizarRecomendadaPeriodo the indActualizarRecomendadaPeriodo to set
	 */
	public void setIndActualizarRecomendadaPeriodo(
			boolean indActualizarRecomendadaPeriodo) {
		this.indActualizarRecomendadaPeriodo = indActualizarRecomendadaPeriodo;
	}

	/**
	 * @return the listConcursoBonificacionPeriodo
	 */
	public List getListConcursoBonificacionPeriodo() {
		return listConcursoBonificacionPeriodo;
	}

	/**
	 * @param listConcursoBonificacionPeriodo the listConcursoBonificacionPeriodo to set
	 */
	public void setListConcursoBonificacionPeriodo(
			List listConcursoBonificacionPeriodo) {
		this.listConcursoBonificacionPeriodo = listConcursoBonificacionPeriodo;
	}

	/**
	 * @return the indActualizarBonificacionPeriodo
	 */
	public boolean isIndActualizarBonificacionPeriodo() {
		return indActualizarBonificacionPeriodo;
	}

	/**
	 * @param indActualizarBonificacionPeriodo the indActualizarBonificacionPeriodo to set
	 */
	public void setIndActualizarBonificacionPeriodo(
			boolean indActualizarBonificacionPeriodo) {
		this.indActualizarBonificacionPeriodo = indActualizarBonificacionPeriodo;
	}

	/**
	 * @return the listConcursoPeriodoDespacho
	 */
	public List getListConcursoPeriodoDespacho() {
		return listConcursoPeriodoDespacho;
	}

	/**
	 * @param listConcursoPeriodoDespacho the listConcursoPeriodoDespacho to set
	 */
	public void setListConcursoPeriodoDespacho(List listConcursoPeriodoDespacho) {
		this.listConcursoPeriodoDespacho = listConcursoPeriodoDespacho;
	}

	/**
	 * @return the indActualizarPeriodoDespacho
	 */
	public boolean isIndActualizarPeriodoDespacho() {
		return indActualizarPeriodoDespacho;
	}

	/**
	 * @param indActualizarPeriodoDespacho the indActualizarPeriodoDespacho to set
	 */
	public void setIndActualizarPeriodoDespacho(boolean indActualizarPeriodoDespacho) {
		this.indActualizarPeriodoDespacho = indActualizarPeriodoDespacho;
	}
	
	/**
	 * @return the listConcursoPuntajeExigido
	 */
	public List getListConcursoPuntajeExigido() {
		return listConcursoPuntajeExigido;
	}

	/**
	 * @param listConcursoPuntajeExigido the listConcursoPuntajeExigido to set
	 */
	public void setListConcursoPuntajeExigido(List listConcursoPuntajeExigido) {
		this.listConcursoPuntajeExigido = listConcursoPuntajeExigido;
	}

	/**
	 * @return the indActualizarPuntajeExigido
	 */
	public boolean isIndActualizarPuntajeExigido() {
		return indActualizarPuntajeExigido;
	}

	/**
	 * @param indActualizarPuntajeExigido the indActualizarPuntajeExigido to set
	 */
	public void setIndActualizarPuntajeExigido(boolean indActualizarPuntajeExigido) {
		this.indActualizarPuntajeExigido = indActualizarPuntajeExigido;
	}
	
	/**
	 * @return the descripcionPrograma
	 */
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}

	/**
	 * @param descripcionPrograma the descripcionPrograma to set
	 */
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}
	
	/**
	 * @return the indicadorCPP
	 */
	public String getIndicadorCPP() {
		return indicadorCPP;
	}
	
	/**
	 * @param indicadorCPP the indicadorCPP to set
	 */
	public void setIndicadorCPP(String indicadorCPP) {
		this.indicadorCPP = indicadorCPP;
	}

	/**
	 * @return the oidCPP
	 */
	public String getOidCPP() {
		return oidCPP;
	}

	/**
	 * @param oidCPP the oidCPP to set
	 */
	public void setOidCPP(String oidCPP) {
		this.oidCPP = oidCPP;
	}

	/**
	 * @return the codigoCPP
	 */
	public String getCodigoCPP() {
		return codigoCPP;
	}

	/**
	 * @param codigoCPP the codigoCPP to set
	 */
	public void setCodigoCPP(String codigoCPP) {
		this.codigoCPP = codigoCPP;
	}

	/**
	 * @return the descripcionCPP
	 */
	public String getDescripcionCPP() {
		return descripcionCPP;
	}

	/**
	 * @param descripcionCPP the descripcionCPP to set
	 */
	public void setDescripcionCPP(String descripcionCPP) {
		this.descripcionCPP = descripcionCPP;
	}	
}
