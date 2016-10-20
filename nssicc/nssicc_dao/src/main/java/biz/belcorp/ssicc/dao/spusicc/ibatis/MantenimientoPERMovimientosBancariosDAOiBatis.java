package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERMovimientosBancariosDAO;

/**
 * Implementacion del DAO de mantenimiento de movimientos bancarios
 * 
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosDAOiBatis.java.html"> <i>View
 * Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes
 *         Prncipe</a>
 * 
 */
@Repository("spusicc.mantenimientoPERMovimientosBancariosDAO")
public class MantenimientoPERMovimientosBancariosDAOiBatis extends
		BaseDAOiBatis implements MantenimientoPERMovimientosBancariosDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#getMovimientosBancarios(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public List getMovimientosBancarios(MovimientosBancariosCabecera cabecera) {
		List movimientos = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getMovimientosBancarios", cabecera);
		return movimientos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#getMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public List getMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getMovimientosBancariosCabecera",
				cabecera);
		return resultado;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#getBeanMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public MovimientosBancariosCabecera getBeanMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera) {
		return (MovimientosBancariosCabecera) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getMovimientosBancariosCabecera",	cabecera);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#getMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle)
	 */
	public List getMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getMovimientosBancariosDetalle",
				detalle);
		return resultado;
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#insertMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertMovimientosBancariosCabecera",
				cabecera);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#insertMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate()
				.insert("sisicc.InterfazSQL.insertMovimientosBancariosDetalle",
						detalle);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#updateMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.updateMovimientosBancariosCabecera",
				cabecera);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#updateMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.updateMovimientosBancariosDetalle",
				detalle);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#removeMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"spusicc.ProcesosPERSQL.removeMovimientosBancariosCabecera",
				cabecera);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#removeMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		getSqlMapClientTemplate().delete(
				"spusicc.ProcesosPERSQL.removeMovimientosBancariosDetalle",
				detalle);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.MantenimientoPERMovimientosBancariosDAO#getNextNumeroLote(java.lang.String)
	 */
	public synchronized String getNextNumeroLote(String codigoPais, String codigoTipoOrigenDatos) {
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoTipoOrigenDatos", codigoTipoOrigenDatos);
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getNextNumeroLote", params);
	}

}