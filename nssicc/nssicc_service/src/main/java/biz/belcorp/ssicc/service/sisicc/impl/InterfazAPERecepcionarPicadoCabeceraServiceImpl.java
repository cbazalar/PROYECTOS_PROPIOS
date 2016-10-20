package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazAPERecepcionarPicadoCabeceraServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("sisicc.interfazAPERecepcionarPicadoCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAPERecepcionarPicadoCabeceraServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazAPEDAO")
	protected InterfazAPEDAO interfazAPEDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		//String usuario = MapUtils.getString(map, "codigoUsuario");
		
		try {
			interfazAPEDAO.executeInterfazAPERecepcionarPicadoCabecera(map);
		} catch (Exception e) {
			throw new InterfazException("Error al procesar los registros: "	+ e.getMessage());
		}
	}
}