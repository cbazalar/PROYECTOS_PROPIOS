package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.directory.InvalidAttributeValueException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDLideresDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDLideresService;

/**
 * Service que ejecutara que ejecutara los metodos del mantenimiento de Lideres
 *  
 * <p>
 * <a href="MantenimientoLIDLideresServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.mantenimientoLIDLideresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLIDLideresServiceImpl extends BaseService implements
		MantenimientoLIDLideresService {
	
	@Resource(name="spusicc.mantenimientoLIDLideresDAO")
	private MantenimientoLIDLideresDAO mantenimientoLIDLideresDAO; 
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#getSeccionesByCriteria(java.util.Map)
	 */
	public List getSeccionesByCriteria(Map criteria) throws Exception {
		//obtenemos la ultima campaa de cierre de region de la zona de la lider
		Base baseUltimoPeriodo = mantenimientoLIDLideresDAO.getUltimaCampanaCierreRegionxZona(criteria);
		log.debug("Ultima campana :" + baseUltimoPeriodo);
		
		if(baseUltimoPeriodo == null) { 
			//throw new Exception("No se encontro ultima campaa de cierre de region de la region seleccionada");

			criteria.put("oidUltimoPeriodoCR", "-1");
			criteria.put("oidPenultimoPeriodoCR", "-1");
			criteria.put("oidAntePenultimoPeriodoCR", "-1");

		} else {
			//obtememos el anterior periodo
			Map criteriaAux = new HashMap();
			criteriaAux.put("oidPeriodo", baseUltimoPeriodo.getCodigo());
			Base basePenultimoPeriodo = mantenimientoMAEClienteDAO.getPeriodoAnterior(criteriaAux);
	
			//obtememos el penultimo periodo
			criteriaAux.put("oidPeriodo", basePenultimoPeriodo.getCodigo());
			Base baseAntePenultimoPeriodo = mantenimientoMAEClienteDAO.getPeriodoAnterior(criteriaAux);
			
			criteria.put("oidUltimoPeriodoCR", baseUltimoPeriodo.getCodigo());
			criteria.put("oidPenultimoPeriodoCR", basePenultimoPeriodo.getCodigo());
			criteria.put("oidAntePenultimoPeriodoCR", baseAntePenultimoPeriodo.getCodigo());
		}
		
		return mantenimientoLIDLideresDAO.getSeccionesByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#validarAsignacionLiderSeccion(java.util.Map)
	 */
	public String validarAsignacionLiderSeccion(Map params) {
		return mantenimientoLIDLideresDAO.validarAsignacionLiderSeccion(params);
	}

	/**
	 * @param params
	 * @throws Exception 
	 */
	public void execAsignacionLiderSeccion(Map criteria) throws InvalidAttributeValueException {
		String codigoCliente = (String)criteria.get("codigoCliente");
		
		//Verifico si la consultora tiene clasificacion lider
		String indicadorClasificacionLider = mantenimientoLIDLideresDAO.getConsultoraClasificacionLider(criteria);
		//Si no tiene clasificacion lider, se inserta en la tabla MAE_CLIEN_CLASI
		String oidLider = criteria.get("oidCliente").toString();
		if (indicadorClasificacionLider.equals("0") && (!oidLider.equals(""))){
			try {
				mantenimientoLIDLideresDAO.insertClasificacionConsultoraLider(criteria);
			} catch (Exception e) {
				//Capturo la excepcion para identificarla y lanzar el mensaje adecuado
				throw new InvalidAttributeValueException();
			}
			
		}
		//obtenemos la ultima responsable de la seccion
		Map mapUltimoResponsable = mantenimientoLIDLideresDAO.getUltimoResponsableSeccion(criteria);
		String codigoResponsableAnterior = null;
		String oidPeriodoActual = (String)criteria.get("oidPeriodo");
		
		if(mapUltimoResponsable != null) {
			codigoResponsableAnterior = (String)mapUltimoResponsable.get("codigoResponsable");
			String oidHistoricoGerente = (String)mapUltimoResponsable.get("oidHistoricoGerente");
			String codigoPeriodoDesde = (String)mapUltimoResponsable.get("codigoPeriodoDesde");
			criteria.put("oidHistoricoGerente", oidHistoricoGerente);
			criteria.put("codigoPeriodoDesde", codigoPeriodoDesde);
			String codPeriodo = (String)criteria.get("codPeriodo");//ACTUAL
			log.debug("codigo periodo actual "+ codPeriodo + " cod periodo desdes "+codigoPeriodoDesde);
			if(!StringUtils.equals(codPeriodo, codigoPeriodoDesde)){
				//obtenemos oid de periodo de la campanha anterior
				Map map= new HashMap();			
				map.put("codigoPeriodo",codPeriodo);
				map.put("oidPais",criteria.get("oidPais"));
				map.put("numeroCampanna",new Integer(-1));
				
				String oidPeriodoAnterior =mantenimientoMAEClienteDAO.getDevuelveOidCampanha(map);
				criteria.put("oidPeriodo", oidPeriodoAnterior);
			}
			//actualizamos la fecha hasta del ultimo responsable de la seccion
			criteria.put("oidPeriodoActualHistoricoGerente", oidPeriodoActual);
			mantenimientoLIDLideresDAO.updateFechaHastaHistoricoGerente(criteria);
			
			//Verifica si el responsable anterior tiene la clasificacion de lider
			//Si existe, la borra de MAE_CLIEN_CLASI
			
			mantenimientoLIDLideresDAO.deleteClasificacionLiderConsultoraAnterior(criteria);
		}
		//volvemos asu estado inical el oidPeriodo x q es reuitlixaso en el desde
		criteria.put("oidPeriodo", oidPeriodoActual);
		if(codigoCliente.equals("")) { //realiza la desasignacion correctamente
			mantenimientoLIDLideresDAO.updateLiderSeccion(criteria);
			
		} else { //realiza la asignacion correctamente
			mantenimientoLIDLideresDAO.updateLiderSeccion(criteria);
			
			//inserta un registro en la entidad Historico Gerente para el nuevo responsable
			mantenimientoLIDLideresDAO.insertHistoricoGerente(criteria);
		
			//inserta un buzon de mensajes para el nuevo responsable
			criteria.put("oidModuloOrigen", Constants.ZON_CODIGO_MODULO);
			criteria.put("codigoMensaje", Constants.ZON_COD_MENSAJE_ASIGNACION_RESPONSABLE);
			criteria.put("tipoPeriodo", Constants.TIPO_PERIOODO_CORPORATIVO_CAMPANIA);
			criteria.put("codigoResponsableAnterior", codigoResponsableAnterior);
			
			mantenimientoLIDLideresDAO.insertBuzonMensajeResponsable(criteria);
		}
	}
	
	/**
	 * @param mantenimientoLIDLideresDAO the mantenimientoLIDLideresDAO to set
	 */
	public void setMantenimientoLIDLideresDAO(
			MantenimientoLIDLideresDAO mantenimientoLIDLideresDAO) {
		this.mantenimientoLIDLideresDAO = mantenimientoLIDLideresDAO;
	}

	/**
	 * @param mantenimientoMAEClienteDAO the mantenimientoMAEClienteDAO to set
	 */
	public void setMantenimientoMAEClienteDAO(
			MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO) {
		this.mantenimientoMAEClienteDAO = mantenimientoMAEClienteDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#getListCondicionesLideres()
	 */
	public List getListCondicionesLideres() {		
		return mantenimientoLIDLideresDAO.getListCondicionesLideres();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#getListCondicionDespachoCanasta(java.lang.String)
	 */
	public List getListCondicionDespachoCanasta(String codigoPeriodo) {
		return mantenimientoLIDLideresDAO.getListCondicionDespachoCanasta(codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#insertCondicionDespachoCanasta(java.util.Map)
	 */
	public void insertCondicionDespachoCanasta(Map map) {
		mantenimientoLIDLideresDAO.insertCondicionDespachoCanasta(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#updateCondicionDespachoCanasta(java.util.Map)
	 */
	public void updateCondicionDespachoCanasta(Map map) {
		mantenimientoLIDLideresDAO.updateCondicionDespachoCanasta(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#getListProductosCanasta(java.lang.String)
	 */
	public List getListProductosCanasta(String codigoPeriodo) {
		return mantenimientoLIDLideresDAO.getListProductosCanasta(codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#insertProductosCanasta(java.util.Map)
	 */
	public void insertProductosCanasta(Map map) {
		mantenimientoLIDLideresDAO.insertProductosCanasta(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#updateProductosCanasta(java.util.Map)
	 */
	public void updateProductosCanasta(Map map) {
		mantenimientoLIDLideresDAO.updateProductosCanasta(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#getListTipoOferta()
	 */
	public List getListTipoOferta() {
		return mantenimientoLIDLideresDAO.getListTipoOferta();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDLideresService#insertListProductosCanasta(java.util.Map)
	 */
	public void insertListProductosCanasta(Map map) {
		String codigoPais= (String)map.get("codigoPais");
		String usuario =(String)map.get("usuario");
		List list =(List)map.get("listProductosCanasta");
		
		Iterator it= list.iterator();
		while (it.hasNext()){
			Map mapProductoCanasta = (Map)it.next();
			mapProductoCanasta.put("codigoPais", codigoPais);
			mapProductoCanasta.put("usuario", usuario);
		    mantenimientoLIDLideresDAO.insertListProductosCanasta(mapProductoCanasta);
		}
		
	}
}
