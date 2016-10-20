/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ComponenteInterfazPaquete;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;

/**
 * TODO    
 * 
 * <p>
 * <a href="InterfazDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */

public interface InterfazDAO extends DAO {

    /**
     * Obtiene un listado de todas las Interfaces
     */
    public List getInterfaces(Interfaz interfaz);

    /**
     * Obtiene un listado de todas las Interfaces en base a ciertos criterios
     * los cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            Objeto Map cuyos atributos son usados como criterios de
     *            búsqueda.
     * 
     * @return Lista de objetos de tipo Interfaz, poblados.
     */
    public List getInterfacesByCriteria(Map criteria);

    /**
     * Obtiene la informacion de la Interfaz en base a su llave primaria. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     * 
     * @param primaryKey
     *            La llave primaria de la Interfaz.
     * 
     * @return Objeto de tipo Interfaz, poblado.
     */
    public Interfaz getInterfaz(final InterfazPK primaryKey);

    /**
     * Registra la información de una nueva Interfaz.
     * 
     * @param interfaz
     *            Objeto de tipo Interfaz a ser insertado.
     * 
     * @param usuario
     *            Objeto de tipo Usuario, quien hace la invocaciòn.
     */
    public void insertInterfaz(Interfaz interfaz, Usuario usuario);

    /**
     * Actualiza la informacion de una Interfaz.
     * 
     * @param interfaz
     *            Objeto de tipo Interfaz a ser actualizado.
     * 
     * @param usuario
     *            Objeto de tipo Usuario, quien hace la invocaciòn.
     */
    public void updateInterfaz(Interfaz interfaz, Usuario usuario);

    /**
     * Elimina una Interfaz de la base de datos en base a su llave primaria.
     * 
     * @param primaryKey
     *            Llave primaria de la Interfaz.
     */
    public void removeInterfaz(final InterfazPK primaryKey);

    /**
     * Obtiene la informacion de las interfaces unitarias que forman parte de un
     * paquete.
     * 
     * @param primaryKey
     *            Llave primaria de la interfaz de tipo paquete
     * @return Lista conteniendo objetos Interfaz.
     */
    public List getComponentesInterfazPaquete(final InterfazPK primaryKey);

    /**
     * Obtiene una lista de objetos Interfaz conteniendo la informacion de las
     * interfaces unitarias que componen una interfaz de tipo paquete en base a
     * la lista de interfaces seleccionadas
     * @param primaryKey
     * @return
     */
    public List getComponentesInterfazPaqueteSeleccionadas(InterfazPK primaryKey);

    /**
     * Devuelve Nro de Hilos a Ejecutar para una Interface Paquete
     * @param primaryKey
     * @return
     */
    public Integer getNroHilosInterfazPaquete(InterfazPK primaryKey);
    
    /**
     * Devuelve Nro de Hilos a Ejecutar para una Interface Paquete
     * @param primaryKey
     * @return
     */
    public Integer getNroHilosInterfazPaqueteSeleccionadas(InterfazPK primaryKey);

    /**
     * Inserta la informacion de un ComponenteInterfazPaquete en la base de
     * datos
     * 
     * @param componenteInterfazPaquete
     * @param usuario
     */
    public void insertComponenteInterfazPaquete(
            ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario);

    /**
     * Elimina todos los registros del detalle de componentes de la base de
     * datos en base a la llave primaria de la interfaz de tipo paquete
     * 
     * @param primaryKey Llave primaria de la interfaz de tipo paquete
     */
    public void removeComponentesInterfazPaquete(InterfazPK primaryKey);

    /**
     * Obtiene la fecha de inicio de la ultima vez que fue procesada la
     * Interfaz, en base a su llave primaria.
     * 
     * @param primaryKey
     *            Llave primaria de la Interfaz.
     * 
     * @return Fecha del ultimo proceso de la Interfaz.
     */
    public Timestamp getFechaUltimoProceso(final InterfazPK primaryKey);

    /**
     * Obtiene el número de lote generado para la Interfaz actual, en base a su
     * llave primaria.
     * 
     * @param primaryKey
     *            Llave primaria de la Interfaz.
     * 
     * @return El número de lote de la Interfaz.
     */
    public String getNumeroLote(final InterfazPK primaryKey);

    /**
     * Obtiene una lista de los codigos siguientes, de las Interfaces
     * existentes.
     * 
     * @return Lista de objetos InterfazNuevoCodigo, poblados.
     */
    public List getNuevosCodigos();

    /**
     * Actualiza la informacion de una Interfaz.
     * 
     * @param interfaz
     *            Objeto de tipo Interfaz a ser actualizado.
     * 
     * @param usuario
     *            Objeto de tipo Usuario, quien hace la invocaciòn.
     */
    public void updateNumeroEjecucionInterfaz(Interfaz interfaz, Usuario usuario);

    /**
     * 
     * @param criteria
     * @return
     */
    public List getInterfacesBySistema(Map criteria);
    
    /**
     * 
     * @param criteria
     * @return
     */
    public List getProcesoBatchBySistema(Map criteria) ;
    
    /**
     * 
     * @param interfaz
     * @param valor
     * @return
     * @deprecated a eliminar
     */
    public List getInterfacesEmpaquetadas(Interfaz interfaz, boolean valor);

    
    public void updateNumLoteGenSolicitudMonetaria(Map criteria, Usuario usuario);

	/**
	 * retorna el numero de niveles 
	 * @param interfazEjecucionPK
	 * @return
	 */
	public Integer getNroNivelesInterfazPaquete(InterfazPK interfazEjecucionPK);

	/**
	 * retorna el numero de niveles
	 * @param interfazEjecucionPK
	 * @return
	 */
	public Integer getNroNivelesInterfazPaqueteSeleccionadas(
			InterfazPK interfazEjecucionPK);

	/**Lista unitarias de interfaz de paquete compuesta 
	 * @param interfazPK
	 * @return
	 */
	public List getComponentesCompuestaInterfazPaquete(InterfazPK interfazPK);
	
	/**
	 * retorna la lista de interfaces unitarios/paquete del paquete compuesto seleccionado
	 * @param interfazEjecucionPK
	 * @return
	 */
	public List getComponentesCompuestaInterfazPaqueteSeleccionada(
			InterfazPK interfazEjecucionPK);

	/**
	 * retorna el oid control
	 * @param criteria
	 * @return
	 */
	public Long getOidArchivoControl(Map criteria);

	/**
	 * Actualiza el estado de archivo de control
	 * @param params
	 */
	public void updateEstadoArchivoControl(Map params);
	
	/**
	 * retrona el pais comercial
	 * @param criteria
	 * @return
	 */
	public String getPaisByCia(Map criteria);

	/**
	 * retorna el codigo de proceso batch configurado
	 * @param criteria
	 * @return
	 */
	public String getCodigoProcesoBatch(Map criteria);
	
	/**
	 * Actualiza el estado de archivo de control de las interfaces unitarias no cargadas
	 * @param params
	 */
	public void updateEstadoCargadasArchivoControl(Map params);

    /**
     * Inserta la informacion de un ComponenteInterfazPaquete en la base de
     * datos
     * 
     * @param componenteInterfazPaquete
     * @param usuario
     */	
	public void insertComponenteInterfazPaqueteModificado(
			ComponenteInterfazPaquete componenteInterfazPaquete, Usuario usuario);

	/**
	 * retorna la descripcion asociado al archivo de control del historico de lotes
	 * 
	 * @param criteria
	 * @return
	 */
	public String getDescripcionArchivoControl(Map criteria);
	
	/**
	 * Devuelve las interfaces no asignadas a una interfaz tipo paquete
	 * @param interfaz
	 * @return
	 */
	public List getInterfacesNoAsignadas(Interfaz interfaz);
	
	/**
	 * Devuelve las interfaces asignadas a una interfaz tipo paquete
	 * @param interfaz
	 * @return
	 */
	public List getInterfacesAsignadasList(Interfaz interfaz);
	
	/**
	 * Inserta una interfaz a la tabla BAS_COMPO_PAQUE
	 * @param criteria
	 */
	public void insertComponenteInterfazPaqueteMante(
			Map criteria);
	
	/**
	 * Elimina una interfaz de la tabla BAS_COMPO_PAQUE
	 * @param criteria
	 */
	public void deleteComponentesInterfazPaqueteMante(Map criteria);
	
	/**
	 * Devuelve si existe una interfaz en el componente
	 * @param criteria
	 * @return
	 */
	public String getInterfazComponente(Map criteria);

	
/* INI NSSICC *
    
    /**
     * @param primaryKey
     * @return
     */
    public List getComponentesInterfazUnitaria(InterfazPK primaryKey);
    
    /* FIN NSSICC*/
	
}