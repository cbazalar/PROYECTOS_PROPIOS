/*
 * Created on 26/12/2005 11:39:41 AM
 * biz.belcorp.ssicc.scdf.service.impl.UltimasNoticiasServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.GeneracionDetallePedidoDAO;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.GeneracionDetallePedidoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.generacionDetallePedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GeneracionDetallePedidoServiceImpl extends BaseService implements GeneracionDetallePedidoService {

	@Resource(name="spisicc.generacionDetallePedidoDAO")
    private GeneracionDetallePedidoDAO generacionDetallePedidoDAO;

    private VelocityEngine velocityEngine;

    @Resource(name="spisicc.procesoIMPGeneracionDocumentosLaserDAO")
	private ProcesoIMPGeneracionDocumentosLaserDAO procesoIMPGeneracionDocumentosLaserDAO;

    /**
     * @return Returns the velocityEngine.
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    /**
     * @param velocityEngine
     *            The velocityEngine to set.
     */
    @Autowired
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }


    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spisicc.service.GeneracionDetallePedidoService#executeGenerarDetallePedido(java.util.Map)
     */
    public void executeGenerarDetallePedido(Map params) {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'executeGenerarDetallePedido'");
        }
        
        // Invocamos al procedimiento encargado de generar el formato 
        // Este proceso reemplaza la generacion a traves de la plantilla velocity
        procesoIMPGeneracionDocumentosLaserDAO.executeProcesaDetallesFacturaLaser(params);
        
        // Se comenta toda la generacion a travs de velocity CHR (25/05/2010)
        /*
        String codigoPais = MapUtils.getString(params, "codigoPais");
        
        // Eliminamos la informacion anterior
        generacionDetallePedidoDAO.executeEliminarDetallesPedidos(); 
        generacionDetallePedidoDAO.executeEliminarXMLDetallePedido();
        generacionDetallePedidoDAO.executeCargarCabeceraDetallePedidos(params);
        
        // Obtenemos la relacion de consultoras que han pasado pedido
        List consultoras = generacionDetallePedidoDAO.getConsultoras();
        Iterator i = consultoras.iterator();
        //StringBuffer str_ArchivoXML= new StringBuffer();
        //str_ArchivoXML.append("");
       try{ 
	        while (i.hasNext()) {
	            // Creamos el map a ser pasado a la plantilla Velocity
	            Map model = new HashMap();
	            // Obtenemos la informacion de la consultora
	            Map consultora = (Map) i.next();
	            String idCabeceraSolicitud = MapUtils.getString(consultora,"ID_CABEC_SOLICITUD");
	            String codigoConsultora = MapUtils.getString(consultora,"CODIGO_CONSULTORA");
	            String anoFacturacion = MapUtils.getString(consultora,"ANO_FACTURACION");
	            String numeroSolicitud = MapUtils.getString(consultora,"NUMERO_PEDIDO");
	            String ultimoDigAno= anoFacturacion.substring(2,4);
	            model.put("ultimoDigAno", ultimoDigAno);
	            model.put("codigoPais", codigoPais);
	            
	            if (log.isDebugEnabled()) {
	                log.debug("Procesando la informacion de la consultora '"
	                        + idCabeceraSolicitud + "'");
	            }
	            
	            // Guardamos la informacion de la consultora
	            model.put("consultora", consultora);
	
	            // 1. PRODUCTOS CON DESCUENTO
	            // Obtenemos la relacion de los productos con descuento
	            Map productosConDescuento = new HashMap();
	            productosConDescuento.put("idCabeceraSolicitud", idCabeceraSolicitud);
	            productosConDescuento.put("tipoProducto", Constants.PRODUCTOS_CON_DESCUENTO);
	            
	            List listproductosConDescuento = generacionDetallePedidoDAO.getProductosDetallePedido(productosConDescuento);
	            // Guardamos la informacion de las fichas
	            model.put("productosConDescuento", listproductosConDescuento);
	            
	            // 2. PRODUCTOS SIN DESCUENTO
	            // Obtenemos la relacion de los productos sin descuento
	            Map productosSinDescuento = new HashMap();
	            productosSinDescuento.put("idCabeceraSolicitud", idCabeceraSolicitud);
	            productosSinDescuento.put("tipoProducto", Constants.PRODUCTOS_SIN_DESCUENTO);
	            
	            List listproductosSinDescuento = generacionDetallePedidoDAO.getProductosDetallePedido(productosSinDescuento);
	            // Guardamos la informacion de las tarjetas de puntos
	            model.put("productosSinDescuento", listproductosSinDescuento);
	
	            // 3. PRODUCTOS DE APOYO/VTA/PROMOCIONES
	            // Obtenemos la relacion de los productos de apoyo/vta/promo
	            Map productosApoyoVtaProm = new HashMap();
	            productosApoyoVtaProm.put("idCabeceraSolicitud", idCabeceraSolicitud);
	            productosApoyoVtaProm.put("tipoProducto", Constants.PRODUCTOS_APOY_VTA_PROMO);
	
	            List listproductosApoyoVtaProm = generacionDetallePedidoDAO.getProductosDetallePedido(productosApoyoVtaProm);
	            // Guardamos la informacion de las tarjetas de puntos
	            model.put("productosApoyoVtaProm", listproductosApoyoVtaProm);
	
	            // Usamos el motor Velocity para hacer el merge
	            int len_nueva=0;
	            String archivoXML1="";
	            try {
	                String archivoXML = VelocityEngineUtils.mergeTemplateIntoString(
	                        velocityEngine,
	                        "generacionDetallePedido.vm", model);
	               
	                // Insertamos el registro en la base de datos
	                int x=0; 
	                int orden=1;
	                //len_nueva= archivoXML.length();
	                while (archivoXML.length() > 0){
	                	if (archivoXML.length() > 4000){// MAXIMO LIMITE VARCHAR2 ,TIPO CLOB SOPORTA MAX 4GB
	                		archivoXML1= archivoXML.substring(x,4000);
		                	generacionDetallePedidoDAO.insertXMLDetallePedido(codigoConsultora, numeroSolicitud, archivoXML1, orden);
		                	len_nueva= archivoXML.length();
		                	archivoXML= archivoXML.substring(x + 4000, len_nueva);
		                	orden++;
		                	
	                	}
	                	else {
	                		archivoXML1= archivoXML.substring(x,archivoXML.length());
	                		generacionDetallePedidoDAO.insertXMLDetallePedido(codigoConsultora, numeroSolicitud, archivoXML1, orden);
	                		archivoXML= "";
	                	}
	                }
	                
	                //generacionDetallePedidoDAO.insertDetallePedido(codigoConsultora, archivoXML);
	                
	            } catch (VelocityException ve) {
	                log.error(ve.getMessage(), ve);
	                throw new ServiceException(ve.getMessage());
	            }
	        }
	        
	         //Procedemos a insertar todas las partes de xml de detalle de pedido en la tabla imp_paque_docuem_detal_factu x consultora
	         generacionDetallePedidoDAO.executeCargarDetallesPedidos();
	         
       }
       catch(Exception e) {
           log.error(e.getMessage());
           throw new ServiceException(e.getMessage());
       }
       */
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.GeneracionDetallePedidoService#getTipoSolicitud()
	 */
	public List getTipoSolicitud(){
		return generacionDetallePedidoDAO.getTipoSolicitud();
	}

	
}
