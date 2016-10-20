/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoMENCargaMasivaInformacionMensajesForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904650138303202583L;
	
	private String codigoPais;
	
	private String indicadorValido;
	private String numRegistros;
	private String numRegistrosError;
	private String numRegistrosValido;
	

	private boolean flagBotonValidar;//creo es flagValidar
	private boolean flagBotonActualizar;// creo es flagProcesar
	
	private String codigoPeriodo;
	private String codigoPeriodoActual;
	private String codigoPeriodoCargaMasiva;
	private String tipoCarga;
	
	
	private String codigoCUVIngreso;
	private String codigoCUV;
	private String descripcionProducto;
	private String precio;
	private String descripcionCatalogo;
	private String pagina;
	
	private String codigoRegion;
	private String codigoZona;
	private String codigoRegionIngreso;
	private String codigoZonaIngreso;
	private String local;
	private String direccion;
	private String fecha;
	private Date fechaDate;
	private String hora;
	private String minutos;
	private String horario;
	
	private String cargaMasiva;
	private boolean flagCargaMasiva;

	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;
	private UploadedFile file;

	
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
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}

	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
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
	 * @return the numRegistrosValido
	 */
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	/**
	 * @param numRegistrosValido the numRegistrosValido to set
	 */
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual the codigoPeriodoActual to set
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return the codigoPeriodoCargaMasiva
	 */
	public String getCodigoPeriodoCargaMasiva() {
		return codigoPeriodoCargaMasiva;
	}

	/**
	 * @param codigoPeriodoCargaMasiva the codigoPeriodoCargaMasiva to set
	 */
	public void setCodigoPeriodoCargaMasiva(String codigoPeriodoCargaMasiva) {
		this.codigoPeriodoCargaMasiva = codigoPeriodoCargaMasiva;
	}

	/**
	 * @return the tipoCarga
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param tipoCarga the tipoCarga to set
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	/**
	 * @return the codigoCUVIngreso
	 */
	public String getCodigoCUVIngreso() {
		return codigoCUVIngreso;
	}

	/**
	 * @param codigoCUVIngreso the codigoCUVIngreso to set
	 */
	public void setCodigoCUVIngreso(String codigoCUVIngreso) {
		this.codigoCUVIngreso = codigoCUVIngreso;
	}

	/**
	 * @return the codigoCUV
	 */
	public String getCodigoCUV() {
		return codigoCUV;
	}

	/**
	 * @param codigoCUV the codigoCUV to set
	 */
	public void setCodigoCUV(String codigoCUV) {
		this.codigoCUV = codigoCUV;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * @return the descripcionCatalogo
	 */
	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}

	/**
	 * @param descripcionCatalogo the descripcionCatalogo to set
	 */
	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}

	/**
	 * @return the pagina
	 */
	public String getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(String pagina) {
		this.pagina = pagina;
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
	 * @return the codigoRegionIngreso
	 */
	public String getCodigoRegionIngreso() {
		return codigoRegionIngreso;
	}

	/**
	 * @param codigoRegionIngreso the codigoRegionIngreso to set
	 */
	public void setCodigoRegionIngreso(String codigoRegionIngreso) {
		this.codigoRegionIngreso = codigoRegionIngreso;
	}

	/**
	 * @return the codigoZonaIngreso
	 */
	public String getCodigoZonaIngreso() {
		return codigoZonaIngreso;
	}

	/**
	 * @param codigoZonaIngreso the codigoZonaIngreso to set
	 */
	public void setCodigoZonaIngreso(String codigoZonaIngreso) {
		this.codigoZonaIngreso = codigoZonaIngreso;
	}

	/**
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return the minutos
	 */
	public String getMinutos() {
		return minutos;
	}

	/**
	 * @param minutos the minutos to set
	 */
	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return the cargaMasiva
	 */
	public String getCargaMasiva() {
		return cargaMasiva;
	}

	/**
	 * @param cargaMasiva the cargaMasiva to set
	 */
	public void setCargaMasiva(String cargaMasiva) {
		this.cargaMasiva = cargaMasiva;
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

	/**
	 * @return the flagCargaMasiva
	 */
	public boolean isFlagCargaMasiva() {
		return flagCargaMasiva;
	}

	/**
	 * @param flagCargaMasiva the flagCargaMasiva to set
	 */
	public void setFlagCargaMasiva(boolean flagCargaMasiva) {
		this.flagCargaMasiva = flagCargaMasiva;
	}

	/**
	 * @return the fechaDate
	 */
	public Date getFechaDate() {
		return fechaDate;
	}

	/**
	 * @param fechaDate the fechaDate to set
	 */
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	} 

	
}
