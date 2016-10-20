package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoMAECargaInformacionMasivoService extends Service {

	public String validarCliente(Map params);
	
	public String validarDireccion(Map params);
	
	public void executeProcesarRegistros(Map criteria) ;
	
	
	
}
