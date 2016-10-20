CREATE OR REPLACE FUNCTION cra_dias(poid_acti IN VARCHAR2, pcod_grupo varchar2, pcod_pais varchar2) RETURN number IS
  loid_acti number;
  lnum_dias number;

BEGIN
  select mat2.cact_oid_acti_orig, mat2.num_dias_refe
    into loid_acti, lnum_dias
    from cra_matri_dias       mat2,
         cra_cabec_grupo_zona cab2,
         cra_activ            ca2,
         seg_pais             sp2
   where mat2.cgzo_oid_cabe_grup_zona = cab2.oid_cabe_grup_zona
     and sp2.oid_pais = ca2.pais_oid_pais
     and ca2.oid_acti = mat2.cact_oid_acti
     and sp2.cod_pais = pcod_pais
     and cab2.cod_grup = pcod_grupo
     and ca2.oid_acti = poid_acti;

   if loid_acti is null then
      return  lnum_dias;
   else
      return  lnum_dias + cra_dias(loid_acti, pcod_grupo,pcod_pais);
   end if;

END;
/
