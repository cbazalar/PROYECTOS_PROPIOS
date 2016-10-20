/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesoRECAtencionesMasivas;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECAtencionesMasivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECAtencionesMasivasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author esanchez@sigcomt.com - Eduardo Snchez
 *
 */
@Service("spusicc.mantenimientoRECAtencionesMasivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECAtencionesMasivasServiceImpl extends BaseService implements MantenimientoRECAtencionesMasivasService{

	@Resource(name="spusicc.mantenimientoRECAtencionesMasivasDAO")
	MantenimientoRECAtencionesMasivasDAO mantenimientoRECAtencionesMasivasDAO;	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getMantenimientoRECAtencionesMasivas(biz.belcorp.ssicc.model.ProcesoRECAtencionesMasivas)
	 */
	public Map getMantenimientoRECAtencionesMasivas(ProcesoRECAtencionesMasivas procesoRECAtencionesMasivas) throws Exception {

		List list = null;
		int numfila = 0;
		int cantExis = 0;
		int cantNoExis = 0;
		
		try{			
			File archivo = procesoRECAtencionesMasivas.getArchivo();
			InputStream is = new FileInputStream(archivo);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String valTipoProducto = procesoRECAtencionesMasivas.getTipoProducto();
			String valTipoAtencion = procesoRECAtencionesMasivas.getTipoAtencion();
			String codTipoOper = procesoRECAtencionesMasivas.getTipoOperacion();
			String codigoPeriodo = procesoRECAtencionesMasivas.getCodigoPeriodoProceso(); 
			String codigoPeriodoReferencia = procesoRECAtencionesMasivas.getCodigoPeriodoReferencia();
			
			log.debug("Tipo Producto : "+valTipoProducto);
			log.debug("Tipo Atencin : "+valTipoAtencion);
			log.debug("Tipo Operacion : "+codTipoOper);
			
			String codigoConsultora = "";
			String codigoVenta = "";
			String cuv = "";
			String cantidad = "";

			Map criteria = new HashMap();
			List datosList = new ArrayList();
			String existe = "";
		    	    
			//String errorConsultora = messageResources.getMessage("mantenimientoRECAtencionesMasivasForm.error.consultora.noExiste");
			//String errorCuvVenta = messageResources.getMessage("mantenimientoRECAtencionesMasivasForm.error.cuv.venta.noExiste");
			//String errorCuvPremio = messageResources.getMessage("mantenimientoRECAtencionesMasivasForm.error.cuv.premio.noExiste");
			//String errorCuvPremioNoUno = messageResources.getMessage("mantenimientoRECAtencionesMasivasForm.error.cuv.premio.masDeUno");
			
			String errorConsultora = "CONSULTORA no existe";
			String errorCuvVenta = "CUV VENTA no existe";
			String errorCuvPremio = "CUV PREMIO no existe";
			String errorCuvPremioNoUno = "CUV PREMIO tiene mas de un cdigo SAP no se puede enviar por masivo";
		
			File file = new File(procesoRECAtencionesMasivas.getArchivo().getName());// Aqui le dan el nombre y/o con la ruta del archivo salida
					
			// Abrimos el archivo Excel para su procesamiento
			InputStream entrada = new FileInputStream(procesoRECAtencionesMasivas.getArchivo());
					
			OutputStream salida = new FileOutputStream(file);
			byte[] buf = new byte[1024];// Actualizado me olvide del 1024
			int len;
			
			while ((len = entrada.read(buf)) > 0) {
			salida.write(buf, 0, len);
			}
			salida.close();
			entrada.close();
			
			ExcelUtil excelUtil = new ExcelUtil(file);
			// nos colocamos en la primera hora del documento Excel
			excelUtil.initSheet(0);
			
			// limpiamos la tabla GTT
			this.eliminarAtencionesMasivas();
			
			while (excelUtil.hasNext()) {

				Map mapRow = excelUtil.next();
	
				if (list == null)
					list = new ArrayList();
	
				if (numfila > 0) {
	
					if (mapRow.get("0") != null && !mapRow.get("0").equals("")) {
						codigoConsultora = (String) mapRow.get("0");
					}
	
					if (mapRow.get("1") != null && !mapRow.get("1").equals("")) {
						codigoVenta = (String) mapRow.get("1");
					}
					
					if (mapRow.get("2") != null && !mapRow.get("2").equals("")) {
						cantidad = (String) mapRow.get("2");
						cantidad = String.valueOf(Double.valueOf(cantidad).longValue());
					}
					
					criteria = new HashMap();
					
					criteria.put("codigoConsultora", codigoConsultora);
					criteria.put("codigoVenta", codigoVenta);
					criteria.put("cantidad", cantidad);
					criteria.put("codigoPeriodo", codigoPeriodo);
					criteria.put("codigoPeriodoReferencia", codigoPeriodoReferencia);
					
					// validacion Si consultora Existe
					existe = this.getValidarCodigoConsultora(criteria);
					log.debug("Existe: " + existe);
					
					if (StringUtils.equals(existe, Constants.UNO)) {
						criteria.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE); //0
					} else {
						// validar que se ingres el nmero de documento de identidad de la consultora
						String documentoIdentidad = codigoConsultora;
						String codigoConsultoraPorDNI = this.getObtenerCodigoConsultora(documentoIdentidad);
						
						if (StringUtils.isBlank(codigoConsultoraPorDNI)){
							// validacion Si consultora Existe
							criteria.put("estadoProceso", Constants.CODIGO_CONSULTORA_NO_EXISTE); //9
							cantNoExis++;
						}else{
							// consultora Existe
							codigoConsultora = codigoConsultoraPorDNI;
							criteria.put("codigoConsultora", codigoConsultora);
							criteria.put("estadoProceso", Constants.CODIGO_CONSULTORA_EXISTE); //0
						}					
						
					}
					
					// validacion pedido
					if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_CONSULTORA_EXISTE)) {
						//Continuamos con las validaciones
						
						if(StringUtils.equals(valTipoProducto, "matriz")){
							datosList = this.getAtencionesMasivasPorMatrizList(criteria);
						}
						
						if(StringUtils.equals(valTipoProducto, "premio")){
							datosList = this.getAtencionesMasivasPorPremioList(criteria);
						}
						
						if(StringUtils.equals(valTipoProducto, "matrizPedido")){
							datosList = this.getAtencionesMasivasPorMatrizList(criteria);
						}
						
						if(!datosList.isEmpty()){
							
							int tamanio = datosList.size();
							if(StringUtils.equals(valTipoProducto, "matriz")){
								criteria.put("estadoProceso", Constants.CODIGO_VENTA_EXISTE);
							}
							
							if(StringUtils.equals(valTipoProducto, "premio")){
								//Existe mas de uno?
								if(tamanio == Constants.FLAG_DEFAULT){
									criteria.put("estadoProceso", Constants.CODIGO_VENTA_EXISTE);
								}else{
									criteria.put("estadoProceso", Constants.CODIGO_VENTA_PREMIO_MAS_DE_UNO);
									cantNoExis++;
								}
							}
							
							if(StringUtils.equals(valTipoProducto, "matrizPedido")){
								criteria.put("estadoProceso", Constants.CODIGO_VENTA_EXISTE);
							}
							
						}else{
							if(StringUtils.equals(valTipoProducto, "matriz")){
								criteria.put("estadoProceso", Constants.CODIGO_VENTA_MATRIZ_NO_EXISTE);
							}
							
							if(StringUtils.equals(valTipoProducto, "premio")){
								criteria.put("estadoProceso", Constants.CODIGO_VENTA_PREMIO_NO_EXISTE);
							}
							
							if(StringUtils.equals(valTipoProducto, "matrizPedido")){
								criteria.put("estadoProceso", Constants.CODIGO_VENTA_MATRIZ_NO_EXISTE);
							}
							cantNoExis++;
						}
						
						if(StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_VENTA_EXISTE)){
							for(int i=0; i<datosList.size(); i++) {
								Map mapDatos = (Map)datosList.get(i);
								
								double precio = ((BigDecimal)mapDatos.get("precio")).doubleValue();
								double precioContable = ((BigDecimal)mapDatos.get("precioContable")).doubleValue();
								
								criteria.put("precio", precio);
								criteria.put("precioContable", precioContable);
								criteria.put("codigoSAP", (String)mapDatos.get("codigoSAP"));
								criteria.put("descripcionProducto", (String)mapDatos.get("descripcionProducto"));
								criteria.put("idTipoOferta",mapDatos.get("idTipoOferta"));
								criteria.put("idProducto", mapDatos.get("idProducto"));
								criteria.put("idMatrizFacturacion", mapDatos.get("idMatrizFacturacion"));
								criteria.put("idOperacionReclamo", mapDatos.get("idOperacionReclamo"));
								criteria.put("idSolicitud", mapDatos.get("idSolicitud"));
								criteria.put("idParametroNivel", mapDatos.get("idParametroNivel"));
								criteria.put("idLoteArticulo", mapDatos.get("idLoteArticulo"));
								criteria.put("idTipoConcurso", mapDatos.get("idTipoConcurso"));
								criteria.put("idDetalleOferta", mapDatos.get("idDetalleOferta"));
								criteria.put("idFormaPago",mapDatos.get("idFormaPago"));
								
							}
							cantExis++;
							
						}
						
						if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_VENTA_EXISTE)) {
							log.debug("Insertando en tabla con Consultora Vlida y CUV correcto");
							criteria.put("error", null);
							//this.insertAtencionesMasivasConsultoraVenta(criteria);
						}
					}						
				
					if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_CONSULTORA_NO_EXISTE)) {
						criteria.put("error", errorConsultora);
						log.debug("Insertando en tabla con error: " + errorConsultora);
						//this.insertAtencionesMasivasError(criteria);
					}
					
					if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_VENTA_MATRIZ_NO_EXISTE)) {
						criteria.put("error", errorCuvVenta);
						log.debug("Insertando en tabla con error: " + errorCuvVenta);
						//this.insertAtencionesMasivasError(criteria);
					}
					
					if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_VENTA_PREMIO_NO_EXISTE)) {
						criteria.put("error", errorCuvPremio);
						log.debug("Insertando en tabla con error: " + errorCuvPremio);
						//this.insertAtencionesMasivasError(criteria);
					}
					
					if (StringUtils.equals((String) criteria.get("estadoProceso"), Constants.CODIGO_VENTA_PREMIO_MAS_DE_UNO)) {
						criteria.put("error", errorCuvPremioNoUno);
						log.debug("Insertando en tabla con error: " + errorCuvPremioNoUno);
						//this.insertAtencionesMasivasError(criteria);
					}
	
					list.add(criteria);
					
					codigoConsultora = "";
					codigoVenta = "";
					cantidad = "";
				}
	
				numfila++;
			}
				
			excelUtil.cerrar();
		
		}catch(Exception e){
			e.printStackTrace();			
			if(e instanceof InvalidUploadException){
				throw e;
			}else{
				throw new InvalidUploadException("archivo XLS","error");
			}			
		}
		
		Map ret = new HashMap();
		
		ret.put("list", list);
		ret.put("numfila", String.valueOf(numfila));
		ret.put("cantExis", String.valueOf(cantExis));
		ret.put("cantNoExis", String.valueOf(cantNoExis));
		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#procesarRECAtencionesMasivas(java.util.Map)
	 */
	public Map executeProcesarRECAtencionesMasivas(Map criteria) {
		
		String numeroLote = this.getNumeroLoteAtencionesMasivas();
		criteria.put("numeroLote", numeroLote);
		
		//this.procesarIngresoAtencionesMasivas(criteria);
		List list =  (List)criteria.get("listaMasivas");
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map map = (Map)it.next();
			String error = (String) map.get("error");
			if(StringUtils.isNotEmpty(error)){
				mantenimientoRECAtencionesMasivasDAO.insertAtencionesMasivasError(map);
			}else{
				mantenimientoRECAtencionesMasivasDAO.insertAtencionesMasivasConsultoraVenta(map);
			}
		}
		
		this.mantenimientoRECAtencionesMasivasDAO.procesarIngresoAtencionesMasivas(criteria);
		List lista = this.getGTTDetalleIngresoAtencionesMasivasList(criteria);
		
		log.debug("executeProcesarRECAtencionesMasivas sizeList = "+ (lista!=null?lista.size():-1));
		criteria.put("lista", lista);
		
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#eliminarAtencionesMasivas()
	 */
	public void eliminarAtencionesMasivas() {
		mantenimientoRECAtencionesMasivasDAO.eliminarAtencionesMasivas();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#insertAtencionesMasivasError(java.util.Map)
	 */
	public void insertAtencionesMasivasError(Map criteria) {
		mantenimientoRECAtencionesMasivasDAO.insertAtencionesMasivasError(criteria);
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getValidarCodigoConsultora(java.util.Map)
	 */
	public String getValidarCodigoConsultora(Map criteria) {
		return this.mantenimientoRECAtencionesMasivasDAO.getValidarCodigoConsultora(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getAtencionesMasivasPorMatrizList(java.util.Map)
	 */
	public List getAtencionesMasivasPorMatrizList(Map criteria) {
		return mantenimientoRECAtencionesMasivasDAO.getAtencionesMasivasPorMatrizList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getAtencionesMasivasPorPremioList(java.util.Map)
	 */
	public List getAtencionesMasivasPorPremioList(Map criteria) {
		return mantenimientoRECAtencionesMasivasDAO.getAtencionesMasivasPorPremioList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#insertAtencionesMasivasConsultoraVenta(java.util.Map)
	 */
	public void insertAtencionesMasivasConsultoraVenta(Map criteria) {
		mantenimientoRECAtencionesMasivasDAO.insertAtencionesMasivasConsultoraVenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getNumeroLoteAtencionesMasivas()
	 */
	public String getNumeroLoteAtencionesMasivas() {
		return this.mantenimientoRECAtencionesMasivasDAO.getNumeroLoteAtencionesMasivas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#procesarIngresoAtencionesMasivas(java.util.Map)
	 */
	public String procesarIngresoAtencionesMasivas(Map criteria) {
		return this.mantenimientoRECAtencionesMasivasDAO.procesarIngresoAtencionesMasivas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getGTTDetalleIngresoAtencionesMasivasList(java.util.Map)
	 */
	public List getGTTDetalleIngresoAtencionesMasivasList(Map criteria) {
		return mantenimientoRECAtencionesMasivasDAO.getGTTDetalleIngresoAtencionesMasivasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getObtenerCodigoConsultora(java.lang.String)
	 */
	public String getObtenerCodigoConsultora(String documentoIdentidad) {
		return this.mantenimientoRECAtencionesMasivasDAO.getObtenerCodigoConsultora(documentoIdentidad);
	}
	
	
	
}