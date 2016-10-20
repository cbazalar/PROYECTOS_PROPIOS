package biz.belcorp.ssicc.service.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoLECEnviarReporteProyeccionService extends Service{

	public void executeEnviarReporteProyeccion(Map params) throws Exception;
}
