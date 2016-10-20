CREATE OR REPLACE PROCEDURE "REFRESCA_REGIO_ZON_USU"
( OWN_PAIS_MARCA VARCHAR2 )
is
   sentencia varchar2(32000);
BEGIN

sentencia:='
insert into own_mare.propertyvalues (
select u.idprincipal, 38 as idproperty, 1 as type, to_char(z.oid_regi) as stringvalue, null
from '||OWN_PAIS_MARCA||'.'||'zon_regio z, (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'zon_zona where rownum =1)
) u
minus
select idprincipal, idproperty, type, stringvalue,null from own_mare.propertyvalues
where idproperty=38 and idprincipal in (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'zon_zona where rownum =1)
)
)';
EXECUTE IMMEDIATE sentencia;

sentencia:='
insert into own_mare.propertyvalues (
select u.idprincipal, 39 as idproperty, 1 as type, to_char(z.oid_zona) as stringvalue, null
from '||OWN_PAIS_MARCA||'.'||'zon_zona z, (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'zon_zona where rownum =1)
) u
minus
select idprincipal, idproperty, type, stringvalue,null from own_mare.propertyvalues
where idproperty=39 and idprincipal in (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'zon_zona where rownum =1)
)
)'
;
EXECUTE IMMEDIATE sentencia;

sentencia:='
insert into own_mare.propertyvalues (
select u.idprincipal, 38 as idproperty, 1 as type, to_char(z.oid_regi) as stringvalue, null
from '||OWN_PAIS_MARCA||'.'||'zon_regio z, (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'ZON_ZONA where rownum =1)
) u
minus
select idprincipal, idproperty, type, stringvalue,null 
from own_mare.propertyvalues
where idproperty=38 and idprincipal in (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'ZON_ZONA where rownum =1)
)
)';
EXECUTE IMMEDIATE sentencia;

sentencia:='
insert into own_mare.propertyvalues (
select u.idprincipal, 39 as idproperty, 1 as type, to_char(z.oid_zona) as stringvalue, null
from '||OWN_PAIS_MARCA||'.'||'zon_zona z, (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'ZON_ZONA where rownum =1)
) u
minus
select idprincipal, idproperty, type, stringvalue,null 
from own_mare.propertyvalues
where idproperty=39 and idprincipal in (
select idprincipal from own_mare.propertyvalues where idproperty=31 
and stringvalue = (select distinct pais_oid_pais from '||OWN_PAIS_MARCA||'.'||'ZON_ZONA where rownum =1)
)
)'
;
EXECUTE IMMEDIATE sentencia;
commit;
EXCEPTION
  when others then
     sentencia:='';
     rollback;
END;
/

