package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEFactoresConversionDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.FactorConversion;

/**
 * 
 * <p>
 * <a href="MantenimientoAPEFactoresConversionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.mantenimientoAPEFactoresConversionDAO")
public class MantenimientoAPEFactoresConversionDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEFactoresConversionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getFactoresConversionList(java.util.Map)
	 */
	public List getFactoresConversionList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getFactoresConversionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getUnidadMedidaList(java.util.Map)
	 */
	public List getUnidadMedidaList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getUnidadMedidaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getMagnitudList(java.util.Map)
	 */
	public List getMagnitudList(Map criteria){
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMagnitudList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getObtenerOidMagnitud(java.util.Map)
	 */
	public String getObtenerOidMagnitud(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerOidMagnitud", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getObtenerOidUnidadMedida(java.util.Map)
	 */
	public String getObtenerOidUnidadMedida(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerOidUnidadMedida", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#insertarFactoresConversion(java.util.Map)
	 */
	public void insertarFactoresConversion(Map criteria){
		this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertFactoresConversion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#actualizarFactoresConversion(java.util.Map)
	 */
	public void actualizarFactoresConversion(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateFactoresConversion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#getFactorConversionObject(java.util.Map)
	 */
	public FactorConversion getFactorConversionObject(Map criteria){
		return (FactorConversion)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getFactorConversionObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#eliminarFactorConversion(java.util.Map)
	 */
	public void eliminarFactorConversion(Map criteria){
		this.getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteFactorConversion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEFactoresConversionDAO#validaRepeticionUndOrigenUndDestino(java.util.Map)
	 */
	public String validaRepeticionUndOrigenUndDestino(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaRepeticionUndOrigenUndDestino", criteria);
	}
	
}
