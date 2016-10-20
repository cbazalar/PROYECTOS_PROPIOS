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
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService;
/**
 * @author USER
 *
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
@Service("spusicc.procesoSTOValidacionSCCWSRutCrediticiaService")
public class ProcesoSTOValidacionSCCWSRutCrediticiaServiceImpl extends BaseProcesoSTOValidationExecutorAbstractService{
	
	private String urlWSOCR;
	private Integer port;
	private String hostParametro;
	private String serie;
	private String zona;
	private String usuario;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#beforeExecuteValidation(biz.belcorp.ssicc.spusicc.sto.model.ValidacionDocumento, java.lang.String, java.util.Map)
	 */
	protected void beforeExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {

		String codigoPais= validacionDocumento.getCodigoPais();
		log.debug("codigoPais "+codigoPais);
	
		urlWSOCR = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		log.debug("urlWSOCR "+urlWSOCR);
		
		port = new Integer(genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		log.debug("port "+port);
		
		hostParametro = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);
		log.debug("hostParametro "+hostParametro);
		
		serie="";
		zona="";
		usuario="";
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#execute(java.util.Map)
	 */
	protected boolean execute(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		
		String valEstaInfoCome = (String)row.get("val_esta_info_come");
		String numDocuIden = (String)row.get("num_docu_iden");
		
		log.debug("numDocuIden "+numDocuIden);
		log.debug("valEstaInfoCome "+valEstaInfoCome);
		
		if (StringUtils.isNotEmpty(numDocuIden) && StringUtils.isNotEmpty(valEstaInfoCome) && !valEstaInfoCome.equals("0") && !valEstaInfoCome.equals("1") ){
			
			String result=null;
			try{
				log.warn("WSBoletinElectronico Inicio : " + numDocuIden);
				IConsultaBelcorpservice locator = new IConsultaBelcorpserviceLocator();
				IConsultaBelcorp service =locator.getIConsultaBelcorpPort(new java.net.URL(urlWSOCR));
				log.debug("WSBoletinElectronico conectado "+service);
				result= service.getEvaluacionBelcorp(hostParametro, port, numDocuIden, serie, zona, usuario);
				log.warn("WSBoletinElectronico Fin : " + numDocuIden);
			}catch(Exception e){
				log.error("Error conexion WSBoletinElectronico: " + e.getMessage());
			}
			log.debug("Webservice Result "+result);
			
			String montoInfoCom ="";
			String estado ="";
			String error ="";
			
			if(StringUtils.isNotEmpty(result)){
				//reporte|montoBic|MontoMic|MontoInfoCom|Estado|Mensaje|Error
				String [] split = StringUtils.split(result,"|");
				if(split.length == 7){
					log.debug("split "+split[3]+" --- "+split[4]+"---"+split[6]);
					montoInfoCom =split[3];
					estado =split[4];
					error =split[6];
					row.put("val_mont_info_come",montoInfoCom);
					row.put("val_esta_info_come", estado);
					row.put("ind_erro_info_come", error);					
				}
			}
			procesoSTODAO.updateSTOData(namespaceSTO,row);
		}
		
		return true;
	}
	
}
