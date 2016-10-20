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

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm;

/**
 * @author Sigcomt
 *
 */

@ManagedBean
@SessionScoped
public class ProcesoPEDCargaCUVRecuperarSiguienteSemanaAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3488981897090480734L;

	private String attachment;
	private List pedArchivoList = new ArrayList();
	private boolean viewValida;
	private String mensajeProcesar;
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm form = new ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
		ProcesoPEDCargaCUVRecuperarSiguienteSemanaService service = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaService)getBean("spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaService");
		List resultados = (List)this.pedArchivoList;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params.put("usuario", usuario);
		params.put("resultados", resultados);
		
		service.executeActualizarCargaCUVRecuperarSiguienteSemana(params);
		return params;	
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#afterExecuteProcessSuccess()
	 */
	@Override
	protected void afterExecuteProcessSuccess() {

		String mensaje = null;
		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		mensaje = "procesoMAECargaClasificacionClienteForm.proceso.ok";
		this.addInfo("Info: ", this.getResourceMessage(mensaje));		
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		log.debug("Enter method - setViewAtributes");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
		
		//seteamos la ruta temporal donde guardar el TXT
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
				
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);	
		
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);        
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());		
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso = false;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		
		//limpiando el flag de validacion de archivo
		this.viewValida = false;
	
	}
	
	public void cargar(FileUploadEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		
		try {
			
			Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();

			ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
			//Obtenemos datos del archivo
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			this.attachment = f.getNombreArchivo();
			// Cargamos el archivo de la maquina del cliente al servidor
			this.uploadArchivo();
			
			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
			
			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);

			this.mostrarPanelAdicionalProceso = true;
			this.viewValida = true;//flag para mostrar el resultado de la validacion
			
			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);
			f.setNumeroLote("");
			this.validar();
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			this.mostrarPanelAdicionalProceso = true;
			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	private void uploadArchivo() throws Exception {
		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);
	}


	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}


	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	public void validar()
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
		
		Map params = BeanUtils.describe(f);
		params.put("usuario", usuario);
		
		//obtenemos el service
		ProcesoPEDCargaCUVRecuperarSiguienteSemanaService service = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaService) getBean("spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaService");

		//validamos los datos cargados del archivo excel
		Map mapresultados = service.executeValidarCargaCUVRecuperarSiguienteSemana(params);
		List resultados = (ArrayList) mapresultados.get("listResultado");
		String totalRegistros = (String) mapresultados.get("totalRegistros");
		String totalErrores = (String) mapresultados.get("totalErrores");
		String totalValidos = (String) mapresultados.get("totalValidos");
		f.setNumRegistros(String.valueOf(totalRegistros));
		f.setNumRegistrosError(String.valueOf(totalErrores));
		f.setNumRegistrosValido(String.valueOf(totalValidos));

		this.pedArchivoList = resultados;		
		
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(true);
	}
	
	
	public void cuvUnitario(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarCUV' method");
		}

		try {
			Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();

			ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
			String rellenoCeros="";
			if(f.getCodigoCUV().length() <5 ){
				for (int i = f.getCodigoCUV().length(); i < 5; i++) {
					rellenoCeros = rellenoCeros + "0";
				}
				String newCuv = rellenoCeros + f.getCodigoCUV();
				f.setCodigoCUV(newCuv);
			}
			
			this.pedArchivoList = new ArrayList();
			this.mostrarPanelAdicionalProceso = true;
			this.viewValida = true;//flag para mostrar el resultado de la validacion
			
			Map params = BeanUtils.describe(f);
			params.put("usuario", usuario);
			
			//obtenemos el service
			ProcesoPEDCargaCUVRecuperarSiguienteSemanaService service = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaService) getBean("spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaService");

			//validamos los datos cargados del formulario
			Map mapresultados = service.executeValidarCargaCUVUnitarioRecuperarSiguienteSemana(params);
			List resultados = (ArrayList) mapresultados.get("listResultado");
			String totalRegistros = (String) mapresultados.get("totalRegistros");
			String totalErrores = (String) mapresultados.get("totalErrores");
			String totalValidos = (String) mapresultados.get("totalValidos");
			f.setNumRegistros(String.valueOf(totalRegistros));
			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));

			this.pedArchivoList = resultados;
			
			f.setFlagBotonValidar(false);
			f.setFlagBotonActualizar(true);
						
		} catch (Exception e) {
			
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;

		if(accion.equals("MENSAJE")){			
			ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm f = (ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm) this.formProceso;
			if(Integer.parseInt(f.getNumRegistrosError())>0){
				this.mensajeProcesar = this.getResourceMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.process.valido.errores");
				
			}else{
				this.mensajeProcesar = this.getResourceMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.process.valido");
			}						
		}
		return mensaje;
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
	 * @return the pedArchivoList
	 */
	public List getPedArchivoList() {
		return pedArchivoList;
	}

	/**
	 * @param pedArchivoList the pedArchivoList to set
	 */
	public void setPedArchivoList(List pedArchivoList) {
		this.pedArchivoList = pedArchivoList;
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
	 * @return the mensajeProcesar
	 */
	public String getMensajeProcesar() {
		return mensajeProcesar;
	}

	/**
	 * @param mensajeProcesar the mensajeProcesar to set
	 */
	public void setMensajeProcesar(String mensajeProcesar) {
		this.mensajeProcesar = mensajeProcesar;
	}
	
	
}
