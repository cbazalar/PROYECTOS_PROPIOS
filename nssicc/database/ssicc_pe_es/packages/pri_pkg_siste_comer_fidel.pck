CREATE OR REPLACE PACKAGE "PRI_PKG_SISTE_COMER_FIDEL" AS

/* Declaracion de variables */
v_indicadorPremioBienvenida   VARCHAR2(1) := '2';

/* Declaracion de procedures */

/**************************************************************************
Descripcion         : Carga la información del archivo XML del paquete documentario
                      generado por el sistema SiCC en la tabla IMP_PAQUE_DOCUM_SICC.
                      Se crea un registro por cada paquete documentario de una
                      consultora.
Fecha Creación      : 05/04/2006
Fecha Modificacion  : 15/10/2007
                      p_codigoPais: Codigo del pais a procesar.
                      p_codigoPeriodo: Codigo del periodo a procesar.
                      p_usuario   : Usuario ejecutor del proceso a ser usado para los
                                    datos de auditoria.
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
***************************************************************************/
PROCEDURE PRI_PR_CARGA_SICC(p_codigoPais IN VARCHAR2, p_codigoPeriodo IN VARCHAR2, p_usuario IN VARCHAR2);

/**************************************************************************
Descripcion         : Obtiene la información de los productos asociados al
                      Programa de Fidelizacion de Privilege de SiCC para
       alimentar la tabla de productos de SSiCC.
Fecha Creación      : 25/09/2007
                      p_codigoPais: Codigo del pais a procesar.
       p_usuario   : Usuario ejecutor del proceso a ser usado para los
                     datos de auditoria.
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
***************************************************************************/
PROCEDURE PRI_PR_CARGA_PRODU(p_codigoPais IN VARCHAR2, p_usuario IN VARCHAR2);

/***************************************************************************************************
Descripcion         : Realiza el Procesamiento de Premios
Fecha Creacion      : 12/12/2005
Fecha Modificacion  : 24/05/2006
Parametros Entrada  : p_codigoPais : Código del País del Usuario Logueado (ejm: PE es de PERÚ)
Parametros Salida   : p_totalPremios : Muestra la cantidad de Premios que han sido procesados
Autor               : Carlos Hurtado Ramírez - cahurtado@belcorp.biz
Versión             : Final (Beta|Final)
Cambios Importantes : Inclusión del comentario
****************************************************************************************************/
PROCEDURE PRI_PR_PROCE_PREMI(p_codigoPais IN VARCHAR2, p_totalPremios OUT VARCHAR2);


/***************************************************************************************************
Descripcion         : Realiza la Generacion de Stickers
Fecha Creacion      : 09/12/2005
Fecha Modificacion  : 24/25/2006
Parametros Entrada  : p_codigoPais : Código del País del Usuario Logueado (ejm: PE es de PERÚ)
Parametros Salida   : p_totalStickers : Muestra la cantidad de Stickers que han sido creados
Autor               : Carlos Hurtado Ramírez - cahurtado@belcorp.biz
Versión             : Final (Beta|Final)
Cambios Importantes : Inclusión del comentario
****************************************************************************************************/
PROCEDURE PRI_PR_GENER_STICK(p_codigoPais IN VARCHAR2, p_totalStickers OUT VARCHAR2);


/***************************************************************************************************
Descripcion         : Realiza el Cierre de los Procesos Diarios del SCdF
Fecha Creacion      : 18/05/2006
Fecha Modificacion  : 24/05/2006
Parametros Entrada  : p_codigoPais : Código del País del Usuario Logueado (ejm: PE es de PERÚ)
Autor               : Carlos Hurtado Ramírez - cahurtado@belcorp.biz
Versión             : Final (Beta|Final)
Cambios Importantes : Inclusión del comentario.
****************************************************************************************************/
PROCEDURE PRI_PR_CIERR_PROCE(p_codigoPais IN VARCHAR2);


/***************************************************************************************************
Descripcion         : Realiza la actualizacion de la Tabla PRI_PEDID referente a los campos de
                      Facturacion NUM_FACT (numero de factura) FEC_FACT (fecha de facturacion)
Fecha Creacion      : 02/01/2006
Fecha Modificacion  : 24/05/2006
Parametros Entrada  : p_codigoPais : Código del País del Usuario Logueado (ejm: PE es de PERÚ)
Autor               : Carlos Hurtado Ramírez - cahurtado@belcorp.biz
Versión             : Final (Alfa|Final)
Cambios Importantes : Inclusión del comentario
****************************************************************************************************/
PROCEDURE PRI_PR_ACTUA_PEDID_FACTU(p_codigoPais IN VARCHAR2);

/******************************************************************************************
Descripcion          : PROCEDIMIENTOS DE GENERACION DE CODIGO DE CLIENTE PRIVILEGE
Autor                : Jhenifer Rosas Limaylla
Fecha Creacion       : 13/02/2008
/******************************************************************************************/

PROCEDURE PRI_PR_CODIG_CLIEN(p_codigoPais IN VARCHAR2, p_NewCodCliente OUT VARCHAR2,
                             p_MensajeError OUT VARCHAR2 );

FUNCTION PRI_FN_DIGIT_MODUL_ONCE ( p_CorrelCliente IN VARCHAR2 )RETURN VARCHAR2;

PROCEDURE PRI_PR_CAPTU_DATOS_CONSU( p_oid_clie NUMBER,
                                 v_cod_esta_clie OUT VARCHAR2,
                                 v_num_docu_iden OUT VARCHAR2,
                                 v_val_text_comu OUT VARCHAR2,
                                 v_nom_clie  OUT VARCHAR2,
                                 v_cod_clie  OUT VARCHAR2,
                                 v_cod_zona  OUT VARCHAR2,
                                 v_cod_regi  OUT VARCHAR2,
                                 v_cod_subg_vent  OUT VARCHAR2);

PROCEDURE PRI_PR_CARGA_EMAIL_CONSU;
/***************************************************************************************************
Descripcion         : Generar las solicitudes de atención de premios Privilege
                      Realiza la insercion en las tablas PED_SOLIC_CABEC y  PED_SOLIC_POSIC
Fecha Creacion      : 07/06/2010
Autor               : Dennys Oliva Iriarte
****************************************************************************************************/
PROCEDURE PRI_PR_GENER_SOLIC_PREMI_PRIVI(p_codigoPais      IN VARCHAR2,
		  				                           p_codigoPeriodo   IN VARCHAR2,
                            						 p_tipoSolicitud   IN VARCHAR2,
                            						 p_subAscceso      IN VARCHAR2,
                            						 p_accesoFisico    IN VARCHAR2,
                            						 p_tipoFlete       IN VARCHAR2,
                            						 p_indicadorPremio IN VARCHAR2,
                            						 p_tipoPosicion    IN VARCHAR2,
                            						 p_formaPago       IN VARCHAR2,
                            						 p_numeroClientes  IN VARCHAR2,
                            						 p_fechaProceso    IN VARCHAR2,
                            						 p_codigoUsuario   IN VARCHAR2);

END;
/

CREATE OR REPLACE PACKAGE BODY "PRI_PKG_SISTE_COMER_FIDEL" AS

PROCEDURE PRI_PR_CARGA_SICC(p_codigoPais IN VARCHAR2, p_codigoPeriodo IN VARCHAR2, p_usuario IN VARCHAR2) IS

l_periodo   VARCHAR2(6);
l_periodoAnterior VARCHAR2(6);
l_statusSinPedido VARCHAR2(1);
l_nombreCompleto   VARCHAR2(100);
l_statusPedido  VARCHAR2(1);
l_longCodigoCliente NUMBER;
l_user VARCHAR2(50);
cur       INTEGER;
sqlc     VARCHAR2(100);
rowp      INTEGER;
/*Variables de salida de PRI_CAPTU_DATOS_CONSU*/
V_COD_ESTA_CLIE VARCHAR2(200);
V_NUM_DOCU_IDEN VARCHAR2(200);
V_VAL_TEXT_COMU VARCHAR2(200);
V_NOM_CLIE VARCHAR2(200);
V_COD_CLIE VARCHAR2(200);
V_COD_ZONA VARCHAR2(200);
V_COD_REGI VARCHAR2(200);
V_COD_SUBG_VENT VARCHAR2(200);
flag VARCHAR2(200);

searchStr  VARCHAR2(100);
replaceStr VARCHAR2(100);
cod_consultora VARCHAR2(200);
oid_cliente NUMBER;
codigo_privilege VARCHAR2(10);
direccion_consultora VARCHAR2(800);
fecha_naci DATE;
p_NewCodCliente VARCHAR2(10);
p_MensajeError VARCHAR2(100);
indicador VARCHAR2(1);

BEGIN

/* Optimizado: En esta seccion dejamos vacias las tablas, es equivalente a un
 * delete from nombre_tabla
 */

-- Deshabilitamos los constraints para el truncamiento

EXECUTE IMMEDIATE 'ALTER TABLE PRI_DETAL_PEDID DISABLE CONSTRAINT PRI_DEPE_PEDI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_ZONA DISABLE CONSTRAINT PRI_ZONA_REGI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_REGIO DISABLE CONSTRAINT PRI_REGI_SUVE_FK';

-- Truncamos las tablas

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CAMPA_PROCE';

--EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_PRODU';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_DETAL_PEDID';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_PEDID';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_MATRI';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CONSU_INSCR';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_STOCK_PRODU';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_ZONA';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_REGIO';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_SUBGE_VENTA';

--EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PRIVI';

-- Habilitamos los constraints para el truncamiento
EXECUTE IMMEDIATE 'ALTER TABLE PRI_DETAL_PEDID ENABLE CONSTRAINT PRI_DEPE_PEDI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_ZONA ENABLE CONSTRAINT PRI_ZONA_REGI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_REGIO ENABLE CONSTRAINT PRI_REGI_SUVE_FK';

l_user := USER;

/* Obtenemos la longitud del codigo de cliente */
SELECT P.LON_CODI_CLIE
INTO l_longCodigoCliente
FROM BAS_PAIS P
WHERE COD_PAIS = p_codigoPais;

/*
 * PRODUCTOS
 * Cargamos todos los productos registrados para el programa de
 * fidelizacion Privilege, primero cargamos los que generan stickers
 * y luego los que son exclusivamente premios.
 */
PRI_PR_CARGA_PRODU(p_codigoPais, p_usuario);

/*
 * CAMPANYAS DE PROCESO
 * Formamos la consulta para obtener la l_periodo Actual por cada pais.
 * Ejemplo:
 *      l_periodo: 200601, p_codigoPais: PE
 */
/* Se modifico el proceso para que el codigo del periodo se reciba como
   parametro y ya no haciendo el distinct sobre los pedidos en GP4
   (CHR 02/05/2008)
INSERT INTO PRI_CAMPA_PROCE(PAIS_COD_PAIS,CAM_PROC)
SELECT   PAIS.COD_PAIS,
         CAMPANYA.COD_PERI
    FROM SEG_PAIS PAIS,
         SEG_PERIO_CORPO CAMPANYA,
         PED_TIPO_SOLIC TIPO_PEDIDO,
         PED_TIPO_SOLIC_PAIS TIPO_PEDIDO_PAIS,
         PED_SOLIC_CABEC PEDIDO,
         CRA_PERIO PERIODO
   WHERE (    (TIPO_PEDIDO.OID_TIPO_SOLI = TIPO_PEDIDO_PAIS.TSOL_OID_TIPO_SOLI)
          AND (PAIS.OID_PAIS = PEDIDO.PAIS_OID_PAIS)
          AND (TIPO_PEDIDO_PAIS.OID_TIPO_SOLI_PAIS = PEDIDO.TSPA_OID_TIPO_SOLI_PAIS)
          AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
          AND (PERIODO.OID_PERI = PEDIDO.PERD_OID_PERI)
          AND (PEDIDO.GRPR_OID_GRUP_PROC = 4) -- GP4
          AND (TIPO_PEDIDO.IND_DEVO = 0)
          AND (TIPO_PEDIDO.IND_ANUL = 0)
          AND (PEDIDO.FEC_FACT IS NULL)
          AND (PEDIDO.IND_OC = 1)
          AND (PAIS.COD_PAIS = p_codigoPais)
         )
GROUP BY PAIS.COD_PAIS, CAMPANYA.COD_PERI
ORDER BY CAMPANYA.COD_PERI ASC;
*/
INSERT INTO PRI_CAMPA_PROCE VALUES(p_codigoPais, p_codigoPeriodo);

/*
 * CONTROL DE FACTURACION
 * Actualizamos los campos del control de facturacion los cuales incluyen la l_periodo a usar
 * por defecto y la fecha de proceso.
 */
UPDATE BAS_CONTR
SET CAM_PROC = p_codigoPeriodo,
FEC_PROC = SYSDATE
WHERE PAIS_COD_PAIS = p_codigoPais;

/*
 * Iteramos sobre las l_periodos cargadas en la tabla PRI_CAMPA_PROCE
 */
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais)
LOOP
    /* Obtenemos la l_periodo y calculamos el valor de la l_periodo anterior */
    l_periodo := rrCampProc.CAM_PROC;
    l_periodoAnterior := GEN_FN_PERIO_ATRAS(rrCampProc.CAM_PROC, 1);

 /*
  * CABECERA DE PEDIDO - Pedidos a facturar (P)
  */
 INSERT INTO PRI_PEDID (
 PAIS_COD_PAIS,
 NUM_PEDI,
 CAM_PEDI,
 COD_CONS,
 NOM_CONS,
 COD_SUVE,
 COD_REGI,
 COD_ZONA,
 COD_TERR,
 STA_PEDI)
 SELECT
 PAIS.COD_PAIS PAIS_COD_PAIS,
 PEDIDO.OID_SOLI_CABE NUM_PEDI,
 CAMPANYA.COD_PERI CAM_PEDI,
 SUBSTR(CONSULTORA.COD_CLIE, -l_longCodigoCliente, 20) COD_CONS,
 TRIM(CONSULTORA.VAL_APE1)||' '||TRIM(CONSULTORA.VAL_APE2)||', '|| TRIM(CONSULTORA.VAL_NOM1)||' '||TRIM(CONSULTORA.VAL_NOM2) NOM_CONS,
 SUBGERENCIA.COD_SUBG_VENT COD_SUVE,
 REGION.COD_REGI COD_REGI,
 ZONA.COD_ZONA COD_ZONA,
 TERRITORIO.COD_TERR COD_TERR,
 'P' STA_PEDI
 FROM SEG_PAIS PAIS,
      SEG_PERIO_CORPO CAMPANYA,
      MAE_CLIEN CONSULTORA,
      PED_SOLIC_CABEC PEDIDO,
      CRA_PERIO PERIODO,
      ZON_TERRI TERRITORIO,
      ZON_ZONA ZONA,
      ZON_REGIO REGION,
      ZON_SUB_GEREN_VENTA SUBGERENCIA,
      PED_TIPO_SOLIC_PAIS TIPO_PAIS,
      PED_ESTAD_SOLIC ESTADO,
      PED_GRUPO_PROCE GRUPO_PROCESO,
      PED_TIPO_SOLIC TIPO,
      BAS_CONTR CONTROL
  WHERE (    (PAIS.OID_PAIS = PEDIDO.PAIS_OID_PAIS)
         AND (CONSULTORA.OID_CLIE = PEDIDO.CLIE_OID_CLIE)
         AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
         AND (PERIODO.OID_PERI = PEDIDO.PERD_OID_PERI)
         AND (TERRITORIO.OID_TERR = PEDIDO.TERR_OID_TERR)
         AND (ZONA.OID_ZONA = PEDIDO.ZZON_OID_ZONA)
         AND (REGION.OID_REGI = ZONA.ZORG_OID_REGI)
         AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
         AND (TIPO_PAIS.OID_TIPO_SOLI_PAIS = PEDIDO.TSPA_OID_TIPO_SOLI_PAIS)
         AND (ESTADO.OID_ESTA_SOLI = PEDIDO.ESSO_OID_ESTA_SOLI)
         AND (GRUPO_PROCESO.OID_GRUP_PROC = PEDIDO.GRPR_OID_GRUP_PROC)
         AND (TIPO.OID_TIPO_SOLI = TIPO_PAIS.TSOL_OID_TIPO_SOLI)
         AND (PEDIDO.IND_OC = 1)
         AND (PEDIDO.FEC_FACT IS NULL)
         AND (TIPO.IND_DEVO = 0)
         AND (TIPO.IND_ANUL = 0)
         AND (PAIS.COD_PAIS = p_codigoPais)
         AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
         AND (GRUPO_PROCESO.COD_GRUP_PROC = CONTROL.COD_GRUP_PROC_EVAL AND CAMPANYA.COD_PERI = l_periodo)
        );

 /*
  * CABECERA DE PEDIDO - Pedidos con bloqueo de monto minimo (M)
  */
 INSERT INTO PRI_PEDID (
 PAIS_COD_PAIS,
 NUM_PEDI,
 CAM_PEDI,
 COD_CONS,
 NOM_CONS,
 COD_SUVE,
 COD_REGI,
 COD_ZONA,
 COD_TERR,
 STA_PEDI)
 SELECT
 PAIS.COD_PAIS PAIS_COD_PAIS,
 PEDIDO.OID_SOLI_CABE NUM_PEDI,
 CAMPANYA.COD_PERI CAM_PEDI,
 SUBSTR(CONSULTORA.COD_CLIE, -l_longCodigoCliente, 20) COD_CONS,
 TRIM(CONSULTORA.VAL_APE1)||' '||TRIM(CONSULTORA.VAL_APE2)||', '|| TRIM(CONSULTORA.VAL_NOM1)||' '||TRIM(CONSULTORA.VAL_NOM2) NOM_CONS,
 SUBGERENCIA.COD_SUBG_VENT COD_SUVE,
 REGION.COD_REGI COD_REGI,
 ZONA.COD_ZONA COD_ZONA,
 TERRITORIO.COD_TERR COD_TERR,
 'M' STA_PEDI
 FROM SEG_PAIS PAIS,
      SEG_PERIO_CORPO CAMPANYA,
      MAE_CLIEN CONSULTORA,
      PED_SOLIC_CABEC PEDIDO,
      CRA_PERIO PERIODO,
      ZON_TERRI TERRITORIO,
      ZON_ZONA ZONA,
      ZON_REGIO REGION,
      ZON_SUB_GEREN_VENTA SUBGERENCIA,
      PED_TIPO_SOLIC_PAIS TIPO_PAIS,
      PED_ESTAD_SOLIC ESTADO,
      PED_GRUPO_PROCE GRUPO_PROCESO,
      PED_TIPO_SOLIC TIPO
  WHERE (    (PAIS.OID_PAIS = PEDIDO.PAIS_OID_PAIS)
         AND (CONSULTORA.OID_CLIE = PEDIDO.CLIE_OID_CLIE)
         AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
         AND (PERIODO.OID_PERI = PEDIDO.PERD_OID_PERI)
         AND (TERRITORIO.OID_TERR = PEDIDO.TERR_OID_TERR)
         AND (ZONA.OID_ZONA = PEDIDO.ZZON_OID_ZONA)
         AND (REGION.OID_REGI = ZONA.ZORG_OID_REGI)
         AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
         AND (TIPO_PAIS.OID_TIPO_SOLI_PAIS = PEDIDO.TSPA_OID_TIPO_SOLI_PAIS)
         AND (ESTADO.OID_ESTA_SOLI = PEDIDO.ESSO_OID_ESTA_SOLI)
         AND (GRUPO_PROCESO.OID_GRUP_PROC = PEDIDO.GRPR_OID_GRUP_PROC)
         AND (TIPO.OID_TIPO_SOLI = TIPO_PAIS.TSOL_OID_TIPO_SOLI)
         AND (PEDIDO.IND_OC = 1)
         AND (PEDIDO.FEC_FACT IS NULL)
         AND (TIPO.IND_DEVO = 0)
         AND (TIPO.IND_ANUL = 0)
         AND (PAIS.COD_PAIS = p_codigoPais)
         AND (GRUPO_PROCESO.COD_GRUP_PROC = 'GP1' AND ESTADO.COD_ESTA_SOLI = 'RE' AND CAMPANYA.COD_PERI = l_periodo)
         AND NOT EXISTS (SELECT P.COD_CONS
                         FROM PRI_PEDID P
                         WHERE P.COD_CONS = SUBSTR(CONSULTORA.COD_CLIE, -l_longCodigoCliente, 20)
                         AND P.CAM_PEDI = l_periodo)
        );

 /*
  * CABECERA DE PEDIDO - Pedidos Anulados (A)
  */
 INSERT INTO PRI_PEDID (
 PAIS_COD_PAIS,
 NUM_PEDI,
 CAM_PEDI,
 COD_CONS,
 NOM_CONS,
 COD_SUVE,
 COD_REGI,
 COD_ZONA,
 COD_TERR,
 FEC_FACT,
 NUM_FACT,
 STA_PEDI)
 SELECT
 PAIS.COD_PAIS PAIS_COD_PAIS,
 PEDIDO.OID_SOLI_CABE NUM_PEDI,
 CAMPANYA.COD_PERI CAM_PEDI,
 SUBSTR(CONSULTORA.COD_CLIE, -l_longCodigoCliente, 20) COD_CONS,
 TRIM(CONSULTORA.VAL_APE1)||' '||TRIM(CONSULTORA.VAL_APE2)||', '|| TRIM(CONSULTORA.VAL_NOM1)||' '||TRIM(CONSULTORA.VAL_NOM2) NOM_CONS,
 SUBGERENCIA.COD_SUBG_VENT COD_SUVE,
 REGION.COD_REGI COD_REGI,
 ZONA.COD_ZONA COD_ZONA,
 TERRITORIO.COD_TERR COD_TERR,
 FACTURA.FEC_FACT FEC_FACT,
 FACTURA.VAL_NUME_SOLI NUM_FACT,
 'A' STA_PEDI
 FROM SEG_PAIS PAIS,
      SEG_PERIO_CORPO CAMPANYA,
      MAE_CLIEN CONSULTORA,
      PED_SOLIC_CABEC PEDIDO,
      CRA_PERIO PERIODO,
      PED_SOLIC_CABEC FACTURA,
      ZON_TERRI TERRITORIO,
      ZON_ZONA ZONA,
      ZON_REGIO REGION,
      ZON_SUB_GEREN_VENTA SUBGERENCIA,
      PED_TIPO_SOLIC_PAIS TIPO_PAIS,
      PED_ESTAD_SOLIC ESTADO,
      PED_GRUPO_PROCE GRUPO_PROCESO,
      PED_TIPO_SOLIC TIPO
  WHERE (    (PAIS.OID_PAIS = PEDIDO.PAIS_OID_PAIS)
         AND (CONSULTORA.OID_CLIE = PEDIDO.CLIE_OID_CLIE)
         AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
         AND (PERIODO.OID_PERI = PEDIDO.PERD_OID_PERI)
         AND (TERRITORIO.OID_TERR = PEDIDO.TERR_OID_TERR)
         AND (ZONA.OID_ZONA = PEDIDO.ZZON_OID_ZONA)
         AND (REGION.OID_REGI = ZONA.ZORG_OID_REGI)
         AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
         AND (TIPO_PAIS.OID_TIPO_SOLI_PAIS = PEDIDO.TSPA_OID_TIPO_SOLI_PAIS)
         AND (ESTADO.OID_ESTA_SOLI = PEDIDO.ESSO_OID_ESTA_SOLI)
         AND (GRUPO_PROCESO.OID_GRUP_PROC = PEDIDO.GRPR_OID_GRUP_PROC)
         AND (TIPO.OID_TIPO_SOLI = TIPO_PAIS.TSOL_OID_TIPO_SOLI)
         AND (PEDIDO.OID_SOLI_CABE = FACTURA.SOCA_OID_SOLI_CABE(+))
         AND (PEDIDO.IND_OC = 1)
         AND (PEDIDO.FEC_FACT IS NULL)
         AND (TIPO.IND_DEVO = 0)
         AND (TIPO.IND_ANUL = 0)
         AND (PAIS.COD_PAIS = p_codigoPais)
         AND (ESTADO.COD_ESTA_SOLI = 'AN' AND CAMPANYA.COD_PERI = l_periodoAnterior)
        );
END LOOP;

/*
 * DETALLE DE PEDIDOS
 */
INSERT INTO PRI_DETAL_PEDID (
    PAIS_COD_PAIS,
    PEDI_NUM_PEDI,
    NUM_POSI,
    COD_PROD,
    VAL_UNIT,
    NUM_UNID_SOLI,
    NUM_UNID_ATEN,
    COD_UNIC_VENT)
SELECT PAIS.COD_PAIS,
       DETALLE.SOCA_OID_SOLI_CABE,
       DETALLE.COD_POSI,
       PRODUCTO.COD_SAP,
       DETALLE.VAL_PREC_CATA_UNIT_LOCA,
       DETALLE.NUM_UNID_DEMA,
       DETALLE.NUM_UNID_COMPR,
       DETALLE.VAL_CODI_VENT
  FROM PED_GRUPO_PROCE GRUPO_PROCESO,
       SEG_PERIO_CORPO CAMPANYA,
       PED_TIPO_SOLIC TIPO,
       SEG_PAIS PAIS,
       PED_TIPO_SOLIC_PAIS TIPO_PAIS,
       MAE_PRODU PRODUCTO,
       PED_SOLIC_POSIC DETALLE,
       PED_SOLIC_CABEC PEDIDO,
       ZON_ZONA ZONA,
       ZON_REGIO REGION,
       ZON_SUB_GEREN_VENTA SUBGERENCIA,
       ZON_TERRI TERRITORIO,
       CRA_PERIO PERIODO,
       MAE_CLIEN CONSULTORA,
       PED_ESTAD_SOLIC ESTADO,
       BAS_CONTR CONTROL
 WHERE (    (TIPO.OID_TIPO_SOLI = TIPO_PAIS.TSOL_OID_TIPO_SOLI)
        AND (PRODUCTO.OID_PROD = DETALLE.PROD_OID_PROD)
        AND (GRUPO_PROCESO.OID_GRUP_PROC = PEDIDO.GRPR_OID_GRUP_PROC)
        AND (PAIS.OID_PAIS = PEDIDO.PAIS_OID_PAIS)
        AND (TIPO_PAIS.OID_TIPO_SOLI_PAIS = PEDIDO.TSPA_OID_TIPO_SOLI_PAIS)
        AND (PEDIDO.OID_SOLI_CABE = DETALLE.SOCA_OID_SOLI_CABE)
        AND (ZONA.OID_ZONA = PEDIDO.ZZON_OID_ZONA)
        AND (REGION.OID_REGI = ZONA.ZORG_OID_REGI)
        AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
        AND (TERRITORIO.OID_TERR = PEDIDO.TERR_OID_TERR)
        AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
        AND (PERIODO.OID_PERI = PEDIDO.PERD_OID_PERI)
        AND (CONSULTORA.OID_CLIE = PEDIDO.CLIE_OID_CLIE)
        AND (ESTADO.OID_ESTA_SOLI = PEDIDO.ESSO_OID_ESTA_SOLI)
        AND (PAIS.COD_PAIS = p_codigoPais)
        AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
        AND (GRUPO_PROCESO.COD_GRUP_PROC = CONTROL.COD_GRUP_PROC_EVAL)
        AND (PEDIDO.IND_OC = 1)
        AND (PEDIDO.FEC_FACT IS NULL)
        AND (TIPO.IND_DEVO = 0)
        AND (TIPO.IND_ANUL = 0)
        AND (CAMPANYA.COD_PERI = p_codigoPeriodo)
       );

/*
 * STOCKS DE PRODUCTOS
 *
 * Por cada producto que haya se define su stock (cantidad actual) por Pais.
 * Ejemplo:
 *      p_codigoPais: PE, codigoProducto: 200007726, stock: 39800.
 *
 */
INSERT INTO PRI_STOCK_PRODU (
      PAIS_COD_PAIS,
      COD_PROD,
   SAL_ACTU)
SELECT   PAIS.COD_PAIS,
         PRODUCTO.COD_SAP,
         STOCK.VAL_SALD
    FROM SEG_PAIS PAIS,
         SEG_PERIO_CORPO CAMPANYA,
         BEL_ALMAC ALMACEN,
         BEL_ESTAD_MERCA ESTADO_MERCA,
         BEL_STOCK STOCK,
         CRA_PERIO PERIODO,
         PRE_MATRI_FACTU_CABEC MATRIZ,
         MAE_PRODU PRODUCTO,
         PRE_OFERT OFERTA,
         PRE_OFERT_DETAL DETALLE_OFERTA,
         BAS_CONTR CONTROL
   WHERE (    (ALMACEN.OID_ALMA = STOCK.ALMC_OID_ALMA)
          AND (ESTADO_MERCA.OID_ESTA_MERC = STOCK.ESME_OID_ESTA_MERC)
          AND (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
          AND (PERIODO.OID_PERI = MATRIZ.PERD_OID_PERI)
          AND (PAIS.OID_PAIS = PRODUCTO.PAIS_OID_PAIS)
          AND (PRODUCTO.OID_PROD = STOCK.PROD_OID_PROD)
          AND (MATRIZ.OID_CABE = OFERTA.MFCA_OID_CABE)
          AND (PRODUCTO.OID_PROD = DETALLE_OFERTA.PROD_OID_PROD)
          AND (OFERTA.OID_OFER = DETALLE_OFERTA.OFER_OID_OFER)
          AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
          AND (ALMACEN.COD_ALMA = CONTROL.COD_ALMA)
          AND (ESTADO_MERCA.COD_ESTA = 'LD')
          AND (CAMPANYA.COD_PERI = p_codigoPeriodo)
          AND (PAIS.COD_PAIS = p_codigoPais)
         )
GROUP BY PAIS.COD_PAIS, PRODUCTO.COD_SAP, STOCK.VAL_SALD
ORDER BY PRODUCTO.COD_SAP ASC;

/*
 * MATRIZ DE FACTURACION:
 * Se agrega el codigo y descripcion del catalogo para la validacion
 * en el proceso de inscripcion automatica
 *
 */
INSERT INTO PRI_MATRI (
      PAIS_COD_PAIS,
      CAM_MATR,
      COD_UNIC_VENT,
      COD_PROD,
      VAL_UNIT,
      PUN_FIJO,
      IND_ESTR,
      IND_FALT_ANUN,
      COD_CATA,
      DES_CATA)
SELECT DISTINCT PAIS.COD_PAIS,
         CAMPANYA.COD_PERI,
         DETALLE_OFERTA.VAL_CODI_VENT,
         PRODUCTO.COD_SAP,
         DETALLE_OFERTA.IMP_PREC_CATA,
         DETALLE_OFERTA.NUM_PUNT_FIJO,
   CASE WHEN VARIANTE.COD_VARI = '01' THEN 'B' -- Puntaje Fijo
        WHEN VARIANTE.COD_VARI = '02' AND DETALLE_OFERTA.IMP_PREC_CATA > 0 THEN ' ' -- Puntaje Calculado
        WHEN VARIANTE.COD_VARI = '03' THEN 'P' -- Premio
        WHEN VARIANTE.COD_VARI = '04' THEN 'Q' -- Oportunidad
        ELSE 'X' -- Sin variante Privilege
         END COD_VAR,
         (DECODE ((SELECT COUNT(1)
                   FROM PED_GESTI_STOCK STOCK
                   WHERE STOCK.OFDE_OID_DETA_OFER = DETALLE_OFERTA.OID_DETA_OFER)
                  , 0, 'N', 'S')
         ) VAL_LIMI_CTRL_VEN,
         CATALOGO.COD_CATA,
         CATALOGO.DES_CATA
    FROM SEG_PAIS PAIS,
         SEG_PERIO_CORPO CAMPANYA,
         CRA_PERIO PERIODO,
         MAE_PRODU PRODUCTO,
         -- MAE_PROGR_FIDEL PROGRAMA, -- Eliminado para considerar aquellos que no tienen variante - CHR (08/05/2008)
         PRE_MATRI_FACTU_CABEC MATRIZ,
         PRE_OFERT OFERTA,
         PRE_OFERT_DETAL DETALLE_OFERTA,
         PRE_CATAL CATALOGO,
         PRE_VARIA VARIANTE
   WHERE (    (CAMPANYA.OID_PERI = PERIODO.PERI_OID_PERI)
          AND (PAIS.OID_PAIS = PRODUCTO.PAIS_OID_PAIS)
          -- AND (PROGRAMA.OID_PROG_FIDE = PRODUCTO.PRFI_OID_PROG_FIDE) - CHR (08/05/2008)
          AND (PERIODO.OID_PERI = MATRIZ.PERD_OID_PERI)
          AND (MATRIZ.OID_CABE = OFERTA.MFCA_OID_CABE)
          AND (PRODUCTO.OID_PROD = DETALLE_OFERTA.PROD_OID_PROD)
          -- AND (PROGRAMA.OID_PROG_FIDE = DETALLE_OFERTA.PRFI_OID_PROG_FIDE) - CHR (08/05/2008)
          AND (OFERTA.OID_OFER = DETALLE_OFERTA.OFER_OID_OFER)
          -- AND (PROGRAMA.OID_PROG_FIDE = VARIANTE.PRFI_OID_PROG_FIDE) - CHR (08/05/2008)
          AND (CATALOGO.OID_CATA(+) = OFERTA.OCAT_OID_CATA) -- CHR (09/12/2008)
          AND (VARIANTE.OID_VARI(+) = DETALLE_OFERTA.VARI_OID_VARI)
          AND (DETALLE_OFERTA.VAL_CODI_VENT IS NOT NULL)
          AND (CAMPANYA.COD_PERI = p_codigoPeriodo)
          AND (PAIS.COD_PAIS = p_codigoPais)
         );


-- Actualizamos las estadisticas
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PEDID', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_DETAL_PEDID', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_MATRI', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PRODU', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_STOCK_PRODU', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CLIEN', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CUENT_CLIEN', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_MOVIM_CLIEN', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PREMI', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_TARJE', CASCADE => TRUE );
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CLIEN_RECHA', CASCADE => TRUE );


/*
 * Inscripción automática de consultoras como clientes Privilege
 *
 */
/** Consultamos el indicador LBEL  ****/
SELECT IND_LBEL INTO indicador FROM BAS_PAIS WHERE COD_PAIS = p_codigoPais ORDER BY COD_PAIS;
if (indicador = 'S') THEN

    /* CHR (11/08/2008) - FASE II - Inscripcion automatica por compra de prod. LBEL
       Obtenemos la relacion de consultoras que han pasado pedido y cuyos detalles
       tienen productos con precio catalogo mayor a cero, unidades atendidas mayores a 0
       y que pertenezcan a la marca LBEL, ya sean o no productos de tratamiento facial.
    */
    FOR consultoras IN (
                       SELECT DISTINCT PCP.COD_CONS
                        FROM PRI_MATRI PM,
                             PRI_DETAL_PEDID PDP,
                             PRI_PEDID PCP,
                             MAE_PRODU MP,
                             SEG_PAIS SP,
                             SEG_MARCA_PRODU SMP,
                             BAS_CONTR BC
                        WHERE
                              PM.PAIS_COD_PAIS = PDP.PAIS_COD_PAIS
                              AND PM.COD_UNIC_VENT = PDP.COD_UNIC_VENT
                              AND PCP.PAIS_COD_PAIS= PDP.PAIS_COD_PAIS
                              AND PCP.NUM_PEDI = PDP.PEDI_NUM_PEDI
                              AND PCP.PAIS_COD_PAIS= p_codigoPais
                              AND PCP.STA_PEDI = 'P' -- Pedidos atendidos
                              AND PCP.PAIS_COD_PAIS = SP.COD_PAIS
                              AND PM.PAIS_COD_PAIS = SP.COD_PAIS
                              AND PDP.COD_PROD = MP.COD_SAP
                              AND SP.OID_PAIS = MP.PAIS_OID_PAIS
                              AND PM.COD_PROD = MP.COD_SAP
                              AND MP.MAPR_OID_MARC_PROD = SMP.OID_MARC_PROD
                              AND SP.COD_PAIS = BC.PAIS_COD_PAIS
                              AND SMP.COD_MARC_PROD = BC.COD_MARC_PROD -- Codigo Marca LBel
                              AND PM.IND_ESTR NOT IN ('P', 'Q') -- No consideramos los premios privilege
                              AND PDP.NUM_UNID_ATEN > 0 -- Unidades Atendidas > 0
                              AND PM.VAL_UNIT > 0 -- Precio Catalogo > 0
                              AND PM.COD_CATA IS NOT NULL -- Excluimos los MAV
                              AND EXISTS (
                                  SELECT NULL
                                  FROM PRI_CATAL_INSCR PCI
                                  WHERE PM.PAIS_COD_PAIS = PCI.PAIS_COD_PAIS
                                    AND PM.COD_CATA = PCI.COD_CATA_INSC
                              )
                              AND NOT EXISTS (
                                  SELECT NULL
                                              FROM PRI_HISTO_CONSU_INSCR PHC
                                              WHERE PHC.PAIS_COD_PAIS = p_codigoPais
                                              AND PHC.COD_CONS = PCP.COD_CONS )
                        )
    LOOP
       cod_consultora := consultoras.cod_cons;
       IF (cod_consultora IS NOT NULL) THEN
          oid_cliente:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(cod_consultora);

          -- Calculamos el nuevo codigo generado de privilege
          PRI_PR_CODIG_CLIEN(p_codigoPais , p_NewCodCliente , p_MensajeError );

          -- Hallamos la fecha de nacimiento del cliente
          SELECT MCD.FEC_NACI INTO fecha_naci
          FROM MAE_CLIEN_DATOS_ADICI MCD
          WHERE MCD.CLIE_OID_CLIE = oid_cliente;

          -- Hallamos la direccion de la consultora
          -- Definimos los caracteres a filtrar y reemplazar por blancos
          searchStr  := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
          replaceStr := 'a        ';

          SELECT  SUBSTR(
                  TRIM(TRANSLATE(MD.VAL_NOMB_VIA, searchStr, replaceStr)) ||
                  TRIM(TRANSLATE(MD.NUM_PPAL, searchStr, replaceStr)) ||
                  TRIM(TRANSLATE(MD.VAL_OBSE, searchStr, replaceStr)),
                  1, 100) INTO direccion_consultora
          FROM MAE_CLIEN_DIREC MD
          WHERE MD.CLIE_OID_CLIE = oid_cliente
                AND MD.IND_DIRE_PPAL =  1
                AND MD.IND_ELIM =  0;

          -- Extraemos datos del cliente
          PRI_PR_CAPTU_DATOS_CONSU(oid_cliente,
                                    V_COD_ESTA_CLIE,
                                    V_NUM_DOCU_IDEN,
                                    V_VAL_TEXT_COMU,
                                    V_NOM_CLIE,
                                    V_COD_CLIE,
                                    V_COD_ZONA,
                                    V_COD_REGI,
                                    V_COD_SUBG_VENT
                                   );
            -- Insertamos data en la tabla temporal PRI_CONSU_INSCR
            --DBMS_OUTPUT.put_line ('Insertando...PRI_CONSU_INSCR');
            INSERT INTO PRI_CONSU_INSCR (
              COD_CONS,
              PAIS_COD_PAIS,
              COD_SUVE,
              COD_REGI,
              COD_ZONA,
              NOM_CONS,
              NUM_DOCU,
              NUM_TELE,
              STA_TRAN,
              STA_CONS,
              Cod_Clie_Priv,
              Dir_Cons,
              FEC_NACI,
              EML_CONS
              )
            VALUES(
              V_COD_CLIE,
              p_codigoPais,
              V_COD_SUBG_VENT,
              V_COD_REGI,
              V_COD_ZONA,
              V_NOM_CLIE,
              V_NUM_DOCU_IDEN,
              V_VAL_TEXT_COMU,
              'N',
              V_COD_ESTA_CLIE,
              p_NewCodCliente,
              direccion_consultora,
              fecha_naci,
              GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(oid_cliente,'ML')
              );

           -- Insertamos data en la tabla temporal PRI_CONSU_INSCR
           --DBMS_OUTPUT.put_line ('Insertando...PRI_HISTO_CONSU_INSCR');
           INSERT INTO PRI_HISTO_CONSU_INSCR (
              COD_CONS,
              PAIS_COD_PAIS,
              COD_CLIE_PRIV
              )
            VALUES(
              V_COD_CLIE,
              p_codigoPais,
              p_NewCodCliente
              );
       END IF;
    END LOOP;
END IF;

/*
 * SUBGERENCIAS DE VENTA
 *
 */
INSERT INTO PRI_SUBGE_VENTA
SELECT   PAIS.COD_PAIS,
         SUBGERENCIA.COD_SUBG_VENT,
         SUBGERENCIA.DES_SUBG_VENT,
         (DECODE (SUBGERENCIA.IND_ACTI, 1, 1, 9)) IND_ACT2
    FROM SEG_PAIS PAIS,
         SEG_CANAL CANAL,
         SEG_MARCA MARCA,
         ZON_SUB_GEREN_VENTA SUBGERENCIA,
         BAS_CONTR CONTROL
   WHERE (    (PAIS.OID_PAIS = SUBGERENCIA.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = SUBGERENCIA.CANA_OID_CANA)
          AND (MARCA.OID_MARC = SUBGERENCIA.MARC_OID_MARC)
          AND (MARCA.COD_MARC = CONTROL.COD_MARC_EBEL)
          AND (CANAL.COD_CANA = CONTROL.COD_CANA)
          AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
          AND (SUBGERENCIA.IND_ACTI = 1)
          AND (PAIS.COD_PAIS = p_codigoPais)
         )
ORDER BY SUBGERENCIA.COD_SUBG_VENT ASC;

/*
 * REGIONES
 */
INSERT INTO PRI_REGIO
SELECT   PAIS.COD_PAIS,
         SUBGERENCIA.COD_SUBG_VENT,
         REGION.COD_REGI,
         REGION.DES_REGI,
         (DECODE (REGION.IND_ACTI, 1, 1, 9)) IND_ACT2
    FROM SEG_PAIS PAIS,
         SEG_CANAL CANAL,
         SEG_MARCA MARCA,
         ZON_REGIO REGION,
         ZON_SUB_GEREN_VENTA SUBGERENCIA,
         BAS_CONTR CONTROL
   WHERE (    (PAIS.OID_PAIS = REGION.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = REGION.CANA_OID_CANA)
          AND (MARCA.OID_MARC = REGION.MARC_OID_MARC)
          AND (PAIS.OID_PAIS = SUBGERENCIA.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = SUBGERENCIA.CANA_OID_CANA)
          AND (MARCA.OID_MARC = SUBGERENCIA.MARC_OID_MARC)
          AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
          AND (CANAL.COD_CANA = CONTROL.COD_CANA)
          AND (MARCA.COD_MARC = CONTROL.COD_MARC_EBEL)
          AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
          AND (REGION.IND_ACTI = 1)
          AND (SUBGERENCIA.IND_ACTI = 1)
          AND (PAIS.COD_PAIS = p_codigoPais)
         )
ORDER BY SUBGERENCIA.COD_SUBG_VENT ASC,
     REGION.COD_REGI ASC;

/*
 * ZONAS
 */
INSERT INTO PRI_ZONA
(PAIS_COD_PAIS,
COD_SUVE,
COD_REGI,
COD_ZONA,
DES_ZONA,
EST_ZONA)
SELECT   PAIS.COD_PAIS,
         SUBGERENCIA.COD_SUBG_VENT,
         REGION.COD_REGI,
         ZONA.COD_ZONA,
         ZONA.DES_ZONA,
         (DECODE (ZONA.IND_ACTI, 1, 1, 9)) IND_ACT2
    FROM SEG_PAIS PAIS,
         SEG_CANAL CANAL,
         SEG_MARCA MARCA,
         ZON_REGIO REGION,
         ZON_SUB_GEREN_VENTA SUBGERENCIA,
         ZON_ZONA ZONA,
         BAS_CONTR CONTROL
   WHERE (    (PAIS.OID_PAIS = REGION.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = REGION.CANA_OID_CANA)
          AND (MARCA.OID_MARC = REGION.MARC_OID_MARC)
          AND (PAIS.OID_PAIS = SUBGERENCIA.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = SUBGERENCIA.CANA_OID_CANA)
          AND (MARCA.OID_MARC = SUBGERENCIA.MARC_OID_MARC)
          AND (SUBGERENCIA.OID_SUBG_VENT = REGION.ZSGV_OID_SUBG_VENT)
          AND (PAIS.OID_PAIS = ZONA.PAIS_OID_PAIS)
          AND (CANAL.OID_CANA = ZONA.CANA_OID_CANA)
          AND (MARCA.OID_MARC = ZONA.MARC_OID_MARC)
          AND (REGION.OID_REGI = ZONA.ZORG_OID_REGI)
          AND (CANAL.COD_CANA = CONTROL.COD_CANA)
          AND (MARCA.COD_MARC = CONTROL.COD_MARC_EBEL)
          AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
          AND (ZONA.IND_ACTI = 1)
          AND (REGION.IND_ACTI = 1)
          AND (SUBGERENCIA.IND_ACTI = 1)
          AND (PAIS.COD_PAIS = p_codigoPais)
         )
ORDER BY SUBGERENCIA.COD_SUBG_VENT ASC,
     REGION.COD_REGI ASC,
         ZONA.COD_ZONA ASC;

/*
 * Consultoras
 */
FOR clientes IN (SELECT OID_CLIE FROM PRI_OID_CONSU)
LOOP
  PRI_PR_CAPTU_DATOS_CONSU(clientes.OID_CLIE,
                          V_COD_ESTA_CLIE,
                          V_NUM_DOCU_IDEN,
                          V_VAL_TEXT_COMU,
                          V_NOM_CLIE,
                          V_COD_CLIE,
                          V_COD_ZONA,
                          V_COD_REGI,
                          V_COD_SUBG_VENT
  );
  IF ((V_COD_CLIE IS NOT NULL) AND
      (V_NOM_CLIE IS NOT NULL) AND
      (V_COD_SUBG_VENT IS NOT NULL) AND
      (V_COD_REGI IS NOT NULL) AND
      (V_COD_ZONA IS NOT NULL)) THEN
      SELECT COUNT(1) INTO FLAG
      FROM PRI_CONSU
      WHERE COD_CONS = V_COD_CLIE
      AND PAIS_COD_PAIS = p_codigoPais;

      IF FLAG = 0 then
        --DBMS_OUTPUT.put_line ('Insertando...');
        INSERT INTO PRI_CONSU (
        COD_CONS,
        PAIS_COD_PAIS,
        COD_SUVE,
        COD_REGI,
        COD_ZONA,
        NOM_CONS,
        NUM_DOCU,
        NUM_TELE,
        STA_TRAN,
        STA_CONS,
        EML_CONS)
        VALUES(
        V_COD_CLIE,
        p_codigoPais,
        V_COD_SUBG_VENT,
        V_COD_REGI,
        V_COD_ZONA,
        V_NOM_CLIE,
        V_NUM_DOCU_IDEN,
        V_VAL_TEXT_COMU,
        'N',
        V_COD_ESTA_CLIE,
        GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CLIENTES.OID_CLIE,'ML'));
      ELSE
        --DBMS_OUTPUT.put_line ('Actualizando...');
        UPDATE PRI_CONSU
        SET COD_SUVE = V_COD_SUBG_VENT,
            COD_REGI = V_COD_REGI,
            COD_ZONA = V_COD_ZONA,
            NOM_CONS = V_NOM_CLIE,
            NUM_DOCU = V_NUM_DOCU_IDEN,
            NUM_TELE = V_VAL_TEXT_COMU,
            STA_CONS = V_COD_ESTA_CLIE,
            STA_TRAN = 'N',
            EML_CONS = GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CLIENTES.OID_CLIE,'ML')
        WHERE COD_CONS = V_COD_CLIE
        AND PAIS_COD_PAIS=p_codigoPais;
      END IF;

  END IF;

END LOOP;

/*
 * Actualizamos la tabla que contiene todas las consultoras PRI_CONSU actualizando en caso de ser privilege
 * los campos fecha de nacimiento, codigoPrivilege, direccion cliente
 */

if (indicador = 'S') THEN
    FOR consultorasPri IN (SELECT PC.COD_CONS,
                                  PHC.COD_CLIE_PRIV
                           FROM PRI_CONSU PC,
                                PRI_HISTO_CONSU_INSCR PHC
                           WHERE
                                PC.PAIS_COD_PAIS = p_codigoPais
                                AND PHC.COD_CONS = PC.COD_CONS
                                AND PHC.PAIS_COD_PAIS = PC.PAIS_COD_PAIS)
    LOOP

      -- validamos si la consultora existe en el historico de privilege

       cod_consultora := consultorasPri.Cod_Cons;
       codigo_privilege := consultorasPri.Cod_Clie_Priv;
       -- Si el codigo Consultora es diferente a NULL entocnes se procedera a ctualizar la tabla PRI_CONSU
       IF (cod_consultora IS NOT NULL) THEN
          oid_cliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(cod_consultora);

          -- Hallamos la fecha de nacimiento del cliente
          SELECT MCD.FEC_NACI
          INTO   fecha_naci
          FROM MAE_CLIEN_DATOS_ADICI MCD
          WHERE MCD.CLIE_OID_CLIE = oid_cliente;

          -- Hallamos la direccion de la consultora
          -- Definimos los caracteres a filtrar y reemplazar por blancos
          searchStr  := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
          replaceStr := 'a        ';

          SELECT  SUBSTR(
                  TRIM(TRANSLATE(MD.VAL_NOMB_VIA, searchStr, replaceStr)) ||
                  TRIM(TRANSLATE(MD.NUM_PPAL, searchStr, replaceStr)) ||
                  TRIM(TRANSLATE(MD.VAL_OBSE, searchStr, replaceStr)),
                  1, 100) INTO direccion_consultora
          FROM MAE_CLIEN_DIREC MD
          WHERE MD.CLIE_OID_CLIE = oid_cliente
                AND MD.IND_DIRE_PPAL =  1
                AND MD.IND_ELIM = 0;

           --DBMS_OUTPUT.put_line ('Actualizando...la tabla PRI_CONSU');
           UPDATE PRI_CONSU
           SET
                PRI_CONSU.COD_CLIE_PRIV = codigo_privilege,
                PRI_CONSU.DIR_CONS = direccion_consultora,
                PRI_CONSU.FEC_NACI = fecha_naci
            WHERE
                COD_CONS = cod_consultora
                AND PAIS_COD_PAIS = p_codigoPais;

       END IF;
    END LOOP;
END IF;

-- Actualizamos las estadisticas

/*DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PEDID', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_DETAL_PEDID', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_MATRI', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PRODU', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_STOCK_PRODU', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CLIEN', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CUENT_CLIEN', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_MOVIM_CLIEN', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_PREMI', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_TARJE', CASCADE => TRUE );

DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'PRI_CLIEN_RECHA', CASCADE => TRUE );*/

END PRI_PR_CARGA_SICC;


PROCEDURE PRI_PR_CARGA_PRODU(p_codigoPais IN VARCHAR2, p_usuario IN VARCHAR2) IS

CURSOR c_insertProducto IS
SELECT
PAIS.COD_PAIS PAIS_COD_PAIS,
PRODUCTO.COD_SAP COD_PROD,
GEN_I18N_SICC_PAIS.VAL_I18N DES_PROD,
'N' STA_TRAN, -- Usamos el valor 'N' ya que es nuevo y no ha sido transferido
'S' IND_GENE_STIC,
DECODE(PRODUCTO.COD_IND_SITU, 'I', '9', '1') EST_PROD,
0 PUN_STIC, -- Puntaje por defecto para los productos nuevos
p_usuario USU_DIGI,
SYSDATE FEC_DIGI,
NULL USU_MODI,
NULL FEC_MODI
FROM
SEG_PAIS PAIS,
MAE_PRODU PRODUCTO,
MAE_PROGR_FIDEL PROGRAMA,
BAS_CONTR CONTROL,
SEG_MARCA MARCA,
SEG_MARCA_PRODU MARCA_PRODUCTO,
SEG_MARCA_CORPO_MARCA_PRODU MARCA_CORP_PROD,
GEN_I18N_SICC_PAIS,
SEG_IDIOM
WHERE ( (PAIS.OID_PAIS = PRODUCTO.PAIS_OID_PAIS)
AND (PAIS.OID_PAIS = PROGRAMA.PAIS_OID_PAIS)
AND (PROGRAMA.OID_PROG_FIDE = PRODUCTO.PRFI_OID_PROG_FIDE)
AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
AND (PROGRAMA.COD_PROG_FIDE = CONTROL.COD_PROG_FIDE)
AND (MARCA_PRODUCTO.OID_MARC_PROD = PRODUCTO.MAPR_OID_MARC_PROD)
AND (PAIS.OID_PAIS = MARCA_CORP_PROD.PAIS_OID_PAIS)
AND (MARCA.OID_MARC = MARCA_CORP_PROD.MARC_OID_MARC)
AND (MARCA_PRODUCTO.OID_MARC_PROD = MARCA_CORP_PROD.MAPR_OID_MARC_PROD)
AND (CONTROL.COD_MARC_EBEL = MARCA.COD_MARC)
AND (PAIS.COD_PAIS = p_codigoPais)
AND (PRODUCTO.IND_PROD_SERV = 0) -- Indicador de producto o servicio.  producto = 0
AND (GEN_I18N_SICC_PAIS.ATTR_ENTI = 'MAE_PRODU')
AND (SEG_IDIOM.COD_IDIO = 'ES')
AND (SEG_IDIOM.OID_IDIO = GEN_I18N_SICC_PAIS.IDIO_OID_IDIO)
AND (GEN_I18N_SICC_PAIS.VAL_OID = PRODUCTO.OID_PROD)
AND NOT EXISTS (SELECT COD_PROD
                FROM PRI_PRODU X
    WHERE X.PAIS_COD_PAIS = PAIS.COD_PAIS
    AND X.COD_PROD = PRODUCTO.COD_SAP)
);

TYPE pri_produ_tab_t IS TABLE OF PRI_PRODU%ROWTYPE INDEX BY BINARY_INTEGER;
pri_produ_tab pri_produ_tab_t;  -- Tabla 'en memoria'

CURSOR c_updateProducto IS
SELECT
PRODUCTO.COD_SAP COD_PROD,
GEN_I18N_SICC_PAIS.VAL_I18N DES_PROD,
DECODE(PRODUCTO.COD_IND_SITU, 'I', '9', '1') EST_PROD
FROM
SEG_PAIS PAIS,
MAE_PRODU PRODUCTO,
MAE_PROGR_FIDEL PROGRAMA,
BAS_CONTR CONTROL,
SEG_MARCA MARCA,
SEG_MARCA_PRODU MARCA_PRODUCTO,
SEG_MARCA_CORPO_MARCA_PRODU MARCA_CORP_PROD,
GEN_I18N_SICC_PAIS,
SEG_IDIOM,
PRI_PRODU X
WHERE ( (PAIS.OID_PAIS = PRODUCTO.PAIS_OID_PAIS)
AND (PAIS.OID_PAIS = PROGRAMA.PAIS_OID_PAIS)
AND (PROGRAMA.OID_PROG_FIDE = PRODUCTO.PRFI_OID_PROG_FIDE)
AND (PAIS.COD_PAIS = CONTROL.PAIS_COD_PAIS)
AND (PROGRAMA.COD_PROG_FIDE = CONTROL.COD_PROG_FIDE)
AND (MARCA_PRODUCTO.OID_MARC_PROD = PRODUCTO.MAPR_OID_MARC_PROD)
AND (PAIS.OID_PAIS = MARCA_CORP_PROD.PAIS_OID_PAIS)
AND (MARCA.OID_MARC = MARCA_CORP_PROD.MARC_OID_MARC)
AND (MARCA_PRODUCTO.OID_MARC_PROD = MARCA_CORP_PROD.MAPR_OID_MARC_PROD)
AND (CONTROL.COD_MARC_EBEL = MARCA.COD_MARC)
AND (PAIS.COD_PAIS = p_codigoPais)
AND (PRODUCTO.IND_PROD_SERV = 0) -- Indicador de producto o servicio.  producto = 0
AND (GEN_I18N_SICC_PAIS.ATTR_ENTI = 'MAE_PRODU')
AND (SEG_IDIOM.COD_IDIO = 'ES')
AND (SEG_IDIOM.OID_IDIO = GEN_I18N_SICC_PAIS.IDIO_OID_IDIO)
AND (GEN_I18N_SICC_PAIS.VAL_OID = PRODUCTO.OID_PROD)
AND (X.PAIS_COD_PAIS = PAIS.COD_PAIS)
AND (X.COD_PROD = PRODUCTO.COD_SAP)
AND (PRODUCTO.FEC_ULTI_ACTU > DECODE(X.FEC_MODI, NULL, X.FEC_DIGI, X.FEC_MODI))
);

TYPE t_cod_prod IS TABLE OF PRI_PRODU.COD_PROD%TYPE;
TYPE t_des_prod IS TABLE OF PRI_PRODU.DES_PROD%TYPE;
TYPE t_est_prod IS TABLE OF PRI_PRODU.EST_PROD%TYPE;

v_cod_prod t_cod_prod;
v_des_prod t_des_prod;
v_est_prod t_est_prod;

rows        NATURAL := 1000; -- Numero de filas a obtener a la vez
v_row_count NUMBER := 0;

BEGIN
  -- Insertamos los productos nuevos
  OPEN c_insertProducto;
  LOOP
    -- Obtenemos la data en memoria, 'rows' cantidad de filas a la vez
    FETCH c_insertProducto BULK COLLECT INTO pri_produ_tab LIMIT rows;
    EXIT WHEN pri_produ_tab.COUNT = 0;

    FORALL i IN pri_produ_tab.FIRST..pri_produ_tab.LAST
    INSERT INTO PRI_PRODU VALUES pri_produ_tab(i);
  END LOOP;
  CLOSE c_insertProducto;

  -- Actualizamos los productos modificados
  OPEN c_updateProducto;
  LOOP
    -- Bulk collect data into memory table - X rows at a time
    FETCH c_updateProducto BULK COLLECT INTO v_cod_prod,
                                             v_des_prod,
                                             v_est_prod LIMIT rows;

    EXIT WHEN v_row_count = c_updateProducto%ROWCOUNT;
    v_row_count := c_updateProducto%ROWCOUNT;

    -- Bulk bind of data in memory table...
    FORALL i IN 1..v_cod_prod.count
    UPDATE PRI_PRODU
       SET DES_PROD = v_des_prod(i) ,
           EST_PROD = v_est_prod(i) ,
           STA_TRAN = 'N',
           USU_MODI = p_usuario,
           FEC_MODI = SYSDATE
     WHERE PAIS_COD_PAIS = p_codigoPais
       AND COD_PROD = v_cod_prod(i);

  END LOOP;
  CLOSE c_updateProducto;

END PRI_PR_CARGA_PRODU;

PROCEDURE PRI_PR_PROCE_PREMI(p_codigoPais IN VARCHAR2,
                                               p_totalPremios OUT VARCHAR2) IS

l_periodo   VARCHAR2(6);
l_periodoDefecto VARCHAR2(6);
l_statusSinPedido VARCHAR2(1);

/*****************************************

VALORES DE LOS FLAGS:

  Z: No registrado en Matriz.
  O: Sin Pedido.
  X: No Procesado (Solo para Peru).
  F: Falta de Puntos.
  B: Falta de Puntos Informado.
  T: Transferido.
  P: Despachado.
  R: Faltante.
  M: Monto Minimo.
  U: Faltante Anunciado.

*****************************************/

CURSOR CC_PREMIOS(l_periodoPedido VARCHAR2) IS
SELECT
C.CLIE_COD_CLIE,
A.TARJ_NUM_TARJ,
A.PAIS_COD_PAIS,
A.COR_PREM,
A.COD_PROD,
A.PUN_PREM,
A.PUN_TOTA,
A.CAM_SPRE,
A.CAM_DPRE,
A.STA_PREM,
A.IND_PREM,
A.FEC_PROC,
A.CAN_PREM,
B.SAL_ACTU
FROM PRI_PREMI A, PRI_STOCK_PRODU B, PRI_TARJE C
WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
AND A.COD_PROD = B.COD_PROD
AND A.PAIS_COD_PAIS = p_codigoPais
AND (A.STA_PREM = 'T' OR A.STA_PREM = 'N')
AND A.IND_PREM = 'P'
AND C.PAIS_COD_PAIS = A.PAIS_COD_PAIS
AND C.NUM_TARJ = A.TARJ_NUM_TARJ
AND A.TARJ_NUM_TARJ IN (
   SELECT DISTINCT NUM_TARJ
   FROM PRI_TARJE T, PRI_PEDID P
    WHERE T.PAIS_COD_PAIS = P.PAIS_COD_PAIS
    AND T.COD_CONS = P.COD_CONS
    AND T.PAIS_COD_PAIS = p_codigoPais
   AND P.CAM_PEDI = l_periodo);

RR_PREMIOS CC_PREMIOS%ROWTYPE;

BEGIN

/* Actualizaciones para poder realizar el reprocesamiento de premios
El objetivo es si es que se ejecuto este procedimiento, para 2 o mas ejecuciones poder regresar al estado original.
En caso sea la primera ejecucion, lo unico que actualizara seran los flags de N a T.*/

UPDATE PRI_PREMI SET STA_PREM = 'T' WHERE STA_PREM NOT IN ('B','F');

UPDATE PRI_PREMI SET STA_PREM = 'F' WHERE STA_PREM = 'B';

UPDATE PRI_PREMI SET CAM_DPRE = NULL;

UPDATE PRI_STOCK_PRODU A SET A.SAL_ACTU = (A.SAL_ACTU + A.NUM_DESP)
WHERE (A.PAIS_COD_PAIS, A.COD_PROD) IN
   (SELECT DISTINCT B.PAIS_COD_PAIS, B.COD_PROD
   FROM PRI_PREMI B
   WHERE B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
   AND B.COD_PROD = A.COD_PROD);

UPDATE PRI_STOCK_PRODU A SET A.NUM_DESP = 0
WHERE (A.PAIS_COD_PAIS, A.COD_PROD) IN
   (SELECT DISTINCT B.PAIS_COD_PAIS, B.COD_PROD
   FROM PRI_PREMI B
   WHERE B.PAIS_COD_PAIS = A.PAIS_COD_PAIS
   AND B.COD_PROD = A.COD_PROD);


/*
Obtenemos el valor de la l_periodo por defecto.  La menor
de las l_periodos en proceso.  Este valor esta almacenado
en la tabla BAS_CONTR y fue actualizado en el proceo de
carga de informacion desde el SiCC.
*/
SELECT CAM_PROC
INTO l_periodoDefecto
FROM BAS_CONTR
WHERE PAIS_COD_PAIS = p_codigoPais;


/*
I. FALTA PUNTOS INFORMADO (B):
Actualizamos los premios con Status en 'F' a 'B'.  La l_periodo de solicitud
la asignamos en base a la l_periodo de los pedidos de la consultora, en caso
la consultora no haya pasado pedido se le asigna la l_periodo menor.
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
 l_periodo := rrCampProc.CAM_PROC;

 /*
 Actualizamos aquellos premios cuyas consultoras
 han pasado pedido
 */
 UPDATE PRI_PREMI SET
 STA_PREM = 'B',
 CAM_SPRE = NVL(CAM_SPRE, l_periodo)
 WHERE TARJ_NUM_TARJ IN (
    SELECT DISTINCT NUM_TARJ
    FROM PRI_TARJE T, PRI_PEDID P
    WHERE T.PAIS_COD_PAIS = P.PAIS_COD_PAIS
    AND T.COD_CONS = P.COD_CONS
    AND T.PAIS_COD_PAIS = p_codigoPais
    AND P.CAM_PEDI = l_periodo)
 AND PAIS_COD_PAIS = p_codigoPais
 AND STA_PREM = 'F';
END LOOP;

/*
Finalmente actualizamos los premios cuyas consultoras no
han pasado pedido asignandoles la l_periodo por defecto
*/
UPDATE PRI_PREMI SET
STA_PREM = 'B',
CAM_SPRE = l_periodoDefecto
WHERE STA_PREM = 'F'
AND CAM_SPRE IS NULL
AND PAIS_COD_PAIS = p_codigoPais;


/*
II. MONTO MINIMO (M):
Actualizamos el Status de los Premios en 'M' de los Pedidos que tenga status M.
Iteramos sobre las l_periodos a procesar, asignando dicho valor a la l_periodo de
solicitud del premio siempre y cuando este sea nulo y la consultora haya pasado
un pedido para dicha l_periodo.
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
 l_periodo := rrCampProc.CAM_PROC;

 UPDATE PRI_PREMI sET
 STA_PREM = 'M',
 CAM_SPRE = NVL(CAM_SPRE,l_periodo)
 WHERE TARJ_NUM_TARJ IN (
    SELECT DISTINCT NUM_TARJ
    FROM PRI_TARJE A, PRI_PEDID B
     WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
     AND A.COD_CONS = B.COD_CONS
     AND A.PAIS_COD_PAIS = p_codigoPais
    AND B.CAM_PEDI = l_periodo
     AND B.STA_PEDI='M')
 AND PAIS_COD_PAIS = p_codigoPais
 AND (STA_PREM = 'T' OR STA_PREM = 'N');
END LOOP;


/*
III. NO REGISTRADO EN MATRIZ (Z):
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
 l_periodo := rrCampProc.CAM_PROC;

 UPDATE PRI_PREMI SET
 STA_PREM = 'Z',
 CAM_SPRE = NVL(CAM_SPRE,l_periodo)
 WHERE (COD_PROD, IND_PREM) NOT IN (
    SELECT DISTINCT COD_PROD, IND_ESTR
    FROM PRI_MATRI
    WHERE PAIS_COD_PAIS = p_codigoPais
    AND CAM_MATR = l_periodo
    )
 AND TARJ_NUM_TARJ IN (
    SELECT DISTINCT NUM_TARJ
    FROM PRI_TARJE T, PRI_PEDID P
    WHERE T.PAIS_COD_PAIS = P.PAIS_COD_PAIS
    AND T.COD_CONS = P.COD_CONS
    AND T.PAIS_COD_PAIS = p_codigoPais
    AND P.CAM_PEDI = l_periodo)
 AND PAIS_COD_PAIS = p_codigoPais
 AND (STA_PREM = 'T' OR STA_PREM = 'N');
END LOOP;


/*
IV. FALTANTE ANUNCIADO (U):
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
 l_periodo := rrCampProc.CAM_PROC;

 UPDATE PRI_PREMI SET
 STA_PREM = 'U',
 CAM_SPRE = NVL(CAM_SPRE,l_periodo)
 WHERE (COD_PROD, IND_PREM) IN (
    SELECT DISTINCT COD_PROD, IND_ESTR
    FROM PRI_MATRI
    WHERE PAIS_COD_PAIS = p_codigoPais
    AND CAM_MATR = l_periodo
    AND IND_FALT_ANUN = 'S'
    )
 AND TARJ_NUM_TARJ IN (
    SELECT DISTINCT NUM_TARJ
    FROM PRI_TARJE T, PRI_PEDID P
    WHERE T.PAIS_COD_PAIS = P.PAIS_COD_PAIS
    AND T.COD_CONS = P.COD_CONS
    AND T.PAIS_COD_PAIS = p_codigoPais
    AND P.CAM_PEDI = l_periodo)
 AND PAIS_COD_PAIS = p_codigoPais
 AND (STA_PREM = 'T' OR STA_PREM = 'N');
END LOOP;


/*
V. DESPACHADO (P) / FALTANTE (R) PARA PREMIOS:
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
 l_periodo := rrCampProc.CAM_PROC;

 OPEN CC_PREMIOS(l_periodo);
    LOOP
  FETCH CC_PREMIOS INTO RR_PREMIOS;
  EXIT WHEN CC_PREMIOS%NOTFOUND;
    BEGIN
       IF (RR_PREMIOS.CAN_PREM <= RR_PREMIOS.SAL_ACTU) THEN
           UPDATE PRI_PREMI SET
     STA_PREM = 'P',
     CAM_SPRE = NVL(CAM_SPRE,l_periodo),
     CAM_DPRE = NVL(CAM_DPRE,l_periodo)
     WHERE PAIS_COD_PAIS = RR_PREMIOS.PAIS_COD_PAIS
     AND TARJ_NUM_TARJ = RR_PREMIOS.TARJ_NUM_TARJ
     AND COR_PREM = RR_PREMIOS.COR_PREM;

     UPDATE PRI_STOCK_PRODU
     SET SAL_ACTU = (RR_PREMIOS.SAL_ACTU - RR_PREMIOS.CAN_PREM),
       NUM_DESP = (NUM_DESP + RR_PREMIOS.CAN_PREM)
     WHERE PAIS_COD_PAIS = RR_PREMIOS.PAIS_COD_PAIS
     AND COD_PROD = RR_PREMIOS.COD_PROD;

     -- Actualizamos la cuenta para que pase los puntos
     -- comprometidos al utilizado
     UPDATE PRI_CUENT_CLIEN SET
     PUN_COMP = PUN_COMP - RR_PREMIOS.PUN_TOTA,
     PUN_UTIL = PUN_UTIL + RR_PREMIOS.PUN_TOTA
     WHERE PAIS_COD_PAIS = RR_PREMIOS.PAIS_COD_PAIS
     AND CLIE_COD_CLIE = RR_PREMIOS.CLIE_COD_CLIE;

     ELSE
           UPDATE PRI_PREMI SET
      STA_PREM = 'R',
     CAM_SPRE = NVL(CAM_SPRE,l_periodo),
     CAM_DPRE = NVL(CAM_DPRE,l_periodo)
     WHERE PAIS_COD_PAIS = RR_PREMIOS.PAIS_COD_PAIS
     AND TARJ_NUM_TARJ = RR_PREMIOS.TARJ_NUM_TARJ
     AND COR_PREM = RR_PREMIOS.COR_PREM;

           END IF;
    END;
    END LOOP;
 CLOSE CC_PREMIOS;
END LOOP;


/*
VI. DESPACHADO (P) / FALTANTE (R) PARA OPORTUNIDADES:
*/
FOR rrCampProc IN (SELECT PAIS_COD_PAIS,
             CAM_PROC
         FROM PRI_CAMPA_PROCE
        WHERE PAIS_COD_PAIS = p_codigoPais
     ORDER BY CAM_PROC)
LOOP
    /* Obtenemos la l_periodo  */
    l_periodo := rrCampProc.CAM_PROC;

 UPDATE PRI_PREMI
 SET STA_PREM = 'P',
 CAM_SPRE = NVL(CAM_SPRE,l_periodo),
    CAM_DPRE = NVL(CAM_DPRE,l_periodo)
 WHERE (PAIS_COD_PAIS, TARJ_NUM_TARJ, COR_PREM) IN
 (
     SELECT PAIS_COD_PAIS, NUM_TARJ, COR_PREM FROM
     (
   SELECT
   PRI_TARJE.PAIS_COD_PAIS,
   PRI_TARJE.COD_CONS,
   PRI_PREMI.COD_PROD,
   PRI_TARJE.NUM_TARJ,
   PRI_PREMI.COR_PREM,
   PRI_DETAL_PEDID.NUM_UNID_ATEN,
   ROW_NUMBER() OVER (PARTITION BY PRI_TARJE.COD_CONS, PRI_PREMI.COD_PROD ORDER BY PRI_TARJE.NUM_TARJ) COR_PROD
   FROM PRI_TARJE, PRI_PREMI, PRI_PEDID, PRI_DETAL_PEDID, PRI_MATRI
   WHERE  PRI_PEDID.PAIS_COD_PAIS = PRI_DETAL_PEDID.PAIS_COD_PAIS
   AND PRI_PEDID.NUM_PEDI = PRI_DETAL_PEDID.PEDI_NUM_PEDI
   AND PRI_TARJE.PAIS_COD_PAIS = PRI_PREMI.PAIS_COD_PAIS
   AND PRI_TARJE.NUM_TARJ = PRI_PREMI.TARJ_NUM_TARJ
   AND PRI_PREMI.PAIS_COD_PAIS = PRI_MATRI.PAIS_COD_PAIS
   AND PRI_PREMI.COD_PROD = PRI_MATRI.COD_PROD
   AND PRI_PREMI.IND_PREM = 'Q'
   AND (PRI_PREMI.STA_PREM = 'T' OR PRI_PREMI.STA_PREM = 'N')
   AND PRI_TARJE.PAIS_COD_PAIS = PRI_PEDID.PAIS_COD_PAIS
   AND PRI_TARJE.COD_CONS = PRI_PEDID.COD_CONS
   AND PRI_DETAL_PEDID.PAIS_COD_PAIS = PRI_MATRI.PAIS_COD_PAIS
   AND PRI_DETAL_PEDID.COD_UNIC_VENT = PRI_MATRI.COD_UNIC_VENT
   AND PRI_PEDID.CAM_PEDI = l_periodo
   ORDER BY PRI_TARJE.PAIS_COD_PAIS, PRI_TARJE.COD_CONS, PRI_PREMI.COD_PROD
     )
  WHERE COR_PROD <= NUM_UNID_ATEN
 );

 /* Asignamos el status Faltante (R) a los premios que no fueron actualizados
    en el query anterior */
 UPDATE PRI_PREMI
 SET STA_PREM = 'R',
 CAM_SPRE = NVL(CAM_SPRE,l_periodo),
    CAM_DPRE = NVL(CAM_DPRE,l_periodo)
 WHERE (PAIS_COD_PAIS, TARJ_NUM_TARJ, COR_PREM) IN
 (
  SELECT
  PRI_TARJE.PAIS_COD_PAIS,
  PRI_TARJE.NUM_TARJ,
  PRI_PREMI.COR_PREM
  FROM PRI_TARJE, PRI_PREMI, PRI_PEDID, PRI_DETAL_PEDID, PRI_MATRI
  WHERE  PRI_PEDID.PAIS_COD_PAIS = PRI_DETAL_PEDID.PAIS_COD_PAIS
  AND PRI_PEDID.NUM_PEDI = PRI_DETAL_PEDID.PEDI_NUM_PEDI
  AND PRI_TARJE.PAIS_COD_PAIS = PRI_PREMI.PAIS_COD_PAIS
  AND PRI_TARJE.NUM_TARJ = PRI_PREMI.TARJ_NUM_TARJ
  AND PRI_PREMI.PAIS_COD_PAIS = PRI_MATRI.PAIS_COD_PAIS
  AND PRI_PREMI.COD_PROD = PRI_MATRI.COD_PROD
  AND PRI_PREMI.IND_PREM = 'Q'
  AND (PRI_PREMI.STA_PREM = 'T' OR PRI_PREMI.STA_PREM = 'N')
  AND PRI_TARJE.PAIS_COD_PAIS = PRI_PEDID.PAIS_COD_PAIS
  AND PRI_TARJE.COD_CONS = PRI_PEDID.COD_CONS
  AND PRI_DETAL_PEDID.PAIS_COD_PAIS = PRI_MATRI.PAIS_COD_PAIS
  AND PRI_DETAL_PEDID.COD_UNIC_VENT = PRI_MATRI.COD_UNIC_VENT
  AND PRI_PEDID.CAM_PEDI = l_periodo
 );

END LOOP;


/*
VII. SIN PEDIDO (O/X):
*/
IF p_codigoPais != 'PE' THEN
   l_statusSinPedido:='O';
ELSE
   l_statusSinPedido:='X';
END IF;

UPDATE PRI_PREMI SET
STA_PREM = l_statusSinPedido,
CAM_SPRE = NVL(CAM_SPRE,l_periodo)
WHERE TARJ_NUM_TARJ NOT IN (
   SELECT DISTINCT NUM_TARJ
   FROM PRI_TARJE A, PRI_PEDID B
   WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
   AND A.COD_CONS = B.COD_CONS
   AND A.PAIS_COD_PAIS = p_codigoPais
   AND B.STA_PEDI != 'A')
AND PAIS_COD_PAIS = p_codigoPais
AND (STA_PREM = 'T' OR STA_PREM = 'N');

UPDATE PRI_PREMI SET
STA_PREM = l_statusSinPedido,
CAM_SPRE = NVL(CAM_SPRE,l_periodo)
WHERE PAIS_COD_PAIS = p_codigoPais
AND (STA_PREM = 'T' OR STA_PREM = 'N');

/*
Elimina aquellos premios que se encuentren en el Historico, para evitar problemas de duplicidad.
*/
DELETE FROM PRI_HISTO_PREMI
WHERE (PAIS_COD_PAIS, COD_CLIE, NUM_TARJ, COR_PREM) IN (
   SELECT
   A.PAIS_COD_PAIS,
   C.COD_CLIE,
   A.TARJ_NUM_TARJ,
   A.COR_PREM
   FROM PRI_PREMI A, PRI_TARJE B, PRI_CLIEN C
   WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
   AND B.PAIS_COD_PAIS = C.PAIS_COD_PAIS
   AND A.TARJ_NUM_TARJ = B.NUM_TARJ
   AND B.CLIE_COD_CLIE = C.COD_CLIE
   -- AND A.STA_PREM = 'P' -- Comentado para eliminar premios revertidos manualmente
   );


/*
Insertamos los premios que han sido procesados
*/
INSERT INTO PRI_HISTO_PREMI
SELECT
A.PAIS_COD_PAIS,
C.COD_CLIE,
A.TARJ_NUM_TARJ,
A.COR_PREM,
B.COD_CONS,
C.NOM_CLIE,
C.NUM_DOCU,
A.COD_PROD,
A.CAN_PREM,
A.PUN_PREM,
A.PUN_TOTA,
A.CAM_SPRE,
A.CAM_DPRE,
A.STA_PREM,
A.IND_PREM,
SYSDATE,
A.IND_CARN
FROM PRI_PREMI A, PRI_TARJE B, PRI_CLIEN C
WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
AND B.PAIS_COD_PAIS = C.PAIS_COD_PAIS
AND A.TARJ_NUM_TARJ = B.NUM_TARJ
AND B.CLIE_COD_CLIE = C.COD_CLIE
AND A.STA_PREM = 'P';


/*
Obtenemos el total de premios procesados
*/
SELECT COUNT(1)
INTO p_totalPremios
FROM PRI_PREMI A, PRI_TARJE B
WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
AND A.TARJ_NUM_TARJ = B.NUM_TARJ
AND A.PAIS_COD_PAIS = p_codigoPais;

/*
Actualizacion del Status de Impresion en las Fichas y Tarjetas de las Consultoras
que han pasado pedido durante el proceso, independientemente del status del pedido.
*/
UPDATE PRI_TARJE T
SET T.STA_IMPR = 'I'
WHERE T.COD_CONS IN (
   SELECT P.COD_CONS
   FROM PRI_PEDID P
   WHERE P.PAIS_COD_PAIS = T.PAIS_COD_PAIS
   AND P.COD_CONS = T.COD_CONS
   AND P.STA_PEDI != 'A'
   )
AND T.PAIS_COD_PAIS = p_codigoPais;

UPDATE PRI_CLIEN C
SET C.STA_IMPR = 'I'
WHERE C.COD_CONS IN (
   SELECT P.COD_CONS
   FROM PRI_PEDID P
   WHERE P.PAIS_COD_PAIS = C.PAIS_COD_PAIS
   AND P.COD_CONS = C.COD_CONS
   AND P.STA_PEDI != 'A'
   )
AND C.PAIS_COD_PAIS = p_codigoPais;

/*
Actualizamos los periodos de las fichas y tarjetas
*/
UPDATE PRI_CLIEN
SET CAM_REGI = (SELECT MIN(CAM_PROC)
                FROM PRI_CAMPA_PROCE
                WHERE PAIS_COD_PAIS = p_codigoPais )
WHERE PAIS_COD_PAIS = p_codigoPais
AND CAM_REGI IS NULL;

UPDATE PRI_TARJE
SET CAM_TARJ = (SELECT MIN(CAM_PROC)
                FROM PRI_CAMPA_PROCE
                WHERE PAIS_COD_PAIS = p_codigoPais )
WHERE PAIS_COD_PAIS = p_codigoPais
AND CAM_TARJ IS NULL;

-- Invocamos al procedimiento que desbloquea a las consultoras temporalmente
-- que tienen bloqueo por Boleta de Recojo y que tienen premio despachado
PRI_PR_DESBL_CLIEN_PREMI(p_codigoPais, l_periodoDefecto);

END PRI_PR_PROCE_PREMI;


PROCEDURE PRI_PR_GENER_STICK(p_codigoPais IN VARCHAR2,
                                               p_totalStickers OUT VARCHAR2) IS

/***************************************************************************************************
Descripcion         : Realiza la Generacion de Stickers
Fecha Creacion      : 09/12/2005
Fecha Modificacion  : 24/25/2006
Parametros Entrada  : p_codigoPais : Codigo del Pais del Usuario Logeado (ejm: PE es de PERU)
Parametros Salida   : p_totalStickers : Muestra la cantidad de Stickers que han sido creados
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Beta|Final)
Cambios Importantes : Inclusion del comentario
****************************************************************************************************/

/*
Cursor que contendra la informacion para la creacion de Stickers.
*/
CURSOR CC_UNIDADES IS
SELECT
BAS_CONTR.PAIS_COD_PAIS,
PRI_PEDID.COD_CONS,
PRI_PRODU.COD_PROD,
PRI_PRODU.DES_PROD,
PRI_PEDID.COD_SUVE,
PRI_PEDID.COD_REGI,
PRI_PEDID.COD_ZONA,
PRI_PEDID.COD_TERR,
PRI_PEDID.CAM_PEDI,
SYSDATE FEC_STIC,
PRI_MATRI.VAL_UNIT,
BAS_CONTR.VAL_FACT,
-- Si el archivo de control esta configurado como puntaje fijo entonces colocamos 0
-- en caso contrario calculamos el valor dependiendo del valor de la estrategia
CASE WHEN BAS_CONTR.IND_PUNT_FIJO = 'S' THEN 0
     ELSE DECODE(PRI_MATRI.IND_ESTR, 'B', 0, CEIL(PRI_MATRI.VAL_UNIT/BAS_CONTR.VAL_FACT))
END PUN_STIC,
-- Si el archivo de control esta configurado como puntaje fijo entonces colocamos el valor
-- de la tabla PRI_PRODU, en caso contrario calculamos el valor dependiendo del valor de la
-- estrategia
CASE WHEN BAS_CONTR.IND_PUNT_FIJO = 'S' THEN PRI_PRODU.PUN_STIC
     ELSE DECODE(PRI_MATRI.IND_ESTR, 'B', PRI_MATRI.PUN_FIJO, 0)
END PUN_FIJO,
PRI_PEDID.NUM_FACT,
PRI_PEDID.NUM_SECU_FACT_DIAR,
PRI_PEDID.NUM_SECU_ZONA_RUTA,
PRI_DETAL_PEDID.NUM_UNID_ATEN,
PRI_DETAL_PEDID.PEDI_NUM_PEDI,
PRI_DETAL_PEDID.NUM_POSI,
PRI_MATRI.COD_UNIC_VENT,
DECODE(BAS_CONTR.IND_PUNT_FIJO, 'S', 'F', PRI_MATRI.IND_ESTR) IND_ESTR
FROM PRI_DETAL_PEDID, PRI_PEDID, PRI_PRODU, PRI_MATRI, BAS_CONTR
WHERE PRI_PEDID.PAIS_COD_PAIS = PRI_DETAL_PEDID.PAIS_COD_PAIS
AND PRI_PEDID.NUM_PEDI = PRI_DETAL_PEDID.PEDI_NUM_PEDI
AND PRI_MATRI.PAIS_COD_PAIS = PRI_DETAL_PEDID.PAIS_COD_PAIS
AND PRI_MATRI.COD_UNIC_VENT = PRI_DETAL_PEDID.COD_UNIC_VENT
AND PRI_PRODU.PAIS_COD_PAIS = PRI_DETAL_PEDID.PAIS_COD_PAIS
AND PRI_PRODU.COD_PROD = PRI_DETAL_PEDID.COD_PROD
AND PRI_MATRI.PAIS_COD_PAIS = BAS_CONTR.PAIS_COD_PAIS
AND PRI_DETAL_PEDID.PAIS_COD_PAIS = p_codigoPais
AND PRI_DETAL_PEDID.NUM_UNID_ATEN > 0
AND PRI_MATRI.IND_ESTR != 'P' -- Diferente de premios gratis
AND PRI_MATRI.VAL_UNIT > 0 -- Precio mayor a cero
AND PRI_PRODU.IND_GENE_STIC = 'S' -- Solo los que estan marcados para generar sticker
AND PRI_PEDID.STA_PEDI = 'P' -- Solo los pedidos validos, no anulados
AND BAS_CONTR.IND_GENE_STIC = 'S' -- Solo si esta activo el indicador por pais
ORDER BY PRI_PEDID.NUM_SECU_ZONA_RUTA,
         PRI_PEDID.NUM_SECU_FACT_DIAR,
         PRI_PEDID.COD_CONS;

TYPE unidadesRecordType IS RECORD (
codigoPais                BAS_CONTR.PAIS_COD_PAIS%TYPE,
codigoConsultora          PRI_PEDID.COD_CONS%TYPE,
codigoProducto            PRI_PRODU.COD_PROD%TYPE,
descripcionProducto       PRI_PRODU.DES_PROD%TYPE,
codigoSubgerencia         PRI_PEDID.COD_SUVE%TYPE,
codigoRegion              PRI_PEDID.COD_REGI%TYPE,
codigoZona                PRI_PEDID.COD_ZONA%TYPE,
codigoTerritorio          PRI_PEDID.COD_TERR%TYPE,
codigoPeriodo             PRI_PEDID.CAM_PEDI%TYPE,
fechaSticker              DATE,
valorUnitario             PRI_MATRI.VAL_UNIT%TYPE,
valorFactor               BAS_CONTR.VAL_FACT%TYPE,
puntajeSticker            NUMBER(5),
puntajeFijo               NUMBER(5),
numeroFactura             PRI_PEDID.NUM_FACT%TYPE,
numeroSecuenciaDiario     PRI_PEDID.NUM_SECU_FACT_DIAR%TYPE,
numeroSecuenciaRuta       PRI_PEDID.NUM_SECU_ZONA_RUTA%TYPE,
numeroUnidadesAtendidas   PRI_DETAL_PEDID.NUM_UNID_ATEN%TYPE,
numeroPedido              PRI_DETAL_PEDID.PEDI_NUM_PEDI%TYPE,
numeroPosicion            PRI_DETAL_PEDID.NUM_POSI%TYPE,
codigoUnicoVenta          PRI_MATRI.COD_UNIC_VENT%TYPE,
indicadorEstrategia       PRI_MATRI.IND_ESTR%TYPE
);

TYPE unidadesRecordTab  IS TABLE OF unidadesRecordType;
unidadesRecord unidadesRecordTab;

rows        NATURAL := 1000;
k           BINARY_INTEGER := 0;
v_row_count NUMBER := 0;

nrostick INTEGER;
indrec   INTEGER;
i        INTEGER;
curgen   INTEGER;

stickerActual BAS_CONTR.COD_SACT%TYPE;


BEGIN

/*
Elimina todos las filas de la Tabla PRI_STICK.
*/
EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_STICK';

-- Obtenemos el valor del sticker actual
SELECT COD_SACT
INTO stickerActual
FROM BAS_CONTR
WHERE PAIS_COD_PAIS = p_codigoPais;

curgen:=0;

/*
Se Inserta en la tabla PRI_STICK de acuerdo a la informacion de las siguientes tablas:
    PRI_DETAL_PEDID obtiene el numero de unidades atendidas por producto.
    PRI_PEDID nos permite ordenar los resultados por Consultora.
    PRI_PRODU corrobora que los productos que se encuentran en el detalle de pedido esten registrados.
    PRI_MATRI a traves del Indicador de Estrategia nos determina si es premio u oportunidad Privilege.
    BAS_CONTR es la tabla en la que se almacenara el Codigo de Sticker Actual,
    de igual manera contiene el Valor del Factor.
*/

OPEN CC_UNIDADES;
   LOOP
   -- Bulk Collect
   FETCH CC_UNIDADES BULK COLLECT INTO unidadesRecord LIMIT rows;
      IF unidadesRecord.COUNT > 0 THEN
         FOR k IN unidadesRecord.FIRST..unidadesRecord.LAST LOOP
      indrec := unidadesRecord(k).numeroUnidadesAtendidas;
            FOR i IN 1..indrec LOOP
               curgen:= curgen + 1;
               INSERT INTO PRI_STICK (
               PAIS_COD_PAIS,
               COD_STIC,
               COD_PROD,
               COD_CONS,
               DES_PROD,
               COD_SUVE,
               COD_REGI,
               COD_ZONA,
               COD_TERR,
               CAM_STIC,
               FEC_STIC,
               VAL_UNIT,
               FAC_STIC,
               PUN_STIC,
               PUN_FIJO,
               NUM_IMPR,
               STA_STIC,
               NUM_FACT,
               STA_ANUL,
               NUM_SECU_FACT_DIAR,
               NUM_SECU_ZONA_RUTA,
               COD_UNIC_VENT,
               IND_ESTR)
               VALUES (
               unidadesRecord(k).codigoPais,
               LPAD(TO_CHAR(NVL(stickerActual, 0) + curgen), 10, '0'),
               unidadesRecord(k).codigoProducto,
               unidadesRecord(k).codigoConsultora,
               unidadesRecord(k).descripcionProducto,
               unidadesRecord(k).codigoSubgerencia,
               unidadesRecord(k).codigoRegion,
               unidadesRecord(k).codigoZona,
               unidadesRecord(k).codigoTerritorio,
               unidadesRecord(k).codigoPeriodo,
               unidadesRecord(k).fechaSticker,
               unidadesRecord(k).valorUnitario,
               unidadesRecord(k).valorFactor,
               unidadesRecord(k).puntajeSticker,
               unidadesRecord(k).puntajeFijo,
               1,
               '0',
               unidadesRecord(k).numeroFactura,
               ' ',
               unidadesRecord(k).numeroSecuenciaDiario,
               unidadesRecord(k).numeroSecuenciaRuta,
               unidadesRecord(k).codigoUnicoVenta,
               unidadesRecord(k).indicadorEstrategia);
            END LOOP;
         END LOOP;
      END IF;
      EXIT WHEN CC_UNIDADES%NOTFOUND;
   END LOOP;
CLOSE CC_UNIDADES;


-- Actualizamos las estadisticas
DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'PRI_STICK', CASCADE => TRUE );

/*
Elimina aquellos stickers que se encuentren en el Historico, para evitar problemas de duplicidad.
*/
DELETE FROM PRI_HISTO_STICK H
WHERE EXISTS (
SELECT NULL
FROM PRI_STICK S
WHERE S.PAIS_COD_PAIS = H.PAIS_COD_PAIS
AND S.COD_STIC = H.COD_STIC
);

/*
Pasa la informacion generada en el PRI_STICK a la Tabla PRI_HISTO_STICK, que es la Historico de Stickers.
*/
INSERT INTO PRI_HISTO_STICK (
            PAIS_COD_PAIS,
            COD_STIC,
            COD_PROD,
            COD_CONS,
            COD_SUVE,
            COD_REGI,
            COD_ZONA,
            COD_TERR,
            CAM_STIC,
            FEC_STIC,
            VAL_UNIT,
            FAC_STIC,
            PUN_STIC,
            PUN_FIJO,
            NUM_IMPR,
            STA_STIC,
            NUM_FACT,
            STA_ANUL,
            DES_PROD,
            NUM_SECU_FACT_DIAR,
            NUM_SECU_ZONA_RUTA,
   COD_UNIC_VENT,
   IND_ESTR)
SELECT PAIS_COD_PAIS,
       COD_STIC,
       COD_PROD,
       COD_CONS,
       COD_SUVE,
       COD_REGI,
       COD_ZONA,
       COD_TERR,
       CAM_STIC,
       FEC_STIC,
       VAL_UNIT,
       FAC_STIC,
       PUN_STIC,
       PUN_FIJO,
       NUM_IMPR,
       STA_STIC,
       NUM_FACT,
       STA_ANUL,
       DES_PROD,
       NUM_SECU_FACT_DIAR,
       NUM_SECU_ZONA_RUTA,
    COD_UNIC_VENT,
    IND_ESTR
FROM PRI_STICK;

/*
 Actualizamos la tabla de Historico de Stickers, se anulan los Stickers
 cuyas Boletas de Despacho tambien han sido anuladas
 Al status le asignamos el valor '0' para que sea enviado a Privilege
 durante la ejecucion de las interfaces.
 */
/*
UPDATE PRI_HISTO_STICK A SET
A.STA_STIC = '0',
A.STA_ANUL = 'A'
WHERE A.NUM_FACT IN
   (  SELECT DISTINCT B.NUM_FACT
      FROM PRI_PEDID B
      WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
      AND B.STA_PEDI = 'A'
   )
AND A.STA_ANUL != 'A'
AND A.PAIS_COD_PAIS = p_codigoPais;
*/

SELECT COUNT(1) INTO p_totalStickers FROM PRI_STICK;

END PRI_PR_GENER_STICK;


PROCEDURE PRI_PR_CIERR_PROCE(p_codigoPais IN VARCHAR2) IS

/***************************************************************************************************
Descripcion         : Realiza el Cierre de los Procesos Diarios del SCdF
Fecha Creacion      : 18/05/2006
Fecha Modificacion  : 24/05/2006
Parametros Entrada  : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Beta|Final)
Cambios Importantes : Inclusion del comentario.
****************************************************************************************************/

BEGIN

/*
 * I. ACTUALIZACION DE STATUS DE TRANSFERENCIA DE CONSULTORAS:
 * En este punto actualizamos el status a T para que dichas consultoras no
 * sean enviadas a Privilege en los futuros procesos diarios.  Se hace de
 * esta forma para poder realizar reprocesos y reenvios de esta informacion.
 */
 UPDATE PRI_CONSU SET
 STA_TRAN = 'T'
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND STA_TRAN = 'N';

/*
 * II. ACTUALIZACION DEL CORRELATIVO DE STICKER ACTUAL:
 * Actualizamos el valor del correlativo en la tabla BAS_CONTR tomando
 * el valor maximo actual de la tabla PRI_STICK.
 */
 UPDATE BAS_CONTR SET
 COD_SACT = NVL((SELECT MAX(COD_STIC) FROM PRI_STICK WHERE PAIS_COD_PAIS = p_codigoPais),COD_SACT)
 WHERE PAIS_COD_PAIS = p_codigoPais;

/*
 * III. ELIMINACION DE LOS OID DE CONSULTORAS
 * Eliminamos los OIDs de la tabla PRI_OID_CONSU para que
 * sean enviadas a Privilege, dejamos un margen de fechas
 * para evitar la falta de actualizacion de alguna de ellas
 */
 DELETE FROM PRI_OID_CONSU
 WHERE FECHA <  SYSDATE - 2;

-- Deshabilitamos los constraints para el truncamiento

EXECUTE IMMEDIATE 'ALTER TABLE PRI_DETAL_PEDID DISABLE CONSTRAINT PRI_DEPE_PEDI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_ZONA DISABLE CONSTRAINT PRI_ZONA_REGI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_REGIO DISABLE CONSTRAINT PRI_REGI_SUVE_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_CUENT_CLIEN DISABLE CONSTRAINT PRI_CUCL_CLIE_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_PREMI DISABLE CONSTRAINT PRI_PREM_TARJ_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_TARJE DISABLE CONSTRAINT PRI_TARJ_PAIS_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_TARJE DISABLE CONSTRAINT PRI_TARJ_CLIE_FK';

-- Truncamos las tablas

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CAMPA_PROCE';

-- Se elimina el truncate de la tabla de productos para
-- el soporte de Puntaje Fijo el cual se hace a través
-- del Mantenimiento de Productos (CHR 22/10/2007)
-- EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_PRODU';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_DETAL_PEDID';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_PEDID';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_MATRI';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_STOCK_PRODU';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_ZONA';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_REGIO';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_SUBGE_VENTA';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_PREMI';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_TARJE';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CUENT_CLIEN';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CLIEN';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_CONSU';

EXECUTE IMMEDIATE 'TRUNCATE TABLE PRI_STICK';

-- Se elimina el truncamiento de la tabla del paquete documentario
-- para que este proceso no este sujeto a la validacion del mismo
-- EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PRIVI';

-- Habilitamos los constraints para el truncamiento
EXECUTE IMMEDIATE 'ALTER TABLE PRI_DETAL_PEDID ENABLE CONSTRAINT PRI_DEPE_PEDI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_ZONA ENABLE CONSTRAINT PRI_ZONA_REGI_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_REGIO ENABLE CONSTRAINT PRI_REGI_SUVE_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_CUENT_CLIEN ENABLE CONSTRAINT PRI_CUCL_CLIE_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_PREMI ENABLE CONSTRAINT PRI_PREM_TARJ_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_TARJE ENABLE CONSTRAINT PRI_TARJ_PAIS_FK';

EXECUTE IMMEDIATE 'ALTER TABLE PRI_TARJE ENABLE CONSTRAINT PRI_TARJ_CLIE_FK';

END PRI_PR_CIERR_PROCE;

PROCEDURE PRI_PR_ACTUA_PEDID_FACTU(p_codigoPais IN VARCHAR2) IS

/***************************************************************************************************
Descripcion         : Realiza la actualizacion de la Tabla PRI_PEDID referente a los campos de
                      Facturacion NUM_FACT (numero de factura) FEC_FACT (fecha de facturacion)
Fecha Creacion      : 02/01/2006
Fecha Modificacion  : 24/05/2006
Parametros Entrada  : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
****************************************************************************************************/


CURSOR ccCabeceraPedido IS
SELECT SEG_PAIS.COD_PAIS,
       PED_SOLIC_CABEC.OID_SOLI_CABE,
       FACTURA.FEC_FACT,
       FACTURA.VAL_NUME_SOLI,
       PED_SOLIC_CABEC_SECUE.NUM_SECU_FACT_DIAR,
       PED_SOLIC_CABEC_SECUE.NUM_SECU_ZONA_RUTA
  FROM PED_SOLIC_CABEC,
       MAE_CLIEN,
       CRA_PERIO,
       PED_ESTAD_SOLIC,
       PED_GRUPO_PROCE,
       ZON_TERRI,
       ZON_ZONA,
       ZON_REGIO,
       ZON_SUB_GEREN_VENTA,
       SEG_PAIS,
       PED_SOLIC_CABEC FACTURA,
       SEG_PERIO_CORPO,
       PED_TIPO_SOLIC,
       PED_TIPO_SOLIC_PAIS,
       PED_SOLIC_CABEC_SECUE,
       PRI_CAMPA_PROCE,
       PRI_PEDID
 WHERE (    (SEG_PAIS.COD_PAIS = p_codigoPais)
        AND (CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI)
        AND (PED_ESTAD_SOLIC.OID_ESTA_SOLI = PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI)
        AND (MAE_CLIEN.OID_CLIE = PED_SOLIC_CABEC.CLIE_OID_CLIE)
        AND (PED_GRUPO_PROCE.OID_GRUP_PROC = PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC)
        AND (ZON_TERRI.OID_TERR = PED_SOLIC_CABEC.TERR_OID_TERR)
        AND (ZON_ZONA.OID_ZONA = PED_SOLIC_CABEC.ZZON_OID_ZONA)
        AND (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI)
        AND (ZON_SUB_GEREN_VENTA.OID_SUBG_VENT = ZON_REGIO.ZSGV_OID_SUBG_VENT)
        AND (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS)
        AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
        AND (PED_TIPO_SOLIC.OID_TIPO_SOLI = PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
        AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
        AND (PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE = FACTURA.OID_SOLI_CABE(+))
        AND (SEG_PAIS.COD_PAIS = PRI_CAMPA_PROCE.PAIS_COD_PAIS)
        AND (FACTURA.OID_SOLI_CABE = PED_SOLIC_CABEC_SECUE.SOCA_OID_SOLI_CABE)
        AND (PED_GRUPO_PROCE.COD_GRUP_PROC = 'GP5')
        AND (SEG_PERIO_CORPO.COD_PERI = PRI_CAMPA_PROCE.CAM_PROC)
        AND (PED_SOLIC_CABEC.OID_SOLI_CABE = PRI_PEDID.NUM_PEDI)
        AND (PED_SOLIC_CABEC.IND_OC = 1)
        AND (PED_TIPO_SOLIC.IND_DEVO = 0)
        AND (PED_TIPO_SOLIC.IND_ANUL = 0)
       )
    ORDER BY OID_SOLI_CABE;

rrCabeceraPedido ccCabeceraPedido%ROWTYPE;

BEGIN

OPEN ccCabeceraPedido;
   LOOP
 FETCH ccCabeceraPedido INTO rrCabeceraPedido;
 EXIT WHEN ccCabeceraPedido%NOTFOUND;
   BEGIN
       /* Se actualizan los valor referentes a la Facturacion GP5: los cuales son:
      * el Numero de la Factura y la Fecha de Facturacion
      */
      UPDATE PRI_PEDID
      SET
      FEC_FACT = rrCabeceraPedido.fec_fact,
      NUM_FACT = rrCabeceraPedido.val_nume_soli,
      NUM_SECU_FACT_DIAR = rrCabeceraPedido.num_secu_fact_diar,
      NUM_SECU_ZONA_RUTA = rrCabeceraPedido.num_secu_zona_ruta
      WHERE PAIS_COD_PAIS = rrCabeceraPedido.cod_pais
      AND NUM_PEDI = rrCabeceraPedido.oid_soli_cabe;
   END;
   END LOOP;
CLOSE ccCabeceraPedido;

-- Invocamos al procedimiento que vuelve a bloquear a  las consultoras
-- con bloqueo de boleta de recojo que han sido desbloqueadas previamente
PRI_PR_BLOQU_CLIEN_PREMI(p_codigoPais);

END PRI_PR_ACTUA_PEDID_FACTU;


PROCEDURE PRI_PR_CAPTU_DATOS_CONSU (p_oid_clie number,
                                 v_cod_esta_clie OUT varchar2,
                                 v_num_docu_iden OUT varchar2,
                                 v_val_text_comu OUT varchar2,
                                 v_nom_clie  OUT varchar2,
                                 v_cod_clie  OUT varchar2,
                                 v_cod_zona  OUT varchar2,
                                 v_cod_regi  OUT varchar2,
                                 v_cod_subg_vent  OUT varchar2 ) IS
   contador number;
   numTelefonoFijo VARCHAR2(100);
   numTelefonoCelular VARCHAR2(100);

   -- Definimos los caracteres a filtrar y reemplazar por blancos
   searchStr  VARCHAR2(100) := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
   replaceStr VARCHAR2(100) := 'a        ';

BEGIN
 -- estado de la consultora
  SELECT COUNT(COD_ESTA_CLIE)
  INTO contador
  FROM MAE_ESTAT_CLIEN a, MAE_CLIEN_DATOS_ADICI b
  where a.ESTA_OID_ESTA_CLIE = OID_ESTA_CLIE
  AND CLIE_OID_CLIE=P_OID_CLIE;
  IF contador= 1 THEN
    SELECT E.COD_ESTA_EQUI INTO v_cod_esta_clie
    FROM MAE_ESTAT_CLIEN a, MAE_CLIEN_DATOS_ADICI b, PRI_ESTAT_CLIEN_EQUIV E
    WHERE a.ESTA_OID_ESTA_CLIE = a.OID_ESTA_CLIE
    AND b.CLIE_OID_CLIE = p_oid_clie
    AND E.COD_ESTA_CLIE = a.COD_ESTA_CLIE;
  END IF;

 --Numero de documento de la consultora:
  SELECT COUNT(NUM_DOCU_IDEN)
  INTO contador
  FROM MAE_CLIEN_IDENT
  WHERE CLIE_OID_CLIE= p_oid_clie
  AND VAL_IDEN_DOCU_PRIN = 1;
  IF contador = 1 THEN
      SELECT TRIM(TRANSLATE(NUM_DOCU_IDEN, searchStr, replaceStr))
      INTO V_NUM_DOCU_IDEN
      FROM MAE_CLIEN_IDENT
      WHERE CLIE_OID_CLIE= p_oid_clie
      AND VAL_IDEN_DOCU_PRIN = 1;
  END IF;

--Telefono de la consultora:
  BEGIN
       numTelefonoFijo := TRIM(TRANSLATE(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(p_oid_clie, 'TF'), searchStr, replaceStr));
  EXCEPTION
  WHEN no_data_found THEN
       numTelefonoFijo:= '';
  END;

  BEGIN
    numTelefonoCelular := TRIM(TRANSLATE(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(p_oid_clie, 'TM'), searchStr, replaceStr));
  EXCEPTION
  WHEN no_data_found THEN
       numTelefonoCelular:= '';
  END;

  IF numTelefonoFijo IS NOT NULL THEN
      IF numTelefonoCelular IS NOT NULL THEN
       v_val_text_comu := numTelefonoFijo ||'/'||numTelefonoCelular;
   ELSE
       v_val_text_comu := numTelefonoFijo;
   END IF;
  ELSE
      IF numTelefonoCelular IS NOT NULL THEN
       v_val_text_comu := numTelefonoCelular;
      END IF;
  END IF;

  IF v_val_text_comu IS NOT NULL THEN
      v_val_text_comu := SUBSTR(TRIM(TRANSLATE(v_val_text_comu, searchStr, replaceStr)), 1, 100);
  END IF;


--- nombre y Codigo de consultora:
  SELECT COUNT(1) INTO contador FROM MAE_CLIEN WHERE OID_CLIE= p_oid_clie;
  IF contador= 1 THEN
   SELECT TRIM(TRANSLATE(VAL_NOM1||' '||VAL_NOM2||' '||VAL_APE1||' '||VAL_APE2, searchStr, replaceStr)), COD_CLIE
   INTO v_nom_clie, v_cod_clie FROM
   MAE_CLIEN WHERE  OID_CLIE= p_oid_clie;
  END IF;

-- Codigo de zOna,region y subgerencia:
   SELECT COUNT(1) INTO contador FROM
   MAE_CLIEN_UNIDA_ADMIN a, ZON_TERRI_ADMIN b , ZON_SECCI c, ZON_ZONA d,
   ZON_REGIO e, ZON_SUB_GEREN_VENTA f
   WHERE a.CLIE_OID_CLIE=p_oid_clie and a.ZTAD_OID_TERR_ADMI = b.OID_TERR_ADMI and b.ZSCC_OID_SECC=c.OID_SECC and c.ZZON_OID_ZONA=d.OID_ZONA
   AND  d.ZORG_OID_REGI=e.OID_REGI AND e.ZSGV_OID_SUBG_VENT=f.OID_SUBG_VENT
   -- se agrego estos indices activos
    AND a.ind_acti =1
    AND c.ind_acti =1
    AND d.ind_acti =1
    AND e.ind_acti =1
    AND f.ind_acti=1;

  IF contador= 1 then
    SELECT COD_ZONA,COD_REGI,COD_SUBG_VENT INTO v_cod_zona, v_cod_regi, v_cod_subg_vent FROM
    MAE_CLIEN_UNIDA_ADMIN a, ZON_TERRI_ADMIN b , ZON_SECCI c, ZON_ZONA d,
    ZON_REGIO e, ZON_SUB_GEREN_VENTA f
    WHERE a.CLIE_OID_CLIE=p_oid_clie  AND a.ZTAD_OID_TERR_ADMI = b.OID_TERR_ADMI AND b.ZSCC_OID_SECC=c.OID_SECC AND c.ZZON_OID_ZONA=d.OID_ZONA
    AND  d.ZORG_OID_REGI=e.OID_REGI and e.ZSGV_OID_SUBG_VENT=f.OID_SUBG_VENT
    -- se agrego estos indices activos
    AND a.ind_acti =1
    AND c.ind_acti =1
    AND d.ind_acti =1
    AND e.ind_acti =1
    AND f.ind_acti=1;
  END IF;
END;

/******** Procedimiento que genera el nuevo codigod e Privilege para las cosnultoras de test facial *********/
PROCEDURE PRI_PR_CODIG_CLIEN(p_codigoPais IN VARCHAR2, p_NewCodCliente OUT VARCHAR2, p_MensajeError OUT VARCHAR2 )
IS
    p_CorrelCliente VARCHAR2(10);
    p_DigitoModOnce VARCHAR2(1);
    p_Valor  NUMBER;

BEGIN
    p_NewCodCliente := '';
    p_MensajeError := '';

    BEGIN
      --Obtiene el correlativo actual
      SELECT COR_CLIE_PRIV
      INTO   p_CorrelCliente
      FROM   BAS_PAIS
      WHERE  COD_PAIS = p_CodigoPais
      FOR UPDATE OF COR_CLIE_PRIV;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
      p_MensajeError := 'Existen problemas con el Pais, por favor verificar Datos Basicos';
      RETURN;
    END;

    IF ( p_CorrelCliente IS NULL OR p_CorrelCliente = '' ) THEN
      p_MensajeError := 'No existe correlativo para este Pais';
    END IF;

    p_CorrelCliente := TO_CHAR(TO_NUMBER(p_CorrelCliente) + 1);

    p_DigitoModOnce := PRI_FN_DIGIT_MODUL_ONCE(p_CorrelCliente);
    LOOP
     EXIT WHEN p_DigitoModOnce IS NOT NULL;
     p_Valor := TO_NUMBER(p_CorrelCliente) + 1;
     p_CorrelCliente := LPAD(TO_CHAR(p_Valor),9,'0');
     p_DigitoModOnce := PRI_FN_DIGIT_MODUL_ONCE(p_CorrelCliente);
    END LOOP;

    p_NewCodCliente := p_CorrelCliente || p_DigitoModOnce;

    --Actualiza el correlativo actual
    UPDATE BAS_PAIS
    SET    COR_CLIE_PRIV = p_CorrelCliente
    WHERE  COD_PAIS = p_CodigoPais;

END PRI_PR_CODIG_CLIEN;

FUNCTION PRI_FN_DIGIT_MODUL_ONCE ( p_CorrelCliente IN VARCHAR2 )RETURN VARCHAR2
IS
  p_DigitoVerificador VARCHAR2(1);
  p_StringPesos VARCHAR2(9);
  i_Suma  NUMBER(4);
  i_Divide  NUMBER(4);
  i_LenPesos NUMBER(2);
  i_LenCodigo NUMBER(2);
  i_DigitoTemp1 VARCHAR2(1);
  i_DigitoTemp2 VARCHAR2(1);

BEGIN

    p_StringPesos := '432765432';
    i_Suma := 0;
    i_Divide := 0;
    i_LenPesos := LENGTH(p_StringPesos);
    i_LenCodigo := LENGTH(p_CorrelCliente);

    p_DigitoVerificador := NULL;

    /*  Suma de los productos parciales    */
    FOR i IN 0 .. i_LenCodigo-1 LOOP
     i_DigitoTemp1 := SUBSTR(p_CorrelCliente,i_LenCodigo-i,1);
     i_DigitoTemp2 := SUBSTR(p_StringPesos,i_LenPesos-i,1);
     i_Suma := i_Suma +  ( TO_NUMBER(i_DigitoTemp1) * TO_NUMBER(i_DigitoTemp2) );
    END LOOP;
    /*     Division entre 11     */
    i_Divide := TRUNC(i_Suma / 11);
    /*   Parte entera por 11     */
    i_Divide := i_Divide * 11;

    IF ( i_Suma - i_Divide != 1 ) THEN
     IF ( i_Suma - i_Divide = 0 ) THEN
      p_DigitoVerificador := '0';
     ELSE
      p_DigitoVerificador := TO_CHAR(11-(i_Suma-i_Divide));
     END IF;
    END IF;

    RETURN p_DigitoVerificador;

END PRI_FN_DIGIT_MODUL_ONCE;

PROCEDURE PRI_PR_CARGA_EMAIL_CONSU IS
  CURSOR c_emailins IS
    SELECT mae_clco_seq.NEXTVAL,
           oid_cons,
           3,
           eml_cons,
           0,
           SYSDATE
      FROM pri_email_consu e
     WHERE NOT EXISTS (SELECT 1
              FROM mae_clien_comun c
             WHERE c.clie_oid_clie = e.oid_cons
               AND c.ticm_oid_tipo_comu = 3);

  CURSOR c_emailupd IS
    SELECT c.oid_clie_comu,
           e.oid_cons,
           3,
           e.eml_cons,
           0,
           SYSDATE
      FROM pri_email_consu e,
           mae_clien_comun c
     WHERE c.clie_oid_clie = e.oid_cons
       AND c.ticm_oid_tipo_comu = 3;

  TYPE t_oid_clie_comu IS TABLE OF mae_clien_comun.oid_clie_comu%TYPE;
  TYPE t_clie_oid_clie IS TABLE OF mae_clien_comun.clie_oid_clie %TYPE;
  TYPE t_ticm_oid_tipo_comu IS TABLE OF mae_clien_comun.ticm_oid_tipo_comu%TYPE;
  TYPE t_val_text_comu IS TABLE OF mae_clien_comun.val_text_comu %TYPE;
  TYPE t_ind_comu_ppal IS TABLE OF mae_clien_comun.ind_comu_ppal %TYPE;
  TYPE t_fec_ulti_actu IS TABLE OF mae_clien_comun.fec_ulti_actu %TYPE;

  v_oid_clie_comu      t_oid_clie_comu;
  v_clie_oid_clie      t_clie_oid_clie;
  v_ticm_oid_tipo_comu t_ticm_oid_tipo_comu;
  v_val_text_comu      t_val_text_comu;
  v_ind_comu_ppal      t_ind_comu_ppal;
  v_fec_ulti_actu      t_fec_ulti_actu;

  rows NATURAL := 5000; -- Numero de filas a procesar cada vez
  i    BINARY_INTEGER := 0;
  j    BINARY_INTEGER := 0;
  ls_sqlerrm VARCHAR2(1500);
BEGIN

  OPEN c_emailins;
  LOOP
    FETCH c_emailins BULK COLLECT
      INTO v_oid_clie_comu, v_clie_oid_clie, v_ticm_oid_tipo_comu,
           v_val_text_comu, v_ind_comu_ppal, v_fec_ulti_actu LIMIT rows;

    IF v_oid_clie_comu.COUNT > 0 THEN

      FORALL i IN 1 .. v_oid_clie_comu.COUNT

        INSERT INTO mae_clien_comun
          (oid_clie_comu,
           clie_oid_clie,
           ticm_oid_tipo_comu,
           val_text_comu,
           ind_comu_ppal,
           fec_ulti_actu)
        VALUES
          (v_oid_clie_comu(i),
           v_clie_oid_clie(i),
           v_ticm_oid_tipo_comu(i),
           v_val_text_comu(i),
           v_ind_comu_ppal(i),
           v_fec_ulti_actu(i));

    END IF;

    EXIT WHEN c_emailins%NOTFOUND;

  END LOOP;
  CLOSE c_emailins;

  OPEN c_emailupd;
  LOOP
    FETCH c_emailupd BULK COLLECT
      INTO v_oid_clie_comu, v_clie_oid_clie, v_ticm_oid_tipo_comu,
           v_val_text_comu, v_ind_comu_ppal, v_fec_ulti_actu LIMIT rows;

    IF v_oid_clie_comu.COUNT > 0 THEN

      FORALL j IN 1 .. v_oid_clie_comu.COUNT

        UPDATE mae_clien_comun
           SET val_text_comu = v_val_text_comu(j),
               fec_ulti_actu = v_fec_ulti_actu(j)
         WHERE oid_clie_comu = v_oid_clie_comu(j)
           AND ticm_oid_tipo_comu = v_ticm_oid_tipo_comu(j);

    END IF;

    EXIT WHEN c_emailupd%NOTFOUND;

  END LOOP;
  CLOSE c_emailupd;

EXCEPTION
  WHEN OTHERS THEN

    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR PRI_PR_CARGA_EMAIL_CONSU: ' ||
                             ls_sqlerrm);

END PRI_PR_CARGA_EMAIL_CONSU;
/***************************************************************************************************
Descripcion         : Generar las solicitudes de atención de premios Privilege
                      Realiza la insercion en las tablas PED_SOLIC_CABEC y  PED_SOLIC_POSIC
Fecha Creacion      : 07/06/2010
Autor               : Dennys Oliva Iriarte
****************************************************************************************************/
PROCEDURE PRI_PR_GENER_SOLIC_PREMI_PRIVI(p_codigoPais      IN VARCHAR2,
                      		  				     p_codigoPeriodo   IN VARCHAR2,
                          					 		 p_tipoSolicitud   IN VARCHAR2,
                          				 			 p_subAscceso      IN VARCHAR2,
                          							 p_accesoFisico    IN VARCHAR2,
                          							 p_tipoFlete       IN VARCHAR2,
                          							 p_indicadorPremio IN VARCHAR2,
                          							 p_tipoPosicion    IN VARCHAR2,
                          							 p_formaPago       IN VARCHAR2,
                          							 p_numeroClientes  IN VARCHAR2,
                          							 p_fechaProceso    IN VARCHAR2,
                          							 p_codigoUsuario   IN VARCHAR2) IS

CURSOR c_cabeceras IS
      SELECT P.PAIS_COD_PAIS,
             p_codigoPeriodo CAM_PROC, --B.CAM_PROC,
             T.COD_CONS,
             to_date(p_fechaProceso,'dd/MM/YYYY')  FEC_CPED, --SYSDATE FEC_CPED,
             p_numeroClientes NUM_CLIE, --COUNT(DISTINCT T.CLIE_COD_CLIE) NUM_CLIE,
             p_tipoSolicitud TIP_SOL,
             p_subAscceso SUB_ACC,
             p_accesoFisico ACC_FIS,
             p_tipoFlete TIP_FLE
        FROM PRI_PREMI P,
             PRI_TARJE T,
             BAS_CONTR B
       WHERE P.PAIS_COD_PAIS = T.PAIS_COD_PAIS
         AND T.PAIS_COD_PAIS = B.PAIS_COD_PAIS
         AND P.TARJ_NUM_TARJ = T.NUM_TARJ
         AND B.PAIS_COD_PAIS = p_codigoPais
         AND P.STA_PREM = 'P'
         AND P.IND_PREM = p_indicadorPremio
       GROUP BY P.PAIS_COD_PAIS, B.CAM_PROC, T.COD_CONS;

CURSOR c_detalles(lsperiodoProceso VARCHAR, lscodigoConsultora VARCHAR) IS
   SELECT C.PAIS_COD_PAIS,
          C.CAM_PROC,
          T.COD_CONS,
          M.COD_UNIC_VENT,
          M.Cod_Prod,
          SUM(P.CAN_PREM) CAN_PREM,
          p_tipoPosicion TIP_POS,
          p_formaPago FOR_PAGO
     FROM BAS_CONTR C,
          PRI_TARJE T,
          PRI_PREMI P,
          PRI_MATRI M
    WHERE C.PAIS_COD_PAIS = T.PAIS_COD_PAIS
      AND T.PAIS_COD_PAIS = P.PAIS_COD_PAIS
      AND T.NUM_TARJ = P.TARJ_NUM_TARJ
      AND P.PAIS_COD_PAIS = M.PAIS_COD_PAIS
      AND P.COD_PROD = M.COD_PROD
      AND P.IND_PREM = M.IND_ESTR
      AND P.STA_PREM = 'P'
      AND P.IND_PREM = p_indicadorPremio
      AND C.PAIS_COD_PAIS = p_codigoPais
      AND C.CAM_PROC= lsperiodoProceso
  		AND T.COD_CONS = lscodigoConsultora
    GROUP BY C.PAIS_COD_PAIS, C.CAM_PROC, T.COD_CONS, M.COD_UNIC_VENT,M.Cod_Prod
    ORDER BY T.COD_CONS;


 	TYPE t_codpaiscabecera IS TABLE OF BAS_CONTR.PAIS_COD_PAIS%TYPE;
  TYPE t_camproccabecera IS TABLE OF BAS_CONTR.CAM_PROC%TYPE;
  TYPE t_codconscabecera IS TABLE OF PRI_TARJE.COD_CONS%TYPE;
  TYPE t_fecpedido 	     IS TABLE OF BAS_CONTR.FEC_PROC %TYPE;
	TYPE t_numcliente	     IS TABLE OF NUMBER;
	TYPE t_tiposolicitud   IS TABLE OF VARCHAR(10);
	TYPE t_subacceso	     IS TABLE OF VARCHAR(10);
	TYPE t_accesofisico	   IS TABLE OF VARCHAR(10);
	TYPE t_tipoflete	     IS TABLE OF VARCHAR(10);

	v_codpaiscabecera    t_codpaiscabecera;
  v_camproccabecera    t_camproccabecera;
  v_codconscabecera    t_codconscabecera;
	v_fecpedido					 t_fecpedido;
	v_numcliente				 t_numcliente;
	v_tiposolicitud			 t_tiposolicitud;
	v_subacceso					 t_subacceso;
	v_accesofisico			 t_accesofisico;
	v_tipoflete					 t_tipoflete;

	TYPE t_codpais IS TABLE OF BAS_CONTR.PAIS_COD_PAIS%TYPE;
  TYPE t_camproc IS TABLE OF BAS_CONTR.CAM_PROC%TYPE;
  TYPE t_codcons IS TABLE OF PRI_TARJE.COD_CONS%TYPE;
  TYPE t_codvent IS TABLE OF PRI_MATRI.COD_UNIC_VENT%TYPE;
  TYPE t_codprod IS TABLE OF PRI_MATRI.Cod_Prod%TYPE;
  TYPE t_canprem IS TABLE OF PRI_PREMI.CAN_PREM%TYPE;
  TYPE t_tipopos IS TABLE OF VARCHAR(10);
	TYPE t_forpago IS TABLE OF VARCHAR(10);

  v_codpais      t_codpais;
  v_camproc      t_camproc;
  v_codcons      t_codcons;
  v_codvent      t_codvent;
  v_codprod      t_codprod;
  v_canprem      t_canprem;
  v_tipopos      t_tipopos;
  v_forpago      t_forpago;

  rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  i    BINARY_INTEGER := 0;
	j    BINARY_INTEGER := 0;

  varOidCabe         PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
  varNumeSoli        NUMBER;
  varTspa            ped_tipo_solic_pais.OID_TIPO_SOLI_PAIS%TYPE;
  varTipoDocum       ped_tipo_solic_pais.TIDO_OID_TIPO_DOCU%TYPE;
  varFormaPago       ped_tipo_solic_pais.FOPA_OID_FORM_PAGO%TYPE;
  varClaseSolic 	   ped_clase_solic.OID_CLAS_SOLI%TYPE;
  varOidAlmac	  	   ped_tipo_solic_pais.ALMC_OID_ALMA%TYPE;
  vartsolOidTipoCons ped_tipo_solic_pais.TSOL_OID_TIPO_CONS%TYPE;
  varOidPais		     ped_solic_cabec.PAIS_OID_PAIS%TYPE;
  varOidTerrAdmin	   ped_solic_cabec.ZTAD_OID_TERR_ADMI%TYPE;
  varSubtipoClie	   ped_solic_cabec.SBTI_OID_SUBT_CLIE%TYPE;
  varTipoClie		     ped_solic_cabec.TICL_OID_TIPO_CLIE%TYPE;
  varTipoDocum2	     mae_tipo_docum.TIDO_OID_TIPO_DOCU%TYPE;
  varOidDireClie	   mae_clien_direc.OID_CLIE_DIRE%TYPE;
  varOidZonClie	     ped_solic_cabec.ZZON_OID_ZONA%TYPE;
  varIndExenFlete	   ped_solic_cabec.IND_EXEN_FLET%TYPE;
  varTipoDocIdent	   mae_tipo_docum.OID_TIPO_DOCU%TYPE;
  varSocie		       ped_solic_cabec.SOCI_OID_SOCI%TYPE;
  varTerri		       ped_solic_cabec.TERR_OID_TERR%TYPE;
  varVepo			       ped_solic_cabec.VEPO_OID_VALO_ESTR_GEOP%TYPE;
  varTipoConcu	     PED_TIPOS_SOLIC_PROGR_INCEN.ICTP_OID_TIPO_PROG%TYPE;
  varSBAC_OID_SBAC   ped_solic_cabec.SBAC_OID_SBAC%TYPE;
  varOidCliente	     MAE_CLIEN.OID_CLIE%TYPE;
  varOidPeriodo	     cra_perio.PERI_OID_PERI%TYPE;

  varOidProd		     pre_ofert_detal.PROD_OID_PROD%TYPE;
  varOidFopa 		     pre_ofert_detal.FOPA_OID_FORM_PAGO%TYPE;
  varOidDetaOfer	   pre_ofert_detal.OID_DETA_OFER%TYPE;
  varFactRepe		     pre_ofert_detal.VAL_FACT_REPE%TYPE;
  varPrecioUnitario  pre_ofert_detal.PRECIO_UNITARIO%TYPE;
  varPrecPosi		     pre_ofert_detal.IMP_PREC_POSI%TYPE;

  is_ok boolean;

  lsindicadorstock BAS_PAIS.Ind_Stoc_Sicc%TYPE;

  varTipoCambio    PRE_MATRI_FACTU_CABEC.Val_Tipo_Camb%type;
  v_ind_dent_caja  MAE_PRODU.Cod_Ind_Dent_Caja%type;
  v_oid_tasa_impu  ped_tasa_impue.oid_tasa_impu%type;
  v_val_tasa_impu  ped_tasa_impue.val_tasa_impu%type;


BEGIN
  -- Obtiene el tipo de cambio para el periodo seleccionado
  begin
      select pm.val_tipo_camb
        into varTipoCambio
        from PRE_MATRI_FACTU_CABEC pm
       where pm.perd_oid_peri = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_codigoPeriodo);
  exception
    when no_data_found then
      varTipoCambio := 0;
  end;

  -- Obtiene el OID y la Tasa de impuesto
  begin
    select ti.oid_tasa_impu,
           ti.val_tasa_impu
      into v_oid_tasa_impu,
           v_val_tasa_impu
      from ped_tasa_impue ti,
           ped_impue_gener ig
     where ti.pais_oid_pais = ig.pais_oid_pais
       and ti.oid_tasa_impu = ig.taim_oid_tasa_impu
       and ig.sbac_oid_sbac = 888;
  exception
    when no_data_found then
       v_oid_tasa_impu := null;
       v_val_tasa_impu := null;
  end;

    -- Eliminamos solicitudes que se encuentren en GP3 para
    -- permitir que el proceso se pueda ejecutar repetidas veces
    -- Primero eliminamos las lineas
    delete from ped_solic_posic det
    where exists (
        select null
        from ped_solic_cabec psc,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic ts
        where psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
          and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
          and ts.cod_tipo_soli = p_tipoSolicitud
          and psc.fec_prog_fact = to_date(p_fechaProceso, 'dd/mm/yyyy')
          and psc.perd_oid_peri = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_codigoPeriodo)
          and psc.grpr_oid_grup_proc = 3 -- GP3
          and psc.oid_soli_cabe = det.soca_oid_soli_cabe
    );

    delete from ped_solic_cabec cab
    where exists (
        select null
        from ped_solic_cabec psc,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic ts
        where psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
          and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
          and ts.cod_tipo_soli = p_tipoSolicitud
          and psc.fec_prog_fact = to_date(p_fechaProceso, 'dd/mm/yyyy')
          and psc.perd_oid_peri = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_codigoPeriodo)
          and psc.grpr_oid_grup_proc = 3 -- GP3
          and psc.oid_soli_cabe = cab.oid_soli_cabe
    );

	OPEN c_cabeceras;
    LOOP
      FETCH c_cabeceras BULK COLLECT INTO v_codpaiscabecera,
                                          v_camproccabecera,
                                          v_codconscabecera,
                                          v_fecpedido,
                                          v_numcliente,
                                          v_tiposolicitud,
                                          v_subacceso,
                                          v_accesofisico,
                                          v_tipoflete
                                     LIMIT rows;

      IF v_codpaiscabecera.COUNT > 0 THEN
	  	 FOR j IN v_codpaiscabecera.FIRST .. v_codpaiscabecera.LAST
         LOOP
  				is_ok := true;
          varOidCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(v_codconscabecera(j));
				  varOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(v_camproccabecera(j));

          SELECT ped_soca_seq.nextval INTO varOidCabe FROM DUAL;

  				BEGIN
            SELECT to_char(sysdate, 'yy') || lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
    			    INTO  varNumeSoli
    			    FROM  ped_numer_solic
    			    WHERE val_oper = 'PED001'
    			          and cod_cana = 'VD'
    			          and cod_acce = p_accesoFisico
    			          and cod_suba = p_subAscceso
    			          and cod_pais = p_codigoPais
    			          and val_anio = to_char(to_number(to_char(SYSDATE,'YY')));
             EXCEPTION
              WHEN no_data_found THEN
                varNumeSoli := NULL;
          END;

          UPDATE ped_numer_solic
             SET VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 2
           WHERE val_oper = 'PED001'
             and cod_cana = 'VD'
             and cod_acce = p_accesoFisico
             and cod_suba = p_subAscceso
             and cod_pais = p_codigoPais
             and val_anio = to_char(to_number(to_char(SYSDATE, 'YY')));


          SELECT d.OID_TIPO_SOLI_PAIS,
                 d.TIDO_OID_TIPO_DOCU
            INTO varTspa,
                 varTipoDocum
            FROM ped_tipo_solic_pais d,
                 ped_tipo_solic e
           WHERE e.COD_TIPO_SOLI = p_tipoSolicitud
             AND d.TSOL_OID_TIPO_SOLI = e.OID_TIPO_SOLI;


          SELECT a.FOPA_OID_FORM_PAGO,
                 d.OID_CLAS_SOLI,
                 a.ALMC_OID_ALMA,
                 a.TSOL_OID_TIPO_CONS
            INTO varFormaPago,
                 varClaseSolic,
                 varOidAlmac,
                 vartsolOidTipoCons
            FROM ped_tipo_solic_pais a,
                 ped_tipo_solic c,
                 ped_clase_solic d
           WHERE a.OID_TIPO_SOLI_PAIS = varTspa
             and a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
             and c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

          begin
            SELECT a.PAIS_OID_PAIS,
                   a.ZTAD_OID_TERR_ADMI,
                   a.SBTI_OID_SUBT_CLIE,
                   a.TICL_OID_TIPO_CLIE,
                   d.TIDO_OID_TIPO_DOCU,
                   b.OID_CLIE_DIRE,
                   a.ZZON_OID_ZONA,
                   a.IND_EXEN_FLET,
                   d.OID_TIPO_DOCU,
                   a.SOCI_OID_SOCI,
                   a.SBAC_OID_SBAC,
                   a.TERR_OID_TERR,
                   a.VEPO_OID_VALO_ESTR_GEOP
              INTO varOidPais,
                   varOidTerrAdmin,
                   varSubtipoClie,
                   varTipoClie,
                   varTipoDocum2, --- REEMPLA a varTipoDocum2
                   varOidDireClie,
                   varOidZonClie,
                   varIndExenFlete,
                   varTipoDocIdent,
                   varSocie,
                   varSBAC_OID_SBAC,
                   varTerri,
                   varVepo
              FROM ped_solic_cabec a,
                   mae_clien_direc b,
                   mae_clien_ident c,
                   mae_tipo_docum  d
             WHERE a.CLIE_OID_CLIE = c.CLIE_OID_CLIE
               AND c.TDOC_OID_TIPO_DOCU = d.OID_TIPO_DOCU
               AND c.VAL_IDEN_DOCU_PRIN = 1
               AND a.ind_oc = 1
               AND a.grpr_oid_grup_proc = 4
               AND a.perd_oid_peri = varOidPeriodo
               AND a.clie_oid_clie = varOidCliente
               AND a.clie_oid_clie = b.clie_oid_clie
               AND b.ind_elim = 0
               AND b.ind_dire_ppal = 1;
          exception
            when no_data_found then
                 is_ok := false;
          end;


         SELECT ICTP_OID_TIPO_PROG
           INTO varTipoConcu
           FROM PED_TIPOS_SOLIC_PROGR_INCEN e
          WHERE e.TSPA_OID_TIPO_SOLI_PAIS = varTspa;

         if is_ok then

           INSERT INTO PED_SOLIC_CABEC (OID_SOLI_CABE,
                                        FEC_PROG_FACT,
                                        TSPA_OID_TIPO_SOLI_PAIS,
                                        TIDS_OID_TIPO_DESP,
                                        ALMC_OID_ALMA,
                                        MODU_OID_MODU,
                                        TICL_OID_TIPO_CLIE,
                                        PERD_OID_PERI,
                                        CLIE_OID_CLIE,
                                        CLIE_OID_CLIE_RECE_FACT,
                                        CLIE_OID_CLIE_PAGA,
                                        CLIE_OID_CLIE_DEST,
                                        CLDI_OID_CLIE_DIRE,
                                        TDOC_OID_TIPO_DOCU,
                                        SOCI_OID_SOCI,
                                        SBAC_OID_SBAC, -- *****
                                        TERR_OID_TERR,
                                        ZZON_OID_ZONA,
                                        VAL_NUME_SOLI, -- *****
                                        VAL_USUA,
                                        VAL_TASA_IMPU,
                                        FEC_CRON,
                                        IND_PERM_UNIO_SOL,
                                        NUM_DOCU_ORIG,
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
                                        IND_OC,
                                        IND_PEDI_PRUE,
                                        IND_TS_NO_CONSO,
                                        NUM_PREM,
                                        VAL_IMPO_DESC_3_TOTA_LOCA,
                                        VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
                                        PAIS_OID_PAIS, -- *****
                                        TIDO_OID_TIPO_DOCU,
                                        VEPO_OID_VALO_ESTR_GEOP,
                                        ESSO_OID_ESTA_SOLI,
                                        COPA_OID_PARA_GENE,
                                        GRPR_OID_GRUP_PROC,
                                        SBTI_OID_SUBT_CLIE,
                                        TSPA_OID_TIPO_SOLI_PAIS_CONS,
                                        FOPA_OID_FORM_PAGO,
                                        CLSO_OID_CLAS_SOLI,
                                        ZTAD_OID_TERR_ADMI,
                                        OPER_OID_OPER,
                                        PROC_OID_PROC,
                                        SOCA_OID_DOCU_REFE,
                                        IND_INTE_LARI_GENE,
                                        ICTP_OID_TIPO_PROG,
                                        IND_EXEN_FLET,
                                        VAL_TIPO_CAMB,
                                        VAL_GLOS_OBSE )
                                 VALUES(varOidCabe,
                                        v_fecpedido(j),
                                        /*(select fec_proc
                                           from bas_ctrl_fact
                                          where ind_camp_act = 1
                                            and sta_camp = 0),*/ --TO_DATE(p_fechaProceso,'DD/MM/YYYY'),
                                        varTspa,
                                        3,
                                        NVL(varOidAlmac, 2001),
                                        27,  -- OID INCENTIVOS
                                        varTipoClie,
                                        varOidPeriodo, --< oid de periodo de proceso >, ???????
                                        varOidCliente,
                                        varOidCliente,
                                        varOidCliente,
                                        varOidCliente,
                                        varOidDireClie,
                                        varTipoDocIdent,
                                        varSocie,
                                        varSBAC_OID_SBAC, -- *****
                                        varTerri,
                                        varOidZonClie, --varZona,
                                        varNumeSoli, -- CODIGO NEW SOLICITUD  -- *****
                                        p_codigoUsuario, --< código de usuario >,
                                        0,
                                        TRUNC(sysdate),
                                        1,
                                        NULL,
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
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0, -- debe ir en 0
                                        0,
                                        1,
                                        NULL,
                                        0,
                                        0,
                                        varOidPais,
                                        decode(varTipoDocum, null, varTipoDocum2, varTipoDocum),
                                        varVepo,
                                        1,
                                        NULL,
                                        3,
                                        varSubtipoClie,
                                        vartsolOidTipoCons, --varTipoSoliCons,
                                        --varFormaPago,
                                        nvl(nvl(varFormaPago,(select fopa_oid_form_pago from mae_clien where oid_clie=varOidCliente)),(select fopa_oid_form_pago from seg_pais where oid_pais=varOidPais)),
                                        varClaseSolic,
                                        varOidTerrAdmin,
                                        21,
                                        1,
                                        NULL,
                                        0,
                                        varTipoConcu,
                                        varIndExenFlete,
                                        varTipoCambio,
                                        'TRANSFERENCIA GRATUITA');


  			    OPEN c_detalles(v_camproccabecera(j),v_codconscabecera(j));
  			    LOOP
  			      FETCH c_detalles BULK COLLECT INTO v_codpais,
                                                 v_camproc,
                                                 v_codcons,
                                                 v_codvent,
                                                 v_codprod,
                                                 v_canprem,
                                                 v_tipopos,
                                                 v_forpago
                                            LIMIT rows;
  			      IF v_codpais.COUNT > 0 THEN
  				  	 FOR i IN v_codpais.FIRST .. v_codpais.LAST
  			         LOOP
                    SELECT a.prod_oid_prod,
                           a.fopa_oid_form_pago,
                           a.oid_deta_ofer,
                           a.val_fact_repe,
                           a.precio_unitario,
                           a.imp_prec_posi
                      INTO varOidProd,
                           varOidFopa,
                           varOidDetaOfer,
                           varFactRepe,
                           varPrecioUnitario,
                           varPrecPosi
                      FROM pre_ofert_detal       a,
                           pre_ofert             b,
                           pre_matri_factu_cabec c
                     WHERE a.ofer_oid_ofer = b.oid_ofer
                       AND b.mfca_oid_cabe = c.oid_cabe
                       AND c.perd_oid_peri = varOidPeriodo
                       AND a.val_codi_vent = v_codvent(i);

                       begin
                           select mp.cod_ind_dent_caja
                             into v_ind_dent_caja
                             from mae_produ mp
                            where mp.oid_prod = gen_pkg_gener.GEN_FN_DEVUELVE_ID_PRODU(v_codprod(i));
                       exception
                         when no_data_found then
                           v_ind_dent_caja := null;
                       end;

               INSERT INTO PED_SOLIC_POSIC (OID_SOLI_POSI,
                                            COD_POSI,
                                            NUM_UNID_DEMA,
                                            NUM_UNID_POR_ATEN,
                                            VAL_TASA_IMPU,
                                            SOCA_OID_SOLI_CABE,
                                            TPOS_OID_TIPO_POSI,
                                            PROD_OID_PROD,
                                            VAL_PREC_CATA_UNIT_LOCA,
                                            VAL_PREC_CONT_UNIT_LOCA,
                                            VAL_PREC_CATA_UNIT_DOCU,
                                            VAL_PREC_CONTA_UNIT_DOCU,
                                            VAL_PREC_FACT_UNIT_LOCA,
                                            VAL_PREC_FACT_UNIT_DOCU,
                                            VAL_PREC_SIN_IMPU_UNIT_LOCA,
                                            VAL_PREC_SIN_IMPU_UNIT_DOCU,
                                            VAL_PREC_SIN_IMPU_TOTA_DOCU,
                                            VAL_IMPO_DESC_UNIT_LOCA,
                                            VAL_IMPO_DESC_UNIT_DOCU,
                                            VAL_PREC_NETO_UNIT_LOCA,
                                            VAL_PREC_NETO_TOTA_DOCU,
                                            VAL_PREC_NETO_UNIT_DOCU,
                                            VAL_PREC_TOTA_TOTA_LOCA,
                                            VAL_PREC_TOTA_TOTA_DOCU,
                                            VAL_IMPO_IMPU_UNIT_LOCA,
                                            VAL_IMPO_IMPU_UNIT_DOCU,
                                            VAL_IMPO_DESC_TOTA_DOCU,
                                            VAL_IMPO_IMPU_TOTA_LOCA,
                                            VAL_IMPO_IMPU_TOTA_DOCU,
                                            VAL_IMPO_DESC_TOTA_LOCA,
                                            VAL_PREC_TOTA_UNIT_LOCA,
                                            VAL_PREC_TOTA_UNIT_DOCU,
                                            VAL_PREC_CONT_TOTA_LOCA,
                                            VAL_PREC_CATA_TOTA_LOCA,
                                            VAL_PREC_CATA_TOTA_DOCU,
                                            VAL_PREC_CONT_TOTA_DOCU,
                                            VAL_PORC_DESC,
                                            VAL_PREC_CATA_TOTA_LOCA_UNID,
                                            NUM_UNID_DEMA_REAL,
                                            NUM_UNID_COMPR,
                                            VAL_CODI_VENT_FICT,
                                            VAL_PREC_FACT_TOTA_LOCA,
                                            VAL_PREC_FACT_TOTA_DOCU,
                                            VAL_PREC_SIN_IMPU_TOTA_LOCA,
                                            VAL_PREC_NETO_TOTA_LOCA,
                                            OFDE_OID_DETA_OFER,
                                            ESPO_OID_ESTA_POSI,
                                            STPO_OID_SUBT_POSI,
                                            VAL_CODI_VENT,
                                            FOPA_OID_FORM_PAGO,
                                            IND_DENT_FUER_CAJA_BOLS,
                                            TAIM_OID_TASA_IMPU)
                                    VALUES (ped_sopo_seq.nextval,
                                            i,
                                            v_canprem(i) * varFactRepe,
                                            v_canprem(i) * varFactRepe,
                                            v_val_tasa_impu,--0,
                                            varOidCabe,
                                            17,
                                            varOidProd,
                                            varPrecioUnitario,
                                            decode(varPrecioUnitario, 0, varPrecPosi, 0),
                                            0, --w.val_prec_cata_unit_loca,
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
                                            0,
                                            0,
                                            0,
                                            0,
                                            0,
                                            v_canprem(i) * varFactRepe,
                                            0,--v_canprem(i) * varFactRepe,
                                            NULL,
                                            0,
                                            0,
                                            0,
                                            0,
                                            varOidDetaOfer,
                                            4,
                                            20,
                                            v_codvent(i),
                                            varOidFopa,
                                            v_ind_dent_caja,
                                            v_oid_tasa_impu);
  				     END LOOP;
  				  END IF;
  			    EXIT WHEN c_detalles%NOTFOUND;
  			   END LOOP;
  			 CLOSE c_detalles;
       END IF;
	END LOOP;
	END IF;
  EXIT WHEN c_cabeceras%NOTFOUND;
 END LOOP;
CLOSE c_cabeceras;
SELECT ind_stoc_sicc
  INTO lsindicadorstock
  FROM bas_pais
 WHERE cod_pais = p_codigoPais;

 if lsindicadorstock='S' then
   ped_pkg_cuadr_ofert.ped_pr_stock;
   ped_pkg_cuadr_ofert.ped_pr_flete;
 end if;


END PRI_PR_GENER_SOLIC_PREMI_PRIVI;

END;
/

