package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

public class MantenimientoSTOContratoEjecutivaForm extends BaseMantenimientoSTOGestionForm
implements Serializable {
	
	
	private static final long serialVersionUID = 4455669931173849665L;
	
	private String codigoPais;
	private String codCompania;
	private String codCliente;
	private String numDocumento;
	private String fechaProceso;
	private String codPeriodo;
	private String valApellido1;
	private String valApellido2;
	private String valNombre1;
	private String valNombre2;
	private String tipoDocumento;
	private String numDocuIdentidad;
	private String numDocuLegal;
	private String indicadorFirma; 
	
	private String valDirecCliente;
	private String valBarrCliente;
	private String valCiudCliente;
	private String valDepaCliente;
	private String valTelfCliente;
	private String valCeluCliente;
	private String valTelfTrabajo;	
	private String valMailCliente;
	private String numSecuencia;
	private String numLote;	
	private String salirPantalla = "N";
	private String indicadorValidacionOK;
	private String indicadorValidacionOKHidden;
	
	private String region;
	private String zona;
	private String detalle;
	
	private String fechaNacimiento;
	private String nombreActual;
	
	
	public String getCodCliente() {
		return codCliente;
	}
	
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
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return the codPeriodo
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo the codPeriodo to set
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	/**
	 * @return the valApellido1
	 */
	public String getValApellido1() {
		return valApellido1;
	}
	/**
	 * @param valApellido1 the valApellido1 to set
	 */
	public void setValApellido1(String valApellido1) {
		this.valApellido1 = valApellido1;
	}
	/**
	 * @return the valApellido2
	 */
	public String getValApellido2() {
		return valApellido2;
	}
	/**
	 * @param valApellido2 the valApellido2 to set
	 */
	public void setValApellido2(String valApellido2) {
		this.valApellido2 = valApellido2;
	}
	/**
	 * @return the valNombre1
	 */
	public String getValNombre1() {
		return valNombre1;
	}
	/**
	 * @param valNombre1 the valNombre1 to set
	 */
	public void setValNombre1(String valNombre1) {
		this.valNombre1 = valNombre1;
	}
	/**
	 * @return the valNombre2
	 */
	public String getValNombre2() {
		return valNombre2;
	}
	/**
	 * @param valNombre2 the valNombre2 to set
	 */
	public void setValNombre2(String valNombre2) {
		this.valNombre2 = valNombre2;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numDocuIdentidad
	 */
	public String getNumDocuIdentidad() {
		return numDocuIdentidad;
	}
	/**
	 * @param numDocuIdentidad the numDocuIdentidad to set
	 */
	public void setNumDocuIdentidad(String numDocuIdentidad) {
		this.numDocuIdentidad = numDocuIdentidad;
	}
	/**
	 * @return the indicadorFirma
	 */
	public String getIndicadorFirma() {
		return indicadorFirma;
	}
	/**
	 * @param indicadorFirma the indicadorFirma to set
	 */
	public void setIndicadorFirma(String indicadorFirma) {
		this.indicadorFirma = indicadorFirma;
	}
	/**
	 * @return the valDirecCliente
	 */
	public String getValDirecCliente() {
		return valDirecCliente;
	}
	/**
	 * @param valDirecCliente the valDirecCliente to set
	 */
	public void setValDirecCliente(String valDirecCliente) {
		this.valDirecCliente = valDirecCliente;
	}
	/**
	 * @return the valBarrCliente
	 */
	public String getValBarrCliente() {
		return valBarrCliente;
	}
	/**
	 * @param valBarrCliente the valBarrCliente to set
	 */
	public void setValBarrCliente(String valBarrCliente) {
		this.valBarrCliente = valBarrCliente;
	}
	/**
	 * @return the valCiudCliente
	 */
	public String getValCiudCliente() {
		return valCiudCliente;
	}
	/**
	 * @param valCiudCliente the valCiudCliente to set
	 */
	public void setValCiudCliente(String valCiudCliente) {
		this.valCiudCliente = valCiudCliente;
	}
	/**
	 * @return the valDepaCliente
	 */
	public String getValDepaCliente() {
		return valDepaCliente;
	}
	/**
	 * @param valDepaCliente the valDepaCliente to set
	 */
	public void setValDepaCliente(String valDepaCliente) {
		this.valDepaCliente = valDepaCliente;
	}
	/**
	 * @return the valTelfCliente
	 */
	public String getValTelfCliente() {
		return valTelfCliente;
	}
	/**
	 * @param valTelfCliente the valTelfCliente to set
	 */
	public void setValTelfCliente(String valTelfCliente) {
		this.valTelfCliente = valTelfCliente;
	}
	/**
	 * @return the valCeluCliente
	 */
	public String getValCeluCliente() {
		return valCeluCliente;
	}
	/**
	 * @param valCeluCliente the valCeluCliente to set
	 */
	public void setValCeluCliente(String valCeluCliente) {
		this.valCeluCliente = valCeluCliente;
	}
	/**
	 * @return the valTelfTrabajo
	 */
	public String getValTelfTrabajo() {
		return valTelfTrabajo;
	}
	/**
	 * @param valTelfTrabajo the valTelfTrabajo to set
	 */
	public void setValTelfTrabajo(String valTelfTrabajo) {
		this.valTelfTrabajo = valTelfTrabajo;
	}
	/**
	 * @return the valMailCliente
	 */
	public String getValMailCliente() {
		return valMailCliente;
	}
	/**
	 * @param valMailCliente the valMailCliente to set
	 */
	public void setValMailCliente(String valMailCliente) {
		this.valMailCliente = valMailCliente;
	}
	/**
	 * @return the numSecuencia
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia the numSecuencia to set
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return the numLote
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote the numLote to set
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}
	/**
	 * @param salirPantalla the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}
	/**
	 * @return the indicadorValidacionOK
	 */
	public String getIndicadorValidacionOK() {
		return indicadorValidacionOK;
	}
	/**
	 * @param indicadorValidacionOK the indicadorValidacionOK to set
	 */
	public void setIndicadorValidacionOK(String indicadorValidacionOK) {
		this.indicadorValidacionOK = indicadorValidacionOK;
	}
	/**
	 * @return the indicadorValidacionOKHidden
	 */
	public String getIndicadorValidacionOKHidden() {
		return indicadorValidacionOKHidden;
	}
	/**
	 * @param indicadorValidacionOKHidden the indicadorValidacionOKHidden to set
	 */
	public void setIndicadorValidacionOKHidden(String indicadorValidacionOKHidden) {
		this.indicadorValidacionOKHidden = indicadorValidacionOKHidden;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the numDocuLegal
	 */
	public String getNumDocuLegal() {
		return numDocuLegal;
	}
	/**
	 * @param numDocuLegal the numDocuLegal to set
	 */
	public void setNumDocuLegal(String numDocuLegal) {
		this.numDocuLegal = numDocuLegal;
	}
	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 * 
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the nombreActual
	 */
	public String getNombreActual() {
		return nombreActual;
	}
	/**
	 * @param nombreActual the nombreActual to set
	 */
	public void setNombreActual(String nombreActual) {
		this.nombreActual = nombreActual;
	}
	

}
