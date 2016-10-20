/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;


/**
 * 
 * <p>
 * <a href="InterfazDATDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman</a>
 * 
 */
/**
 * @author peextsbuchelli
 *
 */
@Repository("sisicc.interfazDATDAO")
public class InterfazDATDAOiBatis extends BaseDAOiBatis implements InterfazDATDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeCargarTablaInterfaz(java.util.Map)
	 */
	public void executeCargarTablaInterfaz(Map params){
		getSqlMapClientTemplate().update(
				"spusicc.InterfazDATSQL.executeCargarTablaInterfaz", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#getInterfazDATEnviarTipoClasificacionPrograma(java.util.Map)
	 */
	public List getInterfazDATEnviarTipoClasificacionPrograma(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.getInterfazDATEnviarTipoClasificacionPrograma",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#getInterfazDATEnviarResponsablesSeccion(java.util.Map)
	 */
	public List getInterfazDATEnviarResponsablesSeccion(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.getInterfazDATEnviarResponsablesSeccion",params);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#getInterfazDATEnviarRankingLideres(java.util.Map)
	 */
	public List getInterfazDATEnviarRankingLideres(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.getInterfazDATEnviarRankingLideres",params);
	}		
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarRankingLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarRankingLideres(Map params) {
		getSqlMapClientTemplate().
			update("spusicc.InterfazDATSQL.executeInterfazDATEnviarRankingLideres",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResponsablesSeccion(java.util.Map)
	 */
	public void executeInterfazDATEnviarResponsablesSeccion(Map params) {
		getSqlMapClientTemplate().
			update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResponsablesSeccion",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoClasificacionPrograma(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoClasificacionPrograma(Map params) {
		getSqlMapClientTemplate().
			update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoClasificacionPrograma",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroTipoClasificacionLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroTipoClasificacionLideres(
			Map params) {
		getSqlMapClientTemplate().
			update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroTipoClasificacionLideres",params);
		
	}

	public void executeInterfazDATEnviarPuntajeConcurso(Map params) {
		getSqlMapClientTemplate().
			update("spusicc.InterfazDATSQL.executeInterfazDATEnviarPuntajeConcurso",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarProductosApoyo(java.util.Map)
	 */
	public void executeInterfazDATEnviarProductosApoyo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarProductosApoyo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeEnviarProductosReemplazo(java.util.Map)
	 */
	public void executeInterfazDATEnviarProductosReemplazo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarProductosReemplazo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCronogramaPlanificado(java.util.Map)
	 */
	public void executeInterfazDATEnviarCronogramaPlanificado(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCronogramaPlanificado", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMotivoCDR(java.util.Map)
	 */
	public void executeInterfazDATEnviarMotivoCDR(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMotivoCDR", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMotivoRechazo(java.util.Map)
	 */
	public void executeInterfazDATEnviarMotivoRechazo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMotivoRechazo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMotivoRechazoCDR(java.util.Map)
	 */
	public void executeInterfazDATEnviarMotivoRechazoCDR(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMotivoRechazoCDR", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarProductosRecuperacion(java.util.Map)
	 */
	public void executeInterfazDATEnviarProductosRecuperacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarProductosRecuperacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarBoletasRecojo(java.util.Map)
	 */
	public void executeInterfazDATEnviarBoletasRecojo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarBoletasRecojo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarDetalleDocuPostVenta(java.util.Map)
	 */
	public void executeInterfazDATEnviarDetalleDocuPostVenta(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarDetalleDocuPostVenta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarDocuPostVenta(java.util.Map)
	 */
	public void executeInterfazDATEnviarDocuPostVenta(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarDocuPostVenta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarPedidosRechazados(java.util.Map)
	 */
	public void executeInterfazDATEnviarPedidosRechazados(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarPedidosRechazados", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechaCierrePeriodo(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechaCierrePeriodo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"spusicc.InterfazDATSQL.executeInterfazDATEnviarFechaCierrePeriodo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarProductoReemplazo(java.util.Map)
	 */
	public void executeInterfazDATEnviarProductoReemplazo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"spusicc.InterfazDATSQL.executeInterfazDATEnviarProductoReemplazo", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarLogrosLoveConsultora(java.util.Map)
	 */
	public void executeInterfazDATEnviarLogrosLoveConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarLogrosLoveConsultora", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroGrupoSegmentoLove(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroGrupoSegmentoLove(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroGrupoSegmentoLove", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroSegmentoLove(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroSegmentoLove(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroSegmentoLove", params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroTipoLogro(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroTipoLogro(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroTipoLogro", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechaDocumentos(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechaDocumentos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarFechaDocumentos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarAcceso(java.util.Map)
	 */
	public void executeInterfazDATEnviarAcceso(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarAcceso", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarEmpresaSiCC(java.util.Map)
	 */
	public void executeInterfazDATEnviarEmpresaSiCC(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarEmpresaSiCC", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechaCierreIncentivos(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechaCierreIncentivos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarFechaCierreIncentivos", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarHijasDuplas(java.util.Map)
	 */
	public void executeInterfazDATEnviarHijasDuplas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarHijasDuplas", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarRegion(java.util.Map)
	 */
	public void executeInterfazDATEnviarRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarRegion", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarAsistenciaCompartamos(java.util.Map)
	 */
	public void executeInterfazDATEnviarAsistenciaCompartamos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarAsistenciaCompartamos", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarGananciaCampanha(java.util.Map)
	 */
	public void executeInterfazDATEnviarGananciaCampanha(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarGananciaCampanha", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarRegistroLogros(java.util.Map)
	 */
	public void executeInterfazDATEnviarRegistroLogros(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarRegistroLogros", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoLogros(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoLogros(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoLogros", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroProgramaLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroProgramaLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroProgramaLideres", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelCampana(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelCampana", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelPrograma(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelPrograma(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelPrograma", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarRangoPedidos(java.util.Map)
	 */
	public void executeInterfazDATEnviarRangoPedidos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarRangoPedidos", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoCampana(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoCampana", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoPrograma(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoPrograma(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoPrograma", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCampanhas(java.util.Map)
	 */
	public void executeInterfazDATEnviarCampanhas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCampanhas", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarHomologacionStatusCliente(java.util.Map)
	 */
	public void executeInterfazDATEnviarHomologacionStatusCliente(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarHomologacionStatusCliente", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarInformacionGerenteRegionales(java.util.Map)
	 */
	public void executeInterfazDATEnviarInformacionGerenteRegionales(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarInformacionGerenteRegionales", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarInformacionLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarInformacionLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarInformacionLideres", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMatrizCampanha(java.util.Map)
	 */
	public void executeInterfazDATEnviarMatrizCampanha(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMatrizCampanha", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarSecciones(java.util.Map)
	 */
	public void executeInterfazDATEnviarSecciones(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarSecciones", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarStatusZona(java.util.Map)
	 */
	public void executeInterfazDATEnviarStatusZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarStatusZona", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTablaZona(java.util.Map)
	 */
	public void executeInterfazDATEnviarTablaZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTablaZona", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoOfertas(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoOfertas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoOfertas", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarUnidadesGeograficas(java.util.Map)
	 */
	public void executeInterfazDATEnviarUnidadesGeograficas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarUnidadesGeograficas", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarUnidadesGeograficasCampanha(java.util.Map)
	 */
	public void executeInterfazDATEnviarUnidadesGeograficasCampanha(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarUnidadesGeograficasCampanha", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarZonaRealesRegion(java.util.Map)
	 */
	public void executeInterfazDATEnviarZonaRealesRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarZonaRealesRegion", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarInformacionGerenteZona(java.util.Map)
	 */
	public void executeInterfazDATEnviarInformacionGerenteZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarInformacionGerenteZona", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNumeroPedidosDia(java.util.Map)
	 */
	public void executeInterfazDATEnviarNumeroPedidosDia(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNumeroPedidosDia", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTransaccionesZona(java.util.Map)
	 */
	public void executeInterfazDATEnviarTransaccionesZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTransaccionesZona", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarControlCierre(java.util.Map)
	 */
	public void executeInterfazDATEnviarControlCierre(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarControlCierre", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNumeroPedidosCampana(java.util.Map)
	 */
	public void executeInterfazDATEnviarNumeroPedidosCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNumeroPedidosCampana", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarStatusConsultora(java.util.Map)
	 */
	public void executeInterfazDATEnviarStatusConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarStatusConsultora", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTiempoCampana(java.util.Map)
	 */
	public void executeInterfazDATEnviarTiempoCampana(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTiempoCampana", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechaProceso(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechaProceso(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarFechaProceso", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarBoletaDespacho(java.util.Map)
	 */
	public void executeInterfazDATEnviarBoletaDespacho(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarBoletaDespacho", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarProyeProgra(java.util.Map)
	 */
	public void executeInterfazDATEnviarProyeProgra(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarProyeProgra", params);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTransaccionesClientes(java.util.Map)
	 */
	public void executeInterfazDATEnviarTransaccionesClientes(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTransaccionesClientes", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCanalIngresoPedido(java.util.Map)
	 */
	public void executeInterfazDATEnviarCanalIngresoPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCanalIngresoPedido", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarImpuestoIce(java.util.Map)
	 */
	public void executeInterfazDATEnviarImpuestoIce(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarImpuestoIce", params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroDeProgramaDeReconocimiento(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroDeProgramaDeReconocimiento(
			Map params) {//aca DAT-128
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroDeProgramaDeReconocimiento", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelDeProgramaReconocimiento(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelDeProgramaReconocimiento(Map params) {//DAT-129
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelDeProgramaReconocimiento", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelDeProgramaReconocimientoPuntaje(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelDeProgramaReconocimientoPuntaje(
			Map params) {//DAT-130
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelDeProgramaReconocimientoPuntaje", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarConsultoraConReconocimiento(java.util.Map)
	 */
	public void executeInterfazDATEnviarConsultoraConReconocimiento(Map params) {//DAT-131
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarConsultoraConReconocimiento", params);
		
	}
	
	/* INI JJ PER-SiCC-2012-0250 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnterfazDATEnviarClasificacionLider(java.util.Map)
	 */
	public void executeInterfazDATEnterfazDATEnviarClasificacionLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnterfazDATEnviarClasificacionLider", params);
	}
	/* FIN JJ PER-SiCC-2012-0250 */
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCargosConsultora(java.util.Map)
	 */
	public void executeInterfazDATEnviarCargosConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCargosConsultora", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarAbonosConsultora(java.util.Map)
	 */
	public void executeInterfazDATEnviarAbonosConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarAbonosConsultora", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechaCierreZonas(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechaCierreZonas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarFechaCierreZonas", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoCargos(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoCargos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoCargos", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTiposAbonos(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoAbonos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoAbonos", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarEstadosLider(java.util.Map)
	 */
	public void executeInterfazDATEnviarEstadosLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarEstadosLider", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarClasificacionesLider(java.util.Map)
	 */
	public void executeInterfazDATEnviarClasificacionesLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarClasificacionesLider", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarLideres", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelExitoObjetivoIngreso(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelExitoObjetivoIngreso(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelExitoObjetivoIngreso", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelExitoObjetivoPedido(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelExitoObjetivoPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelExitoObjetivoPedido", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarProgramasLider(java.util.Map)
	 */
	public void executeInterfazDATEnviarProgramasLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarProgramasLider", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResponsableSeccion(java.util.Map)
	 */
	public void executeInterfazDATEnviarResponsableSeccion(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResponsableSeccion", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoConcursoLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoConcursoLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoConcursoLideres", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoCumplRetenciones(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoCumplRetenciones(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoCumplRetenciones", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTiposBajaLider(java.util.Map)
	 */
	public void executeInterfazDATEnviarTiposBajaLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTiposBajaLider", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarRolDirec(java.util.Map)
	 */
	public void executeInterfazDATEnviarRolDirec(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarRolDirec", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarPerfilDirec(java.util.Map)
	 */
	public void executeInterfazDATEnviarPerfilDirec(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarPerfilDirec", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoCargo(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoCargo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoCargo", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoLicen(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoLicen(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoLicen", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoOpera(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoOpera(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoOpera", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarClienDirec(java.util.Map)
	 */
	public void executeInterfazDATEnviarClienDirec(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarClienDirec", params);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarOperaDirec(java.util.Map)
	 */
	public void executeInterfazDATEnviarOperaDirec(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarOperaDirec", params);
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroPrograma(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroPrograma(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroPrograma", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarInicioCursoBonoLet(java.util.Map)
	 */
	public void executeInterfazDATEnviarInicioCursoBonoLet(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarInicioCursoBonoLet", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoBonoLet(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoBonoLet(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoBonoLet", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarNivelLet(java.util.Map)
	 */
	public void executeInterfazDATEnviarNivelLet(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarNivelLet", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarLanzamientosEstrategicos(java.util.Map)
	 */
	public void executeInterfazDATEnviarLanzamientosEstrategicos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarLanzamientosEstrategicos", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarDetalleLanzamientosEstrategicos(java.util.Map)
	 */
	public void executeInterfazDATEnviarDetalleLanzamientosEstrategicos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarDetalleLanzamientosEstrategicos", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarLanzamientosEstrategicosNivel(java.util.Map)
	 */
	public void executeInterfazDATEnviarLanzamientosEstrategicosNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarLanzamientosEstrategicosNivel", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoLideres", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarTipoCanasta(java.util.Map)
	 */
	public void executeInterfazDATEnviarTipoCanasta(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarTipoCanasta", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCanastaPorNivel(java.util.Map)
	 */
	public void executeInterfazDATEnviarCanastaPorNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCanastaPorNivel", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarDetalleCanastaPorNivel(java.util.Map)
	 */
	public void executeInterfazDATEnviarDetalleCanastaPorNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarDetalleCanastaPorNivel", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroTramos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroTramos", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCobranzaTramos(java.util.Map)
	 */
	public void executeInterfazDATEnviarCobranzaTramos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCobranzaTramos", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoCobranzaTramos(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoCobranzaTramos(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoCobranzaTramos", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCicloVida(java.util.Map)
	 */
	public void executeInterfazDATEnviarCicloVida(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCicloVida", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarCicloVidaPorNivel(java.util.Map)
	 */
	public void executeInterfazDATEnviarCicloVidaPorNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarCicloVidaPorNivel", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarResultadoPedido(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoPedido", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroLideres", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroClasificacionLideres(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroClasificacionLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroClasificacionLideres", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroSeccion(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroSeccion(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroSeccion", params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroBajas(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroBajas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroBajas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroCalendarioFeriados(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroCalendarioFeriados(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroCalendarioFeriados", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroExigenciaWeb(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroExigenciaWeb(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroExigenciaWeb", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarMaestroTipoReemplazo(java.util.Map)
	 */
	public void executeInterfazDATEnviarMaestroTipoReemplazo(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarMaestroTipoReemplazo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarArchivoControl(java.util.Map)
	 */
	public void executeInterfazDATEnviarArchivoControl(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarArchivoControl", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATnviarRankingNivel(java.util.Map)
	 */
	public void executeInterfazDATnviarRankingNivel(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATnviarRankingNivel", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarDescuentosNuevas(java.util.Map)
	 */
	public void executeInterfazDATEnviarDescuentosNuevas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarDescuentosNuevas", params);
	}

	public void executeInterfazDATEnviarConsultorasExcluidas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarConsultorasExcluidas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#executeInterfazDATEnviarFechasEntregaExacta(java.util.Map)
	 */
	public void executeInterfazDATEnviarFechasEntregaExacta(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarFechasEntregaExacta", params);
	}
	
	public boolean validarCodigoConsultora(Map params) {
		boolean valida=false;
		Integer cantidad= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.validarCodigoConsultora", params);
		if(cantidad.intValue()>0){
			valida=true;
		}
		return valida;	
			
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDATDAO#getTipoClienteSubTipoClientePorConsultora(java.util.Map)
	 */
	public Map getTipoClienteSubTipoClientePorConsultora(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.InterfazDATSQL.getTipoClienteSubTipoClientePorConsultora", criteria);
	}
	
	
	public Integer validarCodigoCampanha(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.validarCodigoCampanha", params);
	}
	
	
	public int getCorrelativoDATClienClasi() {
		Integer correlativo = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.getCorrelativoDATClienClasi", null);
		if (correlativo == null) {
			correlativo = 1;
		}
		return  correlativo;
	}

	public void insertDATClienClasi(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.InterfazDATSQL.insertDATClienClasi", criteria);
	}
	
	public boolean validarExistenciaTipoClasificacion(Map params) {
		boolean valida=false;
		Integer cantidad= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.validarExistenciaTipoClasificacion", params);
		if(cantidad.intValue()>0){
			valida=true;
		}
		return valida;	
	}
	
	public Map getOidsClasificacionTipoClasificacionPorParametrosInterfaz(Map params) {		
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.InterfazDATSQL.getOidsClasificacionTipoClasificacionPorParametrosInterfaz", params);
	}

	public void deleteClasificacionClientePorOidClasificacion(Map params) {
		getSqlMapClientTemplate().delete(
				"spusicc.InterfazDATSQL.deleteClasificacionClientePorOidClasificacion",
				params);
		
	}

	public Integer getOidTipoSubTipoClientePorCodigoCliente(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.getOidTipoSubTipoClientePorCodigoCliente", params);
	}

	public Integer getOidTipoClasificacionPorParametriaInterfaz(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.getOidTipoClasificacionPorParametriaInterfaz", params);
	}

	public boolean validarExistenciaClienteClasificacion(Map params) {
		boolean valida=false;
		Integer cantidad= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.validarExistenciaClienteClasificacion", params);
		if(cantidad.intValue()>0){
			valida=true;
		}
		return valida;
	}

	@Override
	public void executeInterfazDATEnviarEstadosProgramas(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarEstadosProgramas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO#executeInterfazDATEnviarRecuperacionCobranza(java.util.Map)
	 */
	@Override
	public void executeInterfazDATEnviarRecuperacionCobranza(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.executeInterfazDATEnviarRecuperacionCobranza", params);	
	}

	@Override
	public String getValidacionInterfazDatamart(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.InterfazDATSQL.getValidacionInterfazDatamart", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO#getValidacionCierreCampanyaPendiente(java.util.Map)
	 */
	@Override
	public String getValidacionCierreCampanyaPendiente(Map criteria) {
		Integer cantidad = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.InterfazDATSQL.validacionCierreCampanyaPendiente", criteria);		
		return (cantidad.intValue() > 0) ? Constants.ESTADO_ACTIVO : Constants.ESTADO_INACTIVO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO#executeInterfazDATEnviarResultadoRangoComision(java.util.Map)
	 */
	public void executeInterfazDATEnviarResultadoRangoComision(Map params) {
		getSqlMapClientTemplate().update("spusicc.InterfazDATSQL.executeInterfazDATEnviarResultadoRangoComision", params);
	}
}