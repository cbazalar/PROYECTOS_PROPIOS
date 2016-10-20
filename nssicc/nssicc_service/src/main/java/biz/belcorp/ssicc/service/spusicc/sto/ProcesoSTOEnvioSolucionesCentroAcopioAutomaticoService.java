package biz.belcorp.ssicc.service.spusicc.sto;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoSTOEnvioSolucionesCentroAcopioAutomaticoService extends
		Service {
	
	/**
     * Metodo que es llamado desde quartz
     */
    public void executeEnvioSolucionesCentroAcopioAutomaticoJob();
}
