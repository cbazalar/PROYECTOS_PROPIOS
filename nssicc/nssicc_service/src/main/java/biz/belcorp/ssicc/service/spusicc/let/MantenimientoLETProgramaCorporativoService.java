package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.let.model.ProgramaCorporativo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoLETProgramaCorporativoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *         
 */
public interface MantenimientoLETProgramaCorporativoService extends Service{
	
	/**
	 * Mtodo que lista los Programas Corporativos
	 * @param criteria
	 * @return
	 */
	public List getProgramaCorporativoList(Map criteria);	
	
	/**
	 * Devuelve el siguiente codigo del Programa Corporativo
	 * @return
	 */
	public String getNextCodigoProgramaCorporativo();
	
	/**
	 * Devuelve los tipos de Nivel de Exito
	 * @return
	 */
	public List getTipoNivelExito();
	
	/**
	 * Mtodo que inserta Programa Corporativo
	 * @param criteria
	 */
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario) throws Exception;
	
	/**
	 * Mtodo que actualiza Programa Corporativo
	 * @param criteria
	 */
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario);
	
	/**
	 * Mtodo que elimina logicamente Programa Corporativo. Estado 9
	 * @param criteria
	 */
	public void deleteProgramaCorporativo(Map criteria);
	
	public List getRangoNivelLET(Map parametros);
    
    public List getRangoRetencionLET(Map parametros);
    
    public List getTramosLET(Map parametros);
    
    public List getPremiosLET(Map parametros);
    
    public String getExisteObjetivosPedidosPorPrograma(Map parametros);
    
    public String getExisteProgramaCampanaFinNulo(Map parametros);
    
    public String getMaximaCampanaFinPrograma();
    
    public List getPremiosPorCampanaList(Map criteria);
    
    public String getNivelesCalculadosByCampanha(Map criteria);
}