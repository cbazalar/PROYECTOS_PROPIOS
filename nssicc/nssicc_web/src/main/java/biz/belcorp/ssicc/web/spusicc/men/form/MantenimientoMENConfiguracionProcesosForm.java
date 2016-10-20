package biz.belcorp.ssicc.web.spusicc.men.form;


import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoMENConfiguracionProcesosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public class MantenimientoMENConfiguracionProcesosForm extends BaseEditForm{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPlantilla;
	private String codigoProceso;
	private String nombreProgramaEjecutar;
	private String observaciones;
	private String indicadorActivo;
	private String tipoProceso;
	private String ordenEjecucion;
	//
	private String[] codigoDato;
	

	
	private String [] valorDefecto;	
	private String [] label;

	
	private String [] campanha;
	private String [] fecha;
	
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

	private String ordenEjecucionEdicion;
	
	private boolean bIndicadorActivo;
	
	private String [] idComponents;
	
	/**
	 * @return the ordenEjecucionEdicion
	 */
	public String getOrdenEjecucionEdicion() {
		return ordenEjecucionEdicion;
	}

	/**
	 * @param ordenEjecucionEdicion the ordenEjecucionEdicion to set
	 */
	public void setOrdenEjecucionEdicion(String ordenEjecucionEdicion) {
		this.ordenEjecucionEdicion = ordenEjecucionEdicion;
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

	/**
	 * @return the nombreProgramaEjecutar
	 */
	public String getNombreProgramaEjecutar() {
		return nombreProgramaEjecutar;
	}

	/**
	 * @param nombreProgramaEjecutar the nombreProgramaEjecutar to set
	 */
	public void setNombreProgramaEjecutar(String nombreProgramaEjecutar) {
		this.nombreProgramaEjecutar = nombreProgramaEjecutar;
	}

	/**
	 * @return the codigoPlantilla
	 */
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}

	/**
	 * @param codigoPlantilla the codigoPlantilla to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 * @struts.validator type="required"
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
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
	 * 
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso the tipoProceso to set
	 * @struts.validator type="required"
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/**
	 * @return the ordenEjecucion
	 */
	public String getOrdenEjecucion() {
		return ordenEjecucion;
	}

	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 *  @struts.validator type="required"
	 */
	public void setOrdenEjecucion(String ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoPlantilla=this.codigoProceso=null;
		this.observaciones = this.tipoProceso ="";
		this.indicadorActivo =Constants.NUMERO_CERO;
		this.ordenEjecucion="";
		

		codigoDato=null;
		this.label=null;
		this.valorDefecto = null;
		this.campanha = null;
		this.fecha = null;
		this.textoLabel = null;
		seleccionHoraInicio= seleccionMinutoInicio=	seleccionTiempoInicio=null;
		//
		this.textPopup=this.textDesPopup=null;//para los checkList o popup
		this.valorPopup=null; 
		//para la busqueda generica popup
		this.codigo=this.descripcionCorta=this.indexPopup=null;
	}*/
	
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the bIndicadorActivo
	 */
	public boolean isbIndicadorActivo() {
		return bIndicadorActivo;
	}

	/**
	 * @param bIndicadorActivo the bIndicadorActivo to set
	 */
	public void setbIndicadorActivo(boolean bIndicadorActivo) {
		this.bIndicadorActivo = bIndicadorActivo;
	}

	/**
	 * @return the idComponents
	 */
	public String[] getIdComponents() {
		return idComponents;
	}

	/**
	 * @param idComponents the idComponents to set
	 */
	public void setIdComponents(String[] idComponents) {
		this.idComponents = idComponents;
	}
	
	

	

}
