package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MensajeValidacionSTO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionSTO;

/**
 * Interface de Validaciones de STO.
 * 
 * <p>
 * <a href="MantenimientoSTOValidacionesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */

public interface MantenimientoSTOValidacionesService extends Service {

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
	 * @param listaValidacionesSecuencia
	 */
	public void updateValidacionesSecuencia(List listaValidacionesSecuencia);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getValidacionListSTO(Map criteria);
	
	/**
	 * Elimina STO_PARAM_VALID, STO_PARAM_SECUE_VALID, STO_MENSA_VALID
	 * @param criteria
	 */
	public void deleteValidacionSTO(String codigoValidacion);
	
	/**
     * Obtiene la informaci�n correspondiente de ValidacionSTO. 
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
     * Obtiene la informaci�n correspondiente de MensajeValidacionSTO. 
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