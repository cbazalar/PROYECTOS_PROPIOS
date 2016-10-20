/*
 * Created on 15/05/2009 11:26:04 AM
 * biz.belcorp.ssicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO
 */
package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosMatricialesDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoIMPGeneracionDocumentosMatricialesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("spisicc.procesoIMPGeneracionDocumentosMatricialesDAO")
public class ProcesoIMPGeneracionDocumentosMatricialesDAOiBatis extends BaseDAOiBatis implements
		ProcesoIMPGeneracionDocumentosMatricialesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaCuponesMatriciales(java.util.Map)
	 */
	public void executeProcesaCuponesMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaCuponesMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraCuponesMatriciales(java.util.Map)
	 */
	public void executeGeneraCuponesMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraCuponesMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#getCantidadCuponesMatriciales(java.util.Map)
	 */
	public int getCantidadCuponesMatriciales(Map params) {
		Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadCuponesMatriciales", params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaFacturasMatriciales(java.util.Map)
	 */
	public void executeProcesaFacturasMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaFacturasMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraFacturasMatriciales(java.util.Map)
	 */
	public void executeGeneraFacturasMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraFacturasMatriciales", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#getCantidadDocumentosMatriciales(java.util.Map)
	 */
	public int getCantidadDocumentosMatriciales(Map params) {
		Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadDocumentosMatriciales", params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraNotasCreditoMatriciales(java.util.Map)
	 */
	public void executeGeneraNotasCreditoMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraNotasCreditoMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraNotasDebitoMatriciales(java.util.Map)
	 */
	public void executeGeneraNotasDebitoMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraNotasDebitoMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaNotasCreditoMatriciales(java.util.Map)
	 */
	public void executeProcesaNotasCreditoMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaNotasCreditoMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaNotasDebitoMatriciales(java.util.Map)
	 */
	public void executeProcesaNotasDebitoMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaNotasDebitoMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeActualizaCorrelativoDocumentosMatriciales(java.util.Map)
	 */
	public void executeActualizaCorrelativoDocumentosMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeActualizaCorrelativoDocumentosMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraFacturasPremioMatriciales(java.util.Map)
	 */
	public void executeGeneraFacturasPremioMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraFacturasPremioMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaFacturasPremioMatriciales(java.util.Map)
	 */
	public void executeProcesaFacturasPremioMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaFacturasPremioMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeGeneraGuiasRemisionMatriciales(java.util.Map)
	 */
	public void executeGeneraGuiasRemisionMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraGuiasRemisionMatriciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO#executeProcesaGuiasRemisionMatriciales(java.util.Map)
	 */
	public void executeProcesaGuiasRemisionMatriciales(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaGuiasRemisionMatriciales", params);
	}

}
