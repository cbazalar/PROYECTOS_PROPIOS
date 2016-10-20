package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 29/01/2015
 */
public class MantenimientoIMPProcesoImpresionForm extends BaseEditForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoProceso;
	private String descripcionProceso;
	private String direccionOrigen;
	private String direccionDestino;
	private String direccionHistorico;
	private String prefijoArchivo;
	private String flagFTP;
	private String servidorFTP;
	private String puertoFTP;
	private String usuarioFTP;
	private String passwordFTP;
	private String direccionFTP;
	private String indicadorEstado;
	
	public void reset() {
		this.codigoProceso = null;
		this.descripcionProceso = null;
		this.direccionOrigen= null;
		this.direccionDestino= null;
		this.direccionHistorico= null;
		this.prefijoArchivo= null;
		this.flagFTP= null;
		this.servidorFTP= null;
		this.puertoFTP= null;
		this.usuarioFTP= null;
		this.passwordFTP= null;
		this.direccionFTP= null;
		this.indicadorEstado= null;
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
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	/**
	 * @return the descripcionProceso
	 */
	public String getDescripcionProceso() {
		return descripcionProceso;
	}

	/**
	 * @param descripcionProceso the descripcionProceso to set

	 */
	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}

	/**
	 * @return the direccionOrigen
	 */
	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	/**
	 * @param direccionOrigen the direccionOrigen to set
	 */
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	/**
	 * @return the direccionDestino
	 */
	public String getDireccionDestino() {
		return direccionDestino;
	}

	/**
	 * @param direccionDestino the direccionDestino to set
	 */
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	/**
	 * @return the direccionHistorico
	 */
	public String getDireccionHistorico() {
		return direccionHistorico;
	}

	/**
	 * @param direccionHistorico the direccionHistorico to set
	 */
	public void setDireccionHistorico(String direccionHistorico) {
		this.direccionHistorico = direccionHistorico;
	}

	/**
	 * @return the prefijoArchivo
	 */
	public String getPrefijoArchivo() {
		return prefijoArchivo;
	}

	/**
	 * @param prefijoArchivo the prefijoArchivo to set

	 */
	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}

	/**
	 * @return the flagFTP
	 */
	public String getFlagFTP() {
		return flagFTP;
	}

	/**
	 * @param flagFTP the flagFTP to set
	 */
	public void setFlagFTP(String flagFTP) {
		this.flagFTP = flagFTP;
	}

	/**
	 * @return the servidorFTP
	 */
	public String getServidorFTP() {
		return servidorFTP;
	}

	/**
	 * @param servidorFTP the servidorFTP to set
	 */
	public void setServidorFTP(String servidorFTP) {
		this.servidorFTP = servidorFTP;
	}

	/**
	 * @return the puertoFTP
	 */
	public String getPuertoFTP() {
		return puertoFTP;
	}

	/**
	 * @param puertoFTP the puertoFTP to set
	 */
	public void setPuertoFTP(String puertoFTP) {
		this.puertoFTP = puertoFTP;
	}

	/**
	 * @return the usuarioFTP
	 */
	public String getUsuarioFTP() {
		return usuarioFTP;
	}

	/**
	 * @param usuarioFTP the usuarioFTP to set
	 */
	public void setUsuarioFTP(String usuarioFTP) {
		this.usuarioFTP = usuarioFTP;
	}

	/**
	 * @return the passwordFTP
	 */
	public String getPasswordFTP() {
		return passwordFTP;
	}

	/**
	 * @param passwordFTP the passwordFTP to set
	 */
	public void setPasswordFTP(String passwordFTP) {
		this.passwordFTP = passwordFTP;
	}

	/**
	 * @return the direccionFTP
	 */
	public String getDireccionFTP() {
		return direccionFTP;
	}

	/**
	 * @param direccionFTP the direccionFTP to set
	 * 
	 */
	public void setDireccionFTP(String direccionFTP) {
		this.direccionFTP = direccionFTP;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 * 
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
	
}
