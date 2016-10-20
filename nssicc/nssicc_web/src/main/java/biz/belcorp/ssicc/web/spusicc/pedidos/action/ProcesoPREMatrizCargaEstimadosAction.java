/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.aspectj.weaver.bcel.AtAjAttributes;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREMatrizCargaEstimadosService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPREMatrizCargaEstimadosForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoPREMatrizCargaEstimadosAction extends BaseProcesoAbstractAction{

	private List cargaEstimadosArchivolist = new ArrayList();
	private boolean viewValida;
	private String attachment;
	private String msjDialog;
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {

		ProcesoPREMatrizCargaEstimadosForm form = new ProcesoPREMatrizCargaEstimadosForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled())
        {
            log.debug("Entering 'setViewAtributes' method");
        }
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
        ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService)getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
        f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
        f.setFlagBotonValidar(false);
        f.setFlagBotonActualizar(false);
        this.mostrarBotonExecute = false;
        this.viewValida = false;
        this.mostrarPanelAdicionalProceso= false; 
        this.cargaEstimadosArchivolist = null;		
        
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoPREMatrizCargaEstimadosService service = (ProcesoPREMatrizCargaEstimadosService)getBean("spusicc.procesoPREMatrizCargaEstimadosService");
        params.put("codigoPais", f.getCodigoPais());
        params.put("codigoUsuario", usuario.getLogin());
        params.put("nombreArchivo", f.getNombreArchivo());
        service.executeActualizarCargaEstimados(params);
        f.setFlagBotonValidar(true);
        f.setFlagBotonActualizar(false);
        return params;
	}

	
	public void cargar (FileUploadEvent event){
		
		 if(log.isDebugEnabled())
		    {
		        log.debug("Entering 'cargar' method");
		    }
		try {
			ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoPREMatrizCargaEstimadosService service = (ProcesoPREMatrizCargaEstimadosService)getBean("spusicc.procesoPREMatrizCargaEstimadosService");
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());	
			this.attachment =f.getNombreArchivo(); 
		    uploadArchivo();
		    String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
		    f.setExtensionArchivo(extensionArchivo);
		    Map criteria = new HashMap();
		    criteria.put("directorioTemporal", f.getDirectorioTemporal());
		    criteria.put("nombreArchivo", f.getNombreArchivo());
		    criteria.put("usuario", usuario);
		    Map resultados = service.cargarArchivoExcel(criteria);
		    f.setNumRegistros((String)resultados.get("totalRegistros"));
		    f.setNumRegistrosError("N");
		    f.setNumRegistrosValido("N");
		    //sesion.setAttribute("cargaEstimadosArchivolist", new ArrayList());
		    borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		    //sesion.setAttribute("viewValida", "1");		    
		    f.setFlagBotonValidar(true);
		    f.setFlagBotonActualizar(false);
		    this.mostrarPanelAdicionalProceso= true; 
		    this.viewValida = true;
		    //return mapping.findForward(getViewForward());			
		} catch (Exception e) {
			
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception
    {
        return nombreArchivo.substring(nombreArchivo.length() - 3);
    }

	
	private void borrarFichero(String path, String nombreArchivo)
    {
        try
        {
        	File file = new File(path, nombreArchivo);
            file.delete();
            log.debug("Se eliminó el archivo");
        }
        catch(Exception ex)
        {
            log.debug((new StringBuilder()).append("No se pudo eliminar el archivo ").append(ex.getMessage()).toString());
        }
    }
	
	private void uploadArchivo() throws Exception
    {	       
		ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
		UploadedFile archivo = f.getArchivo();
        log.debug((new StringBuilder()).append("Nombre Archivo Upload: ").append(f.getNombreArchivo()).toString());
        InputStream is = archivo.getInputstream();
        OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
        int bytesRead = 0;
        byte buffer[] = new byte[1024];
        while((bytesRead = is.read(buffer, 0, 1024)) != -1) 
        {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        f.setArchivo(null);
    }
	
	public void validar (ActionEvent event) {
		
		if(log.isDebugEnabled())
        {
            log.debug("Entering 'validar' method");
        }
		
		try {
			
			ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String codigoUsuario = usuario.getLogin();
	        Map params = new HashMap();
	        params.put("codigoUsuario", codigoUsuario);
	        ProcesoPREMatrizCargaEstimadosService service = (ProcesoPREMatrizCargaEstimadosService)getBean("spusicc.procesoPREMatrizCargaEstimadosService");
	        List resultados = service.executeValidarCargaEstimados(params);
	        int totalErrores = resultados.size();
	        int totalValidos = Integer.parseInt(f.getNumRegistros()) - totalErrores;
	        f.setNumRegistrosError(String.valueOf(totalErrores));
	        f.setNumRegistrosValido(String.valueOf(totalValidos));	        
	        this.cargaEstimadosArchivolist = resultados;
	        f.setFlagBotonValidar(false);
	        f.setFlagBotonActualizar(true);
	        //this.mostrarBotonExecute = true ;
	        this.viewValida = true;
	        this.mostrarPanelAdicionalProceso= true; 
	        //return mapping.findForward(getViewForward());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String msj = null;
		
		ProcesoPREMatrizCargaEstimadosForm f = (ProcesoPREMatrizCargaEstimadosForm)this.formProceso;
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			this.msjDialog = this.getResourceMessage("procesoPREMatrizCargaEstimadosForm.process.valido.errores");
			
		}
		else{
			this.msjDialog = this.getResourceMessage("procesoPREMatrizCargaEstimadosForm.process.valido");
		}
		
		return msj;
	}

	/**
	 * @return the cargaEstimadosArchivolist
	 */
	public List getCargaEstimadosArchivolist() {
		return cargaEstimadosArchivolist;
	}

	/**
	 * @param cargaEstimadosArchivolist the cargaEstimadosArchivolist to set
	 */
	public void setCargaEstimadosArchivolist(List cargaEstimadosArchivolist) {
		this.cargaEstimadosArchivolist = cargaEstimadosArchivolist;
	}

	/**
	 * @return the viewValida
	 */
	public boolean isViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the msjDialog
	 */
	public String getMsjDialog() {
		return msjDialog;
	}

	/**
	 * @param msjDialog the msjDialog to set
	 */
	public void setMsjDialog(String msjDialog) {
		this.msjDialog = msjDialog;
	}
	
}






