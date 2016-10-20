/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


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
public class InterfazRECProductosReclamadosForm extends BaseInterfazForm
    implements Serializable {
    /**
     * Propiedad que contiene los ids seleccionados, en caso de un listado con
     * seleccion multiple
     */
    protected String[] selectedItems = {  };
    private String subAcceso;
    private String tipoIngreso;
    private Date fechaInicio;
    private Date fechaFin;
    private String operacion;
    private String region;
    private String[] regiones;
    private String[] zonas;
    private String[] regionListSearch;
	private String[] zonaListSearch;
    private String[] subAccesos;
    private String opcion;
    
	protected String formatoExportacion; // para el Reporte
	
	protected String tipoReporte; // para el Cabecera o Detalle	
	

	private String codigoProcesoBatch;


	public String[] getSelectedItems() {
		return selectedItems;
	}


	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}


	public String getSubAcceso() {
		return subAcceso;
	}


	public void setSubAcceso(String subAcceso) {
		this.subAcceso = subAcceso;
	}


	public String getTipoIngreso() {
		return tipoIngreso;
	}


	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getOperacion() {
		return operacion;
	}


	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String[] getRegiones() {
		return regiones;
	}


	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}


	public String[] getZonas() {
		return zonas;
	}


	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}


	public String[] getRegionListSearch() {
		return regionListSearch;
	}


	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}


	public String[] getZonaListSearch() {
		return zonaListSearch;
	}


	public void setZonaListSearch(String[] zonaListSearch) {
		this.zonaListSearch = zonaListSearch;
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


	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}


	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

      
}
