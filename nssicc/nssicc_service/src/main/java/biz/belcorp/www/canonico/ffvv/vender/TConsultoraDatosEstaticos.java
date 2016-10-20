
/**
 * TConsultoraDatosEstaticos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.vender;
            

            /**
            *  TConsultoraDatosEstaticos bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TConsultoraDatosEstaticos
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tConsultoraDatosEstaticos
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
                        * field for Persona
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TPersona localPersona ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TPersona
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TPersona getPersona(){
                               return localPersona;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Persona
                               */
                               public void setPersona(biz.belcorp.www.canonico.ffvv.persona.TPersona param){
                            
                                            this.localPersona=param;
                                    

                               }
                            

                        /**
                        * field for Territorio
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio localTerritorio ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio getTerritorio(){
                               return localTerritorio;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Territorio
                               */
                               public void setTerritorio(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio param){
                            
                                            this.localTerritorio=param;
                                    

                               }
                            

                        /**
                        * field for PrimerPedido
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TSiNo localPrimerPedido ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TSiNo
                           */
                           public  biz.belcorp.www.ssg.query.TSiNo getPrimerPedido(){
                               return localPrimerPedido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrimerPedido
                               */
                               public void setPrimerPedido(biz.belcorp.www.ssg.query.TSiNo param){
                            
                                            this.localPrimerPedido=param;
                                    

                               }
                            

                        /**
                        * field for KitNueva
                        */

                        
                                    protected java.lang.String localKitNueva ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKitNueva(){
                               return localKitNueva;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KitNueva
                               */
                               public void setKitNueva(java.lang.String param){
                            
                                            this.localKitNueva=param;
                                    

                               }
                            

                        /**
                        * field for AutorizadaPedido
                        */

                        
                                    protected biz.belcorp.www.ssg.query.TSiNo localAutorizadaPedido ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.ssg.query.TSiNo
                           */
                           public  biz.belcorp.www.ssg.query.TSiNo getAutorizadaPedido(){
                               return localAutorizadaPedido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AutorizadaPedido
                               */
                               public void setAutorizadaPedido(biz.belcorp.www.ssg.query.TSiNo param){
                            
                                            this.localAutorizadaPedido=param;
                                    

                               }
                            

                        /**
                        * field for CampaniaIngreso
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampaniaIngreso ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampaniaIngreso(){
                               return localCampaniaIngreso;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CampaniaIngreso
                               */
                               public void setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampaniaIngreso=param;
                                    

                               }
                            

                        /**
                        * field for FechaIngreso
                        */

                        
                                    protected java.util.Date localFechaIngreso ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Date
                           */
                           public  java.util.Date getFechaIngreso(){
                               return localFechaIngreso;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FechaIngreso
                               */
                               public void setFechaIngreso(java.util.Date param){
                            
                                            this.localFechaIngreso=param;
                                    

                               }
                            

                        /**
                        * field for LET
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TLET localLET ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLETTracker = false ;

                           public boolean isLETSpecified(){
                               return localLETTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TLET
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TLET getLET(){
                               return localLET;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LET
                               */
                               public void setLET(biz.belcorp.www.canonico.ffvv.vender.TLET param){
                            localLETTracker = true;
                                   
                                            this.localLET=param;
                                    

                               }
                            

                        /**
                        * field for DuplaCyzone
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TDuplaCyzone localDuplaCyzone ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDuplaCyzoneTracker = false ;

                           public boolean isDuplaCyzoneSpecified(){
                               return localDuplaCyzoneTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TDuplaCyzone
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TDuplaCyzone getDuplaCyzone(){
                               return localDuplaCyzone;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DuplaCyzone
                               */
                               public void setDuplaCyzone(biz.belcorp.www.canonico.ffvv.vender.TDuplaCyzone param){
                            localDuplaCyzoneTracker = true;
                                   
                                            this.localDuplaCyzone=param;
                                    

                               }
                            

                        /**
                        * field for DigitoVerificacion
                        */

                        
                                    protected java.lang.String localDigitoVerificacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDigitoVerificacion(){
                               return localDigitoVerificacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DigitoVerificacion
                               */
                               public void setDigitoVerificacion(java.lang.String param){
                            
                                            this.localDigitoVerificacion=param;
                                    

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
                           namespacePrefix+":tConsultoraDatosEstaticos",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tConsultoraDatosEstaticos",
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
                             
                                    if (localPersona==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "persona", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPersona.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","persona"),
                                        xmlWriter);
                                    }
                                
                                    if (localTerritorio==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "territorio", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localTerritorio.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","territorio"),
                                        xmlWriter);
                                    }
                                
                                    if (localPrimerPedido==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "primerPedido", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPrimerPedido.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","primerPedido"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "kitNueva", xmlWriter);
                             

                                          if (localKitNueva==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKitNueva);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localAutorizadaPedido==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "autorizadaPedido", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localAutorizadaPedido.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","autorizadaPedido"),
                                        xmlWriter);
                                    }
                                
                                            if (localCampaniaIngreso==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                            }
                                           localCampaniaIngreso.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaIngreso"),
                                               xmlWriter);
                                        
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "fechaIngreso", xmlWriter);
                             

                                          if (localFechaIngreso==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaIngreso));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localLETTracker){
                                    if (localLET==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "LET", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localLET.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","LET"),
                                        xmlWriter);
                                    }
                                } if (localDuplaCyzoneTracker){
                                    if (localDuplaCyzone==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "duplaCyzone", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localDuplaCyzone.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","duplaCyzone"),
                                        xmlWriter);
                                    }
                                }
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "digitoVerificacion", xmlWriter);
                             

                                          if (localDigitoVerificacion==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDigitoVerificacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
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
                                                                      "persona"));
                            
                            
                                    elementList.add(localPersona==null?null:
                                    localPersona);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "territorio"));
                            
                            
                                    elementList.add(localTerritorio==null?null:
                                    localTerritorio);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "primerPedido"));
                            
                            
                                    elementList.add(localPrimerPedido==null?null:
                                    localPrimerPedido);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "kitNueva"));
                                 
                                         elementList.add(localKitNueva==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKitNueva));
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "autorizadaPedido"));
                            
                            
                                    elementList.add(localAutorizadaPedido==null?null:
                                    localAutorizadaPedido);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "campaniaIngreso"));
                            
                            
                                    if (localCampaniaIngreso==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                    }
                                    elementList.add(localCampaniaIngreso);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "fechaIngreso"));
                                 
                                         elementList.add(localFechaIngreso==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaIngreso));
                                     if (localLETTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "LET"));
                            
                            
                                    elementList.add(localLET==null?null:
                                    localLET);
                                } if (localDuplaCyzoneTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "duplaCyzone"));
                            
                            
                                    elementList.add(localDuplaCyzone==null?null:
                                    localDuplaCyzone);
                                }
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "digitoVerificacion"));
                                 
                                         elementList.add(localDigitoVerificacion==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDigitoVerificacion));
                                    

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
        public static TConsultoraDatosEstaticos parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TConsultoraDatosEstaticos object =
                new TConsultoraDatosEstaticos();

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
                    
                            if (!"tConsultoraDatosEstaticos".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TConsultoraDatosEstaticos)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","persona").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPersona(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPersona(biz.belcorp.www.canonico.ffvv.persona.TPersona.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","territorio").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setTerritorio(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setTerritorio(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","primerPedido").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPrimerPedido(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPrimerPedido(biz.belcorp.www.ssg.query.TSiNo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","kitNueva").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKitNueva(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","autorizadaPedido").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setAutorizadaPedido(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setAutorizadaPedido(biz.belcorp.www.ssg.query.TSiNo.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaIngreso").equals(reader.getName())){
                                
                                                object.setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","fechaIngreso").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFechaIngreso(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                                            
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","LET").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setLET(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setLET(biz.belcorp.www.canonico.ffvv.vender.TLET.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","duplaCyzone").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setDuplaCyzone(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setDuplaCyzone(biz.belcorp.www.canonico.ffvv.vender.TDuplaCyzone.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","digitoVerificacion").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDigitoVerificacion(
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
           
    