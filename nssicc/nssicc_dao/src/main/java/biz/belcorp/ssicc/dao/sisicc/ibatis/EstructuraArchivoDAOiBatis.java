/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.EstructuraArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivoPK;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.estructuraArchivoDAO")
public class EstructuraArchivoDAOiBatis extends BaseDAOiBatis implements EstructuraArchivoDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#getEstructuraArchivo(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public List getEstructuraArchivo(InterfazPK interfazPK) {
		List estructura = getSqlMapClientTemplate().queryForList("sisicc.EstructuraArchivoSQL.getEstructuraArchivo", interfazPK);
		return estructura;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#insertEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.EstructuraArchivoSQL.insertEstructuraArchivo", estructuraArchivo);	
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#updateEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.EstructuraArchivoSQL.updateEstructuraArchivo", estructuraArchivo);
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#removeEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivoPK)
	 */
	public void removeEstructuraArchivo(EstructuraArchivoPK primaryKey) {
		getSqlMapClientTemplate().update("sisicc.EstructuraArchivoSQL.removeEstructuraArchivo", primaryKey);
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#getEstructuraArchivoByCriteria(biz.belcorp.ssicc.model.EstructuraArchivo)
	 */
	public List getEstructuraArchivoByCriteria(EstructuraArchivo criteria) {
		List estructura = getSqlMapClientTemplate().queryForList("sisicc.EstructuraArchivoSQL.getEstructuraArchivoByCriteria", criteria);
		return estructura;
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#getItemEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivoPK)
	 */
	public EstructuraArchivo getItemEstructuraArchivo(EstructuraArchivoPK estructuraArchivoPK) {
		// TODO Auto-generated method stub
		EstructuraArchivo item = (EstructuraArchivo)getSqlMapClientTemplate().queryForObject("sisicc.EstructuraArchivoSQL.getItemEstructuraArchivo", estructuraArchivoPK);
		
        if (item == null) {
            throw new ObjectRetrievalFailureException(Interfaz.class, estructuraArchivoPK);
        }
		return item;
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#getSiguienteCodigo(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public String getSiguienteCodigo(InterfazPK pk) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.EstructuraArchivoSQL.getSiguienteCodigo", pk);
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.EstructuraArchivoDAO#getSiguientePosicion(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public int getSiguientePosicion(InterfazPK pk) {
		// TODO Auto-generated method stub
		return ((Integer)getSqlMapClientTemplate().queryForObject("sisicc.EstructuraArchivoSQL.getSiguientePosicion", pk)).intValue();
	}
}
