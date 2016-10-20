package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sergio Apaza
 */
public interface ProcesoOCRRecuperarImagenesSCService  extends Service {

	/**
	 * Obtenemos datos de los clientes que han sido escaneados su Solicitud de Credito
	 * 
	 * @param params
	 * @return
	 */
	public List getConsultaMasivaSC(Map params);
	
	/**
	 * Obtenemos datos de todos los clientes retiradas con saldo cero que han sido escaneados su Solicitud de Credito
	 * 
	 * @param params
	 * @return
	 */
	public List getConsultaTodasMasivaSC(Map params);	
	
	/**
	 * Realiza la generacion masiva de los PDFs de un grupo de consultoras
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void executeGenerarConsultaMasivaSC(Map params) throws Exception;
	
	/**
	 * Realiza la eliminacion masiva de los PDFs de un grupo de consultoras
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void executeDeleteMasivaSC(Map params) throws Exception;
	
	/**
	 * Obtiene el nombre de una carpeta temporal donde se van a generar los archivos de imagenes
	 * @return
	 */
	public String getIDCarpeta();
}
