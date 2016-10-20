package biz.belcorp.ssicc.dao.spusicc.ventas.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.GrupoZonaVenDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;

@Repository("spusicc.grupoZonaVenDAO")
public class GrupoZonaVenDAOiBatis extends BaseDAOiBatis implements GrupoZonaVenDAO{
	
	/**
	 * Metodo que retorna  el listado de grupo zonal, de
	 *  acuerdo al criterio ingresado
	 *  @param bgrupozonam - Bean de Grupo Zonal, que contiene los criterios de busqueda
	 *  @return List  - Listado resulatdo
	 */
	public List getGrupoZona(GrupoZona bgrupozona){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.GrupoZonaVENSQL.getGrupoZona",bgrupozona);
		return resultado;
	}
	
	
	/**
	 * Metodo que retorna  el listado de grupo zonal, de
	 *  acuerdo al criterio ingresado
	 *  @param bgrupozonam - Bean de Grupo Zonal, que contiene los criterios de busqueda
	 *  @return List  - Listado resulatdo
	 */
	public List getGrupoZonaZonas(GrupoZona bgrupozona){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.GrupoZonaVENSQL.getGrupoZonaZonas",bgrupozona);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#updateGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		getSqlMapClientTemplate().update(
				"spusicc.GrupoZonaVENSQL.updateGrupoZona", bgrupozona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#insertGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.GrupoZonaVENSQL.insertGrupoZona", bgrupozona);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#deleteGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.GrupoZonaVENSQL.deleteGrupoZona", bgrupozona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#insertZonaGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.GrupoZonaVENSQL.insertZonaGrupoZona", bgrupozona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#deleteZonaGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		getSqlMapClientTemplate().insert(
				"spusicc.GrupoZonaVENSQL.deleteZonaGrupoZona", bgrupozona);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#getZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona)
	 */
	public List getZona(GrupoZona bgrupozona){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.GrupoZonaVENSQL.getZona",bgrupozona);
		return resultado;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#getListaAgrupadoGrupoZona(java.util.HashMap)
	 */
	public List getListaAgrupadoGrupoZona(){
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.GrupoZonaVENSQL.getListaAgrupadoGrupoZona",null);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.dao.GrupoZonaVenDAO#getRegion(java.util.HashMap)
	 */
	public List getRegion(HashMap criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.GrupoZonaVENSQL.getRegion",criteria);		
	}

}
