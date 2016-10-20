package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MaestroCliente extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4349600172538718010L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCliente;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZona;
	private String descripcionZona;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String apellidoCasada;
	private String primerNombre;
	private String segundoNombre;
	private String descripcionCliente;
	private String numeroDocumento;
	private Date   fechaNacimiento;
	private String numeroTelefono;
	private String campanhaIngreso;
	private String campanhaPrimerPedido;
	private String campanhaUltimoPedido;
	private String campanhaEvaluacion;
	private String indicadorMorosidad;
	private Double saldoCliente;
	private String situacionCliente;
	private String estadoRegistro;
	//sb
	private String apellidoNombreCompleto; 
	

	/**
	 * @return Returns the apellidoNombreCompleto.
	 */
	public String getApellidoNombreCompleto() {
		return apellidoNombreCompleto;
	}

	/**
	 * @param apellidoNombreCompleto The apellidoNombreCompleto to set.
	 */
	public void setApellidoNombreCompleto(String apellidoNombreCompleto) {
		this.apellidoNombreCompleto = apellidoNombreCompleto;
	}

	/**
	 * @return Returns the apellidoCasada.
	 */
	public String getApellidoCasada() {
		return apellidoCasada;
	}

	/**
	 * @param apellidoCasada The apellidoCasada to set.
	 */
	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return Returns the campanhaEvaluacion.
	 */
	public String getCampanhaEvaluacion() {
		return campanhaEvaluacion;
	}

	/**
	 * @param campanhaEvaluacion The campanhaEvaluacion to set.
	 */
	public void setCampanhaEvaluacion(String campanhaEvaluacion) {
		this.campanhaEvaluacion = campanhaEvaluacion;
	}

	/**
	 * @return Returns the campanhaIngreso.
	 */
	public String getCampanhaIngreso() {
		return campanhaIngreso;
	}

	/**
	 * @param campanhaIngreso The campanhaIngreso to set.
	 */
	public void setCampanhaIngreso(String campanhaIngreso) {
		this.campanhaIngreso = campanhaIngreso;
	}

	/**
	 * @return Returns the campanhaPrimerPedido.
	 */
	public String getCampanhaPrimerPedido() {
		return campanhaPrimerPedido;
	}

	/**
	 * @param campanhaPrimerPedido The campanhaPrimerPedido to set.
	 */
	public void setCampanhaPrimerPedido(String campanhaPrimerPedido) {
		this.campanhaPrimerPedido = campanhaPrimerPedido;
	}

	/**
	 * @return Returns the campanhaUltimoPedido.
	 */
	public String getCampanhaUltimoPedido() {
		return campanhaUltimoPedido;
	}

	/**
	 * @param campanhaUltimoPedido The campanhaUltimoPedido to set.
	 */
	public void setCampanhaUltimoPedido(String campanhaUltimoPedido) {
		this.campanhaUltimoPedido = campanhaUltimoPedido;
	}

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return Returns the fechaNacimiento.
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento The fechaNacimiento to set.
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the indicadorMorosidad.
	 */
	public String getIndicadorMorosidad() {
		return indicadorMorosidad;
	}

	/**
	 * @param indicadorMorosidad The indicadorMorosidad to set.
	 */
	public void setIndicadorMorosidad(String indicadorMorosidad) {
		this.indicadorMorosidad = indicadorMorosidad;
	}

	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return Returns the numeroTelefono.
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * @param numeroTelefono The numeroTelefono to set.
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * @return Returns the primerNombre.
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre The primerNombre to set.
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return Returns the saldoCliente.
	 */
	public Double getSaldoCliente() {
		return saldoCliente;
	}

	/**
	 * @param saldoCliente The saldoCliente to set.
	 */
	public void setSaldoCliente(Double saldoCliente) {
		this.saldoCliente = saldoCliente;
	}

	/**
	 * @return Returns the segundoNombre.
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre The segundoNombre to set.
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return Returns the situacionCliente.
	 */
	public String getSituacionCliente() {
		return situacionCliente;
	}

	/**
	 * @param situacionCliente The situacionCliente to set.
	 */
	public void setSituacionCliente(String situacionCliente) {
		this.situacionCliente = situacionCliente;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescripcionCliente() {
		return descripcionCliente;
	}

	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}

}
