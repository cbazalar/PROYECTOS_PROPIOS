/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEjecucionValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;


/**
 * @author USER
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOSolicitudCreditoServiceImpl extends BaseProcesoSTOAbstractService {
	
	
	private BaseMailService mailUtil;
	private ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO;
	
	
	
	/**
	 * @param mailUtil the mailUtil to set
	 */
	public void setMailUtil(BaseMailService mailUtil) {
		this.mailUtil = mailUtil;
	}

	
	/**
	 * @param procesoSTOEjecucionValidacionesDAO the procesoSTOEjecucionValidacionesDAO to set
	 */
	public void setProcesoSTOEjecucionValidacionesDAO(
			ProcesoSTOEjecucionValidacionesDAO procesoSTOEjecucionValidacionesDAO) {
		this.procesoSTOEjecucionValidacionesDAO = procesoSTOEjecucionValidacionesDAO;
	}

	/**
	 * Metodo ejecutado despues de 'processInterfaz'. Este mtodo no tiene
	 * implementacin, su intencion es el de ser sobrescrito en caso se requiera
	 * realizar una tarea adicional despues del procesamiento de la Interfaz.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	public void afterProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessValidacionDocumentoSTO SOLICITUD CREDITO' method");
		
		Map criteria = new HashMap();
		
		TipoDocumentoDigitado tipoDocumentoDigitado = documentoSTOParams.getTipoDocumentoDigitado();
		HistoricoTipoDocumento historico = documentoSTOParams.getHistoricoTipoDocumento();
		criteria.put("codigoPais", tipoDocumentoDigitado.getCodPais());
		criteria.put("numeroProceso",historico.getNumeroProceso());
		criteria.put("tipoDocumento", tipoDocumentoDigitado.getCodTipoDocu());
		criteria.put("codigoParametro", Constants.STO_PARAMETRO_INDICADOR_MAIL_GZ_SCC);		
		criteria.put("observaciones"," ");
		criteria.put("usuario", documentoSTOParams.getUsuario());
		
		log.debug("Antes de Entrar Aqui0");
		if(procesoSTODAO.getParametroSTO(criteria).equals(Constants.STO_INDICADOR_MAIL_GZ_SCC_OK))
		{
			    log.debug("Ingreso Aqui1");
				executeEnvioMailSCC(criteria);
		}
	}
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void afterRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO SOLICITUD CREDITO' method");
		
		Map criteria = new HashMap();
		
		TipoDocumentoDigitado tipoDocumentoDigitado = documentoSTOParams.getTipoDocumentoDigitado();
		HistoricoTipoDocumento historico = documentoSTOParams.getHistoricoTipoDocumento();
		criteria.put("codigoPais", tipoDocumentoDigitado.getCodPais());
		criteria.put("numeroProceso",historico.getNumeroProceso());
		criteria.put("tipoDocumento", tipoDocumentoDigitado.getCodTipoDocu());
		criteria.put("codigoParametro", Constants.STO_PARAMETRO_INDICADOR_MAIL_GZ_SCC);		
		Map params = documentoSTOParams.getQueryParams();
		String observaciones = (String) params.get("observaciones");
		criteria.put("observaciones",observaciones);
		criteria.put("usuario", documentoSTOParams.getUsuario());
		
		if(procesoSTODAO.getParametroSTO(criteria).equals(Constants.STO_INDICADOR_MAIL_GZ_SCC_OK))
		{		
				executeEnvioMailSCC(criteria);
		}
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeEnvioMailSCC(java.util.Map)
	 */
	public void executeEnvioMailSCC(Map criteria){
			
		procesoSTODAO.executeEnvioMailSCC(criteria);
		
		try {
			List listaCorreos = procesoSTODAO.getEnvioMailsSCCList(criteria);
			Map params =  (Map)(procesoSTODAO.getEnvioMailsSCCParams(criteria).get(0));
			
			//------Parametros Generales------		
			String correoOrigen     = params.get("mailOrigen").toString();
			String plantillaOK      = params.get("plantillaOK").toString();
			String plantillaRechazo = params.get("plantillaRechazo").toString();
			//Obteniendo el mail de copia
			log.debug("plantillaOK "+plantillaOK);
			String mailCopia = "";
			if ( params.get("mailCopia")!= null) mailCopia = params.get("mailCopia").toString();
			
			//--------------------------------
			
			String vParamCorreoCli = "";
			boolean flagEmailCons = false;
			Map parametros = new HashMap();
			parametros.put("codigoPais", criteria.get("codigoPais"));
			parametros.put("codigoSistema", Constants.STO_CODIGO_SISTEMA);
			parametros.put("nombreParametro", Constants.STO_IND_CORREO_CONSULTORA);

			vParamCorreoCli = procesoSTODAO.getParametroGenericoSistema(parametros);
			
			criteria.put("codigoParametro", Constants.STO_PARAM_MSG_GERE);
			String mensajeGerente = procesoSTOEjecucionValidacionesDAO.getObtenerValParametroSTO(criteria);
			
			criteria.put("codigoParametro", Constants.STO_PARAM_MSG_NDOC);
			String mensajeNumeroOperacion = procesoSTOEjecucionValidacionesDAO.getObtenerValParametroSTO(criteria);
			log.debug("Mensaje Documento Interno: "+mensajeNumeroOperacion);
			
			criteria.put("codigoParametro", Constants.STO_PARAM_MSG_CDIG);
			String mensajeDigitoVerificacion = procesoSTOEjecucionValidacionesDAO.getObtenerValParametroSTO(criteria);
			
			criteria.put("vMensajeGerente", "");
			if (StringUtils.isNotBlank(mensajeGerente)){
				criteria.put("vMensajeGerente", mensajeGerente);
			}
			
			if (vParamCorreoCli!=null){
				if (StringUtils.isNotBlank(vParamCorreoCli)){
					if (vParamCorreoCli.equals("S")){
						flagEmailCons = true;
					}
				}
			}
			
			log.debug("mailCopia: Test 0 ----->"+mailCopia);
		    log.debug("Tama�o de lista :"+listaCorreos.size());
			//listaCorreos.size();
			for (int i = 0; i < listaCorreos.size(); i++) {
				Map result = new HashMap();
				result = (Map)listaCorreos.get(i);
				
				criteria.put("nombreCliente", result.get("nombreCliente"));
				criteria.put("tipoDocumento", result.get("tipoDocumento"));
				criteria.put("codigoCliente", result.get("codigoCliente"));
				criteria.put("observaciones", result.get("observaciones"));
				criteria.put("numeroOperacion", result.get("numeroOperacion"));
				criteria.put("numeroDocuIden", result.get("numeroDocuIden"));
				criteria.put("descMotivoRechazo", result.get("descMotivoRechazo"));
				
				if (result.get("valMailClie")!=null){
					if (!result.get("valMailClie").toString().equals("") && flagEmailCons==true){
						mailCopia = mailCopia + "," + result.get("valMailClie").toString(); 
					}
				}
				
				criteria.put("vMensajeNumeroOperacion", "");
				if (StringUtils.isNotBlank(mensajeNumeroOperacion)){
					criteria.put("vMensajeNumeroOperacion", mensajeNumeroOperacion + " " +result.get("numeroOperacion"));	
				}
				criteria.put("vMensajeDigitoVerificacion", "");
				if (StringUtils.isNotBlank(mensajeDigitoVerificacion)){
					criteria.put("vMensajeDigitoVerificacion", mensajeDigitoVerificacion + " " +result.get("digitoVerificador"));	
				}
				
				log.debug("mailCopia: Test 1 ----->"+mailCopia);
				
				if ( (result.get("emailGerentaZona") != null) && (StringUtils.isNotBlank((String)result.get("emailGerentaZona")) )	 ){
					
						if(result.get("indicadorRechazo").equals(Constants.STO_INDICADOR_RECHAZADO)){
							//Rechazo
							mailUtil.enviarMail(correoOrigen,                              //correoOrigen
												result.get("emailGerentaZona").toString(), //correo Destino
												mailCopia,								   //correo CC
												result.get("subjectCorreo").toString(),    //Subject  
												plantillaRechazo,                          //plantilla 
									            criteria);
						}
						else{
							//Paso
							mailUtil.enviarMail(correoOrigen,                              //correoOrigen
												result.get("emailGerentaZona").toString(), //correo Destino
												mailCopia,								   //correo CC
												result.get("subjectCorreo").toString(),    //Subject  
												plantillaOK,                               //plantilla
									            criteria);
						}
			    }
			}
		} catch (Exception e) {
			log.error("Error al Enviar Correos" + e);
			log.warn("Se continua con el proceso");
		}
		
		
	}
	
}
