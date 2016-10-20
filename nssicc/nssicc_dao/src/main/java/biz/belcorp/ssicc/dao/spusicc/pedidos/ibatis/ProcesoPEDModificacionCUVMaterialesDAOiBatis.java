package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDModificacionCUVMaterialesDAO;

/**
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 */
@Repository("spusicc.procesoPEDModificacionCUVMaterialesDAO")
public class ProcesoPEDModificacionCUVMaterialesDAOiBatis extends BaseDAOiBatis implements ProcesoPEDModificacionCUVMaterialesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDModificacionCUVMaterialesDAO#getDatosCUVByCodigoSAP(java.lang.String)
	 */
	public LabelValueCUV getDatosCUVByCodigoSAP(String codigoSAP) {
		return (LabelValueCUV)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getDatosCUVByCodigoSAP", codigoSAP);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDModificacionCUVMaterialesDAO#updateCodVentaByOidProducto(java.util.Map)
	 */
	public void updateCodVentaByOidProducto(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateCodVentaByOidProducto", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDModificacionCUVMaterialesDAO#saveAuditoria(java.util.Map)
	 */
	public void saveAuditoria(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.saveAuditoria", params);
	}
}