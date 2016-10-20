package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 *                      
 */
public class ConsultaHIPSociasEmpresariasForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String traccion;
	private String nivel;
	private String campanyaInicio;
	private String fechaInicio;
	private String campanyaCese;
	private String fechaCese;
	private String indicadorActivo;

	private String nombre;
	private String numeroDocumento;
	
	private String nombreRegion;
	private String nombreZona;
	private String nombreSeccion;
	
	private String indicadorLider;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the traccion
	 */
	public String getTraccion() {
		return traccion;
	}
	/**
	 * @param traccion the traccion to set
	 */
	public void setTraccion(String traccion) {
		this.traccion = traccion;
	}
	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the campanyaInicio
	 */
	public String getCampanyaInicio() {
		return campanyaInicio;
	}
	/**
	 * @param campanyaInicio the campanyaInicio to set
	 */
	public void setCampanyaInicio(String campanyaInicio) {
		this.campanyaInicio = campanyaInicio;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the campanyaCese
	 */
	public String getCampanyaCese() {
		return campanyaCese;
	}
	/**
	 * @param campanyaCese the campanyaCese to set
	 */
	public void setCampanyaCese(String campanyaCese) {
		this.campanyaCese = campanyaCese;
	}
	/**
	 * @return the fechaCese
	 */
	public String getFechaCese() {
		return fechaCese;
	}
	/**
	 * @param fechaCese the fechaCese to set
	 */
	public void setFechaCese(String fechaCese) {
		this.fechaCese = fechaCese;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the nombreRegion
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}
	/**
	 * @param nombreRegion the nombreRegion to set
	 */
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	/**
	 * @return the nombreZona
	 */
	public String getNombreZona() {
		return nombreZona;
	}
	/**
	 * @param nombreZona the nombreZona to set
	 */
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	/**
	 * @return the nombreSeccion
	 */
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	/**
	 * @param nombreSeccion the nombreSeccion to set
	 */
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	/**
	 * @return the indicadorLider
	 */
	public String getIndicadorLider() {
		return indicadorLider;
	}
	/**
	 * @param indicadorLider the indicadorLider to set
	 */
	public void setIndicadorLider(String indicadorLider) {
		this.indicadorLider = indicadorLider;
	}
}
