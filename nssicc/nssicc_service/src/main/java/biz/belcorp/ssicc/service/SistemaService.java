/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface SistemaService extends Service {

	/**
	 * Obtiene toda la lista de sistemas.
	 * 
	 * @param sistema
	 * 
	 * @return
	 * Lista de todos los sistemas obtenidos.
	 */
	public List getSistemas(Sistema sistema);
	/**
	 * Retorna una lista de sistemas en base a los parametros a travez del map.
	 * 
	 * @param criteria
	 * Map donde se establcen los criterios para el filtro.
	 * 
	 * @return
	 * Lista de sistemas obtenidos segun el map.
	 */
	public List getSistemasByCriteria(Map criteria);
	
	public List getSistemasByCriteria2(Map criteria);
	
	/**
	 * Obtiene un sistema en base a su llave primaria.
	 * 
	 * @param primaryKey
	 * Llave primaria del sistema.
	 * 
	 * @return
	 * Sistema, objeto poblado de tipo Sistema. 
	 */
	public Sistema getSistema(final SistemaPK primaryKey);
	/**
	 * Registra la información de un nuevo sistema.
	 * 
	 * @param sistema
	 * Sistema a insertar.
	 * 
	 * @param usuario
	 * Usuario que esta insertando el sistema. 
	 */
	public void insertSistema(Sistema sistema, Usuario usuario);
	/**
	 * Actualiza la información de un sistema existente.
	 * 
	 * @param sistema
	 * Información del sistema a actualizar.
	 * 
	 * @param usuario
	 * Usuario que esta realizando la actualización.
	 */
	public void updateSistema(Sistema sistema, Usuario usuario);
	/**
	 * Elimina logicamente un sistema en base a su codigo.
	 * 
	 * @param primaryKey
	 * Llave primaria del sistema a eliminar.
	 * 
	 * @param usuario
	 * Usuario que esta eliminando el sistema.
	 */
	public void removeSistema(final SistemaPK primaryKey, Usuario usuario);
}
