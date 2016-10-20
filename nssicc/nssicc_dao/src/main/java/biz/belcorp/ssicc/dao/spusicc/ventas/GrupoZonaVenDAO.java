package biz.belcorp.ssicc.dao.spusicc.ventas;

import java.util.HashMap;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;

/**
 * Interfaz de Grupo Zona
 * @author peextjnunez
 *
 */
public interface GrupoZonaVenDAO extends DAO {
	
	public List getGrupoZona(GrupoZona bgrupozona);
	public List getGrupoZonaZonas(GrupoZona bgrupozona);
    public void updateGrupoZona(GrupoZona bgrupozona, Usuario usuario);
	public void insertGrupoZona(GrupoZona bgrupozona, Usuario usuario);
	public void deleteGrupoZona(GrupoZona bgrupozona, Usuario usuario);
	
	public void insertZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario);
	public void deleteZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario);
	
	public List getZona(GrupoZona bgrupozona);
	public List getListaAgrupadoGrupoZona();
	
	/**
	 * Metodo que devuelve las regiones
	 * @param criteria
	 * @return
	 */
	public List getRegion(HashMap criteria);

}
