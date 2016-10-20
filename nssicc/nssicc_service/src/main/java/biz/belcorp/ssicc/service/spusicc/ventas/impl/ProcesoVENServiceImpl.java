/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ventas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;
import biz.belcorp.ssicc.dao.spusicc.ventas.ProcesoVENDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;


/**
 * @author cbazalar
 *
 */
@Service("spusicc.procesoVENService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoVENServiceImpl extends BaseService implements ProcesoVENService {

	@Resource(name="spusicc.procesoVENDAO")
	private ProcesoVENDAO procesoVENDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.ProcesoVENService#executeGenerico(java.lang.String, java.util.Map)
	 */
	public void executeGenerico(String nombreExecute, Map criteria) {
		procesoVENDAO.executeGenerico(nombreExecute, criteria);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getCalendarios(map)
	 */
	public List getCalendarios(Map criteria) {
		return procesoVENDAO.getCalendarios(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getCalendario(map)
	 */
	public Calendario getCalendario(Map criteria) {
		return procesoVENDAO.getCalendario(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#updateCalendario(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCalendario(Calendario calendario, Usuario usuario) {
		procesoVENDAO.updateCalendario(calendario, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ProcesoVENServiceImpl#getFeriadoZona(map)
	 */
	public List getFeriadoZona(Map criteria) {
		return procesoVENDAO.getFeriadoZona(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#updateFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		procesoVENDAO.updateFeriadoZona(feriadoZona, usuario);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#insertFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		procesoVENDAO.insertFeriadoZona(feriadoZona, usuario);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoVENServiceImpl#deleteFeriadoZona(map,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteFeriadoZona(FeriadoZona feriadoZona, Usuario usuario) {
		procesoVENDAO.deleteFeriadoZona(feriadoZona, usuario);
	}
	

	public ProcesoVENDAO getProcesoVENDAO() {
		return procesoVENDAO;
	}


	/**
	 * @param procesoVENDAO
	 */
	public void setProcesoVENDAO(ProcesoVENDAO procesoVENDAO) {
		this.procesoVENDAO = procesoVENDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.ProcesoVENService#getZonasRegion(java.lang.String)
	 */
	public List getZonasRegion(String feriadoRegion){
		return procesoVENDAO.getZonasRegion(feriadoRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.ProcesoVENService#getIndicadorHabilitacionRuv(java.util.Map)
	 */
	public String getIndicadorHabilitacionRuv(Map criteria) {		
		return procesoVENDAO.getIndicadorHabilitacionRuv(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.ProcesoVENService#executeGeneracionReporteRUV(java.util.Map)
	 */
	public void executeGeneracionReporteRUV(Map map) {
		procesoVENDAO.executeGeneracionReporteRUV(map);
		
	}

	@Override
	public void executeGenerarArchivosLibroVentasDetalleSII(Map map) {
		procesoVENDAO.executeGenerarArchivosLibroVentasDetalleSII(map);	
		
	}
		
}
