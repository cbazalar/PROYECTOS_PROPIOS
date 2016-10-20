package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/* CREADO EN EL NSSICC DEBIDO QUE SE NECESITABA INTERFACES PARA LOS REPORTES SERVICES */
public interface BaseReporteInterfaceService extends Service {
	
	/**
	 * Ejecuta Reporte respectivo
	 * @param criteria 
	 */
	public void grabarReporte(Map params) throws Exception;

}
