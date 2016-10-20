package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.ProcesoSICGenerarPedidosDigitadosZonaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ProcesoSICGenerarPedidosDigitadosZonaService;
/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
@Service("scsicc.procesoSICGenerarPedidosDigitadosZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSICGenerarPedidosDigitadosZonaServiceImpl extends BaseService implements
											ProcesoSICGenerarPedidosDigitadosZonaService {
	
	@Resource(name="scsicc.procesoSICGenerarPedidosDigitadosZonaDAO")
	private ProcesoSICGenerarPedidosDigitadosZonaDAO reporteGenerarPedidosDigitadosZonaDAO;
	

	/**
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteRegion(Map criteria) {
		return reporteGenerarPedidosDigitadosZonaDAO.getCorreoGerenteRegion(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteZona(Map criteria) {
		return reporteGenerarPedidosDigitadosZonaDAO.getCorreoGerenteZona(criteria);
	}

}
