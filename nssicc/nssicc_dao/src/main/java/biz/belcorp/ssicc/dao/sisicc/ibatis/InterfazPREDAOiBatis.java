/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazPREDAO;

/**
 * 
 * <p>
 * <a href="InterfazPREDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Repository("sisicc.interfazPREDAO")
public class InterfazPREDAOiBatis extends BaseDAOiBatis implements
	InterfazPREDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarOfertaCabecera(java.util.Map)
	 */
	public void executeInterfazPREEnviarOfertaCabecera(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarOfertaCabecera",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarOfertaDetalle(java.util.Map)
	 */
	public void executeInterfazPREEnviarOfertaDetalle(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarOfertaDetalle",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarGrupoOferta(java.util.Map)
	 */
	public void executeInterfazPREEnviarGrupoOferta(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarGrupoOferta",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarPromocion(java.util.Map)
	 */
	public void executeInterfazPREEnviarPromocion(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarPromocion",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarRangoPromocion(java.util.Map)
	 */
	public void executeInterfazPREEnviarRangoPromocion(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarRangoPromocion",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREEnviarMatrizFacturacion(java.util.Map)
	 */
	public void executeInterfazPREEnviarMatrizFacturacion(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREEnviarMatrizFacturacion",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPRERecepcionarOfertaCabecera(java.util.Map)
	 */
	public void executeInterfazPRERecepcionarOfertaCabecera(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPRERecepcionarOfertaCabecera",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPRERecepcionarOfertaDetalle(java.util.Map)
	 */
	public void executeInterfazPRERecepcionarOfertaDetalle(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPRERecepcionarOfertaDetalle",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPRERecepcionarGrupoOferta(java.util.Map)
	 */
	public void executeInterfazPRERecepcionarGrupoOferta(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPRERecepcionarGrupoOferta",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPRERecepcionarPromocion(java.util.Map)
	 */
	public void executeInterfazPRERecepcionarPromocion(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPRERecepcionarPromocion",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPRERecepcionarRangoPromocion(java.util.Map)
	 */
	public void executeInterfazPRERecepcionarRangoPromocion(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPRERecepcionarRangoPromocion",map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#executeInterfazPREInsertarOfertasExportadas(java.util.Map)
	 */
	public void executeInterfazPREInsertarOfertasExportadas(Map map){
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREInsertarOfertasExportadas",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPREDAO#getDirectorioCUV(java.util.Map)
	 */
	public String getDirectorioCUV(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazPRESQL.getDirectorioCUV", params);
	}

	@Override
	public void insertOfertaGenerada(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertOfertaGenerada", params);
	}

	@Override
	public void insertCondicionesOferta(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertCondicionesOferta", params);
	}

	@Override
	public void insertOfertaNiveles(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertOfertaNiveles", params);
	}

	@Override
	public void insertRangoOfertaNiveles(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertRangoOfertaNiveles", params);
	}

	@Override
	public void insertProductoOfertaNiveles(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertProductoOfertaNiveles", params);
	}

	@Override
	public void insertGratisOfertaNiveles(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sisicc.InterfazPRESQL.insertGratisOfertaNiveles", params);
	}

	@Override
	public void executeValidacionMatrizPlanit(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeValidacionMatrizPlanit", params);
	}

	@Override
	public void envioCorreo(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPREMatrizPlanitEnvioCorreo",params);
	}

	@Override
	public Integer verificarErrorMatrizPlanit(Map params) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazPRESQL.verificarErrorMatrizPlanit", params);
	}
	
	@Override
	public void executeInterfazPrCargaMatrizPlan(Map params) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sisicc.InterfazPRESQL.executeInterfazPrCargaMatrizPlan",params);
	}
	
}