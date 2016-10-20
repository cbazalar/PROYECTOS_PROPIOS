/**
 * 
 */
package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazIMPGeneracionDocumentosNumeroInternoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 * 
 */
public class InterfazIMPGeneracionDocumentosNumeroInternoForm extends BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fechaFacturacion;
	private String tipoProceso;
	private String serieDocumento;
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	/**
	 * @return the serieDocumento
	 */
	public String getSerieDocumento() {
		return serieDocumento;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @param tipoProceso the tipoProceso to set
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * @param serieDocumento the serieDocumento to set
	 */
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}
	public UploadedFile getArchivo() {
		return archivo;
	}
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	
}
