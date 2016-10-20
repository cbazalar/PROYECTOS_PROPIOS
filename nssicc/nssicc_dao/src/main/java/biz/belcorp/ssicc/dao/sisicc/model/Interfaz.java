/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.model.Pais;

/**
 * Representa a la entidad BAS_INTER de la base de datos.
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class Interfaz extends AuditableBaseObject implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4751321404794163227L;

	private String codigoPais;

	private String codigoSistema;

	private String codigo;

	private String tipo;

	private String tipoGeneracion;

	private String descripcion;

	private String flagHabilitado;

	/** @deprecated */
	private String codigoInterfazEmpaquetada;

	private String flagExtensionArchivo;

	private String extensionArchivo;

	private String flagDelimitadorCampos;

	private String codigoDelimitador;

	private String flagLogErrores;

	private String extensionLogErrores;

	private String directorioTemporal;

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

	private String nombreArchivoEntradaSalida;

	/**
	 * @deprecated
	 */
	private String metodoInterface;

	private String tipoNombreArchivo;

	/**
	 * @deprecated
	 */
	private String indicadorTipoInterface;

	private Long numeroEjecucion;

	private Pais pais;

	private Sistema sistema;

	private String extensionArchivoDescripcion;

	private String extensionLogErroresDescripcion;

	private String flagArchivoVacio;

	private String directorioProc;

	private String flagProceso;

	private String flagComprimido;
    
    private String tipoSeparadorLinea;
        
	private List parametros = new ArrayList();
	
	private List archivos = new ArrayList();

    private String nombreEtiquetaPrincipalXML;
    
    private String nombreEtiquetaRegistroXML;
    
    private String indicadorSeleccion;
    
    private String addSepLineaArchivoVacio;
    
    private String esPaqueteMultiHilo = "N";
    
    private Long ordenHilo;
    
    private Long ordenEjecucion;
    
    private String flagDirectorioEntradaSalida;
    
    private String flagDirectorioLog;
    
    private String flagDirectorioHistorico;
    
    private String indicadorMultiLote = "N";
	
    //Para en caso se tenga que enviar todo las interfaces del paquete como un archivo ZIP
    private String directorioTemporalPaquete;
    
    //nivel del hilo
    private Long nivelHilo;
    
    //sb indicador control
    private String indicadorControl;
    
    private String codigoHomologacion;
    
    /* INI SA PER-SiCC-2012-0840 */
    private String clavePrivadaSFTP;
    private String passwordClavePrivadaSFTP;
    
    private String indicadorEnviarCorreo;
    private String correoOrigen;
    private String correoDestinos;    
    private String correoCC;
    private String subject;
    /* FIN SA PER-SiCC-2012-0840 */
    
    /* INI CB ECU-SiCC-2012-0191 */
    private String valorEncodingSalida;
    
    /* FIN CB ECU-SiCC-2012-0191 */
    
    private String indicadorArchivoTemporalCopyDestino;
    
    
    private String flagValidarCargaPrevia;
    
    private String indicadorValidarLoteEntrada;   
    
    private String flagMoverArchivoEntradaTemporal;
    
	/**
	 * @return the flagValidarCargaPrevia
	 */
	public String getFlagValidarCargaPrevia() {
		return flagValidarCargaPrevia;
	}
    
	/**
	 * @return
	 */
	public Long getNivelHilo() {
		return nivelHilo;
	}

	/**
	 * @param nivelHilo
	 */
	public void setNivelHilo(Long nivelHilo) {
		this.nivelHilo = nivelHilo;
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

	public InterfazPK getInterfazPK() {
		return new InterfazPK(codigoPais, codigoSistema, codigo);
	}

	public boolean isTipoUnitaria() {
		return getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA);
	}

	public boolean isTipoPaquete() {
		return getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE);
	}

	public boolean comprimir() {
		return StringUtils.equalsIgnoreCase(flagComprimido, Constants.SI);
	}

	public List getParametros() {
		return parametros;
	}

	public void setParametros(List parametros) {
		this.parametros = parametros;
	}

	public String getTipoFormatoArchivo() {
		return tipoFormatoArchivo;
	}

	public void setTipoFormatoArchivo(String tipoFormatoArchivo) {
		this.tipoFormatoArchivo = tipoFormatoArchivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoDelimitador() {
		return codigoDelimitador;
	}

	public void setCodigoDelimitador(String codigoDelimitador) {
		this.codigoDelimitador = codigoDelimitador;
	}

	public String getCodigoFormato() {
		return codigoFormato;
	}

	public void setCodigoFormato(String codigoFormato) {
		this.codigoFormato = codigoFormato;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDirectorioEntradaSalida() {
		return directorioEntradaSalida;
	}

	public void setDirectorioEntradaSalida(String directorioEntradaSalida) {
		this.directorioEntradaSalida = directorioEntradaSalida;
	}

	public String getDirectorioHistorico() {
		return directorioHistorico;
	}

	public void setDirectorioHistorico(String directorioHistorico) {
		this.directorioHistorico = directorioHistorico;
	}

	public String getDirectorioLog() {
		return directorioLog;
	}

	public void setDirectorioLog(String directorioLog) {
		this.directorioLog = directorioLog;
	}

	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	public String getExtensionLogErrores() {
		return extensionLogErrores;
	}

	public void setExtensionLogErrores(String extensionLogErrores) {
		this.extensionLogErrores = extensionLogErrores;
	}

	public String getFlagDelimitadorCampos() {
		return flagDelimitadorCampos;
	}

	public void setFlagDelimitadorCampos(String flagDelimitadorCampos) {
		this.flagDelimitadorCampos = flagDelimitadorCampos;
	}

	public String getFlagEnvioArchivo() {
		return flagEnvioArchivo;
	}

	public void setFlagEnvioArchivo(String flagEnvioArchivo) {
		this.flagEnvioArchivo = flagEnvioArchivo;
	}

	public String getFlagExtensionArchivo() {
		return flagExtensionArchivo;
	}

	public void setFlagExtensionArchivo(String flagExtensionArchivo) {
		this.flagExtensionArchivo = flagExtensionArchivo;
	}

	public String getFlagFormatoArchivo() {
		return flagFormatoArchivo;
	}

	public void setFlagFormatoArchivo(String flagFormatoArchivo) {
		this.flagFormatoArchivo = flagFormatoArchivo;
	}

	public String getFlagHabilitado() {
		return flagHabilitado;
	}

	public void setFlagHabilitado(String flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}

	public String getFlagLogErrores() {
		return flagLogErrores;
	}

	public void setFlagLogErrores(String flagLogErrores) {
		this.flagLogErrores = flagLogErrores;
	}

	public String getFlagTipoFormato() {
		return flagTipoFormato;
	}

	public void setFlagTipoFormato(String flagTipoFormato) {
		this.flagTipoFormato = flagTipoFormato;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getPasswordFtp() {
		return passwordFtp;
	}

	public void setPasswordFtp(String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	public String getPuertoFtp() {
		return puertoFtp;
	}

	public int getPuertoFtpInt() {
		return Integer.parseInt(puertoFtp);
	}

	public void setPuertoFtp(String puertoFtp) {
		this.puertoFtp = puertoFtp;
	}

	public String getServidorFtp() {
		return servidorFtp;
	}

	public void setServidorFtp(String servidorFtp) {
		this.servidorFtp = servidorFtp;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuarioFtp() {
		return usuarioFtp;
	}

	public void setUsuarioFtp(String usuarioFtp) {
		this.usuarioFtp = usuarioFtp;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return -1;
	}

	public String toString() {
		return new ToStringBuilder(this).append("codigo", codigo).append(
				"descripcion", descripcion).toString();
	}

	public String getNombreArchivoEntradaSalida() {
		return nombreArchivoEntradaSalida;
	}

	/**
	 * Devuelve el nombre del Archivo con la extension definida en interfaz y el parametro archivo de entrada
	 * @param archivo
	 * @return
	 */
	public String getNombreArchivo(String archivo) {
		String result;
		String extension = this.getExtensionArchivoDescripcion();
		if (StringUtils.isBlank(extension))
			result =  archivo ;
		else
		    result =  archivo + Constants.DELIMITADOR_EXTENSION_ARCHIVO + extension;
		return result;
	}
	

	public void setNombreArchivoEntradaSalida(String nombreArchivoEntradaSalida) {
		this.nombreArchivoEntradaSalida = nombreArchivoEntradaSalida;
	}

	/**
	 * @deprecated
	 */
	public String getCodigoInterfazEmpaquetada() {
		return codigoInterfazEmpaquetada;
	}

	/**
	 * @deprecated
	 */
	public void setCodigoInterfazEmpaquetada(String codigoInterfazEmpaquetada) {
		this.codigoInterfazEmpaquetada = codigoInterfazEmpaquetada;
	}

	public String getTipoGeneracion() {
		return tipoGeneracion;
	}

	public void setTipoGeneracion(String tipoGeneracion) {
		this.tipoGeneracion = tipoGeneracion;
	}

	/**
	 * @deprecated
	 */
	public String getMetodoInterface() {
		return metodoInterface;
	}

	/**
	 * @deprecated
	 */
	public void setMetodoInterface(String metodoInterface) {
		this.metodoInterface = metodoInterface;
	}

	public String getTipoNombreArchivo() {
		return tipoNombreArchivo;
	}

	public void setTipoNombreArchivo(String tipoNombreArchivo) {
		this.tipoNombreArchivo = tipoNombreArchivo;
	}

	/**
	 * @deprecated
	 */
	public String getIndicadorTipoInterface() {
		return indicadorTipoInterface;
	}

	/**
	 * @deprecated
	 */
	public void setIndicadorTipoInterface(String indicadorTipoInterface) {
		this.indicadorTipoInterface = indicadorTipoInterface;
	}

	public Long getNumeroEjecucion() {
		return numeroEjecucion;
	}

	public void setNumeroEjecucion(Long numeroEjecucion) {
		this.numeroEjecucion = numeroEjecucion;
	}

	public String getExtensionArchivoDescripcion() {
		if (extensionArchivoDescripcion == null) return "";
		return extensionArchivoDescripcion.trim();
	}

	public void setExtensionArchivoDescripcion(
			String extensionArchivoDescripcion) {
		this.extensionArchivoDescripcion = extensionArchivoDescripcion;
	}

	public String getExtensionLogErroresDescripcion() {
		return extensionLogErroresDescripcion;
	}

	public void setExtensionLogErroresDescripcion(
			String extensionLogErroresDescripcion) {
		this.extensionLogErroresDescripcion = extensionLogErroresDescripcion;
	}

	public String getFlagArchivoVacio() {
		return flagArchivoVacio;
	}

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
	 * @param directorioProc
	 *            The directorioProc to set.
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
	 * @param flagComprimido
	 *            The flagComprimido to set.
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
	 * @param flagProceso
	 *            The flagProceso to set.
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
     * @param tipoSeparadorLinea The tipoSeparadorLinea to set.
     */
    public void setTipoSeparadorLinea(String tipoSeparadorLinea) {
        this.tipoSeparadorLinea = tipoSeparadorLinea;
    }

    /**
     * Obtiene la cadena conteniendo el o los caracteres del separador de linea
     * en base al valor del atributo tipoSeparadorLinea
     * 
     * @return Separador de linea de la interfaz
     */
    public String getSeparadorLinea() {
        String separadorLinea = Constants.SEPARADOR_LINEA_SISTEMA_OPERATIVO;
        
        if (StringUtils.isNotBlank(tipoSeparadorLinea)) {
            if (StringUtils.equals(tipoSeparadorLinea,
                    Constants.TIPO_SEPARADOR_LINEA_WINDOWS)) {
                separadorLinea = Constants.SEPARADOR_LINEA_WINDOWS;
            } else if (StringUtils.equals(tipoSeparadorLinea,
                    Constants.TIPO_SEPARADOR_LINEA_UNIX)) {
                separadorLinea = Constants.SEPARADOR_LINEA_UNIX;
            }
        }
        
        return separadorLinea;
    }
    
    /**
	 * @return the archivos
	 */
	public List getArchivos() {
		return archivos;
	}

	/**
	 * @param archivos the archivos to set
	 */
	public void setArchivos(List archivos) {
		this.archivos = archivos;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Interfaz)) {
			return false;
		}
		Interfaz rhs = (Interfaz) object;
		return new EqualsBuilder()
				.append(this.servidorFtp, rhs.servidorFtp)
				.append(this.puertoFtp, rhs.puertoFtp)
				.append(this.flagFormatoArchivo, rhs.flagFormatoArchivo)
				.append(this.codigoInterfazEmpaquetada,
						rhs.codigoInterfazEmpaquetada)
				.append(this.tipoFormatoArchivo, rhs.tipoFormatoArchivo)
				.append(this.sistema, rhs.sistema)
				.append(this.tipoNombreArchivo, rhs.tipoNombreArchivo)
				.append(this.directorioTemporal, rhs.directorioTemporal)
				.append(this.directorioEntradaSalida,
						rhs.directorioEntradaSalida)
				.append(this.auditInfo, rhs.auditInfo)
				.append(this.tipoGeneracion, rhs.tipoGeneracion)
				.append(this.tipo, rhs.tipo)
				.append(this.usuarioFtp, rhs.usuarioFtp)
				.append(this.passwordFtp, rhs.passwordFtp)
				.append(this.flagExtensionArchivo, rhs.flagExtensionArchivo)
				.append(this.codigoPais, rhs.codigoPais)
				.append(this.flagLogErrores, rhs.flagLogErrores)
				.append(this.extensionArchivoDescripcion,
						rhs.extensionArchivoDescripcion)
				.append(this.flagHabilitado, rhs.flagHabilitado)
				.append(this.numeroEjecucion, rhs.numeroEjecucion)
				.append(this.estado, rhs.estado)
				.append(this.extensionLogErrores, rhs.extensionLogErrores)
				.append(this.pais, rhs.pais)
				.append(this.codigo, rhs.codigo)
				.append(this.metodoInterface, rhs.metodoInterface)
				.append(this.extensionLogErroresDescripcion,
						rhs.extensionLogErroresDescripcion)
				.append(this.codigoFormato, rhs.codigoFormato)
				.append(this.flagDelimitadorCampos, rhs.flagDelimitadorCampos)
				.append(this.descripcion, rhs.descripcion)
				.append(this.parametros, rhs.parametros)
				.append(this.flagEnvioArchivo, rhs.flagEnvioArchivo)
				.append(this.codigoSistema, rhs.codigoSistema)
				.append(this.extensionArchivo, rhs.extensionArchivo)
				.append(this.nombreArchivoEntradaSalida,
						rhs.nombreArchivoEntradaSalida)
				.append(this.codigoDelimitador, rhs.codigoDelimitador)
				.append(this.directorioHistorico, rhs.directorioHistorico)
				.append(this.nombreEtiquetaPrincipalXML, rhs.nombreEtiquetaPrincipalXML)
				.append(this.nombreEtiquetaRegistroXML, rhs.nombreEtiquetaRegistroXML)
				.append(this.indicadorTipoInterface, rhs.indicadorTipoInterface)
				.append(this.flagComprimido, rhs.flagComprimido).append(
						this.flagArchivoVacio, rhs.flagArchivoVacio).append(
						this.flagTipoFormato, rhs.flagTipoFormato).append(
						this.directorioLog, rhs.directorioLog).isEquals();
	}

	/**
	 * @return the nombreEtiquetaPrincipalXML
	 */
	public String getNombreEtiquetaPrincipalXML() {
		return nombreEtiquetaPrincipalXML;
	}

	/**
	 * @param nombreEtiquetaPrincipalXML the nombreEtiquetaPrincipalXML to set
	 */
	public void setNombreEtiquetaPrincipalXML(String nombreEtiquetaPrincipalXML) {
		this.nombreEtiquetaPrincipalXML = nombreEtiquetaPrincipalXML;
	}

	/**
	 * @return the nombreEtiquetaRegistroXML
	 */
	public String getNombreEtiquetaRegistroXML() {
		return nombreEtiquetaRegistroXML;
	}

	/**
	 * @param nombreEtiquetaRegistroXML the nombreEtiquetaRegistroXML to set
	 */
	public void setNombreEtiquetaRegistroXML(String nombreEtiquetaRegistroXML) {
		this.nombreEtiquetaRegistroXML = nombreEtiquetaRegistroXML;
	}

	/**
	 * @return the indicadorSeleccion
	 */
	public String getIndicadorSeleccion() {
		return indicadorSeleccion;
	}

	/**
	 * @param indicadorSeleccion the indicadorSeleccion to set
	 */
	public void setIndicadorSeleccion(String indicadorSeleccion) {
		this.indicadorSeleccion = indicadorSeleccion;
	}

	/**
	 * @return the addSepLineaArchivoVacio
	 */
	public String getAddSepLineaArchivoVacio() {
		return addSepLineaArchivoVacio;
	}

	/**
	 * @param addSepLineaArchivoVacio the addSepLineaArchivoVacio to set
	 */
	public void setAddSepLineaArchivoVacio(String addSepLineaArchivoVacio) {
		this.addSepLineaArchivoVacio = addSepLineaArchivoVacio;
	}

	/**
	 * @return the esPaqueteMultiHilo
	 */
	public String getEsPaqueteMultiHilo() {
		return esPaqueteMultiHilo;
	}

	/**
	 * @param esPaqueteMultiHilo the esPaqueteMultiHilo to set
	 */
	public void setEsPaqueteMultiHilo(String esPaqueteMultiHilo) {
		this.esPaqueteMultiHilo = esPaqueteMultiHilo;
	}

	/**
	 * @return the ordenHilo
	 */
	public Long getOrdenHilo() {
		return ordenHilo;
	}

	/**
	 * @param ordenHilo the ordenHilo to set
	 */
	public void setOrdenHilo(Long ordenHilo) {
		this.ordenHilo = ordenHilo;
	}

	/**
	 * @return the ordenEjecucion
	 */
	public Long getOrdenEjecucion() {
		return ordenEjecucion;
	}

	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 */
	public void setOrdenEjecucion(Long ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

	/**
	 * @return the indicadorMultiLote
	 */
	public String getIndicadorMultiLote() {
		return indicadorMultiLote;
	}

	/**
	 * @param indicadorMultiLote the indicadorMultiLote to set
	 */
	public void setIndicadorMultiLote(String indicadorMultiLote) {
		this.indicadorMultiLote = indicadorMultiLote;
	}

	/**
	 * @return the directorioTemporalPaquete
	 */
	public String getDirectorioTemporalPaquete() {
		return directorioTemporalPaquete;
	}

	/**
	 * @param directorioTemporalPaquete the directorioTemporalPaquete to set
	 */
	public void setDirectorioTemporalPaquete(String directorioTemporalPaquete) {
		this.directorioTemporalPaquete = directorioTemporalPaquete;
	}

	/**
	 * @return the indicadorControl
	 */
	public String getIndicadorControl() {
		return indicadorControl;
	}

	/**
	 * @param indicadorControl the indicadorControl to set
	 */
	public void setIndicadorControl(String indicadorControl) {
		this.indicadorControl = indicadorControl;
	}

	/**
	 * @return the codigoHomologacion
	 */
	public String getCodigoHomologacion() {
		return codigoHomologacion;
	}

	/**
	 * @param codigoHomologacion the codigoHomologacion to set
	 */
	public void setCodigoHomologacion(String codigoHomologacion) {
		this.codigoHomologacion = codigoHomologacion;
	}

	/**
	 * @return the clavePrivadaSFTP
	 */
	public String getClavePrivadaSFTP() {
		return clavePrivadaSFTP;
	}

	/**
	 * @param clavePrivadaSFTP the clavePrivadaSFTP to set
	 */
	public void setClavePrivadaSFTP(String clavePrivadaSFTP) {
		this.clavePrivadaSFTP = clavePrivadaSFTP;
	}

	/**
	 * @return the passwordClavePrivadaSFTP
	 */
	public String getPasswordClavePrivadaSFTP() {
		return passwordClavePrivadaSFTP;
	}

	/**
	 * @param passwordClavePrivadaSFTP the passwordClavePrivadaSFTP to set
	 */
	public void setPasswordClavePrivadaSFTP(String passwordClavePrivadaSFTP) {
		this.passwordClavePrivadaSFTP = passwordClavePrivadaSFTP;
	}

	/**
	 * @return the indicadorEnviarCorreo
	 */
	public String getIndicadorEnviarCorreo() {
		return indicadorEnviarCorreo;
	}

	/**
	 * @param indicadorEnviarCorreo the indicadorEnviarCorreo to set
	 */
	public void setIndicadorEnviarCorreo(String indicadorEnviarCorreo) {
		this.indicadorEnviarCorreo = indicadorEnviarCorreo;
	}

	/**
	 * @return the correoOrigen
	 */
	public String getCorreoOrigen() {
		return correoOrigen;
	}

	/**
	 * @param correoOrigen the correoOrigen to set
	 */
	public void setCorreoOrigen(String correoOrigen) {
		this.correoOrigen = correoOrigen;
	}

	/**
	 * @return the correoCC
	 */
	public String getCorreoCC() {
		return correoCC;
	}

	/**
	 * @param correoCC the correoCC to set
	 */
	public void setCorreoCC(String correoCC) {
		this.correoCC = correoCC;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the correoDestinos
	 */
	public String getCorreoDestinos() {
		return correoDestinos;
	}

	/**
	 * @param correoDestinos the correoDestinos to set
	 */
	public void setCorreoDestinos(String correoDestinos) {
		this.correoDestinos = correoDestinos;
	}

	
	/* INI CB ECU-SiCC-2012-0191 */
	/**
	 * @return
	 */
	public String getValorEncodingSalida() {
		return valorEncodingSalida;
	}

	/**
	 * @param valorEncodingSalida
	 */
	public void setValorEncodingSalida(String valorEncodingSalida) {
		this.valorEncodingSalida = valorEncodingSalida;
	}
	/* FIN CB ECU-SiCC-2012-0191 */
	
	
	/**
	 * @return the indicadorArchivoTemporalCopyDestino
	 */
	public String getIndicadorArchivoTemporalCopyDestino() {
		return indicadorArchivoTemporalCopyDestino;
	}

	/**
	 * @param indicadorArchivoTemporalCopyDestino the indicadorArchivoTemporalCopyDestino to set
	 */
	public void setIndicadorArchivoTemporalCopyDestino(
			String indicadorArchivoTemporalCopyDestino) {
		this.indicadorArchivoTemporalCopyDestino = indicadorArchivoTemporalCopyDestino;
	}

	/**
	 * @return the indicadorValidarLoteEntrada
	 */
	public String getIndicadorValidarLoteEntrada() {
		return indicadorValidarLoteEntrada;
	}

	/**
	 * @param indicadorValidarLoteEntrada the indicadorValidarLoteEntrada to set
	 */
	public void setIndicadorValidarLoteEntrada(String indicadorValidarLoteEntrada) {
		this.indicadorValidarLoteEntrada = indicadorValidarLoteEntrada;
	}

	/**
	 * @return the flagMoverArchivoEntradaTemporal
	 */
	public String getFlagMoverArchivoEntradaTemporal() {
		return flagMoverArchivoEntradaTemporal;
	}

	/**
	 * @param flagMoverArchivoEntradaTemporal the flagMoverArchivoEntradaTemporal to set
	 */
	public void setFlagMoverArchivoEntradaTemporal(
			String flagMoverArchivoEntradaTemporal) {
		this.flagMoverArchivoEntradaTemporal = flagMoverArchivoEntradaTemporal;
	}

	/**
	 * @param flagValidarCargaPrevia the flagValidarCargaPrevia to set
	 */
	public void setFlagValidarCargaPrevia(String flagValidarCargaPrevia) {
		this.flagValidarCargaPrevia = flagValidarCargaPrevia;
	}
	
	
	
	/* INI FRAMEWORK NSSICC */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		Interfaz obj = null;
	    obj = (Interfaz)super.clone();
		return obj;
    }
	
	/* FIN FRAMEWORK NSSICC */

}
