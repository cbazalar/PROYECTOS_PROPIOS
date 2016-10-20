package biz.belcorp.ssicc.service.spusicc.ocr.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ocr.MantenimientoOCRArchivoControlMultihiloDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ocr.MantenimientoOCRArchivoControlMultihiloService;


@Service("spusicc.mantenimientoOCRArchivoControlMultihiloService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRArchivoControlMultihiloServiceImpl extends BaseService 
			implements MantenimientoOCRArchivoControlMultihiloService{
	
	@Resource(name="spusicc.mantenimientoOCRArchivoControlMultihiloDAO")
	private MantenimientoOCRArchivoControlMultihiloDAO mantenimientoOCRArchivoControlMultihiloDAO;
	

	/**
	 * @param mantenimientoOCRArchivoControlMultihiloDAO the mantenimientoOCRArchivoControlMultihiloDAO to set
	 */
	public void setMantenimientoOCRArchivoControlMultihiloDAO(
			MantenimientoOCRArchivoControlMultihiloDAO mantenimientoOCRArchivoControlMultihiloDAO) {
		this.mantenimientoOCRArchivoControlMultihiloDAO = mantenimientoOCRArchivoControlMultihiloDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.service.MantenimientoOCRArchivoControlMultihiloService#getArchivoControlMultihilo()
	 */
	public List getArchivoControlMultihilo(Map criteria) {
		return mantenimientoOCRArchivoControlMultihiloDAO.getArchivoControlMultihilo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.service.MantenimientoOCRArchivoControlMultihiloService#getArchivoControlMultihiloGeneral()
	 */
	public List getArchivoControlMultihiloGeneral() {
		return mantenimientoOCRArchivoControlMultihiloDAO.getArchivoControlMultihiloGeneral();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.service.MantenimientoOCRArchivoControlMultihiloService#getArchivoControl(java.util.Map)
	 */
	public Map getArchivoControl(Map criteria) {
		return mantenimientoOCRArchivoControlMultihiloDAO.getArchivoControl(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.service.MantenimientoOCRArchivoControlMultihiloService#updateArchivoControl(java.util.Map)
	 */
	public void updateArchivoControl(Map criteria, Usuario usuario) {
		mantenimientoOCRArchivoControlMultihiloDAO.updateArchivoControl(criteria, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ocr.service.MantenimientoOCRArchivoControlMultihiloService#getBasHistoLotes(java.util.Map)
	 */
	public List getBasHistoLotes(Map criteria) {
		return mantenimientoOCRArchivoControlMultihiloDAO.getBasHistoLotes(criteria);
	}	

}
