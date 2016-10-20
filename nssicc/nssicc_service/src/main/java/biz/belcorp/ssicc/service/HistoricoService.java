/*
 * Created on 22-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="HistoricoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface HistoricoService extends Service {

    /**
     * Registra la informacin de un nuevo historico.
     * 
     * @param historico
     * Objeto de tipo Historico a ser insertado.
     *            
     * @param usuario
     * Objeto de tipo Usuario, quien hace la invocacin.
     */
    public void insertHistorico(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente
     *  
     * @param historico
     * Objeto de tipo Historico a ser actualizado.
     * 
     * @param usuario
     * Objeto de tipo Usuario, quien hace la invocacin.
     */
    public void updateHistorico(Historico historico, Usuario usuario);
    
	/**
	 * Obtiene un listado de todos los historicos en base a ciertos criterios los
	 * cuales son enviados atra vez de un Map.
	 * 
	 * @param criteria
	 * Objeto Map cuyos atributos son usados como criterios de busqueda.
	 * 
	 * @return
	 * Lista de objetos Historico poblados.
	 */
    public List getHistoricosByCriteria(Map criteria);
    
    
    /**
     * Obtiene un listado de todos los historicos en base a ciertos criterios los
	 * cuales son enviados a travez de un Map para proceso MultiHilo.
     * @param criteria
     * @return
     */
    public List<Historico> getHistoricosLotesMultiHilo(Map criteria);
    
    
    /**
     * Obtiene un listado de todos los historicos en base a ciertos criterios los
     * cuales son enviados atra vez de un Map.
     * 
     * @param criteria
     * Objeto Map cuyos atributos son usados como criterios de busqueda.
     * 
     * @return
     * Lista de objetos Historico poblados.
     */
    public List getUltimoHistoricoByCodInterfaz(Map criteria);
    
    /**
     * Devuelve Descripcion del Error del BAS_HISTO_LOTES en base a los criterios ingresados
     * @param criteria
     * @return
     */
    public String getDevuelveDescripcionErrorInterfaz(Map criteria);
    
    
    /* INI FRAMEWORK NSSICC PRUEBAS TRANSACCION */
    public void insertHistorico2(Historico historico, Usuario usuario);
	
	/* 
	 * @see biz.belcorp.ssicc.service.HistoricoService#updateHistorico(biz.belcorp.ssicc.model.Historico, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistorico2(Historico historico, Usuario usuario);
	 /* FINI FRAMEWORK NSSICC PRUEBAS TRANSACCION */
	
	
    /* INI FRAMEWORK NSSICC */
    
    /**
     * Actualiza la informacion de un historico existente con respecto al Inicio de los 
     * Procesos Anteriores a la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoIniProcesoAnteriorInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente con respecto a la Finalizacion de los 
     * Procesos Anteriores a la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoFinProcesoAnteriorInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente con respecto al Inicio de 
     * la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoIniInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente con respecto a la Finalizacion de la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoFinInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente con respecto al Inicio de los 
     * Procesos Posteriores a la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoIniProcesoPosteriorInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Actualiza la informacion de un historico existente con respecto a la Finalizacion de los 
     * Procesos Posteriores a la ejecucion de la Interfaz
     * @param historico
     * @param usuario
     */
    public void updateHistoricoFinProcesoPosteriorInterfaz(Historico historico, Usuario usuario);
    
    /**
     * Obtiene un listado de todos los historicos en base a ciertos criterios los
	 * cuales son enviados a travez de un Map para proceso MultiHilo en base al Nro de Lote
     * @param criteria
     * @return
     */
    public List<Historico> getHistoricosLotesMultiHiloLote(Map criteria);
    
    /* FIN FRAMEWORK NSSICC */
	
}


