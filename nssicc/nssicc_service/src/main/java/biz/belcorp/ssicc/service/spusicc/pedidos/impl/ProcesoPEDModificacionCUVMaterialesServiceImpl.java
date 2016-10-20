package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDModificacionCUVMaterialesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDModificacionCUVMaterialesService;

/**
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 */
@Service("spusicc.procesoPEDModificacionCUVMaterialesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDModificacionCUVMaterialesServiceImpl extends BaseService implements ProcesoPEDModificacionCUVMaterialesService{
	
	@Resource(name="spusicc.procesoPEDModificacionCUVMaterialesDAO")
	private ProcesoPEDModificacionCUVMaterialesDAO procesoPEDModificacionCUVMaterialesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDModificacionCUVMaterialesService#getDatosCUVByCodigoSAP(java.lang.String)
	 */
	public LabelValueCUV getDatosCUVByCodigoSAP(String codigoSAP) {
		return procesoPEDModificacionCUVMaterialesDAO.getDatosCUVByCodigoSAP(codigoSAP);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDModificacionCUVMaterialesService#updateCodVentaByOidProducto(java.util.Map)
	 */
	public void updateCodVentaByOidProducto(Map params) {
		procesoPEDModificacionCUVMaterialesDAO.updateCodVentaByOidProducto(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDModificacionCUVMaterialesService#saveAuditoria(java.util.Map)
	 */
	public void saveAuditoria(Map params) {
		procesoPEDModificacionCUVMaterialesDAO.saveAuditoria(params);
	}
}