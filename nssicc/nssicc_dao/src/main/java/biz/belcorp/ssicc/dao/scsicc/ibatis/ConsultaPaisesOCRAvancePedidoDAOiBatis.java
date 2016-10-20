/**
 * 
 */
package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scsicc.ConsultaPaisesOCRAvancePedidoDAO;

/**
 * 
 * <p>
 * <a href="ConsultaPaisesOCRAvancePedidoDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Sergio Buchelli Silva</a>
 * 
 */
@Repository("scsicc.consultaPaisesOCRAvancePedidoDAO")
public class ConsultaPaisesOCRAvancePedidoDAOiBatis extends BaseDAOiBatis implements ConsultaPaisesOCRAvancePedidoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getCampanhaActivaByZona(java.util.Map)
	 */
	public String getCampanhaActivaByZona(Map criteria) {
//		this.getSqlMapClientTemplate().update(
//	 			"scsicc.GenericoPaisesSQL.lock",
//	 			criteria);
		String resultado = (String)getSqlMapClientTemplate().queryForObject(
				"scsicc.GenericoPaisesSQL.getCampanhaActivaByZona", criteria);
		
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getCodigoVentasRechazados(java.util.Map)
	 */
	public List getCodigoVentasRechazados(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"scsicc.GenericoPaisesSQL.getCodigoVentasRechazados",  criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getConsultorasActivasSinPedido(java.util.Map)
	 */
	public List getConsultorasActivasSinPedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"scsicc.GenericoPaisesSQL.getConsultorasActivasSinPedido",  criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getDetallePedidoFacturado(java.util.Map)
	 */
	public List getDetallePedidoFacturado(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"scsicc.GenericoPaisesSQL.getDetallePedidoFacturado",  criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getFaltantesAnunciados(java.util.Map)
	 */
	public List getFaltantesAnunciados(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"scsicc.GenericoPaisesSQL.getFaltantesAnunciados",  criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getFechaUltimaActualizacionZona(java.util.Map)
	 */
	public String getFechaUltimaActualizacionZona(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"scsicc.GenericoPaisesSQL.getFechaUltimaActualizacionZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getInformeAvancePedido(java.util.Map)
	 */
	public List getInformeAvancePedido(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"scsicc.GenericoPaisesSQL.getInformeAvancePedido",  criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#getNumeroRegistros(java.util.Map)
	 */
	public Integer getNumeroRegistros(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"scsicc.GenericoPaisesSQL.getNumeroRegistros", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ConsultaPaisesOCRAvancePedidoDAO#deleteEntidad(java.util.Map)
	 */
	public void deleteEntidad(Map criteria) {		
		this.getSqlMapClientTemplate().update(
	 			"scsicc.GenericoPaisesSQL.unlock",
	 			criteria);
		getSqlMapClientTemplate().update("scsicc.GenericoPaisesSQL.deleteEntidad",  criteria);
		
	}

}