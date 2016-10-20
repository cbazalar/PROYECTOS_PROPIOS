package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.InterfazACCRecepcionarRecomendantePremio;

/**
 * DAO de la Interfaz Aplicativo Call Center
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface InterfazACCDAO {

	public String getIVRCompania(String codigoPais);
	
	public List getInterfazACCConcursoPremio(Map params);
	
	public String getACCLongitudDocumento(String codigoPais, String tipoDocumento);
	
	public Map validarACCInterfazRecomendantePremio(Map criteria);
	
	public void insertInterfazACCRecepcionarRecomendantePremio(InterfazACCRecepcionarRecomendantePremio detalle);

	public Map procesarInterfazACCActualizaConcursoRecomendacion(Map criteria);
	
	public void executeInterfazACCEnviarTablasClientes(Map params);
	
	public void executeInterfazACCEnviarTablasClientesAct(Map params);
	
	public void executeInterfazACCEnviarTablasCDR(Map params);

	public void executeInterfazACCRecepcionarRecomendantePremio(Map parametroMap);

	public int getCorrelativoACCReferidas();
	
	public void insertReferidas(Map criteria);
	
}
