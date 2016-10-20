package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONActualizarUnidadesGeograficasService"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoZONActualizarUnidadesGeograficasService extends Service {
	
	public String obtenerPathUpload(String codigoPais);
	
	public void deleteTablasUnidadesGeograficas();
	
	public List getEstructurasGeopoliticas(String pais);
	
	public void executeCargarUnidadesGeograficas(Map criteria) throws Exception;
	
	public void executeValidarDatosGeograficos(Map criteria);
	
	public void executeActualizarUnidadesGeograficas(Map criteria);
	
	public List getSubgerencias();
	
	public List getTerritoriosByCriteria(Map criteria);
	
	public List getUbigeosByCriteria(Map criteria);
}
