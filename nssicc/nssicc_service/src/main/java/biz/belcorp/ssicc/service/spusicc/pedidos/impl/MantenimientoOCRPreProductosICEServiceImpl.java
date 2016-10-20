package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPreProductosICEDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPreProductosICEService;

/**
 * @author peextcroman
 */
@Service("spusicc.pedidos.mantenimientoOCRPreProductosICEService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRPreProductosICEServiceImpl extends BaseService implements MantenimientoOCRPreProductosICEService {

	@Resource(name="spusicc.pedidos.mantenimientoOCRPreProductosICEDAO")
	MantenimientoOCRPreProductosICEDAO mantenimientoOCRPreProductosICEDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreProductosICEService#getListaProductosICE(java.util.Map)
	 */
	public List getListaProductosICE(Map criteria){
		return mantenimientoOCRPreProductosICEDAO.getListaProductosICE(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreProductosICEService#deleteProductosICE(java.util.Map)
	 */
	public void deleteProductosICE(Map criteria){
		mantenimientoOCRPreProductosICEDAO.deleteProductosICE(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreProductosICEService#getProductoICEbyOid(java.util.Map)
	 */
	public List getProductoICEbyOid(Map criteria){
		return mantenimientoOCRPreProductosICEDAO.getProductoICEbyOid(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreProductosICEService#updateProductoICE(java.util.Map)
	 */
	public void updateProductoICE(Map criteria){
		mantenimientoOCRPreProductosICEDAO.updateProductoICE(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRPreProductosICEService#insertProductoICE(java.util.Map)
	 */
	public void insertProductoICE(Map criteria){
		mantenimientoOCRPreProductosICEDAO.insertProductoICE(criteria);
	}
}
