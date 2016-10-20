package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.sac.ProcesoSACGenerarReporteDAO;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * 
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas L.</a>
 */
@Service("lec.mailReporteLECPagoSociaEmpresaria")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteLECPagoSociaEmpresariaServiceImpl extends BaseMailReporteAbstractService {

	@Resource(name="spusicc.procesoSACGenerarReporteDAO")
	private ProcesoSACGenerarReporteDAO procesoSACGenerarReporteDAO;			
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = new String();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");		
		//Evalua el parametro a ver si lo envia a la gerente de zona				
		log.debug("getEnviarCC params " + parameterMap);
		
		// Obtiene la direccion de correo de la gerente de la zona que ingresa como parametro
		List resultado = new ArrayList();
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		
		if(codigoRegion!=null){
				resultado = procesoSACGenerarReporteDAO.getListaCorreoRegionReportePEJProgramaEjecutivas(parameterMap);
		}else {
				resultado = procesoSACGenerarReporteDAO.getListaCorreoZonaReportePEJProgramaEjecutivas(parameterMap);
		}	
		
		if (resultado != null && resultado.size()> 0) {			
			correoElectronico = (String)resultado.get(0);
			log.debug("---------------"+correoElectronico);
		}
		
		//Si no se encuentra el correo de la gerente de zona, se envia a la direccion configurada por defecto
		if (StringUtils.isBlank(correoElectronico)) {
			Map params = (Map) mailParams.getQueryParams();
			correoElectronico = (String) params.get("correoDefault");
		}
		log.debug("----ENVIARA CORREO AL ---- "+correoElectronico);
		//correoElectronico = "cbazalarlarosa@gmail.com";
		return correoElectronico;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getSubject(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getSubject(MailParams mailParams) throws Exception {
		String titulo = super.getSubject(mailParams);		
		Pais pais = mailParams.getPais();
		titulo = titulo + pais.getDescripcion();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");		
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		
		
		if(codigoRegion!=null){	
				titulo = titulo + " Region: " + (String) parameterMap.get("codigoRegion");
		}else{
				titulo = titulo + " Zona: " + (String) parameterMap.get("codigoZona");
		}
		
		return titulo;
	}

	

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
}