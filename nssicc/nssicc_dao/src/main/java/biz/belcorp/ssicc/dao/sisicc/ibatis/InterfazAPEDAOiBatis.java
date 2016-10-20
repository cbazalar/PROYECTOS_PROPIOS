package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Repository("sisicc.interfazAPEDAO")
public class InterfazAPEDAOiBatis extends BaseDAOiBatis implements InterfazAPEDAO {
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarCabeceraDocumentosDYTCAB(java.util.Map)
	 */
	public void executeInterfazAPEEnviarCabeceraDocumentosDYTCAB(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarCabeceraDocumentosDYTCAB", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarDetalleDocumentosDYTDET(java.util.Map)
	 */
	public void executeInterfazAPEEnviarDetalleDocumentosDYTDET(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarDetalleDocumentosDYTDET", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarMaestroDestinosDYTCLI(java.util.Map)
	 */
	public void executeInterfazAPEEnviarMaestroDestinosDYTCLI(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarMaestroDestinosDYTCLI", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarMaestroCuentasDYTCTA(java.util.Map)
	 */
	public void executeInterfazAPEEnviarMaestroCuentasDYTCTA(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarMaestroCuentasDYTCTA", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarMaestroTerritoriosDYTSCT(java.util.Map)
	 */
	public void executeInterfazAPEEnviarMaestroTerritoriosDYTSCT(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarMaestroTerritoriosDYTSCT", params);
    }
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarSecuenciaCuentasDYTSCX(java.util.Map)
	 */
	public void executeInterfazAPEEnviarSecuenciaCuentasDYTSCX(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarSecuenciaCuentasDYTSCX", params);
    }	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarCronogramaOperacionesDYTCRO(java.util.Map)
	 */
	public void executeInterfazAPEEnviarCronogramaOperacionesDYTCRO(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarCronogramaOperacionesDYTCRO", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarControlProcesoDYTCTR(java.util.Map)
	 */
	public void executeInterfazAPEEnviarControlProcesoDYTCTR(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarControlProcesoDYTCTR", params);
    }			

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarEstimadosDistribucion(java.util.Map)
	 */
	public void executeInterfazAPEEnviarEstimadosDistribucion(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarEstimadosDistribucion", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPERecepcionarAnaqueles(java.util.Map)
	 */
	public void executeInterfazAPERecepcionarAnaqueles(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPERecepcionarAnaqueles", params);
    }
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPERecepcionarChequeoServiceImpl(java.util.Map)
	 */
	public void executeInterfazAPERecepcionarChequeo(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPERecepcionarChequeo", params);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#getEnvioMailsAPEParams(java.util.Map)
	 */
	public List getEnvioMailsAPEParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazAPESQL.getEnvioMailsAPEParams",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#getListaProductosSinIndicador(java.util.Map)
	 */
	public List getListaProductosSinIndicador(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazAPESQL.getListaProductosSinIndicador",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#getPeriodoProcesoEnvioAnaqueles()
	 */
	public String getPeriodoProcesoEnvioAnaqueles(){		
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazAPESQL.getPeriodoProcesoEnvioAnaqueles", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarCostoCaja(java.util.Map)
	 */
	public void executeInterfazAPEEnviarCostoCaja(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarCostoCaja", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#getPaisMarcaSat(java.util.Map)
	 */
	public List getPaisMarcaSat(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazAPESQL.getPaisMarcaSat",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarArchivoProductos(java.util.Map)
	 */
	public void executeInterfazAPEEnviarArchivoProductos(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarArchivoProductos",criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarArchivoAnaqueles(java.util.Map)
	 */
	public void executeInterfazAPEEnviarArchivoAnaqueles(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarArchivoAnaqueles",criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarOlasWCS(java.util.Map)
	 */
	public void executeInterfazAPEEnviarOlasWCS(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarOlasWCS",criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarDatosEtiquetadoWCS(java.util.Map)
	 */
	public void executeInterfazAPEEnviarDatosEtiquetadoWCS(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarDatosEtiquetadoWCS",criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarOlasConfirmarWCS(java.util.Map)
	 */
	public void executeInterfazAPEEnviarOlasConfirmarWCS(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarOlaConfirmarWCS",criteria);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarDatosEtiquetadoConfirmarWCS(java.util.Map)
	 */
	public void executeInterfazAPEEnviarDatosEtiquetadoConfirmarWCS(Map criteria){
        getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarDatosConfirmarEtiquetadoWCS",criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeGenerarFacturasAnuladasWCS(java.util.Map)
	 */
	public void executeGenerarFacturasAnuladasWCS(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazRECSQL.executeGenerarFacturasAnuladasWCS",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeProcesarCajaEmbalaje(java.util.Map)
	 */
	public void executeProcesarCajaEmbalaje(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeProcesarCajaEmbalaje",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInsertarListaAnuladoTemporal(java.util.Map)
	 */
	public void executeInsertarListaAnuladoTemporal(Map params){
		getSqlMapClientTemplate().insert("sisicc.InterfazRECSQL.executeInsertarListaAnuladoTemporal",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarProductosMaterialGerenteZonaRegional(java.util.Map)
	 */
	public void executeInterfazAPEEnviarProductosMaterialGerenteZonaRegional(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarProductosMaterialGerenteZonaRegional", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarCabeceraBoletasRecojo(java.util.Map)
	 */
	public void executeInterfazAPEEnviarCabeceraBoletasRecojo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarCabeceraBoletasRecojo", params);
	}

	public void executeInterfazAPEEnviarCabeceraBoletasRecojoFacturacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarCabeceraBoletasRecojoFacturacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarDetalleBoletasRecojo(java.util.Map)
	 */
	public void executeInterfazAPEEnviarDetalleBoletasRecojo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarDetalleBoletasRecojo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarDetalleBoletasRecojoFacturacion(java.util.Map)
	 */
	public void executeInterfazAPEEnviarDetalleBoletasRecojoFacturacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarDetalleBoletasRecojoFacturacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPEEnviarEstimadosDistribucionDA(java.util.Map)
	 */
	public void executeInterfazAPEEnviarEstimadosDistribucionDA(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPEEnviarEstimadosDistribucionDA", params);
	}
	
	public void insertInterfazAPEPedidosDespachados(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPEPedidosDespachados", params);
	}
	
	public void insertInterfazAPEPedidosChequeados(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPEPedidosChequeados", params);
	}
	
	public void updateInterfazAPEPedidosChequeados(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.updateInterfazAPEPedidosChequeados", params);
	}
	
	public void insertInterfazAPEInicioArmadoPedido(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPEInicioArmadoPedido", params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAPEDAO#executeInterfazAPECambioFechaPromesa(java.util.Map)
	 */
	public void executeInterfazAPECambioFechaPromesa(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPECambioFechaPromesa", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO#deleteInterfazAPERecepcionarPicadoCabecera(java.lang.String)
	 */
	@Override
	public void deleteInterfazAPERecepcionarPicadoCabecera(String usuario) {
		getSqlMapClientTemplate().delete("sisicc.InterfazAPESQL.deleteInterfazAPERecepcionarPicadoCabecera", usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO#insertInterfazAPERecepcionarPicadoCabecera(java.util.Map)
	 */
	@Override
	public void insertInterfazAPERecepcionarPicadoCabecera(Map row) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPERecepcionarPicadoCabecera", row);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO#deleteInterfazAPERecepcionarPicadoDetalle(java.lang.String)
	 */
	@Override
	public void deleteInterfazAPERecepcionarPicadoDetalle(String usuario) {
		getSqlMapClientTemplate().delete("sisicc.InterfazAPESQL.deleteInterfazAPERecepcionarPicadoDetalle", usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO#insertInterfazAPERecepcionarPicadoDetalle(java.util.Map)
	 */
	@Override
	public void insertInterfazAPERecepcionarPicadoDetalle(Map row) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPERecepcionarPicadoDetalle", row);		
	}

	@Override
	public void insertInterfazAPERecepcionarOrdenImpresion(Map row) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAPESQL.insertInterfazAPERecepcionarOrdenImpresion", row);
	}

	@Override
	public String getCodigoProcesoPrint() {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.InterfazAPESQL.getCodigoProcesoPrint", null);
	}

	@Override
	public void executeInterfazAPERecepcionarPicadoCabecera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPERecepcionarPicadoCabecera", params);
	}

	@Override
	public void executeInterfazAPERecepcionarPicadoDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPERecepcionarPicadoDetalle", params);
		
	}

	@Override
	public void executeInterfazAPERecepcionarOrdenImpresion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAPESQL.executeInterfazAPERecepcionarOrdenImpresion", params);
		
	}

}