/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.mae.ws.impl;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacionOperadora;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteEncuesta;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePreferenciaComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteTipoLogro;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SegmentoGrupoLove;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.ProcesoMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ClienteNuevoMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ComConsMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.DireccionMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.EncuestaConsLoveMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.PrefComMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ProcesoMAEWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.ResultadoMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.SgmtoGrupLoveMAEWebService;
import biz.belcorp.ssicc.service.spusicc.mae.ws.beans.TipLogroLoveMAEWebService;
import biz.belcorp.ssicc.service.util.StringUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoMAEWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="sbuchelli@belcorp.biz"> sbuchelli </a>
 */
@Service("spusicc.procesoMAEWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEWebServiceImpl extends ServletEndpointSupport implements
			ProcesoMAEWebService{

    Log log = LogFactory.getLog(ProcesoMAEWebServiceImpl.class);
    
    @Resource(name="spusicc.mantenimientoMAEClienteService")
    MantenimientoMAEClienteService clienteService;
    @Resource(name="scsicc.reporteService")    
    ReporteService reporteService;
    
    ProcesoMAEWebService procesoMAEService;
    @Resource(name="messageSource")
    MessageSource messageSource;
    @Resource(name="usuarioService")
	UsuarioService usuarioService;
    @Resource(name="genericoService")
	GenericoService genericoService;
	
    /**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @param clienteService the clienteService to set
	 */
	public void setClienteService(MantenimientoMAEClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * @param reporteService the reporteService to set
	 */
	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	clienteService = (MantenimientoMAEClienteService)getWebApplicationContext().getBean("spusicc.mantenimientoMAEClienteService");
    	messageSource = (MessageSource)getWebApplicationContext().getBean("messageSource");
    	//reporteService = (ReporteService)getWebApplicationContext().getBean("scsicc.reporteService");
    	procesoMAEService = (ProcesoMAEWebService)getWebApplicationContext().getBean("spusicc.procesoMAEWebService");    	
		usuarioService = (UsuarioService) getWebApplicationContext().getBean("usuarioService");
		genericoService = (GenericoService)getWebApplicationContext().getBean("genericoService");
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.web.ws.ProcesoMAEWebService#saveEncuesta(java.lang.String, biz.belcorp.ssicc.spusicc.mae.web.ws.beans.EncuestaConsLoveMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.ComConsMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.PrefComMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.SgmtoGrupLoveMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.SgmtoGrupLoveMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.SgmtoGrupLoveMAEWebService[], biz.belcorp.ssicc.spusicc.mae.web.ws.beans.TipLogroLoveMAEWebService[], java.lang.String)
     */
    public ProcesoMAEWebServiceResultado saveEncuesta(String codigoCliente,
   		 EncuestaConsLoveMAEWebService  encuesta,
   		 ComConsMAEWebService [] comunicacionConsultora,
   		 PrefComMAEWebService [] preferenciaContacto,
   		 SgmtoGrupLoveMAEWebService segmentoGrupo3,
   		 SgmtoGrupLoveMAEWebService segmentoGrupo6,
   		 SgmtoGrupLoveMAEWebService segmentoEstablecida,
   		 TipLogroLoveMAEWebService tipoLogro,
   		 String codigoIsoIdioma)
			throws RemoteException {
    	//lo ponemos transaccional
    	ProcesoMAEWebServiceResultado procesoMAEWebServiceResultado =new ProcesoMAEWebServiceResultado();
    	try{
    	   procesoMAEService.saveOrUpdateAll(codigoCliente,
			                      encuesta,
			                      comunicacionConsultora, 
			                      preferenciaContacto, 
			                      segmentoGrupo3,
			                      segmentoGrupo6, 
			                      segmentoEstablecida, 
			                      tipoLogro, 
			                      codigoIsoIdioma);
    	   procesoMAEWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_OK);
    	   procesoMAEWebServiceResultado.setMensaje("");
    	}catch(Exception e){
    		log.debug("error en save Encuesta " +e.getMessage());
   		    procesoMAEWebServiceResultado.setCodigo(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
    		procesoMAEWebServiceResultado.setMensaje(e.getMessage());
    	}
	   return procesoMAEWebServiceResultado;
	}

   
    /**
     * este metodo se encarga d ela integridad del proceso web service
     * @param codigoCliente
     * @param encuesta
     * @param comunicacionConsultora
     * @param preferenciaContacto
     * @param segmentoGrupo3
     * @param segmentoGrupo6
     * @param segmentoEstablecida
     * @param tipoLogro
     * @param codigoIsoIdioma
     * @return
     * @throws RemoteException
     */
    public void saveOrUpdateAll(String codigoCliente,
   		 EncuestaConsLoveMAEWebService  encuesta,
   		 ComConsMAEWebService [] comunicacionConsultora,
   		 PrefComMAEWebService [] preferenciaContacto,
   		 SgmtoGrupLoveMAEWebService segmentoGrupo3,
   		 SgmtoGrupLoveMAEWebService segmentoGrupo6,
   		 SgmtoGrupLoveMAEWebService segmentoEstablecida,
   		 TipLogroLoveMAEWebService tipoLogro,
   		 String codigoIsoIdioma)
			throws Exception {
    	String mensajeError="";
	try{	
		log.debug("inicio save encuesta");
		String usuario = encuesta.getUsuario();
		if(StringUtils.isEmpty(usuario)) throw  new Exception(mensajeError);
				
		mensajeError = validarCliente(codigoCliente, codigoIsoIdioma);
		if(StringUtils.isNotEmpty(mensajeError)) throw  new Exception(mensajeError);
		
		//validamos si el cliente es el mismo
		String cliente = encuesta.getCodigoCliente();
		if(!cliente.equals(codigoCliente)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarSameCliente",null,getLocaleIdioma(codigoIsoIdioma));
			throw  new Exception(mensajeError);
		}
		
		mensajeError = validarEncuesta(encuesta, codigoIsoIdioma);
		if(StringUtils.isNotEmpty(mensajeError)) throw  new Exception(mensajeError);
		
		mensajeError = validarComunicacionCliente(comunicacionConsultora, codigoIsoIdioma);
		if(StringUtils.isNotEmpty(mensajeError)) throw  new Exception(mensajeError);

		log.debug("obteniendo oid cliente");
		String oidCliente=getObtenerOidCliente(codigoCliente);
				
		saveOrUpdatePreferencia(codigoCliente,preferenciaContacto,usuario,codigoIsoIdioma);
		
		saveOrUpdateComunicacionCliente(oidCliente,comunicacionConsultora,usuario,codigoIsoIdioma);
//		inserta o actualiza el grupo love de segmento
		saveOrUpdateGrupoLove(segmentoGrupo3, usuario,codigoIsoIdioma);
		saveOrUpdateGrupoLove(segmentoGrupo6, usuario,codigoIsoIdioma);
		saveOrUpdateGrupoLove(segmentoEstablecida, usuario,codigoIsoIdioma);
//		inserta o actualiza el segmento love
		saveOrUpdateSegmentoLove(segmentoGrupo3, usuario,codigoIsoIdioma);
		saveOrUpdateSegmentoLove(segmentoGrupo6, usuario,codigoIsoIdioma);
		saveOrUpdateSegmentoLove(segmentoEstablecida, usuario,codigoIsoIdioma);
				
		saveOrUpdateLogro(tipoLogro, usuario,codigoIsoIdioma);
		
		updateFechaNacimiento(oidCliente, encuesta.getFechaNaci());
		//insertando o actualizando la encuesta la encuesta		
		//encuesta.setCodigoCliente(codigoCliente);
		log.debug("encuesta");
		saveOrUpdateEncuesta(encuesta,segmentoGrupo3,segmentoGrupo6,segmentoEstablecida,tipoLogro,usuario,codigoIsoIdioma);
		log.debug("fin save encuesta");
			
	}catch(Exception e){
		//log.debug("error en saveOrUpdateAll " +e.getMessage());
		throw  new Exception(e.getMessage());

	}
	
	}
	
	/**
	 * Inserta o actualiza la encuesta
	 * @param encuesta
	 * @param segmentoGrupo3
	 * @param segmentoGrupo6
	 * @param segmentoEstablecida
	 * @param tipoLogro
	 * @param usuario
	 * @param codigoIsoIdioma
	 * @throws Exception 
	 */
	private void saveOrUpdateEncuesta(EncuestaConsLoveMAEWebService encuesta,
			SgmtoGrupLoveMAEWebService segmentoGrupo3,
			SgmtoGrupLoveMAEWebService segmentoGrupo6,
			SgmtoGrupLoveMAEWebService segmentoEstablecida,
			TipLogroLoveMAEWebService tipoLogro, String usuario,
			String codigoIsoIdioma) throws Exception {
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		//
		ClienteEncuesta clienteEncuesta = new ClienteEncuesta();
		BeanUtils.copyProperties(clienteEncuesta, encuesta);
		//anhadimos el logro
		clienteEncuesta.setCodigoTipoLogro(tipoLogro.getCodTipoLogro());
		//anhadimos los segmentosX
		clienteEncuesta.setSecuenciaSegmentoVisita3(getSecuenciaSegmento(segmentoGrupo3));
		clienteEncuesta.setSecuenciaSegmentoVisita6(getSecuenciaSegmento(segmentoGrupo6));
		clienteEncuesta.setSecuenciaSegmentoVisitaEstablecida(getSecuenciaSegmento(segmentoEstablecida));
			
			Integer cont = clienteService.getExisteClienteEncuesta(clienteEncuesta);
			if(cont >0){				
					clienteService.updateClienteEncuesta(clienteEncuesta,usuarioBean);
			}
			else{
				try{	
					clienteService.insertClienteEncuesta(clienteEncuesta,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.encuesta",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}
		
	}


	/**
	 * Obtiene la secuencia del segmento X
	 * @param segmentoGrupoX
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private Long getSecuenciaSegmento(SgmtoGrupLoveMAEWebService segmentoGrupoX) throws IllegalAccessException, InvocationTargetException {
		SegmentoGrupoLove segmentoGrupoLove = new SegmentoGrupoLove();	
		BeanUtils.copyProperties(segmentoGrupoLove, segmentoGrupoX);		
		SegmentoGrupoLove segmentoLove = clienteService.getExisteSegmentoGrupoLove(segmentoGrupoLove);
		return (segmentoLove!=null?segmentoLove.getSecuenciaSegmento():null);
	}


	/**
	 * Se encraga de insertar o actualiza los grupos love
	 * @param segmentoGrupoX
	 * @param usuario
	 * @param codigoIsoIdioma
	 * @throws Exception 
	 */
	private void saveOrUpdateGrupoLove(
			SgmtoGrupLoveMAEWebService segmentoGrupoX, String usuario,
			String codigoIsoIdioma) throws Exception {
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		   log.debug("grupo love");
			SegmentoGrupoLove segmentoGrupoLove = new SegmentoGrupoLove();	
			BeanUtils.copyProperties(segmentoGrupoLove, segmentoGrupoX);
			
			SegmentoGrupoLove grupoLove = clienteService.getExisteGrupoLove(segmentoGrupoLove);
			if(grupoLove!=null){
				if(!StringUtils.equals(grupoLove.getDescripcionGrupo(), segmentoGrupoLove.getDescripcionGrupo()))
					clienteService.updateGrupoLove(segmentoGrupoLove,usuarioBean);
			}
			else{
				try{	
					if(StringUtils.isNotEmpty(segmentoGrupoLove.getCodigoGrupo()))
					 clienteService.insertGrupoLove(segmentoGrupoLove,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.grupolove",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}
			
		
	}

	/**
	 * Se encraga de insertar o actualiza los grupos love
	 * @param segmentoGrupoX
	 * @param usuario
	 * @param codigoIsoIdioma
	 * @throws Exception 
	 */
	private void saveOrUpdateSegmentoLove(
			SgmtoGrupLoveMAEWebService segmentoGrupoX, String usuario,
			String codigoIsoIdioma) throws Exception {
		log.debug("segmento love");
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		//
			SegmentoGrupoLove segmentoGrupoLove = new SegmentoGrupoLove();	
			BeanUtils.copyProperties(segmentoGrupoLove, segmentoGrupoX);
			
			SegmentoGrupoLove segmentoLove = clienteService.getExisteSegmentoGrupoLove(segmentoGrupoLove);
			if(segmentoLove!=null){
				if(!StringUtils.equals(segmentoLove.getDescripcionGrupo(), segmentoGrupoLove.getDescripcionGrupo()))
					clienteService.updateSegmentoGrupoLove(segmentoGrupoLove,usuarioBean);
			}
			else{
				try{	
					if(StringUtils.isNotEmpty(segmentoGrupoLove.getCodigoSegmento()))
					 clienteService.insertSegmentoGrupoLove(segmentoGrupoLove,usuarioBean);
				  } catch(Exception e){//lanzamos el error personalizado					  
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.segmentogrupolove",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();
					  //log.debug("error saveOrUpdateSegmentoLove "+mensajeError+" "+ e.getMessage());
					  throw new Exception(mensajeError);
				  }
			}
			
		
	}

	/**
	 * actuliza la fecha de nacimiento
	 * @param oidCliente
	 * @param fechaNaci
	 * @throws ParseException 
	 */
	private void updateFechaNacimiento(String oidCliente, String fechaNaci) throws ParseException {
		log.debug("updateFechaNacimiento "+fechaNaci);
		if(StringUtils.isNotEmpty(fechaNaci)){
			ClienteAdicional clienteAdicional =clienteService.getDatosAdicionalesCliente(oidCliente);
			if(clienteAdicional!=null){
				//fecha de nacimiento viene en formato ddmmyyyy
				log.debug(convertStringToDate("ddMMyyyy",fechaNaci));
				clienteAdicional.setFechaNacimiento(convertStringToDate("ddMMyyyy",fechaNaci));
				clienteService.updateDatosAdicionalesCliente(clienteAdicional);
			}
		}
		
	}


	/**
	 * Inserta o actualiza los tipos de logro
	 * @param tipoLogro
	 * @param usuario
	 * @param codigoIsoIdioma 
	 * @throws Exception 
	 */
	private void saveOrUpdateLogro(TipLogroLoveMAEWebService tipoLogro,
			String usuario, String codigoIsoIdioma) throws Exception {
		log.debug("logro");
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		//
			ClienteTipoLogro clientTipoLogro = new ClienteTipoLogro();	
			BeanUtils.copyProperties(clientTipoLogro, tipoLogro);
			
			ClienteTipoLogro clientTipoLogr = clienteService.getExisteTipoLogro(clientTipoLogro);
			if(clientTipoLogr!=null){
				if(!StringUtils.equals(clientTipoLogr.getDesTipoLogro(), clientTipoLogro.getDesTipoLogro()))
					clienteService.updateTipoLogro(clientTipoLogro,usuarioBean);
			}
			else{
				try{	
					if(StringUtils.isNotEmpty(clientTipoLogro.getCodTipoLogro()))					
					 clienteService.insertTipoLogro(clientTipoLogro,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.logro",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}
		
		
	}


	/**
	 * Inserta o actualiza las comunicaciones del cliente
	 * @param oidCliente 
	 * @param comunicacionConsultora
	 * @param usuario
	 * @param codigoIsoIdioma 
	 * @throws Exception 
	 */
	private void saveOrUpdateComunicacionCliente(
			String oidCliente, ComConsMAEWebService[] comunicacionConsultora,
			String usuario, String codigoIsoIdioma) throws Exception {
		//obtenemos oid cliente
		//String oidCliente=getObtenerOidCliente(codigoCliente);
		log.debug("comunicacion");
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		//cargamos el maestro de tipo operadora
		for(int i=0 ;i< comunicacionConsultora.length;i++ ){
			ClienteComunicacionOperadora comunicacionOperadora = new ClienteComunicacionOperadora();	
			BeanUtils.copyProperties(comunicacionOperadora, comunicacionConsultora[i]);
			
			ClienteComunicacionOperadora clientComunicacion = clienteService.getExisteTipoOperadora(comunicacionOperadora);
			if(clientComunicacion!=null){
				if(!StringUtils.equals(clientComunicacion.getDesTipoOperador(), comunicacionOperadora.getDesTipoOperador()))
					clienteService.updateTipoOperadora(comunicacionOperadora,usuarioBean);
			}
			else{
				try{	
					if(StringUtils.isNotEmpty(comunicacionOperadora.getCodTipoOperador()))
						clienteService.insertTipoOperadora(comunicacionOperadora,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.operadora",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}	  
				
		}		
				
		//insrtamo o actulizamos la comun del cliente
		for(int i=0 ;i< comunicacionConsultora.length;i++ ){
			ClienteComunicacionOperadora comunicacionOperadora = new ClienteComunicacionOperadora();	
			BeanUtils.copyProperties(comunicacionOperadora, comunicacionConsultora[i]);
			Long oidTipoComunicacion = clienteService.getOidTipoComunicacion(comunicacionOperadora.getCodigoTipoComun());
			comunicacionOperadora.setOidConsultora(oidCliente);
			comunicacionOperadora.setOidTipoComunicacion(oidTipoComunicacion);
			
			Integer cont = clienteService.getExisteComunicacionCliente(comunicacionOperadora);
			if(cont > 0)
				clienteService.updateComunicacionCliente(comunicacionOperadora,usuarioBean);
			else{				
				try{	
					clienteService.insertComunicacionCliente(comunicacionOperadora,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.comunicacion",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}	
		}
		
	}


	/**
	 * Inserta o actualiza la preferencia contacto
	 * @param codigoCliente 
	 * @param preferenciaContacto
	 * @param usuario 
	 * @throws Exception 
	 */
	private void saveOrUpdatePreferencia(
			String codigoCliente, PrefComMAEWebService[] preferenciaContacto, String usuario,String codigoIsoIdioma) throws Exception {
		log.debug("preferencia");
		Usuario usuarioBean = new Usuario();
		usuarioBean.setLogin(usuario);
		//cargamos el maestro de preferencia d ecomunicacion
		for(int i=0 ;i< preferenciaContacto.length;i++ ){
			ClientePreferenciaComunicacion preferenciaComun = new ClientePreferenciaComunicacion();	
			BeanUtils.copyProperties(preferenciaComun, preferenciaContacto[i]);
			ClientePreferenciaComunicacion clientPreferencia = clienteService.getExistePreferenciaComunicacion(preferenciaComun);
			
			if(clientPreferencia!=null){
				if(!StringUtils.equals(clientPreferencia.getDesTipoPreferen(), preferenciaComun.getDesTipoPreferen()))
					clienteService.updatePreferencia(preferenciaComun,usuarioBean);
			}else{
			  try{	
				clienteService.insertPreferencia(preferenciaComun,usuarioBean);
			  }catch(Exception e){//lanzamos el error personalizado
				  String mensajeError =this.messageSource.
		     		getMessage("procesoMAEWebService.msg.error.grabar.preferencia",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
				  throw new Exception(mensajeError);
			  }
			}
		}
		//insrtamo o actulizamos la preferencia del cliente
		for(int i=0 ;i< preferenciaContacto.length;i++ ){
			ClientePreferenciaComunicacion preferenciaComun = new ClientePreferenciaComunicacion();	
			BeanUtils.copyProperties(preferenciaComun, preferenciaContacto[i]);
			preferenciaComun.setCodigoCliente(codigoCliente);
			Integer cont = clienteService.getExistePreferenciaClienteComunicacion(preferenciaComun);
			if(cont > 0)
				clienteService.updatePreferenciaCliente(preferenciaComun,usuarioBean);
			else{
				try{	
					clienteService.insertPreferenciaCliente(preferenciaComun,usuarioBean);
				  }catch(Exception e){//lanzamos el error personalizado
					  String mensajeError =this.messageSource.
			     		getMessage("procesoMAEWebService.msg.error.grabar.preferencia.cliente",null,getLocaleIdioma(codigoIsoIdioma))+" "+e.getMessage();				  
					  throw new Exception(mensajeError);
				  }
			}
				
		}
		
	}


	/**
	 * Valida que exista los tipo de comunicacion
	 * @param comunicacionConsultora
	 * @param codigoIsoIdioma
	 * @return
	 */
	private String validarComunicacionCliente(
			ComConsMAEWebService[] comunicacionConsultora,String codigoIsoIdioma) {
		log.debug("validando comunicacion");
		String mensajeError="";
		for(int i=0;i < comunicacionConsultora.length ;i++){
			String codigoTipoComunicacion = comunicacionConsultora[i].getCodigoTipoComun();			
			Long oidTipoComunicacion = clienteService.getOidTipoComunicacion(codigoTipoComunicacion);
			if(oidTipoComunicacion==null){
				mensajeError =this.messageSource.
	     		getMessage("procesoMAEWebService.msg.validarComunicacionCliente",null,getLocaleIdioma(codigoIsoIdioma))+ ": "+codigoTipoComunicacion;
				return mensajeError;
			}
		}
		
		return mensajeError;
	}


	/**
	 * Valida que los parametors sean los correctos
	 * @param encuesta
	 * @return
	 */
	private String validarEncuesta(EncuestaConsLoveMAEWebService encuesta,String codigoIsoIdioma) {
		log.debug("ini validando encuesta");
		String mensajeError="";		
		String informacionMarca =encuesta.getInfMarca();
		if(!Constants.SI.equals(informacionMarca.toUpperCase())  && !Constants.NO.equals(informacionMarca.toUpperCase()) ){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarInformacionMarca",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}
		
		//fecha de cumpleanhos
		String tipoFormato="1";//corresponde al formato ddmmyyyy
		mensajeError=validarFormatoFecha(encuesta.getFechaNaci(),tipoFormato);
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarFechaNacimiento",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}
		//validar valor del logro		
		mensajeError=validarDatoNumerico(encuesta.getValLogro());
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarValLogro",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}
		//validacion campanha inicio 
		mensajeError=validarCampanha(encuesta.getCmpIniLogro());
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarCampanha",new String []{encuesta.getCmpIniLogro()},getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}
		//validacion campanha fin 
		mensajeError=validarCampanha(encuesta.getCmpFinLogro());
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarCampanha",new String []{encuesta.getCmpFinLogro()},getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}		
		
		//fecha de act v3
		tipoFormato="2";//corresponde al formato ddmmyyyy
		mensajeError=validarFormatoFecha(encuesta.getFechaActV3(),tipoFormato);
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarFechaActV3",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}
		//fecha de act v6
		tipoFormato="2";//corresponde al formato ddmmyyyy
		mensajeError=validarFormatoFecha(encuesta.getFechaActV6(),tipoFormato);
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarFechaActV6",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}		
		//fecha de act esta
		tipoFormato="2";//corresponde al formato ddmmyyyy
		mensajeError=validarFormatoFecha(encuesta.getFechaActEst(),tipoFormato);
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarFechaActEst",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}		
		//porcentaje asignacion logro
		mensajeError=validarDatoNumerico(encuesta.getPorAsigLogro());
		if(StringUtils.isNotEmpty(mensajeError)){
			mensajeError =this.messageSource.
     		getMessage("procesoMAEWebService.msg.validarPorAsigLogro",null,getLocaleIdioma(codigoIsoIdioma));
			return mensajeError;
		}		
		return mensajeError;
	}


	/**
	 * Valida que el formato d ela campanha sea la correcta yyyymm donde mm va de 1 a 18
	 * @param campanha
	 * @return
	 */
	private String validarCampanha(String campanha) {
		String mensaje ="";
		if(StringUtils.isEmpty(campanha)) return mensaje;
		if(campanha.length() != 6) return campanha;
		//
		int anho = Integer.parseInt(campanha.substring(0,4)); 
		int camp = Integer.parseInt(campanha.substring(4));
		log.debug("campanha validada " + anho + " - " + camp);
		if(anho >= 1900 && 
		   camp >= 1 && camp <=18) return mensaje;
		
		return campanha;
	}

	/**
	 * valida que el dato ingresado sea numercio
	 * @param val
	 * @return
	 */
	private String validarDatoNumerico(String val) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(val)){
			try{
			   new Double(val);
			}catch(Exception e){
			  log.debug("error en valor numerico ");
			  mensaje=e.getMessage();
			}			
		}
		return mensaje;
	}


	/**
	 * Si tipoFormato =1 se valida que lo q s ehay ingresado sea ddmmyyyy
	 * si es fomato=2 se valida que el formato sea ddmmyyyy:hh:mi:ss
	 * @param fecha
	 * @param tipoFormato
	 * @return
	 */
	private String validarFormatoFecha(String fecha, String tipoFormato) {
		String mensaje ="";
		if(StringUtils.isNotEmpty(fecha)){
		
			if(tipoFormato.equals(Constants.NUMERO_UNO)){
				if(fecha.length() != 8){
					mensaje= String.valueOf(fecha.length());
				}
			}else{//formato 2
				String separator=":";
				String [] str =StringUtils.split(fecha, separator);
				if(str.length == 4){
					String fechaAux = str[0];
					String hora = str[1];
					String minuto = str[2];
					String segundo = str[3];
					if(fechaAux.length() != 8 ||
					 	hora.length() != 2	||
					 	minuto.length() != 2 ||
					 	segundo.length() != 2){
						
						mensaje=String.valueOf(fecha.length());
					}
					
				}else{
					mensaje=String.valueOf(fecha.length());
				}
			}
		}
		return mensaje;
	}


	/**
	 * Valida que el codigo de cliente exista
	 * @param codigoCliente
	 * @param codigoIsoIdioma
	 * @return
	 */
	private String validarCliente(String codigoCliente,String codigoIsoIdioma) {				 
		   String mensajeError="";	
		   log.debug("validando cliente");
		   String oid=getObtenerOidCliente(codigoCliente);
		   if(StringUtils.isEmpty(oid))
		     mensajeError =this.messageSource.
		     		getMessage("procesoMAEWebService.msg.validarCodigoCliente",null,getLocaleIdioma(codigoIsoIdioma));				
		return mensajeError;
	}
	
	private String getObtenerOidCliente(String codigoCliente){
		Map criteria = new HashMap();
		String oidConsultora="";
		criteria.put("codigoCliente", codigoCliente);
		try{
		    oidConsultora = reporteService.getOidString("getOidClienteByCodigoCliente",
				 									criteria);
		}catch(Exception e){
			e.printStackTrace();
			log.debug("client no exist ");
			oidConsultora="";
		}		
		return oidConsultora;		
	}


	/**
	 * Retorna la descripcion por idioma
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected Locale getLocaleIdioma(String codigoIsoIdioma) {
	    //mantenimientoEDUGenericoService.getIdiomaISO(codigoPais);
		if(StringUtils.isNotEmpty(codigoIsoIdioma)){
		    if(Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
			  return  new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		    else{
		    	log.debug("codigoIsoIdioma " + codigoIsoIdioma);		
		      return new Locale(codigoIsoIdioma.toLowerCase());
		    }
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);		
	}
	
	
    /**
     * Retorna la fecha en un Date si esta fecha es correcta
     * @param aMask
     * @param strDate
     * @return
     * @throws ParseException
     */
    private Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
            log.debug("converting '" + strDate + "' to date with mask '"
                     + aMask + "'");
        try {
        	df.setLenient(false); //deshabilita el modo permisivo
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.web.ws.ProcesoMAEWebService#buscarConsultora(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public ResultadoMAEWebService buscarConsultora(String codPais, String codConsultora, String CUB, 
    											   String usuRed, String tipoDoc, String nroDoc) {
    	ResultadoMAEWebService resultadoMAEWebService = new ResultadoMAEWebService();
    	List lista = new ArrayList();
    	try{
			log.debug("inicio buscarConsultora");
			String mensajeError = "";
			if (StringUtils.isBlank(codPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoMAEWebService.msg.validarPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			Map map = new HashMap();
			map.put("codigoPais", codPais);
			String oidPais = clienteService.getOidPais(map);
			
			
			//Validaciones
			boolean validaOK = false;
			boolean validarDocumento = false;
			if(StringUtils.isNotBlank(codConsultora)) {
				validaOK = true;
			}else if(StringUtils.isNotBlank(CUB)) {
				validaOK = true;
			}else if(StringUtils.isNotBlank(usuRed)) {
				validaOK = true;
			}else if(StringUtils.isNotBlank(tipoDoc)) {
				if(StringUtils.isNotBlank(nroDoc)) {
					validaOK = true;
					validarDocumento = true;
				}
			}
			if(!validaOK){
				mensajeError = getWebApplicationContext().getMessage("procesoMAEWebService.actualizarCliente.msg.alMenos1Filtro", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			
			/* LLenando Valores */
			Map criteria = new HashMap();
			criteria.put("codigoPais", codPais);
			criteria.put("oidPais", oidPais);
			
			if(StringUtils.isNotBlank(codConsultora)) {
				criteria.put("codigoCliente", codConsultora);
			}
			if(StringUtils.isNotBlank(CUB)) {
				criteria.put("codigoCUB", CUB);
			}
			if(StringUtils.isNotBlank(usuRed)) {
				criteria.put("usuarioRed", usuRed);
				
			}
			if (validarDocumento) {
				if(StringUtils.isNotBlank(tipoDoc) && StringUtils.isNotBlank(nroDoc)) {
					
					//Obtenemos el oid Tipo de documento de identidad
					String oidTipoDocumento = clienteService.getOidTipoDocumento(oidPais, tipoDoc);
					
					//Obtenemos longitud de Tipo de Documento de Identidad
					int longitudTipoDocumento = Integer.parseInt(clienteService.getLongitudTipoDocumento(oidPais, oidTipoDocumento));
					boolean permitirCompletarCerosIdentidad = true;
					if(clienteService.getValorModuloxPaisTipoValidacion(codPais, Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD) != null)
						permitirCompletarCerosIdentidad = false;
					
					//Obtenemos el Nro Documento de Identidad
					String numeroDocumento = "";
					if(permitirCompletarCerosIdentidad)
						numeroDocumento = completarCaracteres(nroDoc,longitudTipoDocumento,"0");
					else
						numeroDocumento = nroDoc;
					
					criteria.put("tipoDocumento", tipoDoc);
					criteria.put("numeroDocumento", numeroDocumento);					
					
				}
				else{
					throw new Exception(this.messageSource.getMessage("procesoMAEWebService.actualizarCliente.msg.tipoNroDocumentoError",null,getLocaleIdioma(null)));
				}
			}
	
			lista = clienteService.getObtenerBuscarConsultora(criteria);
			if (lista == null || lista.size() == 0 ) {
				String prefijoError = this.messageSource.getMessage(
						"procesoMAEWebService.actualizarCliente.msg.consulNoExiste",null,getLocaleIdioma(null));
						
				resultadoMAEWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
				resultadoMAEWebService.setMensaje(prefijoError);
				return resultadoMAEWebService;
			}
			
			ClienteNuevoMAEWebService clienteNuevoMAEWebService = new ClienteNuevoMAEWebService();
			Map resultado = (HashMap) lista.get(0);
			BeanUtils.populate(clienteNuevoMAEWebService, resultado);
			
			Map criteriaDireccion = new HashMap();
			criteriaDireccion.put("oidPais", oidPais);
			criteriaDireccion.put("codigoCliente", clienteNuevoMAEWebService.getCodConsultora());
			
			List listaDirecciones = clienteService.getObtenerDireccionesConsultora(criteriaDireccion);
			if (listaDirecciones != null && listaDirecciones.size() > 0) {
				DireccionMAEWebService[] listaDireccionMAEWebService = new DireccionMAEWebService[listaDirecciones.size()];
				for (int i=0; i < listaDirecciones.size(); i++) {
					Map resultadoDireccion = (HashMap) listaDirecciones.get(i);
					DireccionMAEWebService direccionMAEWebService = new DireccionMAEWebService();
					BeanUtils.populate(direccionMAEWebService, resultadoDireccion);
					listaDireccionMAEWebService[i] = direccionMAEWebService;
				}
				clienteNuevoMAEWebService.setLsDireccion(listaDireccionMAEWebService);
			}
			
			List listaResultado = new ArrayList();
			listaResultado.add(clienteNuevoMAEWebService);
			resultadoMAEWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_OK);
			resultadoMAEWebService.setConsultoras(listaResultado);
			resultadoMAEWebService.setMensaje("");
				
		}catch(Exception e){
			log.error("error en buscarConsultora: " +e.getMessage());
			resultadoMAEWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
			resultadoMAEWebService.setMensaje(e.getMessage());
		}

		return resultadoMAEWebService;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.web.ws.ProcesoMAEWebService#mantenerCliente(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, biz.belcorp.ssicc.spusicc.mae.web.ws.beans.DireccionMAEWebService[])
	 */
	public ResultadoMAEWebService mantenerCliente(String indAccion,
    		String indLlave,
    		String codConsultora,
    		String tipoCliente,
     		String subCliente,
     		String codZona,
     		String codTerri,
     		String fecIng,
     		String paqDoc,
     		String apePat,
     		String apeMat,
     		String priNom,
     		String segNom,
     		String nacCliente,
     		String tipoDoc,
     		String nroDoc,
     		String fecNac,
     		String sexo,
     		String estCivil,
     		String gradIns,
     		String nomAbrev,
     		String CUB,
     		String codPais,
     		String indActiva,
     		String indOrigen,
     		String codGrupoFuncional,
     		String desGrupoFuncional,
     		String usuRed,
     		String codJefeCUB,
     		String valRelContr,
     		String mailBelcorp,
     		String nomJefeDir,
     		String valPueOrg,
     		String codEmpleado,
     		DireccionMAEWebService[] lsDireccion) {
    	
    	ResultadoMAEWebService resultadoMAEWebService =new ResultadoMAEWebService();
		try{	
			log.debug("inicio mantenerCliente");
			boolean permitirCompletarCerosIdentidad = true;
			int longitudTipoDocumento = 0;
			String numeroDocumento = "";
			
			String mensajeError = "";
			if (StringUtils.isBlank(codPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoMAEWebService.msg.validarPais", null, getLocaleIdioma("es"));
				throw new Exception(mensajeError);
			}
			
			Map map = new HashMap();
			map.put("codigoPais", codPais);
			String oIDPais = clienteService.getOidPais(map);
			
			//Obtenemos el oid Tipo de documento de identidad
			String oidTipoDocumento = clienteService.getOidTipoDocumento(oIDPais, tipoDoc);
			
			if (StringUtils.isNotBlank(oidTipoDocumento)) {
				//Obtenemos longitud de Tipo de Documento de Identidad
				longitudTipoDocumento = Integer.parseInt(clienteService.getLongitudTipoDocumento(oIDPais, oidTipoDocumento));
				
				if(clienteService.getValorModuloxPaisTipoValidacion(codPais, Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD) != null)
					permitirCompletarCerosIdentidad = false;
				
				//Obtenemos el Nro Documento de Identidad
				if(permitirCompletarCerosIdentidad)
					numeroDocumento = completarCaracteres(nroDoc,longitudTipoDocumento,"0");
				else
					numeroDocumento = nroDoc;
			}
			
			//El sistema Verifica la Existencia del Cliente
			boolean registrarCliente = false;			
			String codigoCliente = null;
			
			if(StringUtils.isNotEmpty(indLlave)) {
				Map criteria = new HashMap();
				criteria.put("tipoDocumento", tipoDoc);
				criteria.put("numeroDocumento", numeroDocumento);
				if("1".equals(indLlave)) { //Buscamos por Tipo Documento y Numero Documento
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
				else if("2".equals(indLlave)) { //Buscamos por CUB
					criteria.put("codigoCUB", CUB);
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
				else if("3".equals(indLlave)) { //Buscamos por C�digo de Consultora
					criteria.put("codigoCliente", codConsultora);
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
				else {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.indLlaveValido",null,getLocaleIdioma(null)));
				}
				
			} else {
				Map criteria = null; 
				
				if(StringUtils.isNotEmpty(tipoDoc) && StringUtils.isNotEmpty(numeroDocumento)) {
					criteria = new HashMap();
					criteria.put("tipoDocumento", tipoDoc);
					criteria.put("numeroDocumento", numeroDocumento);					
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
				
				if(codigoCliente == null && StringUtils.isNotEmpty(CUB)) {
					criteria = new HashMap(); 	
					criteria.put("codigoCUB", CUB);
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
				
				if(codigoCliente == null && StringUtils.isNotEmpty(codConsultora)) {
					criteria = new HashMap(); 	
					criteria.put("codigoCliente", codConsultora);
					
					codigoCliente = clienteService.getExisteCliente(criteria);
				}
			}
			
			//No Se encontro al cliente, entonces se procederia a INGRESAR al cliente
			if(codigoCliente == null) {
				registrarCliente = true;
			} else {
				if(StringUtils.isEmpty(indAccion)) {
					throw  new Exception(this.messageSource.getMessage(
								"procesoMAEWebService.registrarCliente.msg.indAccionVacio",null,getLocaleIdioma(null)));
				}
				
				if(!indAccion.equals("0") && !indAccion.equals("1")) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.indAccionValido",null,getLocaleIdioma(null)));
				}
				
				if("0".equals(indAccion)) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.indAccionNoActualiza",null,getLocaleIdioma(null)));
				}
			}			

			
			//Validamos que codigo Pais, tipo Cliente, subTipo Cliente, codigo Zona, codigo Territorio,
			//tipo Documento, estado Civil, tratamiento, nacionalidad no ESTEN EN BLANCO
			if(registrarCliente && StringUtils.isEmpty(tipoCliente)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.tipoClienteVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(subCliente)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.subtipoClienteVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(codZona)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.codigoZonaVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(codTerri)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.codigoTerritorioVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(tipoDoc)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.tipoDocumentoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(estCivil)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.estadoCivilVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(nomAbrev)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.tratamientoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(CUB)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.cubVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(codPais)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.codigoPaisVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(nacCliente)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.nacionalidadVacio",null,getLocaleIdioma(null)));
			}
			
			//En caso sea actualizacion y no envia codigo de Pais, ubicamos el pais por Defecto
			if(!registrarCliente && StringUtils.isEmpty(codPais)) {
				//Recuperamos el codigo Pais
				ParametroPais parametroPais = new ParametroPais();
				parametroPais.setCodigoSistema(Constants.SISTEMA_GEN);
				parametroPais.setCodigoParametro(Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT);				
				
				ParametroPais pPais = (ParametroPais)genericoService.getParametrosPais(parametroPais).get(0);
				codPais = pPais.getCodigoPais();	
			}
			
			//Validamos que existe codigo Pais, tipo Cliente, subTipo Cliente, codigo Zona, codigo Territorio,
			//tipo Documento, estado Civil, tratamiento, nacionalidad
			Map criteria = new HashMap();
			criteria.put("codigoPais", codPais);
			String oidPais = clienteService.getOidPais(criteria);
			if(oidPais==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.codigoPaisNoExiste",null,getLocaleIdioma(null)));
			}
			if(StringUtils.isNotEmpty(tipoCliente) && clienteService.getOidTipoCliente(tipoCliente)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.tipoClienteNoExiste",null,getLocaleIdioma(null)));
			}
			if(StringUtils.isNotEmpty(subCliente) && clienteService.getOidSubTipoCliente((clienteService.getOidTipoCliente(tipoCliente)).toString(),
					subCliente)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.subtipoClienteNoExiste",null,getLocaleIdioma(null)));
			}
			if(StringUtils.isNotEmpty(codZona) && clienteService.getExisteZona(oidPais, codZona)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.codigoZonaNoExiste",null,getLocaleIdioma(null)));
			}
			//Validamos que Codigo Territorio sea numerico
			if(StringUtils.isNotEmpty(codTerri)) {
				try {
					Long.parseLong(codTerri);
				}catch(Exception ex) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.codigoTerritorioNumerico",null,getLocaleIdioma(null)));
				}			
				if(clienteService.getExisteTerritorio(oidPais, codTerri)==null) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.codigoTerritorioNoExiste",null,getLocaleIdioma(null)));
				}
			}
			
			if(StringUtils.isNotEmpty(tipoDoc) && clienteService.getOidTipoDocumento(oidPais, tipoDoc)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.tipoDocumentoNoExiste",null,getLocaleIdioma(null)));
			}
			if(StringUtils.isNotEmpty(estCivil) && clienteService.getOidEstadoCivil(estCivil)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.estadoCivilNoExiste",null,getLocaleIdioma(null)));
			}
			//Validamos que Tratamiento sea numerico
			if(StringUtils.isNotEmpty(nomAbrev)) {
				try {
					Long.parseLong(nomAbrev);
				}catch(Exception ex) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.tratamientoNumerico",null,getLocaleIdioma(null)));
				}
				if(clienteService.getOidTratamiento(nomAbrev)==null) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.tratamientoNoExiste",null,getLocaleIdioma(null)));
				}
			}	
			if(StringUtils.isNotEmpty(nacCliente) && clienteService.getOidNacionalidad(nacCliente)==null) {
				throw  new Exception(this.messageSource.getMessage(
						"procesoMAEWebService.registrarCliente.msg.nacionalidadNoExiste",null,getLocaleIdioma(null)));
			}
			
			//Validamos Numero Documento, fecha de Nacimiento, fecha de Ingreso, apellido Paterno, apellido Materno 
			//y Primer Nombre que no ingresen en blanco
			if(registrarCliente && StringUtils.isEmpty(nroDoc)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.numeroDocumentoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(fecNac)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.fechaNacimientoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(fecIng)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.fechaIngresoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(apePat)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.apellidoPaternoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(apeMat)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.apellidoMaternoVacio",null,getLocaleIdioma(null)));
			}
			if(registrarCliente && StringUtils.isEmpty(priNom)) {
				throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.primerNombreVacio",null,getLocaleIdioma(null)));
			}

			//Validamos el Tipo Direccion y Tipo Via, que no esten en blancos y que existan
			if(lsDireccion != null) {
				for(int i=0; i<lsDireccion.length; i++) {
					if(registrarCliente && StringUtils.isEmpty(lsDireccion[i].getTipoDir())) {
						throw  new Exception(this.messageSource.getMessage(
									"procesoMAEWebService.registrarCliente.msg.tipoDireccionVacio",null,getLocaleIdioma(null)));
					}
					if(registrarCliente && StringUtils.isEmpty(lsDireccion[i].getTipoVia())) {
						throw  new Exception(this.messageSource.getMessage(
									"procesoMAEWebService.registrarCliente.msg.tipoViaVacio",null,getLocaleIdioma(null)));
					}
					
					if(StringUtils.isNotEmpty(lsDireccion[i].getTipoDir()) && clienteService.getOidTipoDireccion(lsDireccion[i].getTipoDir())==null) {
						throw  new Exception(this.messageSource.getMessage(
								"procesoMAEWebService.registrarCliente.msg.tipoDireccionNoExiste",null,getLocaleIdioma(null)));
					}
					
					if(StringUtils.isNotEmpty(lsDireccion[i].getTipoVia()) && clienteService.getOidTipoVia(lsDireccion[i].getTipoVia())==null) {
						throw  new Exception(this.messageSource.getMessage(
								"procesoMAEWebService.registrarCliente.msg.tipoViaNoExiste",null,getLocaleIdioma(null)));
					}
				}
			}	
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//Validamos la Fecha de Nacimiento 
			if(StringUtils.isNotEmpty(fecNac)) {
				try {
					sdf.parse(fecNac);
				}catch(Exception ex) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.fechaNacimientoNoValido",null,getLocaleIdioma(null)));
				}
			}	

			//Validamos la Fecha de Ingreso
			if(StringUtils.isNotEmpty(fecIng)) {			
				try {
					sdf.parse(fecIng);
				}catch(Exception ex) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.fechaIngresoNoValido",null,getLocaleIdioma(null)));
				}
			}	

			//Validamos que Indicador Activo sea '0' o 1' 
			if(StringUtils.isNotEmpty(indActiva)) {
				if(!indActiva.equals("0") && !indActiva.equals("1")) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.indicadorActivoValido",null,getLocaleIdioma(null)));
				}
			}
			
			//Validamos que Paquete Documentario sea '0' o 1' 
			if(StringUtils.isNotEmpty(paqDoc)) {
				if(!paqDoc.equals("0") && !paqDoc.equals("1")) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.registrarCliente.msg.indicadorPaqDocValido",null,getLocaleIdioma(null)));
				}
			}
			
			if(registrarCliente) {
				if(StringUtils.isEmpty(sexo))
					sexo = "F";
				
				if(StringUtils.isEmpty(indActiva))
					indActiva = "0";
			}
			
			//Construimos el objeto con los datos del cliente a Crear
			ClienteNuevoMAEWebService clienteNuevo = new ClienteNuevoMAEWebService();
			clienteNuevo.setTipoCliente(tipoCliente);
			clienteNuevo.setSubCliente(subCliente);
			clienteNuevo.setCodZona(codZona);
			clienteNuevo.setCodTerri(codTerri);
			clienteNuevo.setFecIng(fecIng);
			clienteNuevo.setPaqDoc(paqDoc);
			clienteNuevo.setApePat(apePat);
			clienteNuevo.setApeMat(apeMat);
			clienteNuevo.setPriNom(priNom);
			clienteNuevo.setSegNom(segNom);
			clienteNuevo.setNacCliente(nacCliente);
			clienteNuevo.setNacCliente(nacCliente);
			clienteNuevo.setTipoDoc(tipoDoc);
			clienteNuevo.setNroDoc(nroDoc);
			clienteNuevo.setFecNac(fecNac);
			clienteNuevo.setSexo(sexo);
			clienteNuevo.setEstCivil(estCivil);
			clienteNuevo.setGradIns(gradIns);
			clienteNuevo.setNomAbrev(nomAbrev);
			clienteNuevo.setCUB(CUB);
		 	clienteNuevo.setCodPais(codPais);
	 		clienteNuevo.setIndActiva(indActiva);
		 	clienteNuevo.setIndOrigen(indOrigen);
		 	
		 	clienteNuevo.setCodGrupoFuncional(codGrupoFuncional);
		 	clienteNuevo.setDesGrupoFuncional(desGrupoFuncional);
		 	clienteNuevo.setUsuRed(usuRed);
		 	clienteNuevo.setCodJefeCUB(codJefeCUB);
		 	clienteNuevo.setValRelContr(valRelContr);
		 	clienteNuevo.setMailBelcorp(mailBelcorp);
		 	clienteNuevo.setNomJefeDir(nomJefeDir);
     		clienteNuevo.setValPueOrg(valPueOrg);
     		clienteNuevo.setCodEmpleado(codEmpleado);
     		
		 	clienteNuevo.setLsDireccion(lsDireccion);			
		 	
		 	if(registrarCliente)
		 		codigoCliente = insertClienteWebSevice(clienteNuevo);
		 	else {
				clienteNuevo.setCodConsultora(codigoCliente);

		 		codigoCliente = updateClienteWebSevice(clienteNuevo);
		 	}	
			
			resultadoMAEWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_OK);
			resultadoMAEWebService.setCodCliente(codigoCliente);
			resultadoMAEWebService.setMensaje("");
	    	   
		}catch(Exception e){
			log.error("error en registrarCliente: " +e.getMessage());
			
			String prefijoError = this.messageSource.getMessage(
					"procesoMAEWebService.registrarCliente.msg.validacionIngresarCliente",null,getLocaleIdioma(null));
					
			resultadoMAEWebService.setCodResultado(Constants.MAE_WEBSERVICE_RESULTADO_ERROR);
			resultadoMAEWebService.setMensaje(prefijoError + " " + e.getMessage());
		}

		return resultadoMAEWebService;
   	}

    /**
     * @param clienteNuevo
     * @return
     * @throws Exception
     */
    private String insertClienteWebSevice(ClienteNuevoMAEWebService clienteNuevo) throws Exception {
		String codigoCliente = "";
		Long oidMarca = clienteService.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));
		String codigoUsuario = "ADMIN_WS";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", clienteNuevo.getCodPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
				
		//Datos relacionados al pais
  		String codigoPais = clienteNuevo.getCodPais();
  		String oidPais = clienteService.getOidPais(criteria);
  		criteria.put("oidPais",oidPais);
		
		//Obtenemos la longitud de Codigo Cliente y si el pais gestiona en forma manual o automatico
		//la generacion de codigo de cliente
		String longitudCodigoCliente = clienteService.getLongitudCodigoCliente(criteria);
		boolean esCodigoClienteAutomatico = clienteService.isCodigoClienteAutomatico(criteria);
		
		//Verificamos si se tiene que completar con ceros el numero de documento de identidad
		boolean permitirCompletarCerosIdentidad = true;
		if(clienteService.getValorModuloxPaisTipoValidacion(codigoPais, Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD) != null)
			permitirCompletarCerosIdentidad = false;

		//Obtenemos el oid Tipo de documento de identidad
		String oidTipoDocumento = clienteService.getOidTipoDocumento(oidPais, clienteNuevo.getTipoDoc());

		//Obtenemos longitud de Tipo de Documento de Identidad
		int longitudTipoDocumento = Integer.parseInt(clienteService.getLongitudTipoDocumento(oidPais, oidTipoDocumento));

		//Obtenemos el Nro Documento de Identidad
		String numeroDocumento = "";
		if(permitirCompletarCerosIdentidad)
			numeroDocumento = completarCaracteres(clienteNuevo.getNroDoc(),longitudTipoDocumento,"0");
		else
			numeroDocumento = clienteNuevo.getNroDoc();
		
		//Obtenemos el periodo Actual
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
		String oidPeriodo = periodos[0].getValue();
		
		//subtipos del cliente
		List listClienteSubTipo = new ArrayList();
		ClienteSubTipo clienteSubTipoPrincipal = null;
		if(clienteNuevo.getTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
			ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
			clienteSubTipo.setCodigoTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA);
			clienteSubTipo.setCodigoSubTipoCliente(Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA);		
			clienteSubTipo.setOidTipoCliente(clienteService.getOidTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA));
			clienteSubTipo.setOidSubTipoCliente(clienteService.getOidSubTipoCliente(
					clienteSubTipo.getOidTipoCliente().toString(), Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA));
			clienteSubTipo.setIndicadorPrincipal(new Integer(1));
			clienteSubTipoPrincipal = clienteSubTipo;
			listClienteSubTipo.add(clienteSubTipo);
			
			clienteSubTipo = new ClienteSubTipo();
			clienteSubTipo.setCodigoTipoCliente(clienteNuevo.getTipoCliente());
			clienteSubTipo.setCodigoSubTipoCliente(clienteNuevo.getSubCliente());		
			clienteSubTipo.setOidTipoCliente(clienteService.getOidTipoCliente(clienteNuevo.getTipoCliente()));
			clienteSubTipo.setOidSubTipoCliente(clienteService.getOidSubTipoCliente(
					clienteSubTipo.getOidTipoCliente().toString(), clienteNuevo.getSubCliente()));
			clienteSubTipo.setIndicadorPrincipal(new Integer(0));
			listClienteSubTipo.add(clienteSubTipo);

		} else {
			ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
			clienteSubTipo.setCodigoTipoCliente(clienteNuevo.getTipoCliente());
			clienteSubTipo.setCodigoSubTipoCliente(clienteNuevo.getSubCliente());		
			clienteSubTipo.setOidTipoCliente(clienteService.getOidTipoCliente(clienteNuevo.getTipoCliente()));
			clienteSubTipo.setOidSubTipoCliente(clienteService.getOidSubTipoCliente(
					clienteSubTipo.getOidTipoCliente().toString(), clienteNuevo.getSubCliente()));
			clienteSubTipo.setIndicadorPrincipal(new Integer(1));
			clienteSubTipoPrincipal = clienteSubTipo;
			listClienteSubTipo.add(clienteSubTipo);
		}	
		
		//VALIDAMOS DATOS DEL DOCUMENTO DE IDENTIDAD, ZONA y TERRITORIO
		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
		criteria.put("tipoDocumentoIdentidad", oidTipoDocumento);
		
		//NO SE REALIZA VALIDACION DE DOCUMENTO DE IDENTIDAD PARA DIFERENTES a '01'
		if(!Constants.MAE_TIPO_DOCUMENTO_PRINCIPAL.equals(clienteNuevo.getTipoDoc()))
			criteria.put("noValidarDocumentoWS", "noValidarDocumentoWS");
		
		String mensajeError = validarInsertarClienteWebService(criteria, clienteNuevo, listClienteSubTipo);
		
		if(StringUtils.isNotEmpty(mensajeError)) {
			throw new Exception(mensajeError);
		}

		//Obtenemos el Territorio y Territorio Administrativo
		String oidTerritorio = (String)criteria.get("oidTerritorio");
		String oidTerritorioAdministrativo = (String)criteria.get("oidTerritorioAdministrativo");

    	//Recuperamos el codigo de cliente y digito de control
    	//obtenemos un nuevo codigo de cliente y su digito de control
        Cliente cliente = new Cliente();
    	if(esCodigoClienteAutomatico) {
    		Map criteriaCodigo = new HashMap();
    		criteriaCodigo.put("codigoPais", codigoPais);
    		criteriaCodigo.put("longitudCodigoCliente", longitudCodigoCliente);
    		
    		Base baseCodigoCliente = clienteService.getNuevoCodigoCliente(criteriaCodigo);
    		
    		cliente.setCodigo(baseCodigoCliente.getCodigo());
    		cliente.setDigitoControl(baseCodigoCliente.getDescripcion());
    	} else {
    		log.info("Numero Documento : " + clienteNuevo.getNroDoc() + ", LongitudCodigoCliente: " + longitudCodigoCliente);
    		
    		String codigoClienteAux = completarCaracteres(clienteNuevo.getNroDoc(), Integer.parseInt(longitudCodigoCliente), "0");
    		log.info("Codigo Cliente Generado: " + codigoClienteAux);
    		
    		cliente.setCodigo(codigoClienteAux);
    		cliente.setDigitoControl("");
    	}

		//validamos que la fecha actual se encuentre dentro del rango de la campa�a de ingreso
		Map criteriaFechas = new HashMap();
		criteriaFechas.put("oidPeriodo", oidPeriodo);
		criteriaFechas.put("fecha", fechaActual);
		String fechaInicio = clienteService.getFechaInicioPeriodo(criteriaFechas);
		if(fechaInicio == null) {
			fechaActual = clienteService.getFechaInicioPeriodo(criteriaFechas);
		}

        //datos del cliente
        cliente.setCodigoPais(codigoPais);
        cliente.setOidPais(new Long(oidPais));
		cliente.setApellido1(clienteNuevo.getApePat());
		cliente.setApellido2(clienteNuevo.getApeMat());
		cliente.setNombre1(clienteNuevo.getPriNom());
		cliente.setNombre2(clienteNuevo.getSegNom());
		cliente.setTratamiento(clienteNuevo.getNomAbrev());
		cliente.setSexo(clienteNuevo.getSexo());
		cliente.setIndicadorFichaInscripcion(new Integer(0));
		cliente.setFechaIngreso(sdf.parse(clienteNuevo.getFecIng()));
		cliente.setOidFormaPago(clienteService.getOidFormaPagoSubTipoCliente(
									oidPais, clienteSubTipoPrincipal.getOidSubTipoCliente().toString()));
		cliente.setOidPeriodoIngreso(new Long(oidPeriodo));
		cliente.setCodigoUsuario(codigoUsuario);
		cliente.setUsuarioModifica(codigoUsuario);
		cliente.setIndicadorOrigen(clienteNuevo.getIndOrigen());
		obtenerCriteriosBusqueda(clienteService, cliente, oidPais);
		
		//clasificaciones del cliente
		//Agregamos la clasificacion por Defecto relacionado al SubTipoCliente
		for(int i=0; i<listClienteSubTipo.size();i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo)listClienteSubTipo.get(i);
			
			String tipoClasificacionDefault = clienteService.getTipoClasificacionDefault(clienteSubTipo.getCodigoTipoCliente(), 
					clienteSubTipo.getCodigoSubTipoCliente());	
	
			String clasificacionDefault = clienteService.getClasificacionDefault(clienteSubTipo.getCodigoTipoCliente(), 
					clienteSubTipo.getCodigoSubTipoCliente());
			
			StringTokenizer st = new StringTokenizer(tipoClasificacionDefault, "-");
			
			ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
			clienteClasificacion.setOidClienteSubTipo(new Long(st.nextToken()));
			clienteClasificacion.setOidTipoClasificacion(new Long(st.nextToken()));
			clienteClasificacion.setDescripcionTipoClasificacion(st.nextToken());
			
			StringTokenizer st2 = new StringTokenizer(clasificacionDefault, "-");
			clienteClasificacion.setOidClasificacion(new Long(st2.nextToken()));
			clienteClasificacion.setDescripcionClasificacion(st2.nextToken());
			clienteClasificacion.setOidPeriodo(new Long(oidPeriodo));
			clienteClasificacion.setFechaClasificacion(sdf.parse(clienteNuevo.getFecIng()));
			clienteClasificacion.setIndicadorPrincipal(new Integer(1));
			
			List listClienteClasificacion = new ArrayList();
			listClienteClasificacion.add(clienteClasificacion);
			clienteSubTipo.setListClienteClasificacion(listClienteClasificacion);
		}	
		cliente.setListClienteSubTipo(listClienteSubTipo);
		
		//datos adicionales del cliente
		ClienteAdicional clienteAdicional = new ClienteAdicional();
		clienteAdicional.setOidEstatusCliente(new Long(1));
		clienteAdicional.setIndicadorActivo(new Integer(clienteNuevo.getIndActiva()));
		clienteAdicional.setNumeroCampanasSinPedido(null);
		clienteAdicional.setFechaNacimiento(sdf.parse(clienteNuevo.getFecNac()));
		clienteAdicional.setEdad(StringUtil.calcularEdad(clienteNuevo.getFecNac()));
		clienteAdicional.setOidEstadoCivil(clienteService.getOidEstadoCivil(clienteNuevo.getEstCivil()));
		clienteAdicional.setOidPeriodoNivelRiesgo(null);
		clienteAdicional.setOidPeriodoLineaCredito(null);
		clienteAdicional.setCodigoCUB(clienteNuevo.getCUB());
		clienteAdicional.setOidNivelRiesgo(new Long(4));
		clienteAdicional.setMontoLineaCredito(new Double(0));
		
		if(StringUtils.isNotEmpty(clienteNuevo.getGradIns()))
			clienteAdicional.setOidNivelEstudios(clienteService.getOidGradoInstruccion(clienteNuevo.getGradIns()));
		if(StringUtils.isNotEmpty(clienteNuevo.getNacCliente()))
			clienteAdicional.setOidNacionalidad(clienteService.getOidNacionalidad(clienteNuevo.getNacCliente()));
		
		clienteAdicional.setOidPeriodoLineaCredito(new Long(oidPeriodo));
		clienteAdicional.setOidPeriodoNivelRiesgo(new Long(oidPeriodo));
		clienteAdicional.setIndicadorCorrespondencia(new Integer(1));
		
		if(Constants.SI.equals(clienteNuevo.getPaqDoc()) || Constants.UNO.equals(clienteNuevo.getPaqDoc()) 
				|| StringUtils.isEmpty(clienteNuevo.getPaqDoc()))
			clienteAdicional.setIndicadorImpresionPaqDoc(null);
		else
			clienteAdicional.setIndicadorImpresionPaqDoc(Constants.NO);
		
		boolean indicadorDocumentosLegalesAux = false;
		String indicadorDocumentosLegales = clienteService.getValorModuloxPaisTipoValidacion(codigoPais, 
												Constants.MAE_VALID_DOCUM_LEGAL);
		if(Constants.MAE_IMPRI_DOCUM_LEGAL.equals(indicadorDocumentosLegales)){
			indicadorDocumentosLegalesAux = true;
		}
		
		if(indicadorDocumentosLegalesAux){
			String codTipoDocLegal = clienteService.getCodigoTipoDocLegal(oidTipoDocumento);
			if(StringUtils.isNotEmpty(codTipoDocLegal)){
				if(codTipoDocLegal.equals(oidTipoDocumento))
					clienteAdicional.setIndicadorImpresionDocumentos("0");
				else
					clienteAdicional.setIndicadorImpresionDocumentos("1");
			}else
				clienteAdicional.setIndicadorImpresionDocumentos("1");		
		}else
			clienteAdicional.setIndicadorImpresionDocumentos("1");
		
		clienteAdicional.setCodGrupoFuncional(clienteNuevo.getCodGrupoFuncional());
		clienteAdicional.setDesGrupoFuncional(clienteNuevo.getDesGrupoFuncional());
		clienteAdicional.setUsuRed(clienteNuevo.getUsuRed());
		clienteAdicional.setCodJefeCUB(clienteNuevo.getCodJefeCUB());
		clienteAdicional.setValRelContr(clienteNuevo.getValRelContr());
		clienteAdicional.setNomJefeDir(clienteNuevo.getNomJefeDir());
		clienteAdicional.setValPueOrg(clienteNuevo.getValPueOrg());
		clienteAdicional.setCodigoEmpleado(clienteNuevo.getCodEmpleado());
		
		cliente.setClienteAdicional(clienteAdicional);
		
		//estatus inicial del cliente
		ClienteHistoricoEstatus clienteHistoricoEstatus = new ClienteHistoricoEstatus();
		clienteHistoricoEstatus.setOidEstatus(new Long(1));
		clienteHistoricoEstatus.setOidPeriodo(new Long(oidPeriodo));
		clienteHistoricoEstatus.setOidPeriodoFin(null);
		cliente.setClienteHistoricoEstatus(clienteHistoricoEstatus);
		
		//datos de identidad del cliente
		List listClienteIdentificacion = new ArrayList();
		ClienteIdentificacion clienteIdentificacion = new ClienteIdentificacion();
		clienteIdentificacion.setOidTipoDocumento(new Long(oidTipoDocumento));
		clienteIdentificacion.setNumeroDocumento(numeroDocumento);
		clienteIdentificacion.setIndicadorPrincipal(new Integer(1));
		clienteIdentificacion.setIdentificadorPersonal("P");
		listClienteIdentificacion.add(clienteIdentificacion);
		cliente.setListClienteIdentificacion(listClienteIdentificacion);
		
		//direcciones del cliente
		if(clienteNuevo.getLsDireccion() != null && clienteNuevo.getLsDireccion().length>0) {
			List listDireccionCliente = new ArrayList();
			
			for(int i=0; i<clienteNuevo.getLsDireccion().length; i++) {
				DireccionMAEWebService direccion = clienteNuevo.getLsDireccion()[i];
				ClienteDireccion clienteDireccion = new ClienteDireccion();
				clienteDireccion.setOidTipoDireccion(clienteService.getOidTipoDireccion(direccion.getTipoDir()));
				
				clienteDireccion.setOidTerritorio(new Long(oidTerritorio));
				clienteDireccion.setIndicadorEstandarizacionGIS("S");
				
				clienteDireccion.setOidTipoVia(clienteService.getOidTipoVia(direccion.getTipoVia()));
				clienteDireccion.setNumeroPrincipal(direccion.getNumPrinc());
				clienteDireccion.setNombreVia(direccion.getDireccion());
				clienteDireccion.setObservaciones(direccion.getObservaciones());
				clienteDireccion.setIndicadorDireccionPrincipal(new Integer(1));
				clienteDireccion.setIndicadorEliminacion(new Integer(0));
				
				clienteDireccion.setCodigoUnidadGeografica(direccion.getNivel1()+ direccion.getNivel2()+direccion.getNivel3());
				listDireccionCliente.add(clienteDireccion);
			}	
			
			cliente.setListClienteDireccion(listDireccionCliente);
		}
		
		//unidad administrativa del cliente
		ClienteUnidadAdministrativa clienteUnidadAdministrativa = new ClienteUnidadAdministrativa();
		clienteUnidadAdministrativa.setOidTerritorioAdministrativo(new Long(oidTerritorioAdministrativo));
		clienteUnidadAdministrativa.setIndicadorActivo(new Integer(1));
		clienteUnidadAdministrativa.setPeriodoInicio(new Long(oidPeriodo));
		clienteUnidadAdministrativa.setPeriodoFin(null);
		cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);
		
		//primer contacto del cliente
		ClientePrimerContacto clientePrimerContacto = new ClientePrimerContacto();
		clientePrimerContacto.setOidPeriodo(new Long(oidPeriodo));
		clientePrimerContacto.setOidMarca(oidMarca);
		clientePrimerContacto.setOidCanal(oidCanal);
		clientePrimerContacto.setCodigoTipoContacto("I");
		clientePrimerContacto.setFechaContacto(sdf.parse(fechaActual));
		
		String fechaSiguienteContacto = sdf.format(obtenerFechaAnnoSiguiente(clientePrimerContacto.getFechaContacto()));
		clientePrimerContacto.setFechaSiguienteContacto(sdf.parse(fechaSiguienteContacto));
		cliente.setClientePrimerContacto(clientePrimerContacto);
		
		//marca del cliente
		ClienteMarca clienteMarca = new ClienteMarca();
		clienteMarca.setIndicadorPrincipal(new Integer(1));
		clienteMarca.setOidMarca(oidMarca);
		cliente.setClienteMarca(clienteMarca);
	
		//Referencias del cliente
		cliente.setClienteReferencias(new ClienteReferencias());
		
		//Comunicaciones del cliente
		if(StringUtils.isNotEmpty(clienteNuevo.getMailBelcorp())){
			List listClienteComunicacion = new ArrayList();
			ClienteComunicacion clienteComunicacion = new ClienteComunicacion();
			Long oidTipoComunicacion = clienteService.getOidTipoComunicacion("MB");			
			
			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(clienteNuevo.getMailBelcorp());
			clienteComunicacion.setIndicadorPrincipal(new Integer(0));
			listClienteComunicacion.add(clienteComunicacion);
			cliente.setListClienteComunicacion(listClienteComunicacion);
		}
		
		//Insertamos el nuevo cliente
		try {
			clienteService.insertCliente(cliente);
		} catch(Exception ex) {
			log.error("error en insertCliente: " +ex.getMessage());
			throw  new Exception(this.messageSource.getMessage(
					"procesoMAEWebService.registrarCliente.msg.errorIngresoCliente",null,getLocaleIdioma(null)));
		}	
		
		return cliente.getCodigo();
    }
    
	/**
	 * @param valor
	 * @param longitud
	 * @param caracter
	 * @return
	 */
	private String completarCaracteres(String valor, int longitud, String caracter) {
		String valorAux = new String("");
		
		int faltante = longitud - valor.length();
			
		if (faltante >= 0) {
			for (int i = 0; i < faltante; i++) {
				valorAux = valorAux + caracter;
			}
			valorAux = valorAux + valor;
		}
		else {
		
			faltante = valor.length() - longitud;
			valorAux = valor.substring(faltante);
		}
		
		return valorAux;
	}

	/**
	 * @param clienteService
	 * @param cliente
	 * @param oidPais
	 */
	private void obtenerCriteriosBusqueda(MantenimientoMAEClienteService clienteService, Cliente cliente, String oidPais) {
		String criterioBusqueda1 = clienteService.getCriterioBusqueda1(oidPais);
		String criterioBusqueda2 = clienteService.getCriterioBusqueda2(oidPais);
        String resultado1 = null;
        String resultado2 = null;		
        
        log.debug("criterioBusqueda1 : " + criterioBusqueda1);
        log.debug("criterioBusqueda2 : " + criterioBusqueda2);
		
		if(criterioBusqueda1 != null) {
			if(criterioBusqueda1.equals("MAECLIEAPELL1"))
				resultado1 = cliente.getApellido1();
			if(criterioBusqueda1.equals("MAECLIEAPELL2"))
				resultado1 = cliente.getApellido2();
			if(criterioBusqueda1.equals("MAECLIEAPELLCA"))
				resultado1 = cliente.getApellidoCasada();
			if(criterioBusqueda1.equals("MAECLIENOM1"))
				resultado1 = cliente.getNombre1();
			if(criterioBusqueda1.equals("MAECLIENOM2"))
				resultado1 = cliente.getNombre2();
			if(criterioBusqueda1.equals("MAECLIETRAT"))
				resultado1 = cliente.getTratamiento();
			if(criterioBusqueda1.equals("MAECLIESEXO"))
				resultado1 = cliente.getSexo();
			if(criterioBusqueda1.equals("MAECLIEFECHING"))
				resultado1 = String.valueOf(cliente.getFechaIngreso().getTime());
			if(criterioBusqueda1.equals("MAECLIEFORMPA"))
				resultado1 = cliente.getOidFormaPago().toString();
		}
		
		if(criterioBusqueda2 != null) {
			if(criterioBusqueda2.equals("MAECLIEAPELL1"))
				resultado2 = cliente.getApellido1();
			if(criterioBusqueda2.equals("MAECLIEAPELL2"))
				resultado2 = cliente.getApellido2();
			if(criterioBusqueda2.equals("MAECLIEAPELLCA"))
				resultado2 = cliente.getApellidoCasada();
			if(criterioBusqueda2.equals("MAECLIENOM1"))
				resultado2 = cliente.getNombre1();
			if(criterioBusqueda2.equals("MAECLIENOM2"))
				resultado2 = cliente.getNombre2();
			if(criterioBusqueda2.equals("MAECLIETRAT"))
				resultado2 = cliente.getTratamiento();
			if(criterioBusqueda2.equals("MAECLIESEXO"))
				resultado2 = cliente.getSexo();
			if(criterioBusqueda2.equals("MAECLIEFECHING"))
				resultado2 = String.valueOf(cliente.getFechaIngreso().getTime());
			if(criterioBusqueda2.equals("MAECLIEFORMPA"))
				resultado2 = cliente.getOidFormaPago().toString();
		}

        log.debug("resultado1 : " + resultado1);
        log.debug("resultado2 : " + resultado2);
		
		cliente.setCriterioBusqueda1(resultado1);
		cliente.setCriterioBusqueda2(resultado2);
	}

	/**
	 * @param criteria
	 * @param clienteNuevo
	 * @param listClienteSubTipo
	 * @return
	 */
	private String validarInsertarClienteWebService(Map criteria, 
											ClienteNuevoMAEWebService clienteNuevo, List listClienteSubTipo) {
		Locale locale = new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		String mensajeError = "";
		String documento = clienteService.validarDocumentoIdentidad(criteria);

		if(documento != null) {
			if(documento.equals("Modulo10")) {
				mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValido",
	 					null,locale);
								
			} else if(documento.equals("Modulo11V")) {
				mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT",
	 					null,locale);
								
			} else {
				StringTokenizer st = new StringTokenizer(documento, "-");
				String saldo = st.nextToken();

				if(saldo.equals(" "))
					mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.ClienteExiste",
		 					null,locale);
				else
					mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.ClienteTieneCuentasCastigadas",
							new String[]{saldo},locale);
			}	
			
			return mensajeError;
		}
	
		criteria.put("mostrarUnidadAdministrativa","true");
		criteria.put("esDuplaCyzone","false");
		criteria.put("codigoUbigeo","false");
		criteria.put("confirmacionTerritorio","ok");
		criteria.put("listSubTipo", listClienteSubTipo);
		
		criteria.put("codigoZona", clienteNuevo.getCodZona());
		criteria.put("codigoTerritorio", clienteNuevo.getCodTerri());
		criteria.put("codigoCUB", clienteNuevo.getCUB());
    	
		List erroresEncontrados = clienteService.validarDatosCliente(criteria);
		if(erroresEncontrados.size()>0) {
			Iterator it = erroresEncontrados.iterator();
			String error = (String)it.next();
			
			mensajeError = messageSource.getMessage(error, null,locale);
		} 
		
		return mensajeError;
	}

	/**
	 * Calcula la fecha proyectada en un a�o 
	 * 
	 * @param date
	 * @return
	 */
	private Date obtenerFechaAnnoSiguiente(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.add(Calendar.YEAR, 1);
		Date fechaAnnoSiguiente = calendar.getTime();

		return fechaAnnoSiguiente;
	}
    
	/**
	 * @param clienteNuevo
	 * @return
	 * @throws Exception
	 */
	private String updateClienteWebSevice(ClienteNuevoMAEWebService clienteNuevo) throws Exception {
		Long oidMarca = clienteService.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));
		String codigoUsuario = "ADMIN_WS";
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", clienteNuevo.getCodPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
				
		//Datos relacionados al pais
  		String codigoPais = clienteNuevo.getCodPais();
  		String oidPais = clienteService.getOidPais(criteria);
  		criteria.put("oidPais",oidPais);
		
		//Obtenemos la longitud de Codigo Cliente y si el pais gestiona en forma manual o automatico
		//la generacion de codigo de cliente
		String longitudCodigoCliente = clienteService.getLongitudCodigoCliente(criteria);
		boolean esCodigoClienteAutomatico = clienteService.isCodigoClienteAutomatico(criteria);
		
		//Verificamos si se tiene que completar con ceros el numero de documento de identidad
		boolean permitirCompletarCerosIdentidad = true;
		if(clienteService.getValorModuloxPaisTipoValidacion(codigoPais, Constants.MAE_VALID_COMPLETA_CEROS_IDENTIDAD) != null)
			permitirCompletarCerosIdentidad = false;

		//Obtenemos el oid Tipo de documento de identidad
		String oidTipoDocumento = "";
		String numeroDocumento = "";
		int longitudTipoDocumento = 0;
		if(StringUtils.isNotEmpty(clienteNuevo.getTipoDoc())) {
			oidTipoDocumento = clienteService.getOidTipoDocumento(oidPais, clienteNuevo.getTipoDoc());

			//Obtenemos longitud de Tipo de Documento de Identidad
			longitudTipoDocumento = Integer.parseInt(clienteService.getLongitudTipoDocumento(oidPais, oidTipoDocumento));
		}	

		//Obtenemos el Nro Documento de Identidad
		if(StringUtils.isNotEmpty(clienteNuevo.getNroDoc()))
			if(permitirCompletarCerosIdentidad)
				numeroDocumento = completarCaracteres(clienteNuevo.getNroDoc(),longitudTipoDocumento,"0");
			else
				numeroDocumento = clienteNuevo.getNroDoc();
		
		//Obtenemos el periodo Actual
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria);
		
		//RECUPERAMOS DATOS DEL CLIENTE
        Cliente cliente = obtenerDatosCliente(codigoPais, clienteNuevo.getCodConsultora()); 
        ClienteAdicional clienteAdicional = cliente.getClienteAdicional();

        //SUBTIPOS DEL CLIENTE
        if(StringUtils.isNotEmpty(clienteNuevo.getTipoCliente())) {
        	List listClienteSubTipo = cliente.getListClienteSubTipo();
        	
        	boolean esGerente = false;
        	boolean esConsultoraNegocio = false;
        	boolean registrar = true;
			Long oidTipoCliente = clienteService.getOidTipoCliente(clienteNuevo.getTipoCliente());
			Long oidSubTipoCliente = clienteService.getOidSubTipoCliente(oidTipoCliente.toString(), clienteNuevo.getSubCliente());
			
			for(int j=0; j<listClienteSubTipo.size(); j++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo)listClienteSubTipo.get(j);
				
				if(clienteSubTipoAux.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
					esGerente = true;
				}
				if(Constants.MAE_TIPO_CLIENTE_CONSULTORA.equals(clienteSubTipoAux.getCodigoTipoCliente()) &&
						Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO.equals(clienteSubTipoAux.getCodigoSubTipoCliente()))  {
					esConsultoraNegocio = true;
				}
			}
			
        	ClienteSubTipo clienteSubTipo = new ClienteSubTipo();
			for(int j=0; j<listClienteSubTipo.size(); j++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo)listClienteSubTipo.get(j);
				
				if(clienteSubTipoAux.getOidTipoCliente().longValue() == oidTipoCliente.longValue()) {
					if(clienteSubTipoAux.getOidSubTipoCliente().longValue() == oidSubTipoCliente.longValue()) {
						clienteSubTipo = clienteSubTipoAux;
						registrar = false;
					} else {
						clienteSubTipoAux.setEliminar(true);
						
						//Si el subTipoCliente ha sido eliminado, tambien sera eliminado sus clasificaciones
						Iterator itClasificaciones = clienteSubTipoAux.getListClienteClasificacion().iterator();
						while(itClasificaciones.hasNext()) {
							ClienteClasificacion clienteClasificacion = (ClienteClasificacion)itClasificaciones.next();
							
							clienteClasificacion.setEliminar(true);
						}
					}	
				}
			}
			
			//Si se manda Consultora/Oficina y se tiene Gerente, se quiere dar de baja la promocion y
			//y se elimina el subtipo de Gerente
			if(!registrar) {
				if(Constants.MAE_TIPO_CLIENTE_CONSULTORA.equals(clienteNuevo.getTipoCliente()) &&
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA.equals(clienteNuevo.getSubCliente()))  {
					
					if(esGerente) {
						for(int j=0; j<listClienteSubTipo.size(); j++) {
							ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo)listClienteSubTipo.get(j);
							
							if(clienteSubTipoAux.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
								clienteSubTipoAux.setEliminar(true);
								
								//Si el subTipoCliente ha sido eliminado, tambien sera eliminado sus clasificaciones
								Iterator itClasificaciones = clienteSubTipoAux.getListClienteClasificacion().iterator();
								while(itClasificaciones.hasNext()) {
									ClienteClasificacion clienteClasificacion = (ClienteClasificacion)itClasificaciones.next();
									
									clienteClasificacion.setEliminar(true);
								}
							}
						}
					}
					
				}
			}
			
			if(registrar) {
				//Si se manda Gerente y se tiene registrado Consultora/Negocio, no se permite la promocion
				if(Constants.MAE_TIPO_CLIENTE_GERENTE.equals(clienteNuevo.getTipoCliente()) && esConsultoraNegocio) {
					throw  new Exception(this.messageSource.getMessage(
							"procesoMAEWebService.actualizarCliente.msg.subTipoClientePromocionGerente",null,getLocaleIdioma(null)));
				}
			
				//Si se manda Consultora/Negocio, indica que se quiere cambiar su Consultora/Oficina a Consultora/Negocio
				//pero tiene tambien el subtipo Gerente, no se permite el cambio de Subtipo
				if(Constants.MAE_TIPO_CLIENTE_CONSULTORA.equals(clienteNuevo.getTipoCliente()) &&
					Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO.equals(clienteNuevo.getSubCliente()))  {
					
					if(esGerente) {
						throw  new Exception(this.messageSource.getMessage(
								"procesoMAEWebService.actualizarCliente.msg.subTipoClienteCambioNegocio",null,getLocaleIdioma(null)));
					}
					
				}	
				
				clienteSubTipo.setCodigoTipoCliente(clienteNuevo.getTipoCliente());
		  		clienteSubTipo.setCodigoSubTipoCliente(clienteNuevo.getSubCliente());		
		  		clienteSubTipo.setOidTipoCliente(clienteService.getOidTipoCliente(clienteNuevo.getTipoCliente()));
		  		clienteSubTipo.setOidSubTipoCliente(clienteService.getOidSubTipoCliente(
		  				clienteSubTipo.getOidTipoCliente().toString(), clienteNuevo.getSubCliente()));
				
		  		clienteSubTipo.setIndicadorPrincipal(new Integer(1));
		  		clienteSubTipo.setOidCliente(cliente.getOid());
		  		
		  		//clasificaciones del cliente
				//Agregamos la clasificacion por Defecto relacionado al SubTipoCliente
				String tipoClasificacionDefault = clienteService.getTipoClasificacionDefault(clienteSubTipo.getCodigoTipoCliente(), 
													clienteSubTipo.getCodigoSubTipoCliente());	

				String clasificacionDefault = clienteService.getClasificacionDefault(clienteSubTipo.getCodigoTipoCliente(), 
													clienteSubTipo.getCodigoSubTipoCliente());
				
				StringTokenizer st = new StringTokenizer(tipoClasificacionDefault, "-");
				
				ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
				clienteClasificacion.setOidClienteSubTipo(new Long(st.nextToken()));
				clienteClasificacion.setOidTipoClasificacion(new Long(st.nextToken()));
				clienteClasificacion.setDescripcionTipoClasificacion(st.nextToken());
				
				StringTokenizer st2 = new StringTokenizer(clasificacionDefault, "-");
				clienteClasificacion.setOidClasificacion(new Long(st2.nextToken()));
				clienteClasificacion.setDescripcionClasificacion(st2.nextToken());
				
				//Obtenemos el periodo Actual
				clienteClasificacion.setOidPeriodo(new Long(periodos[0].getValue()));
				
				if(StringUtils.isNotEmpty(clienteNuevo.getFecIng()))
					clienteClasificacion.setFechaClasificacion(sdf.parse(clienteNuevo.getFecIng()));
				else
					clienteClasificacion.setFechaClasificacion(cliente.getFechaIngreso());
				
				clienteClasificacion.setIndicadorPrincipal(new Integer(1));
				
				List listClienteClasificacion = new ArrayList();
				listClienteClasificacion.add(clienteClasificacion);
				clienteSubTipo.setListClienteClasificacion(listClienteClasificacion);
				cliente.setListClienteSubTipo(listClienteSubTipo);
				
				clienteSubTipo.setCodigoUsuario(codigoUsuario);
				listClienteSubTipo.add(clienteSubTipo);
			}
			
			//Si es tipoCliente = Consultora, seteamos su indicador Principal a 1, los demas a 0 
			for(int i=0; i<listClienteSubTipo.size(); i++) {
				ClienteSubTipo clienteSubTipoAux = (ClienteSubTipo)listClienteSubTipo.get(i);
				
				if(clienteSubTipoAux.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA))
					clienteSubTipoAux.setIndicadorPrincipal(new Integer(1));
				else
					clienteSubTipoAux.setIndicadorPrincipal(new Integer(0));
			}

		}
        
        List listClienteSubTipo = cliente.getListClienteSubTipo();
        String numeroDocumentoAnterior = "";
        
        //OBTENEMOS EL NUMERO DE DOCUMENTO DEL CLIENTE
  		for(int i=0; i<cliente.getListClienteIdentificacion().size(); i++) {
  			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion)cliente.getListClienteIdentificacion().get(i);
  			
  			if (clienteIdentificacion.getIndicadorPrincipal().intValue()==1) { //Representa el documento principal de la consultora
  				numeroDocumentoAnterior = clienteIdentificacion.getNumeroDocumento();
  				break; 
  			} 
  		}
      		
  		//VALIDAMOS DATOS DEL DOCUMENTO DE IDENTIDAD, ZONA y TERRITORIO
  		criteria.put("numeroDocumentoIdentidad", numeroDocumento);
  		criteria.put("tipoDocumentoIdentidad", oidTipoDocumento);
  		
  		//NO SE REALIZA VALIDACION DE DOCUMENTO DE IDENTIDAD PARA DIFERENTES a '01'
  		if(!Constants.MAE_TIPO_DOCUMENTO_PRINCIPAL.equals(clienteNuevo.getTipoDoc()))
			criteria.put("noValidarDocumentoWS", "noValidarDocumentoWS");
  		
  		String mensajeError = validarActualizarClienteWebService(criteria, clienteNuevo, listClienteSubTipo,
  				numeroDocumentoAnterior, numeroDocumento, clienteAdicional.getCodigoCUB(), 
  				cliente.getClienteUnidadAdministrativa().getCodigoZona(), cliente.getClienteUnidadAdministrativa().getCodigoTerritorio());
  		
  		if(StringUtils.isNotEmpty(mensajeError)) {
  			throw new Exception(mensajeError);
  		}

  		//Obtenemos el Territorio y Territorio Administrativo
  		String oidTerritorio = (String)criteria.get("oidTerritorio");
  		String oidTerritorioAdministrativo = (String)criteria.get("oidTerritorioAdministrativo");
        
        //DATOS DEL CLIENTE
  		if(StringUtils.isNotEmpty(clienteNuevo.getApePat()))
  			cliente.setApellido1(clienteNuevo.getApePat());
  		if(StringUtils.isNotEmpty(clienteNuevo.getApeMat()))
  			cliente.setApellido2(clienteNuevo.getApeMat());
  		if(StringUtils.isNotEmpty(clienteNuevo.getPriNom()))
  			cliente.setNombre1(clienteNuevo.getPriNom());
  		if(StringUtils.isNotEmpty(clienteNuevo.getSegNom()))
  			cliente.setNombre2(clienteNuevo.getSegNom());
  		if(StringUtils.isNotEmpty(clienteNuevo.getNomAbrev()))
  			cliente.setTratamiento(clienteNuevo.getNomAbrev());
  		if(StringUtils.isNotEmpty(clienteNuevo.getSexo()))
  			cliente.setSexo(clienteNuevo.getSexo());
  		if(StringUtils.isNotEmpty(clienteNuevo.getFecIng()))
  			cliente.setFechaIngreso(sdf.parse(clienteNuevo.getFecIng()));
  		if(StringUtils.isNotEmpty(clienteNuevo.getIndOrigen()))
  			cliente.setIndicadorOrigen(clienteNuevo.getIndOrigen());

		cliente.setCodigoUsuario(codigoUsuario);
		cliente.setUsuarioModifica(codigoUsuario);

		obtenerCriteriosBusqueda(clienteService, cliente, oidPais);
		
		//DATOS ADICIONALES DEL CLIENTE
		if(StringUtils.isNotEmpty(clienteNuevo.getIndActiva()))
			clienteAdicional.setIndicadorActivo(new Integer(clienteNuevo.getIndActiva()));
		if(StringUtils.isNotEmpty(clienteNuevo.getFecNac()))
			clienteAdicional.setFechaNacimiento(sdf.parse(clienteNuevo.getFecNac()));
		if(StringUtils.isNotEmpty(clienteNuevo.getFecNac()))
			clienteAdicional.setEdad(StringUtil.calcularEdad(clienteNuevo.getFecNac()));
		if(StringUtils.isNotEmpty(clienteNuevo.getEstCivil()))
			clienteAdicional.setOidEstadoCivil(clienteService.getOidEstadoCivil(clienteNuevo.getEstCivil()));
		if(StringUtils.isNotEmpty(clienteNuevo.getCUB()))
			clienteAdicional.setCodigoCUB(clienteNuevo.getCUB());
		
		if(StringUtils.isNotEmpty(clienteNuevo.getGradIns()))
			clienteAdicional.setOidNivelEstudios(clienteService.getOidGradoInstruccion(clienteNuevo.getGradIns()));
		if(StringUtils.isNotEmpty(clienteNuevo.getNacCliente()))
			clienteAdicional.setOidNacionalidad(clienteService.getOidNacionalidad(clienteNuevo.getNacCliente()));
		
		if(StringUtils.isNotEmpty(clienteNuevo.getPaqDoc())) {
			if(Constants.SI.equals(clienteNuevo.getPaqDoc()) || Constants.UNO.equals(clienteNuevo.getPaqDoc()) 
					|| StringUtils.isEmpty(clienteNuevo.getPaqDoc()))
				clienteAdicional.setIndicadorImpresionPaqDoc(null);
			else
				clienteAdicional.setIndicadorImpresionPaqDoc(Constants.NO);
		}
		
		if(StringUtils.isNotEmpty(oidTipoDocumento)) {
			boolean indicadorDocumentosLegalesAux = false;
			String indicadorDocumentosLegales = clienteService.getValorModuloxPaisTipoValidacion(codigoPais, 
													Constants.MAE_VALID_DOCUM_LEGAL);
			if(Constants.MAE_IMPRI_DOCUM_LEGAL.equals(indicadorDocumentosLegales)){
				indicadorDocumentosLegalesAux = true;
			}
			
			if(indicadorDocumentosLegalesAux){
				String codTipoDocLegal = clienteService.getCodigoTipoDocLegal(oidTipoDocumento);
				if(StringUtils.isNotEmpty(codTipoDocLegal)){
					if(codTipoDocLegal.equals(oidTipoDocumento))
						clienteAdicional.setIndicadorImpresionDocumentos("0");
					else
						clienteAdicional.setIndicadorImpresionDocumentos("1");
				}else
					clienteAdicional.setIndicadorImpresionDocumentos("1");		
			}else
				clienteAdicional.setIndicadorImpresionDocumentos("1");
		}
		
		if(StringUtils.isNotEmpty(clienteNuevo.getCodGrupoFuncional()))
			clienteAdicional.setCodGrupoFuncional(clienteNuevo.getCodGrupoFuncional());
		if(StringUtils.isNotEmpty(clienteNuevo.getDesGrupoFuncional()))
			clienteAdicional.setDesGrupoFuncional(clienteNuevo.getDesGrupoFuncional());
		if(StringUtils.isNotEmpty(clienteNuevo.getUsuRed()))
			clienteAdicional.setUsuRed(clienteNuevo.getUsuRed());
		if(StringUtils.isNotEmpty(clienteNuevo.getCodJefeCUB()))
			clienteAdicional.setCodJefeCUB(clienteNuevo.getCodJefeCUB());
		if(StringUtils.isNotEmpty(clienteNuevo.getValRelContr()))
			clienteAdicional.setValRelContr(clienteNuevo.getValRelContr());
		if(StringUtils.isNotEmpty(clienteNuevo.getNomJefeDir()))
			clienteAdicional.setNomJefeDir(clienteNuevo.getNomJefeDir());
		if(StringUtils.isNotEmpty(clienteNuevo.getValPueOrg()))
			clienteAdicional.setValPueOrg(clienteNuevo.getValPueOrg());
		if(StringUtils.isNotEmpty(clienteNuevo.getCodEmpleado()))
			clienteAdicional.setCodigoEmpleado(clienteNuevo.getCodEmpleado());
		
		cliente.setClienteAdicional(clienteAdicional);

		//DATOS DE IDENTIDAD DEL CLIENTE
		for(int i=0; i<cliente.getListClienteIdentificacion().size(); i++) {
			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion)cliente.getListClienteIdentificacion().get(i);
			
			if (clienteIdentificacion.getIndicadorPrincipal().intValue()==1) { //Representa el documento principal de la consultora
				if(StringUtils.isNotEmpty(oidTipoDocumento)) 
					clienteIdentificacion.setOidTipoDocumento(new Long(oidTipoDocumento));
				if(StringUtils.isNotEmpty(numeroDocumento))
					clienteIdentificacion.setNumeroDocumento(numeroDocumento);
			} 
			
			if(StringUtils.isNotEmpty(oidTipoDocumento) || StringUtils.isNotEmpty(numeroDocumento)) 
				clienteIdentificacion.setCodigoUsuario(codigoUsuario);
		}	
		
		
		//DIRECCIONES DEL CLIENTE
		if(clienteNuevo.getLsDireccion() != null && clienteNuevo.getLsDireccion().length>0) {
			List listDireccionCliente = cliente.getListClienteDireccion();
			
			Integer indDireccionPrincipal = new Integer(1);
			if(cliente.getListClienteDireccion().size() > 0)
				indDireccionPrincipal = new Integer(0);
			
			for(int i=0; i<clienteNuevo.getLsDireccion().length; i++) {
				DireccionMAEWebService direccion = clienteNuevo.getLsDireccion()[i];
				
				boolean registrar = true;
				Long oidTipoDireccion = clienteService.getOidTipoDireccion(direccion.getTipoDir());
				
				ClienteDireccion clienteDireccion = new ClienteDireccion();
				for(int j=0; j<listDireccionCliente.size(); j++) {
					ClienteDireccion clienteDireccionAux = (ClienteDireccion)listDireccionCliente.get(j);
					
					if(clienteDireccionAux.getOidTipoDireccion().longValue() == oidTipoDireccion.longValue()) {
						clienteDireccion = clienteDireccionAux;
						registrar = false;
					}
				}
				
				clienteDireccion.setOidTipoDireccion(oidTipoDireccion);
				if(StringUtils.isNotEmpty(oidTerritorio)) 
					clienteDireccion.setOidTerritorio(new Long(oidTerritorio)); //REVISAR
				
				if(StringUtils.isNotEmpty(direccion.getTipoVia()))
					clienteDireccion.setOidTipoVia(clienteService.getOidTipoVia(direccion.getTipoVia()));
				
				if(StringUtils.isNotEmpty(direccion.getNumPrinc()))
					clienteDireccion.setNumeroPrincipal(direccion.getNumPrinc());
				
				if(StringUtils.isNotEmpty(direccion.getDireccion()))
					clienteDireccion.setNombreVia(direccion.getDireccion());
					
				if(StringUtils.isNotEmpty(direccion.getObservaciones()))
					clienteDireccion.setObservaciones(direccion.getObservaciones());
				
				if(StringUtils.isNotEmpty(direccion.getNivel1())) {
					clienteDireccion.setCodigoUnidadGeografica(direccion.getNivel1()+ direccion.getNivel2()+direccion.getNivel3());
				}	
				
				if(registrar) {
					clienteDireccion.setIndicadorDireccionPrincipal(indDireccionPrincipal);
					clienteDireccion.setIndicadorEliminacion(new Integer(0));
					clienteDireccion.setIndicadorEstandarizacionGIS("S");
					
					listDireccionCliente.add(clienteDireccion);
					
					indDireccionPrincipal = new Integer(0);
				}
			}	
			
			cliente.setListClienteDireccion(listDireccionCliente);
		}
		
		//UNIDAD ADMINISTRATIVA DEL CLIENTE
		boolean cambioZonaTerritorio = false;
		
		if(StringUtils.isNotEmpty(clienteNuevo.getCodZona()) && StringUtils.isNotEmpty(clienteNuevo.getCodTerri())) {
			if(!cliente.getClienteUnidadAdministrativa().getCodigoZona().equals(clienteNuevo.getCodZona()) ||
					!cliente.getClienteUnidadAdministrativa().getCodigoTerritorio().equals(clienteNuevo.getCodTerri())) {
				cambioZonaTerritorio = true;		
			}		
		}
		
		if(cambioZonaTerritorio) {
			String oidPeriodo = obtenerPeriodoActualCambioZonaTerritorio(cliente, clienteNuevo.getCodZona());
			
			//VERIFICAMOS SI LA CONSULTORA PASO PEDIDOS EN PERIODOS VIGENTES
			String[] periodoVig = new String [periodos.length];
			for(int i=0;i<periodos.length;i++) {
				periodoVig[i] = periodos[i].getValue();
			}
			criteria.put("oidCliente", cliente.getOid().toString());
			criteria.put("listPeriodos", periodoVig); 
	 		boolean consultoraPasoPedido = clienteService.esClienteHaFacturadoPeriodos(criteria);

	 		ClienteUnidadAdministrativa clienteUnidadAdministrativaOld = cliente.getClienteUnidadAdministrativa();
			ClienteUnidadAdministrativa clienteUnidadAdministrativaNew = new ClienteUnidadAdministrativa();
			
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("oidPeriodo", oidPeriodo);
			Base basePeriodoFin =  clienteService.getPeriodoAnterior(criteriaPeriodo);
			String oidPeriodoFin = basePeriodoFin.getCodigo();
			criteriaPeriodo.put("oidPeriodo", oidPeriodoFin);
			
			boolean esPeriodoFinCerrado = clienteService.esPeriodoCerrado(criteriaPeriodo);
			Integer indActivoUANuevo;
			Integer indActivoUAAnterior;
			
			if (!esPeriodoFinCerrado && consultoraPasoPedido) {
	            indActivoUANuevo = new Integer(0);
	            indActivoUAAnterior = new Integer(1);
	        } else {
	            indActivoUANuevo = new Integer(1);
	            indActivoUAAnterior = new Integer(0);
	        }  

			//Actualizamos el periodo Fin de la actual unidad administrativa
			if(clienteUnidadAdministrativaOld != null) {
				clienteUnidadAdministrativaOld.setPeriodoFin(new Long(oidPeriodoFin));
				clienteUnidadAdministrativaOld.setIndicadorActivo(indActivoUAAnterior);
			
				//si es registrada y no paso pedido, que limpie la unidad administrativa
				if(cliente.getClienteAdicional().getCodigoStatus().equals(Constants.MAE_ESTADO_REGISTRADA) && !consultoraPasoPedido)
					clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(true); 
				else  //verificamos si el periodo de inicio de la unidad administrativa ultima, es mayor o igual al periodo vigente
					clienteUnidadAdministrativaOld.setEsPeriodoInicioMayorIgualPeriodoVigente(
							validarPeriodoInicioMayorAPeriodoVigente(clienteUnidadAdministrativaOld.getPeriodoInicio().toString(), 
											oidPeriodo, periodos));
			}	
						

			//creamos una nueva unidad administrativa
			clienteUnidadAdministrativaNew.setOidCliente(cliente.getOid());
			clienteUnidadAdministrativaNew.setOidTerritorioAdministrativo(new Long(oidTerritorioAdministrativo));
			clienteUnidadAdministrativaNew.setPeriodoInicio(new Long(oidPeriodo));
			clienteUnidadAdministrativaNew.setPeriodoFin(null);
			clienteUnidadAdministrativaNew.setIndicadorActivo(indActivoUANuevo);
			clienteUnidadAdministrativaNew.setCodigoZona(clienteNuevo.getCodZona());
			
			cliente.setClienteUnidadAdministrativaNew(clienteUnidadAdministrativaNew);
		}

		//COMUNICACIONES DEL CLIENTE
		if(StringUtils.isNotEmpty(clienteNuevo.getMailBelcorp()))
			verificarComunicacion(clienteService, cliente, "MB", clienteNuevo.getMailBelcorp());
		
		//Actualizamos los datos del cliente
		try {
			clienteService.updateCliente(cliente);
		} catch(Exception ex) {
			log.error("error en updateCliente: " +ex.getMessage());
			throw  new Exception(this.messageSource.getMessage(
					"procesoMAEWebService.actualizarCliente.msg.errorActualizarCliente",null,getLocaleIdioma(null)));
		}	

		return cliente.getCodigo();
    }

	/**
	 * @param criteria
	 * @param clienteNuevo
	 * @param listClienteSubTipo
	 * @param numeroDocumentoAnterior
	 * @param numeroDocumento
	 * @param codigoCUBAnterior
	 * @param zonaAnterior
	 * @param territorioAnterior
	 * @return
	 */
	private String validarActualizarClienteWebService(Map criteria, 
			ClienteNuevoMAEWebService clienteNuevo, List listClienteSubTipo, 
			String numeroDocumentoAnterior, String numeroDocumento, String codigoCUBAnterior,
			String zonaAnterior, String territorioAnterior) {
		Locale locale = new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		String mensajeError = "";
		
		if(StringUtils.isNotEmpty(numeroDocumento) && !numeroDocumentoAnterior.equals(numeroDocumento)) {
			String documento = clienteService.validarDocumentoIdentidad(criteria);
	
			if(documento != null) {
				if(documento.equals("Modulo10")) {
					mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValido",
		 					null,locale);
									
				} else if(documento.equals("Modulo11V")) {
					mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.DocIdentidadNoValidoRUT",
		 					null,locale);
									
				} else {
					StringTokenizer st = new StringTokenizer(documento, "-");
					String saldo = st.nextToken();
	
					if(saldo.equals(" "))
						mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.ClienteExiste",
			 					null,locale);
					else
						mensajeError = messageSource.getMessage("mantenimientoMAEClienteForm.msg.ClienteTieneCuentasCastigadas",
								new String[]{saldo},locale);
				}
				
				return mensajeError;
			}
		}
			
		criteria.put("mostrarUnidadAdministrativa","true");
		criteria.put("esDuplaCyzone","false");
		criteria.put("codigoUbigeo","false");
		criteria.put("confirmacionTerritorio","ok");
		criteria.put("listSubTipo", listClienteSubTipo);
		
		if(StringUtils.isNotEmpty(clienteNuevo.getCodZona()) && StringUtils.isNotEmpty(clienteNuevo.getCodTerri())) {
			criteria.put("codigoZona", clienteNuevo.getCodZona());
			criteria.put("codigoTerritorio", clienteNuevo.getCodTerri());
		} else {
			criteria.put("codigoZona", zonaAnterior);
			criteria.put("codigoTerritorio", territorioAnterior);
		}
		
		if(StringUtils.isNotEmpty(clienteNuevo.getCUB())) {
			criteria.put("codigoCUB", clienteNuevo.getCUB());
			criteria.put("codigoCUBAnterior", codigoCUBAnterior);
		}
		
    	
		List erroresEncontrados = clienteService.validarDatosCliente(criteria);
		if(erroresEncontrados.size()>0) {
			Iterator it = erroresEncontrados.iterator();
			String error = (String)it.next();
			
			mensajeError = messageSource.getMessage(error, null,locale);
		} 
		
		return mensajeError;
	}
	
	/**
	 * @param oidPeriodoInicio
	 * @param oidPeriodoVigente
	 * @param listPeriodosVigentes
	 * @return
	 */
	private boolean validarPeriodoInicioMayorAPeriodoVigente(String oidPeriodoInicio, String oidPeriodoVigente, 
								LabelValue[] listPeriodosVigentes) {
		LabelValue basePeriodoVigente = listPeriodosVigentes[0];
		boolean esPeriodoMayorIgual = false;
		
		String codigoPeriodoInicio = clienteService.getCodigoPeriodoByOidPeriodo(oidPeriodoInicio);
		
		if(codigoPeriodoInicio.compareTo(basePeriodoVigente.getLabel()) >= 0 ) {
			esPeriodoMayorIgual = true;
		}
		
		return esPeriodoMayorIgual;
	}
	
	/**
	 * @param cliente
	 * @param codigoZona
	 * @return
	 */
	private String obtenerPeriodoActualCambioZonaTerritorio(Cliente cliente, String codigoZona) {
	    Base basePeriodoRegionZonaSiguiente = null;
        
		String oidPeriodo = "";
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", cliente.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoZona", codigoZona);
		
		//RECUPERAMOS LOS PERIODOS VIGENTES
		LabelValue[] periodos = clienteService.getPeriodosVigentesByPaisMarcaCanal(criteria); 
		
		//OBTENEMOS EL ULTIMO PERIODO DE CIERRE DE ZONA Y REGION
		for(int i=0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];
		
			criteria.put("codigoPeriodo", periodo.getLabel());
			boolean esRegionCerrada = clienteService.esRegionCerradaxZona(criteria);
			
			if(!esRegionCerrada) {
				boolean esZonaCerrada = clienteService.esZonaCerrada(criteria);
				
				if(!esZonaCerrada) {
					basePeriodoRegionZonaSiguiente = new Base();
					basePeriodoRegionZonaSiguiente.setCodigo(periodo.getValue());
					basePeriodoRegionZonaSiguiente.setDescripcion(periodo.getLabel());
					
					log.debug("obtenerPeriodoActual: (Periodo sin cierre de region/zona) :" + periodo.getLabel());
					oidPeriodo = periodo.getValue();
					break;
				}	
			}	
		}
		
		//VERIFICAMOS SI LA CONSULTORA PASO PEDIDOS EN PERIODOS VIGENTES
		String[] periodoVig = new String [periodos.length];
		for(int i=0;i<periodos.length;i++) {
			periodoVig[i] = periodos[i].getValue();
		}
		criteria.put("oidCliente", cliente.getOid().toString());
		criteria.put("listPeriodos", periodoVig); 
 		boolean pasoPedido = clienteService.esClienteHaFacturadoPeriodos(criteria);
 		
		//SI LA REGION / ZONA DESTINO NO CERRO Y LA CONSULTORA NO FACTURO EN PERIODOS VIGENTES
		if((periodos[0].getValue().equals(oidPeriodo)) && !pasoPedido) {
			log.debug("obtenerPeriodoActual: Periodo Inicio es el menor de los Periodos Vigentes");
			
			//Periodo Inicio es el menor de los vigentes.
			oidPeriodo = periodos[0].getValue();
			
		} else {
			
			//SI REGION/ZONA DESTINO CERRO
			if(!periodos[0].getValue().equals(oidPeriodo) && !pasoPedido) {
				log.debug("obtenerPeriodoActual: (Region/Zona Destino CerroPeriodo Inicio es el menor de los Periodos Vigentes");
				
				//Periodo Inicio es el menor de los vigentes.
				oidPeriodo = periodos[0].getValue();
				
			}
			else {  //LA CONSULTORA FACTURO EN PERIODO VIGENTE
				log.debug("obtenerPeriodoActual: (Consultora Facturo en Periodo Vigente)");
				Base basePeriodoSiguiente = null;
				
				//PERIODO INICIO ES EL MAYOR PERIODOS DE LOS SIGUIENTES:
				if(basePeriodoRegionZonaSiguiente == null)
					basePeriodoRegionZonaSiguiente = clienteService.getSiguientePeriodo(criteria);
				
				Base basePeriodoFacturadoSiguiente = null;
				String ultimoPeriodoPedido = clienteService.getUltimoPeriodoFacturado(cliente.getOid().toString());
				log.debug("obtenerPeriodoActual: Oid Ultimo Periodo Facturado : " + ultimoPeriodoPedido);
				
				if(ultimoPeriodoPedido != null) {
					criteria.remove("codigoPeriodo");
					criteria.put("oidPeriodo", ultimoPeriodoPedido);
					basePeriodoFacturadoSiguiente = clienteService.getSiguientePeriodo(criteria);
				}	
				
				if(basePeriodoFacturadoSiguiente == null) {
					log.debug("obtenerPeriodoActual: Ultimo Periodo Facturado : Nulo");
					
					oidPeriodo = basePeriodoRegionZonaSiguiente.getCodigo();
					basePeriodoSiguiente = basePeriodoRegionZonaSiguiente;
					
				} else {
					log.debug("obtenerPeriodoActual: (Periodo RegionZona Siguiente) : " + basePeriodoRegionZonaSiguiente.getDescripcion());
					log.debug("obtenerPeriodoActual: (Periodo Facturado Siguiente) : " + basePeriodoFacturadoSiguiente.getDescripcion());
					
					if(basePeriodoRegionZonaSiguiente.getDescripcion().compareTo(basePeriodoFacturadoSiguiente.getDescripcion())>=0) {
						basePeriodoSiguiente = basePeriodoRegionZonaSiguiente;
						oidPeriodo = basePeriodoRegionZonaSiguiente.getCodigo();
					} else {
						basePeriodoSiguiente = basePeriodoFacturadoSiguiente;
						oidPeriodo = basePeriodoFacturadoSiguiente.getCodigo();
					}
				}
				
				log.debug("obtenerPeriodoActual: (basePeriodoSiguiente) : " + basePeriodoSiguiente);
			}
		}
		
		return oidPeriodo;
	}

	/**
	 * @param codigoPais
	 * @param codigoCliente
	 * @return
	 */
	private Cliente obtenerDatosCliente(String codigoPais, String codigoCliente) {
		Cliente cliente = clienteService.getDatosBasicosCliente(codigoPais, codigoCliente);
		cliente.setCodigoPais(codigoPais);
		ClienteAdicional clienteAdicional = clienteService.getDatosAdicionalesCliente(cliente.getOid().toString());
		ClientePrimerContacto clientePrimerContacto = clienteService.getPrimerContactoCliente(cliente.getOid().toString());
		
		List listClienteIdentificacion = clienteService.getListIdentificacionCliente(cliente.getOid().toString());
		List listClienteSubTipo = clienteService.getListTipoSubtipoCliente(cliente.getOid().toString());
		List listClienteDireccion = clienteService.getListDireccionCliente(cliente.getOid().toString());
		List listClienteComunicacion = clienteService.getListComunicacionCliente(cliente.getOid().toString());
		
		cliente.setClienteAdicional(clienteAdicional);
		cliente.setClientePrimerContacto(clientePrimerContacto);
		cliente.setListClienteIdentificacion(listClienteIdentificacion);
		cliente.setListClienteDireccion(listClienteDireccion);
		cliente.setListClienteComunicacion(listClienteComunicacion);
				
		ClienteUnidadAdministrativa clienteUnidadAdministrativa = clienteService.getUnidadAdministrativaCliente(cliente.getOid().toString());
		cliente.setClienteUnidadAdministrativa(clienteUnidadAdministrativa);
		
		for(int i=0; i<listClienteSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo)listClienteSubTipo.get(i);
			
			clienteSubTipo.setListClienteClasificacion(clienteService.getListClasificacionCliente(clienteSubTipo.getOid().toString()));
		}	
		
		cliente.setListClienteSubTipo(listClienteSubTipo);

		//COMPLETAMOS DATOS EN VACIO
		cliente.setListClienteVinculo(new ArrayList());
		cliente.setListClienteObservacion(new ArrayList());
		cliente.setListClienteConcursoPremio(new ArrayList());
		cliente.setClienteReferencias(new ClienteReferencias());

		return cliente;
	}

	/**
	 * @param clienteService
	 * @param cliente
	 * @param codigoTipoComunicacion
	 * @param valorComunicacion
	 */
	private void verificarComunicacion(MantenimientoMAEClienteService clienteService, Cliente cliente, 
			String codigoTipoComunicacion, String valorComunicacion) {
		ClienteComunicacion clienteComunicacion = null; //new ClienteComunicacion();
		boolean encontrado = false;
		
		Iterator it = cliente.getListClienteComunicacion().iterator();
		while(it.hasNext()) {
			clienteComunicacion = (ClienteComunicacion)it.next();
			
			if(clienteComunicacion.getCodigoTipoComunicacion().equals(codigoTipoComunicacion)) {
				encontrado = true;
				break;
			}	
		}
		if(valorComunicacion!= null && !("".equals(valorComunicacion))) {
			if(!encontrado) {
				clienteComunicacion = new ClienteComunicacion();
				clienteComunicacion.setCodigoTipoComunicacion(codigoTipoComunicacion);
				clienteComunicacion.setOidCliente(cliente.getOid());

				if(cliente.getListClienteComunicacion().size()>0)
					clienteComunicacion.setIndicadorPrincipal(new Integer(0));
				else
					clienteComunicacion.setIndicadorPrincipal(new Integer(1));
			}
			
			Long oidTipoComunicacion = clienteService.getOidTipoComunicacion(codigoTipoComunicacion);			
			
			clienteComunicacion.setOidTipoComunicacion(oidTipoComunicacion);
			clienteComunicacion.setTextoComunicacion(valorComunicacion);
			
			if(clienteComunicacion.getOid()==null)
				cliente.getListClienteComunicacion().add(clienteComunicacion);
		} else {
			if(encontrado)
				clienteComunicacion.setEliminar(true);
		}
	}

	

}
