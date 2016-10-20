package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;

/**
 * @author Aurelio Oviedo
 *
 */
public interface MantenimientoZONUnidadGeograficaDAO extends DAO {
	
	public List getUnidadesGeograficasList(Map criteria);
	
	public List getUnidadesGeograficasByCriteria(Map criteria);
	
	public List getEstructuraGeopoliticaList();
	
	public Integer getCodigoSiguienteUnidadGeografica();
	
	public void insertUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception;
	
	public void updateUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception;
	
	public int getCantidadUnidadesGeograficasByNivel(Map criteria);
	
	public List getCantidadTerritoriosByUnidadGeografica(String oidUg);
	
	public void removeUnidadGeografica(UnidadGeografica ug, Usuario usuario);
	
	public void deleteUnidadGeografica(UnidadGeografica ug, Usuario usuario);
	
	/**
	 * Devuelve Unidad Geografica dependiendo del Nivel y la Descripcion
	 * @param criteria
	 * @return
	 */
	public List getUnidadesGeograficasValidar(Map criteria);
}