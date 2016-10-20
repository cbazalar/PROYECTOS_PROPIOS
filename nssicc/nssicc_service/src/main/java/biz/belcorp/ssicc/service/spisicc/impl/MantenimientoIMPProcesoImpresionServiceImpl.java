/*
 * Created on 26/12/2005 11:39:41 AM
 * biz.belcorp.ssicc.scdf.service.impl.UltimasNoticiasServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.MantenimientoIMPProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ClasificacionVIP;
import biz.belcorp.ssicc.dao.spisicc.model.Etiqueta;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaClasificacion;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaEstatus;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.MantenimientoIMPProcesoImpresionService;


/**
 * @author ghuertasa
 *
 */
@Service("sto.mantenimientoIMPProcesoImpresionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoIMPProcesoImpresionServiceImpl extends BaseService implements MantenimientoIMPProcesoImpresionService {

	@Resource(name="imp.mantenimientoIMPProcesoImpresionDAO")
	MantenimientoIMPProcesoImpresionDAO mantenimientoIMPProcesoImpresionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getProcesosImpresionByCriteria(java.util.Map)
	 */
	public List getProcesosImpresionByCriteria(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getProcesosImpresionByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getProcesosImpresion(java.util.Map)
	 */
	public ProceImpresion getProcesosImpresion(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getProcesosImpresion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#insertProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProceImpresion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProcesoImpresion(ProceImpresion proceImpresion,
			Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.insertProcesoImpresion(proceImpresion,usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#updateProcesoImpresion(biz.belcorp.ssicc.spisicc.model.ProceImpresion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProcesoImpresion(ProceImpresion proceImpresion,
			Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.updateProcesoImpresion(proceImpresion,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#deleteEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta)
	 */
	public void deleteEtiqueta(Etiqueta etiqueta) {
		mantenimientoIMPProcesoImpresionDAO.deleteEtiqueta(etiqueta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiqueta(java.util.Map)
	 */
	public Etiqueta getEtiqueta(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiqueta(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetaByCriteria(java.util.Map)
	 */
	public List getEtiquetaByCriteria(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetaByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#insertEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiqueta(Etiqueta etiqueta, Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.insertEtiqueta(etiqueta, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#updateEtiqueta(biz.belcorp.ssicc.spisicc.model.Etiqueta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEtiqueta(Etiqueta etiqueta, Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.updateEtiqueta(etiqueta, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEstatusList(java.util.Map)
	 */
	public List getEstatusList(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEstatusList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetasList()
	 */
	public List getEtiquetasList() {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetasList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#deleteEtiquetaEstatus(biz.belcorp.ssicc.spisicc.model.EtiquetaEstatus)
	 */
	public void deleteEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus) {
		mantenimientoIMPProcesoImpresionDAO.deleteEtiquetaEstatus(etiquetaEstatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetaEstatusByCriteria(java.util.Map)
	 */
	public List getEtiquetaEstatusByCriteria(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetaEstatusByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#insertEtiquetaEstatus(biz.belcorp.ssicc.spisicc.model.EtiquetaEstatus, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus,
			Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.insertEtiquetaEstatus(etiquetaEstatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetaEstatus(java.util.Map)
	 */
	public EtiquetaEstatus getEtiquetaEstatus(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetaEstatus(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#deleteEtiquetaClasificacion(biz.belcorp.ssicc.spisicc.model.EtiquetaClasificacion)
	 */
	public void deleteEtiquetaClasificacion(
			EtiquetaClasificacion etiquetaClasificacion) {
		mantenimientoIMPProcesoImpresionDAO.deleteEtiquetaClasificacion(etiquetaClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getClasificacionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetaClasificacion(java.util.Map)
	 */
	public EtiquetaClasificacion getEtiquetaClasificacion(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetaClasificacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getEtiquetaClasificacionByCriteria(java.util.Map)
	 */
	public List getEtiquetaClasificacionByCriteria(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getEtiquetaClasificacionByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getTipoClasificacionList()
	 */
	public List getTipoClasificacionList(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getTipoClasificacionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#insertEtiquetaClasificacion(biz.belcorp.ssicc.spisicc.model.EtiquetaClasificacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtiquetaClasificacion(
			EtiquetaClasificacion etiquetaClasificacion, Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.insertEtiquetaClasificacion(etiquetaClasificacion, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getClasificacionVIPByCriteria(java.util.Map)
	 */
	public List getClasificacionVIPByCriteria(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getClasificacionVIPByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#deleteClasificacionVIP(biz.belcorp.ssicc.spisicc.model.ClasificacionVIP)
	 */
	public void deleteClasificacionVIP(ClasificacionVIP clasificacionVIP) {
		mantenimientoIMPProcesoImpresionDAO.deleteClasificacionVIP(clasificacionVIP);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#getClasificacionVIP(java.util.Map)
	 */
	public ClasificacionVIP getClasificacionVIP(Map params) {
		return mantenimientoIMPProcesoImpresionDAO.getClasificacionVIP(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.MantenimientoIMPProcesoImpresionService#insertClasificacionVIP(biz.belcorp.ssicc.spisicc.model.ClasificacionVIP, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClasificacionVIP(ClasificacionVIP clasificacionVIP,
			Usuario usuario) {
		mantenimientoIMPProcesoImpresionDAO.insertClasificacionVIP(clasificacionVIP, usuario);
	}
}
