package biz.belcorp.ssicc.service.spisicc.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.ProcesoIMPGeneracionDocumentosLaserService;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/** 
* 
* <p>
* <a href="ProcesoIMPGeneracionDocumentosLaserServiceImpl.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:leonardo.lch@gmail.com">Leonardo Lizana Chauca</a>
* 
*/
@Service("spisicc.procesoIMPGeneracionDocumentosLaserService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPGeneracionDocumentosLaserServiceImpl  extends BaseService implements	ProcesoIMPGeneracionDocumentosLaserService {
	
	@Resource(name="spisicc.procesoIMPGeneracionDocumentosLaserDAO")
	ProcesoIMPGeneracionDocumentosLaserDAO procesoIMPGeneracionDocumentosLaserDAO;

	@Resource(name="spisicc.procesoImpresionLaserColorService")
	BaseProcesoImpresionService procesoImpresionLaserColorService;
	
	@Resource(name="spusicc.mantenimientoSTOBloqueoControlDAO")
	MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO;
	
	private VelocityEngine velocityEngine;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeGeneracionDocumentosLaser(java.util.Map)
	 */
	public void executeGeneracionDocumentosLaser(Map params) {
		String codigoPais= (String)params.get("codigoPais");
		String codigoSistema= Constants.SISTEMA_IMP;
		String nombreParametro="indLaserFacturaXML";

		String indicadorLaser = getParametroSistema(codigoPais,codigoSistema,nombreParametro);
	    //		
		generacionDocumentoDetallePedido(params);
		
		//se ejecuta desde oracle
		if(Constants.NUMERO_UNO.equals(indicadorLaser))
			generacionDocumentoLaserProcesoFacturaXML(params);	
		else	
		generacionDocumentoLaserFacturaXML(params);
		//
		
		generacionDocumentoLaserGuiaXML(params);
		generacionDocumentoLaserCuentaCorrienteXML(params);
		
		//se anhade un indicador para ejecutar la nota de credito
		nombreParametro="indLaserNotCredXML";
	    String indicadorNotCredito = getParametroSistema(codigoPais,codigoSistema,nombreParametro);
	    
	    
	    //se ejecuta desde oracle
  		if(Constants.NUMERO_UNO.equals(indicadorNotCredito))
  			generacionDocumentoLaserProcesoNotaCreditoXML(params);	
  		else	
		generacionDocumentoLaserNotaCreditoXML(params);
	  		//
	    
						
		generacionDocumentoLaserNotaDebitoXML(params);
		generacionDocumentoLaserBoletaDespachoXML(params);
		generacionDocumentoLaserOrdenCompraSimplificadaXML(params);
	}
	

	/**
	 * @param codigoPais
	 * @return
	 */
	private String getParametroSistema(String codigoPais,String codigoSistema,String nombreParametro) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", codigoPais);
		criteriaParam.put("codigoSistema", codigoSistema);
		criteriaParam.put("nombreParametro", nombreParametro);
	      //criteria.put("valorParametro", value);
	    String indicadorLaser = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParam);
		return indicadorLaser;
	}
	

	/**
	 * Ejecuta la invocacion a la nota de credito
	 * @param params
	 */
	private void generacionDocumentoLaserProcesoNotaCreditoXML(Map params) {
		params.put("nombreArchivo", "");
		params.put("directorio", "");
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentoLaserProcesoNotaCreditoXML(params);
	}


	/**
	 * Realiza la generacion de detalle de pedido
	 * @param params
	 */
	private void generacionDocumentoDetallePedido(Map params) {
		procesoIMPGeneracionDocumentosLaserDAO.executeProcesaDetallesFacturaLaser(params);		
	}

	/**
	 * Realiza la generacion de orden compra simplificada
	 * @param params
	 */
	private void generacionDocumentoLaserOrdenCompraSimplificadaXML(Map params) {
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentoLaserOrdenCompraSimplificadaXML(params);
		
	}

	/**
	 * Realiza la generacion de la boleta de despacho
	 * @param params
	 */
	private void generacionDocumentoLaserBoletaDespachoXML(Map params) {
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentoLaserBoletaDespachoXML(params);
		
	}

	/**
	 * Ejecuta el proceso desde un procedimiento almacenado
	 * @param params
	 */
	private void generacionDocumentoLaserProcesoFacturaXML(Map params) {
		params.put("nombreArchivo", "");
		params.put("directorio", "");
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentoLaserProcesoFacturaXML(params);
		
	}

	/**
	 * @param params
	 */
	private void generacionDocumentoLaserFacturaXML(Map params) {
		Calendar fechaEmisionCalenar =Calendar.getInstance();
		DateFormat formatter ;
		Date date ;
		Map criteria = new HashMap();
		Map model = new HashMap();
		
		model.put("numbertool", new NumberTool());
		model.put("mathtool",new MathTool());
		
		criteria.put("codProceso",Constants.PROCESO_IMPRESION_LASER);
		criteria.put("nomParametro",Constants.IMPRESION_LASER_NUMERO_DETALLES_FACTURA);
		
		int numRows = Integer.parseInt(procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria)); 
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneracionDocumentoLaserFactura(params);
		List documentoLaserFacturaCabeceraList =  procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserFacturaCabeceraList();
		try {
			for (Iterator iterator = documentoLaserFacturaCabeceraList.iterator(); iterator.hasNext();) {
				
				Map documentoLaserFacturaCabecera = (Map) iterator.next();
				
    	        formatter = new SimpleDateFormat("yyyy-MM-dd");
			    date =formatter.parse(MapUtils.getString(documentoLaserFacturaCabecera,"fecEmis")); 
			    fechaEmisionCalenar.setTime(date);
			    documentoLaserFacturaCabecera.put("dia",new Integer(fechaEmisionCalenar.get(Calendar.DATE)));
			    documentoLaserFacturaCabecera.put("mes",new Integer(fechaEmisionCalenar.get(Calendar.MONTH)+1));
			    documentoLaserFacturaCabecera.put("anio",new Integer(fechaEmisionCalenar.get(Calendar.YEAR)));
			    
 			    params.put("oidCabe", MapUtils.getString(documentoLaserFacturaCabecera,"oidCabe"));
 			    
				List documentoLaserFacuraDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserFacturaDetalleList(documentoLaserFacturaCabecera);
				
				if(documentoLaserFacuraDetalleList.size()>0){
					int totalPaginas = documentoLaserFacuraDetalleList.size()%numRows == 0 ? documentoLaserFacuraDetalleList.size()/numRows : documentoLaserFacuraDetalleList.size()/numRows+1;
					documentoLaserFacturaCabecera.put("totalPaginas", new Integer(totalPaginas));
					int pagina = 1;
					int rowDesde = 1;
				    int rowHasta = numRows;
				    
				    documentoLaserFacturaCabecera.put("pagina", new Integer(pagina));
				    documentoLaserFacturaCabecera.put("rowDesde", new Integer(rowDesde));
				    documentoLaserFacturaCabecera.put("rowHasta", new Integer(rowHasta));
				    boolean exitePaginas=true;
					while(exitePaginas){
						documentoLaserFacuraDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserFacturaDetalleList(documentoLaserFacturaCabecera);
						
						if(documentoLaserFacuraDetalleList.size()>0){
							model.put("documentoLaserFacturaCabecera", documentoLaserFacturaCabecera);
							model.put("documentoLaserFacturaDetalleList", documentoLaserFacuraDetalleList);
							String documentoLaserFacturaXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "generacionDocumentoLaserFactura.vm", model);
							
							params.put("codClie", MapUtils.getString(documentoLaserFacturaCabecera,"codClie"));
							params.put("documentoLaserXML", documentoLaserFacturaXML);
							params.put("pagina", new Integer(pagina));
							procesoIMPGeneracionDocumentosLaserDAO.saveDocumentoLaserFacturaXML(params);
							pagina++;
							rowDesde+=numRows;  
						    rowHasta+=numRows;
						    documentoLaserFacturaCabecera.put("pagina", new Integer(pagina));
							documentoLaserFacturaCabecera.put("rowDesde", new Integer(rowDesde));
						    documentoLaserFacturaCabecera.put("rowHasta", new Integer(rowHasta));
						}else{
							exitePaginas=false;
						}
					}
				}
		
					
			}
		} catch (VelocityException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentosLaserFacturaXML();
	}
	

	/**
	 * @param params
	 */
	private void generacionDocumentoLaserGuiaXML(Map params) {
		Calendar fechaEmisionCalenar =Calendar.getInstance();
		DateFormat formatter ;
		Date date ;
		Map criteria = new HashMap();
		criteria.put("codProceso",Constants.PROCESO_IMPRESION_LASER);
		criteria.put("nomParametro",Constants.IMPRESION_LASER_NUMERO_DETALLES_GUIA);
		int numRows = Integer.parseInt(procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria));
				
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneracionDocumentoLaserGuia(params);
		List documentoLaserGuiaCabeceraList =  procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserGuiaCabeceraList();
		try {
			for (Iterator iterator = documentoLaserGuiaCabeceraList.iterator(); iterator.hasNext();) {
				Map model = new HashMap();
				Map documentoLaserGuiaCabecera = (Map) iterator.next();
    	        formatter = new SimpleDateFormat("yyyy-MM-dd");
			    date =formatter.parse(MapUtils.getString(documentoLaserGuiaCabecera,"fecEmis")); 
			    
			    fechaEmisionCalenar.setTime(date);

			    documentoLaserGuiaCabecera.put("dia",new Integer(fechaEmisionCalenar.get(Calendar.DATE)));
			    documentoLaserGuiaCabecera.put("mes",new Integer(fechaEmisionCalenar.get(Calendar.MONTH)+1));
			    documentoLaserGuiaCabecera.put("anio",new Integer(fechaEmisionCalenar.get(Calendar.YEAR)));
			    
 			    params.put("oidCabe", MapUtils.getString(documentoLaserGuiaCabecera,"oidCabe"));
 			    
				List documentoLaserGuiaDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserGuiaDetalleList(documentoLaserGuiaCabecera);
				if(documentoLaserGuiaDetalleList.size()>0){
					int totalPaginas = documentoLaserGuiaDetalleList.size()%numRows == 0 ? documentoLaserGuiaDetalleList.size()/numRows : documentoLaserGuiaDetalleList.size()/numRows+1;
					documentoLaserGuiaCabecera.put("totalPaginas", new Integer(totalPaginas));
					int pagina = 1;
					int rowDesde = 1;
				    int rowHasta = numRows;
				    
				    documentoLaserGuiaCabecera.put("pagina", new Integer(pagina));
				    documentoLaserGuiaCabecera.put("rowDesde", new Integer(rowDesde));
				    documentoLaserGuiaCabecera.put("rowHasta", new Integer(rowHasta));
				    boolean exitePaginas=true;
					while(exitePaginas){
						documentoLaserGuiaDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserGuiaDetalleList(documentoLaserGuiaCabecera);
						if(documentoLaserGuiaDetalleList.size()>0){
							model.put("documentoLaserGuiaCabecera", documentoLaserGuiaCabecera);
							model.put("documentoLaserGuiaDetalleList", documentoLaserGuiaDetalleList);
							String documentoLaserGuiaXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "generacionDocumentoLaserGuia.vm", model);
							
							params.put("codClie", MapUtils.getString(documentoLaserGuiaCabecera,"codClie"));
							params.put("documentoLaserXML", documentoLaserGuiaXML);
							params.put("pagina", new Integer(pagina));
							procesoIMPGeneracionDocumentosLaserDAO.saveDocumentoLaserGuiaXML(params);
							pagina++;
							rowDesde+=numRows;
						    rowHasta+=numRows;
						    documentoLaserGuiaCabecera.put("pagina", new Integer(pagina));
							documentoLaserGuiaCabecera.put("rowDesde", new Integer(rowDesde));
						    documentoLaserGuiaCabecera.put("rowHasta", new Integer(rowHasta));
						}else{
							exitePaginas=false;
						}
					}
				}
		
					
			}
		} catch (VelocityException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentosLaseGuiaXML();	
		
	}
	
	/**
	 * @param params
	 */
	private void generacionDocumentoLaserCuentaCorrienteXML(Map params) {
		procesoIMPGeneracionDocumentosLaserDAO.deleteTablaDocumentoLaser();
		procesoIMPGeneracionDocumentosLaserDAO.deleteDocumentoLaserCuentaCorrienteXML();
		Map model = new HashMap();
		Map criteria = new HashMap();
		
		model.put("numbertool", new NumberTool());
		model.put("mathtool",new MathTool());
		
		criteria.put("codProceso",Constants.PROCESO_IMPRESION_LASER);
		criteria.put("nomParametro",Constants.IMPRESION_LASER_INDICADOR_GENERAR_CUENTA_CORRIENTE);
		String indicadorGenerarCtaCteLaser = procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria);
		
		if(indicadorGenerarCtaCteLaser.equals(Constants.IMPRESION_LASER_INDICADOR_GENERAR)){
			criteria.put("nomParametro",Constants.IMPRESION_LASER_NUMERO_DETALLES_CUENTA_CORRIENTE);
			int numRows = Integer.parseInt(procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria));
			
			int item =0;
			
			List documentoLaserCuentaCorrienteCabeceraList =  procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserCuentaCorrienteCabeceraList(params);
			
			try {
				for (Iterator iterator = documentoLaserCuentaCorrienteCabeceraList.iterator(); iterator.hasNext();) {
					Map documentoLaserCuentaCorrienteCabecera = (Map) iterator.next();
					
					params.put("oidClie", MapUtils.getString(documentoLaserCuentaCorrienteCabecera,"oidClie"));
							 			
		 			int pagina   = 1;
					int rowDesde = 1;
				    int rowHasta = numRows;
				  
				    documentoLaserCuentaCorrienteCabecera.put("rowDesde", new Integer(rowDesde));
				    documentoLaserCuentaCorrienteCabecera.put("rowHasta", new Integer(rowHasta));
				    				    
				    documentoLaserCuentaCorrienteCabecera.put("fechaFacturacion", params.get("fechaFacturacion"));
				    				    
				    float saldoActualConsultora  = Float.parseFloat(procesoIMPGeneracionDocumentosLaserDAO.getSaldoInicialCtaCte(documentoLaserCuentaCorrienteCabecera));				    
				    //float saldoActualConsultora = Float.parseFloat(procesoIMPGeneracionDocumentosLaserDAO.getSaldoActualConsultora(params));
		 			documentoLaserCuentaCorrienteCabecera.put("saldoActualConsultora", new Float(saldoActualConsultora));
				    
					List documentoLaserCuentaCorrienteDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserCuentaCorrienteDetalleList(documentoLaserCuentaCorrienteCabecera);
					
					if(documentoLaserCuentaCorrienteDetalleList.size()>0){
						model.put("documentoLaserCuentaCorrienteCabecera", documentoLaserCuentaCorrienteCabecera);
						model.put("documentoLaserCuentaCorrienteDetalleList", documentoLaserCuentaCorrienteDetalleList);
	
						String documentoLaserGuiaXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "generacionDocumentoLaserCuentaCorriente.vm", model);
						
						params.put("oidCabe", new Integer(item++));
						params.put("codClie", MapUtils.getString(documentoLaserCuentaCorrienteCabecera,"codClie"));
						params.put("documentoLaserXML", documentoLaserGuiaXML);
						params.put("pagina", new Integer(pagina));
						procesoIMPGeneracionDocumentosLaserDAO.saveDocumentoLaserCuentaCorrienteXML(params);
					}
				}
				}catch (VelocityException e) {
				e.printStackTrace();
			}
			procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentosLaserCuentaCorrienteXML();
		}			
		
	}
	
	private void generacionDocumentoLaserNotaCreditoXML(Map params) {
		Calendar fechaEmisionCalenar =Calendar.getInstance();
		DateFormat formatter ;
		Date date ;
		Map model = new HashMap();
		Map criteria = new HashMap();
		
		model.put("numbertool", new NumberTool());
		model.put("mathtool",new MathTool());
		
		criteria.put("codProceso",Constants.PROCESO_IMPRESION_LASER);
		criteria.put("nomParametro",Constants.IMPRESION_LASER_NUMERO_DETALLES_NOTA_CREDITO);
		
		int numRows = Integer.parseInt(procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria)); 
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneracionDocumentoLaserNotaCredito(params);
		List documentoLaserNotaCreditoCabeceraList =  procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaCreditoCabeceraList();
		try {
			for (Iterator iterator = documentoLaserNotaCreditoCabeceraList.iterator(); iterator.hasNext();) {
				
				Map documentoLaserNotaCreditoCabecera = (Map) iterator.next();
    	        formatter = new SimpleDateFormat("yyyy-MM-dd");
			    date =formatter.parse(MapUtils.getString(documentoLaserNotaCreditoCabecera,"fecEmis")); 
			    fechaEmisionCalenar.setTime(date);
			    documentoLaserNotaCreditoCabecera.put("dia",new Integer(fechaEmisionCalenar.get(Calendar.DATE)));
			    documentoLaserNotaCreditoCabecera.put("mes",new Integer(fechaEmisionCalenar.get(Calendar.MONTH)+1));
			    documentoLaserNotaCreditoCabecera.put("anio",new Integer(fechaEmisionCalenar.get(Calendar.YEAR)));
			    
 			    params.put("oidCabe", MapUtils.getString(documentoLaserNotaCreditoCabecera,"oidCabe"));
 			    
				List documentoLaserNotaCreditoDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaCreditoDetalleList(documentoLaserNotaCreditoCabecera);
				
				if(documentoLaserNotaCreditoDetalleList.size()>0){
					int totalPaginas = documentoLaserNotaCreditoDetalleList.size()%numRows == 0 ? documentoLaserNotaCreditoDetalleList.size()/numRows : documentoLaserNotaCreditoDetalleList.size()/numRows+1;
					documentoLaserNotaCreditoCabecera.put("totalPaginas", new Integer(totalPaginas));
					int pagina = 1;
					int rowDesde = 1;
				    int rowHasta = numRows;
				    
				    documentoLaserNotaCreditoCabecera.put("pagina", new Integer(pagina));
				    documentoLaserNotaCreditoCabecera.put("rowDesde", new Integer(rowDesde));
				    documentoLaserNotaCreditoCabecera.put("rowHasta", new Integer(rowHasta));
				    boolean exitePaginas=true;
					while(exitePaginas){
						documentoLaserNotaCreditoDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaCreditoDetalleList(documentoLaserNotaCreditoCabecera);
						
						if(documentoLaserNotaCreditoDetalleList.size()>0){
							model.put("documentoLaserNotaCreditoCabecera", documentoLaserNotaCreditoCabecera);
							model.put("documentoLaserNotaCreditoDetalleList", documentoLaserNotaCreditoDetalleList);
							String documentoLaserNotaCredioXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "generacionDocumentoLaserNotaCredito.vm", model);
							
							documentoLaserNotaCreditoCabecera.put("documentoLaserXML", documentoLaserNotaCredioXML);
							procesoIMPGeneracionDocumentosLaserDAO.saveDocumentoLaserNotaCreditoXML(documentoLaserNotaCreditoCabecera);
							pagina++;
							rowDesde+=numRows;  
						    rowHasta+=numRows;
						    documentoLaserNotaCreditoCabecera.put("pagina", new Integer(pagina));
						    documentoLaserNotaCreditoCabecera.put("rowDesde", new Integer(rowDesde));
						    documentoLaserNotaCreditoCabecera.put("rowHasta", new Integer(rowHasta));
						}else{
							exitePaginas=false;
						}
					}
				}
		
					
			}
		} catch (VelocityException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentosLaserNotaCretidoXML();
		
	}
	
	private void generacionDocumentoLaserNotaDebitoXML(Map params) {
		Calendar fechaEmisionCalenar =Calendar.getInstance();
		DateFormat formatter ;
		Date date ;
		Map model = new HashMap();
		Map criteria = new HashMap();
		
		model.put("numbertool", new NumberTool());
		model.put("mathtool",new MathTool());
		
		criteria.put("codProceso",Constants.PROCESO_IMPRESION_LASER);
		criteria.put("nomParametro",Constants.IMPRESION_LASER_NUMERO_DETALLES_NOTA_DEBITO);
		
		int numRows = Integer.parseInt(procesoIMPGeneracionDocumentosLaserDAO.getParametroPaginacionDetalle(criteria));
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneracionDocumentoLaserNotaDebito(params);
		List documentoLaserNotaDebitoCabeceraList =  procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaDebitoCabeceraList();
		try {
			for (Iterator iterator = documentoLaserNotaDebitoCabeceraList.iterator(); iterator.hasNext();) {
				
				Map documentoLaserNotaDebitoCabecera = (Map) iterator.next();
    	        formatter = new SimpleDateFormat("yyyy-MM-dd");
			    date =formatter.parse(MapUtils.getString(documentoLaserNotaDebitoCabecera,"fecEmis")); 
			    fechaEmisionCalenar.setTime(date);
			    documentoLaserNotaDebitoCabecera.put("dia",Integer.toString(fechaEmisionCalenar.get(Calendar.DATE)));
			    documentoLaserNotaDebitoCabecera.put("mes",Integer.toString(fechaEmisionCalenar.get(Calendar.MONTH)+1));
			    documentoLaserNotaDebitoCabecera.put("anio",Integer.toString(fechaEmisionCalenar.get(Calendar.YEAR)));

			    params.put("oidCabe", MapUtils.getString(documentoLaserNotaDebitoCabecera,"oidCabe"));
 			    
				List documentoLaserNotaDebitoDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaDebitoDetalleList(documentoLaserNotaDebitoCabecera);
				
				if(documentoLaserNotaDebitoDetalleList.size()>0){
					int totalPaginas = documentoLaserNotaDebitoDetalleList.size()%numRows == 0 ? documentoLaserNotaDebitoDetalleList.size()/numRows : documentoLaserNotaDebitoDetalleList.size()/numRows+1;
					documentoLaserNotaDebitoCabecera.put("totalPaginas", new Integer(totalPaginas));
					int pagina = 1;
					int rowDesde = 1;
				    int rowHasta = numRows;
				    
				    documentoLaserNotaDebitoCabecera.put("pagina", new Integer(pagina));
				    documentoLaserNotaDebitoCabecera.put("rowDesde", new Integer(rowDesde));
				    documentoLaserNotaDebitoCabecera.put("rowHasta", new Integer(rowHasta));
				    boolean exitePaginas=true;
					while(exitePaginas){
						documentoLaserNotaDebitoDetalleList = procesoIMPGeneracionDocumentosLaserDAO.getDocumentoLaserNotaDebitoDetalleList(documentoLaserNotaDebitoCabecera);
						
						if(documentoLaserNotaDebitoDetalleList.size()>0){
							model.put("documentoLaserNotaDebitoCabecera", documentoLaserNotaDebitoCabecera);
							model.put("documentoLaserNotaDebitoDetalleList", documentoLaserNotaDebitoDetalleList);
							String documentoLaserNotaDebitoXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "generacionDocumentoLaserNotaDebito.vm", model);
							
							documentoLaserNotaDebitoCabecera.put("documentoLaserXML", documentoLaserNotaDebitoXML);
							procesoIMPGeneracionDocumentosLaserDAO.saveDocumentoLaserNotaDebitoXML(documentoLaserNotaDebitoCabecera);
							pagina++;
							rowDesde+=numRows;  
						    rowHasta+=numRows;
						    documentoLaserNotaDebitoCabecera.put("pagina", new Integer(pagina));
						    documentoLaserNotaDebitoCabecera.put("rowDesde", new Integer(rowDesde));
						    documentoLaserNotaDebitoCabecera.put("rowHasta", new Integer(rowHasta));
						}else{
							exitePaginas=false;
						}
					}
				}
		
					
			}
		} catch (VelocityException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		procesoIMPGeneracionDocumentosLaserDAO.generacionDocumentosLaserNotaDebitoXML();
		
	}

	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @param velocityEngine the velocityEngine to set
	 */
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeGeneracionDocumentosLaserColor(java.util.Map)
	 */
	public void executeGeneracionDocumentosLaserColor(Map params) {
		//caraga las secciones de color en sus temporaels respectivos
		procesoIMPGeneracionDocumentosLaserDAO.executeGeneracionDocumentosLaserColor(params);
		 // Obtengo la Interfaz a ejecutar partir de los parametros del Map
        String codigo = (String) params.get("codigoProcesoImpresion");
        log.debug("codigo impresion "+ codigo);
        Usuario usuario = (Usuario) params.get("usuario");
        
        procesoImpresionLaserColorService.executeProcesoImpresion(params, usuario);
		
	}

	/**
	 * @return the procesoImpresionLaserColorService
	 */
	public BaseProcesoImpresionService getProcesoImpresionLaserColorService() {
		return procesoImpresionLaserColorService;
	}

	/**
	 * @param procesoImpresionLaserColorService the procesoImpresionLaserColorService to set
	 */
	public void setProcesoImpresionLaserColorService(
			BaseProcesoImpresionService procesoImpresionLaserColorService) {
		this.procesoImpresionLaserColorService = procesoImpresionLaserColorService;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#validarFormatoArchivoExcel(java.util.Map)
	 */
	public boolean validarFormatoArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Integer numeroCampos = (Integer)criteria.get("numeroCampos");
		
		boolean valor = true;
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();		
			 if(mapRow.size() != (numeroCampos.intValue() + 1)) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeValidarSeccionVentas(java.util.Map)
	 */
	public boolean executeValidarSeccionVentas(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = new ArrayList();
		int numfila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRowSeccionVentas(excelUtil.next());
			String numeroConcurso = mapRow.get("0").toString();
			
			//No se valida informacion de los registros porque solo se cargara los correspondiente
			//a la campaa y concurso seleccionado
		}
		excelUtil.cerrar();
		
		Integer totalActivos = procesoIMPGeneracionDocumentosLaserDAO.getCantidadSeccionVentas(criteria);
		
		if(totalActivos.intValue() == 0)
			return true;
		else
			return false;
		
	}	
	
	/**
	 * se encarga de parsear el valor correcto de los campos codigos que vengan con (.)
	 * @param next
	 * @return
	 */
	private Map getParseRowSeccionVentas(Map mapRow) {
		 String codigoPeriodo =(String)mapRow.get("1");
		 String codigoPeriodoFinal =(String)mapRow.get("3");

		 mapRow.put("1",codigoPeriodo.substring(0,codigoPeriodo.indexOf(".")!=-1?codigoPeriodo.indexOf("."):codigoPeriodo.length()));
		 mapRow.put("3",codigoPeriodoFinal.substring(0,codigoPeriodoFinal.indexOf(".")!=-1?codigoPeriodoFinal.indexOf("."):codigoPeriodoFinal.length()));
		 
		 return mapRow;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeCargaSeccionVentas(java.util.Map)
	 */
	public void executeCargaSeccionVentas(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
		
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String numeroConcurso = (String)criteria.get("numeroConcurso");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = new ArrayList();
		
		//borramos la lista de registros activos para la campaa y concurso seleccionado
		procesoIMPGeneracionDocumentosLaserDAO.deleteSeccionVentas(criteria);

		while(excelUtil.hasNext()) { 
			Map mapRow = getParseRowSeccionVentas(excelUtil.next());//caso de que las columnas vengan tipo numericos en region y zona
			mapRow.put("codigoUsuario",usuario.getLogin());

			String codigoPeriodoRow = (String)mapRow.get("1");
			String numeroConcursoRow = (String)mapRow.get("2");
			
			if(codigoPeriodo.equalsIgnoreCase(codigoPeriodoRow) && numeroConcurso.equalsIgnoreCase(numeroConcursoRow))
				list.add(mapRow);	
		}
		excelUtil.cerrar();
		
		//hacemos el proceso de registro en batch
		procesoIMPGeneracionDocumentosLaserDAO.insertListSeccionVentas(list);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeValidarSeccionCompartamos(java.util.Map)
	 */
	public boolean executeValidarSeccionCompartamos(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String codigoRegion = (String)criteria.get("codigoRegion");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoRegion", codigoRegion);
		
		List list = new ArrayList();
		int numfila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRowSeccionCompartamos(excelUtil.next());
			
			String codigoPeriodoRow = (String)mapRow.get("1");
			if(!codigoPeriodo.equalsIgnoreCase(codigoPeriodoRow))
				continue;
			
			String codigoZona = mapRow.get("2").toString();
			criteriaAux.put("codigoZona", codigoZona);

			//Validamos 
			String codigoValidacion = procesoIMPGeneracionDocumentosLaserDAO.getValidacionSeccionCompartamos(criteriaAux);
			
			//validaciones
			String mensajeError=null;
			 
			//codigo de Zona no existe en Maestro de Zonas
			if(codigoValidacion.equalsIgnoreCase("01")) {
			 	mensajeError = messageSource.getMessage("procesoIMPCargaSeccionCompartamosForm.error.noExisteZona",
			 						new String[]{codigoZona},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			//Codigo de Zona no corresponde a region seleccionado
			if(codigoValidacion.equalsIgnoreCase("02")) {
			 	mensajeError = messageSource.getMessage("procesoIMPCargaSeccionCompartamosForm.error.zonaNoPerteneceRegion",
			 						new String[]{codigoZona},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			
		}
		excelUtil.cerrar();
		
		Integer totalActivos = procesoIMPGeneracionDocumentosLaserDAO.getCantidadSeccionCompartamos(criteria);
		
		if(totalActivos.intValue() == 0)
			return true;
		else
			return false;
		
	}	
	
	/**
	 * se encarga de parsear el valor correcto de los campos codigos que vengan con (.)
	 * @param next
	 * @return
	 */
	private Map getParseRowSeccionCompartamos(Map mapRow) {
		 String codigoPeriodo =(String)mapRow.get("1");
		 String codigoZona =(String)mapRow.get("2");

		 mapRow.put("1",codigoPeriodo.substring(0,codigoPeriodo.indexOf(".")!=-1?codigoPeriodo.indexOf("."):codigoPeriodo.length()));
		 mapRow.put("2",codigoZona.substring(0,codigoZona.indexOf(".")!=-1?codigoZona.indexOf("."):codigoZona.length()));
		 
		 return mapRow;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeCargaSeccionCompartamos(java.util.Map)
	 */
	public void executeCargaSeccionCompartamos(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
		
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String codigoRegion = (String)criteria.get("codigoRegion");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = new ArrayList();
		
		//borramos la lista de registros activos para la campaa y region seleccionado
		procesoIMPGeneracionDocumentosLaserDAO.deleteSeccionCompartamos(criteria);

		while(excelUtil.hasNext()) { 
			Map mapRow = getParseRowSeccionCompartamos(excelUtil.next());//caso de que las columnas vengan tipo numericos en region y zona
			mapRow.put("codigoUsuario",usuario.getLogin());

			String codigoPeriodoRow = (String)mapRow.get("1");
			if(codigoPeriodo.equalsIgnoreCase(codigoPeriodoRow))
				list.add(mapRow);	
		}
		excelUtil.cerrar();
		
		//hacemos el proceso de registro en batch
		procesoIMPGeneracionDocumentosLaserDAO.insertListSeccionCompartamos(list);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeValidarPlanPiloto(java.util.Map)
	 */
	public boolean executeValidarPlanPiloto(Map criteria) {
		Integer totalActivos = procesoIMPGeneracionDocumentosLaserDAO.getCantidadPlanPiloto(criteria);
		
		if(totalActivos.intValue() == 0)
			return true;
		else
			return false;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeInsertPlanPiloto(java.util.List)
	 */
	public void executeInsertPlanPiloto(List list) throws Exception {
		String codigoPeriodo = "";
		
		//obtenenemos la campaa
		if(list.size() > 0) {
			Map mapRow = (Map)list.get(0);
			codigoPeriodo = (String)mapRow.get("codigoPeriodo");
		}

		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		//borramos la lista de registros activos para la campaa y region seleccionado
		procesoIMPGeneracionDocumentosLaserDAO.deletePlanPiloto(criteria);

		//hacemos el proceso de registro en batch
		procesoIMPGeneracionDocumentosLaserDAO.insertListPlanPiloto(list);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeValidarSeccionFocalizados(java.util.Map)
	 */
	public boolean executeValidarSeccionFocalizados(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		Map criteriaAux = new HashMap();
		
		List list = new ArrayList();
		int numfila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = getParseRowSeccionFocalizados(excelUtil.next());
			
			String codigoPeriodoRow = (String)mapRow.get("1");
			if(!codigoPeriodo.equalsIgnoreCase(codigoPeriodoRow))
				continue;
			
			String codigoZona = mapRow.get("2").toString();
			criteriaAux.put("codigoZona", codigoZona);

			//Validamos 
			String codigoValidacion = procesoIMPGeneracionDocumentosLaserDAO.getValidacionSeccionFocalizados(criteriaAux);
			
			//validaciones
			String mensajeError=null;
			 
			//codigo de Zona no existe en Maestro de Zonas
			if(codigoValidacion.equalsIgnoreCase("01")) {
			 	mensajeError = messageSource.getMessage("procesoIMPCargaSeccionFocalizadosForm.error.noExisteZona",
			 						new String[]{codigoZona},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			//Codigo de Zona es de Tipo Oficina
			if(codigoValidacion.equalsIgnoreCase("02")) {
			 	mensajeError = messageSource.getMessage("procesoIMPCargaSeccionFocalizadosForm.error.zonaEsOficina",
			 						new String[]{codigoZona},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			
		}
		excelUtil.cerrar();
		
		Integer totalActivos = procesoIMPGeneracionDocumentosLaserDAO.getCantidadSeccionFocalizados(criteria);
		
		if(totalActivos.intValue() == 0)
			return true;
		else
			return false;
		
	}	
	
	/**
	 * se encarga de parsear el valor correcto de los campos codigos que vengan con (.)
	 * @param next
	 * @return
	 */
	private Map getParseRowSeccionFocalizados(Map mapRow) {
		 String codigoPeriodo =(String)mapRow.get("1");
		 String codigoZona =(String)mapRow.get("2");

		 mapRow.put("1",codigoPeriodo.substring(0,codigoPeriodo.indexOf(".")!=-1?codigoPeriodo.indexOf("."):codigoPeriodo.length()));
		 mapRow.put("2",codigoZona.substring(0,codigoZona.indexOf(".")!=-1?codigoZona.indexOf("."):codigoZona.length()));
		 
		 return mapRow;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.ProcesoIMPGeneracionDocumentosLaserService#executeCargaSeccionFocalizados(java.util.Map)
	 */
	public void executeCargaSeccionFocalizados(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
		
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String codigoRegion = (String)criteria.get("codigoRegion");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = new ArrayList();
		
		//borramos la lista de registros activos para la campaa seleccionado
		procesoIMPGeneracionDocumentosLaserDAO.deleteSeccionFocalizados(criteria);

		while(excelUtil.hasNext()) { 
			Map mapRow = getParseRowSeccionFocalizados(excelUtil.next());//caso de que las columnas vengan tipo numericos en region y zona
			mapRow.put("codigoUsuario",usuario.getLogin());

			String codigoPeriodoRow = (String)mapRow.get("1");
			if(codigoPeriodo.equalsIgnoreCase(codigoPeriodoRow))
				list.add(mapRow);	
		}
		excelUtil.cerrar();
		
		//hacemos el proceso de registro en batch
		procesoIMPGeneracionDocumentosLaserDAO.insertListSeccionFocalizados(list);
	}
}
