package biz.belcorp.ssicc.service.spusicc.app;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPPCargarSecuenciaZonaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface ProcesoAPPCargarSecuenciaZonaService extends Service {
	
	/**
	 * Metodo que inserta Secuencia Zonas
	 * @param lineas
	 */
	public void insertaSecuenciaZona(List lineas);

	/**
	 * Limpia la tabla de Secuencia Zonas
	 */
	public void deleteTablaSecuenciaZona();

	/**
	 * Ejecuta la carga
	 * @param criteria
	 */
	public void executeCargaSecuenciaZona(Map criteria);
	
	/**
	 * Obtiene message resource
	 * @param usuario
	 * @param messageResource
	 * @return
	 */
	public String getMessageResource(Usuario usuario, String messageResource);
	
	

}