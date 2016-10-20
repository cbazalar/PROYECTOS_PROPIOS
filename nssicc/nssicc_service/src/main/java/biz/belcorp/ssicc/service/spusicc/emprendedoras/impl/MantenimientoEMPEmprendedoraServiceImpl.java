package biz.belcorp.ssicc.service.spusicc.emprendedoras.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.MantenimientoEMPEmprendedoraDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.emprendedoras.MantenimientoEMPEmprendedoraService;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Service("spusicc.mantenimientoEMPEmprendedoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoEMPEmprendedoraServiceImpl extends BaseService implements MantenimientoEMPEmprendedoraService {
	
	@Resource(name = "spusicc.mantenimientoEMPEmprendedoraDAO")
	MantenimientoEMPEmprendedoraDAO mantenimientoEMPEmprendedoraDAO;


	
	/**
	 * Metodo que obtiene el listado de regimenes (Solo Peru)
	 * @return
	 */
	public List getRegimenes(Map criteria){
		return mantenimientoEMPEmprendedoraDAO.getRegimenes(criteria);
	}
	
	/**
	 * Metodo que obtiene el listado de tipo de emprendedora (Fustruck o regular)
	 * @return
	 */
	public List getTipoEmprededoras(){
		return mantenimientoEMPEmprendedoraDAO.getTipoEmprededoras();
	}
	
	/**
	 * Metodo que retorna los datos de la consultora
	 * @param criteria
	 * @return
	 */
	public List getDatosConsultora(Map criteria){
		return mantenimientoEMPEmprendedoraDAO.getDatosConsultora(criteria);
	}
	
	/**
	 * Metodo que valida los requisitos de creacion de emprendedora (Fastrack o regular)
	 * @param datos
	 */
	public List executeValidarEmprendedora(Map datos){
		
		String tipoEmprendedora = (String)datos.get("tipoEmprendedora") ;
		
		if (tipoEmprendedora.equals(Constants.EMP_TIPO_EMPRENDEDORA_REGULAR))
			mantenimientoEMPEmprendedoraDAO.executeValidarEmprendedoraRegular(datos);
		else
			if (tipoEmprendedora.equals(Constants.EMP_TIPO_EMPRENDEDORA_FASTTRACK))
				mantenimientoEMPEmprendedoraDAO.executeValidarEmprendedoraFastTrack(datos);	
		
		String error = (String) datos.get("error");

		if (error.equals("0"))
			return mantenimientoEMPEmprendedoraDAO.getRecomendadas(datos);		
		else
			return mantenimientoEMPEmprendedoraDAO.getRequisitosNoCumple(datos);
	}
	
	/**
	 * Metodo que actualiza los datos telefono, mail de la consultora
	 * @param datos
	 */
	public void updateDatosCliente(Map datos){
		mantenimientoEMPEmprendedoraDAO.updateDatosCliente(datos);
	}

	/**
	 * Metodo que inserta datos de la emprendedora	 
	 * @param datos
	 */
	public void insertDatosEmprendedora(Map datos){
		mantenimientoEMPEmprendedoraDAO.insertDatosEmprendedora(datos);
	}
	
	public String getTextoComunicacion(Map criteria){
		return mantenimientoEMPEmprendedoraDAO.getTextoComunicacion(criteria);
	}
	
	public void insertClienteComunicacion(ClienteComunicacion clienteComunicacion) {
		mantenimientoEMPEmprendedoraDAO.insertClienteComunicacion(clienteComunicacion);
	}
}
