package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Aurelio Oviedo
 *
 */
public interface MantenimientoZONUnidadGeograficaService extends Service {
	
	public List getUnidadesGeograficasList(Map criteria);
	
	public List getUnidadesGeograficasByCriteria(Map criteria);
	
	public List getEstructuraGeopoliticaList();
	
	public void insertUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception;
	
	public void updateUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception;
	
	public void deleteUnidadGeografica(String oid, Usuario usuario) throws Exception;
	
	/**
	 * Devuelve Unidad Geografica dependiendo del Nivel y la Descripcion
	 * @param criteria
	 * @return
	 */
	public List getUnidadesGeograficasValidar(Map criteria);
}