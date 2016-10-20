package biz.belcorp.ssicc.service.spncd.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spncd.MantenimientoCUPLogrosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPLogrosService;

@Service("spncd.procesoIMPGeneraConsolidadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCUPLogrosServiceImpl extends BaseService implements MantenimientoCUPLogrosService {

	@Resource(name="spncd.mantenimientoCUPLogrosDAO")
	MantenimientoCUPLogrosDAO mantenimientoCUPLogrosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getLogrosList(java.util.Map)
	 */
	public List getLogrosList(Map criteria){
		return mantenimientoCUPLogrosDAO.getLogrosList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#deleteLogros(java.lang.String[])
	 */
	public void deleteLogros(String[] items, String usuario){
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoPais"     , StringUtils.split(id, "|")[0]);
			criteria.put("codigoCliente"  , StringUtils.split(id, "|")[1]);
			criteria.put("campanaInicio"  , StringUtils.split(id, "|")[2]);
			criteria.put("codigoTipoLogro", StringUtils.split(id, "|")[3]);
			criteria.put("codigoUsuario"  , usuario);
			mantenimientoCUPLogrosDAO.deleteLogros(criteria);
		}
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getTiposLogro()
	 */
	public List getTiposLogro(){
		return mantenimientoCUPLogrosDAO.getTiposLogro();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getMediosCaptura()
	 */
	public List getMediosCaptura(){
		return mantenimientoCUPLogrosDAO.getMediosCaptura();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getLogrosByIdList(java.util.Map)
	 */
	public List getLogrosByIdList(Map criteria){
		return mantenimientoCUPLogrosDAO.getLogrosByIdList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#insertLogros(java.util.Map)
	 */
	public void insertLogros(Map criteria){
		mantenimientoCUPLogrosDAO.insertLogros(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#updateLogros(java.util.Map)
	 */
	public void updateLogros(Map criteria){
		mantenimientoCUPLogrosDAO.updateLogros(criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getPeriodoIngresoConsultora(java.util.Map)
	 */
	public String getPeriodoIngresoConsultora(String codigoConsultora){
		return mantenimientoCUPLogrosDAO.getPeriodoIngresoConsultora(codigoConsultora);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getValidaMontoLogro(java.util.Map)
	 */
	public LabelValue getValidaMontoLogro(Map criteria){
		return mantenimientoCUPLogrosDAO.getValidaMontoLogro(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getParametroNuevasLogro(java.util.Map)
	 */
	public String getParametroNuevasLogro(Map criteria){
		return mantenimientoCUPLogrosDAO.getParametroNuevasLogro(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getIndicadorActivoConsultora(java.util.Map)
	 */
	public String getIndicadorActivoConsultora(Map criteria){
		return mantenimientoCUPLogrosDAO.getIndicadorActivoConsultora(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spncd.service.MantenimientoCUPLogrosService#getPeriodoSiguiente(java.util.Map)
	 */
	public String getPeriodoSiguiente(Map criteria) {
		return mantenimientoCUPLogrosDAO.getPeriodoSiguiente(criteria);
	}
}