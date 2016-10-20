package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMENPlantillaConfiguracionProcesosForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6901598255429754286L;
	private String codigoPais;
	private String codigoPlantilla;
	private String descripcion;
	private String observaciones;
	private String indicadorActivo;
	private String indicadorGestionUsuario;
	private String[] codigoDato;
	
	private String [] indicadorObligatorio;
	private String [] indicadorModificable;
	private String [] indicadorVisible;
	
	private String [] valorDefecto;	
	private String [] label;
	private String [] ordenPresentacion;
	
	private String [] campanha;
	private String [] fecha;
	private Date [] fechaD;
	
	/**
	 * @return the fechaD
	 */
	public Date[] getFechaD() {
		return fechaD;
	}
	/**
	 * @param fechaD the fechaD to set
	 */
	public void setFechaD(Date[] fechaD) {
		this.fechaD = fechaD;
	}
	private String [] textoLabel;//label
	
	private String[] seleccionHoraInicio;
	private String[] seleccionMinutoInicio;
	private String[] seleccionTiempoInicio;
	
	private String[] textPopup;//para los checkList o popup
	private String[] textDesPopup; 
	private String [][] valorPopup; 
	//para la busqueda generica popup
	private String codigo;
	private String descripcionCorta;
	private String indexPopup;
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
	 * @return the codigoPlantilla
	 */
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}
	/**
	 * @param codigoPlantilla the codigoPlantilla to set
	 */
	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	 * @return the indicadorGestionUsuario
	 */
	public String getIndicadorGestionUsuario() {
		return indicadorGestionUsuario;
	}
	/**
	 * @param indicadorGestionUsuario the indicadorGestionUsuario to set
	 */
	public void setIndicadorGestionUsuario(String indicadorGestionUsuario) {
		this.indicadorGestionUsuario = indicadorGestionUsuario;
	}
	/**
	 * @return the codigoDato
	 */
	public String[] getCodigoDato() {
		return codigoDato;
	}
	/**
	 * @param codigoDato the codigoDato to set
	 */
	public void setCodigoDato(String[] codigoDato) {
		this.codigoDato = codigoDato;
	}
	/**
	 * @return the indicadorObligatorio
	 */
	public String[] getIndicadorObligatorio() {
		return indicadorObligatorio;
	}
	/**
	 * @param indicadorObligatorio the indicadorObligatorio to set
	 */
	public void setIndicadorObligatorio(String[] indicadorObligatorio) {
		this.indicadorObligatorio = indicadorObligatorio;
	}
	/**
	 * @return the indicadorModificable
	 */
	public String[] getIndicadorModificable() {
		return indicadorModificable;
	}
	/**
	 * @param indicadorModificable the indicadorModificable to set
	 */
	public void setIndicadorModificable(String[] indicadorModificable) {
		this.indicadorModificable = indicadorModificable;
	}
	/**
	 * @return the indicadorVisible
	 */
	public String[] getIndicadorVisible() {
		return indicadorVisible;
	}
	/**
	 * @param indicadorVisible the indicadorVisible to set
	 */
	public void setIndicadorVisible(String[] indicadorVisible) {
		this.indicadorVisible = indicadorVisible;
	}
	/**
	 * @return the valorDefecto
	 */
	public String[] getValorDefecto() {
		return valorDefecto;
	}
	/**
	 * @param valorDefecto the valorDefecto to set
	 */
	public void setValorDefecto(String[] valorDefecto) {
		this.valorDefecto = valorDefecto;
	}
	/**
	 * @return the label
	 */
	public String[] getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String[] label) {
		this.label = label;
	}
	/**
	 * @return the ordenPresentacion
	 */
	public String[] getOrdenPresentacion() {
		return ordenPresentacion;
	}
	/**
	 * @param ordenPresentacion the ordenPresentacion to set
	 */
	public void setOrdenPresentacion(String[] ordenPresentacion) {
		this.ordenPresentacion = ordenPresentacion;
	}
	/**
	 * @return the campanha
	 */
	public String[] getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(String[] campanha) {
		this.campanha = campanha;
	}
	/**
	 * @return the fecha
	 */
	public String[] getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String[] fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the textoLabel
	 */
	public String[] getTextoLabel() {
		return textoLabel;
	}
	/**
	 * @param textoLabel the textoLabel to set
	 */
	public void setTextoLabel(String[] textoLabel) {
		this.textoLabel = textoLabel;
	}
	/**
	 * @return the seleccionHoraInicio
	 */
	public String[] getSeleccionHoraInicio() {
		return seleccionHoraInicio;
	}
	/**
	 * @param seleccionHoraInicio the seleccionHoraInicio to set
	 */
	public void setSeleccionHoraInicio(String[] seleccionHoraInicio) {
		this.seleccionHoraInicio = seleccionHoraInicio;
	}
	/**
	 * @return the seleccionMinutoInicio
	 */
	public String[] getSeleccionMinutoInicio() {
		return seleccionMinutoInicio;
	}
	/**
	 * @param seleccionMinutoInicio the seleccionMinutoInicio to set
	 */
	public void setSeleccionMinutoInicio(String[] seleccionMinutoInicio) {
		this.seleccionMinutoInicio = seleccionMinutoInicio;
	}
	/**
	 * @return the seleccionTiempoInicio
	 */
	public String[] getSeleccionTiempoInicio() {
		return seleccionTiempoInicio;
	}
	/**
	 * @param seleccionTiempoInicio the seleccionTiempoInicio to set
	 */
	public void setSeleccionTiempoInicio(String[] seleccionTiempoInicio) {
		this.seleccionTiempoInicio = seleccionTiempoInicio;
	}
	/**
	 * @return the textPopup
	 */
	public String[] getTextPopup() {
		return textPopup;
	}
	/**
	 * @param textPopup the textPopup to set
	 */
	public void setTextPopup(String[] textPopup) {
		this.textPopup = textPopup;
	}
	/**
	 * @return the textDesPopup
	 */
	public String[] getTextDesPopup() {
		return textDesPopup;
	}
	/**
	 * @param textDesPopup the textDesPopup to set
	 */
	public void setTextDesPopup(String[] textDesPopup) {
		this.textDesPopup = textDesPopup;
	}
	/**
	 * @return the valorPopup
	 */
	public String[][] getValorPopup() {
		return valorPopup;
	}
	/**
	 * @param valorPopup the valorPopup to set
	 */
	public void setValorPopup(String[][] valorPopup) {
		this.valorPopup = valorPopup;
	}
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
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	/**
	 * @param descripcionCorta the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	/**
	 * @return the indexPopup
	 */
	public String getIndexPopup() {
		return indexPopup;
	}
	/**
	 * @param indexPopup the indexPopup to set
	 */
	public void setIndexPopup(String indexPopup) {
		this.indexPopup = indexPopup;
	}
	
	

}
