/*
 * Created on 04/07/2006 03:23:21 PM
 * biz.belcorp.ssicc.dao.ibatis.ProcesoImpresionDAOiBatis
 */
package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresionPK;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("spisicc.procesoImpresionDAO")
public class ProcesoImpresionDAOiBatis extends BaseDAOiBatis implements
ProcesoImpresionDAO {

	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getProcesosImpresion(biz.belcorp.ssicc.spisicc.model.ProcesoImpresion)
	*/
	public List getProcesosImpresion(ProcesoImpresion procesoImpresion) {
	List procesos = getSqlMapClientTemplate()
	        .queryForList("ProcesoImpresionSQL.getProcesosImpresion",
	                procesoImpresion);
	return procesos;
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getProcesosImpresionByCriteria(java.util.Map)
	*/
	public List getProcesosImpresionByCriteria(Map criteria) {
	List procesos = getSqlMapClientTemplate().queryForList(
	        "ProcesoImpresionSQL.getProcesosImpresionByCriteria",
	        criteria);
	return procesos;
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#getProcesoImpresion(java.lang.String)
	*/
	public ProcesoImpresion getProcesoImpresion(String codigoProceso) {
	ProcesoImpresion procesoImpresion = (ProcesoImpresion) getSqlMapClientTemplate()
	        .queryForObject("ProcesoImpresionSQL.getProcesoImpresion",
	                codigoProceso);
	if (procesoImpresion == null) {
	    throw new ObjectRetrievalFailureException(ProcesoImpresion.class,
	            codigoProceso);
	} else {
	    List subprocesos = getSqlMapClientTemplate()
	            .queryForList(
	                    "ProcesoImpresionSQL.getSubprocesosImpresionByCodigoProceso",
	                    codigoProceso);
	    if (subprocesos != null && subprocesos.size() > 0) {
	        for (int i = 0; i < subprocesos.size(); i++) {
	            SubprocesoImpresion subproceso = (SubprocesoImpresion) subprocesos
	                    .get(i);
	            SubprocesoImpresionPK pk = new SubprocesoImpresionPK(
	                    subproceso.getCodigoProceso(), subproceso
	                            .getCodigo());
	            List archivos = getSqlMapClientTemplate()
	                    .queryForList(
	                            "ProcesoImpresionSQL.getArchivosImpresionByCodigoSubproceso",
	                            pk);
	            subproceso.setArchivosImpresion(archivos);
	        }
	        procesoImpresion.setSubprocesosImpresion(subprocesos);
	    }
	}
	return procesoImpresion;
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#insertProcesoImpresion(biz.belcorp.ssicc.model.ProcesoImpresion,
	*      biz.belcorp.ssicc.model.Usuario)
	*/
	public void insertProcesoImpresion(ProcesoImpresion procesoImpresion,
	    Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.insertProcesoImpresion",
	        procesoImpresion);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#updateProcesoImpresion(biz.belcorp.ssicc.model.ProcesoImpresion,
	*      biz.belcorp.ssicc.model.Usuario)
	*/
	public void updateProcesoImpresion(ProcesoImpresion procesoImpresion,
	    Usuario usuario) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.updateProcesoImpresion",
	        procesoImpresion);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeCargarPaqueteDocumentarioSiCC(java.util.Map)
	*/
	public void executeCargarPaqueteDocumentarioSiCC(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarPaqueteDocumentarioSiCC",
	        parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeCargarCuponPago(java.util.Map)
	*/
	public void executeCargarCuponPago(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarCuponPago", parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeFusionarPaqueteDocumentario(java.util.Map)
	*/
	public void executeFusionarPaqueteDocumentario(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeFusionarPaqueteDocumentario",
	        parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeGenerarPaqueteDocumentario(java.util.Map)
	*/
	public void executeGenerarPaqueteDocumentario(Map parametros) {
	Integer numeroArchivosPaqueteDocumentario = new Integer(1);
	String numeroArchivos = MapUtils.getString(parametros,
	        "numeroArchivosPaqueteDocumentario");
	if (StringUtils.isNotBlank(numeroArchivos)) {
	    try {
	        numeroArchivosPaqueteDocumentario = Integer
	                .valueOf(numeroArchivos);
	    } catch (NumberFormatException ignore) {
	        log
	                .warn("Numero de archivos es invalido, el valor no es un número.");
	    }
	}
	parametros.put("numeroArchivosPaqueteDocumentario",
	        numeroArchivosPaqueteDocumentario);
	
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeGenerarPaqueteDocumentario",
	        parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#removeNotasCredito()
	*/
	public void removeNotasCredito() {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.removeNotasCredito", null);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeCargarNotaCredito(java.util.Map)
	*/
	public void executeCargarNotaCredito(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarNotaCredito", parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeGenerarNotaCreditoConsolidada(java.util.Map)
	*/
	public void executeGenerarNotaCreditoConsolidada(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeGenerarNotaCreditoConsolidada",
	        parametros);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getValorParametroImpresion(java.lang.String, java.lang.String)
	*/
	public String getValorParametroImpresion(String codigoProceso, String nombreParametro) {
	Map criteria = new HashMap();
	criteria.put("codigoProceso", codigoProceso);
	criteria.put("nombreParametro", nombreParametro);
	String valorParametro = (String) getSqlMapClientTemplate()
	        .queryForObject("ProcesoImpresionSQL.getValorParametroImpresion",
	                criteria);
	log.debug("valorParametro " + valorParametro);		 
	return valorParametro;
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeActualizarIndicadorImpresionNotasCredito(java.util.Map)
	*/
	public void executeActualizarIndicadorImpresionNotasCredito(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeActualizarIndicadorImpresionNotasCredito",
	        parametros);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeActualizarIndicadorImpresionNotasDebito(java.util.Map)
	*/
	public void executeActualizarIndicadorImpresionNotasDebito(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeActualizarIndicadorImpresionNotasDebito",
	        parametros);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCargarHojaPicado(java.util.Map)
	*/
	public void executeCargarHojaPicado(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarHojaPicado",
	        parametros);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeEliminarPaquetesDocumentarios(java.util.Map)
	*/
	public void executeEliminarPaquetesDocumentarios(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeEliminarPaquetesDocumentarios", null);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeCargarBlobPaqueteDocumentarioSiCC(java.util.Map)
	*/
	public void executeCargarBlobPaqueteDocumentarioSiCC(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarBlobPaqueteDocumentarioSiCC",
	        parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeCargarBlobCuponPago(java.util.Map)
	*/
	public void executeCargarBlobCuponPago(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeCargarBlobCuponPago", parametros);
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see biz.belcorp.ssicc.dao.ProcesoImpresionDAO#executeGenerarCuponPago(java.util.Map)
	*/
	public void executeGenerarCuponPago(Map parametros) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeGenerarCuponPago", parametros);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeEliminarPaquetesDocumentariosColor(java.lang.Object)
	*/
	public void executeEliminarPaquetesDocumentariosColor(Object object) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeEliminarPaquetesDocumentariosColor", null);
	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGenerarPaqueteDocumentarioColor(java.util.Map)
	*/
	public void executeGenerarPaqueteDocumentarioColor(Map params) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeGenerarPaqueteDocumentarioColor",
	        params);
	
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getListaRegionesActivasSpool()
	*/
	public List getListaRegionesActivasSpool() {
	return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getListaRegionesActivasSpool",null);
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeProcesoIMPSpoolDetalleFactura(java.util.Map)
	*/
	public void executeProcesoIMPSpoolDetalleFactura(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeProcesoIMPSpoolDetalleFactura",
	        proceso);
	
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#deleteProcesoIMPSpoolDetalleFactura()
	*/
	public void deleteProcesoIMPSpoolDetalleFactura() {
	getSqlMapClientTemplate().delete(
			"ProcesoImpresionSQL.deleteProcesoIMPSpoolDetalleFactura",
			null);
	
	}
	
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getListaZonasActivasSpool()
	*/
	public List getListaZonasActivasSpool() {
	return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getListaZonasActivasSpool",null);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolCupones(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/	
	public void executeSpoolCupones(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update(
	        "ProcesoImpresionSQL.executeSpoolCupones",
	        proceso);
	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#deleteProcesoIMPSpoolCupones()
	*/	
	public void deleteProcesoIMPSpoolCupones() {
	getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deleteProcesoIMPSpoolCupones",null);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolUltimasNoticias(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolUltimasNoticias(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolUltimasNoticias",proceso);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolBoletaDespacho(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolBoletaDespacho(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolBoletaDespacho",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolDetalleFacturaZona(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolDetalleFacturaZona(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolDetalleFacturaZona",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolEstadoCtaCte(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolEstadoCtaCte(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolEstadoCtaCte",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolOrdenCompra(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolOrdenCompra(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolOrdenCompra",proceso);		
	}
	/*procesos q no son spool*/
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeAsignacionStock(java.util.Map)
	*/
	public void executeAsignacionStock(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeAsignacionStock",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeBoletaElectronicas(java.util.Map)
	*/
	public void executeBoletaElectronicas(ProcesoSpool params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeBoletaElectronicas",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCalculoFlete(java.util.Map)
	*/
	public void executeCalculoFlete(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeCalculoFlete",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeDocumentoChequeo(java.util.Map)
	*/
	public void executeDocumentoChequeo(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeDocumentoChequeo",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGeneraConsolidado(java.util.Map)
	*/
	public void executeGeneraConsolidado(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraConsolidado",params);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSecuenciaPedido(java.util.Map)
	*/
	public void executeSecuenciaPedido(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSecuenciaPedido",params);
	}
	/*fin de procesos q no son spoll*/
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolCalculaConsolidado(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolCalculaConsolidado(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolCalculaConsolidado",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolCompaginacionFinal(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolCompaginacionFinal(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolCompaginacionFinal",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoBoletaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoBoletaMatricial(Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoBoletaMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoBoletaPremioMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoBoletaPremioMatricial(
		Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoBoletaPremioMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoCuponPagoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoCuponPagoMatricial(Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoCuponPagoMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoFacturaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoFacturaMatricial(Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoFacturaMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoGuiaRemisionMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoGuiaRemisionMatricial(
		Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoGuiaRemisionMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoNotaCreditoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoNotaCreditoMatricial(
		Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoNotaCreditoMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraArchivoNotaDebitoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraArchivoNotaDebitoMatricial(
		Map proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraArchivoNotaDebitoMatricial",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraBoletaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraBoletaMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraBoletaMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraBoletaPremioMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraBoletaPremioMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraBoletaPremioMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraCtaCteService(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraCtaCteService(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraCtaCteService",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraCuponMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraCuponMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraCuponMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraDocumentosLegales(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraDocumentosLegales(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraDocumentosLegales",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraFacturaMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraFacturaMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraFacturaMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraGuiaRemisionMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraGuiaRemisionMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraGuiaRemisionMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraNotaCreditoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraNotaCreditoMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraNotaCreditoMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraNotaDebitoMatricial(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraNotaDebitoMatricial(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraNotaDebitoMatricial",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraRuv(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraRuv(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraRuv",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolFacturaLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolFacturaLaserMultihilo(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolFacturaLaserMultihilo",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolNotaCreditoLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolNotaCreditoLaserMultihilo(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolNotaCreditoLaserMultihilo",proceso);	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolNotaDebitoLaserMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolNotaDebitoLaserMultihilo(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolNotaDebitoLaserMultihilo",proceso);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraNotaDebito(java.util.Map)
	*/
	public void executeSpoolGeneraNotaDebito(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraNotaDebito",params);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraNotaCredito(java.util.Map)
	*/
	public void executeSpoolGeneraNotaCredito(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraNotaCredito",params);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraFacturaLaser(java.util.Map)
	*/
	public void executeSpoolGeneraFacturaLaser(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraFacturaLaser",params);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolDetalleFactura3(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolDetalleFactura3(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolDetalleFactura3",proceso);
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCalculoConsolidado(java.util.Map)
	*/
	public void executeSpoolCalculaConsolidadoTotal(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolCalculaConsolidadoTotal",params);
	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolDetalleFactura4(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolDetalleFactura4(ProcesoSpool proceso) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolDetalleFactura4",proceso);
	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolGeneraCtaCorriente(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	*/
	public void executeSpoolGeneraCtaCorriente(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolGeneraCtaCorriente",params);		
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeSpoolProcesoSecuenciacion(java.util.Map)
	*/
	public void executeSpoolProcesoSecuenciacion(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeSpoolProcesoSecuenciacion",params);
	
	}
	
	/* (non-Javadoc)
	* @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCompaginacionPaqueteDocumentarioFinal2(java.util.Map)
	*/
	public void executeCompaginacionPaqueteDocumentarioFinal2(Map params) {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeCompaginacionPaqueteDocumentarioFinal2",params);
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCargaTemporalReporteErroresSTO(java.util.Map)
	 */
	public void executeCargaTemporalReporteErroresSTO(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeCargaTemporalReporteErroresSTO",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getCorreosGerenteRegion(java.util.Map)
	 */
	public List getCorreosGerenteRegion(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getCorreosGerenteRegion", codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getCorreosGerenteZona(java.util.Map)
	 */
	public List getCorreosGerenteZona(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getCorreosGerenteZona", codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#getParamEmailGerenteRegion(java.util.Map)
	 */
	public String getParamEmailGerenteRegion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getParamEmailGerenteRegion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeActualizaIndicadorGP2(java.util.Map)
	 */
	public void executeActualizaIndicadorGP2(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeActualizaIndicadorGP2", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGeneraFechaRepartoMultihilo(biz.belcorp.ssicc.spisicc.model.ProcesoSpool)
	 */
	public void executeGeneraFechaRepartoMultihilo(ProcesoSpool proceso) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraFechaRepartoMultihilo", proceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGenerarArchivoPaqueteDocumentario(java.util.Map)
	 */
	public void executeGenerarArchivoPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGenerarArchivoPaqueteDocumentario", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGeneraBoletaHonorarios(java.util.Map)
	 */
	public void executeGeneraBoletaHonorarios(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraBoletaHonorarios", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeGeneraFacturaGlobalMexico(java.util.Map)
	 */
	public void executeGeneraFacturaGlobalMexico(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraFacturaGlobalMexico", params);
    }	
	
	public String getParametroSTO(Map params){	
		return (String)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getParametroSTO", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoImpresionDAO#executeCalculoInterMora()
	 */
	public void executeCalculoInterMora() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeCalculoInterMora");		
	}

	public boolean validacionLimiteTiempoEjecucionProceso() {
		boolean valida=false;
		Integer dias = (Integer)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.validacionLimiteTiempoEjecucionProceso");
		if(dias!=null && dias.intValue() >= 1){
			valida=true;
		}
		return valida;
	}
	

}
