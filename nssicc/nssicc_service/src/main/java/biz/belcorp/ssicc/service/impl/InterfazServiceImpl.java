/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

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
 * 
 * <p>
 * <a href="InterfazServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazServiceImpl extends BaseService implements InterfazService {

	private InterfazDAO interfazDAO;	
	
	/**
	 * @return Returns the interfazDAO.
	 */
	public InterfazDAO getInterfazDAO() {
		return interfazDAO;
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#setInterfazDAO(biz.belcorp.ssicc.dao.InterfazDAO)
	 */
	public void setInterfazDAO(InterfazDAO interfazDAO) {
		this.interfazDAO = interfazDAO;
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfaces(biz.belcorp.ssicc.model.Interfaz)
	 */
	public List getInterfaces(Interfaz interfaz) {
		return this.interfazDAO.getInterfaces(interfaz);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfaces(biz.belcorp.ssicc.model.Interfaz)
	 */
	public List getInterfacesEmpaquetadas(Interfaz interfaz, boolean valor) {
		return this.interfazDAO.getInterfacesEmpaquetadas(interfaz, valor);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfacesByCriteria(java.util.Map)
	 */
	public List getInterfacesByCriteria(Map criteria) {
		return this.interfazDAO.getInterfacesByCriteria(criteria);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfaz(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public Interfaz getInterfaz(InterfazPK primaryKey) {
		return this.interfazDAO.getInterfaz(primaryKey);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getNuevosCodigos()
	 */
	public List getNuevosCodigos() {
		return this.interfazDAO.getNuevosCodigos();
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getFechaUltimoProceso(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public Timestamp getFechaUltimoProceso(InterfazPK primaryKey) {
		return this.interfazDAO.getFechaUltimoProceso(primaryKey);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#getNumeroLote(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public String getNumeroLote(InterfazPK primaryKey) {
		return this.interfazDAO.getNumeroLote(primaryKey);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#insertInterfaz(biz.belcorp.ssicc.model.Interfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertInterfaz(Interfaz interfaz, Usuario usuario) {
		try{
			// Verificamos que no exista una interfaz con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException			
			this.interfazDAO.getInterfaz(new InterfazPK(interfaz.getCodigoPais(), interfaz.getCodigoSistema(), interfaz.getCodigo(), interfaz.getCodigoInterfazEmpaquetada()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(Interfaz.class, interfaz.getCodigo());
			
		}
		catch(ObjectRetrievalFailureException orfe){
			// Seteamos los valores por defecto
			interfaz.setEstado(Constants.ESTADO_ACTIVO);
			
			// Insertamos el nuevo sistema
			this.interfazDAO.insertInterfaz(interfaz, usuario);
		}
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#updateInterfaz(biz.belcorp.ssicc.model.Interfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateInterfaz(Interfaz interfaz, Usuario usuario) {
        // Verificamos que no exista una interfaz con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        Interfaz criteria = new Interfaz();
        criteria.setDescripcion(interfaz.getDescripcion());
        criteria.setCodigoSistema(interfaz.getCodigoSistema());
        List interfaces = this.interfazDAO.getInterfaces(criteria);
        if (interfaces != null && interfaces.size() > 0) {
            for (int i = 0; i < interfaces.size(); i++) {
                Interfaz o = (Interfaz) interfaces.get(i);
                if (!o.getCodigo().equals(interfaz.getCodigo())) {
                    throw new InvalidDescriptionException(Interfaz.class, interfaz.getDescripcion());
                }
            }
        }

        this.interfazDAO.updateInterfaz(interfaz, usuario);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#removeInterfaz(biz.belcorp.ssicc.model.InterfazPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeInterfaz(InterfazPK primaryKey, Usuario usuario) {
        // Actualizamos el estado de la interfaz
		try {
            Interfaz interfaz = this.interfazDAO.getInterfaz(primaryKey);
            interfaz.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos la interfaz
            this.interfazDAO.updateInterfaz(interfaz, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
	}
	
	public void removeInterfaz(InterfazPK primaryKey) {
        // Actualizamos el estado de la interfaz
		try {
            this.interfazDAO.removeInterfaz(primaryKey);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }
	}

	/* 
	 * @see biz.belcorp.ssicc.service.InterfazService#updateNumeroEjecucionInterfaz()
	 */
	public void updateNumeroEjecucionInterfaz(Interfaz interfaz, Usuario usuario) {
		this.interfazDAO.updateNumeroEjecucionInterfaz(interfaz, usuario);
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public List getComponentesInterfazPaquete(InterfazPK primaryKey) {
        throw new UnsupportedOperationException();
    }

    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#getComponentesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
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
	 * @see biz.belcorp.ssicc.service.InterfazService#getNroHilosInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroHilosInterfazPaqueteSeleccionadas(InterfazPK primaryKey) {
		throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#insertComponenteInterfazPaquete(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertComponenteInterfazPaquete(ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.service.InterfazService#removeComponentesInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
     */
    public void removeComponentesInterfazPaquete(InterfazPK primaryKey) {
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
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getNroNivelesInterfazPaqueteSeleccionadas(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public Integer getNroNivelesInterfazPaqueteSeleccionadas(
			InterfazPK interfazEjecucionPK) {	
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getComponentesCompuestaInterfazPaquete(biz.belcorp.ssicc.sisicc.model.InterfazPK)
	 */
	public List getComponentesCompuestaInterfazPaquete(InterfazPK interfazPK) {
		throw new UnsupportedOperationException();
	}

	public List getComponentesCompuestaInterfazPaqueteSeleccionada(
			InterfazPK interfazEjecucionPK) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getOidArchivoControl(java.util.Map)
	 */
	public Long getOidArchivoControl(Map criteria) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#updateEstadoArchivoControl(java.util.Map)
	 */
	public void updateEstadoArchivoControl(Map params) {
		throw new UnsupportedOperationException();
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getPaisByCia(java.util.Map)
	 */
	public String getPaisByCia(Map criteria) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getCodigoProceoBatch(java.util.Map)
	 */
	public String getCodigoProcesoBatch(Map criteria) {
		throw new UnsupportedOperationException();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#updateEstadoCargadasArchivoControl(java.util.Map)
	 */
	public void updateEstadoCargadasArchivoControl(Map params) {
		throw new UnsupportedOperationException();
	}
	
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#insertComponenteInterfazPaqueteModifcado(biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComponenteInterfazPaqueteModificado(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getDescripcionArchivoControl(java.util.Map)
	 */
	public String getDescripcionArchivoControl(Map criteria) {
		throw new UnsupportedOperationException();
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
	 * @see biz.belcorp.ssicc.service.InterfazService#getComponentesInterfazUnitaria(biz.belcorp.ssicc.dao.sisicc.model.InterfazPK)
	 */
	public List getComponentesInterfazUnitaria(InterfazPK primaryKey) {
    	return interfazDAO.getComponentesInterfazUnitaria(primaryKey);
    }
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#deleteComponentesInterfazPaqueteMante(java.util.Map)
	 */
	public void deleteComponentesInterfazPaqueteMante(Map criteria) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfacesAsignadasList(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesAsignadasList(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfacesNoAsignadas(biz.belcorp.ssicc.sisicc.model.Interfaz)
	 */
	public List getInterfacesNoAsignadas(Interfaz interfaz) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#insertComponenteInterfazPaqueteMante(java.util.Map)
	 */
	public void insertComponenteInterfazPaqueteMante(Map criteria) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.InterfazService#getInterfazComponente(java.util.Map)
	 */
	public String getInterfazComponente(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}


}
