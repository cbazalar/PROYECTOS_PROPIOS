package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazPREDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.util.FTPUtil;

@Service("sisicc.interfazPRERecepcionarOfertasGeneradasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRERecepcionarOfertasGeneradasServiceImpl  extends BaseInterfazEntradaAbstractService 
{
	@Resource(name="sisicc.interfazPREDAO")
	private InterfazPREDAO interfazPREDAO;

	/**
	 * @param interfazPREDAO the interfazPREDAO to set
	 */
	public void setInterfazPREDAO(InterfazPREDAO interfazPREDAO) {
		this.interfazPREDAO = interfazPREDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	@Override
	protected void addLine(List data, Map row) 
	{
		data.add(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, java.util.List)
	 */
	@Override
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException 
	{		
		try {
			Map mapElimArch = interfazParams.getQueryParams();
			String eliminarArchivos = MapUtils.getString(mapElimArch, "eliminarArchivos");
			
			if(StringUtils.equals(eliminarArchivos, Constants.SI)){
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(interfazParams.getInterfaz());
				ftpUtil.eliminarArchivo(interfazParams.getArchivoEntradaPath(), interfazParams.getArchivoEntradaFileName());
				ftpUtil.cerrarFTP();
			}else{
				int j = 1;
				for(int i=0; i< data.size(); i++)
				{
					HashMap criteria = (HashMap) data.get(i);
					criteria.put("numRegistro", j);
					interfazPREDAO.insertOfertaGenerada(criteria);
					j++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
