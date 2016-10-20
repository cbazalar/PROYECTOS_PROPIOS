/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoPEDNumerosFacturacionForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7560494745853742441L;
	
	private String codigoPais;
	
	private String oidPais;
	
	private String oidDocumentoSubacceso;
	
	private String oidTipoDocumento;
	
	private String oidSociedad;
	
	private String limiteNumero;
	
	private String numeroInterno;
	
	private String fechaFin;
	
	private String serieInterno;
	
	private String annio;
	
	private String numeroAutorizacion;
	
	private String llave;
	
	private String observaciones;
	
	private String usuario;
	
	private Date fechaFinD;

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
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the oidDocumentoSubacceso
	 */
	public String getOidDocumentoSubacceso() {
		return oidDocumentoSubacceso;
	}

	/**
	 * @param oidDocumentoSubacceso the oidDocumentoSubacceso to set
	 */
	public void setOidDocumentoSubacceso(String oidDocumentoSubacceso) {
		this.oidDocumentoSubacceso = oidDocumentoSubacceso;
	}

	/**
	 * @return the oidTipoDocumento
	 */
	public String getOidTipoDocumento() {
		return oidTipoDocumento;
	}

	/**
	 * @param oidTipoDocumento the oidTipoDocumento to set
	 */
	public void setOidTipoDocumento(String oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}

	/**
	 * @return the oidSociedad
	 */
	public String getOidSociedad() {
		return oidSociedad;
	}

	/**
	 * @param oidSociedad the oidSociedad to set
	 */
	public void setOidSociedad(String oidSociedad) {
		this.oidSociedad = oidSociedad;
	}

	/**
	 * @return the limiteNumero
	 */
	public String getLimiteNumero() {
		return limiteNumero;
	}

	/**
	 * @param limiteNumero the limiteNumero to set
	 */
	public void setLimiteNumero(String limiteNumero) {
		this.limiteNumero = limiteNumero;
	}

	/**
	 * @return the numeroInterno
	 */
	public String getNumeroInterno() {
		return numeroInterno;
	}

	/**
	 * @param numeroInterno the numeroInterno to set
	 */
	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the serieInterno
	 */
	public String getSerieInterno() {
		return serieInterno;
	}

	/**
	 * @param serieInterno the serieInterno to set
	 */
	public void setSerieInterno(String serieInterno) {
		this.serieInterno = serieInterno;
	}

	/**
	 * @return the annio
	 */
	public String getAnnio() {
		return annio;
	}

	/**
	 * @param annio the annio to set
	 */
	public void setAnnio(String annio) {
		this.annio = annio;
	}

	/**
	 * @return the numeroAutorizacion
	 */
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	/**
	 * @param numeroAutorizacion the numeroAutorizacion to set
	 */
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	/**
	 * @return the llave
	 */
	public String getLlave() {
		return llave;
	}

	/**
	 * @param llave the llave to set
	 */
	public void setLlave(String llave) {
		this.llave = llave;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}

	/**
	 * @param fechaFinD the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	
	

}
