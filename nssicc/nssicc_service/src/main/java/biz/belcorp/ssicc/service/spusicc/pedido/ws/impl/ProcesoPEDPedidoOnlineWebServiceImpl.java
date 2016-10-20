/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.pedido.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.ProcesoPEDPedidoOnlineWebService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * * <p>
 * <a href="ProcesoPEDPedidoOnlineWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href=""> Jose Luis Rodriguez</a>
 */
public class ProcesoPEDPedidoOnlineWebServiceImpl extends ServletEndpointSupport implements ProcesoPEDPedidoOnlineWebService{

	private static final int NUM_PROCESO_WS_DEFAULT = 10;
	Log log = LogFactory.getLog(ProcesoPEDPedidoOnlineWebServiceImpl.class);
	public static int DELAY_DEFAULT =10000;
	
    ProcesoPEDService procesoPEDService;
	MantenimientoOCRCapturaPedidoService mantenimientoOCRCapturaPedidoService;
	ProcesoSTOService procesoSTOService;	
	ProcesoSTOExecutionService procesoSTOExecutionService;
	GenericoService genericoService;
	
	
	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
     */
    protected void onInit() throws ServiceException {
    	
    	procesoPEDService = (ProcesoPEDService)getWebApplicationContext().getBean("spusicc.procesoPEDService");
    	mantenimientoOCRCapturaPedidoService = (MantenimientoOCRCapturaPedidoService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
    	procesoSTOService = (ProcesoSTOService)getWebApplicationContext().getBean("spusicc.procesoSTOService");
    	procesoSTOExecutionService =(ProcesoSTOExecutionService)getWebApplicationContext(). getBean("spusicc.procesoSTOExecutionService");
    	genericoService = (GenericoService)getWebApplicationContext().getBean("genericoService");
    	
    }

    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.pedido.web.ws.ProcesoPEDPedidoOnlineWebService#executeActivaFlagControlActividadPROL(java.lang.String)
     */
    public boolean executeEnvioPortalSICCFinDia(String pais, String marca, Date fechaLlamada) throws RemoteException{

    	try {

    		Map criteria = new HashMap();
			procesoPEDService.executeEnvioPortalSICCFinDia(criteria);

    	} catch (Exception e) {
    		return false;
		}

    	return true;

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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedido.web.ws.ProcesoPEDPedidoOnlineWebService#executeRecepcionPedidosPROL(java.lang.String)
	 */
	public String[] executeRecepcionPedidosPROL(String archivoEntrada,long oidProceso,long oidCola)	throws RemoteException {
		log.debug("Entra a executeRecepcionPedidosPROL");

		String[] respuesta = new String[3];// posicion 0:exito(1) o no(0)
											// posicion 1:mensaje de error pos
											// 2:xml respuesta
		String codigoError = Constants.NUMERO_UNO;
		String mensajeError = "";
		String cadResultado = "";
		boolean retornar=false;//esta variable se utiliza para retornar sin hacer el update del finally

		Map criteria = new HashMap();
		criteria.put("archivoEntrada", archivoEntrada);
		criteria.put("indOrigen", Constants.PED_ORIGEN_PROL);

		String auditoriaPROL=Constants.NUMERO_CERO;//parametro inicial
		try {
								
			//Se valida que el indicador de PROL se encuentre en 1
			String indicadorPROL = procesoPEDService.getIndicadorActividadPROL2();
			int numeroProcesWs = NUM_PROCESO_WS_DEFAULT;//default son 10

			if(Constants.NUMERO_UNO.equals(indicadorPROL)){
				log.debug("Se va a ejecutar el proceso de PROL");
			}
			else{
				throw new Exception("Error al ejecutar el Proceso PROL. El indicador de PROL no esta activo " );
			}

			// Inserta el archivo de entrada en
			// en las tablas int_solic_cabec int_solic_posic
			log.debug("LLamando a insertaArchivoEntradaPROL");
			try {
				//1 fec_ingr (se inserta despues del proceso  no se tiene cod pais para obtener parametro)				
				procesoPEDService.insertaArchivoEntradaPROL(criteria);
				//				
			} catch (Exception e) {
				throw new Exception("Error al leer el Archivo XML: " + e);
			}
						
			try {
				
				criteria = getPedidoTemporal(criteria);
				//inserta via parametro recien se tiene pais y cliente

				//1 fec_ing
				//BigDecimal oidAuditoriaPROL =null;
				String codigoPais = (String) criteria.get("codigoPais");
				
				auditoriaPROL=genericoService.getParametroPais(codigoPais,Constants.SISTEMA_GEN, Constants.GEN_AUDITORIA);
				String numProceso = genericoService.getParametroPais(codigoPais,Constants.SISTEMA_GEN, Constants.GEN_PROCESO_WS);
				if(StringUtils.isNotEmpty(numProceso))	numeroProcesWs = new Integer(numProceso);
				
				if(Constants.NUMERO_UNO.equals(auditoriaPROL)){
					
						//si existe y ya termino el proceso cola, se envia los parametros finalizados
  					    //caso contrario se hace validacion si existe el proceso o consultora en espera
						criteria.put("oidProceso", oidProceso);
						criteria.put("oidCola", oidCola);		
						criteria.put("numeroProcesWs", numeroProcesWs);					
						Map params = procesoPEDService.getProcesoColaPROL(criteria);						
						if(params != null && params.get("oidAuditoriaPROL")!= null){
							BigDecimal oidAuditoriaPROL =(BigDecimal)params.get("oidAuditoriaPROL");
							criteria.put("oidAuditoriaPROL", oidAuditoriaPROL);
							String codError= (String)params.get("codigoError");
							if(Constants.NUMERO_UNO.equals(codError)){
								codigoError = codError;
								mensajeError = (String)params.get("mensajeError");
								cadResultado = (String)params.get("cadResultado");;//mxl_salida			
								//despues de este return hara el finally 
								retornar=true;//
								return respuesta;
							}else{
								//actualizamos la fecha de inicio de reintento  
								criteria.put("indFecInicio",Constants.NUMERO_UNO);
								procesoPEDService.updateAuditoriaPROL(criteria);
								criteria.remove("indFecInicio");
							}
						}else{					
						
							//validamos:1 si se trata del mismo oidProceso oidCola que aun no termina
						    //2:cliente en proceso
	  					    //3: NUMERO MAXIMO DE PROCEOS						
							String mensajeValidacion = procesoPEDService.getValidarProcesoPROL(criteria);
							//el criteria contendra el oidAuditoriaPROL si no hay error en validacion
							if(StringUtils.isNotEmpty(mensajeValidacion))
										throw new Exception(mensajeValidacion);
						}
						
				}
			 								
				log.debug("Se realiza la Accion enviada: " + criteria);				
				// Se ejecuta el proceso de PROL segun la accion
				// Si la accion es igual a 1 � 2 se inserta el nuevo pedido
				//String codigoPais = (String) criteria.get("codigoPais");
				String accion = (String) criteria.get("accion");
				
				/*Eliminando Pedidos PROL no Facturados  en caso Existan*/
				List stoList =   procesoPEDService.getPedidoDocumentoDigitadoPKByCriteria(criteria);

		    	log.debug("LLamando a execute Eliminar");
		    	//2.(fec_borrad)
		    	if(Constants.NUMERO_UNO.equals(auditoriaPROL)){
		    		criteria.put("indFecBorrado",Constants.NUMERO_UNO);
					procesoPEDService.updateAuditoriaPROL(criteria);
					criteria.remove("indFecBorrado");
				}
		    	
		    	
		    	
		    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC, Constants.STO_ACCI_ELIM_ONLI);
		    	
		    	String modoEliminaOnline = genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_MODO_ELIMINA_ONLINE);
		    	if (modoEliminaOnline.equals(Constants.SI))  accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_ELIM_LINEA);
		    	procesoSTOExecutionService.execute(accionTipoDocumento, criteria, stoList);
				
				if (Constants.NUMERO_UNO.equals(accion)	|| Constants.NUMERO_DOS.equals(accion)){		
					log.debug("Consolidando los pedidos");
					//3 fec_conso
			    	if(Constants.NUMERO_UNO.equals(auditoriaPROL)){
			    		criteria.put("indFecConsolidado",Constants.NUMERO_UNO);
						procesoPEDService.updateAuditoriaPROL(criteria);
						criteria.remove("indFecConsolidado");
					}					
			    	procesoPEDService.executeConsolidadoPedidos(criteria);

			    	log.debug("Ejecutando STO..");
			    	criteria.remove("numeroProceso");
			    	List listaSTO = procesoSTOService.getDocumentoDigitadoPKByLote(criteria);
			    	
			    	String modoOnline = genericoService.getParametroPais(codigoPais,Constants.SISTEMA_STO, Constants.STO_MODO_ONLINE);
			    	if (modoOnline.equals(Constants.SI)) 
			    	accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_LINEA);
			    	
			    	else
			    		accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_ON_LINE);
			    	//4 fec_valid
			    	if(Constants.NUMERO_UNO.equals(auditoriaPROL)){
			    		criteria.put("indFecValidacion",Constants.NUMERO_UNO);
						procesoPEDService.updateAuditoriaPROL(criteria);
						criteria.remove("indFecValidacion");
					}			    	
			    	procesoSTOExecutionService.execute(accionTipoDocumento,criteria,listaSTO);

					String oidSolicitud = procesoPEDService.getOidSolicitudPROL(criteria);
					
					if (oidSolicitud != null) {
						criteria.put("oidSolicitud", oidSolicitud);
				    	log.debug("Ejecutando SiCC GP1..GP3");
				    	//5 fec_sicc
				    	if(Constants.NUMERO_UNO.equals(auditoriaPROL)){
				    		criteria.put("indFecSicc",Constants.NUMERO_UNO);
							procesoPEDService.updateAuditoriaPROL(criteria);
							criteria.remove("indFecSicc");
						}
				    	mantenimientoOCRCapturaPedidoService.executeEjecutarGP2(criteria);
					}
					
				}
				procesoPEDService.generaArchivoSalidaPROL(criteria);
				cadResultado = procesoPEDService.getCadenaResultadoPROL(criteria);
				
			} catch (Exception e) {
				codigoError = Constants.NUMERO_CERO;
				throw new Exception("Error de disponibilidad, reintentar procesar: " + e);
			}

		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			mensajeError= ""+ e.getMessage();
		}finally{
			respuesta[0] = codigoError;
			respuesta[1] = mensajeError;
			respuesta[2] = cadResultado;//mxl_salida
			//fec_fin
			if(Constants.NUMERO_UNO.equals(auditoriaPROL) && !retornar){
	    		criteria.put("indFecFin",Constants.NUMERO_UNO);
	    		criteria.put("codigoError",codigoError);
	    		criteria.put("mensajeError",StringUtils.isNotEmpty(mensajeError) && mensajeError.length()>=1000?mensajeError.subSequence(0,999):mensajeError);
				procesoPEDService.updateAuditoriaPROL(criteria);
				criteria.remove("indFecFin");
			}
			
			log.debug("codigoError='"+codigoError+"' mensajeError='" +mensajeError +"' cadResultado='" + cadResultado+ "'");
		}

		return respuesta;
	}


	private Map getPedidoTemporal(Map criteria){
		String oidTemporal = (String) criteria.get("oidTemporal");
		List pedidosList = procesoPEDService.getPedidoTemporalById(criteria);
		
		criteria = (Map)pedidosList.get(0);
	
		String codigoPais = (String) criteria.get("codigoPais");
		String numeroLote = (String) criteria.get("numeroLote");
		String codigoCliente = (String) criteria.get("codigoCliente");
		
		Usuario usuarioWeb =  getUsuarioWeb(codigoPais);
		
		criteria.put("oidTemporal", oidTemporal);
		criteria.put("usuario", usuarioWeb);
		criteria.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("codigoUsuario", usuarioWeb.getLogin());		
		criteria.put("indOrigen", Constants.PED_ORIGEN_PROL);
		/*Origen Online*/
		criteria.put("codigoOrigen", "OL");
		/*Pedidos No facturados*/
		criteria.put("indProcGp2", "0");
		
		
		criteria.put("codPais", codigoPais);
    	criteria.put("numLote", numeroLote);
    	criteria.put("codClie", codigoCliente);
    	
    	return criteria;
	}
	
	private Usuario getUsuarioWeb(String codigoPais){
		String idioIso = genericoService.getParametroPais(codigoPais,Constants.SISTEMA_GEN, Constants.GEN_IDIO_ISO_DEFA);
		String loginUsuarioWeb = genericoService.getParametroPais(codigoPais, Constants.SISTEMA_GEN,Constants.GEN_USUA_WEB);

		Idioma i = new Idioma();
		i.setCodigoISO(idioIso);
		Usuario usuarioWeb =  new Usuario();
		usuarioWeb.setLogin(loginUsuarioWeb);
		usuarioWeb.setIdioma(i);

		return usuarioWeb;
	}

}