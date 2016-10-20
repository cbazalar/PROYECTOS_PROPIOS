package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.util.List;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@SuppressWarnings("rawtypes")
public class ProcesoZONActualizarUnidadesAdministrativasForm extends BaseProcesoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5567116147642301172L;

	private String codigoMarca;
	private String codigoCanal;
	private String periodoCorporativo;
	private String[] codigosRegion;
	private String[] codigosZona;
	
	private UploadedFile archivo;
	private String nombreArchivo;
	private String nombreCompletoArchivo;
	
	private ArchivoSubidoForm[] archivosSubidos;
	private String extensionArchivos;
	
	private String directorioTemporal;
	
	private String columnaSGV;
	private String columnaRegion;
	private String columnaZona;
	private String columnaSeccion;
	private String columnaTerritorio;
	private String columnaNSE;
	private String columnaUbigeo;
	
	private String operacion;
	
	private boolean flagHabilitarCombos;
	private boolean flagHabilitaCargaDatos;
	private boolean flagHabilitaValidarDatos;
	private boolean flagHabilitaAprobarCreaciones;
	private boolean flagHabilitaAprobarTrasvases;
	private boolean flagHabilitaAprobarModificaciones;
	private boolean flagHabilitaAprobarEliminaciones;
	private boolean flagHabilitaAprobarReactivaciones;
	
	private boolean flagMostrarPDFErrorCargaDatos;
	private boolean flagHabilitaGeneraReporte;
	private boolean flagHayErroresEliminacion;
	private String flagReporteVisto;
	private String flagReporteVistoErroresEliminacion;
	
	private List unidadesAdministrativasACrear;
	private List consultorasATrasvasar;
	
	private String cantidadRegistrosBaja;
	private String cantidadRegistrosAlta;
	private String cantidadRegistrosTrasvase;
	private String cantidadRegistrosModificacion;
	private String cantidadRegistrosReactivacion;
	
	private String indicadorNivelSocioEconomico;
	private String indRegionZona;
	
	
	/**
	 * @return
	 */
	public String getCantidadRegistrosReactivacion() {
		return cantidadRegistrosReactivacion;
	}

	/**
	 * @param cantidadRegistrosReactivacion
	 */
	public void setCantidadRegistrosReactivacion(String cantidadRegistrosReactivacion) {
		this.cantidadRegistrosReactivacion = cantidadRegistrosReactivacion;
	}

	/**
	 * @return
	 */
	public boolean isFlagHabilitaAprobarReactivaciones() {
		return flagHabilitaAprobarReactivaciones;
	}
	
	/**
	 * @param flagHabilitaAprobarReactivaciones
	 */
	public void setFlagHabilitaAprobarReactivaciones(boolean flagHabilitaAprobarReactivaciones) {
		this.flagHabilitaAprobarReactivaciones = flagHabilitaAprobarReactivaciones;
	}
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getCodigoCanal() {
		return codigoCanal;
	}
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	public String getPeriodoCorporativo() {
		return periodoCorporativo;
	}
	public void setPeriodoCorporativo(String periodoCorporativo) {
		this.periodoCorporativo = periodoCorporativo;
	}
	public String[] getCodigosRegion() {
		return codigosRegion;
	}
	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigosRegion(String[] codigoRegion) {
		this.codigosRegion = codigoRegion;
	}
	public ArchivoSubidoForm[] getArchivosSubidos() {
		return archivosSubidos;
	}
	public void setArchivosSubidos(ArchivoSubidoForm[] archivosSubidos) {
		this.archivosSubidos = archivosSubidos;
	}
	public UploadedFile getArchivo() {
		return archivo;
	}
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	public String getNombreCompletoArchivo() {
		return nombreCompletoArchivo;
	}
	public void setNombreCompletoArchivo(String nombreCompletoArchivo) {
		this.nombreCompletoArchivo = nombreCompletoArchivo;
	}
	public String getColumnaSGV() {
		return columnaSGV;
	}
	public void setColumnaSGV(String columnaSGV) {
		this.columnaSGV = columnaSGV;
	}
	public String getColumnaRegion() {
		return columnaRegion;
	}
	public void setColumnaRegion(String columnaRegion) {
		this.columnaRegion = columnaRegion;
	}
	public String getColumnaZona() {
		return columnaZona;
	}
	public void setColumnaZona(String columnaZona) {
		this.columnaZona = columnaZona;
	}
	public String getColumnaSeccion() {
		return columnaSeccion;
	}
	public void setColumnaSeccion(String columnaSeccion) {
		this.columnaSeccion = columnaSeccion;
	}
	public String getColumnaTerritorio() {
		return columnaTerritorio;
	}
	public void setColumnaTerritorio(String columnaTerritorio) {
		this.columnaTerritorio = columnaTerritorio;
	}
	public String getColumnaNSE() {
		return columnaNSE;
	}
	public void setColumnaNSE(String columnaNSE) {
		this.columnaNSE = columnaNSE;
	}
	public String getColumnaUbigeo() {
		return columnaUbigeo;
	}
	public void setColumnaUbigeo(String columnaUbigeo) {
		this.columnaUbigeo = columnaUbigeo;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public boolean isFlagHabilitaCargaDatos() {
		return flagHabilitaCargaDatos;
	}
	public void setFlagHabilitaCargaDatos(boolean flagHabilitaCargaDatos) {
		this.flagHabilitaCargaDatos = flagHabilitaCargaDatos;
	}
	public boolean isFlagHabilitaGeneraReporte() {
		return flagHabilitaGeneraReporte;
	}
	public void setFlagHabilitaGeneraReporte(boolean flagHabilitaGeneraReporte) {
		this.flagHabilitaGeneraReporte = flagHabilitaGeneraReporte;
	}
	public boolean isFlagHabilitaAprobarCreaciones() {
		return flagHabilitaAprobarCreaciones;
	}
	public void setFlagHabilitaAprobarCreaciones(
			boolean flagHabilitaAprobarCreaciones) {
		this.flagHabilitaAprobarCreaciones = flagHabilitaAprobarCreaciones;
	}
	public boolean isFlagHabilitaAprobarTrasvases() {
		return flagHabilitaAprobarTrasvases;
	}
	public void setFlagHabilitaAprobarTrasvases(boolean flagHabilitaAprobarTrasvases) {
		this.flagHabilitaAprobarTrasvases = flagHabilitaAprobarTrasvases;
	}
	public boolean isFlagHabilitaAprobarModificaciones() {
		return flagHabilitaAprobarModificaciones;
	}
	public void setFlagHabilitaAprobarModificaciones(boolean flagHabilitaAprobarModificaciones) {
		this.flagHabilitaAprobarModificaciones = flagHabilitaAprobarModificaciones;
	}
	public boolean isFlagHabilitaAprobarEliminaciones() {
		return flagHabilitaAprobarEliminaciones;
	}
	public void setFlagHabilitaAprobarEliminaciones(boolean flagHabilitaAprobarEliminaciones) {
		this.flagHabilitaAprobarEliminaciones = flagHabilitaAprobarEliminaciones;
	}
	public boolean isFlagHabilitaValidarDatos() {
		return flagHabilitaValidarDatos;
	}
	public void setFlagHabilitaValidarDatos(boolean flagHabilitaValidarDatos) {
		this.flagHabilitaValidarDatos = flagHabilitaValidarDatos;
	}
	public boolean isFlagHabilitarCombos() {
		return flagHabilitarCombos;
	}
	public void setFlagHabilitarCombos(boolean flagHabilitarCombos) {
		this.flagHabilitarCombos = flagHabilitarCombos;
	}
	public String getExtensionArchivos() {
		return extensionArchivos;
	}
	public void setExtensionArchivos(String extensionArchivos) {
		this.extensionArchivos = extensionArchivos;
	}
	public boolean isFlagMostrarPDFErrorCargaDatos() {
		return flagMostrarPDFErrorCargaDatos;
	}
	public void setFlagMostrarPDFErrorCargaDatos(boolean flagMostrarPDFErrorCargaDatos) {
		this.flagMostrarPDFErrorCargaDatos = flagMostrarPDFErrorCargaDatos;
	}
	
	public List getUnidadesAdministrativasACrear() {
		return unidadesAdministrativasACrear;
	}
	public void setUnidadesAdministrativasACrear(List unidadesAdministrativasACrear) {
		this.unidadesAdministrativasACrear = unidadesAdministrativasACrear;
	}
	public String getFlagReporteVisto() {
		return flagReporteVisto;
	}
	public void setFlagReporteVisto(String flagReporteVisto) {
		this.flagReporteVisto = flagReporteVisto;
	}
	public boolean isFlagHayErroresEliminacion() {
		return flagHayErroresEliminacion;
	}
	public void setFlagHayErroresEliminacion(boolean flagHayErroresEliminacion) {
		this.flagHayErroresEliminacion = flagHayErroresEliminacion;
	}
	public String getFlagReporteVistoErroresEliminacion() {
		return flagReporteVistoErroresEliminacion;
	}
	public void setFlagReporteVistoErroresEliminacion(
			String flagReporteVistoErroresEliminacion) {
		this.flagReporteVistoErroresEliminacion = flagReporteVistoErroresEliminacion;
	}
	public List getConsultorasATrasvasar() {
		return consultorasATrasvasar;
	}
	public void setConsultorasATrasvasar(List consultorasATrasvasar) {
		this.consultorasATrasvasar = consultorasATrasvasar;
	}
	
	/**
	 * @return the cantidadRegistrosBaja
	 */
	public String getCantidadRegistrosBaja() {
		return cantidadRegistrosBaja;
	}
	/**
	 * @param cantidadRegistrosBaja the cantidadRegistrosBaja to set
	 */
	public void setCantidadRegistrosBaja(String cantidadRegistrosBaja) {
		this.cantidadRegistrosBaja = cantidadRegistrosBaja;
	}
	/**
	 * @return the cantidadRegistrosAlta
	 */
	public String getCantidadRegistrosAlta() {
		return cantidadRegistrosAlta;
	}
	/**
	 * @param cantidadRegistrosAlta the cantidadRegistrosAlta to set
	 */
	public void setCantidadRegistrosAlta(String cantidadRegistrosAlta) {
		this.cantidadRegistrosAlta = cantidadRegistrosAlta;
	}
	/**
	 * @return the cantidadRegistrosTrasvase
	 */
	public String getCantidadRegistrosTrasvase() {
		return cantidadRegistrosTrasvase;
	}
	/**
	 * @param cantidadRegistrosTrasvase the cantidadRegistrosTrasvase to set
	 */
	public void setCantidadRegistrosTrasvase(String cantidadRegistrosTrasvase) {
		this.cantidadRegistrosTrasvase = cantidadRegistrosTrasvase;
	}
	/**
	 * @return the cantidadRegistrosModificacion
	 */
	public String getCantidadRegistrosModificacion() {
		return cantidadRegistrosModificacion;
	}
	/**
	 * @param cantidadRegistrosModificacion the cantidadRegistrosModificacion to set
	 */
	public void setCantidadRegistrosModificacion(
			String cantidadRegistrosModificacion) {
		this.cantidadRegistrosModificacion = cantidadRegistrosModificacion;
	}
	/**
	 * @return the indicadorNivelSocioEconomico
	 */
	public String getIndicadorNivelSocioEconomico() {
		return indicadorNivelSocioEconomico;
	}
	/**
	 * @param indicadorNivelSocioEconomico the indicadorNivelSocioEconomico to set
	 */
	public void setIndicadorNivelSocioEconomico(String indicadorNivelSocioEconomico) {
		this.indicadorNivelSocioEconomico = indicadorNivelSocioEconomico;
	}

	/**
	 * @return the codigosZona
	 */
	public String[] getCodigosZona() {
		return codigosZona;
	}

	/**
	 * @param codigosZona the codigosZona to set
	 */
	public void setCodigosZona(String[] codigosZona) {
		this.codigosZona = codigosZona;
	}

	/**
	 * @return the indRegionZona
	 */
	public String getIndRegionZona() {
		return indRegionZona;
	}

	/**
	 * @param indRegionZona the indRegionZona to set
	 */
	public void setIndRegionZona(String indRegionZona) {
		this.indRegionZona = indRegionZona;
	}
	
	
}
