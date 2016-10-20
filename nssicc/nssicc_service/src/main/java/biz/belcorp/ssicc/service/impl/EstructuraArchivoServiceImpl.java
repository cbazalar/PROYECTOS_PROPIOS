/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.EstructuraArchivoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivoPK;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.EstructuraArchivoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.estructuraArchivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class EstructuraArchivoServiceImpl extends BaseService implements EstructuraArchivoService {

	@Resource(name="sisicc.estructuraArchivoDAO")
	private EstructuraArchivoDAO estructuraArchivoDAO;
	
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#getEstructuraArchivo(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public List getEstructuraArchivo(InterfazPK interfazPK) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getEstructuraArchivo(interfazPK);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#insertEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
		try{
			// Verificamos que no exista un item con el mismo identificador
            // En caso no exista el objeto se lanzará una excepcion ObjectRetrievalFailureException			
			this.estructuraArchivoDAO.getItemEstructuraArchivo(new EstructuraArchivoPK(estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema(), estructuraArchivo.getCodigoInterfaz(), estructuraArchivo.getCodigo()));
			
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(EstructuraArchivo.class, estructuraArchivo.getCodigo());
		}
		catch(ObjectRetrievalFailureException orfe){

            // Verificamos que no exista un item con la misma descripción
            // Creamos el bean que nos servirá como criterio de busqueda
			EstructuraArchivo criteria = new EstructuraArchivo();
			criteria.setCodigoPais(estructuraArchivo.getCodigoPais());
			criteria.setCodigoSistema(estructuraArchivo.getCodigoSistema());
			criteria.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
			criteria.setDescripcionCampo(estructuraArchivo.getDescripcionCampo());
			
			List items = this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
			
			if(items != null && items.size() > 0){
				throw new InvalidDescriptionException(EstructuraArchivo.class, estructuraArchivo.getDescripcionCampo());	
			}
			
			// Seteamos los valores por defecto
			InterfazPK pk = new InterfazPK(estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema(), estructuraArchivo.getCodigoInterfaz());
			estructuraArchivo.setEstado(Constants.ESTADO_ACTIVO);
        	estructuraArchivo.setCodigo(getSiguienteCodigo(pk));
        	estructuraArchivo.setPosicionCampo(getSiguientePosicion(pk));
			
			// Insertamos el nuevo sistema
			this.estructuraArchivoDAO.insertEstructuraArchivo(estructuraArchivo, usuario);
		}
	}

	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#updateEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstructuraArchivo(EstructuraArchivo estructuraArchivo, Usuario usuario) {
        // Verificamos que no exista un item con la misma descripción
        // Creamos el bean que nos servirá como criterio de busqueda
		EstructuraArchivo criteria = new EstructuraArchivo();
		criteria.setCodigoPais(estructuraArchivo.getCodigoPais());
		criteria.setCodigoSistema(estructuraArchivo.getCodigoSistema());
		criteria.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
        criteria.setDescripcionCampo(estructuraArchivo.getDescripcionCampo());
        
        List items = this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
        
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
            	EstructuraArchivo o = (EstructuraArchivo) items.get(i);
                if (!(o.getCodigo().equals(estructuraArchivo.getCodigo()) && 
                		o.getCodigoPais().equals(estructuraArchivo.getCodigoPais()) && 
						o.getCodigoInterfaz().equals(estructuraArchivo.getCodigoInterfaz()) && 
						o.getCodigoSistema().equals(estructuraArchivo.getCodigoSistema()))) {
                    throw new InvalidDescriptionException(EstructuraArchivo.class, estructuraArchivo.getDescripcionCampo());
                }
            }
        }

        // Si no existe un item con la misma descripción realizamos la actualización
        this.estructuraArchivoDAO.updateEstructuraArchivo(estructuraArchivo, usuario);		
	}

	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#removeEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivoPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeEstructuraArchivo(EstructuraArchivoPK primaryKey, Usuario usuario) {
        // Actualizamos el estado del item
        try {
        	EstructuraArchivo item = this.estructuraArchivoDAO.getItemEstructuraArchivo(primaryKey);
            item.setEstado(Constants.ESTADO_INACTIVO);

            // Actualizamos el item
            this.estructuraArchivoDAO.updateEstructuraArchivo(item, usuario);
        }
        catch (ObjectRetrievalFailureException orfe) {
            log.warn(orfe.getMessage());
        }		
	}	
	
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#removeEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivoPK)
	 */
	public void removeEstructuraArchivo(EstructuraArchivoPK primaryKey) {
		// TODO Auto-generated method stub
		this.estructuraArchivoDAO.removeEstructuraArchivo(primaryKey);
	}
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#getEstructuraArchivoByCriteria(biz.belcorp.ssicc.model.EstructuraArchivo)
	 */
	public List getEstructuraArchivoByCriteria(EstructuraArchivo criteria) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getEstructuraArchivoByCriteria(criteria);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#getItemEstructuraArchivo(biz.belcorp.ssicc.model.EstructuraArchivoPK)
	 */
	public EstructuraArchivo getItemEstructuraArchivo(EstructuraArchivoPK estructuraArchivoPK) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getItemEstructuraArchivo(estructuraArchivoPK);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#getSiguienteCodigo(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public String getSiguienteCodigo(InterfazPK pk) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getSiguienteCodigo(pk);
	}
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#getSiguientePosicion(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public int getSiguientePosicion(InterfazPK pk) {
		// TODO Auto-generated method stub
		return this.estructuraArchivoDAO.getSiguientePosicion(pk);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.service.EstructuraArchivoService#updatePosicion(biz.belcorp.ssicc.model.InterfazPK, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePosicion(InterfazPK pk, Usuario usuario) {
		// TODO Auto-generated method stub
		EstructuraArchivo item;
		List estructura = this.estructuraArchivoDAO.getEstructuraArchivo(pk);
		int posicion = 0;
		for(int i=0; i<estructura.size(); i++){
			item = (EstructuraArchivo)estructura.get(i);
			posicion = i+1;			
			if(posicion != item.getPosicionCampo()){
				item.setPosicionCampo(posicion);
				this.estructuraArchivoDAO.updateEstructuraArchivo(item, usuario);
			}
		}
	}
}
