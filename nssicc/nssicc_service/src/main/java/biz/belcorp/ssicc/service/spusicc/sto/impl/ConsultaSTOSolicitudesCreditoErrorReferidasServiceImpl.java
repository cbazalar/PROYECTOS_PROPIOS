package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.ConsultaSTOSolicitudesCreditoErrorReferidasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.ConsultaSTOSolicitudesCreditoErrorReferidasService;

/**
 * 
 * @author Dennys Oliva Iriarte
 *
 */

@Service("spusicc.consultaSTOSolicitudesCreditoErrorReferidasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaSTOSolicitudesCreditoErrorReferidasServiceImpl extends BaseService implements ConsultaSTOSolicitudesCreditoErrorReferidasService{
	
	@Resource(name ="spusicc.consultaSTOSolicitudesCreditoErrorReferidasDAO")
	ConsultaSTOSolicitudesCreditoErrorReferidasDAO consultaSTOSolicitudesCreditoErrorReferidasDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOExcepcionValidaDeudaService#getExcepcionValidaDeudaList(java.util.Map)
	 */
	public List getSolicitudesCreditoErrorReferidosList(Map criteria) {
		return consultaSTOSolicitudesCreditoErrorReferidasDAO.getSolicitudesCreditoErrorReferidosList(criteria);
	}

}
