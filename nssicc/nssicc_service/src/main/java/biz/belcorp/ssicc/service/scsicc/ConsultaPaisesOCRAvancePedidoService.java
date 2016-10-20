package biz.belcorp.ssicc.service.scsicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Service de OCR para el sistema comercial FOX
 * 
 * @author <a href="">Sergio Buchelli Silva</a>
 * 
 */
public interface ConsultaPaisesOCRAvancePedidoService extends Service {


    /**
     * Retorna lista de consultoras activas sin pedido
     * @param criteria
     */
    public List getConsultorasActivasSinPedido(Map criteria) ;
     
    /**
     * Retorna la fecha de ultima actualizacion dela zona
     * @param criteria
     */
    public String getFechaUltimaActualizacionZona(Map criteria) ;
    
    /**
     * Retorna el numero de registros
     * @param criteria
     * @
     */
    public Integer getNumeroRegistros(Map criteria) ;
    
    /**
     * Retorna en uno solo el Informe de Avance de Pedido
     * @param criteria
     */
    public List  getInformeAvancePedido(Map criteria) ;
    
    
    /**
     * Retorna los codigo de venta rechazados para la consultora
     * @param criteria
     */
    public List getCodigoVentasRechazados(Map criteria) ;
    

    
    /**
     * Retorna los detalle de pedidos facturados para la consultora
     * @param criteria 
     */
    public List getDetallePedidoFacturado(Map criteria) ;
    
    
    
    /**
     * Retorna el numero de faltantes Anunciados
     * @param criteria
     */
    public List getFaltantesAnunciados(Map criteria) ;
    

    /**
     * Devuelve la campnha activa de facturacion
     * @param criteria
     * @return
     * @
     */
    public String getCampanhaActivaByZona(Map criteria);
    
    /**elimina la entidad creada
     * @param criteria
     */
    public void deleteArchivo(Map criteria);
    	
}