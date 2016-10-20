package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sgr.ProcesoSGRGenericoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENCancelarPolizasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCancelarPolizasServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.procesoSGRGenericoDAO")
	ProcesoSGRGenericoDAO procesoSGRGenericoDAO;
	
	
	protected void executeStoreProcedure(Map params){
		procesoSGRGenericoDAO.executeCancelarPolizas(params);
	}

}