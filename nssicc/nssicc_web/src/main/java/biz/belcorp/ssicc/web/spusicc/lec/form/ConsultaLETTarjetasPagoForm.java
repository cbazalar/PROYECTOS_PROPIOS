package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ConsultaLETTarjetasPagoForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	
	private String codigoLider;
	
	private String numeroTarjeta;
	
	private String estadoTarjeta;
	
	private UploadedFile archivoCodigoLider;
	
	private UploadedFile archivoNumeroTarjeta;

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
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}
	/**
	 * @param codigoLider the codigoLider to set
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}
	/**
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	/**
	 * @return the estadoTarjeta
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}
	/**
	 * @param estadoTarjeta the estadoTarjeta to set
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}

	/**
	 * @return the archivoCodigoLider
	 */
	public UploadedFile getArchivoCodigoLider() {
		return archivoCodigoLider;
	}

	/**
	 * @param archivoCodigoLider the archivoCodigoLider to set
	 */
	public void setArchivoCodigoLider(UploadedFile archivoCodigoLider) {
		this.archivoCodigoLider = archivoCodigoLider;
	}

	/**
	 * @return the archivoNumeroTarjeta
	 */
	public UploadedFile getArchivoNumeroTarjeta() {
		return archivoNumeroTarjeta;
	}

	/**
	 * @param archivoNumeroTarjeta the archivoNumeroTarjeta to set
	 */
	public void setArchivoNumeroTarjeta(UploadedFile archivoNumeroTarjeta) {
		this.archivoNumeroTarjeta = archivoNumeroTarjeta;
	}
}