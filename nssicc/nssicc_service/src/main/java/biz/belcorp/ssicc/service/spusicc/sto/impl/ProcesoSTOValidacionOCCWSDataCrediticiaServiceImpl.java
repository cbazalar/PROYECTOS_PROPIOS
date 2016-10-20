package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService;

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
@Service("spusicc.procesoSTOValidacionOCCWSDataCrediticiaService")
public class ProcesoSTOValidacionOCCWSDataCrediticiaServiceImpl extends BaseProcesoSTOValidationExecutorAbstractService{
	
	String urlSccRecep;
	String indActivoWs;
	String loginData;
	String listaParametros[];
	String indDataCrediticia;
	int cont=0;
	int cantidad=0;	
	
	protected void beforeExecuteValidation(ValidacionDocumento validacionDocumento , String namespaceSTO, Map queryParams) throws Exception {
				
		String codigoPais= validacionDocumento.getCodigoPais();
		log.debug("codigoPais "+codigoPais);		
		
		this.indActivoWs = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.IND_WS_DATCRED_OCC);
		log.debug("indActivODataCrediticia "+indActivoWs);
		
		if(StringUtils.equals(indActivoWs, Constants.NUMERO_UNO)){
		
			String codigoTipoDoc=validacionDocumento.getCodigoTipo();
			log.debug("codigoTipoDoc "+codigoTipoDoc);				
		
			urlSccRecep = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.URL_WS_OCC_DATCRED);
			log.debug("urlSccRecep "+urlSccRecep);
			
			loginData = genericoDAO.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.WS_OCC_LOGIN_DATCRED);
			log.debug("loginData "+loginData);
			
			Map criteriaOCR = new HashMap();
			criteriaOCR.put("codigoPais", codigoPais);
			criteriaOCR.put("codigoParametro", Constants.STO_ESTA_OID_INFO);			
			String valor=interfazOCRDAO.getSTOParametroGeneralOCR(criteriaOCR);
			
			if(StringUtils.isNotBlank(valor))
				this.listaParametros=StringUtils.split(valor, ",");
			else
				this.listaParametros=null;			
			
			AccionTipoDocumento accionTipoDoc = new AccionTipoDocumento(codigoPais, codigoTipoDoc, "");
			String numeroProceso=procesoSTOHistoricoDAO.getNumeroProceso(accionTipoDoc);	
			log.debug("numeroProceso "+numeroProceso);			
			
		}else{
			throw new Exception("Estado de consultora:  WS inactiva");
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOValidationExecutorAbstractService#execute(java.util.Map)
	 */
	protected boolean execute11(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		return true;
	}
	protected boolean execute(ValidacionDocumento validacionDocumento , String namespaceSTO, Map row) throws Exception {
		log.debug("ProcesoSTOValidacionOCCWSDataCrediticiaServiceImpl-EXECUTE");
		String codCliente = (String)row.get("cod_clie");
		row.put("listaParametros", this.listaParametros);
		
		if(StringUtils.isNotBlank(codCliente)){
				
			List listaConsultora=procesoSTODAO.getListOCCDataCrediticiaConsultora(namespaceSTO,row);
			if(!listaConsultora.isEmpty()){					
			cont=cont+1;	
			String tipoDocu = ((Map)listaConsultora.get(0)).get("tdoc_oid_tipo_docu").toString();
			String nroDocu = ((Map)listaConsultora.get(0)).get("num_docu_iden").toString();
			String apellido = ((Map)listaConsultora.get(0)).get("val_ape1").toString();
			String codRegion = (String)row.get("cod_regi");
			String codZona = (String)row.get("cod_zona");
					
			
			log.debug("tipoDocu "+tipoDocu);log.debug("nroDocu "+nroDocu);log.debug("apellido "+apellido);
			log.debug("codRegion "+codRegion);log.debug("codZona "+codZona);log.debug("codCliente "+codCliente);
			
			boolean validateRecord = false;
			
			if(StringUtils.isNotBlank(tipoDocu) && StringUtils.isNotBlank(nroDocu) && StringUtils.isNotBlank(apellido)
					&& StringUtils.isNotBlank(this.loginData))
				validateRecord = true;
			
			//Obtener el Oid del tipo de Documento
			String oidTipoDocu="";
			if(StringUtils.equals(tipoDocu, "2001"))
				oidTipoDocu= "1";
			if(StringUtils.equals(tipoDocu, "2003"))
				oidTipoDocu= "4";
			if(StringUtils.equals(tipoDocu, "2010"))
				oidTipoDocu= "2";
			if(StringUtils.equals(tipoDocu, "2012"))
				oidTipoDocu= "3";
			if(StringUtils.isBlank(oidTipoDocu))
				validateRecord = false;
		
		if (validateRecord){
			cantidad=cantidad+1;
			
			try{
				URL url = new URL(this.urlSccRecep);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");				
				
				
				String input = "{\"TipoDocumento\":\""+oidTipoDocu+"\",\"NumDocumento\":\""+nroDocu
						+"\",\"Apellido\":\""+apellido+"\",\"CodRegion\":\""+codRegion+"\",\"CodZona\":\""+codZona
						+"\",\"Login\":\""+this.loginData+"\"}";
				
				log.debug("entradaDataCrediticia "+input);
				
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				
				String cadena="";				
				String output;	
				while ((output = br.readLine()) != null) {
					cadena = cadena + output;
				}
				
				log.debug("salidaDataCrediticia "+cadena);				
				
				String nroDocuResult=StringUtils.substringBetween(cadena,"<a:NumDocumento>", "</a:NumDocumento>");
				String nombreResult=StringUtils.substringBetween(cadena,"<a:Nombre>", "</a:Nombre>");
				String resultadoResult=StringUtils.substringBetween(cadena,"<a:Resultado>", "</a:Resultado>");
				String estadoResult=StringUtils.substringBetween(cadena,"<a:Estado>", "</a:Estado>");
				this.indDataCrediticia="";
				if(StringUtils.equals(resultadoResult, "A"))
					this.indDataCrediticia="1";
				else
					this.indDataCrediticia="0";
				
				row.put("num_Documento", nroDocuResult);
				row.put("nombre", nombreResult);
				row.put("resultado", resultadoResult);
				row.put("estado", estadoResult);
				row.put("indicador_cred", this.indDataCrediticia);
				row.put("cod_clie", codCliente);	
				
				log.debug("nroDocuResult "+nroDocuResult); log.debug("nombreResult "+nombreResult);
				log.debug("resultadoResult "+resultadoResult); log.debug("estadoResult "+estadoResult);
				log.debug("indicadorCred "+this.indDataCrediticia); log.debug("codCliente "+codCliente);

				conn.disconnect();				
			
			}catch(Exception e){
				log.error("Error conexion WSRecepcion: " + e.getMessage());
				row.put("indicador_cred", "0");
				row.put("estado", "00");
				this.indDataCrediticia="0";
			}
			log.debug("cantidadRegistrosdata"+this.cont);
			log.debug("cantidadBien"+this.cantidad);
			
			procesoSTODAO.updateOCCDataCrediticiaConsultora(namespaceSTO,row);
			if(StringUtils.equals(this.indDataCrediticia, "1"))
				return true;
			else
				return false;
			
			}else
				return false;
		}else
			return false;	
		
		}else
			return false;
	}

}
