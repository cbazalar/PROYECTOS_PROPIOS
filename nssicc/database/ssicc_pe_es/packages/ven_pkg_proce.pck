CREATE OR REPLACE PACKAGE "VEN_PKG_PROCE" IS
 /* Declaracion de Tipos */
 TYPE TIPOCURSOR IS REF CURSOR;
 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 W_FILAS NUMBER:=1000;

/* Declaracion de Funciones */
/***************************************************************************
Descripcion : Devuelve el flag de rezonificacion
Fecha Creacion : 23/04/2009
Autor : Telly Tataje
Modified by		 : Sergio Buchelli Silva
Fech Modified	 : 23/05/2009
Parametros :
 psOidPeriAnte : oid Periodo
 psOidTerr : oid territorio
 psCodigoRegion : Codigo de Region
 psCodigodZona : Codigo de Zona
 psCodigoSeccion : Codigo de Seccion
***************************************************************************/
FUNCTION VEN_FN_DEVUE_FLAG_REZON
(
 psOidPeriAnte 	 NUMBER,
 psOidTerr 	 VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigodZona VARCHAR2,
 psCodigoSeccion VARCHAR2
)
RETURN NUMBER;


/* Declaracion de Procedimientos Almacenados */

/***************************************************************************
Descripcion : Genera la data para el Reporte de Venta Variable de
 la tabla Historica
Fecha Creacion : 15/04/2009
Autor : Telly Tataje
Modified by		 : Sergio Buchelli Silva
Fech Modified	 : 23/05/2009
Parametros :
 psCodPais : codigo Pais
 psCodMarca : codigo Marca
 psCodCanal : codigo Canal
 psCodPeriodo : codigo Periodo
***************************************************************************/
PROCEDURE VEN_PR_CALCU_FUENT_VENTA_REAL(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psCodPeriodo VARCHAR2);

/***************************************************************************
Descripcion : Permite actualizar la fuente de venta acumulada con las unidades y monto de venta neta
 de productos de eventa estdisticabae por campaña y territorio
Fecha Creacion : 07/09/2009
Autor : Sergio Buchelli Silva
Parametros :
 psCodPais : codigo Pais
 psCodMarca : codigo Marca
 psCodCanal : codigo Canal
 psCodPeriodo : codigo Periodo
***************************************************************************/
PROCEDURE VEN_PR_CALCU_FUENT_VENTA_ACUMU(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psCodPeriodo VARCHAR2);
END VEN_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY "VEN_PKG_PROCE"
IS

/***************************************************************************
Descripcion : Devuelve el flag de rezonificacion
Fecha Creacion : 23/04/2009
Autor : Telly Tataje
Modified by : Sergio Buchelli Silva
Fech Modified : 23/05/2009
Parametros :
 psOidPeriAnte : oid Periodo
 psOidTerr : oid territorio
 psCodigoRegion : Codigo de Region
 psCodigodZona : Codigo de Zona
 psCodigoSeccion : Codigo de Seccion
***************************************************************************/
FUNCTION VEN_FN_DEVUE_FLAG_REZON
(
 psOidPeriAnte NUMBER,
 psOidTerr VARCHAR2,
 psCodigoRegion VARCHAR2,
 psCodigodZona VARCHAR2,
 psCodigoSeccion VARCHAR2
)
RETURN NUMBER
IS
 lsCodigoRegion ZON_REGIO.COD_REGI%TYPE;
 lsCodigodZona ZON_ZONA.COD_ZONA%TYPE;
 lsCodigoSeccion ZON_SECCI.COD_SECC%TYPE;
 lnFlag NUMBER;
BEGIN

 lsCodigoRegion:='';
 lsCodigodZona:='';
 lsCodigoSeccion:='';
 lnFlag:=0;
 BEGIN
 SELECT D.COD_REGI, C.COD_ZONA, B.COD_SECC
   INTO LSCODIGOREGION, LSCODIGODZONA, LSCODIGOSECCION
   FROM ZON_TERRI_ADMIN A, ZON_SECCI B, ZON_ZONA C, ZON_REGIO D
  WHERE A.TERR_OID_TERR = PSOIDTERR
    AND (PSOIDPERIANTE >= A.PERD_OID_PERI_INIC OR
        A.PERD_OID_PERI_INIC IS NULL) --oid anterior
    AND (PSOIDPERIANTE <= A.PERD_OID_PERI_FINA OR
        A.PERD_OID_PERI_FINA IS NULL) --oid anteriro
 AND A.ZSCC_OID_SECC = B.OID_SECC
 AND B.ZZON_OID_ZONA = C.OID_ZONA
 AND C.ZORG_OID_REGI = D.OID_REGI;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN lnFlag;--NO ES REZONIFICADO
 END;


 IF( (psCodigoRegion!=lsCodigoRegion) OR (psCodigodZona!=lsCodigodZona) OR (psCodigoSeccion!=lsCodigoSeccion) )THEN
 lnFlag:=1;--ES RZONIFICADO
 END IF;

 RETURN lnFlag;
END VEN_FN_DEVUE_FLAG_REZON;

/***************************************************************************
Descripcion : Genera la data para el Reporte de Venta Variable de
 la tabla Historica
Fecha Creacion : 15/04/2009
Fecha Modificacion : 22/05/2009
Autor : Telly Tataje
Modified by : Sergio Buchelli Silva
Fech Modified : 23/05/2009
Parametros :
 psCodPais : codigo Pais
 psCodMarca : codigo Marca
 psCodCanal : codigo Canal
 psCodPeriodo : codigo Periodo
***************************************************************************/
PROCEDURE VEN_PR_CALCU_FUENT_VENTA_REAL
 (
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psCodPeriodo VARCHAR2
)
IS
 
 lnIdPais seg_pais.oid_pais%TYPE;
 lnIdCanal seg_canal.oid_cana%TYPE;
 lnIdMarca seg_marca.oid_marc%TYPE;
 lnIdPeriodo seg_perio_corpo.oid_peri%TYPE;
 lnIdPeriodoAnterior seg_perio_corpo.oid_peri%TYPE;
 lnNumRegCerradas NUMBER;
 lnNumRegCerradasAnt NUMBER;
 lnFlagProceso BOOLEAN := TRUE;

 /* Creando el tipo de dato territorio */
 TYPE cTerritorio IS RECORD
 (
 OID_TERR_ADMI ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE,
 OID_TERR ZON_TERRI.OID_TERR%TYPE,
 COD_TERR ZON_TERRI.COD_TERR%TYPE,
 OID_SECC ZON_SECCI.OID_SECC%TYPE,
 COD_SECC ZON_SECCI.COD_SECC%TYPE,
 OID_ZONA ZON_ZONA.OID_ZONA%TYPE,
 COD_ZONA ZON_ZONA.COD_ZONA%TYPE,
 OID_REGI ZON_REGIO.OID_REGI%TYPE,
 COD_REGI ZON_REGIO.COD_REGI%TYPE
 );


 /* Creando el tipo de dato consultora */
 TYPE cConsultora IS RECORD
 (
 OID_CLIE MAE_CLIEN.OID_CLIE%TYPE,
 COD_CLIE MAE_CLIEN.COD_CLIE%TYPE,
 OID_ESTA_CLIE MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE,
 COD_ESTA_CLIE MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
 PERD_OID_PERI_INI MAE_CLIEN_UNIDA_ADMIN.PERD_OID_PERI_INI%TYPE,
 PERD_OID_PERI_FIN MAE_CLIEN_UNIDA_ADMIN.PERD_OID_PERI_FIN%TYPE,
 ZTAD_OID_TERR_ADMI MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE,
 NUM_CAMP_SIN_PEDI MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE,
 OID_TERR ZON_TERRI.OID_TERR%TYPE,
 COD_TERR ZON_TERRI.COD_TERR%TYPE,
 OID_SECC ZON_SECCI.OID_SECC%TYPE,
 COD_SECC ZON_SECCI.COD_SECC%TYPE,
 OID_ZONA ZON_ZONA.OID_ZONA%TYPE,
 COD_ZONA ZON_ZONA.COD_ZONA%TYPE,
 OID_REGI ZON_REGIO.OID_REGI%TYPE,
 COD_REGI ZON_REGIO.COD_REGI%TYPE
 );

 TYPE TABLA_CONSULTORA IS TABLE OF cConsultora;
 rowConsultora cConsultora;
 tablaConsultora TABLA_CONSULTORA;
 tmpRow VEN_GTT_FUENT_VENTA_REAL%ROWTYPE;
 tmpRowAnt VEN_GTT_FUENT_VENTA_REAL%ROWTYPE;

 tmpTerritorio cTerritorio;
 
 lnAnhoProceso VARCHAR2(4);
 hayRegion BOOLEAN;
 hayZona BOOLEAN;
 lnFlagProcesoAnterior BOOLEAN;
 lnHayEgreso NUMBER;
 lsIndicadodorEgreso VARCHAR2(1);
 lnActivas              INT_FUENT_VENTAS_REAL.Num_Acti_Fina%type;

 /* Creando el curso para las consultoras */
 CURSOR c_consultoras (lnIdPeriodo NUMBER,lnIdPeriodoAnterior NUMBER) IS

    WITH TMP AS
     (
      SELECT he.clie_oid_clie,
             he.esta_oid_esta_clie
        FROM mae_clien_histo_estat he
       WHERE (lnidperiodo >= he.perd_oid_peri or he.perd_oid_peri IS NULL)
         AND (lnidperiodo <= he.perd_oid_peri_peri_fin or he.perd_oid_peri_peri_fin IS NULL)
     ) SELECT a.oid_clie,cod_clie,
              c.oid_esta_clie,
              c.cod_esta_clie,
              d.perd_oid_peri_ini,
              d.perd_oid_peri_fin,
              d.ztad_oid_terr_admi,
              b.num_camp_sin_pedi,
              f.oid_terr,
              f.cod_terr,
              g.oid_secc,
              g.cod_secc,
              h.oid_zona,
              h.cod_zona,
              i.oid_regi,
              i.cod_regi
         FROM mae_clien a,
              mae_clien_datos_adici b,
              mae_estat_clien c,
              mae_clien_unida_admin d,
              zon_terri_admin e,
              zon_terri f,
              zon_secci g,
              zon_zona h,
              zon_regio i,
              tmp he
        WHERE a.oid_clie = b.clie_oid_clie
          AND b.ind_acti = '1'
          AND he.clie_oid_clie = a.oid_clie
          AND he.esta_oid_esta_clie = c.oid_esta_clie
          AND a.oid_clie = d.clie_oid_clie
          AND (lnidperiodo >= d.perd_oid_peri_ini )
          AND (lnidperiodo <= d.perd_oid_peri_fin or d.perd_oid_peri_fin IS NULL)
          AND d.ztad_oid_terr_admi = e.oid_terr_admi
          AND e.terr_oid_terr = f.oid_terr
          AND e.zscc_oid_secc = g.oid_secc
          AND g.zzon_oid_zona = h.oid_zona
          AND h.zorg_oid_regi = i.oid_regi
          AND EXISTS (--en regiones cerradas
                     SELECT count(1)
                       from fac_contr_cierr x , fac_tipos_cierr y
                      where x.tcie_oid_tipo_cier = y.oid_tipo_cier
                        AND y.cod_tipo_cier ='R'
                        AND x.perd_oid_peri = lnidperiodo
                        AND i.oid_regi = x.zorg_oid_regi
                    );

BEGIN

 /* obteniendos ids (Pais, Canal, Marca) */
 lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 lnIdCanal := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
 lnIdMarca := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);

 /* obteniendo el oid del periodo actual con la funcion */
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodPeriodo,lnIdMarca,lnIdCanal);
 /* obteniendo el oid del periodo anterior */
 lnIdPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(
 PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1),lnIdMarca,lnIdCanal);

 /* obtenemos el año de proceso */
 lnAnhoProceso := SUBSTR(psCodPeriodo,1,4);


 /* Borrando tabla temporal */
 DELETE FROM VEN_GTT_FUENT_VENTA_REAL;

 /* Insertando en el temporal los distintos territorios activos en campanha a procesar y territots activos en campanha anterior
 solo de las regiones que cierran
 */
INSERT INTO VEN_GTT_FUENT_VENTA_REAL
  (COD_PAIS,
   COD_TERR,
   OID_TERR,
   COD_REGI,
   OID_REGI,
   COD_ZONA,
   OID_ZONA,
   COD_SECC,
   OID_SECC,
   OID_PERI,
   IND_ACTU,
   INT_REZO,
   ACT_INIC,
   NUM_INGR,
   NUM_REIN,
   NUM_EGRE,
   NUM_REZO_ENTR_SECC,
   NUM_REZO_ENTR_ZONA,
   NUM_REZO_ENTR_REGI,
   NUM_REZO_RECI_SECC,
   NUM_REZO_RECI_ZONA,
   NUM_REZO_RECI_REGI,
   NUM_REZO_ENTR_TOTA,
   NUM_REZO_RECI_TOTA,
   FEC_PROC,
   ACT_FINA,
   NUM_REZO_ENTR_TERR,
   NUM_REZO_RECI_TERR)
 SELECT PSCODPAIS,
        A.COD_TERR,
        A.OID_TERR,
        E.COD_REGI,
        E.OID_REGI,
        D.COD_ZONA,
        D.OID_ZONA,
        C.COD_SECC,
        C.OID_SECC,
        LNIDPERIODO,
        1,
        VEN_PKG_PROCE.VEN_FN_DEVUE_FLAG_REZON(LNIDPERIODOANTERIOR,
                                              A.OID_TERR,
                                              E.COD_REGI,
                                              D.COD_ZONA,
                                              C.COD_SECC),
 NVL((SELECT IFVR.NUM_ACTI_FINA
 FROM INT_FUENT_VENTAS_REAL IFVR
 WHERE IFVR.TERR_OID_TERR = A.OID_TERR
 AND IFVR.PERD_OID_PERI = lnIdPeriodoAnterior),0) ACT_INIC,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        SYSDATE,
         NVL((SELECT IFVR.NUM_ACTI_FINA
 FROM INT_FUENT_VENTAS_REAL IFVR
 WHERE IFVR.TERR_OID_TERR = A.OID_TERR
 AND IFVR.PERD_OID_PERI = lnIdPeriodoAnterior),0) ACT_FINA,
        0,
        0
   FROM ZON_TERRI       A,
        ZON_TERRI_ADMIN B,
        ZON_SECCI       C,
        ZON_ZONA        D,
        ZON_REGIO       E
 WHERE A.OID_TERR = B.TERR_OID_TERR
 --AND B.IND_BORR = 0
 --AND A.IND_BORR = 0
    AND (B.PERD_OID_PERI_INIC IS NULL OR
        LNIDPERIODO > = B.PERD_OID_PERI_INIC)
    AND (LNIDPERIODO <= B.PERD_OID_PERI_FINA OR
        B.PERD_OID_PERI_FINA IS NULL)
 AND B.ZSCC_OID_SECC = C.OID_SECC
 AND C.ZZON_OID_ZONA = D.OID_ZONA
 AND D.ZORG_OID_REGI = E.OID_REGI
    AND D.ZORG_OID_REGI IN
        ( --TERRITORIOS DE REGIONES CERARDAS
 SELECT DISTINCT X.ZORG_OID_REGI
 FROM FAC_CONTR_CIERR X , FAC_TIPOS_CIERR Y
 WHERE X.TCIE_OID_TIPO_CIER = Y.OID_TIPO_CIER
 AND Y.COD_TIPO_CIER ='R'
            AND X.PERD_OID_PERI = LNIDPERIODO
 AND D.ZORG_OID_REGI = X.ZORG_OID_REGI)
 UNION ALL --TERRITOROS ACTIVOS EN CAMPANHA ANTERIOR
 SELECT PSCODPAIS,
        A.COD_TERR,
        A.OID_TERR,
        E.COD_REGI,
        E.OID_REGI,
        D.COD_ZONA,
        D.OID_ZONA,
        C.COD_SECC,
        C.OID_SECC,
        LNIDPERIODO,
        0,
        0,
 NVL((SELECT IFVR.NUM_ACTI_FINA
 FROM INT_FUENT_VENTAS_REAL IFVR
 WHERE IFVR.TERR_OID_TERR = A.OID_TERR
 AND IFVR.PERD_OID_PERI = lnIdPeriodoAnterior),0) ACT_INIC,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        SYSDATE,
 NVL((SELECT IFVR.NUM_ACTI_FINA
 FROM INT_FUENT_VENTAS_REAL IFVR
 WHERE IFVR.TERR_OID_TERR = A.OID_TERR
 AND IFVR.PERD_OID_PERI = lnIdPeriodoAnterior),0) ACT_FINA,
        0,
        0
   FROM ZON_TERRI       A,
        ZON_TERRI_ADMIN B,
        ZON_SECCI       C,
        ZON_ZONA        D,
        ZON_REGIO       E
 WHERE A.OID_TERR = B.TERR_OID_TERR
 AND B.IND_BORR = 1
 AND A.IND_BORR = 1
    AND B.PERD_OID_PERI_FINA = LNIDPERIODOANTERIOR --oid de la CAMPANHA ANTES DEL PERIODO
 AND B.ZSCC_OID_SECC = C.OID_SECC
 AND C.ZZON_OID_ZONA = D.OID_ZONA
 AND D.ZORG_OID_REGI = E.OID_REGI
    AND D.ZORG_OID_REGI IN
        ( --TERRITORIOS DE REGIONES CERARDAS
 SELECT DISTINCT X.ZORG_OID_REGI
 FROM FAC_CONTR_CIERR X , FAC_TIPOS_CIERR Y
 WHERE X.TCIE_OID_TIPO_CIER = Y.OID_TIPO_CIER
 AND Y.COD_TIPO_CIER ='R'
            AND X.PERD_OID_PERI = LNIDPERIODO
 AND D.ZORG_OID_REGI = X.ZORG_OID_REGI);

 /*Borrando duplicados*/
 DELETE FROM VEN_GTT_FUENT_VENTA_REAL A
  WHERE A.OID_PERI = LNIDPERIODO
 AND A.IND_ACTU = '0'
    AND A.COD_TERR IN (SELECT X.COD_TERR
                         FROM VEN_GTT_FUENT_VENTA_REAL X
 WHERE A.COD_TERR = X.COD_TERR
                        GROUP BY X.COD_TERR
                       HAVING COUNT(1) > 1);

 /* Generando informacion */
 OPEN c_consultoras(lnIdPeriodo,lnIdPeriodoAnterior);
 LOOP
  FETCH c_consultoras BULK COLLECT
    INTO tablaConsultora LIMIT W_FILAS;
 IF tablaConsultora.COUNT > 0 THEN
 FOR x IN tablaConsultora.FIRST .. tablaConsultora.LAST LOOP
 lnFlagProceso := TRUE;
 rowConsultora := tablaConsultora(x);
     /*
      BEGIN
         SELECT IFVR.NUM_ACTI_FINA
         into lnActivas
         FROM INT_FUENT_VENTAS_REAL IFVR
         WHERE IFVR.TERR_OID_TERR = rowConsultora.OID_TERR
          AND IFVR.PERD_OID_PERI = LNIDPERIODOANTERIOR;
          
        UPDATE VEN_GTT_FUENT_VENTA_REAL VFVR
        SET
           ACT_INIC = lnActivas,
           ACT_FINA = lnActivas
        WHERE VFVR.COD_TERR = ROWCONSULTORA.COD_TERR;
      
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lnActivas := 0;
      END;
      */

      BEGIN
        --OBTENEMOS COD_TERRITORIO ACTUAL DEL GTT
 SELECT COD_PAIS,
        COD_TERR,
        OID_TERR,
        COD_REGI,
        OID_REGI,
        COD_ZONA,
        OID_ZONA,
        COD_SECC,
        OID_SECC,
        OID_PERI,
        IND_ACTU,
        INT_REZO,
        ACT_INIC,
        NUM_INGR,
        NUM_REIN,
        NUM_EGRE,
        NUM_REZO_ENTR_SECC,
        NUM_REZO_ENTR_ZONA,
        NUM_REZO_ENTR_REGI,
        NUM_REZO_RECI_SECC,
        NUM_REZO_RECI_ZONA,
        NUM_REZO_RECI_REGI,
        NUM_REZO_ENTR_TOTA,
        NUM_REZO_RECI_TOTA,
        FEC_PROC,
        ACT_FINA,
        NUM_REZO_ENTR_TERR,
        NUM_REZO_RECI_TERR
   INTO TMPROW --ACTUAL
 FROM VEN_GTT_FUENT_VENTA_REAL VFVR
  WHERE VFVR.COD_TERR = ROWCONSULTORA.COD_TERR;
 -- AND VFVR.IND_ACTU = 1;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 lnFlagProceso := FALSE;
 END;

      IF lnFlagProceso THEN
        --SI NO HAY TERRITORIO NO SE HACE NADA?

 tmpRowAnt:=NULL;
 /* Incrementando el contador de ingresos */
        IF rowConsultora.COD_ESTA_CLIE = '02' OR
           rowConsultora.COD_ESTA_CLIE = '08' THEN
 tmpRow.NUM_INGR := tmpRow.NUM_INGR + 1;
 END IF;

 /* Incrementando el contador de reingresos */
 IF rowConsultora.COD_ESTA_CLIE = '06' THEN
 tmpRow.NUM_REIN := tmpRow.NUM_REIN + 1;
 END IF;

 /* Incrementando el contador de egresos e inicializando el indicador */
 lsIndicadodorEgreso:='0';
 IF rowConsultora.COD_ESTA_CLIE = '05' THEN
 lnHayEgreso:=0;
 BEGIN
            SELECT COUNT(1)
              INTO lnHayEgreso
 FROM MAE_CLIEN_HISTO_ESTAT A
 WHERE A.CLIE_OID_CLIE = rowConsultora.OID_CLIE
 AND A.PERD_OID_PERI= lnIdPeriodo
 AND A.ESTA_OID_ESTA_CLIE = 5;
 EXCEPTION
 WHEN OTHERS THEN
 lnHayEgreso:=0;
 END;

 IF lnHayEgreso > 0 THEN
 tmpRow.NUM_EGRE := tmpRow.NUM_EGRE + 1;
 lsIndicadodorEgreso:='1';
 END IF;

END IF;

 /* Verificando si es posible egreso o constante */
        IF (rowConsultora.COD_ESTA_CLIE = '03' OR
           rowConsultora.COD_ESTA_CLIE = '04' OR lsIndicadodorEgreso = '1') THEN

 --DBMS_OUTPUT.PUT_LINE('cod consultora '|| rowConsultora.COD_CLIE || 'rowConsultora.COD_TERR '||rowConsultora.COD_TERR );

 /* Obtenemos los datos del territorio donde estuvo la consultora el periodo anterior */
 BEGIN
 SELECT E.OID_TERR_ADMI,
        F.OID_TERR,
        F.COD_TERR,
        G.OID_SECC,
        G.COD_SECC,
        H.OID_ZONA,
        H.COD_ZONA,
        I.OID_REGI,
        I.COD_REGI
   INTO TMPTERRITORIO
 FROM MAE_CLIEN A,
 MAE_CLIEN_UNIDA_ADMIN D,
 ZON_TERRI_ADMIN E,
 ZON_TERRI F,
 ZON_SECCI G,
 ZON_ZONA H,
 ZON_REGIO I
  WHERE A.OID_CLIE = ROWCONSULTORA.OID_CLIE
 AND A.OID_CLIE = D.CLIE_OID_CLIE
    AND (LNIDPERIODOANTERIOR >= D.PERD_OID_PERI_INI)
    AND (LNIDPERIODOANTERIOR <= D.PERD_OID_PERI_FIN OR
        D.PERD_OID_PERI_FIN IS NULL)
 AND D.ZTAD_OID_TERR_ADMI = E.OID_TERR_ADMI
 AND E.TERR_OID_TERR = F.OID_TERR
 AND E.ZSCC_OID_SECC = G.OID_SECC
 AND G.ZZON_OID_ZONA = H.OID_ZONA
 AND H.ZORG_OID_REGI = I.OID_REGI;
 EXCEPTION
 WHEN OTHERS THEN
 tmpTerritorio:=NULL;
 END;
 --Verificacion si la conusltora ha sido rezonificada
 /* Rezonificadas recibidas */
 --solo si territoriow son distintos
 -- DBMS_OUTPUT.PUT_LINE('tmpTerritorio.COD_TERR '||tmpTerritorio.COD_TERR);
          IF (tmpTerritorio.COD_TERR IS NOT NULL AND
             tmpTerritorio.COD_TERR <> rowConsultora.COD_TERR) THEN

            hayRegion := FALSE;
            hayZona   := FALSE;
 IF tmpTerritorio.COD_REGI != rowConsultora.COD_REGI THEN
 tmpRow.NUM_REZO_RECI_REGI := tmpRow.NUM_REZO_RECI_REGI + 1;
 tmpRow.NUM_REZO_RECI_ZONA := tmpRow.NUM_REZO_RECI_ZONA + 1;
 tmpRow.NUM_REZO_RECI_SECC := tmpRow.NUM_REZO_RECI_SECC + 1;
 tmpRow.NUM_REZO_RECI_TOTA := tmpRow.NUM_REZO_RECI_TOTA + 1;
 hayRegion:=True;
 END IF;

 IF(hayRegion =FALSE) THEN
 IF tmpTerritorio.COD_ZONA != rowConsultora.COD_ZONA THEN
 tmpRow.NUM_REZO_RECI_ZONA := tmpRow.NUM_REZO_RECI_ZONA + 1;
 tmpRow.NUM_REZO_RECI_SECC := tmpRow.NUM_REZO_RECI_SECC + 1;
 tmpRow.NUM_REZO_RECI_TOTA := tmpRow.NUM_REZO_RECI_TOTA + 1;
 hayZona:=TRUE;
 END IF;

 IF(hayZona=FALSE) THEN
 IF (tmpTerritorio.COD_SECC != rowConsultora.COD_SECC) THEN
 tmpRow.NUM_REZO_RECI_SECC := tmpRow.NUM_REZO_RECI_SECC + 1;
 tmpRow.NUM_REZO_RECI_TOTA := tmpRow.NUM_REZO_RECI_TOTA + 1;
 ELSE
 tmpRow.NUM_REZO_RECI_TERR := tmpRow.NUM_REZO_RECI_TERR + 1;
 tmpRow.NUM_REZO_RECI_TOTA := tmpRow.NUM_REZO_RECI_TOTA + 1;
 END IF;
 END IF;

 END IF; --FIN DE NO HAY REGIONES DISTINTAS

 --ACCEDIENDO A LA TABLA TEMPORAL CON TERRITORIO ANTERIOR DE LA CONSULTORA

 lnFlagProcesoAnterior := TRUE;
 BEGIN
 SELECT COD_PAIS,
        COD_TERR,
        OID_TERR,
        COD_REGI,
        OID_REGI,
        COD_ZONA,
        OID_ZONA,
        COD_SECC,
        OID_SECC,
        OID_PERI,
        IND_ACTU,
        INT_REZO,
        ACT_INIC,
        NUM_INGR,
        NUM_REIN,
        NUM_EGRE,
        NUM_REZO_ENTR_SECC,
        NUM_REZO_ENTR_ZONA,
        NUM_REZO_ENTR_REGI,
        NUM_REZO_RECI_SECC,
        NUM_REZO_RECI_ZONA,
        NUM_REZO_RECI_REGI,
        NUM_REZO_ENTR_TOTA,
        NUM_REZO_RECI_TOTA,
        FEC_PROC,
        ACT_FINA,
        NUM_REZO_ENTR_TERR,
        NUM_REZO_RECI_TERR
   INTO TMPROWANT --ANTERIOR
 FROM VEN_GTT_FUENT_VENTA_REAL VFVR
  WHERE VFVR.COD_TERR = TMPTERRITORIO.COD_TERR;
 -- AND VFVR.IND_ACTU = 0;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 lnFlagProcesoAnterior := FALSE;
 tmpRowAnt:=NULL;
 END;

 IF(lnFlagProcesoAnterior = TRUE) THEN

 /**** */
 IF tmpTerritorio.COD_REGI != rowConsultora.COD_REGI THEN
 tmpRowAnt.NUM_REZO_ENTR_REGI := tmpRowAnt.NUM_REZO_ENTR_REGI + 1;
 tmpRowAnt.NUM_REZO_ENTR_ZONA := tmpRowAnt.NUM_REZO_ENTR_ZONA + 1;
 tmpRowAnt.NUM_REZO_ENTR_SECC := tmpRowAnt.NUM_REZO_ENTR_SECC + 1;
 /* Rezonificadas entregadas */
 tmpRowAnt.NUM_REZO_ENTR_TOTA := tmpRowAnt.NUM_REZO_ENTR_TOTA + 1;
 ELSE
 IF tmpTerritorio.COD_ZONA != rowConsultora.COD_ZONA THEN
 tmpRowAnt.NUM_REZO_ENTR_ZONA := tmpRowAnt.NUM_REZO_ENTR_ZONA + 1;
 tmpRowAnt.NUM_REZO_ENTR_SECC := tmpRowAnt.NUM_REZO_ENTR_SECC + 1;
 /* Rezonificadas entregadas */
 tmpRowAnt.NUM_REZO_ENTR_TOTA := tmpRowAnt.NUM_REZO_ENTR_TOTA + 1;
 ELSE
 IF tmpTerritorio.COD_SECC != rowConsultora.COD_SECC THEN
 tmpRowAnt.NUM_REZO_ENTR_SECC := tmpRowAnt.NUM_REZO_ENTR_SECC + 1;
 /* Rezonificadas entregadas */
 tmpRowAnt.NUM_REZO_ENTR_TOTA := tmpRowAnt.NUM_REZO_ENTR_TOTA + 1;
 ELSE
 tmpRowAnt.NUM_REZO_ENTR_TERR := tmpRowAnt.NUM_REZO_ENTR_TERR + 1;
 tmpRowAnt.NUM_REZO_ENTR_TOTA := tmpRowAnt.NUM_REZO_ENTR_TOTA + 1;
 END IF;
 END IF;
 END IF;--END DE REGIONES DISTINTAS

 --aCTUALIZAMOS LAS VARIABLES PARA TERRITORIO ANTERIOR

 UPDATE VEN_GTT_FUENT_VENTA_REAL VFVR
    SET VFVR.NUM_INGR           = TMPROWANT.NUM_INGR,
        VFVR.NUM_REIN           = TMPROWANT.NUM_REIN,
        VFVR.NUM_EGRE           = TMPROWANT.NUM_EGRE,
        VFVR.NUM_REZO_RECI_TOTA = TMPROWANT.NUM_REZO_RECI_TOTA,
        VFVR.NUM_REZO_RECI_REGI = TMPROWANT.NUM_REZO_RECI_REGI,
        VFVR.NUM_REZO_RECI_ZONA = TMPROWANT.NUM_REZO_RECI_ZONA,
        VFVR.NUM_REZO_RECI_SECC = TMPROWANT.NUM_REZO_RECI_SECC,
        VFVR.NUM_REZO_ENTR_TOTA = TMPROWANT.NUM_REZO_ENTR_TOTA,
        VFVR.NUM_REZO_ENTR_REGI = TMPROWANT.NUM_REZO_ENTR_REGI,
        VFVR.NUM_REZO_ENTR_ZONA = TMPROWANT.NUM_REZO_ENTR_ZONA,
        VFVR.NUM_REZO_ENTR_SECC = TMPROWANT.NUM_REZO_ENTR_SECC,
        VFVR.NUM_REZO_ENTR_TERR = TMPROWANT.NUM_REZO_ENTR_TERR,
        VFVR.NUM_REZO_RECI_TERR = TMPROWANT.NUM_REZO_RECI_TERR,
        VFVR.ACT_FINA          =
                     (VFVR.ACT_INIC + TMPROWANT.NUM_INGR +
                     TMPROWANT.NUM_REIN - TMPROWANT.NUM_EGRE +
                     TMPROWANT.NUM_REZO_RECI_TOTA -
        TMPROWANT.NUM_REZO_ENTR_TOTA)
  WHERE VFVR.COD_TERR = TMPROWANT.COD_TERR;
 --AND VFVR.IND_ACTU = 0;

 END IF;--END FLAGPROCESOANTERIOR

 END IF;

 END IF; --POSIBLE EGRESO O CONSTANTE

 --aCTUALIZAMOS LAS VARIABLES PARA TERRITORIO ACTUAL
 UPDATE VEN_GTT_FUENT_VENTA_REAL VFVR
    SET VFVR.NUM_INGR           = TMPROW.NUM_INGR,
        VFVR.NUM_REIN           = TMPROW.NUM_REIN,
        VFVR.NUM_EGRE           = TMPROW.NUM_EGRE,
        VFVR.NUM_REZO_RECI_TOTA = TMPROW.NUM_REZO_RECI_TOTA,
        VFVR.NUM_REZO_RECI_REGI = TMPROW.NUM_REZO_RECI_REGI,
        VFVR.NUM_REZO_RECI_ZONA = TMPROW.NUM_REZO_RECI_ZONA,
        VFVR.NUM_REZO_RECI_SECC = TMPROW.NUM_REZO_RECI_SECC,
        VFVR.NUM_REZO_ENTR_TOTA = TMPROW.NUM_REZO_ENTR_TOTA,
        VFVR.NUM_REZO_ENTR_REGI = TMPROW.NUM_REZO_ENTR_REGI,
        VFVR.NUM_REZO_ENTR_ZONA = TMPROW.NUM_REZO_ENTR_ZONA,
        VFVR.NUM_REZO_ENTR_SECC = TMPROW.NUM_REZO_ENTR_SECC,
        VFVR.NUM_REZO_ENTR_TERR = TMPROW.NUM_REZO_ENTR_TERR,
        VFVR.NUM_REZO_RECI_TERR = TMPROW.NUM_REZO_RECI_TERR,
        VFVR.ACT_FINA          =
        (VFVR.ACT_INIC + TMPROW.NUM_INGR + TMPROW.NUM_REIN -
        TMPROW.NUM_EGRE + TMPROW.NUM_REZO_RECI_TOTA -
        TMPROW.NUM_REZO_ENTR_TOTA)
  WHERE VFVR.COD_TERR = ROWCONSULTORA.COD_TERR;
 --AND VFVR.IND_ACTU = 1;

 END IF; --FIN lnFlagProceso

 END LOOP;
 END IF;
 EXIT WHEN c_consultoras%NOTFOUND;
 END LOOP;
 CLOSE c_consultoras;

 /* Se elimina informacion anterior de la tabla INT_FUENT_VENTAS_REAL */
 DELETE FROM INT_FUENT_VENTAS_REAL WHERE PERD_OID_PERI = lnIdPeriodo;

 /* Se realiza el insert en la tabla INT_FUENT_VENTAS_REAL */
 INSERT INTO INT_FUENT_VENTAS_REAL(
 OID_FUEN_VENT_REAL,
 VAL_CENT,
 VAL_ANIO_COME,
 NUM_ACTI_INIC,
 NUM_ACTI_FINA,
 NUM_INGR,
 NUM_REIN,
 NUM_EGRE,
 NUM_REZO_ENTR_SEC,
 NUM_REZO_ENTR_ZON,
 NUM_REZO_ENTR_REG,
 NUM_REZO_RECI_SEC,
 NUM_REZO_RECI_ZON,
 NUM_REZO_RECI_REG,
 NUM_REZO_RECI,
 NUM_REZO_ENTR,
 ZZON_OID_ZONA,
 PAIS_OID_PAIS,
 PERD_OID_PERI,
 ZORG_OID_REGI,
 TERR_OID_TERR,
 NUM_REZO_ENTR_TERR,
 NUM_REZO_RECI_TERR
 )
 SELECT
 INT_FVRL_SEQ.NEXTVAL,
 NULL,
 lnAnhoProceso,
 T.ACT_INIC,
 (T.ACT_INIC + T.NUM_INGR + T.NUM_REIN - T.NUM_EGRE + T.NUM_REZO_RECI_TOTA - T.NUM_REZO_ENTR_TOTA),
 T.NUM_INGR,
 T.NUM_REIN,
 T.NUM_EGRE,
 T.NUM_REZO_ENTR_SECC,
 T.NUM_REZO_ENTR_ZONA,
 T.NUM_REZO_ENTR_REGI,
 T.NUM_REZO_RECI_SECC,
 T.NUM_REZO_RECI_ZONA,
 T.NUM_REZO_RECI_REGI,
 T.NUM_REZO_RECI_TOTA,
 T.NUM_REZO_ENTR_TOTA,
 T.OID_ZONA,
 lnIdPais,
 T.OID_PERI,
 T.OID_REGI,
 T.OID_TERR,
 T.NUM_REZO_ENTR_TERR,
 T.NUM_REZO_RECI_TERR
 FROM
 VEN_GTT_FUENT_VENTA_REAL T;


 --ACTUALIZANDO LAS FUENTE DE VENTAS ACUMULADAS

 VEN_PR_CALCU_FUENT_VENTA_ACUMU(
 psCodPais,
 psCodMarca,
 psCodCanal,
 psCodPeriodo);

 RETURN;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CALCU_FUENT_VENTA_REAL: '||ls_sqlerrm);
END VEN_PR_CALCU_FUENT_VENTA_REAL;


/***************************************************************************
Descripcion : Permite actualizar la fuente de venta acumulada con las unidades y monto de venta neta
 de productos de eventa estdisticabae por campaña y territorio
Fecha Creacion : 07/09/2009
Autor : Sergio Buchelli Silva
Parametros :
 psCodPais : codigo Pais
 psCodMarca : codigo Marca
 psCodCanal : codigo Canal
 psCodPeriodo : codigo Periodo
***************************************************************************/
PROCEDURE VEN_PR_CALCU_FUENT_VENTA_ACUMU(
 psCodPais VARCHAR2,
 psCodMarca VARCHAR2,
 psCodCanal VARCHAR2,
 psCodPeriodo VARCHAR2)
IS
 lnIdPais seg_pais.oid_pais%TYPE;
 lnIdCanal seg_canal.oid_cana%TYPE;
 lnIdMarca seg_marca.oid_marc%TYPE;
 lnIdPeriodo seg_perio_corpo.oid_peri%TYPE;
 lnIdSociedad seg_socie.oid_soci%TYPE;
 lnIdAlmacen bel_almac.oid_alma%TYPE;


 TYPE tRegAcum IS RECORD (
 ZORG_OID_REGI INT_FUENT_VENTA_REAL_VACUM.ZORG_OID_REGI%TYPE,
	 ZZON_OID_ZONA INT_FUENT_VENTA_REAL_VACUM.ZZON_OID_ZONA%TYPE,
 TERR_OID_TERR INT_FUENT_VENTA_REAL_VACUM.TERR_OID_TERR%TYPE,
 TSPA_OID_TIPO_SOLI_PAIS INT_FUENT_VENTA_REAL_VACUM.TSPA_OID_TIPO_SOLI_PAIS%TYPE,
	 TICL_OID_TIPO_CLIE INT_FUENT_VENTA_REAL_VACUM.TICL_OID_TIPO_CLIE%TYPE,
 SOCI_OID_SOCI INT_FUENT_VENTA_REAL_VACUM.SOCI_OID_SOCI%TYPE,
 ALMC_OID_ALMA INT_FUENT_VENTA_REAL_VACUM.ALMC_OID_ALMA%TYPE,
	 NUM_ORDE INT_FUENT_VENTA_REAL_VACUM.NUM_ORDE%TYPE,
	 NUM_PEDI INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE,
	 NUM_CLIE INT_FUENT_VENTA_REAL_VACUM.NUM_CLIE%TYPE,
 NUM_UNID_VEND INT_FUENT_VENTA_REAL_VACUM.NUM_UNID_VEND%TYPE,
	 IMP_VENT_NETA_ESTA INT_FUENT_VENTA_REAL_VACUM.IMP_VENT_NETA_ESTA%TYPE
 );
 TYPE TABLA_VACUM IS TABLE OF tRegAcum;
 TablaVacum TABLA_VACUM;

 RegAcum tRegAcum;
 RegVentAcum INT_FUENT_VENTA_REAL_VACUM%ROWTYPE;

 lnOidVacum NUMBER;

 CURSOR cVentaAcumulada(pnIdPais NUMBER,
 pnIdSociedad NUMBER,
 pnIdAlmacen NUMBER,
 pnIdCampana NUMBER,
 pnIdMarca NUMBER,
 pnIdCanal NUMBER)
 IS
    select
    zorg_oid_regi,
    zzon_oid_zona,
    terr_oid_terr,
    tspa_oid_tipo_soli_pais,
    ticl_oid_tipo_clie,
    oid_soci,
    oid_alma,
    sum(num_orde) num_orde,
    sum(num_pedi) num_pedi,
    sum(num_clie) num_clie,
    sum(num_unid_vend) num_unid_vend,
    sum(imp_vent_neta_esta) imp_vent_neta_esta
    from TMP_FUENT_VENTA_REAL_VACUM
    GROUP BY zorg_oid_regi,
        zzon_oid_zona,
        terr_oid_terr,
        tspa_oid_tipo_soli_pais,
        ticl_oid_tipo_clie,
        oid_soci,
        oid_alma
    ORDER BY
        zorg_oid_regi,
        zzon_oid_zona,
        terr_oid_terr,
        tspa_oid_tipo_soli_pais,
        ticl_oid_tipo_clie,
        oid_soci,
        oid_alma;

 lsIndiParallel VARCHAR2(1):='0';
BEGIN
 /* obteniendos ids */
 lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 lnIdCanal := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
 lnIdMarca := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);

 /* obteniendo el oid del periodo actual con la funcion */
 lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodPeriodo,lnIdMarca,lnIdCanal);

 /* Borrando tabla temporal */
 EXECUTE IMMEDIATE 'TRUNCATE TABLE VEN_GTT_SOLIC_CABEC';

 BEGIN
    SELECT VAL_PARA INTO lsIndiParallel
    FROM BAS_PARAM_PAIS
    WHERE COD_PAIS = psCodPais
     AND COD_SIST='IMP'
     AND COD_PARA='015'
     AND IND_ACTI='1';
 EXCEPTION 
    WHEN OTHERS THEN
      lsIndiParallel:='0';
 END;    
 /* Cargando Informacion para la cabecera */
 
 IF(lsIndiParallel = '0') THEN
 INSERT   INTO VEN_GTT_SOLIC_CABEC (
 OID_SOLI_CABE, FEC_PROG_FACT,
 FEC_FACT, NUM_CLIEN,
 VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
 MONE_OID_MONE, TIDS_OID_TIPO_DESP,
 ALMC_OID_ALMA, MODU_OID_MODU,
 TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
 PERD_OID_PERI, SOCA_OID_SOLI_CABE,
 CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
 CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
 CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
 SOCI_OID_SOCI, SBAC_OID_SBAC,
 TERR_OID_TERR, ZZON_OID_ZONA,
 IND_ESTA, IND_IMPR,
 IND_EXEN_FLET, VAL_NUME_SOLI,
 VAL_USUA, VAL_TASA_IMPU,
 FEC_CRON, IND_PERM_UNIO_SOL,
 IND_GENE_CC, IND_APLI_MANU,
 VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
 NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
 FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
 VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
 VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
 VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
 VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
 VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
 VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
 VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
 VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
 VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
 VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
 VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
 VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
 VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
 VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
 VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
 VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
 VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
 VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
 VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
 VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
 VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
 NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
 IND_OC, IND_PEDI_PRUE,
 IND_TS_NO_CONSO, VAL_GLOS_OBSE,
 VAL_OBSE_REVI, NUM_PREM,
 VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
 PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
 VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
 ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
 GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
 ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
 FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
 ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
 ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
 OPER_OID_OPER, PROC_OID_PROC,
 SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
 CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
 NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
 IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
 )
 SELECT  OID_SOLI_CABE,
        FEC_PROG_FACT,
        FEC_FACT,
        NUM_CLIEN,
        VAL_GRUP_REVE,
        TSPA_OID_TIPO_SOLI_PAIS,
        MONE_OID_MONE,
        TIDS_OID_TIPO_DESP,
        ALMC_OID_ALMA,
        MODU_OID_MODU,
        TICL_OID_TIPO_CLIE,
        TAIM_OID_TASA_IMPU,
        PERD_OID_PERI,
        SOCA_OID_SOLI_CABE,
        CLIE_OID_CLIE,
        CLIE_OID_CLIE_RECE_FACT,
        CLIE_OID_CLIE_PAGA,
        CLIE_OID_CLIE_DEST,
        CLDI_OID_CLIE_DIRE,
        TDOC_OID_TIPO_DOCU,
        SOCI_OID_SOCI,
        SBAC_OID_SBAC,
        TERR_OID_TERR,
        ZZON_OID_ZONA,
        IND_ESTA,
        IND_IMPR,
        IND_EXEN_FLET,
        VAL_NUME_SOLI,
        VAL_USUA,
        VAL_TASA_IMPU,
        FEC_CRON,
        IND_PERM_UNIO_SOL,
        IND_GENE_CC,
        IND_APLI_MANU,
        VAL_TIPO_CAMB,
        NUM_DOCU_CONT_INTE,
        NUM_DOCU_ORIG,
        VAL_LOTE_REPO_FALT,
        FEC_REPO_FALT,
        VAL_BASE_FLET_LOCA,
        VAL_IMPO_FLET_LOCA,
        VAL_IMPO_FLET_TOTA_LOCA,
        VAL_IMPO_FLET_SIN_IMPU_TOTA,
        VAL_RECA_FLET_LOCA,
        VAL_OTRO_RECA_LOCA,
        VAL_TOTA_PAGA_LOCA,
        VAL_PREC_CATA_TOTA_LOCA,
        VAL_PREC_CATA_SIN_IMPU_TOTA,
        VAL_PREC_FACT_TOTA_LOCA,
        VAL_IMPO_IMPU_TOTA_LOCA,
        VAL_IMPO_DESC_1_TOTA_LOCA,
        VAL_IMPO_DESC_1_TOTA_DOCU,
        VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
        VAL_IMPO_DESC_3_TOTA_DOCU,
        VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
        VAL_IMPO_DESC_TOTA_LOCA,
        VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
        VAL_IMPO_REDO_LOCA,
        VAL_BASE_FLET_DOCU,
        VAL_IMPO_FLET_DOCU,
        VAL_IMPO_DESC_TOTA_DOCU,
        VAL_IMPO_FLET_SIN_IMPU_DOCU,
        VAL_RECA_FLET_DOCU,
        VAL_OTRO_RECA_DOCU,
        VAL_TOTA_FLET_DOCU,
        VAL_IMPO_FLET_TOTA_DOCU,
        VAL_TOTA_FLET_LOCA,
        VAL_TOTA_PAGA_DOCU,
        VAL_PREC_CATA_TOTA_DOCU,
        VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
        VAL_PREC_CONT_TOTA_LOCA,
        VAL_PREC_CONT_SIN_IMPU_TOTA,
        VAL_PREC_CONT_SIN_IMPU_TOTA_1,
        VAL_PREC_FACT_TOTA_DOCU,
        VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
        VAL_PREC_NETO_TOTA_DOCU,
        VAL_PREC_NETO_TOTA_LOCA,
        VAL_IMPO_IMPU_TOTA_DOCU,
        VAL_IMPO_REDO_DOCU,
        VAL_IMPO_REDO_CONS_LOCA,
        VAL_IMPO_REDO_CONS_DOCU,
        VAL_UNID_DEMA_REAL_TOTA,
        NUM_UNID_POR_ATEN_TOTA,
        NUM_UNID_ATEN_TOTA,
        IND_OC,
        IND_PEDI_PRUE,
        IND_TS_NO_CONSO,
        VAL_GLOS_OBSE,
        VAL_OBSE_REVI,
        NUM_PREM,
        VAL_IMPO_DESC_3_TOTA_LOCA,
        VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
        PAIS_OID_PAIS,
        TIDO_OID_TIPO_DOCU,
        VEPO_OID_VALO_ESTR_GEOP,
        RECQ_OID_RESU_CHEQ,
        ESSO_OID_ESTA_SOLI,
        COPA_OID_PARA_GENE,
        GRPR_OID_GRUP_PROC,
        SBTI_OID_SUBT_CLIE,
        ACFI_OID_ACCE_FISI,
        TSPA_OID_TIPO_SOLI_PAIS_CONS,
        FOPA_OID_FORM_PAGO,
        CLIE_OID_CONS_ASOC,
        ESPE_OID_ESTA_PEDI,
        CLSO_OID_CLAS_SOLI,
        ZTAD_OID_TERR_ADMI,
        INRE_OID_INDI_REVI,
        OPER_OID_OPER,
        PROC_OID_PROC,
        SOCA_OID_DOCU_REFE,
        TCCL_OID_TCCL_FLET,
        CLAS_OID_CLAS_FLET,
        VAL_PUNT_EMIS,
        NUM_LOTE_FACT,
        VAL_PREC_CONT_TOTA_DOCU,
        IND_INTE_LARI_GENE,
        FEC_PROG_FACT_COMP
   FROM PED_SOLIC_CABEC A
  WHERE A.PAIS_OID_PAIS = LNIDPAIS
 --AND A.SOCI_OID_SOCI = lnIdSociedad
 --AND A.ALMC_OID_ALMA = lnIdAlmacen
    AND A.PERD_OID_PERI = LNIDPERIODO
 AND A.FEC_FACT IS NOT NULL
 AND A.IND_TS_NO_CONSO = 1
 AND A.IND_OC = 1
 AND A.IND_PEDI_PRUE = 0;

 ELSE
  
  INSERT  /*+PARALLEL(VEN_GTT_SOLIC_CABEC) */ INTO VEN_GTT_SOLIC_CABEC (
 OID_SOLI_CABE, FEC_PROG_FACT,
 FEC_FACT, NUM_CLIEN,
 VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
 MONE_OID_MONE, TIDS_OID_TIPO_DESP,
 ALMC_OID_ALMA, MODU_OID_MODU,
 TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
 PERD_OID_PERI, SOCA_OID_SOLI_CABE,
 CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
 CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
 CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
 SOCI_OID_SOCI, SBAC_OID_SBAC,
 TERR_OID_TERR, ZZON_OID_ZONA,
 IND_ESTA, IND_IMPR,
 IND_EXEN_FLET, VAL_NUME_SOLI,
 VAL_USUA, VAL_TASA_IMPU,
 FEC_CRON, IND_PERM_UNIO_SOL,
 IND_GENE_CC, IND_APLI_MANU,
 VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
 NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
 FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
 VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
 VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
 VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
 VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
 VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
 VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
 VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
 VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
 VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
 VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
 VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
 VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
 VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
 VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
 VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
 VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
 VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
 VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
 VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
 VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
 VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
 NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
 IND_OC, IND_PEDI_PRUE,
 IND_TS_NO_CONSO, VAL_GLOS_OBSE,
 VAL_OBSE_REVI, NUM_PREM,
 VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
 PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
 VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
 ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
 GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
 ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
 FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
 ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
 ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
 OPER_OID_OPER, PROC_OID_PROC,
 SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
 CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
 NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
 IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
 )
 SELECT  /*+PARALLEL(A 3) */ OID_SOLI_CABE,
        FEC_PROG_FACT,
        FEC_FACT,
        NUM_CLIEN,
        VAL_GRUP_REVE,
        TSPA_OID_TIPO_SOLI_PAIS,
        MONE_OID_MONE,
        TIDS_OID_TIPO_DESP,
        ALMC_OID_ALMA,
        MODU_OID_MODU,
        TICL_OID_TIPO_CLIE,
        TAIM_OID_TASA_IMPU,
        PERD_OID_PERI,
        SOCA_OID_SOLI_CABE,
        CLIE_OID_CLIE,
        CLIE_OID_CLIE_RECE_FACT,
        CLIE_OID_CLIE_PAGA,
        CLIE_OID_CLIE_DEST,
        CLDI_OID_CLIE_DIRE,
        TDOC_OID_TIPO_DOCU,
        SOCI_OID_SOCI,
        SBAC_OID_SBAC,
        TERR_OID_TERR,
        ZZON_OID_ZONA,
        IND_ESTA,
        IND_IMPR,
        IND_EXEN_FLET,
        VAL_NUME_SOLI,
        VAL_USUA,
        VAL_TASA_IMPU,
        FEC_CRON,
        IND_PERM_UNIO_SOL,
        IND_GENE_CC,
        IND_APLI_MANU,
        VAL_TIPO_CAMB,
        NUM_DOCU_CONT_INTE,
        NUM_DOCU_ORIG,
        VAL_LOTE_REPO_FALT,
        FEC_REPO_FALT,
        VAL_BASE_FLET_LOCA,
        VAL_IMPO_FLET_LOCA,
        VAL_IMPO_FLET_TOTA_LOCA,
        VAL_IMPO_FLET_SIN_IMPU_TOTA,
        VAL_RECA_FLET_LOCA,
        VAL_OTRO_RECA_LOCA,
        VAL_TOTA_PAGA_LOCA,
        VAL_PREC_CATA_TOTA_LOCA,
        VAL_PREC_CATA_SIN_IMPU_TOTA,
        VAL_PREC_FACT_TOTA_LOCA,
        VAL_IMPO_IMPU_TOTA_LOCA,
        VAL_IMPO_DESC_1_TOTA_LOCA,
        VAL_IMPO_DESC_1_TOTA_DOCU,
        VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
        VAL_IMPO_DESC_3_TOTA_DOCU,
        VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
        VAL_IMPO_DESC_TOTA_LOCA,
        VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
        VAL_IMPO_REDO_LOCA,
        VAL_BASE_FLET_DOCU,
        VAL_IMPO_FLET_DOCU,
        VAL_IMPO_DESC_TOTA_DOCU,
        VAL_IMPO_FLET_SIN_IMPU_DOCU,
        VAL_RECA_FLET_DOCU,
        VAL_OTRO_RECA_DOCU,
        VAL_TOTA_FLET_DOCU,
        VAL_IMPO_FLET_TOTA_DOCU,
        VAL_TOTA_FLET_LOCA,
        VAL_TOTA_PAGA_DOCU,
        VAL_PREC_CATA_TOTA_DOCU,
        VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
        VAL_PREC_CONT_TOTA_LOCA,
        VAL_PREC_CONT_SIN_IMPU_TOTA,
        VAL_PREC_CONT_SIN_IMPU_TOTA_1,
        VAL_PREC_FACT_TOTA_DOCU,
        VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
        VAL_PREC_NETO_TOTA_DOCU,
        VAL_PREC_NETO_TOTA_LOCA,
        VAL_IMPO_IMPU_TOTA_DOCU,
        VAL_IMPO_REDO_DOCU,
        VAL_IMPO_REDO_CONS_LOCA,
        VAL_IMPO_REDO_CONS_DOCU,
        VAL_UNID_DEMA_REAL_TOTA,
        NUM_UNID_POR_ATEN_TOTA,
        NUM_UNID_ATEN_TOTA,
        IND_OC,
        IND_PEDI_PRUE,
        IND_TS_NO_CONSO,
        VAL_GLOS_OBSE,
        VAL_OBSE_REVI,
        NUM_PREM,
        VAL_IMPO_DESC_3_TOTA_LOCA,
        VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
        PAIS_OID_PAIS,
        TIDO_OID_TIPO_DOCU,
        VEPO_OID_VALO_ESTR_GEOP,
        RECQ_OID_RESU_CHEQ,
        ESSO_OID_ESTA_SOLI,
        COPA_OID_PARA_GENE,
        GRPR_OID_GRUP_PROC,
        SBTI_OID_SUBT_CLIE,
        ACFI_OID_ACCE_FISI,
        TSPA_OID_TIPO_SOLI_PAIS_CONS,
        FOPA_OID_FORM_PAGO,
        CLIE_OID_CONS_ASOC,
        ESPE_OID_ESTA_PEDI,
        CLSO_OID_CLAS_SOLI,
        ZTAD_OID_TERR_ADMI,
        INRE_OID_INDI_REVI,
        OPER_OID_OPER,
        PROC_OID_PROC,
        SOCA_OID_DOCU_REFE,
        TCCL_OID_TCCL_FLET,
        CLAS_OID_CLAS_FLET,
        VAL_PUNT_EMIS,
        NUM_LOTE_FACT,
        VAL_PREC_CONT_TOTA_DOCU,
        IND_INTE_LARI_GENE,
        FEC_PROG_FACT_COMP
   FROM PED_SOLIC_CABEC A
  WHERE A.PAIS_OID_PAIS = LNIDPAIS
 --AND A.SOCI_OID_SOCI = lnIdSociedad
 --AND A.ALMC_OID_ALMA = lnIdAlmacen
    AND A.PERD_OID_PERI = LNIDPERIODO
 AND A.FEC_FACT IS NOT NULL
 AND A.IND_TS_NO_CONSO = 1
 AND A.IND_OC = 1
 AND A.IND_PEDI_PRUE = 0;  
 
 END IF;


 /* Generando informacion de los acumulados */
 --Borramos la tabla temporal donde se acumulan los datos por cliente
 EXECUTE IMMEDIATE 'TRUNCATE TABLE TMP_FUENT_VENTA_REAL_VACUM';
 EXECUTE IMMEDIATE 'TRUNCATE TABLE TMP_VENTA_REAL_VACUM_DETAL';
 -- --
 INSERT INTO TMP_VENTA_REAL_VACUM_DETAL(
  zorg_oid_regi,
  zzon_oid_zona,
  terr_oid_terr,
  tspa_oid_tipo_soli_pais,
  ticl_oid_tipo_clie,
  oid_soci,
  oid_alma,
  clie_oid_clie,
  num_unid_vend,
  num_unid_vend_otro,
  ind_esta,
  num_unid_aten,
  val_prec_cata_unit_loca,
  val_prec_neto_tota_loca,
  OID_SOLI_CABE)
 SELECT
         reg.oid_regi zorg_oid_regi, zon.oid_zona zzon_oid_zona,
         ter.oid_terr terr_oid_terr,
         soc.tspa_oid_tipo_soli_pais tspa_oid_tipo_soli_pais,
         soc.ticl_oid_tipo_clie ticl_oid_tipo_clie, soci.oid_soci,
         alm.oid_alma,
         soc.clie_oid_clie,
         pt.num_unid_vend,
         pt.num_unid_vend_otro,
         tof.ind_esta,
         pos.num_unid_aten,
         pos.val_prec_cata_unit_loca,
         pos.val_prec_neto_tota_loca,
         soc.OID_SOLI_CABE
         
    FROM ven_gtt_solic_cabec soc,
         ped_solic_posic pos,
         seg_pais pai,
         seg_socie soci,
         pre_ofert_detal ofd,
         pre_tipo_ofert tof,
         cra_perio peri,
         ped_tipo_solic ts,
         int_param_tipo_solic pt,
         bel_almac alm,
         zon_zona zon,
         zon_regio reg,
         zon_terri ter,
         ped_tipo_solic_pais tsp
   WHERE pos.soca_oid_soli_cabe = soc.oid_soli_cabe
     AND soc.tspa_oid_tipo_soli_pais = pt.tspa_oid_tipo_soli_pais
     AND (   (pt.num_unid_vend = 1 AND soc.ind_oc = 1)
          OR (pt.num_unid_vend_otro = 1)
         )
     AND pos.espo_oid_esta_posi <> 2
     AND soc.perd_oid_peri = peri.oid_peri
     AND pos.ofde_oid_deta_ofer = ofd.oid_deta_ofer
     AND ofd.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
     AND soc.pais_oid_pais = pai.oid_pais
     AND soc.soci_oid_soci = soci.oid_soci
     AND soc.almc_oid_alma = alm.oid_alma
     AND soc.zzon_oid_zona = zon.oid_zona
     AND soc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
     AND zon.zorg_oid_regi = reg.oid_regi
     AND soc.perd_oid_peri = lnIdPeriodo
     AND soc.fec_fact IS NOT NULL
     AND soc.ind_ts_no_conso = 1
     AND soc.ind_pedi_prue = 0
     AND pos.val_codi_vent IS NOT NULL
     AND soc.pais_oid_pais = zon.pais_oid_pais
     AND soc.terr_oid_terr = ter.oid_terr
     AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
     AND ts.ind_anul = 0
     AND ts.ind_devo = 0;


 -- Cargamos la taa temporal con los registros agrupados por cliente
 INSERT INTO TMP_FUENT_VENTA_REAL_VACUM(
  zorg_oid_regi,
  zzon_oid_zona,
  terr_oid_terr,
  tspa_oid_tipo_soli_pais,
  ticl_oid_tipo_clie,
  oid_soci,
  oid_alma,
  clie_oid_clie,
  num_orde,
  num_pedi,
  num_clie,
  num_unid_vend,
  imp_vent_neta_esta)
 SELECT
         zorg_oid_regi,
         zzon_oid_zona,
         terr_oid_terr,
         tspa_oid_tipo_soli_pais,
         ticl_oid_tipo_clie,
         oid_soci,
         oid_alma,
         clie_oid_clie,
         COUNT
            (DISTINCT (CASE
                  WHEN (num_unid_vend = 1 )
                     THEN (oid_soli_cabe)
               END
              )
            ) num_orde,
         COUNT
            (DISTINCT (CASE
                  WHEN (num_unid_vend = 1 )
                     THEN (clie_oid_clie)
               END
              )
            ) num_pedi,
         COUNT
            (DISTINCT (CASE
                  WHEN (num_unid_vend = 1 )
                     THEN (clie_oid_clie)
               END
              )
            ) num_clie,
         NVL
            (SUM (CASE
                     WHEN num_unid_vend = 1 OR num_unid_vend_otro = 1
                        THEN DECODE (ind_esta, 1, num_unid_aten, 0)
                     ELSE 0
                  END
                 ),
             0
            ) num_unid_vend,
         NVL
            (SUM
                (CASE
                    WHEN (    val_prec_cata_unit_loca > 0
                          AND (   num_unid_vend = 1
                               OR num_unid_vend_otro = 1
                              )
                         )
                       THEN DECODE (ind_esta,
                                    1, val_prec_neto_tota_loca,
                                    0
                                   )
                    ELSE 0
                 END
                ),
             0
            ) imp_vent_neta_esta
    FROM TMP_VENTA_REAL_VACUM_DETAL
 GROUP BY zorg_oid_regi,
         zzon_oid_zona,
         terr_oid_terr,
         tspa_oid_tipo_soli_pais,
         ticl_oid_tipo_clie,
         oid_soci,
         oid_alma,
         clie_oid_clie;
 -- --

 -- Actualizamos en la temporal los registros de los clientes que estan en mas de un territorio
 update TMP_FUENT_VENTA_REAL_VACUM u
 set u.num_pedi = 0
 where (u.clie_oid_clie, u.ZORG_OID_REGI,  u.ZZON_OID_ZONA, u.TERR_OID_TERR, U.OID_ALMA)
 in(
    with dup as (
    SELECT t.*,
        ROW_NUMBER() OVER (PARTITION BY clie_oid_clie ORDER BY num_orde ASC) AS rn
      FROM TMP_FUENT_VENTA_REAL_VACUM t
     WHERE t.clie_oid_clie IN (SELECT   clie_oid_clie
                                 FROM TMP_FUENT_VENTA_REAL_VACUM
                             GROUP BY clie_oid_clie
                               HAVING COUNT (clie_oid_clie) > 1)
                               )
    select dup.clie_oid_clie, dup.ZORG_OID_REGI,  dup.ZZON_OID_ZONA, dup.TERR_OID_TERR, DUP.OID_ALMA from dup
    where dup.rn > 1
 );
 -- --
 /* */
 
 /* Se elimina informacion anterior de la tabla INT_FUENT_VENTAS_VACUM */
 DELETE FROM INT_FUENT_VENTA_REAL_VACUM WHERE PERD_OID_PERI = lnIdPeriodo;

 OPEN cVentaAcumulada(lnIdPais, lnIdSociedad, lnIdAlmacen, lnIdPeriodo, lnIdMarca, lnIdCanal );
 LOOP
 FETCH cVentaAcumulada BULK COLLECT INTO TablaVacum LIMIT W_FILAS;
 IF TablaVacum.COUNT > 0 THEN
 FOR x IN TablaVacum.FIRST .. TablaVacum.LAST LOOP
 RegAcum := TablaVacum(x);
   lnOidVacum := INT_FVRA_SEQ.NEXTVAL;
 RegVentAcum.OID_FUEN_VENT_REAL_VACU := lnOidVacum;
 RegVentAcum.PAIS_OID_PAIS := lnIdPais;
 RegVentAcum.PERD_OID_PERI := lnIdPeriodo;
 RegVentAcum.ZORG_OID_REGI := RegAcum.ZORG_OID_REGI;
 RegVentAcum.ZZON_OID_ZONA := RegAcum.ZZON_OID_ZONA;
 RegVentAcum.TERR_OID_TERR := RegAcum.TERR_OID_TERR;
 RegVentAcum.SOCI_OID_SOCI := RegAcum.SOCI_OID_SOCI;
 RegVentAcum.ALMC_OID_ALMA := RegAcum.ALMC_OID_ALMA;
 RegVentAcum.TSPA_OID_TIPO_SOLI_PAIS := RegAcum.TSPA_OID_TIPO_SOLI_PAIS;
 RegVentAcum.TICL_OID_TIPO_CLIE := RegAcum.TICL_OID_TIPO_CLIE;
 RegVentAcum.NUM_ORDE := RegAcum.NUM_ORDE;
 RegVentAcum.NUM_PEDI := RegAcum.NUM_PEDI;
 RegVentAcum.NUM_UNID_VEND := RegAcum.NUM_UNID_VEND;
 RegVentAcum.NUM_CLIE := RegAcum.NUM_CLIE;
 RegVentAcum.IMP_VENT_NETA_ESTA := RegAcum.IMP_VENT_NETA_ESTA;
 RegVentAcum.FEC_CIER := SYSDATE;
 /* Pasandolo al table */
 INSERT INTO INT_FUENT_VENTA_REAL_VACUM
 VALUES RegVentAcum;
 END LOOP;
 END IF;
 EXIT WHEN cVentaAcumulada%NOTFOUND;
 END LOOP;
 CLOSE cVentaAcumulada;

 DBMS_STATS.GATHER_TABLE_STATS( USER, TABNAME => 'TMP_FUENT_VENTA_REAL_VACUM', CASCADE => TRUE );
 DBMS_STATS.GATHER_TABLE_STATS( USER, TABNAME => 'VEN_GTT_FUENT_VENTA_REAL', CASCADE => TRUE );
 DBMS_STATS.GATHER_TABLE_STATS( USER, TABNAME => 'VEN_GTT_SOLIC_CABEC', CASCADE => TRUE );
 DBMS_STATS.GATHER_TABLE_STATS( USER, TABNAME => 'TMP_VENTA_REAL_VACUM_DETAL', CASCADE => TRUE );

 RETURN;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CALCU_FUENT_VENTA_ACUMU: '||ls_sqlerrm);
END VEN_PR_CALCU_FUENT_VENTA_ACUMU;

END VEN_PKG_PROCE;
/
