package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * 
 * <p>
 * <a href="MantenimientoRECIngresoAtencionesForm.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * 
 */
public class MantenimientoRECIngresoMasivoOperacionesForm extends BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodoProceso;
	private String codigoOperacion;
	private boolean sFlagIndExpress;	
	private UploadedFile archivo;
	private String[] listaNumeroUnidades;
	private String[] selectedItems2;	
	private String mostrarPanel;	
	private String origen;
	private String codigoMotivo;
		
	
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}


	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodigoPeriodoProceso() {
		return codigoPeriodoProceso;
	}


	public void setCodigoPeriodoProceso(String codigoPeriodoProceso) {
		this.codigoPeriodoProceso = codigoPeriodoProceso;
	}
	
	public String getCodigoOperacion() {
		return codigoOperacion;
	}


	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}


	public String[] getListaNumeroUnidades() {
		return listaNumeroUnidades;
	}


	public void setListaNumeroUnidades(String[] listaNumeroUnidades) {
		this.listaNumeroUnidades = listaNumeroUnidades;
	}


	public String[] getSelectedItems2() {
		return selectedItems2;
	}


	public void setSelectedItems2(String[] selectedItems2) {
		this.selectedItems2 = selectedItems2;
	}


	public String getMostrarPanel() {
		return mostrarPanel;
	}


	public void setMostrarPanel(String mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}


	public boolean issFlagIndExpress() {
		return sFlagIndExpress;
	}


	public void setsFlagIndExpress(boolean sFlagIndExpress) {
		this.sFlagIndExpress = sFlagIndExpress;
	}


	public UploadedFile getArchivo() {
		return archivo;
	}


	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	
}
