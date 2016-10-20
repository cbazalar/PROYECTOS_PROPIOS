create or replace package CRA_PKG_REPOR is

 /******************************************************************************
 Descripcion         : CRA_PR_REPOR_ZONA_FACTU
                       Lista las zonas a facturar por fecha.
 ------------------------------------------------------------------------------
 Fecha Creacion      : 17/10/2013
 ------------------------------------------------------------------------------
  Parametros Entrada:
     p_fecha : fecha Facturación
 ------------------------------------------------------------------------------
 Autor               : Yahir Rivas Luna
********************************************************************************/
PROCEDURE CRA_PR_REPOR_ZONA_FACTU(p_fecha varchar2,p_codigoPais varchar2);

/******************************************************************************
 Descripcion         : CRA_FN_EVALU_ACTIV_FECHA
                       Devuelve si la actividad puede figurar en el cronograma 
                       para la fecha
 ------------------------------------------------------------------------------
 Fecha Creacion      : 07/11/2013
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/

FUNCTION CRA_FN_EVALU_ACTIV_FECHA(p_activ varchar2, p_fecha varchar2,p_codigoPais varchar2)
 RETURN NUMBER;

end CRA_PKG_REPOR;
/
create or replace package body CRA_PKG_REPOR is

    /* Declaracion de Variables */
  ln_sqlcode        NUMBER(10);
  ls_sqlerrm        VARCHAR2(1000);

/******************************************************************************
 Descripcion         : CRA_PR_REPOR_ZONA_FACTU
                       Lista las zonas a facturar por fecha.
 ------------------------------------------------------------------------------
 Fecha Creacion      : 17/10/2013
 ------------------------------------------------------------------------------
 Autor               : Yahir Rivas Luna
********************************************************************************/

PROCEDURE CRA_PR_REPOR_ZONA_FACTU(p_fecha varchar2,p_codigoPais varchar2)
IS
 CURSOR c_zona_factu(p_fecha varchar2, lv_cod1 varchar2, lv_cod2 varchar2, lv_cod_fa varchar2, lv_cod_rf varchar2 ) IS
    SELECT cp.val_nomb_peri AS Campana,
         ccg.nom_grup AS Grupo,
         zz.cod_zona AS Zona,
         DECODE(lv_cod1,lv_cod_fa,ca1.nom_acti,'') AS Facturacion,
         DECODE(lv_cod1,lv_cod_fa,cr1.fec_inic,'') AS Fecha_Facturacion,
         DECODE(lv_cod2,lv_cod_rf,ca2.nom_acti,'') AS Refacturacion,
         DECODE(lv_cod2,lv_cod_rf,cr2.fec_inic,'') AS Fecha_Refacturacion
    FROM cra_crono cr1,
         cra_crono cr2,
         cra_activ ca1,
         cra_activ ca2,
         cra_cabec_grupo_zona ccg,
         seg_pais sp,
         cra_detal_grupo_zona cdg,
         zon_zona zz,
         cra_perio cp
   WHERE     cr1.perd_oid_peri = cr2.perd_oid_peri
         AND cr1.zzon_oid_zona = cr2.zzon_oid_zona
         AND ccg.oid_cabe_grup_zona = cdg.cgzo_oid_cabe_grup_zona
         AND cdg.zzon_oid_zona = zz.oid_zona
         AND cr1.zzon_oid_zona = cdg.zzon_oid_zona
         AND ccg.pais_oid_pais = sp.oid_pais
         AND cr1.perd_oid_peri = cp.oid_peri
         AND cr1.cact_oid_acti = ca1.oid_acti
         AND cr2.cact_oid_acti = ca2.oid_acti
         AND ca1.ind_acti = '1'  -- constante
         AND ca2.ind_acti = '1'  -- constante
         AND ca1.pais_oid_pais = ca2.pais_oid_pais
         AND ca2.pais_oid_pais = sp.oid_pais
         AND sp.cod_pais = p_codigoPais   -- cambiar por codigo de pais de la pantalla
         AND ca1.cod_acti = lv_cod1   -- constante
         AND ca2.cod_acti = lv_cod2    -- constante
         AND TO_DATE (p_fecha, 'dd/mm/yyyy')  -- fecha de la pantalla
         BETWEEN cr1.fec_inic AND cr2.fec_inic;


       -- Declarando variables

       TYPE campana IS TABLE OF CRA_REPOR_ZONA_FACTU.VAL_NOMB_PERI%TYPE;
       TYPE grupo IS TABLE OF CRA_REPOR_ZONA_FACTU.NOM_GRUP%TYPE;
       TYPE zona IS TABLE OF CRA_REPOR_ZONA_FACTU.COD_ZONA%TYPE;
       TYPE facturacion IS TABLE OF CRA_REPOR_ZONA_FACTU.NOM_ACT1%TYPE;
       TYPE fecha_fact IS TABLE OF CRA_REPOR_ZONA_FACTU.FEC_INI1%TYPE;
       TYPE refacturacion IS TABLE OF CRA_REPOR_ZONA_FACTU.NOM_ACT2%TYPE;
       TYPE fecha_refact IS TABLE OF CRA_REPOR_ZONA_FACTU.FEC_INI2%TYPE;


       v_campana campana;
       v_grupo grupo;
       v_zona zona;
       v_facturacion1 facturacion;
       v_fecha_fact1 fecha_fact;
       v_facturacion2 refacturacion;
       v_fecha_fact2 fecha_refact;
       
       lv_cod_fa    VARCHAR2(2) := 'FA';
       lv_cod_rf    VARCHAR2(2) := 'RF';
       lv_cod1      VARCHAR2(2);
       lv_cod2      VARCHAR2(2);

       filas NUMBER := 5000;

BEGIN

  EXECUTE IMMEDIATE 'TRUNCATE TABLE CRA_REPOR_ZONA_FACTU'; -- eliminamos la data de la tabla temporal
  
  select DECODE(CRA_FN_EVALU_ACTIV_FECHA(lv_cod_fa,p_fecha, p_codigoPais), 1, lv_cod_fa,lv_cod_rf)
         INTO lv_cod1 from dual;

  select DECODE(CRA_FN_EVALU_ACTIV_FECHA(lv_cod_rf,p_fecha, p_codigoPais),1, lv_cod_rf, lv_cod_fa)
         INTO lv_cod2 from dual;
                                            
  OPEN c_zona_factu(p_fecha, 
                             lv_cod1, 
                             lv_cod2,
                             lv_cod_fa,
                             lv_cod_rf  );
     LOOP
       FETCH c_zona_factu BULK COLLECT INTO  v_campana,
                                   v_grupo,
                                   v_zona,
                                   v_facturacion1,
                                   v_fecha_fact1,
                                   v_facturacion2,
                                   v_fecha_fact2  LIMIT filas;


       FORALL i IN 1..v_campana.COUNT
       INSERT INTO CRA_REPOR_ZONA_FACTU(val_nomb_peri,nom_grup,cod_zona,nom_act1,fec_ini1,nom_act2,fec_ini2)
                                      VALUES(v_campana(i),
                                             v_grupo(i),
                                             v_zona(i),
                                             v_facturacion1(i),
                                             v_fecha_fact1(i),
                                             v_facturacion2(i),
                                             v_fecha_fact2(i));

       EXIT WHEN c_zona_factu%NOTFOUND;

       END LOOP;

   CLOSE c_zona_factu;
   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_PR_REPOR_ZONA_FACTU: '||ls_sqlerrm);

END CRA_PR_REPOR_ZONA_FACTU;

/******************************************************************************
 Descripcion         : CRA_FN_EVALU_ACTIV_FECHA
                       Devuelve si la actividad puede figurar en el cronograma 
                       para la fecha
 ------------------------------------------------------------------------------
 Fecha Creacion      : 07/11/2013
 ------------------------------------------------------------------------------
 Autor               : Rosalvina Ramírez
********************************************************************************/

FUNCTION CRA_FN_EVALU_ACTIV_FECHA(p_activ varchar2, p_fecha varchar2,p_codigoPais varchar2)
 RETURN NUMBER IS
 
 ln_ind_no_lab   NUMBER(1);
 ln_oid_act      NUMBER(8);
 ln_fer          NUMBER(8);
 ln_no_lab       NUMBER(8);
 ln_res          NUMBER(1);
 
 BEGIN
   select ca.oid_acti, ca.ind_labo
     INTO ln_oid_act, ln_ind_no_lab
     from cra_activ ca, seg_pais sp
    where ca.pais_oid_pais = sp.oid_pais
      and sp.cod_pais = p_codigoPais
      and ca.ind_acti = '1'
      and ca.cod_acti = p_activ;
  
  select count(*) INTO ln_fer
    from cra_feria cf
   where trunc(cf.fec_feri) = to_date(p_fecha, 'dd/mm/yyyy')
     and cf.cact_oid_acti = ln_oid_act
     and (cf.ind_fest = 1);
     
  select count(*) INTO ln_no_lab
    from cra_feria cf
   where trunc(cf.fec_feri) = to_date(p_fecha, 'dd/mm/yyyy')
     and cf.cact_oid_acti = ln_oid_act
     and (cf.ind_no_labo = 1);
  
     
  IF (ln_fer > 0) THEN
     ln_res := 0;
  ELSE
    IF ln_ind_no_lab  = 1  THEN
       
       IF ln_no_lab > 0 THEN 
         ln_res := 0;
       ELSE 
         ln_res := 1;
       END IF;
    ELSE
      ln_res := 1;
    END IF;    
  END IF;
  
  RETURN ln_res;
 
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR CRA_FN_EVALU_ACTIV_FECHA: '||ls_sqlerrm);

END CRA_FN_EVALU_ACTIV_FECHA;



end CRA_PKG_REPOR;
/