/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.AsignacionCarteraCobrador;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.AsignacionCarteraSupervisor;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorPais;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CobradorUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.EtapaDeuda;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ExcepcionAsignacionCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ExcepcionClasificacionCliente;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ValidacionAsignacionCartera;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaNoCritica;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 *
 */
public interface MantenimientoCOBGenericoService extends Service {
    
	/**
     * Obtiene lista con los Etapas de Deuda. 
     * @param bean
     * @return
     */
    public List getListaEtapaDeuda(EtapaDeuda bean);
    
    /**
     * Obtiene la información correspondiente de Etapa Deuda.
     * @param bean
     * @return
     */
    public EtapaDeuda getEtapaDeuda(EtapaDeuda bean);
	
    /**
	 * Crea un  registro de Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void insertEtapaDeuda(EtapaDeuda bean,Usuario usuario);
	
	/**
	 * Actualiza registro de Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void updateEtapaDeuda(EtapaDeuda bean, Usuario usuario);
	
	/**
	 * Elimina registro de Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void deleteEtapaDeuda(EtapaDeuda bean, Usuario usuario);
	
    /**
     * Obtiene lista con los Cobradores Pais. 
     * @param bean
     * @return
     */
    public List getListaCobradorPais(CobradorPais bean);
	
    /**
     * Obtiene la información correspondiente de Cobrador Pais. 
     * @param bean
     * @return
     */
    public CobradorPais getCobradorPais(CobradorPais bean);
	
    /**
	 * Crea un  registro de Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertCobradorPais(CobradorPais bean,Usuario usuario);
	
	/**
	 * Actualiza registro de Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void updateCobradorPais(CobradorPais bean, Usuario usuario);
	
	/**
	 * Elimina registro de Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteCobradorPais(CobradorPais bean, Usuario usuario);
	
	
    /**
     * Obtiene lista con los CobradorUnidadAdministrativa. 
     * @param bean
     * @return
     */
    public List getListaCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean);
	
    /**
     * Obtiene la información correspondiente de CobradorUnidadAdministrativa. 
     * @param bean
     * @return
     */
    public CobradorUnidadAdministrativa getCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean);
	
    /**
	 * Crea un  registro de CobradorUnidadAdministrativa.
	 * @param bean
	 * @param usuario
	 */
	public void insertCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean,Usuario usuario);
	
	/**
	 * Actualiza registro de CobradorUnidadAdministrativa.
	 * @param params
	 */
	public void updateCobradorUnidadAdministrativa(Map params);
	
	/**
	 * Elimina registro de CobradorUnidadAdministrativa.
	 * @param bean
	 * @param usuario
	 */
	public void deleteCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean, Usuario usuario);
	
	
	/**                                                                          
     * Obtiene lista con los ZonaNoCritica.                                  
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaZonaNoCritica(ZonaNoCritica bean);                   
	                                                                     
    /**                                                                      
     * Obtiene la información correspondiente de ZonaNoCritica.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public ZonaNoCritica getZonaNoCritica(ZonaNoCritica bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de ZonaNoCritica.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertZonaNoCritica(ZonaNoCritica bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de ZonaNoCritica.                              
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void updateZonaNoCritica(ZonaNoCritica bean, Usuario usuario);
	                                                                     
	/**                                                                  
	 * Elimina registro de ZonaNoCritica.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteZonaNoCritica(ZonaNoCritica bean, Usuario usuario);
	
	
	/**                                                                          
     * Obtiene lista con los AsignacionCarteraCobrador.                                  
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaAsignacionCarteraCobrador(AsignacionCarteraCobrador bean);                   
	                                                                     
    /**                                                                      
     * Obtiene la información correspondiente de AsignacionCarteraCobrador.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public AsignacionCarteraCobrador getAsignacionCarteraCobrador(AsignacionCarteraCobrador bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de AsignacionCarteraCobrador.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertAsignacionCarteraCobrador(AsignacionCarteraCobrador bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de AsignacionCarteraCobrador.                              
	 * @param params                                                                                                           
	 */                                                                  
	public void updateAsignacionCarteraCobrador(Map params);
	                                                                     
	/**                                                                  
	 * Elimina registro de AsignacionCarteraCobrador.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteAsignacionCarteraCobrador(AsignacionCarteraCobrador bean, Usuario usuario);
	
	/**                                                                          
     * Obtiene lista con las Validaciones de la Asignación de Cartera                              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaValidacionAsignacionCartera(ValidacionAsignacionCartera bean);    
    
    /**                                                                      
     * Obtiene la información correspondiente de ValidacionAsignacionCartera.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public ValidacionAsignacionCartera getValidacionAsignacionCartera(ValidacionAsignacionCartera bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de ValidacionAsignacionCartera.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertValidacionAsignacionCartera(ValidacionAsignacionCartera bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de ValidacionAsignacionCartera.                              
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void updateValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario);
	                                                                     
	/**                                                                  
	 * Elimina registro de ValidacionAsignacionCartera.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario);

	/**                                                                          
     * Obtiene lista con las Excepciones de la Asignación de Cartera                              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean);    
    
    /**                                                                      
     * Obtiene la información correspondiente de ExcepcionAsignacionCartera.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public ExcepcionAsignacionCartera getExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de ExcepcionAsignacionCartera.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de ExcepcionAsignacionCartera.                              
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void updateExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario);
	                                                                     
	/**                                                                  
	 * Elimina registro de ExcepcionAsignacionCartera.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario);
	
	/**                                                                          
     * Obtiene lista con las Carteas Asignadas                             
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaCarteraAsignada(Map datos);
    
    /**                                                                          
     * Depura la Cartera Asignada                             
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public void executeDepurarCarteraAsignada(Map datos);
    
    /**                                                                          
     * Elimina la Cartera Asignada                             
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public void executeEliminarCarteraAsignada(Map datos);
    
    /**                                                                          
     * Obtiene lista con las Excepciones de Clasificacion Cliente                            
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean);    
    
    /**                                                                      
     * Obtiene la información correspondiente de ExcepcionClasificacionCliente.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public ExcepcionClasificacionCliente getExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de ExcepcionClasificacionCliente.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de ExcepcionClasificacionCliente.                              
	 * @param params                                                  
	 */                                                                  
	public void updateExcepcionClasificacionCliente(Map params);
	                                                                     
	/**                                                                  
	 * Elimina registro de ExcepcionClasificacionCliente.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean, Usuario usuario);
	
	/**                                                                          
     * Obtiene lista con las Unidades Administrativas del Cornograma de Cartera                            
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean);    
    
    /**                                                                      
     * Obtiene la información correspondiente de UnidadAdministrativaCronogramaCartera.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public UnidadAdministrativaCronogramaCartera getUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de UnidadAdministrativaCronogramaCartera.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de UnidadAdministrativaCronogramaCartera.                              
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void updateUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario);
	                                                                     
	/**                                                                  
	 * Elimina registro de UnidadAdministrativaCronogramaCartera.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario);
	
	
	/**                                                                          
     * Obtiene lista con la Asignacion De Cartera Supervisor                         
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public List getListaAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean);    
    
    /**                                                                      
     * Obtiene la información correspondiente de AsignacionCarteraSupervisor.              
     * @param bean                                                           
     * @return                                                               
     */                                                                      
    public AsignacionCarteraSupervisor getAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean);               
	                                                                     
    /**                                                                      
	 * Crea un  registro de AsignacionCarteraSupervisor.                               
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void insertAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean,Usuario usuario); 
	                                                                     
	/**                                                                  
	 * Actualiza registro de AsignacionCarteraSupervisor.                              
	 * @param params                                                                                                          
	 */                                                                  
	public void updateAsignacionCarteraSupervisor(Map params);
	                                                                     
	/**                                                                  
	 * Elimina registro de AsignacionCarteraSupervisor.                                
	 * @param bean                                                       
	 * @param usuario                                                    
	 */                                                                  
	public void deleteAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean, Usuario usuario);
	
	/**
	 * Lista de Parametros Generales de Cobranza
	 * @param bean
	 * @param usuario
	 */	
	public List getParametrosGeneralesCobList(ParametrosGeneralesCOB bean);
	
	/**
	 * @param bean
	 * @return
	 */
	public ParametrosGeneralesCOB getParametrosGeneralesCob(ParametrosGeneralesCOB bean);
	
	/**
	 *Insertar Parametros Generales de Cobranza
	 * @param bean
	 * @param usuario
	 */			
	public void insertParametrosGeneralesCob(ParametrosGeneralesCOB bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente a Parametros Generales de Cobranza.
	 * @param bean
	 * @param usuario
	 */		
	public void updateParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente a Parametros Generales de Cobranza.
	 * @param bean
	 * @param usuario
	 */			
	public void deleteParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario);
	
	/**
	 * @param bean
	 * @return
	 */
	public List getSeccionNoCriticaList(SeccionNoCritica bean);
	
	/**
	 * @param bean
	 * @return
	 */
	public SeccionNoCritica getSeccionNoCritica(SeccionNoCritica bean);
	
	/**
	 * @param bean
	 * @param usuario
	 */
	public void insertSeccionNoCritica(SeccionNoCritica bean,Usuario usuario);
	
	/**
	 * @param bean
	 * @param usuario
	 */
	public void updateSeccionNoCritica(SeccionNoCritica bean, Usuario usuario);
	
	/**
	 * @param bean
	 * @param usuario
	 */
	public void deleteSeccionNoCritica(SeccionNoCritica bean, Usuario usuario);

}