/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * <p>
 * <a href="InterfazRECProductosReclamadosForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 *
 * @struts.form name = "interfazRECProductosReclamadosForm" extends =
 *              "baseInterfazForm"
 */
public class ReporteRECEnviaProductosForm extends BaseReporteForm
    implements Serializable {
    /**
     * Propiedad que contiene los ids seleccionados, en caso de un listado con
     * seleccion multiple
     */
    protected String[] selectedItems = {  };
    private String subAcceso;
    private String tipoIngreso;
    private String fechaInicio;
    private String fechaFin;
    private String operacion;
    private String region;
    private String[] regiones;
    private String[] zonas;
    private String[] regionListSearch;
	private String zonaListSearch;
    private String[] subAccesos;
    private String opcion;
    
	protected String formatoExportacion; // para el Reporte
	
	protected String tipoReporte; // para el Cabecera o Detalle	
	
	
	private String codigoProcesoBatch;

	private List zonaList2;
	
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
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
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
     * @return Returns the region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     *            The region to set.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return Returns the subAcceso.
     */
    public String getSubAcceso() {
        return subAcceso;
    }

    /**
     * @param subAcceso
     *            The subAcceso to set.
     */
    public void setSubAcceso(String subAcceso) {
        this.subAcceso = subAcceso;
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

    public String[] getRegiones() {
        return regiones;
    }

    public void setRegiones(String[] regiones) {
        this.regiones = regiones;
    }

    public String[] getSubAccesos() {
        return subAccesos;
    }

    public void setSubAccesos(String[] subAccesos) {
        this.subAccesos = subAccesos;
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

	

	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @return the regionListSearch
	 */
	public String[] getRegionListSearch() {
		return regionListSearch;
	}

	/**
	 * @param regionListSearch the regionListSearch to set
	 */
	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}

	/**
	 * @return the zonaListSearch
	 */
	public String getZonaListSearch() {
		return zonaListSearch;
	}

	/**
	 * @param zonaListSearch the zonaListSearch to set
	 */
	public void setZonaListSearch(String zonaListSearch) {
		this.zonaListSearch = zonaListSearch;
	}

	/**
	 * @return the zonaList2
	 */
	public List getZonaList2() {
		return zonaList2;
	}

	/**
	 * @param zonaList2 the zonaList2 to set
	 */
	public void setZonaList2(List zonaList2) {
		this.zonaList2 = zonaList2;
	}

	
	
}
