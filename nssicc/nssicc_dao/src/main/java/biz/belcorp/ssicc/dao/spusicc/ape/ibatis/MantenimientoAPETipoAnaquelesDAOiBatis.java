package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETipoAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquel;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPETipoAnaquelesDAO")
public class MantenimientoAPETipoAnaquelesDAOiBatis extends BaseDAOiBatis implements MantenimientoAPETipoAnaquelesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getCodigoTipoAnaquelesList(java.util.Map)
	 */
	public List getCodigoTipoAnaquelesList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getCodigoTipoAnaquelesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getTipoAnaquelesList(java.util.Map)
	 */
	public List getTipoAnaquelesList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoAnaquelesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#deleteTipoAnaquel(java.util.Map)
	 */
	public void deleteTipoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.deleteTipoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getTipoAnaquelObject(java.util.Map)
	 */
	public TipoAnaquel getTipoAnaquelObject(Map criteria){
		return (TipoAnaquel)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getTipoAnaquelObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getTipoChanelList(java.util.Map)
	 */
	public List getTipoChanelList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoChanelList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getOidTipoChanel(java.util.Map)
	 */
	public int getOidTipoChanel(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoChanel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#updateTipoAnaquel(java.util.Map)
	 */
	public void updateTipoAnaquel(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateTipoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getNextOidTipoAnaquel(java.util.Map)
	 */
	public int getNextOidTipoAnaquel(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidTipoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getMaxCodTipoAnaquel(java.util.Map)
	 */
	public int getExisteCodTipoAnaquel(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteCodTipoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#insertTipoAnaquel(java.util.Map)
	 */
	public void insertTipoAnaquel(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoAnaquel",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#insertIdiomasComunAPE(java.util.Map)
	 */
	public void insertIdiomasComunAPE(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertIdiomasComunAPE",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#updateIdiomasComunAPE(java.util.Map)
	 */
	public void updateIdiomasComunAPE(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateIdiomasComunAPE", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#validaTipoAnaquelDefaultbyOid(java.util.Map)
	 */
	public int validaTipoAnaquelDefaultbyOid(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaTipoAnaquelDefaultbyOid", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#validaExisteTipoAnaquelDefaultbyTipo(java.util.Map)
	 */
	public int validaExisteTipoAnaquelDefaultbyTipo(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteTipoAnaquelDefaultbyTipo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#updateValorTipoDefault(java.util.Map)
	 */
	public void updateValorTipoDefault(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateValorTipoDefault", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoAnaquelesDAO#getOidTipoAnaquelbyTipoDefault(java.util.Map)
	 */
	public String getOidTipoAnaquelbyTipoDefault(Map criteria){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoAnaquelbyTipoDefault", criteria);
	}
}