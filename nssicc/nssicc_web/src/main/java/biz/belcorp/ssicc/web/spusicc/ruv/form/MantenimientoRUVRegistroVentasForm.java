package biz.belcorp.ssicc.web.spusicc.ruv.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRUVRegistroVentasForm extends BaseEditForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	
	private String oidReg;    
	private String oidEmpresa;
	private String oidIndImpuestos;    
	private Date fechaEmision;   
	private String codigoCliente;   
	private String nombre1; 
	private String nombre2;    
	private String apellido1; 
	private String apellido2;  
	private String baseImponible;   
	private String importeImpuesto;   
	private String importeTotal;
	private String coeficienteImpuesto;  
	private String oidCanal;					
	private String oidAcceso;					
	private String oidSubAcceso;     
	private String puntoEmision;     
	private String oidTipoDocumentoLegal;    
	private String serieDocumentoLegal;

    private String numeroDocumentoLegal;
	private String numeroIdentificacionFiscal; 
	private String numeroIdentificacionNacional;  
	private String oidTipoDocumentoRef;   
	private String serieDocumentoRef;    

    private String numeroDocumentoRef;
	private String estadoAnulado;     
	private String estadoRUV;  
	private String indTransfGratuita;
        
    private String descuento; 
    private String baseImponibleNeto; 
    private String numeroDocumentoLegalFinal;
    private String exportacion;
    
    private String oidTipoDocumento;    
    private Date fechaEmisionReferencia;  
 
    private String numDocControlLegal;
    private String numDocControlFinalLegal;
    
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the oidReg
	 */
	public String getOidReg() {
		return oidReg;
	}
	/**
	 * @param oidReg the oidReg to set
	 */
	public void setOidReg(String oidReg) {
		this.oidReg = oidReg;
	}
	/**
	 * @return the oidEmpresa
	 */
	public String getOidEmpresa() {
		return oidEmpresa;
	}
	/**
	 * @param oidEmpresa the oidEmpresa to set
	 */
	public void setOidEmpresa(String oidEmpresa) {
		this.oidEmpresa = oidEmpresa;
	}
	/**
	 * @return the oidIndImpuestos
	 */
	public String getOidIndImpuestos() {
		return oidIndImpuestos;
	}
	/**
	 * @param oidIndImpuestos the oidIndImpuestos to set
	 */
	public void setOidIndImpuestos(String oidIndImpuestos) {
		this.oidIndImpuestos = oidIndImpuestos;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
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
	 * @return the baseImponible
	 */
	public String getBaseImponible() {
		return baseImponible;
	}
	/**
	 * @param baseImponible the baseImponible to set
	 */
	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}
	/**
	 * @return the importeImpuesto
	 */
	public String getImporteImpuesto() {
		return importeImpuesto;
	}
	/**
	 * @param importeImpuesto the importeImpuesto to set
	 */
	public void setImporteImpuesto(String importeImpuesto) {
		this.importeImpuesto = importeImpuesto;
	}
	/**
	 * @return the importeTotal
	 */
	public String getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}
	/**
	 * @return the coeficienteImpuesto
	 */
	public String getCoeficienteImpuesto() {
		return coeficienteImpuesto;
	}
	/**
	 * @param coeficienteImpuesto the coeficienteImpuesto to set
	 */
	public void setCoeficienteImpuesto(String coeficienteImpuesto) {
		this.coeficienteImpuesto = coeficienteImpuesto;
	}
	/**
	 * @return the oidCanal
	 */
	public String getOidCanal() {
		return oidCanal;
	}
	/**
	 * @param oidCanal the oidCanal to set
	 */
	public void setOidCanal(String oidCanal) {
		this.oidCanal = oidCanal;
	}
	/**
	 * @return the oidAcceso
	 */
	public String getOidAcceso() {
		return oidAcceso;
	}
	/**
	 * @param oidAcceso the oidAcceso to set
	 */
	public void setOidAcceso(String oidAcceso) {
		this.oidAcceso = oidAcceso;
	}
	/**
	 * @return the oidSubAcceso
	 */
	public String getOidSubAcceso() {
		return oidSubAcceso;
	}
	/**
	 * @param oidSubAcceso the oidSubAcceso to set
	 */
	public void setOidSubAcceso(String oidSubAcceso) {
		this.oidSubAcceso = oidSubAcceso;
	}
	/**
	 * @return the puntoEmision
	 */
	public String getPuntoEmision() {
		return puntoEmision;
	}
	/**
	 * @param puntoEmision the puntoEmision to set
	 */
	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}
	/**
	 * @return the oidTipoDocumentoLegal
	 */
	public String getOidTipoDocumentoLegal() {
		return oidTipoDocumentoLegal;
	}
	/**
	 * @param oidTipoDocumentoLegal the oidTipoDocumentoLegal to set
	 */
	public void setOidTipoDocumentoLegal(String oidTipoDocumentoLegal) {
		this.oidTipoDocumentoLegal = oidTipoDocumentoLegal;
	}
	/**
	 * @return the serieDocumentoLegal
	 */
	public String getSerieDocumentoLegal() {
		return serieDocumentoLegal;
	}
	/**
	 * @param serieDocumentoLegal the serieDocumentoLegal to set
	 */
	public void setSerieDocumentoLegal(String serieDocumentoLegal) {
		this.serieDocumentoLegal = serieDocumentoLegal;
	}
	/**
	 * @return the numeroDocumentoLegal
	 */
	public String getNumeroDocumentoLegal() {
		return numeroDocumentoLegal;
	}
	/**
	 * @param numeroDocumentoLegal the numeroDocumentoLegal to set
	 */
	public void setNumeroDocumentoLegal(String numeroDocumentoLegal) {
		this.numeroDocumentoLegal = numeroDocumentoLegal;
	}
	/**
	 * @return the numeroIdentificacionFiscal
	 */
	public String getNumeroIdentificacionFiscal() {
		return numeroIdentificacionFiscal;
	}
	/**
	 * @param numeroIdentificacionFiscal the numeroIdentificacionFiscal to set
	 */
	public void setNumeroIdentificacionFiscal(String numeroIdentificacionFiscal) {
		this.numeroIdentificacionFiscal = numeroIdentificacionFiscal;
	}
	/**
	 * @return the numeroIdentificacionNacional
	 */
	public String getNumeroIdentificacionNacional() {
		return numeroIdentificacionNacional;
	}
	/**
	 * @param numeroIdentificacionNacional the numeroIdentificacionNacional to set
	 */
	public void setNumeroIdentificacionNacional(String numeroIdentificacionNacional) {
		this.numeroIdentificacionNacional = numeroIdentificacionNacional;
	}
	/**
	 * @return the oidTipoDocumentoRef
	 */
	public String getOidTipoDocumentoRef() {
		return oidTipoDocumentoRef;
	}
	/**
	 * @param oidTipoDocumentoRef the oidTipoDocumentoRef to set
	 */
	public void setOidTipoDocumentoRef(String oidTipoDocumentoRef) {
		this.oidTipoDocumentoRef = oidTipoDocumentoRef;
	}
	/**
	 * @return the serieDocumentoRef
	 */
	public String getSerieDocumentoRef() {
		return serieDocumentoRef;
	}
	/**
	 * @param serieDocumentoRef the serieDocumentoRef to set
	 */
	public void setSerieDocumentoRef(String serieDocumentoRef) {
		this.serieDocumentoRef = serieDocumentoRef;
	}
	/**
	 * @return the numeroDocumentoRef
	 */
	public String getNumeroDocumentoRef() {
		return numeroDocumentoRef;
	}
	/**
	 * @param numeroDocumentoRef the numeroDocumentoRef to set
	 */
	public void setNumeroDocumentoRef(String numeroDocumentoRef) {
		this.numeroDocumentoRef = numeroDocumentoRef;
	}
	/**
	 * @return the estadoAnulado
	 */
	public String getEstadoAnulado() {
		return estadoAnulado;
	}
	/**
	 * @param estadoAnulado the estadoAnulado to set
	 */
	public void setEstadoAnulado(String estadoAnulado) {
		this.estadoAnulado = estadoAnulado;
	}
	/**
	 * @return the estadoRUV
	 */
	public String getEstadoRUV() {
		return estadoRUV;
	}
	/**
	 * @param estadoRUV the estadoRUV to set
	 */
	public void setEstadoRUV(String estadoRUV) {
		this.estadoRUV = estadoRUV;
	}
	/**
	 * @return the indTransfGratuita
	 */
	public String getIndTransfGratuita() {
		return indTransfGratuita;
	}
	/**
	 * @param indTransfGratuita the indTransfGratuita to set
	 */
	public void setIndTransfGratuita(String indTransfGratuita) {
		this.indTransfGratuita = indTransfGratuita;
	}
	/**
	 * @return the descuento
	 */
	public String getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	/**
	 * @return the baseImponibleNeto
	 */
	public String getBaseImponibleNeto() {
		return baseImponibleNeto;
	}
	/**
	 * @param baseImponibleNeto the baseImponibleNeto to set
	 */
	public void setBaseImponibleNeto(String baseImponibleNeto) {
		this.baseImponibleNeto = baseImponibleNeto;
	}
	/**
	 * @return the numeroDocumentoLegalFinal
	 */
	public String getNumeroDocumentoLegalFinal() {
		return numeroDocumentoLegalFinal;
	}
	/**
	 * @param numeroDocumentoLegalFinal the numeroDocumentoLegalFinal to set
	 */
	public void setNumeroDocumentoLegalFinal(String numeroDocumentoLegalFinal) {
		this.numeroDocumentoLegalFinal = numeroDocumentoLegalFinal;
	}
	/**
	 * @return the exportacion
	 */
	public String getExportacion() {
		return exportacion;
	}
	/**
	 * @param exportacion the exportacion to set
	 */
	public void setExportacion(String exportacion) {
		this.exportacion = exportacion;
	}
	/**
	 * @return the oidTipoDocumento
	 */
	public String getOidTipoDocumento() {
		return oidTipoDocumento;
	}
	/**
	 * @param oidTipoDocumento the oidTipoDocumento to set
	 */
	public void setOidTipoDocumento(String oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}
	/**
	 * @return the fechaEmisionReferencia
	 */
	public Date getFechaEmisionReferencia() {
		return fechaEmisionReferencia;
	}
	/**
	 * @param fechaEmisionReferencia the fechaEmisionReferencia to set
	 */
	public void setFechaEmisionReferencia(Date fechaEmisionReferencia) {
		this.fechaEmisionReferencia = fechaEmisionReferencia;
	}
	/**
	 * @return the numDocControlLegal
	 */
	public String getNumDocControlLegal() {
		return numDocControlLegal;
	}
	/**
	 * @param numDocControlLegal the numDocControlLegal to set
	 */
	public void setNumDocControlLegal(String numDocControlLegal) {
		this.numDocControlLegal = numDocControlLegal;
	}
	/**
	 * @return the numDocControlFinalLegal
	 */
	public String getNumDocControlFinalLegal() {
		return numDocControlFinalLegal;
	}
	/**
	 * @param numDocControlFinalLegal the numDocControlFinalLegal to set
	 */
	public void setNumDocControlFinalLegal(String numDocControlFinalLegal) {
		this.numDocControlFinalLegal = numDocControlFinalLegal;
	}
    

}
