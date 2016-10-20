CREATE OR REPLACE PACKAGE "IMP_PKG_PROCE_LASER_COLOR" AS

CODIGO_CANAL VARCHAR2(10) := 'VD';
CODIGO_MARCA VARCHAR2(10) := 'T';
IMAGEN_CUMPLEANHOS       VARCHAR2(15) := 'cumpleanos.jpg';
IMAGEN_NAVIDAD           VARCHAR2(15) := 'navidad.jpg';
IMAGEN_DIAMADRE          VARCHAR2(15) := 'diaMadre.jpg';
IMAGEN_DIAMUJER          VARCHAR2(15) := 'diaMujer.jpg';
IMAGEN_ANIVERSARIO       VARCHAR2(15) := 'aniversario.jpg';
IMAGEN_MENSAJE_GENERICO  VARCHAR2(15) := 'saludo.jpg';
MES_MAYO                 VARCHAR2(2) := '05';
NRO_PAQUETE_COLOR        NUMBER := 4;


w_filas               NUMBER := 1000;


/**************************************************************************
Descripcion         : Genera TODAS LAS SECCIONES DEL PAQ DOUCMENTARIO A COLOR
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_GENER_PAQUE_DOCUM_COLOR(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera LOS SALUDOS INICALES
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_SALUD_CONSU(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);



/**************************************************************************
Descripcion         : Genera la seccion de cuenta corriente
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CUENT_CORRI(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);


/**************************************************************************
Descripcion         : Genera la seccion de ENQUE CAMPANHA
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_ENFOQ_CAMPA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccion de programas especiales
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PROGR_ESPEC(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccion de pedido ideal
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PEDID_IDEAL(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccion programa focalizado
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PROGR_FOCAL(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);


/**************************************************************************
Descripcion         : Obtiene los nombres de la imageN
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    fechaNaci          : Fecha Nacimiento
    fechaIngre         : Fecha Ingreso
    p_codigoPeriodo    : Codigo Periodo
    l_oidPeriodo       : Oid periodo
/**************************************************************************/
FUNCTION IMP_FN_OBTEN_NOMBR_IMAGE(fechaNaci VARCHAR2,
                                  fechaIngre VARCHAR2,
                                  p_codigoPeriodo VARCHAR2,
                                  l_oidPeriodo NUMBER) RETURN VARCHAR2;


/**************************************************************************
Descripcion         : Genera la seccion resumen pedidos
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_RESUM_PEDID(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccionN DE ENCUNTRO COMPARTAMOS
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_ENCUE_COMPA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccionN DE CONCURSO CERRADOS
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_CERRA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccionN DE CONCURSO DE VENTAS
Fecha Creación      :  14/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_VIGEN(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : Genera la seccionN DE CONCURSO DE VENTAS
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_VENTA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);


/***************************************************************************
Descripcion       : Funcion usada para obtener la sentencia SQL dinamica usada
                    para la compaginacion, la cual toma la parametria especificada
                    en la tabla IMP_FORMU_PAQUE_DOCUM, recibe como parametro el
                    numero de documento el cual se compara con el valor de la
                    columna NUM_PADO de la tabla anterior, asi como el orden
                    especificado en la tabla VAL_ORDE_SECU.
Fecha Creacion    : 15/12/2010
Autor             : Sergio Buchelli
Parametros Entrada  :
    p_numeroPaquete            : indicador de paquete a compaginar

***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_SQL (p_numeroPaquete NUMBER)
RETURN VARCHAR2;


/**************************************************************************
Descripcion         : elimina la informacion cargada anteriormente
Fecha Creación      : 15/12/2010
Autor               : Sergio Buchelli
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_PAQUE_DOCUM_COLOR;

/**************************************************************************
Descripcion         :  general el archivo xml en txt
Fecha Creación      : 15/12/2010
Autor               : Sergio Buchelli
Parametors
  p_nombreArchivo : Nombre Archivo
  p_directorio  : Directorio
  p_numeroDocumento :Numeros de Documento
  p_totalArchivos : Numeros d earchivo
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_COLOR_FILE(
                                     p_codigoPeriodo VARCHAR2,
                                     p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1,
                                     p_totalArchivos NUMBER := 1);

/**************************************************************************
Descripcion         : Genera la seccionN DE BIENVENIDA TRANSPROMO
Fecha Creación      :  03/03/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_BIENV_TRANS(p_codigoPais VARCHAR2,
                                   p_codigoPeriodo VARCHAR2,
                                   p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion         : elimina etiquetas de paq doc a blanco y nero
                      devuelve el clob cambiado
Fecha Creación      :  30/03/2011
Parametros Entrada  :
    xml                   : Xml de la consultora
Autor               : Sergio Buchelli
/**************************************************************************/
FUNCTION IMP_FN_ELIMI_BLOQU_PAQUE(xml IN CLOB)
RETURN CLOB;

/**************************************************************************
Descripcion         : elimina etiquetas de paq doc a blanco y nero
                      devuelve el clob cambiado
Fecha Creación      :  31/03/2011
Parametros Entrada  :
    xml             : Xml de la consultora
    replaceStr      : Correlativo  paq documentario
    replaceWith     : Numero Paq Docu
Autor               : Sergio Buchelli
/**************************************************************************/
FUNCTION IMP_FN_ELIMI_BLOQU_PAQUE (
srcClob IN CLOB,
replaceStr IN VARCHAR2,
replaceWith IN VARCHAR2) RETURN CLOB;


/******************************************************************************************
Descripcion         : Genera la SECUNECIA EN COLOR EN BASE A LA SECUENCIA DE BLANCO Y NEGRO
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/*****************************************************************************************/
PROCEDURE IMP_PR_PROCE_SECUE_COLOR(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/**************************************************************************
Descripcion        : Realiza validaciones para la carga de Seccion Compartamos
Fecha Creacion     : 28/04/2011
Parametros Entrada :
           psCodigoRegion : Codigo Region
           psCodigoZona : Codigo Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION IMP_FN_VALID_SECCI_COMPA(psCodigoRegion  VARCHAR2,
                                  psCodigoZona    VARCHAR2)
RETURN VARCHAR2;


/**************************************************************************
Descripcion        : Realiza validaciones para la carga de Seccion Focalizados
Fecha Creacion     : 02/05/2011
Parametros Entrada :
           psCodigoZona : Codigo Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION IMP_FN_VALID_SECCI_FOCAL(psCodigoZona    VARCHAR2)
RETURN VARCHAR2;

END;
/
CREATE OR REPLACE PACKAGE BODY "IMP_PKG_PROCE_LASER_COLOR" AS


/**************************************************************************
Descripcion         : Genera LOS SALUDOS INICALES
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_GENER_PAQUE_DOCUM_COLOR(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS

ls_sqlerrm   VARCHAR2(250);
-- Variables usadas para la compaginacion
l_query      VARCHAR2(4000);

BEGIN
     EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_COLOR';
     --SECCION BIENVENIDA
     IMP_PR_PROCE_BIENV_TRANS(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION SALUDO
     IMP_PR_PROCE_SALUD_CONSU(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION RESUMEN
     IMP_PR_PROCE_RESUM_PEDID(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --seccion cuenta corriente
     IMP_PR_PROCE_CUENT_CORRI(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION ENFOQUE CAMPANHA
     IMP_PR_PROCE_ENFOQ_CAMPA(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION ENCUNETRO COMPPRATMAOS
     IMP_PR_PROCE_ENCUE_COMPA(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION CONCURSOS CERRADOS
     IMP_PR_PROCE_CONCU_CERRA(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION CONCURSOS VIGENTES
     IMP_PR_PROCE_CONCU_VIGEN(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     -- SECCION CONCURSO VENTAS
     IMP_PR_PROCE_CONCU_VENTA(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION PROGRAmAS ESPECIALES
     IMP_PR_PROCE_PROGR_ESPEC(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION PEDIDO IDEAL
     IMP_PR_PROCE_PEDID_IDEAL(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);
     --SECCION PROGRAMA FOCALIZADO
     IMP_PR_PROCE_PROGR_FOCAL(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);

     -- GENERAR PAQU DOCUMENTARIO A COLOR
      l_query := NULL;
      l_query := IMP_PKG_PROCE_LASER_COLOR.IMP_FN_COMPA_XML_SQL(NRO_PAQUETE_COLOR);
      IF l_query IS NOT NULL THEN
          EXECUTE IMMEDIATE l_query;
      END IF;

     -- GENERA LOS TAGGS DE SECUENCIA EN EL PAQ DOC COLOR

     IMP_PR_PROCE_SECUE_COLOR(p_codigoPais,p_codigoPeriodo,p_fechaFacturacion);



 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_PAQUE_DOCUM_COLOR: '||substr(sqlerrm,1,250));
END IMP_PR_GENER_PAQUE_DOCUM_COLOR;


/**************************************************************************
Descripcion         : Genera LOS SALUDOS INICALES
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_SALUD_CONSU(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       TO_CHAR(mad.fec_naci,'MMdd') fec_naci,
       TO_CHAR(mc.fec_ingr,'MMdd') fec_ingr,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarSaludo       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarSaludo');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
anho                           VARCHAR2(4);
fechaNaci                      VARCHAR2(8);
fechaIngre                     VARCHAR2(8);
BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarSaludo IS NULL OR l_indicadorGenerarSaludo<>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SECCI_SALUD';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_SECCI_SALUD(
            COR_SESA,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            anho:= SUBSTR(p_codigoPeriodo, 1, 4);
            fechaNaci:= anho || r_ordenCompra.fec_naci;
            fechaIngre:= anho || r_ordenCompra.fec_ingr;

            nombreImagen:=IMP_FN_OBTEN_NOMBR_IMAGE(fechaNaci,fechaIngre,p_codigoPeriodo,l_oidPeriodo);


            -- Inicio de saludo
            l_textoActual := '<saludo>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


            -- imagen saludo
            l_textoActual := '<img>' || nombreImagen || '</img>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- nombre
            l_textoActual := '<nombre>' || r_ordenCompra.val_nom1 || ' ' || r_ordenCompra.val_nom2 || '</nombre>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


            -- Fin saludo
            l_textoActual := '</saludo>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo:=l_correlativo+1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PAQUE_DOCUM_OCS: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_SALUD_CONSU;


/**************************************************************************
Descripcion         : Genera la seccion de cuenta corriente
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CUENT_CORRI(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarCta          VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarCtaCorriente');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarCta IS NULL OR l_indicadorGenerarCta<>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SECCI_CORRI';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_SECCI_CORRI(
            COR_PDSC,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;



            -- Inicio de tag cuenta corrienet
            l_textoActual := '<frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


            -- inico de bloque
            l_textoActual := '<blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- saldo
            l_textoActual := '<saldo>' ||  CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_TOTAL( r_ordenCompra.oid_clie) || '</saldo>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- fec vencimiento

            SELECT to_char(MAX(mcc.fec_venc),'dd/MM/yyyy') into lsFecha
            FROM ccc_movim_cuent_corri mcc
            WHERE mcc.imp_pend > 0
            AND mcc.clie_oid_clie =  r_ordenCompra.oid_clie;

            l_textoActual := '<fecven>' || lsFecha || '</fecven>';

            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- fin de bloque
            l_textoActual := '</blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin
            l_textoActual := '</frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_CUENT_CORRI: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_CUENT_CORRI;



/**************************************************************************
Descripcion         : Genera la seccion de ENQUE CAMPANHA
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_ENFOQ_CAMPA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarEnfoque      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarEnfoqueCampanha');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarEnfoque IS NULL OR l_indicadorGenerarEnfoque<>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_ENFOQ_CAMPA';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_ENFOQ_CAMPA(
            COR_PDEC,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            --
            l_textoActual := '<enfoque><img>fococamp.jpg</img></enfoque>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_ENFOQ_CAMPA: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_ENFOQ_CAMPA;

/**************************************************************************
Descripcion         : Genera la seccion de programas especiales
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PROGR_ESPEC(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarProgEspe     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarProgramaEspecial');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarProgEspe IS NULL OR l_indicadorGenerarProgEspe <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PROGA_ESPEC';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_PROGA_ESPEC(
            COR_PDPE,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            --
            l_textoActual := '<programas><img>entrypoint.jpg</img></programas>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PROGR_ESPEC: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PROGR_ESPEC;


/**************************************************************************
Descripcion         : Genera la seccion de programas peddio especial
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PEDID_IDEAL(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and con.num_unid_aten_tota > 0
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarPedIdeal     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarPedidoIdeal');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarPedIdeal IS NULL OR l_indicadorGenerarPedIdeal <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PEDID_IDEAL';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_PEDID_IDEAL(
            COR_PDPI,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            --
            l_textoActual := '<pedidoIdeal><img>pedidoideal.jpg</img></pedidoIdeal>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PEDID_IDEAL: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PEDID_IDEAL;

/**************************************************************************
Descripcion         : Genera la seccion de programas focal
Fecha Creación      :  10/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_PROGR_FOCAL(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
     and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarProgFocal    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarProgramaFocalizado');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

regProgramFocal                imp_progr_focal%ROWTYPE;
imgNoFocal                     IMP_PROGR_FOCAL.NOM_IMAG_NAC2%type;
indProgramFocal                VARCHAR2(1);
BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarProgFocal IS NULL OR l_indicadorGenerarProgFocal <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;


   --obtenemos la imagen nofcal es indistinto la fila , razon por la cual tomamos el primer registro
           begin
            select NOM_IMAG_NAC2 into imgNoFocal
            from imp_progr_focal
            where rownum = 1 ;
           exception
            when others then
             imgNoFocal:=null;
           end;

    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PROGR_FOCAL';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_PROGR_FOCAL(
            COR_PDPF,
            COD_CLIE,
            XML_CONS
            )
            VALUES (
            l_correlativo,
            r_ordenCompra.cod_clie,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;


            --se debe obtener si se trata d euna imagen focalizada o no focalizada
           indProgramFocal:='1';
           begin
            select * into regProgramFocal
            from imp_progr_focal t
            where t.cod_peri = p_codigoPeriodo
             and t.cod_zona = r_ordenCompra.cod_zona
             and t.ind_acti =1;
           exception
            when others then
             indProgramFocal:='0';
           end;

            if(indProgramFocal='1') then
               --
                l_textoActual := '<progfocal><img>'||regProgramFocal.NOM_IMAG_FOCA||'</img></progfocal>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                l_textoActual := '<pnc><img>'||regProgramFocal.NOM_IMAG_NAC1||'</img></pnc>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            else
               --
                l_textoActual := '<prognofocal><img>'||imgNoFocal||'</img></prognofocal>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            end if;


            l_correlativo := l_correlativo + 1;
        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_PROGR_FOCAL: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_PROGR_FOCAL;



/**************************************************************************
Descripcion         : Obtiene los nombres de la imageN
Fecha Creación      : 09/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    fechaNaci          : Fecha Nacimiento
    fechaIngre         : Fecha Ingreso
    p_codigoPeriodo    : Codigo Periodo
    l_oidPeriodo       : Oid periodo
/**************************************************************************/
FUNCTION IMP_FN_OBTEN_NOMBR_IMAGE(fechaNaci VARCHAR2,
                                  fechaIngre VARCHAR2,
                                  p_codigoPeriodo VARCHAR2,
                                  l_oidPeriodo NUMBER) RETURN VARCHAR2
IS
 nombreImagen VARCHAR2(15):= IMAGEN_MENSAJE_GENERICO;
 lsFechaDiaMadre VARCHAR2(8);
 navidad VARCHAR2(8);
 diaMujer VARCHAR2(8);
 lsFechaIni   VARCHAR2(8);
 lsFichaFinal VARCHAR2(8);
 anho         VARCHAR2(4);
 anhoNavidad  VARCHAR2(4);
BEGIN

     anho:= SUBSTR(p_codigoPeriodo, 1, 4);--no es relevante para navidad
     --navidad:= anho || '1225';
     diaMujer:= anho || '0308';

    SELECT  to_char(MAX(FEC_CALE),'yyyyMMdd')
        into lsFechaDiaMadre
     FROM BAS_CALEN X
     WHERE X.COD_ANIO=anho
      AND TO_CHAR(FEC_CALE,'MM')=MES_MAYO
      AND NUM_POSI_SEMA=1 --dias domingo
      AND ROWNUM <= 2;


     --obteniendo el rango d efechas del peridood
     SELECT to_char(X.FEC_INIC,'yyyyMMdd'),
            to_char(X.FEC_FINA,'yyyyMMdd')
          INTO lsFechaIni,lsFichaFinal
     FROM CRA_PERIO X
     WHERE X.OID_PERI= l_oidPeriodo;

       --cumpleanhos
            if(to_number(fechaNaci) >= to_number(lsFechaIni)
              and to_number(fechaNaci) <= to_number(lsFichaFinal)) then
              nombreImagen:=IMAGEN_CUMPLEANHOS;
              RETURN nombreImagen;
            end if;

        --navidad
            anhoNavidad := SUBSTR(lsFechaIni, 1, 4);
            navidad:= anhoNavidad || '1225';
            if((to_number(navidad) >= to_number(lsFechaIni)
              and to_number(navidad) <= to_number(lsFichaFinal))
              or ('1225' = SUBSTR(lsFechaIni, 5, 4))
              or ('1225' = SUBSTR(lsFichaFinal, 5, 4))
              ) then
              nombreImagen:=IMAGEN_NAVIDAD;
              RETURN nombreImagen;
            end if;



        --dia madrr
            if(to_number(lsFechaDiaMadre) >= to_number(lsFechaIni)
              and to_number(lsFechaDiaMadre) <= to_number(lsFichaFinal)) then
              nombreImagen:=IMAGEN_DIAMADRE;
              RETURN nombreImagen;
            end if;

        --dia mujer
             if(to_number(diaMujer) >= to_number(lsFechaIni)
              and to_number(diaMujer) <= to_number(lsFichaFinal)) then
              nombreImagen:=IMAGEN_DIAMUJER;
              RETURN nombreImagen;
            end if;

        --aniversario
             if(to_number(fechaIngre) >= to_number(lsFechaIni)
              and to_number(fechaIngre) <= to_number(lsFichaFinal)) then
              nombreImagen:=IMAGEN_ANIVERSARIO;
              RETURN nombreImagen;
            end if;


        --mensaje generico
     RETURN nombreImagen;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_OBTEN_NOMBR_IMAGE: '||substr(sqlerrm,1,250));

END IMP_FN_OBTEN_NOMBR_IMAGE;


/**************************************************************************
Descripcion         : Genera la seccion de resumen pedidso
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_RESUM_PEDID(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


TYPE consolidadorecord IS RECORD (
    cod_clie                mae_clien.cod_clie%TYPE,
    nom_clie                varchar2(100),
    val_dir1                varchar2(200),
    val_dir2                varchar2(200),
    val_tele                varchar2(50),
    fec_emis                varchar2(10),
    val_nume_soli           ped_solic_cabec.val_nume_soli%type,
    cod_zona                zon_zona.cod_zona%type,
    cod_secc                zon_secci.cod_secc%type,
    cod_terr                zon_terri.cod_terr%type,
    total_catalogo          NUMBER(12, 2),
    total_descuento         NUMBER(12, 2),
    total_con_descuento     NUMBER(12, 2),
    flete                   NUMBER(12, 2),
    percepcion              NUMBER(12, 2),
    total_factura           NUMBER(12, 2),
    pago_posterior          NUMBER(12, 2),
    abono_servicio          NUMBER(12, 2),
    saldo_anterior          NUMBER(12, 2),
    total_a_pagar           NUMBER(12, 2)
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
select cod_clie,
       nom_clie,
       val_dir1,
       val_dir2,
       trim(val_tele),
       fec_emis,
       val_nume_soli,
       cod_zona,
       cod_secc,
       cod_terr,
       total_catalogo,
       total_descuento,
       total_catalogo-total_descuento total_con_descuento,
       flete,
       percepcion,
       total_catalogo-total_descuento+flete+percepcion total_factura,
       nvl(pago_posterior,0) pago_posterior,
       0 abono_servicio,
       saldo_anterior,
       total_catalogo-total_descuento+flete+percepcion-nvl(pago_posterior,0)+saldo_anterior total_a_pagar
from
(
select sp.oid_pais,
       sp.cod_pais,
       mc.oid_clie,
       mc.cod_clie,
       mc.cod_digi_ctrl,
       mc.val_nom1,
       mc.val_nom2,
       mc.val_ape1,
       mc.val_ape2,
       mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' || mc.val_nom2 nom_clie,
       trim(stv.des_abrv_tipo_via)  || ' ' || trim(mcd.val_nomb_via) || ' ' || trim(mcd.num_ppal) || ' ' || trim(mcd.val_obse) val_dir1,
       trim('/' from
       gen_pkg_gener.gen_fn_descr_estru_geopo(con.pais_oid_pais, mc.oid_clie, 4) || '/' ||
       gen_pkg_gener.gen_fn_descr_estru_geopo(con.pais_oid_pais, mc.oid_clie, 3) || '/' ||
       gen_pkg_gener.gen_fn_descr_estru_geopo(con.pais_oid_pais, mc.oid_clie, 2) || '/' ||
       gen_pkg_gener.gen_fn_descr_estru_geopo(con.pais_oid_pais, mc.oid_clie, 1)) val_dir2,
       substr(trim('/' from
       gen_pkg_gener.gen_fn_clien_texto_comun(mc.oid_clie, 'TF')  || '/' ||
       gen_pkg_gener.gen_fn_clien_texto_comun(mc.oid_clie, 'TM')),1,40) val_tele,
       to_char(con.fec_fact, 'dd/MM/yyyy') fec_emis,
       mci.num_docu_iden,
       con.oid_soli_cabe,
       con.val_nume_soli,
       con.fec_fact,
       con.val_impo_flet_tota_loca,
       con.val_impo_redo_loca,
       bp.des_pais,
       zon.cod_zona,
       sec.cod_secc,
       ter.cod_terr,
       sec.num_secu_fact_diar,
       val_prec_cata_tota_loca+val_prec_cont_tota_loca total_catalogo,
       val_impo_desc_1_tota_loca+val_prec_cont_tota_loca-con.val_impo_redo_loca total_descuento,
       val_tota_flet_loca flete,
       --round((val_tota_paga_loca*0.02),2) percepcion,
       ROUND(((VAL_TOTA_PAGA_LOCA-VAL_TOTA_FLET_LOCA)*0.02),2) PERCEPCION,
       imp_pkg_proce_compa.imp_fn_calcu_total_pago_poste(con.oid_soli_cabe,gen_fn_calcu_perio(p_codigoPeriodo, 1), 'a')
           - imp_pkg_proce_compa.imp_fn_calcu_saldo_favor(con.oid_soli_cabe) saldo_anterior,
       (
       select sum(imp_pend) from ccc_movim_cuent_corri where soca_oid_soli_cabe=con.oid_soli_cabe
       and fec_venc>(select fec_inic from cra_crono where perd_oid_peri=con.perd_oid_peri and zzon_oid_zona=con.zzon_oid_zona
                     and cact_oid_acti=(select oid_acti from cra_activ where cod_acti='v1' and pais_oid_pais=con.pais_oid_pais)
                    )
       ) pago_posterior
from mae_clien mc,
     mae_clien_direc mcd,
     seg_tipo_via stv,
     mae_clien_ident mci,
     ped_solic_cabec con,
     zon_zona zon,
     zon_terri ter,
     zon_terri_admin zta,
     zon_secci sec,
     ped_solic_cabec_secue sec,
     seg_pais sp,
     bas_pais bp,
     zon_regio r
where mc.oid_clie = con.clie_oid_clie
  and mc.oid_clie = mci.clie_oid_clie
  and mc.oid_clie = mcd.clie_oid_clie
  and mcd.tivi_oid_tipo_via = stv.oid_tipo_via (+)
  and mci.val_iden_docu_prin = 1
  and sp.oid_pais = con.pais_oid_pais
  and sp.cod_pais = bp.cod_pais
  and con.zzon_oid_zona = zon.oid_zona
  and con.terr_oid_terr = ter.oid_terr
  and con.ztad_oid_terr_admi = zta.oid_terr_admi
  and zta.zscc_oid_secc = sec.oid_secc
  and con.oid_soli_cabe = sec.soca_oid_soli_cabe
  and con.perd_oid_peri = oidPeriodo
  and con.fec_fact = to_date(p_fechaFacturacion, 'dd/MM/yyyy')
  AND con.num_lote_fact = numeroLoteFacturacion
 -- AND zta.ind_borr =0
  AND mcd.IND_DIRE_PPAL=1
  and mcd.ind_elim=0
  and con.num_unid_aten_tota > 0
  and r.oid_regi= zon.zorg_oid_regi
  and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
  )
       and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and zon.cod_zona=x.cod_zona)
       )
order by mc.cod_clie,
         con.val_nume_soli);



r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenResumenPedido    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarResumenPedido');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenResumenPedido IS NULL OR l_indicadorGenResumenPedido <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_RESUM_PEDID';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
       FETCH c_ordenCompra BULK COLLECT INTO r_consolidado LIMIT W_FILAS; --INTO r_ordenCompra;
            IF  r_consolidado.COUNT > 0 THEN
              FOR i IN r_consolidado.FIRST..r_consolidado.LAST
              LOOP

                -- Insertamos el registro y obtenemos la referencia al CLOB
                INSERT INTO IMP_PAQUE_DOCUM_RESUM_PEDID (
                 COR_REPE,
                 COD_CLIE,
                 VAL_NUME_SOLI,
                 XML_CONS
                )
                VALUES(
                l_correlativo,
                r_consolidado(i).cod_clie,
                r_consolidado(i).val_nume_soli,
                EMPTY_CLOB())
                RETURNING XML_CONS INTO l_clob;

                -- Inicio Detalle de Factura
                l_textoActual := '<detfac>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Cabecera
                l_textoActual := '<blqcab>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- codigo consultorea
                l_textoActual := '<ccon>' || r_consolidado(i).cod_clie || '</ccon>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- nombre consultorea
                l_textoActual := '<ncon>' || r_consolidado(i).nom_clie || '</ncon>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- telefono
                l_textoActual := '<telefonos>' || r_consolidado(i).val_tele || '</telefonos>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- campnha
                l_textoActual := '<fcam>' || l_codigoPeriodo || '</fcam>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- territorio
                l_textoActual := '<cter>' || r_consolidado(i).cod_zona || ',' || r_consolidado(i).cod_secc || ',' || r_consolidado(i).cod_terr || '</cter>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

               -- num pedido
                l_textoActual := '<numpedido>' || r_consolidado(i).val_nume_soli || '</numpedido>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

               -- dir1
                l_textoActual := '<dir1>' || r_consolidado(i).val_dir1 || '</dir1>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

               -- dir2
                l_textoActual := '<dir2>' || r_consolidado(i).val_dir2 || '</dir2>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                -- Fin Cabecera
                l_textoActual := '<ffac>'||r_consolidado(i).fec_emis ||'</ffac></blqcab>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                --PIES D PAGINA

                     -- Inicio Pie
                l_textoActual := '<pie>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_clob, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);


                -- Total Catalogo
                l_textoActual := '<linea1>' || trim(to_char( r_consolidado(i).total_catalogo, l_formatoNumerico)) || '</linea1>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Total Descuentos (Incluimos el redondeo)
                l_textoActual := '<linea2>' || trim(to_char( r_consolidado(i).total_descuento, l_formatoNumerico)) || '</linea2>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Total Facturado
                l_textoActual := '<linea3>' || trim(to_char(r_consolidado(i).total_con_descuento, l_formatoNumerico)) || '</linea3>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Flete
                l_textoActual := '<linea4>' || trim(to_char(r_consolidado(i).flete, l_formatoNumerico)) || '</linea4>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Percepciones
                l_textoActual := '<linea5>' || trim(to_char(r_consolidado(i).percepcion, l_formatoNumerico)) || '</linea5>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Total Factura
                l_textoActual := '<linea6>' || trim(to_char(r_consolidado(i).total_factura, l_formatoNumerico)) || '</linea6>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Pagos Posteriores
                l_textoActual := '<linea7>' || trim(to_char(r_consolidado(i).pago_posterior, l_formatoNumerico)) || '</linea7>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Abono Atencion de Servicios
                l_textoActual := '<linea8>' || trim(to_char(0, l_formatoNumerico)) || '</linea8>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Saldo anterior
                 l_textoActual := '<linea9>' || trim(to_char(r_consolidado(i).saldo_anterior, l_formatoNumerico)) || '</linea9>';
                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                 DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Importe Total
                 l_textoActual := '<linea10>' || trim(to_char(r_consolidado(i).total_a_pagar, l_formatoNumerico)) || '</linea10>';
                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                 DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                -- Fin Pie
                l_textoActual := '</pie>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_CLOB, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

               -- Fin Detallefactura
                l_textoActual := '</detfac>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                DBMS_LOB.writeappend(l_clob, LENGTH(l_cambioLineaRetornoCarro), l_cambioLineaRetornoCarro);

                 l_correlativo:=l_correlativo + 1;

              END LOOP;

             END IF;
       EXIT WHEN c_ordenCompra%NOTFOUND;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_RESUM_PEDID: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_RESUM_PEDID;

/**************************************************************************
Descripcion         : Genera la seccion de encuntro compartamos
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_ENCUE_COMPA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


TYPE consolidadorecord IS RECORD (
    cod_clie              mae_clien.cod_clie%type,
    fec_re                varchar2(10),
    nombregz              IMP_INVIT_COMPA.NOM_GERE_ZONA%type,
    loca                  IMP_INVIT_COMPA.VAL_LOCA%type,
    dire                  IMP_INVIT_COMPA.VAL_DIRE%type,
    fecha                 IMP_INVIT_COMPA.VAL_FECH%type,
    hora                  IMP_INVIT_COMPA.VAL_HORA%type,
    nombreImagen          IMP_INVIT_COMPA.NOM_IMAG%type,
    fec_rp                varchar2(10)
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     codigoPeriodoSgte VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS

select  cod_clie,
        to_char(base.fec_re,'dd/MM/yyyy') fec_re,
        base.nombregz,
        base.loca,
        base.dire,
        base.fecha,
        base.hora,
        base.nombreImagen,
        --base.val_text_comu ,
        to_char(base.fec_rp,'dd/MM/yyyy') fec_rp
  from
    (select distinct sc.clie_oid_clie,
                mc.cod_clie,
             --   mc.val_nom1 ||' '||val_nom2||' '||val_ape1||' '||val_ape2 as nombre,
                acti.cod_zona,
                acti.acti_re,
                acti.peri_re,
                acti.fec_re,
                acti.acti_rp,
                acti.peri_rp,
                acti.fec_rp,
                imp.NOM_GERE_ZONA nombregz,
                imp.VAL_LOCA loca,
                imp.VAL_DIRE dire,
                imp.VAL_FECH fecha,
                imp.VAL_HORA hora,
                imp.NOM_IMAG nombreImagen
        from
          ped_solic_cabec sc,
          ped_tipo_solic_pais tsp,
          ped_tipo_solic ts,
          mae_clien mc,
        (
        select     tab_rp.oid_zona,
                   tab_rp.cod_zona,
                   tab_rp.acti_rp,
                   tab_rp.peri_rp,
                   tab_rp.fec_rp,
                   tab_re.acti_re,
                   tab_re.peri_re,
                   tab_re.fec_re
        from
        ( select zz.oid_zona,
                 zz.cod_zona,
                 null acti_re,
                 null peri_re,
                 null fec_re,
                 cac.cod_acti acti_rp,
                 spc.cod_peri peri_rp,
                 ccr.fec_inic fec_rp
                 --mcgz.val_nom1 ||' '||mcgz.val_nom2||' '||mcgz.val_ape1||' '||mcgz.val_ape2 as nombregz,
                 --mccm.val_text_comu
         from
            cra_crono ccr,
            cra_activ cac,
            cra_perio cp,
            seg_perio_corpo spc,
            zon_zona zz
            --mae_clien mcgz,
            --mae_clien_comun mccm
            where
                ccr.cact_oid_acti = cac.cact_oid_acti
                and cac.cod_acti in ('RP')  ---  reparto - compartamos
                and ccr.perd_oid_peri = cp.oid_peri
                and cp.peri_oid_peri = spc.oid_peri
                and spc.cod_peri = codigoPeriodoSgte
                   --per_pkg_repor_perce.per_fn_obtie_perio('200801',2001,2003,2001,1) -- codcmp, oidpais, oidmarca, oidcanal, num
                 and ccr.zzon_oid_zona = zz.oid_zona
                --and zz.clie_oid_clie = mcgz.oid_clie
                --and mcgz.oid_clie = mccm.clie_oid_clie
                --and mccm.ind_comu_ppal = 1
                and cac.pais_oid_pais = oidPais
           ) tab_rp
          full outer join
         ( select zz.oid_zona, zz.cod_zona, cac.cod_acti acti_re, spc.cod_peri peri_re, ccr.fec_inic fec_re, null acti_rp,null peri_rp,null fec_rp
           from
                cra_crono ccr,
                cra_activ cac,
                cra_perio cp,
                seg_perio_corpo spc,
                zon_zona zz
            where
            ccr.cact_oid_acti = cac.cact_oid_acti
            and cac.cod_acti in ('RE')  ---  reunion de exito - compartamos
            and ccr.perd_oid_peri = cp.oid_peri
            and cp.peri_oid_peri = spc.oid_peri
            and spc.cod_peri =  codigoPeriodoSgte
               --per_pkg_repor_perce.per_fn_obtie_perio('200801',2001,2003,2001,1) -- codcmp, oidpais, oidmarca, oidcanal, num
            and ccr.zzon_oid_zona = zz.oid_zona
            and cac.pais_oid_pais = oidPais
         ) tab_re
       on tab_rp.oid_zona = tab_re.oid_zona
       ) acti,
       zon_zona z,
       zon_regio r,
       IMP_INVIT_COMPA imp
    where
        sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and ts.cod_tipo_soli = 'SOC'
        and sc.fec_fact =  to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        and sc.perd_oid_peri =  oidPeriodo
        and sc.grpr_oid_grup_proc = 5
        and sc.clie_oid_clie = mc.oid_clie
        and sc.zzon_oid_zona = acti.oid_zona
        and sc.zzon_oid_zona = z.oid_zona
        and r.oid_regi = z.zorg_oid_regi
        and imp.cod_peri = p_codigoPeriodo
        and imp.cod_zona =  z.cod_zona
        and imp.ind_acti=1
        and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    ) base;


r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenEncuentro        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarEncuentroCompartamos');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

codigoPeriodoSgte           VARCHAR2(6);
BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenEncuentro IS NULL OR l_indicadorGenEncuentro <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);
    codigoPeriodoSgte := per_pkg_repor_perce.per_fn_obtie_perio(p_codigoPeriodo,l_oidPais,l_oidMarca,l_oidCanal,1) ;


       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_ENCUE_COMPA';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa,codigoPeriodoSgte, l_numeroLoteFacturacion);
    LOOP
       FETCH c_ordenCompra BULK COLLECT INTO r_consolidado LIMIT W_FILAS; --INTO r_ordenCompra;
            IF  r_consolidado.COUNT > 0 THEN
              FOR i IN r_consolidado.FIRST..r_consolidado.LAST
              LOOP

                -- Insertamos el registro y obtenemos la referencia al CLOB
                INSERT INTO IMP_PAQUE_DOCUM_ENCUE_COMPA (
                 COR_ENCO,
                 COD_CLIE,
                 XML_CONS
                )
                VALUES(
                l_correlativo,
                r_consolidado(i).cod_clie,
                EMPTY_CLOB())
                RETURNING XML_CONS INTO l_clob;

                -- Inicio Detalle de Factura
                l_textoActual := '<compartamos>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- Inicio Cabecera
                l_textoActual := '<img>' || r_consolidado(i).nombreImagen ||'</img> ';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- nombre consultorea
                l_textoActual := '<nomgeren>' || r_consolidado(i).nombregz || '</nomgeren>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

               -- local
                l_textoActual := '<local>' || r_consolidado(i).loca || '</local>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                -- direccion
                l_textoActual := '<direccion>' || r_consolidado(i).dire || '</direccion>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                --fecha
                 l_textoActual := '<fecha>'|| r_consolidado(i).fecha ||'</fecha>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                --hora
                 l_textoActual := '<hora>'|| r_consolidado(i).hora ||'</hora>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

               -- Fin Detallefactura
                l_textoActual := '</compartamos>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                 l_correlativo:=l_correlativo + 1;

              END LOOP;

             END IF;
       EXIT WHEN c_ordenCompra%NOTFOUND;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_ENCUE_COMPA: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_ENCUE_COMPA;


/**************************************************************************
Descripcion         : Genera la seccion de CONCURSO CERRADOS
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_CERRA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


TYPE consolidadorecord IS RECORD (
    cod_clie              mae_clien.cod_clie%type,
    oid_clie              mae_clien.oid_clie%type,
    concurso              msg_buzon_mensa.DATO_VARI_01%type,
    numconcurso           msg_buzon_mensa.DATO_VARI_01%type,
    nivela                msg_buzon_mensa.DATO_VARI_01%type,
    cantidad              msg_buzon_mensa.DATO_VARI_01%type,
    cuv                   msg_buzon_mensa.DATO_VARI_01%type,
    puntaje               msg_buzon_mensa.DATO_VARI_01%type,
    gano                  msg_buzon_mensa.DATO_VARI_01%type,
    premio                msg_buzon_mensa.DATO_VARI_01%type,
    estado                msg_buzon_mensa.DATO_VARI_01%type
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
SELECT DISTINCT
    mc.cod_clie As cod_clie,
    mc.oid_clie As oid_clie,
    --- concurso
    ''  AS concurso,
    --- numero de concurso
    ''  AS numconcurso,
    --- nivel alcanzado
    ''  AS nivela,
    --- cantidad
    ''  AS cantidad,
    --- cuv
    ''  AS cuv,
    '' AS puntaje,
    --- gano si/no
    '' AS gano,
    --- premio
    '' AS premio,
    --- estado
    '' AS estado
FROM
    PED_SOLIC_CABEC oc,
    mae_clien mc,
    ped_tipo_solic_pais ptsp,
    ped_tipo_solic pts,
    (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
    msg_buzon_mensa bm,
    zon_regio r,
    zon_zona z
WHERE
        ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
        AND pts.OID_TIPO_SOLI = ts.val_oid
        AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
        AND pts.COD_TIPO_SOLI = 'SOC'
        AND oc.GRPR_OID_GRUP_PROC = 5
        and z.oid_zona = oc.ZZON_OID_ZONA
        and z.zorg_oid_regi= r.oid_regi
        AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        AND oc.clie_oid_clie = mc.oid_clie
        AND oc.clie_oid_clie = bm.clie_oid_clie
        AND bm.mens_oid_mens
         IN((SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'PED20' AND B.oid_mens = bm.mens_oid_mens),
            (SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'PED21' AND B.oid_mens = bm.mens_oid_mens)
          )
        AND bm.IND_ACTI = 0
        AND bm. PERI_OID_PERI = oidPeriodo
       and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona));


r_ordenCompra c_ordenCompra%ROWTYPE;


CURSOR c_mesajesPEDXX(oidCliente NUMBER,
                     oidPeriodo NUMBER,
                     oidMensaje NUMBER) IS
SELECT
    mc.cod_clie As cod_clie,
    mc.oid_clie As oid_clie,
    --- concurso
    DATO_VARI_16  AS concurso,
    --- numero de concurso
    DATO_VARI_17  AS numconcurso,
    --- nivel alcanzado
    DATO_VARI_13  AS nivela,
    --- cantidad
    DATO_VARI_08  AS cantidad,
    --- cuv
    DATO_VARI_19  AS cuv,
    '' AS puntaje,
    --- gano si/no
    'SI' AS gano,
    --- premio
    DATO_VARI_11 AS premio,
    --- estado
    'ENTREGADO' AS estado
FROM
    PED_SOLIC_CABEC oc,
    mae_clien mc,
    ped_tipo_solic_pais ptsp,
    ped_tipo_solic pts,
    (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
    msg_buzon_mensa bm,
    zon_regio r,
    zon_zona z
WHERE
        ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
        AND pts.OID_TIPO_SOLI = ts.val_oid
        AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
        AND pts.COD_TIPO_SOLI = 'SOC'
        AND oc.GRPR_OID_GRUP_PROC = 5
        and z.oid_zona = oc.ZZON_OID_ZONA
        and z.zorg_oid_regi= r.oid_regi
        AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        AND oc.clie_oid_clie = mc.oid_clie
        AND mc.oid_clie = oidCliente
        AND oc.clie_oid_clie = bm.clie_oid_clie
        AND bm.mens_oid_mens = oidMensaje
        AND bm.IND_ACTI = 0
        AND bm. PERI_OID_PERI = oidPeriodo
       and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona));

r_mesajesPEDXX c_mesajesPEDXX%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenConcursoCerrado  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarConcursoCerrados');
l_maximoFilasConcursoCerrados  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'maximoFilasConcursoCerrados');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

codigoPeriodoSgte           VARCHAR2(6);
l_Offset                    NUMBER := 1;
xmlClob                     CLOB := EMPTY_CLOB();
l_clobTemp                  CLOB := EMPTY_CLOB();
lnContExisteCliente         NUMBER:=0;
lnValNivelDeta              NUMBER:=1;
lnoidMensaje                NUMBER;
lnDetalle                   NUMBER:=1;
BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenConcursoCerrado IS NULL OR l_indicadorGenConcursoCerrado <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);

    SELECT oid_peri
      INTO l_oidperiodo
      FROM seg_perio_corpo
     WHERE cod_peri = p_codigoperiodo;

    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);


    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CONCU_CERRA';



    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
       FETCH c_ordenCompra BULK COLLECT INTO r_consolidado LIMIT W_FILAS; --INTO r_ordenCompra;
            IF  r_consolidado.COUNT > 0 THEN
              FOR i IN r_consolidado.FIRST..r_consolidado.LAST
              LOOP

                       -- Insertamos el registro y obtenemos la referencia al CLOB
                        INSERT INTO IMP_PAQUE_DOCUM_CONCU_CERRA (
                         COR_COCE,
                         COD_CLIE,
                         VAL_NIVE_DETA,
                         XML_CONS
                        )
                        VALUES(
                        l_correlativo,
                        r_consolidado(i).cod_clie,
                        1,
                        EMPTY_CLOB())
                        RETURNING XML_CONS INTO l_clob;

                        -- Inicio
                        l_textoActual := '<tabla24>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                       begin
                         SELECT oid_mens INTO lnoidMensaje
                         FROM msg_mensa B
                         WHERE B.COD_MENS = 'PED20';
                        exception
                         when others then
                          lnoidMensaje:=null;
                        end;

                   lnDetalle:=1;
                   if(lnDetalle <= to_number(l_maximoFilasConcursoCerrados)) then
                        --Inicio Detalle inc01
                     OPEN c_mesajesPEDXX( r_consolidado(i).oid_clie, l_oidPeriodo,lnoidMensaje);
                        LOOP
                        FETCH c_mesajesPEDXX INTO r_mesajesPEDXX;
                        EXIT WHEN c_mesajesPEDXX%NOTFOUND;
                            BEGIN
                               if(lnDetalle <= to_number(l_maximoFilasConcursoCerrados)) then
                                --Inicio Detalle
                                l_textoActual := '<txt>'|| r_mesajesPEDXX.concurso ||'<tc/>'|| r_mesajesPEDXX.numconcurso || '<tc/>'
                                         || r_mesajesPEDXX.nivela  ||'<tr/>'|| r_mesajesPEDXX.cantidad || '<tc/>'
                                         || r_mesajesPEDXX.cuv  ||'<tr/>'|| r_mesajesPEDXX.puntaje || '<tc/>'
                                         || r_mesajesPEDXX.gano ||'<tr/>'||  r_mesajesPEDXX.premio || '<tc/>'
                                         || r_mesajesPEDXX.estado || '</txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                               lnDetalle:=lnDetalle+1;
                              end if;
                            END;
                        END LOOP;
                        CLOSE c_mesajesPEDXX;
                   end if;


                   --para el PED21
                        begin
                         SELECT oid_mens INTO lnoidMensaje
                         FROM msg_mensa B
                         WHERE B.COD_MENS = 'PED21';
                        exception
                         when others then
                          lnoidMensaje:=null;
                        end;
                   if(lnDetalle <= to_number(l_maximoFilasConcursoCerrados)) then
                        --Inicio Detalle
                     OPEN c_mesajesPEDXX( r_consolidado(i).oid_clie, l_oidPeriodo,lnoidMensaje);
                        LOOP
                        FETCH c_mesajesPEDXX INTO r_mesajesPEDXX;
                        EXIT WHEN c_mesajesPEDXX%NOTFOUND;
                            BEGIN
                               if(lnDetalle <= to_number(l_maximoFilasConcursoCerrados)) then
                                --Inicio Detalle
                                l_textoActual := '<txt>'|| r_mesajesPEDXX.concurso ||'<tc/>'|| r_mesajesPEDXX.numconcurso || '<tc/>'
                                         || r_mesajesPEDXX.nivela  ||'<tr/>'|| r_mesajesPEDXX.cantidad || '<tc/>'
                                         || r_mesajesPEDXX.cuv  ||'<tr/>'|| r_mesajesPEDXX.puntaje || '<tc/>'
                                         || r_mesajesPEDXX.gano ||'<tr/>'||  r_mesajesPEDXX.premio || '<tc/>'
                                         || r_mesajesPEDXX.estado || '</txt>';
                                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                               lnDetalle:=lnDetalle+1;
                              end if;
                            END;
                        END LOOP;
                        CLOSE c_mesajesPEDXX;
                   end if;

                      -- Fin
                        l_textoActual := '</tabla24>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                         l_correlativo:=l_correlativo + 1;



               END LOOP;

             END IF;
       EXIT WHEN c_ordenCompra%NOTFOUND;
    END LOOP;
    CLOSE c_ordenCompra;



 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_CONCU_CERRA: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_CONCU_CERRA;


/**************************************************************************
Descripcion         : Genera la seccion de CONCURSO VENTAS
Fecha Creación      :  14/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_VENTA(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


TYPE consolidadorecord IS RECORD (
    concurso              msg_buzon_mensa.DATO_VARI_01%type,
    puntajeacmp           msg_buzon_mensa.DATO_VARI_01%type,
    puntajeaacm           msg_buzon_mensa.DATO_VARI_01%type,
    nivela                INC_PARAM_NIVEL_PREMI.NUM_NIVE%type,
    ptsnv1                   number(8),
    ptsnv2                   number(8),
    nvm                   INC_PARAM_GENER_PREMI.num_nive%type,
    oidperiodofinal       INC_CONCU_PARAM_GENER.PERD_OID_PERI_HAST%type,
    oidPeriodoFacturacion PED_SOLIC_CABEC.perd_oid_peri%type,
    cod_clie              mae_clien.cod_clie%type,
    nombres               varchar2(100),
    campanhaIniConcurso   varchar2(6),
    campanhaFinConcurso   varchar2(6),
    nivel                 INC_PARAM_NIVEL_PREMI.NUM_NIVE%type,
    imgBaseConcurso       IMP_INCEN_VENTA.NOM_IMAG_BASE_CONC%type,
    imgNivelAnterior      IMP_INCEN_VENTA.NOM_IMAG_NIVE_ANTE%type,
    imgNivelObtenido      IMP_INCEN_VENTA.NOM_IMAG_NIVE_OBTE%type,
    imgNivelSgte          IMP_INCEN_VENTA.NOM_IMAG_NIVE_SIGU%type,
    imgNuevoConcurso      IMP_INCEN_VENTA.NOM_IMAG_NUEV_CONC%type
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS

SELECT
    --- numero de concurso
    DATO_VARI_02  AS concurso,
    --- puntaje de campaña
    nvl(DATO_VARI_06,0)  AS puntajeacmp,
    --- puntaje acumulado
    nvl(DATO_VARI_09,0)  AS puntajeaacm,
--- nivel alcanzado
    (SELECT
      PNP.NUM_NIVE
      FROM  INC_CONCU_PARAM_GENER CPG,
            INC_PARAM_GENER_PREMI PGP,
            INC_PARAM_NIVEL_PREMI PNP
        WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
        AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
        AND cpg.NUM_CONC = DATO_VARI_02
        AND DATO_VARI_09 BETWEEN PNP.NUM_CANT_INIC_PUNT AND PNP.NUM_CANT_FINA_PUNT
    )  AS nivela,
    --- puntos para llegar al siguiente nivel
    (SELECT
     (PNP.NUM_CANT_FINA_PUNT + 1) - dato_vari_09
    FROM INC_CONCU_PARAM_GENER CPG,
         INC_PARAM_GENER_PREMI PGP,
         INC_PARAM_NIVEL_PREMI PNP
    WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
    AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
    AND cpg.NUM_CONC = DATO_VARI_02
    AND DATO_VARI_09 BETWEEN PNP.NUM_CANT_INIC_PUNT AND PNP.NUM_CANT_FINA_PUNT
    )  AS ptsnv1,
    -- puntos para llegar al siguiente nivel
    (SELECT
    (PNP.NUM_CANT_INIC_PUNT ) - dato_vari_09
    FROM  INC_CONCU_PARAM_GENER CPG,
          INC_PARAM_GENER_PREMI PGP,
          INC_PARAM_NIVEL_PREMI PNP
    WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
    AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
    AND cpg.NUM_CONC = DATO_VARI_02
    AND PNP.NUM_NIVE = 1
    )  AS ptsnv2,
    --- nivel maximo de premiacion
    (SELECT
    PGP.NUM_NIVE
    FROM  INC_CONCU_PARAM_GENER CPG,
          INC_PARAM_GENER_PREMI PGP,
          INC_PARAM_NIVEL_PREMI PNP
    WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
    AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
    AND cpg.NUM_CONC = DATO_VARI_02
    AND PNP.NUM_NIVE = 1
    )  AS nvm,
    -- periodo fin de concurso
    (SELECT cpg.PERD_OID_PERI_HAST
     FROM
       INC_CONCU_PARAM_GENER CPG
     WHERE cpg.NUM_CONC = DATO_VARI_02) AS oidperiodofinal,
    --- periodo de facturacion
    oc.perd_oid_peri AS oidPeriodoFacturacion,
    mc.cod_clie,
    upper(mc.VAL_NOM1) || ' ' || upper(mc.VAL_NOM2) as nombres,
    imp.COD_PERI campanhaIniConcurso,
    imp.COD_PERI_FINA campanhaFinConcurso ,
    imp.NUM_NIVE nivel               ,
    imp.NOM_IMAG_BASE_CONC imgBaseConcurso     ,
    imp.NOM_IMAG_NIVE_ANTE imgNivelAnterior    ,
    imp.NOM_IMAG_NIVE_OBTE imgNivelObtenido    ,
    imp.NOM_IMAG_NIVE_SIGU imgNivelSgte        ,
    imp.NOM_IMAG_NUEV_CONC imgNuevoConcurso
FROM
        PED_SOLIC_CABEC oc,
        mae_clien mc,
        ped_tipo_solic_pais ptsp,
        ped_tipo_solic pts,
        (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
        msg_buzon_mensa bm,
        INC_CONCU_PARAM_GENER cpg,
        IMP_INCEN_VENTA imp,
        zon_zona z,
        zon_regio r
WHERE
        ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
        AND pts.OID_TIPO_SOLI = ts.val_oid
        AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
        AND pts.COD_TIPO_SOLI = 'SOC'
        AND oc.GRPR_OID_GRUP_PROC = 5
        AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        AND oc.clie_oid_clie = mc.oid_clie
        AND oc.clie_oid_clie = bm.clie_oid_clie
        AND bm.mens_oid_mens = (SELECT oid_mens FROM msg_mensa b WHERE b.COD_MENS = 'INC01')
        AND bm.IND_ACTI = 0
        AND bm. PERI_OID_PERI = oidPeriodo
        AND z.oid_zona = oc.ZZON_OID_ZONA
        AND r.oid_regi =  z.zorg_oid_regi
        AND cpg.NUM_CONC = bm.DATO_VARI_02
        AND cpg.COIV_OID_CONC_IVR = 1
        AND cpg.CCON_OID_CLAS_CONC = 3
        AND cpg.NUM_CONC = imp.NUM_CONC
        and imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE in(
                (SELECT
                  PNP.NUM_NIVE
                  FROM  INC_CONCU_PARAM_GENER CPG,
                        INC_PARAM_GENER_PREMI PGP,
                        INC_PARAM_NIVEL_PREMI PNP
                    WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
                    AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
                    AND cpg.NUM_CONC = DATO_VARI_02
                    and imp.num_nive = PNP.NUM_NIVE
                    AND DATO_VARI_09 BETWEEN PNP.NUM_CANT_INIC_PUNT AND PNP.NUM_CANT_FINA_PUNT))
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona));


CURSOR c_mensajeVentaNoAcum IS
SELECT
    mc.cod_clie,
    upper(mc.VAL_NOM1) || ' ' || upper(mc.VAL_NOM2) as nombres,
    NVL((SELECT imp.NOM_IMAG_NUEV_CONC
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'')  imgNuevoConcurso,
    NVL((SELECT imp.NOM_IMAG_NIVE_OBTE
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'')  imgNivelObtenido,
    NVL((SELECT imp.NOM_IMAG_NIVE_SIGU
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'')  imgNivelSgte,
    NVL((SELECT imp.COD_PERI
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'') campanhaIniConcurso,
    NVL((SELECT imp.COD_PERI_FINA
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'')  campanhaFinConcurso,
    NVL((SELECT imp.NOM_IMAG_BASE_CONC
     FROM IMP_INCEN_VENTA imp
     WHERE imp.ind_acti='1'
        and p_codigoPeriodo > = imp.COD_PERI
        and p_codigoPeriodo <=  imp.COD_PERI_FINA
        and imp.NUM_NIVE =1
        AND ROWNUM=1),'') imgBaseConcurso,
    (SELECT
         PNP.NUM_CANT_FINA_PUNT
        FROM INC_CONCU_PARAM_GENER CPG,
             INC_PARAM_GENER_PREMI PGP,
             INC_PARAM_NIVEL_PREMI PNP
        WHERE PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
        AND PGP.OID_PARA_GENE_PREM =  PNP.PAGP_OID_PARA_GENE_PREM(+)
        AND PNP.NUM_NIVE=1
        AND cpg.NUM_CONC = (SELECT imp.NUM_CONC
                                 FROM IMP_INCEN_VENTA imp
                                 WHERE imp.ind_acti='1'
                                    and p_codigoPeriodo > = imp.COD_PERI
                                    and p_codigoPeriodo <=  imp.COD_PERI_FINA
                                    and imp.NUM_NIVE =1
                                    AND ROWNUM=1)
        AND ROWNUM=1)ptsnv1
FROM
        PED_SOLIC_CABEC oc,
        mae_clien mc,
        ped_tipo_solic_pais ptsp,
        ped_tipo_solic pts,
        (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
        zon_zona z,
        zon_regio r
WHERE
        ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
        AND pts.OID_TIPO_SOLI = ts.val_oid
        AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
        AND pts.COD_TIPO_SOLI = 'SOC'
        AND oc.GRPR_OID_GRUP_PROC = 5
        AND oc.fec_fact = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
        AND oc.clie_oid_clie = mc.oid_clie
        AND z.oid_zona = oc.ZZON_OID_ZONA
        AND r.oid_regi =  z.zorg_oid_regi
        --AND mc.COD_CLIE = ipdcv.cod_clie
        AND mc.COD_CLIE NOT IN(
            SELECT ipdcv.cod_clie
            FROM IMP_PAQUE_DOCUM_CONCU_VENTA ipdcv
            WHERE mc.COD_CLIE = ipdcv.cod_clie)
      AND(
        EXISTS(SELECT NULL
            FROM imp_plan_pilot x
            WHERE x.cod_regi IS  NULL
             AND x.cod_zona IS NULL
             and x.cod_peri= p_codigoPeriodo
             AND x.ind_acti=1)
        OR
         EXISTS(SELECT NULL
            FROM imp_plan_pilot x
            WHERE x.cod_regi IS NOT NULL
             AND x.cod_zona IS NULL
             and x.cod_peri= p_codigoPeriodo
             AND x.ind_acti=1 AND r.cod_regi=x.cod_regi)
        OR
         EXISTS(SELECT NULL
            FROM imp_plan_pilot x
            WHERE x.cod_regi IS NULL
             AND x.cod_zona IS NOT NULL
             and x.cod_peri= p_codigoPeriodo
             AND x.ind_acti=1 AND z.cod_zona=x.cod_zona));

r_mensajeVentaNoAcum c_mensajeVentaNoAcum%ROWTYPE;


r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenConcursoVentas   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarConcursoVentas');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

codigoPeriodoSgte           VARCHAR2(6);
nivelSgte                   NUMBER(3);
hayImagen                   BOOLEAN;

lnCampanhas                 NUMBER;
ptosNivel                   NUMBER;
imgNuevoConcurso            VARCHAR2(50):='';

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenConcursoVentas IS NULL OR l_indicadorGenConcursoVentas <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);

    SELECT oid_peri
      INTO l_oidperiodo
      FROM seg_perio_corpo
     WHERE cod_peri = p_codigoperiodo;
    --l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);

       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CONCU_VENTA';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
       FETCH c_ordenCompra BULK COLLECT INTO r_consolidado LIMIT W_FILAS; --INTO r_ordenCompra;
            IF  r_consolidado.COUNT > 0 THEN
              FOR i IN r_consolidado.FIRST..r_consolidado.LAST
              LOOP

                -- Insertamos el registro y obtenemos la referencia al CLOB
                INSERT INTO IMP_PAQUE_DOCUM_CONCU_VENTA (
                 COR_COVE,
                 COD_CLIE,
                 XML_CONS
                )
                VALUES(
                l_correlativo,
                r_consolidado(i).cod_clie,
                EMPTY_CLOB())
                RETURNING XML_CONS INTO l_clob;

                hayImagen:=false;
                -- Inicio
                ---l_textoActual := '<ventas>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                lnCampanhas:=  VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(r_consolidado(i).campanhaIniConcurso,
                                                                   p_codigoPeriodo,
                                                                   l_oidPais, l_oidMarca, l_oidCanal);


                --si se encuentra al inicio del concurso
                if( p_codigoPeriodo = r_consolidado(i).campanhaIniConcurso )then
                    l_textoActual := '<ventas><texto>'|| r_consolidado(i).nombres || ' Te presentamos el nuevo Programa de ';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_textoActual := 'Bonificación de campaña '|| r_consolidado(i).campanhaIniConcurso ||' a campaña ' || r_consolidado(i).campanhaFinConcurso ;
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_textoActual :=' No dejes de pasar la oportunidad de acumular puntos en estas ';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_textoActual := lnCampanhas ||
                     ' campañas  y ganar fabulosas bonificaciones.
                     Encuentra todas la bonificaciones ofrecidas en el folleto, recuerda revisar las bases del programa.</texto></ventas>';
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_textoActual :='<nivelgral><img>'|| r_consolidado(i).imgBaseConcurso ||'</img></nivelgral>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                    l_textoActual :='<nivelx><img></img></nivelx>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                    l_textoActual :='<nively><img></img></nively>';
                    DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);

                    hayImagen:=TRUE;

                end if;



                if(NOT hayImagen) then



                    --consejera llegó a  de los niveles del programa de Bonificación
                    if(r_consolidado(i).nivela is not null and length(r_consolidado(i).nivela)>0) then

                        --si nos encontramos en segundo pedido
                        if(lnCampanhas=2) then

                          l_textoActual := '<ventas><texto>'||  r_consolidado(i).nombres ||
                             ' Felicitaciones llegaste al nivel '|| r_consolidado(i).nivel ||
                             ' te faltan '||  r_consolidado(i).ptsnv1 ||' puntos para llegar al siguiente nivel,'||
                             ' recuerda que te queda 1 campaña para alcanzar tu meta.</texto></ventas>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelgral><img>'|| r_consolidado(i).imgBaseConcurso ||'</img></nivelgral>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelx><img>'|| r_consolidado(i).imgNivelObtenido ||'</img></nivelx>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nively><img>'|| r_consolidado(i).imgNivelSgte ||'</img></nively>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);

                        else--ultima campanha


                          l_textoActual := '<ventas><texto>'||  r_consolidado(i).nombres ||
                             ' Felicitaciones llegaste al nivel '|| r_consolidado(i).nivel ||
                             ' te superaste haciendo tuyas estas fabulosas bonificaciones.</texto></ventas>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelgral><img>'|| r_consolidado(i).imgBaseConcurso ||'</img></nivelgral>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelx><img>'|| r_consolidado(i).imgNivelAnterior ||'</img></nivelx>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nively><img>'|| r_consolidado(i).imgNivelObtenido ||'</img></nively>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);


                        end if;


                    else
                    --consejera NO llegó a ninguno de los niveles del programa de Bonificación

                        --si nos encontramos en segundo pedido
                        if(lnCampanhas=2) then

                          l_textoActual := '<ventas><texto>'||  r_consolidado(i).nombres ||
                             ' te Faltan '||  r_consolidado(i).ptsnv1 ||' puntos para llegar al N1 del programa de'||
                             ' Bonificación, recuerda que te queda 1 campaña para alcanzar tu meta.</texto></ventas>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelgral><img>'|| r_consolidado(i).imgBaseConcurso ||'</img></nivelgral>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelx><img>'|| r_consolidado(i).imgNivelObtenido ||'</img></nivelx>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nively><img>'|| r_consolidado(i).imgNivelSgte ||'</img></nively>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);

                        else--ultima campanha
                          l_textoActual :='<ventas><texto></texto></ventas>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                          l_textoActual :='<nivelgral><img>'|| r_consolidado(i).imgNuevoConcurso ||'</img></nivelgral>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nivelx><img></img></nivelx>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                         l_textoActual :='<nively><img></img></nively>';
                         DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                        end if;



                        --r_consolidado(i).nivela is null OR length(r_consolidado(i).nivela) =0
                      /*  if(r_consolidado(i).puntajeaacm = 0)then
                           l_textoActual := '<ventas><img>nivelvnp.jpg</img></ventas';
                           DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                         else -- tien puntaje
                            l_textoActual := '<ventas><img>nivelvng.jpg</img></ventas>';
                           DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                         end if;
                       */

                    end if;



                end if;--en no hay imagen

                -- Inicio Cabecera

               -- Fin
                --l_textoActual := '</ventas>';
                --DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                 l_correlativo:=l_correlativo + 1;

              END LOOP;

             END IF;
       EXIT WHEN c_ordenCompra%NOTFOUND;
    END LOOP;
    CLOSE c_ordenCompra;



    --imagen cuyas consultoras tienen puntaje noa cumulado
                      OPEN c_mensajeVentaNoAcum;
                        LOOP
                        FETCH c_mensajeVentaNoAcum INTO r_mensajeVentaNoAcum;
                        EXIT WHEN c_mensajeVentaNoAcum%NOTFOUND;
                            BEGIN

                              -- Insertamos el registro y obtenemos la referencia al CLOB
                                    INSERT INTO IMP_PAQUE_DOCUM_CONCU_VENTA (
                                     COR_COVE,
                                     COD_CLIE,
                                     XML_CONS
                                    )
                                    VALUES(
                                    l_correlativo,
                                    r_mensajeVentaNoAcum.cod_clie,
                                    EMPTY_CLOB())
                                    RETURNING XML_CONS INTO l_clob;

                            hayImagen:=false;
                            lnCampanhas:=  VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(r_mensajeVentaNoAcum.campanhaIniConcurso,
                                                                   p_codigoPeriodo,
                                                                   l_oidPais, l_oidMarca, l_oidCanal);

                             -- Inicio Concurso
                               if( p_codigoPeriodo = r_mensajeVentaNoAcum.campanhaIniConcurso )then
                                l_textoActual := '<ventas><texto>'|| r_mensajeVentaNoAcum.nombres || ' Te presentamos el nuevo Programa de ';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                l_textoActual := 'Bonificación de campaña '||r_mensajeVentaNoAcum.campanhaIniConcurso ||' a campaña ' || r_mensajeVentaNoAcum.campanhaFinConcurso ;
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                l_textoActual :=' No dejes de pasar la oportunidad de acumular puntos en estas ';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                l_textoActual := lnCampanhas ||
                                    ' campañas  y ganar fabulosas bonificaciones.
                                      Encuentra todas la bonificaciones ofrecidas en el folleto, recuerda revisar las bases del programa.</texto></ventas>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                 l_textoActual :='<nivelgral><img>'|| r_mensajeVentaNoAcum.imgBaseConcurso ||'</img></nivelgral>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nivelx><img></img></nivelx>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nively><img></img></nively>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 hayImagen:=TRUE;

                               end if;



                               IF(NOT hayImagen) THEN
                               --consejera NO llegó a ninguno de los niveles del programa de Bonificación

                                --si nos encontramos en segundo pedido
                                if(lnCampanhas=2) then

                                  l_textoActual := '<ventas><texto>'||  r_mensajeVentaNoAcum.nombres ||
                                     ' te Faltan '||  r_mensajeVentaNoAcum.ptsnv1 ||' puntos para llegar al N1 del programa de'||
                                     ' Bonificación, recuerda que te queda 1 campaña para alcanzar tu meta.</texto></ventas>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nivelgral><img>'||r_mensajeVentaNoAcum.imgBaseConcurso ||'</img></nivelgral>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nivelx><img>'|| r_mensajeVentaNoAcum.imgNivelObtenido ||'</img></nivelx>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nively><img>'|| r_mensajeVentaNoAcum.imgNivelSgte ||'</img></nively>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);

                                else--ultima campanha
                                  l_textoActual :='<ventas><texto></texto></ventas>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                  l_textoActual :='<nivelgral><img>'|| r_mensajeVentaNoAcum.imgNuevoConcurso ||'</img></nivelgral>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nivelx><img></img></nivelx>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                 l_textoActual :='<nively><img></img></nively>';
                                 DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual),l_textoActual);
                                end if;

                               END IF;


                            END;
                            l_correlativo:=l_correlativo + 1;
                        END LOOP;
                       CLOSE c_mensajeVentaNoAcum;


 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_CONCU_VENTA: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_CONCU_VENTA;




/**************************************************************************
Descripcion         : Genera la seccion de CONCURSO VIGENTES
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_CONCU_VIGEN(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


TYPE consolidadorecord IS RECORD (
    cod_clie              mae_clien.cod_clie%type,
    oid_clie              mae_clien.oid_clie%type,
    concurso              msg_buzon_mensa.DATO_VARI_01%type,
    numconcurso           msg_buzon_mensa.DATO_VARI_01%type,
    pacumul               msg_buzon_mensa.DATO_VARI_01%type,
    pcamp                 msg_buzon_mensa.DATO_VARI_01%type,
    pbnf                   msg_buzon_mensa.DATO_VARI_01%type,
    pesc                   msg_buzon_mensa.DATO_VARI_01%type,
    pdevul                   msg_buzon_mensa.DATO_VARI_01%type,
    ptotal                  msg_buzon_mensa.DATO_VARI_01%type
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;
r_concurso       consolidadorecord;


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
SELECT
   DISTINCT
     mc.COD_CLIE AS cod_clie,
     mc.OID_CLIE AS oid_clie,
    --- concurso
    ''  AS concurso,
    --- numero de concurso
    ''  AS numconcurso,
    --- puntaje acumulado
    ''  AS pacumul,
    --- cantidad
    ''  AS pcamp,
    --- puntaje bonificacion
    '' AS pbnf,
    --- puntaje Esika Center
    '' AS pesc,
    --- puntaje Devuelto
    '' AS pdevul,
    --- puntaje total
   ''  AS ptotal
FROM
    PED_SOLIC_CABEC oc,
    mae_clien mc,
    ped_tipo_solic_pais ptsp,
    ped_tipo_solic pts,
    (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
    msg_buzon_mensa bm,
    zon_regio r,
    zon_zona z
WHERE
        ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
        AND pts.OID_TIPO_SOLI = ts.val_oid
        AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
        AND pts.COD_TIPO_SOLI = 'SOC'
        AND oc.GRPR_OID_GRUP_PROC = 5
        AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
        AND oc.clie_oid_clie = mc.oid_clie
        AND oc.clie_oid_clie = bm.clie_oid_clie
        AND bm.mens_oid_mens
         IN((SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC01' AND B.oid_mens = bm.mens_oid_mens),
            (SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC03' AND B.oid_mens = bm.mens_oid_mens),
            (SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC21' AND B.oid_mens = bm.mens_oid_mens),
            (SELECT B.oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC96' AND B.oid_mens = bm.mens_oid_mens)
          )
        AND bm.IND_ACTI = 0
        AND bm.PERI_OID_PERI = oidPeriodo
        AND z.oid_zona = oc.ZZON_OID_ZONA
        AND r.oid_regi =  z.zorg_oid_regi
      and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona));


CURSOR c_mensajeINC01(oidCliente NUMBER,
                     oidPeriodo NUMBER) IS
        SELECT
                mc.COD_CLIE  as cod_clie ,
                mc.OID_CLIE  as oid_clie,
                --- concurso
                DATO_VARI_18 AS concurso ,
                --- numero de concurso
                DATO_VARI_02 AS numconcurso ,
                --- puntaje acumulado
                DATO_VARI_09 AS pacumul ,
                --- cantidad
                DATO_VARI_06  AS pcamp,
                --- puntaje bonificacion
                '' AS pbnf,
                --- puntaje Esika Center
                '' AS pesc,
                --- puntaje Devuelto
                DATO_VARI_03 AS pdevul,
                --- puntaje total
               to_char((DATO_VARI_09-DATO_VARI_03)) AS ptotal
            FROM
                PED_SOLIC_CABEC oc,
                mae_clien mc,
                ped_tipo_solic_pais ptsp,
                ped_tipo_solic pts,
                (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
                msg_buzon_mensa bm
            WHERE
                    ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                    AND pts.OID_TIPO_SOLI = ts.val_oid
                    AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                    AND pts.COD_TIPO_SOLI = 'SOC'
                    AND oc.GRPR_OID_GRUP_PROC = 5
                    AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
                    AND mc.oid_clie = oidCliente
                    AND oc.clie_oid_clie = mc.oid_clie
                    AND oc.clie_oid_clie = bm.clie_oid_clie
                    AND bm.mens_oid_mens = (SELECT oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC01')
                    AND bm.IND_ACTI = 0
                    AND bm.PERI_OID_PERI = oidPeriodo;

CURSOR c_mensajeINC03(oidCliente NUMBER,
                     oidPeriodo NUMBER) IS
    SELECT
        mc.COD_CLIE ,
        mc.OID_CLIE  ,
        --- concurso
        DATO_VARI_16  AS concurso,
        --- numero de concurso
        DATO_VARI_11  AS numconcurso,
        --- puntaje acumulado
        ''  AS pacumul,
        --- puntaje de campaña
        ''  AS pcamp,
        --- puntaje bonificacion
        '' AS pbnf,
        --- puntaje Esika Center
        '' AS pesc,
        --- puntaje Devuelto
        '' AS pdevul,
        --- puntaje total
        '' AS ptotal
    FROM
        PED_SOLIC_CABEC oc,
        mae_clien mc,
        ped_tipo_solic_pais ptsp,
        ped_tipo_solic pts,
        (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
        msg_buzon_mensa bm
    WHERE
            ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
            AND pts.OID_TIPO_SOLI = ts.val_oid
            AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
            AND pts.COD_TIPO_SOLI = 'SOC'
            AND oc.GRPR_OID_GRUP_PROC = 5
            AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
            AND mc.oid_clie = oidCliente
            AND oc.clie_oid_clie = mc.oid_clie
            AND oc.clie_oid_clie = bm.clie_oid_clie
            AND bm.mens_oid_mens = (SELECT oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC03')
            AND bm.IND_ACTI = 0
            AND bm.PERI_OID_PERI = oidPeriodo;

CURSOR c_mensajeINC21(oidCliente NUMBER,
                     oidPeriodo NUMBER) IS
        SELECT
            mc.COD_CLIE,
             mc.OID_CLIE  ,
            --- concurso
            DATO_VARI_18  AS concurso,
            --- numero de concurso
            DATO_VARI_02  AS numconcurso,
            --- puntaje acumulado
            DATO_VARI_09  AS pacumul,
            --- puntaje de campaña
            DATO_VARI_06  AS pcamp,
            --- puntaje bonificacion
            '' AS pbnf,
            --- puntaje Esika Center
            '' AS pesc,
            --- puntaje Devuelto
            '' AS pdevul,
            --- puntaje total
            '' AS ptotal
        FROM
            PED_SOLIC_CABEC oc,
            mae_clien mc,
            ped_tipo_solic_pais ptsp,
            ped_tipo_solic pts,
            (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
            msg_buzon_mensa bm
        WHERE
                ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                AND pts.OID_TIPO_SOLI = ts.val_oid
                AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                AND pts.COD_TIPO_SOLI = 'SOC'
                AND oc.GRPR_OID_GRUP_PROC = 5
                AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
                AND mc.oid_clie =oidCliente
                AND oc.clie_oid_clie = mc.oid_clie
                AND oc.clie_oid_clie = bm.clie_oid_clie
                AND bm.mens_oid_mens = (SELECT oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC21')
                AND bm.IND_ACTI = 0
                AND bm.PERI_OID_PERI = oidPeriodo;

CURSOR c_mensajeINC96(oidCliente NUMBER,
                     oidPeriodo NUMBER) IS
        SELECT
            mc.COD_CLIE,
            mc.OID_CLIE  ,
            --- concurso
            DATO_VARI_18  AS concurso,
            --- numero de concurso
            DATO_VARI_02  AS numconcurso,
            --- puntaje acumulado
            DATO_VARI_09  AS pacumul,
            --- puntaje de campaña
            DATO_VARI_06  AS pcamp,
            --- puntaje bonificacion
            '' AS pbnf,
            --- puntaje Esika Center
            '' AS pesc,
            --- puntaje Devuelto
            '' AS pdevul,
            --- puntaje total
            '' AS ptotal
        FROM
            PED_SOLIC_CABEC oc,
            mae_clien mc,
            ped_tipo_solic_pais ptsp,
            ped_tipo_solic pts,
            (SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PED_TIPO_SOLIC') ts,
            msg_buzon_mensa bm
        WHERE
                ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                AND pts.OID_TIPO_SOLI = ts.val_oid
                AND oc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                AND pts.COD_TIPO_SOLI = 'SOC'
                AND oc.GRPR_OID_GRUP_PROC = 5
                AND oc.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
                AND mc.oid_clie = oidCliente
                AND oc.clie_oid_clie = mc.oid_clie
                AND oc.clie_oid_clie = bm.clie_oid_clie
                AND bm.mens_oid_mens = (SELECT oid_mens FROM msg_mensa B WHERE B.COD_MENS = 'INC96')
                AND bm.IND_ACTI = 0
                AND bm.PERI_OID_PERI = oidPeriodo;


r_mensajeINC01 c_mensajeINC01%ROWTYPE;
r_mensajeINC03 c_mensajeINC03%ROWTYPE;
r_mensajeINC21 c_mensajeINC21%ROWTYPE;
r_mensajeINC96 c_mensajeINC96%ROWTYPE;


-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenConcursoVigen   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarConcursoVigentes');
l_maximoFilasConcursoVigen    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'maximoFilasConcursoVigentes');

lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);
l_formatoNumerico           VARCHAR2(100) := '9999999G990D00';

codigoPeriodoSgte           VARCHAR2(6);
numFila                     NUMBER(12);

l_Offset                    NUMBER := 1;
xmlClob                     CLOB := EMPTY_CLOB();
l_clobTemp                  CLOB := EMPTY_CLOB();
lnContExisteCliente         NUMBER:=0;
longitudAmout               NUMBER:=0;

lnDetalle                   NUMBER:=1;

BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenConcursoVigen IS NULL OR l_indicadorGenConcursoVigen <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);

    SELECT oid_peri
      INTO l_oidperiodo
      FROM seg_perio_corpo
     WHERE cod_peri = p_codigoperiodo;


    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_SALUD
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CONCU_VIGEN';

    numFila:=0;
    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
       FETCH c_ordenCompra BULK COLLECT INTO r_consolidado LIMIT W_FILAS; --INTO r_ordenCompra;
            IF  r_consolidado.COUNT > 0 THEN
              FOR i IN r_consolidado.FIRST..r_consolidado.LAST
              LOOP

                  --lnValNivelDeta

                        -- Insertamos el registro y obtenemos la referencia al CLOB
                        INSERT INTO IMP_PAQUE_DOCUM_CONCU_VIGEN (
                         COR_COVI,
                         COD_CLIE,
                         XML_CONS,
                         VAL_NIVE_DETA
                        )
                        VALUES(
                        l_correlativo,
                        r_consolidado(i).cod_clie,
                        EMPTY_CLOB(),
                        1)
                        RETURNING XML_CONS INTO l_clob;

                        -- Inicio
                        l_textoActual := '<tabla25>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                        r_concurso:=NULL;
                   lnDetalle:=1;
                   if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                        --Inicio Detalle inc01
                     OPEN c_mensajeINC01( r_consolidado(i).oid_clie, l_oidPeriodo);
                        LOOP
                        FETCH c_mensajeINC01 INTO r_mensajeINC01;
                        EXIT WHEN c_mensajeINC01%NOTFOUND;
                            BEGIN
                               if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                                l_textoActual :='<txt>'|| r_mensajeINC01.concurso       ||
                                           '<tr/>' ||r_mensajeINC01.numconcurso ||
                                           '<tr/>' ||r_mensajeINC01.pacumul    ||
                                           '<tr/>' || r_mensajeINC01.pcamp      ||
                                           '<tr/>' || r_mensajeINC01.pbnf       ||
                                           '<tr/>' || r_mensajeINC01.pesc       ||
                                           '<tr/>' || r_mensajeINC01.pdevul     ||
                                           '<tr/>' || r_mensajeINC01.ptotal     ||
                                         '</txt>';

                               DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               lnDetalle:=lnDetalle+1;
                              end if;
                            END;
                        END LOOP;
                        CLOSE c_mensajeINC01;
                   end if;

                    if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                      OPEN c_mensajeINC03( r_consolidado(i).oid_clie, l_oidPeriodo);
                        LOOP
                        FETCH c_mensajeINC03 INTO r_mensajeINC03;
                        EXIT WHEN c_mensajeINC03%NOTFOUND;
                            BEGIN
                               if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                                l_textoActual :='<txt>'|| r_mensajeINC03.concurso       ||
                                           '<tr/>' ||r_mensajeINC03.numconcurso ||
                                           '<tr/>' ||r_mensajeINC03.pacumul    ||
                                           '<tr/>' || r_mensajeINC03.pcamp      ||
                                           '<tr/>' || r_mensajeINC03.pbnf       ||
                                           '<tr/>' || r_mensajeINC03.pesc       ||
                                           '<tr/>' || r_mensajeINC03.pdevul     ||
                                           '<tr/>' || r_mensajeINC03.ptotal     ||
                                         '</txt>';
                                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                lnDetalle:=lnDetalle+1;
                               end if;
                            END;
                        END LOOP;
                      CLOSE c_mensajeINC03;
                    end if;

                   if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then

                     OPEN c_mensajeINC21( r_consolidado(i).oid_clie, l_oidPeriodo);
                        LOOP
                        FETCH c_mensajeINC21 INTO r_mensajeINC21;
                        EXIT WHEN c_mensajeINC21%NOTFOUND;
                            BEGIN
                              if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                                l_textoActual :='<txt>'|| r_mensajeINC21.concurso       ||
                                           '<tr/>' ||r_mensajeINC21.numconcurso ||
                                           '<tr/>' ||r_mensajeINC21.pacumul    ||
                                           '<tr/>' || r_mensajeINC21.pcamp      ||
                                           '<tr/>' || r_mensajeINC21.pbnf       ||
                                           '<tr/>' || r_mensajeINC21.pesc       ||
                                           '<tr/>' || r_mensajeINC21.pdevul     ||
                                           '<tr/>' || r_mensajeINC21.ptotal     ||
                                         '</txt>';
                               DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                                lnDetalle:=lnDetalle+1;
                              end if;
                            END;
                        END LOOP;
                       CLOSE c_mensajeINC21;
                   end if;

                    if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                        OPEN c_mensajeINC96( r_consolidado(i).oid_clie, l_oidPeriodo);
                        LOOP
                        FETCH c_mensajeINC96 INTO r_mensajeINC96;
                        EXIT WHEN c_mensajeINC96%NOTFOUND;
                            BEGIN
                              if(lnDetalle <= to_number(l_maximoFilasConcursoVigen)) then
                                l_textoActual :='<txt>'|| r_mensajeINC96.concurso       ||
                                           '<tr/>' ||r_mensajeINC96.numconcurso ||
                                           '<tr/>' ||r_mensajeINC96.pacumul     ||
                                           '<tr/>' || r_mensajeINC96.pcamp      ||
                                           '<tr/>' || r_mensajeINC96.pbnf       ||
                                           '<tr/>' || r_mensajeINC96.pesc       ||
                                           '<tr/>' || r_mensajeINC96.pdevul     ||
                                           '<tr/>' || r_mensajeINC96.ptotal     ||
                                         '</txt>';
                               DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
                               lnDetalle:=lnDetalle+1;
                              end if;
                            END;
                        END LOOP;
                       CLOSE c_mensajeINC96;

                    end if;

                       -- Fin
                        l_textoActual := '</tabla25>';
                        DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);


                         l_correlativo:=l_correlativo + 1;
                         numFila := numFila +1;



              END LOOP;

             END IF;
       EXIT WHEN c_ordenCompra%NOTFOUND;
    END LOOP;
    CLOSE c_ordenCompra;




 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_CONCU_VIGEN: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_CONCU_VIGEN;



/***************************************************************************
Descripcion       : Funcion usada para obtener la sentencia SQL dinamica usada
                    para la compaginacion, la cual toma la parametria especificada
                    en la tabla IMP_FORMU_PAQUE_DOCUM, recibe como parametro el
                    numero de documento el cual se compara con el valor de la
                    columna NUM_PADO de la tabla anterior, asi como el orden
                    especificado en la tabla VAL_ORDE_SECU.

Fecha Creacion    : 15/12/2010
Autor             : Sergio Buchelli
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_SQL (p_numeroPaquete NUMBER)
RETURN VARCHAR2
IS

l_result VARCHAR2(4000);
l_count  NUMBER := 0;

-- Cursor para la extraccion de las funciones
CURSOR c_func IS
SELECT VAL_FUNC_XML,
    VAL_NUME_PAGI,
    nvl((select VAL_PARA_PRIM
    from IMP_PARAM_PROCE_IMPRE
    where PRIM_COD_PROC='LAS'
    and NOM_PARA_PRIM = 'tagInicioPag'|| val_nume_pagi),'')   tagIni,
    nvl((select VAL_PARA_PRIM
    from IMP_PARAM_PROCE_IMPRE
    where PRIM_COD_PROC='LAS'
    and NOM_PARA_PRIM = 'tagFinPag'|| val_nume_pagi),'') tagFin
FROM IMP_FORMU_PAQUE_DOCUM
WHERE NUM_PADO = p_numeroPaquete
  AND EST_FORM_PADO = 1
ORDER BY NUM_PADO,
         VAL_NUME_PAGI,
         NUM_ORDE_SECU;

r_func c_func%ROWTYPE;

lnInicioPaginado    NUMBER:=0;
lnInicioPaginadoAnt NUMBER:=0;
lsTagPagFinAnt      VARCHAR2(15);
lsTagInicio         VARCHAR2(15);
nfilas              NUMBER:=0;

BEGIN

    SELECT COUNT(COD_FORM_PADO)
    INTO l_count
    FROM IMP_FORMU_PAQUE_DOCUM
    WHERE NUM_PADO = p_numeroPaquete
    AND EST_FORM_PADO = 1;

    IF l_count = 0 THEN
        l_result := NULL;
    ELSE
        l_result := l_result || ' INSERT INTO IMP_PAQUE_DOCUM_COLOR ';
        l_result := l_result || ' SELECT X.COR_REPE, ';

        lnInicioPaginado:=0;

        nfilas:=0;
        OPEN c_func;
        LOOP
        FETCH c_func INTO r_func;
        EXIT WHEN c_func%NOTFOUND;
            -- Concatenamos los valores de las funciones en base a la
            -- tabla de parametria la cual contiene las funciones que
            -- extraen la informacion de los bloques XML
            -- se arma de acuerdo a la paginacion
               lsTagInicio:=''''|| r_func.tagIni ||'''';
               if(nfilas=0) then
                 lnInicioPaginadoAnt:= r_func.VAL_NUME_PAGI;
                 lnInicioPaginado := r_func.VAL_NUME_PAGI;
                 lsTagPagFinAnt:= ''''|| r_func.tagFin ||'''';

                 l_result := ' ' || l_result || lsTagInicio  || ' || ' || '(' || r_func.VAL_FUNC_XML || ')'|| ' || ';

               else
                 lnInicioPaginado := r_func.VAL_NUME_PAGI;

                 -- verificar si hay cambio de paginado
                     if(lnInicioPaginado <> lnInicioPaginadoAnt) then
                       --cambio de paginado se coloca el fin de paginacion
                       l_result := ' ' || l_result || lsTagPagFinAnt ||'||' || lsTagInicio || ' || ' ||  '(' || r_func.VAL_FUNC_XML || ')'|| ' || ';
                     else
                       l_result := ' ' || l_result  ||  '(' || r_func.VAL_FUNC_XML || ')'|| ' || ';
                     end if;
                     lnInicioPaginadoAnt:= lnInicioPaginado;
                     lsTagPagFinAnt:= ''''|| r_func.tagFin ||'''';
               end if;
                nfilas:=nfilas+1;
        END LOOP;
        CLOSE c_func;
        --falta cerrar el paginado final
        l_result := ' ' || l_result  ||  lsTagPagFinAnt || ' || ';

        l_result := l_result || ' ''' || '' || ''', ';
        l_result := l_result || ' X.COD_CLIE, ';
        l_result := l_result || 1 || ', ';
        l_result := l_result || ' X.VAL_NUME_SOLI,'''' ';
        l_result := l_result || ' FROM IMP_PAQUE_DOCUM_RESUM_PEDID X ';
       -- l_result := l_result || ' ORDER BY X.COR_REPE ';
    END IF;

    RETURN l_result;

END IMP_FN_COMPA_XML_SQL;


/***************************************************************************
Descripcion       : Procedimiento que limpia las tablas en las cuales se cargan
                    los archivos del paquete documentario
Fecha Creacion    : 15/12/2010
Autor             : Sergio buchelli
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_PAQUE_DOCUM_COLOR IS
BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_COLOR';

END IMP_PR_ELIMI_PAQUE_DOCUM_COLOR;


/**************************************************************************
Descripcion         :  general el archivo xml en txt
Fecha Creación      : 15/12/2010
Autor               : Sergio Buchelli
Parametors
  p_nombreArchivo : Nombre Archivo
  p_directorio  : Directorio
  p_numeroDocumento :Numeros de Documento
  p_totalArchivos : Numeros d earchivo
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_COLOR_FILE(
                                     p_codigoPeriodo VARCHAR2,
                                     p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1,
                                     p_totalArchivos NUMBER := 1) IS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;
l_numeroArchivo  INTEGER;
l_filaInicio     NUMBER := 0;
l_filaFin        NUMBER := 0;
l_totalFilas     NUMBER;
l_subtotalFilas  NUMBER;
l_nombre         VARCHAR2(100);
l_extension      VARCHAR2(100);
l_separador      VARCHAR2(1) := '.';
l_nombreCompleto VARCHAR2(100);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
lvTagInicioColor  VARCHAR2(8);
lvTagFinColor  VARCHAR2(8);

CURSOR c1(filaInicio NUMBER, filaFin NUMBER) IS
SELECT   P.XML_CONS
    FROM IMP_PAQUE_DOCUM_COLOR P
   WHERE P.NUM_PDCO = p_numeroDocumento
     AND P.COR_PDCO > filaInicio
     AND P.COR_PDCO <= filaFin
ORDER BY P.COR_PADO;

BEGIN

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_OUT_DIR AS ''' || p_directorio || '''';

    -- Separamos el nombre del archivo
    IF (INSTR(p_nombreArchivo, l_separador)) <> 0 THEN
        l_nombre := SUBSTR(p_nombreArchivo, 1, INSTR(p_nombreArchivo, l_separador, -1) - 1);
        l_extension := SUBSTR(p_nombreArchivo, INSTR(p_nombreArchivo, l_separador, -1) + 1, LENGTH(p_nombreArchivo));
    END IF;

    -- Iteramos la creacion del archivo dependiendo de la cantidad
    -- de archivos a generar la cual es pasado como parametro
    SELECT MAX(COR_PDCO)
    INTO l_totalFilas
    FROM IMP_PAQUE_DOCUM_COLOR
    WHERE NUM_PDCO = p_numeroDocumento;

    -- Inicializamos las variables
    l_numeroArchivo := 0;
    l_subtotalFilas := ROUND(l_totalFilas / p_totalArchivos);
    l_filaInicio := 0;
    l_filaFin := l_subtotalFilas;

    -- Iteramos por la cantidad de archivos a generar
    FOR i IN 1..p_totalArchivos LOOP

        -- Abrimos el cursor y el archivo a crear
        l_numeroArchivo := l_numeroArchivo + 1;

        -- Creamos el nombre del archivo
        IF p_totalArchivos > 1 THEN
            l_nombreCompleto := l_nombre || '_' || TO_CHAR(l_numeroArchivo) || l_separador || l_extension;
            l_output := UTL_FILE.fopen ('XML_OUT_DIR', l_nombreCompleto, 'wb', 32760);
        ELSE
            l_output := UTL_FILE.fopen ('XML_OUT_DIR', p_nombreArchivo, 'wb', 32760);
        END IF;

        -- Escribimos la cabecera y el tag inicial de la raiz del XML
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<spoolpd>'), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        --obtenemos y escribimos la etiqueta de color de incio
        --lvTagInicioColor:='<tp' || SUBSTR(p_codigoPeriodo, 5, 2) || '>';
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(lvTagInicioColor), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);


        OPEN c1(l_filaInicio, l_filaFin);
        -- Iteramos sobre el cursor
        LOOP
            FETCH c1 INTO t_clob;
            EXIT WHEN C1%NOTFOUND;
            l_length := DBMS_LOB.GETLENGTH(t_clob);
            position := 1;
            l_offset := 1;
            l_amt := 4000;
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<pd>'), TRUE);
            -- Escribimos los bloques en el archivo
            WHILE (l_offset <= l_length) LOOP
              IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
                dbms_lob.read (t_clob, l_amt, l_offset, x);
                UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
                l_offset := l_offset + l_amt;
                position := position + 4000;
                x := null;
            END LOOP;

            -- Escribimos el tag de cierre del xml
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</pd>'), TRUE);
            -- Escribimos el cambio de linea en el archivo
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        END LOOP;


        --obtenemos y escribimos la etiqueta de color final
        --lvTagFinColor:='</tp' || SUBSTR(p_codigoPeriodo, 5, 2) || '>';
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(lvTagFinColor), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        -- Escribimos el tag de cierre del xml
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</spoolpd>'), TRUE);

        -- Cerramos el archivo
        UTL_FILE.fclose (l_output);

        -- Cerramos el cursor
        CLOSE c1;

        -- Reasignamos los valores de inicio y fin de filas
        l_filaInicio := l_filaFin;
        l_filaFin := l_filaFin + l_subtotalFilas;

    END LOOP;

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.WRITE_ERROR THEN
           l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
           RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_PAQUE_DOCUM_COLOR_FILE;


/**************************************************************************
Descripcion         : Genera la seccion de bienvenida transpromo
Fecha Creación      :  03/03/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/**************************************************************************/
PROCEDURE IMP_PR_PROCE_BIENV_TRANS(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_ordenCompra(oidPais NUMBER,
                     oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
    select
       mc.oid_clie,
       mc.cod_clie,
       mc.val_nom1,
       mc.val_nom2,
       r.cod_regi,
       z.cod_zona
    from mae_clien mc,
         ped_solic_cabec con,
         mae_clien_datos_adici mad,
         zon_zona z,
         zon_regio r
    where mc.oid_clie = con.clie_oid_clie
      and mad.clie_oid_clie = mc.oid_clie
      and con.perd_oid_peri = oidPeriodo
      and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
      and con.num_lote_fact = numeroLoteFacturacion
      and con.num_unid_aten_tota > 0
      and r.oid_regi = z.ZORG_OID_REGI
      and z.oid_zona = con.ZZON_OID_ZONA
      and exists (
          select null
          from int_lar_tipo_solici_pedido_dis l
          where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
      )
     and(
        exists(select null
            from imp_plan_pilot x
            where x.cod_regi is  null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is not null
             and x.cod_zona is null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and r.cod_regi=x.cod_regi)
        or
         exists(select null
            from imp_plan_pilot x
            where x.cod_regi is null
             and x.cod_zona is not null
             and x.cod_peri= p_codigoPeriodo
             and x.ind_acti=1 and z.cod_zona=x.cod_zona)
       )
    order by mc.cod_clie,
             con.val_nume_soli;

r_ordenCompra c_ordenCompra%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_codigoPeriodo                 VARCHAR2(25);
l_correlativo                   NUMBER := 1;
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;

l_indicadorEnvioLarissa        VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
--NUEVO PARAMETRO
l_indicadorGenerarSeccBienv    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarSeccionBienvenida');
lsFechaIni                     VARCHAR2(10);
lsFichaFinal                   VARCHAR2(10);
nombreImagen                   VARCHAR2(15);
l_textoActual                  VARCHAR2(1000) := '';
lsFecha                        VARCHAR2(10);

lnCont                         NUMBER;
BEGIN

    -- SE VALIDARA SI EL PARAMETRO ESTA ACTIVO 'S' , si no esta activo NO se realizara el proceso de generacion de la OCS
    IF(l_indicadorGenerarSeccBienv IS NULL OR l_indicadorGenerarSeccBienv <>'S')THEN
      RETURN;
    END IF;

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);



       BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = l_oidPeriodo
           AND con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
           AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
       END;




    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SECCI_BIENV
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SECCI_BIENV';


    OPEN c_ordenCompra(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_ordenCompra INTO r_ordenCompra;
    EXIT WHEN c_ordenCompra%NOTFOUND;
        BEGIN

            SELECT count(1) into lnCont
            FROM IMP_PAQUE_DOCUM_CARTA_CONSU
            WHERE COD_PERI= p_codigoPeriodo
             AND COD_CLIE= r_ordenCompra.cod_clie;

            if(lnCont=0) then

                INSERT INTO IMP_PAQUE_DOCUM_CARTA_CONSU(
                   COD_PERI,
                   COD_CLIE,
                   NOM_CART,
                   FEC_MODI)
                VALUES (p_codigoPeriodo,
                    r_ordenCompra.cod_clie,
                    r_ordenCompra.val_nom1 ||' '|| r_ordenCompra.val_nom2 ,
                    SYSDATE
                );

               INSERT INTO IMP_PAQUE_DOCUM_SECCI_BIENV(
                COR_PDSB,
                COD_CLIE,
                XML_CONS
                )
                VALUES (
                l_correlativo,
                r_ordenCompra.cod_clie,
                EMPTY_CLOB())
                RETURNING XML_CONS INTO l_clob;

                --
                l_textoActual := '<runps>Bienvenida</runps><nombre>'|| r_ordenCompra.val_nom1 ||' '|| r_ordenCompra.val_nom2 ||'</nombre>';
                DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

                l_correlativo := l_correlativo + 1;

            end if;


        END;
    END LOOP;
    CLOSE c_ordenCompra;
 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_BIENV_TRANS: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_BIENV_TRANS;

/**************************************************************************
Descripcion         : elimina etiquetas de paq doc a blanco y nero
                      devuelve el clob cambiado
Fecha Creación      :  30/03/2011
Parametros Entrada  :
    xml                   : Xml de la consultora
    psCorrelativo         : Correlativo  paq documentario
    p_fechaFacturacion    : Numero Paq Docu
Autor               : Sergio Buchelli
/**************************************************************************/
FUNCTION IMP_FN_ELIMI_BLOQU_PAQUE(xml IN CLOB)
RETURN CLOB
IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;
l_etiqueta          IMP_BLOQU_ETIQU_ELIMI.VAL_ETIQ_DESD%TYPE;
l_etiqueta_Cierre   IMP_BLOQU_ETIQU_ELIMI.VAL_ETIQ_HAST%TYPE;
l_etiquetaApertura  VARCHAR2(100);
l_etiquetaCierre    VARCHAR2(100);
l_etiquetaReemplazo VARCHAR2(100);
l_longitudApertura  NUMBER;
l_longitudCierre    NUMBER;
l_subcontenido      CLOB;
lsIndicadorFormulario VARCHAR2(1);


CURSOR c_etiquetas IS
SELECT VAL_ETIQ_DESD,
       VAL_ETIQ_HAST,
       IND_FORM
FROM IMP_BLOQU_ETIQU_ELIMI
WHERE IND_ACTI = '1'
ORDER BY ORD_ELIM;

BEGIN
   l_CLOB:=xml;
  /* Iteramos sobre cada una de las etiquetas que estan en la tabla
     IMP_ETIQU_XML y obtenemos los bloques XML que encierran. */
  OPEN c_etiquetas;
  LOOP
  FETCH c_etiquetas INTO l_etiqueta,l_etiqueta_Cierre,lsIndicadorFormulario;
  EXIT WHEN c_etiquetas%NOTFOUND;
    IF(INSTR(l_CLOB, l_etiqueta) != 0) THEN
        l_etiquetaApertura :=  l_etiqueta;
        l_etiquetaCierre :=  l_etiqueta_Cierre ;
        l_longitudApertura := LENGTH(l_etiquetaApertura);
        l_longitudCierre := LENGTH(l_etiquetaCierre);

        -- Reemplazamos por una cadena de comentario XML
        l_etiquetaReemplazo := '<!-- -->';
        IF(lsIndicadorFormulario='1') THEN
            l_subcontenido := DBMS_LOB.SUBSTR(l_CLOB, INSTR(l_CLOB, l_etiquetaCierre, -1) - (INSTR(l_CLOB, l_etiquetaApertura)) + l_longitudCierre, INSTR(l_CLOB, l_etiquetaApertura));
        ELSE
            l_subcontenido := DBMS_LOB.SUBSTR(l_CLOB, INSTR(l_CLOB, l_etiquetaCierre, -1) - (INSTR(l_CLOB, l_etiquetaApertura)) , INSTR(l_CLOB, l_etiquetaApertura));

        END IF;

        --IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(xml, l_subcontenido, l_etiquetaReemplazo);
        l_CLOB:= IMP_FN_ELIMI_BLOQU_PAQUE(l_CLOB,l_subcontenido,l_etiquetaReemplazo);
    END IF;
  END LOOP;
  CLOSE c_etiquetas;

 RETURN l_CLOB;
END IMP_FN_ELIMI_BLOQU_PAQUE;

/**************************************************************************
Descripcion         : elimina etiquetas de paq doc a blanco y nero
                      devuelve el clob cambiado
Fecha Creación      :  31/03/2011
Parametros Entrada  :
    xml             : Xml de la consultora
    replaceStr      : Correlativo  paq documentario
    replaceWith     : Numero Paq Docu
Autor               : Sergio Buchelli
/**************************************************************************/
FUNCTION IMP_FN_ELIMI_BLOQU_PAQUE (
srcClob IN CLOB,
replaceStr IN VARCHAR2,
replaceWith IN VARCHAR2)
RETURN CLOB IS

vBuffer    VARCHAR2 (32767);
l_amount   BINARY_INTEGER := 32767;
l_pos      PLS_INTEGER := 1;
l_clob_len PLS_INTEGER;
newClob    CLOB := EMPTY_CLOB;

BEGIN
  -- initalize the new clob
  dbms_lob.createtemporary(newClob,TRUE);

  l_clob_len := dbms_lob.getlength(srcClob);

  WHILE l_pos <= l_clob_len
  LOOP
    dbms_lob.read(srcClob, l_amount, l_pos, vBuffer);

    IF vBuffer IS NOT NULL THEN
      -- replace the text
      vBuffer := replace(vBuffer, replaceStr, replaceWith);
      -- write it to the new clob
      dbms_lob.writeappend(newClob, LENGTH(vBuffer), vBuffer);
    END IF;
    l_pos := l_pos + l_amount;
  END LOOP;

  RETURN newClob;
EXCEPTION
  WHEN OTHERS THEN
    RAISE;
END IMP_FN_ELIMI_BLOQU_PAQUE;

/******************************************************************************************
Descripcion         : Genera la SECUNECIA EN COLOR EN BASE A LA SECUENCIA DE BLANCO Y NEGRO
Fecha Creación      :  13/12/2010
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_codigoPeriodo         : Codigo periodo
    p_fechaFacturacion     : Fecha Facturacion
/*****************************************************************************************/
PROCEDURE IMP_PR_PROCE_SECUE_COLOR(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2)
IS
w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);


CURSOR c_paquete IS
        select x.cor_pado,
               x.xml_cons,
               X.COD_CLIE
        from imp_paque_docum x,
             imp_paque_docum_color y
        where x.num_pado=1
         and x.cod_clie = y.cod_clie;
        --order by x.cor_pado;

r_paquete c_paquete%ROWTYPE;

-- Variables locales
l_etiqueta          VARCHAR2(100);
l_etiqueta_Cierre   VARCHAR2(100);
l_etiquetaApertura  VARCHAR2(100);
l_etiquetaCierre    VARCHAR2(100);
l_etiquetaReemplazo VARCHAR2(100);
l_longitudApertura  NUMBER;
l_longitudCierre    NUMBER;
l_subcontenido      CLOB;

xmlClob             CLOB := EMPTY_CLOB();
l_clobTemp          CLOB := EMPTY_CLOB();

offset              NUMBER;
BEGIN

 l_etiqueta:='<nsec>';
 l_etiqueta_Cierre:= '</nsec>';

   -- Iteramos sobre el cursor
    OPEN c_paquete;
    LOOP
        FETCH c_paquete INTO r_paquete;
        EXIT WHEN c_paquete%NOTFOUND;
        --
        l_clobTemp:= r_paquete.xml_cons;
        IF(INSTR(l_clobTemp, l_etiqueta) != 0) THEN

           l_etiquetaApertura :=  l_etiqueta;
           l_etiquetaCierre :=  l_etiqueta_Cierre ;
           l_longitudApertura := LENGTH(l_etiquetaApertura);
           l_longitudCierre := LENGTH(l_etiquetaCierre);


           l_subcontenido := DBMS_LOB.SUBSTR(l_clobTemp, INSTR(l_clobTemp, l_etiquetaCierre) -
                                                       (INSTR(l_clobTemp, l_etiquetaApertura)) + l_longitudCierre,
                                                        INSTR(l_clobTemp, l_etiquetaApertura));
          begin
           SELECT XML_CONS INTO xmlClob
           FROM IMP_PAQUE_DOCUM_COLOR
           WHERE COD_CLIE = r_paquete.COD_CLIE;
          exception
            when others then
             xmlClob := EMPTY_CLOB();
          end;


          if( dbms_lob.getlength(xmlClob) > 0) then
            --copy
            --l_etiquetaApertura :=  '<pag01>';
            select VAL_PARA_PRIM into l_etiquetaApertura
            from IMP_PARAM_PROCE_IMPRE
            where PRIM_COD_PROC='LAS'
                and NOM_PARA_PRIM = 'tagInicioPag1';

            l_etiquetaReemplazo:= l_etiquetaApertura || l_subcontenido; --<PAG01><NSEC>XX</NSEC>
            offset:= INSTR(xmlClob, l_etiquetaApertura)+ LENGTH(l_etiquetaApertura);
            -- esta funcion hace reemplazo de etiquetas
            xmlClob:= IMP_FN_ELIMI_BLOQU_PAQUE(xmlClob,l_etiquetaApertura,l_etiquetaReemplazo);


          end if;

        end IF;

            --obtenemos la secuencia
            UPDATE IMP_PAQUE_DOCUM_COLOR A
            SET   A.COR_PADO = r_paquete.COR_PADO,
                  A.XML_CONS = xmlClob
            WHERE  A.COD_CLIE = r_paquete.COD_CLIE;


    END LOOP;
    -- Cerramos el cursor
    CLOSE c_paquete;

 EXCEPTION
  WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PROCE_SECUE_COLOR: '||substr(sqlerrm,1,250));
END IMP_PR_PROCE_SECUE_COLOR;


/**************************************************************************
Descripcion        : Realiza validaciones para la carga de Seccion Compartamos
Fecha Creacion     : 28/04/2011
Parametros Entrada :
           psCodigoRegion : Codigo Region
           psCodigoZona : Codigo Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION IMP_FN_VALID_SECCI_COMPA(psCodigoRegion  VARCHAR2,
                                  psCodigoZona    VARCHAR2)
RETURN VARCHAR2 IS
  lnOcurrencias       NUMBER;
  lsCodigoError       VARCHAR2(2);
BEGIN
  lsCodigoError := '00';

  --Validamos si existe el Codigo de Zona
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM ZON_ZONA
   WHERE COD_ZONA = psCodigoZona;

  IF (lnOcurrencias = 0) THEN
     lsCodigoError := '01';
  END IF;

  --Validamos si la zona pertenece a la region
  IF (lsCodigoError = '00') THEN
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM ZON_ZONA zon, ZON_REGIO reg
     WHERE zon.ZORG_OID_REGI = reg.OID_REGI
       AND zon.COD_ZONA = psCodigoZona
       AND reg.COD_REGI = psCodigoRegion;

    IF (lnOcurrencias = 0) THEN
       lsCodigoError := '02';
    END IF;
  END IF;

  RETURN lsCodigoError;

END IMP_FN_VALID_SECCI_COMPA;


/**************************************************************************
Descripcion        : Realiza validaciones para la carga de Seccion Focalizados
Fecha Creacion     : 02/05/2011
Parametros Entrada :
           psCodigoZona : Codigo Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION IMP_FN_VALID_SECCI_FOCAL(psCodigoZona    VARCHAR2)
RETURN VARCHAR2 IS
  lnOcurrencias       NUMBER;
  lsCodigoError       VARCHAR2(2);
BEGIN
  lsCodigoError := '00';

  --Validamos si existe el Codigo de Zona
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM ZON_ZONA
   WHERE COD_ZONA = psCodigoZona;

  IF (lnOcurrencias = 0) THEN
     lsCodigoError := '01';
  END IF;

  --Validamos si la zona no sea de tipo Oficina
 /* IF (lsCodigoError = '00') THEN
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM ZON_ZONA
     WHERE COD_ZONA = psCodigoZona
       AND NVL(IND_OFIC,'0') = '0';

    IF (lnOcurrencias = 0) THEN
       lsCodigoError := '02';
    END IF;
  END IF;*/

  RETURN lsCodigoError;

END IMP_FN_VALID_SECCI_FOCAL;

END;
/
