package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CajaCerradaWCS;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CajasCerradasWCS;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazAPERecepcionarCajasChequeadasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazAPERecepcionarCajasChequeadasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAPERecepcionarCajasChequeadasServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazAPEDAO")
	protected InterfazAPEDAO interfazAPEDAO;
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#readData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
     */
    protected int readData(InterfazParams interfazParams, File tempFile, List data) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData Castor XML' method");
		int lineCount = 0;
		URL mappingResource ;

		try{
			String codigoPais = (String)interfazParams.getQueryParams().get("codigoPais");
			/*Map map = interfazParams.getQueryParams();
			String codigoPais = (String) map.get("codigoPais");
			String archivo = (String) map.get("nombreArchivo");
			String numeroLote = (String) map.get("numeroLote");
			String nombreArchivo = "WCS7" + numeroLote + ".XML";*/
						
			Mapping mapping = new Mapping();
			mappingResource = getClass().getClassLoader().getResource("applicationCastormapping.xml");
	    	mapping.loadMapping( mappingResource );
	    		    	
	    	Unmarshaller un = new Unmarshaller(CajasCerradasWCS.class);
	    	un.setMapping( mapping );
			
	    	FileReader reader = new FileReader(tempFile);
	    	log.debug("redader: " + reader.ready());
	    	CajasCerradasWCS cajasCerradas = (CajasCerradasWCS) un.unmarshal(reader);
	        reader.close();
	        
	        List cajas = cajasCerradas.getCajas();
	    	Iterator iter = cajas.iterator();
						
			while ( iter.hasNext() ) {
				lineCount++;
				
				CajaCerradaWCS caja = (CajaCerradaWCS) iter.next();
				
				Map row = new HashMap();
				row.put("codigoPais", codigoPais);
				row.put("oidMarca", caja.getMarca());
				row.put("oidCentro", caja.getCentroDistribucion());
				row.put("oidLinea",  caja.getLinea());
				row.put("numConsolidado", caja.getFactura());
				row.put("numCaja", caja.getNumeroCaja());
				row.put("indArmado", caja.getIndicadorArmado());
				
				processLine(interfazParams, lineCount, row);
			
			}
			
		}catch (Exception e) {
			log.error("Error de IO al leer el archivo temporal: " + e.getMessage());
			InterfazException ie = new InterfazException(e.getMessage());
			ie.setRegistrosProcesados(lineCount);
			throw new InterfazException(e.getMessage());
		}
		
		return lineCount;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
     */
    protected void processLine(InterfazParams interfazParams, int lineCount, Map row) throws InterfazException {
    	interfazAPEDAO.executeProcesarCajaEmbalaje(row);
	}
}
