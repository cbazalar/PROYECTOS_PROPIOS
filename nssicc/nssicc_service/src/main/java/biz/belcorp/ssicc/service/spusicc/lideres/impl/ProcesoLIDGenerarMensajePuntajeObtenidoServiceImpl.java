package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDGenerarMensajePuntajeObtenidoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarMensajePuntajeObtenidoService;

/**
 * @author Leonardo Lizana
 *
 */
@Service("spusicc.procesoLIDGenerarMensajePuntajeObtenidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDGenerarMensajePuntajeObtenidoServiceImpl extends BaseService implements
		ProcesoLIDGenerarMensajePuntajeObtenidoService {
	
	@Resource(name="spusicc.procesoLIDGenerarMensajePuntajeObtenidoDAO")
	ProcesoLIDGenerarMensajePuntajeObtenidoDAO procesoLIDGenerarMensajePuntajeObtenidoDAO;
	
	@Resource(name="velocityEngine")
	private VelocityEngine velocityEngine; 
	 
	public void executeGenerarMensajePuntajeObtenido(Map params) {
		this.procesoLIDGenerarMensajePuntajeObtenidoDAO.executeGenerarMensajePuntajeObtenido(params);
		List puntajeObtenidoList = this.procesoLIDGenerarMensajePuntajeObtenidoDAO.getPuntajeObtenidoList();
		String plantilla = null;
		
		//Verificamos el indicador Pais Marca, si es Esika o Lbel
		Pais pais = (Pais)params.get("pais");
		log.debug("Indicador Pais Marca (" + pais.getCodigo() + ") : " + pais.getIndicadorPaisMarca());
		if(pais.getIndicadorPaisMarca().equalsIgnoreCase(Constants.MARCA_LBEL))
			plantilla = "generMensajePuntajeObtenido.vm";
		if(pais.getIndicadorPaisMarca().equalsIgnoreCase(Constants.MARCA_ESIKA))
			plantilla = "generMensajePuntajeObtenidoEsika.vm";	
		
		Iterator i = puntajeObtenidoList.iterator();
        try{  
        	this.procesoLIDGenerarMensajePuntajeObtenidoDAO.truncateGenerarMensajePuntajeObtenido(params);
	        while (i.hasNext()) { 
	            Map puntajeObtenido = (Map) i.next();
	            List mensajeList = new ArrayList(); 
	            	
        		String codigoCliente=      	  MapUtils.getString(puntajeObtenido,"codigoCliente");
        		String mensaje=      	  MapUtils.getString(puntajeObtenido,"mensaje");
        		
	            StringTokenizer stFilas = new StringTokenizer(mensaje, "||");
	            while(stFilas.hasMoreTokens()) {
	            	String filaMensaje = stFilas.nextToken();
	            	
	            	Map mapFila = new HashMap();
	            	StringTokenizer stColumnas = new StringTokenizer(filaMensaje, "__");
	            	
	            	String numConcurso=      	  stColumnas.nextToken();
	            	String nombreConcurso=        stColumnas.nextToken();      
	        		String valPuntajeAcumulado=   stColumnas.nextToken();
	        		String valPuntajeCanjeado=    stColumnas.nextToken();    
	        		String valPuntajeDisponible=  stColumnas.nextToken();    

	        		mapFila.put("numConcurso", numConcurso);
	        		mapFila.put("nombreConcurso", nombreConcurso);
		            mapFila.put("valPuntajeAcumulado", valPuntajeAcumulado);
		            mapFila.put("valPuntajeCanjeado", valPuntajeCanjeado);
		            mapFila.put("valPuntajeDisponible", valPuntajeDisponible);
		            
		            mensajeList.add(mapFila);
	            }
	            
	            Map model = new HashMap();
	            model.put("mensajeList", mensajeList);
	            String puntajeObtenidoXML = VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, plantilla, model);
	            
	            params.put("codigoCliente", codigoCliente);
	            params.put("puntajeObtenidoXML", puntajeObtenidoXML);
	            this.procesoLIDGenerarMensajePuntajeObtenidoDAO.saveMensajePuntajeObtenido(params);
	        
			}
		} catch (VelocityException e) {
			this.log.debug("Error en Velocity : ");
			e.printStackTrace();
		}
	}



}
