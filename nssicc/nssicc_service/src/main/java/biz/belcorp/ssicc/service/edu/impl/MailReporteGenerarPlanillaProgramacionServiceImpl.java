package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailReporteAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;

/**
 * Clase Service para el envio de Correo luego de generado el Reporte de Generacin de
 * Planilla Programacion. 
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Service("edu.mailReporteGenerarPlanillaProgramacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MailReporteGenerarPlanillaProgramacionServiceImpl extends BaseMailReporteAbstractService {

	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
	
	private String emailGerenteRegional;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCC(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCC(MailParams mailParams) throws Exception {
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		Map params = (Map) mailParams.getQueryParams(); 
		String indicadorReporte   = (String) parameterMap.get("indicadorReporte");
		String nivelEnvioCorreoCC = (String) params.get("nivelEnvioCorreoCC");
		if (StringUtils.isNotBlank(nivelEnvioCorreoCC)) {
			if (Constants.EDU_GENERACION_PLANILLA_NIVEL_ENVIO_NO.equals(nivelEnvioCorreoCC)) {
				return null;
			}
			if (Constants.EDU_GENERACION_PLANILLA_NIVEL_ENVIO_REGION.equals(nivelEnvioCorreoCC)) {
				if (Constants.REPORTE_EDU_GENERACION_PLANILLA_REGION.equals(indicadorReporte)){
					//Obtenemos la gerente Regional si el indicado es S
					String cc=super.getEnviarCC(mailParams);
					String indicadorEmailRegional = (String) mailParams.getQueryParams().get("indEmailRegional");
					log.debug("indicadorEmailRegional " + indicadorEmailRegional);
					if(Constants.SI.equals(indicadorEmailRegional) && StringUtils.isNotEmpty(emailGerenteRegional)){
						if(StringUtils.isNotEmpty(cc)){
							cc+=","+emailGerenteRegional;
						}else{
							cc=emailGerenteRegional;
						}
					}
					return cc; 
				}	
				else
					return null;
			}
		}	
		else {
			Usuario usuario = (Usuario) params.get("usuario");
			String error = this.getKeyMessage("reporteEDUGenerarPlanillaProgramacion.error.nivelEnvioCC",usuario);
			throw new Exception(error);
		}
		return super.getEnviarCC(mailParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseMailReporteAbstractService#getEnviarCorreoElectronico(biz.belcorp.ssicc.sisicc.service.framework.beans.MailParams)
	 */
	protected String getEnviarCorreoElectronico(MailParams mailParams) throws Exception {
		String correoElectronico = new String();
		Map parameterMap = (Map) mailParams.getQueryParams().get("parameterMap");
		String indicadorReporte = (String) parameterMap.get("indicadorReporte");
		
		/* Obteniendo email del Gerente de Region en caso el reporte sea por Region */
		if (Constants.REPORTE_EDU_GENERACION_PLANILLA_REGION.equals(indicadorReporte)) {
			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais((String) parameterMap.get("codigoPais"));
			region.setCodigoEmpresa((String) parameterMap.get("codigoEmpresa"));
			region.setCodigoRegion((String) parameterMap.get("codigoRegion"));
			List resultado = mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
			if (resultado != null && resultado.size()> 0) {
				RegionCursoCapacitacion temp = (RegionCursoCapacitacion) resultado.get(0);
				correoElectronico = temp.getCorreoInstructora();
				//seteamos el atributo de email gerente regional
				emailGerenteRegional = temp.getEmailGerenteRegional();
			}
		}
		/* Obteniendo email del Gerente de Zona en caso el reporte sea por Zona */
		else {
			ZonaCursoCapacitacion zona = new ZonaCursoCapacitacion();
			zona.setCodigoPais((String) parameterMap.get("codigoPais"));
			zona.setCodigoEmpresa((String) parameterMap.get("codigoEmpresa"));
			zona.setCodigoRegion((String) parameterMap.get("codigoRegion"));
			zona.setCodigoZona((String) parameterMap.get("codigoZona"));
			List resultado = mantenimientoEDUCursoCapacitacionDAO.getZona(zona);
			if (resultado != null && resultado.size()> 0) {
				ZonaCursoCapacitacion temp = (ZonaCursoCapacitacion) resultado.get(0);
				correoElectronico = temp.getCorreoGerenteZona();
			}
		}
		if (StringUtils.isBlank(correoElectronico)) {
			Map params = (Map) mailParams.getQueryParams();
			correoElectronico = (String) params.get("correoDefault");
		}
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
		String indicadorReporte = (String) parameterMap.get("indicadorReporte");
		titulo = titulo + " - " + "Regin: " + (String) parameterMap.get("codigoRegion");
		if (Constants.REPORTE_EDU_GENERACION_PLANILLA_ZONA.equals(indicadorReporte)) 
			titulo = titulo + " Zona: " + (String) parameterMap.get("codigoZona");
		return titulo;
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