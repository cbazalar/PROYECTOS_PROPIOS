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
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTODAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapProxy;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService;
/**
 * @author USER
 *
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
@Service("spusicc.procesoSTOValidacionSCCWSBuroCoporativoCrediticiaService")
public class ProcesoSTOValidacionSCCWSBuroCoporativoCrediticiaServiceImpl extends BaseProcesoSTOValidationExecutorAbstractService{
	
	private String urlSccRecep;
	private String tokenSccRecep;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#beforeExecuteValidation(biz.belcorp.ssicc.spusicc.sto.model.ValidacionDocumento, java.lang.String, java.util.Map)
	 */
	protected void beforeExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {

		String codigoPais= validacionDocumento.getCodigoPais();
		log.debug("codigoPais "+codigoPais);
	
		urlSccRecep = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SCCRECEP);
		log.debug("urlSccRecep "+urlSccRecep);
		
		tokenSccRecep = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TOKEN_WS_SCCRECEP);
		log.debug("tokenSccRecep "+tokenSccRecep);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#execute(java.util.Map)
	 */
	protected boolean execute(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		
		String valEstaInfoCome = (String)row.get("val_esta_info_come");
		String numDocuIden = (String)row.get("num_docu_iden");
		String indSituCred = (String)row.get("ind_situ_cred");
		
		log.debug("numDocuIden "+numDocuIden);
		log.debug("valEstaInfoCome "+valEstaInfoCome);
		log.debug("indSituCred "+indSituCred);
		
		boolean validateRecord = true;
		
		if (StringUtils.isEmpty(numDocuIden)) validateRecord = false;
		if (StringUtils.isNotEmpty(indSituCred)&& indSituCred.equals("1")) validateRecord = false;
		if (StringUtils.isNotEmpty(valEstaInfoCome)&& valEstaInfoCome.equals("1")) validateRecord = false;
		
		
		if (validateRecord){
			String result = null;
			
			try{
				log.warn("WSRecepcion Inicio : " + numDocuIden);
				WsInfoCrediticiaSoapProxy serviceSP = new WsInfoCrediticiaSoapProxy();
				serviceSP.setEndpoint(urlSccRecep);
				log.debug("WSRecepcion conectado "+serviceSP);
				result = serviceSP.consultar(numDocuIden, tokenSccRecep);
				log.warn("WSRecepcion Fin : " + numDocuIden);
				
			}catch(Exception e){
				log.error("Error conexion WSRecepcion: " + e.getMessage());
			}
			log.debug("Webservice Result "+result);
			row.put("val_nomb_info_come", result);
			row.put("val_esta_info_come", result);
			row.put("ind_situ_cred", "");
			//row.put("ind_erro_info_come", "");
			//row.put("val_expl_info_come", "");
			
			row.put("ind_tele_ok", "1"); //Paso por web service

							if(result.equals("1")){
									row.put("val_esta_info_come", "1");
									row.put("ind_situ_cred", result);
									row.put("val_nomb_info_come", "1: Apto");		
							}
							
						  if(result.equals("0")){
									row.put("val_esta_info_come", "12");
									row.put("ind_situ_cred", result);
									row.put("val_nomb_info_come", "0: No Apto");		
							}
							
							if (result.equals("T")) {
								row.put("val_esta_info_come", "5");
								row.put("ind_situ_cred", "0");
								row.put("val_nomb_info_come", "T: Token Incorrecto");
							}							
							
							if (result.equals("E")){ 
								row.put("val_esta_info_come", "5");
								row.put("ind_situ_cred", "0");
								row.put("val_nomb_info_come", "E: Error de servicio Externo");
							}
							
							if (result.equals("B")) {
								row.put("val_esta_info_come", "5");
								row.put("ind_situ_cred", "0");
								row.put("val_nomb_info_come", "B: Error de base de dato");
							}
			
			procesoSTODAO.updateSTOData(namespaceSTO,row);
		}
		
		return true;
	}
}
