package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoCOMRegistrarConsultoraMasivoService extends Service{
	
	public void executeObtenerListaZonasAsociadas(Map criteria);
	
	public void executeValidarRegistroxCodigoConsultora(Map criteria);
	
	public void executeProcesarRegistros(Map criteria);
	
}