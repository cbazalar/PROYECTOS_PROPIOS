/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentas;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasPais;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasRegion;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasZona;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FuenteVentasService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface FuenteVentasService extends Service {
    
    
    public void insertNuevaConfiguracion(Map criteria, Usuario usuario);
    
    public String  getPeriodoInicio(String codigoRangoPeriodo);
    
    public String  getPeriodoFin(String codigoRangoPeriodo);
    
    public void executeCalculoFuenteVentasPrevista(Map criteria, Usuario usuario);
     
    public int executeCalculoFuenteZona   (Map criteria, Usuario usuario);
    
    public int executeCalculoFuenteRegion (Map criteria, Usuario usuario);
    
    public int executeCalculoFuentePais   (Map criteria, Usuario usuario);
 
    public long  getNextPK();
    
    public String getPeriodoAbierto(String periodo);
    
    public List getFuenteVentasByCriteria(Map criteria);

    public ArrayList getConsultaFuenteVentasPrevistaByCriteria(Map criteria);

    public FuenteVentas getFuenteVenta(String primaryKey);
    
    public void insertFuenteVenta(FuenteVentas fuenteVentas, Usuario usuario);
    
    public void updateFuenteVenta(FuenteVentas fuenteVentas, Usuario usuario);
    
    public void updateFuenteVentasLista(ArrayList listaFuentesVenta, Usuario usuario);
    
    public void removeFuenteVenta(String primaryKey, Usuario usuario);
   
    public long  getNextPKZona();
    
    public List getFuenteVentasZonaByCriteria(Map criteria);
    
    public FuenteVentasZona getFuenteVentaZona(String primaryKey);
    
    public void insertFuenteVentaZona(FuenteVentasZona fuenteVentasZona, Usuario usuario);
    
    public void updateFuenteVentaZona(FuenteVentasZona fuenteVentasZona, Usuario usuario);
    
    public void updateFuenteVentasZonaLista(ArrayList listaFuentesVentaZona, Usuario usuario);
    
    public void removeFuenteVentaZona(String primaryKey, Usuario usuario);
   
    
    
    public long  getNextPKRegion();
    
    public List getFuenteVentasRegionByCriteria(Map criteria);
    
    public FuenteVentasRegion getFuenteVentaRegion(String primaryKey);
    
    public void insertFuenteVentaRegion(FuenteVentasRegion fuenteVentasRegion, Usuario usuario);
    
    public void updateFuenteVentaRegion(FuenteVentasRegion fuenteVentasRegion, Usuario usuario);
    
    public void updateFuenteVentasRegionLista(ArrayList listaFuentesVentaRegion, Usuario usuario);
    
    public void removeFuenteVentaRegion(String primaryKey, Usuario usuario);
   
    
    
    public long  getNextPKPais();
    
    public List getFuenteVentasPaisByCriteria(Map criteria);
    
    public FuenteVentasPais getFuenteVentaPais(String primaryKey);
    
    public void insertFuenteVentaPais(FuenteVentasPais fuenteVentasPais, Usuario usuario);
    
    public void updateFuenteVentaPais(FuenteVentasPais fuenteVentasPais, Usuario usuario);
    
    public void updateFuenteVentasPaisLista(ArrayList listaFuentesVentaPais, Usuario usuario);
    
    public void removeFuenteVentaPais(String primaryKey, Usuario usuario);

	/**
	 * Metodo que borra la tabla temporal de la Caga Fuente Venta Prevista
	 */
	public void deleteTablasCargaFuenteVentaPrevista();
	
	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param codigoPais
	 * @return
	 */
	public String obtenerPathUpload(String codigoPais);

	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarCargaFuenteVentaPrevista(Map datos) throws Exception;

	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaFuenteVentaPrevista();

	/**
	 * Metodo que realiza el calculo de la carga
	 * @return
	 */
	public List getCalcularCargaFuenteVentaPrevista(Map datos);

	/**
	 * Metodo que actualiza las Fuente de Venta Prevista del sicc y ssicc
	 * @param datos 
	 */
	public void actualizarCargaFuenteVentaPrevista(Map datos);

	/**
	 * Metodo que obtiene la fecha de calculo
	 * @return
	 */
	public List getFechaCalculo();

	/**
	 * * Metodo que obtiene los periodos ya cargados en tablas del sicc y ssicc
	 * @param datos
	 * @return
	 * @throws Exception
	 */
	public List getPeriodosYaCargados(Map datos)throws Exception;
   
    
    
    
}
