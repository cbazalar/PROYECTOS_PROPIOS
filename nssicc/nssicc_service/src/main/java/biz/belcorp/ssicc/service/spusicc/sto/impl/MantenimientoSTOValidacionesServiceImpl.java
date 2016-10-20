package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MensajeValidacionSTO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionSTO;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOValidacionesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOValidacionesService;

/**
 * Service que ejecuta las Validaciones de STO
 *  
 * <p>
 * <a href="MantenimientoSTOValidacionesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
@Service("spusicc.mantenimientoSTOValidacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOValidacionesServiceImpl extends BaseService implements MantenimientoSTOValidacionesService {
	
	@Resource(name="spusicc.mantenimientoSTOValidacionesDAO")
	private MantenimientoSTOValidacionesDAO mantenimientoSTOValidacionesDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOValidacionesService#getValidacionesSTO(java.util.Map)
	 */
	public List getValidacionesSTO(Map criteria) {
		return mantenimientoSTOValidacionesDAO.getValidacionesSTO(criteria);
	}

	public String getNextCodigoValidacion(String tipoDocumento) {
		return mantenimientoSTOValidacionesDAO.getNextCodigoValidacion(tipoDocumento);
	}

	public void insertValidacionesParametria(Map criteria) {
		mantenimientoSTOValidacionesDAO.insertValidacionesParametria(criteria);
	}
	
	public void insertValidacionesMensaje(Map criteria) {
		mantenimientoSTOValidacionesDAO.insertValidacionesMensaje(criteria);
	}
	
	public void insertValidacionesSecuencia(Map criteria) {
		mantenimientoSTOValidacionesDAO.insertValidacionesSecuencia(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOValidacionesService#getValidacionesParametriaMensajeSTO(java.lang.String)
	 */
	public List getValidacionesParametriaMensajeSTO(String codigoValidacion) {
		return mantenimientoSTOValidacionesDAO.getValidacionesParametriaMensajeSTO(codigoValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOValidacionesService#updateValidacionesParametria(java.util.Map)
	 */
	public void updateValidacionesParametria(Map criteria) {
		mantenimientoSTOValidacionesDAO.updateValidacionesParametria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOValidacionesService#updateValidacionesMensaje(java.util.Map)
	 */
	public void updateValidacionesMensaje(Map criteria) {
		mantenimientoSTOValidacionesDAO.updateValidacionesMensaje(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOValidacionesService#updateValidacionesSecuencia(java.util.List)
	 */
	public void updateValidacionesSecuencia(List listaValidacionesSecuencia) {
		if(listaValidacionesSecuencia != null && listaValidacionesSecuencia.size() > 0){
			for(int i = 0; i < listaValidacionesSecuencia.size(); i ++){
				Map criteria = new HashMap();
				
				try {
					criteria = BeanUtils.describe(listaValidacionesSecuencia.get(i));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				mantenimientoSTOValidacionesDAO.updateValidacionesSecuencia(criteria);
			}
		}
	}
	public List getValidacionListSTO(Map criteria) {
		return mantenimientoSTOValidacionesDAO.getValidacionListSTO(criteria);
	}
	
	
	public void deleteValidacionSTO(String codigoValidacion) {
		mantenimientoSTOValidacionesDAO.deleteParamSecueValidSTO(codigoValidacion);
		mantenimientoSTOValidacionesDAO.deleteMensaValidSTO(codigoValidacion);
		mantenimientoSTOValidacionesDAO.deleteParamValidSTO(codigoValidacion);		
	}
	public ValidacionSTO getValidacionSTO(ValidacionSTO bean) {
		return mantenimientoSTOValidacionesDAO.getValidacionSTO(bean);
	}
	public void insertValidacionSTO(ValidacionSTO bean, Usuario usuario) {
		mantenimientoSTOValidacionesDAO.insertValidacionSTO(bean, usuario);
	}
	public void updateValidacionSTO(ValidacionSTO bean, Usuario usuario) {
		mantenimientoSTOValidacionesDAO.updateValidacionSTO(bean, usuario);
	}
	public List getMensajeValidacionListSTO(Map criteria) {
		return mantenimientoSTOValidacionesDAO.getMensajeValidacionListSTO(criteria);
	}
	
	public void deleteMensajeValidacionSTO(Map criteria) {
		mantenimientoSTOValidacionesDAO.deleteMensajeValidacionSTO(criteria);
	}
	public MensajeValidacionSTO getMensajeValidacionSTO(
			MensajeValidacionSTO bean) {
		return mantenimientoSTOValidacionesDAO.getMensajeValidacionSTO(bean);
	}
	public void insertMensajeValidacionSTO(MensajeValidacionSTO bean,
			Usuario usuario) {
		mantenimientoSTOValidacionesDAO.insertMensajeValidacionSTO(bean, usuario);		
	}
	public void updateMensajeValidacionSTO(MensajeValidacionSTO bean,
			Usuario usuario) {
		mantenimientoSTOValidacionesDAO.updateMensajeValidacionSTO(bean, usuario);		
	}

}