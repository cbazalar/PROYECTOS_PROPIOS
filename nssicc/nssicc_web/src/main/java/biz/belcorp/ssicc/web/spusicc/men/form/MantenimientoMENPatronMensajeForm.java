package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMENPatronMensajeForm extends BaseEditForm implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -646539948875459646L;
	// seccion datos patron
	private String codigoPais;
	private String campanhaProceso;
	private String codigoDocumento;
	private String descripcionPatron;
	//
	// seccion datos mensaje
	private String tipoMensaje;
	private String codigoModulo;
	private String codigoMensaje;
	private String indicadorManual;
	private String codigoSeccion;
	private String correlativo;
	private String indicadorActivo;
	private String descripcionMensaje;
	private String textoMensaje;
	private String textoHtml;
	private String tabSeleccion;

	private String codigoConsideracion;
	private String codigoRestriccion;

	private String[] selectedItemsConsideracion;
	private String[] selectedItemsRestriccion;

	private boolean flagSeccionPatron;
	private boolean flagSeccionMensaje;
	private boolean flagSeccionSimple;

	private String oidPatron;
	private String oidPeriodoCorpo;

	private String condicion1;
	private String condicion2;
	private String condicion3;
	private String condicion4;
	private String condicionRest1;
	private String condicionRest2;

	private String condicionRest1S;
	private String condicion1S;

	private String indicadorConsideracion;
	private String indicadorRestriccion;
	//
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String[] selectedItemsPopup;
	//
	private String regionList;
	private String zonaList;
	private String seccionList;
	private String territorioList;

	private String descripcionRegionList;
	private String descripcionZonaList;
	private String descripcionTerritorioList;
	private String descripcionSeccionList;

	//
	private String descripcionEstadoList;
	private String estadoList;
	private boolean flagEditCabecera;
	private boolean flagConsulta;
	private String idEdicion;
	private String campanhaActual;
	//
	private String nombreArchivo;
	private String directorioTemporal;
	private UploadedFile archivo;
	//
	private String oidMensaje;
	
	private String extensionArchivo;	//extension del archivo
	private String rutaFichero;
	
	
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
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}
	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}
	/**
	 * @return the codigoDocumento
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	/**
	 * @param codigoDocumento the codigoDocumento to set
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	/**
	 * @return the descripcionPatron
	 */
	public String getDescripcionPatron() {
		return descripcionPatron;
	}
	/**
	 * @param descripcionPatron the descripcionPatron to set
	 */
	public void setDescripcionPatron(String descripcionPatron) {
		this.descripcionPatron = descripcionPatron;
	}
	/**
	 * @return the tipoMensaje
	 */
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	/**
	 * @param tipoMensaje the tipoMensaje to set
	 */
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}
	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	/**
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}
	/**
	 * @param codigoMensaje the codigoMensaje to set
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
	/**
	 * @return the indicadorManual
	 */
	public String getIndicadorManual() {
		return indicadorManual;
	}
	/**
	 * @param indicadorManual the indicadorManual to set
	 */
	public void setIndicadorManual(String indicadorManual) {
		this.indicadorManual = indicadorManual;
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
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
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
	 * @return the descripcionMensaje
	 */
	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}
	/**
	 * @param descripcionMensaje the descripcionMensaje to set
	 */
	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}
	/**
	 * @return the textoMensaje
	 */
	public String getTextoMensaje() {
		return textoMensaje;
	}
	/**
	 * @param textoMensaje the textoMensaje to set
	 */
	public void setTextoMensaje(String textoMensaje) {
		this.textoMensaje = textoMensaje;
	}
	/**
	 * @return the textoHtml
	 */
	public String getTextoHtml() {
		return textoHtml;
	}
	/**
	 * @param textoHtml the textoHtml to set
	 */
	public void setTextoHtml(String textoHtml) {
		this.textoHtml = textoHtml;
	}
	/**
	 * @return the tabSeleccion
	 */
	public String getTabSeleccion() {
		return tabSeleccion;
	}
	/**
	 * @param tabSeleccion the tabSeleccion to set
	 */
	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}
	/**
	 * @return the codigoConsideracion
	 */
	public String getCodigoConsideracion() {
		return codigoConsideracion;
	}
	/**
	 * @param codigoConsideracion the codigoConsideracion to set
	 */
	public void setCodigoConsideracion(String codigoConsideracion) {
		this.codigoConsideracion = codigoConsideracion;
	}
	/**
	 * @return the codigoRestriccion
	 */
	public String getCodigoRestriccion() {
		return codigoRestriccion;
	}
	/**
	 * @param codigoRestriccion the codigoRestriccion to set
	 */
	public void setCodigoRestriccion(String codigoRestriccion) {
		this.codigoRestriccion = codigoRestriccion;
	}
	/**
	 * @return the selectedItemsConsideracion
	 */
	public String[] getSelectedItemsConsideracion() {
		return selectedItemsConsideracion;
	}
	/**
	 * @param selectedItemsConsideracion the selectedItemsConsideracion to set
	 */
	public void setSelectedItemsConsideracion(String[] selectedItemsConsideracion) {
		this.selectedItemsConsideracion = selectedItemsConsideracion;
	}
	/**
	 * @return the selectedItemsRestriccion
	 */
	public String[] getSelectedItemsRestriccion() {
		return selectedItemsRestriccion;
	}
	/**
	 * @param selectedItemsRestriccion the selectedItemsRestriccion to set
	 */
	public void setSelectedItemsRestriccion(String[] selectedItemsRestriccion) {
		this.selectedItemsRestriccion = selectedItemsRestriccion;
	}
	/**
	 * @return the flagSeccionPatron
	 */
	public boolean isFlagSeccionPatron() {
		return flagSeccionPatron;
	}
	/**
	 * @param flagSeccionPatron the flagSeccionPatron to set
	 */
	public void setFlagSeccionPatron(boolean flagSeccionPatron) {
		this.flagSeccionPatron = flagSeccionPatron;
	}
	/**
	 * @return the flagSeccionMensaje
	 */
	public boolean isFlagSeccionMensaje() {
		return flagSeccionMensaje;
	}
	/**
	 * @param flagSeccionMensaje the flagSeccionMensaje to set
	 */
	public void setFlagSeccionMensaje(boolean flagSeccionMensaje) {
		this.flagSeccionMensaje = flagSeccionMensaje;
	}
	/**
	 * @return the flagSeccionSimple
	 */
	public boolean isFlagSeccionSimple() {
		return flagSeccionSimple;
	}
	/**
	 * @param flagSeccionSimple the flagSeccionSimple to set
	 */
	public void setFlagSeccionSimple(boolean flagSeccionSimple) {
		this.flagSeccionSimple = flagSeccionSimple;
	}
	/**
	 * @return the oidPatron
	 */
	public String getOidPatron() {
		return oidPatron;
	}
	/**
	 * @param oidPatron the oidPatron to set
	 */
	public void setOidPatron(String oidPatron) {
		this.oidPatron = oidPatron;
	}
	/**
	 * @return the oidPeriodoCorpo
	 */
	public String getOidPeriodoCorpo() {
		return oidPeriodoCorpo;
	}
	/**
	 * @param oidPeriodoCorpo the oidPeriodoCorpo to set
	 */
	public void setOidPeriodoCorpo(String oidPeriodoCorpo) {
		this.oidPeriodoCorpo = oidPeriodoCorpo;
	}
	/**
	 * @return the condicion1
	 */
	public String getCondicion1() {
		return condicion1;
	}
	/**
	 * @param condicion1 the condicion1 to set
	 */
	public void setCondicion1(String condicion1) {
		this.condicion1 = condicion1;
	}
	/**
	 * @return the condicion2
	 */
	public String getCondicion2() {
		return condicion2;
	}
	/**
	 * @param condicion2 the condicion2 to set
	 */
	public void setCondicion2(String condicion2) {
		this.condicion2 = condicion2;
	}
	/**
	 * @return the condicion3
	 */
	public String getCondicion3() {
		return condicion3;
	}
	/**
	 * @param condicion3 the condicion3 to set
	 */
	public void setCondicion3(String condicion3) {
		this.condicion3 = condicion3;
	}
	/**
	 * @return the condicion4
	 */
	public String getCondicion4() {
		return condicion4;
	}
	/**
	 * @param condicion4 the condicion4 to set
	 */
	public void setCondicion4(String condicion4) {
		this.condicion4 = condicion4;
	}
	/**
	 * @return the condicionRest1
	 */
	public String getCondicionRest1() {
		return condicionRest1;
	}
	/**
	 * @param condicionRest1 the condicionRest1 to set
	 */
	public void setCondicionRest1(String condicionRest1) {
		this.condicionRest1 = condicionRest1;
	}
	/**
	 * @return the condicionRest2
	 */
	public String getCondicionRest2() {
		return condicionRest2;
	}
	/**
	 * @param condicionRest2 the condicionRest2 to set
	 */
	public void setCondicionRest2(String condicionRest2) {
		this.condicionRest2 = condicionRest2;
	}
	/**
	 * @return the condicionRest1S
	 */
	public String getCondicionRest1S() {
		return condicionRest1S;
	}
	/**
	 * @param condicionRest1S the condicionRest1S to set
	 */
	public void setCondicionRest1S(String condicionRest1S) {
		this.condicionRest1S = condicionRest1S;
	}
	/**
	 * @return the condicion1S
	 */
	public String getCondicion1S() {
		return condicion1S;
	}
	/**
	 * @param condicion1s the condicion1S to set
	 */
	public void setCondicion1S(String condicion1s) {
		condicion1S = condicion1s;
	}
	/**
	 * @return the indicadorConsideracion
	 */
	public String getIndicadorConsideracion() {
		return indicadorConsideracion;
	}
	/**
	 * @param indicadorConsideracion the indicadorConsideracion to set
	 */
	public void setIndicadorConsideracion(String indicadorConsideracion) {
		this.indicadorConsideracion = indicadorConsideracion;
	}
	/**
	 * @return the indicadorRestriccion
	 */
	public String getIndicadorRestriccion() {
		return indicadorRestriccion;
	}
	/**
	 * @param indicadorRestriccion the indicadorRestriccion to set
	 */
	public void setIndicadorRestriccion(String indicadorRestriccion) {
		this.indicadorRestriccion = indicadorRestriccion;
	}
	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}
	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}
	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}
	/**
	 * @return the selectedItemsPopup
	 */
	public String[] getSelectedItemsPopup() {
		return selectedItemsPopup;
	}
	/**
	 * @param selectedItemsPopup the selectedItemsPopup to set
	 */
	public void setSelectedItemsPopup(String[] selectedItemsPopup) {
		this.selectedItemsPopup = selectedItemsPopup;
	}
	/**
	 * @return the regionList
	 */
	public String getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}
	/**
	 * @return the zonaList
	 */
	public String getZonaList() {
		return zonaList;
	}
	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String zonaList) {
		this.zonaList = zonaList;
	}
	/**
	 * @return the seccionList
	 */
	public String getSeccionList() {
		return seccionList;
	}
	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String seccionList) {
		this.seccionList = seccionList;
	}
	/**
	 * @return the territorioList
	 */
	public String getTerritorioList() {
		return territorioList;
	}
	/**
	 * @param territorioList the territorioList to set
	 */
	public void setTerritorioList(String territorioList) {
		this.territorioList = territorioList;
	}
	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}
	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}
	/**
	 * @return the descripcionTerritorioList
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}
	/**
	 * @param descripcionTerritorioList the descripcionTerritorioList to set
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		this.descripcionTerritorioList = descripcionTerritorioList;
	}
	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}
	/**
	 * @param descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}
	/**
	 * @return the descripcionEstadoList
	 */
	public String getDescripcionEstadoList() {
		return descripcionEstadoList;
	}
	/**
	 * @param descripcionEstadoList the descripcionEstadoList to set
	 */
	public void setDescripcionEstadoList(String descripcionEstadoList) {
		this.descripcionEstadoList = descripcionEstadoList;
	}
	/**
	 * @return the estadoList
	 */
	public String getEstadoList() {
		return estadoList;
	}
	/**
	 * @param estadoList the estadoList to set
	 */
	public void setEstadoList(String estadoList) {
		this.estadoList = estadoList;
	}
	/**
	 * @return the flagEditCabecera
	 */
	public boolean isFlagEditCabecera() {
		return flagEditCabecera;
	}
	/**
	 * @param flagEditCabecera the flagEditCabecera to set
	 */
	public void setFlagEditCabecera(boolean flagEditCabecera) {
		this.flagEditCabecera = flagEditCabecera;
	}
	/**
	 * @return the flagConsulta
	 */
	public boolean isFlagConsulta() {
		return flagConsulta;
	}
	/**
	 * @param flagConsulta the flagConsulta to set
	 */
	public void setFlagConsulta(boolean flagConsulta) {
		this.flagConsulta = flagConsulta;
	}
	/**
	 * @return the idEdicion
	 */
	public String getIdEdicion() {
		return idEdicion;
	}
	/**
	 * @param idEdicion the idEdicion to set
	 */
	public void setIdEdicion(String idEdicion) {
		this.idEdicion = idEdicion;
	}
	/**
	 * @return the campanhaActual
	 */
	public String getCampanhaActual() {
		return campanhaActual;
	}
	/**
	 * @param campanhaActual the campanhaActual to set
	 */
	public void setCampanhaActual(String campanhaActual) {
		this.campanhaActual = campanhaActual;
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
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	/**
	 * @return the oidMensaje
	 */
	public String getOidMensaje() {
		return oidMensaje;
	}
	/**
	 * @param oidMensaje the oidMensaje to set
	 */
	public void setOidMensaje(String oidMensaje) {
		this.oidMensaje = oidMensaje;
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
	 * @return the rutaFichero
	 */
	public String getRutaFichero() {
		return rutaFichero;
	}
	/**
	 * @param rutaFichero the rutaFichero to set
	 */
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
	
}

