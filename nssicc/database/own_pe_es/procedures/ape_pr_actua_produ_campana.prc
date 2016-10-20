CREATE OR REPLACE PROCEDURE APE_PR_ACTUA_PRODU_CAMPANA(
pFechFact   VARCHAR2
)
IS
W_FILAS     NUMBER := 5000;
ln_oid_peri NUMBER(12):=0;
ln_flag     NUMBER(12):=0;

TYPE tmptablaProducto IS RECORD
(
  perd_oid_peri                 APE_LISTA_PICAD_CABEC.perd_oid_peri %TYPE,
  oid_prod                      MAE_PRODU.oid_prod%TYPE,
  fec_factu                     APE_LISTA_PICAD_CABEC.fec_factu %TYPE
);

TYPE tmptablaAsigProduc IS RECORD
(
  perd_oid_peri                 APE_LISTA_PICAD_CABEC.perd_oid_peri %TYPE,
  oid_prod                      MAE_PRODU.oid_prod%TYPE
);

TYPE tablaRegProducto IS TABLE OF tmptablaProducto;
tablaRegProductosrecord tablaRegProducto;

TYPE tablaRegAsigProducto IS TABLE OF tmptablaAsigProduc;
tablaRegAsigProducrecord tablaRegAsigProducto;

-- se obtiene los productos y campaña correspondiente a la fecha de Facturación
-- considerando los Mapas Activos y con Indicador de Facturación = Si
CURSOR c_periodo_fecfact IS

  SELECT cabe.perd_oid_peri, deta.prod_oid_prod, cabe.fec_factu
  FROM   APE_LISTA_PICAD_CABEC cabe,
         APE_LISTA_PICAD_DETAL deta
  WHERE  cabe.OID_LIST_PICA_CABE = deta.LPCA_OID_LIST_PICA_CABE
  AND    TRUNC(cabe.fec_factu)   = TO_DATE(pFechFact,'dd/MM/yyyy')
  AND    NOT EXISTS( SELECT 1
                     FROM  ape_produ_xcamp xc
                     WHERE xc.perd_oid_peri = cabe.perd_oid_peri
                     AND   xc.prod_oid_prod = deta.prod_oid_prod )
  AND    deta.prod_oid_prod IS NOT NULL
  GROUP BY cabe.perd_oid_peri, deta.prod_oid_prod, cabe.fec_factu
  ORDER BY deta.prod_oid_prod;

CURSOR c_asigprod_periodo(vn_oid_peri NUMBER) IS
  SELECT asgcab.perd_oid_peri, asgdet.prod_oid_prod
  FROM   APE_ASIGN_PRODU_ANAQU_DETAL asgdet,
         APE_ASIGN_PRODU_ANAQU_CABEC asgcab
  WHERE  asgdet.apac_oid_asig_prod_anaq_cabe = asgcab.oid_asig_prod_anaq_cabe
  AND    asgcab.ind_acti_fact = 'S'
  AND    asgcab.perd_oid_peri = vn_oid_peri
  AND    NOT EXISTS( SELECT 1
                     FROM  ape_produ_xcamp xc
                     WHERE xc.perd_oid_peri = asgcab.perd_oid_peri
                     AND   xc.prod_oid_prod = asgdet.prod_oid_prod)
  AND    asgdet.prod_oid_prod IS NOT NULL
  GROUP BY asgcab.perd_oid_peri, asgdet.prod_oid_prod
  ORDER BY asgdet.prod_oid_prod;

BEGIN
  ln_flag := 0;

  OPEN c_periodo_fecfact;
  LOOP
     FETCH c_periodo_fecfact BULK COLLECT INTO tablaRegProductosrecord LIMIT W_FILAS;

    IF tablaRegProductosrecord.COUNT > 0 THEN

        FOR x IN tablaRegProductosrecord.FIRST .. tablaRegProductosrecord.LAST LOOP

            IF (ln_flag = 0) THEN
                ln_oid_peri:= tablaRegProductosrecord(x).perd_oid_peri;
                ln_flag := 1;
            END IF;

            IF (ln_oid_peri = 0) THEN
               ln_flag := 0;
            END IF;

            -- Por cada producto seleccionado inserta un registro en la tabla APE_PRODU_XCAMP
            -- si es que este producto no existe previamente.
            INSERT INTO APE_PRODU_XCAMP
            (
               oid_prod_xcam        ,        perd_oid_peri                    ,           prod_oid_prod                      ,              fec_fact
            )
            VALUES
            (
               APE_APPX_SEQ.NEXTVAL , tablaRegProductosrecord(x).perd_oid_peri,     tablaRegProductosrecord(x).oid_prod      , tablaRegProductosrecord(x).fec_factu
            );

        END LOOP;

    END IF;

    EXIT WHEN c_periodo_fecfact%NOTFOUND;

  END LOOP;

  CLOSE c_periodo_fecfact;

  -- Se ejecuta el proceso de inserción de Productos consolidados de la Asignación Productos x Anaquel - segundo Query

  OPEN c_asigprod_periodo(ln_oid_peri);
  LOOP
    FETCH c_asigprod_periodo BULK COLLECT INTO tablaRegAsigProducrecord LIMIT W_FILAS;

    IF tablaRegAsigProducrecord.COUNT > 0 THEN

        FOR x IN tablaRegAsigProducrecord.FIRST .. tablaRegAsigProducrecord.LAST LOOP

            -- Por cada producto seleccionado inserta un registro en la tabla APE_PRODU_XCAMP
            -- si es que este producto no existe previamente.
            INSERT INTO APE_PRODU_XCAMP
            (
               oid_prod_xcam        ,        perd_oid_peri                    ,           prod_oid_prod                      ,              fec_fact
            )
            VALUES
            (
               APE_APPX_SEQ.NEXTVAL , tablaRegAsigProducrecord(x).perd_oid_peri,     tablaRegAsigProducrecord(x).oid_prod      ,           TO_DATE(pFechFact,'dd/MM/yyyy')
            );

        END LOOP;

    END IF;

    EXIT WHEN c_asigprod_periodo%NOTFOUND;

  END LOOP;

  CLOSE c_asigprod_periodo;

END APE_PR_ACTUA_PRODU_CAMPANA;
/

