package biz.belcorp.ssicc.service.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ProgramaEjecutiva;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sebastian Guerra
 *
 */
public interface MantenimientoPEJProgramaEjecutivasService extends Service {
	/**
     * Obtiene todos los paises tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getProgramasByCriteria(Map criteria);

    public String getNextCodigoPrograma();
    
    public void insertProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario) throws Exception;
    
    public List getFasePEJ(Map parametros);
    
    public List getNivelPEJ(Map parametros);
    
    public List getRangoPEJ(Map parametros);
    
    public List getGrupoPEJ(Map parametros);
    
    public List getPorcentajePEJ(Map parametros);
    
    public List getTipoAbonoPEJ(Map parametros);
    
    public void updateProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario);
    
    public void deleteProgramaEjecutiva(Map parametros) throws Exception;
    
    public Map getPeriodoDefault();
    
    public String getExisteDependenciaFase(Map parametros);

	public String getExisteDependenciaNivel(Map parametros);

	public String getExisteDependenciaRango(Map parametros);

	public String getExisteDependenciaGrupo(Map parametros);

	public String getExisteDependenciaTipoAbono(Map parametros);

	public String getExisteLiquidacionCampanya(Map parametros);
}
