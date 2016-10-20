/*
 * 
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;

// TODO: Auto-generated Javadoc
/**
 * The Class Pais.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */
public class Pais extends AuditableBaseObject implements Serializable {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8144760011618833233L;

	/** The oid pais. */
	private long oidPais;
	
    /**
     * @return Returns the oidPais
     */
    public long getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais 
     *            The oidPais to set.
	 */
	public void setOidPais(long oidPais) {
		this.oidPais = oidPais;
	}
    
    /** The codigo. */
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /** The descripcion. */
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /** The codigo privilege. */
    private String codigoPrivilege;

    public String getCodigoPrivilege() {
        return codigoPrivilege;
    }

    public void setCodigoPrivilege(String codigoPrivilege) {
        this.codigoPrivilege = codigoPrivilege;
    }

    /** The url. */
    private String URL;
    
    /** The ambiente aplicacion. */
    private String ambienteAplicacion;

   
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
        if (StringUtils.isBlank(URL))
        	this.ambienteAplicacion = "    ";
        else
        	this.ambienteAplicacion = StringUtils.right(this.URL.trim(), 3);        
    }
    
    

    /**
	 * @return the ambienteAplicacion
	 */
	public String getAmbienteAplicacion() {
		return ambienteAplicacion;
	}

	/**
	 * @param ambienteAplicacion the ambienteAplicacion to set
	 */
	public void setAmbienteAplicacion(String ambienteAplicacion) {
		this.ambienteAplicacion = ambienteAplicacion;
	}

    /** The estado. */
    private String estado;

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /** The control facturacion. */
    private ControlFacturacion controlFacturacion = new ControlFacturacion();

    /**
     * @return Returns the controlFacturacion.
     */
    public ControlFacturacion getControlFacturacion() {
        return controlFacturacion;
    }

    /**
     * @param controlFacturacion
     *            The controlFacturacion to set.
     */
    public void setControlFacturacion(ControlFacturacion controlFacturacion) {
        this.controlFacturacion = controlFacturacion;
    }
    
    /** The indicador ocr comercial. */
    private String indicadorOCRComercial;
    
    

    /**
	 * @return Returns the indicadorOCRComercial.
	 */
	public String getIndicadorOCRComercial() {
		return indicadorOCRComercial;
	}

	/**
	 * @param indicadorOCRComercial The indicadorOCRComercial to set.
	 */
	public void setIndicadorOCRComercial(String indicadorOCRComercial) {
		this.indicadorOCRComercial = indicadorOCRComercial;
	}

	/**
	 * Equals.
	 *
	 * @param object the object
	 * @return true, if successful
	 * @see java.lang.Object#equals(Object)
	 */
    public boolean equals(Object object) {
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais rhs = (Pais) object;
        return new EqualsBuilder().append(this.controlFacturacion, rhs.controlFacturacion).append(this.descripcion,
                rhs.descripcion).append(this.URL, rhs.URL).append(this.auditInfo, rhs.auditInfo).append(this.estado,
                rhs.estado).append(this.codigoPrivilege, rhs.codigoPrivilege).append(this.codigo, rhs.codigo)
                .isEquals();
    }

    /**
     * Hash code.
     *
     * @return the int
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(57526443, 1334634051).append(this.controlFacturacion).append(this.descripcion)
                .append(this.URL).append(this.auditInfo).append(this.estado).append(this.codigoPrivilege).append(
                        this.codigo).toHashCode();
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("estado", this.estado).append(
                "codigoPrivilege", this.codigoPrivilege).append("URL", this.URL).append("auditInfo", this.auditInfo)
                .append("controlFacturacion", this.controlFacturacion).append("codigo", this.codigo).append(
                        "descripcion", this.descripcion).toString();
    }

    /** The longitud codigo instructora. */
    private Long longitudCodigoInstructora;

    /** The longitud codigo cliente. */
    private long longitudCodigoCliente;

    /**
     * @return Returns the longitudCodigoCliente.
     */
    public long getLongitudCodigoCliente() {
        return longitudCodigoCliente;
    }

    /**
     * @param longitudCodigoCliente The longitudCodigoCliente to set.
     */
    public void setLongitudCodigoCliente(long longitudCodigoCliente) {
        this.longitudCodigoCliente = longitudCodigoCliente;
    }

	/**
	 * @return Returns the longitudCodigoInstructora.
	 */
	public Long getLongitudCodigoInstructora() {
		return longitudCodigoInstructora;
	}

	/**
	 * @param longitudCodigoInstructora The longitudCodigoInstructora to set.
	 */
	public void setLongitudCodigoInstructora(Long longitudCodigoInstructora) {
		this.longitudCodigoInstructora = longitudCodigoInstructora;
	}
    
	/** The indicador mostrar detalle digitacion pedidos. */
	private String indicadorMostrarDetalleDigitacionPedidos;

	/**
	 * @return the indicadorMostrarDetalleDigitacionPedidos
	 */
	public String getIndicadorMostrarDetalleDigitacionPedidos() {
		return indicadorMostrarDetalleDigitacionPedidos;
	}

	/**
	 * @param indicadorMostrarDetalleDigitacionPedidos the indicadorMostrarDetalleDigitacionPedidos to set
	 */
	public void setIndicadorMostrarDetalleDigitacionPedidos(
			String indicadorMostrarDetalleDigitacionPedidos) {
		this.indicadorMostrarDetalleDigitacionPedidos = indicadorMostrarDetalleDigitacionPedidos;
	}
	
	/** The codigo idioma iso. */
	private String codigoIdiomaIso;
	
	/** The codigo pais iso. */
	private String codigoPaisIso;

	/**
	 * @return Returns the codigoIdiomaIso.
	 */
	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	/**
	 * @param codigoIdiomaIso The codigoIdiomaIso to set.
	 */
	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	/**
	 * @return Returns the codigoPaisIso.
	 */
	public String getCodigoPaisIso() {
		return codigoPaisIso;
	}

	/**
	 * @param codigoPaisIso The codigoPaisIso to set.
	 */
	public void setCodigoPaisIso(String codigoPaisIso) {
		this.codigoPaisIso = codigoPaisIso;
	}
	
	/** The codigo periodo facturado. */
	private String codigoPeriodoFacturado;

	/**
	 * @return
	 */
	public String getCodigoPeriodoFacturado() {
		return codigoPeriodoFacturado;
	}

	/**
	 * @param codigoPeriodoFacturado The codigoPeriodoFacturado to set.
	 */
	public void setCodigoPeriodoFacturado(String codigoPeriodoFacturado) {
		this.codigoPeriodoFacturado = codigoPeriodoFacturado;
	}
	
	/** The indicador pais marca. */
	private String indicadorPaisMarca;

	/**
	 * @return the indicadorPaisMarca
	 */
	public String getIndicadorPaisMarca() {
		return indicadorPaisMarca;
	}

	/**
	 * @param indicadorPaisMarca the indicadorPaisMarca to set
	 */
	public void setIndicadorPaisMarca(String indicadorPaisMarca) {
		this.indicadorPaisMarca = indicadorPaisMarca;
	}
	
	/** The valor repo cabecera fact. */
	private String valorRepoCabeceraFact;

	/**
	 * @return the valorRepoCabeceraFact
	 */
	public String getValorRepoCabeceraFact() {
		return valorRepoCabeceraFact;
	}
	/**
	 * @param valorRepoCabeceraFact the valorRepoCabeceraFact to set
	 */
	public void setValorRepoCabeceraFact(String valorRepoCabeceraFact) {
		this.valorRepoCabeceraFact = valorRepoCabeceraFact;
	}
	
	
	/** The valor repo cabecera nota. */
	private String valorRepoCabeceraNota;

	/**
	 * @return the valorRepoCabeceraNota
	 */
	public String getValorRepoCabeceraNota() {
		return valorRepoCabeceraNota;
	}
	/**
	 * @param valorRepoCabeceraNota the valorRepoCabeceraNota to set
	 */
	public void setValorRepoCabeceraNota(String valorRepoCabeceraNota) {
		this.valorRepoCabeceraNota = valorRepoCabeceraNota;
	}
	
	/** The indicador excluir pedidos anulados. */
	private String indicadorExcluirPedidosAnulados;
	/**
	 * @return the indicadorExcluirPedidosAnulados
	 */
	public String getIndicadorExcluirPedidosAnulados() {
		return indicadorExcluirPedidosAnulados;
	}
	/**
	 * @param indicadorExcluirPedidosAnulados the indicadorExcluirPedidosAnulados to set
	 */
	public void setIndicadorExcluirPedidosAnulados(String indicadorExcluirPedidosAnulados) {
		this.indicadorExcluirPedidosAnulados = indicadorExcluirPedidosAnulados;
	}
	
	/** The indicador numero control documento legal. */
	private String indicadorNumeroControlDocumentoLegal;

	/**
	 * @return the indicadorNumeroControlDocumentoLegal
	 */
	public String getIndicadorNumeroControlDocumentoLegal() {
		return indicadorNumeroControlDocumentoLegal;
	}

	/**
	 * @param indicadorNumeroControlDocumentoLegal
	 */
	public void setIndicadorNumeroControlDocumentoLegal(String indicadorNumeroControlDocumentoLegal) {
		this.indicadorNumeroControlDocumentoLegal = indicadorNumeroControlDocumentoLegal;
	}
	
	/** The indicador visualizar montos facturacion. */
	private String indicadorVisualizarMontosFacturacion;

	/**
	 * @return the indicadorVisualizarMontosFacturacion
	 */
	public String getIndicadorVisualizarMontosFacturacion() {
		return indicadorVisualizarMontosFacturacion;
	}

	/**
	 * @param indicadorVisualizarMontosFacturacion the indicadorVisualizarMontosFacturacion to set
	 */
	public void setIndicadorVisualizarMontosFacturacion(String indicadorVisualizarMontosFacturacion) {
		this.indicadorVisualizarMontosFacturacion = indicadorVisualizarMontosFacturacion;
	}
	
	/** The maximo numero registros file. */
	private String maximoNumeroRegistrosFile;

	/**
	 * @return the maximoNumeroRegistrosFile
	 */
	public String getMaximoNumeroRegistrosFile() {
		return maximoNumeroRegistrosFile;
	}

	/**
	 * @param maximoNumeroRegistrosFile the maximoNumeroRegistrosFile to set
	 */
	public void setMaximoNumeroRegistrosFile(String maximoNumeroRegistrosFile) {
		this.maximoNumeroRegistrosFile = maximoNumeroRegistrosFile;
	}
	
	/** The codigo conexion externa. */
	private String codigoConexionExterna;
	
	/**
	 * @return the codigoConexionExterna
	 */
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}

	/**
	 * @param codigoConexionExterna the codigoConexionExterna to set
	 */
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}
	
	/** The indicador eliminar sesion. */
	private String indicadorEliminarSesion;

	/**
	 * @return the indicadorEliminarSesion
	 */
	public String getIndicadorEliminarSesion() {
		return indicadorEliminarSesion;
	}

	/**
	 * @param indicadorEliminarSesion the indicadorEliminarSesion to set
	 */
	public void setIndicadorEliminarSesion(String indicadorEliminarSesion) {
		this.indicadorEliminarSesion = indicadorEliminarSesion;
	}
	

	/** The url servidor reportes. */
	private String urlServidorReportes;

	/**
	 * @return the urlServidorReportes
	 */
	public String getUrlServidorReportes() {
		return urlServidorReportes;
	}

	/**
	 * @param urlServidorReportes the urlServidorReportes to set
	 */
	public void setUrlServidorReportes(String urlServidorReportes) {
		this.urlServidorReportes = urlServidorReportes;
	}
}
