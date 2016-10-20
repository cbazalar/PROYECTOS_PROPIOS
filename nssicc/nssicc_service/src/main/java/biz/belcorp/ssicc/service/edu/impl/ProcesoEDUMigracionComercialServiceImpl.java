package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUMigracionComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */

public class ProcesoEDUMigracionComercialServiceImpl extends BaseService  
		implements ProcesoEDUMigracionComercialService	{
	

	ProcesoEDUComercialService	procesoEDUComercialService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDUMigracionComercialService#executeMigracionComercial(java.lang.String, java.util.Map)
	 */
	public void executeMigracionComercial(String codigoPais, Map params) throws Exception {
		
		/* Obteniendo lista de consultoras a migrar */
		List listaConsultorasMigracion = procesoEDUComercialService.getMigracionComercialConsultora(codigoPais, params);
		
		/* Insertando lista en tabla temporal oracle */
		procesoEDUComercialService.insertMigracionComercialConsultoraTemporal(codigoPais, listaConsultorasMigracion);
	}

	/**
	 * @return Returns the procesoEDUComercialService.
	 */
	public ProcesoEDUComercialService getProcesoEDUComercialService() {
		return procesoEDUComercialService;
	}

	/**
	 * @param procesoEDUComercialService The procesoEDUComercialService to set.
	 */
	public void setProcesoEDUComercialService(
			ProcesoEDUComercialService procesoEDUComercialService) {
		this.procesoEDUComercialService = procesoEDUComercialService;
	}
	
	
	
	
}
