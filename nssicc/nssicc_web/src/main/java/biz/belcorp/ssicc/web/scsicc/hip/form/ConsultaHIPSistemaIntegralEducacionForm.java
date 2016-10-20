package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaHIPSistemaIntegralEducacionForm extends BaseSearchForm {

	private static final long serialVersionUID = 2593553698401475693L;

	private String codigoPais;

	private String codigoMarca;
	
	private String codigoCanal;

	private String numeroBoleta;

	private String codigoCliente;

	private String [] regionList;
	
	private String []zonaList;
	
	private String []seccionList;

	private String []territorioList;
	
	private String[] selectedItemsVacio = {};

	private String[] selectedItems = {};

	private String fechaInicial;

	private String fechaFinal;
	
	private String codigoPeriodo;
	
	private String tipoSeleccion;

	private String tipoSeleccion1;
	private String tipoSeleccion2;
	private String tipoSeleccion3;	
	
	private String codigoProceso;

	private File archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo	
	
	private String flagNotaMercaderiaPerdida;
	private String flagMostrarError;
	
	private String codigoConsultora;
	private String numeroPedido;
	private String fechaFacturacion;
	
	private String codConsultora;
	private String nomConsultora; 
	private String desRegZonTerri;
	
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

	private String desActividad;
	private String fecPeriodoAnterior;
	private String fecPeriodoActual;
	private String fecPeriodoDespues;

	private String nivAlcanzado;
	private String estado;
	private String nivPorAlcanzar;

	private String lugar;
	private String direccion;
	private String fecha;
	private String hora;

	public ConsultaHIPSistemaIntegralEducacionForm() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.fechaInicial = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaFinal = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoPeriodo = null;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.numeroBoleta = null;
        this.codigoCliente = null;
        this.numeroBoleta = null;
        this.numeroBoleta = null;        
    	this.regionList = null;
    	this.codigoConsultora = null;
    	this.numeroPedido = null;
    	this.fechaFacturacion = null;
    	this.zonaList = null;
	}
	
	/**
	 * @return
	 */
	public String getNivAlcanzado() {
		return nivAlcanzado;
	}

	/**
	 * @param nivAlcanzado
	 */
	public void setNivAlcanzado(String nivAlcanzado) {
		this.nivAlcanzado = nivAlcanzado;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return
	 */
	public String getNivPorAlcanzar() {
		return nivPorAlcanzar;
	}

	/**
	 * @param nivPorAlcanzar
	 */
	public void setNivPorAlcanzar(String nivPorAlcanzar) {
		this.nivPorAlcanzar = nivPorAlcanzar;
	}

	/**
	 * @return
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
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
	 * @return the flagNotaMercaderiaPerdida
	 */
	public String getFlagNotaMercaderiaPerdida() {
		return flagNotaMercaderiaPerdida;
	}

	/**
	 * @param flagNotaMercaderiaPerdida the flagNotaMercaderiaPerdida to set
	 */
	public void setFlagNotaMercaderiaPerdida(String flagNotaMercaderiaPerdida) {
		this.flagNotaMercaderiaPerdida = flagNotaMercaderiaPerdida;
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
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the territorioList
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList the territorioList to set
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
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
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	 * @return the archivo
	 */
	public File getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the flagMostrarError
	 */
	public String getFlagMostrarError() {
		return flagMostrarError;
	}

	/**
	 * @param flagMostrarError the flagMostrarError to set
	 */
	public void setFlagMostrarError(String flagMostrarError) {
		this.flagMostrarError = flagMostrarError;
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
	 * @return the tipoSeleccion1
	 */
	public String getTipoSeleccion1() {
		return tipoSeleccion1;
	}

	/**
	 * @param tipoSeleccion1 the tipoSeleccion1 to set
	 */
	public void setTipoSeleccion1(String tipoSeleccion1) {
		this.tipoSeleccion1 = tipoSeleccion1;
	}

	/**
	 * @return the tipoSeleccion2
	 */
	public String getTipoSeleccion2() {
		return tipoSeleccion2;
	}

	/**
	 * @param tipoSeleccion2 the tipoSeleccion2 to set
	 */
	public void setTipoSeleccion2(String tipoSeleccion2) {
		this.tipoSeleccion2 = tipoSeleccion2;
	}

	/**
	 * @return the tipoSeleccion3
	 */
	public String getTipoSeleccion3() {
		return tipoSeleccion3;
	}

	/**
	 * @param tipoSeleccion3 the tipoSeleccion3 to set
	 */
	public void setTipoSeleccion3(String tipoSeleccion3) {
		this.tipoSeleccion3 = tipoSeleccion3;
	}

	/**
	 * @return
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}

	/**
	 * @param nomConsultora
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}

	/**
	 * @return
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}

	/**
	 * @param desRegZonTerri
	 */
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
	public String getDesActividad() {
		return desActividad;
	}

	/**
	 * @param desActividad
	 */
	public void setDesActividad(String desActividad) {
		this.desActividad = desActividad;
	}

	/**
	 * @return
	 */
	public String getFecPeriodoAnterior() {
		return fecPeriodoAnterior;
	}

	/**
	 * @param fecPeriodoAnterior
	 */
	public void setFecPeriodoAnterior(String fecPeriodoAnterior) {
		this.fecPeriodoAnterior = fecPeriodoAnterior;
	}

	/**
	 * @return
	 */
	public String getFecPeriodoActual() {
		return fecPeriodoActual;
	}

	/**
	 * @param fecPeriodoActual
	 */
	public void setFecPeriodoActual(String fecPeriodoActual) {
		this.fecPeriodoActual = fecPeriodoActual;
	}

	/**
	 * @return
	 */
	public String getFecPeriodoDespues() {
		return fecPeriodoDespues;
	}

	/**
	 * @param fecPeriodoDespues
	 */
	public void setFecPeriodoDespues(String fecPeriodoDespues) {
		this.fecPeriodoDespues = fecPeriodoDespues;
	}

	
	
}
