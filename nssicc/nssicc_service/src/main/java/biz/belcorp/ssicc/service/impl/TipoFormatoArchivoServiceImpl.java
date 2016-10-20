/*
 * Created on 05-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.TipoFormatoArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;
import biz.belcorp.ssicc.service.TipoFormatoArchivoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoFormatoArchivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.tipoFormatoArchivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class TipoFormatoArchivoServiceImpl extends BaseService implements TipoFormatoArchivoService {
	
	@Resource(name="sisicc.tipoFormatoArchivoDAO")
	private TipoFormatoArchivoDAO tipoFormatoArchivoDAO;	

	/**
	 * @return Returns the tipoFormatoArchivoDAO.
	 */
	public TipoFormatoArchivoDAO getTipoFormatoArchivoDAO() {
		return tipoFormatoArchivoDAO;
	}
	/**
	 * @param tipoFormatoArchivoDAO The tipoFormatoArchivoDAO to set.
	 */
	public void setTipoFormatoArchivoDAO(
			TipoFormatoArchivoDAO tipoFormatoArchivoDAO) {
		this.tipoFormatoArchivoDAO = tipoFormatoArchivoDAO;
	}
	/* 
	 * @see biz.belcorp.ssicc.service.TipoFormatoArchivoService#getTiposFormatoArchivo(biz.belcorp.ssicc.model.TipoFormatoArchivo)
	 */
	public List getTiposFormatoArchivo(TipoFormatoArchivo formato) {
		return this.tipoFormatoArchivoDAO.getTiposFormatoArchivo(formato);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.TipoFormatoArchivoService#getTipoFormatoArchivo(java.lang.String)
	 */
	public TipoFormatoArchivo getTipoFormatoArchivo(String codigo) {
		return this.tipoFormatoArchivoDAO.getTipoFormatoArchivo(codigo);
	}

}
