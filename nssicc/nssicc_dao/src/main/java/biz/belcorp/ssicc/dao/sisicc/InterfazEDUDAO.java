package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz Educacion.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public interface InterfazEDUDAO {

	
	public void insertInterfazEDURecepcionarConsultoraDemanda(Map params);

	public void executeProcessRegistroAsistencia(Map map);

	public void insertInterfazEDURecepcionarConsultoraEstablecidas(Map map);

	public void deleteInterfazEDURecepcionarConsultoraEstablecidas(Map row);

}
