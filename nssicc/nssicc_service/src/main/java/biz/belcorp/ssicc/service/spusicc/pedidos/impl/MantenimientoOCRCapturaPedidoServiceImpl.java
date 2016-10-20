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

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCapturaPedidoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import es.indra.sicc.cmn.negocio.integracion.ConstantesIntegracion;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;
import es.indra.sicc.cmn.negocio.integracion.MONIntegracionSICC;

@Service("spusicc.pedidos.mantenimientoOCRCapturaPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRCapturaPedidoServiceImpl extends BaseService implements MantenimientoOCRCapturaPedidoService {

	@Resource(name="spusicc.pedidos.mantenimientoOCRCapturaPedidoDAO")
	MantenimientoOCRCapturaPedidoDAO mantenimientoOCRCapturaPedidoDAO;
	
	@Resource(name="ejb.integracionSICC")
	private MONIntegracionSICC monIntegracionSICC;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#insertarPedido(biz.belcorp.ssicc.model.LabelDatosConsultoraValue, java.util.ArrayList, biz.belcorp.ssicc.model.Usuario, java.util.Map, java.lang.String[])
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
		//*************************************
		objDatosConsultora.setTotalTotal(Constants.NUMERO_CERO);
		//*************************************
		if(StringUtils.isBlank(codConsul)){
			log.debug("insert !!");
			insertCabeceraPedido(objDatosConsultora, usuario);
			insertDetallePedido(objListaPedidos, usuario);
		}
		else{
			log.debug("update !!");
			insertDetallePedido(objListaPedidos, usuario);			
		}
		updateCabeceraPedido(objDatosConsultora, usuario);
	}		

	/**
	 * @param objDatosConsultora
	 * @param usuario
	 * Inserta la cabecera del pedido
	 */
	public void insertCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {
		mantenimientoOCRCapturaPedidoDAO.insertcabeceraPedido(objDatosConsultora, usuario);		
	}
    
	/**
	 * @param objListaPedidos
	 * @param usuario
	 * Inserta los detalle del pedido
	 */
	public void insertDetallePedido(ArrayList objListaPedidos, Usuario usuario) {
	    ArrayList listaConsolidada = consolidarDetalles(objListaPedidos);
	    for (int i=0; i<listaConsolidada.size();i++){
	    	LabelPedidosValue objPedidoConsultora= (LabelPedidosValue)listaConsolidada.get(i);
	    	try {	    		
	    		mantenimientoOCRCapturaPedidoDAO.insertDetallePedido(objPedidoConsultora, usuario);				
			} catch (Exception e) {				
				mantenimientoOCRCapturaPedidoDAO.updateDetallePedido(objPedidoConsultora, usuario);
			}
		}
	}
    
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
    				/*
    				objPedidoConsultora.setTotal(String.valueOf(new Double(objPedidoConsultora.getTotal()).doubleValue()+
    												             new Double(aux.getTotal()).doubleValue()));
    				*/								             
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
    
    /**
     * @param params
     * @return
     * Devuelve el codigo de consultora
     */
    public String getCodConsultora(Map params){
		return mantenimientoOCRCapturaPedidoDAO.getCodConsultora(params);
	}
    
    /**
     * @param criteria
     * Elimina el detalle del pedido
     */
    public void deleteDetallePedido(Map criteria) {    	
		mantenimientoOCRCapturaPedidoDAO.deleteDetallePedido(criteria);
	}
    
	/**
	 * @param objDatosConsultora
	 * @param usuario
	 * Actualiza la cabecera del pedido
	 */
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario){
		mantenimientoOCRCapturaPedidoDAO.updateCabeceraPedido(objDatosConsultora, usuario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getListaCodigosVentaMatriz(java.util.Map)
	 */
	public List getListaCodigosVentaMatriz(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getListaCodigosVentaMatriz(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#validarBloqueoDigitacionPedidos(java.util.Map)
	 */
	public boolean validarBloqueoDigitacionPedidos(Map criteria) {
		String indicadorValidacion= mantenimientoOCRCapturaPedidoDAO.validarBloqueoDigitacionPedidos(criteria);
		return "S".equals(indicadorValidacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getListaDetallePedido(java.util.Map)
	 */
	public List getListaDetallePedido(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getListaDetallePedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getSumaTotalPedidoListaDetallePedido(java.util.Map)
	 */
	public String getSumaTotalPedidoListaDetallePedido(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getSumaTotalPedidoListaDetallePedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#deleteDetallePedidoOnLine(java.lang.String[])
	 */
	public void deleteDetallePedidoOnLine(String[] items){
		Map criteria = new HashMap();

		for(int i = 0; i < items.length; i++){
			if (i>0){
				String id = items[i];
				
				criteria.put("codigoPais", StringUtils.split(id, "|")[0]);
				criteria.put("numLote", StringUtils.split(id, "|")[1]);
				criteria.put("codigoConsultora", StringUtils.split(id, "|")[2]);
				criteria.put("codigoPeriodo", StringUtils.split(id, "|")[3]);
				criteria.put("codigoVenta", StringUtils.split(id, "|")[4]);

				mantenimientoOCRCapturaPedidoDAO.deleteDetallePedidoOnLine(criteria);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#verificarDetallePedido(java.util.Map)
	 */
	public String verificarDetallePedido(Map params){
		return mantenimientoOCRCapturaPedidoDAO.verificarDetallePedido(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#deleteCabeceraPedidoOnLine(java.util.Map)
	 */
	public void deleteCabeceraPedidoOnLine(Map criteria){
		mantenimientoOCRCapturaPedidoDAO.deleteCabeceraPedidoOnLine(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#actualizaIndicadorOCS(java.util.Map)
	 */
	public void actualizaIndicadorOCS(Map criteria){
		mantenimientoOCRCapturaPedidoDAO.actualizaIndicadorOCS(criteria);
		mantenimientoOCRCapturaPedidoDAO.actualizaIndicadorProcDetal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getNumeroLoteByPk(java.util.Map)
	 */
	public String getNumeroLoteByPk(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getNumeroLoteByPk(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#executeEjecutarGP2(java.util.Map)
	 */
	public void executeEjecutarGP2(Map criteria) throws Exception {
		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(criteria);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_PEDIDO_GP2);

		String oidSol = (String)criteria.get("oidSolicitud");
		Long oidSolicitud = new Long(oidSol);
		//oidSolicitud = (Long)criteria.get("oidSolicitud");

		List lista = new ArrayList();
		lista.add(oidSolicitud);

		dtoEntrada.getParametros().put("solicitudes", lista);

		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());

		try {	    		
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);
			
			//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
		} catch (Exception e) {
			log.error("Error INVOCANDO AL PROCESO SICC : " + e.getMessage());
			throw new Exception("Error INVOCANDO AL PROCESO SICC : " + ConstantesIntegracion.PROCESO_PEDIDO_GP2 + " "+ e.getMessage());
		}
	}
	
	/**
	 * Obtiene los datos de Parametros de Entrada SICC
	 * 
	 * @param params
	 * @return
	 */
	private DTOEntradaSICC obtenerDTOEntradaSICC(Map params) {
		DTOEntradaSICC dto = new DTOEntradaSICC();

		//obtenemos el codigoPais y codigoIsoIdioma
		dto.setCodigoPais(params.get("codigoPais").toString());

		//obtenemos el codigo Usuario
		Usuario usuario = (Usuario)params.get("usuario");
		dto.setCodigoUsuario(usuario.getLogin());

		//obtenemos el idioma ISO
		Idioma idioma = usuario.getIdioma();
		dto.setCodigoIsoIdioma(idioma.getCodigoISO());

		//obtenemos el codigo de Periodo y Fecha de Proceso
		dto.setCodigoPeriodo(params.get("codigoPeriodo").toString());
		dto.setFechaProceso(params.get("fechaProceso").toString());

		return dto;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getFechaFacturacion(java.util.Map)
	 */
	public String getFechaFacturacion(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getFechaFacturacion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getNumeroSecuenciaDocumento(java.util.Map)
	 */
	public String getNumeroSecuenciaDocumento(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getNumeroSecuenciaDocumento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getOidSolicitud(java.util.Map)
	 */
	public String getOidSolicitud(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getOidSolicitud(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#existePedido(java.util.Map)
	 */
	public int existePedido(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.existePedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoOCRCapturaPedidoService#getOidSolicitudPROL(java.util.Map)
	 */
	public String getOidSolicitudPROL(Map criteria){
		return mantenimientoOCRCapturaPedidoDAO.getOidSolicitudPROL(criteria);
	}
		
}