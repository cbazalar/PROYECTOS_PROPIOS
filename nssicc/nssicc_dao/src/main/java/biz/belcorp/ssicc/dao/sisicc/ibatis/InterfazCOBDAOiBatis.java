/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;

/**
 * 
 * <p>
 * <a href="InterfazCOBDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Repository("sisicc.interfazCOBDAO")
public class InterfazCOBDAOiBatis extends BaseDAOiBatis implements InterfazCOBDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarCobranzaSaldoPendiente(java.util.Map)
	 */
	public void executeInterfazCOBEnviarCobranzaSaldoPendiente(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarCobranzaSaldoPendiente",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarArchivoTransaccionCobranza(java.util.Map)
	 */
	public void executeInterfazCOBEnviarArchivoTransaccionCobranza(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarArchivoTransaccionCobranza", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarCobranzaPeriodoZona(java.util.Map)
	 */
	public void executeInterfazCOBEnviarCobranzaPeriodoZona(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarCobranzaPeriodoZona",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarRecuperacionCobranzaPorCobrador(java.util.Map)
	 */
	public void executeInterfazCOBEnviarRecuperacionCobranzaPorCobrador(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarRecuperacionCobranzaPorCobrador",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO#executeInterfazCOBEnviarInformacionTransUnion(java.util.Map)
	 */
	public void executeInterfazCOBEnviarInformacionTransUnion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarInformacionTransUnion",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO#executeInterfazCOBEnviarInformacionTU(java.util.Map)
	 */
	public void executeInterfazCOBEnviarInformacionTU(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarInformacionTU",params);
	}
	
	/* INI SA PER-SiCC-2012-0840 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#getSociedadPorDefecto()
	 */
	public String getSociedadPorDefecto() {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazCOBSQL.getSociedadPorDefecto");
	}
	/* FIN SA PER-SiCC-2012-0840 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarInformacionProveedoresCobranza(java.util.Map)
	 */
	public void executeInterfazCOBEnviarInformacionProveedoresCobranza(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarInformacionProveedoresCobranza", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarEntregaCartera(java.util.Map)
	 */
	public void executeInterfazCOBEnviarEntregaCartera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarEntregaCartera", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarActualizacionSaldos(java.util.Map)
	 */
	public void executeInterfazCOBEnviarActualizacionSaldos(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarActualizacionSaldos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarRetroalimentacionGestiones(java.util.Map)
	 */
	public void executeInterfazCOBEnviarRetroalimentacionGestiones(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarRetroalimentacionGestiones", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarControlRegistrosEnviados(java.util.Map)
	 */
	public void executeInterfazCOBEnviarControlRegistrosEnviados(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarControlRegistrosEnviados", params);
	}

	public List getDatosFTPProveedores(String codigoPais) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazCOBSQL.getDatosFTPProveedores", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarArchivoOCR1(java.util.Map)
	 */
	public void executeInterfazCOBEnviarArchivoOCR1(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarArchivoOCR1", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBEnviarArchivoOCR2(java.util.Map)
	 */
	public void executeInterfazCOBEnviarArchivoOCR2(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarArchivoOCR2", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#getDatosFTP()
	 */
	public List getDatosFTP() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazCOBSQL.getDatosFTP", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#getDatosFTPCOBRecepcion()
	 */
	public List getDatosFTPCOBRecepcion() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazCOBSQL.getDatosFTPCOBRecepcion", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBRecepcionarControlRegistroRecibido(java.util.Map)
	 */
	public void executeInterfazCOBRecepcionarControlRegistroRecibido(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBRecepcionarControlRegistroRecibido", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBRecepcionarGestionCobranzaTercero(java.util.Map)
	 */
	public void executeInterfazCOBRecepcionarGestionCobranzaTercero(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBRecepcionarGestionCobranzaTercero", params);
	}
	
	public void executeInterfazCOBEnviarRecuCobranzaFFVVDatamart(Map params) {
		
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBEnviarRecuCobranzaFFVVDatamart",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBGenerarInformacionAcovedi(java.util.Map)
	 */
	public void executeInterfazCOBGenerarInformacionAcovedi(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBGenerarInformacionAcovedi", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCOBDAO#executeInterfazCOBGenerarInformacionDatacredito(java.util.Map)
	 */
	public void executeInterfazCOBGenerarInformacionDatacredito(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazCOBGenerarInformacionDatacredito", params);
	}

	@Override
	public void executeInterfazEnvioArchivoInicioEsika(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoInicioEsika", params);		
	}

	@Override
	public void executeInterfazEnvioArchivoMorosasEsika(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoMorosasEsika", params);		
	}

	@Override
	public void executeInterfazEnvioArchivoCastigadasEsika(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoCastigadasEsika", params);		
	}

	@Override
	public void executeInterfazEnvioArchivoInicioLbel(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoInicioLbel", params);		
	}

	@Override
	public void executeInterfazEnvioArchivoMorosasLbel(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoMorosasLbel", params);		
	}

	@Override
	public void executeInterfazEnvioArchivoCastigadasLbel(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazCOBSQL.executeInterfazEnvioArchivoCastigadasLbel", params);		
	}
}