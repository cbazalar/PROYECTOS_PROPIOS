package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaHIPActualizacionDuplaCyzoneForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	
	private String codigoDupla;
	private String apellido1Modificar;
	private String apellido2Modificar;
	private String nombre1Modificar;
	private String nombre2Modificar;
	private String mailModificar;
	private String telefonoModificar;
	private String celularModificar;
	private Date fechaNacimientoModificar;
	
	private String apellido1Ingresar;
	private String apellido2Ingresar;
	private String nombre1Ingresar;
	private String nombre2Ingresar;
	private String mailIngresar;
	private String telefonoIngresar;
	private String celularIngresar;
	private Date fechaNacimientoIngresar;
	
	private boolean habilitarActualizacion;
	private String indicadorModificar;
	
	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;

	private boolean validarCaracteres1;
	private boolean validarCaracteres2;
	private boolean validarCaracteres3;

	private boolean flagModificar;
	private boolean flagIngresar;
	
	/**
	 * @return Returns the apellido1Ingresar.
	 */
	public String getApellido1Ingresar() {
		return apellido1Ingresar;
	}
	
	/**
	 * @param apellido1Ingresar The apellido1Ingresar to set.
	 */
	public void setApellido1Ingresar(String apellido1Ingresar) {
		this.apellido1Ingresar = apellido1Ingresar;
	}
	
	/**
	 * @return Returns the apellido1Modificar.
	 */
	public String getApellido1Modificar() {
		return apellido1Modificar;
	}
	
	/**
	 * @param apellido1Modificar The apellido1Modificar to set.
	 */
	public void setApellido1Modificar(String apellido1Modificar) {
		this.apellido1Modificar = apellido1Modificar;
	}
	
	/**
	 * @return Returns the apellido2Ingresar.
	 */
	public String getApellido2Ingresar() {
		return apellido2Ingresar;
	}
	
	/**
	 * @param apellido2Ingresar The apellido2Ingresar to set.
	 */
	public void setApellido2Ingresar(String apellido2Ingresar) {
		this.apellido2Ingresar = apellido2Ingresar;
	}
	
	/**
	 * @return Returns the apellido2Modificar.
	 */
	public String getApellido2Modificar() {
		return apellido2Modificar;
	}
	
	/**
	 * @param apellido2Modificar The apellido2Modificar to set.
	 */
	public void setApellido2Modificar(String apellido2Modificar) {
		this.apellido2Modificar = apellido2Modificar;
	}
	
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
	
	public Date getFechaNacimientoModificar() {
		return fechaNacimientoModificar;
	}

	public void setFechaNacimientoModificar(Date fechaNacimientoModificar) {
		this.fechaNacimientoModificar = fechaNacimientoModificar;
	}

	public Date getFechaNacimientoIngresar() {
		return fechaNacimientoIngresar;
	}

	public void setFechaNacimientoIngresar(Date fechaNacimientoIngresar) {
		this.fechaNacimientoIngresar = fechaNacimientoIngresar;
	}

	/**
	 * @return Returns the mailIngresar.
	 */
	public String getMailIngresar() {
		return mailIngresar;
	}
	
	/**
	 * @param mailIngresar The mailIngresar to set.
	 */
	public void setMailIngresar(String mailIngresar) {
		this.mailIngresar = mailIngresar;
	}
	
	/**
	 * @return Returns the mailModificar.
	 */
	public String getMailModificar() {
		return mailModificar;
	}
	
	/**
	 * @param mailModificar The mailModificar to set.
	 */
	public void setMailModificar(String mailModificar) {
		this.mailModificar = mailModificar;
	}
	
	/**
	 * @return Returns the nombre1Ingresar.
	 */
	public String getNombre1Ingresar() {
		return nombre1Ingresar;
	}
	
	/**
	 * @param nombre1Ingresar The nombre1Ingresar to set.
	 */
	public void setNombre1Ingresar(String nombre1Ingresar) {
		this.nombre1Ingresar = nombre1Ingresar;
	}
	
	/**
	 * @return Returns the nombre1Modificar.
	 */
	public String getNombre1Modificar() {
		return nombre1Modificar;
	}
	
	/**
	 * @param nombre1Modificar The nombre1Modificar to set.
	 */
	public void setNombre1Modificar(String nombre1Modificar) {
		this.nombre1Modificar = nombre1Modificar;
	}
	
	/**
	 * @return Returns the nombre2Ingresar.
	 */
	public String getNombre2Ingresar() {
		return nombre2Ingresar;
	}
	
	/**
	 * @param nombre2Ingresar The nombre2Ingresar to set.
	 */
	public void setNombre2Ingresar(String nombre2Ingresar) {
		this.nombre2Ingresar = nombre2Ingresar;
	}
	
	/**
	 * @return Returns the nombre2Modificar.
	 */
	public String getNombre2Modificar() {
		return nombre2Modificar;
	}
	
	/**
	 * @param nombre2Modificar The nombre2Modificar to set.
	 */
	public void setNombre2Modificar(String nombre2Modificar) {
		this.nombre2Modificar = nombre2Modificar;
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
	 * @return Returns the codigoDupla.
	 */
	public String getCodigoDupla() {
		return codigoDupla;
	}

	/**
	 * @param codigoDupla The codigoDupla to set.
	 */
	public void setCodigoDupla(String codigoDupla) {
		this.codigoDupla = codigoDupla;
	}

	/**
	 * @return Returns the indicadorModificar.
	 */
	public String getIndicadorModificar() {
		return indicadorModificar;
	}

	/**
	 * @param indicadorModificar The indicadorModificar to set.
	 */
	public void setIndicadorModificar(String indicadorActualizacion) {
		this.indicadorModificar = indicadorActualizacion;
	}

	/**
	 * @return Returns the habilitarActualizacion.
	 */
	public boolean isHabilitarActualizacion() {
		return habilitarActualizacion;
	}

	/**
	 * @param habilitarActualizacion The habilitarActualizacion to set.
	 */
	public void setHabilitarActualizacion(boolean habilitarActualizacion) {
		this.habilitarActualizacion = habilitarActualizacion;
	}

	/**
	 * @return the telefonoModificar
	 */
	public String getTelefonoModificar() {
		return telefonoModificar;
	}

	/**
	 * @param telefonoModificar the telefonoModificar to set
	 */
	public void setTelefonoModificar(String telefonoModificar) {
		this.telefonoModificar = telefonoModificar;
	}

	/**
	 * @return the celularModificar
	 */
	public String getCelularModificar() {
		return celularModificar;
	}

	/**
	 * @param celularModificar the celularModificar to set
	 */
	public void setCelularModificar(String celularModificar) {
		this.celularModificar = celularModificar;
	}

	/**
	 * @return the telefonoIngresar
	 */
	public String getTelefonoIngresar() {
		return telefonoIngresar;
	}

	/**
	 * @param telefonoIngresar the telefonoIngresar to set
	 */
	public void setTelefonoIngresar(String telefonoIngresar) {
		this.telefonoIngresar = telefonoIngresar;
	}

	/**
	 * @return the celularIngresar
	 */
	public String getCelularIngresar() {
		return celularIngresar;
	}

	/**
	 * @param celularIngresar the celularIngresar to set
	 */
	public void setCelularIngresar(String celularIngresar) {
		this.celularIngresar = celularIngresar;
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
	 * @return the flagModificar
	 */
	public boolean isFlagModificar() {
		return flagModificar;
	}

	/**
	 * @param flagModificar the flagModificar to set
	 */
	public void setFlagModificar(boolean flagModificar) {
		this.flagModificar = flagModificar;
	}

	/**
	 * @return the flagIngresar
	 */
	public boolean isFlagIngresar() {
		return flagIngresar;
	}

	/**
	 * @param flagIngresar the flagIngresar to set
	 */
	public void setFlagIngresar(boolean flagIngresar) {
		this.flagIngresar = flagIngresar;
	}
		
}
