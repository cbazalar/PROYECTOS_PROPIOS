
/**
 * ZMM_RPT_SALDOS_SICCServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package biz.belcorp.ssicc.reportes.ws.client.stub.sap.rec.cuadre;

    /**
     *  ZMM_RPT_SALDOS_SICCServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ZMM_RPT_SALDOS_SICCServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ZMM_RPT_SALDOS_SICCServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ZMM_RPT_SALDOS_SICCServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for zmmRptSaldosSicc method
            * override this method for handling normal response from zmmRptSaldosSicc operation
            */
           public void receiveResultzmmRptSaldosSicc(
        		   biz.belcorp.ssicc.reportes.ws.client.stub.sap.rec.cuadre.ZMM_RPT_SALDOS_SICCServiceStub.ZmmRptSaldosSiccResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from zmmRptSaldosSicc operation
           */
            public void receiveErrorzmmRptSaldosSicc(java.lang.Exception e) {
            }
                


    }
    