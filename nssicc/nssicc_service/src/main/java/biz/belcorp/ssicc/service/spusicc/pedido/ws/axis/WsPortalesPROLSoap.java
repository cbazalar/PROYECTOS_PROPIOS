/**
 * WsPortalesPROLSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.pedido.ws.axis;

public interface WsPortalesPROLSoap extends java.rmi.Remote {

    // envio de montos del pedido: pedido catalogo, pedido real
    public void envioTotalPedidos(java.lang.String pais, java.lang.String marca, java.lang.String consultora, java.lang.String campania, javax.xml.rpc.holders.StringHolder pedidocatalogo, javax.xml.rpc.holders.StringHolder pedidoreal) throws java.rmi.RemoteException;

    // Indica si el pedido ha sido marcado=ENVIADO
    public String pedidosMarcados(java.lang.String pais, java.lang.String marca, java.lang.String consultora, java.lang.String campania) throws java.rmi.RemoteException;

    // Activa el indicador PROL para el pa�s
    public void activacionPROL(java.lang.String pais, java.lang.String marca, javax.xml.rpc.holders.StringHolder indicador, javax.xml.rpc.holders.StringHolder mensaje) throws java.rmi.RemoteException;

    // Desactiva el indicador PROL para el pa�s
    public void desactivacionPROL(java.lang.String prefijoPais, java.lang.String marca, javax.xml.rpc.holders.StringHolder indicador, javax.xml.rpc.holders.StringHolder mensaje) throws java.rmi.RemoteException;

    // Verificar el indicador PROL para el pa�s y la marca
    public boolean verificarPROL(java.lang.String prefijoPais, java.lang.String marca) throws java.rmi.RemoteException;

    // Indica si la matriz esta disponible=true
    public boolean matrizDisponible(java.lang.String prefijoPaisISO, java.lang.String codigoMarca, java.lang.String anocampania) throws java.rmi.RemoteException;

    // Devuelve Completado (indicador si se ha recibido y procesado correctamente
    // el pedido).
    public ArrayOfAnyType executeRespuestaPedidoPortalWeb(java.lang.String codigoPais, java.lang.String codigoMarca, int identificador, java.lang.String XML_Salida) throws java.rmi.RemoteException;

    // Modifica el estado de la validacion de PROL
    public void modificarEstadoValidacionPROL(java.lang.String prefijoPais, java.lang.String marca, boolean estado, javax.xml.rpc.holders.StringHolder indicador, javax.xml.rpc.holders.StringHolder mensaje) throws java.rmi.RemoteException;
}
