package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.let.model.Premios;
import biz.belcorp.ssicc.dao.spusicc.let.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoNivel;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoRetencion;
import biz.belcorp.ssicc.dao.spusicc.let.model.Tramos;

/**
 * <p>
 * <a href="MantenimientoLETProgramaCorporativoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *         
 */
public interface MantenimientoLETProgramaCorporativoDAO extends DAO {
	
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
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario);
	
	public void insertRangoNivel(RangoNivel rangoNivel, Usuario usuario);
	
	public void insertRangoRetencion(RangoRetencion rangoRetencion, Usuario usuario);
	
	public void insertTramos(Tramos tramos, Usuario usuario);
	
	public void insertPremios(Premios premios, Usuario usuario);
		
	/**
	 * Mtodo que actualiza Programa Corporativo
	 * @param criteria
	 */
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario);
	
	public void updateRangoNivel(RangoNivel rangoNivel, Usuario usuario);
	
	public void updateRangoRetencion(RangoRetencion rangoRetencion, Usuario usuario);
	
	public void updateTramos(Tramos tramos, Usuario usuario);
	
	public void updatePremios(Premios premios, Usuario usuario);
	
	/**
	 * Mtodo que elimina logicamente la entidad Programa Corporativo. Estado 9
	 * @param criteria
	 */
	public void deleteProgramaCorporativo(Map criteria);
	
	public void deleteRangoNivel(Map criteria);
	
	public void deleteRangoRetencion(Map criteria);
	
	public void deleteTramos(Map criteria);
	
	public void deletePremios(Map criteria);
	
	
	public List getRangoNivelLET(Map parametros);
    
    public List getRangoRetencionLET(Map parametros);
    
    public List getTramosLET(Map parametros);
    
    public List getPremiosLET(Map parametros);
    
    /**
	 * @return
	 * Devuelve la descripcion del CUV
	 */
	public String getBuscarCUV(Map criteria);
	
	public String getExisteObjetivosPedidosPorPrograma(Map parametros);
	
	public String getExisteProgramaCampanaFinNulo(Map parametros);
	
	public String getMaximaCampanaFinPrograma();
	
	public List getPremiosPorCampanaList(Map criteria);
	
	public String getNivelesCalculadosByCampanha(Map criteria);
}