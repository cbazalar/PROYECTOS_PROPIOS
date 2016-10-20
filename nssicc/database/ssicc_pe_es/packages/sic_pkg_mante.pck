CREATE OR REPLACE PACKAGE SIC_PKG_MANTE IS
   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;
   TYPE tRegListaMenu IS RECORD (
     oid_func        men_funci.oid_func%TYPE,
     ind_nive        men_funci.ind_nive%TYPE,
     func_oid_func   men_funci.func_oid_func%TYPE,
     val_i18n        gen_i18n_sicc_comun.val_i18n%TYPE,
     ind_acce        VARCHAR2(12),
     val_rama_menu   NUMBER(1)
   );
  TYPE TABLA_LISTA_MENU IS TABLE OF tRegListaMenu ;

  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=5000;

/***************************************************************************
Descripcion       : Devuelve Table de Menu del SICC
Fecha Creacion    : 22/03/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION SIC_FN_OBTIE_LISTA_MENUS
RETURN TABLA_LISTA_MENU PIPELINED;

/***************************************************************************
Descripcion       : Devuelve Table de Menu del SICC de acuerdo al ROL
Fecha Creacion    : 23/03/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION SIC_FN_OBTIE_LISTA_MENUS_ROL(pnidRol NUMBER)
RETURN TABLA_LISTA_MENU PIPELINED;

/**************************************************************************
  Descripcion       : Devuelve los datos del usuario
  Fecha Creacion    : 29/03/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
FUNCTION sic_fn_devue_datos_byusu
(
  pnidprincipal NUMBER,
  pnidproperty  NUMBER
) RETURN VARCHAR2;

/***********************************************************************************
Descripcion        : Inserta Los datos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_inser_datos_usuar
(
  pslogin           VARCHAR2,
  psprimerapellido  VARCHAR2,
  pssegundoapellido VARCHAR2,
  psprimernombre    VARCHAR2,
  pssegundonombre   VARCHAR2,
  psemail           VARCHAR2,
  pstelefono        VARCHAR2,
  psclavedefault    VARCHAR2
);
/***********************************************************************************
Descripcion        : Actualiza Los datos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_actua_datos_usuar
(
  psidusuario       VARCHAR2,
  psprimerapellido  VARCHAR2,
  pssegundoapellido VARCHAR2,
  psprimernombre    VARCHAR2,
  pssegundonombre   VARCHAR2,
  psemail           VARCHAR2,
  pstelefono        VARCHAR2
);
/***********************************************************************************
Descripcion        : Actualiza Los datos Masivos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_actua_datos_masiv(psidusuario VARCHAR2);
/***********************************************************************************
Descripcion        : Actualiza Los datos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_restb_usuar
(
  psidusuario    VARCHAR2,
  psclavedefault VARCHAR2
);
/***********************************************************************************
Descripcion        : Bloquea/Desbloquea el Usuario SICC

Fecha Creacion     : 18/06/2010
Autor              : Carlos Diaz Valverde
***************************************************************************/
PROCEDURE SEG_PR_BLOQU_DESBL_USUAR
(
  PSIDUSUARIO    VARCHAR2,
  PNFLAGBLOQUEO  NUMBER
);

END SIC_PKG_MANTE;
/

CREATE OR REPLACE PACKAGE BODY SIC_PKG_MANTE
IS

/***************************************************************************
Descripcion       : Devuelve Table de Menu del SICC
Fecha Creacion    : 22/03/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION SIC_FN_OBTIE_LISTA_MENUS
RETURN TABLA_LISTA_MENU PIPELINED
IS
  tablaRegistro     TABLA_LISTA_MENU;
  registro          tRegListaMenu;
  lnoid_func        men_funci.oid_func%TYPE;
  lsind_nive        men_funci.ind_nive%TYPE;
  lnfunc_oid_func   men_funci.func_oid_func%TYPE;
  lsval_i18n        gen_i18n_sicc_comun.val_i18n%TYPE;
  i                 BINARY_INTEGER := 0;
  ESPACIO           VARCHAR2(10):='          ';

  CURSOR cOpciones(voidPadre NUMBER) IS
  select oid_func, ind_nive, func_oid_func,val_i18n
  from men_funci, gen_i18n_sicc_comun
  WHERE oid_func=val_oid and attr_enti='MEN_FUNCI'
   AND func_oid_func =voidPadre
   ORDER BY val_i18n;

  CURSOR cOpciones2(voidPadre NUMBER) IS
  select oid_func, ind_nive, func_oid_func,val_i18n
  from men_funci, gen_i18n_sicc_comun
  WHERE oid_func=val_oid and attr_enti='MEN_FUNCI'
   AND func_oid_func =voidPadre
   ORDER BY val_i18n;

  CURSOR cOpciones3(voidPadre NUMBER) IS
  select oid_func, ind_nive, func_oid_func,val_i18n
  from men_funci, gen_i18n_sicc_comun
  WHERE oid_func=val_oid and attr_enti='MEN_FUNCI'
   AND func_oid_func =voidPadre
   ORDER BY val_i18n;

  CURSOR cOpciones4(voidPadre NUMBER) IS
  select oid_func, ind_nive, func_oid_func,val_i18n
  from men_funci, gen_i18n_sicc_comun
  WHERE oid_func=val_oid and attr_enti='MEN_FUNCI'
   AND func_oid_func =voidPadre
   ORDER BY val_i18n;

BEGIN
  tablaRegistro := TABLA_LISTA_MENU();

  /* Obtiene 1er registro */
  SELECT oid_func, ind_nive, func_oid_func,val_i18n
  INTO lnoid_func, lsind_nive, lnfunc_oid_func, lsval_i18n
  FROM  men_funci, gen_i18n_sicc_comun
  WHERE oid_func=val_oid
  AND attr_enti='MEN_FUNCI'
  AND ind_nive = 0
  AND func_oid_func IS NULL;

  registro.oid_func := lnoid_func;
  registro.ind_nive := lsind_nive;
  registro.func_oid_func := lnfunc_oid_func;
  registro.val_i18n :=  lsval_i18n;
  registro.val_rama_menu := 0;
  i := i + 1;
  tablaRegistro.EXTEND;
  tablaRegistro(i) := registro;
  PIPE ROW(tablaRegistro(i));

  FOR cOpcion IN cOpciones(registro.oid_func) LOOP
      i := i + 1;
      tablaRegistro.EXTEND;
      registro.oid_func := cOpcion.oid_func;
      registro.ind_nive := cOpcion.ind_nive;
      registro.func_oid_func := cOpcion.func_oid_func;
      registro.val_i18n :=  ESPACIO || cOpcion.val_i18n;
      registro.val_rama_menu := 1;
      tablaRegistro(i) := registro;
      PIPE ROW(tablaRegistro(i));

      FOR cOpcion2 IN cOpciones2(registro.oid_func) LOOP
          i := i + 1;
          tablaRegistro.EXTEND;
          registro.oid_func := cOpcion2.oid_func;
          registro.ind_nive := cOpcion2.ind_nive;
          registro.func_oid_func := cOpcion2.func_oid_func;
          registro.val_i18n :=  ESPACIO || ESPACIO || cOpcion2.val_i18n;
          registro.val_rama_menu := 2;
          tablaRegistro(i) := registro;
          PIPE ROW(tablaRegistro(i));

          FOR cOpcion3 IN cOpciones3(registro.oid_func) LOOP
              i := i + 1;
              tablaRegistro.EXTEND;
              registro.oid_func := cOpcion3.oid_func;
              registro.ind_nive := cOpcion3.ind_nive;
              registro.func_oid_func := cOpcion3.func_oid_func;
              registro.val_i18n := ESPACIO || ESPACIO || ESPACIO || cOpcion3.val_i18n;
              registro.val_rama_menu := 3;
              tablaRegistro(i) := registro;
              PIPE ROW(tablaRegistro(i));
              FOR cOpcion4 IN cOpciones4(registro.oid_func) LOOP
                i := i + 1;
                tablaRegistro.EXTEND;
                registro.oid_func := cOpcion4.oid_func;
                registro.ind_nive := cOpcion4.ind_nive;
                registro.func_oid_func := cOpcion4.func_oid_func;
                registro.val_i18n :=  ESPACIO || ESPACIO || ESPACIO || ESPACIO || cOpcion4.val_i18n;
                registro.val_rama_menu := 4;
                tablaRegistro(i) := registro;
                PIPE ROW(tablaRegistro(i));
              END LOOP;

          END LOOP;

      END LOOP;

  END LOOP;

  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SIC_FN_OBTIE_LISTA_MENUS: '||ls_sqlerrm);
END SIC_FN_OBTIE_LISTA_MENUS;


/***************************************************************************
Descripcion       : Devuelve Table de Menu del SICC de acuerdo al ROL
Fecha Creacion    : 23/03/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION SIC_FN_OBTIE_LISTA_MENUS_ROL(pnidRol NUMBER)
RETURN TABLA_LISTA_MENU PIPELINED
IS
  tablaRegistro     TABLA_LISTA_MENU;
  registro          tRegListaMenu;
  lnoid_func        men_funci.oid_func%TYPE;
  lsind_nive        men_funci.ind_nive%TYPE;
  lnfunc_oid_func   men_funci.func_oid_func%TYPE;
  lsval_i18n        gen_i18n_sicc_comun.val_i18n%TYPE;
  lnMember          memberof.idmember%TYPE;
  i                 BINARY_INTEGER := 0;
  ESPACIO           VARCHAR2(10):='          ';

  CURSOR cOpciones IS
  SELECT oid_func, ind_nive, func_oid_func, val_i18n, ind_acce, val_rama_menu
  FROM
  	 TABLE(sic_pkg_mante.SIC_FN_OBTIE_LISTA_MENUS);

BEGIN
  tablaRegistro := TABLA_LISTA_MENU();
  FOR cOpcion IN cOpciones LOOP
      i := i + 1;
      tablaRegistro.EXTEND;
      registro.oid_func := cOpcion.oid_func;
      registro.ind_nive := cOpcion.ind_nive;
      registro.func_oid_func := cOpcion.func_oid_func;
      registro.val_i18n :=  cOpcion.val_i18n;
      registro.val_rama_menu := cOpcion.val_rama_menu;
      BEGIN
        SELECT a.idmember
        INTO lnMember
        FROM memberof a
        WHERE idmember = pnidRol
          AND idrole = (select idprincipal from principals where name=to_char(registro.oid_func));

        registro.ind_acce := '1';
      EXCEPTION
      WHEN no_data_found THEN
           registro.ind_acce := '0';
      END;
      tablaRegistro(i) := registro;

      PIPE ROW(tablaRegistro(i));
  END LOOP;
END SIC_FN_OBTIE_LISTA_MENUS_ROL;
/**************************************************************************
  Descripcion       : Devuelve los datos del usuario
  Fecha Creacion    : 29/03/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
FUNCTION sic_fn_devue_datos_byusu
(
  pnidprincipal NUMBER,
  pnidproperty  NUMBER
) RETURN VARCHAR2 IS
  lsproperty propertyvalues.stringvalue%TYPE;
BEGIN
  SELECT stringvalue
    INTO lsproperty
    FROM propertyvalues pv
   WHERE pv.idproperty = pnidproperty
     AND pv.idprincipal = pnidprincipal;

  RETURN lsproperty;
END;

/**************************************************************************
Descripcion        : Inserta Los datos del Usuario SICC
Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_inser_datos_usuar
(
  pslogin           VARCHAR2,
  psprimerapellido  VARCHAR2,
  pssegundoapellido VARCHAR2,
  psprimernombre    VARCHAR2,
  pssegundonombre   VARCHAR2,
  psemail           VARCHAR2,
  pstelefono        VARCHAR2,
  psclavedefault    VARCHAR2
) IS
  lnoidusuario principals.idprincipal%TYPE;

BEGIN

  SELECT principals_seq.nextval INTO lnoidusuario FROM dual;

  INSERT INTO principals
    (idprincipal,
     NAME,
     TYPE,
     description)
  VALUES
    (lnoidusuario,
     pslogin,
     NULL,
     NULL);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     2,
     1,
     psprimerapellido);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     3,
     1,
     pssegundoapellido);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     5,
     1,
     psprimernombre);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     6,
     1,
     pssegundonombre);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     7,
     1,
     psemail);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     8,
     1,
     pstelefono);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 24, 1, oid_marc FROM seg_marca);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 49, 1, oid_marc FROM seg_marca WHERE rownum = 1);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 25, 1, oid_cana FROM seg_canal);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 50, 1, oid_cana FROM seg_canal WHERE rownum = 1);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 26, 1, oid_acce FROM seg_acces);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 51, 1, oid_acce FROM seg_acces WHERE rownum = 1);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 27, 1, oid_sbac FROM seg_subac);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 52, 1, oid_sbac FROM seg_subac WHERE rownum = 1);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 30, 1, oid_soci FROM seg_socie);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 48, 1, oid_soci FROM seg_socie WHERE rownum = 1);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 38, 1, oid_regi
       FROM zon_regio
      WHERE ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 33, 1, oid_regi
       FROM zon_regio
      WHERE rownum = 1
        AND ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 39, 1, oid_zona
       FROM zon_zona
      WHERE ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 34, 1, oid_zona
       FROM zon_zona
      WHERE rownum = 1
        AND ind_acti = 1
        AND ind_borr = 0);

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 40, 1, oid_secc
       FROM zon_secci
      WHERE ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 35, 1, oid_secc
       FROM zon_secci
      WHERE rownum = 1
        AND ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 41, 1, oid_terr FROM zon_terri WHERE ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 53, 1, oid_terr
       FROM zon_terri
      WHERE rownum = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 42, 1, oid_subg_vent
       FROM zon_sub_geren_venta
      WHERE ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
    (SELECT lnoidusuario, 43, 1, oid_subg_vent
       FROM zon_sub_geren_venta
      WHERE rownum = 1
        AND ind_acti = 1
        AND ind_borr = 0);
  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     1,
     1,
     'S');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     4,
     1,
     '-');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     9,
     1,
     '-');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     10,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     11,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     12,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     13,
     1,
     'TERCERO');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     14,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     18,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     19,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     20,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     21,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     22,
     1,
     '');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     23,
     1,
     '1');

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     28,
     1,
     '1');

  INSERT INTO users
  VALUES
    (lnoidusuario,
     psclavedefault,
     SYSDATE + 2000,
     0,
     0);

END sic_pr_inser_datos_usuar;

/***********************************************************************************
Descripcion        : Actualiza Los datos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_actua_datos_usuar
(
  psidusuario       VARCHAR2,
  psprimerapellido  VARCHAR2,
  pssegundoapellido VARCHAR2,
  psprimernombre    VARCHAR2,
  pssegundonombre   VARCHAR2,
  psemail           VARCHAR2,
  pstelefono        VARCHAR2
) IS
  lnoidusuario principals.idprincipal%TYPE;

BEGIN

  lnoidusuario := psidusuario;
  UPDATE propertyvalues
     SET stringvalue = psprimerapellido
   WHERE idproperty = 2
     AND idprincipal = lnoidusuario;

  UPDATE propertyvalues
     SET stringvalue = pssegundoapellido
   WHERE idproperty = 3
     AND idprincipal = lnoidusuario;

  UPDATE propertyvalues
     SET stringvalue = psprimernombre
   WHERE idproperty = 5
     AND idprincipal = lnoidusuario;

  UPDATE propertyvalues
     SET stringvalue = pssegundonombre
   WHERE idproperty = 6
     AND idprincipal = lnoidusuario;

  UPDATE propertyvalues
     SET stringvalue = psemail
   WHERE idproperty = 7
     AND idprincipal = lnoidusuario;

  UPDATE propertyvalues
     SET stringvalue = pstelefono
   WHERE idproperty = 8
     AND idprincipal = lnoidusuario;

END sic_pr_actua_datos_usuar;

/***********************************************************************************
Descripcion        : Actualiza Los datos Masivos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_actua_datos_masiv(psidusuario VARCHAR2) IS
  lnoidusuario principals.idprincipal%TYPE;

BEGIN
lnoidusuario := psidusuario; DELETE FROM propertyvalues WHERE idprincipal = lnoidusuario AND idproperty IN (
4,  -- Apellido de Casada
9,  -- Departamento
10, -- IdentificadorPc
11, -- PeriodoValidezFechaDesde
12, -- PeriodoValidezFechaHasta
13, -- TipoUsuario
14, -- Internet
18, -- RastreoAcceso
19, -- UsuarioSustitucion
20, -- FechaSustitucionDesde
21, -- FechaSustitucionHasta
22, -- ObservacionesSustitucion
23, -- UsuarioHabilitado
24, -- Marca
                        25, -- Canal
                        26, -- Acceso
                        27, -- Subacceso
28, -- Idioma
                        30, -- Sociedad
31, -- Pais Defecto
33, -- RegionPorDefecto
34, -- ZonaPorDefecto
35, -- SeccionDefecto
36, -- Pais
                        38, -- Región
                        39, -- Zona
                        40, -- Sección
                        41, -- Territorio
                        42, -- Subgerencia
                        43, -- SubgerenciaDefecto
                        33, -- RegiónDefecto
                        34, -- ZonaDefecto
                        35, -- SecciónDefecto
38, -- Region
39, -- Zona
40, -- Seccion
41, -- Territorio
42, -- SubgerenciaVentas
43, -- SubgerenciaVentasDefecto
                        48, -- SociedadDefecto
                        49, -- MarcaDefecto
                        50, -- CanalDefecto
                        51, -- AccesoDefecto
                        52, -- SubaccesoDefecto
                        53); -- TerritorioDefecto

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 4, 1, '-');

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 9, 1, '-');

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 10, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 11, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 12, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 13, 1, 'TERCERO');

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 14, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 18, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 19, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 20, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 21, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 22, 1, NULL);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 23, 1, 'Y');

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 24, 1, oid_marc FROM seg_marca);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 25, 1, oid_cana FROM seg_canal);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 26, 1, oid_acce FROM seg_acces);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 27, 1, oid_sbac FROM seg_subac);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue) VALUES(lnoidusuario, 28, 1, '1');

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 30, 1, oid_soci FROM seg_socie);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 31, 1, oid_pais FROM seg_pais where cod_pais in ((select substr(name,1,2) from principals where idprincipal=lnoidusuario),(select substr(name,1,3) from principals where idprincipal=lnoidusuario)));

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 33, 1, oid_regi FROM zon_regio WHERE rownum = 1 AND ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 34, 1, oid_zona FROM zon_zona WHERE rownum = 1 AND ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 35, 1, oid_secc FROM zon_secci WHERE rownum = 1 AND ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 36, 1, oid_pais FROM seg_pais where cod_pais in ((select substr(name,1,2) from principals where idprincipal=lnoidusuario),(select substr(name,1,3) from principals where idprincipal=lnoidusuario)));

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 38, 1, oid_regi FROM zon_regio WHERE ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 39, 1, oid_zona FROM zon_zona WHERE ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 40, 1, oid_secc FROM zon_secci WHERE ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 41, 1, oid_terr FROM zon_terri WHERE ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 42, 1, oid_subg_vent FROM zon_sub_geren_venta WHERE ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 43, 1, oid_subg_vent FROM zon_sub_geren_venta WHERE rownum = 1 AND ind_acti = 1 AND ind_borr = 0);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 48, 1, oid_soci FROM seg_socie WHERE rownum = 1);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 49, 1, oid_marc FROM seg_marca WHERE rownum = 1);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 50, 1, oid_cana FROM seg_canal WHERE rownum = 1);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 51, 1, oid_acce FROM seg_acces WHERE rownum = 1);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 52, 1, oid_sbac FROM seg_subac WHERE rownum = 1);

INSERT INTO propertyvalues(idprincipal, idproperty, TYPE, stringvalue)(SELECT lnoidusuario, 53, 1, oid_terr FROM zon_terri WHERE rownum = 1 AND ind_borr = 0);

END sic_pr_actua_datos_masiv;

/***********************************************************************************
Descripcion        : Actualiza Los datos del Usuario SICC

Fecha Creacion     : 31/04/2010
Autor              : Jose Cairampoma
***************************************************************************/
PROCEDURE sic_pr_restb_usuar
(
  psidusuario    VARCHAR2,
  psclavedefault VARCHAR2
) IS
  lnoidusuario principals.idprincipal%TYPE;

BEGIN

  lnoidusuario := psidusuario;
  UPDATE propertyvalues
     SET stringvalue = 'Y'
   WHERE idprincipal = lnoidusuario
     AND idproperty = 23;

  UPDATE users a
     SET a.blocked    = 0,
         a.retries    = 0,
         a.setdate    = a.setdate + 2000,
         a.credential = psclavedefault
   WHERE a.iduser = lnoidusuario;

  UPDATE users a
     SET a.credential =
         (SELECT credential FROM users WHERE iduser = lnoidusuario)
   WHERE a.iduser = lnoidusuario;

  DELETE FROM propertyvalues
   WHERE idprincipal = lnoidusuario
     AND idproperty = 1;

  DELETE FROM oldcredentials WHERE iduser = lnoidusuario;

  INSERT INTO propertyvalues
    (idprincipal,
     idproperty,
     TYPE,
     stringvalue)
  VALUES
    (lnoidusuario,
     1,
     1,
     'S');

END sic_pr_restb_usuar;

/***********************************************************************************
Descripcion        : Bloquea/Desbloquea el Usuario SICC

Fecha Creacion     : 18/06/2010
Autor              : Carlos Diaz Valverde
***************************************************************************/
PROCEDURE SEG_PR_BLOQU_DESBL_USUAR
(
  PSIDUSUARIO    VARCHAR2,
  PNFLAGBLOQUEO  NUMBER
)
IS

BEGIN

    -- BLOQUEAR
    IF PNFLAGBLOQUEO = 1 THEN
        UPDATE    USERS A
        SET       A.BLOCKED = 1
        WHERE     A.IDUSER = PSIDUSUARIO;

    -- DESBLOQUEAR
    ELSE
        -- 1 PROPIEDADES
        UPDATE    PROPERTYVALUES
        SET       STRINGVALUE='Y'
        WHERE     IDPRINCIPAL=PSIDUSUARIO
        AND       IDPROPERTY=23;

        -- 2 USUARIO
        UPDATE    USERS U
        SET       U.BLOCKED = 0,
                  U.RETRIES = 0,
                  U.SETDATE = U.SETDATE + 2000
        WHERE     U.IDUSER = PSIDUSUARIO;

    END IF;

END SEG_PR_BLOQU_DESBL_USUAR;

END SIC_PKG_MANTE;
/

