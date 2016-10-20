CREATE OR REPLACE PACKAGE OCR_PKG_REPOR IS

   /* Declaracion de Tipos */
   --TYPE tablaPedidosOCR IS TABLE OF TOBJ_OCR_PEDID;

   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Informacion de Pedidos OCR
Fecha Creacion       : 23/02/2011
Autor             : Sergio Buchelli
Parametros :
     psCodPais    : Codigo Pais,
    psCodPeriodo : Codigo Periodo,
    psCodZona : Codigo Zona
***************************************************************************/
FUNCTION OCR_FN_GENER_REPOR_PEDID(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodZona  VARCHAR2)
RETURN  TTAB_OCR_PEDID ;

/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Avance de Pedidos OCR
Fecha Creacion       : 10/03/2011
Autor             : Sergio Buchelli
Parametros :
     psCodPais    : Codigo Pais,
    psCodPeriodo : Codigo Periodo,
    psCodZona : Codigo Zona
***************************************************************************/
FUNCTION OCR_FN_GENER_REPOR_PEDID_ACTUA(
    psCodPeriodo VARCHAR2,
    psCodZona  VARCHAR2)
RETURN  TTAB_OCR_PEDID_ACTUA ;

/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Informacion de Detalles de
                    Pedidos  No Facturados
Fecha Creacion       : 17/12/2012
Autor             : Sergio Buchelli
Parametros :
    psCodCliente : Codigo Cliente
***************************************************************************/
FUNCTION OCR_FN_REPOR_DETAL_PEDID_NFACT(
    psCodCliente VARCHAR2)
RETURN  TTAB_OCR_PEDID_DETAL_NFACT ;

END OCR_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY OCR_PKG_REPOR IS

/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Informacion de Pedidos OCR
Fecha Creacion       : 23/02/2010
Autor             : Sergio Buchelli
Parametros :
     psCodPais    : Codigo Pais,
    psCodPeriodo : Codigo Periodo,
    psCodZona : Codigo Zona
***************************************************************************/
FUNCTION OCR_FN_GENER_REPOR_PEDID(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodZona  VARCHAR2)
RETURN  TTAB_OCR_PEDID
IS
  ltPedidosOCR  TTAB_OCR_PEDID ;
BEGIN

select TOBJ_OCR_PEDID
    (
        a.COD_CLIE
        ,a.COD_PERI
        ,a.NUM_LOTE
        ,a.FEC_SOLI
        ,a.NOM_CLIE
        ,a.COD_REGI
        ,a.COD_ZONA
        ,a.COD_SECC
        ,a.COD_TERR
        ,a.ESTADO
        ,a.TELEFONO_1
        ,a.TELEFONO_2
        ,a.DIRECCION
        ,a.IND_RECE_DD
        ,a.IND_RECE_WEB
        ,a.IND_RECE_OCR
        ,a.IND_ERRO_DEUD
        ,a.VAL_SALD_DEUD
        ,a.IND_ADMI_CART
        ,a.OBS_PRUB
        ,a.TIP_ORDE
        ,a.CODIGOS_ERRADOS
        ,a.MONTO_PEDIDO
        ,a.MONTO_PEDIDO_SIN_FA
        ,a.MONTO_OCR
        ,a.MONTO_WEB
        ,a.MONTO_DD
        ,a.NUMERO_FA
        ,a.FACTURADO
        ,a.ERROR_MONTO_MINIMO
        ,a.ERROR_MONTO_MAXIMO
        ,a.MONTO_PEDIDO_BLOQUEADO
        ,a.MONTO_MINIMO
        ,a.MONTO_MAXIMO
        ,a.PEDIDOS_ESTIMADOS
    )
    bulk collect
    into ltPedidosOCR
    from
    (
with
tt as
(
  select g.val_oid oid, g.VAL_I18N i18n
  from gen_i18n_sicc_comun g
  where g.ATTR_ENTI='MAE_ESTAT_CLIEN'
),
pp as
(
  select oid_peri d
  from cra_perio
  where val_nomb_peri like '%'||psCodPeriodo||'%'
),
zz as
(
  select oid_zona d
  from zon_zona
  where cod_zona=psCodZona
),
aa as
(
  select max(val_niv1) d
  from ped_monto_minim
),
bb as
(
  select max(VAL_MONT_MAXI_PERM) d
  from car_param_carte
),
cc as
(
  select
    x.d d
  from
  (
      select 2 r, 0 d from dual
    union
      select 1 r, i.num_pedi d
      from INT_FUENT_VENTA_PREVI_SAP i, pp p, zz z
      where i.perd_oid_peri=p.d
      and i.zzon_oid_zona=z.d
  )x
  where rownum=1
),
xx as
(
  select
    a.cod_clie, a.cod_peri, a.num_lote, d.zzon_oid_zona,
    a.fec_soli, a.nom_clie, a.cod_regi, a.cod_zona,
    d.COD_SECC, a.COD_TERR, f.d , a.IND_RECE_DD, a.IND_RECE_WEB,
    a.IND_RECE_OCR, a.IND_ERRO_DEUD, a.VAL_SALD_DEUD, a.IND_ADMI_CART,
    a.OBS_PRUB ,
    (
      select t.i18n
      from mae_clien_datos_adici f, tt t
      where mc.oid_clie = f.clie_oid_clie
      and t.oid = f.esta_oid_esta_clie
    ) ESTADO
    , a.tip_orde
    , decode(a.ind_ocs_proc,1,'SI','NO') FACTURADO
    , decode(a.ind_erro_mtmi,1,'SI','NO') ERROR_MONTO_MINIMO
    , decode(a.ind_erro_mtma,1,'SI','NO') ERROR_MONTO_MAXIMO
    , a.VAL_MONT_PEDI MONTO_PEDIDO_BLOQUEADO
    , (
    select count(*)
from int_solic_conso_detal x, sto_detal_docum_excep y where x.sec_nume_docu=y.sec_nume_docu
and x.cod_clie=a.cod_clie
and x.cod_peri=a.cod_peri
and y.cod_vali='OCD-2'
    ) CODIGOS_ERRADOS
    , (select com.VAL_TEXT_COMU from mae_clien_comun com where com.TICM_OID_TIPO_COMU=1 and com.CLIE_OID_CLIE=mc.oid_clie) TELEFONO_1
    , (select com.VAL_TEXT_COMU from mae_clien_comun com where com.TICM_OID_TIPO_COMU=6 and com.CLIE_OID_CLIE=mc.oid_clie) TELEFONO_2
    , (select dir.VAL_NOMB_VIA || ' ' || dir.NUM_PPAL || ' ' || dir.VAL_OBSE DIRECCION from mae_clien_direc dir where dir.IND_ELIM=0 and dir.CLIE_OID_CLIE=mc.oid_clie and dir.IND_DIRE_PPAL=1) DIRECCION
  from
    int_solic_conso_cabec a,
    mae_clien mc,
    mae_clien_unida_admin mcua,
    zon_terri_admin c,
    zon_secci d,
    pp f
  Where
    a.cod_peri=psCodPeriodo
  and a.COD_CLIE=mc.COD_CLIE
  and mc.oid_clie=mcua.clie_oid_clie
  and mcua.ZTAD_OID_TERR_ADMI=c.oid_terr_admi
  and mcua.ind_acti=1
  and c.ZSCC_OID_SECC=d.OID_SECC
  and a.IND_ERRO_REMP=0
  and a.IND_ERRO_RECH=0
  and a.COD_ZONA=psCodZona
),
yy as
(
  select
    d.cod_clie,
    d.cod_peri,
    d.num_lote,
    sum(decode(d.IND_RECE_OCR,1, d.VAL_UNID_DEM*m.IMP_PREC_CATA,0)) MONTO_OCR,
    sum(decode(d.IND_RECE_WEB,1, d.VAL_UNID_DEM*m.IMP_PREC_CATA,0)) MONTO_WEB,
    sum(decode(d.IND_RECE_DD, 1, d.VAL_UNID_DEM*m.IMP_PREC_CATA,0)) MONTO_DD,
    sum(d.VAL_UNID_DEM * m.IMP_PREC_CATA) MONTO_PEDIDO,
    0 numero_fa,
    0 monto_pedido_sin_fa
  from
    int_solic_conso_cabec     c,
    int_solic_conso_detal     d,
    pre_ofert_detal             m,
    pre_matri_factu_cabec     i,
    pre_ofert                 l,
    pp                        p
  where
      c.cod_peri      = psCodPeriodo
    and    c.cod_zona    = psCodZona
    and d.cod_peri      = psCodPeriodo
    and d.cod_clie      = c.cod_clie
    and m.val_codi_vent = d.cod_vent
    and i.perd_oid_peri = p.d
    and l.oid_ofer      = m.ofer_oid_ofer
    and i.OID_CABE      = l.MFCA_OID_CABE
  group by
    d.cod_clie, d.cod_peri, d.num_lote
 )
SELECT
      xx.cod_clie
    , xx.cod_peri
    , xx.num_lote
    , xx.fec_soli
    , xx.nom_clie
    , xx.cod_regi
    , xx.cod_zona
    , xx.cod_secc
    , xx.cod_terr
    , xx.estado
    , xx.TELEFONO_1
    , xx.TELEFONO_2
    , xx.DIRECCION
    , IND_RECE_DD
    , IND_RECE_WEB
    , IND_RECE_OCR
    , IND_ERRO_DEUD
    , VAL_SALD_DEUD
    , IND_ADMI_CART
    , OBS_PRUB
    , tip_orde
    , CODIGOS_ERRADOS
    , yy.MONTO_PEDIDO
    , yy.MONTO_PEDIDO_SIN_FA
    , yy.MONTO_OCR
    , yy.MONTO_WEB
    , yy.MONTO_DD
    , yy.NUMERO_FA
    , FACTURADO
    , ERROR_MONTO_MINIMO
    , ERROR_MONTO_MAXIMO
    , MONTO_PEDIDO_BLOQUEADO
    , aa.d MONTO_MINIMO
    , bb.d MONTO_MAXIMO
    , cc.d PEDIDOS_ESTIMADOS
  FROM
    xx, yy, aa, bb, cc
 WHERE xx.cod_peri = yy.cod_peri (+)
   AND xx.cod_clie = yy.cod_clie (+)
   AND xx.num_lote = yy.num_lote (+)
    ) a;

   -- pipe row(ltPedidosOCR);


    return ltPedidosOCR;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     ltPedidosOCR.DELETE;
     RAISE_APPLICATION_ERROR(-20123, 'ERROR OCR_PR_GENER_REPOR_PEDID: '||ls_sqlerrm);

  return ltPedidosOCR;
END OCR_FN_GENER_REPOR_PEDID;



/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Avance de Pedidos OCR
Fecha Creacion       : 10/03/2011
Autor             : Sergio Buchelli
Parametros :
     psCodPais    : Codigo Pais,
    psCodPeriodo : Codigo Periodo,
    psCodZona : Codigo Zona
***************************************************************************/
FUNCTION OCR_FN_GENER_REPOR_PEDID_ACTUA(
    psCodPeriodo VARCHAR2,
    psCodZona  VARCHAR2)
RETURN  TTAB_OCR_PEDID_ACTUA
IS
  ltPedidosOCR  TTAB_OCR_PEDID_ACTUA ;
BEGIN

select TOBJ_OCR_PEDID_ACTUA
    (
      a.COD_CLIE,
      a.COD_PERI,
      a.NUM_LOTE,
      a.FEC_SOLI,
      a.NOM_CLIE,
      a.COD_REGI,
      a.COD_ZONA,
      a.COD_SECC,
      a.COD_TERR,
      a.RECHAZO_OTROS,
      a.ESTADO,
      a.IND_RECE_DD,
      a.IND_RECE_WEB,
      a.IND_RECE_OCR ,
      a.IND_ERRO_DEUD,
      a.VAL_SALD_DEUD,
      a.IND_ADMI_CART,
      a.OBS_PRUB,
      a.TIP_ORDE,
      a.CODIGOS_ERRADOS,
      a.MONTO_PEDIDO,
      a.FACTURADO,
      a.ERROR_MONTO_MINIMO,
      a.ERROR_MONTO_MAXIMO,
      a.MONTO_PEDIDO_BLOQUEADO,
      a.MONTO_MINIMO,
      a.MONTO_MAXIMO,
      a.PEDIDOS_ESTIMADOS,
      a.MONTO_ESTIMADOS,
      a.NUMERO_FA,
      a.MONTO_PEDIDO_SIN_FA,
      a.RECHAZO_OCR,
      a.OID_ZONA,
      a.MONTO_FACTURADO,
      a.FECHA_FACTURACION,
      a.OID_PEDIDO,
      a.IND_RECE_DIGI
    )
    bulk collect
    into ltPedidosOCR
    from
    (
with
pp as
(
  select oid_peri d
  from cra_perio
  where val_nomb_peri like '%'||psCodPeriodo||'%'
),
zz as
(
  select oid_zona d, ZORG_OID_REGI r, cod_zona
  from zon_zona
  where cod_zona=psCodZona
),
aa as
(
  select max(val_niv1) d
  from ped_monto_minim
),
cc as
(
  select
    x.d d
  from
  (
      select 2 r, 0 d from dual
    union
      select 1 r, i.num_pedi d
      from INT_FUENT_VENTA_PREVI_SAP i, pp p, zz z
      where i.perd_oid_peri=p.d
      and i.zzon_oid_zona=z.d
  )x
  where rownum=1
),
dd as
(
  select
    x.d d
  from
  (
      select 2 r, 0 d from dual
    union
      select 1 r, i.val_vent_neta_esta d
      from INT_FUENT_VENTA_PREVI_SAP i, pp p, zz z
      where i.perd_oid_peri=p.d
      and i.zzon_oid_zona=z.d
  )x
  where rownum=1
),
/*ee as(
        SELECT h.cod_vent cod_vent,
             h.cod_peri cod_peri,
             h.num_lote num_lote,
             h.cod_clie cod_clie,
             h.val_unid_dem val_unid_dem,
             bb.zzon_oid_zona zzon_oid_zona,
             zz.r ZORG_OID_REGI
        FROM int_solic_conso_detal h,
             int_solic_conso_cabec bb,
             zz
       WHERE h.cod_clie = bb.cod_clie
         AND bb.zzon_oid_zona = zz.d
         AND h.cod_peri=psCodPeriodo
),*/
xx as(
 SELECT a.cod_clie,
        a.cod_peri,
        a.num_lote,
        decode(a.cod_zona,
               NULL,
               (SELECT oid_zona FROM zon_zona WHERE cod_zona = a.cod_zona_arri),
               (SELECT oid_zona FROM zon_zona WHERE cod_zona = a.cod_zona)) zzon_oid_zona,
        a.fec_soli,
        nvl(a.nom_clie,
            (SELECT val_nom1 || ' ' || val_nom2 || ' ' || val_ape1 || ' ' || val_ape2
               FROM mae_clien
              WHERE cod_clie = a.cod_clie)) nom_clie,
        a.cod_regi,
        decode(a.cod_zona,
               NULL,
               a.cod_zona_arri,
               a.cod_zona) cod_zona,
        d.cod_secc,
        e.cod_terr,
        a.ind_rece_dd,
        a.ind_rece_web,
        a.ind_rece_ocr,
        a.ind_rece_digi,
        a.ind_erro_deud,
        a.val_sald_deud,
        a.ind_admi_cart,
        decode((select count(1) from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe_refe and esso_oid_esta_soli=7),0,a.obs_prub,'CS') obs_prub,
        a.tip_orde,
        (SELECT MAX(val_mont_maxi_perm)
           FROM car_param_carte
          WHERE ind_mont_maxi = 1
            AND niri_oid_nive_ries = (SELECT niri_oid_nive_ries
                                        FROM mae_clien_datos_adici,
                                             mae_clien
                                       WHERE clie_oid_clie = oid_clie
                                         AND cod_clie = a.cod_clie)) d,
        decode(a.ind_proc_gp2,
               1,
               'SI',
               'NO') facturado,
        decode(a.ind_erro_mtmi,
               1,
               'SI',
               'NO') error_monto_minimo,
        decode(a.ind_erro_mtma,
               1,
               'SI',
               'NO') error_monto_maximo,
        a.val_mont_pedi monto_pedido_bloqueado,
        (
        select count(1)
        from int_solic_conso_detal x, sto_detal_docum_excep y
        where x.sec_nume_docu=y.sec_nume_docu
        and x.cod_clie=a.cod_clie
        and x.cod_peri=a.cod_peri
        and y.cod_vali='OCD-2'
        ) codigos_errados,
        decode(ind_erro_rech,
               1,
               cod_moti_rech,
               '00') rechazo_ocr,
        /*(SELECT SUM (val_prec_neto_tota_loca)
         FROM ped_solic_posic,
              pre_ofert_detal,
              pre_tipo_ofert
        WHERE soca_oid_soli_cabe = a.soca_oid_soli_cabe_refe
          AND ofde_oid_deta_ofer = oid_deta_ofer
          AND tofe_oid_tipo_ofer = oid_tipo_ofer
          AND val_form_vent = 1)*/
        (SELECT conso.val_tota_paga_loca monto_pedido
           FROM ped_solic_cabec ped,
                ped_solic_cabec conso
          WHERE ped.soca_oid_soli_cabe = conso.oid_soli_cabe
            AND ped.oid_soli_cabe = a.soca_oid_soli_cabe_refe) monto_facturado,
        (SELECT fec_fact FROM ped_solic_cabec WHERE oid_soli_cabe = a.soca_oid_soli_cabe_refe) fec_fact,
        a.soca_oid_soli_cabe_refe oid_soli_cabe,
        CASE
          WHEN (to_number(a.ind_erro_mtmi) + to_number(a.ind_erro_mtma) +
               to_number(decode(ind_erro_rech,
                                 1,
                                 cod_moti_rech,
                                 '00')) + decode(to_number(ind_erro_deud),
                                                  2,
                                                  1,
                                                  0)) +
                                                  (select count(1) from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe_refe and esso_oid_esta_soli=7) > 0 THEN
           0
          ELSE
           decode(nvl((SELECT COUNT(1)
                        FROM sto_detal_docum_excep
                       WHERE sec_nume_docu = a.sec_nume_docu
                         AND cod_tipo_docu = 'OCC'
                         AND cod_vali NOT IN ('OCC-14','OCC-31')
                         and ind_apro=0
                      ),0),
                  0,
                  0,
                  1)
        END rechazo_otros
   FROM int_solic_conso_cabec a,
        zon_terri_admin       b,
        zon_secci             d,
        zon_terri             e,
        zz
  WHERE decode(a.cod_zona,
               NULL,
               a.cod_zona_arri,
               a.cod_zona) = pscodzona
    AND cod_peri = pscodperiodo
    AND a.ztad_oid_terr_admi = b.oid_terr_admi(+)
    AND b.zscc_oid_secc = d.oid_secc(+)
    AND d.zzon_oid_zona = zz.d(+)
    AND b.terr_oid_terr = e.oid_terr(+)
    AND a.ind_erro_remp = 0
),
yy as
(
               SELECT   s.cod_clie,
                        s.cod_peri,
                        s.num_lote,
                        j.oid_peri,
                 SUM (s.val_unid_dem * case when l.coes_oid_estr in (2002,2006) then (select sum(imp_prec_cata) from pre_ofert_detal where ofer_oid_ofer=l.oid_ofer) else m.imp_prec_cata end) monto_pedido,
                 SUM
                    (DECODE ((SELECT MAX (n.val_limi_ctrl_vent)
                                FROM ped_gesti_stock n,
                                     mae_clien o,
                                     mae_clien_tipo_subti p,
                                     mae_clien_clasi q
                               WHERE n.ofde_oid_deta_ofer = m.oid_deta_ofer
                                 AND o.cod_clie = s.cod_clie
                                 AND o.oid_clie = p.clie_oid_clie
                                 AND p.oid_clie_tipo_subt =
                                                     q.ctsu_oid_clie_tipo_subt
                                 AND p.ticl_oid_tipo_clie =
                                        DECODE (n.ticl_oid_tipo_clie,
                                                NULL, p.ticl_oid_tipo_clie,
                                                n.ticl_oid_tipo_clie
                                               )
                                 AND p.sbti_oid_subt_clie =
                                        DECODE (n.sbti_oid_subt_clie,
                                                NULL, p.sbti_oid_subt_clie,
                                                n.sbti_oid_subt_clie
                                               )
                                 AND q.tccl_oid_tipo_clasi =
                                        DECODE (n.tccl_oid_tipo_clas,
                                                NULL, q.tccl_oid_tipo_clasi,
                                                n.tccl_oid_tipo_clas
                                               )
                                 AND q.clas_oid_clas =
                                        DECODE (n.clas_oid_clas,
                                                NULL, q.clas_oid_clas,
                                                n.clas_oid_clas
                                               )
                                 AND s.zzon_oid_zona =
                                        DECODE (n.zzon_oid_zona,
                                                NULL, s.zzon_oid_zona,
                                                n.zzon_oid_zona
                                               )
                                 AND s.ZORG_OID_REGI =
                                        DECODE (n.zorg_oid_regi,
                                                NULL, s.ZORG_OID_REGI,
                                                n.zorg_oid_regi
                                               )
                                 AND n.val_limi_ctrl_vent IS NOT NULL
                                 AND n.perd_oid_peri = j.oid_peri),
                             NULL, 0,
                             1
                            )
                    ) numero_fa,
                 SUM
                    (  DECODE ((SELECT MAX (n.val_limi_ctrl_vent)
                                  FROM ped_gesti_stock n,
                                       mae_clien o,
                                       mae_clien_tipo_subti p,
                                       mae_clien_clasi q
                                 WHERE n.ofde_oid_deta_ofer = m.oid_deta_ofer
                                   AND o.cod_clie = s.cod_clie
                                   AND o.oid_clie = p.clie_oid_clie
                                   AND p.oid_clie_tipo_subt =
                                                     q.ctsu_oid_clie_tipo_subt
                                   AND p.ticl_oid_tipo_clie =
                                          DECODE (n.ticl_oid_tipo_clie,
                                                  NULL, p.ticl_oid_tipo_clie,
                                                  n.ticl_oid_tipo_clie
                                                 )
                                   AND p.sbti_oid_subt_clie =
                                          DECODE (n.sbti_oid_subt_clie,
                                                  NULL, p.sbti_oid_subt_clie,
                                                  n.sbti_oid_subt_clie
                                                 )
                                   AND q.tccl_oid_tipo_clasi =
                                          DECODE (n.tccl_oid_tipo_clas,
                                                  NULL, q.tccl_oid_tipo_clasi,
                                                  n.tccl_oid_tipo_clas
                                                 )
                                   AND q.clas_oid_clas =
                                          DECODE (n.clas_oid_clas,
                                                  NULL, q.clas_oid_clas,
                                                  n.clas_oid_clas
                                                 )
                                   AND s.zzon_oid_zona =
                                          DECODE (n.zzon_oid_zona,
                                                  NULL, s.zzon_oid_zona,
                                                  n.zzon_oid_zona
                                                 )
                                   AND s.ZORG_OID_REGI =
                                          DECODE (n.zorg_oid_regi,
                                                  NULL, s.ZORG_OID_REGI,
                                                  n.zorg_oid_regi
                                                 )),
                               NULL, s.val_unid_dem,
                               (SELECT MAX (n.val_limi_ctrl_vent)
                                  FROM ped_gesti_stock n,
                                       mae_clien o,
                                       mae_clien_tipo_subti p,
                                       mae_clien_clasi q
                                 WHERE n.ofde_oid_deta_ofer = m.oid_deta_ofer
                                   AND o.cod_clie = s.cod_clie
                                   AND o.oid_clie = p.clie_oid_clie
                                   AND p.oid_clie_tipo_subt =
                                                     q.ctsu_oid_clie_tipo_subt
                                   AND p.ticl_oid_tipo_clie =
                                          DECODE (n.ticl_oid_tipo_clie,
                                                  NULL, p.ticl_oid_tipo_clie,
                                                  n.ticl_oid_tipo_clie
                                                 )
                                   AND p.sbti_oid_subt_clie =
                                          DECODE (n.sbti_oid_subt_clie,
                                                  NULL, p.sbti_oid_subt_clie,
                                                  n.sbti_oid_subt_clie
                                                 )
                                   AND q.tccl_oid_tipo_clasi =
                                          DECODE (n.tccl_oid_tipo_clas,
                                                  NULL, q.tccl_oid_tipo_clasi,
                                                  n.tccl_oid_tipo_clas
                                                 )
                                   AND q.clas_oid_clas =
                                          DECODE (n.clas_oid_clas,
                                                  NULL, q.clas_oid_clas,
                                                  n.clas_oid_clas
                                                 )
                                   AND n.val_limi_ctrl_vent IS NOT NULL
                                   AND n.perd_oid_peri = j.oid_peri
                                   AND s.zzon_oid_zona =
                                          DECODE (n.zzon_oid_zona,
                                                  NULL, s.zzon_oid_zona,
                                                  n.zzon_oid_zona
                                                 )
                                   AND s.zorg_oid_regi =
                                          DECODE (n.zorg_oid_regi,
                                                  NULL, s.zorg_oid_regi,
                                                  n.zorg_oid_regi
                                                 ))
                              )
                     * case when l.coes_oid_estr in (2002,2006) then (select sum(imp_prec_cata) from pre_ofert_detal where ofer_oid_ofer=l.oid_ofer) else m.imp_prec_cata end
                    ) monto_pedido_sin_fa
            FROM (SELECT h.cod_vent cod_vent,
                     h.cod_peri cod_peri,
                     h.num_lote num_lote,
                     h.cod_clie cod_clie,
                     h.val_unid_dem val_unid_dem,
                     bb.zzon_oid_zona zzon_oid_zona,
                     zz.r ZORG_OID_REGI
                FROM int_solic_conso_detal h,
                     int_solic_conso_cabec bb,
                     zz
               WHERE h.cod_clie = bb.cod_clie
                 AND bb.zzon_oid_zona = zz.d
                 AND h.cod_peri=psCodPeriodo) s,
                 pre_matri_factu_cabec i,
                 cra_perio j,
                 seg_perio_corpo k,
                 pre_ofert l,
                 pre_ofert_detal m
           WHERE s.cod_peri = k.cod_peri
             AND k.oid_peri = j.peri_oid_peri
             AND s.cod_peri = psCodPeriodo
             AND j.oid_peri = i.perd_oid_peri
             AND i.oid_cabe = l.mfca_oid_cabe
             AND l.oid_ofer = m.ofer_oid_ofer
             AND m.val_codi_vent = s.cod_vent
        GROUP BY s.cod_clie, s.cod_peri, s.num_lote, j.oid_peri

 )
SELECT DISTINCT
      xx.cod_clie
    , xx.cod_peri
    , xx.num_lote
    , xx.fec_soli
    , xx.nom_clie
    , xx.cod_regi
    , xx.cod_zona
    , xx.cod_secc
    , xx.cod_terr
    , xx.rechazo_otros
    ,CASE
      WHEN xx.facturado = 'SI'
         THEN 'FACTURADO'
      WHEN xx.rechazo_otros = 1
       OR xx.error_monto_minimo = 'SI'
       OR xx.error_monto_maximo = 'SI'
       OR xx.ind_erro_deud = 2
       OR xx.rechazo_ocr   <> '00'
       OR xx.obs_prub   = 'CS'
         THEN 'RECHAZADO'
      WHEN xx.ind_erro_deud = 2
        OR yy.monto_pedido     < aa.d
        OR yy.monto_pedido > xx.d
         THEN 'OBSERVADO'
      ELSE 'ENVIADO'
    END estado
    , IND_RECE_DD
    , IND_RECE_WEB
    , IND_RECE_OCR
    , IND_ERRO_DEUD
    , VAL_SALD_DEUD
    , IND_ADMI_CART
    , OBS_PRUB
    , tip_orde
    , CODIGOS_ERRADOS
    , yy.monto_pedido
    , facturado
    , error_monto_minimo
    , error_monto_maximo
    , NVL(monto_pedido_bloqueado,0)  MONTO_PEDIDO_BLOQUEADO
    , aa.d MONTO_MINIMO
    , xx.d MONTO_MAXIMO
    , cc.d PEDIDOS_ESTIMADOS
    , dd.d MONTO_ESTIMADOS
     ,yy.numero_fa
     ,yy.monto_pedido_sin_fa
     ,xx.rechazo_ocr
     ,xx.zzon_oid_zona   OID_ZONA
     ,NVL(xx.monto_facturado,0) MONTO_FACTURADO
     ,xx.fec_fact FECHA_FACTURACION
     ,xx.oid_soli_cabe OID_PEDIDO
     ,ind_rece_digi   IND_RECE_DIGI
  FROM
    xx, yy, aa, cc, dd, pp, zz
 WHERE xx.cod_peri = yy.cod_peri (+)
   AND xx.cod_clie = yy.cod_clie (+)
   AND xx.num_lote = yy.num_lote (+)
  -- AND xx.cod_peri = ee.cod_peri(+)
    ) a;

   -- pipe row(ltPedidosOCR);


    return ltPedidosOCR;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     ltPedidosOCR.DELETE;
     RAISE_APPLICATION_ERROR(-20123, 'ERROR OCR_FN_GENER_REPOR_PEDID_ACTUA: '||ls_sqlerrm);

  return ltPedidosOCR;
END OCR_FN_GENER_REPOR_PEDID_ACTUA;

/***************************************************************************
Descripcion          : Devuelve Lista de Reporte de Informacion de Detalles de
                    Pedidos  No Facturados
Fecha Creacion       : 17/12/2012
Autor             : Sergio Buchelli
Parametros :
    psCodCliente : Codigo Cliente
***************************************************************************/
FUNCTION OCR_FN_REPOR_DETAL_PEDID_NFACT(
    psCodCliente VARCHAR2) RETURN  TTAB_OCR_PEDID_DETAL_NFACT
IS
  ltDetallePedidoNoFacturado  TTAB_OCR_PEDID_DETAL_NFACT ;
BEGIN

    select TOBJ_OCR_DETAL_PEDID_NFACT
    ( a.cod_vent,
      a.des_prod,
      a.val_unid_dem,
      a.VAL_LIMI_CTRL_VENT,
      a.VAL_PREC_CATA_UNIT_LOCA,
      a.VAL_PREC_CATA_TOTA_LOCA
    )
    bulk collect
    into ltDetallePedidoNoFacturado
    from (
       select distinct b.cod_vent,
              des_prod,
              val_unid_dem,
              VAL_LIMI_CTRL_VENT ,
              VAL_PREC_CATA_UNIT_LOCA,
              VAL_PREC_CATA_UNIT_LOCA*val_unid_dem  VAL_PREC_CATA_TOTA_LOCA
        from int_solic_conso_cabec a,
             int_solic_conso_detal b,
             ped_gesti_stock c
        where a.cod_clie=b.cod_clie and a.cod_clie= psCodCliente
        and b.ofde_oid_deta_ofer=c.ofde_oid_deta_ofer(+)
        and c.VAL_LIMI_CTRL_VENT(+) is not null
     )a;

    return ltDetallePedidoNoFacturado;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     ltDetallePedidoNoFacturado.DELETE;
     RAISE_APPLICATION_ERROR(-20123, 'ERROR OCR_FN_REPOR_DETAL_PEDID_NFACT: '||ls_sqlerrm);

  return ltDetallePedidoNoFacturado;
END OCR_FN_REPOR_DETAL_PEDID_NFACT;

END OCR_PKG_REPOR;
/
