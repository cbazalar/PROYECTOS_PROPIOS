package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoParametrosPremiacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer numeroNiveles;
	private Integer indicadorNivelElegible;
	private Integer premiosAcumulativosNiveles;
	private Integer hastaNivel;
	private Integer indicadorNivelesRotativos;
	private Integer numeroRotaciones;
	private Integer accesoNivelSuperior;
	private Integer indicadorPremiosElectivos;
	private Integer periodoDespachoExigido;
	private Integer numeroPeriodos;
	private Integer indicadorComunicacion;
	private Long oidMensaje;
	private Long oidTipoEleccion;
	private Long oidTipoPremiacion;
	private Long oidPeriodoDespacho;
	private Long oidConcurso;
    private Long oidPeriodoDespachoInicio;
    
    private String codigoPeriodoDespacho;
    private String codigoPeriodoDespachoInicio;
    
    private List listConcursoNivelPremiacion;
    
    private boolean indRedefinirNivelPremiacion;
	
    private String porcentajeMaximoDescuentoCPP;
    
	public ConcursoParametrosPremiacion() {
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
	 * @return the numeroNiveles
	 */
	public Integer getNumeroNiveles() {
		return numeroNiveles;
	}

	/**
	 * @param numeroNiveles the numeroNiveles to set
	 */
	public void setNumeroNiveles(Integer numeroNiveles) {
		this.numeroNiveles = numeroNiveles;
	}

	/**
	 * @return the indicadorNivelElegible
	 */
	public Integer getIndicadorNivelElegible() {
		return indicadorNivelElegible;
	}

	/**
	 * @param indicadorNivelElegible the indicadorNivelElegible to set
	 */
	public void setIndicadorNivelElegible(Integer indicadorNivelElegible) {
		this.indicadorNivelElegible = indicadorNivelElegible;
	}

	/**
	 * @return the premiosAcumulativosNiveles
	 */
	public Integer getPremiosAcumulativosNiveles() {
		return premiosAcumulativosNiveles;
	}

	/**
	 * @param premiosAcumulativosNiveles the premiosAcumulativosNiveles to set
	 */
	public void setPremiosAcumulativosNiveles(Integer premiosAcumulativosNiveles) {
		this.premiosAcumulativosNiveles = premiosAcumulativosNiveles;
	}

	/**
	 * @return the hastaNivel
	 */
	public Integer getHastaNivel() {
		return hastaNivel;
	}

	/**
	 * @param hastaNivel the hastaNivel to set
	 */
	public void setHastaNivel(Integer hastaNivel) {
		this.hastaNivel = hastaNivel;
	}

	/**
	 * @return the indicadorNivelesRotativos
	 */
	public Integer getIndicadorNivelesRotativos() {
		return indicadorNivelesRotativos;
	}

	/**
	 * @param indicadorNivelesRotativos the indicadorNivelesRotativos to set
	 */
	public void setIndicadorNivelesRotativos(Integer indicadorNivelesRotativos) {
		this.indicadorNivelesRotativos = indicadorNivelesRotativos;
	}

	/**
	 * @return the numeroRotaciones
	 */
	public Integer getNumeroRotaciones() {
		return numeroRotaciones;
	}

	/**
	 * @param numeroRotaciones the numeroRotaciones to set
	 */
	public void setNumeroRotaciones(Integer numeroRotaciones) {
		this.numeroRotaciones = numeroRotaciones;
	}

	/**
	 * @return the accesoNivelSuperior
	 */
	public Integer getAccesoNivelSuperior() {
		return accesoNivelSuperior;
	}

	/**
	 * @param accesoNivelSuperior the accesoNivelSuperior to set
	 */
	public void setAccesoNivelSuperior(Integer accesoNivelSuperior) {
		this.accesoNivelSuperior = accesoNivelSuperior;
	}

	/**
	 * @return the indicadorPremiosElectivos
	 */
	public Integer getIndicadorPremiosElectivos() {
		return indicadorPremiosElectivos;
	}

	/**
	 * @param indicadorPremiosElectivos the indicadorPremiosElectivos to set
	 */
	public void setIndicadorPremiosElectivos(Integer indicadorPremiosElectivos) {
		this.indicadorPremiosElectivos = indicadorPremiosElectivos;
	}

	/**
	 * @return the periodoDespachoExigido
	 */
	public Integer getPeriodoDespachoExigido() {
		return periodoDespachoExigido;
	}

	/**
	 * @param periodoDespachoExigido the periodoDespachoExigido to set
	 */
	public void setPeriodoDespachoExigido(Integer periodoDespachoExigido) {
		this.periodoDespachoExigido = periodoDespachoExigido;
	}

	/**
	 * @return the numeroPeriodos
	 */
	public Integer getNumeroPeriodos() {
		return numeroPeriodos;
	}

	/**
	 * @param numeroPeriodos the numeroPeriodos to set
	 */
	public void setNumeroPeriodos(Integer numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
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
	 * @return the oidMensaje
	 */
	public Long getOidMensaje() {
		return oidMensaje;
	}

	/**
	 * @param oidMensaje the oidMensaje to set
	 */
	public void setOidMensaje(Long oidMensaje) {
		this.oidMensaje = oidMensaje;
	}

	/**
	 * @return the oidTipoEleccion
	 */
	public Long getOidTipoEleccion() {
		return oidTipoEleccion;
	}

	/**
	 * @param oidTipoEleccion the oidTipoEleccion to set
	 */
	public void setOidTipoEleccion(Long oidTipoEleccion) {
		this.oidTipoEleccion = oidTipoEleccion;
	}

	/**
	 * @return the oidTipoPremiacion
	 */
	public Long getOidTipoPremiacion() {
		return oidTipoPremiacion;
	}

	/**
	 * @param oidTipoPremiacion the oidTipoPremiacion to set
	 */
	public void setOidTipoPremiacion(Long oidTipoPremiacion) {
		this.oidTipoPremiacion = oidTipoPremiacion;
	}

	/**
	 * @return the oidPeriodoDespacho
	 */
	public Long getOidPeriodoDespacho() {
		return oidPeriodoDespacho;
	}

	/**
	 * @param oidPeriodoDespacho the oidPeriodoDespacho to set
	 */
	public void setOidPeriodoDespacho(Long oidPeriodoDespacho) {
		this.oidPeriodoDespacho = oidPeriodoDespacho;
	}

	/**
	 * @return the oidConcurso
	 */
	public Long getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the oidPeriodoDespachoInicio
	 */
	public Long getOidPeriodoDespachoInicio() {
		return oidPeriodoDespachoInicio;
	}

	/**
	 * @param oidPeriodoDespachoInicio the oidPeriodoDespachoInicio to set
	 */
	public void setOidPeriodoDespachoInicio(Long oidPeriodoDespachoInicio) {
		this.oidPeriodoDespachoInicio = oidPeriodoDespachoInicio;
	}

	/**
	 * @return the codigoPeriodoDespacho
	 */
	public String getCodigoPeriodoDespacho() {
		return codigoPeriodoDespacho;
	}

	/**
	 * @param codigoPeriodoDespacho the codigoPeriodoDespacho to set
	 */
	public void setCodigoPeriodoDespacho(String codigoPeriodoDespacho) {
		this.codigoPeriodoDespacho = codigoPeriodoDespacho;
	}

	/**
	 * @return the listConcursoNivelPremiacion
	 */
	public List getListConcursoNivelPremiacion() {
		return listConcursoNivelPremiacion;
	}

	/**
	 * @param listConcursoNivelPremiacion the listConcursoNivelPremiacion to set
	 */
	public void setListConcursoNivelPremiacion(List listConcursoNivelPremiacion) {
		this.listConcursoNivelPremiacion = listConcursoNivelPremiacion;
	}

	/**
	 * @return the indRedefinirNivelPremiacion
	 */
	public boolean isIndRedefinirNivelPremiacion() {
		return indRedefinirNivelPremiacion;
	}

	/**
	 * @param indRedefinirNivelPremiacion the indRedefinirNivelPremiacion to set
	 */
	public void setIndRedefinirNivelPremiacion(boolean indRedefinirNivelPremiacion) {
		this.indRedefinirNivelPremiacion = indRedefinirNivelPremiacion;
	}

	/**
	 * @return the codigoPeriodoDespachoInicio
	 */
	public String getCodigoPeriodoDespachoInicio() {
		return codigoPeriodoDespachoInicio;
	}

	/**
	 * @param codigoPeriodoDespachoInicio the codigoPeriodoDespachoInicio to set
	 */
	public void setCodigoPeriodoDespachoInicio(String codigoPeriodoDespachoInicio) {
		this.codigoPeriodoDespachoInicio = codigoPeriodoDespachoInicio;
	}

	/**
	 * @return the porcentajeMaximoDescuentoCPP
	 */
	public String getPorcentajeMaximoDescuentoCPP() {
		return porcentajeMaximoDescuentoCPP;
	}

	/**
	 * @param porcentajeMaximoDescuentoCPP the porcentajeMaximoDescuentoCPP to set
	 */
	public void setPorcentajeMaximoDescuentoCPP(String porcentajeMaximoDescuentoCPP) {
		this.porcentajeMaximoDescuentoCPP = porcentajeMaximoDescuentoCPP;
	}
	
}
