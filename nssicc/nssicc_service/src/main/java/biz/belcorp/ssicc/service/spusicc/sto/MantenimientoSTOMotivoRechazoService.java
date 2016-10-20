package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MotivoRechazo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * Service con metodos para las consultas invocados por la pantalla de Mantenimiento de Motivos de Rechazo
 * 
 * <p>
 * <a href="MantenimientoSTOMotivoRechazoService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href=""> </a>
 * 
 */
public interface MantenimientoSTOMotivoRechazoService extends Service
{
	/**
	 * Retorna la lista de motivos de Rechazo.
	 * @param criteria
	 * @return
	 */
	public List getMotivoRechazoList(Map criteria);
	
	/**
	 * Retorna la información correspondiente a un Motivo de Rechazo
	 * @param criteria
	 * @return
	 */
	public MotivoRechazo getMotivoRechazo(Map criteria);
	
	/**
	 * Inserta un nuevo Registro
	 * @param criteria
	 */
	public void insertMotivoRechazo(MotivoRechazo rechazo, Usuario usuario);
	
	/**
	 * Modifica el registro existente
	 * @param criteria
	 */
	public void updateMotivoRechazo(Map criteria, Usuario usuario);
	
	/**
	 * Elimina el registro
	 * @param criteria
	 */
	public void deleteMotivoRechazo(Map criteria);

}
