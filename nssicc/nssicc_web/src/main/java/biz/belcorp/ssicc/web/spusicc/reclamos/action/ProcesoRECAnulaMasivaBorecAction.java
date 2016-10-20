/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

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

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCierreBRService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECAnulaMasivaBorecForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoRECAnulaMasivaBorecAction extends BaseProcesoAbstractAction{
	
	private List recAnulaMasivaBoletaRecojoList = new ArrayList();
	private Integer recListaAnulaMasivaBoletaRecojoCantidad = 0 ;
	private Integer recListaAnulaMasivaBoletaCantidadIncorrectas = 0 ;
	private Integer recListaAnulaMasivaBoletaRecojoCantidadCorrectas = 0 ;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRECAnulaMasivaBorecForm form = new ProcesoRECAnulaMasivaBorecForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoRECProductosFFNNEEService serviceFFNNEE = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");
		ProcesoRECAnulaMasivaBorecForm f = (ProcesoRECAnulaMasivaBorecForm) this.formProceso;
		
		
		f.setUsuario(usuario.getLogin());
		
		//Borramos la tabla temporal
		service.deleteTablaTemporal();
		
		//Obtenemos el directorio temporal
		f.setCodigoPais(pais.getCodigo());
		f.setDirectorioTemporal(serviceFFNNEE.obtenerPathUpload(pais.getCodigo()));
		log.debug(">>>>  "+f.getDirectorioTemporal());
		
		f.setFlagVerificar(false);
		f.setFlagProcesar(false);
		f.setFlagLimpiar(false);
		f.setFlagRetornar(true);
		
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso = false;
				
	}
	
	public void validar(FileUploadEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		
		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");
		ProcesoRECAnulaMasivaBorecForm f = (ProcesoRECAnulaMasivaBorecForm) this.formProceso;
		log.debug("tmp  "+f.getDirectorioTemporal());
		
		f.setArchivo(event.getFile());
		f.setNombreArchivo(event.getFile().getFileName());
		
		try {			

			//Obtenemos los numeros de boletas del archivo excel
		    if(f.getArchivo()!=null){
		    	
	    	uploadArchivo();
	    	log.debug("f.getNombreArchivo() "+f.getNombreArchivo());
    		String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
    		f.setExtensionArchivo(extensionArchivo);
    		
    	    List listCamposArchivo = leerArchivoExcel(f.getDirectorioTemporal(), f.getNombreArchivo());
    	    
    	    Map criteria = new HashMap();
    	    criteria.put("listCamposArchivo", listCamposArchivo);
    	    
    	    //Dirigimos el control al service debido a la operaciones de sesión de una tabla temporal gtt
    	    service.executeValidaRelacionBoletaRecojo(criteria);
    	    
    	    //Obtenemos los datos cargados en la tabla temporal
    	    List listTablaTemporal = (List)criteria.get("listTablaTemporal");
    	    this.recAnulaMasivaBoletaRecojoList = listTablaTemporal;
    	    
    	    //Obtenemos el total de boletas, total correctas y total de incorrectas
    	    Integer totalBoletas = (Integer)criteria.get("totalBoletas");
    	    Integer totalIncorrectas = (Integer)criteria.get("totalIncorrectas");
    	    Integer totalCorrectas = (Integer)criteria.get("totalCorrectas");
    	    
    	    this.recListaAnulaMasivaBoletaRecojoCantidad = totalBoletas;
    	    this.recListaAnulaMasivaBoletaCantidadIncorrectas = totalIncorrectas;
    	    this.recListaAnulaMasivaBoletaRecojoCantidadCorrectas = totalCorrectas;
    	    
    	    f.setFlagVerificar(true);
			f.setFlagProcesar(true);
			f.setFlagLimpiar(true);
			f.setFlagRetornar(true);
			
			this.mostrarPanelAdicionalProceso = true;
		    }
		    
		
		} catch (Exception e) {
			
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
		
		}		
//		return mapping.findForward("view");
	}
	
	private void uploadArchivo() throws Exception {
		ProcesoRECAnulaMasivaBorecForm f = (ProcesoRECAnulaMasivaBorecForm) this.formProceso;

		//Recuperamos el fichero
		UploadedFile archivo = f.getArchivo();
		
		//Obtenemos el nombre del archivo
		f.setNombreArchivo(archivo.getFileName());
		
		//Leyemos el stream de entrada
		InputStream is = archivo.getInputstream();

		//Abrimos el stream de escritura, ubicacion al cual se grabara el archivo del cliente
		OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));

		//Grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();

		f.setArchivo(null);
	}
	
	private String obtenerExtensionArchivo(String nombreArchivo) throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}
	
	private List leerArchivoExcel(String directorioTemp, String nombreArchivo) throws Exception {
		ExcelUtil excelUtil = new ExcelUtil(directorioTemp, nombreArchivo);
		excelUtil.initSheet(0);
		int fila = 0;
		
		List listArchivo = new ArrayList();
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			if(fila > 0){
				Map map = new HashMap();
				map.put("numeroBR", (String)mapRow.get("0"));
				map.put("mensaje", "");
				map.put("nroRecojo", "");
				map.put("codigoCliente", "");
				map.put("nombreCliente", "");
				listArchivo.add(map);
			}
			
			fila++;
		}
		
		excelUtil.cerrar();

		return listArchivo;
	}
	
	
	public void procesar(ActionEvent event){
		
		log.debug("Enter method - procesar");
		
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		ProcesoRECAnulaMasivaBorecForm f = (ProcesoRECAnulaMasivaBorecForm) this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		try {
			Map datos = new HashMap();
			Integer resultado;
			String numeroLote="", mensaje = "";
			datos = ajax.executeProcesoAnulacionBR1(f.getUsuario(), this.recAnulaMasivaBoletaRecojoList, this.recListaAnulaMasivaBoletaCantidadIncorrectas);
			numeroLote = (String) datos.get("numeroLote");
			resultado = (Integer) datos.get("resultado");
			f.setNumeroLote(numeroLote);
			switch(resultado){
			case 1:
				mensaje = "procesoRECAnulaMasivaBorecForm.msg.proceso.error";
				this.addError("Error: ", this.getResourceMessage(mensaje));
		        break;
			case 2:
				f.setFlagVerificar(false);
				f.setFlagProcesar(false);
				f.setFlagRetornar(false);
				f.setFlagLimpiar(true);		
				mensaje = "procesoRECAnulaMasivaBorecForm.msg.proceso.ok";	
				this.addInfo("Info: ", this.getResourceMessage(mensaje));
				break;
			case 3:
				mensaje = "procesoRECAnulaMasivaBorecForm.error.noExisteRegistro";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				break;
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		
	}

	/**
	 * @return the recAnulaMasivaBoletaRecojoList
	 */
	public List getRecAnulaMasivaBoletaRecojoList() {
		return recAnulaMasivaBoletaRecojoList;
	}

	/**
	 * @param recAnulaMasivaBoletaRecojoList the recAnulaMasivaBoletaRecojoList to set
	 */
	public void setRecAnulaMasivaBoletaRecojoList(
			List recAnulaMasivaBoletaRecojoList) {
		this.recAnulaMasivaBoletaRecojoList = recAnulaMasivaBoletaRecojoList;
	}

	/**
	 * @return the recListaAnulaMasivaBoletaRecojoCantidad
	 */
	public Integer getRecListaAnulaMasivaBoletaRecojoCantidad() {
		return recListaAnulaMasivaBoletaRecojoCantidad;
	}

	/**
	 * @param recListaAnulaMasivaBoletaRecojoCantidad the recListaAnulaMasivaBoletaRecojoCantidad to set
	 */
	public void setRecListaAnulaMasivaBoletaRecojoCantidad(
			Integer recListaAnulaMasivaBoletaRecojoCantidad) {
		this.recListaAnulaMasivaBoletaRecojoCantidad = recListaAnulaMasivaBoletaRecojoCantidad;
	}

	/**
	 * @return the recListaAnulaMasivaBoletaCantidadIncorrectas
	 */
	public Integer getRecListaAnulaMasivaBoletaCantidadIncorrectas() {
		return recListaAnulaMasivaBoletaCantidadIncorrectas;
	}

	/**
	 * @param recListaAnulaMasivaBoletaCantidadIncorrectas the recListaAnulaMasivaBoletaCantidadIncorrectas to set
	 */
	public void setRecListaAnulaMasivaBoletaCantidadIncorrectas(
			Integer recListaAnulaMasivaBoletaCantidadIncorrectas) {
		this.recListaAnulaMasivaBoletaCantidadIncorrectas = recListaAnulaMasivaBoletaCantidadIncorrectas;
	}

	/**
	 * @return the recListaAnulaMasivaBoletaRecojoCantidadCorrectas
	 */
	public Integer getRecListaAnulaMasivaBoletaRecojoCantidadCorrectas() {
		return recListaAnulaMasivaBoletaRecojoCantidadCorrectas;
	}

	/**
	 * @param recListaAnulaMasivaBoletaRecojoCantidadCorrectas the recListaAnulaMasivaBoletaRecojoCantidadCorrectas to set
	 */
	public void setRecListaAnulaMasivaBoletaRecojoCantidadCorrectas(
			Integer recListaAnulaMasivaBoletaRecojoCantidadCorrectas) {
		this.recListaAnulaMasivaBoletaRecojoCantidadCorrectas = recListaAnulaMasivaBoletaRecojoCantidadCorrectas;
	}

	
	
	
}
