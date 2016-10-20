package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;




/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

public class ConsultaHIPSolicitudCertificacionForm extends BaseSearchForm{

	private static final long serialVersionUID = 5807886982575468445L;

	private String codigoPais;

	private String codigoMarca;
	
	private String codigoCanal;

	private String numeroBoleta;

	private String codigoCliente;

	private String codigoPeriodo;
	
	private String tipoSeleccion;

	private String codigoConsultora;
	private String numeroPedido;
	private String fechaFacturacion;
	
	private String codConsultora;
	private String nomConsultora; 
	private String desRegZonTerri;
	
	
	private String[] selectedItemsVacio = {};

	private String[] selectedItems = {};
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private String telefonoCasa;
	private String email;
	private String celular;

	private String apellido1Reg;
	private String apellido2Reg;
	private String nombre1Reg;
	private String nombre2Reg;
	private String telefonoCasaReg;
	private String emailReg;
	private String celularReg;

	private String codTCertificacion;
	private String empDestino;
	private String numCampPromedio;
	private String codPremio;
	private String desPremio;
	private String codPeriFinalPromVenta;
	private String desPeriFinalPromVenta;
	private String modelo;
	private String codPeriIniPromVenta;
	private String marca;
	private String canal;

	private String serie;
	private String color;
	private String oidCliente;
	private String periodo;
	private String pais;
	private String nomCliente;
	private String tipDocIdentidad;
	private String numDocIdentidad;
	private String fecFacturacion;
	
	private String periodoUltimoPedido;
	
	private boolean flagSoloLecturaEmpresaDestino;

	public ConsultaHIPSolicitudCertificacionForm() {
		this.codigoPeriodo = null;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.numeroBoleta = null;
        this.codigoCliente = null;
        this.numeroBoleta = null;
        this.numeroBoleta = null;        
    	this.codigoConsultora = null;
    	this.numeroPedido = null;
    	this.fechaFacturacion = null;
    	this.flagSoloLecturaEmpresaDestino = false;
	}
	
	/**
	 * @return
	 */
	public String getTipDocIdentidad() {
		return tipDocIdentidad;
	}

	/**
	 * @param tipDocIdentidad
	 */
	public void setTipDocIdentidad(String tipDocIdentidad) {
		this.tipDocIdentidad = tipDocIdentidad;
	}

	/**
	 * @return
	 */
	public String getNumDocIdentidad() {
		return numDocIdentidad;
	}

	/**
	 * @param numDocIdentidad
	 */
	public void setNumDocIdentidad(String numDocIdentidad) {
		this.numDocIdentidad = numDocIdentidad;
	}

	/**
	 * @return
	 */
	public String getNomCliente() {
		return nomCliente;
	}

	/**
	 * @param nomCliente
	 */
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	/**
	 * @return
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return
	 */
	public String getApellido1Reg() {
		return apellido1Reg;
	}

	/**
	 * @param apellido1Reg
	 */
	public void setApellido1Reg(String apellido1Reg) {
		this.apellido1Reg = apellido1Reg;
	}

	/**
	 * @return
	 */
	public String getApellido2Reg() {
		return apellido2Reg;
	}

	/**
	 * @param apellido2Reg
	 */
	public void setApellido2Reg(String apellido2Reg) {
		this.apellido2Reg = apellido2Reg;
	}

	/**
	 * @return
	 */
	public String getNombre1Reg() {
		return nombre1Reg;
	}

	/**
	 * @param nombre1Reg
	 */
	public void setNombre1Reg(String nombre1Reg) {
		this.nombre1Reg = nombre1Reg;
	}

	/**
	 * @return
	 */
	public String getNombre2Reg() {
		return nombre2Reg;
	}

	/**
	 * @param nombre2Reg
	 */
	public void setNombre2Reg(String nombre2Reg) {
		this.nombre2Reg = nombre2Reg;
	}

	/**
	 * @return
	 */
	public String getTelefonoCasaReg() {
		return telefonoCasaReg;
	}

	/**
	 * @param telefonoCasaReg
	 */
	public void setTelefonoCasaReg(String telefonoCasaReg) {
		this.telefonoCasaReg = telefonoCasaReg;
	}

	/**
	 * @return
	 */
	public String getEmailReg() {
		return emailReg;
	}

	/**
	 * @param emailReg
	 */
	public void setEmailReg(String emailReg) {
		this.emailReg = emailReg;
	}

	/**
	 * @return
	 */
	public String getCelularReg() {
		return celularReg;
	}

	/**
	 * @param celularReg
	 */
	public void setCelularReg(String celularReg) {
		this.celularReg = celularReg;
	}

	/**
	 * @return
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @param nombre1
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @return
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @param nombre2
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	/**
	 * @return
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	/**
	 * @param telefonoCasa
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

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
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}

	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}



	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 * @struts.validator type = "required"
	 */

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the numeroBoleta
	 */
	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	

	/**
	 * @return the tipoSeleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * @param tipoSeleccion
	 *            the tipoSeleccion to set
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	/**
	 * @return the selectedItemsVacio
	 */
	public String[] getSelectedItemsVacio() {
		return selectedItemsVacio;
	}

	/**
	 * @param selectedItemsVacio the selectedItemsVacio to set
	 */
	public void setSelectedItemsVacio(String[] selectedItemsVacio) {
		this.selectedItemsVacio = selectedItemsVacio;
	}

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getNomConsultora() {
		return nomConsultora;
	}

	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}

	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}

	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}

	/**
	 * @return
	 */
	public String getCodConsultora() {
		return codConsultora;
	}

	/**
	 * @param codConsultora
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}

	/**
	 * @return
	 */
	public String getCodTCertificacion() {
		return codTCertificacion;
	}

	/**
	 * @param codTCertificacion The codTCertificacion to set.
	 * @struts.validator type = "required"
	 */
	public void setCodTCertificacion(String codTCertificacion) {
		this.codTCertificacion = codTCertificacion;
	}

	public String getEmpDestino() {
		return empDestino;
	}

	/**
	 * @param empDestino
	 *            The empDestino to set.
	 * @struts.validator type = "required"
	 */	
	public void setEmpDestino(String empDestino) {
		this.empDestino = empDestino;
	}

	/**
	 * @return
	 */
	public String getNumCampPromedio() {
		return numCampPromedio;
	}

	/**
	 * @param numCampPromedio
	 */
	public void setNumCampPromedio(String numCampPromedio) {
		this.numCampPromedio = numCampPromedio;
	}

	/**
	 * @return
	 */
	public String getCodPremio() {
		return codPremio;
	}

	/**
	 * @param codPremio
	 */
	public void setCodPremio(String codPremio) {
		this.codPremio = codPremio;
	}

	/**
	 * @return
	 */
	public String getDesPremio() {
		return desPremio;
	}

	/**
	 * @param desPremio
	 */
	public void setDesPremio(String desPremio) {
		this.desPremio = desPremio;
	}

	/**
	 * @return
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return
	 */
	public String getCodPeriFinalPromVenta() {
		return codPeriFinalPromVenta;
	}

	/**
	 * @param codPeriFinalPromVenta
	 */
	public void setCodPeriFinalPromVenta(String codPeriFinalPromVenta) {
		this.codPeriFinalPromVenta = codPeriFinalPromVenta;
	}

	/**
	 * @return
	 */
	public String getDesPeriFinalPromVenta() {
		return desPeriFinalPromVenta;
	}

	/**
	 * @param desPeriFinalPromVenta
	 */
	public void setDesPeriFinalPromVenta(String desPeriFinalPromVenta) {
		this.desPeriFinalPromVenta = desPeriFinalPromVenta;
	}

	/**
	 * @return
	 */
	public String getCodPeriIniPromVenta() {
		return codPeriIniPromVenta;
	}

	/**
	 * @param codPeriIniPromVenta
	 */
	public void setCodPeriIniPromVenta(String codPeriIniPromVenta) {
		this.codPeriIniPromVenta = codPeriIniPromVenta;
	}

	/**
	 * @return
	 */
	public String getFecFacturacion() {
		return fecFacturacion;
	}

	/**
	 * @param fecFacturacion
	 */
	public void setFecFacturacion(String fecFacturacion) {
		this.fecFacturacion = fecFacturacion;
	}

	/**
	 * @return
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * @param canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * @return Returns the periodoUltimoPedido.
	 */
	public String getPeriodoUltimoPedido() {
		return periodoUltimoPedido;
	}

	/**
	 * @param periodoUltimoPedido The periodoUltimoPedido to set.
	 */
	public void setPeriodoUltimoPedido(String periodoUltimoPedido) {
		this.periodoUltimoPedido = periodoUltimoPedido;
	}

	/**
	 * @return the flagSoloLecturaEmpresaDestino
	 */
	public boolean isFlagSoloLecturaEmpresaDestino() {
		return flagSoloLecturaEmpresaDestino;
	}

	/**
	 * @param flagSoloLecturaEmpresaDestino the flagSoloLecturaEmpresaDestino to set
	 */
	public void setFlagSoloLecturaEmpresaDestino(
			boolean flagSoloLecturaEmpresaDestino) {
		this.flagSoloLecturaEmpresaDestino = flagSoloLecturaEmpresaDestino;
	}

	
	
}
