package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPreProductosICEDAO;

/**
 * @author peextcroman
 */
@Repository("spusicc.pedidos.mantenimientoOCRPreProductosICEDAO")
public class MantenimientoOCRPreProductosICEDAOiBatis extends
		BaseDAOiBatis implements MantenimientoOCRPreProductosICEDAO {
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreProductosICEDAO#getListaProductosICE(java.util.Map)
	 */
	public List getListaProductosICE(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaProductosICE", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreAlternativosAutomaticosDAO#deleteProductosICE(java.util.Map)
	 */
	public void deleteProductosICE(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteProductosICE", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreProductosICEDAO#getProductoICEbyOid(java.util.Map)
	 */
	public List getProductoICEbyOid(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoICEbyOid", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreProductosICEDAO#updateProductoICE(java.util.Map)
	 */
	public void updateProductoICE(Map criteria){
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateProductoICE", criteria);	
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRPreProductosICEDAO#insertProductoICE(java.util.Map)
	 */
	public void insertProductoICE(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductoICE", criteria);
	}
}