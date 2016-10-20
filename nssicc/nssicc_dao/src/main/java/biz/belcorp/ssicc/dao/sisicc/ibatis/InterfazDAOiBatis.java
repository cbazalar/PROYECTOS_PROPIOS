/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ComponenteInterfazPaquete;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazDAOiBatis extends BaseDAOiBatis implements InterfazDAO {

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getFechaUltimoProceso(biz.belcorp.ssicc.model.InterfazPK)
     */
    public Timestamp getFechaUltimoProceso(InterfazPK primaryKey) {
        Timestamp fecha = (Timestamp) getSqlMapClientTemplate().queryForObject(
                "sisicc.ConfiguracionInterfazSQL.getFechaUltimoProceso",
                primaryKey);
        return fecha;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getInterfaces(biz.belcorp.ssicc.model.Interfaz)
     */
    public List getInterfaces(Interfaz interfaz) {
        List interfaces = getSqlMapClientTemplate().queryForList(
                "sisicc.ConfiguracionInterfazSQL.getInterfaces", interfaz);
        return interfaces;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getInterfaces(biz.belcorp.ssicc.model.Interfaz)
     */
    public List getInterfacesEmpaquetadas(Interfaz interfaz, boolean valor) {
        List interfaces = null;
        if (valor)
            interfaces = getSqlMapClientTemplate()
                    .queryForList(
                            "sisicc.ConfiguracionInterfazSQL.getInterfacesEmpaquetadas",
                            interfaz);
        else
            interfaces = getSqlMapClientTemplate()
                    .queryForList(
                            "sisicc.ConfiguracionInterfazSQL.getInterfacesNoEmpaquetadas",
                            interfaz);
        return interfaces;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getInterfacesByCriteria(java.util.Map)
     */
    public List getInterfacesByCriteria(Map criteria) {
        List interfaces = getSqlMapClientTemplate().queryForList(
                "sisicc.ConfiguracionInterfazSQL.getInterfacesByCriteria",
                criteria);
        return interfaces;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getInterfaz(biz.belcorp.ssicc.model.InterfazPK)
     */
    public Interfaz getInterfaz(InterfazPK primaryKey) {
        Interfaz interfaz = (Interfaz) getSqlMapClientTemplate()
                .queryForObject("sisicc.ConfiguracionInterfazSQL.getInterfaz",
                        primaryKey);
        if (interfaz == null) {
            throw new ObjectRetrievalFailureException(Interfaz.class,
                    primaryKey);
        }
        // obtenemos los parametros de la interfaz.
        List parametros = getSqlMapClientTemplate().queryForList(
                "sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz",
                primaryKey);
        interfaz.setParametros(parametros);
        return interfaz;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getNuevosCodigos()
     */
    public List getNuevosCodigos() {
        List codigos = getSqlMapClientTemplate().queryForList(
                "sisicc.ConfiguracionInterfazSQL.getNuevosCodigos", null);
        return codigos;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#getNumeroLote(biz.belcorp.ssicc.model.InterfazPK)
     */
    public String getNumeroLote(InterfazPK primaryKey) {
        String numeroLote = (String) getSqlMapClientTemplate().queryForObject(
                "sisicc.ConfiguracionInterfazSQL.getNumeroLote", primaryKey);
        return numeroLote;
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#insertInterfaz(biz.belcorp.ssicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertInterfaz(Interfaz interfaz, Usuario usuario) {
        getSqlMapClientTemplate().update(
                "sisicc.ConfiguracionInterfazSQL.insertInterfaz", interfaz);
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#removeInterfaz(biz.belcorp.ssicc.model.InterfazPK)
     */
    public void removeInterfaz(InterfazPK primaryKey) {
        getSqlMapClientTemplate().update(
                "sisicc.ConfiguracionInterfazSQL.removeInterfaz", primaryKey);
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#updateInterfaz(biz.belcorp.ssicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateInterfaz(Interfaz interfaz, Usuario usuario) {
        getSqlMapClientTemplate().update(
                "sisicc.ConfiguracionInterfazSQL.updateInterfaz", interfaz);
    }

    /*
     * @see biz.belcorp.ssicc.dao.InterfazDAO#updateNumeroEjecucionInterfaz(biz.belcorp.ssicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateNumeroEjecucionInterfaz(Interfaz interfaz, Usuario usuario) {
        getSqlMapClientTemplate()
                .update(
                        "sisicc.ConfiguracionInterfazSQL.updateNumeroEjecucionInterfaz",
                        interfaz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfacesBySistema(java.util.Map)
     */
    public List getInterfacesBySistema(Map criteria) {
        List interfaces = getSqlMapClientTemplate().queryForList(
                "sisicc.ConfiguracionInterfazSQL.getInterfacesBySistema",
                criteria);
        return interfaces;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getProcesoBatchBySistema(java.util.Map)
     */
    public List getProcesoBatchBySistema(Map criteria) {
        List interfaces = getSqlMapClientTemplate().queryForList(
                "sisicc.ConfiguracionInterfazSQL.getProcesoBatchBySistema",
                criteria);
        return interfaces;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public List getComponentesInterfazPaquete(InterfazPK primaryKey) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public List getComponentesInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroHilosInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroHilosInterfazPaquete(InterfazPK primaryKey) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroHilosInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroHilosInterfazPaqueteSeleccionadas(InterfazPK primaryKey){
		throw new UnsupportedOperationException();
	}

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#insertComponenteInterfazPaquete(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertComponenteInterfazPaquete(
            ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#removeComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public void removeComponentesInterfazPaquete(InterfazPK primaryKey) {
        throw new UnsupportedOperationException();
    }
    
    public void updateNumLoteGenSolicitudMonetaria(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate()
		.update(
				"sisicc.ConfiguracionInterfaz2SQL.updateNumLoteGenSolicitudMonetaria",	criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroNivelesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaquete(InterfazPK interfazEjecucionPK) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getNroNivelesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaqueteSeleccionadas(
			InterfazPK interfazEjecucionPK) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesCompuestaInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaquete(InterfazPK interfazPK) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getComponentesCompuestaInterfazPaqueteSeleccionada(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaqueteSeleccionada(
			InterfazPK interfazEjecucionPK) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getOidArchivoControl(java.util.Map)
	 */
	public Long getOidArchivoControl(Map criteria) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#updateEstadoArchivoControl(java.util.Map)
	 */
	public void updateEstadoArchivoControl(Map params) {
		throw new UnsupportedOperationException();
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getPaisByCia(java.util.Map)
	 */
	public String getPaisByCia(Map criteria) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getCodigoProceoBatch(java.util.Map)
	 */
	public String getCodigoProcesoBatch(Map criteria) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDAO#updateEstadoCargadasArchivoControl(java.util.Map)
	 */
	public void updateEstadoCargadasArchivoControl(Map params) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#insertComponenteInterfazPaqueteModifcado(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComponenteInterfazPaqueteModificado(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getDescripcionArchivoControl(java.util.Map)
	 */
	public String getDescripcionArchivoControl(Map criteria) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazDAO#getComponentesInterfazUnitaria(biz.belcorp.ssicc.dao.sisicc.model.InterfazPK)
	 */
	public List getComponentesInterfazUnitaria(InterfazPK primaryKey) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfacesAsignadasList(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesAsignadasList(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfacesNoAsignadas(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesNoAsignadas(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#deleteComponentesInterfazPaqueteMante(java.util.Map)
	 */
	public void deleteComponentesInterfazPaqueteMante(Map criteria) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#insertComponenteInterfazPaqueteMante(java.util.Map)
	 */
	public void insertComponenteInterfazPaqueteMante(Map criteria) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDAO#getInterfazComponente(java.util.Map)
	 */
	public String getInterfazComponente(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
