/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EstructuraCOBCargarCronograma;

/**
 * @author Carlos Bazalar
 *
 */
public interface ProcesoCOBCargarCronogramaDAO extends DAO {

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
	public void executeProcesarCargarCronograma(EstructuraCOBCargarCronograma estructuraCOBCargarCronograma);
	
	/**
	 * Obtener path upload estandar.
	 *
	 * @param datos the datos
	 * @return the string
	 */
	public String obtenerPathUploadEstandar(Map datos);
	
}
