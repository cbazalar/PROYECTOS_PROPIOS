package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

public class MantenimientoSTOFamiliaSeguraForm extends BaseMantenimientoSTOGestionForm implements Serializable{
	
	private static final long serialVersionUID = -3034654077479981465L;
	
	private String oidPais;
	private String codigoPais;
	private String detalle;
	private String codCompania;
	private String codCliente;
	private String nombreConsultora;
	private String codTipoDocumentoIdentidad;
	private String codNumeDocumentoIdentidad;
	private String fechaProceso;
	private String codCampanaProc;
	private String codCampanaInicio;
	private String numCoasegurado;
	private String numSecuencia;
	private String numLote;
	private String codCampanaRegistro;
	private String nomBeneficiario1;
	private String codTipoDocumentoIdentidad1;
	private String codNumeDocumentoIdentidad1;
	private String nomBeneficiario2;
	private String codTipoDocumentoIdentidad2;
	private String codNumeDocumentoIdentidad2;
	private String fechaNacimiento;
	private String codSexo;
	private String codEstadoCivil;
	private String valEdad;
	private String codEstadoOCR;
	private String motRechazoOCR;
	private String codCampanaAntigua;
	private String numDocumento;
	private String salirPantalla = "N";
	private String codigoRegion;
	private String codigoZona;
	private boolean indicadorLink;
	
	private String firmaBlanco;
	
	private String longitudCodigoCliente;
	private boolean indicadorCompletarCerosNumDocumento;
	private String longitudNumeroDocIdentidad;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getOidPais() {
		return oidPais;
	}

	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	public String getCodCompania() {
		return codCompania;
	}

	public void setCodCompania(String codCompania) {
		this.codCompania = codCompania;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getNombreConsultora() {
		return nombreConsultora;
	}

	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	public String getCodTipoDocumentoIdentidad() {
		return codTipoDocumentoIdentidad;
	}

	public void setCodTipoDocumentoIdentidad(String codTipoDocumentoIdentidad) {
		this.codTipoDocumentoIdentidad = codTipoDocumentoIdentidad;
	}

	public String getCodNumeDocumentoIdentidad() {
		return codNumeDocumentoIdentidad;
	}

	public void setCodNumeDocumentoIdentidad(String codNumeDocumentoIdentidad) {
		this.codNumeDocumentoIdentidad = codNumeDocumentoIdentidad;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getCodCampanaProc() {
		return codCampanaProc;
	}

	public void setCodCampanaProc(String codCampanaProc) {
		this.codCampanaProc = codCampanaProc;
	}

	public String getCodCampanaInicio() {
		return codCampanaInicio;
	}

	public void setCodCampanaInicio(String codCampanaInicio) {
		this.codCampanaInicio = codCampanaInicio;
	}

	public String getNumCoasegurado() {
		return numCoasegurado;
	}

	public void setNumCoasegurado(String numCoasegurado) {
		this.numCoasegurado = numCoasegurado;
	}

	public String getNumSecuencia() {
		return numSecuencia;
	}

	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	public String getNumLote() {
		return numLote;
	}

	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	public String getCodCampanaRegistro() {
		return codCampanaRegistro;
	}

	public void setCodCampanaRegistro(String codCampanaRegistro) {
		this.codCampanaRegistro = codCampanaRegistro;
	}

	public String getNomBeneficiario1() {
		return nomBeneficiario1;
	}

	public void setNomBeneficiario1(String nomBeneficiario1) {
		this.nomBeneficiario1 = nomBeneficiario1;
	}

	public String getCodTipoDocumentoIdentidad1() {
		return codTipoDocumentoIdentidad1;
	}

	public void setCodTipoDocumentoIdentidad1(String codTipoDocumentoIdentidad1) {
		this.codTipoDocumentoIdentidad1 = codTipoDocumentoIdentidad1;
	}

	public String getCodNumeDocumentoIdentidad1() {
		return codNumeDocumentoIdentidad1;
	}

	public void setCodNumeDocumentoIdentidad1(String codNumeDocumentoIdentidad1) {
		this.codNumeDocumentoIdentidad1 = codNumeDocumentoIdentidad1;
	}

	public String getNomBeneficiario2() {
		return nomBeneficiario2;
	}

	public void setNomBeneficiario2(String nomBeneficiario2) {
		this.nomBeneficiario2 = nomBeneficiario2;
	}

	public String getCodTipoDocumentoIdentidad2() {
		return codTipoDocumentoIdentidad2;
	}

	public void setCodTipoDocumentoIdentidad2(String codTipoDocumentoIdentidad2) {
		this.codTipoDocumentoIdentidad2 = codTipoDocumentoIdentidad2;
	}

	public String getCodNumeDocumentoIdentidad2() {
		return codNumeDocumentoIdentidad2;
	}

	public void setCodNumeDocumentoIdentidad2(String codNumeDocumentoIdentidad2) {
		this.codNumeDocumentoIdentidad2 = codNumeDocumentoIdentidad2;
	}

	public String getCodSexo() {
		return codSexo;
	}

	public void setCodSexo(String codSexo) {
		this.codSexo = codSexo;
	}

	public String getCodEstadoCivil() {
		return codEstadoCivil;
	}

	public void setCodEstadoCivil(String codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	public String getValEdad() {
		return valEdad;
	}

	public void setValEdad(String valEdad) {
		this.valEdad = valEdad;
	}

	public String getCodEstadoOCR() {
		return codEstadoOCR;
	}

	public void setCodEstadoOCR(String codEstadoOCR) {
		this.codEstadoOCR = codEstadoOCR;
	}

	public String getMotRechazoOCR() {
		return motRechazoOCR;
	}

	public void setMotRechazoOCR(String motRechazoOCR) {
		this.motRechazoOCR = motRechazoOCR;
	}

	public String getCodCampanaAntigua() {
		return codCampanaAntigua;
	}

	public void setCodCampanaAntigua(String codCampanaAntigua) {
		this.codCampanaAntigua = codCampanaAntigua;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getSalirPantalla() {
		return salirPantalla;
	}

	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public boolean isIndicadorLink() {
		return indicadorLink;
	}

	public void setIndicadorLink(boolean indicadorLink) {
		this.indicadorLink = indicadorLink;
	}

	public String getFirmaBlanco() {
		return firmaBlanco;
	}

	public void setFirmaBlanco(String firmaBlanco) {
		this.firmaBlanco = firmaBlanco;
	}

	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	public boolean isIndicadorCompletarCerosNumDocumento() {
		return indicadorCompletarCerosNumDocumento;
	}

	public void setIndicadorCompletarCerosNumDocumento(
			boolean indicadorCompletarCerosNumDocumento) {
		this.indicadorCompletarCerosNumDocumento = indicadorCompletarCerosNumDocumento;
	}

	public String getLongitudNumeroDocIdentidad() {
		return longitudNumeroDocIdentidad;
	}

	public void setLongitudNumeroDocIdentidad(String longitudNumeroDocIdentidad) {
		this.longitudNumeroDocIdentidad = longitudNumeroDocIdentidad;
	}
}
