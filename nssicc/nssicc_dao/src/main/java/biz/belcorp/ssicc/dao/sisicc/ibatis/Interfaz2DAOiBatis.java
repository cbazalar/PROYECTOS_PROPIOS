/*
 * Created on 20/09/2006 09:28:01 PM
 * biz.belcorp.ssicc.sisicc.dao.ibatis.Interfaz2DAOiBatis
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ComponenteInterfazPaquete;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Interfaz2DAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("sisicc.interfazDAO")
public class Interfaz2DAOiBatis extends BaseDAOiBatis implements InterfazDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getInterfaces(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfaces(Interfaz interfaz) {
		List interfaces = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfaces", interfaz);
		return interfaces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getInterfacesByCriteria(java.util.Map)
	 */
	public List getInterfacesByCriteria(Map criteria) {
		List interfaces = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfacesByCriteria",
				criteria);
		return interfaces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getInterfaz(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Interfaz getInterfaz(InterfazPK primaryKey) {
		Interfaz interfaz = (Interfaz) getSqlMapClientTemplate()
				.queryForObject("sisicc.ConfiguracionInterfaz2SQL.getInterfaz",
						primaryKey);
		/*if (interfaz == null) {
			throw new ObjectRetrievalFailureException(Interfaz.class,
					primaryKey);
		}*/

		// obtenemos los parametros de la interfaz.
		if (interfaz != null) {
			List parametros = getSqlMapClientTemplate().queryForList(
					"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
					primaryKey);
			interfaz.setParametros(parametros);
		}	
		return interfaz;
	}
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#insertInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertInterfaz(Interfaz interfaz, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"sisicc.ConfiguracionInterfaz2SQL.insertInterfaz", interfaz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#updateInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateInterfaz(Interfaz interfaz, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"sisicc.ConfiguracionInterfaz2SQL.updateInterfaz", interfaz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#removeInterfaz(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public void removeInterfaz(InterfazPK primaryKey) {
		getSqlMapClientTemplate().update(
				"sisicc.ConfiguracionInterfaz2SQL.removeInterfaz", primaryKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesInterfazPaquete(InterfazPK primaryKey) {
		log.debug("Entering 'getComponentesInterfazPaquete' method");
		List componentes = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ConfiguracionInterfaz2SQL.getComponentesInterfazPaquete",
						primaryKey);

		for (Iterator iter = componentes.iterator(); iter.hasNext();) {
			Interfaz interfaz = (Interfaz) iter.next();
			List parametros = getSqlMapClientTemplate().queryForList(
					"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
					interfaz.getInterfazPK());
			log.debug(interfaz.getCodigo() + ": params=" + parametros);
			interfaz.setParametros(parametros);
		}
		return componentes;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
		log.debug("Entering 'getComponentesInterfazPaqueteSeleccionadas' method");
		List componentes = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ConfiguracionInterfaz2SQL.getComponentesInterfazPaqueteSeleccionadas",
						primaryKey);

		for (Iterator iter = componentes.iterator(); iter.hasNext();) {
			Interfaz interfaz = (Interfaz) iter.next();
			List parametros = getSqlMapClientTemplate().queryForList(
					"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
					interfaz.getInterfazPK());
			log.debug(interfaz.getCodigo() + ": params=" + parametros);
			interfaz.setParametros(parametros);
		}
		return componentes;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroHilosInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroHilosInterfazPaquete(InterfazPK primaryKey) {
		log.debug("Entering 'getNroHilosInterfazPaquete' method");
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getNroHilosInterfazPaquete", primaryKey);
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroHilosInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroHilosInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
		log.debug("Entering 'getNroHilosInterfazPaqueteSeleccionadas' method");
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getNroHilosInterfazPaqueteSeleccionadas", primaryKey);
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#insertComponenteInterfazPaquete(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComponenteInterfazPaquete(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
		getSqlMapClientTemplate()
				.update(
						"sisicc.ConfiguracionInterfaz2SQL.insertComponenteInterfazPaquete",
						componenteInterfazPaquete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#removeComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public void removeComponentesInterfazPaquete(InterfazPK primaryKey) {
		getSqlMapClientTemplate()
				.update(
						"sisicc.ConfiguracionInterfaz2SQL.removeComponentesInterfazPaquete",
						primaryKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getFechaUltimoProceso(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Timestamp getFechaUltimoProceso(InterfazPK primaryKey) {
		Timestamp fecha = (Timestamp) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getFechaUltimoProceso",
				primaryKey);
		return fecha;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getNumeroLote(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public String getNumeroLote(InterfazPK primaryKey) {
		Map criteria = new HashMap();
		criteria.put("codigoPais",primaryKey.getCodigoPais());
		criteria.put("codigoSistema",primaryKey.getCodigoSistema());
		criteria.put("codigo",primaryKey.getCodigo());
		criteria.put("numeroLote"," ");
		getSqlMapClientTemplate().update(
				"sisicc.ConfiguracionInterfaz2SQL.getNumeroLote", criteria);
		String numeroLote = (String)criteria.get("numeroLote");
		/*String numeroLote = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getNumeroLote", primaryKey);*/
		log.debug("Cambiando a procedimiento Numero Lote: " + numeroLote);
		return numeroLote;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getNuevosCodigos()
	 */
	public List getNuevosCodigos() {
		List codigos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getNuevosCodigos", null);
		return codigos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#updateNumeroEjecucionInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNumeroEjecucionInterfaz(Interfaz interfaz, Usuario usuario) {
		getSqlMapClientTemplate()
				.update(
						"sisicc.ConfiguracionInterfaz2SQL.updateNumeroEjecucionInterfaz",
						interfaz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getInterfacesBySistema(java.util.Map)
	 */
	public List getInterfacesBySistema(Map criteria) {
		List interfaces = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfacesBySistema",
				criteria);
		return interfaces;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getProcesoBatchBySistema(java.util.Map)
	 */
	public List getProcesoBatchBySistema(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchBySistema",
				criteria);
		return procesos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getProcesoBatchActuByCriteria(java.util.Map)
	 */
	public List getProcesoBatchActuByCriteria(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchActuByCriteria",
				criteria);
		return procesos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getProcesoBatchHistoByCriteria(java.util.Map)
	 */
	public List getProcesoBatchHistoByCriteria(Map criteria) {
		List procesos = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getProcesoBatchHistoByCriteria",
				criteria);
		return procesos;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfacesUnitariasAsignadas(java.util.Map)
	 */
	public List getInterfacesUnitariasAsignadas(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfacesUnitariasNoAsignadas(java.util.Map)
	 */
	public List getInterfacesUnitariasNoAsignadas(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.Interfaz2DAO#getInterfacesEmpaquetadas(biz.belcorp.ssicc.sisicc.model.Interfaz,
	 *      boolean)
	 */
	public List getInterfacesEmpaquetadas(Interfaz interfaz, boolean valor) {
		List interfaces = null;
		if (valor)
			interfaces = getSqlMapClientTemplate()
					.queryForList(
							"sisicc.ConfiguracionInterfaz2SQL.getInterfacesEmpaquetadas",
							interfaz);
		else
			interfaces = getSqlMapClientTemplate()
					.queryForList(
							"sisicc.ConfiguracionInterfaz2SQL.getInterfacesNoEmpaquetadas",
							interfaz);
		return interfaces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getExtensionArchivo(java.util.Map)
	 */
	public List getExtensionArchivo(Map criteria) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#updateNumLoteGenSolicitudMonetaria(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNumLoteGenSolicitudMonetaria(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate()
		.update(
				"sisicc.ConfiguracionInterfaz2SQL.updateNumLoteGenSolicitudMonetaria",	criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#insertSATEstimadoProductos()
	 */
	public void insertSATEstimadoProductos(EstimadoProductos cabecera, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertEstimadoProductos",
				cabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroNivelesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaquete(InterfazPK interfazEjecucionPK) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getNroNivelesInterfazPaquete", interfazEjecucionPK);
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroNivelesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaqueteSeleccionadas(
			InterfazPK interfazEjecucionPK) {
		Integer valor = (Integer) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getNroNivelesInterfazPaqueteSeleccionadas", interfazEjecucionPK);
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesCompuestaInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaquete(InterfazPK interfazPK) {
		log.debug("Entering 'getComponentesCompuestaInterfazPaquete' method");
		List result = new ArrayList();
		List componentes = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ConfiguracionInterfaz2SQL.getComponentesInterfazPaquete",
						interfazPK);

		for (Iterator iter = componentes.iterator(); iter.hasNext();) {
			Interfaz interfaz = (Interfaz) iter.next();
			if(Constants.TIPO_GENERACION_UNITARIA.equals(interfaz.getTipoGeneracion())){
				result.add(interfaz);
				List parametros = getSqlMapClientTemplate().queryForList(
						"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
						interfaz.getInterfazPK());
				log.debug(interfaz.getCodigo() + ": params=" + parametros);
				interfaz.setParametros(parametros);
			}
			
			if(Constants.TIPO_GENERACION_PAQUETE.equals(interfaz.getTipoGeneracion())){			
				List listPaquete = getComponentesInterfazPaquete(interfaz.getInterfazPK());
				result.addAll(listPaquete);
			}
			
		}
		log.debug("Finished 'getComponentesCompuestaInterfazPaquete' method");
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesCompuestaInterfazPaqueteSeleccionada(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaqueteSeleccionada(
			InterfazPK interfazPK) {
		log.debug("Entering 'getComponentesCompuestaInterfazPaquete' method");
		List result = new ArrayList();
		List componentes = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ConfiguracionInterfaz2SQL.getComponentesInterfazPaqueteSeleccionadas",
						interfazPK);

		for (Iterator iter = componentes.iterator(); iter.hasNext();) {
			Interfaz interfaz = (Interfaz) iter.next();
			if(Constants.TIPO_GENERACION_UNITARIA.equals(interfaz.getTipoGeneracion())){
				result.add(interfaz);
				List parametros = getSqlMapClientTemplate().queryForList(
						"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
						interfaz.getInterfazPK());
				log.debug(interfaz.getCodigo() + ": params=" + parametros);
				interfaz.setParametros(parametros);
			}
			
			if(Constants.TIPO_GENERACION_PAQUETE.equals(interfaz.getTipoGeneracion())){			
				List listPaquete = getComponentesInterfazPaquete(interfaz.getInterfazPK());
				result.addAll(listPaquete);
			}
			
		}
		log.debug("Finished 'getComponentesCompuestaInterfazPaquete' method");
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getOidArchivoControl(java.util.Map)
	 */
	public Long getOidArchivoControl(Map criteria) {
		Long valor = (Long) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getOidArchivoControl", criteria);
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#updateEstadoArchivoControl(java.util.Map)
	 */
	public void updateEstadoArchivoControl(Map params) {
		getSqlMapClientTemplate()
		.update(
				"sisicc.InterfazSQL.updateEstadoArchivoControl",	params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getPaisByCia(java.util.Map)
	 */
	public String getPaisByCia(Map criteria) {
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getPaisByCia", criteria);
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getCodigoProceoBatch(java.util.Map)
	 */
	public String getCodigoProcesoBatch(Map criteria) {
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getCodigoProcesoBatch", criteria);
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#updateEstadoCargadasArchivoControl(java.util.Map)
	 */
	public void updateEstadoCargadasArchivoControl(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSQL.updateEstadoCargadasArchivoControl", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#insertComponenteInterfazPaqueteModificado(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComponenteInterfazPaqueteModificado(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
		getSqlMapClientTemplate()
		.update(
				"sisicc.ConfiguracionInterfaz2SQL.insertComponenteInterfazPaqueteModificado",
				componenteInterfazPaquete);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getDescripcionArchivoControl(java.util.Map)
	 */
	public String getDescripcionArchivoControl(Map criteria) {
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.InterfazSQL.getDescripcionArchivoControl", criteria);
		return valor;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDAO#getComponentesInterfazUnitaria(biz.belcorp.ssicc.dao.sisicc.model.InterfazPK)
	 */
	public List getComponentesInterfazUnitaria(InterfazPK primaryKey) {
		log.debug("Entering 'getComponentesInterfazUnitaria' method");
		List componentes = getSqlMapClientTemplate()
				.queryForList(
						"sisicc.ConfiguracionInterfaz2SQL.getComponentesInterfazUnitaria",
						primaryKey);

		for (Iterator iter = componentes.iterator(); iter.hasNext();) {
			Interfaz interfaz = (Interfaz) iter.next();
			List parametros = getSqlMapClientTemplate().queryForList(
					"sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
					interfaz.getInterfazPK());
			log.debug(interfaz.getCodigo() + ": params=" + parametros);
			interfaz.setParametros(parametros);
		}
		return componentes;
	}
	
	public List getInterfacesNoAsignadas(Interfaz interfaz) {
		List interfaces = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfacesNoAsignadas", interfaz);
		return interfaces;
	}
	
	public List getInterfacesAsignadasList(Interfaz interfaz) {
		List interfaces = getSqlMapClientTemplate().queryForList(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfacesAsignadasList", interfaz);
		return interfaces;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#deleteComponentesInterfazPaqueteMante(java.util.Map)
	 */
	public void deleteComponentesInterfazPaqueteMante(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.deleteComponentesInterfazPaqueteMante", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#insertComponenteInterfazPaqueteMante(java.util.Map)
	 */
	public void insertComponenteInterfazPaqueteMante(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ConfiguracionInterfaz2SQL.insertComponenteInterfazPaqueteMante",criteria);
		
	}
	
	public String getInterfazComponente(Map criteria) {
		String valor = (String) getSqlMapClientTemplate().queryForObject(
				"sisicc.ConfiguracionInterfaz2SQL.getInterfazComponente", criteria);
		return valor;
	}

	
}