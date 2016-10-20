/*
 * Created on 28-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerar;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerarPK;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAGenerarDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ClienteUAGenerarDAO extends DAO {
    /**
     * Registra la informacion de un nuevo cliente.
     * 
     * @param cliente
     *            Datos del cliente a insertar.
     * 
     * @param usuario
     *            Usuario quien hace la invocacion.
     */
    public void insertClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario);

    /**
     * Actualiza los datos de un cliente en base a su llaer primaria.
     * 
     * @param pk
     *            Llave primaria del cliente.
     * 
     * @return Objeto de tipo ClienteUAGenerar, poblado.
     * 
     */
    public void updateClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario);

    /**
     * Selecciona la informacion de un nuevo cliente.
     * 
     * @param cliente
     *            Datos del cliente a insertar.
     * 
     * @param usuario
     *            Usuario quien hace la invocacion.
     */
    public int selectClienteUAGenerar(ClienteUAGenerar clienteOriginal);

    /**
     * Selecciona la informacion de un cliente.
     * 
     * @param cliente
     *            Datos del cliente a insertar.
     * 
     * @param usuario
     *            Usuario quien hace la invocacion.
     */
    public ClienteUAGenerar getClienteUAGenerar(ClienteUAGenerarPK pk);

    /**
     * Obtiene el numero total de registros que existe en la entidad
     * MAE_CLIEN_UNAD
     * 
     * @return Cantidad total de registros
     */
    public Long getNumeroClientesUAGenerar();

    /**
     * Obtiene todos los clientes existentes.
     * 
     * @return Lista de objetos de tipo ClienteUAGenerar, poblados
     */
    public List getClientesUAGenerar();

    /**
     * Elimina todos los clientes existentes.
     */
    public void removeClientesUAGenerar();

    /**
     * Obtiene todos los clientes lideres by criteria.
     * 
     * @return Lista de objetos de tipo Cliente Lider, poblados
     */

    public List getLibretaAhorroByCriteria(Map criteria);

    /**
     * Obtiene todos los clientes lideres by criteria.
     * 
     * @return Lista de objetos de tipo Cliente Lider, poblados
     */

    public LibretaAhorro getLibretaAhorro(Map criteria);

    /**
     * Obtiene todos los clientes lideres by criteria.
     * 
     * @return Lista de objetos de tipo Cliente Lider, poblados
     */

    public void updateLibretaAhorro(Map criteria, Usuario usuario);

    /**
     * Lo mismo que en el Service
     * 
     * @return Longitud del valor.
     */
    public Integer getTamanhoNumeroClientesXPais(String codigoPais)
            throws Exception;

    /**
     * Retorna el nivel socioeconomico del territorio al cual ha sido asignado
     * el cliente. Toma el valor del nivel socioeconomico 2 de territorio, en
     * caso es nulo devuelve el codigo del nivel socioeconomico 1 o nulo en caso
     * ambos sean nulos. Tambien devuelve nulo si el codigo no esta registrado
     * en la entidad de Niveles Socioeconomicos de SICC.
     * 
     * @param codigoPais
     *            Codigo del Pais
     * @param codigoTerritorio
     *            Codigo del Territorio
     * @return El valor del nivel socioeconomico del territorio.
     */
    public String getNivelSocioeconomico(String codigoPais,
            Long codigoTerritorio);

    /**
     * Obtienen los tipos de regimen
     * 
     * @return
     */    
	public List getTiposRegimen();

}