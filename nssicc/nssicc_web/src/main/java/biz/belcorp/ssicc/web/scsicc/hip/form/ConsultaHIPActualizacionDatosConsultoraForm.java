package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaHIPActualizacionDatosConsultoraForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	
	private String telefonoCasa;
	private String telefonoCelular;
	private String mail;
	private String fechaNacimiento;
	
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private String tipoDocumento;
	private String numeroDocumento;
	
	private boolean validarCaracteres1;
	private boolean validarCaracteres2;
	private boolean validarCaracteres3;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;

	private boolean permitirActualizacion;
	
	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	
	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	
	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	
	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	
	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	
	/**
	 * @return the telefonoCasa
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	
	/**
	 * @param telefonoCasa the telefonoCasa to set
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	
	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	
	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return the nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @param nombre1 the nombre1 to set
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @return the nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @param nombre2 the nombre2 to set
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
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
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the validarCaracteres1
	 */
	public boolean isValidarCaracteres1() {
		return validarCaracteres1;
	}

	/**
	 * @param validarCaracteres1 the validarCaracteres1 to set
	 */
	public void setValidarCaracteres1(boolean validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}

	/**
	 * @return the validarCaracteres2
	 */
	public boolean isValidarCaracteres2() {
		return validarCaracteres2;
	}

	/**
	 * @param validarCaracteres2 the validarCaracteres2 to set
	 */
	public void setValidarCaracteres2(boolean validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}

	/**
	 * @return the validarCaracteres3
	 */
	public boolean isValidarCaracteres3() {
		return validarCaracteres3;
	}

	/**
	 * @param validarCaracteres3 the validarCaracteres3 to set
	 */
	public void setValidarCaracteres3(boolean validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}

	/**
	 * @return the cadenaCaracteresV1
	 */
	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}

	/**
	 * @param cadenaCaracteresV1 the cadenaCaracteresV1 to set
	 */
	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}

	/**
	 * @return the cadenaCaracteresNV1
	 */
	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}

	/**
	 * @param cadenaCaracteresNV1 the cadenaCaracteresNV1 to set
	 */
	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}

	/**
	 * @return the cadenaCaracteresV2
	 */
	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}

	/**
	 * @param cadenaCaracteresV2 the cadenaCaracteresV2 to set
	 */
	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}

	/**
	 * @return the cadenaCaracteresNV2
	 */
	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}

	/**
	 * @param cadenaCaracteresNV2 the cadenaCaracteresNV2 to set
	 */
	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}

	/**
	 * @return the cadenaCaracteresV3
	 */
	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}

	/**
	 * @param cadenaCaracteresV3 the cadenaCaracteresV3 to set
	 */
	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}

	/**
	 * @return the cadenaCaracteresNV3
	 */
	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}

	/**
	 * @param cadenaCaracteresNV3 the cadenaCaracteresNV3 to set
	 */
	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}

	/**
	 * @return the permitirActualizacion
	 */
	public boolean isPermitirActualizacion() {
		return permitirActualizacion;
	}

	/**
	 * @param permitirActualizacion the permitirActualizacion to set
	 */
	public void setPermitirActualizacion(boolean permitirActualizacion) {
		this.permitirActualizacion = permitirActualizacion;
	}
		
}
