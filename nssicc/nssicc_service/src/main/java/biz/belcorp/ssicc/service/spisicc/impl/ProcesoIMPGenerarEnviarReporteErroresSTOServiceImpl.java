package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spisicc.ReporteIMPGenerarEnviarReporteErroresSTOService;


/**
 * Service para el proceso de actulaizacion secuenciacion
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
@Service("spisicc.procesoIMPGenerarEnviarReporteErroresSTOService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPGenerarEnviarReporteErroresSTOServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	
	@Resource(name="spisicc.reporteIMPGenerarEnviarReporteErroresSTOService")
	private ReporteIMPGenerarEnviarReporteErroresSTOService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService
	 * #executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {

		log.debug("inicio ProcesoIMPGenerarEnviarReporteErroresSTOServiceImpl"
				+ params);

		procesoImpresionDAO.executeCargaTemporalReporteErroresSTO(params);

		Map criteria2 = new HashMap();
		
		criteria2.put("codigoPais", MapUtils.getString(params, "codigoPais"));
		criteria2.put("codigoParametro", Constants.STO_PARAMETRO_INDICADOR_MAIL_REPORTE_STO);		
		

		if(procesoImpresionDAO.getParametroSTO(criteria2).equals(Constants.ESTADO_ACTIVO)) {
			

		String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
		String codigoPais = MapUtils.getString(params, "codigoPais");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		
		String correoGerenteRegionParam = procesoImpresionDAO.getParamEmailGerenteRegion(criteria);
		String correoGerenteFinal;
		
		if(StringUtils.isBlank(correoGerenteRegionParam)){
			log.warn("Param de correo no configurado en la Bas_Param_pais: emaGerenteRegionDefecto");			
		}
		// Lista de gerentes GR
		List listCorreoGerenteRegion = procesoImpresionDAO.getCorreosGerenteRegion(codigoUsuario);
		params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));		
		Iterator it = listCorreoGerenteRegion.iterator();
		
		while (it.hasNext()) {
			Map map = (Map) it.next();
			correoGerenteFinal = "";
			
			String codigoRegion = MapUtils.getString(map, "codigoRegion");
			String correoGerenteRegion = MapUtils.getString(map, "correoGerenteRegion");
			
			String descripcionRegion = MapUtils.getString(map, "descripcionRegion");
			
			if(StringUtils.isBlank(correoGerenteRegion)){
				correoGerenteFinal = correoGerenteRegionParam;
			}else{
				correoGerenteFinal = correoGerenteRegion;
			}
			
			if(StringUtils.isNotBlank(correoGerenteFinal)){
				params.put("correoGerente", correoGerenteFinal);
				params.put("codigoRegion", codigoRegion);
				params.put("codigoZona", null);
				
				params.put("descripcionRegion", descripcionRegion);
				params.put("tipoOpcion", "0");
				
				if (log.isDebugEnabled())
					log.debug(params);

				try {
					service.grabarReporte(params);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}else{
				log.warn("Correo No vlido para la regin: " + codigoRegion);
			}			
		}

		// Lista de gerentes GZ
		List listCorreoGerenteZona = procesoImpresionDAO.getCorreosGerenteZona(codigoUsuario);
		Iterator it2 = listCorreoGerenteZona.iterator();
		while (it2.hasNext()) {
			Map map2 = (Map) it2.next();
			correoGerenteFinal = "";
			
			String codigoRegion = MapUtils.getString(map2, "codigoRegion");
			String correoGerenteRegion = MapUtils.getString(map2, "correoGerenteRegion");
			String codigoZona = MapUtils.getString(map2, "codigoZona");
			String correoGerenteZona = MapUtils.getString(map2, "correoGerenteZona");
			
			String descripcionRegion = MapUtils.getString(map2, "descripcionRegion");
			String descripcionZona = MapUtils.getString(map2, "descripcionZona");

			/*
			if(StringUtils.isBlank(correoGerenteRegion)){
				if(StringUtils.isBlank(correoGerenteZona)){
					correoGerenteFinal = correoGerenteRegionParam;
				}else{
					correoGerenteFinal = correoGerenteZona;
				}
			}else{
				correoGerenteFinal = correoGerenteRegion;
			}
			*/
			
			// Si existe el correo de la gerente de zona
			if(!StringUtils.isBlank(correoGerenteZona)){
				correoGerenteFinal = correoGerenteZona;
			}
			// Si no existe correo de gerente de zona
			else{
				if(StringUtils.isBlank(correoGerenteRegion)){
					// Si tampoco hay correo de GR, se envia a la direccin por defecto
					correoGerenteFinal = correoGerenteRegionParam;					
				}else{
					// Si existe correo de GR, se le envia el de la zona que no tiene
					correoGerenteFinal = correoGerenteRegion;
				}
			}
			
			
			if(StringUtils.isNotBlank(correoGerenteFinal)){
				params.put("correoGerente", correoGerenteFinal);
				params.put("codigoRegion", codigoRegion);
				params.put("codigoZona", codigoZona);
				
				params.put("descripcionRegion", descripcionRegion);
				params.put("descripcionZona", descripcionZona);
				params.put("tipoOpcion", "1");
	
				if (log.isDebugEnabled())
					log.debug(params);
	
				try {
					service.grabarReporte(params);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
				
			}else{
				log.warn("Correo No vlido para la regin: " + codigoRegion + " y Zona: " + codigoZona);
			}
		}

	}
	
	}	
}
