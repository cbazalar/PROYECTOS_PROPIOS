package biz.belcorp.ssicc.web.spusicc.emprendedoras.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.ProcesoEMPCargarPreEmprendedorasService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.emprendedoras.form.ProcesoEMPCargarPreEmprendedorasForm;

@ManagedBean
@SessionScoped
public class ProcesoEMPCargarPreEmprendedorasAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List empProgramasList = new ArrayList();
	private List cccErroresCargaMasivaList = new ArrayList();
	private DataTableModel datatableResult = new DataTableModel();
	private String attachment = "";

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoEMPCargarPreEmprendedorasForm f = (ProcesoEMPCargarPreEmprendedorasForm)formProceso;
		f.setFlagMostrarErrores(false);
		
		ProcesoEMPCargarPreEmprendedorasService service = (ProcesoEMPCargarPreEmprendedorasService) getBean("spusicc.procesoEMPCargarPreEmprendedorasService");
				
		this.setEmpProgramasList(service.getProgramas());
		
		//Obtiene la campaña de proceso actual
		Map criteria = new HashMap();
	    criteria.put("codigoPais", f.getCodigoPais());
	    criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
	    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
	    PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
	    String periodoActual = controlFacturacion.getCodigoPeriodo();	    
		criteria.put("codigoParam", Constants.EMP_CARGA_RUTA);
		
		f.setDirectorioTemporal(service.obtenerPathUpload(criteria));	    
	    f.setCodigoPeriodo(periodoActual);
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoEMPCargarPreEmprendedorasForm f = (ProcesoEMPCargarPreEmprendedorasForm) formProceso;
		String error = validar();
		if (error.equals("0")){
			
			if (log.isDebugEnabled()) {
				log.debug("JFA Entering 'procesar' method");
			}
			
			ProcesoEMPCargarPreEmprendedorasService service = (ProcesoEMPCargarPreEmprendedorasService) getBean("spusicc.procesoEMPCargarPreEmprendedorasService");
			Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();			
			params.put("codigoPrograma",f.getCodigoPrograma());
			params.put("codigoPeriodo",f.getCodigoPeriodo());
			params.put("codigoUsuario",usuario.getLogin());        
	        								
			service.executeProcesarPreEmprendedoras(params);		
			this.addInfo("Info:", this.getResourceMessage("procesoEMPCargaPreEmprendedorasUpload.msg.procesa.ok"));
		}
		return params;
	}
	
	/**
	 * Metodo que carga el archivo
	 * @param form
	 * @throws Exception
	 */
	private void upload(FileUploadEvent event)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("upload");
		}
				
		ProcesoEMPCargarPreEmprendedorasForm f = (ProcesoEMPCargarPreEmprendedorasForm) formProceso;
		if(event != null){
			f.setArchivo(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			uploadArchivo();
		}
	}
	
	/**
	 * Metodo que obtiene la extension del archivo
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
	throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}	

	/**
	 * Metodo que guarda el archivo en el servidor
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivo() throws Exception {
		ProcesoEMPCargarPreEmprendedorasForm f = (ProcesoEMPCargarPreEmprendedorasForm) formProceso;

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();

		f.setNombreArchivo(archivo.getFileName());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();

		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente		
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
								f.getNombreArchivo()));

		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();

		//f.setArchivo(null);
	}
	
	/**
	 * Metodo de valida el archivo
	 * @return
	 * @throws Exception
	 */
	private String validar()
			throws Exception {	
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'validar' method");
		}
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoEMPCargarPreEmprendedorasForm f = (ProcesoEMPCargarPreEmprendedorasForm) formProceso;
		
		
		String strMessage = null;	
		String error = null;
		
		try {

			File file = new File(f.getDirectorioTemporal(), f.getNombreArchivo());
			
			if (file.exists()){

				Map datos = new HashMap();
				datos.put("directorioTemporal", f.getDirectorioTemporal());
				datos.put("nombreArchivo", f.getNombreArchivo());
				datos.put("extensionArchivo", f.getExtensionArchivo());
				datos.put("codigoPrograma", f.getCodigoPrograma());
				Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();				
				datos.put("codigoUsuario", usuario.getLogin());							
																																											
				ProcesoEMPCargarPreEmprendedorasService service = (ProcesoEMPCargarPreEmprendedorasService) getBean("spusicc.procesoEMPCargarPreEmprendedorasService");
				
				service.deleteTablasCargaPreEmprendedoras();							
				service.executeValidarPreEmprendedoras(datos);
												
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				
				error = (String) datos.get("error");				
				
				if (error.equals("0")){
					strMessage = "procesoEMPCargaPreEmprendedorasUpload.msg.CargaDatos.ok";
					f.setCantidadRegistrosCargados((String) datos.get("cantidadRegistrosCargados"));
									
				}else{
					f.setFlagMostrarErrores(true);
										
					List list = service.getErroresCargaPreEmprendedoras();
					cccErroresCargaMasivaList = list;				
					datatableResult = new DataTableModel(cccErroresCargaMasivaList);
					strMessage = "procesoEMPCargaPreEmprendedorasUpload.msg.CargaDatos.error";
				}
				
				this.addInfo("Info:", strMessage);
			}
		}catch (Exception ex) {			
			strMessage = ex.getMessage();
			if (StringUtils.isBlank(strMessage))
				strMessage = ex.getLocalizedMessage();
			this.addError("Error:", this.getResourceMessage("errors.detail"));			
		}
		return error;		
	}
	
	/**
	 *Metodo que borra el archivo
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo");
		}
	}
	
	/**
	 * Metodo que procesa
	 * @throws Exception
	 */
	public void procesar(ActionEvent e)throws Exception {
		if(log.isDebugEnabled()){
			log.debug("procesar");
		}
		
		
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoEMPCargarPreEmprendedorasForm form = new ProcesoEMPCargarPreEmprendedorasForm();
		return form;
	}

	public List getEmpProgramasList() {
		return empProgramasList;
	}

	public void setEmpProgramasList(List empProgramasList) {
		this.empProgramasList = empProgramasList;
	}

	public List getCccErroresCargaMasivaList() {
		return cccErroresCargaMasivaList;
	}

	public void setCccErroresCargaMasivaList(List cccErroresCargaMasivaList) {
		this.cccErroresCargaMasivaList = cccErroresCargaMasivaList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public DataTableModel getDatatableResult() {
		return datatableResult;
	}

	public void setDatatableResult(DataTableModel datatableResult) {
		this.datatableResult = datatableResult;
	}


}
