package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/** 
 * 
 * @author <a href="">Cristhian Roman</a>
 * 
 * @struts.form name="reporteMAENuevasForm" extends="baseReporteForm"
 */

public class ReporteMAENuevasForm extends BaseReporteForm implements Serializable{

	
	private String fechaInicio;
	private String fechaFin;
	private String codigoPais;
	private String tipoConsulta;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String codigoPeriodo;	
	private String[] codigoTipoBloqueo;
	private String indicadorRegionAbierta;
	private String indicadorSinSaldo;
	private String indicadorSinPedido;
	private String[] estado;
	private Date fechaInicioDate;
	private Date fechaFinDate;
	private String tipoArchivoActiva;
	private UploadedFile clienteFile; 
	private String directorioTemporal;
	private boolean indicadorClientesBloqueados;
	private String codigoTipoDocu;
	
	/**
	 * @return
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	private static final long serialVersionUID = 1L;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio
	 *            The fechaInicio to set.
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

/** 
 * @param fechaFin
 *            The fechaFin to set.
 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
/**
 * @return the tipoConsulta
 */
public String getTipoConsulta() {
	return tipoConsulta;
}
/**
 * @param tipoConsulta
 *            The tipoConsulta to set.
 */
public void setTipoConsulta(String tipoConsulta) {
	this.tipoConsulta = tipoConsulta;
}

/**
 * @return codigoPeriodo
 */
public String getCodigoPeriodo() {
	return codigoPeriodo;
}

/**
 * @param codigoPeriodo
 */
public void setCodigoPeriodo(String codigoPeriodo) {
	this.codigoPeriodo = codigoPeriodo;
}

/**
 * @return the codigoTipoBloqueo
 */
public String[] getCodigoTipoBloqueo() {
	return codigoTipoBloqueo;
}

/**
 * @param codigoTipoBloqueo the codigoTipoBloqueo to set
 */
public void setCodigoTipoBloqueo(String[] codigoTipoBloqueo) {
	this.codigoTipoBloqueo = codigoTipoBloqueo;
}

/**
 * @return the indicadorRegionAbierta
 */
public String getIndicadorRegionAbierta() {
	return indicadorRegionAbierta;
}

/**
 * @return the indicadorSinSaldo
 */
public String getIndicadorSinSaldo() {
	return indicadorSinSaldo;
}

/**
 * @return the indicadorSinPedido
 */
public String getIndicadorSinPedido() {
	return indicadorSinPedido;
}

/**
 * @return the estado
 */
public String[] getEstado() {
	return estado;
}

/**
 * @param indicadorRegionAbierta the indicadorRegionAbierta to set
 */
public void setIndicadorRegionAbierta(String indicadorRegionAbierta) {
	this.indicadorRegionAbierta = indicadorRegionAbierta;
}

/**
 * @param indicadorSinSaldo the indicadorSinSaldo to set
 */
public void setIndicadorSinSaldo(String indicadorSinSaldo) {
	this.indicadorSinSaldo = indicadorSinSaldo;
}

/**
 * @param indicadorSinPedido the indicadorSinPedido to set
 */
public void setIndicadorSinPedido(String indicadorSinPedido) {
	this.indicadorSinPedido = indicadorSinPedido;
}

/**
 * @param estado the estado to set
 */
public void setEstado(String[] estado) {
	this.estado = estado;
}

public Date getFechaInicioDate() {
	return fechaInicioDate;
}

public void setFechaInicioDate(Date fechaInicioDate) {
	this.fechaInicioDate = fechaInicioDate;
}

public Date getFechaFinDate() {
	return fechaFinDate;
}

public void setFechaFinDate(Date fechaFinDate) {
	this.fechaFinDate = fechaFinDate;
}

/**
	 * @return the tipoArchivoActiva
	 */
	public String getTipoArchivoActiva() {
		return tipoArchivoActiva;
	}
	
	/**
	 * @param tipoArchivoActiva the tipoArchivoActiva to set
	 */
	public void setTipoArchivoActiva(String tipoArchivoActiva) {
		this.tipoArchivoActiva = tipoArchivoActiva;
	}

	/**
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
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
	 * @return the indicadorClientesBloqueados
	 */
	public boolean isIndicadorClientesBloqueados() {
		return indicadorClientesBloqueados;
	}

	/**
	 * @param indicadorClientesBloqueados the indicadorClientesBloqueados to set
	 */
	public void setIndicadorClientesBloqueados(boolean indicadorClientesBloqueados) {
		this.indicadorClientesBloqueados = indicadorClientesBloqueados;
	}

	/**
	 * @return the codigoTipoDocu
	 */
	public String getCodigoTipoDocu() {
		return codigoTipoDocu;
	}

	/**
	 * @param codigoTipoDocu the codigoTipoDocu to set
	 */
	public void setCodigoTipoDocu(String codigoTipoDocu) {
		this.codigoTipoDocu = codigoTipoDocu;
	}
	
	
}
