
/**
 * TConcurso.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.motivar;
            

            /**
            *  TConcurso bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TConcurso
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tConcurso
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Motivar
                Namespace Prefix = ns6
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
                        * field for Nombre
                        */

                        
                                    protected java.lang.String localNombre ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNombre(){
                               return localNombre;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nombre
                               */
                               public void setNombre(java.lang.String param){
                            
                                            this.localNombre=param;
                                    

                               }
                            

                        /**
                        * field for CantidadPedidoMinimo
                        */

                        
                                    protected java.math.BigInteger localCantidadPedidoMinimo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCantidadPedidoMinimoTracker = false ;

                           public boolean isCantidadPedidoMinimoSpecified(){
                               return localCantidadPedidoMinimoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getCantidadPedidoMinimo(){
                               return localCantidadPedidoMinimo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CantidadPedidoMinimo
                               */
                               public void setCantidadPedidoMinimo(java.math.BigInteger param){
                            localCantidadPedidoMinimoTracker = true;
                                   
                                            this.localCantidadPedidoMinimo=param;
                                    

                               }
                            

                        /**
                        * field for ClasificacionConsultoraParticipantes
                        */

                        
                                    protected java.lang.String localClasificacionConsultoraParticipantes ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getClasificacionConsultoraParticipantes(){
                               return localClasificacionConsultoraParticipantes;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ClasificacionConsultoraParticipantes
                               */
                               public void setClasificacionConsultoraParticipantes(java.lang.String param){
                            
                                            this.localClasificacionConsultoraParticipantes=param;
                                    

                               }
                            

                        /**
                        * field for EstadoConsultoraParticipantes
                        */

                        
                                    protected java.lang.String localEstadoConsultoraParticipantes ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEstadoConsultoraParticipantes(){
                               return localEstadoConsultoraParticipantes;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EstadoConsultoraParticipantes
                               */
                               public void setEstadoConsultoraParticipantes(java.lang.String param){
                            
                                            this.localEstadoConsultoraParticipantes=param;
                                    

                               }
                            

                        /**
                        * field for MontoPedidoMinimo
                        */

                        
                                    protected double localMontoPedidoMinimo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMontoPedidoMinimoTracker = false ;

                           public boolean isMontoPedidoMinimoSpecified(){
                               return localMontoPedidoMinimoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return double
                           */
                           public  double getMontoPedidoMinimo(){
                               return localMontoPedidoMinimo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MontoPedidoMinimo
                               */
                               public void setMontoPedidoMinimo(double param){
                            localMontoPedidoMinimoTracker = true;
                                   
                                            this.localMontoPedidoMinimo=param;
                                    

                               }
                            

                        /**
                        * field for Rotativo
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TSiNo localRotativo ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TSiNo
                           */
                           public  biz.belcorp.www.ssg.query.TSiNo getRotativo(){
                               return localRotativo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Rotativo
                               */
                               public void setRotativo(biz.belcorp.www.ssg.query.TSiNo param){
                            
                                            this.localRotativo=param;
                                    

                               }
                            

                        /**
                        * field for NumeroRotativos
                        */

                        
                                    protected java.math.BigInteger localNumeroRotativos ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getNumeroRotativos(){
                               return localNumeroRotativos;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NumeroRotativos
                               */
                               public void setNumeroRotativos(java.math.BigInteger param){
                            
                                            this.localNumeroRotativos=param;
                                    

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
                        * field for TipoVenta
                        */

                        
                                    protected java.lang.String localTipoVenta ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTipoVenta(){
                               return localTipoVenta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoVenta
                               */
                               public void setTipoVenta(java.lang.String param){
                            
                                            this.localTipoVenta=param;
                                    

                               }
                            

                        /**
                        * field for UnidadesPedidoMinimo
                        */

                        
                                    protected java.math.BigInteger localUnidadesPedidoMinimo ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getUnidadesPedidoMinimo(){
                               return localUnidadesPedidoMinimo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UnidadesPedidoMinimo
                               */
                               public void setUnidadesPedidoMinimo(java.math.BigInteger param){
                            
                                            this.localUnidadesPedidoMinimo=param;
                                    

                               }
                            

                        /**
                        * field for CampaniaInicio
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampaniaInicio ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampaniaInicio(){
                               return localCampaniaInicio;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CampaniaInicio
                               */
                               public void setCampaniaInicio(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampaniaInicio=param;
                                    

                               }
                            

                        /**
                        * field for CampaniaFin
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampaniaFin ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampaniaFin(){
                               return localCampaniaFin;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CampaniaFin
                               */
                               public void setCampaniaFin(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampaniaFin=param;
                                    

                               }
                            

                        /**
                        * field for Tipo
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.motivar.TTipoConcurso localTipo ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.motivar.TTipoConcurso
                           */
                           public  biz.belcorp.www.canonico.ffvv.motivar.TTipoConcurso getTipo(){
                               return localTipo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tipo
                               */
                               public void setTipo(biz.belcorp.www.canonico.ffvv.motivar.TTipoConcurso param){
                            
                                            this.localTipo=param;
                                    

                               }
                            

                        /**
                        * field for Estado
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.motivar.TActivoConcurso localEstado ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.motivar.TActivoConcurso
                           */
                           public  biz.belcorp.www.canonico.ffvv.motivar.TActivoConcurso getEstado(){
                               return localEstado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Estado
                               */
                               public void setEstado(biz.belcorp.www.canonico.ffvv.motivar.TActivoConcurso param){
                            
                                            this.localEstado=param;
                                    

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Motivar");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tConcurso",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tConcurso",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "codigo", xmlWriter);
                             

                                          if (localCodigo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCodigo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "nombre", xmlWriter);
                             

                                          if (localNombre==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNombre);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localCantidadPedidoMinimoTracker){
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "cantidadPedidoMinimo", xmlWriter);
                             

                                          if (localCantidadPedidoMinimo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadPedidoMinimo));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "clasificacionConsultoraParticipantes", xmlWriter);
                             

                                          if (localClasificacionConsultoraParticipantes==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localClasificacionConsultoraParticipantes);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "estadoConsultoraParticipantes", xmlWriter);
                             

                                          if (localEstadoConsultoraParticipantes==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEstadoConsultoraParticipantes);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localMontoPedidoMinimoTracker){
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "montoPedidoMinimo", xmlWriter);
                             
                                               if (java.lang.Double.isNaN(localMontoPedidoMinimo)) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoPedidoMinimo));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    if (localRotativo==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Motivar", "rotativo", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localRotativo.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","rotativo"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "numeroRotativos", xmlWriter);
                             

                                          if (localNumeroRotativos==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumeroRotativos));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "observacion", xmlWriter);
                             

                                          if (localObservacion==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localObservacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "tipoVenta", xmlWriter);
                             

                                          if (localTipoVenta==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTipoVenta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Motivar";
                                    writeStartElement(null, namespace, "unidadesPedidoMinimo", xmlWriter);
                             

                                          if (localUnidadesPedidoMinimo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUnidadesPedidoMinimo));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localCampaniaInicio==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaInicio cannot be null!!");
                                            }
                                           localCampaniaInicio.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","campaniaInicio"),
                                               xmlWriter);
                                        
                                            if (localCampaniaFin==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaFin cannot be null!!");
                                            }
                                           localCampaniaFin.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","campaniaFin"),
                                               xmlWriter);
                                        
                                            if (localTipo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipo cannot be null!!");
                                            }
                                           localTipo.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","tipo"),
                                               xmlWriter);
                                        
                                            if (localEstado==null){
                                                 throw new org.apache.axis2.databinding.ADBException("estado cannot be null!!");
                                            }
                                           localEstado.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","estado"),
                                               xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Motivar")){
                return "ns6";
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

                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "codigo"));
                                 
                                         elementList.add(localCodigo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodigo));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "nombre"));
                                 
                                         elementList.add(localNombre==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNombre));
                                     if (localCantidadPedidoMinimoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "cantidadPedidoMinimo"));
                                 
                                         elementList.add(localCantidadPedidoMinimo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCantidadPedidoMinimo));
                                    }
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "clasificacionConsultoraParticipantes"));
                                 
                                         elementList.add(localClasificacionConsultoraParticipantes==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localClasificacionConsultoraParticipantes));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "estadoConsultoraParticipantes"));
                                 
                                         elementList.add(localEstadoConsultoraParticipantes==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEstadoConsultoraParticipantes));
                                     if (localMontoPedidoMinimoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "montoPedidoMinimo"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMontoPedidoMinimo));
                            }
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "rotativo"));
                            
                            
                                    elementList.add(localRotativo==null?null:
                                    localRotativo);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "numeroRotativos"));
                                 
                                         elementList.add(localNumeroRotativos==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumeroRotativos));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "observacion"));
                                 
                                         elementList.add(localObservacion==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localObservacion));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "tipoVenta"));
                                 
                                         elementList.add(localTipoVenta==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTipoVenta));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "unidadesPedidoMinimo"));
                                 
                                         elementList.add(localUnidadesPedidoMinimo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUnidadesPedidoMinimo));
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "campaniaInicio"));
                            
                            
                                    if (localCampaniaInicio==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaInicio cannot be null!!");
                                    }
                                    elementList.add(localCampaniaInicio);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "campaniaFin"));
                            
                            
                                    if (localCampaniaFin==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaFin cannot be null!!");
                                    }
                                    elementList.add(localCampaniaFin);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "tipo"));
                            
                            
                                    if (localTipo==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipo cannot be null!!");
                                    }
                                    elementList.add(localTipo);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar",
                                                                      "estado"));
                            
                            
                                    if (localEstado==null){
                                         throw new org.apache.axis2.databinding.ADBException("estado cannot be null!!");
                                    }
                                    elementList.add(localEstado);
                                

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
        public static TConcurso parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TConcurso object =
                new TConcurso();

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
                    
                            if (!"tConcurso".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TConcurso)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","codigo").equals(reader.getName())){
                                
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","nombre").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNombre(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","cantidadPedidoMinimo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCantidadPedidoMinimo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","clasificacionConsultoraParticipantes").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setClasificacionConsultoraParticipantes(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","estadoConsultoraParticipantes").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEstadoConsultoraParticipantes(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","montoPedidoMinimo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMontoPedidoMinimo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setMontoPedidoMinimo(java.lang.Double.NaN);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setMontoPedidoMinimo(java.lang.Double.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","rotativo").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setRotativo(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setRotativo(biz.belcorp.www.ssg.query.TSiNo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","numeroRotativos").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNumeroRotativos(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","observacion").equals(reader.getName())){
                                
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","tipoVenta").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTipoVenta(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","unidadesPedidoMinimo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUnidadesPedidoMinimo(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","campaniaInicio").equals(reader.getName())){
                                
                                                object.setCampaniaInicio(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","campaniaFin").equals(reader.getName())){
                                
                                                object.setCampaniaFin(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","tipo").equals(reader.getName())){
                                
                                                object.setTipo(biz.belcorp.www.canonico.ffvv.motivar.TTipoConcurso.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Motivar","estado").equals(reader.getName())){
                                
                                                object.setEstado(biz.belcorp.www.canonico.ffvv.motivar.TActivoConcurso.Factory.parse(reader));
                                              
                                        reader.next();
                                    
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
           
    