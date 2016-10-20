package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.MovimientoCliente;
import biz.belcorp.ssicc.dao.sisicc.InterfazSATDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazSATRecepcionarCoberturaCodigoTerritorialServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazSATRecepcionarCoberturaCodigoTerritorialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATRecepcionarCoberturaCodigoTerritorialServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazSATDAO")
	protected InterfazSATDAO interfazSATDAO;
    
	// INTERFAZ POR ORACLE
	/*
    protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		interfazSATDAO.executeInterfazSATRecepcionarCoberturaCodigoTerritorial(map);				
	}
	*/
	
	// INTERFAZ POR JAVA
	protected void beforeReadData(InterfazParams interfazParams)throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSATDAO.deleteInterfazSATRecepcionarCoberturaCodigoTerritorial();
			
		} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: "+ e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount, Map row) throws InterfazException {

        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        try {
            cargarCoberturaCodigoTerritorial(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    /**
     * Metodo que realiza la carga a la tabla
     * @param row
     * @param usuario
     * @throws Exception
     */
    public synchronized void cargarCoberturaCodigoTerritorial(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarCoberturaCodigoTerritorial'");
        }
        MovimientoCliente mov = new MovimientoCliente();
        try {                        
            interfazSATDAO.insertCoberturaCodigoTerritorial(row, usuario);            
        } catch (Exception e) {
            throw e;
        }
    }
}
