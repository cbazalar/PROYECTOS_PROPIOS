package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacionAmbito;
import biz.belcorp.ssicc.dao.edu.model.DespachoProductoCurso;
import biz.belcorp.ssicc.dao.edu.model.EquivalenciaMatrizServicio;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPedido;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.ZonaCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author peextrvela
 *
 */
@Service("edu.mantenimientoEDUCursoCapacitacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoEDUCursoCapacitacionServiceImpl extends 
	BaseService implements MantenimientoEDUCursoCapacitacionService{

	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
	
	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;//anhadido
		
	@Resource(name="edu.procesoEDUComercialService")
	ProcesoEDUComercialService	procesoEDUComercialService;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion)
	 */
	public CursoCapacitacion getCursoCapacitacion(CursoCapacitacion cursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getCursoCapacitacion(cursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getCursosCapacitacionByCriteria(java.util.Map)
	 */
	public List getCursosCapacitacionByCriteria(Map criterios) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getCursosCapacitacionByCriteria(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getProductosxCurso(biz.belcorp.ssicc.edu.dao.model.DespachoProducto)
	 */
	public List getProductosxCurso(DespachoProductoCurso despachoProducto) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getProductosxCurso(despachoProducto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getProductoxCurso(biz.belcorp.ssicc.edu.dao.model.DespachoProducto)
	 */
	public DespachoProductoCurso getProductoxCurso(DespachoProductoCurso despachoProducto) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getProductoxCurso(despachoProducto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) throws Exception {
		mantenimientoEDUCursoCapacitacionDAO.insertCursoCapacitacion(cursoCapacitacion,usuario);
		Map params = BeanUtils.describe(cursoCapacitacion);
		/***
		 * Se Modifico para que use 
		 * el parametro de conexion externa 
		 * @author sbuchelli
		 * */
		
	 if (verificaConexionExternaSistemaComercial(cursoCapacitacion.getCodigoPais(), cursoCapacitacion.getCodigoEmpresa())) {	
		Integer ex=procesoEDUComercialService.getExisteCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
		if(ex.intValue()==1)//existe
			procesoEDUComercialService.executeUpdateParametrosCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
		else//0:no existe
			procesoEDUComercialService.executeInsertParametrosCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
	 }	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.insertDespachoProducto(despachoProducto,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.removeDespachoProducto(despachoProducto,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeLogicoDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.removeLogicoDespachoProducto(despachoProducto,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) throws Exception  {
		mantenimientoEDUCursoCapacitacionDAO.updateCursoCapacitacion(cursoCapacitacion,usuario);
		Map params=BeanUtils.describe(cursoCapacitacion);
		
		/***
		 * Se Modifico para que use 
		 * el parametro de conexion externa 
		 * @author sbuchelli
		 * */
		
 	  if (verificaConexionExternaSistemaComercial(cursoCapacitacion.getCodigoPais(), cursoCapacitacion.getCodigoEmpresa())) {	
		Integer ex=procesoEDUComercialService.getExisteCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
		if(ex.intValue()==1)//existe
			procesoEDUComercialService.executeUpdateParametrosCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
		else//0:no existe
			procesoEDUComercialService.executeInsertParametrosCursoCapacitacion(cursoCapacitacion.getCodigoPais(),params);
	  }	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateDespachoProducto(biz.belcorp.ssicc.edu.dao.model.DespachoProducto, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDespachoProducto(DespachoProductoCurso despachoProducto, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.updateDespachoProducto(despachoProducto,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateRemoveCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRemoveCursoCapacitacion(CursoCapacitacion cursoCapacitacion, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.updateRemoveCursoCapacitacion(cursoCapacitacion,usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getMaxCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.DespachoProductoCurso)
	 */
	public String getMaxCursoCapacitacion(CursoCapacitacion cursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getMaxCursoCapacitacion(cursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getRegion(biz.belcorp.ssicc.edu.dao.model.Region)
	 */
	public List getRegion(RegionCursoCapacitacion region) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getZona(biz.belcorp.ssicc.edu.dao.model.Zona)
	 */
	public List getZonaByRegionSelected(Map map) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getZonaByRegionSelected(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito)
	 */
	public List getCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getCursoCapacitacionAmbito(cursoCapacitacionAmbito);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.insertCursoCapacitacionAmbito(cursoCapacitacionAmbito,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeCursoCapacitacionAmbito(biz.belcorp.ssicc.edu.dao.model.CursoCapacitacionAmbito)
	 */
	public void removeCursoCapacitacionAmbito(CursoCapacitacionAmbito cursoCapacitacionAmbito) {
		mantenimientoEDUCursoCapacitacionDAO.removeCursoCapacitacionAmbito(cursoCapacitacionAmbito);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getRegionCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion)
	 */
	public List getRegionCursoCapacitacion(RegionCursoCapacitacion region) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getRegionCursoCapacitacion(region);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getZonaCursoCapacitacion(biz.belcorp.ssicc.edu.dao.model.ZonaCursoCapacitacion)
	 */
	public List getZonaCursoCapacitacion(ZonaCursoCapacitacion zonaCursoCapacitacion) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getZonaCursoCapacitacion(zonaCursoCapacitacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getZona(biz.belcorp.ssicc.edu.dao.model.ZonaCursoCapacitacion)
	 */
	public List getZona(ZonaCursoCapacitacion zona) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getZona(zona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateRegion(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRegion(RegionCursoCapacitacion region, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoEDUCursoCapacitacionDAO.updateRegion(region,usuario);
		//Actualizamos en el Historico de Planillas por Instructora
		HistoricoPlanillaInstructora historicoPlanillaInstructora =  new HistoricoPlanillaInstructora();
		historicoPlanillaInstructora.setCodigoPais(region.getCodigoPais());
		historicoPlanillaInstructora.setCodigoEmpresa(region.getCodigoEmpresa());
		historicoPlanillaInstructora.setCodigoRegion(region.getCodigoRegion());
		historicoPlanillaInstructora.setCodigoInstructora(region.getCodigoInstructora());
		mantenimientoEDUCursoCapacitacionDAO.updatePlanillaInstructoraByRegion(historicoPlanillaInstructora,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoPedido(biz.belcorp.ssicc.edu.dao.model.HistoricoPedido)
	 */
	public List getHistoricoPedido(HistoricoPedido historicoPedido) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getHistoricoPedido(historicoPedido);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getEquivalenciaMatrizServiciosByCriteria(java.util.Map)
	 */
	public List getEquivalenciaMatrizServiciosByCriteria(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getEquivalenciaMatrizServiciosByCriteria(criteria);
	}

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getEquivalenciaMatrizServiciosById(java.util.Map)
	 */
	public EquivalenciaMatrizServicio getEquivalenciaMatrizServiciosById(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getEquivalenciaMatrizServiciosById(criteria);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertEquivalenciaMatrizServicio(biz.belcorp.ssicc.edu.dao.model.EquivalenciaMatrizServicio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEquivalenciaMatrizServicio(EquivalenciaMatrizServicio equivalenciaMatrizServicio, Usuario usuario) {
		mantenimientoEDUCursoCapacitacionDAO.insertEquivalenciaMatrizServicio(equivalenciaMatrizServicio,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getValidaRangoCodigoServicio(java.util.Map)
	 */
	public Integer getValidaRangoCodigoServicio(Map criterios) {
		return mantenimientoEDUCursoCapacitacionDAO.getValidaRangoCodigoServicio(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getRegionByInstructora(biz.belcorp.ssicc.edu.dao.model.RegionCursoCapacitacion)
	 */
	public List getRegionByInstructora(RegionCursoCapacitacion region) {
		// TODO Auto-generated method stub
		return mantenimientoEDUCursoCapacitacionDAO.getRegionByInstructora(region);
	}
	
	private boolean verificaConexionExternaSistemaComercial(String codigoPais, String codigoEmpresa) {
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(codigoEmpresa);
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro = this.getParametroCurso(parametro);
		
		if (parametro != null) {
			String conexionExterna = parametro.getIndicadorConexionExterna();
			if (StringUtils.isNotBlank(conexionExterna) && (Constants.EDU_CONEXION_EXTERNA_ACTIVADO.equals(conexionExterna))) {
				return true;
			}
		}
		return false;
	}
	
	private ParametroCursoCapacitacion getParametroCurso(ParametroCursoCapacitacion parametroCursoCapacitacion) {
		return mantenimientoEDUGenericoDAO.getParametroCurso(parametroCursoCapacitacion);
	}
}
