/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
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

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 *
 */
public interface MantenimientoCOBGenericoDAO extends DAO {
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaEtapaDeuda(EtapaDeuda bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public EtapaDeuda getEtapaDeuda(EtapaDeuda bean);
	
	/**
	 * Crea un registro correspondiente al Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void insertEtapaDeuda(EtapaDeuda bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void updateEtapaDeuda(EtapaDeuda bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente al Etapa Deuda.
	 * @param bean
	 * @param usuario
	 */
	public void deleteEtapaDeuda(EtapaDeuda bean, Usuario usuario);
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaCobradorPais(CobradorPais bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public CobradorPais getCobradorPais(CobradorPais bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertCobradorPais(CobradorPais bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void updateCobradorPais(CobradorPais bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteCobradorPais(CobradorPais bean, Usuario usuario);
	
	
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public CobradorUnidadAdministrativa getCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param params
	 */
	public void updateCobradorUnidadAdministrativa(Map params);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean, Usuario usuario);
	
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaZonaNoCritica(ZonaNoCritica bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public ZonaNoCritica getZonaNoCritica(ZonaNoCritica bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertZonaNoCritica(ZonaNoCritica bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void updateZonaNoCritica(ZonaNoCritica bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteZonaNoCritica(ZonaNoCritica bean, Usuario usuario);
	
	
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaAsignacionCarteraCobrador(AsignacionCarteraCobrador bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public AsignacionCarteraCobrador getAsignacionCarteraCobrador(AsignacionCarteraCobrador bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertAsignacionCarteraCobrador(AsignacionCarteraCobrador bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param params
	 */
	public void updateAsignacionCarteraCobrador(Map params);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteAsignacionCarteraCobrador(AsignacionCarteraCobrador bean, Usuario usuario);
	
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaValidacionAsignacionCartera(ValidacionAsignacionCartera bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public ValidacionAsignacionCartera getValidacionAsignacionCartera(ValidacionAsignacionCartera bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertValidacionAsignacionCartera(ValidacionAsignacionCartera bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void updateValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario);
	
	/**
     * Obtiene lista con información correspondiente a los Cobradores del Pais. 
     * @param bean
     * @return
     */
    public List getListaExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean);
    
	/**
	 * Obtiene información correspondiente del Cobrador Pais 
	 * @param bean
	 * @return
	 */
	public ExcepcionAsignacionCartera getExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean);
	
	/**
	 * Crea un registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void insertExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean,Usuario usuario);
	
	/**
	 * Actualiza registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void updateExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario);
	
	/**
	 * Elimina un  registro correspondiente al Cobrador Pais.
	 * @param bean
	 * @param usuario
	 */
	public void deleteExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario);
	
	/**
     * Obtiene lista con información correspondiente a las Carteras Asignadas 
     * @param bean
     * @return
     */
    public List getListaCarteraAsignada(Map datos);
    
    /**
	 * Depura la Cartera Asignada
	 * @param bean
	 * @param usuario
	 */
	public void executeDepurarCarteraAsignada(Map datos);
	
	/**
	 * Elimina la Cartera Asignada
	 * @param bean
	 * @param usuario
	 */
	public void executeEliminarCarteraAsignada(Map datos);
			
    /**
     * @param bean
     * @return
     */
    public List getListaExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean);
    	
	/**
	 * @param bean
	 * @return
	 */
	public ExcepcionClasificacionCliente getExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void insertExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean,Usuario usuario);
		
	/**
	 * @param params
	 */
	public void updateExcepcionClasificacionCliente(Map params);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void deleteExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean, Usuario usuario);
		
	/**
	 * @param bean
	 * @return
	 */
	public List getListaUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean);
    	
	/**
	 * @param bean
	 * @return
	 */
	public UnidadAdministrativaCronogramaCartera getUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void insertUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean,Usuario usuario);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void updateUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void deleteUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario);
		
	/**
	 * @param bean
	 * @return
	 */
	public List getListaAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean);
    	
	/**
	 * @param bean
	 * @return
	 */
	public AsignacionCarteraSupervisor getAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean);
		
	/**
	 * @param bean
	 * @param usuario
	 */
	public void insertAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean,Usuario usuario);
		
	/**
	 * @param params
	 */
	public void updateAsignacionCarteraSupervisor(Map params);
		
	/**
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
	
	public List getSeccionNoCriticaList(SeccionNoCritica bean);
	
	public SeccionNoCritica getSeccionNoCritica(SeccionNoCritica bean);
	
	public void insertSeccionNoCritica(SeccionNoCritica bean,Usuario usuario);
	
	public void updateSeccionNoCritica(SeccionNoCritica bean, Usuario usuario);
	
	public void deleteSeccionNoCritica(SeccionNoCritica bean, Usuario usuario);

}