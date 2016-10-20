/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

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
 * Clase Service para el envio de Correo luego de Generar
 *   Reporte Detallado de Consultoras Habiles
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("ccc.mailReporteFLXDetalladoConsultorasHabilesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteFLXDetalladoConsultorasHabilesServiceImpl extends BaseMailReporteAbstractService {

	@Resource(name="spusicc.procesoSACGenerarReporteDAO")
	private ProcesoSACGenerarReporteDAO procesoSACGenerarReporteDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = null;
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		//Evalua el parametro a ver si lo envia a la gerente de zona
		log.debug("getEnviarCC params " + parameterMap);
		
		String resultConsHabil = procesoSACGenerarReporteDAO.getResultConsulorasHabiles(parameterMap);
		
		if(!StringUtils.equalsIgnoreCase(resultConsHabil, "0"))
			correoElectronico = procesoSACGenerarReporteDAO.getListaCorreoFLXNovedadesZona(parameterMap);
		
		log.debug("----ENVIARA CORREO AL ---- "+correoElectronico);
		
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
	    titulo = titulo + " Zona: " + (String) parameterMap.get("codigoZona");
		
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
