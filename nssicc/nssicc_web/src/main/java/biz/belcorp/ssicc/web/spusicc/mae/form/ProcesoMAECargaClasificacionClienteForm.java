package biz.belcorp.ssicc.web.spusicc.mae.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarBoletasRecojoForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="">JPPS</a>
 * 
 * @struts.form name = "interfazAPEEnviarBoletasRecojoForm" extends =
 *              "baseInterfazForm"
 */
public class ProcesoMAECargaClasificacionClienteForm extends BaseProcesoForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7741899570667046328L;
	/**
	 * 
	 */
	private String codigoPais;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String numeroLote;
	private String numRegistrosValido;
	private String numeroCarga;
	
	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;
	private boolean flagBotonReporte;

	private String indicadorTipoClasificacionPais;
	
	
	public void reset() {
		this.numeroLote=null;
		this.indicadorTipoClasificacionPais = Constants.ESTADO_INACTIVO;
	}
	
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}

	/**
	 * @param numRegistrosError the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
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
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
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
	 * @struts.validator type="required"
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the numeroCarga
	 */
	public String getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @param numeroCarga the numeroCarga to set
	 */
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}

	/**
	 * @return the flagBotonValidar
	 */
	public boolean isFlagBotonValidar() {
		return flagBotonValidar;
	}

	/**
	 * @param flagBotonValidar the flagBotonValidar to set
	 */
	public void setFlagBotonValidar(boolean flagBotonValidar) {
		this.flagBotonValidar = flagBotonValidar;
	}

	/**
	 * @return the flagBotonActualizar
	 */
	public boolean isFlagBotonActualizar() {
		return flagBotonActualizar;
	}

	/**
	 * @param flagBotonActualizar the flagBotonActualizar to set
	 */
	public void setFlagBotonActualizar(boolean flagBotonActualizar) {
		this.flagBotonActualizar = flagBotonActualizar;
	}

	/**
	 * @return the flagBotonReporte
	 */
	public boolean isFlagBotonReporte() {
		return flagBotonReporte;
	}

	/**
	 * @param flagBotonReporte the flagBotonReporte to set
	 */
	public void setFlagBotonReporte(boolean flagBotonReporte) {
		this.flagBotonReporte = flagBotonReporte;
	}

	/**
	 * @return the indicadorTipoClasificacionPais
	 */
	public String getIndicadorTipoClasificacionPais() {
		return indicadorTipoClasificacionPais;
	}

	/**
	 * @param indicadorTipoClasificacionPais the indicadorTipoClasificacionPais to set
	 */
	public void setIndicadorTipoClasificacionPais(
			String indicadorTipoClasificacionPais) {
		this.indicadorTipoClasificacionPais = indicadorTipoClasificacionPais;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

}
