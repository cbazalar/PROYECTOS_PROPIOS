package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCCCFacturarInteresService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 */
public interface MantenimientoCCCFacturarInteresService extends Service {
    

	public List getConsoCalcuInteMoralList(Map criteria);
	
	/**
	 * Actualiza la Consolidado
	 * 
	 * @param criteria
	 * @param usuario
	 */
	public boolean updateConsolidadoInterMoraCCC(Map criteria, Usuario usuario);
	
	String obtenerPathUpload(String codigoPais);
	
	int cargarArchivoExcel(Map criteria) throws Exception;
	
	List executeValidarConsolidadoInterMora(String codigoUsuario);
	
	void executeProcesarCargaConsolidadoInterMora(String codigoUsuario);
	
}
