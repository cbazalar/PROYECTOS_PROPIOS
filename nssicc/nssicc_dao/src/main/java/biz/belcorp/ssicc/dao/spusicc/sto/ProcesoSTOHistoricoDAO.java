package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoValidacion;
/**
 * Interface que ejecutara Historicos
 * <p>
 * <a href="ProcesoSTOHistoricoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
public interface ProcesoSTOHistoricoDAO extends DAO {


	/**
	 * Obtiene el numero de lote de STO actual
	 * @param tipoDocumentoDigitadoPK
	 * @return
	 */
	public String getNumeroProceso(AccionTipoDocumento accionTipoDocumento);

	/**
	 * Inserta el Inicio de Proceso
	 * @param historico
	 * @param usuario
	 */
	public void insertHistoricoProceso(HistoricoTipoDocumento historico,
			Usuario usuario);

	/**
	 * Actualiza el fin de Proceso
	 * @param historico
	 * @param usuario
	 */
	public void updateFinProceso(HistoricoTipoDocumento historico,
			Usuario usuario);
	
	/**
	 * @param params
	 * @return
	 */
	public List getProcesoValidacionEjecucionByDocumento(Map params);
	
	
	/**
	 * @param params
	 * @return
	 */
	public List getProcesoEjecucionByDocumento(Map params);
	
	/**
	 * @param historico
	 * @return
	 */
	public List getListaProcesosHijos(HistoricoTipoDocumento historico);

	/**
	 * @param historicoTipoDocumento
	 */
	public void UpdateInicioProceso(HistoricoTipoDocumento historico);

	/**
	 * @param historicoValidacion
	 */
	public void updateHistoricoValidacion(HistoricoValidacion historico);

	/**
	 * @param historico
	 */
	public void insertHistoricoValidaciones(HistoricoTipoDocumento historico);

	/**
	 * @param historico
	 */
	public void insertHistoricoValidacionesOnline(HistoricoTipoDocumento historico);
	

	/**
	 * @param historicoValidacion
	 */
	public void updateInicioValidacion(HistoricoValidacion historicoValidacion);

	/**
	 * @param historicoValidacion
	 */
	public void updateFinValidacion(HistoricoValidacion historicoValidacion);
	
	
}
