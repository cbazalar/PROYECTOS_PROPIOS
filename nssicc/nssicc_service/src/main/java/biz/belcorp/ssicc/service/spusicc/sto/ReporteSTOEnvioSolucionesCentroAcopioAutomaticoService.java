package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ReporteSTOEnvioSolucionesCentroAcopioAutomaticoService extends
		Service {
	public void grabarReporte(Map params) throws Exception;
}
