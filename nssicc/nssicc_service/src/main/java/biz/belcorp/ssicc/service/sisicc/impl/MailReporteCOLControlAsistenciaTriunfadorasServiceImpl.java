package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
 *   Reporte de Porcentaje de Desviacin de Pedidos
 * 
 * @author <a href="#">Yahir Rivas Luna</a>
 */
@Service("ccc.mailReporteCOLControlAsistenciaTriunfadorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteCOLControlAsistenciaTriunfadorasServiceImpl extends BaseMailReporteAbstractService {

	@Resource(name="spusicc.procesoSACGenerarReporteDAO")
	private ProcesoSACGenerarReporteDAO procesoSACGenerarReporteDAO;			
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = null;
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");		
		//Evalua el parametro a ver si lo envia a la gerente de zona				
		log.debug("getEnviarCC params " + parameterMap);
		
		// Obtiene la direccion de correo de la gerente de la zona o region
		Map criteria = new HashMap();
		criteria.put("codigoZona", String.valueOf(parameterMap.get("codigoZona")));
		criteria.put("codigoRegion", String.valueOf(parameterMap.get("codigoRegion")));
		
		correoElectronico = procesoSACGenerarReporteDAO.getListaCorreoMAENovedadesZona(criteria);
		
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
		
	     titulo = titulo + " Zona: " + (String) parameterMap.get("codigoZona") +
	                " - Region :" + (String) parameterMap.get("codigoRegion");
		
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