package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 *
 */
public class CronogramaDictado extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 559394206161659952L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private Long   oidCronograma;
	private String campannaCronograma;
	private String codigoRegion;
	private String descripcionRegion;	
	private String codigoCurso;
	private String nombreCurso;
	private Date   fechaDictado;
	private String horaInicial;
	private String horaFin;
	private String codigoLocal;
	private String descripcionLocal;
	private String direccion;
	private String codigoSala;
	private String descripcionSala;
	private String estadoActividad;
	private String codigoZona;
	private String descripcionZona;
	private String[] listaZonas;
	private String dia;//0:DOMINGO,1:LUNES,2:MARTES,3:MIERCOLES ...7:SABADO
	
	private String codigoCliente;
	
	private String instructora;
	
	/**
	 * @return Returns the dia.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia The dia to set.
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the campannaCronograma.
	 */
	public String getCampannaCronograma() {
		return campannaCronograma;
	}

	/**
	 * @param campannaCronograma The campannaCronograma to set.
	 */
	public void setCampannaCronograma(String campannaCronograma) {
		this.campannaCronograma = campannaCronograma;
	}

	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
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
	 * @return Returns the codigoLocal.
	 */
	public String getCodigoLocal() {
		return codigoLocal;
	}

	/**
	 * @param codigoLocal The codigoLocal to set.
	 */
	public void setCodigoLocal(String codigoLocal) {
		this.codigoLocal = codigoLocal;
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
	 * @return Returns the descripcionLocal.
	 */
	public String getDescripcionLocal() {
		return descripcionLocal;
	}

	/**
	 * @param descripcionLocal The descripcionLocal to set.
	 */
	public void setDescripcionLocal(String descripcionLocal) {
		this.descripcionLocal = descripcionLocal;
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
	 * @return Returns the direccion.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion The direccion to set.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return Returns the fechaDictado.
	 */
	public Date getFechaDictado() {
		return fechaDictado;
	}

	/**
	 * @param fechaDictado The fechaDictado to set.
	 */
	public void setFechaDictado(Date fechaDictado) {
		this.fechaDictado = fechaDictado;
	}

	/**
	 * @return Returns the horaFin.
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin The horaFin to set.
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return Returns the horaInicial.
	 */
	public String getHoraInicial() {
		return horaInicial;
	}

	/**
	 * @param horaInicial The horaInicial to set.
	 */
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	/**
	 * @return Returns the nombreCurso.
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * @param nombreCurso The nombreCurso to set.
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
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
	 * @return Returns the oidCronograma.
	 */
	public Long getOidCronograma() {
		return oidCronograma;
	}

	/**
	 * @param oidCronograma The oidCronograma to set.
	 */
	public void setOidCronograma(Long oidCronograma) {
		this.oidCronograma = oidCronograma;
	}

	/**
	 * @return Returns the listaZonas.
	 */
	public String[] getListaZonas() {
		return listaZonas;
	}

	/**
	 * @param listaZonas The listaZonas to set.
	 */
	public void setListaZonas(String[] listaZonas) {
		this.listaZonas = listaZonas;
	}

	/**
	 * @return Returns the estadoActividad.
	 */
	public String getEstadoActividad() {
		return estadoActividad;
	}

	/**
	 * @param estadoActividad The estadoActividad to set.
	 */
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	/**
	 * @return Returns the codigoSala.
	 */
	public String getCodigoSala() {
		return codigoSala;
	}

	/**
	 * @param codigoSala The codigoSala to set.
	 */
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}

	/**
	 * @return Returns the descripcionSala.
	 */
	public String getDescripcionSala() {
		return descripcionSala;
	}

	/**
	 * @param descripcionSala The descripcionSala to set.
	 */
	public void setDescripcionSala(String descripcionSala) {
		this.descripcionSala = descripcionSala;
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
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getInstructora() {
		return instructora;
	}

	public void setInstructora(String instructora) {
		this.instructora = instructora;
	}
	
	

		
}
