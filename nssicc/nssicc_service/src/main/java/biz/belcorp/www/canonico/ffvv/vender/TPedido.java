
/**
 * TPedido.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.vender;
            

            /**
            *  TPedido bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TPedido
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tPedido
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Vender
                Namespace Prefix = ns5
                */
            

                        /**
                        * field for Codigo
                        */

                        
                                    protected java.lang.String localCodigo ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodigo(){
                               return localCodigo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Codigo
                               */
                               public void setCodigo(java.lang.String param){
                            
                                            this.localCodigo=param;
                                    

                               }
                            

                        /**
                        * field for Bloqueado
                        */

                        
                                    protected java.lang.String localBloqueado ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBloqueado(){
                               return localBloqueado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Bloqueado
                               */
                               public void setBloqueado(java.lang.String param){
                            
                                            this.localBloqueado=param;
                                    

                               }
                            

                        /**
                        * field for Estado
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TEstadoPedido localEstado ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TEstadoPedido
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TEstadoPedido getEstado(){
                               return localEstado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Estado
                               */
                               public void setEstado(biz.belcorp.www.canonico.ffvv.vender.TEstadoPedido param){
                            
                                            this.localEstado=param;
                                    

                               }
                            

                        /**
                        * field for FechaFacturado
                        */

                        
                                    protected java.util.Calendar localFechaFacturado ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Calendar
                           */
                           public  java.util.Calendar getFechaFacturado(){
                               return localFechaFacturado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FechaFacturado
                               */
                               public void setFechaFacturado(java.util.Calendar param){
                            
                                            this.localFechaFacturado=param;
                                    

                               }
                            

                        /**
                        * field for FechaSolicitud
                        */

                        
                                    protected java.util.Calendar localFechaSolicitud ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Calendar
                           */
                           public  java.util.Calendar getFechaSolicitud(){
                               return localFechaSolicitud;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FechaSolicitud
                               */
                               public void setFechaSolicitud(java.util.Calendar param){
                            
                                            this.localFechaSolicitud=param;
                                    

                               }
                            

                        /**
                        * field for Flete
                        */

                        
                                    protected double localFlete ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getFlete(){
                               return localFlete;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Flete
                               */
                               public void setFlete(double param){
                            
                                            this.localFlete=param;
                                    

                               }
                            

                        /**
                        * field for MotivoRechazo
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TMotivoRechazo localMotivoRechazo ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TMotivoRechazo
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TMotivoRechazo getMotivoRechazo(){
                               return localMotivoRechazo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MotivoRechazo
                               */
                               public void setMotivoRechazo(biz.belcorp.www.canonico.ffvv.vender.TMotivoRechazo param){
                            
                                            this.localMotivoRechazo=param;
                                    

                               }
                            

                        /**
                        * field for Observacion
                        */

                        
                                    protected java.lang.String localObservacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getObservacion(){
                               return localObservacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Observacion
                               */
                               public void setObservacion(java.lang.String param){
                            
                                            this.localObservacion=param;
                                    

                               }
                            

                        /**
                        * field for Origen
                        */

                        
                                    protected java.lang.String localOrigen ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOrigen(){
                               return localOrigen;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Origen
                               */
                               public void setOrigen(java.lang.String param){
                            
                                            this.localOrigen=param;
                                    

                               }
                            

                        /**
                        * field for MontoDescuento
                        */

                        
                                    protected double localMontoDescuento ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoDescuento(){
                               return localMontoDescuento;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoDescuento
                               */
                               public void setMontoDescuento(double param){
                            
                                            this.localMontoDescuento=param;
                                    

                               }
                            

                        /**
                        * field for MontoEstimado
                        */

                        
                                    protected double localMontoEstimado ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoEstimado(){
                               return localMontoEstimado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoEstimado
                               */
                               public void setMontoEstimado(double param){
                            
                                            this.localMontoEstimado=param;
                                    

                               }
                            

                        /**
                        * field for MontoSolicitado
                        */

                        
                                    protected double localMontoSolicitado ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoSolicitado(){
                               return localMontoSolicitado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoSolicitado
                               */
                               public void setMontoSolicitado(double param){
                            
                                            this.localMontoSolicitado=param;
                                    

                               }
                            

                        /**
                        * field for MontoFacturado
                        */

                        
                                    protected double localMontoFacturado ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoFacturado(){
                               return localMontoFacturado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoFacturado
                               */
                               public void setMontoFacturado(double param){
                            
                                            this.localMontoFacturado=param;
                                    

                               }
                            

                        /**
                        * field for MontoFacturadoSinDescuento
                        */

                        
                                    protected double localMontoFacturadoSinDescuento ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoFacturadoSinDescuento(){
                               return localMontoFacturadoSinDescuento;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoFacturadoSinDescuento
                               */
                               public void setMontoFacturadoSinDescuento(double param){
                            
                                            this.localMontoFacturadoSinDescuento=param;
                                    

                               }
                            

                        /**
                        * field for Percepcion
                        */

                        
                                    protected double localPercepcion ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getPercepcion(){
                               return localPercepcion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Percepcion
                               */
                               public void setPercepcion(double param){
                            
                                            this.localPercepcion=param;
                                    

                               }
                            

                        /**
                        * field for CantidadCUVErrado
                        */

                        
                                    protected java.math.BigInteger localCantidadCUVErrado ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getCantidadCUVErrado(){
                               return localCantidadCUVErrado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CantidadCUVErrado
                               */
                               public void setCantidadCUVErrado(java.math.BigInteger param){
                            
                                            this.localCantidadCUVErrado=param;
                                    

                               }
                            

                        /**
                        * field for CantidadFaltanteAnunciado
                        */

                        
                                    protected java.math.BigInteger localCantidadFaltanteAnunciado ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getCantidadFaltanteAnunciado(){
                               return localCantidadFaltanteAnunciado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CantidadFaltanteAnunciado
                               */
                               public void setCantidadFaltanteAnunciado(java.math.BigInteger param){
                            
                                            this.localCantidadFaltanteAnunciado=param;
                                    

                               }
                            

                        /**
                        * field for MontoPedidoRechazado
                        */

                        
                                    protected double localMontoPedidoRechazado ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoPedidoRechazado(){
                               return localMontoPedidoRechazado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoPedidoRechazado
                               */
                               public void setMontoPedidoRechazado(double param){
                            
                                            this.localMontoPedidoRechazado=param;
                                    

                               }
                            

                        /**
                        * field for MontoCatalogoEstimado
                        */

                        
                                    protected double localMontoCatalogoEstimado ;
                                

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoCatalogoEstimado(){
                               return localMontoCatalogoEstimado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoCatalogoEstimado
                               */
                               public void setMontoCatalogoEstimado(double param){
                            
                                            this.localMontoCatalogoEstimado=param;
                                    

                               }
                            

                        /**
                        * field for PedidoDetalle
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[] localPedidoDetalle ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[] getPedidoDetalle(){
                               return localPedidoDetalle;
                           }

                           
                        


                               
                              /**
                               * validate the array for PedidoDetalle
                               */
                              protected void validatePedidoDetalle(biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param PedidoDetalle
                              */
                              public void setPedidoDetalle(biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[] param){
                              
                                   validatePedidoDetalle(param);

                               
                                      this.localPedidoDetalle=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle
                             */
                             public void addPedidoDetalle(biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle param){
                                   if (localPedidoDetalle == null){
                                   localPedidoDetalle = new biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localPedidoDetalle);
                               list.add(param);
                               this.localPedidoDetalle =
                             (biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Vender");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tPedido",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tPedido",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "codigo", xmlWriter);
                             

                                          if (localCodigo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCodigo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "bloqueado", xmlWriter);
                             

                                          if (localBloqueado==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBloqueado);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localEstado==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "estado", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localEstado.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estado"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "fechaFacturado", xmlWriter);
                             

                                          if (localFechaFacturado==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaFacturado));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "fechaSolicitud", xmlWriter);
                             

                                          if (localFechaSolicitud==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaSolicitud));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "flete", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localFlete)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlete));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localMotivoRechazo==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "motivoRechazo", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localMotivoRechazo.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","motivoRechazo"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "observacion", xmlWriter);
                             

                                          if (localObservacion==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localObservacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "origen", xmlWriter);
                             

                                          if (localOrigen==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOrigen);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoDescuento", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoDescuento)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoDescuento));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoEstimado", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoEstimado)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoEstimado));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoSolicitado", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoSolicitado)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoSolicitado));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoFacturado", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoFacturado)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoFacturado));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoFacturadoSinDescuento", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoFacturadoSinDescuento)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoFacturadoSinDescuento));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "percepcion", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localPercepcion)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPercepcion));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "cantidadCUVErrado", xmlWriter);
                             

                                          if (localCantidadCUVErrado==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadCUVErrado));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "cantidadFaltanteAnunciado", xmlWriter);
                             

                                          if (localCantidadFaltanteAnunciado==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadFaltanteAnunciado));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoPedidoRechazado", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoPedidoRechazado)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoPedidoRechazado));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "montoCatalogoEstimado", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoCatalogoEstimado)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoCatalogoEstimado));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                       if (localPedidoDetalle!=null){
                                            for (int i = 0;i < localPedidoDetalle.length;i++){
                                                if (localPedidoDetalle[i] != null){
                                                 localPedidoDetalle[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","pedidoDetalle"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("pedidoDetalle cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("pedidoDetalle cannot be null!!");
                                        
                                    }
                                 
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Vender")){
                return "ns5";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "codigo"));
                                 
                                         elementList.add(localCodigo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodigo));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "bloqueado"));
                                 
                                         elementList.add(localBloqueado==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBloqueado));
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "estado"));
                            
                            
                                    elementList.add(localEstado==null?null:
                                    localEstado);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "fechaFacturado"));
                                 
                                         elementList.add(localFechaFacturado==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaFacturado));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "fechaSolicitud"));
                                 
                                         elementList.add(localFechaSolicitud==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaSolicitud));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "flete"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlete));
                            
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "motivoRechazo"));
                            
                            
                                    elementList.add(localMotivoRechazo==null?null:
                                    localMotivoRechazo);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "observacion"));
                                 
                                         elementList.add(localObservacion==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localObservacion));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "origen"));
                                 
                                         elementList.add(localOrigen==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrigen));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoDescuento"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoDescuento));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoEstimado"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoEstimado));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoSolicitado"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoSolicitado));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoFacturado"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoFacturado));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoFacturadoSinDescuento"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoFacturadoSinDescuento));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "percepcion"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPercepcion));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "cantidadCUVErrado"));
                                 
                                         elementList.add(localCantidadCUVErrado==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadCUVErrado));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "cantidadFaltanteAnunciado"));
                                 
                                         elementList.add(localCantidadFaltanteAnunciado==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadFaltanteAnunciado));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoPedidoRechazado"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoPedidoRechazado));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "montoCatalogoEstimado"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoCatalogoEstimado));
                            
                             if (localPedidoDetalle!=null) {
                                 for (int i = 0;i < localPedidoDetalle.length;i++){

                                    if (localPedidoDetalle[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                          "pedidoDetalle"));
                                         elementList.add(localPedidoDetalle[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("pedidoDetalle cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("pedidoDetalle cannot be null!!");
                                    
                             }

                        

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static TPedido parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TPedido object =
                new TPedido();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"tPedido".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TPedido)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list20 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","codigo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCodigo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","bloqueado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBloqueado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estado").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setEstado(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setEstado(biz.belcorp.www.canonico.ffvv.vender.TEstadoPedido.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","fechaFacturado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFechaFacturado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","fechaSolicitud").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFechaSolicitud(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","flete").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFlete(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setFlete(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","motivoRechazo").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setMotivoRechazo(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setMotivoRechazo(biz.belcorp.www.canonico.ffvv.vender.TMotivoRechazo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","observacion").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setObservacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","origen").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOrigen(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoDescuento").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoDescuento(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoDescuento(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoEstimado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoEstimado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoEstimado(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoSolicitado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoSolicitado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoSolicitado(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoFacturado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoFacturado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoFacturado(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoFacturadoSinDescuento").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoFacturadoSinDescuento(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoFacturadoSinDescuento(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","percepcion").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPercepcion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setPercepcion(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","cantidadCUVErrado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCantidadCUVErrado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","cantidadFaltanteAnunciado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCantidadFaltanteAnunciado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoPedidoRechazado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoPedidoRechazado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoPedidoRechazado(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","montoCatalogoEstimado").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoCatalogoEstimado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoCatalogoEstimado(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","pedidoDetalle").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list20.add(biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone20 = false;
                                                        while(!loopDone20){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone20 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","pedidoDetalle").equals(reader.getName())){
                                                                    list20.add(biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone20 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setPedidoDetalle((biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.vender.TPedidoDetalle.class,
                                                                list20));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                              
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    