package biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia;


public interface WsInfoCrediticiaSoap extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public java.lang.String consultar(java.lang.String codigoConsultora, java.lang.String token) throws java.rmi.RemoteException;
}
