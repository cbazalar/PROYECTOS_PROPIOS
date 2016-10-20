/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaArchivoDesarrolladoraVentasDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DesarrolladoraVenta;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaArchivoDesarrolladoraVentasService;

/**
 * @author Danny Amaro
 *
 */
@Service("spusicc.procesoPEDCargaArchivoDesarrolladoraVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDCargaArchivoDesarrolladoraVentasServiceImpl extends BaseService implements ProcesoPEDCargaArchivoDesarrolladoraVentasService{
	
	@Resource(name="spusicc.procesoPEDCargaArchivoDesarrolladoraVentasDAO")
	ProcesoPEDCargaArchivoDesarrolladoraVentasDAO procesoPEDCargaArchivoDesarrolladoraVentasDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaArchivoDesarrolladoraVentasService#getDesarrolladoraVenta(java.util.Map)
	 */
	public List getDesarrolladoraVenta(Map criteria) {		
		return this.procesoPEDCargaArchivoDesarrolladoraVentasDAO.getDesarrolladoraVenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaArchivoDesarrolladoraVentasService#insertDesarrolladoraVenta(biz.belcorp.ssicc.spusicc.pedidos.model.DesarrolladoraVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDesarrolladoraVenta(
			DesarrolladoraVenta desarrolladoraVenta, Usuario usuario) {
		this.procesoPEDCargaArchivoDesarrolladoraVentasDAO.insertDesarrolladoraVenta(desarrolladoraVenta, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaArchivoDesarrolladoraVentasService#insertDesarrolladoraVentaHistorico(biz.belcorp.ssicc.spusicc.pedidos.model.DesarrolladoraVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDesarrolladoraVentaHistorico(
			DesarrolladoraVenta desarrolladoraVenta, Usuario usuario) {
		this.procesoPEDCargaArchivoDesarrolladoraVentasDAO.insertDesarrolladoraVentaHistorico(desarrolladoraVenta, usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaArchivoDesarrolladoraVentasService#removeDesarrolladoraVenta(java.util.Map)
	 */
	public void removeDesarrolladoraVenta(Map criteria) {
		this.procesoPEDCargaArchivoDesarrolladoraVentasDAO.removeDesarrolladoraVenta(criteria);		
	}
	
}
