package biz.belcorp.ssicc.service.spusicc.ventas.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.PorcentajeIgvVENDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.PorcentajeIgv;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ventas.PorcentajeIgvVENService;

/**
 * Clase que implementa la intefaz de servicio PorcentajeIgvVENService
 * @author peextjnunez
 *
 */
@Service("spusicc.porcentajeIgvVENService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PorcentajeIgvVENServiceImpl extends BaseService implements PorcentajeIgvVENService{
	
	@Resource(name="spusicc.porcentajeIgvVenDAO")
	private PorcentajeIgvVENDAO porcentajeIgvVenDAO;/**Atributo DAO*/
	/**
	 * Metodo que obtiene un listado de objetos del tipo PorcentajeIgv
	 * @param bporcentajeigv - Clase que contiene los criterio de busqueda de la lista
	 * @return List - Listado resultado
	 */
	public List getPorcentajeIgv(PorcentajeIgv bporcentajeigv){
		return porcentajeIgvVenDAO.getPorcentajeIgv(bporcentajeigv);
	}
	/**
	 * Metodo que actualiza un porcentaje igv
	 * @param bporcentajeigv - Clase que contiene los datos a actualizar
	 */
    public void updatePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
    	porcentajeIgvVenDAO.updatePorcentajeIgv(bporcentajeigv,usuario);
    }
    /**
	 * Metodo de registro de un Porcentaje igv
	 * @param bporcentajeigv - Clase que contiene los datos a registrarse
	 */
	public void insertPorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
		porcentajeIgvVenDAO.insertPorcentajeIgv(bporcentajeigv,usuario);
	}
	/**
	 * Metodo que elimina a un porcentaje igv
	 * @param bporcentajeigv - Clase que contiene los datos a eliminarse
	 */
	public void deletePorcentajeIgv(PorcentajeIgv bporcentajeigv, Usuario usuario){
		
		porcentajeIgvVenDAO.deletePorcentajeIgv(bporcentajeigv,usuario);
	}
	/**
	 * @return Returns the porcentajeIgvVenDAO.
	 */
	public PorcentajeIgvVENDAO getPorcentajeIgvVenDAO() {
		return porcentajeIgvVenDAO;
	}
	/**
	 * @param porcentajeIgvVenDAO The porcentajeIgvVenDAO to set.
	 */
	public void setPorcentajeIgvVenDAO(PorcentajeIgvVENDAO porcentajeIgvVenDAO) {
		this.porcentajeIgvVenDAO = porcentajeIgvVenDAO;
	}
	

}
