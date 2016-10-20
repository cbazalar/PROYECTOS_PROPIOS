package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETEntregaPremioCampaniaCierreCampaniaService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETEntregaPremioCampaniaCierreCampaniaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.procesoLETEntregaPremioCampaniaCierreCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETEntregaPremioCampaniaCierreCampaniaServiceImpl extends BaseService implements ProcesoLETEntregaPremioCampaniaCierreCampaniaService {

	@Resource(name="spusicc.procesoLETEntregaPremioCampaniaCierreCampaniaDAO")
	private ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO procesoLETEntregaPremioCampaniaCierreCampaniaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService#executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETEntregaPremioCampaniaCierreCampania(Map params) {
		log.info("Entro a ProcesoLETEntregaPremioCampaniaCierreCampaniaServiceImpl - executeProcesoLETEntregaPremioCampaniaCierreCampania(java.util.Map)");
		procesoLETEntregaPremioCampaniaCierreCampaniaDAO.executeProcesoLETEntregaPremioCampaniaCierreCampania(params);
		log.info("Salio a ProcesoLETEntregaPremioCampaniaCierreCampaniaServiceImpl - executeProcesoLETEntregaPremioCampaniaCierreCampania(java.util.Map)");
	}

	
}
