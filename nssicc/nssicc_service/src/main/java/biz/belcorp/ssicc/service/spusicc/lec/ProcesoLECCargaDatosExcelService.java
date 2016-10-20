package biz.belcorp.ssicc.service.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoLECCargaDatosExcelService extends Service{


	public List getTipoCarga(Map map);
	
	
	public List getPrograma(Map map) ;
	
	public Map cargarArchivoExcel(Map criteria) throws Exception;
	
	public List executeValidarCargaDatos(Map params);
	
	public String executeActualizarCargaDatos(Map params);
	
	public List getListaIntermedia(Map map);

	public String getCampanhaInicialPrograma(Map params);
	
	public boolean getNumeroRegistrosResultadosLet(Map params);


}
