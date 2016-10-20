
/**
 * MsgFaultObtenerValidacionCrediticia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package biz.belcorp.www.soa.business.ffvv.sicc.prospectobs;

public class MsgFaultObtenerValidacionCrediticia extends java.lang.Exception{

    private static final long serialVersionUID = 1352172217485L;
    
    private biz.belcorp.www.ssg.comun.MsgFault faultMessage;

    
        public MsgFaultObtenerValidacionCrediticia() {
            super("MsgFaultObtenerValidacionCrediticia");
        }

        public MsgFaultObtenerValidacionCrediticia(java.lang.String s) {
           super(s);
        }

        public MsgFaultObtenerValidacionCrediticia(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MsgFaultObtenerValidacionCrediticia(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(biz.belcorp.www.ssg.comun.MsgFault msg){
       faultMessage = msg;
    }
    
    public biz.belcorp.www.ssg.comun.MsgFault getFaultMessage(){
       return faultMessage;
    }
}
    