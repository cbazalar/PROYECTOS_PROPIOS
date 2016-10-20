package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifax;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifaxLocator;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio16;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * @author  <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 * 
 */
@Service("sisicc.interfazOCRRecepcionarSolicitudCreditoMobilService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRecepcionarSolicitudCreditoMobilServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazOCRDAO")
	InterfazOCRDAO interfazOCRDAO;
	
	@Resource(name="spusicc.procesoSTOExecutionService")
	ProcesoSTOExecutionService procesoSTOExecutionService;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
	throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {

			interfazOCRDAO
					.deleteInterfazOCRRecepcionarSolicitudCreditoCorporativa();
			interfazOCRDAO
					.executeInterfazOCRRecepcionarSolicitudCreditoCorporativa(map);

		} catch (Exception e) {

			throw new InterfazException(
					"Error al borrar/cargar los registros de la tabla temporal: "
							+ e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		
		String codTipoDocu = Constants.STO_TIPO_DOCUMENTO_SCC;
		
		Map map = interfazParams.getQueryParams();
		map.put("usuario.login",interfazParams.getUsuario().getLogin());
		map.put("usuario",interfazParams.getUsuario());
		map.put("codigoDocumento", codTipoDocu);
		
		String codigoProcesoBatch = (String)interfazParams.getQueryParams().get("codigoProcesoBatch");
		
		String codigoPais= (String)map.get("codigoPais");
		String host = (String)map.get("host");
		try {
			interfazOCRDAO.executeInterfazOCRProcesarConsolidadoSolicitudCredito(map);
			
			String indActivaWS = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_IND_ACTIVA_WS_SOCCRED);
			log.debug("indActivaWS "+indActivaWS);
			if(Constants.NUMERO_UNO.equals(indActivaWS)){
				String urlWSOCR = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
				Integer port = new Integer(genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
				String hostParametro = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);
				String serie="";
				String zona="";
				String usuario = interfazParams.getUsuario().getLogin();
				//	
				if(StringUtils.isEmpty(hostParametro)) hostParametro = host;
				
				IConsultaBelcorp service=null;
				
				WS00230301Endpoint serviceEquifax=null;
				EstructuraServicio16 estructuraServicio16_1 = new EstructuraServicio16();
				Integrante integrante=new Integrante();
				String tipoDocEqui="";
				if(Constants.PAIS_CLE.equals(codigoPais)){
					IConsultaBelcorpservice locator = new IConsultaBelcorpserviceLocator();
					log.debug("URL ws Boletin electronico >>> "+urlWSOCR);
					service =locator.getIConsultaBelcorpPort(new java.net.URL(urlWSOCR));
					log.debug("service ws conectado "+service);
				}
				else{
					//OBTENEMOS LOS PARAMAROS NESECSARIOS PARA EQUIFAX
					String usuarioEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_USU_EQUI_SOCCRED);
					String claveEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_PWD_EQUI_SOCCRED);
					String canalEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_CANAL_EQUI_SOCCRED);
					String modeloEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_MODEL_EQUI_SOCCRED);
					String nombreDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_NOM_DATO_EQUI_SOCCRED);
					String tipoDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DATO_EQUI_SOCCRED);
					String valorDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_VALOR_DATO_EQUI_SOCCRED);
					tipoDocEqui = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DOC_EQUI_SOCCRED);
					
					WebServiceEquifax locator = new WebServiceEquifaxLocator();
					log.debug("URL ws Boletin electronico infocorp  >>> "+urlWSOCR);
					serviceEquifax =locator.getWS00230301EndpointPort(new java.net.URL(urlWSOCR));
					log.debug("service ws conectado "+service);
					
					Header header = new Header();
					header.setCanal(canalEquifax);
					header.setClave(claveEquifax);
					header.setUsuario(usuarioEquifax);
					header.setModelo(modeloEquifax);
					estructuraServicio16_1.setHeader(header );
					Integrante[] integrantesServicio = new Integrante[1];
					
					Dato datosEntrada = new Dato();
					datosEntrada.setNombreDato(nombreDato);
					datosEntrada.setTipoDato(tipoDato);
					datosEntrada.setValor(valorDato);
					integrante.setDatosEntrada(new Dato[]{datosEntrada});
					integrantesServicio[0] = integrante;
					estructuraServicio16_1.setIntegrantesServicio(integrantesServicio );
					
					
				}
				
				
				//map.put("numLoteSTO", "00005219");//prueba
				List list = interfazOCRDAO.getListWsSocCredito(map);
				Iterator it = list.iterator();
				log.debug("host "+hostParametro);
				log.debug("port "+port);
				while(it.hasNext()){
					Map hmap = (Map)it.next();
					String rut = (String)hmap.get("numeroDocumentoIdentidad");
					log.debug("rut "+rut);
					//invocamos ws
					String res=null;
					try{
						
						if(StringUtils.isNotEmpty(rut)){
							if(Constants.PAIS_CLE.equals(codigoPais)){	
								res= service.getEvaluacionBelcorp(hostParametro, port, rut, serie, zona, usuario);
							}else{
							//infocorp							
								integrante.setNumeroDocumento(rut);
								integrante.setTipoDocumento(StringUtils.isNotEmpty(tipoDocEqui)?Integer.parseInt(tipoDocEqui):1);//por default 1:dni
								
								log.warn("Invocacion INFORCORP Inicio : " + rut);
								res=serviceEquifax.consultaServicio16(estructuraServicio16_1);
								log.warn("Invocacion INFORCORP Fin : " + rut);
							}
						}
						
					}catch(Exception e){
						log.debug("error conexion web service "+ e.getMessage());
						res=null;
						throw new InterfazException(
								"Error en conectarse al servicio web boletin electronico: "
										+ e.getMessage());
					}
					log.debug("res "+res);
					if(StringUtils.isNotEmpty(res)){
					  if(Constants.PAIS_CLE.equals(codigoPais)){
						String [] split = StringUtils.split(res,"|");//reporte|montoBic|MontoMic|MontoInfoCom|Estado|Mensaje|Error
						if(split.length == 7){
							log.debug("split "+split[3]+" --- "+split[4]+"---"+split[6]);
							hmap.put("montoInfoCom", split[3]);
							hmap.put("estado", split[4]);
							hmap.put("error", split[6]);					
							interfazOCRDAO.updateInformacionInfoCom(hmap);
						}
					  }else{					
						//infocorp
						//String PATRON_INICIAL ="<Valor>";
						//String PATRON_FINAL ="</Valor>";	
						  						   						  
							int index =res.indexOf(Constants.EQUIFAX_PATRON_INICIAL);
							if(index!=-1){
								int indexf =res.indexOf(Constants.EQUIFAX_PATRON_FINAL);								
								String valor= res.substring(index + Constants.EQUIFAX_PATRON_INICIAL.length(), indexf);
								log.debug("valor "+valor);								
								hmap.put("estado", Constants.EQUIFAX_ESTADO_APROBADO.equals(valor)?"1":"12");//definidos en el rcr													
								
								// NUEVO ----------------------					
								
								String nombresInfocorp = "";
								String apellidoMaternoInfocorp = "";
								String apellidoPaternoInfocorp = "";
								String explicacionInfocorp = "";
								
								try {
															
									int indexExini =res.indexOf(Constants.EQUIFAX_PATRON_INICIAL,indexf); // <Valor>
									if(indexExini!=-1){
										int indexExfin =res.indexOf(Constants.EQUIFAX_PATRON_FINAL,indexf + Constants.EQUIFAX_PATRON_INICIAL.length());					
										explicacionInfocorp = res.substring(indexExini + Constants.EQUIFAX_PATRON_INICIAL.length(), indexExfin);
										log.debug("explicacionInfocorp "+explicacionInfocorp);									
									}	
									
									int indexAPini =res.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_INICIAL); // <ApellidoPaterno>
									if(indexAPini!=-1){
										int indexAPfin =res.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_FINAL);					
										apellidoPaternoInfocorp = res.substring(indexAPini + Constants.EQUIFAX_PATRON_APE_PAT_INICIAL.length(), indexAPfin);
										log.debug("apellidoPaternoInfocorp "+apellidoPaternoInfocorp);									
									}	
									
									int indexAMini =res.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_INICIAL); // <ApellidoMaterno>
									if(indexAMini!=-1){
										int indexAMfin =res.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_FINAL);	 // </ApellidoMaterno>				
										apellidoMaternoInfocorp = res.substring(indexAMini + Constants.EQUIFAX_PATRON_APE_MAT_INICIAL.length(), indexAMfin); 
										log.debug("apellidoMaternoInfocorp "+apellidoMaternoInfocorp);								
									}
									
									int indexNombresini =res.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_INICIAL); // <Nombres>
									if(indexNombresini!=-1){
										int indexNombresfin =res.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_FINAL);	// </Nombres>				
										nombresInfocorp = res.substring(indexNombresini + Constants.EQUIFAX_PATRON_NOMBRES_INICIAL.length(), indexNombresfin); 
										log.debug("nombresInfocorp "+nombresInfocorp);								
									}
																																	
								} catch (Exception e) {
									log.debug("Error : " + e.getMessage());
								}
								
								hmap.put("nombresInfocorp", apellidoPaternoInfocorp + " " + apellidoMaternoInfocorp + ", " +nombresInfocorp);
								hmap.put("explicacionInfocorp", explicacionInfocorp);	

								// FIN ------------------------
																
								interfazOCRDAO.updateInformacionInfoCom(hmap);								
								
							}						
					  }
					}//fin de res no empy					  
				}//fin del while
			}//fin ind_acti=1
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeInterfazOCRProcesarConsolidadoSolicitudCreditoCorporativa: " + e.getMessage());
		}	
		
		map.put("codigoSistema", interfazParams.getInterfaz().getCodigoSistema());
		map.put("codigoProcesoBatch", codigoProcesoBatch);
		
		try {
			String	indValidacionSTO = (String)map.get("indValidacionSTO");
			log.debug("====>Ingreso a execute Validacion Automatica<==== ::: "+indValidacionSTO);
		    if ( StringUtils.isEmpty(indValidacionSTO) || Constants.SI.equals(indValidacionSTO)){
		    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,codTipoDocu,Constants.STO_ACCI_VALI_AUTO);
		    	procesoSTOExecutionService.execute(accionTipoDocumento, map, null);
		    	interfazParams.setQueryParams(map);
		    }
		} catch (Exception e) {
			throw new InterfazException("Error al procesar executeValidacionAutomaticaDocumentoSTO: "+ e.getMessage());
		}
	}
}