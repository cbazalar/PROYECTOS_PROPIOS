package biz.belcorp.ssicc.service.scsicc.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.ConsultaPaisesOCRAvancePedidoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ConsultaPaisesOCRAvancePedidoService;

/**
 * Implementacion de ReporteService
 * 
 * @author <a href="">Sergio Buchelli Silva</a>
 * 
 */
@Service("scsicc.consultaPaisesOCRAvancePedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaPaisesOCRAvancePedidoServiceImpl extends BaseService 
    implements ConsultaPaisesOCRAvancePedidoService {
	
	@Resource(name="scsicc.consultaPaisesOCRAvancePedidoDAO")
	ConsultaPaisesOCRAvancePedidoDAO consultaPaisesOCRAvancePedidoDAO;
	
	public String getCampanhaActivaByZona(Map criteria) {	
		String [] cmdArray = getCmdArray(7,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		String resultado = consultaPaisesOCRAvancePedidoDAO.getCampanhaActivaByZona(criteria);		
		log.debug("getCampanhaActivaByZona xxx "+resultado);
		//deleteArchivo(criteria);
		return resultado;
	}

	/**
	 * obtine el cmd a ejecutar dependiento del tipo
	 * @param tipo
	 * @param criteria
	 * @return
	 */
	private String[] getCmdArray(int tipo, Map criteria) {
		String [] res=null;
		String ext=".dbf";
		String libComercial =(String)criteria.get("libComercial");
		String codigoPais = (String)criteria.get("codigoPais");
		String prg =  (String)criteria.get("prg");
		log.debug("libComercial "+libComercial);
		char ch = (char) ('A' + tipo);
		log.debug("ch "+ch);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        String current = sdf.format(new Date(System.currentTimeMillis()));
        String parametro="";
        String entidad ="";
		switch (tipo) {
		case 0:			 
		case 1:			
		case 2:
		case 3:
		case 5:			
		case 7:
			if(tipo == 5)
				parametro = "" +(Integer)criteria.get("oidPedido");				
			else
				parametro = (String)criteria.get("codigoZona");
			
			res = new String[6];
			res[0]=libComercial + "/" +prg;		
			res[1]=new String(""+ch);
			res[2]=codigoPais;
			res[3]=new String(""+parametro);
			res[4]=current.substring(0, 8);
			res[5]=current.substring(9);
			entidad=res[1]+"_"+res[2]+"_"+res[3]+"_"+res[4]+"_"+res[5]+ext;
			break;
			
		default:
			res = new String[7];
			res[0]=libComercial + "/" +prg;		
			res[1]=new String(""+ch);
			res[2]=codigoPais;
			res[3]=(String)criteria.get("codigoCliente");
			res[4]=(String)criteria.get("codigoZona");
			res[5]=current.substring(0, 8);
			res[6]=current.substring(9);
			entidad=res[1]+"_"+res[2]+"_"+res[3]+"_"+res[4]+"_"+res[5]+"_"+res[6]+ext;
			break;
		}
		
		criteria.put("entidad", entidad);	
		log.debug("tabla "+ entidad);
		return res;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getCodigoVentasRechazados(java.util.Map)
	 */
	public List getCodigoVentasRechazados(Map criteria) {
		String [] cmdArray = getCmdArray(4,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		List list= consultaPaisesOCRAvancePedidoDAO.getCodigoVentasRechazados(criteria);
		//deleteArchivo(criteria);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getConsultorasActivasSinPedido(java.util.Map)
	 */
	public List getConsultorasActivasSinPedido(Map criteria) {
		String [] cmdArray = getCmdArray(1,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		List list = consultaPaisesOCRAvancePedidoDAO.getConsultorasActivasSinPedido(criteria);
		//deleteArchivo(criteria);
		return list;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getDetallePedidoFacturado(java.util.Map)
	 */
	public List getDetallePedidoFacturado(Map criteria) {
		String [] cmdArray = getCmdArray(5,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		List list= consultaPaisesOCRAvancePedidoDAO.getDetallePedidoFacturado(criteria);
		//deleteArchivo(criteria);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getFaltantesAnunciados(java.util.Map)
	 */
	public List getFaltantesAnunciados(Map criteria) {
		String [] cmdArray = getCmdArray(6,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		List list= consultaPaisesOCRAvancePedidoDAO.getFaltantesAnunciados(criteria);
		//deleteArchivo(criteria);
		return list;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getFechaUltimaActualizacionZona(java.util.Map)
	 */
	public String getFechaUltimaActualizacionZona(Map criteria) {
		String [] cmdArray = getCmdArray(2,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String st= consultaPaisesOCRAvancePedidoDAO.getFechaUltimaActualizacionZona(criteria);
		//deleteArchivo(criteria);
		return st;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getInformeAvancePedido(java.util.Map)
	 */
	public List getInformeAvancePedido(Map criteria) {
		String [] cmdArray = getCmdArray(0,criteria);
		log.debug("inico getInformeAvancePedido");
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		List list= consultaPaisesOCRAvancePedidoDAO.getInformeAvancePedido(criteria);
		//deleteArchivo(criteria);
		log.debug("fin getInformeAvancePedido");
		return list;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#getNumeroRegistros(java.util.Map)
	 */
	public Integer getNumeroRegistros(Map criteria) {
		String [] cmdArray = getCmdArray(3,criteria);
		try {
			Process p=Runtime.getRuntime().exec(cmdArray);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Integer res= consultaPaisesOCRAvancePedidoDAO.getNumeroRegistros(criteria);
		//deleteArchivo(criteria);
		return res;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaPaisesOCRAvancePedidoService#deleteArchivo(java.util.Map)
	 */
	public void deleteArchivo(Map criteria) {
		String entidad = (String)criteria.get("entidad");
		String libComercial =(String)criteria.get("libComercial");
		File fileSource = new File(libComercial,entidad);
		log.debug("deleteArchivo inicio "+fileSource.getAbsolutePath());
		try {
			//fileSource.delete();
			//FileUtils.forceDelete(fileSource);
			String entidadBorrar= entidad.substring(0, entidad.length()-4);
			criteria.put("entidad",entidadBorrar);
			log.debug("entidad a borrar "+entidadBorrar);
			consultaPaisesOCRAvancePedidoDAO.deleteEntidad(criteria);
		} catch (Exception e) {
			log.debug("deleteArchivo error "+e.getLocalizedMessage());
			e.printStackTrace();
		}				
		log.debug("deleteArchivo fin");
	}

	
	
}