/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.flx.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.MantenimientoFLXConsultoraDAO;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoObjetada;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;

/**
 * @author Juan Altamirano
 * 
 */
@Service("spusicc.mantenimientoFLXConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoFLXConsultoraServiceImpl extends BaseService
		implements MantenimientoFLXConsultoraService {

	@Resource(name = "spusicc.mantenimientoFLXConsultoraDAO")
	private MantenimientoFLXConsultoraDAO mantenimientoFLXConsultoraDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getConsultorasByCriteria(java.util.Map)
	 */
	public List getConsultorasByCriteria(Map criteria) {
		return mantenimientoFLXConsultoraDAO.getConsultorasByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getConsultora(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK)
	 */
	public ConsultoraFlexipago getConsultora(ConsultoraFlexipagoPK pk) {
		return mantenimientoFLXConsultoraDAO.getConsultora(pk);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#updateConsultora(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConsultora(ConsultoraFlexipago consultora, Usuario usuario) {

		try
		{
			mantenimientoFLXConsultoraDAO.updateConsultora(consultora, usuario);

			// Por cada flag Activo y Habilitado se hace un insert
			ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();
			BeanUtils.copyProperties(audit, consultora);
			audit.setFechaAccion(consultora.getAuditInfo().getLastUpdated());
			audit.setUsuarioAccion(consultora.getAuditInfo().getUpdatedBy());
			audit.setCampanyaFacturacion(consultora.getCodigoCampanyaFacturacion());

			if(StringUtils.equals(consultora.getFlagActivo(), Constants.NUMERO_UNO))
				audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_ACTIVAR);
			else
				audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_DESACTIVAR);

			mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);
			/*
			if(StringUtils.equals(consultora.getFlagHabilitado(), Constants.NUMERO_UNO))
				audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_REGISTRAR_OBJECION);
			else
				audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_ANULAR_OBJECION);

			mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);
            */
			//
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getCalificacionesComportamiento()
	 */
	public List getCalificacionesComportamiento() {
		return mantenimientoFLXConsultoraDAO.getCalificacionesComportamiento();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getCalificacionesExperiencia()
	 */
	public List getCalificacionesExperiencia() {
		return mantenimientoFLXConsultoraDAO.getCalificacionesExperiencia();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getConsultoraAuditoriaByCodigosAccion(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ConsultoraFlexipagoAuditoria getConsultoraAuditoriaByCodigosAccion(
			String codigoPais, String codigoCliente, String codigoAccion1,
			String codigoAccion2) {
		return mantenimientoFLXConsultoraDAO.getConsultoraAuditoriaByCodigosAccion(codigoPais, codigoCliente, codigoAccion1, codigoAccion2);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#validarCodigoConsultora(java.lang.String)
	 */
	public String validarCodigoConsultora(String codigoConsultora) {
		String codigo = mantenimientoFLXConsultoraDAO.getCodigoConsultora(codigoConsultora);

		return (codigo == null) ? Constants.NO : Constants.SI;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#updateMasivoConsultoras(java.util.List, java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMasivoConsultoras(List listaConsultoras,
			String codigoAccion, Usuario usuario) {

		try		
		{			

			for(int i=0; i<listaConsultoras.size(); i++)
			{
				ConsultoraFlexipago c = (ConsultoraFlexipago)listaConsultoras.get(i);

				ConsultoraFlexipago consultora = mantenimientoFLXConsultoraDAO.getConsultora(new ConsultoraFlexipagoPK(c.getCodigoPais(), c.getCodigoCliente(), c.getCodigoCampanyaFacturacion()));
				
				if(consultora != null && StringUtils.equals(c.getFlagActivo(), Constants.SI))
				{
					if(StringUtils.equals(codigoAccion, Constants.FLX_CODIGO_ACCION_ACTIVAR))
					{
						consultora.setFlagActivo(Constants.NUMERO_UNO);
						consultora.setFlagCancelado(Constants.NUMERO_CERO);
					}
					else if(StringUtils.equals(codigoAccion, Constants.FLX_CODIGO_ACCION_DESACTIVAR))
					{
						
						consultora.setFlagActivo(Constants.NUMERO_CERO);
						consultora.setFlagCancelado(Constants.NUMERO_UNO);						
						
					}
					else if(StringUtils.equals(codigoAccion, Constants.FLX_CODIGO_ACCION_REGISTRAR_OBJECION))
					{
						consultora.setFlagActivo(Constants.NUMERO_CERO);
						consultora.setFlagHabilitado(Constants.NUMERO_CERO);
					}
					else if(StringUtils.equals(codigoAccion, Constants.FLX_CODIGO_ACCION_ANULAR_OBJECION))
					{
						consultora.setFlagHabilitado(Constants.NUMERO_UNO);
					}

					mantenimientoFLXConsultoraDAO.updateConsultora(consultora, usuario);

					ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();
					BeanUtils.copyProperties(audit, consultora);
					audit.setFechaAccion(consultora.getAuditInfo().getLastUpdated());
					audit.setUsuarioAccion(consultora.getAuditInfo().getUpdatedBy());
					audit.setCodigoAccion(codigoAccion);
					audit.setCampanyaFacturacion(consultora.getCodigoCampanyaFacturacion());

					mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);

				}
			}
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
		}
	}

	public List getConsultorasObjetadaByCriteria(Map criteria) {

		return mantenimientoFLXConsultoraDAO.getConsultorasObjetadaByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#insertConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoObjetada, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConsultoraObjetada(
			ConsultoraFlexipagoObjetada consultora, Usuario usuario) {
		mantenimientoFLXConsultoraDAO.insertConsultoraObjetada(consultora, usuario);

		ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();

		audit.setCodigoPais(consultora.getCodigoPais());
		audit.setCodigoCliente(consultora.getCodigoCliente());
		audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_REGISTRAR_OBJECION);
		audit.setCampanyaFacturacion(consultora.getCodigoCampanyaFacturacion());
		audit.setFechaAccion(consultora.getAuditInfo().getCreated());
		audit.setUsuarioAccion(consultora.getAuditInfo().getCreatedBy());

		mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#updateMasivoConsultorasObjetadas(java.util.List, java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMasivoConsultorasObjetadas(List listaConsultoras,
			String codigoAccion, Usuario usuario) {
		try
		{
			for(int i=0; i<listaConsultoras.size(); i++)
			{
				ConsultoraFlexipago c = (ConsultoraFlexipago)listaConsultoras.get(i);

				if(StringUtils.equals(c.getFlagActivo(), Constants.SI))
				{

					ConsultoraFlexipagoObjetada consultora = mantenimientoFLXConsultoraDAO.getConsultoraObjetada(new ConsultoraFlexipagoPK(c.getCodigoPais(), c.getCodigoCliente(), c.getCodigoCampanyaFacturacion()));

					if(consultora != null)
					{
						mantenimientoFLXConsultoraDAO.updateConsultoraObjetada(consultora, usuario);

						ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();
						BeanUtils.copyProperties(audit, consultora);
						audit.setFechaAccion(consultora.getAuditInfo().getLastUpdated());
						audit.setUsuarioAccion(consultora.getAuditInfo().getUpdatedBy());
						audit.setCodigoAccion(codigoAccion);
						audit.setCampanyaFacturacion(consultora.getCodigoCampanyaFacturacion());

						mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);					
					}
					else
					{
						consultora = new ConsultoraFlexipagoObjetada();

						BeanUtils.copyProperties(consultora, c);
						mantenimientoFLXConsultoraDAO.insertConsultoraObjetada(consultora, usuario);

						ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();

						audit.setCodigoPais(consultora.getCodigoPais());
						audit.setCodigoCliente(consultora.getCodigoCliente());
						audit.setCodigoAccion(codigoAccion);
						audit.setCampanyaFacturacion(consultora.getCodigoCampanyaFacturacion());
						audit.setFechaAccion(consultora.getAuditInfo().getCreated());
						audit.setUsuarioAccion(consultora.getAuditInfo().getCreatedBy());

						mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);
					}
				}
			}
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK)
	 */
	public ConsultoraFlexipagoObjetada getConsultoraObjetada(
			ConsultoraFlexipagoPK pk) {
		return mantenimientoFLXConsultoraDAO.getConsultoraObjetada(pk);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#updateConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoObjetada, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConsultoraObjetada(
			ConsultoraFlexipagoObjetada consultora, Usuario usuario) {
		mantenimientoFLXConsultoraDAO.updateConsultoraObjetada(consultora, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#deleteConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoObjetada, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteConsultoraObjetada(
			ConsultoraFlexipagoObjetada consultora, Usuario usuario) {
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#deleteConsultoraObjetada(biz.belcorp.ssicc.spusicc.flx.dao.model.ConsultoraFlexipagoPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteConsultoraObjetada(ConsultoraFlexipagoPK pk,
			Usuario usuario) {

		mantenimientoFLXConsultoraDAO.deleteConsultoraObjetada(pk, usuario);

		ConsultoraFlexipagoAuditoria audit = new ConsultoraFlexipagoAuditoria();

		audit.setCodigoPais(pk.getCodigoPais());
		audit.setCodigoCliente(pk.getCodigoCliente());
		audit.setCodigoAccion(Constants.FLX_CODIGO_ACCION_ANULAR_OBJECION);
		audit.setCampanyaFacturacion(pk.getCodigoCampanyaFacturacion());
		audit.setFechaAccion(new Timestamp(System.currentTimeMillis()));
		audit.setUsuarioAccion(usuario.getLogin());

		mantenimientoFLXConsultoraDAO.insertAuditoriaConsultora(audit);
	}

	public List getDatosConsultora(Map criteria) {
		return mantenimientoFLXConsultoraDAO.getDatosConsultora(criteria);
	}

	public List getDatosConsultoraDetalle(Map criteria) {
		return mantenimientoFLXConsultoraDAO.getDatosConsultoraDetalle(criteria);
	}
	
	public List getDatosUsosFlexipago(Map criteria){
		return mantenimientoFLXConsultoraDAO.getDatosUsosFlexipago(criteria);
	}

	public void executeReporteDetalleSaldo(Map criteria){
		mantenimientoFLXConsultoraDAO.executeReporteDetalleSaldo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getMotivos()
	 */
	public List getMotivos() {
		return mantenimientoFLXConsultoraDAO.getMotivos();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getPasoPedido(java.util.Map)
	 */
	public String getPasoPedido(Map criteria){
		return mantenimientoFLXConsultoraDAO.getPasoPedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flx.service.MantenimientoFLXConsultoraService#getCerroRegion(java.util.Map)
	 */
	public String getCerroRegion(Map criteria){
		return mantenimientoFLXConsultoraDAO.getCerroRegion(criteria);
	}
	
	public String getCodigoConsultoraHabil(String codigoConsultora) {
		String codigo = (String) mantenimientoFLXConsultoraDAO.getCodigoConsultoraHabil(codigoConsultora);
		 return (codigo == null)?Constants.NO:Constants.SI;
	}
	
	public String getContratoConsultoraHabil(Map criteria){
		String codigo =  mantenimientoFLXConsultoraDAO.getContratoConsultoraHabil(criteria);
		return (codigo == null)?Constants.NO:Constants.SI;
	}
	
	public String getConsultoraActiva(ConsultoraFlexipago c){
		String codigo =  mantenimientoFLXConsultoraDAO.getConsultoraActiva(c);
		return (codigo == null)?Constants.NO:Constants.SI;
	}
	
	public void updateEnvioContrato(List listaConsultoras){
		try
		{
			for(int i=0; i<listaConsultoras.size(); i++)
			{
				ConsultoraFlexipago c = (ConsultoraFlexipago)listaConsultoras.get(i);
				
				if( (StringUtils.equals(c.getFlagActivo(), Constants.SI)) && (StringUtils.equals(getConsultoraActiva(c),Constants.SI)) )
					
					mantenimientoFLXConsultoraDAO.updateEnvioContrato(c);
			}
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
		}
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#getInteresFlexipago()
	 */
	public List getInteresFlexipagoList() {
		return mantenimientoFLXConsultoraDAO.getInteresFlexipagoList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#getInteresFlexipago(biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago)
	 */
	public InteresFlexipago getInteresFlexipago(InteresFlexipago bean) {		
		return mantenimientoFLXConsultoraDAO.getInteresFlexipago(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#insertInteresFlexipago(biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertInteresFlexipago(InteresFlexipago bean, Usuario usuario) {
		mantenimientoFLXConsultoraDAO.insertInteresFlexipago(bean, usuario);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#deleteInteresesFlexipago(biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteInteresesFlexipago(InteresFlexipago bean, Usuario usuario) {
		mantenimientoFLXConsultoraDAO.deleteInteresesFlexipago(bean, usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#deleteTotalInteresesFlexipago()
	 */
	public void deleteTotalInteresesFlexipago() {
		mantenimientoFLXConsultoraDAO.deleteTotalInteresesFlexipago();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#getRangoMaxHasta()
	 */
	public String getRangoMaxHasta() {		
		return mantenimientoFLXConsultoraDAO.getRangoMaxHasta();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService#getCantDecimalesxPais(java.util.Map)
	 */
	public int getCantDecimalesxPais(Map criteria) {		
		return mantenimientoFLXConsultoraDAO.getCantDecimalesxPais(criteria);
	}

	
	
		
}
