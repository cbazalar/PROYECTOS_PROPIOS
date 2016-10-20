/*
 * Created on 05-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.TipoFormatoArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoFormatoArchivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.tipoFormatoArchivoDAO")
public class TipoFormatoArchivoDAOiBatis extends BaseDAOiBatis implements
		TipoFormatoArchivoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.TipoFormatoArchivoDAO#getTiposFormatoArchivo(biz.belcorp.ssicc.model.TipoFormatoArchivo)
	 */
	public List getTiposFormatoArchivo(TipoFormatoArchivo formato) {
		List formatos = getSqlMapClientTemplate().queryForList("sisicc.TipoFormatoArchivoSQL.getTiposFormatoArchivo", formato);
		return formatos;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.TipoFormatoArchivoDAO#getTipoFormatoArchivo(java.lang.String)
	 */
	public TipoFormatoArchivo getTipoFormatoArchivo(String codigo) {
		return (TipoFormatoArchivo)getSqlMapClientTemplate().queryForObject("sisicc.TipoFormatoArchivoSQL.getTipoFormatoArchivo", codigo);
	}

}
