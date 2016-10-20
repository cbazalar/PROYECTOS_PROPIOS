package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRActualizacionGruposProcesoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRActualizacionGruposProcesoService;

@Service("spusicc.pedidos.procesoOCRActualizacionGruposProcesoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoOCRActualizacionGruposProcesoServiceImpl extends BaseService implements ProcesoOCRActualizacionGruposProcesoService{
	
	@Resource(name="spusicc.procesoOCRActualizacionGruposProcesoDAO")
	private ProcesoOCRActualizacionGruposProcesoDAO actualizacionGruposProcesoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRActualizacionGruposProcesoService#getTiposSolicitud()
	 */
	public List getTiposSolicitud() {
		return actualizacionGruposProcesoDAO.getTiposSolicitud();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRActualizacionGruposProcesoService#updateGrupoProceso(java.util.Map)
	 */
	public void updateGrupoProceso(Map parametros) {
		actualizacionGruposProcesoDAO.updateGrupoProceso(parametros);
	}
}