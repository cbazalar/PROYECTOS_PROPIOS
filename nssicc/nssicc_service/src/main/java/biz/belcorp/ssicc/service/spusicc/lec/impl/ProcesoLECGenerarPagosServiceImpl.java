package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarPagosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService;

@Service("spusicc.procesoLECGenerarPagosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECGenerarPagosServiceImpl  extends BaseService implements ProcesoLECGenerarPagosService{
	
	@Resource(name="spusicc.procesoLECGenerarPagosDAO")
	private ProcesoLECGenerarPagosDAO procesoLECGenerarPagosDAO; 

	
	public List getTipoPago(Map map){
	 return procesoLECGenerarPagosDAO.getTipoPago(map);
	}
	public void executeGenerarPagoRegular(Map params){
		procesoLECGenerarPagosDAO.insertGloblalTemporaryForGrupoUA(params);
		int cont = procesoLECGenerarPagosDAO.getContadorGloblalTemporaryForGrupoUA();
		log.debug("contadorGloblalTemporaryForGrupoUA: "+ cont);
		procesoLECGenerarPagosDAO.executeGenerarPagoRegular(params);
	}
	public void executeGenerarPagoAdicional(Map params){
		procesoLECGenerarPagosDAO.executeGenerarPagoAdicional(params);
	}	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService#getTipoPago02(java.util.Map)
	 */
	public List getTipoPago02(Map params) {
		return procesoLECGenerarPagosDAO.getTipoPago02(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService#getTipoMotivoBloqueo(java.util.Map)
	 */
	public List getTipoMotivoBloqueo(Map params) {
		return procesoLECGenerarPagosDAO.getTipoMotivoBloqueo(params);
	}
}
