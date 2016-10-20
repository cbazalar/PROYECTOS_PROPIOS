CREATE OR REPLACE PACKAGE "PQ_APL_INT" as
    function func_obten_tipo_curso_clien(clie_oid_clie number) return number;
end pq_apl_int;
/

CREATE OR REPLACE PACKAGE BODY "PQ_APL_INT"
as

    function func_obten_tipo_curso_clien(clie_oid_clie number)
    return number
    is
     numrow  number(1):=0;
     tipcur  number(2);
     clie    number(12);
    begin

      clie:=to_number(clie_oid_clie);

      select count(*)
      into   numrow
      from   edu_aptas_curso apt
      where  apt.clie_oid_clie = clie
        and  apt.fec_asis     is not null
        and  rownum            = 1
      order by apt.fec_asis desc;

      if numrow > 0 then
         select tcu.cod_tipo_curs
         into tipcur
         from edu_aptas_curso apt,
             edu_matri_curso mat,
       	     edu_tipo_curso tcu
         where apt.mcur_oid_curs = mat.oid_curs
         and apt.clie_oid_clie = clie
         and mat.ticu_oid_tipo_curs = tcu.oid_tipo_curs
         and apt.fec_asis = (   select max(apt.fec_asis)
                                 from edu_aptas_curso apt
                                 where apt.clie_oid_clie=clie)
         and rownum=1;
      end if;

      if numrow = 0 then
         select tcu.cod_tipo_curs
           into tipcur
           from edu_histo_curso hic,
                edu_matri_curso mat,
                edu_tipo_curso tcu
          where hic.mcur_oid_curs = mat.oid_curs
           and hic.clie_oid_clie = clie
           and mat.ticu_oid_tipo_curs = tcu.oid_tipo_curs
           and hic.fec_asis = (  select max(hic.fec_asis)
                                 from edu_histo_curso hic
                                 where hic.clie_oid_clie=clie)
           and rownum=1;
      end if;

      return(tipcur);

    exception
        when no_data_found then
             return(null);
    end;
end pq_apl_int;
/

