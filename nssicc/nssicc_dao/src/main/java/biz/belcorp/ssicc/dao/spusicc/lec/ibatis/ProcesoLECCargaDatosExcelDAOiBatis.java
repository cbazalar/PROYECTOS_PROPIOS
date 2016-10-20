package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCargaDatosExcelDAO;

@Repository("spusicc.procesoLECCargaDatosExcelDAO")
public class ProcesoLECCargaDatosExcelDAOiBatis  extends BaseDAOiBatis implements ProcesoLECCargaDatosExcelDAO{
	public List getTipoCarga(Map criteria){
		return getSqlMapClientTemplate()
				.queryForList(
						"spusicc.lec.MantenimientoLECSQL.getTipoCarga",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#getTipoCarga()
	 */
	public List getPrograma(Map criteria){
		return getSqlMapClientTemplate()
				.queryForList(
						"spusicc.lec.MantenimientoLECSQL.getPrograma",criteria);
	}
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNumeroCarga");
	}
	public Integer insertCargaDatos(Map params) {
	   return (Integer)getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.insertCargaDatos", params);
	}
	public String executeValidarCargaDatos(Map params) {		
	  getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeValidarCargaDatos", params);
	  String a=(String)params.get("indicadorCarga");	  
	  return a;
	}

	public List getCargaDatosList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getCargaDatosList", params);
	}
	
	public List getListaIntermedia(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getListaIntermedia", params);
	}
	
	public String executeActualizarCargaDatos(Map params) {		
		String tipoCarga=(String)params.get("tipoCarga");		
		if(tipoCarga.compareTo("01")==0)
	     	  getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeGrabaDatosObjetoPedidos", params);
		if(tipoCarga.compareTo("02")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeCargaDatosBrillantes", params);
		if(tipoCarga.compareTo("03")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeCargaDatosNombramientoLideres", params);	
		if(tipoCarga.compareTo("07")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeGrabaTarjetaPagos", params);
		if(tipoCarga.compareTo("08")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeAsociaTarjetaPagos", params);
		if(tipoCarga.compareTo("09")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeAnulaTarjetaPagos", params);
		if(tipoCarga.compareTo("10")==0)		  
		      getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeCargaNivelExito", params);
		
		if(StringUtils.equals(tipoCarga, "11") || StringUtils.equals(tipoCarga, "12") || StringUtils.equals(tipoCarga, "13") || StringUtils.equals(tipoCarga, "14"))
		{
			getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeCargaBonosLanzamiento", params);
		}
		
		if(StringUtils.equals(tipoCarga, "15") || StringUtils.equals(tipoCarga, "16") || StringUtils.equals(tipoCarga, "17"))
		{
			getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeCargaBloqueoDesbloqueoPagosActualizaMontosNetos", params);
		}
		if(StringUtils.equals(tipoCarga, "18"))		  
		    getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.executeActualizacionEstatusReenvioTarjeta", params);
		String a=(String)params.get("indicadorProceso");			 
		
		return a;
	}
	
	public String getCampanhaInicialPrograma(Map params) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getCampanhaInicialPrograma",params);
	}
	public boolean getNumeroRegistrosResultadosLet(Map params) {
		boolean valida=true;
		Integer registros= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNumeroRegistrosResultadosLet",params);
		if(registros.intValue()>0){
			valida=false;
		}
		return valida;
	}

}
