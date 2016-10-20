/*
 * Created on 19/02/2007 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazRECRecepcionarBoletasRecojoControlServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazRECRecepcionarBoletasRecojoControlServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose A. Cairampoma Granados </a>
 */
@Service("sisicc.interfazRECRecepcionarBoletasRecojoDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECRecepcionarBoletasRecojoDetalleServiceImpl extends BaseInterfazEntradaAbstractService {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		interfazSiCCDAO.deleteInterfazRECRecepcionarBoletasRecojoDetalle();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,Map row) throws InterfazException {
		
		try {
		    interfazSiCCDAO.insertInterfazRECRecepcionarBoletasRecojoDetalle(row);
		    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		ArrayList listaValida = null;
		listaValida = (ArrayList)interfazSiCCDAO.getValidaExistenciaProductos(interfazParams.getQueryParams());
		String cadError = "";
		String mensajeError = null;
		Usuario usuario = null;
		
		if (listaValida.size()>0){
			
			for (int i=0; i<listaValida.size(); i++){
				Map codigoError = (Map)listaValida.get(i);
				
				if ((listaValida.size()==1) || (i == listaValida.size()-1)){
					cadError = cadError + codigoError.get("codigo").toString();	
				}else{
					cadError = cadError + codigoError.get("codigo").toString() + ";";
				}
			}
			
			usuario = interfazParams.getUsuario();
			mensajeError = messageSource.getMessage("interfazRECRecibirBoletaRecojoForm.error.msg",null,getLocale(usuario));
			
			throw new InterfazException(mensajeError + " : " + cadError);
			
		}else{
			interfazSiCCDAO.executeInterfazRECRecepcionarBoletasRecojoCabecera(interfazParams.getQueryParams());
			interfazSiCCDAO.executeInterfazRECRecepcionarBoletasRecojoDetalle(interfazParams.getQueryParams());
		}
	}
	
	

}
