package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.LabelSolicitudesCreditoValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCapturaSolicitudesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaSolicitudesService;

@Service("spusicc.pedidos.mantenimientoOCRCapturaSolicitudesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRCapturaSolicitudesServiceImpl extends BaseService implements MantenimientoOCRCapturaSolicitudesService {
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRCapturaSolicitudesDAO")
	MantenimientoOCRCapturaSolicitudesDAO mantenimientoOCRCapturaSolicitudesDAO;
	
	@Resource(name="sisicc.interfazEVIDAO")
	protected InterfazEVIDAO interfazEVIDAO;
  
	 public void insertSolicitudesCreditoxZona(ArrayList objListaSolicitudes, Usuario usuario) {
	    	for (int i=0; i<objListaSolicitudes.size();i++){
	    		LabelSolicitudesCreditoValue objSolicitudxZona= (LabelSolicitudesCreditoValue)objListaSolicitudes.get(i);
	    		mantenimientoOCRCapturaSolicitudesDAO.insertSolicitudesCreditoxZona(objSolicitudxZona, usuario);
			}
	 }
	 
	 public String getNumeroLoteByCriteria(Map criteriaLote){
		 return mantenimientoOCRCapturaSolicitudesDAO.getNumeroLoteByCriteria(criteriaLote);
	 }
	 
	 public List getSearchPedidosZonaByCriteria(Map criteriaPedidosZona){
		 return this.mantenimientoOCRCapturaSolicitudesDAO.getSearchPedidosZonaByCriteria(criteriaPedidosZona);
	 }
	 
	 public LabelSolicitudesCreditoValue getPedidoxZonaExistente(Map criteriaPedidoxZona){
		 return mantenimientoOCRCapturaSolicitudesDAO.getPedidoxZonaExistente(criteriaPedidoxZona);
	 }
	 
	 public void updateCapturaSolicitudCredito(LabelSolicitudesCreditoValue solicitudPedidoCredito,Usuario usuario ) {
		 mantenimientoOCRCapturaSolicitudesDAO.updateCapturaSolicitudCredito(solicitudPedidoCredito, usuario);		
	}
	
	public void executeActualizaNumeroLoteMica(Map map) {
		try {
			interfazEVIDAO.cargarResumenesPrefacturacion();
		} catch (Exception e) {
			log.error("Error en executeActualizaNumeroLoteMica " + e.getMessage());			
		}
	}

}