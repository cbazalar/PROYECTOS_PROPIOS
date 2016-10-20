package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEntregaPremiosConcursoCierreCampanaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETEntregaPremiosConcursoCierreCampanaService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETEntregaPremiosConcursoCierreCampanaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETEntregaPremiosConcursoCierreCampanaServiceImpl extends BaseService implements ProcesoLETEntregaPremiosConcursoCierreCampanaService{
	
	@Resource(name="spusicc.procesoLETEntregaPremiosConcursoCierreCampanaDAO")
	private ProcesoLETEntregaPremiosConcursoCierreCampanaDAO procesoLETEntregaPremiosConcursoCierreCampanaDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETEntregaPremiosConcursoCierreCampanaService#executeProcesoLETEntregaPremiosConcursoCierreCampana(java.util.Map)
	 */
	public void executeProcesoLETEntregaPremiosConcursoCierreCampana(Map params) {
		procesoLETEntregaPremiosConcursoCierreCampanaDAO.executeProcesoLETEntregaPremiosConcursoCierreCampana(params);
	}
	
}