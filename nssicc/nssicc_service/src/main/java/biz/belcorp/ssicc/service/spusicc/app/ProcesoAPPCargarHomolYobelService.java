package biz.belcorp.ssicc.service.spusicc.app;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPPCargarHomolYobelService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface ProcesoAPPCargarHomolYobelService extends Service {
	
	/**
	 * Metodo que inserta registro homologado Yobel
	 * @param lineas 
	 */
	public List insertaHomologadoYobel(List lineas,Usuario usuario);

	/**
	 * Ejecuta el proceso de secuenciar zonas y territorios
	 */
	public void executeSecuenciarZonasTerritorios();
	
	/**
	 * Limpia la tabla de homologacion
	 */
	public void deleteTablaHomologacion();
	
	/**
	 * Obtiene Message Resource
	 */
	public String getMessageResource(Usuario usuario, String messageResource);
	

}
