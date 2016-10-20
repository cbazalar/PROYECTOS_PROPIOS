package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Fase;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Grupo;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Porcentaje;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ProgramaEjecutiva;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Rango;
import biz.belcorp.ssicc.dao.spusicc.pej.model.TipoAbono;

/**
 * @author Sebastian Guerra
 *
 */
public interface MantenimientoPEJProgramaEjecutivasDAO extends DAO {
    /**
     * Obtiene un listado de todos los paises en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Pais poblados
     */
    public List getProgramasByCriteria(Map criteria);
    
    public String getNextCodigoPrograma();
    
    public void insertProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario);

    public void insertFase(Fase fase, Usuario usuario);
    
    public void insertNivel(Nivel nivel, Usuario usuario);
    
    public void insertRango(Rango rango, Usuario usuario);
    
    public void insertGrupo(Grupo grupo, Usuario usuario);
    
    public void insertPorcentaje(Porcentaje porcentaje, Usuario usuario);
    
    public void insertTipoAbono(TipoAbono tipoAbono, Usuario usuario);
    
    public List getFasePEJ(Map parametros);
    
    public List getNivelPEJ(Map parametros);
    
    public List getRangoPEJ(Map parametros);
    
    public List getGrupoPEJ(Map parametros);
    
    public List getPorcentajePEJ(Map parametros);
    
    public List getTipoAbonoPEJ(Map parametros);
    
    public void updateProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario);

    public void updateFase(Fase fase, Usuario usuario);
    
    public void updateNivel(Nivel nivel, Usuario usuario);
    
    public void updateRango(Rango rango, Usuario usuario);
    
    public void updateGrupo(Grupo grupo, Usuario usuario);
    
    public void updatePorcentaje(Porcentaje porcentaje, Usuario usuario);
    
    public void updateTipoAbono(TipoAbono tipoAbono, Usuario usuario);
    
    public void deleteProgramaEjecutiva(Map parametros) throws Exception;
    
    public Map getPeriodoDefault();
    
    public String getExisteDependenciaFase(Map parametros);
    
    public String getExisteDependenciaNivel(Map parametros);
    
    public String getExisteDependenciaRango(Map parametros);
    
    public String getExisteDependenciaGrupo(Map parametros);

	public String getExisteDependenciaTipoAbono(Map parametros);

	public String getExisteLiquidacionCampanya(Map parametros);
}
