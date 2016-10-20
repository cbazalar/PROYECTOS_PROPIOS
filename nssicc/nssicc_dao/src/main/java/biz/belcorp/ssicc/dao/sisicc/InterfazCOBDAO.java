/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="InterfazCOBDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public interface InterfazCOBDAO extends DAO {

	/**
	 * executa la interfaz de envio de Cobranzas y Saldos Pendientes
	 * @param parametros
	 */
	public void executeInterfazCOBEnviarCobranzaSaldoPendiente(Map parametros);
	
	/**
	 * Ejecuta la interfaz de envo de Transaccin Cobranza
	 * @param criteria
	 */
	public void executeInterfazCOBEnviarArchivoTransaccionCobranza(Map criteria);
	
	/**
	 * Ejecuta la interfaz de envio de Cobranzas por Periodo y Zona
	 * @param parametros
	 */
	public void executeInterfazCOBEnviarCobranzaPeriodoZona(Map parametros);

	/**
	 * Ejecuta la interfaz de envio de Recuperacion Cobranzas por Cobrador
	 * @param params
	 */
	public void executeInterfazCOBEnviarRecuperacionCobranzaPorCobrador(Map params);
	
	/**
	 * Ejecuta la interfaz ENVIAR INFORMACION TRANSUNION
	 * @param params
	 */
	public void executeInterfazCOBEnviarInformacionTransUnion(Map params);
	
	/**
	 * Ejecuta la interfaz ENVIAR INFORMACION TRANSUNION, el stored previo INT_PKG_COB. INT_PR_COB_ENVIO_TRANS_UNION
	 * @param params
	 */
	public void executeInterfazCOBEnviarInformacionTU(Map params);
	
	/* INI SA PER-SiCC-2012-0840 */
	/**
	 * Obtiene el codigo de Sociedad por Defecto
	 * 
	 * @return
	 */
	public String getSociedadPorDefecto();
	/* FIN SA PER-SiCC-2012-0840 */
	
	public void executeInterfazCOBEnviarInformacionProveedoresCobranza(Map params);
	
	public void executeInterfazCOBEnviarEntregaCartera(Map params);
	
	public void executeInterfazCOBEnviarActualizacionSaldos(Map params);
	
	public void executeInterfazCOBEnviarRetroalimentacionGestiones(Map params);
	
	public void executeInterfazCOBEnviarControlRegistrosEnviados(Map params);
	
	public List getDatosFTPProveedores(String codigoPais);
	
	public void executeInterfazCOBEnviarArchivoOCR1(Map params);
	
	public void executeInterfazCOBEnviarArchivoOCR2(Map params);
	
	public List getDatosFTP();
	
	public List getDatosFTPCOBRecepcion();

	public void executeInterfazCOBRecepcionarGestionCobranzaTercero(Map params);

	public void executeInterfazCOBRecepcionarControlRegistroRecibido(Map params);
	
	public void executeInterfazCOBEnviarRecuCobranzaFFVVDatamart(Map params);
	
	public void executeInterfazCOBGenerarInformacionAcovedi(Map params);
	
	public void executeInterfazCOBGenerarInformacionDatacredito(Map params);
	
	/**
	 * 
	 * @param params
	 */
	public void executeInterfazEnvioArchivoInicioEsika(Map params);
	public void executeInterfazEnvioArchivoMorosasEsika(Map params);
	public void executeInterfazEnvioArchivoCastigadasEsika(Map params);
	
	public void executeInterfazEnvioArchivoInicioLbel(Map params);
	public void executeInterfazEnvioArchivoMorosasLbel(Map params);
	public void executeInterfazEnvioArchivoCastigadasLbel(Map params);
}