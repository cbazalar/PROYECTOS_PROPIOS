/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBGenericoDAO;
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
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 *
 */
@Service("spusicc.mantenimientoCOBGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOBGenericoServiceImpl extends BaseService 
    implements MantenimientoCOBGenericoService {
	
	@Resource(name="spusicc.mantenimientoCOBGenericoDAO")
	MantenimientoCOBGenericoDAO mantenimientoCOBGenericoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaEtapaDeuda(EtapaDeuda bean) {
		return this.mantenimientoCOBGenericoDAO.getListaEtapaDeuda(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public EtapaDeuda getEtapaDeuda(EtapaDeuda bean) {
		return this.mantenimientoCOBGenericoDAO.getEtapaDeuda(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEtapaDeuda(EtapaDeuda bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertEtapaDeuda(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEtapaDeuda(EtapaDeuda bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateEtapaDeuda(bean, usuario);
	}

	public void deleteEtapaDeuda(EtapaDeuda bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteEtapaDeuda(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaCobradorPais(CobradorPais bean) {
		return this.mantenimientoCOBGenericoDAO.getListaCobradorPais(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public CobradorPais getCobradorPais(CobradorPais bean) {
		return this.mantenimientoCOBGenericoDAO.getCobradorPais(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCobradorPais(CobradorPais bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertCobradorPais(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCobradorPais(CobradorPais bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateCobradorPais(bean, usuario);
	}

	public void deleteCobradorPais(CobradorPais bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteCobradorPais(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean) {
		return this.mantenimientoCOBGenericoDAO.getListaCobradorUnidadAdministrativa(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public CobradorUnidadAdministrativa getCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean) {
		return this.mantenimientoCOBGenericoDAO.getCobradorUnidadAdministrativa(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertCobradorUnidadAdministrativa(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCobradorUnidadAdministrativa(Map params) {
		this.mantenimientoCOBGenericoDAO.updateCobradorUnidadAdministrativa(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteCobradorUnidadAdministrativa(biz.belcorp.ssicc.spusicc.cobranzas.model.CobradorUnidadAdministrativa, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteCobradorUnidadAdministrativa(CobradorUnidadAdministrativa bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteCobradorUnidadAdministrativa(bean, usuario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaZonaNoCritica(ZonaNoCritica bean) {
		return this.mantenimientoCOBGenericoDAO.getListaZonaNoCritica(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public ZonaNoCritica getZonaNoCritica(ZonaNoCritica bean) {
		return this.mantenimientoCOBGenericoDAO.getZonaNoCritica(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertZonaNoCritica(ZonaNoCritica bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertZonaNoCritica(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateZonaNoCritica(ZonaNoCritica bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateZonaNoCritica(bean, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteZonaNoCritica(biz.belcorp.ssicc.spusicc.cobranzas.model.ZonaNoCritica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteZonaNoCritica(ZonaNoCritica bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteZonaNoCritica(bean, usuario);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaAsignacionCarteraCobrador(AsignacionCarteraCobrador bean) {
		return this.mantenimientoCOBGenericoDAO.getListaAsignacionCarteraCobrador(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public AsignacionCarteraCobrador getAsignacionCarteraCobrador(AsignacionCarteraCobrador bean) {
		return this.mantenimientoCOBGenericoDAO.getAsignacionCarteraCobrador(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAsignacionCarteraCobrador(AsignacionCarteraCobrador bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertAsignacionCarteraCobrador(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAsignacionCarteraCobrador(Map params) {
		this.mantenimientoCOBGenericoDAO.updateAsignacionCarteraCobrador(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteAsignacionCarteraCobrador(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraCobrador, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteAsignacionCarteraCobrador(AsignacionCarteraCobrador bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteAsignacionCarteraCobrador(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaValidacionAsignacionCartera(ValidacionAsignacionCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getListaValidacionAsignacionCartera(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public ValidacionAsignacionCartera getValidacionAsignacionCartera(ValidacionAsignacionCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getValidacionAsignacionCartera(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertValidacionAsignacionCartera(ValidacionAsignacionCartera bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertValidacionAsignacionCartera(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateValidacionAsignacionCartera(bean, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteValidacionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ValidacionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteValidacionAsignacionCartera(ValidacionAsignacionCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteValidacionAsignacionCartera(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getListaExcepcionAsignacionCartera(bean);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public ExcepcionAsignacionCartera getExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getExcepcionAsignacionCartera(bean);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertExcepcionAsignacionCartera(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateExcepcionAsignacionCartera(bean, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteExcepcionAsignacionCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionAsignacionCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteExcepcionAsignacionCartera(ExcepcionAsignacionCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteExcepcionAsignacionCartera(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaGenerico(biz.belcorp.ssicc.spusicc.cobranzas.model.Generico)
	 */
	public List getListaCarteraAsignada(Map datos) {
		return this.mantenimientoCOBGenericoDAO.getListaCarteraAsignada(datos);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#executeDepurarCarteraAsignada(java.util.Map)
	 */
	public void executeDepurarCarteraAsignada(Map datos) {
		this.mantenimientoCOBGenericoDAO.executeDepurarCarteraAsignada(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#executeEliminarCarteraAsignada(java.util.Map)
	 */
	public void executeEliminarCarteraAsignada(Map datos) {
		this.mantenimientoCOBGenericoDAO.executeEliminarCarteraAsignada(datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente)
	 */
	public List getListaExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean) {
		return this.mantenimientoCOBGenericoDAO.getListaExcepcionClasificacionCliente(bean);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente)
	 */
	public ExcepcionClasificacionCliente getExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean) {
		return this.mantenimientoCOBGenericoDAO.getExcepcionClasificacionCliente(bean);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertExcepcionClasificacionCliente(bean, usuario);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateExcepcionClasificacionCliente(java.util.Map)
	 */
	public void updateExcepcionClasificacionCliente(Map params) {
		this.mantenimientoCOBGenericoDAO.updateExcepcionClasificacionCliente(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteExcepcionClasificacionCliente(biz.belcorp.ssicc.spusicc.cobranzas.model.ExcepcionClasificacionCliente, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteExcepcionClasificacionCliente(ExcepcionClasificacionCliente bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteExcepcionClasificacionCliente(bean, usuario);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera)
	 */
	public List getListaUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getListaUnidadAdministrativaCronogramaCartera(bean);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera)
	 */
	public UnidadAdministrativaCronogramaCartera getUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean) {
		return this.mantenimientoCOBGenericoDAO.getUnidadAdministrativaCronogramaCartera(bean);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertUnidadAdministrativaCronogramaCartera(bean, usuario);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateUnidadAdministrativaCronogramaCartera(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteUnidadAdministrativaCronogramaCartera(biz.belcorp.ssicc.spusicc.cobranzas.model.UnidadAdministrativaCronogramaCartera, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteUnidadAdministrativaCronogramaCartera(UnidadAdministrativaCronogramaCartera bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteUnidadAdministrativaCronogramaCartera(bean, usuario);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getListaAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor)
	 */
	public List getListaAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean) {
		return this.mantenimientoCOBGenericoDAO.getListaAsignacionCarteraSupervisor(bean);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#getAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor)
	 */
	public AsignacionCarteraSupervisor getAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean) {
		return this.mantenimientoCOBGenericoDAO.getAsignacionCarteraSupervisor(bean);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#insertAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean,Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertAsignacionCarteraSupervisor(bean, usuario);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#updateAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAsignacionCarteraSupervisor(Map params) {
		this.mantenimientoCOBGenericoDAO.updateAsignacionCarteraSupervisor(params);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCOBGenericoService#deleteAsignacionCarteraSupervisor(biz.belcorp.ssicc.spusicc.cobranzas.model.AsignacionCarteraSupervisor, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteAsignacionCarteraSupervisor(AsignacionCarteraSupervisor bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteAsignacionCarteraSupervisor(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#getParametrosGeneralesCobList(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB)
	 */
	public List getParametrosGeneralesCobList(ParametrosGeneralesCOB bean) {
		return this.mantenimientoCOBGenericoDAO.getParametrosGeneralesCobList(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#getParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB)
	 */
	public ParametrosGeneralesCOB getParametrosGeneralesCob(ParametrosGeneralesCOB bean) {		
		return this.mantenimientoCOBGenericoDAO.getParametrosGeneralesCob(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#insertParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertParametrosGeneralesCob(bean, usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#updateParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateParametrosGeneralesCob(bean, usuario);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#deleteParametrosGeneralesCob(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ParametrosGeneralesCOB, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteParametrosGeneralesCob(ParametrosGeneralesCOB bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteParametrosGeneralesCob(bean, usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#getSeccionNoCriticaList(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica)
	 */
	public List getSeccionNoCriticaList(SeccionNoCritica bean) {
		return this.mantenimientoCOBGenericoDAO.getSeccionNoCriticaList(bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#getSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica)
	 */
	public SeccionNoCritica getSeccionNoCritica(SeccionNoCritica bean) {
		return this.mantenimientoCOBGenericoDAO.getSeccionNoCritica(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#insertSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.insertSeccionNoCritica(bean, usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#updateSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.updateSeccionNoCritica(bean, usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService#deleteSeccionNoCritica(biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteSeccionNoCritica(SeccionNoCritica bean, Usuario usuario) {
		this.mantenimientoCOBGenericoDAO.deleteSeccionNoCritica(bean, usuario);
		
	}
	
	


}