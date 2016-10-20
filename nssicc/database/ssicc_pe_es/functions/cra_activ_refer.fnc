CREATE OR REPLACE FUNCTION cra_activ_refer(poid_acti IN VARCHAR2, pcod_pais in varchar2) RETURN varchar2 IS
  loid_acti    number;
  lactividades varchar2(100);

BEGIN
    
lactividades := poid_acti;    
    
    select ca.cact_oid_acti
      into loid_acti
      from cra_activ ca, seg_pais sp
     where sp.cod_pais = pcod_pais
       and ca.pais_oid_pais = sp.oid_pais
       and ca.oid_acti = poid_acti;

   if loid_acti is null then
      return  lactividades;
   else
      return  lactividades || '|' || cra_activ_refer(loid_acti,pcod_pais);
   end if;

END;
/
