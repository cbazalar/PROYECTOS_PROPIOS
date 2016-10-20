package biz.belcorp.ssicc.service.spusicc.dto.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.dto.ProcesoDTOCalcularDescuentosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso de clculo de descuentos
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoDTOCalcularDescuentosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoDTOCalcularDescuentosServiceImpl extends
	BaseInterfazProcesoAbstractService {
	          
	@Resource(name = "spusicc.procesoDTOCalcularDescuentosDAO")
	private ProcesoDTOCalcularDescuentosDAO procesoDTOCalcularDescuentosDAO;


	protected void executeStoreProcedure(Map params) {
		procesoDTOCalcularDescuentosDAO.executeCalcularDescuentos(params);
	}
	

	
}
