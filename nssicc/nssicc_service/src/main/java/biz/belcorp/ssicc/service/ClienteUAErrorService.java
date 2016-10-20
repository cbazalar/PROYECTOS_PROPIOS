/*
 * Created on 03-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAError;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAErrorPK;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAErrorService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ClienteUAErrorService extends Service {


	/**
	 * Registra la informacion de un nuevo cliente.
	 * 
	 * @param cliente
	 * Datos del cliente a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void insertClienteUAError(ClienteUAError cliente, Usuario usuario);
	
    
    /**
     * Registra la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a insertar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public void updateClienteUAError(ClienteUAError cliente, Usuario usuario);
    
    
    /**
     * Registra la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a insertar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public int selectClienteUAError(ClienteUAError cliente);
    
    
    
    
	/**
	 * Obtiene los datos de un cliente en base a su llaer primaria.
	 * 
	 * @param pk
	 * Llave primaria del cliente.
	 * 
	 * @return
	 * Objeto de tipo ClienteUAError, poblado.
	 */
	public ClienteUAError getClienteUAError(ClienteUAErrorPK pk);
	
	/**
	 * Obtiene una lista de clientes en base a un criterio de busqeda.
	 * 
	 * @param criteria
	 * Criterio de busqueda.
	 * 
	 * @return
	 * Lista de objetos de tipo ClienteUAError, poblados.
	 */
	public List getClientesUAErrorByCriteria(Map criteria);
    
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public byte[] getBytesReporteClientesErroneos(Map params, Usuario usuario, Pais pais);
    
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public byte[] getBytesReporteDireccionConsultoras(Map params, Usuario usuario, Pais pais);
    
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public byte[] getBytesReporteCOMPagoLideres(Map params, Usuario usuario, Pais pais);
   
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public byte[] getBytesReporteCOMLideresNuevas(Map params, Usuario usuario, Pais pais);
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public List getListaReporteDireccionConsultoras(Map params, Usuario usuario, Pais pais);
    /**
     * Obtiene un arreglo de bytes del reporte.
     * 
     * @param params
     * Parametros para extraer el reporte.
     * 
     * @param usuario
     * Usuario quein ejecuta la accion.
     * 
     * @param pais
     * Pais al que pertenece el usuario.
     * 
     * @return
     * Array de tipo byte.
     */
    public List getListaReporteClientesErroneos(Map params, Usuario usuario, Pais pais);
    
    public List getLideresNuevasByCriteria(Map criteria);
    
}
