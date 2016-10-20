package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBRecuperacionCarteraFFVVDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;
import biz.belcorp.ssicc.service.spisicc.ReporteCOBRecuperacionCarteraFFVVService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBRecuperacionCarteraFFVVService;
/**
 * @author ESANCHEZ
 *
 */
@Service("spusicc.procesoCOBRecuperacionCarteraFFVVService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBRecuperacionCarteraFFVVServiceImpl extends BaseService implements ProcesoCOBRecuperacionCarteraFFVVService{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="spusicc.procesoCOBRecuperacionCarteraFFVVDAO")
	private ProcesoCOBRecuperacionCarteraFFVVDAO procesoCOBRecuperacionCarteraFFVVDAO;
	
	@Resource(name="spusicc.reporteCOBRecuperacionCarteraFFVVService")
	private ReporteCOBRecuperacionCarteraFFVVService service;

		

	/* 
	 * (non-Javadoc)
	 * 
	 * @see 
	 * biz.belcorp.ssicc.spusicc.cobranzas.service.ProcesoCOBRecuperacionCarteraFFVVService
	 * #executeRecuperacionCarteraFFVV(java.util.Map)
	 */
	public void executeRecuperacionCarteraFFVV(Map params) throws Exception {
		
		String codigoPais = MapUtils.getString(params, "codigoPais");
		String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo");
		
		String nombreParametro = "reporte".concat(codigoPais);
		params.put("nombreParametro", nombreParametro);
		
		//Nombre del Archivo JRXML
		String nombreArchivoReporteParam = this.getParametroGenericoSistema(params);
		params.put("nombreArchivoReporteParam", nombreArchivoReporteParam);
		
		params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		String correoGerenteRegionParam = this.getParamEmailGerenteRegion(criteria);
		
		// Lista de correos de GZ y GR
		List listCorreoGerenteZona = this.getListadoCorreosGerenteZona();
		Iterator it = listCorreoGerenteZona.iterator();
				
		Map regionesEnviadas = new HashMap();
		
		while (it.hasNext()) {
			Map map = (Map) it.next();
			
			String codigoRegion = MapUtils.getString(map, "codigoRegion");
			String codigoZona = MapUtils.getString(map, "codigoZona");
			String correoGerenteRegion = MapUtils.getString(map, "correoGerenteRegion");
			String correoGerenteZona = MapUtils.getString(map, "correoGerenteZona");
			String descripcionRegion = MapUtils.getString(map, "descripcionRegion");
			String descripcionZona = MapUtils.getString(map, "descripcionZona");
						
			params.put("codigoRegion", codigoRegion);
			params.put("codigoZona", codigoZona);
			params.put("codigoPeriodo", codigoPeriodo);
			
			params.put("descripcionRegion", descripcionRegion);
			params.put("descripcionZona", descripcionZona);
			
			if(StringUtils.isBlank(correoGerenteRegion)){
				correoGerenteRegion = correoGerenteRegionParam;							
			}

			params.put("correoGerenteRegion", correoGerenteRegion);
			params.put("correoGerenteZona", correoGerenteZona);

			if(StringUtils.isNotBlank(correoGerenteZona)){
				//Enviamos el correo a la GZ
				criteria.put("codigoPeriodo", codigoPeriodo);
				criteria.put("codigoRegion", codigoRegion);
				criteria.put("codigoZona", codigoZona);
				
				List listaCuerpGZ = procesoCOBRecuperacionCarteraFFVVDAO.getDatosCuerpoMensajeCorreoCarteraFFVV(criteria);
				params.put("listaReporte", listaCuerpGZ);
				
				params.put("flagGerenteZona", Constants.SI);
				
				service.grabarReporte(params);
			}
			else
			{
				log.warn("GZ sin correo");
			}
						
			if(StringUtils.isNotBlank(correoGerenteRegion)){
				
				//enviamos el correo solo si aun no se le ha enviado a la GR
				if(StringUtils.isBlank(MapUtils.getString(regionesEnviadas, codigoRegion, "")))
				{
					//Enviamos correo a la GR
					criteria.put("codigoPeriodo", codigoPeriodo);
					criteria.put("codigoRegion", codigoRegion);
					criteria.put("codigoZona", null);
					
					criteria.put("flagAgruparZonas", null);
					List listaCuerpGRPorRegion = procesoCOBRecuperacionCarteraFFVVDAO.getDatosCuerpoMensajeCorreoCarteraFFVVGR(criteria);					
					params.put("listaReporte", listaCuerpGRPorRegion);
					
					criteria.put("flagAgruparZonas", Constants.SI);
					List listaCuerpGRPorZona = procesoCOBRecuperacionCarteraFFVVDAO.getDatosCuerpoMensajeCorreoCarteraFFVVGR(criteria);					
					params.put("listaReporteZonas", listaCuerpGRPorZona);
					
					params.put("flagGerenteZona", Constants.NO);
					
					//Ya no se le genera el reporte solo se envia el mail
					MailParams mailParams = new MailParams();
					mailParams.setUsuario((Usuario)MapUtils.getObject(params, "usuario"));
					mailParams.setPais((Pais)MapUtils.getObject(params, "pais"));
					
					mailParams.setQueryParams(params);
										 
					try
					{
						MailResult mailResult = service.getMailService().enviarMail(mailParams);	
						if (!mailResult.isCompletado()) {
							throw new Exception("Error Envio Correo "+mailResult.getMensajeError());
						}
						
						//Guardamos el codigo de region para no volver a enviar el correo a la GR
						regionesEnviadas.put(codigoRegion, codigoRegion);						
					}
					catch(Exception ex)
					{
						log.warn("No se ha podido enviar el correo: ");
						log.warn(ex.getMessage());
					}
				}
				else
				{
					log.warn("Correo ya fue enviado a la GZ");
				}
			}
			else
			{
				log.warn("GR sin correo");
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoCOBRecuperacionCarteraFFVVService#getDatosCuerpoMensajeCorreoCarteraFFVV(java.util.Map)
	 */
	public List getDatosCuerpoMensajeCorreoCarteraFFVV(Map criteria) {
		log.debug("ServiceImpl: getDatosCuerpoMensajeCorreoCarteraFFVV");
		return procesoCOBRecuperacionCarteraFFVVDAO.getDatosCuerpoMensajeCorreoCarteraFFVV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoCOBRecuperacionCarteraFFVVService#getListadoCorreosGerenteZona()
	 */
	public List getListadoCorreosGerenteZona() {
		log.debug("ServiceImpl: getListadoCorreosGerenteZona");
		return procesoCOBRecuperacionCarteraFFVVDAO.getListadoCorreosGerenteZona();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoCOBRecuperacionCarteraFFVVService#getParamEmailGerenteRegion(java.util.Map)
	 */
	public String getParamEmailGerenteRegion(Map criteria) {
		log.debug("ServiceImpl: getParamEmailGerenteRegion");
		return procesoCOBRecuperacionCarteraFFVVDAO.getParamEmailGerenteRegion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoCOBRecuperacionCarteraFFVVService#getParametroGenericoSistema(java.util.Map)
	 */
	public String getParametroGenericoSistema(Map criteria) {		
		log.debug("ServiceImpl: getParametroGenericoSistema");
		return procesoCOBRecuperacionCarteraFFVVDAO.getParametroGenericoSistema(criteria);
	}
	
}