/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifax;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifaxLocator;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio16;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService;
/**
 * @author USER
 *
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
@Service("spusicc.procesoSTOValidacionSCCEquifaxService")
public class ProcesoSTOValidacionSCCEquifaxServiceImpl extends BaseProcesoSTOValidationExecutorAbstractService{
	
	private String urlWSOCR;
	private String usuarioEquifax;
	private String claveEquifax;
	private String canalEquifax;
	private String modeloEquifax;
	private String nombreDato;
	private String tipoDato;
	private String valorDato;
	private String tipoDocEqui;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#beforeExecuteValidation(biz.belcorp.ssicc.spusicc.sto.model.ValidacionDocumento, java.lang.String, java.util.Map)
	 */
	protected void beforeExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {

		String codigoPais= validacionDocumento.getCodigoPais();
		log.debug("codigoPais "+codigoPais);
		
		urlWSOCR = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		log.debug("urlWSOCR "+urlWSOCR);
		
		Integer port = new Integer(genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		log.debug("port "+port);
		
		String hostParametro = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);
		log.debug("hostParametro "+hostParametro);
		
		//OBTENEMOS LOS PARAMAROS NECESARIOS PARA EQUIFAX
		 usuarioEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_USU_EQUI_SOCCRED);
		 claveEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_PWD_EQUI_SOCCRED);
		 canalEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_CANAL_EQUI_SOCCRED);
		 modeloEquifax = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_MODEL_EQUI_SOCCRED);
		 nombreDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_NOM_DATO_EQUI_SOCCRED);
		 tipoDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DATO_EQUI_SOCCRED);
		 valorDato = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_VALOR_DATO_EQUI_SOCCRED);
		 tipoDocEqui = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DOC_EQUI_SOCCRED);
	}
	
	
	/**
	 * @param numDocuIden
	 * @return
	 */
	private EstructuraServicio16 getEstructuraServicio16(String numDocuIden){
		EstructuraServicio16 estructuraServicio16 = new EstructuraServicio16();
		Integrante integrante=new Integrante();
		
		Header header = new Header();
		header.setCanal(canalEquifax);
		header.setClave(claveEquifax);
		header.setUsuario(usuarioEquifax);
		header.setModelo(modeloEquifax);
		estructuraServicio16.setHeader(header );
		Integrante[] integrantesServicio = new Integrante[1];
		
		Dato datosEntrada = new Dato();
		datosEntrada.setNombreDato(nombreDato);
		datosEntrada.setTipoDato(tipoDato);
		datosEntrada.setValor(valorDato);
		integrante.setDatosEntrada(new Dato[]{datosEntrada});
		integrantesServicio[0] = integrante;
		estructuraServicio16.setIntegrantesServicio(integrantesServicio);
		
		integrante.setTipoDocumento(Integer.parseInt(tipoDocEqui));
		integrante.setNumeroDocumento(numDocuIden);
		
		return estructuraServicio16;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#execute(java.util.Map)
	 */
	protected boolean execute(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		
		String valEstaInfoCome = (String)row.get("val_esta_info_come");
		String numDocuIden = (String)row.get("num_docu_iden");
		
		log.debug("numDocuIden "+numDocuIden);
		log.debug("valEstaInfoCome "+valEstaInfoCome);
		
		if (StringUtils.isNotEmpty(numDocuIden) && (valEstaInfoCome==null || StringUtils.isEmpty(valEstaInfoCome))) {
			
			String result=null;
			try{
				
					log.warn("WSEQUIFAX Inicio : " + numDocuIden);
					WebServiceEquifax locator = new WebServiceEquifaxLocator();
					WS00230301Endpoint serviceEquifax= locator.getWS00230301EndpointPort(new java.net.URL(urlWSOCR));
					log.debug("WSEQUIFAX conectado "+serviceEquifax);
					EstructuraServicio16 estructuraServicio16 = getEstructuraServicio16(numDocuIden);
					result=serviceEquifax.consultaServicio16(estructuraServicio16);
					log.warn("WSEQUIFAX Fin : " + numDocuIden);
				
			}catch(Exception e){
				log.error("Error conexion WSEQUIFAX: " + e.getMessage());
			}
			log.debug("Webservice Result "+result);
			if(StringUtils.isNotEmpty(result)){
					  
				int index =result.indexOf(Constants.EQUIFAX_PATRON_INICIAL);
				if(index!=-1){
					int indexf =result.indexOf(Constants.EQUIFAX_PATRON_FINAL);								
					String valor= result.substring(index + Constants.EQUIFAX_PATRON_INICIAL.length(), indexf);
					log.debug("valor "+valor);								
					
					String nombresInfocorp = "";
					String apellidoMaternoInfocorp = "";
					String apellidoPaternoInfocorp = "";
					String explicacionInfocorp = "";
					
					try {
												
						int indexExini =result.indexOf(Constants.EQUIFAX_PATRON_INICIAL,indexf); // <Valor>
						if(indexExini!=-1){
							int indexExfin =result.indexOf(Constants.EQUIFAX_PATRON_FINAL,indexf + Constants.EQUIFAX_PATRON_INICIAL.length());					
							explicacionInfocorp = result.substring(indexExini + Constants.EQUIFAX_PATRON_INICIAL.length(), indexExfin);
							log.debug("explicacionInfocorp "+explicacionInfocorp);									
						}	
						
						int indexAPini =result.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_INICIAL); // <ApellidoPaterno>
						if(indexAPini!=-1){
							int indexAPfin =result.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_FINAL);					
							apellidoPaternoInfocorp = result.substring(indexAPini + Constants.EQUIFAX_PATRON_APE_PAT_INICIAL.length(), indexAPfin);
							log.debug("apellidoPaternoInfocorp "+apellidoPaternoInfocorp);									
						}	
						
						int indexAMini =result.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_INICIAL); // <ApellidoMaterno>
						if(indexAMini!=-1){
							int indexAMfin =result.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_FINAL);	 // </ApellidoMaterno>				
							apellidoMaternoInfocorp = result.substring(indexAMini + Constants.EQUIFAX_PATRON_APE_MAT_INICIAL.length(), indexAMfin); 
							log.debug("apellidoMaternoInfocorp "+apellidoMaternoInfocorp);								
						}
						
						int indexNombresini =result.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_INICIAL); // <Nombres>
						if(indexNombresini!=-1){
							int indexNombresfin =result.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_FINAL);	// </Nombres>				
							nombresInfocorp = result.substring(indexNombresini + Constants.EQUIFAX_PATRON_NOMBRES_INICIAL.length(), indexNombresfin); 
							log.debug("nombresInfocorp "+nombresInfocorp);								
						}
																														
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					
					row.put("val_esta_info_come", Constants.EQUIFAX_ESTADO_APROBADO.equalsIgnoreCase(valor)?"1":"12");//definidos en el rcr
					row.put("val_nomb_info_come", apellidoPaternoInfocorp + " " + apellidoMaternoInfocorp + ", " +nombresInfocorp);
					row.put("val_expl_info_come", explicacionInfocorp);	
					
					procesoSTODAO.updateSTOData(namespaceSTO,row);								
					
				}						
			}	
		}
		
		return true;
	}
	
}
