
/**
 * TPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.persona;
            

            /**
            *  TPersona bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TPersona
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tPersona
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Persona
                Namespace Prefix = ns3
                */
            

                        /**
                        * field for PrimerNombre
                        */

                        
                                    protected java.lang.String localPrimerNombre ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPrimerNombre(){
                               return localPrimerNombre;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrimerNombre
                               */
                               public void setPrimerNombre(java.lang.String param){
                            
                                            this.localPrimerNombre=param;
                                    

                               }
                            

                        /**
                        * field for SegundoNombre
                        */

                        
                                    protected java.lang.String localSegundoNombre ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSegundoNombre(){
                               return localSegundoNombre;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SegundoNombre
                               */
                               public void setSegundoNombre(java.lang.String param){
                            
                                            this.localSegundoNombre=param;
                                    

                               }
                            

                        /**
                        * field for PrimerApellido
                        */

                        
                                    protected java.lang.String localPrimerApellido ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPrimerApellido(){
                               return localPrimerApellido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrimerApellido
                               */
                               public void setPrimerApellido(java.lang.String param){
                            
                                            this.localPrimerApellido=param;
                                    

                               }
                            

                        /**
                        * field for SegundoApellido
                        */

                        
                                    protected java.lang.String localSegundoApellido ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSegundoApellido(){
                               return localSegundoApellido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SegundoApellido
                               */
                               public void setSegundoApellido(java.lang.String param){
                            
                                            this.localSegundoApellido=param;
                                    

                               }
                            

                        /**
                        * field for NombreCompleto
                        */

                        
                                    protected java.lang.String localNombreCompleto ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNombreCompleto(){
                               return localNombreCompleto;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NombreCompleto
                               */
                               public void setNombreCompleto(java.lang.String param){
                            
                                            this.localNombreCompleto=param;
                                    

                               }
                            

                        /**
                        * field for FechaNacimiento
                        */

                        
                                    protected java.util.Date localFechaNacimiento ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Date
                           */
                           public  java.util.Date getFechaNacimiento(){
                               return localFechaNacimiento;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FechaNacimiento
                               */
                               public void setFechaNacimiento(java.util.Date param){
                            
                                            this.localFechaNacimiento=param;
                                    

                               }
                            

                        /**
                        * field for Genero
                        */

                        
                                    protected java.lang.String localGenero ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getGenero(){
                               return localGenero;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Genero
                               */
                               public void setGenero(java.lang.String param){
                            
                                            this.localGenero=param;
                                    

                               }
                            

                        /**
                        * field for EstadoCivil
                        */

                        
                                    protected java.lang.String localEstadoCivil ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEstadoCivil(){
                               return localEstadoCivil;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EstadoCivil
                               */
                               public void setEstadoCivil(java.lang.String param){
                            
                                            this.localEstadoCivil=param;
                                    

                               }
                            

                        /**
                        * field for Identificacion
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[] localIdentificacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[] getIdentificacion(){
                               return localIdentificacion;
                           }

                           
                        


                               
                              /**
                               * validate the array for Identificacion
                               */
                              protected void validateIdentificacion(biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Identificacion
                              */
                              public void setIdentificacion(biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[] param){
                              
                                   validateIdentificacion(param);

                               
                                      this.localIdentificacion=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.persona.TIdentificacion
                             */
                             public void addIdentificacion(biz.belcorp.www.canonico.ffvv.persona.TIdentificacion param){
                                   if (localIdentificacion == null){
                                   localIdentificacion = new biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localIdentificacion);
                               list.add(param);
                               this.localIdentificacion =
                             (biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[list.size()]);

                             }
                             

                        /**
                        * field for Contacto
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.persona.TContacto localContacto ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContactoTracker = false ;

                           public boolean isContactoSpecified(){
                               return localContactoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.persona.TContacto
                           */
                           public  biz.belcorp.www.canonico.ffvv.persona.TContacto getContacto(){
                               return localContacto;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Contacto
                               */
                               public void setContacto(biz.belcorp.www.canonico.ffvv.persona.TContacto param){
                            localContactoTracker = param != null;
                                   
                                            this.localContacto=param;
                                    

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Persona");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tPersona",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tPersona",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "primerNombre", xmlWriter);
                             

                                          if (localPrimerNombre==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPrimerNombre);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "segundoNombre", xmlWriter);
                             

                                          if (localSegundoNombre==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSegundoNombre);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "primerApellido", xmlWriter);
                             

                                          if (localPrimerApellido==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPrimerApellido);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "segundoApellido", xmlWriter);
                             

                                          if (localSegundoApellido==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSegundoApellido);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "nombreCompleto", xmlWriter);
                             

                                          if (localNombreCompleto==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNombreCompleto);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "fechaNacimiento", xmlWriter);
                             

                                          if (localFechaNacimiento==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaNacimiento));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "genero", xmlWriter);
                             

                                          if (localGenero==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localGenero);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Persona";
                                    writeStartElement(null, namespace, "estadoCivil", xmlWriter);
                             

                                          if (localEstadoCivil==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEstadoCivil);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                       if (localIdentificacion!=null){
                                            for (int i = 0;i < localIdentificacion.length;i++){
                                                if (localIdentificacion[i] != null){
                                                 localIdentificacion[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","identificacion"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("identificacion cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("identificacion cannot be null!!");
                                        
                                    }
                                  if (localContactoTracker){
                                            if (localContacto==null){
                                                 throw new org.apache.axis2.databinding.ADBException("contacto cannot be null!!");
                                            }
                                           localContacto.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","contacto"),
                                               xmlWriter);
                                        }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Persona")){
                return "ns3";
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

                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "primerNombre"));
                                 
                                         elementList.add(localPrimerNombre==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrimerNombre));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "segundoNombre"));
                                 
                                         elementList.add(localSegundoNombre==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSegundoNombre));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "primerApellido"));
                                 
                                         elementList.add(localPrimerApellido==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrimerApellido));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "segundoApellido"));
                                 
                                         elementList.add(localSegundoApellido==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSegundoApellido));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "nombreCompleto"));
                                 
                                         elementList.add(localNombreCompleto==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNombreCompleto));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "fechaNacimiento"));
                                 
                                         elementList.add(localFechaNacimiento==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaNacimiento));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "genero"));
                                 
                                         elementList.add(localGenero==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGenero));
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "estadoCivil"));
                                 
                                         elementList.add(localEstadoCivil==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEstadoCivil));
                                    
                             if (localIdentificacion!=null) {
                                 for (int i = 0;i < localIdentificacion.length;i++){

                                    if (localIdentificacion[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                          "identificacion"));
                                         elementList.add(localIdentificacion[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("identificacion cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("identificacion cannot be null!!");
                                    
                             }

                         if (localContactoTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona",
                                                                      "contacto"));
                            
                            
                                    if (localContacto==null){
                                         throw new org.apache.axis2.databinding.ADBException("contacto cannot be null!!");
                                    }
                                    elementList.add(localContacto);
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
        public static TPersona parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TPersona object =
                new TPersona();

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
                    
                            if (!"tPersona".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TPersona)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list9 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","primerNombre").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPrimerNombre(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","segundoNombre").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSegundoNombre(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","primerApellido").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPrimerApellido(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","segundoApellido").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSegundoApellido(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","nombreCompleto").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNombreCompleto(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","fechaNacimiento").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFechaNacimiento(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","genero").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setGenero(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","estadoCivil").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEstadoCivil(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","identificacion").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list9.add(biz.belcorp.www.canonico.ffvv.persona.TIdentificacion.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone9 = false;
                                                        while(!loopDone9){
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
                                                                loopDone9 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","identificacion").equals(reader.getName())){
                                                                    list9.add(biz.belcorp.www.canonico.ffvv.persona.TIdentificacion.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone9 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setIdentificacion((biz.belcorp.www.canonico.ffvv.persona.TIdentificacion[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.persona.TIdentificacion.class,
                                                                list9));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Persona","contacto").equals(reader.getName())){
                                
                                                object.setContacto(biz.belcorp.www.canonico.ffvv.persona.TContacto.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
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
           
    