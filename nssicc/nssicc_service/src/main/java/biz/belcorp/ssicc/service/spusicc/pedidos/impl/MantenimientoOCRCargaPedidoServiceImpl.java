package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.ConsultaHIPDatosClienteDAO;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCargaPedidoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;


@Service("spusicc.pedidos.mantenimientoOCRCargaPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRCargaPedidoServiceImpl extends BaseService implements MantenimientoOCRCargaPedidoService {
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRCargaPedidoDAO")
	MantenimientoOCRCargaPedidoDAO mantenimientoOCRCargaPedidoDAO;
	
	@Resource(name="scsicc.consultaHIPDatosClienteDAO")
	ConsultaHIPDatosClienteDAO consultaHIPDatosClienteDAO;
	
	@Resource(name="scsicc.reporteDAO")
	ReporteDAO reporteDAO;
	
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#insertarPedido(biz.belcorp.ssicc.model.LabelDatosConsultoraValue, java.util.ArrayList, biz.belcorp.ssicc.model.Usuario, java.util.Map, java.lang.String[])
	 */
	public void insertarPedido(LabelDatosConsultoraValue objDatosConsultora,
							   ArrayList objListaPedidos,
			                   Usuario usuario,
			                   Map filter,
			                   String[] eliminaList) {
		String codConsul = getCodConsultora(filter);

		Map criteria = new HashMap();
		criteria.put("codigoPais", filter.get("codPais"));
		criteria.put("codigoPeriodo",filter.get("periodo"));
		criteria.put("codigoCliente",filter.get("codCliente"));
		criteria.put("numLote",filter.get("numeroLote"));
		
		// Borra de BD los detalles eliminados
		for (int i = 0; i < eliminaList.length; i++) {
			if(!eliminaList[i].equals("")){
				criteria.put("codigoVenta",eliminaList[i]);
				deleteDetallePedido(criteria);
			}
		}

		if(StringUtils.isBlank(codConsul)){
			log.debug("insert !!");
			insertCabeceraPedido(objDatosConsultora, usuario);
			insertDetallePedido(objListaPedidos, usuario);
		}
		else{
			log.debug("update !!");
			insertDetallePedido(objListaPedidos, usuario);
			updateCabeceraPedido(objDatosConsultora, usuario);
		}
	}	
	
	public void insertCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {
			log.debug("____insertando cabecera : "+objDatosConsultora); 
	    	mantenimientoOCRCargaPedidoDAO.insertcabeceraPedido(objDatosConsultora, usuario);		
	}
    
	 /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#insertDetallePedido(java.util.ArrayList, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDetallePedido(ArrayList objListaPedidos, Usuario usuario) {
		//*************************************************************
	    ArrayList listaConsolidada = consolidarDetalles(objListaPedidos);
	    for (int i=0; i<listaConsolidada.size();i++){
	    //*************************************************************    	
	    //for (int i=0; i<objListaPedidos.size();i++){
	    	LabelPedidosValue objPedidoConsultora= (LabelPedidosValue)listaConsolidada.get(i);
	    	try {
	    		log.debug("___insertando detalle : "+objPedidoConsultora);
	    		mantenimientoOCRCargaPedidoDAO.insertDetallePedido(objPedidoConsultora, usuario);				
			} catch (Exception e) {
				log.debug("___actualizando detalle : "+objPedidoConsultora);
				mantenimientoOCRCargaPedidoDAO.updateDetallePedido(objPedidoConsultora, usuario);
			}
		}
	}
    
    //*************************************************************
    /**
     * @param origen
     * @return Devuele la lista de detalles consolidada
     */
    public ArrayList consolidarDetalles(ArrayList origen){
    	ArrayList listaConsolidada = new ArrayList();
  	    
    	for (int i = 0; i < origen.size(); i++) {
    		LabelPedidosValue objPedidoConsultora= (LabelPedidosValue)origen.get(i);
    		boolean esta = false;
    		int indice = 0;    		
    		for (int j = 0; j < listaConsolidada.size(); j++) {
    			LabelPedidosValue aux= (LabelPedidosValue)listaConsolidada.get(j);
    			if(objPedidoConsultora.getCodigoVta().equals(aux.getCodigoVta())){
    				esta = true;
    				indice = j;
    				objPedidoConsultora.setUnidades(String.valueOf(new Integer(objPedidoConsultora.getUnidades()).intValue() +
    												              	new Integer(aux.getUnidades()).intValue()));
    				objPedidoConsultora.setTotal(String.valueOf(new Double(objPedidoConsultora.getTotal()).doubleValue()+
    												             new Double(aux.getTotal()).doubleValue()));
    				break;
    			}
			}
    		if(esta)
    			listaConsolidada.set(indice,objPedidoConsultora);
    		else
    			listaConsolidada.add(objPedidoConsultora);
		}
    	
    	return listaConsolidada;
    }
    //*************************************************************
    
    public List getSearchPedidosByCriteria(Map criteria) {    	
		return this.mantenimientoOCRCargaPedidoDAO.getSearchPedidosByCriteria(criteria);
	}

    public List getDetallePedidosConsultoraByCriteria(Map criteria) {
		return this.mantenimientoOCRCargaPedidoDAO.getDetallePedidosConsultoraByCriteria(criteria);
	}
    
	public void updateRemoveCabeceraPedido(LabelDatosConsultoraValue consultoraValue, Usuario usuario) {
		mantenimientoOCRCargaPedidoDAO.updateRemoveCabeceraPedido(consultoraValue, usuario);
	}
    
	public List getDetallePedidoByFilter(Map filter){
		return this.mantenimientoOCRCargaPedidoDAO.getDetallePedidoByFilter(filter);
	}
	
	public void executeEnviarDetallesDigitados(Map params){
		mantenimientoOCRCargaPedidoDAO.executeEnviarDetallesDigitados(params);
	}
	
	public String getCodConsultora(Map params){
		return mantenimientoOCRCargaPedidoDAO.getCodConsultora(params);
	}
	
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario){
		mantenimientoOCRCargaPedidoDAO.updateCabeceraPedido(objDatosConsultora, usuario);
	}
	
	public List getSearchDetallesByCriteria(Map criteria){
		
        //obtenemos las unidades administrativas de la consultora
		List lstArrg = new ArrayList();
		List lstUnidAdm = null;
		List lstDetalle = mantenimientoOCRCargaPedidoDAO.getSearchDetallesByCriteria(criteria);
		String promVenta = null;
		Map params = null;
		Map mapRow = null;
		Map mapRow2 = null;
		String oidCliente = null;
		
		for (int i=0; i<lstDetalle.size();i++){
			params = new HashMap();
			mapRow = (Map)lstDetalle.get(i);
			params.put("codigoMarca", criteria.get("codigoMarca"));
			params.put("codigoCanal", criteria.get("codigoCanal"));
	  		params.put("codigoPais", criteria.get("codigoPais"));
	  		params.put("codigoPeriodo", mapRow.get("codigoPeriodo"));
	  		params.put("codigoCliente", mapRow.get("codigoCliente"));
	  		params.put("numDocu", mapRow.get("numDocu"));
	  		params.put("codigoVta", mapRow.get("codigoVta"));
	  		params.put("tipoPosicion", mapRow.get("tipoPosicion"));
	  		params.put("descProd", mapRow.get("descProd"));
	  		params.put("valUnidadDemanda", mapRow.get("valUnidadDemanda"));
	  		params.put("valUnidadDemandaInicial", mapRow.get("valUnidadDemanda"));
	  		params.put("factorRepeticion", mapRow.get("factorRepeticion"));
	  		params.put("totalUnidades", mapRow.get("totalUnidades"));  
	  		params.put("precio", mapRow.get("precio"));
	  		params.put("origen", mapRow.get("origen"));
	  		params.put("tipoCUV", mapRow.get("tipoCUV"));
	  		params.put("totalPedidoGestion", mapRow.get("totalPedidoGestion"));
	  		params.put("estado", mapRow.get("estado"));  
	  		params.put("indCompl", mapRow.get("indCompl"));
	  		params.put("valUnidadCompletadas", mapRow.get("valUnidadCompletadas"));  
	  		params.put("fechaSolicitud", mapRow.get("fechaSolicitud"));  
	  		params.put("indErrRech", mapRow.get("indErrRech"));
	  		params.put("numLote", mapRow.get("numLote"));
	  		params.put("valorLimiteVent", mapRow.get("valorLimiteVent"));
	  		params.put("indErrorSSE", mapRow.get("indErrorSSE"));
	  		params.put("codZona", mapRow.get("codZona"));
	  		params.put("zona", mapRow.get("zona"));
	  		params.put("region", mapRow.get("region"));
	  		params.put("nombreCliente", mapRow.get("nombreCliente"));
	  		params.put("telefonoCliente", mapRow.get("telefonoCliente"));
	  		params.put("fechaFacturacion", mapRow.get("fechaFacturacion"));
	  		
	  		String id = (String)mapRow.get("codigoPais") + "|" +
	  					(String)mapRow.get("codigoPeriodo") + "|" +
	  					(String)mapRow.get("codigoCliente") + "|" +
	  					(String)mapRow.get("numLote") + "|" +
	  					(String)mapRow.get("codigoVta") ;	  		
	  		params.put("id", id);
	  		try{
	  			oidCliente = reporteDAO.getOidString("getOidClienteByCodigoCliente", params);
	  		}
	  		catch(Exception e){
	  			oidCliente = "";
	  		}
	  			
	  		params.put("oidCliente", oidCliente);
	  		
	  		lstUnidAdm = consultaHIPDatosClienteDAO.getUnidadesAdministrativas(params);
	  		
	  		if (lstUnidAdm.size()>0){
	  			mapRow2 = (Map)lstUnidAdm.get(0);
	  			params.put("codigoRegion", mapRow2.get("codigoRegion"));
	  		}else{
	  			params.put("codigoRegion", "");
	  		}
	  		
	  		promVenta = consultaHIPDatosClienteDAO.getPromedioVentasxCampanhas(params);
	  		params.put("promedioVenta", promVenta);
	  			  		
	  		params.put("indicadorPROL", mapRow.get("indicadorPROL"));
	  		
	  		lstArrg.add(params);
		}
		
		return lstArrg;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#execute(java.util.List)
	 */
	public void execute(List lista){
		mantenimientoOCRCargaPedidoDAO.execute(lista);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#getCodigoVentaList(java.util.Map)
	 */
	public List getCodigoVentaList(Map criteria){
		return mantenimientoOCRCargaPedidoDAO.getCodigoVentaList(criteria);
	}
	
	/**
	 * @param criteria
	 */
	public void deleteDetallePedido(Map criteria) {    	
		mantenimientoOCRCargaPedidoDAO.deleteDetallePedido(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#getTiposRecepcionMICAOCSWebDD()
	 */
	public List getTiposRecepcionMICAOCSWebDD() {
		return mantenimientoOCRCargaPedidoDAO.getTiposRecepcionMICAOCSWebDD();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#getTiposRecepcionMICAOCSDemandaWebDD()
	 */
	public List getTiposRecepcionMICAOCSDemandaWebDD() {
		return mantenimientoOCRCargaPedidoDAO.getTiposRecepcionMICAOCSDemandaWebDD();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#getTodosDetallesPedidoByFilter(java.util.Map)
	 */
	public List getTodosDetallesPedidoByFilter(Map filter){
		return this.mantenimientoOCRCargaPedidoDAO.getTodosDetallesPedidoByFilter(filter);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#saveSolicitudesTransferidas(java.util.Map)
	 */
	public void saveSolicitudesTransferidas(Map map) {
		mantenimientoOCRCargaPedidoDAO.saveSolicitudesTransferidas(map);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCargaPedidoService#executeConsolidaPedidoOnline(java.util.Map)
	 */
	public void executeConsolidaPedidoOnline(Map map) {
		mantenimientoOCRCargaPedidoDAO.executeConsolidaPedidoOnline(map);
	}
}