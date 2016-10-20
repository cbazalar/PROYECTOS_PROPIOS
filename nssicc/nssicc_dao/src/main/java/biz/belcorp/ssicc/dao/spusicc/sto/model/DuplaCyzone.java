package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class DuplaCyzone implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaNacimiento;
	private String fechaProceso;
	private String codPeriodo;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String indDuplaNueva;
	private String indActuDatos;
	private String indEnvio;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valMailCliente;
	private String valRegionArribo;
	private String valZonaArribo;
	private String indEstaProceso;
	private String indMotivoRechazo;
	private String codTipoDocumento;
	private String numSecuencia;
	private String numLote;
	private String detalle;
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	/**
	 * @return Returns the codCompania.
	 */
	public String getCodCompania() {
		return codCompania;
	}
	/**
	 * @param codCompania The codCompania to set.
	 */
	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
	}
	
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}

	/**
	 * @return Returns the codTipoDocumento.
	 */
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	/**
	 * @param codTipoDocumento The codTipoDocumento to set.
	 */
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	
	/**
	 * @return Returns the indEstaProceso.
	 */
	public String getIndEstaProceso() {
		return indEstaProceso;
	}
	/**
	 * @param indEstaProceso The indEstaProceso to set.
	 */
	public void setIndEstaProceso(String indEstaProceso) {
		this.indEstaProceso = indEstaProceso;
	}
	/**
	 * @return Returns the indMotivoRechazo.
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	/**
	 * @param indMotivoRechazo The indMotivoRechazo to set.
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
	}
	

	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @return Returns the valApellido1.
	 */
	public String getValApellido1() {
		return valApellido1;
	}
	/**
	 * @param valApellido1 The valApellido1 to set.
	 */
	public void setValApellido1(String valApellido1) {
		this.valApellido1 = valApellido1;
	}
	
	/**
	 * @return Returns the valApellido2.
	 */
	public String getValApellido2() {
		return valApellido2;
	}
	/**
	 * @param valApellido2 The valApellido2 to set.
	 */
	public void setValApellido2(String valApellido2) {
		this.valApellido2 = valApellido2;
	}
	
	
	/**
	 * @return Returns the valCeluCliente.
	 */
	public String getValCeluCliente() {
		return valCeluCliente;
	}
	/**
	 * @param valCeluCliente The valCeluCliente to set.
	 */
	public void setValCeluCliente(String valCeluCliente) {
		this.valCeluCliente = valCeluCliente;
	}
	

	
	/**
	 * @return Returns the valMailCliente.
	 */
	public String getValMailCliente() {
		return valMailCliente;
	}
	/**
	 * @param valMailCliente The valMailCliente to set.
	 */
	public void setValMailCliente(String valMailCliente) {
		this.valMailCliente = valMailCliente;
	}
	/**
	 * @return Returns the valNombre1.
	 */
	public String getValNombre1() {
		return valNombre1;
	}
	/**
	 * @param valNombre1 The valNombre1 to set.
	 */
	public void setValNombre1(String valNombre1) {
		this.valNombre1 = valNombre1;
	}
	
	/**
	 * @return Returns the valNombre2.
	 */
	public String getValNombre2() {
		return valNombre2;
	}
	/**
	 * @param valNombre2 The valNombre2 to set.
	 */
	public void setValNombre2(String valNombre2) {
		this.valNombre2 = valNombre2;
	}
	
	
	/**
	 * @return Returns the valRegionArribo.
	 */
	public String getValRegionArribo() {
		return valRegionArribo;
	}
	/**
	 * @param valRegionArribo The valRegionArribo to set.
	 */
	public void setValRegionArribo(String valRegionArribo) {
		this.valRegionArribo = valRegionArribo;
	}
	/**
	 * @return Returns the valTelfCliente.
	 */
	public String getValTelfCliente() {
		return valTelfCliente;
	}
	/**
	 * @param valTelfCliente The valTelfCliente to set.
	 */
	public void setValTelfCliente(String valTelfCliente) {
		this.valTelfCliente = valTelfCliente;
	}
	
	
	/**
	 * @return Returns the valZonaArribo.
	 */
	public String getValZonaArribo() {
		return valZonaArribo;
	}
	/**
	 * @param valZonaArribo The valZonaArribo to set.
	 */
	public void setValZonaArribo(String valZonaArribo) {
		this.valZonaArribo = valZonaArribo;
	}
	/**
	 * @return Returns the numSecuencia.
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia The numSecuencia to set.
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
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
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle The detalle to set.
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return Returns the fechaNacimiento.
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento The fechaNacimiento to set.
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return Returns the indActuDatos.
	 */
	public String getIndActuDatos() {
		return indActuDatos;
	}
	/**
	 * @param indActuDatos The indActuDatos to set.
	 */
	public void setIndActuDatos(String indActuDatos) {
		this.indActuDatos = indActuDatos;
	}
	/**
	 * @return Returns the indDuplaNueva.
	 */
	public String getIndDuplaNueva() {
		return indDuplaNueva;
	}
	/**
	 * @param indDuplaNueva The indDuplaNueva to set.
	 */
	public void setIndDuplaNueva(String indDuplaNueva) {
		this.indDuplaNueva = indDuplaNueva;
	}
	/**
	 * @return Returns the indEnvio.
	 */
	public String getIndEnvio() {
		return indEnvio;
	}
	/**
	 * @param indEnvio The indEnvio to set.
	 */
	public void setIndEnvio(String indEnvio) {
		this.indEnvio = indEnvio;
	}
	
	
}
