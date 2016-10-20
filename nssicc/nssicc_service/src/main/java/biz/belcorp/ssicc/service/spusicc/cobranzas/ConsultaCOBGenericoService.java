package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaCOBGenericoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
public interface ConsultaCOBGenericoService extends Service {
    
	
	/**
	 * Obtiene el valor del parametro de la tabla COB_PARAM_GENER
	 * @param codigoParametro
	 * @return
	 */
	String getParametroPais(Map criteria);
	
	
	/**
	 * Obtiene las Etapa de Deuda del Pais
	 * 
	 * @return
	 */
	public List getEtapasDeuda();
	
	/**
	 * Obtiene el Historial de Gestiones de Cobranza
	 * 
	 * @param criteria
	 * @return
	 */
	public List getHistorialGestionesCobranza(Map criteria);	
	
	/**
	 * @param criteria
	 * @return
	 */
	public Integer getPorcentajeMetaEtapaVentas(Map criteria);	
	
	/**
	 * Obtiene las carteras de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCarterasConsultoraList(Map criteria);
	
	/**
	 * Obtiene las gestiones de cobranza de una consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGestionesCobranzaConsultora(Map criteria);
	
	/**
	 * Obtiene los cobradores activos
	 * 
	 * @return
	 */
	public List getCobradoresActivos();
	
	/**
	 * Obtiene los Supervisores
	 * 
	 * @return
	 */
	public List getSupervisores();
	
	/**
	 * Obtiene las consultoras que tienen SC de una cartera determinada
	 * 
	 * @return
	 */
	public List getSCCarteraList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public FtpCobrador getFTPCobrador(String codigoCobrador);
	
	/**
	 * @return
	 */
	public String getDirectorioTemporal();
	
	/**
	 * Genera el reporte comision abogados.
	 *
	 * @param criteria the criteria
	 */
	public void generarComisionAbogados(Map criteria);
	
}
