/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.FuenteVentasDAO;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraFuenteVentaPrevista;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentas;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasPais;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasRegion;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentasZona;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FuenteVentasDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

/**
 * @author peextrramirez
 *
 */
@Repository("sisicc.fuenteVentasDAO")
public class FuenteVentasDAOiBatis extends BaseDAOiBatis implements FuenteVentasDAO {

    public String getPeriodoInicio(String codigoRangoPeriodo) {
        String periodoInicio = (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoInicio",codigoRangoPeriodo);
        return  periodoInicio;
    }
    public String getPeriodoFin(String codigoRangoPeriodo) {
        String periodoFin = (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoFin",codigoRangoPeriodo);
        return  periodoFin;
    }
    
    
    public List getConsultaFuenteVentasPais(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getConsultaFuenteVentasPais", criteria);
        return fuenteVentas;
    }
    public List getConsultaFuenteVentasRegion(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getConsultaFuenteVentasRegion", criteria);
        return fuenteVentas;
    }
    public List getConsultaFuenteVentasZona(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getConsultaFuenteVentasZona", criteria);
        return fuenteVentas;
    }
    
	public List getFuenteVentas(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getFuenteVentas", criteria);
        return fuenteVentas;
	}

    public FuenteVentas getFuenteVenta(String primaryKey) {
		FuenteVentas fuenteVenta = (FuenteVentas)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getFuenteVenta", primaryKey);
		if(fuenteVenta == null){
			throw new ObjectRetrievalFailureException(FuenteVentas.class, primaryKey);
		}
		return fuenteVenta;
	}

    public void insertFuenteVenta(FuenteVentas fuenteVenta, Usuario usuario) {
        //fuenteVenta.formatear();  
 		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.insertFuenteVenta", fuenteVenta);
	}
	
    public void updateFuenteVenta(FuenteVentas fuenteVenta, Usuario usuario) {
        //fuenteVenta.formatear();
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.updateFuenteVenta", fuenteVenta);
	}
	
    public void removeFuenteVenta(String primaryKey) {
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVenta", primaryKey);
	}
    
    public long getNextPK() {
        String pk = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getNextPK","");
        return  Long.parseLong(pk);
    }

 
    
    
    
    public List getFuenteVentasZona(Map criteria) {
        List fuenteVentasZona = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getFuenteVentasZona", criteria);
        return fuenteVentasZona;
    }

    public FuenteVentasZona getFuenteVentaZona(String primaryKey) {
        FuenteVentasZona fuenteVentaZona = (FuenteVentasZona)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getFuenteVentaZona", primaryKey);
        if(fuenteVentaZona == null){
            throw new ObjectRetrievalFailureException(FuenteVentasZona.class, primaryKey);
        }
        return fuenteVentaZona;
    }

    public void insertFuenteVentaZona(FuenteVentasZona fuenteVentaZona, Usuario usuario) {
        //fuenteVentaZona.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.insertFuenteVentaZona", fuenteVentaZona);
    }
    
    public void updateFuenteVentaZona(FuenteVentasZona fuenteVentaZona, Usuario usuario) {
        //fuenteVentaZona.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.updateFuenteVentaZona", fuenteVentaZona);
    }
    
    public void removeFuenteVentaZona(String primaryKey) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaZona", primaryKey);
    }
    
    public void removeFuenteVentaZonas(Map criteria) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaZonas", criteria);
    }
    
    public long getNextPKZona() {
        String pk = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getNextPKZona","");
        return  Long.parseLong(pk);
    }

    
    
    
    public List getFuenteVentasRegion(Map criteria) {
        List fuenteVentasRegion = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getFuenteVentasRegion", criteria);
        return fuenteVentasRegion;
    }

    public FuenteVentasRegion getFuenteVentaRegion(String primaryKey) {
        FuenteVentasRegion fuenteVentaRegion = (FuenteVentasRegion)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getFuenteVentaRegion", primaryKey);
        if(fuenteVentaRegion == null){
            throw new ObjectRetrievalFailureException(FuenteVentasRegion.class, primaryKey);
        }
        return fuenteVentaRegion;
    }

    public void insertFuenteVentaRegion(FuenteVentasRegion fuenteVentaRegion, Usuario usuario) {
        //fuenteVentaRegion.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.insertFuenteVentaRegion", fuenteVentaRegion);
    }
    
    public void updateFuenteVentaRegion(FuenteVentasRegion fuenteVentaRegion, Usuario usuario) {
        //fuenteVentaRegion.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.updateFuenteVentaRegion", fuenteVentaRegion);
    }
    
    public void removeFuenteVentaRegion(String primaryKey) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaRegion", primaryKey);
    }
    
    public void removeFuenteVentaRegiones(Map criteria) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaRegiones", criteria);
    }
    
    public long getNextPKRegion() {
        String pk = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getNextPKRegion","");
        return  Long.parseLong(pk);
    }

 
    
    
    public List getFuenteVentasPais(Map criteria) {
        List fuenteVentasPais = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getFuenteVentasPais", criteria);
        return fuenteVentasPais;
    }

    public FuenteVentasPais getFuenteVentaPais(String primaryKey) {
        FuenteVentasPais fuenteVentaPais = (FuenteVentasPais)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getFuenteVentaPais", primaryKey);
        if(fuenteVentaPais == null){
            throw new ObjectRetrievalFailureException(FuenteVentasPais.class, primaryKey);
        }
        return fuenteVentaPais;
    }

    public void insertFuenteVentaPais(FuenteVentasPais fuenteVentaPais, Usuario usuario) {
        //fuenteVentaPais.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.insertFuenteVentaPais", fuenteVentaPais);
    }
    
    public void updateFuenteVentaPais(FuenteVentasPais fuenteVentaPais, Usuario usuario) {
        //fuenteVentaPais.formatear();  
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.updateFuenteVentaPais", fuenteVentaPais);
    }
    
    public void removeFuenteVentaPais(String primaryKey) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaPais", primaryKey);
    }
    
    public void removeFuenteVentaPaises(Map criteria) {
        getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.removeFuenteVentaPaises", criteria);
    }
    
    public long getNextPKPais() {
        String pk = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getNextPKPais","");
        return  Long.parseLong(pk);
    }

    public String getPeriodoAbierto(String periodo) {
    	return (String) getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getPeriodoAbierto", periodo);
    }
    
    // Agregado para el proceso de clculo
    
    public List getListaFuenteVentasForCalculo(Map criteria) {
        List fuenteVentas = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getListaFuenteVentasForCalculo", criteria);
        return fuenteVentas;
    }

    
    public List getListaFuenteVentasRegionForCalculo(Map criteria) {
        List fuenteVentasRegion = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getListaFuenteVentasRegionForCalculo", criteria);
        return fuenteVentasRegion;
    }
    
    public List getListaFuenteVentasPaisForCalculo(Map criteria) {
        List fuenteVentasPais = getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getListaFuenteVentasPaisForCalculo", criteria);
        return fuenteVentasPais;
    }

    
    
    public boolean verificaZona(FuenteVentasZona fuenteVentasZona) {
        boolean result;
        Map params = new HashMap();
        
        params.put("codigoPais",fuenteVentasZona.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasZona.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasZona.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasZona.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasZona.getCodigoPeriodo());
        params.put("codigoZona",fuenteVentasZona.getCodigoZona());
        params.put("codigoRegion",fuenteVentasZona.getCodigoRegion());
         
        String cantidad = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.verificaZona",params);
        int cant = Integer.parseInt(cantidad);
        if (cant>0)  result=true;
               else  result= false;
        return result;
    }
 
    public long getPKZona(FuenteVentasZona fuenteVentasZona) {
        long  result;
        Map params = new HashMap();
        params.put("codigoPais",fuenteVentasZona.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasZona.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasZona.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasZona.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasZona.getCodigoPeriodo());
        params.put("codigoZona",fuenteVentasZona.getCodigoZona());
        params.put("codigoRegion",fuenteVentasZona.getCodigoRegion());
        String codigo = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getPKZona",params);
        result  = Long.parseLong(codigo);
        return result;
    }
 
    public boolean verificaRegion(FuenteVentasRegion fuenteVentasRegion) {
        boolean result;
        Map params = new HashMap();
        
        params.put("codigoPais",fuenteVentasRegion.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasRegion.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasRegion.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasRegion.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasRegion.getCodigoPeriodo());
        params.put("codigoRegion",fuenteVentasRegion.getCodigoRegion());
         
        String cantidad = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.verificaRegion",params);
        int cant = Integer.parseInt(cantidad);
        if (cant>0)  result=true;
               else  result= false;
        return result;
    }
 
    public long getPKRegion(FuenteVentasRegion fuenteVentasRegion) {
        long  result;
        Map params = new HashMap();
        params.put("codigoPais",fuenteVentasRegion.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasRegion.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasRegion.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasRegion.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasRegion.getCodigoPeriodo());
        params.put("codigoRegion",fuenteVentasRegion.getCodigoRegion());
        String codigo = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getPKRegion",params);
        result  = Long.parseLong(codigo);
        return result;
    }
  

    public boolean verificaPais(FuenteVentasPais fuenteVentasPais) {
        boolean result;
        Map params = new HashMap();
        
        params.put("codigoPais",fuenteVentasPais.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasPais.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasPais.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasPais.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasPais.getCodigoPeriodo());
         
        String cantidad = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.verificaPais",params);
        int cant = Integer.parseInt(cantidad);
        if (cant>0)  result=true;
               else  result= false;
        return result;
    }
 
    public long getPKPais(FuenteVentasPais fuenteVentasPais) {
        long  result;
        Map params = new HashMap();
        params.put("codigoPais",fuenteVentasPais.getCodigoPais());
        params.put("codigoSociedad",fuenteVentasPais.getCodigoSociedad());
        params.put("codigoAlmacen",fuenteVentasPais.getCodigoAlmacen());
        params.put("codigoCanal",fuenteVentasPais.getCodigoCanal());
        params.put("codigoPeriodo",fuenteVentasPais.getCodigoPeriodo());
        String codigo = (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosBPSSQL.getPKPais",params);
        result  = Long.parseLong(codigo);
        return result;
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#deleteTablasCargaFuentezaVentaPrevista()
	 */
	public void deleteTablasCargaFuenteVentaPrevista() {		
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.deleteTablasCargaFuenteVentaPrevista", null);		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais) {
		return (String) getSqlMapClientTemplate().
		queryForObject("sisicc.ProcesosBPSSQL.getPathUpload", codigoPais);		

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#insertEstructuraCargaFuenteVentaPrevista(biz.belcorp.ssicc.sisicc.model.EstructuraFuenteVentaPrevista)
	 */
	public void insertEstructuraCargaFuenteVentaPrevista(EstructuraFuenteVentaPrevista estructura){
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.insertEstructuraCargaFuenteVentaPrevista", estructura);
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#executeValidarCargaFuenteVentaPrevista(java.util.Map)
	 */
	public void executeValidarCargaFuenteVentaPrevista(Map criteria){
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.executeValidarCargaFuenteVentaPrevista", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#getErroresCargaFuenteVentaPrevista()
	 */
	public List getErroresCargaFuenteVentaPrevista(){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getErroresCargaFuenteVentaPrevista");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#getCalcularCargaFuenteVentaPrevista(java.util.Map)
	 */
	public List getCalcularCargaFuenteVentaPrevista(Map datos) {
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.executeCalcularCargaFuenteVentaPrevista", datos);
		
		String indTipoFvp = (String)datos.get("indTipoFvp");
		
		if(StringUtils.equalsIgnoreCase(indTipoFvp, Constants.NUMERO_DOS) )
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getCalcularCargaFuenteVentaPrevista");
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#actualizarCargaFuenteVentaPrevista()
	 */
	public void actualizarCargaFuenteVentaPrevista(Map datos){
		getSqlMapClientTemplate().update("sisicc.ProcesosBPSSQL.actualizarCargaFuenteVentaPrevista",datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#getFechaCalculo()
	 */
	public List getFechaCalculo(){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getFechaCalculo");
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#verificaCampCerrada(java.util.Map)
	 */
	public String verificaCampCerrada(Map datos){	
		return (String) getSqlMapClientTemplate().
			queryForObject("sisicc.ProcesosBPSSQL.verificaCampCerrada", datos);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#getPeriodosYaCargados(java.util.Map)
	 */
	public List getPeriodosYaCargados(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getPeriodosYaCargados",criteria); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.FuenteVentasDAO#getPeriodosYaCargadosSeccion(java.util.Map)
	 */
	public List getPeriodosYaCargadosSeccion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosBPSSQL.getPeriodosYaCargadosSeccion",criteria);
	}
	
}



