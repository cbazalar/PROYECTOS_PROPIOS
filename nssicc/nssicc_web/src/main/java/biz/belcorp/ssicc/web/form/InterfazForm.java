/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */

public class InterfazForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2836736075498753657L;

	private String codigoPais;

	private String codigoSistema;

	private String codigoInterfaz;

	private String codigoInterfazEmpaquetada;

	private String codigo;

	private String tipo;

	private String tipoGeneracion;

	private String descripcion;

	private String flagHabilitado;

	private String flagExtensionArchivo;

	private String extensionArchivo;

	private String flagDelimitadorCampos;

	private String codigoDelimitador;

	private String flagLogErrores;

	private String extensionLogErrores;

	private String directorioTemporal;
	
	private String directorioProc;

	private String directorioLog;

	private String directorioHistorico;

	private String flagEnvioArchivo;

	private String directorioEntradaSalida;

	private String servidorFtp;

	private String puertoFtp;

	private String usuarioFtp;

	private String passwordFtp;

	private String estado;

	private String flagFormatoArchivo;

	private String codigoFormato;

	private String flagTipoFormato;

	private String tipoFormatoArchivo;
	
	private String nombreEtiquetaPrincipalXML;
	
	private String nombreEtiquetaRegistroXML;

	private String nombreArchivoEntradaSalida;

	private String tipoNombreArchivo;
	
	private String flagComprimido;
	
	private String flagProceso;
    
    private String tipoSeparadorLinea;
    
    private String flagDirectorioEntradaSalida;
    
    private String flagDirectorioLog;
    
    private String flagDirectorioHistorico;

    private String indicadorSeleccion;

	protected String nombreParametro;
	
	protected String valorParametro;
    	
	private Boolean flagModificarBool;
	private Boolean flagExtensionArchivoBool;
	private Boolean flagLogErroresBool;
	private Boolean flagDelimitadorCamposBool;
	private Boolean flagFormatoArchivoBool;
	
	/**
	 * @return
	 */
	public String getIndicadorSeleccion() {
		return indicadorSeleccion;
	}

	/**
	 * @param indicadorSeleccion
	 */
	public void setIndicadorSeleccion(String indicadorSeleccion) {
		this.indicadorSeleccion = indicadorSeleccion;
	}

	/**
	 * @return
	 */
	public String getFlagDirectorioEntradaSalida() {
		return flagDirectorioEntradaSalida;
	}

	/**
	 * @param flagDirectorioEntradaSalida
	 */
	public void setFlagDirectorioEntradaSalida(String flagDirectorioEntradaSalida) {
		this.flagDirectorioEntradaSalida = flagDirectorioEntradaSalida;
	}

	/**
	 * @return
	 */
	public String getFlagDirectorioLog() {
		return flagDirectorioLog;
	}

	/**
	 * @param flagDirectorioLog
	 */
	public void setFlagDirectorioLog(String flagDirectorioLog) {
		this.flagDirectorioLog = flagDirectorioLog;
	}

	/**
	 * @return
	 */
	public String getFlagDirectorioHistorico() {
		return flagDirectorioHistorico;
	}

	/**
	 * @param flagDirectorioHistorico
	 */
	public void setFlagDirectorioHistorico(String flagDirectorioHistorico) {
		this.flagDirectorioHistorico = flagDirectorioHistorico;
	}

	/**
	 * @deprecated
	 */
	private String metodoInterface;

	/**
	 * @deprecated
	 */
	private String indicadorTipoInterface;

	private String flagArchivoVacio;

	private ParametroInterfazForm[] parametrosForm;

	/**
	 * @return Returns the parametrosForm.
	 */
	public ParametroInterfazForm[] getParametrosForm() {
		return parametrosForm;
	}

	/**
	 * @param parametrosForm
	 *            The parametrosForm to set.
	 */
	public void setParametrosForm(ParametroInterfazForm[] parametrosForm) {
		this.parametrosForm = parametrosForm;
	}

	/**
	 * @return Returns the tipoFormatoArchivo.
	 */
	public String getTipoFormatoArchivo() {
		return tipoFormatoArchivo;
	}

	/**
	 * @param tipoFormatoArchivo
	 *            The tipoFormatoArchivo to set.
	 * 
	 */
	public void setTipoFormatoArchivo(String tipoFormatoArchivo) {
		this.tipoFormatoArchivo = tipoFormatoArchivo;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 * 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the codigoDelimitador.
	 */
	public String getCodigoDelimitador() {
		return codigoDelimitador;
	}

	/**
	 * @param codigoDelimitador
	 *            The codigoDelimitador to set.
	 */
	public void setCodigoDelimitador(String codigoDelimitador) {
		this.codigoDelimitador = codigoDelimitador;
	}

	/**
	 * @return Returns the codigoFormato.
	 */
	public String getCodigoFormato() {
		return codigoFormato;
	}

	/**
	 * @param codigoFormato
	 *            The codigoFormato to set.
	 */
	public void setCodigoFormato(String codigoFormato) {
		this.codigoFormato = codigoFormato;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 * 
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            The descripcion to set.
	 * 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Returns the directorioEntradaSalida.
	 */
	public String getDirectorioEntradaSalida() {
		return directorioEntradaSalida;
	}

	/**
	 * @param directorioEntradaSalida
	 *            The directorioEntradaSalida to set.
	 * 
	 */
	public void setDirectorioEntradaSalida(String directorioEntradaSalida) {
		this.directorioEntradaSalida = directorioEntradaSalida;
	}

	/**
	 * @return Returns the directorioHistorico.
	 */
	public String getDirectorioHistorico() {
		return directorioHistorico;
	}

	/**
	 * @param directorioHistorico
	 *            The directorioHistorico to set.
	 * 
	 */
	public void setDirectorioHistorico(String directorioHistorico) {
		this.directorioHistorico = directorioHistorico;
	}

	/**
	 * @return Returns the directorioLog.
	 */
	public String getDirectorioLog() {
		return directorioLog;
	}

	/**
	 * @param directorioLog
	 *            The directorioLog to set.
	 * 
	 */
	public void setDirectorioLog(String directorioLog) {
		this.directorioLog = directorioLog;
	}

	/**
	 * @return Returns the directorioTemporal.
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the extensionArchivo.
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo
	 *            The extensionArchivo to set.
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return Returns the extensionLogErrores.
	 */
	public String getExtensionLogErrores() {
		return extensionLogErrores;
	}

	/**
	 * @param extensionLogErrores
	 *            The extensionLogErrores to set.
	 */
	public void setExtensionLogErrores(String extensionLogErrores) {
		this.extensionLogErrores = extensionLogErrores;
	}

	/**
	 * @return Returns the flagDelimitadorCampos.
	 */
	public String getFlagDelimitadorCampos() {
		return flagDelimitadorCampos;
	}

	/**
	 * @param flagDelimitadorCampos
	 *            The flagDelimitadorCampos to set.
	 */
	public void setFlagDelimitadorCampos(String flagDelimitadorCampos) {
		this.flagDelimitadorCampos = flagDelimitadorCampos;
	}

	/**
	 * @return Returns the flagEnvioArchivo.
	 */
	public String getFlagEnvioArchivo() {
		return flagEnvioArchivo;
	}

	/**
	 * @param flagEnvioArchivo
	 *            The flagEnvioArchivo to set.
	 * 
	 */
	public void setFlagEnvioArchivo(String flagEnvioArchivo) {
		this.flagEnvioArchivo = flagEnvioArchivo;
	}

	/**
	 * @return Returns the flagExtensionArchivo.
	 */
	public String getFlagExtensionArchivo() {
		return flagExtensionArchivo;
	}

	/**
	 * @param flagExtensionArchivo
	 *            The flagExtensionArchivo to set.
	 */
	public void setFlagExtensionArchivo(String flagExtensionArchivo) {
		this.flagExtensionArchivo = flagExtensionArchivo;
	}

	/**
	 * @return Returns the flagFormatoArchivo.
	 */
	public String getFlagFormatoArchivo() {
		return flagFormatoArchivo;
	}

	/**
	 * @param flagFormatoArchivo
	 *            The flagFormatoArchivo to set.
	 */
	public void setFlagFormatoArchivo(String flagFormatoArchivo) {
		this.flagFormatoArchivo = flagFormatoArchivo;
	}

	/**
	 * @return Returns the flagHabilitado.
	 */
	public String getFlagHabilitado() {
		return flagHabilitado;
	}

	/**
	 * @param flagHabilitado
	 *            The flagHabilitado to set.
	 * 
	 */
	public void setFlagHabilitado(String flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}

	/**
	 * @return Returns the flagLogErrores.
	 */
	public String getFlagLogErrores() {
		return flagLogErrores;
	}

	/**
	 * @param flagLogErrores
	 *            The flagLogErrores to set.
	 */
	public void setFlagLogErrores(String flagLogErrores) {
		this.flagLogErrores = flagLogErrores;
	}

	/**
	 * @return Returns the flagTipoFormato.
	 */
	public String getFlagTipoFormato() {
		return flagTipoFormato;
	}

	/**
	 * @param flagTipoFormato
	 *            The flagTipoFormato to set.
	 */
	public void setFlagTipoFormato(String flagTipoFormato) {
		this.flagTipoFormato = flagTipoFormato;
	}

	/**
	 * @return Returns the passwordFtp.
	 */
	public String getPasswordFtp() {
		return passwordFtp;
	}

	/**
	 * @param passwordFtp
	 *            The passwordFtp to set.
	 */
	public void setPasswordFtp(String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	/**
	 * @return Returns the puertoFtp.
	 */
	public String getPuertoFtp() {
		return puertoFtp;
	}

	/**
	 * @param puertoFtp
	 *            The puertoFtp to set.
	 */
	public void setPuertoFtp(String puertoFtp) {
		this.puertoFtp = puertoFtp;
	}

	/**
	 * @return Returns the servidorFtp.
	 */
	public String getServidorFtp() {
		return servidorFtp;
	}

	/**
	 * @param servidorFtp
	 *            The servidorFtp to set.
	 */
	public void setServidorFtp(String servidorFtp) {
		this.servidorFtp = servidorFtp;
	}

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            The tipo to set.
	 * 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Returns the usuarioFtp.
	 */
	public String getUsuarioFtp() {
		return usuarioFtp;
	}

	/**
	 * @param usuarioFtp
	 *            The usuarioFtp to set.
	 */
	public void setUsuarioFtp(String usuarioFtp) {
		this.usuarioFtp = usuarioFtp;
	}

	public String getNombreArchivoEntradaSalida() {
		return nombreArchivoEntradaSalida;
	}

	public void setNombreArchivoEntradaSalida(String nombreArchivoEntradaSalida) {
		this.nombreArchivoEntradaSalida = nombreArchivoEntradaSalida;
	}

	/**
	 * @return Returns the tipoGeneracion.
	 */
	public String getTipoGeneracion() {
		return tipoGeneracion;
	}

	/**
	 * @param tipoGeneracion
	 *            The tipoGeneracion to set.
	 * 
	 */
	public void setTipoGeneracion(String tipoGeneracion) {
		this.tipoGeneracion = tipoGeneracion;
	}

	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz
	 *            The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return Returns the codigoInterfazEmpaquetada.
	 */
	public String getCodigoInterfazEmpaquetada() {
		return codigoInterfazEmpaquetada;
	}

	/**
	 * @param codigoInterfazEmpaquetada
	 *            The codigoInterfazEmpaquetada to set.
	 */
	public void setCodigoInterfazEmpaquetada(String codigoInterfazEmpaquetada) {
		this.codigoInterfazEmpaquetada = codigoInterfazEmpaquetada;
	}

	/**
	 * @return Returns the tipoNombreArchivo.
	 */
	public String getTipoNombreArchivo() {
		return tipoNombreArchivo;
	}

	/**
	 * @param tipoNombreArchivo
	 *            The tipoNombreArchivo to set.
	 */
	public void setTipoNombreArchivo(String tipoNombreArchivo) {
		this.tipoNombreArchivo = tipoNombreArchivo;
	}

	/**
	 * @return Returns the metodoInterface.
	 */
	public String getMetodoInterface() {
		return metodoInterface;
	}

	/**
	 * @param metodoInterface
	 *            The metodoInterface to set.
	 */
	public void setMetodoInterface(String metodoInterface) {
		this.metodoInterface = metodoInterface;
	}

	/**
	 * @return Returns the indicadorTipoInterface.
	 */
	public String getIndicadorTipoInterface() {
		return indicadorTipoInterface;
	}

	/**
	 * @param indicadorTipoInterface
	 *            The indicadorTipoInterface to set.
	 */
	public void setIndicadorTipoInterface(String indicadorTipoInterface) {
		this.indicadorTipoInterface = indicadorTipoInterface;
	}

	/**
	 * @return Returns the flagArchivoVacio.
	 */
	public String getFlagArchivoVacio() {
		return flagArchivoVacio;
	}

	/**
	 * @param flagArchivoVacio
	 *            The flagArchivoVacio to set.
	 */
	public void setFlagArchivoVacio(String flagArchivoVacio) {
		this.flagArchivoVacio = flagArchivoVacio;
	}

	/**
	 * @return Returns the directorioProc.
	 */
	public String getDirectorioProc() {
		return directorioProc;
	}

	/**
	 * @param directorioProc The directorioProc to set.
	 */
	public void setDirectorioProc(String directorioProc) {
		this.directorioProc = directorioProc;
	}

	/**
	 * @return Returns the flagComprimido.
	 */
	public String getFlagComprimido() {
		return flagComprimido;
	}

	/**
	 * @param flagComprimido The flagComprimido to set.
	 */
	public void setFlagComprimido(String flagComprimido) {
		this.flagComprimido = flagComprimido;
	}

	/**
	 * @return Returns the flagProceso.
	 */
	public String getFlagProceso() {
		return flagProceso;
	}

	/**
	 * @param flagProceso The flagProceso to set.
	 */
	public void setFlagProceso(String flagProceso) {
		this.flagProceso = flagProceso;
	}

    /**
     * @return Returns the tipoSeparadorLinea.
     */
    public String getTipoSeparadorLinea() {
        return tipoSeparadorLinea;
    }

    /**
     * @param tipoSeparadorLinea
     *            The tipoSeparadorLinea to set.
     */
    public void setTipoSeparadorLinea(String tipoSeparadorLinea) {
        this.tipoSeparadorLinea = tipoSeparadorLinea;
    }

	/**
	 * @return nombreEtiquetaPrincipalXML
	 */
	public String getNombreEtiquetaPrincipalXML() {
		return nombreEtiquetaPrincipalXML;
	}

	/**
	 * @param nombreEtiquetaPrincipalXML
	 */
	public void setNombreEtiquetaPrincipalXML(String nombreEtiquetaPrincipalXML) {
		this.nombreEtiquetaPrincipalXML = nombreEtiquetaPrincipalXML;
	}

	/**
	 * @return nombreEtiquetaRegistroXML
	 */
	public String getNombreEtiquetaRegistroXML() {
		return nombreEtiquetaRegistroXML;
	}

	/**
	 * @param nombreEtiquetaRegistroXML
	 */
	public void setNombreEtiquetaRegistroXML(String nombreEtiquetaRegistroXML) {
		this.nombreEtiquetaRegistroXML = nombreEtiquetaRegistroXML;
	}

	/**
	 * @return the nombreParametro
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @param nombreParametro the nombreParametro to set
	 */
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	/**
	 * @return the flagModificarBool
	 */
	public Boolean getFlagModificarBool() {
		return flagModificarBool;
	}

	/**
	 * @param flagModificarBool the flagModificarBool to set
	 */
	public void setFlagModificarBool(Boolean flagModificarBool) {
		this.flagModificarBool = flagModificarBool;
	}

	/**
	 * @return the flagExtensionArchivoBool
	 */
	public Boolean getFlagExtensionArchivoBool() {
		return flagExtensionArchivoBool;
	}

	/**
	 * @param flagExtensionArchivoBool the flagExtensionArchivoBool to set
	 */
	public void setFlagExtensionArchivoBool(Boolean flagExtensionArchivoBool) {
		this.flagExtensionArchivoBool = flagExtensionArchivoBool;
	}

	/**
	 * @return the flagLogErroresBool
	 */
	public Boolean getFlagLogErroresBool() {
		return flagLogErroresBool;
	}

	/**
	 * @param flagLogErroresBool the flagLogErroresBool to set
	 */
	public void setFlagLogErroresBool(Boolean flagLogErroresBool) {
		this.flagLogErroresBool = flagLogErroresBool;
	}

	/**
	 * @return the flagDelimitadorCamposBool
	 */
	public Boolean getFlagDelimitadorCamposBool() {
		return flagDelimitadorCamposBool;
	}

	/**
	 * @param flagDelimitadorCamposBool the flagDelimitadorCamposBool to set
	 */
	public void setFlagDelimitadorCamposBool(Boolean flagDelimitadorCamposBool) {
		this.flagDelimitadorCamposBool = flagDelimitadorCamposBool;
	}

	/**
	 * @return the flagFormatoArchivoBool
	 */
	public Boolean getFlagFormatoArchivoBool() {
		return flagFormatoArchivoBool;
	}

	/**
	 * @param flagFormatoArchivoBool the flagFormatoArchivoBool to set
	 */
	public void setFlagFormatoArchivoBool(Boolean flagFormatoArchivoBool) {
		this.flagFormatoArchivoBool = flagFormatoArchivoBool;
	}    
}
