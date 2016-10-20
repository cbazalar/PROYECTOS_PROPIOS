package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoValidacion;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * interface de validacion de STO.
 * <p>
 * <a href="ProcesoSTOService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

public interface ProcesoSTOHistoricoService extends Service {

	public String getNumeroProceso(AccionTipoDocumento accionTipoDocumento);

	/**
	 * @param historico
	 * @param usuario
	 */
	public void updateFinProceso(
			HistoricoTipoDocumento historico, Usuario usuario);

	/**
	 * @param historico
	 * @param usuario
	 */
	public void insertHistoricoProceso(HistoricoTipoDocumento historico,
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
