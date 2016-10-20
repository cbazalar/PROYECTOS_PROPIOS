package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *                      
 */

public class ConsultaHIPReclamosForm extends BaseSearchForm {
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2417861243725559462L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String selectedItem;
	
	private String numeroReclamo;
	private String fechaProceso;
	private String etapaBoleta;
	private String fechaEmision;
	private String estadoBoleta;
	
	private boolean mostrarBotonBoletaRecojo;
	
	/* INI SA PER-SiCC-2012-0788 */
	private String estadoReclamo;
	/* FIN SA PER-SiCC-2012-0788 */
	
	private String usuarioDigitacion;
	private String fechaDigitacion;
	private String usuarioAprobacion;
	private String fechaAprobacion;
	private String origenCDR;
	
	private String usuarioRegistro;
	private String fechaRegistro;
	
	/**
	 * @return Returns the codConsultora.
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora The codConsultora to set.
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return Returns the desRegZonTerri.
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri The desRegZonTerri to set.
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @return Returns the nomConsultora.
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora The nomConsultora to set.
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return Returns the estadoBoleta.
	 */
	public String getEstadoBoleta() {
		return estadoBoleta;
	}
	/**
	 * @param estadoBoleta The estadoBoleta to set.
	 */
	public void setEstadoBoleta(String estadoBoleta) {
		this.estadoBoleta = estadoBoleta;
	}
	/**
	 * @return Returns the etapaBoleta.
	 */
	public String getEtapaBoleta() {
		return etapaBoleta;
	}
	/**
	 * @param etapaBoleta The etapaBoleta to set.
	 */
	public void setEtapaBoleta(String etapaBoleta) {
		this.etapaBoleta = etapaBoleta;
	}
	/**
	 * @return Returns the fechaEmision.
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision The fechaEmision to set.
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the numeroReclamo.
	 */
	public String getNumeroReclamo() {
		return numeroReclamo;
	}
	/**
	 * @param numeroReclamo The numeroReclamo to set.
	 */
	public void setNumeroReclamo(String numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	/**
	 * @return Returns the mostrarBotonBoletaRecojo.
	 */
	public boolean isMostrarBotonBoletaRecojo() {
		return mostrarBotonBoletaRecojo;
	}
	/**
	 * @param mostrarBotonBoletaRecojo The mostrarBotonBoletaRecojo to set.
	 */
	public void setMostrarBotonBoletaRecojo(boolean mostrarBotonBoletaRecojo) {
		this.mostrarBotonBoletaRecojo = mostrarBotonBoletaRecojo;
	}
	/**
	 * @return the estadoReclamo
	 */
	public String getEstadoReclamo() {
		return estadoReclamo;
	}
	/**
	 * @param estadoReclamo the estadoReclamo to set
	 */
	public void setEstadoReclamo(String estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}
	/**
	 * @return the usuarioDigitacion
	 */
	public String getUsuarioDigitacion() {
		return usuarioDigitacion;
	}
	/**
	 * @param usuarioDigitacion the usuarioDigitacion to set
	 */
	public void setUsuarioDigitacion(String usuarioDigitacion) {
		this.usuarioDigitacion = usuarioDigitacion;
	}
	/**
	 * @return the fechaDigitacion
	 */
	public String getFechaDigitacion() {
		return fechaDigitacion;
	}
	/**
	 * @param fechaDigitacion the fechaDigitacion to set
	 */
	public void setFechaDigitacion(String fechaDigitacion) {
		this.fechaDigitacion = fechaDigitacion;
	}
	/**
	 * @return the usuarioAprobacion
	 */
	public String getUsuarioAprobacion() {
		return usuarioAprobacion;
	}
	/**
	 * @param usuarioAprobacion the usuarioAprobacion to set
	 */
	public void setUsuarioAprobacion(String usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}
	/**
	 * @return the fechaAprobacion
	 */
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	/**
	 * @param fechaAprobacion the fechaAprobacion to set
	 */
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	/**
	 * @return the origenCDR
	 */
	public String getOrigenCDR() {
		return origenCDR;
	}
	/**
	 * @param origenCDR the origenCDR to set
	 */
	public void setOrigenCDR(String origenCDR) {
		this.origenCDR = origenCDR;
	}
	/**
	 * @return the usuarioRegistro
	 */
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	/**
	 * @param usuarioRegistro the usuarioRegistro to set
	 */
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}	
	
}
