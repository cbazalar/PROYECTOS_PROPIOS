package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextjcairampoma@belcorp.biz
 * 
 */
public class BoletaRecojoCabecera extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;               
    private String codigoCabecera;           
    private String boletaRecojo;             
    private String numeroRecojo; 
    private String descNumeroRecojo;
    private String codigoCliente;            
    private String nombreCliente;            
    private String region;                   
    private String zona;                     
    private String seccion;                  
    private String territorio;               
    private String boletaDespacho;           
    private String periodoProceso;
    private String codigoEstado;
    private String estado;
    private String codigoResultado;
    private String descripcionResultado;
    private String fechaEmision1;
    private String fechaRecojo1;
    private String horaRecojo1;
    private String nombreTercero1;
    private String codigoMotivoNoRecojo1;
    private String motivoNoRecojo1;
    private String fechaEmision2;
    private String fechaRecojo2;
    private String horaRecojo2;
    private String nombreTercero2;
    private String codigoMotivoNoRecojo2;
    private String motivoNoRecojo2; 
    private Integer totalUnidadesRecogidas;
    private String montoOriginal;
    private String montoDiscrepante;
    private Float diferencia;  
  
	private String cierreAuto;
    private String cargoTotal;
    private String abonoTotal;
    private String aprueba;
    private String rechazo;
    
    private String fechaCierre;
    private String liquidacion;
    
    private String bloqueoPostVenta; 
    
    private String indicadorAlmacenFisico;
    private String indicadorOCSProcesado;
  
    private String codigoConsultora;
    
    private String codigoUsuario;
    
    private String numeroRecojoInicio;
    
    
    /**
	 * @return the cierreAuto
	 */
	public String getCierreAuto() {
		return cierreAuto;
	}

	/**
	 * @param cierreAuto the cierreAuto to set
	 */
	public void setCierreAuto(String cierreAuto) {
		this.cierreAuto = cierreAuto;
	}

	/**
	 * @return the cargoTotal
	 */
	public String getCargoTotal() {
		return cargoTotal;
	}

	/**
	 * @param cargoTotal the cargoTotal to set
	 */
	public void setCargoTotal(String cargoTotal) {
		this.cargoTotal = cargoTotal;
	}

	/**
	 * @return the abonoTotal
	 */
	public String getAbonoTotal() {
		return abonoTotal;
	}

	/**
	 * @param abonoTotal the abonoTotal to set
	 */
	public void setAbonoTotal(String abonoTotal) {
		this.abonoTotal = abonoTotal;
	}

	/**
	 * @return the aprueba
	 */
	public String getAprueba() {
		return aprueba;
	}

	/**
	 * @param aprueba the aprueba to set
	 */
	public void setAprueba(String aprueba) {
		this.aprueba = aprueba;
	}

	/**
	 * @return the rechazo
	 */
	public String getRechazo() {
		return rechazo;
	}

	/**
	 * @param rechazo the rechazo to set
	 */
	public void setRechazo(String rechazo) {
		this.rechazo = rechazo;
	}

    public boolean equals(Object o) {
		
		return false;
	}

	public int hashCode() {
		
		return 0;
	}

//	public String toString() {
//		
//		return null;
//	}

	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	@Override
	public String toString() {
		return "BoletaRecojoCabecera [codigoPais=" + codigoPais
				+ ", codigoCabecera=" + codigoCabecera + ", boletaRecojo="
				+ boletaRecojo + ", numeroRecojo=" + numeroRecojo
				+ ", descNumeroRecojo=" + descNumeroRecojo + ", codigoCliente="
				+ codigoCliente + ", nombreCliente=" + nombreCliente
				+ ", region=" + region + ", zona=" + zona + ", seccion="
				+ seccion + ", territorio=" + territorio + ", boletaDespacho="
				+ boletaDespacho + ", periodoProceso=" + periodoProceso
				+ ", codigoEstado=" + codigoEstado + ", estado=" + estado
				+ ", codigoResultado=" + codigoResultado
				+ ", descripcionResultado=" + descripcionResultado
				+ ", fechaEmision1=" + fechaEmision1 + ", fechaRecojo1="
				+ fechaRecojo1 + ", horaRecojo1=" + horaRecojo1
				+ ", nombreTercero1=" + nombreTercero1
				+ ", codigoMotivoNoRecojo1=" + codigoMotivoNoRecojo1
				+ ", motivoNoRecojo1=" + motivoNoRecojo1 + ", fechaEmision2="
				+ fechaEmision2 + ", fechaRecojo2=" + fechaRecojo2
				+ ", horaRecojo2=" + horaRecojo2 + ", nombreTercero2="
				+ nombreTercero2 + ", codigoMotivoNoRecojo2="
				+ codigoMotivoNoRecojo2 + ", motivoNoRecojo2="
				+ motivoNoRecojo2 + ", totalUnidadesRecogidas="
				+ totalUnidadesRecogidas + ", montoOriginal=" + montoOriginal
				+ ", montoDiscrepante=" + montoDiscrepante + ", diferencia="
				+ diferencia + ", cierreAuto=" + cierreAuto + ", cargoTotal="
				+ cargoTotal + ", abonoTotal=" + abonoTotal + ", aprueba="
				+ aprueba + ", rechazo=" + rechazo + ", fechaCierre="
				+ fechaCierre + ", liquidacion=" + liquidacion
				+ ", bloqueoPostVenta=" + bloqueoPostVenta
				+ ", indicadorAlmacenFisico=" + indicadorAlmacenFisico
				+ ", indicadorOCSProcesado=" + indicadorOCSProcesado
				+ ", codigoConsultora=" + codigoConsultora + "]";
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * @param codigoCabecera the codigoCabecera to set
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * @return the boletaRecojo
	 */
	public String getBoletaRecojo() {
		return boletaRecojo;
	}

	/**
	 * @param boletaRecojo the boletaRecojo to set
	 */
	public void setBoletaRecojo(String boletaRecojo) {
		this.boletaRecojo = boletaRecojo;
	}

	/**
	 * @return the numeroRecojo
	 */
	public String getNumeroRecojo() {
		return numeroRecojo;
	}

	/**
	 * @param numeroRecojo the numeroRecojo to set
	 */
	public void setNumeroRecojo(String numeroRecojo) {
		this.numeroRecojo = numeroRecojo;
	}

	
	/**
	 * @return the descNumeroRecojo
	 */
	public String getDescNumeroRecojo() {
		return descNumeroRecojo;
	}

	/**
	 * @param descNumeroRecojo the descNumeroRecojo to set
	 */
	public void setDescNumeroRecojo(String descNumeroRecojo) {
		this.descNumeroRecojo = descNumeroRecojo;
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
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the territorio
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * @param territorio the territorio to set
	 */
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	/**
	 * @return the boletaDespacho
	 */
	public String getBoletaDespacho() {
		return boletaDespacho;
	}

	/**
	 * @param boletaDespacho the boletaDespacho to set
	 */
	public void setBoletaDespacho(String boletaDespacho) {
		this.boletaDespacho = boletaDespacho;
	}

	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the codigoResultado
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}

	/**
	 * @param codigoResultado the codigoResultado to set
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	/**
	 * @return the descripcionResultado
	 */
	public String getDescripcionResultado() {
		return descripcionResultado;
	}

	/**
	 * @param descripcionResultado the descripcionResultado to set
	 */
	public void setDescripcionResultado(String descripcionResultado) {
		this.descripcionResultado = descripcionResultado;
	}

	/**
	 * @return the fechaEmision1
	 */
	public String getFechaEmision1() {
		return fechaEmision1;
	}

	/**
	 * @param fechaEmision1 the fechaEmision1 to set
	 */
	public void setFechaEmision1(String fechaEmision1) {
		this.fechaEmision1 = fechaEmision1;
	}

	/**
	 * @return the fechaRecojo1
	 */
	public String getFechaRecojo1() {
		return fechaRecojo1;
	}

	/**
	 * @param fechaRecojo1 the fechaRecojo1 to set
	 */
	public void setFechaRecojo1(String fechaRecojo1) {
		this.fechaRecojo1 = fechaRecojo1;
	}

	/**
	 * @return the horaRecojo1
	 */
	public String getHoraRecojo1() {
		return horaRecojo1;
	}

	/**
	 * @param horaRecojo1 the horaRecojo1 to set
	 */
	public void setHoraRecojo1(String horaRecojo1) {
		this.horaRecojo1 = horaRecojo1;
	}

	/**
	 * @return the nombreTercero1
	 */
	public String getNombreTercero1() {
		return nombreTercero1;
	}

	/**
	 * @param nombreTercero1 the nombreTercero1 to set
	 */
	public void setNombreTercero1(String nombreTercero1) {
		this.nombreTercero1 = nombreTercero1;
	}

	/**
	 * @return the codigoMotivoNoRecojo1
	 */
	public String getCodigoMotivoNoRecojo1() {
		return codigoMotivoNoRecojo1;
	}

	/**
	 * @param codigoMotivoNoRecojo1 the codigoMotivoNoRecojo1 to set
	 */
	public void setCodigoMotivoNoRecojo1(String codigoMotivoNoRecojo1) {
		this.codigoMotivoNoRecojo1 = codigoMotivoNoRecojo1;
	}

	/**
	 * @return the motivoNoRecojo1
	 */
	public String getMotivoNoRecojo1() {
		return motivoNoRecojo1;
	}

	/**
	 * @param motivoNoRecojo1 the motivoNoRecojo1 to set
	 */
	public void setMotivoNoRecojo1(String motivoNoRecojo1) {
		this.motivoNoRecojo1 = motivoNoRecojo1;
	}

	/**
	 * @return the fechaEmision2
	 */
	public String getFechaEmision2() {
		return fechaEmision2;
	}

	/**
	 * @param fechaEmision2 the fechaEmision2 to set
	 */
	public void setFechaEmision2(String fechaEmision2) {
		this.fechaEmision2 = fechaEmision2;
	}

	/**
	 * @return the fechaRecojo2
	 */
	public String getFechaRecojo2() {
		return fechaRecojo2;
	}

	/**
	 * @param fechaRecojo2 the fechaRecojo2 to set
	 */
	public void setFechaRecojo2(String fechaRecojo2) {
		this.fechaRecojo2 = fechaRecojo2;
	}

	/**
	 * @return the horaRecojo2
	 */
	public String getHoraRecojo2() {
		return horaRecojo2;
	}

	/**
	 * @param horaRecojo2 the horaRecojo2 to set
	 */
	public void setHoraRecojo2(String horaRecojo2) {
		this.horaRecojo2 = horaRecojo2;
	}

	/**
	 * @return the nombreTercero2
	 */
	public String getNombreTercero2() {
		return nombreTercero2;
	}

	/**
	 * @param nombreTercero2 the nombreTercero2 to set
	 */
	public void setNombreTercero2(String nombreTercero2) {
		this.nombreTercero2 = nombreTercero2;
	}

	/**
	 * @return the codigoMotivoNoRecojo2
	 */
	public String getCodigoMotivoNoRecojo2() {
		return codigoMotivoNoRecojo2;
	}

	/**
	 * @param codigoMotivoNoRecojo2 the codigoMotivoNoRecojo2 to set
	 */
	public void setCodigoMotivoNoRecojo2(String codigoMotivoNoRecojo2) {
		this.codigoMotivoNoRecojo2 = codigoMotivoNoRecojo2;
	}

	/**
	 * @return the motivoNoRecojo2
	 */
	public String getMotivoNoRecojo2() {
		return motivoNoRecojo2;
	}

	/**
	 * @param motivoNoRecojo2 the motivoNoRecojo2 to set
	 */
	public void setMotivoNoRecojo2(String motivoNoRecojo2) {
		this.motivoNoRecojo2 = motivoNoRecojo2;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the totalUnidadesRecogidas
	 */
	public Integer getTotalUnidadesRecogidas() {
		return totalUnidadesRecogidas;
	}

	/**
	 * @param totalUnidadesRecogidas the totalUnidadesRecogidas to set
	 */
	public void setTotalUnidadesRecogidas(Integer totalUnidadesRecogidas) {
		this.totalUnidadesRecogidas = totalUnidadesRecogidas;
	}

	/**
	 * @return the montoOriginal
	 */
	public String getMontoOriginal() {
		return montoOriginal;
	}

	/**
	 * @param montoOriginal the montoOriginal to set
	 */
	public void setMontoOriginal(String montoOriginal) {
		this.montoOriginal = montoOriginal;
	}

	/**
	 * @return the montoDiscrepante
	 */
	public String getMontoDiscrepante() {
		return montoDiscrepante;
	}

	/**
	 * @param montoDiscrepante the montoDiscrepante to set
	 */
	public void setMontoDiscrepante(String montoDiscrepante) {
		this.montoDiscrepante = montoDiscrepante;
	}

	/**
	 * @return the diferencia
	 */
	public Float getDiferencia() {
		return diferencia;
	}

	/**
	 * @param diferencia the diferencia to set
	 */
	public void setDiferencia(Float diferencia) {
		this.diferencia = diferencia;
	}

	/**
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the liquidacion
	 */
	public String getLiquidacion() {
		return liquidacion;
	}

	/**
	 * @param liquidacion the liquidacion to set
	 */
	public void setLiquidacion(String liquidacion) {
		this.liquidacion = liquidacion;
	}

	/**
	 * @return the bloqueoPostVenta
	 */
	public String getBloqueoPostVenta() {
		return bloqueoPostVenta;
	}

	/**
	 * @param bloqueoPostVenta the bloqueoPostVenta to set
	 */
	public void setBloqueoPostVenta(String bloqueoPostVenta) {
		this.bloqueoPostVenta = bloqueoPostVenta;
	}

	/**
	 * @return the indicadorAlmacenFisico
	 */
	public String getIndicadorAlmacenFisico() {
		return indicadorAlmacenFisico;
	}

	/**
	 * @param indicadorAlmacenFisico the indicadorAlmacenFisico to set
	 */
	public void setIndicadorAlmacenFisico(String indicadorAlmacenFisico) {
		this.indicadorAlmacenFisico = indicadorAlmacenFisico;
	}

	/**
	 * @return the indicadorOCSProcesado
	 */
	public String getIndicadorOCSProcesado() {
		return indicadorOCSProcesado;
	}

	/**
	 * @param indicadorOCSProcesado the indicadorOCSProcesado to set
	 */
	public void setIndicadorOCSProcesado(String indicadorOCSProcesado) {
		this.indicadorOCSProcesado = indicadorOCSProcesado;
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
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the numeroRecojoInicio
	 */
	public String getNumeroRecojoInicio() {
		return numeroRecojoInicio;
	}

	/**
	 * @param numeroRecojoInicio the numeroRecojoInicio to set
	 */
	public void setNumeroRecojoInicio(String numeroRecojoInicio) {
		this.numeroRecojoInicio = numeroRecojoInicio;
	}

}
