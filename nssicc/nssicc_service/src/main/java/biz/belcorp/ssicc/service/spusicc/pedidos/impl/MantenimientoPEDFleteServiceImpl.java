package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDFleteDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Flete;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.FleteDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDFleteService;

@Service("spusicc.pedidos.mantenimientoPEDFleteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDFleteServiceImpl extends BaseService implements MantenimientoPEDFleteService{

	@Resource(name="spusicc.mantenimientoPEDFleteDAO")
	MantenimientoPEDFleteDAO mantenimientoPEDFleteDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#getTipoDespacho()
	 */
	public List getTipoDespacho() {
		return mantenimientoPEDFleteDAO.getTipoDespacho();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#getFlete(java.lang.String)
	 */
	public Flete getFlete(String idFlete) {
		return mantenimientoPEDFleteDAO.getFlete(idFlete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#deleteFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete)
	 */
	public void deleteFlete(Flete flete, Usuario usuario) {
		//Eliminando detalles
	    List listaDetFlet = mantenimientoPEDFleteDAO.getDetalleFleteList(flete.getOidFlete());
	    
	    for(int i=0; i<listaDetFlet.size();i++){
	    	FleteDetalle fd = (FleteDetalle)listaDetFlet.get(i);
	    	
			mantenimientoPEDFleteDAO.deleteFleteDetalle(fd, usuario);
			
			fd.setAccion(Constants.FLETE_AUDITORIA_ELIMINACION);
			mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
	    }
		
		//Eliminando
		mantenimientoPEDFleteDAO.deleteFlete(flete, usuario);
		flete.setAccion(Constants.FLETE_AUDITORIA_ELIMINACION);
		mantenimientoPEDFleteDAO.insertFleteAuditoria(flete, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#insertFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete, java.util.List)
	 */
	public void insertFlete(Flete cabecera, List detalles, Usuario usuario) {
		
		cabecera.setAccion(Constants.FLETE_AUDITORIA_REGISTRO);
		mantenimientoPEDFleteDAO.insertFlete(cabecera, usuario);		
		mantenimientoPEDFleteDAO.insertFleteAuditoria(cabecera, usuario);
		
		if(detalles.size() > 0 && Float.valueOf(cabecera.getMontoFijo())  == 0f){
			for(int i=0; i < detalles.size(); i++){
				FleteDetalle detalle = (FleteDetalle)detalles.get(i);
				detalle.setOidFlete(cabecera.getOidFlete());
				detalle.setOidDetFlete(String.valueOf(mantenimientoPEDFleteDAO.getNextOidFleteDetalle()));
				detalle.setAccion(Constants.FLETE_AUDITORIA_REGISTRO);
				mantenimientoPEDFleteDAO.insertFleteDetalle(detalle, usuario);
				mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(detalle, usuario);
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#getNextOidFlete()
	 */
	public int getNextOidFlete() {

		return mantenimientoPEDFleteDAO.getNextOidFlete();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#getFleteList(java.util.Map)
	 */
	public List getFleteList(Map map) {
		return mantenimientoPEDFleteDAO.getFleteList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#updateFlete(biz.belcorp.ssicc.spusicc.pedidos.model.Flete, java.util.List)
	 */
	public void updateFlete(Flete cabecera, List detalle, Usuario usuario) {
		
		cabecera.setAccion(Constants.FLETE_AUDITORIA_MODIFICACION);
		
		Flete fleteOld = mantenimientoPEDFleteDAO.getFlete(cabecera.getOidFlete());
		fleteOld.setCodigoPais(cabecera.getCodigoPais());
		fleteOld.setAccion(Constants.FLETE_AUDITORIA_MODIFICACION);
		
		mantenimientoPEDFleteDAO.updateFlete(cabecera, usuario);
		mantenimientoPEDFleteDAO.insertFleteAuditoria(fleteOld, usuario);
		
		List detalleExistente = mantenimientoPEDFleteDAO.getDetalleFleteList(cabecera.getOidFlete());
		
		if(detalle.size() == 0 && detalleExistente.size() != 0)
		{
			//Eliminar todos los de la BD
			for (int i = 0; i < detalleExistente.size(); i++) 
			{
				FleteDetalle fd = (FleteDetalle) detalleExistente.get(i);

				fd.setAccion(Constants.FLETE_AUDITORIA_ELIMINACION);
				mantenimientoPEDFleteDAO.deleteFleteDetalle(fd, usuario);
				
				mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
			}
		}
		else if(detalle.size() != 0 && detalleExistente.size() == 0)
		{			
			if(Float.valueOf(cabecera.getMontoFijo()) == 0f)
			{
				//Grabamos
				for (int i = 0; i < detalle.size(); i++) {
					FleteDetalle fd = (FleteDetalle) detalle.get(i);

					// Si existe iodDetFlete
					if (StringUtils.isNotBlank(fd.getOidDetFlete())) {
						fd.setAccion(Constants.FLETE_AUDITORIA_MODIFICACION);
						mantenimientoPEDFleteDAO.updateFleteDetalle(fd, usuario);
					} else {
						// si es nuevo y no tiene oidDetFlete
						fd.setAccion(Constants.FLETE_AUDITORIA_REGISTRO);
						fd.setOidDetFlete(String.valueOf(mantenimientoPEDFleteDAO.getNextOidFleteDetalle()));
						
						mantenimientoPEDFleteDAO.insertFleteDetalle(fd, usuario);					
					}
					
					mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
					
				}
			}
		}
		else if(detalle.size() != 0 && detalleExistente.size() != 0)
		{
			//Existen, nuevos, eliminados y modificados
			boolean existe = false;
			
			//1. Identificar a los eliminados
			List eliminados = new ArrayList();
			for (int i = 0; i < detalleExistente.size(); i++) 
			{
				FleteDetalle dExistente = (FleteDetalle) detalleExistente.get(i);
				
				//Ubicamos al existente
				existe = false;
				for(int j=0; j<detalle.size(); j++)
				{
					FleteDetalle dNuevo = (FleteDetalle) detalle.get(j);
					
					if(StringUtils.equals(dExistente.getOidDetFlete(), dNuevo.getOidDetFlete()))
					{
						existe = true;
						break;
					}
				}
				
				if(!existe)
				{
					eliminados.add(dExistente); 
				}				
			}
			//
			
			//2. Identificar a los nuevos
			List nuevos = new ArrayList();
			for (int i = 0; i < detalle.size(); i++) {
				FleteDetalle fd = (FleteDetalle) detalle.get(i);
				if (StringUtils.isBlank(fd.getOidDetFlete()) || Integer.valueOf(fd.getOidDetFlete()) == 0) {
					nuevos.add(fd);
				}
			}
			//
						
			if(Float.valueOf(cabecera.getMontoFijo())  > 0f)
			{				
				//Eliminamos los detalles de BD
				for (int i = 0; i < detalleExistente.size(); i++) {
					FleteDetalle fd = (FleteDetalle) detalleExistente.get(i);

					fd.setAccion(Constants.FLETE_AUDITORIA_ELIMINACION);
					mantenimientoPEDFleteDAO.deleteFleteDetalle(fd, usuario);
					
					mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
				}
			}
			else
			{
				//Eliminamos los identificados
				for (int i = 0; i < eliminados.size(); i++) {
					FleteDetalle fd = (FleteDetalle) eliminados.get(i);

					fd.setAccion(Constants.FLETE_AUDITORIA_ELIMINACION);
					mantenimientoPEDFleteDAO.deleteFleteDetalle(fd, usuario);
					
					mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
				}
				//
				
				//Insertamos los nuevos
				for (int i = 0; i < nuevos.size(); i++) {
					FleteDetalle fd = (FleteDetalle) nuevos.get(i);

					fd.setAccion(Constants.FLETE_AUDITORIA_REGISTRO);
					fd.setOidDetFlete(String.valueOf(mantenimientoPEDFleteDAO.getNextOidFleteDetalle()));
					
					mantenimientoPEDFleteDAO.insertFleteDetalle(fd, usuario);					
					
					mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fd, usuario);
					
				}
				//
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#getDetalleFleteList(java.lang.String)
	 */
	public List getDetalleFleteList(String oidFlete) {
		return mantenimientoPEDFleteDAO.getDetalleFleteList(oidFlete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDFleteService#deleteFleteDetalle(biz.belcorp.ssicc.spusicc.pedidos.model.FleteDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteFleteDetalle(FleteDetalle fleteDetalle, Usuario usuario) {		
		mantenimientoPEDFleteDAO.deleteFleteDetalle(fleteDetalle, usuario);
		mantenimientoPEDFleteDAO.insertFleteAuditoriaDetalle(fleteDetalle, usuario);
	}
}
