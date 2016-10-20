package biz.belcorp.ssicc.dao.spncd.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPLogrosDAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPLogrosDAOiBatis.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */
@Repository("spncd.mantenimientoCUPLogrosDAO")
public class MantenimientoCUPLogrosDAOiBatis extends BaseDAOiBatis implements
		MantenimientoCUPLogrosDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getLogrosList(java.util.Map)
	 */
	public List getLogrosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getLogrosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#deleteLogros(java.util.Map)
	 */
	public void deleteLogros(Map criteria){
		getSqlMapClientTemplate().update("spncd.CuponesSQL.deleteLogros", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getTiposLogro()
	 */
	public List getTiposLogro(){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getTiposLogro", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getMediosCaptura()
	 */
	public List getMediosCaptura(){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getMediosCaptura", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getLogrosByIdList(java.util.Map)
	 */
	public List getLogrosByIdList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spncd.CuponesSQL.getLogrosByIdList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#insertLogros(java.util.Map)
	 */
	public void insertLogros(Map criteria){
		getSqlMapClientTemplate().insert("spncd.CuponesSQL.insertLogros", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#updateLogros(java.util.Map)
	 */
	public void updateLogros(Map criteria){
		getSqlMapClientTemplate().update("spncd.CuponesSQL.updateLogros", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getPeriodoIngresoConsultora(java.util.Map)
	 */
	public String getPeriodoIngresoConsultora(String codigoConsultora){
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodoIngresoConsultora", codigoConsultora).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getValidaMontoLogro(java.util.Map)
	 */
	public LabelValue getValidaMontoLogro(Map criteria){	
		Map map = (Map)getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getValidaMontoLogro", criteria);		
		return new LabelValue(map.get("rango").toString(),map.get("cantidad").toString());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getCantidadCruceRangoLogro(java.util.Map)
	 */
	public String getCantidadCruceRangoLogro(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getCantidadCruceRangoLogro", criteria).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getParametroNuevasLogro(java.util.Map)
	 */
	public String getParametroNuevasLogro(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getParametroNuevasLogro", criteria).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getIndicadorActivoConsultora(java.util.Map)
	 */
	public String getIndicadorActivoConsultora(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getIndicadorActivoConsultora", criteria).toString(); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getValidacionRegistroMeta(java.util.Map)
	 */
	public String getValidacionRegistroMeta(Map criteria) {
		getSqlMapClientTemplate().update("spncd.CuponesSQL.executeValidacionRegistroMeta", criteria);
		
		String resultado = MapUtils.getString(criteria, "codigoError"); 
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getPeriodoSiguiente(java.util.Map)
	 */
	public String getPeriodoSiguiente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoNSiguiente",criteria);
	}

         /* NSSiCC */
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.dao.MantenimientoCUPLogrosDAO#getPeriodoIngresoConsultora(java.util.Map)
	 */
	public String getPeriodoIngresoConsultora(Map criteria){
		return getSqlMapClientTemplate().queryForObject("spncd.CuponesSQL.getPeriodoIngresoConsultora", criteria).toString();
	}
}
