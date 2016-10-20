/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ProyeccionFaltanteGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594332424934338053L;
	private String codigoPais;
	private String codigoGrupo;            
	private String descripcionGrupo;       
	private Integer unidadesDisponibles;    
	private Integer unidadesDemandadas;     
	private Integer unidadesFaltantes;      
	private Integer unidadesAtendidas;      
	private BigDecimal porUnidadesDemandadas;  
	private BigDecimal montoUnidadesDemandadas;
	private BigDecimal montoUnidadesAtendidas; 
	private BigDecimal montoUnidadesFaltantes; 
	private BigDecimal porMontoDemandadas;     
	private BigDecimal porMontoTotalFactu;   
	private BigDecimal porMaximoPermitido;
	private String indicadorValorCritico;  
		
	public ProyeccionFaltanteGrupo() {
		codigoPais = "";
		indicadorValorCritico = "";
	}

	/**
	 * @return Returns the codigoGrupo.
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}

	/**
	 * @param codigoGrupo The codigoGrupo to set.
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
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
	 * @return Returns the descripcionGrupo.
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}

	/**
	 * @param descripcionGrupo The descripcionGrupo to set.
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}

	/**
	 * @return Returns the indicadorValorCritico.
	 */
	public String getIndicadorValorCritico() {
		return indicadorValorCritico;
	}

	/**
	 * @param indicadorValorCritico The indicadorValorCritico to set.
	 */
	public void setIndicadorValorCritico(String indicadorValorCritico) {
		this.indicadorValorCritico = indicadorValorCritico;
	}

	/**
	 * @return Returns the montoUnidadesAtendidas.
	 */
	public BigDecimal getMontoUnidadesAtendidas() {
		return montoUnidadesAtendidas;
	}

	/**
	 * @param montoUnidadesAtendidas The montoUnidadesAtendidas to set.
	 */
	public void setMontoUnidadesAtendidas(BigDecimal montoUnidadesAtendidas) {
		this.montoUnidadesAtendidas = montoUnidadesAtendidas;
	}

	/**
	 * @return Returns the montoUnidadesDemandadas.
	 */
	public BigDecimal getMontoUnidadesDemandadas() {
		return montoUnidadesDemandadas;
	}

	/**
	 * @param montoUnidadesDemandadas The montoUnidadesDemandadas to set.
	 */
	public void setMontoUnidadesDemandadas(BigDecimal montoUnidadesDemandadas) {
		this.montoUnidadesDemandadas = montoUnidadesDemandadas;
	}

	/**
	 * @return Returns the montoUnidadesFaltantes.
	 */
	public BigDecimal getMontoUnidadesFaltantes() {
		return montoUnidadesFaltantes;
	}

	/**
	 * @param montoUnidadesFaltantes The montoUnidadesFaltantes to set.
	 */
	public void setMontoUnidadesFaltantes(BigDecimal montoUnidadesFaltantes) {
		this.montoUnidadesFaltantes = montoUnidadesFaltantes;
	}

	/**
	 * @return Returns the porMontoDemandadas.
	 */
	public BigDecimal getPorMontoDemandadas() {
		return porMontoDemandadas;
	}

	/**
	 * @param porMontoDemandadas The porMontoDemandadas to set.
	 */
	public void setPorMontoDemandadas(BigDecimal porMontoDemandadas) {
		this.porMontoDemandadas = porMontoDemandadas;
	}

	/**
	 * @return Returns the porMontoTotalFactu.
	 */
	public BigDecimal getPorMontoTotalFactu() {
		return porMontoTotalFactu;
	}

	/**
	 * @param porMontoTotalFactu The porMontoTotalFactu to set.
	 */
	public void setPorMontoTotalFactu(BigDecimal porMontoTotalFactu) {
		this.porMontoTotalFactu = porMontoTotalFactu;
	}

	/**
	 * @return Returns the porUnidadesDemandadas.
	 */
	public BigDecimal getPorUnidadesDemandadas() {
		return porUnidadesDemandadas;
	}

	/**
	 * @param porUnidadesDemandadas The porUnidadesDemandadas to set.
	 */
	public void setPorUnidadesDemandadas(BigDecimal porUnidadesDemandadas) {
		this.porUnidadesDemandadas = porUnidadesDemandadas;
	}

	/**
	 * @return Returns the unidadesAtendidas.
	 */
	public Integer getUnidadesAtendidas() {
		return unidadesAtendidas;
	}

	/**
	 * @param unidadesAtendidas The unidadesAtendidas to set.
	 */
	public void setUnidadesAtendidas(Integer unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}

	/**
	 * @return Returns the unidadesDemandadas.
	 */
	public Integer getUnidadesDemandadas() {
		return unidadesDemandadas;
	}

	/**
	 * @param unidadesDemandadas The unidadesDemandadas to set.
	 */
	public void setUnidadesDemandadas(Integer unidadesDemandadas) {
		this.unidadesDemandadas = unidadesDemandadas;
	}

	/**
	 * @return Returns the unidadesDisponibles.
	 */
	public Integer getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	/**
	 * @param unidadesDisponibles The unidadesDisponibles to set.
	 */
	public void setUnidadesDisponibles(Integer unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}

	/**
	 * @return Returns the unidadesFaltantes.
	 */
	public Integer getUnidadesFaltantes() {
		return unidadesFaltantes;
	}

	/**
	 * @param unidadesFaltantes The unidadesFaltantes to set.
	 */
	public void setUnidadesFaltantes(Integer unidadesFaltantes) {
		this.unidadesFaltantes = unidadesFaltantes;
	}

	/**
	 * @return Returns the porMaximoPermitido.
	 */
	public BigDecimal getPorMaximoPermitido() {
		return porMaximoPermitido;
	}

	/**
	 * @param porMaximoPermitido The porMaximoPermitido to set.
	 */
	public void setPorMaximoPermitido(BigDecimal porMaximoPermitido) {
		this.porMaximoPermitido = porMaximoPermitido;
	}
	
	

	
		

}
