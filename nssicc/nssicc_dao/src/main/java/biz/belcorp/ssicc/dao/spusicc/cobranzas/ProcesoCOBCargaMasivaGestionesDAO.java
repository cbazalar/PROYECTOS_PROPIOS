/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargaMasivaGestiones;

/**
 * @author Gonzalo Huertas
 *
 */
public interface ProcesoCOBCargaMasivaGestionesDAO extends DAO {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	
	/**
	 * Trae Lista de Tipos de Etapa
	 * @param datos
	 * @return
	 */
	public List getTiposEtapa(Map datos);
	
	/**
	 * Metodo que valida si existe Regsitro en la tabla cob_crono_carte
	 * @param criteria
	 * @return
	 */
	public Integer getValidarRegistroCargarCronograma(Map criteria);
	
	/**
	 * Metodo que valida si cumple con poder acturalizar registro
	 * @param criteria
	 */
	public Integer getValidarCargarCronograma(Map criteria);
	
	
	/**
	 * Devuelve descripcion de Etapa
	 * @param criteria
	 * @return
	 */
	public String getDevuelveDescripcionEtapa(Map criteria);
	
		
	/**
	 * Metodo que valida la estructura y data del archivo
	 * @EstructuraCOBCargarCronograma estructuraCOBCargarCronograma
	 */
	public String executeProcesarCargaMasivaGestiones(Map criteria);
	
	/**
	 * Metodo que valida si existe el cliente
	 * @param criteria
	 * @return
	 */
	public Integer getValidarCodigoCliente(Map criteria);
	
	/**
	 * Metodo que valida si existe el codido de etapa, accion o el tipo de la accion
	 * @param criteria
	 * @return
	 */
	public Integer getValidarCodigoEtapaAccion(Map criteria);
	
	/**
	 * Metodo que devuelve el tipo de cobrador
	 * @param criteria
	 * @return
	 */
	public Integer getValidarCodigoCobrador(Map criteria);
	
	/**
	 * Inserta registros en la tabla temporal
	 * @param estructura
	 */
	public void insertCargaMasivaGestiones(EstructuraCOBCargaMasivaGestiones estructura);
	
	/**
	 *Elimina la tabla temporal de Carga Masiva Gestiones 
	 */
	public void deleteTemporalCargaMasivaGestiones();
	
}
