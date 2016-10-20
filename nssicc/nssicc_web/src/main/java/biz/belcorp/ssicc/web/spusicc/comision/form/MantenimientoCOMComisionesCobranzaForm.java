package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
* TODO Include class description here.
* 
* <p>	
* <a href="mantenimientoCOMComisionesCobranzaForm.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:jvelasquez@sigcomt.com">Jorge Velasquez</a>
* 
* @struts.form name = "mantenimientoCOMComisionesCobranzaForm"
*/
public class MantenimientoCOMComisionesCobranzaForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Comisi�n Recuperaci�n
	private Integer oidComision;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoComision;
	private String descripcion;
	private String indicadorConsideraSD = Constants.NUMERO_CERO;
	private String indicadorDctoImpuesto = Constants.NUMERO_CERO;
	private String indicadorComisionEscalonada = Constants.NUMERO_CERO;
	private String indicadorDsctoReclamoFacturacion = Constants.NUMERO_CERO;
	private String codigoBaseComision;
	private String codigoEstado;
	private String codigoTipoComisionista;
	private String estadoComision;
	private String indicadorConsiderarCronograma = Constants.NUMERO_CERO;
	private String porcentajeVentaConsultoras;
	private String porcentajeComisionRetail;
	
	//Datos Regiones
	private Date fechaAntiguedadDesdeD;
	private Date fechaAntiguedadHastaD;
	private String codigoSubGerencia;
	private String descripcionSubGerencia;
	private String[] codigoRegion;
	private String descripcionRegion;
	
	private String[] codigoZona;
	private String codigoZonaModificar;
	
	//Datos  Comisi�n
	private Integer comisionNroTramo;
	private Integer comisionNroDiasComision;
	private String comisionValPorcRecuperacion;
	private String comisionValPorcComision; //Agregada
	//private String comisionValPorcActividad;
	//private String comisionValPorcComisionActividad;
	
	//Datos Comisi�n Escalonadas
	private Integer comiEscalonadaNroEscala;
	private Integer comiEscalonadaNroDiasRecuperacion;
	private Integer comiEscalonadaNroDiasComision;
	private String comiEscalonadaPorcRecuperacionInicial;
	private String comiEscalonadaPorcRecuperacionFinal;
	private String comiEscalonadaPorcComision;
	private String comiEscalonadaPorcComisionAdicional;
	//private String comiEscalonadaBono;
	private String indicadorModificarNumeroDias;
	
	private int contador;
	private int nroEscala;
	
	protected String[] regionesSelectedItems = {};
	protected String[] datosSelectedItems = {};
	protected String[] escalonadaSelectedItems = {};
	protected String[] escalonadaSelectedItems2 = {};
	protected String[] escalonadaSelectedItems3 = {};
	protected String[] clasificacionSelectedItems = {};
	
	private String porcentajeComisionRetirada;
	private String montoFijoComision;

	// Segunda Lista Comision Escalonada
	private String comiEscalonadaValPorcInicial;
	private String comiEscalonadaValPorcFinal;
	private String comiEscalonadaPorcComision2;
	
	//tercera Lista de Comisi�n Escalonada
	private String comiEscalonadaValPorcInicialVenta;
	private String comiEscalonadaValPorcFinalVenta;
	private String comiEscalonadaValPorcInicialRecuperacion;
	private String comiEscalonadaValPorcFinalRecuperacion;
	private String comiEscalonadaPorcComision3;

	private boolean ocultarSubGerenciaRegiones;
	private boolean mostrarCheckEscalonada;
	
	private boolean indicadorBaseComision; //False = Setea el combo base Comision // True Setea todo el Valor y se coloca el codigo de Comision
	
	private String codigoTipoClasificacion;
	private String descripcionTipoClasificacion;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	
	//FlagsMigra
	private boolean indicadorComisionEscalonadaDisabled = false;
	private boolean indicadorConsiderarCronogramaDisabled = false;
	private boolean indicadorConsideraSDDisabled = false;
	private boolean indicadorDsctoReclamoFacturacionDisabled = false;
	private boolean indicadorDctoImpuestoDisabled = false;
	private boolean comiEscalonadaNroDiasRecuperacionDisabled = false;
	private boolean montoFijoComisionDisabled = false;
	private boolean porcentajeVentaConsultorasDisabled = false;
	private boolean porcentajeComisionRetailDisabled = false;
		
	private boolean indicadorComisionEscalonadaBoolean = false;
	private boolean indicadorConsiderarCronogramaBoolean = false;
	private boolean indicadorConsideraSDBoolean = false;
	private boolean indicadorDsctoReclamoFacturacionBoolean = false;
	private boolean indicadorDctoImpuestoBoolean = false;
	
	/**
	 * @return the oidComision
	 */
	public Integer getOidComision() {
		return oidComision;
	}
	/**
	 * @param oidComision the oidComision to set
	 */
	public void setOidComision(Integer oidComision) {
		this.oidComision = oidComision;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required" 
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
	 * @param codigoMarca the codigoMarca to set
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
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}
	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 * @struts.validator type="required" 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the codigoBaseComision
	 */
	public String getCodigoBaseComision() {
		return codigoBaseComision;
	}
	/**
	 * @param codigoBaseComision the codigoBaseComision to set
	 * @struts.validator type="required" 
	 */
	public void setCodigoBaseComision(String codigoBaseComision) {
		this.codigoBaseComision = codigoBaseComision;
	}
	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @return the codigoTipoComisionista
	 */
	public String getCodigoTipoComisionista() {
		return codigoTipoComisionista;
	}
	/**
	 * @param codigoTipoComisionista the codigoTipoComisionista to set
	 */
	public void setCodigoTipoComisionista(String codigoTipoComisionista) {
		this.codigoTipoComisionista = codigoTipoComisionista;
	}
	/**
	 * @return the codigoSubGerencia
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}
	/**
	 * @param codigoSubGerencia the codigoSubGerencia to set
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}
	/**
	 * @return the descripcionSubGerencia
	 */
	public String getDescripcionSubGerencia() {
		return descripcionSubGerencia;
	}
	/**
	 * @param descripcionSubGerencia the descripcionSubGerencia to set
	 */
	public void setDescripcionSubGerencia(String descripcionSubGerencia) {
		this.descripcionSubGerencia = descripcionSubGerencia;
	}
	
	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the comisionNroTramo
	 */
	public Integer getComisionNroTramo() {
		return comisionNroTramo;
	}
	/**
	 * @param comisionNroTramo the comisionNroTramo to set
	 */
	public void setComisionNroTramo(Integer comisionNroTramo) {
		this.comisionNroTramo = comisionNroTramo;
	}
	/**
	 * @return the comisionNroDiasComision
	 */
	public Integer getComisionNroDiasComision() {
		return comisionNroDiasComision;
	}
	/**
	 * @param comisionNroDiasComision the comisionNroDiasComision to set
	 */
	public void setComisionNroDiasComision(Integer comisionNroDiasComision) {
		this.comisionNroDiasComision = comisionNroDiasComision;
	}
	/**
	 * @return the comisionValPorcRecuperacion
	 */
	public String getComisionValPorcRecuperacion() {
		return comisionValPorcRecuperacion;
	}
	/**
	 * @param comisionValPorcRecuperacion the comisionValPorcRecuperacion to set
	 */
	public void setComisionValPorcRecuperacion(String comisionValPorcRecuperacion) {
		this.comisionValPorcRecuperacion = comisionValPorcRecuperacion;
	}

	/**
	 * @return the comiEscalonadaNroEscala
	 */
	public Integer getComiEscalonadaNroEscala() {
		return comiEscalonadaNroEscala;
	}
	/**
	 * @param comiEscalonadaNroEscala the comiEscalonadaNroEscala to set
	 */
	public void setComiEscalonadaNroEscala(Integer comiEscalonadaNroEscala) {
		this.comiEscalonadaNroEscala = comiEscalonadaNroEscala;
	}
	/**
	 * @return the comiEscalonadaNroDiasRecuperacion
	 */
	public Integer getComiEscalonadaNroDiasRecuperacion() {
		return comiEscalonadaNroDiasRecuperacion;
	}
	/**
	 * @param comiEscalonadaNroDiasRecuperacion the comiEscalonadaNroDiasRecuperacion to set
	 */
	public void setComiEscalonadaNroDiasRecuperacion(
			Integer comiEscalonadaNroDiasRecuperacion) {
		this.comiEscalonadaNroDiasRecuperacion = comiEscalonadaNroDiasRecuperacion;
	}
	/**
	 * @return the comiEscalonadaNroDiasComision
	 */
	public Integer getComiEscalonadaNroDiasComision() {
		return comiEscalonadaNroDiasComision;
	}
	/**
	 * @param comiEscalonadaNroDiasComision the comiEscalonadaNroDiasComision to set
	 */
	public void setComiEscalonadaNroDiasComision(
			Integer comiEscalonadaNroDiasComision) {
		this.comiEscalonadaNroDiasComision = comiEscalonadaNroDiasComision;
	}
	/**
	 * @return the comiEscalonadaPorcRecuperacionInicial
	 */
	public String getComiEscalonadaPorcRecuperacionInicial() {
		return comiEscalonadaPorcRecuperacionInicial;
	}
	/**
	 * @param comiEscalonadaPorcRecuperacionInicial the comiEscalonadaPorcRecuperacionInicial to set
	 */
	public void setComiEscalonadaPorcRecuperacionInicial(
			String comiEscalonadaPorcRecuperacionInicial) {
		this.comiEscalonadaPorcRecuperacionInicial = comiEscalonadaPorcRecuperacionInicial;
	}
	/**
	 * @return the comiEscalonadaPorcRecuperacionFinal
	 */
	public String getComiEscalonadaPorcRecuperacionFinal() {
		return comiEscalonadaPorcRecuperacionFinal;
	}
	/**
	 * @param comiEscalonadaPorcRecuperacionFinal the comiEscalonadaPorcRecuperacionFinal to set
	 */
	public void setComiEscalonadaPorcRecuperacionFinal(
			String comiEscalonadaPorcRecuperacionFinal) {
		this.comiEscalonadaPorcRecuperacionFinal = comiEscalonadaPorcRecuperacionFinal;
	}
	/**
	 * @return the comiEscalonadaPorcComision
	 */
	public String getComiEscalonadaPorcComision() {
		return comiEscalonadaPorcComision;
	}
	/**
	 * @param comiEscalonadaPorcComision the comiEscalonadaPorcComision to set
	 */
	public void setComiEscalonadaPorcComision(String comiEscalonadaPorcComision) {
		this.comiEscalonadaPorcComision = comiEscalonadaPorcComision;
	}
	/**
	 * @return the comiEscalonadaPorcComisionAdicional
	 */
	public String getComiEscalonadaPorcComisionAdicional() {
		return comiEscalonadaPorcComisionAdicional;
	}
	/**
	 * @param comiEscalonadaPorcComisionAdicional the comiEscalonadaPorcComisionAdicional to set
	 */
	public void setComiEscalonadaPorcComisionAdicional(
			String comiEscalonadaPorcComisionAdicional) {
		this.comiEscalonadaPorcComisionAdicional = comiEscalonadaPorcComisionAdicional;
	}
	/**
	 * @return the regionesSelectedItems
	 */
	public String[] getRegionesSelectedItems() {
		return regionesSelectedItems;
	}
	/**
	 * @param regionesSelectedItems the regionesSelectedItems to set
	 */
	public void setRegionesSelectedItems(String[] regionesSelectedItems) {
		this.regionesSelectedItems = regionesSelectedItems;
	}
	/**
	 * @return the datosSelectedItems
	 */
	public String[] getDatosSelectedItems() {
		return datosSelectedItems;
	}
	/**
	 * @param datosSelectedItems the datosSelectedItems to set
	 */
	public void setDatosSelectedItems(String[] datosSelectedItems) {
		this.datosSelectedItems = datosSelectedItems;
	}
	/**
	 * @return the escalonadaSelectedItems
	 */
	public String[] getEscalonadaSelectedItems() {
		return escalonadaSelectedItems;
	}
	/**
	 * @param escalonadaSelectedItems the escalonadaSelectedItems to set
	 */
	public void setEscalonadaSelectedItems(String[] escalonadaSelectedItems) {
		this.escalonadaSelectedItems = escalonadaSelectedItems;
	}
	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
	/**
	 * @return the estadoComision
	 */
	public String getEstadoComision() {
		return estadoComision;
	}
	/**
	 * @param estadoComision the estadoComision to set
	 */
	public void setEstadoComision(String estadoComision) {
		this.estadoComision = estadoComision;
	}
	/**
	 * @return the indicadorModificarNumeroDias
	 */
	public String getIndicadorModificarNumeroDias() {
		return indicadorModificarNumeroDias;
	}
	/**
	 * @param indicadorModificarNumeroDias the indicadorModificarNumeroDias to set
	 */
	public void setIndicadorModificarNumeroDias(String indicadorModificarNumeroDias) {
		this.indicadorModificarNumeroDias = indicadorModificarNumeroDias;
	}
	/**
	 * @return the indicadorConsideraSD
	 */
	public String getIndicadorConsideraSD() {
		return indicadorConsideraSD;
	}
	/**
	 * @param indicadorConsideraSD the indicadorConsideraSD to set
	 */
	public void setIndicadorConsideraSD(String indicadorConsideraSD) {
		this.indicadorConsideraSD = indicadorConsideraSD;
	}
	/**
	 * @return the indicadorDctoImpuesto
	 */
	public String getIndicadorDctoImpuesto() {
		return indicadorDctoImpuesto;
	}
	/**
	 * @param indicadorDctoImpuesto the indicadorDctoImpuesto to set
	 */
	public void setIndicadorDctoImpuesto(String indicadorDctoImpuesto) {
		this.indicadorDctoImpuesto = indicadorDctoImpuesto;
	}
	/**
	 * @return the indicadorComisionEscalonada
	 */
	public String getIndicadorComisionEscalonada() {
		return indicadorComisionEscalonada;
	}
	/**
	 * @param indicadorComisionEscalonada the indicadorComisionEscalonada to set
	 */
	public void setIndicadorComisionEscalonada(String indicadorComisionEscalonada) {
		this.indicadorComisionEscalonada = indicadorComisionEscalonada;
	}
	/**
	 * @return the indicadorDsctoReclamoFacturacion
	 */
	public String getIndicadorDsctoReclamoFacturacion() {
		return indicadorDsctoReclamoFacturacion;
	}
	/**
	 * @param indicadorDsctoReclamoFacturacion the indicadorDsctoReclamoFacturacion to set
	 */
	public void setIndicadorDsctoReclamoFacturacion(
			String indicadorDsctoReclamoFacturacion) {
		this.indicadorDsctoReclamoFacturacion = indicadorDsctoReclamoFacturacion;
	}

	/**
	 * @return the indicadorConsiderarCronograma
	 */
	public String getIndicadorConsiderarCronograma() {
		return indicadorConsiderarCronograma;
	}
	/**
	 * @param indicadorConsiderarCronograma the indicadorConsiderarCronograma to set
	 */
	public void setIndicadorConsiderarCronograma(
			String indicadorConsiderarCronograma) {
		this.indicadorConsiderarCronograma = indicadorConsiderarCronograma;
	}
	/**
	 * @return the porcentajeComisionRetirada
	 */
	public String getPorcentajeComisionRetirada() {
		return porcentajeComisionRetirada;
	}
	/**
	 * @param porcentajeComisionRetirada the porcentajeComisionRetirada to set
	 */
	public void setPorcentajeComisionRetirada(String porcentajeComisionRetirada) {
		this.porcentajeComisionRetirada = porcentajeComisionRetirada;
	}
	/**
	 * @return the montoFijoComision
	 */
	public String getMontoFijoComision() {
		return montoFijoComision;
	}
	/**
	 * @param montoFijoComision the montoFijoComision to set
	 */
	public void setMontoFijoComision(String montoFijoComision) {
		this.montoFijoComision = montoFijoComision;
	}
	/**
	 * @return the nroEscala
	 */
	public int getNroEscala() {
		return nroEscala;
	}
	/**
	 * @param nroEscala the nroEscala to set
	 */
	public void setNroEscala(int nroEscala) {
		this.nroEscala = nroEscala;
	}
	/**
	 * @return the comiEscalonadaValPorcInicial
	 */
	public String getComiEscalonadaValPorcInicial() {
		return comiEscalonadaValPorcInicial;
	}
	/**
	 * @param comiEscalonadaValPorcInicial the comiEscalonadaValPorcInicial to set
	 */
	public void setComiEscalonadaValPorcInicial(String comiEscalonadaValPorcInicial) {
		this.comiEscalonadaValPorcInicial = comiEscalonadaValPorcInicial;
	}
	/**
	 * @return the comiEscalonadaValPorcFinal
	 */
	public String getComiEscalonadaValPorcFinal() {
		return comiEscalonadaValPorcFinal;
	}
	/**
	 * @param comiEscalonadaValPorcFinal the comiEscalonadaValPorcFinal to set
	 */
	public void setComiEscalonadaValPorcFinal(String comiEscalonadaValPorcFinal) {
		this.comiEscalonadaValPorcFinal = comiEscalonadaValPorcFinal;
	}
	/**
	 * @return the comiEscalonadaPorcComision2
	 */
	public String getComiEscalonadaPorcComision2() {
		return comiEscalonadaPorcComision2;
	}
	/**
	 * @param comiEscalonadaPorcComision2 the comiEscalonadaPorcComision2 to set
	 */
	public void setComiEscalonadaPorcComision2(String comiEscalonadaPorcComision2) {
		this.comiEscalonadaPorcComision2 = comiEscalonadaPorcComision2;
	}
	
	/**
	 * @return the comiEscalonadaValPorcInicialVenta
	 */
	public String getComiEscalonadaValPorcInicialVenta() {
		return comiEscalonadaValPorcInicialVenta;
	}
	/**
	 * @param comiEscalonadaValPorcInicialVenta the comiEscalonadaValPorcInicialVenta to set
	 */
	public void setComiEscalonadaValPorcInicialVenta(
			String comiEscalonadaValPorcInicialVenta) {
		this.comiEscalonadaValPorcInicialVenta = comiEscalonadaValPorcInicialVenta;
	}
	/**
	 * @return the comiEscalonadaValPorcFinalVenta
	 */
	public String getComiEscalonadaValPorcFinalVenta() {
		return comiEscalonadaValPorcFinalVenta;
	}
	/**
	 * @param comiEscalonadaValPorcFinalVenta the comiEscalonadaValPorcFinalVenta to set
	 */
	public void setComiEscalonadaValPorcFinalVenta(
			String comiEscalonadaValPorcFinalVenta) {
		this.comiEscalonadaValPorcFinalVenta = comiEscalonadaValPorcFinalVenta;
	}
	
	/**
	 * @return the comiEscalonadaValPorcInicialRecuperacion
	 */
	public String getComiEscalonadaValPorcInicialRecuperacion() {
		return comiEscalonadaValPorcInicialRecuperacion;
	}
	/**
	 * @param comiEscalonadaValPorcInicialRecuperacion the comiEscalonadaValPorcInicialRecuperacion to set
	 */
	public void setComiEscalonadaValPorcInicialRecuperacion(
			String comiEscalonadaValPorcInicialRecuperacion) {
		this.comiEscalonadaValPorcInicialRecuperacion = comiEscalonadaValPorcInicialRecuperacion;
	}
	/**
	 * @return the comiEscalonadaValPorcFinalRecuperacion
	 */
	public String getComiEscalonadaValPorcFinalRecuperacion() {
		return comiEscalonadaValPorcFinalRecuperacion;
	}
	/**
	 * @param comiEscalonadaValPorcFinalRecuperacion the comiEscalonadaValPorcFinalRecuperacion to set
	 */
	public void setComiEscalonadaValPorcFinalRecuperacion(
			String comiEscalonadaValPorcFinalRecuperacion) {
		this.comiEscalonadaValPorcFinalRecuperacion = comiEscalonadaValPorcFinalRecuperacion;
	}
	/**
	 * @return the comiEscalonadaPorcComision3
	 */
	public String getComiEscalonadaPorcComision3() {
		return comiEscalonadaPorcComision3;
	}
	/**
	 * @param comiEscalonadaPorcComision3 the comiEscalonadaPorcComision3 to set
	 */
	public void setComiEscalonadaPorcComision3(String comiEscalonadaPorcComision3) {
		this.comiEscalonadaPorcComision3 = comiEscalonadaPorcComision3;
	}
	/**
	 * @return the escalonadaSelectedItems2
	 */
	public String[] getEscalonadaSelectedItems2() {
		return escalonadaSelectedItems2;
	}
	/**
	 * @param escalonadaSelectedItems2 the escalonadaSelectedItems2 to set
	 */
	public void setEscalonadaSelectedItems2(String[] escalonadaSelectedItems2) {
		this.escalonadaSelectedItems2 = escalonadaSelectedItems2;
	}
	/**
	 * @return the escalonadaSelectedItems3
	 */
	public String[] getEscalonadaSelectedItems3() {
		return escalonadaSelectedItems3;
	}
	/**
	 * @param escalonadaSelectedItems3 the escalonadaSelectedItems3 to set
	 */
	public void setEscalonadaSelectedItems3(String[] escalonadaSelectedItems3) {
		this.escalonadaSelectedItems3 = escalonadaSelectedItems3;
	}
	/**
	 * @return the ocultarSubGerenciaRegiones
	 */
	public boolean isOcultarSubGerenciaRegiones() {
		return ocultarSubGerenciaRegiones;
	}
	/**
	 * @param ocultarSubGerenciaRegiones the ocultarSubGerenciaRegiones to set
	 */
	public void setOcultarSubGerenciaRegiones(boolean ocultarSubGerenciaRegiones) {
		this.ocultarSubGerenciaRegiones = ocultarSubGerenciaRegiones;
	}
	/**
	 * @return the comisionValPorcComision
	 */
	public String getComisionValPorcComision() {
		return comisionValPorcComision;
	}
	/**
	 * @param comisionValPorcComision the comisionValPorcComision to set
	 */
	public void setComisionValPorcComision(String comisionValPorcComision) {
		this.comisionValPorcComision = comisionValPorcComision;
	}
	/**
	 * @return the mostrarCheckEscalonada
	 */
	public boolean isMostrarCheckEscalonada() {
		return mostrarCheckEscalonada;
	}
	/**
	 * @param mostrarCheckEscalonada the mostrarCheckEscalonada to set
	 */
	public void setMostrarCheckEscalonada(boolean mostrarCheckEscalonada) {
		this.mostrarCheckEscalonada = mostrarCheckEscalonada;
	}
	/**
	 * @return the indicadorBaseComision
	 */
	public boolean isIndicadorBaseComision() {
		return indicadorBaseComision;
	}
	/**
	 * @param indicadorBaseComision the indicadorBaseComision to set
	 */
	public void setIndicadorBaseComision(boolean indicadorBaseComision) {
		this.indicadorBaseComision = indicadorBaseComision;
	}
	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}
	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}
	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}
	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}
	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}
	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}
	/**
	 * @return the clasificacionSelectedItems
	 */
	public String[] getClasificacionSelectedItems() {
		return clasificacionSelectedItems;
	}
	/**
	 * @param clasificacionSelectedItems the clasificacionSelectedItems to set
	 */
	public void setClasificacionSelectedItems(String[] clasificacionSelectedItems) {
		this.clasificacionSelectedItems = clasificacionSelectedItems;
	}
	/**
	 * @return the porcentajeVentaConsultoras
	 */
	public String getPorcentajeVentaConsultoras() {
		return porcentajeVentaConsultoras;
	}
	/**
	 * @param porcentajeVentaConsultoras the porcentajeVentaConsultoras to set
	 */
	public void setPorcentajeVentaConsultoras(String porcentajeVentaConsultoras) {
		this.porcentajeVentaConsultoras = porcentajeVentaConsultoras;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoZonaModificar
	 */
	public String getCodigoZonaModificar() {
		return codigoZonaModificar;
	}
	/**
	 * @param codigoZonaModificar the codigoZonaModificar to set
	 */
	public void setCodigoZonaModificar(String codigoZonaModificar) {
		this.codigoZonaModificar = codigoZonaModificar;
	}
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the porcentajeComisionRetail
	 */
	public String getPorcentajeComisionRetail() {
		return porcentajeComisionRetail;
	}
	/**
	 * @param porcentajeComisionRetail the porcentajeComisionRetail to set
	 */
	public void setPorcentajeComisionRetail(String porcentajeComisionRetail) {
		this.porcentajeComisionRetail = porcentajeComisionRetail;
	}
	/**
	 * @return the indicadorComisionEscalonadaDisabled
	 */
	public boolean isIndicadorComisionEscalonadaDisabled() {
		return indicadorComisionEscalonadaDisabled;
	}
	/**
	 * @param indicadorComisionEscalonadaDisabled the indicadorComisionEscalonadaDisabled to set
	 */
	public void setIndicadorComisionEscalonadaDisabled(
			boolean indicadorComisionEscalonadaDisabled) {
		this.indicadorComisionEscalonadaDisabled = indicadorComisionEscalonadaDisabled;
	}
	/**
	 * @return the indicadorConsiderarCronogramaDisabled
	 */
	public boolean isIndicadorConsiderarCronogramaDisabled() {
		return indicadorConsiderarCronogramaDisabled;
	}
	/**
	 * @param indicadorConsiderarCronogramaDisabled the indicadorConsiderarCronogramaDisabled to set
	 */
	public void setIndicadorConsiderarCronogramaDisabled(
			boolean indicadorConsiderarCronogramaDisabled) {
		this.indicadorConsiderarCronogramaDisabled = indicadorConsiderarCronogramaDisabled;
	}
	/**
	 * @return the indicadorConsideraSDDisabled
	 */
	public boolean isIndicadorConsideraSDDisabled() {
		return indicadorConsideraSDDisabled;
	}
	/**
	 * @param indicadorConsideraSDDisabled the indicadorConsideraSDDisabled to set
	 */
	public void setIndicadorConsideraSDDisabled(boolean indicadorConsideraSDDisabled) {
		this.indicadorConsideraSDDisabled = indicadorConsideraSDDisabled;
	}
	/**
	 * @return the indicadorDsctoReclamoFacturacionDisabled
	 */
	public boolean isIndicadorDsctoReclamoFacturacionDisabled() {
		return indicadorDsctoReclamoFacturacionDisabled;
	}
	/**
	 * @param indicadorDsctoReclamoFacturacionDisabled the indicadorDsctoReclamoFacturacionDisabled to set
	 */
	public void setIndicadorDsctoReclamoFacturacionDisabled(
			boolean indicadorDsctoReclamoFacturacionDisabled) {
		this.indicadorDsctoReclamoFacturacionDisabled = indicadorDsctoReclamoFacturacionDisabled;
	}
	/**
	 * @return the indicadorDctoImpuestoDisabled
	 */
	public boolean isIndicadorDctoImpuestoDisabled() {
		return indicadorDctoImpuestoDisabled;
	}
	/**
	 * @param indicadorDctoImpuestoDisabled the indicadorDctoImpuestoDisabled to set
	 */
	public void setIndicadorDctoImpuestoDisabled(
			boolean indicadorDctoImpuestoDisabled) {
		this.indicadorDctoImpuestoDisabled = indicadorDctoImpuestoDisabled;
	}
	/**
	 * @return the comiEscalonadaNroDiasRecuperacionDisabled
	 */
	public boolean isComiEscalonadaNroDiasRecuperacionDisabled() {
		return comiEscalonadaNroDiasRecuperacionDisabled;
	}
	/**
	 * @param comiEscalonadaNroDiasRecuperacionDisabled the comiEscalonadaNroDiasRecuperacionDisabled to set
	 */
	public void setComiEscalonadaNroDiasRecuperacionDisabled(
			boolean comiEscalonadaNroDiasRecuperacionDisabled) {
		this.comiEscalonadaNroDiasRecuperacionDisabled = comiEscalonadaNroDiasRecuperacionDisabled;
	}
	/**
	 * @return the montoFijoComisionDisabled
	 */
	public boolean isMontoFijoComisionDisabled() {
		return montoFijoComisionDisabled;
	}
	/**
	 * @param montoFijoComisionDisabled the montoFijoComisionDisabled to set
	 */
	public void setMontoFijoComisionDisabled(boolean montoFijoComisionDisabled) {
		this.montoFijoComisionDisabled = montoFijoComisionDisabled;
	}
	/**
	 * @return the porcentajeVentaConsultorasDisabled
	 */
	public boolean isPorcentajeVentaConsultorasDisabled() {
		return porcentajeVentaConsultorasDisabled;
	}
	/**
	 * @param porcentajeVentaConsultorasDisabled the porcentajeVentaConsultorasDisabled to set
	 */
	public void setPorcentajeVentaConsultorasDisabled(
			boolean porcentajeVentaConsultorasDisabled) {
		this.porcentajeVentaConsultorasDisabled = porcentajeVentaConsultorasDisabled;
	}
	/**
	 * @return the porcentajeComisionRetailDisabled
	 */
	public boolean isPorcentajeComisionRetailDisabled() {
		return porcentajeComisionRetailDisabled;
	}
	/**
	 * @param porcentajeComisionRetailDisabled the porcentajeComisionRetailDisabled to set
	 */
	public void setPorcentajeComisionRetailDisabled(
			boolean porcentajeComisionRetailDisabled) {
		this.porcentajeComisionRetailDisabled = porcentajeComisionRetailDisabled;
	}
	/**
	 * @return the fechaAntiguedadDesdeD
	 */
	public Date getFechaAntiguedadDesdeD() {
		return fechaAntiguedadDesdeD;
	}
	/**
	 * @param fechaAntiguedadDesdeD the fechaAntiguedadDesdeD to set
	 */
	public void setFechaAntiguedadDesdeD(Date fechaAntiguedadDesdeD) {
		this.fechaAntiguedadDesdeD = fechaAntiguedadDesdeD;
	}
	/**
	 * @return the fechaAntiguedadHastaD
	 */
	public Date getFechaAntiguedadHastaD() {
		return fechaAntiguedadHastaD;
	}
	/**
	 * @param fechaAntiguedadHastaD the fechaAntiguedadHastaD to set
	 */
	public void setFechaAntiguedadHastaD(Date fechaAntiguedadHastaD) {
		this.fechaAntiguedadHastaD = fechaAntiguedadHastaD;
	}
	/**
	 * @return the indicadorComisionEscalonadaBoolean
	 */
	public boolean isIndicadorComisionEscalonadaBoolean() {
		return indicadorComisionEscalonadaBoolean;
	}
	/**
	 * @param indicadorComisionEscalonadaBoolean the indicadorComisionEscalonadaBoolean to set
	 */
	public void setIndicadorComisionEscalonadaBoolean(
			boolean indicadorComisionEscalonadaBoolean) {
		this.indicadorComisionEscalonadaBoolean = indicadorComisionEscalonadaBoolean;
	}
	/**
	 * @return the indicadorConsiderarCronogramaBoolean
	 */
	public boolean isIndicadorConsiderarCronogramaBoolean() {
		return indicadorConsiderarCronogramaBoolean;
	}
	/**
	 * @param indicadorConsiderarCronogramaBoolean the indicadorConsiderarCronogramaBoolean to set
	 */
	public void setIndicadorConsiderarCronogramaBoolean(
			boolean indicadorConsiderarCronogramaBoolean) {
		this.indicadorConsiderarCronogramaBoolean = indicadorConsiderarCronogramaBoolean;
	}
	/**
	 * @return the indicadorConsideraSDBoolean
	 */
	public boolean isIndicadorConsideraSDBoolean() {
		return indicadorConsideraSDBoolean;
	}
	/**
	 * @param indicadorConsideraSDBoolean the indicadorConsideraSDBoolean to set
	 */
	public void setIndicadorConsideraSDBoolean(boolean indicadorConsideraSDBoolean) {
		this.indicadorConsideraSDBoolean = indicadorConsideraSDBoolean;
	}
	/**
	 * @return the indicadorDsctoReclamoFacturacionBoolean
	 */
	public boolean isIndicadorDsctoReclamoFacturacionBoolean() {
		return indicadorDsctoReclamoFacturacionBoolean;
	}
	/**
	 * @param indicadorDsctoReclamoFacturacionBoolean the indicadorDsctoReclamoFacturacionBoolean to set
	 */
	public void setIndicadorDsctoReclamoFacturacionBoolean(
			boolean indicadorDsctoReclamoFacturacionBoolean) {
		this.indicadorDsctoReclamoFacturacionBoolean = indicadorDsctoReclamoFacturacionBoolean;
	}
	/**
	 * @return the indicadorDctoImpuestoBoolean
	 */
	public boolean isIndicadorDctoImpuestoBoolean() {
		return indicadorDctoImpuestoBoolean;
	}
	/**
	 * @param indicadorDctoImpuestoBoolean the indicadorDctoImpuestoBoolean to set
	 */
	public void setIndicadorDctoImpuestoBoolean(boolean indicadorDctoImpuestoBoolean) {
		this.indicadorDctoImpuestoBoolean = indicadorDctoImpuestoBoolean;
	}		

}