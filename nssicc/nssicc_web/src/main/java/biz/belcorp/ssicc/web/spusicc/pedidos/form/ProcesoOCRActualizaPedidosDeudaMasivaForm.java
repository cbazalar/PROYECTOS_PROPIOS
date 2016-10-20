package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoOCRActualizaPedidosDeudaMasivaForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
     * Propiedad que contiene los ids seleccionados, en caso de un listado con
     * seleccion multiple
     */
	
	protected String[] selectedItems = {  };
	
    private String id;
    
    private String tipoIngreso;
    private String fechaInicio;
    private Date fechaInicioDate;
    private String fechaFin;
    private Date fechaFinDate;
    private String operacion; // indicador Deuda o Rechazado o ...
    private String codigoRegion;
    private String codigoZona;    
    private String[] regiones;
    private String[] zonas;
    private String opcion;
    
    private String resumen;    
    
    private String subAcceso;
    private String codigoPeriodo;
    private String codigoCliente;
    private String[] subAccesos; 
    
    private String montoMaximoDeuda;    
    
	private String codigoMarca;	
	private String codigoCanal;
	
	private String estadoDeuda;	
	private String fechaSolicitud;
	
	private String codigoPais;	
	private String estadoProceso;
	private String nombreCliente;
	private String descripcionRegion;
	private String descripcionZona;
	private String codigoTerritorio;
	private String valorSaldoDeudor;
	
    /**
     * Holds value of property indicadorAdmCartera.
     */
    protected boolean indicadorAdmCartera;
	private String observaciones;	
	
	private String indErrorAdminCartera;	

    public String getObservaciones() {
		return observaciones;
	}
    /**
     * 
     * @param observaciones
     * @struts.validator type="required"
     */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstadoDeuda() {
		return estadoDeuda;
	}

	public void setEstadoDeuda(String estadoDeuda) {
		this.estadoDeuda = estadoDeuda;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
     * @return Returns the fechaFin.
     */
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return Returns the fechaInicio.
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return Returns the operacion.
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion
     *            The operacion to set.
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return Returns the tipoIngreso.
     */
    public String getTipoIngreso() {
        return tipoIngreso;
    }

    /**
     * @param tipoIngreso
     *            The tipoIngreso to set.
     */
    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    /*
     *  (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
//    public void reset(ActionMapping mapping, HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        this.fechaInicio = sdf.format(new Date(System.currentTimeMillis()));
//        this.fechaFin = sdf.format(new Date(System.currentTimeMillis()));
//		this.subAccesos = new String[]{ Constants.REC_SUBACCESO_DEFAULT };     
//		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
//		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
//		
//    }

    public String[] getRegiones() {
        return regiones;
    }

    public void setRegiones(String[] regiones) {
        this.regiones = regiones;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getSubAcceso() {
		return subAcceso;
	}

	public void setSubAcceso(String subAcceso) {
		this.subAcceso = subAcceso;
	}

	public String[] getSubAccesos() {
		return subAccesos;
	}

	public void setSubAccesos(String[] subAccesos) {
		this.subAccesos = subAccesos;
	}

	public String[] getZonas() {
		return zonas;
	}

	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	/*public String toString() {
		return new ToStringBuilder(this).append("page", this.getPage()).append(
				"resultValueMap", this.getResultValueMap()).append(
				"codigoCliente", this.codigoCliente).append("zona", this.codigoZona)
				.append("multipartRequestHandler",
						this.getMultipartRequestHandler()).append("subAccesos",
						this.subAccesos)
				.append("tipoIngreso", this.tipoIngreso).append("zonas",
						this.zonas).append("pageSelected", this.pageSelected)
				.append("operacion", this.operacion).append("codigoPeriodo",
						this.codigoPeriodo).append("opcion", this.opcion)
				.append("selectedItems", this.selectedItems).append(
						"subAcceso", this.subAcceso).append("validatorResults",
						this.getValidatorResults()).append("editable",
						this.editable).append("region", this.codigoRegion).append(
						"newRecord", this.newRecord).append("servletWrapper",
						this.getServletWrapper()).append("regiones",
						this.regiones).append("fechaFin", this.fechaFin)
				.append("fechaInicio", this.fechaInicio).toString();
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getValorSaldoDeudor() {
		return valorSaldoDeudor;
	}

	public void setValorSaldoDeudor(String valorSaldoDeudor) {
		this.valorSaldoDeudor = valorSaldoDeudor;
	}

	public boolean isIndicadorAdmCartera() {
		return indicadorAdmCartera;
	}

	public void setIndicadorAdmCartera(boolean indicadorAdmCartera) {
		this.indicadorAdmCartera = indicadorAdmCartera;
	}

	public String getIndErrorAdminCartera() {
		return indErrorAdminCartera;
	}

	public void setIndErrorAdminCartera(String indErrorAdminCartera) {
		this.indErrorAdminCartera = indErrorAdminCartera;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getMontoMaximoDeuda() {
		return montoMaximoDeuda;
	}
	public void setMontoMaximoDeuda(String montoMaximoDeuda) {
		this.montoMaximoDeuda = montoMaximoDeuda;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
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
}
