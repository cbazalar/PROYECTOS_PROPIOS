/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.mae.action;

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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ConsultoraMasivo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.DireccionTelefonoMasivo;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaInformacionMasivoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.ProcesoMAECargaInformacionMasivoForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoMAECargaMasivaInformacionAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -8308147099901021561L;
	
	
	private List maeListaDireccionesTelefono = new ArrayList();
	private int procesoMAECargaInformacionMasivoForm_totalRegistros = 0;
	private String procesoMAECargaInformacionMasivoForm_archivo = null;
	private String procesoMAECargaInformacionMasivoForm_vista = null;
	private int procesoMAECargaInformacionMasivoForm_registrosValidos = 0;
	private int procesoMAECargaInformacionMasivoForm_registrosError = 0;
	private List maeRegistrosValidosList = new ArrayList();
	private List maeRegistrosConErrorList = new ArrayList();
	private List maeDireccionesTelefonoRegistradasAlProgramaList = new ArrayList();
	private String attachment = null;
	
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoMAECargaInformacionMasivoForm form = new ProcesoMAECargaInformacionMasivoForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarBotonExecute = false;
		this.mostrarPanelAdicionalProceso = false;
//		
//		session.removeAttribute(Constants.COM_REGISTROS_VALIDOS_LIST);
//		session.removeAttribute(Constants.COM_REGISTROS_CON_ERROR_LIST);
//		session.removeAttribute(Constants.COM_CONSULTORAS_REGISTRADAS_AL_PROGRAMA_LIST);

		
		ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService service= (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setTipoCarga(Constants.NUMERO_UNO);
		
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));		
			
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcess' method");
		}
		ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;
		borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());			
		return params;
	}
	
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se eliminó el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo");
		}
	}
	
	public void cargar(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("ProcesoMAECargaInformacionMasivoAction - cargar");
		}
		
		try {
			
			ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;
			//obtenemos nombre de archivo y archivo
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());
			this.attachment = event.getFile().getFileName();
			
			uploadArchivo();
			
			ExcelUtil excelUtil = new ExcelUtil(f.getDirectorioTemporal(), f.getNombreArchivo());		
			
			excelUtil.initSheet(0);
			
			List listaDireccionesTelefonos = new ArrayList();
			String tmpcodigo="";	
			if(f.getTipoCarga().compareTo(Constants.NUMERO_UNO)==0){ // direcciones
			
				String filaExcel =  "";
				String codigoCliente = "";
				String direccionDomicilio = "";
				String direccionEntrega = "";
				String direccionVacaciones = "";
				String referenciaDomicilio = "";
				String referenciaEntrega = "";
				String referenciaVacaciones = "";
					
				while(excelUtil.hasNext()) {
					Map mapRow = excelUtil.next();
					DireccionTelefonoMasivo bean = new DireccionTelefonoMasivo();
					filaExcel = (String)mapRow.get("rowNum");
					codigoCliente = (String)mapRow.get("0");		
					direccionDomicilio = (String)mapRow.get("1");	
					direccionEntrega = (String)mapRow.get("2");	
					direccionVacaciones = (String)mapRow.get("3");	
					referenciaDomicilio = (String)mapRow.get("4");	
					referenciaEntrega = (String)mapRow.get("5");	
					referenciaVacaciones = (String)mapRow.get("6");		
					if(StringUtils.isNotBlank(codigoCliente)){
						codigoCliente=codigoCliente.trim();
						tmpcodigo= codigoCliente.substring(codigoCliente.length()-2, codigoCliente.length());
						 if(tmpcodigo.compareTo(".0")==0)
							 codigoCliente=codigoCliente.substring(0, codigoCliente.length()-2);
						}
				    if(StringUtils.isNotBlank(codigoCliente)){
				    	bean.setFila(filaExcel);
				    	bean.setCodigoConsultora(codigoCliente);
				    	if(StringUtils.isNotBlank(direccionDomicilio))
				    	   bean.setDireccionDomicilio(direccionDomicilio.trim());
				    	if(StringUtils.isNotBlank(direccionEntrega))
				    	   bean.setDireccionEntrega(direccionEntrega.trim());
				    	if(StringUtils.isNotBlank(direccionVacaciones))
				    	   bean.setDireccionVacaciones(direccionVacaciones.trim());
				    	if(StringUtils.isNotBlank(referenciaDomicilio))
				    	   bean.setReferenciaDomicilio(referenciaDomicilio.trim());
				    	if(StringUtils.isNotBlank(referenciaEntrega))
				    	   bean.setReferenciaEntrega(referenciaEntrega.trim());
				    	if(StringUtils.isNotBlank(referenciaVacaciones))
				    	   bean.setReferenciaVacaciones(referenciaVacaciones.trim());
				    	
				    	listaDireccionesTelefonos.add(bean);
					  }
				}
			}
			if(f.getTipoCarga().compareTo(Constants.NUMERO_DOS)==0){ // telefonos
				
				String filaExcel =  "";
				String codigoCliente = "";
				String telefonoCasa = "";
				String telefonoCelular = "";
				String telefonoTrabajo = "";
				String email = "";		
				String tmpcasa= "";	
				String tmpcelular="";	
				String tmptrabajo="";			
				while(excelUtil.hasNext()) {
					Map mapRow = excelUtil.next();
					DireccionTelefonoMasivo bean = new DireccionTelefonoMasivo();			
					filaExcel = (String)mapRow.get("rowNum");
					codigoCliente = (String)mapRow.get("0");		
					telefonoCasa = (String)mapRow.get("1");	
					telefonoCelular = (String)mapRow.get("2");	
					telefonoTrabajo = (String)mapRow.get("3");	
					email = (String)mapRow.get("4");
					if(StringUtils.isNotBlank(codigoCliente)){
						tmpcodigo= codigoCliente.trim().substring(codigoCliente.length()-2, codigoCliente.length());
						 if(tmpcodigo.compareTo(".0")==0)
							 codigoCliente=codigoCliente.substring(0, codigoCliente.length()-2);
						}
					if(StringUtils.isNotBlank(telefonoCasa)){
					  telefonoCasa=telefonoCasa.trim();
					  tmpcasa= telefonoCasa.substring(telefonoCasa.length()-2, telefonoCasa.length());
					  if(tmpcasa.compareTo(".0")==0)
					   	telefonoCasa=telefonoCasa.substring(0, telefonoCasa.length()-2);
					}
					if(StringUtils.isNotBlank(telefonoCelular)){
						telefonoCelular= telefonoCelular.trim();
					 tmpcelular= telefonoCelular.substring(telefonoCelular.length()-2, telefonoCelular.length());
					 if(tmpcelular.compareTo(".0")==0)
					    	telefonoCelular=telefonoCelular.substring(0, telefonoCelular.length()-2);
					}
					if(StringUtils.isNotBlank(telefonoTrabajo)){
					   telefonoTrabajo=telefonoTrabajo.trim();
					   tmptrabajo= telefonoTrabajo.trim().substring(telefonoTrabajo.length()-2, telefonoTrabajo.length());
				      if(tmptrabajo.compareTo(".0")==0)
				    	telefonoTrabajo=telefonoTrabajo.substring(0, telefonoTrabajo.length()-2);
					}
				    if(StringUtils.isNotBlank(codigoCliente)){
				    	bean.setFila(filaExcel);
				    	bean.setCodigoConsultora(codigoCliente);
				    	bean.setTelefonoCasa(telefonoCasa);
				    	bean.setTelefonoCelular(telefonoCelular);
				    	bean.setTelefonoTrabajo(telefonoTrabajo);
				    	if(StringUtils.isNotBlank(email))
				    	bean.setEmail(email.trim());		    	
				    	listaDireccionesTelefonos.add(bean);
					}
				}
			}
			excelUtil.cerrar();
			
			this.procesoMAECargaInformacionMasivoForm_totalRegistros = listaDireccionesTelefonos.size();
			this.procesoMAECargaInformacionMasivoForm_archivo =  f.getNombreArchivo();
			this.procesoMAECargaInformacionMasivoForm_vista =  Constants.NUMERO_UNO;
			this.maeListaDireccionesTelefono = listaDireccionesTelefonos;
			this.mostrarPanelAdicionalProceso = true;
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void uploadArchivo() throws Exception {
		ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;

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
	
	public void validar(ActionEvent event) {		
		if (log.isDebugEnabled()) {
			log.debug("ProcesoMAECargaInformacionMasivoAction - validar");
		}
		
		List listaRegistrosValidos = new ArrayList();
		List listaRegistrosConError = new ArrayList();
		try {
			ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;
			ProcesoMAECargaInformacionMasivoService service = (ProcesoMAECargaInformacionMasivoService) getBean("spusicc.procesoMAECargaInformacionMasivoService");
		
			Map criteria = new HashMap();		
		List lista = (List)this.maeListaDireccionesTelefono; 
		
		String valorProcedure= "";
		String existe=" ";
		String error="";
		int pivot=0;
		if (lista != null && lista.size() > 0) {

			for (int i = 0; i < lista.size(); i++) {
				DireccionTelefonoMasivo bean = new DireccionTelefonoMasivo();
				bean = (DireccionTelefonoMasivo)lista.get(i);
				criteria.put("codigoConsultora", bean.getCodigoConsultora());
				error="";
				if (StringUtils.isNotBlank(bean.getCodigoConsultora())) {
					String val = service.validarCliente(criteria);				
					if (val!=null) {	
						if(StringUtils.equals(f.getTipoCarga(), Constants.NUMERO_UNO)){
							if (StringUtils.isNotBlank(bean.getDireccionDomicilio()) || StringUtils.isNotBlank(bean.getDireccionEntrega()) || StringUtils.isNotBlank(bean.getDireccionVacaciones())
								|| StringUtils.isNotBlank(bean.getReferenciaDomicilio()) || StringUtils.isNotBlank(bean.getReferenciaEntrega()) || StringUtils.isNotBlank(bean.getReferenciaVacaciones())) {
								
								if (StringUtils.isNotBlank(bean.getDireccionDomicilio()) || StringUtils.isNotBlank(bean.getReferenciaDomicilio())) {
									criteria.put("tipoDireccion", "0"+Constants.NUMERO_UNO);
									existe=service.validarDireccion(criteria);
									pivot++;
									if(existe==null && StringUtils.isBlank(existe))
										error=Constants.MESSAGE_MAE_NO_EXISTE_REGISTRO_DIREC_DOMICILIO+",";									
									
									if(existenCaracteres(bean.getDireccionDomicilio()))
										error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";													

									if(StringUtils.isBlank(bean.getReferenciaDomicilio()))
										error = error + " " + Constants.MESSAGE_MAE_NO_EXISTE_REFER_DOMICILIO+",";									
									else{
										if(existenCaracteres(bean.getReferenciaDomicilio()))										
											error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";										
									}
								}
								
								if (StringUtils.isNotBlank(bean.getDireccionEntrega()) || StringUtils.isNotBlank(bean.getReferenciaEntrega())) {					
										criteria.put("tipoDireccion", "0"+Constants.NUMERO_SIETE);
										//Quitando la validacion para la direccion Tipo Entrega
								       // existe=service.validarDireccionEntrega(criteria);
										pivot++;
										if(StringUtils.isBlank(bean.getDireccionEntrega()))
											error = error + " " + Constants.MESSAGE_MAE_NO_EXISTE_DIREC_ENTREGA+","	;									
										else{
											if(existenCaracteres(bean.getDireccionEntrega()))											
												error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";											
										}
										
										if(StringUtils.isBlank(bean.getReferenciaEntrega()))
											error = error + " " + Constants.MESSAGE_MAE_NO_EXISTE_REFER_ENTREGA+",";										
										else{
											if(existenCaracteres(bean.getReferenciaEntrega()))											
												error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";											
										}										
								}
								
								if (StringUtils.isNotBlank(bean.getDireccionVacaciones()) || StringUtils.isNotBlank(bean.getReferenciaVacaciones())) {
										criteria.put("tipoDireccion", "0"+Constants.NUMERO_OCHO);
										existe=service.validarDireccion(criteria);
										pivot++;
										if(existe==null && StringUtils.isBlank(existe))
											error=error +" "+ Constants.MESSAGE_MAE_NO_EXISTE_REGISTRO_DIREC_VACACIONES+",";										
										
										if(existe!=null && StringUtils.isBlank(bean.getDireccionVacaciones()))
											error = error +" "+ Constants.MESSAGE_MAE_NO_EXISTE_DIREC_VACACIONES+",";										
										else{
											if(existenCaracteres(bean.getDireccionVacaciones()))											
												error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";											
										}
										
										if(existe!=null && StringUtils.isBlank(bean.getReferenciaVacaciones()))
											error = error +" "+ Constants.MESSAGE_MAE_NO_EXISTE_REFER_VACACIONES+",";										
										else{
											if(existenCaracteres(bean.getReferenciaVacaciones()))											
												error = error + " " + Constants.MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO+",";											
										}
								}								
								
								if(StringUtils.isNotBlank(error) && pivot > 0){
										
									    bean.setMotivoRechazo(error.substring(0, error.length()-1)+".");
										listaRegistrosConError.add(bean);
								}
								if(pivot == 0){
								   bean.setMotivoRechazo(Constants.MESSAGE_MAE_NO_EXISTE_DATOS);
								   listaRegistrosConError.add(bean);
								}  
								if(StringUtils.isBlank(error) && pivot > 0)
									listaRegistrosValidos.add(bean);								
							}else{
								bean.setMotivoRechazo(Constants.MESSAGE_MAE_NO_EXISTE_DATOS+".");
								listaRegistrosConError.add(bean);							
							}						
						}
						
						if(StringUtils.equals(f.getTipoCarga(),Constants.NUMERO_DOS)){
							if (StringUtils.isNotBlank(bean.getTelefonoCasa()) || StringUtils.isNotBlank(bean.getTelefonoCelular()) || StringUtils.isNotBlank(bean.getTelefonoTrabajo())
								|| StringUtils.isNotBlank(bean.getEmail())) {
								
								if(StringUtils.isNotBlank(bean.getTelefonoCasa()))
								     if(bean.getTelefonoCasa().trim().matches(".*\\D+.*"))
								    	 error =Constants.MESSAGE_MAE_NO_NUMERICO_TELE_CASA+",";
								if(StringUtils.isNotBlank(bean.getTelefonoCelular()))
								     if(bean.getTelefonoCelular().trim().matches(".*\\D+.*"))
								    	 error =error +" "+ Constants.MESSAGE_MAE_NO_NUMERICO_TELE_CELULAR+",";
								if(StringUtils.isNotBlank(bean.getTelefonoTrabajo()))
								     if(bean.getTelefonoTrabajo().trim().matches(".*\\D+.*"))
								    	 error =error +" "+ Constants.MESSAGE_MAE_NO_NUMERICO_TELE_TRABAJO+",";
								if(StringUtils.isNotBlank(bean.getEmail()))
								     if(!validateEmail(bean.getEmail()))
								    	 error =error +" "+Constants.MESSAGE_MAE_EMAIL_SIN_FORMATO+",";						
								
								if(StringUtils.isNotBlank(error)){
									bean.setMotivoRechazo(error.substring(0, error.length()-1)+".");
									listaRegistrosConError.add(bean);
							     }    
								if(StringUtils.isBlank(error))
									listaRegistrosValidos.add(bean); 
							}else{
								bean.setMotivoRechazo(Constants.MESSAGE_MAE_NO_EXISTE_DATOS+".");
								listaRegistrosConError.add(bean);							
							}						
						}										
					}else {
						bean.setMotivoRechazo(Constants.MESSAGE_MAE_NO_EXISTE_CONSULTORA+".");
						listaRegistrosConError.add(bean);
					}
				}else{
					String motivoRechazo = "procesoMAECargaInformacionMasivoForm.error.vacio.codigoCliente";
					bean.setMotivoRechazo(motivoRechazo);
					listaRegistrosConError.add(bean);
				}
			}
		}else		
			throw new InvalidIdentifierException(ConsultoraMasivo.class,this.getResourceMessage("procesoMAECargaInformacionMasivoForm.error.vacio"));
		
		this.procesoMAECargaInformacionMasivoForm_registrosValidos = listaRegistrosValidos.size();
		this.procesoMAECargaInformacionMasivoForm_registrosError = listaRegistrosConError.size();
		this.maeRegistrosValidosList = listaRegistrosValidos;
		this.maeRegistrosConErrorList = listaRegistrosConError;
		this.procesoMAECargaInformacionMasivoForm_vista = Constants.NUMERO_DOS;		
	}
		catch(InvalidIdentifierException iie)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(iie));				
		}
		catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));				
		}
	}

	
	public boolean existenCaracteres(String direccion){
		boolean existe = false;
		
		for(int i=0;i<direccion.length();i++){
			if((StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_1)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_2)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_3)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_4)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_5)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_6)) ||
					(StringUtils.equalsIgnoreCase(direccion.substring(i,i+1), Constants.MAE_CLIENTE_CARACTER_7))){
				existe = true;
				break;
			}
		}
		return existe;
	}

	public void ejecutar(ActionEvent event) {
	
		String mensaje = null;	
		ProcesoMAECargaInformacionMasivoForm f = (ProcesoMAECargaInformacionMasivoForm) this.formProceso;
		ProcesoMAECargaInformacionMasivoService service = 
			(ProcesoMAECargaInformacionMasivoService) getBean("spusicc.procesoMAECargaInformacionMasivoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List listaRegistrosValidos = (List)this.maeRegistrosValidosList;
	
		try {
		
		if (listaRegistrosValidos != null && listaRegistrosValidos.size() > 0) {
			
			Map criteria = new HashMap();
			
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("tipoCarga", f.getTipoCarga());
			criteria.put("usuarioLogin",usuario.getLogin());
			criteria.put("listaRegistrosValidos", listaRegistrosValidos);
			service.executeProcesarRegistros(criteria);
			
			this.maeDireccionesTelefonoRegistradasAlProgramaList = listaRegistrosValidos;
			this.procesoMAECargaInformacionMasivoForm_vista = Constants.NUMERO_TRES;
		}else {
			mensaje = "procesoMAECargaInformacionMasivoForm.errors.listaValidos.vacio";
			this.addError("Error: ", this.getResourceMessage(mensaje));			
		}	
	
	}catch (Exception e) {
		String error = e.getMessage();
		if (StringUtils.isBlank(error)) 
			error = e.getLocalizedMessage();
		this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
	}
	//return mapping.findForward("view");
	}

/**
 * @return the maeListaDireccionesTelefono
 */
public List getMaeListaDireccionesTelefono() {
	return maeListaDireccionesTelefono;
}

/**
 * @param maeListaDireccionesTelefono the maeListaDireccionesTelefono to set
 */
public void setMaeListaDireccionesTelefono(List maeListaDireccionesTelefono) {
	this.maeListaDireccionesTelefono = maeListaDireccionesTelefono;
}

/**
 * @return the procesoMAECargaInformacionMasivoForm_totalRegistros
 */
public int getProcesoMAECargaInformacionMasivoForm_totalRegistros() {
	return procesoMAECargaInformacionMasivoForm_totalRegistros;
}

/**
 * @param procesoMAECargaInformacionMasivoForm_totalRegistros the procesoMAECargaInformacionMasivoForm_totalRegistros to set
 */
public void setProcesoMAECargaInformacionMasivoForm_totalRegistros(
		int procesoMAECargaInformacionMasivoForm_totalRegistros) {
	this.procesoMAECargaInformacionMasivoForm_totalRegistros = procesoMAECargaInformacionMasivoForm_totalRegistros;
}

/**
 * @return the procesoMAECargaInformacionMasivoForm_archivo
 */
public String getProcesoMAECargaInformacionMasivoForm_archivo() {
	return procesoMAECargaInformacionMasivoForm_archivo;
}

/**
 * @param procesoMAECargaInformacionMasivoForm_archivo the procesoMAECargaInformacionMasivoForm_archivo to set
 */
public void setProcesoMAECargaInformacionMasivoForm_archivo(
		String procesoMAECargaInformacionMasivoForm_archivo) {
	this.procesoMAECargaInformacionMasivoForm_archivo = procesoMAECargaInformacionMasivoForm_archivo;
}

/**
 * @return the procesoMAECargaInformacionMasivoForm_vista
 */
public String getProcesoMAECargaInformacionMasivoForm_vista() {
	return procesoMAECargaInformacionMasivoForm_vista;
}

/**
 * @param procesoMAECargaInformacionMasivoForm_vista the procesoMAECargaInformacionMasivoForm_vista to set
 */
public void setProcesoMAECargaInformacionMasivoForm_vista(
		String procesoMAECargaInformacionMasivoForm_vista) {
	this.procesoMAECargaInformacionMasivoForm_vista = procesoMAECargaInformacionMasivoForm_vista;
}

/**
 * @return the procesoMAECargaInformacionMasivoForm_registrosValidos
 */
public int getProcesoMAECargaInformacionMasivoForm_registrosValidos() {
	return procesoMAECargaInformacionMasivoForm_registrosValidos;
}

/**
 * @param procesoMAECargaInformacionMasivoForm_registrosValidos the procesoMAECargaInformacionMasivoForm_registrosValidos to set
 */
public void setProcesoMAECargaInformacionMasivoForm_registrosValidos(
		int procesoMAECargaInformacionMasivoForm_registrosValidos) {
	this.procesoMAECargaInformacionMasivoForm_registrosValidos = procesoMAECargaInformacionMasivoForm_registrosValidos;
}

/**
 * @return the procesoMAECargaInformacionMasivoForm_registrosError
 */
public int getProcesoMAECargaInformacionMasivoForm_registrosError() {
	return procesoMAECargaInformacionMasivoForm_registrosError;
}

/**
 * @param procesoMAECargaInformacionMasivoForm_registrosError the procesoMAECargaInformacionMasivoForm_registrosError to set
 */
public void setProcesoMAECargaInformacionMasivoForm_registrosError(
		int procesoMAECargaInformacionMasivoForm_registrosError) {
	this.procesoMAECargaInformacionMasivoForm_registrosError = procesoMAECargaInformacionMasivoForm_registrosError;
}

/**
 * @return the maeRegistrosValidosList
 */
public List getMaeRegistrosValidosList() {
	return maeRegistrosValidosList;
}

/**
 * @param maeRegistrosValidosList the maeRegistrosValidosList to set
 */
public void setMaeRegistrosValidosList(List maeRegistrosValidosList) {
	this.maeRegistrosValidosList = maeRegistrosValidosList;
}

/**
 * @return the maeRegistrosConErrorList
 */
public List getMaeRegistrosConErrorList() {
	return maeRegistrosConErrorList;
}

/**
 * @param maeRegistrosConErrorList the maeRegistrosConErrorList to set
 */
public void setMaeRegistrosConErrorList(List maeRegistrosConErrorList) {
	this.maeRegistrosConErrorList = maeRegistrosConErrorList;
}

/**
 * @return the maeDireccionesTelefonoRegistradasAlProgramaList
 */
public List getMaeDireccionesTelefonoRegistradasAlProgramaList() {
	return maeDireccionesTelefonoRegistradasAlProgramaList;
}

/**
 * @param maeDireccionesTelefonoRegistradasAlProgramaList the maeDireccionesTelefonoRegistradasAlProgramaList to set
 */
public void setMaeDireccionesTelefonoRegistradasAlProgramaList(
		List maeDireccionesTelefonoRegistradasAlProgramaList) {
	this.maeDireccionesTelefonoRegistradasAlProgramaList = maeDireccionesTelefonoRegistradasAlProgramaList;
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



}
