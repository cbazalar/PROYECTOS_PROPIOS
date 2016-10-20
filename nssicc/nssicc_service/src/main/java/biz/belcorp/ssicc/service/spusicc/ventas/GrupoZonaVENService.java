package biz.belcorp.ssicc.service.spusicc.ventas;

import java.util.HashMap;
import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * Interfaz de la capa logica referente a Grupo Zonal
 * @author peextjnunez
 *
 */

public interface  GrupoZonaVENService extends Service{
	
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

