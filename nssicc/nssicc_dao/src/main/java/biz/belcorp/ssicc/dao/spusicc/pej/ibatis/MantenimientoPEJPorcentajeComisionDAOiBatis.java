package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJPorcentajeComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.PorcentajeComisionesCabecera;
import biz.belcorp.ssicc.dao.spusicc.pej.model.PorcentajeComisionesDetalle;

/**
 * @author Jesse James Rios Franco
 *
 */

public class MantenimientoPEJPorcentajeComisionDAOiBatis extends BaseDAOiBatis implements MantenimientoPEJPorcentajeComisionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#getNivelList(java.lang.String)
	 */
	public List getNivelList(String codigoPais){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getNivelList", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#getPorcentajesComisionesList(java.util.Map)
	 */
	public List getPorcentajesComisionesList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getPorcentajesComisionesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#getPorcentajeComisionDetalleList(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesCabecera)
	 */
	public List getPorcentajeComisionDetalleList(PorcentajeComisionesCabecera porcentajeComisionesCabecera){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getPorcentajeComisionDetalleList",porcentajeComisionesCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#updatePorcentajeComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesCabecera)
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(PorcentajeComisionesCabecera porcentajeComisionesCabecera){
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updatePorcentajeComisionCabeceraAndDetalle", porcentajeComisionesCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#getSecuenciaNextValue()
	 */
	public String getSecuenciaNextValue() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getSecuenciaNextValue");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#insertPorcentajeComisionCabecera(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesCabecera)
	 */
	public void insertPorcentajeComisionCabecera(PorcentajeComisionesCabecera porcentajeComisionesCabecera){
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertPorcentajeComisionCabecera",porcentajeComisionesCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#insertPorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesDetalle)
	 */
	public void insertPorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertPorcentajeComisionDetalle", porcentajeComisionesDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#getMaximoNumeroNivel(java.util.Map)
	 */
	public Integer getMaximoNumeroNivel(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getMaximoNumeroNivel",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#updatePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesDetalle)
	 */
	public void updatePorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updatePorcentajeComisionDetalle", porcentajeComisionesDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#deletePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesDetalle)
	 */
	public void deletePorcentajeComisionDetalle(PorcentajeComisionesDetalle porcentajeComisionesDetalle) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deletePorcentajeComisionDetal", porcentajeComisionesDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#deletePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesCabecera)
	 */
	public void deletePorcentajeComisionDetalle(PorcentajeComisionesCabecera porcentajeComisionesCabecera) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deletePorcentajeComisionDetalle", porcentajeComisionesCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJPorcentajeComisionDAO#deletePorcentajeComisionCabecera(biz.belcorp.ssicc.spusicc.pej.model.PorcentajeComisionesCabecera)
	 */
	public void deletePorcentajeComisionCabecera(PorcentajeComisionesCabecera porcentajeComisionesCabecera) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deletePorcentajeComisionCabecera", porcentajeComisionesCabecera);
	}
}