package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.EstructuraGeografica;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONActualizarUnidadesGeograficasDAO"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoZONActualizarUnidadesGeograficasDAO extends DAO {

	public String obtenerPathUpload(String codigoPais);
	
	public void deleteTablasUnidadesGeograficas();

	public List getEstructurasGeopoliticas(String pais);
	
	public void insertEstructuraGeografica(EstructuraGeografica estructuraGeografica);
	
	public void executeValidarDatosGeograficos(Map criteria);
	
	public void executeValidarEstructurasGeograficas(Map criteria);

	public void executeActualizarUnidadesGeograficas(Map criteria);
	
	public List getSubgerencias();
	
	public List getTerritoriosByCriteria(Map criteria);
	
	public List getUbigeosByCriteria(Map criteria);
}
