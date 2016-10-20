package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPESecuenciaClientesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
public interface ProcesoAPESecuenciaClientesService extends Service {

	/**
	 * @param criteria
	 * Ejecuta el proceso de secuencia de clientes
	 */
	public void executeSecuenciaClientes(Map criteria);
	
	/**
	 * @param queryParams
	 * Carga la secuencia de clientes
	 */
	public void executeProcesoAPECargaRutasCliente(Map queryParams);
}
