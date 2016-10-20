/*
 * Created on 20/09/2006 07:30:01 PM
 * biz.belcorp.ssicc.service.impl.Interfaz2ServiceImpl
 */
package biz.belcorp.ssicc.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ComponenteInterfazPaquete;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Interfaz2ServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class Interfaz2ServiceImpl extends BaseService implements
        InterfazService {

	@Resource(name="sisicc.interfazDAO")
    private InterfazDAO interfazDAO;

	   /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getInterfaces(biz.belcorp.ssicc.sisicc.model.Interfaz)
     */
    public List getInterfaces(Interfaz interfaz) {
        return interfazDAO.getInterfaces(interfaz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getInterfacesByCriteria(java.util.Map)
     */
    public List getInterfacesByCriteria(Map criteria) {
        return interfazDAO.getInterfacesByCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getInterfaz(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public Interfaz getInterfaz(InterfazPK primaryKey) {
        return interfazDAO.getInterfaz(primaryKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#insertInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertInterfaz(Interfaz interfaz, Usuario usuario) {
        try {
            // Verificamos que no exista una interfaz con el mismo identificador
            // En caso no exista el objeto se lanzará una excepcion
            // ObjectRetrievalFailureException
        	InterfazPK primaryKey = new InterfazPK(interfaz.getCodigoPais(), interfaz.getCodigoSistema(), interfaz.getCodigo());
    		if (interfazDAO.getInterfaz(primaryKey) == null) {
    			throw new ObjectRetrievalFailureException(Interfaz.class, primaryKey);
			}

            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
            throw new InvalidIdentifierException(Interfaz.class, interfaz
                    .getCodigo());

        } catch (ObjectRetrievalFailureException orfe) {
            // Seteamos los valores por defecto
            interfaz.setEstado(Constants.ESTADO_ACTIVO);

            // Insertamos el nuevo sistema
            interfazDAO.insertInterfaz(interfaz, usuario);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#updateInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateInterfaz(Interfaz interfaz, Usuario usuario) {
        // Verificamos que no exista una interfaz con la misma descripción
        // Creamos el bean que nos servirá como criterio de busqueda
        Interfaz criteria = new Interfaz();
        criteria.setDescripcion(interfaz.getDescripcion());
        criteria.setCodigoSistema(interfaz.getCodigoSistema());
        List interfaces = interfazDAO.getInterfaces(criteria);
        if (interfaces != null && interfaces.size() > 0) {
            for (int i = 0; i < interfaces.size(); i++) {
                Interfaz o = (Interfaz) interfaces.get(i);
                if (!o.getCodigo().equals(interfaz.getCodigo())) {
                    throw new InvalidDescriptionException(Interfaz.class,
                            interfaz.getDescripcion());
                }
            }
        }

        interfazDAO.updateInterfaz(interfaz, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#removeInterfaz(biz.belcorp.ssicc.sisicc.model.InterfazPK,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void removeInterfaz(InterfazPK primaryKey, Usuario usuario) {
        // Actualizamos el estado de la interfaz
        try {
            Interfaz interfaz = interfazDAO.getInterfaz(primaryKey);
            interfaz.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos la interfaz
            interfazDAO.updateInterfaz(interfaz, usuario);
        } catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public List getComponentesInterfazPaquete(InterfazPK primaryKey) {
        return interfazDAO.getComponentesInterfazPaquete(primaryKey);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getComponentesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public List getComponentesInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
    	return interfazDAO.getComponentesInterfazPaqueteSeleccionadas(primaryKey);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getNroHilosInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public Integer getNroHilosInterfazPaquete(InterfazPK primaryKey) {
    	return interfazDAO.getNroHilosInterfazPaquete(primaryKey);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getNroHilosInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public Integer getNroHilosInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
    	return interfazDAO.getNroHilosInterfazPaqueteSeleccionadas(primaryKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#insertComponenteInterfazPaquete(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertComponenteInterfazPaquete(
            ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
        interfazDAO.insertComponenteInterfazPaquete(componenteInterfazPaquete,
                usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#removeComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public void removeComponentesInterfazPaquete(InterfazPK primaryKey) {
        interfazDAO.removeComponentesInterfazPaquete(primaryKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getNuevosCodigos()
     */
    public List getNuevosCodigos() {
        return interfazDAO.getNuevosCodigos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getFechaUltimoProceso(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public Timestamp getFechaUltimoProceso(InterfazPK primaryKey) {
        return interfazDAO.getFechaUltimoProceso(primaryKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getNumeroLote(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public String getNumeroLote(InterfazPK primaryKey) {
        return interfazDAO.getNumeroLote(primaryKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.service.Interfaz2Service#updateNumeroEjecucionInterfaz(biz.belcorp.ssicc.sisicc.model.Interfaz,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateNumeroEjecucionInterfaz(Interfaz interfaz, Usuario usuario) {
        interfazDAO.updateNumeroEjecucionInterfaz(interfaz, usuario);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.Interfaz2Service#getInterfacesEmpaquetadas(biz.belcorp.ssicc.sisicc.model.Interfaz, boolean)
     */
    public List getInterfacesEmpaquetadas(Interfaz interfaz, boolean valor) {
        return interfazDAO.getInterfacesEmpaquetadas(interfaz, valor);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getExtensionArchivo(java.util.Map)
     */
    public List getExtensionArchivo(Map criteria) {
        throw new UnsupportedOperationException();
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#updateNumLoteGenSolicitudMonetaria(java.lang.String, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateNumLoteGenSolicitudMonetaria(Map criteria, Usuario usuario) {
        interfazDAO.updateNumLoteGenSolicitudMonetaria(criteria, usuario);
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getNroNivelesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaquete(InterfazPK interfazEjecucionPK) {
		return interfazDAO.getNroNivelesInterfazPaquete(interfazEjecucionPK);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getNroNivelesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaqueteSeleccionadas(
			InterfazPK interfazEjecucionPK) {
		return interfazDAO.getNroNivelesInterfazPaqueteSeleccionadas(interfazEjecucionPK);
	}
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getComponentesCompuestaInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaquete(InterfazPK interfazPK) {
		return interfazDAO.getComponentesCompuestaInterfazPaquete(interfazPK);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getComponentesCompuestaInterfazPaqueteSeleccionada(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaqueteSeleccionada(
			InterfazPK interfazEjecucionPK) {
		return interfazDAO.getComponentesCompuestaInterfazPaqueteSeleccionada(interfazEjecucionPK);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getOidArchivoControl(java.util.Map)
	 */
	public Long getOidArchivoControl(Map criteria) {
		return interfazDAO.getOidArchivoControl(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#updateEstadoArchivoControl(java.util.Map)
	 */
	public void updateEstadoArchivoControl(Map params) {
		interfazDAO.updateEstadoArchivoControl(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getPaisByCia(java.util.Map)
	 */
	public String getPaisByCia(Map criteria) {
		return interfazDAO.getPaisByCia(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getCodigoProceoBatch(java.util.Map)
	 */
	public String getCodigoProcesoBatch(Map criteria) {
		return interfazDAO.getCodigoProcesoBatch(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#updateEstadoCargadasArchivoControl(java.util.Map)
	 */
	public void updateEstadoCargadasArchivoControl(Map params) {
		interfazDAO.updateEstadoCargadasArchivoControl(params);
		
	}
        
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#insertComponenteInterfazPaqueteModifcado(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComponenteInterfazPaqueteModificado(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
        interfazDAO.insertComponenteInterfazPaqueteModificado(componenteInterfazPaquete,
                usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getDescripcionArchivoControl(java.util.Map)
	 */
	public String getDescripcionArchivoControl(Map criteria) {
		return interfazDAO.getDescripcionArchivoControl(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.InterfazService#getNuevoCodigo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getNuevoCodigo(String codigoPais, String codigoSistema, String tipoGeneracion) {
		//return interfazDAO.getNuevoCodigo(codigoPais, codigoSistema, tipoGeneracion);
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.InterfazService#getInterfacesBySistema(java.lang.String, java.lang.String)
	 */
	@Override
	public List getInterfacesBySistema(String codigoPais, String codigoSistema) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoSistema", codigoSistema);
		
		return interfazDAO.getInterfacesBySistema(criteria);
	}    
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfacesAsignadasList(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesAsignadasList(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return interfazDAO.getInterfacesAsignadasList(interfaz);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfacesNoAsignadas(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesNoAsignadas(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return interfazDAO.getInterfacesNoAsignadas(interfaz);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#deleteComponentesInterfazPaqueteMante(java.util.Map)
	 */
	public void deleteComponentesInterfazPaqueteMante(Map criteria) {
		interfazDAO.deleteComponentesInterfazPaqueteMante(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#insertComponenteInterfazPaqueteMante(java.util.Map)
	 */
	public void insertComponenteInterfazPaqueteMante(Map criteria) {
		interfazDAO.insertComponenteInterfazPaqueteMante(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfazComponente(java.util.Map)
	 */
	public String getInterfazComponente(Map criteria) {
		return interfazDAO.getInterfazComponente(criteria);
	}

	
/* INI NSSICC *
    
    /**
     * @param primaryKey
     * @return
     */
    public List getComponentesInterfazUnitaria(InterfazPK primaryKey) {
    	return interfazDAO.getComponentesInterfazUnitaria(primaryKey);
    }
    
    /* FIN NSSICC*/
}
