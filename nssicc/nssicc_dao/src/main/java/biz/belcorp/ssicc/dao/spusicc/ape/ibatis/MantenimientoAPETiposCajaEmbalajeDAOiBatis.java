package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETiposCajaEmbalajeDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TiposCajaEmbalaje;

/**
 * 
 * <p>
 * <a href="MantenimientoAPETiposCajaEmbalajeDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.mantenimientoAPETiposCajaEmbalajeDAO")
public class MantenimientoAPETiposCajaEmbalajeDAOiBatis extends	BaseDAOiBatis implements MantenimientoAPETiposCajaEmbalajeDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#getTipoCajaEmbalajeList(java.util.Map)
	 */
	public List getTipoCajaEmbalajeList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaEmbalajeList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#insertarTipoCajaEmbalaje(java.util.Map)
	 */
	public void insertarTipoCajaEmbalaje(Map criteria){
		this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoCajaEmbalaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#deleteTiposCajaEmbalaje(java.util.Map)
	 */
	public void deleteTiposCajaEmbalaje(Map criteria){
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoCajaEmbalaje", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#updateTipoCajaEmbalaje(java.util.Map)
	 */
	public void updateTipoCajaEmbalaje(Map criteria){
		this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.updateTipoCajaEmbalaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#getNextOidTipoCajaEmbalaje(java.util.Map)
	 */
	public String getNextOidTipoCajaEmbalaje(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidTipoCajaEmbalaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#getTiposCajaEmbalajeObject(java.util.Map)
	 */
	public TiposCajaEmbalaje getTiposCajaEmbalajeObject(Map criteria){
		return (TiposCajaEmbalaje)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getTipoCajaEmbalajeObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposCajaEmbalajeDAO#getObtenerCodigoTipoCaja(java.util.Map)
	 */
	public String getObtenerCodigoTipoCaja(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerCodigoTipoCaja", criteria);
	}

}
