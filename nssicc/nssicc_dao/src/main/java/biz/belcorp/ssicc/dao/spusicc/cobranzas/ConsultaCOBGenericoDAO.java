
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;

/**
 * <p>
 * <a href="ConsultaCOBGenericoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */
public interface ConsultaCOBGenericoDAO extends DAO {
	
	
	/**
	 * @param codigoParametro
	 * @return
	 */
	String getParametroPais(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getEtapasDeuda();
	
	/**
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
	 * @param criteria
	 * @return
	 */
	public List getCarterasConsultoraList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getGestionesCobranzaConsultora(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCobradoresActivos();
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getSupervisores();
	
	/**
	 * @param criteria
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
	 * Generar comision abogados.
	 *
	 * @param criteria the criteria
	 */
	public void generarComisionAbogados(Map criteria);
	
}
