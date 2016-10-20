/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.scsicc.bean;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RetencionPedidosMYEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class RetencionPedidosBean implements Serializable {

	private static final long serialVersionUID = 4692137155397486165L;
	private String codigoConsultora;
	private String numeroRetencion;
	private String codigoRegion;
	private String codigoZona;
	private String ultCampaFactu;
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
	 * @return the numeroRetencion
	 */
	public String getNumeroRetencion() {
		return numeroRetencion;
	}
	/**
	 * @param numeroRetencion the numeroRetencion to set
	 */
	public void setNumeroRetencion(String numeroRetencion) {
		this.numeroRetencion = numeroRetencion;
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
	 * @return the ultCampaFactu
	 */
	public String getUltCampaFactu() {
		return ultCampaFactu;
	}
	/**
	 * @param ultCampaFactu the ultCampaFactu to set
	 */
	public void setUltCampaFactu(String ultCampaFactu) {
		this.ultCampaFactu = ultCampaFactu;
	}
	

}
