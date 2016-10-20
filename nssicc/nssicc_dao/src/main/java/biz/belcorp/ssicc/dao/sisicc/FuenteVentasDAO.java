/*
 * Created on 07-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraFuenteVentaPrevista;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentas;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasPais;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasRegion;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasZona;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FuenteVentasDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface FuenteVentasDAO extends DAO {
   
    public String getPeriodoInicio(String codigoRangoPeriodo);
    
    public String getPeriodoFin(String codigoRangoPeriodo);
    
    
    public List getFuenteVentas(Map criteria);
    public List getConsultaFuenteVentasPais(Map criteria);
    public List getConsultaFuenteVentasRegion(Map criteria);
    public List getConsultaFuenteVentasZona(Map criteria);
    
    public FuenteVentas getFuenteVenta(String primaryKey);
    
    public void insertFuenteVenta(FuenteVentas fuenteVenta, Usuario usuario);
    
    public void updateFuenteVenta(FuenteVentas fuenteVenta, Usuario usuario);
    
    public void removeFuenteVenta(String primaryKey);
    
    public long getNextPK();
    
    public String getPeriodoAbierto(String periodo);
    
    public List getFuenteVentasZona(Map criteria);
    
    public FuenteVentasZona getFuenteVentaZona(String primaryKey);
    
    public void insertFuenteVentaZona(FuenteVentasZona fuenteVentaZona, Usuario usuario);
    
    public void updateFuenteVentaZona(FuenteVentasZona fuenteVentaZona, Usuario usuario);
    
    public void removeFuenteVentaZona(String primaryKey);
    
    public void removeFuenteVentaZonas(Map criteria);
    
    public long getNextPKZona();
    
    
    
    public List getFuenteVentasRegion(Map criteria);
    
    public FuenteVentasRegion getFuenteVentaRegion(String primaryKey);
    
    public void insertFuenteVentaRegion(FuenteVentasRegion fuenteVentaRegion, Usuario usuario);
    
    public void updateFuenteVentaRegion(FuenteVentasRegion fuenteVentaRegion, Usuario usuario);
    
    public void removeFuenteVentaRegion(String primaryKey);
    
    public void removeFuenteVentaRegiones(Map criteria);
    
    public long getNextPKRegion();
    
    
    
    public List getFuenteVentasPais(Map criteria);
    
    public FuenteVentasPais getFuenteVentaPais(String primaryKey);
    
    public void insertFuenteVentaPais(FuenteVentasPais fuenteVentaPais, Usuario usuario);
    
    public void updateFuenteVentaPais(FuenteVentasPais fuenteVentaPais, Usuario usuario);
    
    public void removeFuenteVentaPais(String primaryKey);
    
    public void removeFuenteVentaPaises(Map criteria);
    
    public long getNextPKPais();
    
    
    // Agregados para el clculo
    
    public List getListaFuenteVentasForCalculo(Map criteria);
    
    public List getListaFuenteVentasRegionForCalculo(Map criteria);
    
    public List getListaFuenteVentasPaisForCalculo(Map criteria);
    
    public boolean verificaZona(FuenteVentasZona fuenteVentasZona);
    
    public long getPKZona(FuenteVentasZona fuenteVentasZona);
    
    public boolean verificaRegion(FuenteVentasRegion fuenteVentasRegion);
    
    public long getPKRegion(FuenteVentasRegion fuenteVentasRegion);
    
    public boolean verificaPais(FuenteVentasPais fuenteVentasPais);
    
    public long getPKPais(FuenteVentasPais fuenteVentasPais);

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
	 * Metodo que inserta en la tabla temporal
	 * @param estructura
	 */
	public void insertEstructuraCargaFuenteVentaPrevista(EstructuraFuenteVentaPrevista estructura);

	/**
	 * Metodo que valida la estructura y data del archivo
	 * @param criteria
	 */
	public void executeValidarCargaFuenteVentaPrevista(Map criteria);

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
	 * @return
	 */
	public void actualizarCargaFuenteVentaPrevista(Map datos);

	/**
	 * Metodo que obtiene la fecha de calculo
	 * @return
	 */
	public List getFechaCalculo();


	/**
	 * Metodo que verifica la campaa cerrada
	 * @param datos
	 * @return
	 */
	public String verificaCampCerrada(Map datos);

	/**
	 * Metodo que obtiene los periodos ya cargados
	 * @param criteria
	 * @return
	 */
	public List getPeriodosYaCargados(Map criteria);

	/**
	 * Metodo que obtiene los periodos ya cargados por seccion
	 * @param criteria
	 * @return
	 */
	public List getPeriodosYaCargadosSeccion(Map criteria);
    
}
