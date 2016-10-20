
package biz.belcorp.www.soa.business.ffvv.sicc.prospectobs;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.soa.ProspectoService;
import biz.belcorp.www.canonico.ffvv.persona.TIdentificacion;
import biz.belcorp.www.canonico.ffvv.persona.TPersona;
import biz.belcorp.www.canonico.ffvv.persona.TTipoDocumento;
import biz.belcorp.www.canonico.ffvv.reclutar.TDeuda;
import biz.belcorp.www.canonico.ffvv.reclutar.TMotivoBloqueo;
import biz.belcorp.www.canonico.ffvv.reclutar.TPersonaDeuda;
import biz.belcorp.www.canonico.ffvv.reclutar.TValoracion;
import biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda;
import biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion;
import biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion;
import biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona;
import biz.belcorp.www.canonico.ffvv.vender.TCampania;
import biz.belcorp.www.canonico.ffvv.vender.TConsultora;
import biz.belcorp.www.canonico.ffvv.vender.TConsultoraDatosDinamicos;
import biz.belcorp.www.canonico.ffvv.vender.TConsultoraDatosEstaticos;
import biz.belcorp.www.canonico.ffvv.vender.TCronograma;
import biz.belcorp.www.soa.mensajes.gestionprospectos.Datos_type1;
import biz.belcorp.www.soa.mensajes.gestionprospectos.MsgInValidacionCrediticia;
import biz.belcorp.www.soa.mensajes.gestionprospectos.MsgOutValidacionCrediticia;
import biz.belcorp.www.soa.mensajes.gestionprospectos.TMsgOutObtenerValidacionCrediticia;
import biz.belcorp.www.ssg.comun.Detalle_type0;
import biz.belcorp.www.ssg.comun.MsgFault;
import biz.belcorp.www.ssg.comun.TMsgFault;


public class ProspectoBSSkeleton{

	private static final String SERVICES_PROSPECTO_BS = "/services2/ProspectoBS";
	private static final String SOA_PAIS_DEFAULT ="PE";
	private Log log = LogFactory.getLog(ProspectoBSSkeleton.class); 
	
	
	private ProspectoService prospectoService;
	
	private MessageSource messageSource;
	
	private GenericoService genericoService;
         
    /**
     *   proceso que realiza la validacion si la consultora es apta para pertencer ala fuerza de ventas
     *   TValoracion value1:Apta, value2:No Apta , value3:Consultora
     *     
         * @param msgInValidacionCrediticia 
         * @return msgOutValidacionCrediticia 
         * @throws MsgFaultObtenerValidacionCrediticia 
     */
        
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public MsgOutValidacionCrediticia obtenerValidacionCrediticia (
                  	MsgInValidacionCrediticia msgInValidacionCrediticia)
            throws MsgFaultObtenerValidacionCrediticia{
    	 log.debug(">> inicio obtener validacion creditica>> "+msgInValidacionCrediticia);
    	 
    	 MsgOutValidacionCrediticia result = new MsgOutValidacionCrediticia();    	 
		 TMsgOutObtenerValidacionCrediticia part= new TMsgOutObtenerValidacionCrediticia();		 
		 //datos de gestipn de prospectos
		 Datos_type1 typeRes= new Datos_type1();
		  //
		  HttpServletRequest obj = (HttpServletRequest)MessageContext.getCurrentMessageContext().getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		  log.debug("obj >> " + obj.getContextPath());
		  //log.debug("request.getContextPath()+ "+request.getContextPath()); HttpServletRequest request = (HttpServletRequest)			  	  
		  try{
			  String codigoPais   = msgInValidacionCrediticia.getMsgInValidacionCrediticia().getDatos().getPais().getCodigoISO();	  
			  String numDocuIdentidad = msgInValidacionCrediticia.getMsgInValidacionCrediticia().getDatos().getIdentificacion().getNumero();
			  boolean isDocuPrincipal = msgInValidacionCrediticia.getMsgInValidacionCrediticia().getDatos().getIdentificacion().getDocumentoPrincipal();
			  String codigoTipoDocu= msgInValidacionCrediticia.getMsgInValidacionCrediticia().getDatos().getIdentificacion().getTipo().getCodigo();
			  log.debug("isDocuPrincipal "+isDocuPrincipal + " codigoTipoDocu "+codigoTipoDocu);
			  
		     String mensajeValidacion = getMensajeValidacion(codigoPais,codigoTipoDocu,numDocuIdentidad);
			 if(StringUtils.isNotEmpty(mensajeValidacion)) throw new Exception(mensajeValidacion);
				  
			 //armamos nuestro criteria
			  Map criteria = new HashMap();
			  criteria.put("codigoPais", codigoPais);
			  //criteria.put("indDocumentoPrincipal", isDocuPrincipal?Constants.NRO_UNO:Constants.NUMERO_CERO);
			  criteria.put("tipoDocumento", codigoTipoDocu);
			  int lengthDocumento;
			  lengthDocumento = prospectoService.getLengthDocumento(criteria);
			  //metodo que retorna el lengthDocumento
			  if(lengthDocumento != 0){
				  criteria.put("numeroDocumento", StringUtils.leftPad(numDocuIdentidad,lengthDocumento,"0") );
			  }else{
			  criteria.put("numeroDocumento", numDocuIdentidad);
			  }
			  
			  //Obtenemos el indicador para consultar data hist�rica
			  String indCrediHistorico = genericoService.getParametroPais(codigoPais,Constants.SISTEMA_SOA, Constants.SOA_IND_CREDI_HISTO);
			  log.debug(" indCrediHistorico >>> "+ indCrediHistorico );
			  criteria.put("indCrediHistorico", StringUtils.isNotEmpty(indCrediHistorico)?indCrediHistorico:null );
			  List<Map> list = prospectoService.getValidacionCrediticiaUsuario(criteria);
				  
			  log.debug("list Final "+list.size());
			  String encontroSELECTPrincipal = (String) criteria.get("encontroSELECTPrincipal");
			  
  			  if(list.size() > 0){
 					  //SOA NO ACEPTA objetos nullos x la cual deberia crearse insatncias vacias
    				  for(Map o : list){//es una lista de un solo objeto
    					  TPersonaDeuda tpersonaDeuda = new TPersonaDeuda();
    					  BigDecimal deudaPersona = new BigDecimal(0);
    					  
    					  TDeuda tdeuda = new TDeuda();
    					  TMoneda tmoneda =  new TMoneda();
    					  TPersona tpersona = new TPersona(); 
    					  TConsultora tConsultora = new TConsultora();
						  TCampania tCampania = new TCampania();
						  TCronograma tCronograma = new TCronograma();
						  TRegion tRegion = new TRegion();
						  tRegion.setCodigo((String)o.get("codigoRegion"));
						  TZona tZona = new TZona();
						  tZona.setCodigo((String)o.get("codigoZona"));
						  TSeccion tSeccion = new TSeccion();
						  tSeccion.setCodigo((String)o.get("codigoSeccion"));						  
						  tCampania.setCronograma(tCronograma);
						  TConsultoraDatosEstaticos tConsultoraDatosEstaticos = new TConsultoraDatosEstaticos();
						  tConsultoraDatosEstaticos.setCampaniaIngreso(tCampania);
						  tConsultoraDatosEstaticos.setCodigo((String)o.get("codigoCliente"));
						  tConsultora.setDatosEstaticos(tConsultoraDatosEstaticos);
						  TConsultoraDatosDinamicos tConsultoraDatosDinamicos = new TConsultoraDatosDinamicos();
						  tConsultora.setDatosDinamicos(tConsultoraDatosDinamicos );
    					  
    					  //datos para la clase SOA Persona
    					  tpersona.setPrimerApellido((String)o.get("apellido1"));
    					  tpersona.setSegundoApellido((String)o.get("apellido2"));
    					  tpersona.setPrimerNombre((String)o.get("nombre1"));
    					  tpersona.setSegundoNombre((String)o.get("nombre2"));
    					  
    					  TIdentificacion tIdentificacion= new TIdentificacion();
    					  TTipoDocumento tTipoDocumento = new TTipoDocumento();
    					  tTipoDocumento.setCodigo(codigoTipoDocu);
    					  tIdentificacion.setNumero(numDocuIdentidad);
    					  tIdentificacion.setTipo(tTipoDocumento );
    					  
    					  tpersona.addIdentificacion(tIdentificacion);
    					  
    					  
    					  //datos para SOA clase Moneda
    					  tmoneda.setCodigoISOPais(codigoPais);
    					  tmoneda.setNombre((String)o.get("descripcionMoneda"));
    					  tmoneda.setSimbolo((String)o.get("simboloMoneda"));
    					  //    					  
    					  tdeuda.setEntidadCrediticia(Constants.SOA_ENTIDAD_CREDITICIA);
    					  tdeuda.setMoneda(tmoneda);
    					  log.debug("o.getDeudaBelcorp() >>> "+ String.valueOf(o.get("deudaBelcorp")));
    					  if(String.valueOf(o.get("deudaBelcorp"))!= null && String.valueOf(o.get("deudaCastigada"))!= null ){
    					
	    						  BigDecimal deudaBelcorp = new BigDecimal(String.valueOf(o.get("deudaBelcorp")));
	    						  deudaBelcorp = deudaBelcorp.add(new BigDecimal(String.valueOf(o.get("deudaCastigadaTmp"))));    						
	    						  tdeuda.setMonto(deudaBelcorp);
	    						  deudaPersona = deudaBelcorp;
    					  }
    					  else {
    						  tdeuda.setMonto(new BigDecimal(0));
    						  deudaPersona = new BigDecimal(0);
    					  }
    					  //
    					  tpersonaDeuda.addDeuda(tdeuda);
    					  tpersonaDeuda.setPersona(tpersona);
    					  
    					  //Obtenemos si la consultora tiene Motivos de Bloqueo
    					  if (Constants.SI.equals(encontroSELECTPrincipal)) {
	    					  List<Map> listMotBloq = prospectoService.getMotivosBloqueoXConsultora(criteria);
	    					  int cantBlqueos = listMotBloq.size();
	    					  log.debug("list Motivos de Bloqueo X Consultora "+ cantBlqueos);
	    		  			  if(cantBlqueos > 0){
	    		    				  for(Map objMB : listMotBloq){//es una lista de un solo objeto
	    		    					  TMotivoBloqueo tMotivoBloqueo = new TMotivoBloqueo();
	    								  tMotivoBloqueo.setDescripcion((String)objMB.get("descripcionBloqueo"));
	    								  tMotivoBloqueo.setTipo((String)objMB.get("tipoBloqueo"));
	    								  tMotivoBloqueo.setObservacion((String)objMB.get("observacionBloqueo"));
	    		    					  tpersonaDeuda.addMotivoBloqueo(tMotivoBloqueo);
	    		    				  }
	    		  			  }else{
	        					  TMotivoBloqueo tMotivoBloqueo = new TMotivoBloqueo();
		    					  tpersonaDeuda.addMotivoBloqueo(tMotivoBloqueo);
	    		  			  }
	    		  			  
	    		  			  TValoracion tValoracion = new TValoracion();
							  tValoracion.setTipo(Constants.SOA_ENTIDAD_CREDITICIA);
	    		  			  if(!Constants.MAE_ESTADO_RETIRADA.equals(o.get("estadoCliente"))){
	    						  tValoracion.setValor(Constants.SOA_VALORACION_CONSULTORA);//CONSULTORA 
	    						    							  
	    					  }
	    		  			  else{
	    						//tiene deuda
	    						  BigDecimal deudaBelcorp = new BigDecimal(String.valueOf(o.get("deudaBelcorp")));
	    						  BigDecimal deudaCastigada = new BigDecimal(String.valueOf(o.get("deudaCastigada")));
	    						  BigDecimal deudaCastigadaTmp = new BigDecimal(String.valueOf(o.get("deudaCastigadaTmp")));
	    						  
	    						  if(deudaPersona.doubleValue() > 0 || cantBlqueos > 0){    							  
	    							  if(deudaCastigada.doubleValue() > 0 || deudaCastigadaTmp.doubleValue() > 0 )
	    								  tValoracion.setValor(Constants.SOA_VALORACION_INCOBRABLE);//INCOBRABLE
	    							  else
	    								  tValoracion.setValor(Constants.SOA_VALORACION_NO_APTA);//NO_APTA
	    						  }
	    						  else
	    							  tValoracion.setValor(Constants.SOA_VALORACION_APTA);//APTA
	    					 } 
	    		  			 tpersonaDeuda.setValoracion(tValoracion);
    					  }
    					  else {
    						  TMotivoBloqueo tMotivoBloqueo = new TMotivoBloqueo();
							  tMotivoBloqueo.setDescripcion(Constants.SOA_CASTIGO_DEUDA_INCOBRABLE);
							  tMotivoBloqueo.setTipo(Constants.SOA_INCOBRABLE);
							  tMotivoBloqueo.setObservacion(Constants.SOA_BLOQUEO_MASIVO_CASTIGO_INCOBRABLE);
	    					  tpersonaDeuda.addMotivoBloqueo(tMotivoBloqueo);
	    					  
	    					  TValoracion tValoracion = new TValoracion();
							  tValoracion.setTipo(Constants.SOA_ENTIDAD_CREDITICIA);
	    					  tValoracion.setValor(Constants.SOA_VALORACION_VALOR_COE);
	    					  tpersonaDeuda.setValoracion(tValoracion);
    					  }
    					  
 
						//se llena los datos para SOA
    					  typeRes.setConsultora(tConsultora);
    					  typeRes.setRegion(tRegion);
    					  typeRes.setZona(tZona);
    					  typeRes.setSeccion(tSeccion);
    					  typeRes.setPersonaDeuda(tpersonaDeuda);
    				  }
				  }else{
					  //no existe en comercial lo cual es apta
					  TPersona tpersona = new TPersona();
					  TPersonaDeuda tpersonaDeuda = new TPersonaDeuda();
					  TDeuda tdeuda = new TDeuda();
					  TMoneda tmoneda =  new TMoneda();
					  TIdentificacion tIdentificacion = new TIdentificacion();
					  TTipoDocumento tTipoDocumento = new TTipoDocumento();
					  tTipoDocumento.setCodigo(codigoTipoDocu);
					  tIdentificacion.setNumero(numDocuIdentidad);
					  tIdentificacion.setTipo(tTipoDocumento );
					  tpersona.addIdentificacion(tIdentificacion);
					  tdeuda.setMoneda(tmoneda);
					  TValoracion tValoracion = new TValoracion();
					  tValoracion.setTipo(Constants.SOA_ENTIDAD_CREDITICIA);					  
					  tValoracion.setValor(Constants.SOA_VALORACION_SIN_INFORMACION);	
					  TMotivoBloqueo tMotivoBloqueo = new TMotivoBloqueo();
					  tpersonaDeuda.addMotivoBloqueo(tMotivoBloqueo);
					  tpersonaDeuda.addDeuda(tdeuda);
					  tpersonaDeuda.setPersona(tpersona);
					  //tpersonaDeuda.addValoracion(tValoracion);	
					  tpersonaDeuda.setValoracion(tValoracion);
					  TConsultora tConsultora = new TConsultora();
					  TCampania tCampania = new TCampania();
					  TCronograma tCronograma = new TCronograma();
					  TRegion tRegion = new TRegion();
					  TZona tZona = new TZona();
					  TSeccion tSeccion = new TSeccion();						  
					  tCampania.setCronograma(tCronograma);
					  TConsultoraDatosEstaticos tConsultoraDatosEstaticos = new TConsultoraDatosEstaticos();
					  tConsultoraDatosEstaticos.setCampaniaIngreso(tCampania);
					  tConsultora.setDatosEstaticos(tConsultoraDatosEstaticos);
					  TConsultoraDatosDinamicos tConsultoraDatosDinamicos = new TConsultoraDatosDinamicos();
					  tConsultora.setDatosDinamicos(tConsultoraDatosDinamicos );
					  typeRes.setConsultora(tConsultora);
					  typeRes.setRegion(tRegion);
					  typeRes.setZona(tZona);
					  typeRes.setSeccion(tSeccion);
					  typeRes.setPersonaDeuda(tpersonaDeuda);
				  }			  			  
			  part.setDatos(typeRes);		  
			  //typeRes.setEvento(actividad);
			  msgInValidacionCrediticia.getMsgInValidacionCrediticia().getAuditoria().setFechaProceso(Calendar.getInstance());
			  msgInValidacionCrediticia.getMsgInValidacionCrediticia().getAuditoria().setSistema(Constants.SOA_SISTEMA_COMERCIAL);
			  part.setAuditoria(msgInValidacionCrediticia.getMsgInValidacionCrediticia().getAuditoria());
			  result.setMsgOutValidacionCrediticia(part);		  
			  		  		  
		  }catch(Exception e){
			  log.debug("error "+e.getLocalizedMessage());
				e.printStackTrace();
			  MsgFaultObtenerValidacionCrediticia error = new MsgFaultObtenerValidacionCrediticia();
			  TMsgFault msgFault =  new TMsgFault();
			  MsgFault msg = new MsgFault(); 		  
			  Detalle_type0 detalleError = new Detalle_type0();
			  
			  detalleError.setURLServicio(obj.getContextPath()+SERVICES_PROSPECTO_BS);
			  detalleError.setPilaError(e.getMessage());
			      				 
			  //codigo de error y descripcion
			  if(StringUtils.isNotEmpty(e.getMessage())){
				
		       if(e.getMessage().indexOf(Constants.SOA_SISTEMA_COMERCIAL+":")!= -1){	  
				 msgFault.setCodigoError(Constants.SOA_CODIGO_MENSAJE_ERROR);
			  	 msgFault.setDescripcion(e.getMessage());
		       }else{
			     msgFault.setCodigoError(Constants.SOA_CODIGO_MENSAJE_ERROR_BD);
				 msgFault.setDescripcion(e.getMessage()); 
		       }
			  }else{
				  //error en creacion del docuento SOA
				 msgFault.setCodigoError(Constants.SOA_CODIGO_MENSAJE_ERROR_SOA);
				 msgFault.setDescripcion(this.messageSource.getMessage("procesoSOAprospecto.msg.errorCreacionDocumento", null, getLocale())); 
			  }
			  //
			  msgFault.setFechaHora(Calendar.getInstance());
			  msgFault.setSistema(Constants.SOA_SISTEMA_COMERCIAL);
			  //
			  msgFault.setDetalle(detalleError);
			  msg.setMsgFault(msgFault);			  
			  error.setFaultMessage(msg);
			  throw error;  
		  }
		  
		  //
		  log.debug("fin obtener validacion crediticia");
		  	  
		  return result;
      }
     
    private String getMensajeValidacion(String strCodigoPais, String strTipoDocumento, String strNumeroDocumento) {

		String mensaje = "";
			
		if(StringUtils.isEmpty(strCodigoPais)) 
			mensaje = this.messageSource.getMessage("procesoSOAcronograma.msg.verificarPais", null, getLocale());
			
		if(StringUtils.isEmpty(strTipoDocumento)) 
			mensaje = this.messageSource.getMessage("procesoSOAprospecto.msg..verificarTipoDocumento", null, getLocale());
			
		if(StringUtils.isEmpty(strNumeroDocumento)) 
			mensaje = this.messageSource.getMessage("procesoSOAprospecto.msg.verificarNumeroDocumento", null, getLocale());

		return mensaje;
		
	}
    
	/**
	 * Metodo para obtener el Locate del usuario ingresado como parametro 
	 * @param usuario
	 * @return
	 */
	protected Locale getLocale() {		
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}
	
    public ProspectoService getProspectoService() {
		return prospectoService;
	}

	public void setProspectoService(ProspectoService prospectoService) {
		this.prospectoService = prospectoService;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the genericoService
	 */
	public GenericoService getGenericoService() {
		return genericoService;
	}

	/**
	 * @param genericoService the genericoService to set
	 */
	public void setGenericoService(GenericoService genericoService) {
		this.genericoService = genericoService;
	}
	
	
}
    