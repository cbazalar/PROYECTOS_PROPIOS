package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEMantenerMapaCDDAO;


/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPEMantenerMapaCDDAO")
public class MantenimientoAPEMantenerMapaCDDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEMantenerMapaCDDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getAnaquelDestinoListar(java.util.Map)
	 */
	public List getAnaquelDestinoListar(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAnaquelDestinoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getAnaquelOrigenListar(java.util.Map)
	 */
	public List getAnaquelOrigenListar(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAnaquelOrigenList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getObtenerOidSubLineaxOidMapaCDDet(java.util.Map)
	 */
	public String getObtenerOidSubLineaxOidMapaCDDet(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerSubLineaxOidMapaCDDet", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getValidaNoExisteAsignaProductoAAnaquelDet(java.util.Map)
	 */
	public String getValidaNoExisteAsignaProductoAAnaquelDet(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getValidaNoExisteAsignaProductoAAnaquelDet", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getOidMarcaxCod(java.util.Map)
	 */
	public String getOidMarcaxCod(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMarcaxCod", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getOidCanalxCod(java.util.Map)
	 */
	public String getOidCanalxCod(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidCanalxCod", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getOidPeriodo(java.util.Map)
	 */
	public String getOidPeriodo(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPeriodo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#updateCapacidadMapCDDetalle(java.util.Map)
	 */
	public void updateCapacidadMapCDDetalle(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateCapacidadMapCDDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#updateAnaquelOrigenMapaCDDetalle(java.util.Map)
	 */
	public void updateAnaquelOrigenMapaCDDetalle(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateAnaquelOrigenMapaCDDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getCodAnaquelOrigenListar(java.util.Map)
	 */
	public List getCodAnaquelOrigenListar(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodAnaqOrigenList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#updateAnaquelOrigenOpcionDividir(java.util.Map)
	 */
	public void updateAnaquelOrigenOpcionDividir(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateAnaquelOrigOpcionDividir", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getNumeroAnaquel(java.util.Map)
	 */
	public String getNumeroAnaquel(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNumeroAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEMantenerMapaCDDAO#getCodigoAnaquelDividirList(java.util.Map)
	 */
	public List getCodigoAnaquelDividirList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodigoAnaquelDividirList", criteria);
	}
	
}
