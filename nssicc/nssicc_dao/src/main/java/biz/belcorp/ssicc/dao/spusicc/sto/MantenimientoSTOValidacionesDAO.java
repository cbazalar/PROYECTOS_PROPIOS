package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MensajeValidacionSTO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionSTO;

/**
 * <p>
 * <a href="MantenimientoSTOValidacionesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */

public interface MantenimientoSTOValidacionesDAO extends DAO {

	/**
	 * @param criteria
	 * @return
	 */
	public List getValidacionesSTO(Map criteria);
	
	/**
	 * Obtiene el siguiente codigo de validacion de acuerdo al Tipo Documento
	 * 
	 * @param tipoDocumento
	 * @return
	 */
	public String getNextCodigoValidacion(String tipoDocumento);
	
	/**
	 * Inserta la parametria de una validacion
	 * 
	 * @param criteria
	 */
	public void insertValidacionesParametria(Map criteria);
	
	/**
	 * Inserta los mensajes de una validacion
	 * 
	 * @param criteria
	 */
	public void insertValidacionesMensaje(Map criteria);
	
	/**
	 * Inserta la secuencia de una validacion
	 * 
	 * @param criteria
	 */
	public void insertValidacionesSecuencia(Map criteria);
	
	/**
	 * Obtiene la parametria y mensajes de la validacion 
	 * 
	 * @param codigoValidacion
	 * @return
	 */
	public List getValidacionesParametriaMensajeSTO(String codigoValidacion);
	
	/**
	 * Actualiza la parametria de una validacion
	 * 
	 * @param criteria
	 */
	public void updateValidacionesParametria(Map criteria);
	
	/**
	 * Actualiza los mensajes de una validacion
	 * 
	 * @param criteria
	 */
	public void updateValidacionesMensaje(Map criteria);
	
	/**
	 * Actualiza la secuencia de una validacion
	 * 
	 * @param criteria
	 */
	public void updateValidacionesSecuencia(Map criteria);	
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getValidacionListSTO(Map criteria);
	
	/**
	 * Elimina STO_PARAM_VALID
	 * @param criteria
	 */
	public void deleteParamValidSTO(String codigoValidacion);
	
	/**
	 * Elimina STO_PARAM_SECUE_VALID
	 * @param criteria
	 */
	public void deleteParamSecueValidSTO(String codigoValidacion);
	
	/**
	 * Elimina STO_MENSA_VALID
	 * @param criteria
	 */
	public void deleteMensaValidSTO(String codigoValidacion);
	
	/**
     * Obtiene la informacion correspondiente de ValidacionSTO. 
     * @param bean
     * @return
     */
    public ValidacionSTO getValidacionSTO(ValidacionSTO bean);
    
    /**
   	 * Inserta la ValidacionSTO
   	 * 
   	 * @param bean
   	 * @param usuario
   	 */
   	public void insertValidacionSTO(ValidacionSTO bean, Usuario usuario);
   	
   	/**
   	 * Actualiza la ValidacionSTO
   	 * 
   	 * @param bean
   	 * @param usuario
   	 */
   	public void updateValidacionSTO(ValidacionSTO bean, Usuario usuario);
   	
   	/**
	 * @param criteria
	 * @return
	 */
	public List getMensajeValidacionListSTO(Map criteria);
	
	/**
	 * Elimina STO_PARAM_SECUE_VALID, STO_MENSA_VALID
	 * @param criteria
	 */
	public void deleteMensajeValidacionSTO(Map criteria);
	
	/**
     * Obtiene la informacion correspondiente de MensajeValidacionSTO. 
     * @param bean
     * @return
     */
    public MensajeValidacionSTO getMensajeValidacionSTO(MensajeValidacionSTO bean);
    
    /**
	 * Inserta la MensajeValidacionSTO
	 * 
	 * @param bean
	 * @param usuario
	 */
	public void insertMensajeValidacionSTO(MensajeValidacionSTO bean, Usuario usuario);
	
	/**
	 * Actualiza la MensajeValidacionSTO
	 * 
	 * @param bean
	 * @param usuario
	 */
	public void updateMensajeValidacionSTO(MensajeValidacionSTO bean, Usuario usuario);
    
}