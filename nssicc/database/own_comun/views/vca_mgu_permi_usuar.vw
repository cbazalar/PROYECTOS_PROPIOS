CREATE OR REPLACE VIEW VCA_MGU_PERMI_USUAR AS
SELECT PRINCIPALS.NAME COD_USUA
          ,PROPERTIES.NAME COD_PROP
          ,PROPERTYVALUES.STRINGVALUE VAL_PROP
FROM PROPERTYVALUES
    ,PRINCIPALS
    ,PROPERTIES
  WHERE principals.idprincipal = propertyvalues.idprincipal
    and propertyvalues.idproperty  = properties.idproperty
    and properties.name in ( 'Marca'
                    ,'Canal'
                    ,'Acceso'
                    ,'SubnivelAcceso'
                    ,'Pais'
                    ,'Region'
                    ,'Zona'
                    ,'Seccion'
                    ,'Territorio'
                    ,'SubgerenciaVentas'
                    ,'Sociedad' );
comment on column VCA_MGU_PERMI_USUAR.COD_USUA is 'C?digo de usuario';
comment on column VCA_MGU_PERMI_USUAR.COD_PROP is 'Nombre asignado a la propiedad, por el cual la identifica el usuario final.';
comment on column VCA_MGU_PERMI_USUAR.VAL_PROP is 'Volor String asignado (caracter o num?rico).';

