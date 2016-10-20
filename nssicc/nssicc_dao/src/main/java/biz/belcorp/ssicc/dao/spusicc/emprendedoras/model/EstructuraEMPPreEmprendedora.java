package biz.belcorp.ssicc.dao.spusicc.emprendedoras.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public class EstructuraEMPPreEmprendedora implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String periodoInicio;
	private String codigoConsultora;
	private String codigoZona;
	private String codigoSeccion;
	private Integer pedidosBaseGD;	
	private String pedidosBaseGDValidacion;
	private Double ventaBaseGD;
	private String ventaBaseGDValidacion;
	private Integer activasBaseGD;
	private String activasBaseGDValidacion;
	private String nivelEjecutiva;
	private Double porcentajeComision;
	private String porcentajeComisionValidacion;
	private Double psp;
	private String pspValidacion;
	private Double ventaBaseIncentivos;
	private String ventaBaseIncentivosValidacion;
	private String codigoUsuario;
	
	private int fila;

	/**
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}

	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the pedidosBaseGD
	 */
	public Integer getPedidosBaseGD() {
		return pedidosBaseGD;
	}

	/**
	 * @param pedidosBaseGD the pedidosBaseGD to set
	 */
	public void setPedidosBaseGD(Integer pedidosBaseGD) {
		this.pedidosBaseGD = pedidosBaseGD;
	}

	/**
	 * @return the pedidosBaseGDValidacion
	 */
	public String getPedidosBaseGDValidacion() {
		return pedidosBaseGDValidacion;
	}

	/**
	 * @param pedidosBaseGDValidacion the pedidosBaseGDValidacion to set
	 */
	public void setPedidosBaseGDValidacion(String pedidosBaseGDValidacion) {
		this.pedidosBaseGDValidacion = pedidosBaseGDValidacion;
	}

	/**
	 * @return the ventaBaseGD
	 */
	public Double getVentaBaseGD() {
		return ventaBaseGD;
	}

	/**
	 * @param ventaBaseGD the ventaBaseGD to set
	 */
	public void setVentaBaseGD(Double ventaBaseGD) {
		this.ventaBaseGD = ventaBaseGD;
	}

	/**
	 * @return the ventaBaseGDValidacion
	 */
	public String getVentaBaseGDValidacion() {
		return ventaBaseGDValidacion;
	}

	/**
	 * @param ventaBaseGDValidacion the ventaBaseGDValidacion to set
	 */
	public void setVentaBaseGDValidacion(String ventaBaseGDValidacion) {
		this.ventaBaseGDValidacion = ventaBaseGDValidacion;
	}

	/**
	 * @return the activasBaseGD
	 */
	public Integer getActivasBaseGD() {
		return activasBaseGD;
	}

	/**
	 * @param activasBaseGD the activasBaseGD to set
	 */
	public void setActivasBaseGD(Integer activasBaseGD) {
		this.activasBaseGD = activasBaseGD;
	}

	/**
	 * @return the activasBaseGDValidacion
	 */
	public String getActivasBaseGDValidacion() {
		return activasBaseGDValidacion;
	}

	/**
	 * @param activasBaseGDValidacion the activasBaseGDValidacion to set
	 */
	public void setActivasBaseGDValidacion(String activasBaseGDValidacion) {
		this.activasBaseGDValidacion = activasBaseGDValidacion;
	}


	/**
	 * @return the porcentajeComision
	 */
	public Double getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * @param porcentajeComision the porcentajeComision to set
	 */
	public void setPorcentajeComision(Double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * @return the porcentajeComisionValidacion
	 */
	public String getPorcentajeComisionValidacion() {
		return porcentajeComisionValidacion;
	}

	/**
	 * @param porcentajeComisionValidacion the porcentajeComisionValidacion to set
	 */
	public void setPorcentajeComisionValidacion(String porcentajeComisionValidacion) {
		this.porcentajeComisionValidacion = porcentajeComisionValidacion;
	}

	/**
	 * @return the psp
	 */
	public Double getPsp() {
		return psp;
	}

	/**
	 * @param psp the psp to set
	 */
	public void setPsp(Double psp) {
		this.psp = psp;
	}

	/**
	 * @return the pspValidacion
	 */
	public String getPspValidacion() {
		return pspValidacion;
	}

	/**
	 * @param pspValidacion the pspValidacion to set
	 */
	public void setPspValidacion(String pspValidacion) {
		this.pspValidacion = pspValidacion;
	}

	/**
	 * @return the ventaBaseIncentivos
	 */
	public Double getVentaBaseIncentivos() {
		return ventaBaseIncentivos;
	}

	/**
	 * @param ventaBaseIncentivos the ventaBaseIncentivos to set
	 */
	public void setVentaBaseIncentivos(Double ventaBaseIncentivos) {
		this.ventaBaseIncentivos = ventaBaseIncentivos;
	}

	/**
	 * @return the ventaBaseIncentivosValidacion
	 */
	public String getVentaBaseIncentivosValidacion() {
		return ventaBaseIncentivosValidacion;
	}

	/**
	 * @param ventaBaseIncentivosValidacion the ventaBaseIncentivosValidacion to set
	 */
	public void setVentaBaseIncentivosValidacion(
			String ventaBaseIncentivosValidacion) {
		this.ventaBaseIncentivosValidacion = ventaBaseIncentivosValidacion;
	}

	/**
	 * @return the nivelEjecutiva
	 */
	public String getNivelEjecutiva() {
		return nivelEjecutiva;
	}

	/**
	 * @param nivelEjecutiva the nivelEjecutiva to set
	 */
	public void setNivelEjecutiva(String nivelEjecutiva) {
		this.nivelEjecutiva = nivelEjecutiva;
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
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}


}
