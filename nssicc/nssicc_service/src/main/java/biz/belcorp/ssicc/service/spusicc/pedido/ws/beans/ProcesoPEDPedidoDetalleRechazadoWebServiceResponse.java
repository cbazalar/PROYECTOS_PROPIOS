package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDPedidoDetalleRechazadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	
	private String cuv;
	private Integer unidades;
	private Integer unidadesReal;
	private Integer unidadesAtender;
	private Double precioCatalogo;
	private Integer porcentajeDesc;
	private Double montoDesc;
	private Double montoAPagar;
	private String observaciones;
	private Integer oidOferta;
	private Integer oidEstrategia;
	
	public ProcesoPEDPedidoDetalleRechazadoWebServiceResponse() {
		this.cuv = "";
		this.unidades = 0;
		this.unidadesReal = 0;
		this.unidadesAtender = 0;
		this.precioCatalogo = 0.00;
		this.porcentajeDesc = 0;
		this.montoDesc = 0.00;
		this.montoAPagar = 0.00;
		this.observaciones = "";
		this.oidOferta = 0;
		this.oidEstrategia = 0;
	}

	public ProcesoPEDPedidoDetalleRechazadoWebServiceResponse(String cuv,
			Integer unidades, Integer unidadesReal, Integer unidadesAtender,
			Double precioCatalogo, Integer porcentajeDesc, Double montoDesc,
			Double montoAPagar, String observaciones, Integer oidOferta,
			Integer oidEstrategia) {
		this.cuv = cuv;
		this.unidades = unidades;
		this.unidadesReal = unidadesReal;
		this.unidadesAtender = unidadesAtender;
		this.precioCatalogo = precioCatalogo;
		this.porcentajeDesc = porcentajeDesc;
		this.montoDesc = montoDesc;
		this.montoAPagar = montoAPagar;
		this.observaciones = observaciones;
		this.oidOferta = oidOferta;
		this.oidEstrategia = oidEstrategia;
	}

	/**
	 * @return the cuv
	 */
	public String getCuv() {
		return cuv;
	}

	/**
	 * @param cuv the cuv to set
	 */
	public void setCuv(String cuv) {
		this.cuv = cuv;
	}

	/**
	 * @return the unidades
	 */
	public Integer getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	/**
	 * @return the unidadesReal
	 */
	public Integer getUnidadesReal() {
		return unidadesReal;
	}

	/**
	 * @param unidadesReal the unidadesReal to set
	 */
	public void setUnidadesReal(Integer unidadesReal) {
		this.unidadesReal = unidadesReal;
	}

	/**
	 * @return the unidadesAtender
	 */
	public Integer getUnidadesAtender() {
		return unidadesAtender;
	}

	/**
	 * @param unidadesAtender the unidadesAtender to set
	 */
	public void setUnidadesAtender(Integer unidadesAtender) {
		this.unidadesAtender = unidadesAtender;
	}

	/**
	 * @return the precioCatalogo
	 */
	public Double getPrecioCatalogo() {
		return precioCatalogo;
	}

	/**
	 * @param precioCatalogo the precioCatalogo to set
	 */
	public void setPrecioCatalogo(Double precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}

	/**
	 * @return the porcentajeDesc
	 */
	public Integer getPorcentajeDesc() {
		return porcentajeDesc;
	}

	/**
	 * @param porcentajeDesc the porcentajeDesc to set
	 */
	public void setPorcentajeDesc(Integer porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	/**
	 * @return the montoDesc
	 */
	public Double getMontoDesc() {
		return montoDesc;
	}

	/**
	 * @param montoDesc the montoDesc to set
	 */
	public void setMontoDesc(Double montoDesc) {
		this.montoDesc = montoDesc;
	}

	/**
	 * @return the montoAPagar
	 */
	public Double getMontoAPagar() {
		return montoAPagar;
	}

	/**
	 * @param montoAPagar the montoAPagar to set
	 */
	public void setMontoAPagar(Double montoAPagar) {
		this.montoAPagar = montoAPagar;
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
	 * @return the oidOferta
	 */
	public Integer getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(Integer oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the oidEstrategia
	 */
	public Integer getOidEstrategia() {
		return oidEstrategia;
	}

	/**
	 * @param oidEstrategia the oidEstrategia to set
	 */
	public void setOidEstrategia(Integer oidEstrategia) {
		this.oidEstrategia = oidEstrategia;
	}
}