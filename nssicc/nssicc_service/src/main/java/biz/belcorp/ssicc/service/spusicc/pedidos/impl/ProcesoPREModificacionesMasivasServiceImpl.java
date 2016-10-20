package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREModificacionesMasivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREModificacionesMasivasService;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Service("spusicc.pedido.procesoPREModificacionesMasivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPREModificacionesMasivasServiceImpl extends BaseService implements ProcesoPREModificacionesMasivasService{

	@Resource(name="spusicc.procesoPREModificacionesMasivasDAO")
    private ProcesoPREModificacionesMasivasDAO procesoPREModificacionesMasivasDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREModificacionesMasivasService#deleteTablaModificacionesMasivasTemporal()
	 */
	public void deleteTablaModificacionesMasivasTemporal() {
		procesoPREModificacionesMasivasDAO.deleteTablaModificacionesMasivasTemporal();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREModificacionesMasivasService#insertModificacionesMasivasTemporal(java.util.List)
	 */
	public void insertModificacionesMasivasTemporal(List lineas) {
		for (int i = 0; i < lineas.size(); i++) {
			Map params = (Map)lineas.get(i);
			params.put("numeroFila", i + 1);
			procesoPREModificacionesMasivasDAO.insertModificacionesMasivasTemporal(params);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREModificacionesMasivasService#executeValidarCargaModificacionesMasivas(java.util.Map)
	 */
	public List executeValidarCargaModificacionesMasivas(Map params) {
		procesoPREModificacionesMasivasDAO.executeValidarCargaModificacionesMasivas(params);
        List resultados = procesoPREModificacionesMasivasDAO.getCargaModificacionesMasivasList(params);
        return resultados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREModificacionesMasivasService#executeActualizarCargaModificacionesMasivas(java.util.Map)
	 */
	public void executeActualizarCargaModificacionesMasivas(Map params) {
		procesoPREModificacionesMasivasDAO.executeActualizarCargaModificacionesMasivas(params);
	}
}