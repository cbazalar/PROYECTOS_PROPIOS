package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECTarjetaPagoDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaLider;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECTarjetaPagoService;

/**
 * <p>
 * <a href="MantenimientoLECTarjetaPagoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="dtorres@sigcomt.com">Diego Torres L.</a>
 *         
 */
@Service("spusicc.mantenimientoLECTarjetaPagoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLECTarjetaPagoServiceImpl extends BaseService implements MantenimientoLECTarjetaPagoService{
	
	@Resource(name="spusicc.mantenimientoLECTarjetaPagoDAO")
	private MantenimientoLECTarjetaPagoDAO mantenimientoLECTarjetaPagoDAO;
	

	public List getTarjetaPagoByCriteria(Map params) {
		return mantenimientoLECTarjetaPagoDAO.getTarjetaPagoByCriteria(params);
	}

	public TarjetaPago getTarjetaPago(String id) {
		return mantenimientoLECTarjetaPagoDAO.getTarjetaPago(id);
	}

	public void insertTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		mantenimientoLECTarjetaPagoDAO.insertTarjetaPago(tarjetaPago, usuario);		
	}

	public void updateTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		mantenimientoLECTarjetaPagoDAO.updateTarjetaPago(tarjetaPago, usuario);	
	}

	public void deleteTarjetaPago(TarjetaPago tarjetaPago) {
		mantenimientoLECTarjetaPagoDAO.deleteTarjetaPago(tarjetaPago);	
	}

	public List getEstadoTarjetaPago() {
		return mantenimientoLECTarjetaPagoDAO.getEstadoTarjetaPago();
	}

	public List getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(Map params) {
		return mantenimientoLECTarjetaPagoDAO.getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(params);
	}

	public List getLecAsociacionTarjetaPagoPorLiderByCriteria(Map params) {
		return mantenimientoLECTarjetaPagoDAO.getLecAsociacionTarjetaPagoPorLiderByCriteria(params);
	}
	
	public String getNombreLider(String codigoLider) {
		return mantenimientoLECTarjetaPagoDAO.getNombreLider(codigoLider);
	}

	public boolean validaCodigoLider(String codigoLider) {
		return mantenimientoLECTarjetaPagoDAO.validaCodigoLider(codigoLider);
	}

	public void insertTarjetaLider(TarjetaLider tarjetaLider, Usuario usuario) {
		mantenimientoLECTarjetaPagoDAO.insertTarjetaLider(tarjetaLider, usuario);	
	}
	
	public void updateEstadoTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		mantenimientoLECTarjetaPagoDAO.updateEstadoTarjetaPago(tarjetaPago, usuario);	
	}
	
	public TarjetaLider getTarjetaLider(Map params) {
		return mantenimientoLECTarjetaPagoDAO.getTarjetaLider(params);
	}
	
	public void updateTarjetaLider(Map params) {
		mantenimientoLECTarjetaPagoDAO.updateTarjetaLider(params);
	}
	
	public TarjetaPago getTarjetaPagoByNumeroTarjeta(String numeroTarjeta) {
		return mantenimientoLECTarjetaPagoDAO.getTarjetaPagoByNumeroTarjeta(numeroTarjeta);
	}
	
	public boolean validaNumeroTarjeta(String numeroTarjeta) {
		return mantenimientoLECTarjetaPagoDAO.validaNumeroTarjeta(numeroTarjeta);
	}
	
	public String validaTarjetaPagoAsociada(String numeroTarjeta) {
		return mantenimientoLECTarjetaPagoDAO.validaTarjetaPagoAsociada(numeroTarjeta);
	}

	public void updateClienteLider(Map params) {
		mantenimientoLECTarjetaPagoDAO.updateClienteLider(params);		
	}

	public List getLecConsultaTarjetasPago(Map params) {
		return mantenimientoLECTarjetaPagoDAO.getLecConsultaTarjetasPago(params);
	}

	public void deleteTarjetaLider(Map params) {
		mantenimientoLECTarjetaPagoDAO.deleteTarjetaLider(params);		
		
	}
	
}
