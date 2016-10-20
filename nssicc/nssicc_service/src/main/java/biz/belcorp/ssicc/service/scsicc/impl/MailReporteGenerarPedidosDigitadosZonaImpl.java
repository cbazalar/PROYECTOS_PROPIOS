package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.ProcesoSICGenerarPedidosDigitadosZonaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;


/**
 * Clase Service para el envio de Correo luego de generado el Reporte de Generacin de
 * Pedidos Digitados por Zona. 
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sic.mailReporteGenerarPedidosDigitadosZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteGenerarPedidosDigitadosZonaImpl extends BaseMailReporteAbstractService {

	@Resource(name="scsicc.procesoSICGenerarPedidosDigitadosZonaDAO")
	private ProcesoSICGenerarPedidosDigitadosZonaDAO procesoDAO;
	
	private String mailGerenteRegional;
	Map map = new HashMap();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = new String();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String indicadorReporte = (String) parameterMap.get("indicadorReporte");
		Map parameters = (Map) mailParams.getQueryParams();
		
		
		/* Obteniendo email del Gerente de Region en caso el reporte sea por Region */
		if (Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_REGION.equals(indicadorReporte)) {
			
			map.put("codigoRegion",(String) parameterMap.get("codigoRegion"));
			correoElectronico = procesoDAO.getCorreoGerenteRegion(map);

			parameters.put("subject",(String)mailParams.getQueryParams().get("subject")+" "+(String) parameterMap.get("codigoRegion"));
			parameters = prepareParameterMap(parameters);
			parameters.put("subject",(String)mailParams.getQueryParams().get("subject")+" "+(String) parameterMap.get("codigoRegion"));
			mailParams.setQueryParams(parameters);
			
		}
		/* Obteniendo email del Gerente de Zona en caso el reporte sea por Zona */
		else {
						
			map.put("codigoRegion",(String) parameterMap.get("codigoRegion"));//VIENE NULL

			map.put("codigoZona",(String) parameterMap.get("codigoZona"));
			//obteniendo gerente regional 
			mailGerenteRegional = procesoDAO.getCorreoGerenteRegionByCodigoZona(map);
			correoElectronico = procesoDAO.getCorreoGerenteZona(map);
			
			parameters.put("subject",(String)mailParams.getQueryParams().get("subject")+" "+(String) parameterMap.get("codigoZona"));
			parameters = prepareParameterMap(parameters);
			parameters.put("subject",(String)mailParams.getQueryParams().get("subject")+" "+(String) parameterMap.get("codigoZona"));
			mailParams.setQueryParams(parameters);
			
		}
		if (StringUtils.isBlank(correoElectronico)) {
			Map params = (Map) mailParams.getQueryParams();
			correoElectronico = (String) params.get("correoDefault");
		}
		
		log.debug("getEnviarCorreoElectronico >>>>>>>>> " +correoElectronico);
		log.debug("subJect >>>>>>>>> " +(String)mailParams.getQueryParams().get("subject"));
		
		return correoElectronico;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		String correoCC= super.getEnviarCC(mailParams);
		String indicadorEmailRegional = (String) mailParams.getQueryParams().get("indEmailRegional");
		
		log.debug("indicadorEmailRegional " + indicadorEmailRegional);
		if(Constants.SI.equals(indicadorEmailRegional)){
		    
			if(StringUtils.isNotEmpty(correoCC)){
				correoCC+=","+mailGerenteRegional;
			}else{
				correoCC=mailGerenteRegional;
			}
		}
		
		return correoCC;
	}
	
	/* INI NSSICC */
	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String plantillaBody, Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMailAdjunto(String from, String destinos,
			String subject, String plantillaTxt, String plantillaHtml,
			Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailResult enviarMail(String from, String destinos, String copias,
			String subject, String plantillaBody, Map parametros) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public MailResult enviarMail(String from, String destinos, String subject,
			String mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	/* FIN NSSICC */
	

}