CREATE OR REPLACE PACKAGE APE_PKG_PROCE IS

  /* Declaracion de variables */
  ln_sqlcode           NUMBER(10);
  ls_sqlerrm           VARCHAR2(1500);
  ln_oidProBalanceo    NUMBER:=3;
  ln_oidProEstimado    NUMBER:= 2;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APP_CONFI_CENTR_DISTR
     Fecha Creacion    : 09/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_CENTR_DISTR(
   p_oid_pais      IN  VARCHAR2,
   p_oid_centro    IN  VARCHAR2,
   p_val_error     OUT VARCHAR2,
   p_nom_tabla     OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de linea de armado.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_LINEA_ARMAD
     Fecha Creacion    : 14/06/2010
     Parametros Entrada :
            Oid Centro Distribucion
            Oid Linea Armado
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_LINEA_ARMAD(
   p_oid_centro    IN  VARCHAR2,
   p_oid_linea     IN  VARCHAR2,
   p_val_error     OUT VARCHAR2,
   p_nom_tabla     OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_LINEA_ARMAD
     Fecha Creacion    : 14/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_LINEA_ARMAD(
   p_oid_linea    IN  NUMBER,
   p_val_error    IN OUT VARCHAR2,
   p_nom_tabla    IN OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Registra en la tabla APE_LINEA_UADMI
     Fecha Creacion    : 16/06/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
            Cod Pais
            Oid Linea Armado
            Oid AFP
            Cod Region
            Cod Zona
            Cod Seccion
     Parametros Salida : Ninguno
***************************************************************************/
 PROCEDURE APE_PR_REGIS_LINEA_UADMI(
   p_cod_Pais       IN VARCHAR2,
   p_oidLineArma    IN VARCHAR2,
   p_oid_AAFP       IN VARCHAR2,
   p_cod_Region     IN VARCHAR2,
   p_cod_Zona       IN VARCHAR2,
   p_cod_Seccion    IN VARCHAR2
 );

/**************************************************************************
Descripcion        : Valida la existencia de un registro previo en la tabla APE_LINEA_UADMI
Fecha Creacion     : 16/06/2010
Autor              : Nicolas Lopez
***************************************************************************/
 FUNCTION APE_FN_VALIDA_UADMI_LINEA(
   p_cod_Pais       IN  VARCHAR2,
   p_oidLineArma    IN  VARCHAR2,
   p_oid_AAFP       IN  VARCHAR2,
   p_cod_Region     IN  VARCHAR2,
   p_cod_Zona       IN  VARCHAR2,
   p_cod_Seccion    IN  VARCHAR2
 )RETURN NUMBER;

/**************************************************************************
Descripcion        : Obtiene el numero de chanel Aframe
Fecha Creacion     : 23/09/2010
Autor              : Christian Gonzales komiya
***************************************************************************/
 FUNCTION APE_FN_DEVUE_NUMER_CHANE(
   p_num_unid_estim    NUMBER,
   p_num_hora_inve     NUMBER,
   p_num_lane_capa     NUMBER
 )RETURN NUMBER;

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Sub linea de armado.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_SUBLI_ARMAD
     Fecha Creacion    : 23/06/2010
     Parametros Entrada :
            Oid Sub Linea Armado
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_ELIMI_SUBLI_ARMAD(
   p_oid_sub_linea    IN  VARCHAR2,
   p_val_error        OUT VARCHAR2,
   p_nom_tabla        OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_SUBLI_ARMAD
     Fecha Creacion    : 23/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_SUBLI_ARMAD(
   p_oid_sub_linea    IN  NUMBER,
   p_val_error        IN OUT VARCHAR2,
   p_nom_tabla        IN OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Tipo de Anaqueles.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_TIPO_ANAQU
     Fecha Creacion    : 28/06/2010
     Parametros Entrada :
            Oid Tipo Anaquel
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_ELIMI_TIPO_ANAQU(
   p_oid_Tipo_Anaquel    IN  VARCHAR2,
   p_val_error           OUT VARCHAR2,
   p_nom_tabla           OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_TIPO_ANAQU
     Fecha Creacion    : 28/06/2010
     Parametros Entrada :
            Oid Tipo Anaquel
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_ANAQU(
   p_oid_Tipo_Anaquel    IN NUMBER,
   p_val_error           IN OUT VARCHAR2,
   p_nom_tabla           IN OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_MAPA_ZONA_CABEC
     Fecha Creacion    : 30/06/2010
     Parametros Entrada :
            Oid Mapa Zona Cabecera
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_MAPA_ZONA(
   p_oid_mapazona    IN NUMBER,
   p_val_error       IN OUT VARCHAR2,
   p_nom_tabla       IN OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Mapa Zona Cabecera y Detalle.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_MAPA_ZONA_CABEC
     Fecha Creacion    : 30/06/2010
     Parametros Entrada :
            Oid Mapa Zona
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_ELIMI_MAPA_ZONA(
   p_oid_mapazona    IN  VARCHAR2,
   p_val_error       OUT VARCHAR2,
   p_nom_tabla       OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Genera el Mapa del Centro de Distribucion
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_MAPA_CENTR_DISTR(
   p_cod_centr_distr    IN VARCHAR2,
   p_cod_mapa				    IN VARCHAR2,
   p_des_mapa			      IN VARCHAR2,
   p_oid_idiomaISO			IN VARCHAR2
 );

/**************************************************************************
     Descripcion       : Inserta en la tabla cabecera de Mapa de Centro de
     										 distribucion
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_INSER_MAPA_CABEC(
   p_oid_mapa           IN NUMBER,
	 p_cod_mapa						IN VARCHAR2,
	 p_des_mapa						IN VARCHAR2,
	 p_oid_centr_distr    IN NUMBER,
	 p_oid_idiomaISO 			IN NUMBER
 );

/**************************************************************************
     Descripcion       : Genera los detalles de Mapa de Centro de
     										 distribucion a insertar
     Fecha Creacion    : 30/06/2010

***************************************************************************/
 PROCEDURE APE_PR_GENER_DETAL_MAPA (
   p_oid_mapa						IN NUMBER,
   p_oid_centr_distr    IN NUMBER
 );

/**************************************************************************
     Descripcion : Devuelve la letra segun la posicion
     Fecha Creacion : 22/10/2009

***************************************************************************/
 FUNCTION APE_FN_DEVUE_LETRA_NIVEL (
   pn_posicion    NUMBER
 )RETURN VARCHAR2;

/**************************************************************************
     Descripcion       : Inserta en la tabla cabecera de Mapa de Centro de
     										 distribucion
     Fecha Creacion    : 30/06/2010

***************************************************************************/
 PROCEDURE APE_PR_INSER_MAPA_DETAL(
   p_oid_picado      IN NUMBER,
   p_num_anaquel     IN VARCHAR2,
   p_oid_mapa        IN NUMBER,
   p_oid_sublinea    IN NUMBER
 );

/**************************************************************************
     Descripcion       : Genera un nuevo Mapa de Centro Distribucion a travez de una copia de un MAPA CD elegido
     Autor             : Nicolas Lopez
     Fecha Creacion    : 07/06/2010
     Parametros Entrada :
            p_oid_pais
            p_oid_centr_distr
            p_oid_mapa_cent_dist
            p_desc_mapa_cent
            p_oid_idiomaISO
***************************************************************************/
 PROCEDURE APE_PR_GENER_MAPA_CD_DETAL(
   p_oid_pais              VARCHAR2,
   p_oid_centr_distr       VARCHAR2,
   p_oid_mapa_cent_dist    VARCHAR2,
   p_desc_mapa_cent        VARCHAR2,
   p_oid_idiomaISO         gen_i18n_sicc_pais.idio_oid_idio %TYPE
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APP_TIPO_CAJA_PRODU
     Fecha Creacion    : 09/07/2010
     Parametros Entrada :
            Oid Pais

     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija

***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_CAJA(
   p_oid_pais     IN  VARCHAR2,
   p_oid_caja     IN  VARCHAR2,
   p_val_error    OUT VARCHAR2,
   p_nom_tabla    OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Generar un nuevo registro Orden de Anaqueles, a traves de una copia de un registro
                         Orden Anaquel elegido.
     Autor             : Nicolas Lopez
     Fecha Creacion    : 12/07/2010
     Parametros Entrada :
            p_oid_mapa_zona_orig
            p_oid_ordenanaq_orig
            p_desc_orden_anaq
            p_oid_mapa_zona_dest
            p_oid_idiomaISO
***************************************************************************/
 PROCEDURE APE_PR_GENER_ORD_ANAQUEL_DETAL(
   p_oid_mapa_zona_orig    VARCHAR2,
   p_oid_ordenanaq_orig    VARCHAR2,
   p_desc_orden_anaq       VARCHAR2,
   p_oid_mapa_zona_dest    VARCHAR2,
   p_oid_idiomaISO         gen_i18n_sicc_pais.idio_oid_idio %TYPE
 );

/**************************************************************************
     Descripcion       : Inserta en la tabla detalle de Asignacion de Productos
                         Anaquel
     Fecha Creacion    : 23/07/2010
***************************************************************************/
 PROCEDURE APE_PR_INSER_ASIGN_DETAL(
   p_oid_mapa_cent    IN VARCHAR2,
   p_oid_mapa_zona    IN VARCHAR2,
   p_oid_line_arma    IN VARCHAR2,
   p_oid_asig_cabe    IN VARCHAR2,
   p_oid_proc_esti		IN VARCHAR2
 );

/**************************************************************************
     Descripcion       : Registra los Tipos de Anaqueles en la tabla APE_MAPA_CENTR_DISTR_DETAL
     Fecha Creacion    : 27/07/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
       p_oid_sistpicad   Contiene el OidSistPicad para identificar el tipo AFRAME
     Parametros Salida : Ninguno
***************************************************************************/
 PROCEDURE APE_PR_REGIS_ANAQ_MAPACDDET(
   p_oid_sistpicad    IN VARCHAR2
 );

/**************************************************************************
     Descripcion       : Registra los Anaqueles Destinos en los Anaqueles Expandidos en el
                         Nuevo Mapa Centro de Distribucion , se utiliza la tabla APE_MAPA_CENTR_DISTR_DETAL
     Fecha Creacion    : 03/08/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
       p_oid_mapa_cent_dist   Oid Mapa CD que corresponde al Nuevo Mapa CD
     Parametros Salida : Ninguno
***************************************************************************/
 PROCEDURE APE_PR_REGIS_MAPA_CD(
   p_oid_mapa_cent_dist    VARCHAR2
 );

/**************************************************************************
     Descripcion       : Realiza la validaciones antes de asignar un producto
                         a un anaquel
     Fecha Creacion    : 05/08/2010
***************************************************************************/
 PROCEDURE APE_PR_VALID_PRODU_ANAQU(
   p_oid_pais                IN  VARCHAR2,
   p_oid_sap                 IN  VARCHAR2,
   p_oid_perio               IN  VARCHAR2,
   p_num_anaqu               IN  VARCHAR2,
   p_oid_subli               IN  VARCHAR2,
   p_oid_mapa_centr_cabec    IN  VARCHAR2,
   p_oid_mapa_centr_detal    IN  VARCHAR2,
   p_oid_asig_produ_cabec    IN  VARCHAR2,
   p_ind_intercambio         IN  VARCHAR2,
   p_val_error               OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Realiza el intercambio de los productos de un anaquel a
                         otro
     Fecha Creacion    : 05/08/2010
***************************************************************************/
 PROCEDURE APE_PR_INTER_PRODU_ANAQU(
   p_oid_pais                IN  VARCHAR2,
   p_oid_sap                 IN  VARCHAR2,
   p_oid_perio               IN  VARCHAR2,
   p_num_anaqu_orige         IN  VARCHAR2,
   p_num_anaqu_desti         IN  VARCHAR2,
   p_oid_subli               IN  VARCHAR2,
   p_oid_mapa_centr_detal    IN  VARCHAR2,
   p_oid_mapa_centr_cabec    IN  VARCHAR2,
   p_oid_asig_produ_detal    IN  VARCHAR2,
   p_oid_asig_produ_cabec    IN  VARCHAR2,
   p_ind_intercambio         IN  VARCHAR2,
   p_val_error               OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Realiza la generacion de los estimado de los porducto
                         para la campa?a ingresada
     Fecha Creacion    : 09/08/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_ESTIM_PRODU(
   p_oid_perio          IN  VARCHAR2,
   p_oid_linea          IN  VARCHAR2,
   p_ind_borra_manua    IN  VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_TIPO_CAJA_EMBAL
     Fecha Creacion    : 16/08/2010
     Parametros Entrada :
            Oid Tipo Caja Embalaje
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_CAEMB(
   p_oid_tipocaja    IN  NUMBER,
   p_val_error       IN OUT VARCHAR2,
   p_nom_tabla       IN OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Tipos de Caja Embalaje.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_TIPO_CAJA_EMBAL
     Fecha Creacion    : 09/06/2010
     Parametros Entrada :
            Oid Tipo Caja Embalaje
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_TIPO_CAEMB(
   p_oid_tipocaja    IN  VARCHAR2,
   p_val_error       OUT VARCHAR2,
   p_nom_tabla       OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Genera el numero de OLA en base al consolodados de
                         pedidos.
     Fecha Creacion    : 26/08/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_OLAS(
   oidpais    IN VARCHAR2
 );

/**************************************************************************
     Descripcion       : Inserta el numero de Ola genera en la tabla de
                         Olas por Dia. Ademas si el numer minimo de perido
                         por CD es 0 se actualiza el campo Numero de Ola de
                         la Lista de Picado
     Fecha Creacion    : 26/08/2010
***************************************************************************/
 PROCEDURE APE_PR_INSER_OLAS_DIA(
   p_num_lote_factu        IN NUMBER,
   p_fec_factu             IN DATE,
   p_num_ola               IN NUMBER,
   p_val_agrup_afp         IN NUMBER,
   p_num_oid_linea         IN NUMBER,
   p_num_mini_pedid_ola    IN NUMBER,
   p_oid_lista_conso       IN NUMBER,
   p_contPedidoOLA         IN NUMBER
 );

/**************************************************************************
     Descripcion       : Devuelve la cantidad de registro por OLA
     Fecha Creacion    : 26/08/2010
***************************************************************************/
 FUNCTION APE_FN_DEVUE_NUMER_PEDID(
   p_val_agrup_afp    IN NUMBER,
   p_fec_factu        IN DATE,
   p_num_oid_linea    IN NUMBER,
   p_oid_region       IN NUMBER,
   p_oid_zona         IN NUMBER,
   p_oid_seccion      IN NUMBER
 )RETURN NUMBER;

/**************************************************************************
     Descripcion       : Generar una copia de un mapa de asignacion de productos a anaqueles
                         asignando una nueva version.
     Autor             : Nicolas Lopez
     Fecha Creacion    : 16/09/2010
     Parametros Entrada :
            p_oid_asigprodAnaq
            p_val_version_destino
***************************************************************************/
 PROCEDURE APE_PR_GENER_ASIGN_PRANQ_NVERS(
   p_oid_asigprodAnaq       VARCHAR2,
   p_val_version_destino    VARCHAR2
 );

/**************************************************************************
     Descripcion       : Preasignacion x Validacion de Estimados
     Autor             : Christian Gonzales
     Fecha Creacion    : 16/09/2010
     Parametros Entrada :
            p_oid_mapa_cent
            p_oid_sublinea
            p_oid_peri_origen
            p_oid_peri_destino
            p_val_version_destino
            p_cod_fuente
***************************************************************************/
 PROCEDURE APE_PR_PREAS_VARIA_ESTIM(
   p_oid_mapa_cent          NUMBER,
   p_oid_sublinea           NUMBER,
   cod_peri_origen          NUMBER,
   cod_peri_destino         NUMBER,
   p_val_version_origen     VARCHAR2,
   p_val_version_destino    VARCHAR2,
   p_cod_fuente             VARCHAR2
 );

/**************************************************************************
     Descripcion       : Genera el Balanceo
     Fecha Creacion    : 21/09/2010

***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_LINEA(
   p_oidMapaCentroDistribucion    VARCHAR2,
   p_oidPeriodo                   VARCHAR2,
   p_codVersion                   VARCHAR2,
   p_oidLinea                     VARCHAR2,
   p_oidSubLinea                  VARCHAR2,
   p_codOrdeAnaquel               VARCHAR2,
   p_cod_orig_dato                VARCHAR2,
   p_codOrdeProducto              VARCHAR2,
   p_numAnaqueles                 OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Genera el Balanceo x Matching
     Fecha Creacion    : 21/09/2010

***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_MATCH(
   p_oidPeriodo         NUMBER,
   p_oidLinea           NUMBER,
   p_oidSubLinea        NUMBER,
   p_codOrdeProducto    VARCHAR2,
   p_oidMapaZona        NUMBER,
   p_oidMapaOrden       NUMBER,
   p_oidVersion         NUMBER
 );

/**************************************************************************
     Descripcion       : Genera el Balanceo x Aframe
     Fecha Creacion    : 21/09/2010

***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_AFRAM(
   p_oidMapaCentroDistribucion    NUMBER,
   p_oidPeriodo                   NUMBER,
   p_oidLinea                     NUMBER,
   p_oidSubLinea                  NUMBER,
   p_codOrdeProducto              VARCHAR2,
   p_oidMapaZona                  NUMBER,
   p_oidMapaOrden                 NUMBER,
   p_oidVersion                   NUMBER
 );

/**************************************************************************
     Descripcion       : Genera el Balanceo x Tipo de Anaquel
     Fecha Creacion    : 21/09/2010
     Autor             : Jose Luis Rodriguez
     Modificado por    : Nicolas Lopez
     Fecha Modificacion: 11/11/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_ANAQU(
   p_oidMapaCentroDistribucion    NUMBER,
   p_oidPeriodo                   NUMBER,
   p_oidLinea                     NUMBER,
   p_oidSubLinea                  NUMBER,
   p_codOrdeProducto              VARCHAR2,
   p_oidMapaZona                  NUMBER,
   p_oidMapaOrden                 NUMBER,
   p_oidVersion                   NUMBER,
   p_numAnaqueles             OUT NUMBER
 );

/**************************************************************************
     Descripcion       : Preasignacion Aframe
     Autor             : Christian Gonzales
     Fecha Creacion    : 23/09/2010
     Parametros Entrada :
            p_oid_mapa_cent
            p_oid_sublinea
            p_oid_peri_origen
            p_oid_peri_destino
            p_val_version_destino
            p_cod_fuente
            p_linea_armado
***************************************************************************/
 PROCEDURE APE_PR_PREAS_AFRAM(
   p_oid_mapa_cent          NUMBER,
   p_linea_armado           NUMBER,
   p_oid_sublinea           NUMBER,
   cod_peri_origen          NUMBER,
   cod_peri_destino         NUMBER,
   p_val_version_origen     VARCHAR2,
   p_val_version_destino    VARCHAR2,
   p_cod_fuente             VARCHAR2
 );

/**************************************************************************
     Descripcion       : Actualizar Seguimiento de pedidos
     Autor             : Christian Gonzales
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
            p_oid_solicitud
            p_oid_consolidado
            p_cod_hito
            p_nro_doc
            p_fecha
            p_ind_completo
***************************************************************************/
 PROCEDURE PED_ACTUA_SEGUI_PEDID(
   p_oid_solicitud      IN NUMBER,
   p_oid_consolidado    IN NUMBER,
   p_cod_hito           IN VARCHAR2,
   p_nro_doc            IN VARCHAR2,
   p_fecha              IN DATE,
   p_ind_completo       IN NUMBER
 );

/**************************************************************************
     Descripcion       : Procesa Caja Terminada de  Embalaje en WCS
     Autor             : Christian Gonzales
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
          p_oid_pais
          p_oid_marca
          p_oid_centro
          p_oid_linea
          p_num_consolidad
          p_num_caja
          p_indicador_armado
***************************************************************************/
 PROCEDURE APE_PR_PROCE_CAJA_TERMI_EMBAL(
   p_cod_pais            IN VARCHAR2,
   p_oid_marca           IN VARCHAR2,
   p_oid_centro          IN VARCHAR2,
   p_oid_linea           IN VARCHAR2,
   p_num_consolidad      IN VARCHAR2,
   p_num_caja            IN VARCHAR2,
   p_indicador_armado    IN VARCHAR2
 );

/**************************************************************************
     Descripcion       : Generar reporte de Mapa de Anaqueles con Balanceo Diario
     Autor             : Nicolas Lopez
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
            psCodigoPais
            psCodigoMarca
            psCodigoCanal
            psCodigoPeriodo
            psCodigoSecuencia
            psCodigoMapaCD
            psCodigoLineaArmado
            psFechaFacturacion
***************************************************************************/
 PROCEDURE APE_PR_GENER_REPOR_MAPA_ANQBD(
   psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSecuencia      VARCHAR2,
   psCodigoMapaCD         VARCHAR2,
   psCodigoLineaArmado    VARCHAR2,
   psFechaFacturacion     VARCHAR2
 );

/**************************************************************************
     Descripcion       : Generar reporte de Balanceo Linea y Productos
     Autor             : Nicolas Lopez
     Fecha Creacion    : 26/10/2010
     Parametros Entrada :
            psCodigoPais
            psCodigoMarca
            psCodigoCanal
            psCodigoPeriodo
            psCodigoSecuencia
            psCodigoCentro
            psCodigoLineaArmado
***************************************************************************/
 PROCEDURE APE_PR_GENER_REPOR_BALAN_LINEA(
   psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSecuencia      VARCHAR2,
   psCodigoCentro         VARCHAR2,
   psCodigoLineaArmado    VARCHAR2
 );

/**************************************************************************
     Descripcion       : Realiza la validaciones antes de realizar la carga
                         de los productos de APE
     Fecha Creacion    : 19/11/2010
***************************************************************************/
 PROCEDURE APE_PR_VALID_VISTA_PRODU(
   p_oid_pais         IN VARCHAR2,
   p_cod_sap          IN VARCHAR2,
   p_cod_tipo_disp    IN VARCHAR2,
   p_cod_tipo_anaq    IN VARCHAR2,
   p_cod_caja_maes    IN VARCHAR2,
   p_val_error        OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Valida la sublinea AFRAME y actualiza los tipos de anaqueles
     Autor             : Christian Gonzales Komiya
     Fecha Creacion    : 26/10/2010
     Parametros Entrada :
            p_tipo_subli
            p_num_anaq
            p_cod_tipo_anaq
            p_val_side
            p_nro_aframe
            p_chan_address_aframe
            p_height_aframe
            p_width_aframe
            p_mach_address_aframe
            p_level_aframe
            p_oid_subl
            p_oid_mapacd
            p_val_error
***************************************************************************/
 PROCEDURE APE_PR_ACTUA_TIPOS_ANAQU_MAPCD(
   p_tipo_subli             IN VARCHAR2,
   p_num_anaq               IN VARCHAR2,
   p_cod_tipo_anaq          IN VARCHAR2,
   p_val_side               IN VARCHAR2,
   p_nro_aframe             IN NUMBER,
   p_chan_address_aframe    IN NUMBER,
   p_height_aframe          IN NUMBER,
   p_width_aframe           IN NUMBER,
   p_mach_address_aframe    IN NUMBER,
   p_level_aframe           IN NUMBER,
   p_oid_subl               IN VARCHAR2,
   p_oid_mapacd             IN VARCHAR2,
   p_val_error              OUT VARCHAR2
 );

/**************************************************************************
     Descripcion       : Registra en la tabla de Anaquel Producto el tipo de
                         Anaquel que llega en la carga en exel
     Fecha Creacion    : 01/12/2010
***************************************************************************/
 PROCEDURE APE_PR_REGIS_TIPO_ANAQU(
   p_oid_sap          IN VARCHAR2,
   p_oid_tipo_anaq    IN VARCHAR2
 );

/***************************************************************************
Descripcion       : Valida si existe repeticion de Valores Side+FrameNumber+ChannelAddres en
                    el listado de Anaqueles
Fecha Creacion    : 03/12/2010
Autor             : Nicolas Lopez
***************************************************************************/
 PROCEDURE APE_PR_APE_GENER_VALID_ANAQU(
   psCodigoPais    VARCHAR2,
   psNumError      OUT VARCHAR2
 );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Mgpedxdia
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 12/12/2013
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_MGPED_CSV1(
    psCodigoPeriodo                     VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera data para el reporte de Distribucion Facturacion Real
Fecha Creacion    : 13/12/2013
Autor             : Aurelio Oviedo
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoPeriodo : Codigo Periodo
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE APE_PR_GENER_DATOS_FACTU_REAL(
    psCodigoPais     VARCHAR2,
    psCodigoPeriodo  VARCHAR2,
    psCodigoUsuario  VARCHAR2,
    psFechaInicio    VARCHAR2,
    psFechaFin       VARCHAR2,
    psCentroAcopio   VARCHAR2,
    psCompaniaTrans  VARCHAR2);

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Distribucion Facturacion Real
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 15/01/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_DISTR_FACTU_REAL(
    psCodigoPeriodo                     VARCHAR2,
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera Hoja de Picado APE para Proceso de PRINT.
Fecha Creacion    : 27/01/2016
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE APE_PR_GENER_PICAD_PRINT(
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  pscodigoproceso VARCHAR2,
  pscodigoPaquete  VARCHAR2,
  psusuario VARCHAR2
  );
  
/***************************************************************************
Descripcion       : Genera data para el reporte de Inventario de Campo - RESUMEN
Fecha Creacion    : 11/02/2016
Autor             : Gonzalo Huertas
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE APE_PR_GENER_DATOS_INVEN_CARES(
    psCodigoPais     VARCHAR2,
    psCodigoUsuario  VARCHAR2,
    psFechaInicio    VARCHAR2,
    psFechaFin       VARCHAR2,
    psCentroAcopio   VARCHAR2,
    psCompaniaTrans  VARCHAR2);  
    
/***************************************************************************
Descripcion       : Genera data para el reporte de Inventario de Campo - DETALLE
Fecha Creacion    : 11/02/2016
Autor             : Gonzalo Huertas
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE APE_PR_GENER_DATOS_INVEN_CADET(
    psCodigoPais     VARCHAR2,
    psCodigoUsuario  VARCHAR2,
    psFechaInicio    VARCHAR2,
    psFechaFin       VARCHAR2,
    psCentroAcopio   VARCHAR2,
    psCompaniaTrans  VARCHAR2);      
    
/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Inventario Campo 
                    Resumen
Fecha Creacion    : 12/02/2016
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_INVEN_CAMPO_RESU(
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;    
    
/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Inventario Campo 
                    Detalle
Fecha Creacion    : 12/02/2016
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_INVEN_CAMPO_DETA(
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;  

END APE_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY APE_PKG_PROCE IS

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APP_CONFI_CENTR_DISTR
     Fecha Creacion    : 09/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_CENTR_DISTR (
   p_oid_pais      IN  VARCHAR2,
   p_oid_centro    IN  VARCHAR2,
   p_val_error     OUT VARCHAR2,
   p_nom_tabla     OUT VARCHAR2
 )
 IS
   vn_oid_pais      NUMBER;
   vn_oid_centro    NUMBER;
   vn_contador      NUMBER;

 BEGIN

   vn_oid_pais := to_number(p_oid_pais);
   vn_oid_centro := to_number(p_oid_centro);
   vn_contador := 0;
   p_val_error := '0';
   p_nom_tabla := '';

   -- Validando en la tabla ape_confi_liafp_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_confi_liafp_cabec a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_CONFI_LIAFP_CABEC';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_etiqu
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_etiqu a
      WHERE a.pais_oid_pais = vn_oid_pais
        AND a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_ETIQU';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_linea_armad
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_linea_armad a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LINEA_ARMAD';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_lista_picad_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_lista_picad_cabec a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LISTA_PICAD_CABEC';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_mapa_centr_distr_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_mapa_centr_distr_cabec a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_MAPA_CENTR_DISTR_CABEC';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_param_balan_area_chequ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_param_balan_area_chequ a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_PARAM_BALAN_AREA_CHEQU';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_subli_armad
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_subli_armad a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_SUBLI_ARMAD';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_turno_chequ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_turno_chequ a
      WHERE a.ccdi_oid_conf_cent_dist = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_TURNO_CHEQU';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla bel_almac
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM bel_almac a
      WHERE a.pais_oid_pais = vn_oid_pais
        AND a.ccdi_oid_confi_centr_distr = vn_oid_centro;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'BEL_ALMAC';
     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_CENTR_DISTR: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_CENTR_DISTR;

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de linea de armado.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_LINEA_ARMAD
     Fecha Creacion    : 09/06/2010
     Parametros Entrada :
            Oid Centro Distribucion
            Oid Linea Armado
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_LINEA_ARMAD (
   p_oid_centro    IN  VARCHAR2,
   p_oid_linea     IN  VARCHAR2,
   p_val_error     OUT VARCHAR2,
   p_nom_tabla     OUT VARCHAR2
 )
 IS
   vn_oid_centro  NUMBER;
   vn_oid_linea   NUMBER;

 BEGIN

   vn_oid_centro := to_number(p_oid_centro);
   vn_oid_linea  := to_number(p_oid_linea);
   p_val_error := '0';
   p_nom_tabla := '';

   -- Primero se valida que no existan registros en las tablas hijas
   APE_PR_VALID_REGIS_LINEA_ARMAD(vn_oid_linea, p_val_error, p_nom_tabla);

   -- Si no existen registros en las tablas hijas se procede a borrar
   IF (p_val_error = '0') THEN
     -- Se borra de la tabla Usuarios Alarma Linea
     DELETE FROM ape_usuar_alarm_linea a WHERE a.liar_oid_line_arma = vn_oid_linea;

     -- Se borra de la tabla Tipo Solicitud Linea
     DELETE FROM ape_tipo_solic_linea a WHERE a.liar_oid_line_arma = vn_oid_linea;

     -- Se borra de la tabla de Linea de Armado
     DELETE FROM ape_linea_armad a WHERE a.oid_line_arma = vn_oid_linea AND ccdi_oid_conf_cent_dist = vn_oid_centro;

     -- Se borra de la tabla de Idiomas
     DELETE FROM gen_i18n_sicc_pais WHERE attr_enti = 'APE_LINEA_ARMAD' AND val_oid =  vn_oid_linea;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_LINEA_ARMAD: '||ls_sqlerrm);
 END APE_PR_ELIMI_LINEA_ARMAD;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_LINEA_ARMAD
     Fecha Creacion    : 14/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_LINEA_ARMAD (
   p_oid_linea    IN  NUMBER,
   p_val_error    IN OUT VARCHAR2,
   p_nom_tabla    IN OUT VARCHAR2
 )
 IS
   vn_contador    NUMBER;

 BEGIN

   vn_contador := 0;
   -- Validando en la tabla ape_estim_produ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_estim_produ a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_ESTIM_PRODU';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_linea_tipo_caja_embal
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_linea_tipo_caja_embal a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LINEA_TIPO_CAJA_EMBAL';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_linea_uadmi
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_linea_uadmi a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LINEA_UADMI';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_lista_picad_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_lista_picad_cabec a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LISTA_PICAD_CABEC';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_olas_xdia
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_olas_xdia a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_OLAS_XDIA';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_orden_anaqu_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_orden_anaqu_cabec a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_ORDEN_ANAQU_CABEC';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_param_balan_area_chequ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_param_balan_area_chequ a
      WHERE a.liar_oid_line_arma = p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_PARAM_BALAN_AREA_CHEQU';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_subli_armad
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_subli_armad a
      WHERE a.liar_oid_line_arma =  p_oid_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_SUBLI_ARMAD';
     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_LINEA_ARMAD: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_LINEA_ARMAD;

/**************************************************************************
     Descripcion       : Registra en la tabla APE_LINEA_UADMI
     Fecha Creacion    : 16/06/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
            Cod Pais
            Oid Linea Armado
            Oid AFP
            Cod Region
            Cod Zona
            Cod Seccion
     Parametros Salida : Ninguno
***************************************************************************/
 PROCEDURE APE_PR_REGIS_LINEA_UADMI (
   p_cod_Pais       IN VARCHAR2,
   p_oidLineArma    IN VARCHAR2,
   p_oid_AAFP       IN VARCHAR2,
   p_cod_Region     IN VARCHAR2,
   p_cod_Zona       IN VARCHAR2,
   p_cod_Seccion    IN VARCHAR2
 )
 IS
   vn_oid_Region     VARCHAR2(20);
   vn_oid_Zona       VARCHAR2(20);
   vn_oid_Seccion    VARCHAR2(20);
   p_contador        NUMBER;
   vn_flag_valida    NUMBER;

 BEGIN

   vn_oid_Region   := '';
   vn_oid_Zona     := '';
   vn_oid_Seccion  := '';
   vn_flag_valida  := 0;

   -- Obtenemos el valor de OidRegion
   vn_oid_Region   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(p_cod_Pais,'T','VD',p_cod_Region);

   -- Validando el valor de Cod Zona
   IF (p_cod_Zona IS NOT null) THEN
     --Verificamos si existe el OidZona
     SELECT COUNT(1)
       INTO p_contador
       FROM zon_zona a
      WHERE a.zorg_oid_regi = vn_oid_Region
        AND a.cod_zona = p_cod_Zona;

     IF (p_contador = 0) THEN
       vn_oid_Zona := NULL;
     ELSE
       -- Obtenemos el valor de OidZona
       SELECT a.oid_zona
         INTO vn_oid_Zona
         FROM zon_zona a
        WHERE a.zorg_oid_regi = vn_oid_Region
          AND a.cod_zona = p_cod_Zona;
     END IF;

   ELSE
     vn_oid_Zona := NULL;
   END IF;

   IF (p_cod_Seccion IS NOT NULL AND vn_oid_Zona IS NOT NULL) THEN
     -- Verificamos si existe el oidSeccion
     SELECT COUNT(1)
       INTO p_contador
       FROM zon_secci
      WHERE zzon_oid_zona = vn_oid_Zona
        AND cod_secc = p_cod_Seccion;

     IF (p_contador = 0) THEN
       vn_oid_Seccion := NULL;
     ELSE
       -- Obtenemos el valor de OidSeccion
       SELECT oid_secc
         INTO vn_oid_Seccion
         FROM zon_secci
        WHERE zzon_oid_zona = vn_oid_Zona
          AND cod_secc = p_cod_Seccion;
     END IF;

   ELSE
     vn_oid_Seccion := NULL;
   END IF;

   --- Se procede a registrar en la tabla APE_LINEA_UADMI
   IF (p_oid_AAFP = '1') THEN

     IF (vn_oid_Region IS NOT NULL AND vn_oid_Zona IS NOT NULL AND vn_oid_Seccion IS NOT NULL) THEN

       vn_flag_valida := APE_FN_VALIDA_UADMI_LINEA(p_cod_Pais,p_oidLineArma,p_oid_AAFP,vn_oid_Region,vn_oid_Zona,vn_oid_Seccion);

       IF (vn_flag_valida = 0) THEN

         INSERT INTO ape_linea_uadmi(
      	   oid_line_uadm,
      		 liar_oid_line_arma,
      		 aafp_oid_aafp,
      		 zorg_oid_regi,
      		 zzon_oid_zona,
      		 zscc_oid_secc
         )
         VALUES(
      	   ape_uali_seq.nextval,
      		 TO_NUMBER(p_oidLineArma),
      		 TO_NUMBER(p_oid_AAFP),
      		 vn_oid_Region,
      		 vn_oid_Zona,
      		 vn_oid_Seccion
         );

       END IF;

     END IF;

   END IF;

   IF (p_oid_AAFP = '3') THEN

     IF (vn_oid_Region IS NOT NULL AND vn_oid_Zona IS NOT NULL) THEN

       vn_flag_valida := APE_FN_VALIDA_UADMI_LINEA(p_cod_Pais,p_oidLineArma,p_oid_AAFP,vn_oid_Region,vn_oid_Zona,vn_oid_Seccion);

       IF (vn_flag_valida = 0) THEN

         INSERT INTO ape_linea_uadmi(
           oid_line_uadm,
      		 liar_oid_line_arma,
      		 aafp_oid_aafp,
      		 zorg_oid_regi,
      		 zzon_oid_zona,
      		 zscc_oid_secc
         )
         VALUES(
      	   ape_uali_seq.nextval,
      		 TO_NUMBER(p_oidLineArma),
      		 TO_NUMBER(p_oid_AAFP),
      		 vn_oid_Region,
      		 vn_oid_Zona,
      		 vn_oid_Seccion
         );

       END IF;

     END IF;

   END IF;

   IF (p_oid_AAFP = '2') THEN

     IF (vn_oid_Region IS NOT NULL) THEN

       vn_flag_valida := APE_FN_VALIDA_UADMI_LINEA(p_cod_Pais,p_oidLineArma,p_oid_AAFP,vn_oid_Region,vn_oid_Zona,vn_oid_Seccion);

       IF (vn_flag_valida = 0) THEN

         INSERT INTO ape_linea_uadmi(
           oid_line_uadm,
           liar_oid_line_arma,
           aafp_oid_aafp,
           zorg_oid_regi,
           zzon_oid_zona,
           zscc_oid_secc
         )
         VALUES(
           ape_uali_seq.nextval,
           TO_NUMBER(p_oidLineArma),
           TO_NUMBER(p_oid_AAFP),
           vn_oid_Region,
           vn_oid_Zona,
           vn_oid_Seccion
         );

       END IF;

     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_REGIS_LINEA_UADMI: '||ls_sqlerrm);
 END APE_PR_REGIS_LINEA_UADMI;

/**************************************************************************
Descripcion        : Valida la existencia de un registro previo en la tabla APE_LINEA_UADMI
Fecha Creacion     : 16/06/2010
Autor              : Nicolas Lopez
***************************************************************************/
 FUNCTION APE_FN_VALIDA_UADMI_LINEA (
   p_cod_Pais       IN VARCHAR2,
   p_oidLineArma    IN VARCHAR2,
   p_oid_AAFP       IN VARCHAR2,
   p_cod_Region     IN VARCHAR2,
   p_cod_Zona       IN VARCHAR2,
   p_cod_Seccion    IN VARCHAR2
 )
 RETURN NUMBER
 IS
   lnFlagvalida      NUMBER;
   vn_oid_Region     VARCHAR2(20);
   vn_oid_Zona       VARCHAR2(20);
   vn_oid_Seccion    VARCHAR2(20);
   p_contador        NUMBER;
   vn_cantidad       NUMBER;
   ln_id_pais        seg_pais.oid_pais%TYPE;
   ln_id_canal       seg_canal.oid_cana%TYPE;
   ln_id_marca       seg_marca.oid_marc%TYPE;

 BEGIN

   lnFlagvalida := 0;
   vn_oid_Region := '';
   vn_oid_Zona := '';
   vn_oid_Seccion := '';
   vn_cantidad := 0;
   ln_id_pais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_Pais, TRUE);
   ln_id_Marca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T', TRUE);
   ln_id_canal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD', TRUE);

   -- Obtenemos el valor de OidRegion
   --vn_oid_Region   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(p_cod_Pais,'T','VD',p_cod_Region);

   SELECT COUNT(1)
     INTO vn_cantidad
     FROM zon_regio a
    WHERE a.pais_oid_pais = ln_id_pais
      AND a.marc_oid_marc = ln_id_Marca
      AND a.cana_oid_cana = ln_id_canal
      AND a.cod_regi = p_cod_Region;

   IF (vn_cantidad = 0) THEN
     vn_oid_Region := NULL;
   ELSE
     SELECT a.oid_regi
       INTO vn_oid_Region
       FROM zon_regio a
      WHERE a.pais_oid_pais = ln_id_pais
        AND a.marc_oid_marc = ln_id_Marca
        AND a.cana_oid_cana = ln_id_canal
        AND a.cod_regi = p_cod_Region;
   END IF;

   -- Validando el valor de Cod Zona
   IF (p_cod_Zona IS NOT NULL AND vn_oid_Region IS NOT NULL) THEN
     --Verificamos si existe el OidZona
     SELECT COUNT(1)
       INTO p_contador
       FROM zon_zona a
      WHERE a.zorg_oid_regi = vn_oid_Region
        AND a.cod_zona = p_cod_Zona;

     IF (p_contador = 0) THEN
       vn_oid_Zona := NULL;
     ELSE
       -- Obtenemos el valor de OidZona
       SELECT a.oid_zona
         INTO vn_oid_Zona
         FROM zon_zona a
        WHERE a.zorg_oid_regi = vn_oid_Region
         AND a.cod_zona = p_cod_Zona;
     END IF;

   ELSE
     vn_oid_Zona := NULL;
   END IF;

   IF (p_cod_Seccion IS NOT NULL AND vn_oid_Zona IS NOT NULL) THEN

     SELECT COUNT(1)
       INTO p_contador
       FROM zon_secci
      WHERE zzon_oid_zona = vn_oid_Zona
        AND cod_secc = p_cod_Seccion;

     IF (p_contador = 0) THEN
       vn_oid_Seccion := NULL;
     ELSE
       -- Obtenemos el valor de OidSeccion
       SELECT oid_secc
         INTO vn_oid_Seccion
         FROM zon_secci
        WHERE zzon_oid_zona = vn_oid_Zona
          AND cod_secc = p_cod_Seccion;
     END IF;

   ELSE
     vn_oid_Seccion := NULL;
   END IF;

   -- Se valida si existe registro previo en la tabla APE_LINEA_UADMI
   IF (p_oid_AAFP = '2') THEN
     -- Valida si existe registro para el Oid Region
     SELECT COUNT(1)
       INTO lnFlagvalida
       FROM ape_linea_uadmi
      WHERE liar_oid_line_arma = p_oidLineArma
        AND aafp_oid_aafp = p_oid_AAFP
        AND zorg_oid_regi = vn_oid_Region;
   END IF;

   IF (p_oid_AAFP = '3') THEN
     -- Valida si existe registro para el OOid Region, Oid Zona
     SELECT COUNT(1)
       INTO lnFlagvalida
       FROM ape_linea_uadmi
      WHERE liar_oid_line_arma = p_oidLineArma
        AND aafp_oid_aafp = p_oid_AAFP
        AND zorg_oid_regi = vn_oid_Region
        AND zzon_oid_zona = vn_oid_Zona;
   END IF;

   IF (p_oid_AAFP = '1') THEN
     -- Valida si existe registro para el OOid Region, Oid Zona, Oid Seccion
     SELECT COUNT(1)
       INTO lnFlagvalida
       FROM ape_linea_uadmi
      WHERE liar_oid_line_arma = p_oidLineArma
        AND aafp_oid_aafp = p_oid_AAFP
        AND zorg_oid_regi = vn_oid_Region
        AND zzon_oid_zona = vn_oid_Zona
        AND zscc_oid_secc = vn_oid_Seccion;
   END IF;

   RETURN lnFlagvalida;

 EXCEPTION
   WHEN no_data_found THEN
     RETURN NULL;

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm, 1, 1000);
     RAISE_APPLICATION_ERROR(-20123,'ERROR GEN_FN_VALIDA_UADMI_LINEA_APE ' || ls_sqlerrm);
 END APE_FN_VALIDA_UADMI_LINEA;

/**************************************************************************
Descripcion        : Obtiene el numero de chanel Aframe
Fecha Creacion     : 23/09/2010
Autor              : Christian Gonzales komiya
***************************************************************************/
 FUNCTION APE_FN_DEVUE_NUMER_CHANE (
   p_num_unid_estim    NUMBER,
   p_num_hora_inve     NUMBER,
   p_num_lane_capa     NUMBER
 )
 -- p_num_lane_capa = valor num_lane_capa_60 o num_lane_capa_95 segun lo que se necesite hallar
 RETURN NUMBER
 IS
   vn_nume_chane    NUMBER;

 BEGIN

   SELECT CEIL( DECODE( p_num_lane_capa,0,0,
                ( ( DECODE( val_hora_dia, 0, 0, DECODE( Val_Dias_Camp, 0 ,0 , (p_num_unid_estim/Val_Dias_Camp))/ val_hora_dia)
                  * DECODE(NVL(p_num_hora_inve,0),0, Val_Hora_Inve, p_num_hora_inve))* val_fact_segu_inve ) / p_num_lane_capa
          ))
     INTO vn_nume_chane
     FROM ape_valor_defau_afram;

   RETURN vn_nume_chane;

 EXCEPTION
   WHEN no_data_found THEN
     RETURN NULL;

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm, 1, 1000);
     RAISE_APPLICATION_ERROR(-20123,'ERROR GEN_FN_DEVUE_NUMER_CHANE ' || ls_sqlerrm);
 END APE_FN_DEVUE_NUMER_CHANE;

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Sub linea de armado.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_SUBLI_ARMAD
     Fecha Creacion    : 23/06/2010
     Parametros Entrada :
            Oid Sub Linea Armado
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_SUBLI_ARMAD (
   p_oid_sub_linea    IN  VARCHAR2,
   p_val_error        OUT VARCHAR2,
   p_nom_tabla        OUT VARCHAR2
 )
 IS
   vn_oid_sub_linea    NUMBER;

 BEGIN

   vn_oid_sub_linea  := to_number(p_oid_sub_linea);
   p_val_error := '0';
   p_nom_tabla := '';

   -- Primero se valida que no existan registros en las tablas hijas
   APE_PR_VALID_REGIS_SUBLI_ARMAD(p_oid_sub_linea, p_val_error, p_nom_tabla);

   -- Si no existen registros en las tablas hijas se procede a borrar
   IF (p_val_error = '0') THEN
     -- Se borra de la tabla Asignacion Impresoras
     DELETE FROM ape_asign_impre a WHERE a.sbar_oid_subl_arma = vn_oid_sub_linea;

     -- Se borra de la tabla Tipo Caja Sub Linea
     DELETE FROM ape_tipo_caja_subli a WHERE a.sbar_oid_subl_arma = vn_oid_sub_linea;

     -- Se borra de la tabla de Sub Linea de Armado
     DELETE FROM ape_subli_armad a WHERE a.oid_subl_arma = vn_oid_sub_linea;

     -- Se borra de la tabla de Idiomas
     DELETE FROM gen_i18n_sicc_pais WHERE attr_enti = 'APE_SUBLI_ARMAD' AND val_oid =  vn_oid_sub_linea;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_SUBLI_ARMAD: '||ls_sqlerrm);
 END APE_PR_ELIMI_SUBLI_ARMAD;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_SUBLI_ARMAD
     Fecha Creacion    : 23/06/2010
     Parametros Entrada :
            Oid Pais
            Oid Centro Distribucion
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_SUBLI_ARMAD (
   p_oid_sub_linea    IN  NUMBER,
   p_val_error        IN OUT VARCHAR2,
   p_nom_tabla        IN OUT VARCHAR2
 )
 IS
   vn_contador    NUMBER;

 BEGIN

   vn_contador := 0;
   -- Validando en la tabla ape_forma_etiqu
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_forma_etiqu a
      WHERE a.sbar_oid_subl_arma = p_oid_sub_linea;

     IF (vn_contador > 0) THEN

       p_val_error := '1';
       p_nom_tabla := 'APE_FORMA_ETIQU';

     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_mapa_centr_distr_detal
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_mapa_centr_distr_detal a
      WHERE a.sbar_oid_subl_arma = p_oid_sub_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_MAPA_CENTR_DISTR_DETAL';
     END IF;

   END IF;

   vn_contador := 0;
   -- Validando en la tabla ape_mapa_zona_detal
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_mapa_zona_detal a
      WHERE a.sbar_oid_subl_arma = p_oid_sub_linea;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_MAPA_ZONA_DETAL';
     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_SUBLI_ARMAD: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_SUBLI_ARMAD;

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Tipo de Anaqueles.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_TIPO_ANAQU
     Fecha Creacion    : 28/06/2010
     Parametros Entrada :
            Oid Tipo Anaquel
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_TIPO_ANAQU (
   p_oid_Tipo_Anaquel    IN  VARCHAR2,
   p_val_error           OUT VARCHAR2,
   p_nom_tabla           OUT VARCHAR2
 )
 IS
   vn_oid_Tipo_Anaquel   NUMBER;

 BEGIN

   vn_oid_Tipo_Anaquel  := to_number(p_oid_Tipo_Anaquel);
   p_val_error := '0';
   p_nom_tabla := '';

   -- Primero se valida que no existan registros en las tablas hijas
   APE_PR_VALID_REGIS_TIPO_ANAQU(p_oid_Tipo_Anaquel, p_val_error, p_nom_tabla);

   -- Si no existen registros en las tablas hijas se procede a borrar
   IF (p_val_error = '0') THEN
     -- Se borra de la tabla de Sub Linea de Armado que no sean marcado por defecto
     DELETE FROM ape_tipo_anaqu a WHERE a.oid_tipo_anaq = vn_oid_Tipo_Anaquel AND a.ind_anaq_defa = '0';

      -- Se borra de la tabla de Idiomas
      DELETE FROM gen_i18n_sicc_comun WHERE attr_enti = 'APE_TIPO_ANAQU' AND val_oid =  vn_oid_Tipo_Anaquel;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_TIPO_ANAQU: '||ls_sqlerrm);
 END APE_PR_ELIMI_TIPO_ANAQU;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_TIPO_ANAQU
     Fecha Creacion    : 28/06/2010
     Parametros Entrada :
            Oid Tipo Anaquel
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_ANAQU (
   p_oid_Tipo_Anaquel    IN  NUMBER,
   p_val_error           IN OUT VARCHAR2,
   p_nom_tabla           IN OUT VARCHAR2
 )
 IS
   vn_contador    NUMBER;

 BEGIN

   vn_contador := 0;

   -- Validando en la tabla ape_mapa_centr_distr_detal
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_mapa_centr_distr_detal a
      WHERE a.tian_oid_tipo_anaq = p_oid_Tipo_Anaquel;

     IF (vn_contador > 0) THEN

       p_val_error := '1';
       p_nom_tabla := 'APE_MAPA_CENTR_DISTR_DETAL';

     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_TIPO_ANAQU: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_TIPO_ANAQU;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_MAPA_ZONA_CABEC
     Fecha Creacion    : 30/06/2010
     Parametros Entrada :
            Oid Mapa Zona Cabecera
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_MAPA_ZONA (
   p_oid_mapazona    IN  NUMBER,
   p_val_error       IN OUT VARCHAR2,
   p_nom_tabla       IN OUT VARCHAR2
 )
 IS
   vn_contador    NUMBER;

 BEGIN

   vn_contador := 0;

   -- Validando en la tabla ape_orden_anaqu_cabec
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_orden_anaqu_cabec
      WHERE mzca_oid_mapa_zona_cabe = p_oid_mapazona;

     IF (vn_contador > 0) THEN

       p_val_error := '1';
       p_nom_tabla := 'APE_ORDEN_ANAQU_CABEC';

     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_MAPA_ZONA: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_MAPA_ZONA;

/**************************************************************************
     Descripcion       : Elimina registros de la tabla de Mapa Zona Cabecera y Detalle.
                         Primero se valida que no existan registros en las tablas hijas de
                         la tabla APE_MAPA_ZONA_CABEC
     Fecha Creacion    : 30/06/2010
     Parametros Entrada :
            Oid Mapa Zona
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_ELIMI_MAPA_ZONA (
   p_oid_mapazona    IN  VARCHAR2,
   p_val_error       OUT VARCHAR2,
   p_nom_tabla       OUT VARCHAR2
 )
 IS
    vn_oid_mapazn    NUMBER;

 BEGIN

   vn_oid_mapazn := to_number(p_oid_mapazona);
   p_val_error := '0';
   p_nom_tabla := '';

   -- Primero se valida que no existan registros en las tablas hijas
   APE_PR_VALID_REGIS_MAPA_ZONA(vn_oid_mapazn, p_val_error, p_nom_tabla);

   -- Si no existen registros en las tablas hijas se procede a borrar
   IF (p_val_error = '0') THEN
     -- Se borra de la tabla Mapa Zona Detalle
     DELETE FROM ape_mapa_zona_detal a WHERE a.mzca_oid_mapa_zona_cabe = vn_oid_mapazn;

     -- Se borra de la tabla de Idiomas
     DELETE FROM gen_i18n_sicc_pais WHERE attr_enti = 'APE_MAPA_ZONA_CABEC' AND val_oid =  vn_oid_mapazn;

     -- Se borra de la tabla Mapa Zona Cabecera
     DELETE FROM ape_mapa_zona_cabec a WHERE a.oid_mapa_zona_cabe = vn_oid_mapazn;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_MAPA_ZONA: '||ls_sqlerrm);
 END APE_PR_ELIMI_MAPA_ZONA;

/**************************************************************************
     Descripcion       : Genera el Mapa del Centro de Distribucion
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_MAPA_CENTR_DISTR (
   p_cod_centr_distr    IN VARCHAR2,
   p_cod_mapa				    IN VARCHAR2,
   p_des_mapa			      IN VARCHAR2,
   p_oid_idiomaISO			IN VARCHAR2
 )
 IS
    vn_oid_centr_distr    NUMBER;
    vn_oid_mapa			      NUMBER;
    vn_oid_idiomaISO      NUMBER;

 BEGIN

   vn_oid_centr_distr := to_number(p_cod_centr_distr);
   vn_oid_idiomaISO := to_number(p_oid_idiomaISO);

   -- Obteniendo el oid del centro de distribucion
   SELECT oid_conf_cent_dist
     INTO vn_oid_centr_distr
     FROM app_confi_centr_distr
    WHERE cod_cent_dist = p_cod_centr_distr;

   -- Obteniendo el siguiente oid del mapa cabecera
   SELECT APE_MCDC_SEQ.nextval
     INTO vn_oid_mapa
     FROM dual;

   -- Insertamos en la tabla cabecera del mapa y en la tabla de idiomas
   APE_PR_INSER_MAPA_CABEC(vn_oid_mapa, p_cod_mapa, p_des_mapa, vn_oid_centr_distr, vn_oid_idiomaISO );

   -- Insertamos en la tabla detalle del mapa
   APE_PR_GENER_DETAL_MAPA(vn_oid_mapa, vn_oid_centr_distr );

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_MAPA_CENTR_DISTR: '||ls_sqlerrm);
 END APE_PR_GENER_MAPA_CENTR_DISTR;

/**************************************************************************
     Descripcion       : Inserta en la tabla cabecera de Mapa de Centro de
     										 distribucion
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_INSER_MAPA_CABEC (
   p_oid_mapa           IN NUMBER,
   p_cod_mapa						IN VARCHAR2,
   p_des_mapa						IN VARCHAR2,
   p_oid_centr_distr    IN NUMBER,
   p_oid_idiomaISO 			IN NUMBER
 )
 IS
   vn_oid_atributo    NUMBER;

 BEGIN
   -- insertando en APE_MAPA_CENTR_DISTR_CABEC
   INSERT INTO ape_mapa_centr_distr_cabec (
     oid_mapa_cent_dist_cabe,
  	 num_codi_mapa,
  	 ccdi_oid_conf_cent_dist
   )
   VALUES (
     p_oid_mapa,
     p_cod_mapa,
     p_oid_centr_distr
   );

   SELECT g.num_atri
     INTO	vn_oid_atributo
     FROM gen_atrib_tradu g
    WHERE g.nom_enti = 'APE_MAPA_CENTR_DISTR_CABEC';

   -- insertando en la tabla de Idiomas
   INSERT INTO gen_i18n_sicc_pais (
     oid_i18n,
  	 attr_enti,
  	 attr_num_atri,
  	 idio_oid_idio,
  	 val_i18n,
  	 val_oid
   )
   VALUES (
     GEN_I18N_SEQ.nextval,
     'APE_MAPA_CENTR_DISTR_CABEC',
     vn_oid_atributo,
     p_oid_idiomaISO,
     p_des_mapa,
     p_oid_mapa
   );

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_INSER_MAPA_CABEC: '||ls_sqlerrm);
 END APE_PR_INSER_MAPA_CABEC;

/**************************************************************************
     Descripcion       : Genera los detalles de Mapa de Centro de
     										 distribucion a insertar
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_DETAL_MAPA (
   p_oid_mapa           IN NUMBER,
   p_oid_centr_distr    IN NUMBER
 )
 IS
   CURSOR c_lineas IS
     SELECT oid_line_arma
       FROM ape_linea_armad
      WHERE ccdi_oid_conf_cent_dist = p_oid_centr_distr
    ORDER BY 1;

   CURSOR c_sublineas (v_oidLinea NUMBER) IS
     SELECT oid_subl_arma         oid_sublinea,
            val_letr_para_anaq    val_letra_anaquel,
            ind_fren_espa         ind_frente_espalda,
            num_bahi_fren         num_bahia_frente,
            num_bahi_espa         num_bahia_espalda,
            num_nive_fren         num_nivel_frente,
            num_nive_espa         num_nivel_espalda,
            num_colu_fren         num_columna_frente,
            num_colu_espa         num_columna_espalda,
            sipi_oid_sist_pica    oid_sistema_picado
       FROM ape_subli_armad
      WHERE liar_oid_line_arma = v_oidLinea
        AND ccdi_oid_conf_cent_dist = p_oid_centr_distr
    ORDER BY 1;

   TYPE subLineas IS RECORD(
     oid_sublinea          ape_subli_armad.oid_subl_arma%TYPE,
     val_letra_anaquel     ape_subli_armad.val_letr_para_anaq%TYPE,
     ind_frente_espalda    ape_subli_armad.ind_fren_espa%TYPE,
     num_bahia_frente      ape_subli_armad.num_bahi_fren%TYPE,
     num_bahia_espalda     ape_subli_armad.num_bahi_espa%TYPE,
     num_nivel_frente      ape_subli_armad.num_nive_fren%TYPE,
     num_nivel_espalda     ape_subli_armad.num_nive_espa%TYPE,
     num_columna_frente    ape_subli_armad.num_colu_fren%TYPE,
     num_columna_espalda   ape_subli_armad.num_colu_espa%TYPE,
     oid_sistema_picado    ape_subli_armad.sipi_oid_sist_pica%TYPE
   );

   TYPE subLineasTab  IS TABLE OF subLineas;
   subLineasRecord subLineasTab;

   W_FILAS                 NUMBER := 1000;
   vn_oid_linea            NUMBER;
   vn_bahias			         NUMBER;
   vn_num_bahia_frente     NUMBER;
   vn_num_bahia_espalda    NUMBER;
   vs_num_bahia            VARCHAR2(3);
   vn_niveles  	           NUMBER;
   vn_columnas		         NUMBER;
   vs_letra_nivel          VARCHAR2(1);
   vs_posicion             NUMBER;
   vs_num_Anaquel          VARCHAR2(10);

 BEGIN

   OPEN c_lineas;
     LOOP
       FETCH c_lineas INTO vn_oid_linea;
       EXIT WHEN (c_lineas%NOTFOUND);

       OPEN c_sublineas (vn_oid_linea);
         LOOP
           FETCH c_sublineas BULK COLLECT INTO subLineasRecord LIMIT W_FILAS;

             IF subLineasRecord.COUNT > 0 THEN

               FOR x IN subLineasRecord.FIRST .. subLineasRecord.LAST LOOP
                 vn_num_bahia_frente := 1;
                 vn_num_bahia_espalda := 2;

                 -- Frente
                 vn_bahias := subLineasRecord(x).num_bahia_frente;
                 vn_niveles :=subLineasRecord(x).num_nivel_frente;
                 vn_columnas := subLineasRecord(x).num_columna_frente;

                 -- Recorriendo las bahias Frente
                 FOR contadorBahias IN 1 .. vn_bahias  LOOP
                   vs_num_bahia := to_char(vn_num_bahia_frente, '00');

                   -- Recorriendo los niveles Frente
                   FOR contadorNiveles IN 1 .. vn_niveles LOOP
                     -- Se obtiene la letra para el nivel
                     vs_letra_nivel := ape_fn_devue_letra_nivel (contadorNiveles);

                     -- Recorriendo Columnas Frente
                     FOR contadorColumnas IN 1 .. vn_columnas LOOP
                       vs_posicion := to_char(contadorColumnas);

                       -- Se forma el numero de Anaquel
                       vs_num_Anaquel := TRIM(subLineasRecord(x).val_letra_anaquel) || TRIM(vs_num_bahia)||
                                          TRIM(vs_letra_nivel) ||  TRIM(vs_posicion);

                       -- Se inserta en la tabla detalle
                       ape_pr_inser_mapa_detal( subLineasRecord(x).oid_sistema_picado, vs_num_Anaquel,
                                                p_oid_mapa, subLineasRecord(x).oid_sublinea );

                     END LOOP;

                   END LOOP;
                   vn_num_bahia_frente := vn_num_bahia_frente + 2;

                 END LOOP;

                 -- Si el indicador de Frente Espalda esta marcado se recorren para espalda
                 -- Espalda
                 IF (subLineasRecord(x).ind_frente_espalda = 1) THEN

                   vn_bahias := subLineasRecord(x).num_bahia_espalda;
                   vn_niveles :=subLineasRecord(x).num_nivel_espalda;
                   vn_columnas := subLineasRecord(x).num_columna_espalda;

                   -- Recorriendo las bahias Espalda
                   FOR contadorBahias IN 1 .. vn_bahias  LOOP
                     vs_num_bahia := to_char(vn_num_bahia_espalda, '00');

                     -- Recorriendo los niveles Espalda
                     FOR contadorNiveles IN 1 .. vn_niveles LOOP
                       -- Se obtiene la letra para el nivel
                       vs_letra_nivel := ape_fn_devue_letra_nivel(contadorNiveles);

                       -- Recorriendo Columnas Espalda
                       FOR contadorColumnas IN 1 .. vn_columnas LOOP
                         vs_posicion := to_char(contadorColumnas);

                         -- Se forma el numero de Anaquel
                         vs_num_Anaquel := TRIM(subLineasRecord(x).val_letra_anaquel) || TRIM(vs_num_bahia) ||
                                           TRIM(vs_letra_nivel) ||  TRIM(vs_posicion);

                         -- Se inserta en la tabla detalle
                         ape_pr_inser_mapa_detal( subLineasRecord(x).oid_sistema_picado, vs_num_Anaquel,
                                                  p_oid_mapa, subLineasRecord(x).oid_sublinea );
                       END LOOP;
                     END LOOP;

                     vn_num_bahia_espalda := vn_num_bahia_espalda + 2;
                   END LOOP;

                 END IF;
               END LOOP;

             END IF;

           EXIT WHEN (c_sublineas%NOTFOUND);
         END LOOP;
       CLOSE c_sublineas;

     END LOOP;
   CLOSE c_lineas;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_DETAL_MAPA: '||ls_sqlerrm);
 END APE_PR_GENER_DETAL_MAPA;

/**************************************************************************
     Descripcion : Devuelve la letra segun la posicion
     Fecha Creacion : 22/10/2009
 ***************************************************************************/
 FUNCTION APE_FN_DEVUE_LETRA_NIVEL (
   pn_posicion    NUMBER
 )
 RETURN VARCHAR2
 IS
   vs_letra    VARCHAR2(1);

 BEGIN

   CASE pn_posicion
     WHEN 1 THEN vs_letra := 'A';
     WHEN 2 THEN vs_letra := 'B';
     WHEN 3 THEN vs_letra := 'C';
     WHEN 4 THEN vs_letra := 'D';
     WHEN 5 THEN vs_letra := 'E';
     WHEN 6 THEN vs_letra := 'F';
     WHEN 7 THEN vs_letra := 'G';
     WHEN 8 THEN vs_letra := 'H';
     WHEN 9 THEN vs_letra := 'I';
     WHEN 10 THEN vs_letra := 'J';
     WHEN 11 THEN vs_letra := 'K';
     WHEN 12 THEN vs_letra := 'L';
     WHEN 13 THEN vs_letra := 'M';
     WHEN 14 THEN vs_letra := 'N';
     WHEN 15 THEN vs_letra := 'O';
     WHEN 16 THEN vs_letra := 'P';
     WHEN 17 THEN vs_letra := 'Q';
     WHEN 18 THEN vs_letra := 'R';
     WHEN 19 THEN vs_letra := 'S';
     WHEN 20 THEN vs_letra := 'T';
     WHEN 21 THEN vs_letra := 'U';
     WHEN 22 THEN vs_letra := 'V';
     WHEN 23 THEN vs_letra := 'W';
     WHEN 24 THEN vs_letra := 'X';
     WHEN 25 THEN vs_letra := 'Y';
     ELSE vs_letra := 'Z';
   END CASE;

   RETURN vs_letra;

 EXCEPTION
   WHEN OTHERS THEN
     RETURN '';
 END APE_FN_DEVUE_LETRA_NIVEL;

/**************************************************************************
     Descripcion       : Inserta en la tabla cabecera de Mapa de Centro de
     										 distribucion
     Fecha Creacion    : 30/06/2010
***************************************************************************/
 PROCEDURE APE_PR_INSER_MAPA_DETAL (
   p_oid_picado      IN NUMBER,
   p_num_anaquel     IN VARCHAR2,
   p_oid_mapa        IN NUMBER,
   p_oid_sublinea    IN NUMBER
 )
 IS
   vn_oid_tipo_anaquel     NUMBER;
   vn_oid_mapa_detal       NUMBER;
   vn_cont_tipo_anaquel    NUMBER;

 BEGIN

   vn_oid_tipo_anaquel := NULL;
   -- Obteniendo el tipo de anquel por el sistema de picado
   -- Si el sistema de Picado es Aframe (3) se selecciona el anaquel por defecto Tipo Aframe
   -- indicador tipo aframe = 1
   IF ( p_oid_picado = 3) THEN

     SELECT COUNT(1)
       INTO vn_cont_tipo_anaquel
       FROM ape_tipo_anaqu a
      WHERE a.ind_anaq_defa = 1
        AND a.ind_tipo_afrm = 1;

     IF (vn_cont_tipo_anaquel >0) THEN
     SELECT a.oid_tipo_anaq
       INTO vn_oid_tipo_anaquel
       FROM ape_tipo_anaqu a
      WHERE a.ind_anaq_defa = 1
        AND a.ind_tipo_afrm = 1;
     END IF;

   -- Caso contrario se obtenie el tipo por defecto normal
   -- indicador tipo aframe = 0
   ELSE
     SELECT a.oid_tipo_anaq
       INTO vn_oid_tipo_anaquel
       FROM ape_tipo_anaqu a
      WHERE a.ind_anaq_defa = 1
        AND a.ind_tipo_afrm = 0;
   END IF;

   -- Obteniendo el siguiente oid del mapa cabecera
   SELECT APE_MCDD_SEQ.nextval
     INTO vn_oid_mapa_detal
     FROM dual;

   INSERT INTO ape_mapa_centr_distr_detal(
     oid_mapa_cent_dist_deta,
     num_anaq,
     num_capa,
     mcdc_oid_mapa_cent_dist_cabe,
     sbar_oid_subl_arma,
     tian_oid_tipo_anaq,
     ind_expa
   )
   VALUES(
     vn_oid_mapa_detal,
     p_num_anaquel,
     1,
     p_oid_mapa,
     p_oid_sublinea,
     vn_oid_tipo_anaquel,
     0
   );

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_INSER_MAPA_DETAL: '||ls_sqlerrm);
 END APE_PR_INSER_MAPA_DETAL;

/**************************************************************************
     Descripcion       : Genera un nuevo Mapa de Centro Distribucion a travez de una copia de un MAPA CD elegido
     Autor             : Nicolas Lopez
     Fecha Creacion    : 07/06/2010
     Parametros Entrada :
            p_oid_pais
            p_oid_centr_distr
            p_oid_mapa_cent_dist
            p_desc_mapa_cent
            p_oid_idiomaISO
***************************************************************************/
 PROCEDURE APE_PR_GENER_MAPA_CD_DETAL (
   p_oid_pais              VARCHAR2,
   p_oid_centr_distr       VARCHAR2,
   p_oid_mapa_cent_dist    VARCHAR2,
   p_desc_mapa_cent        VARCHAR2,
   p_oid_idiomaISO         gen_i18n_sicc_pais.idio_oid_idio %TYPE
 )
 IS
   W_FILAS                           NUMBER := 1000;
   p_num_atri                        gen_atrib_tradu.num_atri %TYPE:=0;
   v_num_codi_mapa                   ape_mapa_centr_distr_cabec.num_codi_mapa %TYPE;
   p_oid_mapa_cent_dist_cabe_ante    ape_mapa_centr_distr_cabec.oid_mapa_cent_dist_cabe %TYPE := NULL;
   p_oid_mapa_cent_dist_cabe         ape_mapa_centr_distr_cabec.oid_mapa_cent_dist_cabe %TYPE;
   p_nuevo                           ape_mapa_centr_distr_cabec.oid_mapa_cent_dist_cabe %TYPE;
   p_ccdi_oid_conf_cent_dist         ape_mapa_centr_distr_cabec.ccdi_oid_conf_cent_dist %TYPE;

   TYPE tmptablaMapa IS RECORD (
     oid_mapa_cent_dist_cabe         ape_mapa_centr_distr_cabec.oid_mapa_cent_dist_cabe %TYPE,
     num_codi_mapa                   ape_mapa_centr_distr_cabec.num_codi_mapa %TYPE,
     ccdi_oid_conf_cent_dist         ape_mapa_centr_distr_cabec.ccdi_oid_conf_cent_dist %TYPE,
     oid_mapa_cent_dist_deta         APE_MAPA_CENTR_DISTR_DETAL.oid_mapa_cent_dist_deta %TYPE,
     num_anaq                        ape_mapa_centr_distr_detal.num_anaq %TYPE,
     num_capa                        ape_mapa_centr_distr_detal.num_capa %TYPE,
     mcdc_oid_mapa_cent_dist_cabe    ape_mapa_centr_distr_detal.mcdc_oid_mapa_cent_dist_cabe %TYPE,
     mcdd_oid_mapa_cent_dist_deta    ape_mapa_centr_distr_detal.mcdd_oid_mapa_cent_dist_deta %TYPE,
     sbar_oid_subl_arma              ape_mapa_centr_distr_detal.sbar_oid_subl_arma %TYPE,
     ind_expa                        ape_mapa_centr_distr_detal.ind_expa %TYPE,
     tian_oid_tipo_anaq              ape_mapa_centr_distr_detal.tian_oid_tipo_anaq %TYPE,
     val_side                        ape_mapa_centr_distr_detal.val_side %TYPE,
     num_afra_fram_numb              ape_mapa_centr_distr_detal.num_afra_fram_numb %TYPE,
     num_afra_chan_addr              ape_mapa_centr_distr_detal.num_afra_chan_addr %TYPE,
     num_afra_heig                   ape_mapa_centr_distr_detal.num_afra_heig %TYPE,
     num_afra_widt                   ape_mapa_centr_distr_detal.num_afra_widt %TYPE,
     num_afra_mach_addr              ape_mapa_centr_distr_detal.num_afra_mach_addr %TYPE,
     num_afra_leve_numb              ape_mapa_centr_distr_detal.num_afra_leve_numb %TYPE
   );

   TYPE tablaRegMapa IS TABLE OF tmptablaMapa;
   tablaRegMapasrecord tablaRegMapa;

   -- Se obtiene la informacion de las Tablas APP_CONFI_CENTR_DISTR y APE_MAPA_CENTR_DISTR_DETAL
   CURSOR MAPACD(p_oid_pais           NUMBER, p_oid_centr_distr NUMBER, p_oid_mapa_cent_dist NUMBER ) IS
     SELECT mapacab.oid_mapa_cent_dist_cabe,
            mapacab.num_codi_mapa,
            mapacab.ccdi_oid_conf_cent_dist,
            mpcddet.oid_mapa_cent_dist_deta,
            mpcddet.num_anaq,
            mpcddet.num_capa,
            mpcddet.mcdc_oid_mapa_cent_dist_cabe,
            mpcddet.mcdd_oid_mapa_cent_dist_deta,
            mpcddet.sbar_oid_subl_arma,
            mpcddet.ind_expa,
            mpcddet.tian_oid_tipo_anaq,
            mpcddet.val_side,
            mpcddet.num_afra_fram_numb,
            mpcddet.num_afra_chan_addr,
            mpcddet.num_afra_heig,
            mpcddet.num_afra_widt,
            mpcddet.num_afra_mach_addr,
            mpcddet.num_afra_leve_numb
       FROM app_confi_centr_distr cent,
            ape_mapa_centr_distr_cabec mapacab,
            ape_mapa_centr_distr_detal mpcddet
      WHERE cent.pais_oid_pais = p_oid_pais
        AND cent.oid_conf_cent_dist = p_oid_centr_distr
        AND mapacab.ccdi_oid_conf_cent_dist = cent.oid_conf_cent_dist
        AND mapacab.oid_mapa_cent_dist_cabe = p_oid_mapa_cent_dist
        AND mpcddet.mcdc_oid_mapa_cent_dist_cabe = mapacab.oid_mapa_cent_dist_cabe
    ORDER BY mapacab.oid_mapa_cent_dist_cabe;

 BEGIN

   OPEN MAPACD(p_oid_pais ,p_oid_centr_distr, p_oid_mapa_cent_dist);
     LOOP
       FETCH MAPACD BULK COLLECT INTO tablaRegMapasrecord LIMIT W_FILAS;

         IF tablaRegMapasrecord.COUNT > 0 THEN

           FOR x IN tablaRegMapasrecord.FIRST .. tablaRegMapasrecord.LAST LOOP

             p_oid_mapa_cent_dist_cabe:=tablaRegMapasrecord(x).oid_mapa_cent_dist_cabe;
             p_ccdi_oid_conf_cent_dist:=tablaRegMapasrecord(x).ccdi_oid_conf_cent_dist;

             IF ( p_oid_mapa_cent_dist_cabe_ante IS NULL OR
                  p_oid_mapa_cent_dist_cabe_ante <> p_oid_mapa_cent_dist_cabe ) THEN
               -- Se obtiene el autogenerado para el nuevo Mapa CD Destino
               SELECT ape_mcdc_seq.NEXTVAL
                 INTO p_nuevo
                 FROM dual;

               -- Se obtiene el Maximo numero de Codigo de Mapa CD + 1, no se envia el criterio de oidCentroDistribucion.
               SELECT NVL(MAX(num_codi_mapa),0)+1
                 INTO v_num_codi_mapa
                 FROM ape_mapa_centr_distr_cabec;

               -- Se realiza la insercion en la tabla APE_MAPA_CENTR_DISTR_CABEC
               INSERT INTO APE_MAPA_CENTR_DISTR_CABEC(
                 oid_mapa_cent_dist_cabe,
                 num_codi_mapa,
                 ccdi_oid_conf_cent_dist
               )
               VALUES(
                 p_nuevo,
                 v_num_codi_mapa,
                 p_ccdi_oid_conf_cent_dist
               );

               SELECT num_atri
                 INTO p_num_atri
                 FROM gen_atrib_tradu
                WHERE nom_enti = 'APE_MAPA_CENTR_DISTR_CABEC';

               -- Se registra la descripcion del Mapa CD Destino
               INSERT INTO GEN_I18N_SICC_PAIS (
                 oid_i18n,
                 attr_enti,
                 attr_num_atri,
                 idio_oid_idio,
                 val_i18n,
                 val_oid
               )
               VALUES (
                 GEN_I18N_SEQ.nextval,
                 'APE_MAPA_CENTR_DISTR_CABEC',
                 p_num_atri,
                 p_oid_idiomaISO,
                 p_desc_mapa_cent,
                 p_nuevo
               );

               p_oid_mapa_cent_dist_cabe_ante := p_oid_mapa_cent_dist_cabe;

             END IF;

             -- Se registran los datos del Mapa CD Origen en el Nuevo Mapa CD Destino Detalle
             INSERT INTO APE_MAPA_CENTR_DISTR_DETAL (
               oid_mapa_cent_dist_deta,
               num_anaq,
               num_capa,
               mcdc_oid_mapa_cent_dist_cabe,
               mcdd_oid_mapa_cent_dist_deta,
               sbar_oid_subl_arma,
               ind_expa,
               tian_oid_tipo_anaq,
               val_side,
               num_afra_fram_numb,
               num_afra_chan_addr,
               num_afra_heig,
               num_afra_widt,
               num_afra_mach_addr,
               num_afra_leve_numb
             )
             VALUES (
               ape_mcdd_seq.nextval,
               tablaRegMapasrecord(x).num_anaq,
               tablaRegMapasrecord(x).num_capa,
               p_nuevo,
               tablaRegMapasrecord(x).mcdd_oid_mapa_cent_dist_deta,
               tablaRegMapasrecord(x).sbar_oid_subl_arma,
               tablaRegMapasrecord(x).ind_expa,
               tablaRegMapasrecord(x).tian_oid_tipo_anaq,
               tablaRegMapasrecord(x).val_side,
               tablaRegMapasrecord(x).num_afra_fram_numb,
               tablaRegMapasrecord(x).num_afra_chan_addr,
               tablaRegMapasrecord(x).num_afra_heig,
               tablaRegMapasrecord(x).num_afra_widt,
               tablaRegMapasrecord(x).num_afra_mach_addr,
               tablaRegMapasrecord(x).num_afra_leve_numb
             );

           END LOOP;

         END IF;

       EXIT WHEN MAPACD%NOTFOUND;
     END LOOP;
   CLOSE MAPACD;

   -- Se registra los Anaqueles Destino en los anaqueles expandidos del Nuevo Mapa CD
   ape_pr_regis_mapa_cd(p_nuevo);

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_MAPA_CD_DETAL: '||ls_sqlerrm);
 END APE_PR_GENER_MAPA_CD_DETAL;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APP_TIPO_CAJA_PRODU
     Fecha Creacion    : 09/07/2010
     Parametros Entrada :
            Oid Pais
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_CAJA (
   p_oid_pais     IN  VARCHAR2,
   p_oid_caja     IN  VARCHAR2,
   p_val_error    OUT VARCHAR2,
   p_nom_tabla    OUT VARCHAR2
 )
 IS
   vn_oid_pais    NUMBER;
   vn_oid_caja    NUMBER;
   vn_contador    NUMBER;

 BEGIN

   vn_oid_pais := to_number(p_oid_pais);
   vn_oid_caja := to_number(p_oid_caja);
   vn_contador := 0;
   p_val_error := '0';
   p_nom_tabla := '';

   -- Validando en la tabla ape_produ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_produ a
      WHERE a.ticp_oid_tipo_caja_prod = vn_oid_caja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_PRODU';
     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla ape_tipo_caja_embal
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_tipo_caja_embal a
      WHERE a.ticp_oid_tipo_caja_prod = vn_oid_caja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_TIPO_CAJA_EMBAL';
     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla ape_tipo_caja_subli
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_tipo_caja_subli a
      WHERE a.ticp_oid_tipo_caja_prod = vn_oid_caja;

     IF (vn_contador > 0) THEN

       p_val_error := '1';
       p_nom_tabla := 'APE_TIPO_CAJA_SUBLI';

     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla mae_produ
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM mae_produ a
      WHERE a.ticp_oid_tipo_caja_prod = vn_oid_caja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'MAE_PRODU';
     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_TIPO_CAJA: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_TIPO_CAJA;

/********************************************************************************
     Descripcion       : Generar un nuevo registro Orden de Anaqueles, a traves
                         de una copia de un registro Orden Anaquel elegido.
     Autor             : Nicolas Lopez
     Fecha Creacion    : 12/07/2010
     Parametros Entrada :
            p_oid_mapa_zona_orig
            p_oid_ordenanaq_orig
            p_desc_orden_anaq
            p_oid_mapa_zona_dest
            p_oid_idiomaISO
*********************************************************************************/
 PROCEDURE APE_PR_GENER_ORD_ANAQUEL_DETAL (
   p_oid_mapa_zona_orig    VARCHAR2,
   p_oid_ordenanaq_orig    VARCHAR2,
   p_desc_orden_anaq       VARCHAR2,
   p_oid_mapa_zona_dest    VARCHAR2,
   p_oid_idiomaISO         gen_i18n_sicc_pais.idio_oid_idio %TYPE
 )
 IS
   p_num_atri              gen_atrib_tradu.num_atri %TYPE:=0;
   p_nuevo                 ape_orden_anaqu_cabec.oid_anaq_cabe %TYPE;
   p_cod_mapa_orde         ape_orden_anaqu_cabec.cod_mapa_orde %TYPE;
   W_FILAS                 NUMBER := 1000;
   p_oid_anaq_cabe_ante    ape_orden_anaqu_cabec.oid_anaq_cabe %TYPE := NULL;
   p_oid_anaq_cabe         ape_orden_anaqu_cabec.oid_anaq_cabe %TYPE;

   TYPE tmptablaOrdenAnaquel IS RECORD(
     oid_anaq_cabe                   ape_orden_anaqu_cabec.oid_anaq_cabe %TYPE,
     val_defe                        ape_orden_anaqu_cabec.val_defe %TYPE,
     num_orde                        ape_orden_anaqu_detal.num_orde %TYPE,
     liar_oid_line_arma              ape_orden_anaqu_cabec.liar_oid_line_arma %TYPE,
     mcdd_oid_mapa_cent_dist_deta    ape_orden_anaqu_detal.mcdd_oid_mapa_cent_dist_deta %TYPE
   );

   TYPE tablaRegOrdenAnaquel IS TABLE OF tmptablaOrdenAnaquel;
   tablaRegOrdenAnaqrecord tablaRegOrdenAnaquel;

   -- Se obtienen los datos de Orden Anaqueles Origen de acuerdo a los criterios de Oid Mapa Zona Origen y Oid Anaquel origen
   CURSOR ORDEN_ANAQUEL(p_oid_mapazonaorigen NUMBER, p_oid_anaq_cabe0 NUMBER) IS
     SELECT ordanaqcab.oid_anaq_cabe,
            0 val_defe,
            ordanaqdet.num_orde,
            ordanaqcab.liar_oid_line_arma,
            ordanaqdet.mcdd_oid_mapa_cent_dist_deta
       FROM ape_orden_anaqu_cabec ordanaqcab,
            ape_orden_anaqu_detal ordanaqdet
      WHERE ordanaqcab.oid_anaq_cabe = ordanaqdet.oaca_oid_anaq_cabe
        AND ordanaqcab.mzca_oid_mapa_zona_cabe = p_oid_mapazonaorigen
        AND ordanaqcab.oid_anaq_cabe = p_oid_anaq_cabe0;

 BEGIN

   OPEN ORDEN_ANAQUEL(p_oid_mapa_zona_orig ,p_oid_ordenanaq_orig);
     LOOP
       FETCH ORDEN_ANAQUEL BULK COLLECT INTO tablaRegOrdenAnaqrecord LIMIT W_FILAS;

         IF tablaRegOrdenAnaqrecord.COUNT > 0 THEN
           FOR x IN tablaRegOrdenAnaqrecord.FIRST .. tablaRegOrdenAnaqrecord.LAST LOOP

             p_oid_anaq_cabe:=tablaRegOrdenAnaqrecord(x).oid_anaq_cabe;

             IF ( p_oid_anaq_cabe_ante IS NULL OR p_oid_anaq_cabe_ante <> p_oid_anaq_cabe ) THEN
               -- Se obtiene el correlativo de la tabla APE_ORDEN_ANAQU_CABEC
               SELECT ape_oaca_seq.NEXTVAL
                 INTO p_nuevo
                 FROM dual;

               -- Se Captura la nueva secuencia para Cod Mapa Orden Anaquel
               SELECT NVL(MAX(cod_mapa_orde),0)+1
                 INTO p_cod_mapa_orde
                 FROM ape_orden_anaqu_cabec;

               -- Se crea el nuevo Orden de Anaquel destino
               INSERT INTO ape_orden_anaqu_cabec (
                 oid_anaq_cabe,
                 cod_mapa_orde,
                 mzca_oid_mapa_zona_cabe,
                 val_defe,
                 liar_oid_line_arma
               )
               VALUES (
                 p_nuevo,
                 p_cod_mapa_orde,
                 p_oid_mapa_zona_dest,
                 tablaRegOrdenAnaqrecord(x).val_defe,
                 tablaRegOrdenAnaqrecord(x).liar_oid_line_arma
               );

               SELECT num_atri
                 INTO p_num_atri
                 FROM gen_atrib_tradu
                WHERE nom_enti = 'APE_ORDEN_ANAQU_CABEC';

               INSERT INTO gen_i18n_sicc_pais (
                 oid_i18n,
                 attr_enti,
                 attr_num_atri,
                 idio_oid_idio,
                 val_i18n,
                 val_oid
               )
               VALUES (
                 gen_i18n_seq.nextval,
                 'APE_ORDEN_ANAQU_CABEC',
                 p_num_atri,
                 p_oid_idiomaISO,
                 p_desc_orden_anaq,
                 p_nuevo
               );

               p_oid_anaq_cabe_ante := p_oid_anaq_cabe;

             END IF;

             -- Se copia el detalle de Orden de Anaquel origen al nuevo destino
             INSERT INTO ape_orden_anaqu_detal (
               oid_orde_anaq_deta,
               num_orde,
               oaca_oid_anaq_cabe,
               mcdd_oid_mapa_cent_dist_deta
             )
             VALUES (
               ape_oacd_seq.nextval,
               tablaRegOrdenAnaqrecord(x).num_orde,
               p_nuevo,
               tablaRegOrdenAnaqrecord(x).mcdd_oid_mapa_cent_dist_deta
             );

           END LOOP;
         END IF;

       EXIT WHEN ORDEN_ANAQUEL%NOTFOUND;
     END LOOP;
   CLOSE ORDEN_ANAQUEL;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_ORD_ANAQUEL_DETAL: '||ls_sqlerrm);
 END APE_PR_GENER_ORD_ANAQUEL_DETAL;

/*******************************************************************************
     Descripcion       : Inserta en la tabla detalle de Asignacion de Productos
                         Anaquel
     Fecha Creacion    : 23/07/2010
********************************************************************************/
 PROCEDURE APE_PR_INSER_ASIGN_DETAL (
  	p_oid_mapa_cent    IN VARCHAR2,
    p_oid_mapa_zona    IN VARCHAR2,
    p_oid_line_arma    IN VARCHAR2,
    p_oid_asig_cabe    IN VARCHAR2,
    p_oid_proc_esti		 IN VARCHAR2
 )
 IS
   CURSOR c_asignacion IS
     SELECT DISTINCT mcd.oid_mapa_cent_dist_deta
       FROM ape_mapa_centr_distr_cabec mcc,
            ape_mapa_centr_distr_detal mcd,
            ape_mapa_zona_cabec mzc,
            ape_mapa_zona_detal mzd,
            ape_subli_armad sla
      WHERE mzc.oid_mapa_zona_cabe = to_number(p_oid_mapa_zona)
        AND sla.liar_oid_line_arma = to_number(p_oid_line_arma)
        AND mcc.oid_mapa_cent_dist_cabe = to_number(p_oid_mapa_cent)
        AND mcc.oid_mapa_cent_dist_cabe = mcd.mcdc_oid_mapa_cent_dist_cabe
        AND mzc.oid_mapa_zona_cabe = mzd.mzca_oid_mapa_zona_cabe
        AND sla.oid_subl_arma = mzd.sbar_oid_subl_arma
        AND sla.oid_subl_arma = mcd.sbar_oid_subl_arma
      ORDER BY 1;

   vn_oid_mapa_deta    NUMBER;

 BEGIN

   OPEN c_asignacion;
     LOOP
       FETCH c_asignacion INTO vn_oid_mapa_deta;
       EXIT WHEN (c_asignacion%NOTFOUND);

       INSERT INTO ape_asign_produ_anaqu_detal (
         oid_asig_prod_anaq,
         num_unida,
         ind_sigu_asig,
         ind_asig_petl,
         apac_oid_asig_prod_anaq_cabe,
         prod_oid_prod,
         mcdd_oid_mapa_cent_dist_deta,
         prce_oid_proc,
         num_afra_lane_numb,
         num_afra_lane_qtty
        )
        VALUES(
         APE_APAN_SEQ.NEXTVAL,
         0,
         NULL,
         NULL,
         to_number(p_oid_asig_cabe),
         NULL,
         vn_oid_mapa_deta,
         to_number(p_oid_proc_esti),
         NULL,
         NULL
        );

     END LOOP;
   CLOSE c_asignacion;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_INSER_ASIGN_DETAL: '||ls_sqlerrm);
 END APE_PR_INSER_ASIGN_DETAL;

/**************************************************************************************
     Descripcion       : Registra los Tipos de Anaqueles en la tabla APE_MAPA_CENTR_DISTR_DETAL
     Fecha Creacion    : 27/07/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
       p_oid_sistpicad   Contiene el OidSistPicad para identificar el tipo AFRAME
     Parametros Salida : Ninguno
****************************************************************************************/
 PROCEDURE APE_PR_REGIS_ANAQ_MAPACDDET (
   p_oid_sistpicad    IN VARCHAR2
 )
 IS
   CURSOR c_tipos_anaqu IS
     SELECT oid_mapa_cent_dist_deta,
            oid_tipo_anaq,
            val_side,
            num_afra_fram_numb,
            num_afra_chan_addr,
            num_afra_mach_addr,
            num_afra_leve_numb,
            num_afra_heig,
            num_afra_widt
       FROM ape_gtt_tipo_anaqu;

   TYPE tipoAnaqueles IS RECORD(
     oidMapaCentDistDeta    ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta%TYPE,
     oidTipoAnaq            ape_tipo_anaqu.oid_tipo_anaq%TYPE,
     valSide                ape_mapa_centr_distr_detal.val_side%TYPE,
     numAfraFramNumb        ape_mapa_centr_distr_detal.num_afra_fram_numb%TYPE,
     numAfraChanAddr        ape_mapa_centr_distr_detal.num_afra_chan_addr%TYPE,
     numAfraMachAddr        ape_mapa_centr_distr_detal.num_afra_mach_addr%TYPE,
     numAfraLeveNumb        ape_mapa_centr_distr_detal.num_afra_leve_numb%TYPE,
     numAfraHeig            ape_mapa_centr_distr_detal.num_afra_heig%TYPE,
     numAfraWidt            ape_mapa_centr_distr_detal.num_afra_widt%TYPE
   );

   TYPE tipoAnaquelesRec IS TABLE OF tipoAnaqueles;
   tipoAnaquelesrecord tipoAnaquelesRec;

   W_FILAS    NUMBER := 10000;

 BEGIN

   OPEN c_tipos_anaqu;
     LOOP
       FETCH c_tipos_anaqu BULK COLLECT INTO tipoAnaquelesrecord LIMIT W_FILAS;

         IF tipoAnaquelesrecord.COUNT > 0 THEN

           FOR x IN tipoAnaquelesrecord.FIRST .. tipoAnaquelesrecord.LAST LOOP
             -- Se graban los cambios en los Tipos Anaqueles en la tabla APE_MAPA_CENTR_DISTR_DETAL
             IF (p_oid_sistpicad = '3') THEN
               UPDATE ape_mapa_centr_distr_detal
                  SET tian_oid_tipo_anaq = tipoAnaquelesrecord(x).oidTipoAnaq,
                    	val_side           = tipoAnaquelesrecord(x).valSide,
                    	num_afra_fram_numb = tipoAnaquelesrecord(x).numAfraFramNumb,
                    	num_afra_chan_addr = tipoAnaquelesrecord(x).numAfraChanAddr,
                    	num_afra_mach_addr = tipoAnaquelesrecord(x).numAfraMachAddr,
                    	num_afra_leve_numb = tipoAnaquelesrecord(x).numAfraLeveNumb,
                    	num_afra_heig      = tipoAnaquelesrecord(x).numAfraHeig,
                    	num_afra_widt      = tipoAnaquelesrecord(x).numAfraWidt
                WHERE oid_mapa_cent_dist_deta = tipoAnaquelesrecord(x).oidMapaCentDistDeta;
             ELSE
               UPDATE ape_mapa_centr_distr_detal
                  SET tian_oid_tipo_anaq = tipoAnaquelesrecord(x).oidTipoAnaq
                WHERE oid_mapa_cent_dist_deta = tipoAnaquelesrecord(x).oidMapaCentDistDeta;
             END IF;

           END LOOP;

         END IF;

       EXIT WHEN c_tipos_anaqu%NOTFOUND;
     END LOOP;
   CLOSE c_tipos_anaqu;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_REGIS_ANAQ_MAPACDDET: '||ls_sqlerrm);
 END APE_PR_REGIS_ANAQ_MAPACDDET;

/************************************************************************************
     Descripcion       : Registra los Anaqueles Destinos en los Anaqueles Expandidos
                         en el Nuevo Mapa Centro de Distribucion , se utiliza la tabla
                         APE_MAPA_CENTR_DISTR_DETAL
     Fecha Creacion    : 03/08/2010
     Autor             : Nicolas Lopez (nlopez@csigcomt.com)
     Parametros Entrada :
       p_oid_mapa_cent_dist   Oid Mapa CD que corresponde al Nuevo Mapa CD
     Parametros Salida : Ninguno
**************************************************************************************/
 PROCEDURE APE_PR_REGIS_MAPA_CD (
   p_oid_mapa_cent_dist    VARCHAR2
 )
 IS
   p_oid_mapa_cent_dist_deta         ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE := NULL;
   p_newoid_mapa_cent_dist_deta      ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE := NULL;
   p_mcdd_oid_mapa_cent_dist_deta    ape_mapa_centr_distr_detal.mcdd_oid_mapa_cent_dist_deta %TYPE := NULL;
   p_num_anaquel                     ape_mapa_centr_distr_detal.num_anaq %TYPE := NULL;
   W_FILAS                           NUMBER:= 1000;

   TYPE tmptablaNuevoMapa IS RECORD(
     oid_mapa_cent_dist_deta         ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE,
     mcdd_oid_mapa_cent_dist_deta    ape_mapa_centr_distr_detal.mcdd_oid_mapa_cent_dist_deta %TYPE
   );

   TYPE tablaRegNuevoMapa IS TABLE OF tmptablaNuevoMapa;
   tablaRegNewMaparecord tablaRegNuevoMapa;

   -- Se obtiene la informacion de la Tabla APE_MAPA_CENTR_DISTR_DETAL DEL NUEVO MAPA CD
   CURSOR NUEVOMAPACD(p_oid_mapa_cent_dist NUMBER) IS
     SELECT oid_mapa_cent_dist_deta,
            mcdd_oid_mapa_cent_dist_deta
       FROM ape_mapa_centr_distr_detal hh
      WHERE hh.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent_dist --- Corresponde al nuevo Oid Mapa CD
        AND hh.mcdd_oid_mapa_cent_dist_deta IS NOT NULL;

 BEGIN

   OPEN NUEVOMAPACD(p_oid_mapa_cent_dist);
     LOOP
       FETCH NUEVOMAPACD BULK COLLECT INTO tablaRegNewMaparecord LIMIT W_FILAS;

         IF tablaRegNewMaparecord.COUNT > 0 THEN

           FOR x IN tablaRegNewMaparecord.FIRST .. tablaRegNewMaparecord.LAST LOOP

             p_oid_mapa_cent_dist_deta := tablaRegNewMaparecord(x).oid_mapa_cent_dist_deta;
             p_mcdd_oid_mapa_cent_dist_deta := tablaRegNewMaparecord(x).mcdd_oid_mapa_cent_dist_deta;

             -- Se realiza la busqueda del Numero de Anaquel Destino en el Mapa CD Origen
             SELECT num_anaq
               INTO p_num_anaquel
               FROM ape_mapa_centr_distr_detal
              WHERE oid_mapa_cent_dist_deta = p_mcdd_oid_mapa_cent_dist_deta;  ---A01A1  -- BUSCAMOS EN EL ANTIGUO MAPA CD

             -- Se busca el oidMapaCDDetalle correcto segun el Numero de Anaquel de la busqueda anterior
             SELECT oid_mapa_cent_dist_deta
               INTO p_newoid_mapa_cent_dist_deta
               FROM ape_mapa_centr_distr_detal
              WHERE num_anaq = p_num_anaquel
                AND mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent_dist;

             -- Se actualiza el anaquel origen expandido con el oidMapaCDDetalle correcto
             UPDATE ape_mapa_centr_distr_detal
                SET mcdd_oid_mapa_cent_dist_deta = p_newoid_mapa_cent_dist_deta
              WHERE oid_mapa_cent_dist_deta = p_oid_mapa_cent_dist_deta;

           END LOOP;

         END IF;

       EXIT WHEN NUEVOMAPACD%NOTFOUND;
     END LOOP;
   CLOSE NUEVOMAPACD;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_REGIS_MAPA_CD: '||ls_sqlerrm);
 END APE_PR_REGIS_MAPA_CD;

/******************************************************************************************
     Descripcion       : Realiza la validaciones antes de asignar un producto a un anaquel
     Fecha Creacion    : 05/08/2010
*******************************************************************************************/
 PROCEDURE APE_PR_VALID_PRODU_ANAQU (
   p_oid_pais                IN  VARCHAR2,
   p_oid_sap                 IN  VARCHAR2,
   p_oid_perio               IN  VARCHAR2,
   p_num_anaqu               IN  VARCHAR2,
   p_oid_subli               IN  VARCHAR2,
   p_oid_mapa_centr_cabec    IN  VARCHAR2,
   p_oid_mapa_centr_detal    IN  VARCHAR2,
   p_oid_asig_produ_cabec    IN  VARCHAR2,
   p_ind_intercambio         IN  VARCHAR2,
   p_val_error               OUT VARCHAR2
 )
 IS
   vnOidPais         NUMBER;
   vnOidSAP          NUMBER;
   vnOidPeriodo      NUMBER;
   vnOidSubLinea     NUMBER;
   vnOidMapaCab      NUMBER;
   vnOidMapaDet      NUMBER;
   vnOidAsigCab      NUMBER;
   vnContadorVal1    NUMBER;
   vnContadorVal2    NUMBER;
   vnContadorVal3    NUMBER;
   vnContadorVal4    NUMBER;
   vnContadorVal5    NUMBER;
   vnMaxAnaqProd     NUMBER;
   vnFunDist         NUMBER;
   vnSublineaAux     NUMBER;

 BEGIN

   vnOidPais     := to_number(p_oid_pais);
   vnOidSAP      := to_number(p_oid_sap);
   vnOidPeriodo  := to_number(p_oid_perio);
   vnOidSubLinea := to_number(p_oid_subli);
   vnOidMapaCab  := to_number(p_oid_mapa_centr_cabec);
   vnOidMapaDet  := to_number(p_oid_mapa_centr_detal);
   vnOidAsigCab  := to_number(p_oid_asig_produ_cabec);

   p_val_error    := '0';
   vnContadorVal1 := 0;
   vnContadorVal2 := 0;
   vnContadorVal3 := 0;
   vnContadorVal4 := 0;
   vnContadorVal5 := 0;

   -- Para el caso de Intercambio de Producto Anaquel no se realizan estas validaciones
   IF ( p_ind_intercambio = 'N') THEN
     -- Se valida que el producto exista en los estimados de la campa?a
     IF (p_val_error = '0') THEN

       SELECT COUNT(1)
         INTO vnContadorVal1
         FROM mae_produ mp,
              ape_estim_produ ep
        WHERE mp.oid_prod = vnOidSAP
          AND mp.pais_oid_pais = vnOidPais
          AND ep.perd_oid_peri = vnOidPeriodo
          AND mp.oid_prod = ep.prod_oid_prod;

       IF (vnContadorVal1 = 0) THEN
         p_val_error := '1';
       END IF;

     END IF;

     -- Se valida el indicador de Fuera de Pedido
     IF (p_val_error = '0') THEN

       SELECT COUNT(1)
         INTO vnContadorVal3
         FROM ape_mapa_centr_distr_detal mcd,
              ape_subli_armad sla,
              ape_linea_armad la,
              ape_estim_produ ep,
              mae_produ mp
        WHERE mcd.oid_mapa_cent_dist_deta = p_oid_mapa_centr_detal
          AND mcd.num_anaq = p_num_anaqu
          AND mp.oid_prod = vnOidSAP
          AND mp.pais_oid_pais = vnOidPais
          AND ep.perd_oid_peri = vnOidPeriodo
          AND mcd.sbar_oid_subl_arma = sla.oid_subl_arma
          AND sla.liar_oid_line_arma = la.oid_line_arma
          AND ep.prod_oid_prod = mp.oid_prod
          AND ((la.num_line_afp = '1' AND mp.cod_ind_dent_caja IN ('F','B'))
            OR (la.num_line_afp = '0' AND (mp.cod_ind_dent_caja NOT IN ('F','B') OR mp.cod_ind_dent_caja IS NULL)));

       IF (vnContadorVal3 = 0) THEN
         p_val_error := '3';
       END IF;

     END IF;

     SELECT nvl(sa.num_maxi_anaq_prod,0)
       INTO vnMaxAnaqProd
       FROM ape_subli_armad sa
      WHERE sa.oid_subl_arma = vnOidSubLinea;

     -- Se valida la asignacion a mas de un Anaquel no sea mayor al numero permitido
     IF (p_val_error = '0') THEN

        IF (vnMaxAnaqProd != 0) THEN

          SELECT COUNT(1)
            INTO vnContadorVal4
            FROM ape_mapa_centr_distr_cabec mcdc,
                 ape_mapa_centr_distr_detal mcdd,
                 ape_asign_produ_anaqu_detal apad,
                 ape_asign_produ_anaqu_cabec apac
           WHERE apad.prod_oid_prod = vnOidSAP
             AND apad.prod_oid_prod IS NOT NULL
             AND mcdd.mcdc_oid_mapa_cent_dist_cabe = vnOidMapaCab
             AND mcdd.sbar_oid_subl_arma = vnOidSubLinea
             AND apac.oid_asig_prod_anaq_cabe = vnOidAsigCab
             AND apac.perd_oid_peri = vnOidPeriodo
             AND mcdc.oid_mapa_cent_dist_cabe = mcdd.mcdc_oid_mapa_cent_dist_cabe
             AND mcdc.oid_mapa_cent_dist_cabe = apac.mcdc_oid_mapa_cent_dist_cabe
             AND mcdd.oid_mapa_cent_dist_deta = apad.mcdd_oid_mapa_cent_dist_deta
             AND apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe;

          IF (vnContadorVal4 >= vnMaxAnaqProd) THEN
            p_val_error := '4';
          END IF;

        END IF;

     END IF;

   END IF;

   -- Se valida si la Sublinea Tiene funcion de Distribucion 'Tipo de Anaquel' (oid = 2 de la tabla de APE_FUNCI_DISTR),
   -- si lo tiene el tipo asignado al anaquel debe ser igual a por lo menos uno de los tipos asignado al producto en la
   -- tabla de APE_PRODU_ANAQU
   SELECT nvl(sa.fndi_oid_func_dist,0)
     INTO vnFunDist
     FROM ape_subli_armad sa
    WHERE sa.oid_subl_arma = vnOidSubLinea;

   IF ( vnFunDist != 1 )THEN

     IF (p_val_error = '0') THEN

       SELECT COUNT(1)
         INTO vnContadorVal2
         FROM ape_subli_armad sa,
              ape_mapa_centr_distr_detal mcdd
        WHERE mcdd.oid_mapa_cent_dist_deta = vnOidMapaDet
          AND sa.oid_subl_arma = vnOidSubLinea
          AND sa.fndi_oid_func_dist = vnFunDist
          AND sa.oid_subl_arma = mcdd.sbar_oid_subl_arma
          AND mcdd.tian_oid_tipo_anaq IN ( SELECT pa.tian_oid_tipo_anaq
                                             FROM ape_produ_anaqu pa
                                            WHERE pa.prod_oid_prod = vnOidSAP );

       IF (vnContadorVal2 = 0) THEN
         p_val_error := '2';
       END IF;

     END IF;

   END IF;

   -- Se valida que los anaqueles pertenescan a una misma sublinea
   IF(p_val_error = '0')THEN

     IF ( vnMaxAnaqProd > 1) THEN
       -- Se verifiaca si el articulo ya tiene asigancion para el periodo
       SELECT COUNT(mcdd.sbar_oid_subl_arma)
         INTO vnContadorVal5
         FROM ape_mapa_centr_distr_cabec mcdc,
              ape_mapa_centr_distr_detal mcdd,
              ape_asign_produ_anaqu_detal apad,
              ape_asign_produ_anaqu_cabec apac
        WHERE mcdc.oid_mapa_cent_dist_cabe = vnOidMapaCab
          AND apad.prod_oid_prod = vnOidSAP
          AND apac.perd_oid_peri = vnOidPeriodo
          AND apac.oid_asig_prod_anaq_cabe = vnOidAsigCab
          AND mcdc.oid_mapa_cent_dist_cabe = mcdd.mcdc_oid_mapa_cent_dist_cabe
          AND mcdc.oid_mapa_cent_dist_cabe = apac.mcdc_oid_mapa_cent_dist_cabe
          AND mcdd.oid_mapa_cent_dist_deta = apad.mcdd_oid_mapa_cent_dist_deta
          AND apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe;

       IF (vnContadorVal5 > 0) THEN

         SELECT DISTINCT mcdd.sbar_oid_subl_arma
           INTO vnSublineaAux
           FROM ape_mapa_centr_distr_cabec mcdc,
                ape_mapa_centr_distr_detal mcdd,
                ape_asign_produ_anaqu_detal apad,
                ape_asign_produ_anaqu_cabec apac
          WHERE mcdc.oid_mapa_cent_dist_cabe = vnOidMapaCab
            AND apad.prod_oid_prod = vnOidSAP
            AND apac.perd_oid_peri = vnOidPeriodo
            AND apac.oid_asig_prod_anaq_cabe = vnOidAsigCab
            AND mcdc.oid_mapa_cent_dist_cabe = mcdd.mcdc_oid_mapa_cent_dist_cabe
            AND mcdc.oid_mapa_cent_dist_cabe = apac.mcdc_oid_mapa_cent_dist_cabe
            AND mcdd.oid_mapa_cent_dist_deta = apad.mcdd_oid_mapa_cent_dist_deta
            AND apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe;

         -- SE verifiaca si la sublinea pasada como parametro es igual al de la asignacion
         IF ( vnOidSubLinea != vnSublineaAux ) THEN
           p_val_error := '5';
         END IF;

       END IF;

     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_VALID_PRODU_ANAQU: '||ls_sqlerrm);
 END APE_PR_VALID_PRODU_ANAQU;

/********************************************************************************
     Descripcion       : Realiza el intercambio de los productos de un anaquel a
                         otro
     Fecha Creacion    : 05/08/2010
*********************************************************************************/
 PROCEDURE APE_PR_INTER_PRODU_ANAQU (
   p_oid_pais                IN  VARCHAR2,
   p_oid_sap                 IN  VARCHAR2,
   p_oid_perio               IN  VARCHAR2,
   p_num_anaqu_orige         IN  VARCHAR2,
   p_num_anaqu_desti         IN  VARCHAR2,
   p_oid_subli               IN  VARCHAR2,
   p_oid_mapa_centr_detal    IN  VARCHAR2,
   p_oid_mapa_centr_cabec    IN  VARCHAR2,
   p_oid_asig_produ_detal    IN  VARCHAR2,
   p_oid_asig_produ_cabec    IN  VARCHAR2,
   p_ind_intercambio         IN  VARCHAR2,
   p_val_error               OUT VARCHAR2
 )
 IS
   vnOidSAPOrigen      NUMBER;
   vnOidSAPDestino     NUMBER;
   vnOidMapaCab        NUMBER;
   vnOidAsigDetOrig    NUMBER;
   vnOidAsigDetDest    NUMBER;
   vnContProdAnaq      NUMBER;

 BEGIN

   vnOidSAPOrigen   := to_number(p_oid_sap);
   vnOidMapaCab     := to_number(p_oid_mapa_centr_cabec);
   vnOidAsigDetOrig := to_number(p_oid_asig_produ_detal);

   p_val_error := '0';
   vnContProdAnaq := 0;

   -- Se valida que el anaquel destino tenga un producto asignado
   SELECT COUNT(apad.prod_oid_prod)
     INTO vnContProdAnaq
     FROM ape_mapa_centr_distr_detal mcdd,
          ape_asign_produ_anaqu_detal apad
    WHERE mcdd.num_anaq = p_num_anaqu_desti
      AND mcdd.mcdc_oid_mapa_cent_dist_cabe = vnOidMapaCab
      AND apad.apac_oid_asig_prod_anaq_cabe = p_oid_asig_produ_cabec
      AND apad.prod_oid_prod IS NOT NULL
      AND mcdd.oid_mapa_cent_dist_deta = apad.mcdd_oid_mapa_cent_dist_deta;

   IF ( vnContProdAnaq = 0) THEN
     p_val_error := '6';
   ELSE
     -- Obteniendo el Oid Producto del Anaquel Destino, y el oid de la asignaciondetalle Destino
     SELECT apad.prod_oid_prod,
            apad.oid_asig_prod_anaq
       INTO vnOidSAPDestino,
            vnOidAsigDetDest
       FROM ape_mapa_centr_distr_detal mcdd,
            ape_asign_produ_anaqu_detal apad
      WHERE mcdd.num_anaq = p_num_anaqu_desti
        AND mcdd.mcdc_oid_mapa_cent_dist_cabe = vnOidMapaCab
        AND apad.apac_oid_asig_prod_anaq_cabe = p_oid_asig_produ_cabec
        AND apad.prod_oid_prod IS NOT NULL
        AND mcdd.oid_mapa_cent_dist_deta = apad.mcdd_oid_mapa_cent_dist_deta
        AND ROWNUM = 1;

     -- Se llama al procedure para realizar la validaciones para ambos casos
     APE_PR_VALID_PRODU_ANAQU( p_oid_pais, to_char(vnOidSAPDestino), p_oid_perio, p_num_anaqu_orige, p_oid_subli,
                               p_oid_mapa_centr_cabec, p_oid_mapa_centr_detal, p_oid_asig_produ_cabec,
                               p_ind_intercambio,p_val_error);

     IF (  p_val_error = '0' )THEN
       APE_PR_VALID_PRODU_ANAQU( p_oid_pais, to_char(p_oid_sap), p_oid_perio, p_num_anaqu_desti, p_oid_subli,
                                 p_oid_mapa_centr_cabec, p_oid_mapa_centr_detal, p_oid_asig_produ_cabec,
                                 p_ind_intercambio,p_val_error);
     END IF;

     -- Si no existen errores se realiza el intercambio
     IF (p_val_error = '0' )THEN

       -- Actualizando en el Anaquel Origen el oid Producto destino
       UPDATE ape_asign_produ_anaqu_detal
          SET prod_oid_prod = vnOidSAPDestino
        WHERE oid_asig_prod_anaq = vnOidAsigDetOrig;

       -- Actualizando en el Anaquel Destino el oid Producto destino
       UPDATE ape_asign_produ_anaqu_detal
          SET prod_oid_prod = vnOidSAPOrigen
        WHERE oid_asig_prod_anaq = vnOidAsigDetDest;

      END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_INTER_PRODU_ANAQU: '||ls_sqlerrm);
 END APE_PR_INTER_PRODU_ANAQU;

/******************************************************************************
     Descripcion       : Realiza la generacion de los estimado de los porducto
                         para la campa?a ingresada
     Fecha Creacion    : 09/08/2010
*******************************************************************************/
 PROCEDURE APE_PR_GENER_ESTIM_PRODU (
   p_oid_perio          IN VARCHAR2,
   p_oid_linea          IN VARCHAR2,
   p_ind_borra_manua    IN VARCHAR2
 )
 IS
   v_oidPeiodoAct      NUMBER;
   v_oidPeriodoAnt     NUMBER;
   v_oidLinea          NUMBER;
   v_oidProcedencia    NUMBER;
   v_indAFP            NUMBER;
   W_FILAS             NUMBER := 1000;

   CURSOR c_estimado (v_oidPeiodoAct NUMBER, v_oidPeriodoAnt NUMBER, v_indAFP NUMBER, v_oidLinea NUMBER) IS
     SELECT b.oid_prod  OID_PROD,
            SUM(decode(g.cod_orig,'MAV',nvl((SELECT SUM(val_base_esti_dest * num_unid_clie)
                                               FROM mav_detal_mav
                                              WHERE prod_oid_prod = g.prod_oid_prod
                                                AND tofe_oid_tipo_ofer = g.tofe_oid_tipo_ofer
                                                AND cod_orig = 'MAV'), 0), nvl(g.num_unid_esti,0)))  NUM_UNID_ESTI
       FROM pre_ofert f,
            pre_ofert_detal g,
            pre_matri_factu_cabec h,
            cra_perio i,
            seg_perio_corpo j,
            mae_produ b,
            gen_i18n_sicc_pais c
      WHERE f.oid_ofer = g.ofer_oid_ofer
        AND f.mfca_oid_cabe = h.oid_cabe
        AND h.perd_oid_peri = i.oid_peri
        AND i.peri_oid_peri = j.oid_peri
        AND g.prod_oid_prod = b.oid_prod
        AND b.oid_prod = c.val_oid
        AND c.attr_enti = 'MAE_PRODU'
        AND i.oid_peri = v_oidPeiodoAct
        AND decode(b.cod_ind_dent_caja,'F',1,(decode(b.cod_ind_dent_caja,'B',1,0))) = v_indAFP
        AND NOT EXISTS ( SELECT b2.oid_prod
                           FROM ape_estim_produ a,
                                mae_produ b2,
                                cra_perio d,
                                seg_perio_corpo e
                          WHERE a.prod_oid_prod = b2.oid_prod
                            AND a.perd_oid_peri = d.oid_peri
                            AND d.peri_oid_peri = e.oid_peri
                            AND b2.oid_prod = b.oid_prod
                            AND d.oid_peri = v_oidPeiodoAct
                            AND a.liar_oid_line_arma = v_oidLinea)
     GROUP BY b.oid_prod
     UNION
     SELECT DISTINCT
            p.oid_prod  OID_PROD,
            0 NUM_UNID_ESTI
       FROM inc_concu_param_gener cpg,
            inc_versi_concu vc,
            inc_param_gener_premi pgp,
            inc_param_nivel_premi pnp,
            inc_premi_artic pa,
            inc_lote_premi_artic lpa,
            inc_artic_lote al,
            mae_produ p,
            inc_concu_param_consu cpc,
            cra_perio cp1,
            seg_perio_corpo spc1,
            cra_perio cp2,
            seg_perio_corpo spc2
      WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
        AND cpg.oid_para_gral = vc.copa_oid_para_gral
        AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem(+)
        AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem(+)
        AND pa.oid_prem_arti = lpa.prar_oid_prem_arti(+)
        AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti(+)
        AND al.prod_oid_prod = p.oid_prod
        AND cpg.oid_para_gral = cpc.copa_oid_para_gral(+)
        AND pgp.perd_oid_peri = cp1.oid_peri(+)
        AND cp1.peri_oid_peri = spc1.oid_peri(+)
        AND cpc.perd_oid_peri_inic_eval = cp2.oid_peri(+)
        AND cp2.peri_oid_peri = spc2.oid_peri(+)
        AND ( cp1.oid_peri = v_oidPeiodoAct OR (pgp.perd_oid_peri IS NULL AND cp2.oid_peri IN (v_oidPeiodoAct,v_oidPeriodoAnt)))
        AND ((cpg.ind_acti = 1 AND vc.vico_oid_vige_conc = 1) OR (cpg.ind_acti = 0 AND vc.vico_oid_vige_conc = 6))
        AND  decode(p.cod_ind_dent_caja,'F',1,(decode(p.cod_ind_dent_caja,'B',1,0))) = v_indAFP
        AND NOT EXISTS ( SELECT b2.oid_prod
                           FROM ape_estim_produ a,
                                mae_produ b2,
                                cra_perio d,
                                seg_perio_corpo e
                          WHERE a.prod_oid_prod = b2.oid_prod
                            AND a.perd_oid_peri = d.oid_peri
                            AND d.peri_oid_peri = e.oid_peri
                            AND b2.oid_prod = p.oid_prod
                            AND d.oid_peri = v_oidPeiodoAct
                            AND a.liar_oid_line_arma = v_oidLinea);

   TYPE estimados IS RECORD(
     oid_prod       	mae_produ.oid_prod%TYPE,
     num_unid_esti    ape_estim_produ.num_unid_esti%TYPE
   );

   TYPE estimadosTab  IS TABLE OF estimados;
   estimadosRecord estimadosTab;

 BEGIN

   v_oidPeiodoAct := to_number(p_oid_perio);
   v_oidLinea     := to_number(p_oid_linea);
   -- Se setea el oid de Procedencia de Estimados (2)
   v_oidProcedencia := 2;

   -- Obteniendo el oid del periodo anterior
   v_oidPeriodoAnt := gen_pkg_gener.gen_fn_devuelve_id_nante_campa(v_oidPeiodoAct,1);

   -- Obteniendo el indicador de Fuera de Pedido de la Linea
   SELECT la.num_line_afp
     INTO v_indAFP
     FROM ape_linea_armad la
    WHERE la.oid_line_arma = v_oidLinea;

   -- Si el indicador de borrar los datos introducidos manualmente no esta activo se eliminan
   -- todos los registros para el periodo y linea
   IF (p_ind_borra_manua = 'S') THEN

     DELETE FROM ape_estim_produ
      WHERE liar_oid_line_arma = v_oidLinea
        AND perd_oid_peri = v_oidPeiodoAct
        AND ind_fuer_pedi = v_indAFP;

   ELSE

     DELETE FROM ape_estim_produ
      WHERE liar_oid_line_arma = v_oidLinea
        AND perd_oid_peri = v_oidPeiodoAct
        AND ind_fuer_pedi = v_indAFP
        AND prce_oid_proc <> 1 ;

   END IF;

   OPEN c_estimado (v_oidPeiodoAct, v_oidPeriodoAnt, v_indAFP, v_oidLinea);
     LOOP
       FETCH c_estimado BULK COLLECT INTO estimadosRecord LIMIT W_FILAS;
         IF estimadosRecord.COUNT > 0 THEN

           FOR x IN estimadosRecord.FIRST .. estimadosRecord.LAST LOOP

             INSERT INTO ape_estim_produ (
               oid_esti_prod,
               num_unid_esti,
               ind_fuer_pedi,
               prce_oid_proc,
               liar_oid_line_arma,
               perd_oid_peri,
               prod_oid_prod
             )
             VALUES (
               ape_espr_seq.nextval,
               estimadosRecord(x).num_unid_esti,
               v_indAFP,
               v_oidProcedencia,
               v_oidLinea,
               v_oidPeiodoAct,
               estimadosRecord(x).oid_prod
             );

           END LOOP;

         END IF;
       EXIT WHEN (c_estimado%NOTFOUND);
     END LOOP;
	 CLOSE c_estimado;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'APE_PR_GENER_ESTIM_PRODU: '||ls_sqlerrm);
 END APE_PR_GENER_ESTIM_PRODU;

/**************************************************************************
     Descripcion       : Valida si existe registro en las tablas hijas de
                         la tabla APE_TIPO_CAJA_EMBAL
     Fecha Creacion    : 16/08/2010
     Parametros Entrada :
            Oid Tipo Caja Embalaje
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***************************************************************************/
 PROCEDURE APE_PR_VALID_REGIS_TIPO_CAEMB (
   p_oid_tipocaja    IN NUMBER,
   p_val_error       IN OUT VARCHAR2,
   p_nom_tabla       IN OUT VARCHAR2
 )
 IS
   vn_contador    NUMBER;

 BEGIN

   vn_contador := 0;

   -- Validando en la tabla APE_ETIQU
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_etiqu a
      WHERE a.tcem_oid_tipo_caja_emba = p_oid_tipocaja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_ETIQU';
     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla APE_HISTO_LISTA_PICAD_ERRSF
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_histo_lista_picad_errsf a
      WHERE a.tcem_oid_tipo_caja_emba = p_oid_tipocaja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_HISTO_LISTA_PICAD_ERRSF';
     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla APE_LINEA_TIPO_CAJA_EMBAL
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_linea_tipo_caja_embal a
      WHERE a.tcem_oid_tipo_caja_emba = p_oid_tipocaja;

     IF (vn_contador > 0) THEN
       p_val_error := '1';
       p_nom_tabla := 'APE_LINEA_TIPO_CAJA_EMBAL';
     END IF;

   END IF;

   vn_contador := 0;

   -- Validando en la tabla APE_LISTA_PICAD_DETAL
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vn_contador
       FROM ape_lista_picad_detal a
      WHERE a.tcem_oid_tipo_caja_emba = p_oid_tipocaja;

      IF (vn_contador > 0) THEN
        p_val_error := '1';
        p_nom_tabla := 'APE_LISTA_PICAD_DETAL';
      END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_REGIS_TIPO_CAEMB: '||ls_sqlerrm);
 END APE_PR_VALID_REGIS_TIPO_CAEMB;

/**********************************************************************************
     Descripcion       : Elimina registros de la tabla de Tipos de Caja Embalaje.
                         Primero se valida que no existan registros en las tablas
                         hijas de la tabla APE_TIPO_CAJA_EMBAL
     Fecha Creacion    : 09/06/2010
     Parametros Entrada :
            Oid Tipo Caja Embalaje
     Parametros Salida :
            Valor del Error
            Nombre Tabla Hija
***********************************************************************************/
 PROCEDURE APE_PR_ELIMI_TIPO_CAEMB (
   p_oid_tipocaja    IN  VARCHAR2,
   p_val_error       OUT VARCHAR2,
   p_nom_tabla       OUT VARCHAR2
 )
 IS

 BEGIN

   p_val_error := '0';
   p_nom_tabla := '';

   -- Primero se valida que no existan registros en las tablas hijas
   APE_PR_VALID_REGIS_TIPO_CAEMB(p_oid_tipocaja, p_val_error, p_nom_tabla);

   -- Si no existen registros en las tablas hijas se procede a borrar
   IF (p_val_error = '0') THEN
     -- Se borra de la tabla APE_TIPO_CAJA_EMBAL
     DELETE FROM APE_TIPO_CAJA_EMBAL
      WHERE oid_tipo_caja_emba = TO_NUMBER(p_oid_tipocaja);

     -- Se borra de la tabla de Idiomas
     DELETE FROM gen_i18n_sicc_pais
      WHERE attr_enti = 'APE_TIPO_CAJA_EMBAL'
        AND val_oid =  TO_NUMBER(p_oid_tipocaja);

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_ELIMI_TIPO_CAEMB: '||ls_sqlerrm);
 END APE_PR_ELIMI_TIPO_CAEMB;

/***************************************************************************
     Descripcion       : Genera el numero de OLA en base al consolodados de
                         pedidos.
     Fecha Creacion    : 26/08/2010
****************************************************************************/
 PROCEDURE APE_PR_GENER_OLAS (
   oidpais    IN VARCHAR2
 )
 IS
   vn_oidAgruOlas           NUMBER;
   vn_numPediOlas           NUMBER;
   vn_numOLA                NUMBER(4);
   W_FILAS                  NUMBER := 1000;
   vn_contPedidoOLAFinal    NUMBER;
   vn_oid_centr_distr       NUMBER;
   vb_insertarOla           BOOLEAN := FALSE;
   vn_indice                NUMBER;

   CURSOR c_centr_distr IS
     SELECT ccd.oid_conf_cent_dist,
            nvl(ccd.aafp_oid_aafp_olas,0),
            nvl(ccd.num_mini_pedi_olas,0),
            DECODE(ccd.num_secu_olas,10000,1, ccd.num_secu_olas+1)
       FROM app_confi_centr_distr ccd
      WHERE ccd.pais_oid_pais = oidpais;

   CURSOR c_consolidado(p_oid_centr_distr NUMBER) IS
     SELECT lpc.zorg_oid_regi       OID_REGION,
            lpc.zzon_oid_zona       OID_ZONA,
            lpc.zscc_oid_secc       OID_SECCION,
            lpc.liar_oid_line_arma  OID_LINEA,
            lpc.soca_oid_soli_cabe  OID_LISTA_CONSO,
            psc.fec_fact            FEC_FACTURACION,
            psc.num_lote_fact       NUM_LOTE,
            lpc.oid_list_pica_cabe  LISTA_PICADO
       FROM ped_solic_cabec psc,
            ape_lista_picad_cabec lpc,
            int_lar_tipo_solici_pedido_dis lts,
            app_confi_centr_distr ccd
      WHERE psc.ind_inte_lari_gene = 0
        AND lpc.ccdi_oid_conf_cent_dist = ccd.oid_conf_cent_dist
        AND psc.tspa_oid_tipo_soli_pais = lts.tspa_oid_tipo_soli_pais
        AND psc.oid_soli_cabe = lpc.soca_oid_soli_cabe
        AND lpc.clie_oid_clie IS NOT NULL
        AND lpc.soca_oid_soli_cabe IS NOT NULL
        AND ccd.oid_conf_cent_dist  = p_oid_centr_distr
   ORDER BY lpc.liar_oid_line_arma,
            lpc.num_secu_zona_ruta,
            lpc.zorg_oid_regi,
            lpc.zzon_oid_zona,
            lpc.zscc_oid_secc;

   TYPE consolidado IS RECORD (
     oidRegion         zon_regio.oid_regi%TYPE,
     oidZona           zon_zona.oid_zona%TYPE,
     oidSeccion        zon_regio.oid_regi%TYPE,
     oidLinea          ape_linea_armad.oid_line_arma%TYPE,
     oidListaConso     ape_lista_picad_cabec.soca_oid_soli_cabe%TYPE,
     fecFacturacion    ped_solic_cabec.fec_fact%TYPE,
     numLote           ped_solic_cabec.num_lote_fact%TYPE,
     listaPicado       ape_lista_picad_cabec.oid_list_pica_cabe%TYPE
   );

   TYPE consolidadoTab  IS TABLE OF consolidado;
   consolidadoRecord consolidadoTab;

 BEGIN

   OPEN c_centr_distr;
     LOOP
       FETCH c_centr_distr INTO vn_oid_centr_distr, vn_oidAgruOlas, vn_numPediOlas, vn_numOLA;
       EXIT WHEN (c_centr_distr%NOTFOUND);

       -- La agrupacion de OLAs puede ser:
       -- Seccion = 1; Region = 2; Zona = 3
       vn_contPedidoOLAFinal := 1;

       OPEN c_consolidado(vn_oid_centr_distr);
         LOOP
           FETCH c_consolidado BULK COLLECT INTO consolidadoRecord LIMIT W_FILAS;

             IF consolidadoRecord.COUNT > 0 THEN

               vn_indice := consolidadoRecord.LAST;
               FOR x IN consolidadoRecord.FIRST .. (consolidadoRecord.LAST - 1) LOOP
                 -- Para el caso de Agrupacion por Seccion
                 IF ( vn_oidAgruOlas = 1)  THEN
                   -- Si hay cambio de seccion se inserta la OLA
                   IF (consolidadoRecord(x).oidSeccion <> consolidadoRecord(x+1).oidSeccion ) THEN
                     vb_insertarOla := TRUE;
                   ELSE
                     vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                   END IF;
                 ELSE
                   -- Para el caso de Agrupacion por Region
                   IF (vn_oidAgruOlas = 2) THEN
                     -- Si hay cambio de region se inserta la OLA
                     IF (consolidadoRecord(x).oidRegion <> consolidadoRecord(x+1).oidRegion ) THEN
                       vb_insertarOla := TRUE;
                     ELSE
                       vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                     END IF;
                   ELSE
                     -- Para el caso de Agrupacion por Zona
                     IF (vn_oidAgruOlas = 3) THEN
                       -- Si hay cambio de zona se inserta la OLA
                       IF (consolidadoRecord(x).oidZona <> consolidadoRecord(x+1).oidZona ) THEN
                         vb_insertarOla := TRUE;
                       ELSE
                         vn_contPedidoOLAFinal := vn_contPedidoOLAFinal + 1;
                       END IF;
                     END IF;
                   END IF;
                 END IF;

                 -- El numero de OLA no puede tener mas de cuatro digitos
                 IF ( vn_numOLA > 9999) THEN
                   vn_numOLA := 1;
                 END IF;

                 -- Se actualiza el nuemro de Ola en la tabla de Lista de Picado
                 UPDATE ape_lista_picad_cabec
                    SET num_ola  = vn_numOLA
                  WHERE oid_list_pica_cabe = consolidadoRecord(x).listaPicado;

                 IF ( vb_insertarOla = TRUE ) THEN
                   -- Se elimina el registro para volver a insertarlo con los nuevos datos
                   DELETE FROM ape_olas_xdia WHERE num_ola = vn_numOLA and CCDI_OID_CONF_CENT_DIST = vn_oid_centr_distr;

                   -- Se inserta en la tabla de Olas por Dia
                   INSERT INTO ape_olas_xdia(
                     oid_olas_xdia,
                     num_ola,
                     fec_fact,
                     num_lote_fact,
                     tipo_grup,
                     liar_oid_line_arma,
                     num_pedi,
                     val_esta_ola,
                     ccdi_oid_conf_cent_dist
                   )
                   VALUES(
                     APE_OLXD_SEQ.NEXTVAL,
                     vn_numOLA,
                     consolidadoRecord(x).fecFacturacion,
                     consolidadoRecord(x).numLote,
                     to_char(vn_oidAgruOlas),
                     consolidadoRecord(x).oidLinea,
                     vn_contPedidoOLAFinal,
                     0,
                     vn_oid_centr_distr
                   );

                   IF (vn_numPediOlas = 0) THEN
                     UPDATE ape_lista_picad_cabec
                        SET num_ola  = vn_numOLA
                      WHERE soca_oid_soli_cabe = consolidadoRecord(x).oidListaConso;
                   END IF;

                   vb_insertarOla := FALSE;
                   vn_contPedidoOLAFinal := 1;
                   vn_numOLA := vn_numOLA + 1;
                 END IF;
               END LOOP;

               -- Se inserta la ultima OLA
               -- Se elimina el registro para volver a insertarlo con los nuevos datos
               DELETE FROM ape_olas_xdia WHERE num_ola = vn_numOLA and ccdi_oid_conf_cent_dist = vn_oid_centr_distr;

               -- Se inserta en la tabla de Olas por Dia
               INSERT INTO ape_olas_xdia(
                 oid_olas_xdia,
                 num_ola,
                 fec_fact,
                 num_lote_fact,
                 tipo_grup,
                 liar_oid_line_arma,
                 num_pedi,
                 val_esta_ola,
                 ccdi_oid_conf_cent_dist
               )
               VALUES(
                 APE_OLXD_SEQ.NEXTVAL,
                 vn_numOLA,
                 consolidadoRecord(vn_indice).fecFacturacion,
                 consolidadoRecord(vn_indice).numLote,
                 to_char(vn_oidAgruOlas),
                 consolidadoRecord(vn_indice).oidLinea,
                 vn_contPedidoOLAFinal,
                 0,
                 vn_oid_centr_distr
               );

               -- Se actualiza el nuemro de Ola en la tabla de Lista de Picado
               UPDATE ape_lista_picad_cabec
                  SET num_ola  = vn_numOLA
                WHERE oid_list_pica_cabe = consolidadoRecord(vn_indice).listaPicado;

               --  Se actualiza el nuemro de Ola en la tabla de Centro de distribucion
               UPDATE app_confi_centr_distr
                  SET num_secu_olas  = vn_numOLA
                WHERE oid_conf_cent_dist = vn_oid_centr_distr;

             END IF;

           EXIT WHEN (c_consolidado%NOTFOUND);
         END LOOP;
       CLOSE c_consolidado;

     END LOOP;
   CLOSE c_centr_distr;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_OLAS: '||ls_sqlerrm);
 END APE_PR_GENER_OLAS;

/***************************************************************************
     Descripcion       : Inserta el numero de Ola genera en la tabla de
                         Olas por Dia. Ademas si el numer minimo de perido
                         por CD es 0 se actualiza el campo Numero de Ola de
                         la Lista de Picado
     Fecha Creacion    : 26/08/2010
****************************************************************************/
 PROCEDURE APE_PR_INSER_OLAS_DIA (
   p_num_lote_factu        IN NUMBER,
   p_fec_factu             IN DATE,
   p_num_ola               IN NUMBER,
   p_val_agrup_afp         IN NUMBER,
   p_num_oid_linea         IN NUMBER,
   p_num_mini_pedid_ola    IN NUMBER,
   p_oid_lista_conso       IN NUMBER,
   p_contPedidoOLA         IN NUMBER
 )
 IS
   vn_numOLA    NUMBER;

 BEGIN

   vn_numOLA := p_num_ola;

   -- El numero de OLA no puede tener mas de cuatro digitos
   IF ( vn_numOLA > 9999) THEN
     vn_numOLA := 1;
   END IF;

   -- Se elimina el registro para volver a insertarlo
   -- con los nuevos datos
   DELETE FROM ape_olas_xdia WHERE num_ola = vn_numOLA;

   INSERT INTO ape_olas_xdia(
     oid_olas_xdia,
     num_ola,
     fec_fact,
     num_lote_fact,
     tipo_grup,
     liar_oid_line_arma,
     num_pedi,
     val_esta_ola
   )
   VALUES(
     APE_OLXD_SEQ.NEXTVAL,
     vn_numOLA,
     p_fec_factu,
     p_num_lote_factu,
     to_char(p_val_agrup_afp),
     p_num_oid_linea,
     p_contPedidoOLA,
     0
   );

   IF (p_num_mini_pedid_ola = 0) THEN

     UPDATE ape_lista_picad_cabec
        SET num_ola  = vn_numOLA
      WHERE soca_oid_soli_cabe = p_oid_lista_conso;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_INSER_OLAS_DIA: '||ls_sqlerrm);
 END APE_PR_INSER_OLAS_DIA;

/******************************************************************
     Descripcion       : Devuelve la cantidad de registro por OLA
     Fecha Creacion    : 26/08/2010
*******************************************************************/
 FUNCTION APE_FN_DEVUE_NUMER_PEDID (
   p_val_agrup_afp    IN NUMBER,
   p_fec_factu        IN DATE,
   p_num_oid_linea    IN NUMBER,
   p_oid_region       IN NUMBER,
   p_oid_zona         IN NUMBER,
   p_oid_seccion      IN NUMBER
 ) RETURN NUMBER
 IS
   vn_contPedidoOLA    NUMBER;

 BEGIN

   vn_contPedidoOLA := 0;

   IF (p_val_agrup_afp = 1) THEN

     SELECT COUNT(1)
       INTO vn_contPedidoOLA
       FROM ape_lista_picad_cabec lpc
      WHERE lpc.liar_oid_line_arma = p_num_oid_linea
        AND lpc.zscc_oid_secc = p_oid_seccion
        AND lpc.fec_factu = p_fec_factu;

   ELSE

     IF (p_val_agrup_afp = 2) THEN

       SELECT COUNT(1)
         INTO vn_contPedidoOLA
         FROM ape_lista_picad_cabec lpc
        WHERE lpc.liar_oid_line_arma = p_num_oid_linea
          AND lpc.zorg_oid_regi = p_oid_region
          AND lpc.fec_factu = p_fec_factu;

     ELSE

       SELECT COUNT(1)
         INTO vn_contPedidoOLA
         FROM ape_lista_picad_cabec lpc
        WHERE lpc.liar_oid_line_arma = p_num_oid_linea
          AND lpc.zzon_oid_zona = p_oid_zona
          AND lpc.fec_factu = p_fec_factu;

     END IF;

   END IF;

   RETURN vn_contPedidoOLA;

 EXCEPTION
   WHEN OTHERS THEN
     RETURN 0;
 END APE_FN_DEVUE_NUMER_PEDID;

 /*******************************************************************************
     Descripcion       : Generar una copia de un mapa de asignacion de productos
                         a anaqueles asignando una nueva version.
     Autor             : Nicolas Lopez
     Fecha Creacion    : 16/09/2010
     Parametros Entrada :
            p_oid_asigprodAnaq
            p_val_version_destino
 ********************************************************************************/
 PROCEDURE APE_PR_GENER_ASIGN_PRANQ_NVERS (
   p_oid_asigprodAnaq       VARCHAR2,
   p_val_version_destino    VARCHAR2
 )
 IS
   p_nuevo                      ape_asign_produ_anaqu_cabec.oid_asig_prod_anaq_cabe %TYPE;
   W_FILAS                      NUMBER := 1000;
   p_oid_asig_anaq_cabe_ante    ape_asign_produ_anaqu_cabec.oid_asig_prod_anaq_cabe %TYPE := NULL;
   p_oid_asig_anaq_cabe         ape_asign_produ_anaqu_cabec.oid_asig_prod_anaq_cabe %TYPE;

   TYPE tmptablaAsignarProductoAnaquel IS RECORD(
     oid_asig_prod_anaq_cabe         ape_asign_produ_anaqu_cabec.oid_asig_prod_anaq_cabe %TYPE,
     val_vers                        ape_asign_produ_anaqu_cabec.val_vers %TYPE,
     indActivo                       ape_asign_produ_anaqu_cabec.ind_acti_fact %TYPE,
     perd_oid_peri                   ape_asign_produ_anaqu_cabec.perd_oid_peri %TYPE,
     mcdc_oid_mapa_cent_dist_cabe    ape_asign_produ_anaqu_cabec.mcdc_oid_mapa_cent_dist_cabe %TYPE,
     mzca_oid_mapa_zona_cabe         ape_asign_produ_anaqu_cabec.mzca_oid_mapa_zona_cabe %TYPE,
     num_unida                       ape_asign_produ_anaqu_detal.num_unida %TYPE,
     ind_sigu_asig                   ape_asign_produ_anaqu_detal.ind_sigu_asig %TYPE,
     ind_asig_petl                   ape_asign_produ_anaqu_detal.ind_asig_petl %TYPE,
     apac_oid_asig_prod_anaq_cabe    ape_asign_produ_anaqu_detal.apac_oid_asig_prod_anaq_cabe %TYPE,
     prod_oid_prod                   ape_asign_produ_anaqu_detal.prod_oid_prod %TYPE,
     mcdd_oid_mapa_cent_dist_deta    ape_asign_produ_anaqu_detal.mcdd_oid_mapa_cent_dist_deta %TYPE,
     prce_oid_proc                   ape_asign_produ_anaqu_detal.prce_oid_proc %TYPE,
     num_afra_lane_numb              ape_asign_produ_anaqu_detal.num_afra_lane_numb %TYPE,
     num_afra_lane_qtty              ape_asign_produ_anaqu_detal.num_afra_lane_qtty %TYPE
   );

   TYPE tablaRegAsignarProdAnaquel IS TABLE OF tmptablaAsignarProductoAnaquel;
   tablaRegAsignarProdAnaqrecord tablaRegAsignarProdAnaquel;

   -- Se obtienen los datos de Asignacion Productos Anaqueles Origen de acuerdo al criterio de Oid Asignacion Producto Anaquel Origen
   CURSOR ASIGN_PRODU_ANAQU(p_oid_asig_prod_anaq NUMBER) IS
     SELECT ascbpr.oid_asig_prod_anaq_cabe,
            ascbpr.val_vers,
            'N' indActivo,
            ascbpr.perd_oid_peri,
            ascbpr.mcdc_oid_mapa_cent_dist_cabe,
            ascbpr.mzca_oid_mapa_zona_cabe,
            ascdpr.num_unida,
            ascdpr.ind_sigu_asig,
            ascdpr.ind_asig_petl,
            ascdpr.apac_oid_asig_prod_anaq_cabe,
            ascdpr.prod_oid_prod,
            ascdpr.mcdd_oid_mapa_cent_dist_deta,
            ascdpr.prce_oid_proc,
            ascdpr.num_afra_lane_numb,
            ascdpr.num_afra_lane_qtty
       FROM ape_asign_produ_anaqu_cabec ascbpr,
            ape_asign_produ_anaqu_detal ascdpr
      WHERE ascbpr.oid_asig_prod_anaq_cabe = ascdpr.apac_oid_asig_prod_anaq_cabe
        AND ascbpr.oid_asig_prod_anaq_cabe = p_oid_asig_prod_anaq;

 BEGIN

   OPEN ASIGN_PRODU_ANAQU(p_oid_asigprodAnaq);
     LOOP
       FETCH ASIGN_PRODU_ANAQU BULK COLLECT INTO tablaRegAsignarProdAnaqrecord LIMIT W_FILAS;

         IF tablaRegAsignarProdAnaqrecord.COUNT > 0 THEN

           FOR x IN tablaRegAsignarProdAnaqrecord.FIRST .. tablaRegAsignarProdAnaqrecord.LAST LOOP

             p_oid_asig_anaq_cabe:= tablaRegAsignarProdAnaqrecord(x).oid_asig_prod_anaq_cabe;

             IF ( p_oid_asig_anaq_cabe_ante IS NULL OR p_oid_asig_anaq_cabe_ante <> p_oid_asig_anaq_cabe ) THEN
               -- Se obtiene el correlativo de la tabla APE_ASIGN_PRODU_ANAQU_CABEC
               SELECT ape_apac_seq.NEXTVAL
                 INTO p_nuevo
                 FROM DUAL;

               -- Se crea el nuevo registro de Asignacion Productos Anaquel destino
               INSERT INTO APE_ASIGN_PRODU_ANAQU_CABEC (
                 oid_asig_prod_anaq_cabe,
                 val_vers,
                 ind_acti_fact,
                 perd_oid_peri,
                 mcdc_oid_mapa_cent_dist_cabe,
                 mzca_oid_mapa_zona_cabe
               )
               VALUES(
                 p_nuevo,
                 p_val_version_destino,
                 tablaRegAsignarProdAnaqrecord(x).indActivo,
                 tablaRegAsignarProdAnaqrecord(x).perd_oid_peri,
                 tablaRegAsignarProdAnaqrecord(x).mcdc_oid_mapa_cent_dist_cabe,
                 tablaRegAsignarProdAnaqrecord(x).mzca_oid_mapa_zona_cabe
               );

               p_oid_asig_anaq_cabe_ante := p_oid_asig_anaq_cabe;

             END IF;

             -- Se copia el detalle de Asignacion Productos Anaquel origen al nuevo destino
             INSERT INTO APE_ASIGN_PRODU_ANAQU_DETAL (
               oid_asig_prod_anaq,
               num_unida,
               ind_sigu_asig,
               ind_asig_petl,
               apac_oid_asig_prod_anaq_cabe,
               prod_oid_prod,
               mcdd_oid_mapa_cent_dist_deta,
               prce_oid_proc,
               num_afra_lane_numb,
               num_afra_lane_qtty
             )
             VALUES(
               ape_apan_seq.nextval,
               tablaRegAsignarProdAnaqrecord(x).num_unida,
               tablaRegAsignarProdAnaqrecord(x).ind_sigu_asig,
               tablaRegAsignarProdAnaqrecord(x).ind_asig_petl,
               p_nuevo,
               tablaRegAsignarProdAnaqrecord(x).prod_oid_prod,
               tablaRegAsignarProdAnaqrecord(x).mcdd_oid_mapa_cent_dist_deta,
               tablaRegAsignarProdAnaqrecord(x).prce_oid_proc,
               tablaRegAsignarProdAnaqrecord(x).num_afra_lane_numb,
               tablaRegAsignarProdAnaqrecord(x).num_afra_lane_qtty
             );

           END LOOP;

         END IF;

       EXIT WHEN  ASIGN_PRODU_ANAQU%NOTFOUND;
     END LOOP;
   CLOSE ASIGN_PRODU_ANAQU;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_ASIGN_PRANQ_NVERS: '||ls_sqlerrm);
 END APE_PR_GENER_ASIGN_PRANQ_NVERS;

/**************************************************************************
     Descripcion       : Preasignacion x Validacion de Estimados
     Autor             : Christian Gonzales
     Fecha Creacion    : 16/09/2010
     Parametros Entrada :
            p_oid_mapa_cent
            p_oid_sublinea
            p_oid_peri_origen
            p_oid_peri_destino
            p_val_version_destino
            p_cod_fuente
 ***************************************************************************/
 PROCEDURE APE_PR_PREAS_VARIA_ESTIM (
   p_oid_mapa_cent          NUMBER,
   p_oid_sublinea           NUMBER,
   cod_peri_origen          NUMBER,
   cod_peri_destino         NUMBER,
   p_val_version_origen     VARCHAR2,
   p_val_version_destino    VARCHAR2,
   p_cod_fuente             VARCHAR2
 )
 IS
   p_oid_peri_origen         NUMBER;
   p_oid_peri_destino        NUMBER;
   p_oid_presignacion        ape_proce.oid_proc  %TYPE;
   p_oid_estimado            ape_proce.oid_proc  %TYPE;
   p_oid_balanceado          ape_proce.oid_proc  %TYPE;
   v_oid_asig_prod_anaq_d    ape_asign_produ_anaqu_detal.oid_asig_prod_anaq  %TYPE;
   v_oid_mapacd_d            ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE;
   v_counter                 INT;
   v_count_prod              INT;
   v_count_update            INT;
   W_FILAS                   NUMBER := 1000;

   TYPE tmptablaPreasigVarEstimados IS RECORD (
     unidades_estimadas    ape_estim_produ.num_unid_esti %TYPE,
     oid_prod              ape_asign_produ_anaqu_detal.prod_oid_prod %TYPE
   );

   TYPE tablaPreasigVarEstimados IS TABLE OF tmptablaPreasigVarEstimados;
   tablaPreasigVarEstimadosrecord tablaPreasigVarEstimados;

   -- Se obtienen los productos validados para la preasignacion
   CURSOR PREASIGN_PROD_VARIACION_ESTIM(v_oid_peri_origen NUMBER, v_oid_peri_destino NUMBER) IS
     SELECT estimado_destino.num_unid_esti,
            estimado_origen.prod_oid_prod
       FROM ape_estim_produ estimado_origen,
            ape_estim_produ estimado_destino,
            ape_subli_armad sublinea,
            ape_linea_armad linea
      WHERE sublinea.liar_oid_line_arma = linea.oid_line_arma
        AND estimado_origen.liar_oid_line_arma = sublinea.liar_oid_line_arma
        AND estimado_destino.liar_oid_line_arma = sublinea.liar_oid_line_arma
        AND estimado_destino.prod_oid_prod = estimado_origen.prod_oid_prod
        AND estimado_origen.perd_oid_peri = v_oid_peri_origen -- periodo origen
        AND estimado_destino.perd_oid_peri =  v_oid_peri_destino  -- periodo destino
        AND sublinea.oid_subl_arma = p_oid_sublinea -- sublinea
        AND (DECODE(estimado_origen.num_unid_esti,0,0,(100 * ABS(estimado_origen.num_unid_esti -
                    estimado_destino.num_unid_esti)) / estimado_origen.num_unid_esti) <=
                    NVL(sublinea.val_porc_vari_esti, linea.num_porc_vari_esti)
         OR ABS(estimado_origen.num_unid_esti - estimado_destino.num_unid_esti) <=
                NVL(sublinea.num_unid_prea, linea.num_unid_prea))
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_detal apad,
                               ape_asign_produ_anaqu_cabec apac
                         WHERE apac.oid_asig_prod_anaq_cabe =apad.apac_oid_asig_prod_anaq_cabe
                           AND apad.prod_oid_prod = estimado_origen.prod_oid_prod
                           AND apac.perd_oid_peri = v_oid_peri_destino -- PERIODO DESTINO
                           AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent); -- OIDMAPACD

   CURSOR PREASIGN_ANAQUELES(v_oid_prod NUMBER, v_oid_peri_origen NUMBER, v_oid_peri_destino NUMBER) IS
     SELECT mapacd_det.oid_mapa_cent_dist_deta
       INTO v_oid_mapacd_d
       FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
            ape_asign_produ_anaqu_cabec asignprodanaq_cab,
            ape_mapa_centr_distr_detal  mapacd_det
      WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
        AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = asignprodanaq_cab.mcdc_oid_mapa_cent_dist_cabe
        AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
        AND asignprodanaq_cab.perd_oid_peri = v_oid_peri_origen
        AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
        AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea
        AND mapacd_det.ind_expa = 0 -- FALSE
        AND asignprodanaq_det.prod_oid_prod = v_oid_prod
        AND asignprodanaq_cab.val_vers = p_val_version_origen
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_detal apad,
                               ape_asign_produ_anaqu_cabec apac
                         WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                           AND apad.prod_oid_prod = asignprodanaq_det.prod_oid_prod
                           AND apac.val_vers = p_val_version_destino
                           AND apac.perd_oid_peri = v_oid_peri_destino-- PERIODO DESTINO
                           AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent); -- OIDMAPACD

 BEGIN
   -- se obtienen los oids de procedencia
   SELECT oid_proc
     INTO p_oid_presignacion
     FROM ape_proce
    WHERE cod_proc = 'P';

   SELECT oid_proc
     INTO p_oid_estimado
     FROM ape_proce
    WHERE cod_proc = 'E';

   SELECT oid_proc
     INTO p_oid_balanceado
     FROM ape_proce
    WHERE cod_proc = 'B';

   -- se obtiente los oids periodos
   p_oid_peri_origen := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(cod_peri_origen);
   p_oid_peri_destino := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(cod_peri_destino);

   -- Se limpia lo preasignado o balanceado anteriormente
   UPDATE ape_asign_produ_anaqu_detal
      SET prod_oid_prod = NULL,
          num_unida = 0,
          prce_oid_proc = p_oid_estimado
    WHERE oid_asig_prod_anaq IN ( SELECT detalle.oid_asig_prod_anaq
                                    FROM ape_asign_produ_anaqu_cabec cabecera,
                                         ape_asign_produ_anaqu_detal detalle,
                                         ape_mapa_centr_distr_detal  mapa
                                   WHERE cabecera.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                                     AND cabecera.val_vers = p_val_version_destino
                                     AND detalle.prce_oid_proc in (p_oid_presignacion, p_oid_balanceado)
                                     AND mapa.sbar_oid_subl_arma = p_oid_sublinea
                                     AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                     AND mapa.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                     AND cabecera.perd_oid_peri = p_oid_peri_destino
                                     AND detalle.prod_oid_prod IS NOT NULL);

   OPEN PREASIGN_PROD_VARIACION_ESTIM(p_oid_peri_origen, p_oid_peri_destino);
     -- la fuente del periodo de destino puede ser E => 'Estimados' o F => facturacion proyectada
     IF p_cod_fuente = 'E' THEN

       LOOP
         FETCH PREASIGN_PROD_VARIACION_ESTIM BULK COLLECT INTO tablaPreasigVarEstimadosrecord LIMIT W_FILAS;

           IF tablaPreasigVarEstimadosrecord.COUNT > 0 THEN

             FOR x IN tablaPreasigVarEstimadosrecord.FIRST .. tablaPreasigVarEstimadosrecord.LAST LOOP
               -- Se valida que el producto se encuentre asignado en el periodo anterior y no
               -- este asignado en el periodo actual
               SELECT COUNT(1)
                 INTO v_counter
                 FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
                      ape_asign_produ_anaqu_cabec asignprodanaq_cab,
                      ape_mapa_centr_distr_detal  mapacd_det
                WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
                  AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = asignprodanaq_cab.mcdc_oid_mapa_cent_dist_cabe
                  AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
                  AND asignprodanaq_cab.perd_oid_peri = p_oid_peri_origen  -- periodo origen
                  AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                  AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea
                  AND mapacd_det.ind_expa = 0 -- FALSE
                  AND asignprodanaq_det.prod_oid_prod = tablaPreasigVarEstimadosrecord(x).oid_prod
                  AND asignprodanaq_cab.val_vers = p_val_version_origen
                  AND NOT EXISTS (SELECT 1
                                    FROM ape_asign_produ_anaqu_detal apad,
                                         ape_asign_produ_anaqu_cabec apac
                                   WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                                     AND apad.prod_oid_prod = asignprodanaq_det.prod_oid_prod
                                     AND apac.perd_oid_peri = p_oid_peri_destino-- PERIODO DESTINO
                                     AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent); -- OIDMAPACD

               v_count_update := 0;
               IF v_counter > 0 THEN

                 OPEN PREASIGN_ANAQUELES(tablaPreasigVarEstimadosrecord(x).oid_prod, p_oid_peri_origen, p_oid_peri_destino);
                   LOOP
                     FETCH PREASIGN_ANAQUELES INTO v_oid_mapacd_d;
                     EXIT WHEN (PREASIGN_ANAQUELES%NOTFOUND);

                     -- Valida de que no existan productos asignados y obtiene el oid de asignacion detalle
                     SELECT apad.oid_asig_prod_anaq,
                            COUNT(apad.prod_oid_prod)
                       INTO v_oid_asig_prod_anaq_d,
                            v_count_prod
                       FROM ape_asign_produ_anaqu_cabec apac,
                            ape_asign_produ_anaqu_detal apad
                      WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                        AND apad.mcdd_oid_mapa_cent_dist_deta = v_oid_mapacd_d
                        AND apac.perd_oid_peri = p_oid_peri_destino
                        AND apac.val_vers = p_val_version_destino
                        AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                   GROUP BY apad.oid_asig_prod_anaq;

                     IF v_count_prod = 0 THEN

                       IF v_count_update = 0 THEN
                         -- v_count_update valida de que solo en el primer anaquel guarde la cantidad estimada
                         UPDATE ape_asign_produ_anaqu_detal
                            SET num_unida = tablaPreasigVarEstimadosrecord(x).unidades_estimadas,
                                prce_oid_proc = p_oid_presignacion,
                                prod_oid_prod = tablaPreasigVarEstimadosrecord(x).oid_prod
                          WHERE oid_asig_prod_anaq = v_oid_asig_prod_anaq_d;

                         v_count_update := v_count_update + 1;
                       ELSE
                         UPDATE ape_asign_produ_anaqu_detal
                            SET prce_oid_proc = p_oid_presignacion,
                                prod_oid_prod = tablaPreasigVarEstimadosrecord(x).oid_prod
                          WHERE oid_asig_prod_anaq = v_oid_asig_prod_anaq_d;
                       END IF;

                     END IF;

                   END LOOP;
                 CLOSE PREASIGN_ANAQUELES;

               END IF;

             END LOOP;

           END IF;

         EXIT WHEN  PREASIGN_PROD_VARIACION_ESTIM%NOTFOUND;
       END LOOP;

     END IF;

   CLOSE PREASIGN_PROD_VARIACION_ESTIM;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_PREASIG_X_VALID_ESTIM: '||ls_sqlerrm);
 END APE_PR_PREAS_VARIA_ESTIM;

/**************************************************************************
     Descripcion       : Genera el Balanceo
     Fecha Creacion    : 21/09/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_LINEA (
   p_oidMapaCentroDistribucion    VARCHAR2,
   p_oidPeriodo                   VARCHAR2,
   p_codVersion                   VARCHAR2,
   p_oidLinea                     VARCHAR2,
   p_oidSubLinea                  VARCHAR2,
   p_codOrdeAnaquel               VARCHAR2,
   p_cod_orig_dato                VARCHAR2,
   p_codOrdeProducto              VARCHAR2,
   p_numAnaqueles                 OUT VARCHAR2
 )
 IS
   vn_OidMapaCentroDistribucion    NUMBER;
   vn_oidPeriodo                   NUMBER;
   vn_oidLinea                     NUMBER;
   vn_oidSubLinea                  NUMBER;
   vn_oidFuncionDistribucion       NUMBER;
   vn_oidMapaZona                  NUMBER;
   vn_oidMapaOrden                 NUMBER;
   vn_numAnaqueles                 NUMBER;
   vn_oidVersion                   NUMBER;

 BEGIN

   vn_OidMapaCentroDistribucion := to_number(p_oidMapaCentroDistribucion);
   vn_oidPeriodo := to_number(p_oidPeriodo);
   vn_oidLinea := to_number(p_oidLinea);
   vn_oidSubLinea := to_number(p_oidSubLinea);
   p_numAnaqueles := '0';
   vn_numAnaqueles := 0;

   -- Obteniendo la funcion de distribucion de la sublinea
   SELECT NVL(sla.fndi_oid_func_dist,0)
     INTO vn_oidFuncionDistribucion
     FROM ape_subli_armad sla
    WHERE sla.oid_subl_arma = vn_oidSubLinea;

    -- Obteniendo el Mapa Zona y el oid de la Version
   SELECT apac.oid_asig_prod_anaq_cabe,
          apac.mzca_oid_mapa_zona_cabe
     INTO vn_oidVersion,
          vn_oidMapaZona
     FROM ape_asign_produ_anaqu_cabec apac
    WHERE apac.mcdc_oid_mapa_cent_dist_cabe = vn_OidMapaCentroDistribucion
      AND apac.perd_oid_peri = p_oidPeriodo
      AND LTRIM(RTRIM(apac.val_vers)) = LTRIM(RTRIM(p_codVersion));

   -- Obteniendo el Mapa Orden
   SELECT a.oid_anaq_cabe
     INTO vn_oidMapaOrden
     FROM ape_orden_anaqu_cabec a
    WHERE a.mzca_oid_mapa_zona_cabe = vn_oidMapaZona
      AND a.cod_mapa_orde = p_codOrdeAnaquel;

   IF(vn_oidFuncionDistribucion = 1) THEN
     -- distribucion x Matching
     APE_PR_GENER_BALAN_MATCH(vn_oidPeriodo, vn_oidLinea, vn_oidSubLinea,  p_codOrdeProducto,
                              vn_oidMapaZona, vn_oidMapaOrden, vn_oidVersion);
   ELSE
     IF(vn_oidFuncionDistribucion = 2) THEN
       -- distribucion x Aframe
       APE_PR_GENER_BALAN_AFRAM(vn_OidMapaCentroDistribucion, vn_oidPeriodo, vn_oidLinea, vn_oidSubLinea,
                                p_codOrdeProducto, vn_oidMapaZona, vn_oidMapaOrden, vn_oidVersion);
     ELSE
       IF(vn_oidFuncionDistribucion = 3) THEN
         -- distribucion x Tipo Anaquel
         APE_PR_GENER_BALAN_ANAQU(vn_OidMapaCentroDistribucion, vn_oidPeriodo, vn_oidLinea, vn_oidSubLinea,
                                  p_codOrdeProducto, vn_oidMapaZona, vn_oidMapaOrden, vn_oidVersion, vn_numAnaqueles);

       END IF;
     END IF;
   END IF;

   IF (vn_numAnaqueles > 0) THEN
     p_numAnaqueles := to_char(vn_numAnaqueles);
   ELSE
     p_numAnaqueles := '0';
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_BALAN_LINEA: '||ls_sqlerrm);
 END APE_PR_GENER_BALAN_LINEA;

/**************************************************************************
     Descripcion       : Genera el Balanceo x Matching
     Fecha Creacion    : 21/09/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_MATCH (
   p_oidPeriodo         NUMBER,
   p_oidLinea           NUMBER,
   p_oidSubLinea        NUMBER,
   p_codOrdeProducto    VARCHAR2,
   p_oidMapaZona        NUMBER,
   p_oidMapaOrden       NUMBER,
   p_oidVersion         NUMBER
 )
 IS
   vn_contListaArticulo  NUMBER;
   vn_contListaAnaquel   NUMBER;

   CURSOR cListaArticulosAsc  IS
     SELECT producto.oid_prod        OID_PRODUCTO,
            estimado.num_unid_esti   NUM_ESTIMADO
       FROM ape_estim_produ estimado,
            mae_produ  producto
      WHERE estimado.liar_oid_line_arma = p_oidLinea
        AND estimado.perd_oid_peri = p_oidPeriodo
        AND producto.oid_prod = estimado.prod_oid_prod
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND detalle.prod_oid_prod = producto.oid_prod)
      ORDER BY estimado.num_unid_esti ASC;

   CURSOR cListaArticulosDes  IS
     SELECT producto.oid_prod        OID_PRODUCTO,
            estimado.num_unid_esti   NUM_ESTIMADO
       FROM ape_estim_produ estimado,
            mae_produ  producto
      WHERE estimado.liar_oid_line_arma = p_oidLinea
        AND estimado.perd_oid_peri = p_oidPeriodo
        AND producto.oid_prod = estimado.prod_oid_prod
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND detalle.prod_oid_prod = producto.oid_prod)
      ORDER BY estimado.num_unid_esti DESC;

   TYPE articulos IS RECORD(
     oidProducto     mae_produ.oid_prod%TYPE,
     num_estimado    ape_estim_produ.num_unid_esti%TYPE
   );

   TYPE articulosTab  IS TABLE OF articulos;
   articulosRecord articulosTab;

   CURSOR cListaAnaquel  IS
     SELECT  asignaciondetalle.oid_asig_prod_anaq,
             mapadetalle.sbar_oid_subl_arma
       FROM  ape_orden_anaqu_cabec        ordencabecera,
             ape_orden_anaqu_detal        ordendetalle,
             ape_mapa_centr_distr_detal   mapadetalle,
             ape_asign_produ_anaqu_cabec  asignacioncabecer,
             ape_asign_produ_anaqu_detal  asignaciondetalle
      WHERE  ordencabecera.oid_anaq_cabe = p_oidMapaOrden
        AND  ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
        AND  ordencabecera.liar_oid_line_arma = p_oidLinea
        AND  mapadetalle.ind_expa = 0
        AND  asignacioncabecer.oid_asig_prod_anaq_cabe = p_oidVersion
        AND  ordencabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
        AND  mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
        AND  mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
        AND  asignacioncabecer.oid_asig_prod_anaq_cabe = asignaciondetalle.apac_oid_asig_prod_anaq_cabe
        AND  asignacioncabecer.mzca_oid_mapa_zona_cabe = ordencabecera.mzca_oid_mapa_zona_cabe
        AND  asignacioncabecer.mcdc_oid_mapa_cent_dist_cabe = mapadetalle.mcdc_oid_mapa_cent_dist_cabe
        AND  NOT EXISTS (SELECT 1
                           FROM ape_asign_produ_anaqu_cabec cabecera,
                                ape_asign_produ_anaqu_detal detalle
                          WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                            AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                            AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                            AND detalle.prod_oid_prod IS NOT NULL)
      ORDER BY mapadetalle.num_anaq ASC;

   TYPE anaqueles IS RECORD(
     oidAnaquel     ape_asign_produ_anaqu_detal.oid_asig_prod_anaq%TYPE,
     oidSubLinea    ape_subli_armad.oid_subl_arma%TYPE
   );

   TYPE anaquelesTab  IS TABLE OF anaqueles;
   anaquelesRecord anaquelesTab;

   W_FILAS    NUMBER := 10000;

 BEGIN
   -- Obteniendo la longitud de la lista de productos
   SELECT COUNT(1)
     INTO vn_contListaArticulo
     FROM ape_estim_produ estimado,
          mae_produ  producto
    WHERE estimado.liar_oid_line_arma = p_oidLinea
      AND estimado.perd_oid_peri = p_oidPeriodo
      AND producto.oid_prod = estimado.prod_oid_prod
      AND NOT EXISTS (SELECT 1
                        FROM ape_asign_produ_anaqu_cabec cabecera,
                             ape_asign_produ_anaqu_detal detalle
                       WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                         AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                         AND detalle.prod_oid_prod = producto.oid_prod);

   -- Obteniendo la longitud de la lista de anaqueles
   SELECT COUNT(1)
     INTO vn_contListaAnaquel
     FROM ape_orden_anaqu_cabec ordencabecera,
          ape_orden_anaqu_detal ordendetalle,
          ape_mapa_centr_distr_detal mapadetalle,
          ape_asign_produ_anaqu_cabec asignacioncabecer,
          ape_asign_produ_anaqu_detal asignaciondetalle
    WHERE ordencabecera.oid_anaq_cabe = p_oidMapaOrden
      AND  ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
      AND  ordencabecera.liar_oid_line_arma = p_oidLinea
      AND  mapadetalle.ind_expa = 0
      AND  asignacioncabecer.oid_asig_prod_anaq_cabe = p_oidVersion
      AND  ordencabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
      AND  mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
      AND  mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
      AND  asignacioncabecer.oid_asig_prod_anaq_cabe = asignaciondetalle.apac_oid_asig_prod_anaq_cabe
      AND  asignacioncabecer.mzca_oid_mapa_zona_cabe = ordencabecera.mzca_oid_mapa_zona_cabe
      AND  asignacioncabecer.mcdc_oid_mapa_cent_dist_cabe = mapadetalle.mcdc_oid_mapa_cent_dist_cabe
      AND  NOT EXISTS (SELECT 1
                         FROM ape_asign_produ_anaqu_cabec cabecera,
                              ape_asign_produ_anaqu_detal detalle
                        WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                          AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                          AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                          AND detalle.prod_oid_prod IS NOT NULL);

   IF (vn_contListaArticulo >0 AND vn_contListaAnaquel >0) THEN
     -- Si esta ordenado de forma Ascendente
     IF ( p_codOrdeProducto = 'A') THEN

       OPEN cListaArticulosAsc;
         LOOP
           FETCH cListaArticulosAsc BULK COLLECT INTO articulosRecord LIMIT W_FILAS;
           EXIT WHEN (cListaArticulosAsc%NOTFOUND);
         END LOOP;
       CLOSE cListaArticulosAsc;

     END IF;

     -- Si esta ordenado de forma Descendente
     IF ( p_codOrdeProducto = 'D') THEN

       OPEN cListaArticulosDes;
         LOOP
           FETCH cListaArticulosDes BULK COLLECT INTO articulosRecord LIMIT W_FILAS;
           EXIT WHEN (cListaArticulosDes%NOTFOUND);
         END LOOP;
       CLOSE cListaArticulosDes;

     END IF;

     OPEN cListaAnaquel;
       LOOP
         FETCH cListaAnaquel BULK COLLECT INTO anaquelesRecord LIMIT W_FILAS;
         EXIT WHEN (cListaAnaquel%NOTFOUND);
       END LOOP;
     CLOSE cListaAnaquel;

     -- Se borra el producto de la asignacion anterior
     UPDATE ape_asign_produ_anaqu_detal
        SET prod_oid_prod = NULL,
            prce_oid_proc = ln_oidProEstimado
      WHERE oid_asig_prod_anaq IN ( SELECT detalle.oid_asig_prod_anaq
                                      FROM ape_asign_produ_anaqu_cabec cabecera,
                                           ape_asign_produ_anaqu_detal detalle,
                                           ape_mapa_centr_distr_detal  mapa
                                     WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                       AND detalle.prce_oid_proc = ln_oidProBalanceo
                                       AND mapa.sbar_oid_subl_arma = p_oidSubLinea
                                       AND cabecera.perd_oid_peri = p_oidPeriodo
                                       AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                       AND mapa.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta);

     IF (vn_contListaArticulo < vn_contListaAnaquel) THEN

       FOR x IN articulosRecord.FIRST .. articulosRecord.LAST LOOP

         IF ( anaquelesRecord(x).oidSubLinea = p_oidSubLinea ) THEN

           UPDATE ape_asign_produ_anaqu_detal
              SET prod_oid_prod = articulosRecord(x).oidProducto,
                  prce_oid_proc = ln_oidProBalanceo,
                  num_unida     = articulosRecord(x).num_estimado
            WHERE oid_asig_prod_anaq  = anaquelesRecord(x).oidAnaquel;

         END IF;

       END LOOP;

     ELSE
       FOR x IN anaquelesRecord.FIRST .. anaquelesRecord.LAST LOOP

         IF ( anaquelesRecord(x).oidSubLinea = p_oidSubLinea ) THEN

           UPDATE ape_asign_produ_anaqu_detal
            SET prod_oid_prod = articulosRecord(x).oidProducto,
                prce_oid_proc = ln_oidProBalanceo,
                num_unida     = articulosRecord(x).num_estimado
          WHERE oid_asig_prod_anaq = anaquelesRecord(x).oidAnaquel;

         END IF;

       END LOOP;

     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_BALAN_MATCH: '||ls_sqlerrm);
 END APE_PR_GENER_BALAN_MATCH;

/**************************************************************************
     Descripcion       : Genera el Balanceo x Aframe
     Fecha Creacion    : 21/09/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_AFRAM (
   p_oidMapaCentroDistribucion    NUMBER,
   p_oidPeriodo                   NUMBER,
   p_oidLinea                     NUMBER,
   p_oidSubLinea                  NUMBER,
   p_codOrdeProducto              VARCHAR2,
   p_oidMapaZona                  NUMBER,
   p_oidMapaOrden                 NUMBER,
   p_oidVersion                   NUMBER
 )
 IS
   vn_contListaArticulo       NUMBER;
   vn_oidTipoAnaquel          NUMBER;
   vn_numTopeAnaquel          NUMBER;
   vnFrameActual              NUMBER;
   vnFrameSiguiente           NUMBER;
   vnNumOrdenActual           NUMBER;
   vnNumOrdenSiguiente        NUMBER;
   vnIndExpandidoPrimero      NUMBER;
   vnIndExpandidoSiguiente    NUMBER;
   vnChanelActual             NUMBER;
   vnChanelSiguiente          NUMBER;
   vn_contAnaquelConsec       NUMBER;
   vn_contAnaquelExpa         NUMBER;
   vb_Asignado                BOOLEAN;
   vb_FlagIndExp              BOOLEAN;
   vb_FlagValorExp            BOOLEAN;
   vn_PosicionInicialSideA    NUMBER;
   vn_PosicionFinalSideA      NUMBER;
   vn_PosicionInicialSideB    NUMBER;
   vn_PosicionFinalSideB      NUMBER;

   -- Lista de Articulos
   CURSOR cListaArticulos IS
     SELECT producto.oid_prod                 OID_PRODUCTO,
            estimado.num_unid_esti            NUM_UNIDADES,
            apeproducto.num_afra_anch         VAL_ANCHO,
            apeproducto.num_asig_maxi_chan    NUM_MAXIMO,
            APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, apeproducto.num_hora_inve, apeproducto.num_lane_capa_95)  LANE95,
            APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, apeproducto.num_hora_inve, apeproducto.Num_Lane_Capa_60)  LANE60
       FROM ape_estim_produ estimado,
            ape_linea_armad linea,
            mae_produ producto,
            ape_produ apeproducto
      WHERE estimado.liar_oid_line_arma = p_oidLinea
        AND estimado.perd_oid_peri = p_oidPeriodo
        AND apeproducto.tidi_oid_tipo_dispe = 2  -- Tipo Aframe
        AND producto.oid_prod = estimado.prod_oid_prod
        AND producto.oid_prod = apeproducto.prod_oid_prod
        AND ((linea.num_line_afp = '1' AND producto.cod_ind_dent_caja IN ('F','B'))
         OR  (linea.num_line_afp = '0' AND (producto.cod_ind_dent_caja NOT IN ('F','B') OR producto.cod_ind_dent_caja IS NULL)))
        AND producto.oid_prod = estimado.prod_oid_prod
        AND linea.oid_line_arma = estimado.liar_oid_line_arma
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND detalle.prod_oid_prod = producto.oid_prod)
      ORDER BY estimado.num_unid_esti DESC;

   TYPE articulos IS RECORD(
     oidProducto    mae_produ.oid_prod%TYPE,
     numUnidades    ape_estim_produ.num_unid_esti%TYPE,
     valAncho       ape_produ.num_afra_anch%TYPE,
     numMaximo      ape_produ.num_asig_maxi_chan%TYPE,
     numLane95      NUMBER,
     numLane60      NUMBER
   );

   TYPE articulosTab  IS TABLE OF articulos;
   articulosRecord articulosTab;

   -- Lista de Tipo de Anaqueles
   CURSOR cListaTipoAnaqueles(oidProducto NUMBER) IS
     SELECT proanaquel.tian_oid_tipo_anaq  OID_TIPO_ANAQUEL
       FROM ape_produ_anaqu proanaquel
      WHERE proanaquel.prod_oid_prod = oidProducto
   ORDER BY proanaquel.num_nume_prio;

   -- Lista de Anaqueles del lado A
   CURSOR cListaAnaquelesA (vn_oidTipoAnaquel NUMBER, vn_ancho NUMBER ) IS
     SELECT ordendetalle.num_orde                   NUM_ORDEN,
            asignaciondetalle.oid_asig_prod_anaq    NUM_ANAQUEL,
            mapadetalle.num_afra_fram_numb          NUM_FRAME,
            mapadetalle.num_afra_chan_addr          NUM_CHANEL,
            mapadetalle.val_side                    VAL_SIDE,
            mapadetalle.ind_expa                    IND_EXPA
       FROM ape_orden_anaqu_cabec ordencabecera,
            ape_orden_anaqu_detal ordendetalle,
            ape_mapa_centr_distr_detal mapadetalle,
            ape_subli_armad sublinea,
            ape_asign_produ_anaqu_detal asignaciondetalle,
            ape_asign_produ_anaqu_cabec asignacioncabecera
      WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
        AND sublinea.oid_subl_arma = p_oidSubLinea
        AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
        AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
        AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
        AND ordenCabecera.liar_oid_line_arma = p_oidLinea
        AND mapadetalle.num_afra_widt >= vn_ancho
        AND mapadetalle.val_side = 'A'
        AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
        AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
        AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
        AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
        AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
        AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asignacioncabecera.oid_asig_prod_anaq_cabe
        AND asignacioncabecera.perd_oid_peri = p_oidPeriodo
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                           AND cabecera.perd_oid_peri = p_oidPeriodo
                           AND detalle.prod_oid_prod IS NOT NULL)
      ORDER BY mapadetalle.val_side, ordencabecera.oid_anaq_cabe;

   -- Lista de Anaqueles del lado B
   CURSOR cListaAnaquelesB (vn_oidTipoAnaquel NUMBER, vn_ancho NUMBER ) IS
     SELECT ordendetalle.num_orde                   NUM_ORDEN,
            asignaciondetalle.oid_asig_prod_anaq    NUM_ANAQUEL,
            mapadetalle.num_afra_fram_numb          NUM_FRAME,
            mapadetalle.num_afra_chan_addr          NUM_CHANEL,
            mapadetalle.val_side                    VAL_SIDE,
            mapadetalle.ind_expa                    IND_EXPA
       FROM ape_orden_anaqu_cabec ordencabecera,
            ape_orden_anaqu_detal ordendetalle,
            ape_mapa_centr_distr_detal mapadetalle,
            ape_subli_armad  sublinea,
            ape_asign_produ_anaqu_detal asignaciondetalle,
            ape_asign_produ_anaqu_cabec asignacioncabecera
      WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
        AND sublinea.oid_subl_arma = p_oidSubLinea
        AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
        AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
        AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
        AND ordenCabecera.liar_oid_line_arma = p_oidLinea
        AND mapadetalle.num_afra_widt >= vn_ancho
        AND mapadetalle.val_side = 'B'
        AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
        AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
        AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
        AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
        AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
        AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asignacioncabecera.oid_asig_prod_anaq_cabe
        AND asignacioncabecera.perd_oid_peri = p_oidPeriodo
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                           AND cabecera.perd_oid_peri = p_oidPeriodo
                           AND detalle.prod_oid_prod IS NOT NULL)
      ORDER BY mapadetalle.val_side, ordencabecera.oid_anaq_cabe;

   TYPE anaqueles IS RECORD(
     num_Orden     ape_orden_anaqu_detal.num_orde%TYPE,
     numAnaquel    NUMBER,
     numFrame      NUMBER,
     numChanel     NUMBER,
     valSide       ape_mapa_centr_distr_detal.val_side%TYPE,
     ind_Expa      ape_mapa_centr_distr_detal.ind_expa%TYPE
   );

   TYPE anaquelesTab  IS TABLE OF anaqueles;
   anaquelesRecord anaquelesTab;

   W_FILAS    NUMBER := 10000;

 BEGIN
   -- Obteniendo la longitud de la lista de productos
   SELECT COUNT(producto.oid_prod)
     INTO vn_contListaArticulo
     FROM ape_estim_produ estimado,
          ape_linea_armad linea,
          mae_produ producto,
          ape_produ apeproducto
    WHERE estimado.liar_oid_line_arma = p_oidLinea
      AND estimado.perd_oid_peri = p_oidPeriodo
      AND apeproducto.tidi_oid_tipo_dispe = 2  -- Tipo Aframe
      AND producto.oid_prod = estimado.prod_oid_prod
      AND producto.oid_prod = apeproducto.prod_oid_prod
      AND ((linea.num_line_afp = '1' AND producto.cod_ind_dent_caja IN ('F','B'))
       OR  (linea.num_line_afp = '0' AND (producto.cod_ind_dent_caja NOT IN ('F','B') OR producto.cod_ind_dent_caja IS NULL)))
      AND producto.oid_prod = estimado.prod_oid_prod
      AND linea.oid_line_arma = estimado.liar_oid_line_arma
      AND NOT EXISTS (SELECT 1
                        FROM ape_asign_produ_anaqu_cabec cabecera,
                             ape_asign_produ_anaqu_detal detalle
                       WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                         AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                         AND detalle.prod_oid_prod = producto.oid_prod);

   IF ( vn_contListaArticulo > 0) THEN
     -- Se borra el producto de la asignacion anterior
     UPDATE ape_asign_produ_anaqu_detal
        SET prod_oid_prod = NULL
      WHERE oid_asig_prod_anaq IN ( SELECT detalle.oid_asig_prod_anaq
                                      FROM ape_asign_produ_anaqu_cabec cabecera,
                                           ape_asign_produ_anaqu_detal detalle,
                                           ape_mapa_centr_distr_detal  mapa
                                     WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                       AND detalle.prce_oid_proc = ln_oidProBalanceo
                                       AND mapa.sbar_oid_subl_arma = p_oidSubLinea
                                       AND cabecera.perd_oid_peri = p_oidPeriodo
                                       AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                       AND mapa.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                       AND detalle.prod_oid_prod IS NOT NULL);

     OPEN cListaArticulos;
       LOOP
         -- Inicializamos los contadores y flags
         vn_contAnaquelExpa      := 0;
         vb_FlagIndExp           := FALSE;
         vb_FlagValorExp         := FALSE;
         vn_contAnaquelConsec    := 0;
         vn_PosicionFinalSideA   := 0;
         vnIndExpandidoSiguiente := 0;
         vnIndExpandidoPrimero   := 0;
         vn_numTopeAnaquel       := 0;

         FETCH cListaArticulos BULK COLLECT INTO articulosRecord LIMIT W_FILAS;

           IF articulosRecord.COUNT > 0 THEN

             FOR x IN articulosRecord.FIRST .. articulosRecord.LAST LOOP

               vb_Asignado := FALSE;

               OPEN cListaTipoAnaqueles ( articulosRecord(x).oidProducto );
                 LOOP
                   -- Inicializamos el contador Expandido
                   vn_contAnaquelExpa      := 0;
                   vnIndExpandidoSiguiente := 0;
                   vnIndExpandidoPrimero   := 0;
                   vn_numTopeAnaquel       := 0;

                   -- Inicializamos la posicion Inicial y Final (Side A)
                   vn_PosicionInicialSideA := 0;
                   vn_PosicionFinalSideA   := 0;

                   -- Inicializamos la posicion Inicial y Final (Side B)
                   vn_PosicionInicialSideB := 0;
                   vn_PosicionFinalSideB   := 0;

                   vb_FlagIndExp   := FALSE;
                   vb_FlagValorExp := FALSE;

                   -- Se toma la lista de Tipo de Anaquel segun el producto
                   FETCH cListaTipoAnaqueles INTO vn_oidTipoAnaquel;

                     IF (vb_Asignado = FALSE) THEN

                       -- Se toma la lista de Anqueles A segun el tipo de anaquel y el producto
                       OPEN cListaAnaquelesA ( vn_oidTipoAnaquel, articulosRecord(x).valAncho);
                         LOOP
                           vn_contAnaquelConsec    := 0;
                           vn_contAnaquelExpa      := 0;
                           vnIndExpandidoSiguiente := 0;
                           vnIndExpandidoPrimero   := 0;
                           vn_numTopeAnaquel       := 0;

                           -- Se verifica si el lado A es mayor al numero maximo
                           -- si es mayor se toma el numero maximo
                           IF ( articulosRecord(x).numLane95 > articulosRecord(x).numMaximo ) THEN

                             IF (articulosRecord(x).numMaximo = 0) THEN

                               SELECT val_maxi_chan_prod
                                 INTO vn_numTopeAnaquel
                                 FROM ape_valor_defau_afram;

                             ELSE
                               vn_numTopeAnaquel := articulosRecord(x).numMaximo;
                             END IF;

                           ELSE
                             vn_numTopeAnaquel := articulosRecord(x).numLane95;
                           END IF;

                           FETCH cListaAnaquelesA BULK COLLECT INTO anaquelesRecord LIMIT W_FILAS;

                             IF (vb_Asignado = FALSE) THEN

                               IF anaquelesRecord.COUNT > 0 THEN

                                 FOR i IN anaquelesRecord.FIRST .. (anaquelesRecord.LAST - 1) LOOP
                                   vnNumOrdenActual        := anaquelesRecord(i).num_Orden;
                                   vnNumOrdenSiguiente     := anaquelesRecord(i+1).num_Orden;
                                   vnIndExpandidoSiguiente := anaquelesRecord(i+1).ind_Expa;

                                   IF (vb_FlagValorExp = FALSE) THEN

                                     vnIndExpandidoPrimero := anaquelesRecord(i).ind_Expa;
                                     vb_FlagValorExp       := TRUE;

                                   END IF;

                                   IF (vb_Asignado = FALSE) THEN
                                     -- Se verifica que el Primer Anaquel sea Expandido
                                     IF ((vb_FlagIndExp = FALSE) AND (vnIndExpandidoPrimero = 1)) THEN

                                       vn_contAnaquelExpa := vn_contAnaquelExpa + 1;
                                       vb_FlagIndExp := TRUE;

                                     END IF;

                                     -- Se verifica que el Anaquel Siguiente sea Expandido
                                     IF (vnIndExpandidoSiguiente = 1) THEN
                                       vn_contAnaquelExpa := vn_contAnaquelExpa + 1;
                                     END IF;

                                     -- Se verifican que los anaqueles sean continuos
                                     -- Para esto los chanel deben de ser consecutivos y deben tener el mismo frame
                                     IF (ABS(vnNumOrdenSiguiente - vnNumOrdenActual) = 1) THEN

                                       vn_contAnaquelConsec := vn_contAnaquelConsec + 1;

                                       IF (vn_PosicionInicialSideA = 0) THEN
                                         vn_PosicionInicialSideA := i;
                                       END IF;

                                     ELSE

                                       vn_PosicionInicialSideA := 0;
                                       vn_contAnaquelConsec    := 0;
                                       vb_FlagIndExp           := FALSE;
                                       vn_contAnaquelExpa      := 0;
                                       vnIndExpandidoPrimero   := 0;

                                     END IF;

                                     IF ((vn_contAnaquelConsec + 1) = (vn_numTopeAnaquel + vn_contAnaquelExpa)) THEN

                                       vb_Asignado := TRUE;
                                       vn_PosicionFinalSideA := vn_PosicionInicialSideA + vn_contAnaquelConsec;
                                       EXIT;

                                     END IF;

                                   END IF;

                                 END LOOP;

                                 IF ( vb_Asignado = TRUE ) THEN

                                   FOR j IN vn_PosicionInicialSideA .. (vn_PosicionFinalSideA) LOOP
                                     UPDATE ape_asign_produ_anaqu_detal
                                        SET prod_oid_prod = articulosRecord(x).oidProducto,
                                            num_unida     = articulosRecord(x).numUnidades,
                                            prce_oid_proc = ln_oidProBalanceo
                                      WHERE oid_asig_prod_anaq  = anaquelesRecord(j).numAnaquel
                                        AND anaquelesRecord(j).ind_Expa = 0;
                                   END LOOP;

                                 END IF;

                               END IF;

                             END IF;

                           EXIT WHEN (cListaAnaquelesA%NOTFOUND);
                         END LOOP;
                       CLOSE cListaAnaquelesA;

                       -- Si no se encontro anaqueles en el lado A se procede con la lista de Anaqueles
                       -- del lado B
                       IF (vb_Asignado = FALSE) THEN
                         -- Se toma la lista de Anqueles B segun el tipo de anaquel y el producto
                         OPEN cListaAnaquelesB (vn_oidTipoAnaquel, articulosRecord(x).valAncho);
                           LOOP
                             FETCH cListaAnaquelesB BULK COLLECT INTO anaquelesRecord LIMIT W_FILAS;
                               vn_contAnaquelConsec := 0;

                               -- Inicializamos el contador Expandido
                               vn_contAnaquelExpa := 0;

                               -- Se verifica si el lado B es mayor al numero maximo
                               -- si es mayor se toma el numero maximo
                               IF ( articulosRecord(x).numLane60 > articulosRecord(x).numMaximo ) THEN

                                 IF (articulosRecord(x).numMaximo = 0) THEN

                                   SELECT val_maxi_chan_prod
                                     INTO vn_numTopeAnaquel
                                     FROM ape_valor_defau_afram;

                                 ELSE
                                   vn_numTopeAnaquel := articulosRecord(x).numMaximo;
                                 END IF;

                               ELSE
                                 vn_numTopeAnaquel := articulosRecord(x).numLane60;
                               END IF;

                               IF (vb_Asignado = FALSE) THEN

                                 IF anaquelesRecord.COUNT > 0 THEN

                                   FOR i IN anaquelesRecord.FIRST .. (anaquelesRecord.LAST - 1) LOOP
                                     vnNumOrdenActual        := anaquelesRecord(i).num_Orden;
                                     vnNumOrdenSiguiente     := anaquelesRecord(i+1).num_Orden;
                                     vnIndExpandidoSiguiente := anaquelesRecord(i+1).ind_Expa;

                                     IF (vb_FlagValorExp = FALSE) THEN

                                       vnIndExpandidoPrimero := anaquelesRecord(i).ind_Expa;
                                       vb_FlagValorExp       := TRUE;

                                     END IF;

                                     IF (vb_Asignado = FALSE) THEN
                                       -- Se verifica que el Primer Anaquel sea Expandido
                                       IF (vb_FlagIndExp = FALSE AND vnIndExpandidoPrimero = 1) THEN

                                         vn_contAnaquelExpa := vn_contAnaquelExpa + 1;
                                         vb_FlagIndExp := TRUE;

                                       END IF;

                                       -- Se verifica que el Anaquel Siguiente sea Expandido
                                       IF (vnIndExpandidoSiguiente = 1) THEN
                                         vn_contAnaquelExpa := vn_contAnaquelExpa + 1;
                                       END IF;

                                       -- Se verifican que los anaqueles sean continuos
                                       -- Para esto los chanel deben de ser consecutivos y deben tener el mismo frame
                                       IF (ABS(vnNumOrdenSiguiente - vnNumOrdenActual) = 1) THEN

                                         vn_contAnaquelConsec := vn_contAnaquelConsec + 1;

                                         IF (vn_PosicionInicialSideB = 0) THEN
                                           vn_PosicionInicialSideB := i;
                                         END IF;

                                       ELSE
                                         vn_PosicionInicialSideB := 0;
                                         vn_contAnaquelConsec    := 0;
                                         vb_FlagIndExp           := FALSE;
                                         vn_contAnaquelExpa      := 0;
                                         vnIndExpandidoPrimero   := 0;

                                       END IF;

                                       IF ((vn_contAnaquelConsec + 1) = (vn_numTopeAnaquel + vn_contAnaquelExpa)) THEN

                                         vb_Asignado := TRUE;
                                         vn_PosicionFinalSideB:= vn_PosicionInicialSideB + vn_contAnaquelConsec;
                                         EXIT;

                                       END IF;

                                     END IF;

                                   END LOOP;

                                   IF ( vb_Asignado = TRUE ) THEN

                                     FOR j IN vn_PosicionInicialSideB .. vn_PosicionFinalSideB LOOP
                                       UPDATE ape_asign_produ_anaqu_detal
                                          SET prod_oid_prod = articulosRecord(x).oidProducto,
                                              num_unida     = articulosRecord(x).numUnidades,
                                              prce_oid_proc = ln_oidProBalanceo
                                        WHERE oid_asig_prod_anaq  = anaquelesRecord(j).numAnaquel
                                          AND anaquelesRecord(j).ind_Expa = 0;
                                     END LOOP;

                                   END IF;

                                 END IF;

                               END IF;

                             EXIT WHEN (cListaAnaquelesB%NOTFOUND);
                           END LOOP;
                         CLOSE cListaAnaquelesB;

                       END IF;

                     END IF;

                   EXIT WHEN (cListaTipoAnaqueles%NOTFOUND);
                 END LOOP;
               CLOSE cListaTipoAnaqueles;

             END LOOP;

           END IF;

         EXIT WHEN (cListaArticulos%NOTFOUND);
       END LOOP;
     CLOSE cListaArticulos;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_BALAN_AFRAM: '||ls_sqlerrm);
 END APE_PR_GENER_BALAN_AFRAM;

/**************************************************************************
     Descripcion       : Genera el Balanceo x Tipo de Anaquel
     Fecha Creacion    : 21/09/2010
     Autor             : Jose Luis Rodriguez
     Modificado por    : Nicolas Lopez
     Fecha Modificacion: 15/11/2010
***************************************************************************/
 PROCEDURE APE_PR_GENER_BALAN_ANAQU (
   p_oidMapaCentroDistribucion    NUMBER,
   p_oidPeriodo                   NUMBER,
   p_oidLinea                     NUMBER,
   p_oidSubLinea                  NUMBER,
   p_codOrdeProducto              VARCHAR2,
   p_oidMapaZona                  NUMBER,
   p_oidMapaOrden                 NUMBER,
   p_oidVersion                   NUMBER,
   p_numAnaqueles                 OUT NUMBER
 )
 IS
   vn_conNoAsignados          NUMBER;
   vn_oidTipoAnaquel          NUMBER;
   vn_numEstimado             NUMBER;
   vn_oidAsignacionDetalle    NUMBER;
   vn_oidProducto             NUMBER;
   vb_Asignado                BOOLEAN;
   vn_validaCantidad          NUMBER;

   CURSOR cListaArticulosAsc  IS
     SELECT producto.oid_prod         OID_PRODUCTO,
            estimado.num_unid_esti    NUM_ESTIMADO
       FROM ape_estim_produ estimado,
            ape_linea_armad linea,
            mae_produ producto
      WHERE estimado.liar_oid_line_arma = p_oidLinea
        AND estimado.perd_oid_peri = p_oidPeriodo
        AND producto.oid_prod = estimado.prod_oid_prod
        AND ((linea.num_line_afp = '1' AND producto.cod_ind_dent_caja IN ('F','B'))
         OR (linea.num_line_afp = '0' AND (producto.cod_ind_dent_caja NOT IN ('F','B') OR producto.cod_ind_dent_caja IS NULL)))
        AND linea.oid_line_arma = estimado.liar_oid_line_arma
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND detalle.prod_oid_prod = producto.oid_prod)
     ORDER BY estimado.num_unid_esti ASC;

   CURSOR cListaArticulosDes  IS
     SELECT producto.oid_prod         OID_PRODUCTO,
            estimado.num_unid_esti    NUM_ESTIMADO
       FROM ape_estim_produ estimado,
            ape_linea_armad linea,
            mae_produ producto
      WHERE estimado.liar_oid_line_arma = p_oidLinea
        AND estimado.perd_oid_peri = p_oidPeriodo
        AND producto.oid_prod = estimado.prod_oid_prod
        AND ((linea.num_line_afp = '1' AND producto.cod_ind_dent_caja IN ('F','B'))
         OR (linea.num_line_afp = '0' AND (producto.cod_ind_dent_caja NOT IN ('F','B') OR producto.cod_ind_dent_caja IS NULL)))
        AND linea.oid_line_arma = estimado.liar_oid_line_arma
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_cabec cabecera,
                               ape_asign_produ_anaqu_detal detalle
                         WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                           AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                           AND detalle.prod_oid_prod = producto.oid_prod)
     ORDER BY estimado.num_unid_esti DESC;

   CURSOR cListaTipoAnaqueles(oidProducto NUMBER) IS
     SELECT  proanaquel.tian_oid_tipo_anaq  OID_TIPO_ANAQUEL
       FROM  ape_produ_anaqu proanaquel
      WHERE  proanaquel.prod_oid_prod = oidProducto
      ORDER BY proanaquel.num_nume_prio;

 BEGIN

   vn_conNoAsignados := 0;

   -- Se borra el producto de la asignacion anterior
   UPDATE ape_asign_produ_anaqu_detal
      SET prod_oid_prod = NULL
    WHERE oid_asig_prod_anaq IN ( SELECT detalle.oid_asig_prod_anaq
                                    FROM ape_asign_produ_anaqu_cabec cabecera,
                                         ape_asign_produ_anaqu_detal detalle,
                                         ape_mapa_centr_distr_detal  mapa
                                   WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                     AND detalle.prce_oid_proc = ln_oidProBalanceo
                                     AND mapa.sbar_oid_subl_arma = p_oidSubLinea
                                     AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                     AND mapa.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                     AND cabecera.perd_oid_peri = p_oidPeriodo
                                     AND detalle.prod_oid_prod IS NOT NULL);

   -- Si esta ordenado de forma Ascendente
   IF ( p_codOrdeProducto = 'A') THEN

     OPEN cListaArticulosAsc;
       LOOP
         FETCH cListaArticulosAsc INTO vn_oidProducto, vn_numEstimado;
         EXIT WHEN (cListaArticulosAsc%NOTFOUND);

         vb_Asignado := FALSE;

         OPEN cListaTipoAnaqueles (vn_oidProducto);
           LOOP
             FETCH cListaTipoAnaqueles INTO vn_oidTipoAnaquel;
             EXIT WHEN (cListaTipoAnaqueles%NOTFOUND);

             --Se valida si existe registro
             SELECT COUNT(1)
               INTO vn_validaCantidad
               FROM ape_orden_anaqu_cabec ordencabecera,
                    ape_orden_anaqu_detal ordendetalle,
                    ape_mapa_centr_distr_detal mapadetalle,
                    ape_subli_armad sublinea,
                    ape_asign_produ_anaqu_detal asignaciondetalle,
                    ape_asign_produ_anaqu_cabec asigcab
              WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
                AND sublinea.oid_subl_arma = p_oidSubLinea
                AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
                AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
                AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
                AND ordenCabecera.liar_oid_line_arma = p_oidLinea
                AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
                AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
                AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
                AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
                AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
                AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asigcab.oid_asig_prod_anaq_cabe
                AND asigcab.perd_oid_peri = p_oidPeriodo
                AND ROWNUM = 1
                AND NOT EXISTS (SELECT 1
                                  FROM ape_asign_produ_anaqu_cabec cabecera,
                                       ape_asign_produ_anaqu_detal detalle
                                 WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                   AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                   AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                   AND detalle.prod_oid_prod IS NOT NULL);

             IF ( vn_validaCantidad > 0 ) THEN

               -- Se obtiene el primer anaquel disponible
               SELECT asignaciondetalle.oid_asig_prod_anaq
                 INTO vn_oidAsignacionDetalle
                 FROM ape_orden_anaqu_cabec ordencabecera,
                      ape_orden_anaqu_detal ordendetalle,
                      ape_mapa_centr_distr_detal mapadetalle,
                      ape_subli_armad sublinea,
                      ape_asign_produ_anaqu_detal asignaciondetalle,
                      ape_asign_produ_anaqu_cabec asigcab
                WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
                  AND sublinea.oid_subl_arma = p_oidSubLinea
                  AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
                  AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
                  AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
                  AND ordenCabecera.liar_oid_line_arma = p_oidLinea
                  AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
                  AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
                  AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
                  AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
                  AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
                  AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asigcab.oid_asig_prod_anaq_cabe
                  AND asigcab.perd_oid_peri = p_oidPeriodo
                  AND ROWNUM = 1
                  AND NOT EXISTS (SELECT 1
                                    FROM ape_asign_produ_anaqu_cabec cabecera,
                                         ape_asign_produ_anaqu_detal detalle
                                   WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                     AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                     AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                     AND detalle.prod_oid_prod IS NOT NULL)
                ORDER BY mapadetalle.num_anaq;

             END IF;

             IF ( vn_oidAsignacionDetalle > 0 ) THEN

               IF ( vb_Asignado = FALSE) THEN

                 UPDATE ape_asign_produ_anaqu_detal
                    SET prod_oid_prod = vn_oidProducto,
                        prce_oid_proc = ln_oidProBalanceo,
                        num_unida     = vn_numEstimado
                  WHERE oid_asig_prod_anaq = vn_oidAsignacionDetalle;

                 vb_Asignado := TRUE;

               END IF;

             END IF;

           END LOOP;
         CLOSE cListaTipoAnaqueles;

         IF (vb_Asignado = FALSE) THEN
           vn_conNoAsignados := vn_conNoAsignados + 1;
         END IF;

       END LOOP;
     CLOSE cListaArticulosAsc;

   END IF;

   -- Si esta ordenado de forma Descendente
   IF ( p_codOrdeProducto = 'D') THEN

     OPEN cListaArticulosDes;
       LOOP
         FETCH cListaArticulosDes INTO vn_oidProducto, vn_numEstimado;
         EXIT WHEN (cListaArticulosDes%NOTFOUND);

         vb_Asignado := FALSE;

         OPEN cListaTipoAnaqueles (vn_oidProducto);
           LOOP
             FETCH cListaTipoAnaqueles INTO vn_oidTipoAnaquel;
             EXIT WHEN (cListaTipoAnaqueles%NOTFOUND);

             --Se valida si existe registro
             SELECT COUNT(1) INTO vn_validaCantidad
               FROM ape_orden_anaqu_cabec ordencabecera,
                    ape_orden_anaqu_detal ordendetalle,
                    ape_mapa_centr_distr_detal mapadetalle,
                    ape_subli_armad sublinea,
                    ape_asign_produ_anaqu_detal asignaciondetalle,
                    ape_asign_produ_anaqu_cabec asigcab
              WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
                AND sublinea.oid_subl_arma = p_oidSubLinea
                AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
                AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
                AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
                AND ordenCabecera.liar_oid_line_arma = p_oidLinea
                AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
                AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
                AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
                AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
                AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
                AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asigcab.oid_asig_prod_anaq_cabe
                AND asigcab.perd_oid_peri = p_oidPeriodo
                AND ROWNUM = 1
                AND NOT EXISTS (SELECT 1
                                  FROM ape_asign_produ_anaqu_cabec cabecera,
                                       ape_asign_produ_anaqu_detal detalle
                                 WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                   AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                   AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                   AND detalle.prod_oid_prod IS NOT NULL);

             IF (vn_validaCantidad>0) THEN

               -- Se obtiene el primer anaquel disponible
               SELECT asignaciondetalle.oid_asig_prod_anaq
                 INTO vn_oidAsignacionDetalle
                 FROM ape_orden_anaqu_cabec ordencabecera,
                      ape_orden_anaqu_detal ordendetalle,
                      ape_mapa_centr_distr_detal mapadetalle,
                      ape_subli_armad sublinea,
                      ape_asign_produ_anaqu_detal asignaciondetalle,
                      ape_asign_produ_anaqu_cabec asigcab
                WHERE mapadetalle.mcdc_oid_mapa_cent_dist_cabe = p_oidMapaCentroDistribucion
                  AND sublinea.oid_subl_arma = p_oidSubLinea
                  AND mapadetalle.tian_oid_tipo_anaq = vn_oidTipoAnaquel
                  AND ordencabecera.mzca_oid_mapa_zona_cabe = p_oidMapaZona
                  AND ordencabecera.oid_anaq_cabe = p_oidMapaOrden
                  AND ordenCabecera.liar_oid_line_arma = p_oidLinea
                  AND sublinea.oid_subl_arma = mapadetalle.sbar_oid_subl_arma
                  AND ordenCabecera.oid_anaq_cabe = ordendetalle.oaca_oid_anaq_cabe
                  AND ordencabecera.liar_oid_line_arma = sublinea.liar_oid_line_arma
                  AND mapadetalle.oid_mapa_cent_dist_deta = ordendetalle.mcdd_oid_mapa_cent_dist_deta
                  AND mapadetalle.oid_mapa_cent_dist_deta = asignaciondetalle.mcdd_oid_mapa_cent_dist_deta
                  AND asignaciondetalle.apac_oid_asig_prod_anaq_cabe = asigcab.oid_asig_prod_anaq_cabe
                  AND asigcab.perd_oid_peri = p_oidPeriodo
                  AND ROWNUM = 1
                  AND NOT EXISTS (SELECT 1
                                    FROM ape_asign_produ_anaqu_cabec cabecera,
                                         ape_asign_produ_anaqu_detal detalle
                                   WHERE cabecera.oid_asig_prod_anaq_cabe = p_oidVersion
                                     AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                     AND mapadetalle.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                     AND detalle.prod_oid_prod IS NOT NULL)
               ORDER BY mapadetalle.num_anaq;

             END IF;

             IF ( vn_oidAsignacionDetalle > 0 ) THEN

               IF ( vb_Asignado = FALSE) THEN

                 UPDATE ape_asign_produ_anaqu_detal
                    SET prod_oid_prod = vn_oidProducto,
                        prce_oid_proc = ln_oidProBalanceo,
                        num_unida    = vn_numEstimado
                  WHERE oid_asig_prod_anaq = vn_oidAsignacionDetalle;

                 vb_Asignado := TRUE;

               END IF;

             END IF;

           END LOOP;
         CLOSE cListaTipoAnaqueles;

         IF (vb_Asignado = FALSE) THEN
           vn_conNoAsignados := vn_conNoAsignados + 1;
         END IF;

       END LOOP;
     CLOSE cListaArticulosDes;

   END IF;

   p_numAnaqueles := vn_conNoAsignados;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_BALAN_ANAQU: '||ls_sqlerrm);
 END APE_PR_GENER_BALAN_ANAQU;

/**************************************************************************
     Descripcion       : Preasignacion Aframe
     Autor             : Christian Gonzales
     Fecha Creacion    : 23/09/2010
     Parametros Entrada :
            p_oid_mapa_cent
            p_oid_sublinea
            p_oid_peri_origen
            p_oid_peri_destino
            p_val_version_destino
            p_cod_fuente
            p_linea_armado
 ***************************************************************************/
 PROCEDURE APE_PR_PREAS_AFRAM (
   p_oid_mapa_cent          NUMBER,
   p_linea_armado           NUMBER,
   p_oid_sublinea           NUMBER,
   cod_peri_origen          NUMBER,
   cod_peri_destino         NUMBER,
   p_val_version_origen     VARCHAR2,
   p_val_version_destino    VARCHAR2,
   p_cod_fuente             VARCHAR2
 )
 IS
   p_oid_peri_origen         NUMBER;
   p_oid_peri_destino        NUMBER;
   p_oid_presignacion        ape_proce.oid_proc %TYPE;
   p_oid_estimado            ape_proce.oid_proc %TYPE;
   p_oid_balanceado          ape_proce.oid_proc %TYPE;
   v_oid_asig_prod_anaq_d    ape_asign_produ_anaqu_detal.oid_asig_prod_anaq  %TYPE;
   v_oid_mapacd_d            ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE;
   v_counter                 INT;
   v_count_prod_anaqueles    INT;
   v_count_prod              INT;
   v_count_update            INT;
   v_lado                    ape_mapa_centr_distr_detal.VAL_SIDE%TYPE;
   v_count_anaqueles         INT;
   v_total_asignado          INT;
   v_cantidad_chanel         INT;
   W_FILAS                   NUMBER := 1000;

   TYPE tmptablaPreasigAframe IS RECORD(
     unidades_estimadas    ape_estim_produ.num_unid_esti %TYPE,
     oid_prod              ape_asign_produ_anaqu_detal.prod_oid_prod %TYPE,
     nro_channel_95        NUMBER,
     nro_channel_60        NUMBER
   );

   TYPE tablaPreasigAframe IS TABLE OF tmptablaPreasigAframe;
   tablaPreasigAframerecord tablaPreasigAframe;

   -- Se obtienen los productos validados para la preasignacion
   CURSOR preasign_prod_aframe(v_oid_peri_origen NUMBER, v_oid_peri_destino NUMBER) IS
     SELECT estimado.num_unid_esti,
            producto.prod_oid_prod,
            APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, producto.num_hora_inve, producto.num_lane_capa_95),
            APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, producto.num_hora_inve, producto.num_lane_capa_60)
       FROM ape_estim_produ estimado,
            ape_produ producto
      WHERE estimado.prod_oid_prod = producto.prod_oid_prod
        AND estimado.perd_oid_peri = v_oid_peri_destino  --   PERIODO DESTINO
        AND estimado.liar_oid_line_arma = p_linea_armado   -- LINEA ARMADO
        AND producto.tidi_oid_tipo_dispe = 2
        AND EXISTS ( SELECT 1
                       FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
                            ape_asign_produ_anaqu_cabec asignprodanaq_cab,
                            ape_subli_armad sublinea,
                            ape_mapa_centr_distr_detal  mapacd_det
                      WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
                        AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
                        AND asignprodanaq_cab.perd_oid_peri = v_oid_peri_origen  -- PERIODO ANTERIOR
                        AND asignprodanaq_cab.val_vers = p_val_version_origen      -- VERSION ANTERIOR
                        AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea  --    SUBLINEA
                        AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent   -- OID_MAPA CD
                        AND asignprodanaq_det.prod_oid_prod = producto.prod_oid_prod
                   GROUP BY asignprodanaq_det.prod_oid_prod, mapacd_det.val_side
                  /* el producto esta asignado en la campa?a anterior al lado "A"
                     el numero de anaqueles se debe comparar con el campo Unid_Chn_95
                     y si el producto esta asignado en la campa?a anterior al lado "B"
                     el numero de anaqueles se debe comparar con el campo Unid_Chn_60 */
                    HAVING((val_side = 'A' AND COUNT(mapacd_det.num_anaq) >= APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, producto.num_hora_inve, producto.num_lane_capa_95))
                        OR (val_side = 'B' AND COUNT(mapacd_det.num_anaq) >= APE_PKG_PROCE.APE_FN_DEVUE_NUMER_CHANE(estimado.num_unid_esti, producto.num_hora_inve, producto.num_lane_capa_60))));

   CURSOR preasign_anaqueles(v_oid_prod NUMBER, v_oid_peri_origen NUMBER, v_oid_peri_destino NUMBER) IS
     SELECT mapacd_det.oid_mapa_cent_dist_deta
       INTO v_oid_mapacd_d
       FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
            ape_asign_produ_anaqu_cabec asignprodanaq_cab,
            ape_mapa_centr_distr_detal mapacd_det
      WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
        AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = asignprodanaq_cab.mcdc_oid_mapa_cent_dist_cabe
        AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
        AND asignprodanaq_cab.perd_oid_peri = v_oid_peri_origen
        AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
        AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea
        AND mapacd_det.ind_expa = 0 -- FALSE
        AND asignprodanaq_det.prod_oid_prod = v_oid_prod
        AND asignprodanaq_cab.val_vers = p_val_version_origen
        AND NOT EXISTS (SELECT 1
                          FROM ape_asign_produ_anaqu_detal apad,
                               ape_asign_produ_anaqu_cabec apac
                         WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                           AND apad.prod_oid_prod = asignprodanaq_det.prod_oid_prod
                           AND apac.val_vers = p_val_version_destino
                           AND apac.perd_oid_peri = v_oid_peri_destino-- PERIODO DESTINO
                           AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent); -- OIDMAPACD

 BEGIN
   -- se obtienen los oids de procedencia
   SELECT oid_proc
     INTO p_oid_presignacion
     FROM ape_proce
    WHERE cod_proc = 'P';

   SELECT oid_proc
     INTO p_oid_estimado
     FROM ape_proce
    WHERE cod_proc = 'E';

   SELECT oid_proc
     INTO p_oid_balanceado
     FROM ape_proce
    WHERE cod_proc = 'B';

   -- se obtiente los oids periodos
   p_oid_peri_origen :=  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(cod_peri_origen);
   p_oid_peri_destino := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(cod_peri_destino);

   -- Se limpia lo preasignado o balanceado anteriormente
   UPDATE ape_asign_produ_anaqu_detal
      SET prod_oid_prod = NULL,
          num_unida = 0,
          prce_oid_proc = p_oid_estimado
    WHERE oid_asig_prod_anaq IN ( SELECT detalle.oid_asig_prod_anaq
                                    FROM ape_asign_produ_anaqu_cabec cabecera,
                                         ape_asign_produ_anaqu_detal detalle,
                                         ape_mapa_centr_distr_detal mapa
                                   WHERE cabecera.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                                     AND cabecera.val_vers = p_val_version_destino
                                     AND detalle.prce_oid_proc in (p_oid_presignacion, p_oid_balanceado)
                                     AND mapa.sbar_oid_subl_arma = p_oid_sublinea
                                     AND cabecera.oid_asig_prod_anaq_cabe = detalle.apac_oid_asig_prod_anaq_cabe
                                     AND mapa.oid_mapa_cent_dist_deta = detalle.mcdd_oid_mapa_cent_dist_deta
                                     AND cabecera.perd_oid_peri = p_oid_peri_destino
                                     AND detalle.prod_oid_prod IS NOT NULL);

   OPEN PREASIGN_PROD_AFRAME(p_oid_peri_origen, p_oid_peri_destino);
     -- la fuente del periodo de destino puede ser E => 'Estimados' o F => facturacion proyectada
     IF p_cod_fuente = 'E' THEN

       LOOP
         FETCH PREASIGN_PROD_AFRAME BULK COLLECT INTO tablaPreasigAframerecord LIMIT W_FILAS;

           IF tablaPreasigAframerecord.COUNT > 0 THEN

             FOR x IN tablaPreasigAframerecord.FIRST .. tablaPreasigAframerecord.LAST LOOP
               -- Se valida que el producto se encuentre asignado en el periodo anterior y no
               -- este asignado en el periodo actual
               SELECT COUNT(1)
                 INTO v_counter
                 FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
                      ape_asign_produ_anaqu_cabec asignprodanaq_cab,
                      ape_mapa_centr_distr_detal mapacd_det
                WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
                  AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = asignprodanaq_cab.mcdc_oid_mapa_cent_dist_cabe
                  AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
                  AND asignprodanaq_cab.perd_oid_peri = p_oid_peri_origen  -- periodo origen
                  AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                  AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea
                  AND mapacd_det.ind_expa = 0 -- FALSE
                  AND asignprodanaq_det.prod_oid_prod = tablaPreasigAframerecord(x).oid_prod
                  AND asignprodanaq_cab.val_vers = p_val_version_origen
                  AND NOT EXISTS (SELECT 1
                                    FROM ape_asign_produ_anaqu_detal apad,
                                         ape_asign_produ_anaqu_cabec apac
                                   WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                                     AND apad.prod_oid_prod = asignprodanaq_det.prod_oid_prod
                                     AND apac.val_vers = p_val_version_destino
                                     AND apac.perd_oid_peri = p_oid_peri_destino-- PERIODO DESTINO
                                     AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent); -- OIDMAPACD

               IF v_counter > 0 THEN
                 -- Valida si la cantidad de chaneles es apta para la preasignacion
                 -- Se obtiene el lado (A o B) y la cantidad producto asignados
                 SELECT mapacd_det.val_side,
                        COUNT(mapacd_det.num_anaq)
                   INTO v_lado,
                        v_total_asignado
                   FROM ape_asign_produ_anaqu_detal asignprodanaq_det,
                        ape_asign_produ_anaqu_cabec asignprodanaq_cab,
                        ape_subli_armad sublinea,
                        ape_mapa_centr_distr_detal mapacd_det
                  WHERE asignprodanaq_cab.oid_asig_prod_anaq_cabe = asignprodanaq_det.apac_oid_asig_prod_anaq_cabe
                    AND mapacd_det.oid_mapa_cent_dist_deta = asignprodanaq_det.mcdd_oid_mapa_cent_dist_deta
                    AND asignprodanaq_cab.perd_oid_peri = p_oid_peri_origen  -- PERIODO ANTERIOR
                    AND asignprodanaq_cab.val_vers = p_val_version_origen      -- VERSION ANTERIOR
                    AND mapacd_det.sbar_oid_subl_arma = p_oid_sublinea  --    SUBLINEA
                    AND mapacd_det.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent   -- OID_MAPA CD
                    AND asignprodanaq_det.prod_oid_prod = tablaPreasigAframerecord(x).oid_prod
                  GROUP BY mapacd_det.val_side;

                 -- Se obtiene la cantidad de anaqueles con productos en el periodo actual
                 SELECT COUNT(apad_actual.mcdd_oid_mapa_cent_dist_deta)
                   INTO v_count_prod_anaqueles
                   FROM ape_asign_produ_anaqu_cabec apac_actual,
                        ape_asign_produ_anaqu_detal apad_actual
                  WHERE apac_actual.oid_asig_prod_anaq_cabe = apad_actual.apac_oid_asig_prod_anaq_cabe
                    AND apac_actual.perd_oid_peri =  p_oid_peri_destino
                    AND apac_actual.val_vers = p_val_version_destino
                    AND apac_actual.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                    AND apad_actual.prod_oid_prod IS NOT NULL
                    AND apac_actual.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                    AND EXISTS (SELECT 1
                                  FROM ape_asign_produ_anaqu_detal apad,
                                       ape_asign_produ_anaqu_cabec apac
                                 WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                                   AND apad_actual.mcdd_oid_mapa_cent_dist_deta= apad.mcdd_oid_mapa_cent_dist_deta
                                   AND apac.val_vers = p_val_version_origen
                                   AND apac.perd_oid_peri = p_oid_peri_origen
                                   ANd apad.prod_oid_prod = tablaPreasigAframerecord(x).oid_prod
                                   AND apac.mcdc_oid_mapa_cent_dist_cabe = apac_actual.mcdc_oid_mapa_cent_dist_cabe);

                 /*Se valida que la cantidad de chaneles disponibles (incluyendo CHANELES VACIOS)
                   sea mayor o igual que la cantidad de chaneles a asignar
                   ejemplo:
                   (cantidad de chaneles disponibles) <= (chaneles asignados en el periodo anterior) - (cantidad de chaneles con productos en el periodo actual)
                 */
                 IF v_lado = 'A' THEN
                   v_cantidad_chanel := tablaPreasigAframerecord(x).nro_channel_95;
                 ELSE
                   v_cantidad_chanel := tablaPreasigAframerecord(x).nro_channel_60;
                 END IF;

                 IF v_cantidad_chanel <= (v_total_asignado - v_count_prod_anaqueles) THEN

                   v_count_anaqueles := 0;
                   v_count_update := 0;

                   OPEN PREASIGN_ANAQUELES(tablaPreasigAframerecord(x).oid_prod, p_oid_peri_origen, p_oid_peri_destino);
                     LOOP
                       FETCH PREASIGN_ANAQUELES INTO v_oid_mapacd_d;
                       EXIT WHEN (PREASIGN_ANAQUELES%NOTFOUND);

                       -- en nro de actualizaciones debe ser igual al nro de chaneles
                       IF  v_count_anaqueles < v_cantidad_chanel THEN

                         -- Valida de que no existan productos asignados y obtiene el oid de asignacion detalle
                         SELECT apad.oid_asig_prod_anaq,
                                COUNT(apad.prod_oid_prod)
                           INTO v_oid_asig_prod_anaq_d,
                                v_count_prod
                           FROM ape_asign_produ_anaqu_cabec apac,
                                ape_asign_produ_anaqu_detal apad
                          WHERE apac.oid_asig_prod_anaq_cabe = apad.apac_oid_asig_prod_anaq_cabe
                            AND apad.mcdd_oid_mapa_cent_dist_deta = v_oid_mapacd_d
                            AND apac.perd_oid_peri = p_oid_peri_destino
                            AND apac.val_vers = p_val_version_destino
                            AND apac.mcdc_oid_mapa_cent_dist_cabe = p_oid_mapa_cent
                          GROUP BY apad.oid_asig_prod_anaq;

                         IF v_count_prod = 0 THEN

                           IF v_count_update = 0 THEN
                             -- v_count_update valida de que solo en el primer anaquel guarde la cantidad estimada
                             UPDATE ape_asign_produ_anaqu_detal
                                SET num_unida = tablaPreasigAframerecord(x).unidades_estimadas,
                                    prce_oid_proc = p_oid_presignacion,
                                    prod_oid_prod = tablaPreasigAframerecord(x).oid_prod
                              WHERE oid_asig_prod_anaq = v_oid_asig_prod_anaq_d;

                             v_count_update := v_count_update + 1;

                           ELSE

                             UPDATE ape_asign_produ_anaqu_detal
                                SET prce_oid_proc = p_oid_presignacion,
                                    prod_oid_prod = tablaPreasigAframerecord(x).oid_prod
                              WHERE oid_asig_prod_anaq = v_oid_asig_prod_anaq_d;

                           END IF;

                         END IF;

                         v_count_anaqueles := v_count_anaqueles + 1;

                       END IF;

                     END LOOP;
                   CLOSE PREASIGN_ANAQUELES;

                 END IF;

               END IF;

             END LOOP;

           END IF;

         EXIT WHEN  PREASIGN_PROD_AFRAME%NOTFOUND;
       END LOOP;

     END IF;

   CLOSE PREASIGN_PROD_AFRAME;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_PREASIG_AFRAM: '||ls_sqlerrm);
 END APE_PR_PREAS_AFRAM;

/**************************************************************************
     Descripcion       : Actualizar Seguimiento de pedidos
     Autor             : Christian Gonzales
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
            p_oid_solicitud
            p_oid_consolidado
            p_cod_hito
            p_nro_doc
            p_fecha
            p_ind_completo
 ***************************************************************************/
 PROCEDURE PED_ACTUA_SEGUI_PEDID (
   p_oid_solicitud      IN NUMBER,
   p_oid_consolidado    IN NUMBER,
   p_cod_hito           IN VARCHAR2,
   p_nro_doc            IN VARCHAR2,
   p_fecha              IN DATE,
   p_ind_completo       IN NUMBER
 )
 AS
   v_tipo_documento    NUMBER;
   v_num_hito          NUMBER;
   v_oid_hito          NUMBER;
   v_ind_completo      NUMBER;
   v_fecha             DATE;

 BEGIN
   -- Obtenemos el nro de hito, retirando el cero de la izquierda si es que tiene
   -- Obtenemos la fecha, si es null, la fecha es sysdate
   SELECT TO_NUMBER(p_cod_hito),
          NVL(p_fecha, sysdate)
     INTO v_num_hito,
          v_fecha
     FROM dual;

   IF v_num_hito < 5 THEN
     -- Tipo de documento: PEDIDO
     v_tipo_documento := 1;
   ELSIF v_num_hito >= 5 AND v_num_hito < 10 THEN
     -- Tipo de documento: CONSOLIDADO
     v_tipo_documento := 2;
   ELSIF v_num_hito >= 10 AND v_num_hito <= 15 THEN
     -- Tipo de documento: LISTA PICADO
     v_tipo_documento := 3;
   ELSE
     -- Tipo de documento: ORDEN DE TRANSPORTE
     v_tipo_documento := 4;
   END IF;

   IF v_num_hito < 5 THEN
     -- 1 = TRUE
     v_ind_completo := 1;
   ELSE
     v_ind_completo :=  p_ind_completo;
   END IF;

   -- Se obtiene el oid del hito
   SELECT oid_hito
     INTO v_oid_hito
     FROM ped_hito_segui
    WHERE cod_hito = p_cod_hito;

   INSERT INTO ped_segui_pedid(
     oid_segu_pedi,
     soca_oid_soli_cabe,
     soca_oid_cons,
     tdse_oid_tipo,
     hise_oid_hito,
     fec,
     num_docu,
     ind_comp
   )
   VALUES(
     ped_sepe_seq.Nextval,
     p_oid_solicitud,
     p_oid_consolidado,
     v_tipo_documento,
     v_oid_hito,
     v_fecha,
     p_nro_doc,
     v_ind_completo
   );

   IF v_num_hito = 5 THEN

     UPDATE ped_segui_pedid
        SET soca_oid_cons = p_oid_consolidado
      WHERE hise_oid_hito < 5
        AND soca_oid_soli_cabe = (SELECT oid_soli_cabe
                                    FROM ped_solic_cabec
                                   WHERE soca_oid_soli_cabe = p_oid_consolidado);

   END IF;

 END PED_ACTUA_SEGUI_PEDID;

/**************************************************************************
     Descripcion       : Procesa Caja Terminada de  Embalaje en WCS
     Autor             : Christian Gonzales
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
          p_oid_pais
          p_oid_marca
          p_oid_centro
          p_oid_linea
          p_num_consolidad
          p_num_caja
          p_indicador_armado
 ***************************************************************************/
 PROCEDURE APE_PR_PROCE_CAJA_TERMI_EMBAL (
   p_cod_pais            IN VARCHAR2,
   p_oid_marca           IN VARCHAR2,
   p_oid_centro          IN VARCHAR2,
   p_oid_linea           IN VARCHAR2,
   p_num_consolidad      IN VARCHAR2,
   p_num_caja            IN VARCHAR2,
   p_indicador_armado    IN VARCHAR2
 )
 AS
   v_count_valid_embalar         INT;
   v_count_pedidos               INT;
   v_count_pedidos_recibidos     INT;
   v_count_pedidos_chequeados    INT;
   v_oid_soli_cabe               ape_etiqu.soca_oid_soli_cabe%TYPE;
   v_oid_consolidado             ped_solic_cabec.soca_oid_soli_cabe%TYPE;
   v_cod_lista_picado            ape_lista_picad_cabec.cod_list_pica%TYPE;
   v_oid_pais                    ped_solic_cabec.pais_oid_pais%TYPE;

 BEGIN

   v_oid_pais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);

   -- conteo de los pedidos por caja para actualizar el estado del pedido ---------------------
   SELECT COUNT(*)
     INTO v_count_valid_embalar
     FROM ped_solic_cabec a,
          ape_etiqu b,
          ape_lista_picad_cabec c
    WHERE a.pais_oid_pais = v_oid_pais
      AND a.val_nume_soli = SUBSTR(SUBSTR(to_char(sysdate,'YYYY'),3)||p_num_consolidad,1,
                                     LENGTH(SUBSTR(to_char(sysdate,'YYYY'),3)||p_num_consolidad)-3 )
      AND b.num_caja = to_number(p_num_caja)
      AND b.soca_oid_soli_cabe = a.oid_soli_cabe
      AND a.oid_soli_cabe = c.soca_oid_soli_cabe;

   -- Si el contador es mayor que cero, se actualiza el estado del pedido
   IF (v_count_valid_embalar > 0) THEN

    SELECT a.oid_soli_cabe,
           a.oid_soli_cabe,
           c.cod_list_pica
      INTO v_oid_soli_cabe,
           v_oid_consolidado,
           v_cod_lista_picado
      FROM ped_solic_cabec a,
           ape_etiqu b,
           ape_lista_picad_cabec c
     WHERE a.pais_oid_pais = v_oid_pais
       AND a.val_nume_soli = SUBSTR(SUBSTR(to_char(sysdate,'YYYY'),3)||p_num_consolidad,1,
                                     LENGTH(SUBSTR(to_char(sysdate,'YYYY'),3)||p_num_consolidad)-3 )
       AND b.num_caja = to_number(p_num_caja)
       AND b.soca_oid_soli_cabe = a.oid_soli_cabe
       AND a.oid_soli_cabe = c.soca_oid_soli_cabe;

     UPDATE ape_etiqu
        SET estp_oid_esta_pedi = to_number(p_indicador_armado)
      WHERE pais_oid_pais = v_oid_pais
        AND soca_oid_soli_cabe = v_oid_soli_cabe
        AND num_caja = to_number(p_num_caja);

   END IF;

   ----- contar pedidos ---------------------------------------------------
   SELECT COUNT(*)
     INTO v_count_pedidos
     FROM ped_solic_cabec a,
          ape_etiqu b,
          ape_lista_picad_cabec c
    WHERE a.pais_oid_pais = v_oid_pais
      AND a.pais_oid_pais = b.pais_oid_pais
      AND a.oid_soli_cabe = b.soca_oid_soli_cabe
      AND a.oid_soli_cabe = c.soca_oid_soli_cabe
      AND a.oid_soli_cabe = v_oid_soli_cabe;

   ----- contar pedidos recibidos ---------------------------------------------------
   ----  ESTADO:  2 ->  Embalado
   SELECT COUNT(*)
     INTO v_count_pedidos_recibidos
     FROM ped_solic_cabec a,
          ape_etiqu b,
          ape_lista_picad_cabec c
    WHERE a.pais_oid_pais = v_oid_pais
      AND a.pais_oid_pais = b.pais_oid_pais
      AND a.oid_soli_cabe = b.soca_oid_soli_cabe
      AND a.oid_soli_cabe = c.soca_oid_soli_cabe
      AND a.oid_soli_cabe = v_oid_soli_cabe
      AND b.estp_oid_esta_pedi = 2;

   -- Si todos los pedidos fueron recibidos, se llama al caso de uso PED-Actualizar Seguimiento de Pedidos
   IF (v_count_pedidos = v_count_pedidos_recibidos AND v_count_pedidos > 0) THEN
     PED_ACTUA_SEGUI_PEDID( v_oid_soli_cabe, v_oid_consolidado, '15', NULL, TO_CHAR(v_cod_lista_picado), 1);
   END IF;

   ------ contar pedidos chequeados ---------------------------------------------------
   ----  ESTADO:  3,4 y 5
   SELECT COUNT(*)
     INTO v_count_pedidos_chequeados
     FROM ped_solic_cabec a,
          ape_etiqu b,
          ape_lista_picad_cabec c
    WHERE a.pais_oid_pais = v_oid_pais
      AND a.pais_oid_pais = b.pais_oid_pais
      AND a.oid_soli_cabe = b.soca_oid_soli_cabe
      AND a.oid_soli_cabe = c.soca_oid_soli_cabe
      AND a.oid_soli_cabe = v_oid_soli_cabe
      AND b.estp_oid_esta_pedi IN (3, 4, 5);

   -- Si todos los pedidos fueron chequeados, se actualiza la tabla PED_SOLIC_CABE
   IF (v_count_pedidos =  v_count_pedidos_chequeados AND v_count_pedidos > 0) THEN

     UPDATE ped_solic_cabec ped
        SET ped.inre_oid_indi_revi = 2
      WHERE ped.recq_oid_resu_cheq = (SELECT oid_resu_cheq
                                        FROM rec_resul_chequ
                                       WHERE cod_resu_cheq = 'OK')
        AND ped.oid_soli_cabe = v_oid_soli_cabe;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_PROCE_CAJA_TERMI_EMBAL: '||ls_sqlerrm);
 END APE_PR_PROCE_CAJA_TERMI_EMBAL;

/*********************************************************************************
     Descripcion       : Generar reporte de Mapa de Anaqueles con Balanceo Diario
     Autor             : Nicolas Lopez
     Fecha Creacion    : 07/10/2010
     Parametros Entrada :
            psCodigoPais
            psCodigoMarca
            psCodigoCanal
            psCodigoPeriodo
            psCodigoSecuencia
            psCodigoMapaCD
            psCodigoLineaArmado
            psFechaFacturacion
**********************************************************************************/
 PROCEDURE APE_PR_GENER_REPOR_MAPA_ANQBD (
   psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSecuencia      VARCHAR2,
   psCodigoMapaCD         VARCHAR2,
   psCodigoLineaArmado    VARCHAR2,
   psFechaFacturacion     VARCHAR2
 )
 IS
   W_FILAS              NUMBER := 5000;
   p_cod_sap_ante       mae_produ.cod_sap %TYPE := NULL;
   p_cod_sap            mae_produ.cod_sap %TYPE;
   lnIdPais             NUMBER;
   lnIdMarca            NUMBER;
   lnIdCanal            NUMBER;
   lnIdCentro           NUMBER;
   lnIdPeriodo          NUMBER;
   lnIdMapaCD           NUMBER;
   lnLineaArmado        NUMBER;
   lnPorcAcumulado      NUMBER;
   lsDescripZona        VARCHAR2(4000);
   lsDescripNegocio     VARCHAR2(4000);
   lnNroBahia           NUMBER;
   lnOidMapaZonaCabe    ape_mapa_zona_cabec.oid_mapa_zona_cabe %TYPE;

   TYPE tmptablaMapaAnaquelBalanceo IS RECORD(
     anaquel            ape_mapa_centr_distr_detal.num_anaq %TYPE,
     producto           mae_produ.cod_sap %TYPE,
     descripcion        gen_i18n_sicc_pais.val_i18n %TYPE,
     codigo_sublinea    ape_subli_armad.cod_subl_arma %TYPE,
     zona               gen_i18n_sicc_pais.val_i18n %TYPE,
     volumen            mae_produ.val_volu %TYPE,
     peso               mae_produ.val_peso %TYPE,
     negocio            gen_i18n_sicc_pais.val_i18n %TYPE,
     oidproducto        ape_asign_produ_anaqu_detal.prod_oid_prod %TYPE,
     oidlineaarmado     ape_linea_armad.oid_line_arma %TYPE,
     codigo_linea       ape_linea_armad.num_codi_line %TYPE,
     oid_mapa_zona      ape_mapa_zona_cabec.oid_mapa_zona_cabe %TYPE,
     oid_nego           mae_produ.nego_oid_nego %TYPE,
     oid_mapacddet      ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta %TYPE,
     oid_subliarma      ape_subli_armad.oid_subl_arma %TYPE
   );

   TYPE tmptablaListaPicadUnidad IS RECORD(
     oidproducto       mae_produ.oid_prod %TYPE,
     num_unidad        ape_lista_picad_detal.num_unid_prod %TYPE,
     porc_prod         NUMBER,
     numerototunida    NUMBER
   );

   TYPE tablaRegMapaAnaquelBalanceo IS TABLE OF tmptablaMapaAnaquelBalanceo;
   tablaRegMapaAnaquelBalrecord tablaRegMapaAnaquelBalanceo;

   TYPE tablaRegListaPicadUnidad IS TABLE OF tmptablaListaPicadUnidad;
   tablaRegListaPicadUnidadrecord tablaRegListaPicadUnidad;

   -- Se obtienen los datos de la primera Lista Mapa Anaqueles con Balanceo Diario
   CURSOR REPOR_MAPA_ANQBD(vn_oid_mapaCD NUMBER, vs_oid_linea VARCHAR2, vn_oid_periodo NUMBER) IS
     SELECT mcddet.num_anaq                   ANAQUEL,
            mprd.cod_sap                      PRODUCTO,
            gen.val_i18n                      DESCRIPCION,
            sublin.cod_subl_arma              CODIGO_SUBLINEA,
            NULL                              ZONA,
            mprd.val_volu                     VOLUMEN,
            mprd.val_peso                     PESO,
            NULL                              NEGOCIO,
            apdet.prod_oid_prod               OIDPRODUCTO,
            sublin.liar_oid_line_arma         OIDLINEAARMADO,
            larm.num_codi_line                CODIGO_LINEA,
            apcab.mzca_oid_mapa_zona_cabe     OID_MAPAZONA,
            mprd.nego_oid_nego                OID_NEGO,
            mcddet.oid_mapa_cent_dist_deta    OID_MAPACDDET,
            sublin.oid_subl_arma              OID_SUBLIARMA
       FROM ape_asign_produ_anaqu_cabec apcab,
            ape_asign_produ_anaqu_detal apdet,
            mae_produ mprd,
            gen_i18n_sicc_pais gen,
            ape_mapa_centr_distr_detal mcddet,
            ape_subli_armad sublin,
            ape_linea_armad larm
      WHERE apcab.oid_asig_prod_anaq_cabe = apdet.apac_oid_asig_prod_anaq_cabe
        AND (apdet.prod_oid_prod = mprd.oid_prod
            AND gen.attr_enti = 'MAE_PRODU'
            AND gen.val_oid = mprd.oid_prod)
        AND apdet.mcdd_oid_mapa_cent_dist_deta = mcddet.oid_mapa_cent_dist_deta
        AND mcddet.sbar_oid_subl_arma = sublin.oid_subl_arma
        AND larm.oid_line_arma = sublin.liar_oid_line_arma
        AND apcab.ind_acti_fact = 'S'
        AND sublin.liar_oid_line_arma = vs_oid_linea
        AND mcddet.mcdc_oid_mapa_cent_dist_cabe = vn_oid_mapaCD
        AND apcab.perd_oid_peri = vn_oid_periodo
      ORDER BY 2,1;

   CURSOR REPOR_LISTA_PICAD_UNIDA(vs_oid_linea VARCHAR2, vs_fecha_factura VARCHAR2) IS
     SELECT DISTINCT apidet.prod_oid_prod                                              OIDPRODUCTO,
            SUM(apidet.num_unid_prod) OVER (partition by apidet.prod_oid_prod)         NUM_UNIDAD,
            SUM(apidet.num_unid_prod) OVER (partition by apidet.prod_oid_prod)/
            SUM(apidet.num_unid_prod) OVER (partition by apicab.liar_oid_line_arma)    PORC_PROD,
            SUM(apidet.num_unid_prod) OVER (partition by apicab.liar_oid_line_arma)    NUMTOTUNIDAD
       FROM ape_lista_picad_cabec apicab,
            ape_lista_picad_detal apidet
      WHERE apicab.oid_list_pica_cabe = apidet.lpca_oid_list_pica_cabe
        AND apicab.liar_oid_line_arma = vs_oid_linea
        AND TRUNC(apicab.fec_factu) = TO_DATE(vs_fecha_factura,'DD/MM/YYYY')
      ORDER BY 2 DESC;

 BEGIN
   /* obteniendo id's */
   lnIdPais        := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
   lnIdMarca       := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);-- id del marca consultante
   lnIdCanal       := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);-- id del canal consultante
   lnIdPeriodo     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,lnIdMarca,lnIdCanal);-- id del periodo consultante
   lnPorcAcumulado := 0.0;

   /* Obtenemos el Oid Mapa Centro de Distribucion */
   SELECT mpcd.oid_mapa_cent_dist_cabe
     INTO lnIdMapaCD
     FROM ape_mapa_centr_distr_cabec mpcd
    WHERE mpcd.num_codi_mapa = psCodigoMapaCD;

   /* Obtenemos el Oid Linea de Armado */
   SELECT aplam.oid_line_arma
     INTO lnLineaArmado
     FROM ape_linea_armad aplam
    WHERE aplam.num_codi_line = psCodigoLineaArmado;

   OPEN REPOR_MAPA_ANQBD(lnIdMapaCD,lnLineaArmado,lnIdPeriodo);
     LOOP
       FETCH REPOR_MAPA_ANQBD BULK COLLECT INTO tablaRegMapaAnaquelBalrecord LIMIT W_FILAS;

         IF tablaRegMapaAnaquelBalrecord.COUNT > 0 THEN

           FOR x IN tablaRegMapaAnaquelBalrecord.FIRST .. tablaRegMapaAnaquelBalrecord.LAST LOOP

             p_cod_sap:= tablaRegMapaAnaquelBalrecord(x).producto;

             IF ( p_cod_sap_ante IS NULL OR p_cod_sap_ante <> p_cod_sap ) THEN
               --Se obtiene el Numero de Bahia
               SELECT TO_NUMBER(SUBSTR(mpcdet.num_anaq,2,2))
                 INTO lnNroBahia
                 FROM ape_mapa_centr_distr_detal mpcdet
                WHERE mpcdet.oid_mapa_cent_dist_deta = tablaRegMapaAnaquelBalrecord(x).oid_mapacddet;

               --Se obtiene la Zona
               SELECT TO_CHAR(mpzcab.cod_mapa_zona)
                 INTO lsDescripZona
                 FROM ape_mapa_zona_detal mpzdet,
                      ape_mapa_zona_cabec mpzcab
                WHERE mpzdet.mzca_oid_mapa_zona_cabe = mpzcab.oid_mapa_zona_cabe
                  AND lnNroBahia > = mpzdet.num_bahi_desd
                  AND lnNroBahia < = mpzdet.num_bahi_hast
                  AND mpzdet.mzca_oid_mapa_zona_cabe = tablaRegMapaAnaquelBalrecord(x).oid_mapa_zona
                  AND mpzdet.sbar_oid_subl_arma = tablaRegMapaAnaquelBalrecord(x).oid_subliarma;

               --Se obtiene la Descripcion del Negocio
               SELECT val_i18n
                 INTO lsDescripNegocio
                 FROM gen_i18n_sicc_pais
                WHERE attr_enti ='MAE_NEGOC'
                  AND val_oid = tablaRegMapaAnaquelBalrecord(x).oid_nego;

               INSERT INTO APE_TMP_REP_MPANQ(
                 num_anaq,
                 cod_sap,
                 desc_prod,
                 cod_subl_arma,
                 desc_zona,
                 desc_vol,
                 val_peso,
                 desc_nego,
                 oid_prod,
                 oid_line_arma,
                 num_codi_line,
                 oid_rep_mapa_anaq
               )
               VALUES(
                 ''|| tablaRegMapaAnaquelBalrecord(x).anaquel || '',
                 ''|| tablaRegMapaAnaquelBalrecord(x).producto || '',
                 ''|| tablaRegMapaAnaquelBalrecord(x).descripcion||'',
                 tablaRegMapaAnaquelBalrecord(x).codigo_sublinea ,
                 ''|| lsDescripZona || '',
                 tablaRegMapaAnaquelBalrecord(x).volumen,
                 tablaRegMapaAnaquelBalrecord(x).peso,
                 ''|| lsDescripNegocio || '',
                 tablaRegMapaAnaquelBalrecord(x).oidproducto,
                 tablaRegMapaAnaquelBalrecord(x).oidlineaarmado,
                 tablaRegMapaAnaquelBalrecord(x).codigo_linea,
                 TO_NUMBER(psCodigoSecuencia)
               );

             END IF;

             p_cod_sap_ante := p_cod_sap;

           END LOOP;

         END IF;

       EXIT WHEN  REPOR_MAPA_ANQBD%NOTFOUND;
     END LOOP;
   CLOSE REPOR_MAPA_ANQBD;

   /* Se recorre la segunda lista*/
   OPEN REPOR_LISTA_PICAD_UNIDA(lnLineaArmado, psFechaFacturacion);
     LOOP
       FETCH REPOR_LISTA_PICAD_UNIDA BULK COLLECT INTO tablaRegListaPicadUnidadrecord LIMIT W_FILAS;

         IF tablaRegListaPicadUnidadrecord.COUNT > 0 THEN

           FOR x IN tablaRegListaPicadUnidadrecord.FIRST .. tablaRegListaPicadUnidadrecord.LAST LOOP
             lnPorcAcumulado := lnPorcAcumulado + tablaRegListaPicadUnidadrecord(x).porc_prod;

             UPDATE ape_tmp_rep_mpanq
                SET num_unida = tablaRegListaPicadUnidadrecord(x).num_unidad,
                    porc_prod = ROUND(tablaRegListaPicadUnidadrecord(x).num_unidad/tablaRegListaPicadUnidadrecord(x).numerototunida,9),
                    porc_acum = ROUND(lnPorcAcumulado,9)
              WHERE oid_prod  = tablaRegListaPicadUnidadrecord(x).oidproducto
                AND oid_rep_mapa_anaq = TO_NUMBER(psCodigoSecuencia);
           END LOOP;

         END IF;

       EXIT WHEN  REPOR_LISTA_PICAD_UNIDA%NOTFOUND;
     END LOOP;
   CLOSE REPOR_LISTA_PICAD_UNIDA;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_REPOR_MAPA_ANQBD: '||ls_sqlerrm);
 END APE_PR_GENER_REPOR_MAPA_ANQBD;

/**************************************************************************
     Descripcion       : Generar reporte de Balanceo Linea y Productos
     Autor             : Nicolas Lopez
     Fecha Creacion    : 26/10/2010
     Parametros Entrada :
            psCodigoPais
            psCodigoMarca
            psCodigoCanal
            psCodigoPeriodo
            psCodigoSecuencia
            psCodigoCentro
            psCodigoLineaArmado
***************************************************************************/
 PROCEDURE APE_PR_GENER_REPOR_BALAN_LINEA (
   psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoSecuencia      VARCHAR2,
   psCodigoCentro         VARCHAR2,
   psCodigoLineaArmado    VARCHAR2
 )
 IS
  W_FILAS                NUMBER := 5000;
  lnIdPais               NUMBER;
  lnIdMarca              NUMBER;
  lnIdCanal              NUMBER;
  lnIdCentro             NUMBER;
  lnIdPeriodo            NUMBER;
  lnLineaArmado          NUMBER;
  lnValidaFactorDime     NUMBER;
  lnValidaFactorPeso     NUMBER;
  lnFactConvDimension    NUMBER(7,3);
  lnFactConvPeso         NUMBER(7,3);
  lsVal_Largo            VARCHAR2(20);
  lsVal_Ancho            VARCHAR2(20);
  lsVal_Alto             VARCHAR2(20);
  lsVal_Peso             VARCHAR2(20);

  TYPE tmptablaBalanceoLinea IS RECORD(
    codigo             mae_produ.cod_sap %TYPE,
    descripcion        gen_i18n_sicc_pais.val_i18n %TYPE,
    anaquel            ape_mapa_centr_distr_detal.num_anaq %TYPE,
    tipo_anaquel       ape_tipo_chane.cod_tipo_chan %TYPE,
    largo              VARCHAR2(20),
    alto               VARCHAR2(20),
    ancho              VARCHAR2(20),
    af                 VARCHAR2(1),
    operario           mae_produ.num_capa_oper %TYPE,
    indicador          VARCHAR2(20),
    costo              mae_produ.val_cost_estd %TYPE,
    peso               VARCHAR2(20),
    unidades           mae_produ.num_unid_caja_prod %TYPE,
    estimadas          ape_asign_produ_anaqu_detal.num_unida %TYPE,
    unid_medid_dime    mae_produ.unmd_oid_unid_medi_dime %TYPE,
    unid_medid_peso    mae_produ.unmd_oid_unid_medi_peso %TYPE,
    num_afra_larg      ape_produ.num_afra_larg %TYPE,
    num_afra_alto      ape_produ.num_afra_alto %TYPE,
    num_afra_anch      ape_produ.num_afra_anch %TYPE,
    val_peso           mae_produ.val_peso %TYPE
  );

  TYPE tablaRegBalanceoLinea IS TABLE OF tmptablaBalanceoLinea;
  tablaRegBalanceoLinearecord tablaRegBalanceoLinea;

  -- Se obtienen los datos de Balanceo Linea y Productos
  CURSOR REPOR_BALAN_LINEA(vn_oid_peri NUMBER, vn_oid_centro NUMBER, vn_oid_linea NUMBER) IS
    SELECT DISTINCT
           mprod.cod_sap                                                     CODIGO,
           s.val_i18n                                                        DESCRIPCION,
           mcddet.num_anaq                                                   ANAQUEL,
           tipchn.cod_tipo_chan                                              TIPO_ANAQUEL,
           NULL                                                              LARGO,
           NULL                                                              ALTO,
           NULL                                                              ANCHO,
           CASE WHEN prod.tidi_oid_tipo_dispe = 2  THEN 'S'
                ELSE 'N'
                END                                                          AF,
           mprod.num_capa_oper                                               OPERARIO,
           CASE WHEN mprod.cod_ind_dent_caja = 'F' THEN 'FUERA DE PEDIDO'
                WHEN mprod.cod_ind_dent_caja = 'B' THEN 'FUERA DE PEDIDO'
                ELSE 'DENTRO'
                END                                                          INDICADOR,
           mprod.val_cost_estd                                               COSTO,
           NULL                                                              PESO,
           mprod.num_unid_caja_prod                                          UNIDADES,
           aspdet.num_unida                                                  ESTIMADAS,
           mprod.unmd_oid_unid_medi_dime                                     UNID_MEDID_DIME,
           mprod.unmd_oid_unid_medi_peso                                     UNID_MEDID_PESO,
           prod.num_afra_larg                                                NUM_AFRA_LARG,
           prod.num_afra_alto                                                NUM_AFRA_ALTO,
           prod.num_afra_anch                                                NUM_AFRA_ANCH,
           mprod.val_peso                                                    VAL_PESO
      FROM ape_asign_produ_anaqu_cabec aspcab,
           ape_asign_produ_anaqu_detal aspdet,
           ape_mapa_centr_distr_detal mcddet,
           ape_mapa_centr_distr_cabec mcdcab,
           mae_produ mprod,
           ape_produ prod,
           ape_tipo_anaqu tanaquel,
           ape_tipo_chane tipchn,
           gen_i18n_sicc_pais s,
           app_confi_centr_distr appcd,
           ape_linea_armad linar
     WHERE (aspcab.oid_asig_prod_anaq_cabe = aspdet.apac_oid_asig_prod_anaq_cabe
            AND  aspcab.ind_acti_fact = 'S'
            AND  aspcab.perd_oid_peri = vn_oid_peri)
       AND  mprod.oid_prod = aspdet.prod_oid_prod
       AND  mprod.oid_prod = prod.prod_oid_prod
       AND  mcddet.oid_mapa_cent_dist_deta = aspdet.mcdd_oid_mapa_cent_dist_deta
       AND  mcdcab.oid_mapa_cent_dist_cabe = mcddet.mcdc_oid_mapa_cent_dist_cabe
       AND  (s.attr_enti = 'MAE_PRODU'
            AND  s.val_oid  = mprod.oid_prod)
       AND  (tanaquel.oid_tipo_anaq = mcddet.tian_oid_tipo_anaq
            AND  tanaquel.tich_oid_tipo_chan = tipchn.oid_tipo_chan (+))
       AND  (mcdcab.ccdi_oid_conf_cent_dist = appcd.oid_conf_cent_dist
            AND  appcd.oid_conf_cent_dist = vn_oid_centro)
       AND  (linar.ccdi_oid_conf_cent_dist = appcd.oid_conf_cent_dist
            AND  linar.oid_line_arma = vn_oid_linea)
     ORDER BY mprod.cod_sap;

 BEGIN
   /* obteniendo id's */
   lnIdPais    := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
   lnIdMarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);-- id del marca consultante
   lnIdCanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);-- id del canal consultante
   lnIdPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo, lnIdMarca, lnIdCanal);-- id del periodo consultante

   /* Obtenemos el Oid de Centro de Distribucion */
   SELECT apcd.oid_conf_cent_dist
     INTO lnIdCentro
     FROM app_confi_centr_distr apcd
    WHERE apcd.cod_cent_dist = psCodigoCentro
      AND apcd.pais_oid_pais = lnIdPais;

   /* Obtenemos el Oid Linea de Armado */
   SELECT aplam.oid_line_arma
     INTO lnLineaArmado
     FROM ape_linea_armad aplam
    WHERE aplam.num_codi_line = psCodigoLineaArmado;

   OPEN REPOR_BALAN_LINEA(lnIdPeriodo, lnIdCentro, lnLineaArmado);
     LOOP
       FETCH REPOR_BALAN_LINEA BULK COLLECT INTO tablaRegBalanceoLinearecord LIMIT W_FILAS;

         IF tablaRegBalanceoLinearecord.COUNT > 0 THEN

           FOR x IN tablaRegBalanceoLinearecord.FIRST .. tablaRegBalanceoLinearecord.LAST LOOP
             -- Permite validar si existe Factor de Conversion para Dimension
             SELECT COUNT(1)
               INTO lnValidaFactorDime
               FROM mae_unida_medid medi,
                    ape_facto_conve fcon
              WHERE medi.magn_oid_magn = fcon.magn_oid_magn
                AND medi.oid_unid_medi = fcon.unmd_oid_unid_medi_orig
                AND medi.ind_um_stnd = 1
                AND medi.oid_unid_medi = tablaRegBalanceoLinearecord(x).unid_medid_dime;

             IF (lnValidaFactorDime >0) THEN
               --Se obtiene el Factor de Conversion para la dimension
               SELECT fcon.num_fact_conv
                 INTO lnFactConvDimension
                 FROM mae_unida_medid medi,
                      ape_facto_conve fcon
                WHERE medi.magn_oid_magn = fcon.magn_oid_magn
                  AND medi.oid_unid_medi = fcon.unmd_oid_unid_medi_orig
                  AND medi.ind_um_stnd = 1
                  AND medi.oid_unid_medi = tablaRegBalanceoLinearecord(x).unid_medid_dime;

             ELSE
               lnFactConvDimension := NULL;
             END IF;

             -- Se realiza la conversion de dimensiones de APE
             IF (lnFactConvDimension IS NOT NULL) THEN

               lsVal_Largo := TO_CHAR(lnFactConvDimension * tablaRegBalanceoLinearecord(x).num_afra_larg,'999999999.99');
               lsVal_Ancho := TO_CHAR(lnFactConvDimension * tablaRegBalanceoLinearecord(x).num_afra_anch,'999999999.99');
               lsVal_Alto  := TO_CHAR(lnFactConvDimension * tablaRegBalanceoLinearecord(x).num_afra_alto,'999999999.99');

             ELSE
               lsVal_Largo := '*';
               lsVal_Ancho := '*';
               lsVal_Alto  := '*';
             END IF;

             -- Permite validar si existe Factor de Conversion para Peso
             SELECT COUNT(1)
               INTO lnValidaFactorPeso
               FROM mae_unida_medid medi,
                    ape_facto_conve fcon
              WHERE medi.magn_oid_magn = fcon.magn_oid_magn
                AND medi.oid_unid_medi = fcon.unmd_oid_unid_medi_orig
                AND medi.ind_um_stnd = 1
                AND medi.oid_unid_medi = tablaRegBalanceoLinearecord(x).unid_medid_peso;

             IF (lnValidaFactorDime >0) THEN
               --Se obtiene el Factor de Conversion para el peso
               SELECT fcon.num_fact_conv
                 INTO lnFactConvPeso
                 FROM mae_unida_medid medi,
                      ape_facto_conve fcon
                WHERE medi.magn_oid_magn = fcon.magn_oid_magn
                  AND medi.oid_unid_medi = fcon.unmd_oid_unid_medi_orig
                  AND medi.ind_um_stnd = 1
                  AND medi.oid_unid_medi = tablaRegBalanceoLinearecord(x).unid_medid_peso;

             ELSE
               lnFactConvPeso := NULL;
             END IF;

             -- Se realiza la conversion de Peso de APE
             IF (lnFactConvPeso IS NOT NULL) THEN
               lsVal_Peso := TO_CHAR(lnFactConvPeso *  tablaRegBalanceoLinearecord(x).val_peso,'999999999.99');
             ELSE
               lsVal_Peso := '*';
             END IF;

             -- Se procede a insertar los valores a la tabla de reporte Balanceo de Linea y Productos
             INSERT INTO ape_tmp_rep_blprd(
               oid_rep_balan_linea,
               cod_sap,
               desc_prod,
               num_anaq,
               cod_tipo_chan,
               val_largo,
               val_alto,
               val_ancho,
               val_af,
               num_capa_oper,
               val_ind,
               val_cost_estd,
               val_peso,
               num_unid_caja_prod,
               num_unida
             )
             VALUES(
               TO_NUMBER(psCodigoSecuencia),
               ''|| tablaRegBalanceoLinearecord(x).codigo        || '',
               ''|| tablaRegBalanceoLinearecord(x).descripcion   ||'' ,
               ''|| tablaRegBalanceoLinearecord(x).anaquel       || '',
               ''|| tablaRegBalanceoLinearecord(x).tipo_anaquel  ||'' ,
               ''|| lsVal_Largo                                  || '',
               ''|| lsVal_Alto                                   ||'' ,
               ''|| lsVal_Ancho                                  || '',
               ''|| tablaRegBalanceoLinearecord(x).af            ||'' ,
               tablaRegBalanceoLinearecord(x).operario,
               '' || tablaRegBalanceoLinearecord(x).indicador    ||'',
               tablaRegBalanceoLinearecord(x).costo,
               '' || lsVal_Peso                                  ||'',
               tablaRegBalanceoLinearecord(x).unidades,
               tablaRegBalanceoLinearecord(x).estimadas
             );

             lsVal_Peso  := '';
             lsVal_Largo := '';
             lsVal_Ancho := '';
             lsVal_Alto  := '';

           END LOOP;

         END IF;

       EXIT WHEN  REPOR_BALAN_LINEA%NOTFOUND;
     END LOOP;
 CLOSE REPOR_BALAN_LINEA;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_REPOR_BALAN_LINEA: '||ls_sqlerrm);
 END APE_PR_GENER_REPOR_BALAN_LINEA;

/***************************************************************************
     Descripcion       : Realiza la validaciones antes de realizar la carga
                         de los productos de APE
     Fecha Creacion    : 19/11/2010
****************************************************************************/
 PROCEDURE APE_PR_VALID_VISTA_PRODU (
   p_oid_pais         IN  VARCHAR2,
   p_cod_sap          IN  VARCHAR2,
   p_cod_tipo_disp    IN  VARCHAR2,
   p_cod_tipo_anaq    IN  VARCHAR2,
   p_cod_caja_maes    IN  VARCHAR2,
   p_val_error        OUT VARCHAR2
 )
 IS
   vnContadorVal1    NUMBER;
   vnContadorVal2    NUMBER;
   vnContadorVal3    NUMBER;
   vnContadorVal4    NUMBER;
   vsCodTipoDisp     ape_tipo_dispe.cod_tipo_disp%TYPE;

 BEGIN

   p_val_error    := '0';
   vnContadorVal1 := 0;
   vnContadorVal2 := 0;
   vnContadorVal3 := 0;
   vnContadorVal4 := 0;

   --Se valida que exista el codigo de producto
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vnContadorVal1
       FROM mae_produ m
      WHERE m.cod_sap = p_cod_sap
        AND m.pais_oid_pais = p_oid_pais;

     IF (vnContadorVal1 = 0) THEN
       p_val_error := '1';
     END IF;

   END IF;

   --Se valida que exista el tipo de dispensacion
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vnContadorVal4
       FROM ape_tipo_dispe a
      WHERE a.cod_tipo_disp = p_cod_tipo_disp;

     IF (vnContadorVal4 = 0) THEN
       p_val_error := '3';
     END IF;

   END IF;

   --Se valida el tipo de dispensacion Aframe
   IF (p_cod_tipo_disp = 2) THEN

     IF (p_val_error = '0') THEN

       SELECT a.cod_tipo_disp
         INTO vsCodTipoDisp
         FROM ape_tipo_dispe a
        WHERE a.oid_tipo_disp = 2;

       IF ( vsCodTipoDisp != p_cod_tipo_disp) THEN
         p_val_error := '2';
       END IF;

     END IF;

   END IF;

   -- Se valida que exista el codigo de tipo Anaquel
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vnContadorVal2
       FROM ape_tipo_anaqu a
      WHERE a.cod_tipo_anaq = p_cod_tipo_anaq;

     IF (vnContadorVal2 = 0) THEN
       p_val_error := '4';
     END IF;

   END IF;

   -- Se valida que exista el codigo de caja maestra
   IF (p_val_error = '0') THEN

     SELECT COUNT(1)
       INTO vnContadorVal3
       FROM app_tipo_caja_produ a
      WHERE a.cod_caja = p_cod_caja_maes
        AND a.pais_oid_pais = p_oid_pais;

     IF (vnContadorVal3 = 0) THEN
       p_val_error := '5';
     END IF;

   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_VALID_VISTA_PRODUREPOR_BALAN_LINEA: '||ls_sqlerrm);
 END APE_PR_VALID_VISTA_PRODU;

/**************************************************************************************
     Descripcion       : Valida la sublinea AFRAME y actualiza los tipos de anaqueles
     Autor             : Christian Gonzales Komiya
     Fecha Creacion    : 26/10/2010
     Parametros Entrada :
            p_tipo_subli
            p_num_anaq
            p_cod_tipo_anaq
            p_val_side
            p_nro_aframe
            p_chan_address_aframe
            p_height_aframe
            p_width_aframe
            p_mach_address_aframe
            p_level_aframe
            p_oid_subl
            p_oid_mapacd
            p_val_error
***************************************************************************************/
 PROCEDURE APE_PR_ACTUA_TIPOS_ANAQU_MAPCD (
   p_tipo_subli             IN  VARCHAR2,
   p_num_anaq               IN  VARCHAR2,
   p_cod_tipo_anaq          IN  VARCHAR2,
   p_val_side               IN  VARCHAR2,
   p_nro_aframe             IN  NUMBER,
   p_chan_address_aframe    IN  NUMBER,
   p_height_aframe          IN  NUMBER,
   p_width_aframe           IN  NUMBER,
   p_mach_address_aframe    IN  NUMBER,
   p_level_aframe           IN  NUMBER,
   p_oid_subl               IN  VARCHAR2,
   p_oid_mapacd             IN  VARCHAR2,
   p_val_error              OUT VARCHAR2
 )
 IS
   v_count            INT := 0;
   v_oid_tipo_anaq    ape_tipo_anaqu.oid_tipo_anaq%TYPE;

 BEGIN

   p_val_error:=0;

   -- Validacion 1.	El codigo del anaquel debe existir en la Sublinea Tratada
   SELECT COUNT(1)
     INTO v_count
     FROM ape_mapa_centr_distr_detal mapad
    WHERE mapad.num_anaq = p_num_anaq
      AND mapad.sbar_oid_subl_arma = p_oid_subl
      AND mapad.mcdc_oid_mapa_cent_dist_cabe=p_oid_mapacd;

   IF v_count = 0 THEN

     p_val_error := '1';
     RETURN;

   END IF;

   -- Validacion 2.	El tipo de anaquel debe existir en la tabla APE_TIPO_ANAQU
   SELECT COUNT(1)
     INTO v_count
     FROM ape_tipo_anaqu
    WHERE cod_tipo_anaq = p_cod_tipo_anaq;

   IF v_count = 0 THEN

     p_val_error := '2';
     RETURN;

   END IF;

   SELECT oid_tipo_anaq
     INTO v_oid_tipo_anaq
     FROM ape_tipo_anaqu
    WHERE cod_tipo_anaq = p_cod_tipo_anaq;

   -- Validacion 3 .Para una sublinea tipo Aframe,
   -- se considera como unica la combinacion de valores: Side + FrameNumber + ChanelAddress
   IF p_tipo_subli = 3 THEN
     -- es de tipo AFRAME
     SELECT COUNT(1)
       INTO v_count
       FROM ape_subli_armad apsl,
            ape_mapa_centr_distr_detal mapad
      WHERE mapad.sbar_oid_subl_arma = apsl.oid_subl_arma
        AND apsl.sipi_oid_sist_pica = 2
        AND apsl.oid_subl_arma = p_oid_subl
        AND mapad.mcdc_oid_mapa_cent_dist_cabe=p_oid_mapacd
        AND mapad.val_side = p_val_side
        AND mapad.num_afra_fram_numb=TO_NUMBER(p_nro_aframe)
        AND mapad.num_afra_chan_addr=TO_NUMBER(p_chan_address_aframe);

     IF v_count > 0 THEN

       p_val_error := '3';
       RETURN;

     END IF;

     /* Validacion 4
        si la Sublinea esta soportada por un sistema Aframe
        (SIPI_OID_SIST_PICA = 2 se debe validar que este anaquel
        sea de tipo Aframe  IND_TIPO_AFRM = 1
      */
     SELECT COUNT(1)
       INTO v_count
       FROM ape_mapa_centr_distr_detal mapad,
            ape_tipo_anaqu tipoanaquel
      WHERE mapad.tian_oid_tipo_anaq = tipoanaquel.oid_tipo_anaq
        AND mapad.sbar_oid_subl_arma = p_oid_subl
        AND mapad.mcdc_oid_mapa_cent_dist_cabe=p_oid_mapacd
        AND tipoanaquel.ind_tipo_afrm = 1
        AND mapad.num_anaq = p_num_anaq;

     IF v_count = 0 THEN

       p_val_error := '4';
       RETURN;

     END IF;

     UPDATE ape_mapa_centr_distr_detal
        SET tian_oid_tipo_anaq = v_oid_tipo_anaq,
            val_side = p_val_side,
            num_afra_fram_numb = p_nro_aframe,
            num_afra_chan_addr = p_chan_address_aframe,
            num_afra_heig      = p_height_aframe,
            num_afra_widt      = p_width_aframe,
            num_afra_mach_addr = p_mach_address_aframe,
            num_afra_leve_numb = p_level_aframe
      WHERE num_anaq = p_num_anaq
        AND mcdc_oid_mapa_cent_dist_cabe = p_oid_mapacd;

   ELSE
     UPDATE ape_mapa_centr_distr_detal
        SET tian_oid_tipo_anaq = v_oid_tipo_anaq
      WHERE num_anaq = p_num_anaq
        AND mcdc_oid_mapa_cent_dist_cabe = p_oid_mapacd;

   END IF;

 END APE_PR_ACTUA_TIPOS_ANAQU_MAPCD;

/****************************************************************************
     Descripcion       : Registra en la tabla de Anaquel Producto el tipo de
                         Anaquel que llega en la carga en exel
     Fecha Creacion    : 01/12/2010
*****************************************************************************/
 PROCEDURE APE_PR_REGIS_TIPO_ANAQU (
   p_oid_sap          IN VARCHAR2,
   p_oid_tipo_anaq    IN VARCHAR2
 )
 IS
   vn_existe_tipo_anaquel    NUMBER;
   vn_numero_actual          ape_produ_anaqu.num_nume_prio%TYPE;
   vn_oidSAP                 ape_produ_anaqu.prod_oid_prod%TYPE;
   vn_oitTipoAnaquel         ape_produ_anaqu.tian_oid_tipo_anaq%TYPE;

 BEGIN

   vn_existe_tipo_anaquel := 0;
   vn_numero_actual := 0;
   vn_oidSAP := to_number(p_oid_sap);
   vn_oitTipoAnaquel := to_number(p_oid_tipo_anaq);

   -- Se verifica si existe el tipo de Anaquel en la tabla de Anaquel Producto
   SELECT COUNT(1)
     INTO vn_existe_tipo_anaquel
     FROM ape_produ_anaqu pa
    WHERE prod_oid_prod = vn_oidSAP
      AND tian_oid_tipo_anaq = vn_oitTipoAnaquel;

   --Si no existe se le suma 10 a las prioridades de todos los tipos de anaqueles
   IF (vn_existe_tipo_anaquel = 0) THEN

     UPDATE ape_produ_anaqu
        SET num_nume_prio = num_nume_prio + 10
      WHERE prod_oid_prod = vn_oidSAP;

     -- Insertando en la tabla APE_PRODU_ANAQU
     INSERT INTO ape_produ_anaqu(
       oid_prod_anaq,
       prod_oid_prod,
       num_nume_prio,
       tian_oid_tipo_anaq
     )
     VALUES(
       APE_PRAN_SEQ.NEXTVAL,
       vn_oidSAP,
       10,
       vn_oitTipoAnaquel
     );

   ELSE
     -- Si existe se obtiene el numero de prioridad del tipo de anaquel llegado por parametro
     SELECT pa.num_nume_prio
       INTO vn_numero_actual
       FROM ape_produ_anaqu pa
      WHERE prod_oid_prod = vn_oidSAP
        AND tian_oid_tipo_anaq = vn_oitTipoAnaquel;

     -- Se le suma10 a todos los tipos de anaquel con numero de prioridad menores al vn_numero_actual
     UPDATE ape_produ_anaqu
        SET num_nume_prio = num_nume_prio + 10
      WHERE prod_oid_prod = vn_oidSAP
        AND num_nume_prio <= vn_numero_actual;

     -- Se actualiza el numero de prioridad 10 al tipo de anaquel llegado por parametro
     UPDATE ape_produ_anaqu
        SET num_nume_prio = 10
      WHERE prod_oid_prod = vn_oidSAP
        AND tian_oid_tipo_anaq = vn_oitTipoAnaquel;
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_REGIS_TIPO_ANAQU: '||ls_sqlerrm);
 END APE_PR_REGIS_TIPO_ANAQU;

/*****************************************************************************************
Descripcion       : Valida si existe repeticion de Valores Side+FrameNumber+ChannelAddres
                    en el listado de Anaqueles
Fecha Creacion    : 03/12/2010
Autor             : Nicolas Lopez
******************************************************************************************/
 PROCEDURE APE_PR_APE_GENER_VALID_ANAQU (
   psCodigoPais    VARCHAR2,
   psNumError      OUT VARCHAR2
 )
 IS
   CURSOR c_tipos_anaqu_A IS
		 SELECT oid_mapa_cent_dist_deta,
            oid_tipo_anaq,
            val_side,
            num_afra_fram_numb,
            num_afra_chan_addr,
            num_afra_mach_addr,
            num_afra_leve_numb,
            num_afra_heig,
            num_afra_widt
       FROM ape_gtt_tipo_anaqu;

   CURSOR c_tipos_anaqu_B(ls_ValSide VARCHAR2, ln_oidMapaCentroDet NUMBER) IS
		 SELECT oid_mapa_cent_dist_deta,
            oid_tipo_anaq,
            val_side,
            num_afra_fram_numb,
            num_afra_chan_addr,
            num_afra_mach_addr,
            num_afra_leve_numb,
            num_afra_heig,
            num_afra_widt
       FROM ape_gtt_tipo_anaqu
      WHERE val_side = ls_ValSide
        AND oid_mapa_cent_dist_deta NOT IN (ln_oidMapaCentroDet);

   TYPE tipoAnaquelesA IS RECORD(
     oidMapaCentDistDeta    ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta%TYPE,
     oidTipoAnaq            ape_tipo_anaqu.oid_tipo_anaq%TYPE,
     valSide                ape_mapa_centr_distr_detal.val_side%TYPE,
     numAfraFramNumb        ape_mapa_centr_distr_detal.num_afra_fram_numb%TYPE,
     numAfraChanAddr        ape_mapa_centr_distr_detal.num_afra_chan_addr%TYPE,
     numAfraMachAddr        ape_mapa_centr_distr_detal.num_afra_mach_addr%TYPE,
     numAfraLeveNumb        ape_mapa_centr_distr_detal.num_afra_leve_numb%TYPE,
     numAfraHeig            ape_mapa_centr_distr_detal.num_afra_heig%TYPE,
     numAfraWidt            ape_mapa_centr_distr_detal.num_afra_widt%TYPE
    );

   TYPE tipoAnaquelesB IS RECORD(
     oidMapaCentDistDeta    ape_mapa_centr_distr_detal.oid_mapa_cent_dist_deta%TYPE,
     oidTipoAnaq            ape_tipo_anaqu.oid_tipo_anaq%TYPE,
     valSide                ape_mapa_centr_distr_detal.val_side%TYPE,
     numAfraFramNumb        ape_mapa_centr_distr_detal.num_afra_fram_numb%TYPE,
     numAfraChanAddr        ape_mapa_centr_distr_detal.num_afra_chan_addr%TYPE,
     numAfraMachAddr        ape_mapa_centr_distr_detal.num_afra_mach_addr%TYPE,
     numAfraLeveNumb        ape_mapa_centr_distr_detal.num_afra_leve_numb%TYPE,
     numAfraHeig            ape_mapa_centr_distr_detal.num_afra_heig%TYPE,
     numAfraWidt            ape_mapa_centr_distr_detal.num_afra_widt%TYPE
    );

   TYPE tipoAnaquelesARec IS TABLE OF tipoAnaquelesA;
   tipoAnaquelesArecord tipoAnaquelesARec;

   TYPE tipoAnaquelesBRec IS TABLE OF tipoAnaquelesB;
   tipoAnaquelesBrecord tipoAnaquelesBRec;

   -- Variables
   lsNumAnaquel    ape_mapa_centr_distr_detal.num_anaq%TYPE;
   lnContador      NUMBER;
   W_FILAS         NUMBER:=10000;

 BEGIN
   -- Inicializamos el Numero de Anaquel con Cero
   lsNumAnaquel := '0';
   lnContador   := 0;

   OPEN c_tipos_anaqu_A;
     LOOP
       FETCH c_tipos_anaqu_A BULK COLLECT INTO tipoAnaquelesArecord LIMIT W_FILAS;

         IF tipoAnaquelesArecord.COUNT > 0 THEN

           FOR x IN tipoAnaquelesArecord.FIRST .. tipoAnaquelesArecord.LAST LOOP
             OPEN c_tipos_anaqu_B ( tipoAnaquelesArecord(x).valSide, tipoAnaquelesArecord(x).oidMapaCentDistDeta );
               LOOP
                 FETCH c_tipos_anaqu_B BULK COLLECT INTO tipoAnaquelesBrecord LIMIT W_FILAS;

                   IF tipoAnaquelesBrecord.COUNT > 0 THEN

                     FOR i IN tipoAnaquelesBrecord.FIRST .. tipoAnaquelesBrecord.LAST LOOP
                       ---Para una sublinea tipo Aframe, se considera como unica la combinacion de valores:
                       -- Side + FrameNumber + ChanelAddress
                       IF (tipoAnaquelesArecord(x).valSide         = tipoAnaquelesBrecord(i).valSide AND
                           tipoAnaquelesArecord(x).numAfraFramNumb = tipoAnaquelesBrecord(i).numAfraFramNumb AND
                           tipoAnaquelesArecord(x).numAfraChanAddr = tipoAnaquelesBrecord(i).numAfraChanAddr) THEN

                         SELECT mpcd.num_anaq
                           INTO lsNumAnaquel
                           FROM ape_mapa_centr_distr_detal mpcd
                          WHERE mpcd.oid_mapa_cent_dist_deta = tipoAnaquelesArecord(x).oidMapaCentDistDeta;

                         INSERT INTO ape_gtt_lista_anaqu(
                           num_anaq
                         )
                         VALUES(
                           lsNumAnaquel
                         );

                       END IF;

                     END LOOP;

                   END IF;

                 EXIT WHEN c_tipos_anaqu_B%NOTFOUND;
               END LOOP;
             CLOSE c_tipos_anaqu_B;
           END LOOP;

         END IF;

       EXIT WHEN c_tipos_anaqu_A%NOTFOUND;
     END LOOP;
   CLOSE c_tipos_anaqu_A;

   SELECT COUNT(1)
     INTO lnContador
     FROM ape_gtt_lista_anaqu;

   IF (lnContador > 0) THEN
     psNumError := '1';
   ELSE
     psNumError := '0';
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_APE_GENER_VALID_ANAQU: '||ls_sqlerrm);
 END APE_PR_APE_GENER_VALID_ANAQU;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Mgpedxdia
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 12/12/2013
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_MGPED_CSV1(
    psCodigoPeriodo                     VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;

  CURSOR c_interfaz IS
  SELECT psCodigoPeriodo AS CAMPANHA,
         TO_CHAR(PED_SOLIC_CABEC.FEC_FACT, 'DD/MM/YYYY') AS FECHA_FACT,
         ZON_ZONA.COD_ZONA AS ZONA,
         CON.VAL_NUME_SOLI AS PEDIDO,
         FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA AS FACTURA,
         MAE_CLIEN.COD_CLIE AS CONSULTORA,
         (SELECT NUM_DOCU_IDEN
            FROM MAE_CLIEN_IDENT, MAE_TIPO_DOCUM
           WHERE MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU =
                 MAE_TIPO_DOCUM.OID_TIPO_DOCU
             AND MAE_TIPO_DOCUM.COD_TIPO_DOCU = '01'
             AND MAE_CLIEN_IDENT.CLIE_OID_CLIE = CON.CLIE_OID_CLIE) AS CEDULA
    FROM PED_SOLIC_CABEC,
         ZON_ZONA,
         PED_TIPO_SOLIC_PAIS,
         PED_TIPO_SOLIC,
         PED_SOLIC_CABEC CON,
         FAC_DOCUM_CONTA_CABEC,
         ZON_REGIO ZRE,
         MAE_CLIEN
   WHERE PED_SOLIC_CABEC.PERD_OID_PERI = lnOidCodigoPeriodo
     AND PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
     AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
     AND PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI
     AND PED_TIPO_SOLIC.COD_TIPO_SOLI = 'SOC'
     AND CON.OID_SOLI_CABE = PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
     AND FAC_DOCUM_CONTA_CABEC.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
     AND PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE
     AND ZRE.OID_REGI = ZON_ZONA.ZORG_OID_REGI
     AND ((lsFlagRegionesZonas = '1' AND ZON_ZONA.COD_ZONA IN(SELECT COD_ZONA FROM APE_REPOR_REGIO_ZONA)) OR lsFlagRegionesZonas='0');

TYPE interfazTipo IS RECORD (

 v_CAMPANHA            VARCHAR2(12),
 v_FECHA_FACT          VARCHAR2(12),
 v_ZONA                VARCHAR2(10),
 v_PEDIDO              PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
 v_FACTURA             FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA%TYPE,
 v_CONSULTORA          VARCHAR2(15),
 v_CEDULA              VARCHAR2(30)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
    SELECT DECODE(COUNT(*), 0, '0', '1')
       INTO lsFlagRegionesZonas
     FROM APE_REPOR_REGIO_ZONA;

  lnOidCodigoPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_CAMPANHA||'")' ||','||
                    '=T("'||interfazRecord(x).v_FECHA_FACT||'")' ||','||
                    interfazRecord(x).v_ZONA ||','||
                    '=T("'||interfazRecord(x).v_PEDIDO||'")' ||','||
                    '=T("'||interfazRecord(x).v_FACTURA||'")' ||','||
                    '=T("'||interfazRecord(x).v_CONSULTORA||'")' ||','||
                    '=T("'||interfazRecord(x).v_CEDULA||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_REPOR_MGPED_CSV1: '||ls_sqlerrm);
END APE_PR_REPOR_MGPED_CSV1;

/***************************************************************************
Descripcion       : Genera data para el reporte de Distribucion Facturacion Real
Fecha Creacion    : 13/12/2013
Autor             : Aurelio Oviedo
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoPeriodo : Codigo Periodo
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE ape_pr_gener_datos_factu_real
(
  pscodigopais    VARCHAR2,
  pscodigoperiodo VARCHAR2,
  pscodigousuario VARCHAR2,
  psfechainicio   VARCHAR2,
  psfechafin      VARCHAR2,
  pscentroacopio  VARCHAR2,
  pscompaniatrans VARCHAR2
) IS

  lsflagregioneszonas VARCHAR2(1);
  lnidperiodo         NUMBER;
  lnidperiodomenos1   NUMBER;

  CURSOR c_clie_vip IS
    SELECT x.clie_oid_clie
      FROM mae_clien_tipo_subti x,
           mae_clien_clasi      y,
           lar_tipo_clien_vip   z
     WHERE x.oid_clie_tipo_subt = y.ctsu_oid_clie_tipo_subt
       AND y.tccl_oid_tipo_clasi = z.oid_tipo_clas
       AND y.clas_oid_clas = z.oid_clas;

  TYPE t_clie_oid_clie IS TABLE OF mae_clien.oid_clie%TYPE;
  v_clie_oid_clie t_clie_oid_clie;

  rows NUMBER := 5000;
  i    BINARY_INTEGER := 0;

BEGIN
  lnidperiodo       := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  lnidperiodomenos1 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_fn_calcu_perio(pscodigoperiodo,
                                                                                      -1));

  SELECT decode(COUNT(1), 0, '0', '1')
    INTO lsflagregioneszonas
    FROM ape_repor_distr_factu_zona;

  DELETE FROM ape_repor_distr_factu WHERE usu_digi = pscodigousuario;

  IF (psfechainicio IS NULL AND psfechafin IS NULL) THEN
    INSERT INTO ape_repor_distr_factu
      (cod_peri,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       nom_comp_tran,
       cod_cent_acop,
       nom_cent_acop,
       fec_entr,
       ind_reme,
       num_docu_iden,
       nom_clie,
       fec_fact,
       num_bole,
       num_fact,
       des_dire,
       num_tele,
       num_celu,
       val_prim,
       des_esta,
       tip_soli,
       val_impo_flet_tota,
       val_impo_impu_tota,
       val_prec_neto_tota,
       fec_sigu_fact,
       fec_sigu_entr,
       tip_bole,
       fec_ante_fact,
       fec_ante_entr,
       des_depa,
       des_ciud,
       des_pobl,
       fec_real_entr,
       VAL_LATI, 
       VAL_LONG, 
       HOR_FACT,
       des_nove,
       ind_triu,
       usu_digi,
       ind_pedi_anul,
       clie_oid_clie,
       val_turn,
       fec_entr_orig
       )
      SELECT pscodigoperiodo AS campania,
             reg.cod_regi AS region,
             zon.cod_zona AS zona,
             secc.cod_secc AS seccion,
             terr.cod_terr AS terr,
             cli.cod_clie AS consultora,
             psp.nom_comp_tran,
             psp.cod_cent_acop,
             psp.nom_cent_acop,
             fec AS fecha_entrega_estimada,
             psp.ind_comp remesa,
             (SELECT num_docu_iden
                FROM mae_clien_ident iden
               WHERE iden.clie_oid_clie = con.clie_oid_clie
                 AND tdoc_oid_tipo_docu = 2001) AS cedula,
             cli.val_ape1 || ' ' || cli.val_ape2 || ', ' || cli.val_nom1 || ' ' ||
             cli.val_nom2 AS nombres,
             con.fec_fact AS fecha_fact,
             con.val_nume_soli AS numero_boleta,
             doc.num_docu_lega AS factura,
             doc.val_dire_comp || ' ' || dir.val_barr AS direccion,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 1) AS telefono,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 6) AS celular,
             (SELECT MIN(val_nomb_peri)
                FROM cra_perio             crp,
                     mae_clien_prime_conta cpc
               WHERE cpc.perd_oid_peri = crp.oid_peri
                 AND cpc.clie_oid_clie = con.clie_oid_clie) AS primero,
             (SELECT val_i18n
                FROM mae_clien_histo_estat,
                     gen_i18n_sicc_comun
               WHERE clie_oid_clie = con.clie_oid_clie
                 AND perd_oid_peri <= con.perd_oid_peri
                 AND (con.perd_oid_peri <= perd_oid_peri_peri_fin OR
                     perd_oid_peri_peri_fin IS NULL)
                 AND attr_enti = 'MAE_ESTAT_CLIEN'
                 AND val_oid = esta_oid_esta_clie
                 and rownum = 1) AS estatus,
             dts.val_i18n AS tipo_solicitud,
             con.val_impo_flet_tota_loca,
             con.val_impo_impu_tota_loca,
             con.val_prec_neto_tota_loca,
             psp.fec_sigu_fact AS fec_sigu_fact,
             psp.fec_sigu_entr AS fec_sigu_entr,             
             CASE
               WHEN EXISTS (SELECT 1
                       FROM ped_solic_cabec
                      WHERE soca_oid_soli_cabe = con.oid_soli_cabe
                        AND ind_oc = 1) THEN
                'PEDIDO'
               ELSE
                'POSTVENTA'
             END tipo_boleta,
             (SELECT MAX(x.fec_sigu_fact)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_fact,
             (SELECT MAX(x.fec_sigu_entr)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_entr,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 IS NULL
                 AND orde_3 IS NULL) departamento,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 IS NULL) ciudad,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 = substr(dir.cod_unid_geog, 13, 6)
                 AND rownum = 1) poblacion,
             decode(ot.estado, '01', ot.fec_proc, NULL) fecha_real_entrega,
             OT.VAL_LATI, 
             OT.VAL_LONG, 
             OT.HOR_FACT,
             ot.descripcion novedad,
             'NO' indicador_triunfadora,
             pscodigousuario AS usuario,
             decode(con.esso_oid_esta_soli, 4, 'SI', 'NO') pedido_anulado,
             con.clie_oid_clie,
             psp.val_turn,
             psp.fec_orig
        FROM ped_solic_cabec con,
             zon_regio reg,
             mae_clien cli,
             gen_i18n_sicc_comun dts,
             zon_zona zon,
             zon_terri terr,
             zon_terri_admin ztad,
             zon_secci secc,
             ped_tipo_solic_pais tspa,
             fac_docum_conta_cabec doc,
             ped_segui_pedid psp,
             mae_clien_direc dir,
             (SELECT ot.sec_nume_docu,
                     ot.num_docu,
                     ot.fec_proc,
                     est.val_desc || ' / ' || nov.val_desc descripcion,
                     OT.VAL_LATI, 
                     OT.VAL_LONG, 
                     OT.HOR_FACT,
                     ot.cod_nove novedad,
                     CASE
                       WHEN ot.cod_esta_entr IN ('01', '04', '06') THEN
                        '01'
                       WHEN ot.cod_esta_ent2 IN ('01', '04', '06') THEN
                        '01'
                     END AS estado
                FROM int_solic_conso_orden_trans ot,
                     sto_estad_orden_trans est,
                     sto_noved_orden_trans nov,
                     (SELECT DISTINCT num_docu,
                                      MAX(sec_nume_docu) sec_nume_docu
                        FROM int_solic_conso_orden_trans
                       WHERE cod_pais = pscodigopais
                         AND tip_orde = 'BEP'
                         AND cod_peri = pscodigoperiodo
                       GROUP BY num_docu) arctmp
               WHERE ot.sec_nume_docu = arctmp.sec_nume_docu
                 AND nvl(ot.cod_esta_ent2, ot.cod_esta_entr) =
                     est.cod_esta_entr(+)
                 AND ot.tip_orde = est.cod_tipo_orde_tran(+)
                 AND ot.cod_nove = nov.cod_nove(+)
                 AND ot.cod_peri = pscodigoperiodo) ot
       WHERE con.perd_oid_peri = lnidperiodo
         AND con.clie_oid_clie = cli.oid_clie
         AND con.val_nume_soli = ot.num_docu(+)
         AND doc.cldi_oid_clie_dire = dir.oid_clie_dire
         AND con.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND dts.val_oid = tspa.tsol_oid_tipo_soli
         AND con.zzon_oid_zona = zon.oid_zona
         AND con.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = secc.oid_secc
         AND zon.zorg_oid_regi = reg.oid_regi
         AND ztad.terr_oid_terr = terr.oid_terr
         AND doc.soca_oid_soli_cabe = con.oid_soli_cabe
         AND psp.soca_oid_soli_cabe = con.oid_soli_cabe
         AND dts.attr_enti = 'PED_TIPO_SOLIC'
         AND con.ind_ts_no_conso = 0
         AND doc.tido_oid_tipo_docu = 1
         AND (lsflagregioneszonas = '0' OR
             (lsflagregioneszonas = '1' AND
             zon.cod_zona IN
             (SELECT cod_zona FROM ape_repor_distr_factu_zona)))
         AND (pscentroacopio IS NULL OR (pscentroacopio IS NOT NULL AND
             psp.cod_cent_acop = pscentroacopio))
         AND (pscompaniatrans IS NULL OR
             (pscompaniatrans IS NOT NULL AND
             psp.nom_comp_tran = pscompaniatrans));

  ELSIF (psfechainicio IS NOT NULL AND psfechafin IS NULL) THEN
    INSERT INTO ape_repor_distr_factu
      (cod_peri,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       nom_comp_tran,
       cod_cent_acop,
       nom_cent_acop,
       fec_entr,
       ind_reme,
       num_docu_iden,
       nom_clie,
       fec_fact,
       num_bole,
       num_fact,
       des_dire,
       num_tele,
       num_celu,
       val_prim,
       des_esta,
       tip_soli,
       val_impo_flet_tota,
       val_impo_impu_tota,
       val_prec_neto_tota,
       fec_sigu_fact,
       fec_sigu_entr,
       tip_bole,
       fec_ante_fact,
       fec_ante_entr,
       des_depa,
       des_ciud,
       des_pobl,
       fec_real_entr,
       VAL_LATI, 
       VAL_LONG, 
       HOR_FACT,
       des_nove,
       ind_triu,
       usu_digi,
       ind_pedi_anul,
       clie_oid_clie,
       val_turn,
       fec_entr_orig)
      SELECT pscodigoperiodo AS campania,
             reg.cod_regi AS region,
             zon.cod_zona AS zona,
             secc.cod_secc AS seccion,
             terr.cod_terr AS terr,
             cli.cod_clie AS consultora,
             psp.nom_comp_tran,
             psp.cod_cent_acop,
             psp.nom_cent_acop,
             fec AS fecha_entrega_estimada,
             psp.ind_comp remesa,
             (SELECT num_docu_iden
                FROM mae_clien_ident iden
               WHERE iden.clie_oid_clie = con.clie_oid_clie
                 AND tdoc_oid_tipo_docu = 2001) AS cedula,
             cli.val_ape1 || ' ' || cli.val_ape2 || ', ' || cli.val_nom1 || ' ' ||
             cli.val_nom2 AS nombres,
             con.fec_fact AS fecha_fact,
             con.val_nume_soli AS numero_boleta,
             doc.num_docu_lega AS factura,
             doc.val_dire_comp || ' ' || dir.val_barr AS direccion,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 1) AS telefono,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 6) AS celular,
             (SELECT MIN(val_nomb_peri)
                FROM cra_perio             crp,
                     mae_clien_prime_conta cpc
               WHERE cpc.perd_oid_peri = crp.oid_peri
                 AND cpc.clie_oid_clie = con.clie_oid_clie) AS primero,
             (SELECT val_i18n
                FROM mae_clien_histo_estat,
                     gen_i18n_sicc_comun
               WHERE clie_oid_clie = con.clie_oid_clie
                 AND perd_oid_peri <= con.perd_oid_peri
                 AND (con.perd_oid_peri <= perd_oid_peri_peri_fin OR
                     perd_oid_peri_peri_fin IS NULL)
                 AND attr_enti = 'MAE_ESTAT_CLIEN'
                 AND val_oid = esta_oid_esta_clie
                 and rownum = 1) AS estatus,
             dts.val_i18n AS tipo_solicitud,
             con.val_impo_flet_tota_loca,
             con.val_impo_impu_tota_loca,
             con.val_prec_neto_tota_loca,
             psp.fec_sigu_fact AS fec_sigu_fact,
             psp.fec_sigu_entr AS fec_sigu_entr,
             CASE
               WHEN EXISTS (SELECT 1
                       FROM ped_solic_cabec
                      WHERE soca_oid_soli_cabe = con.oid_soli_cabe
                        AND ind_oc = 1) THEN
                'PEDIDO'
               ELSE
                'POSTVENTA'
             END tipo_boleta,
             (SELECT MAX(x.fec_sigu_fact)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_fact,
             (SELECT MAX(x.fec_sigu_entr)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_entr,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 IS NULL
                 AND orde_3 IS NULL) departamento,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 IS NULL) ciudad,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 = substr(dir.cod_unid_geog, 13, 6)
                 AND rownum = 1) poblacion,
             decode(ot.estado, '01', ot.fec_proc, NULL) fecha_real_entrega,
             OT.VAL_LATI, 
             OT.VAL_LONG, 
             OT.HOR_FACT,
             ot.descripcion novedad,
             'NO' indicador_triunfadora,
             pscodigousuario AS usuario,
             decode(con.esso_oid_esta_soli, 4, 'SI', 'NO') pedido_anulado,
             con.clie_oid_clie,
             psp.val_turn,
             psp.fec_orig
        FROM ped_solic_cabec con,
             zon_regio reg,
             mae_clien cli,
             gen_i18n_sicc_comun dts,
             zon_zona zon,
             zon_terri terr,
             zon_terri_admin ztad,
             zon_secci secc,
             ped_tipo_solic_pais tspa,
             fac_docum_conta_cabec doc,
             ped_segui_pedid psp,
             mae_clien_direc dir,
             (SELECT ot.sec_nume_docu,
                     ot.num_docu,
                     ot.fec_proc,
                     est.val_desc || ' / ' || nov.val_desc descripcion,
                     OT.VAL_LATI, 
                     OT.VAL_LONG, 
                     OT.HOR_FACT,
                     ot.cod_nove novedad,
                     CASE
                       WHEN ot.cod_esta_entr IN ('01', '04', '06') THEN
                        '01'
                       WHEN ot.cod_esta_ent2 IN ('01', '04', '06') THEN
                        '01'
                     END AS estado
                FROM int_solic_conso_orden_trans ot,
                     sto_estad_orden_trans est,
                     sto_noved_orden_trans nov,
                     (SELECT DISTINCT num_docu,
                                      MAX(sec_nume_docu) sec_nume_docu
                        FROM int_solic_conso_orden_trans
                       WHERE cod_pais = pscodigopais
                         AND tip_orde = 'BEP'
                         AND cod_peri = pscodigoperiodo
                       GROUP BY num_docu) arctmp
               WHERE ot.sec_nume_docu = arctmp.sec_nume_docu
                 AND nvl(ot.cod_esta_ent2, ot.cod_esta_entr) =
                     est.cod_esta_entr(+)
                 AND ot.tip_orde = est.cod_tipo_orde_tran(+)
                 AND ot.cod_nove = nov.cod_nove(+)
                 AND ot.cod_peri = pscodigoperiodo) ot
       WHERE con.perd_oid_peri = lnidperiodo
         AND con.clie_oid_clie = cli.oid_clie
         AND con.val_nume_soli = ot.num_docu(+)
         AND doc.cldi_oid_clie_dire = dir.oid_clie_dire
         AND con.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND dts.val_oid = tspa.tsol_oid_tipo_soli
         AND con.zzon_oid_zona = zon.oid_zona
         AND con.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = secc.oid_secc
         AND zon.zorg_oid_regi = reg.oid_regi
         AND ztad.terr_oid_terr = terr.oid_terr
         AND doc.soca_oid_soli_cabe = con.oid_soli_cabe
         AND psp.soca_oid_soli_cabe = con.oid_soli_cabe
         AND dts.attr_enti = 'PED_TIPO_SOLIC'
         AND con.ind_ts_no_conso = 0
         AND doc.tido_oid_tipo_docu = 1
         AND con.fec_fact = to_date(psfechainicio, 'DD/MM/YYYY')
         AND (lsflagregioneszonas = '0' OR
             (lsflagregioneszonas = '1' AND
             zon.cod_zona IN
             (SELECT cod_zona FROM ape_repor_distr_factu_zona)))
         AND (pscentroacopio IS NULL OR (pscentroacopio IS NOT NULL AND
             psp.cod_cent_acop = pscentroacopio))
         AND (pscompaniatrans IS NULL OR
             (pscompaniatrans IS NOT NULL AND
             psp.nom_comp_tran = pscompaniatrans));

  ELSIF (psfechainicio IS NOT NULL AND psfechafin IS NOT NULL) THEN
    INSERT INTO ape_repor_distr_factu
      (cod_peri,
       cod_regi,
       cod_zona,
       cod_secc,
       cod_terr,
       cod_clie,
       nom_comp_tran,
       cod_cent_acop,
       nom_cent_acop,
       fec_entr,
       ind_reme,
       num_docu_iden,
       nom_clie,
       fec_fact,
       num_bole,
       num_fact,
       des_dire,
       num_tele,
       num_celu,
       val_prim,
       des_esta,
       tip_soli,
       val_impo_flet_tota,
       val_impo_impu_tota,
       val_prec_neto_tota,
       fec_sigu_fact,
       fec_sigu_entr,
       tip_bole,
       fec_ante_fact,
       fec_ante_entr,
       des_depa,
       des_ciud,
       des_pobl,
       fec_real_entr,
       VAL_LATI, 
       VAL_LONG, 
       HOR_FACT,
       des_nove,
       ind_triu,
       usu_digi,
       ind_pedi_anul,
       clie_oid_clie,
       val_turn,
       fec_entr_orig)
      SELECT pscodigoperiodo AS campania,
             reg.cod_regi AS region,
             zon.cod_zona AS zona,
             secc.cod_secc AS seccion,
             terr.cod_terr AS terr,
             cli.cod_clie AS consultora,
             psp.nom_comp_tran,
             psp.cod_cent_acop,
             psp.nom_cent_acop,
             fec AS fecha_entrega_estimada,
             psp.ind_comp remesa,
             (SELECT num_docu_iden
                FROM mae_clien_ident iden
               WHERE iden.clie_oid_clie = con.clie_oid_clie
                 AND tdoc_oid_tipo_docu = 2001) AS cedula,
             cli.val_ape1 || ' ' || cli.val_ape2 || ', ' || cli.val_nom1 || ' ' ||
             cli.val_nom2 AS nombres,
             con.fec_fact AS fecha_fact,
             con.val_nume_soli AS numero_boleta,
             doc.num_docu_lega AS factura,
             doc.val_dire_comp || ' ' || dir.val_barr AS direccion,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 1) AS telefono,
             (SELECT val_text_comu
                FROM mae_clien_comun com
               WHERE com.clie_oid_clie = con.clie_oid_clie
                 AND ticm_oid_tipo_comu = 6) AS celular,
             (SELECT MIN(val_nomb_peri)
                FROM cra_perio             crp,
                     mae_clien_prime_conta cpc
               WHERE cpc.perd_oid_peri = crp.oid_peri
                 AND cpc.clie_oid_clie = con.clie_oid_clie) AS primero,
             (SELECT val_i18n
                FROM mae_clien_histo_estat,
                     gen_i18n_sicc_comun
               WHERE clie_oid_clie = con.clie_oid_clie
                 AND perd_oid_peri <= con.perd_oid_peri
                 AND (con.perd_oid_peri <= perd_oid_peri_peri_fin OR
                     perd_oid_peri_peri_fin IS NULL)
                 AND attr_enti = 'MAE_ESTAT_CLIEN'
                 AND val_oid = esta_oid_esta_clie
                 and rownum = 1) AS estatus,
             dts.val_i18n AS tipo_solicitud,
             con.val_impo_flet_tota_loca,
             con.val_impo_impu_tota_loca,
             con.val_prec_neto_tota_loca,
             psp.fec_sigu_fact AS fec_sigu_fact,
             psp.fec_sigu_entr AS fec_sigu_entr,
             CASE
               WHEN EXISTS (SELECT 1
                       FROM ped_solic_cabec
                      WHERE soca_oid_soli_cabe = con.oid_soli_cabe
                        AND ind_oc = 1) THEN
                'PEDIDO'
               ELSE
                'POSTVENTA'
             END tipo_boleta,
             (SELECT MAX(x.fec_sigu_fact)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_fact,
             (SELECT MAX(x.fec_sigu_entr)
                FROM ped_segui_pedid x,
                     ped_solic_cabec y
               WHERE y.perd_oid_peri = lnidperiodomenos1
                 AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND y.clie_oid_clie = con.clie_oid_clie) fec_ante_entr,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 IS NULL
                 AND orde_3 IS NULL) departamento,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 IS NULL) ciudad,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = substr(dir.cod_unid_geog, 1, 6)
                 AND orde_2 = substr(dir.cod_unid_geog, 7, 6)
                 AND orde_3 = substr(dir.cod_unid_geog, 13, 6)
                 AND rownum = 1) poblacion,
             decode(ot.estado, '01', ot.fec_proc, NULL) fecha_real_entrega,
             OT.VAL_LATI, 
             OT.VAL_LONG, 
             OT.HOR_FACT,
             ot.descripcion novedad,
             'NO' indicador_triunfadora,
             pscodigousuario AS usuario,
             decode(con.esso_oid_esta_soli, 4, 'SI', 'NO') pedido_anulado,
             con.clie_oid_clie,
             psp.val_turn,
             psp.fec_orig
        FROM ped_solic_cabec con,
             zon_regio reg,
             mae_clien cli,
             gen_i18n_sicc_comun dts,
             zon_zona zon,
             zon_terri terr,
             zon_terri_admin ztad,
             zon_secci secc,
             ped_tipo_solic_pais tspa,
             fac_docum_conta_cabec doc,
             ped_segui_pedid psp,
             mae_clien_direc dir,
             (SELECT ot.sec_nume_docu,
                     ot.num_docu,
                     ot.fec_proc,
                     est.val_desc || ' / ' || nov.val_desc descripcion,
                     OT.VAL_LATI, 
                     OT.VAL_LONG, 
                     OT.HOR_FACT,
                     ot.cod_nove novedad,
                     CASE
                       WHEN ot.cod_esta_entr IN ('01', '04', '06') THEN
                        '01'
                       WHEN ot.cod_esta_ent2 IN ('01', '04', '06') THEN
                        '01'
                     END AS estado
                FROM int_solic_conso_orden_trans ot,
                     sto_estad_orden_trans est,
                     sto_noved_orden_trans nov,
                     (SELECT DISTINCT num_docu,
                                      MAX(sec_nume_docu) sec_nume_docu
                        FROM int_solic_conso_orden_trans
                       WHERE cod_pais = pscodigopais
                         AND tip_orde = 'BEP'
                         AND cod_peri = pscodigoperiodo
                       GROUP BY num_docu) arctmp
               WHERE ot.sec_nume_docu = arctmp.sec_nume_docu
                 AND nvl(ot.cod_esta_ent2, ot.cod_esta_entr) =
                     est.cod_esta_entr(+)
                 AND ot.tip_orde = est.cod_tipo_orde_tran(+)
                 AND ot.cod_nove = nov.cod_nove(+)
                 AND ot.cod_peri = pscodigoperiodo) ot
       WHERE con.perd_oid_peri = lnidperiodo
         AND con.clie_oid_clie = cli.oid_clie
         AND con.val_nume_soli = ot.num_docu(+)
         AND doc.cldi_oid_clie_dire = dir.oid_clie_dire
         AND con.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND dts.val_oid = tspa.tsol_oid_tipo_soli
         AND con.zzon_oid_zona = zon.oid_zona
         AND con.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = secc.oid_secc
         AND zon.zorg_oid_regi = reg.oid_regi
         AND ztad.terr_oid_terr = terr.oid_terr
         AND doc.soca_oid_soli_cabe = con.oid_soli_cabe
         AND psp.soca_oid_soli_cabe = con.oid_soli_cabe
         AND dts.attr_enti = 'PED_TIPO_SOLIC'
         AND con.ind_ts_no_conso = 0
         AND doc.tido_oid_tipo_docu = 1
         AND con.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY')
         AND con.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY')
         AND (lsflagregioneszonas = '0' OR
             (lsflagregioneszonas = '1' AND
             zon.cod_zona IN
             (SELECT cod_zona FROM ape_repor_distr_factu_zona)))
         AND (pscentroacopio IS NULL OR (pscentroacopio IS NOT NULL AND
             psp.cod_cent_acop = pscentroacopio))
         AND (pscompaniatrans IS NULL OR
             (pscompaniatrans IS NOT NULL AND
             psp.nom_comp_tran = pscompaniatrans));
  END IF;

  OPEN c_clie_vip;
  LOOP
    FETCH c_clie_vip BULK COLLECT
      INTO v_clie_oid_clie LIMIT rows;
    IF v_clie_oid_clie.count > 0 THEN

      FORALL i IN 1 .. v_clie_oid_clie.count
        UPDATE ape_repor_distr_factu occ
           SET occ.ind_triu = 'SI'
         WHERE occ.clie_oid_clie = v_clie_oid_clie(i)
           AND usu_digi = pscodigousuario;

    END IF;
    EXIT WHEN c_clie_vip%NOTFOUND;
  END LOOP;
  CLOSE c_clie_vip;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR APE_PR_GENER_DATOS_FACTU_REAL: ' ||
                            ls_sqlerrm);
END ape_pr_gener_datos_factu_real;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Reporte Distribucion Facturacion Real
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 15/01/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_DISTR_FACTU_REAL(
    psCodigoPeriodo                     VARCHAR2,
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;

  CURSOR c_interfaz IS
    SELECT COD_PERI CAMPANIA,
         COD_REGI REGION,
         COD_ZONA ZONA,
         COD_SECC SECCION,
         COD_TERR TERR,
         COD_CLIE CONSULTORA,
         NOM_COMP_TRAN NOM_COMP_TRAN,
         COD_CENT_ACOP COD_CENT_ACOP,
         NOM_CENT_ACOP NOM_CENT_ACOP,
         NVL(TO_CHAR(FEC_ENTR_ORIG, 'DD/MM/YYYY'),TO_CHAR(FEC_ENTR, 'DD/MM/YYYY')) FECHA_ENTREGA_ESTIMADA_ORIG,
         TO_CHAR(FEC_ENTR, 'DD/MM/YYYY') FECHA_ENTREGA_ESTIMADA,
         VAL_TURN TURNO,
         IND_REME REMESA,
         NUM_DOCU_IDEN CEDULA,
         NOM_CLIE NOMBRES,
         TO_CHAR(FEC_FACT, 'DD/MM/YYYY') FECHA_FACT,
         NUM_BOLE NUMERO_BOLETA,
         NUM_FACT FACTURA,
         DES_DIRE DIRECCION,
         NUM_TELE TELEFONO,
         NUM_CELU CELULAR,
         VAL_PRIM PRIMERO,
         DES_ESTA ESTATUS,
         TIP_SOLI TIPO_SOLICITUD,
         VAL_IMPO_FLET_TOTA VAL_IMPO_FLET_TOTA_LOCA,
         VAL_IMPO_IMPU_TOTA VAL_IMPO_IMPU_TOTA_LOCA,
         VAL_PREC_NETO_TOTA VAL_PREC_NETO_TOTA_LOCA,
         TO_CHAR(FEC_SIGU_FACT, 'DD/MM/YYYY') FEC_SIGU_FACT,
         TO_CHAR(FEC_SIGU_ENTR, 'DD/MM/YYYY') FEC_SIGU_ENTR,
         TIP_BOLE TIPO_BOLETA,
         TO_CHAR(FEC_ANTE_FACT, 'DD/MM/YYYY') FEC_ANTE_FACT,
         TO_CHAR(FEC_ANTE_ENTR, 'DD/MM/YYYY') FEC_ANTE_ENTR,
         DES_DEPA DEPARTAMENTO,
         DES_CIUD CIUDAD,
         DES_POBL POBLACION,
         TO_CHAR(FEC_REAL_ENTR, 'DD/MM/YYYY') FECHA_REAL_ENTREGA,
         HOR_FACT,                              
         VAL_LATI, 
         VAL_LONG, 
         DES_NOVE NOVEDAD,
         IND_TRIU INDICADOR_TRIUNFADORA,
         IND_PEDI_ANUL INDICADOR_ANULADO
    FROM APE_REPOR_DISTR_FACTU
   WHERE USU_DIGI = psUsuario
   ORDER BY FEC_FACT;

TYPE interfazTipo IS RECORD (
  v_COD_PERI           APE_REPOR_DISTR_FACTU.COD_PERI%TYPE,
  v_COD_REGI           APE_REPOR_DISTR_FACTU.COD_REGI%TYPE,
  v_COD_ZONA           APE_REPOR_DISTR_FACTU.COD_ZONA%TYPE,
  v_COD_SECC           APE_REPOR_DISTR_FACTU.COD_SECC%TYPE,
  v_COD_TERR           APE_REPOR_DISTR_FACTU.COD_TERR%TYPE,
  v_COD_CLIE           APE_REPOR_DISTR_FACTU.COD_CLIE%TYPE,
  v_NOM_COMP_TRAN      APE_REPOR_DISTR_FACTU.NOM_COMP_TRAN%TYPE,
  v_COD_CENT_ACOP      APE_REPOR_DISTR_FACTU.COD_CENT_ACOP%TYPE,
  v_NOM_CENT_ACOP      APE_REPOR_DISTR_FACTU.NOM_CENT_ACOP%TYPE,
  v_FEC_ENTR_ORIG      VARCHAR2(10),
  v_FEC_ENTR           VARCHAR2(10),
  v_TURNO              APE_REPOR_DISTR_FACTU.VAL_TURN%TYPE,
  v_IND_REME           APE_REPOR_DISTR_FACTU.IND_REME%TYPE,
  v_NUM_DOCU_IDEN      APE_REPOR_DISTR_FACTU.NUM_DOCU_IDEN%TYPE,
  v_NOM_CLIE           APE_REPOR_DISTR_FACTU.NOM_CLIE%TYPE,
  v_FEC_FACT           VARCHAR2(10),
  v_NUM_BOLE           APE_REPOR_DISTR_FACTU.NUM_BOLE%TYPE,
  v_NUM_FACT           APE_REPOR_DISTR_FACTU.NUM_FACT%TYPE,
  v_DES_DIRE           APE_REPOR_DISTR_FACTU.DES_DIRE%TYPE,
  v_NUM_TELE           APE_REPOR_DISTR_FACTU.NUM_TELE%TYPE,
  v_NUM_CELU           APE_REPOR_DISTR_FACTU.NUM_CELU%TYPE,
  v_VAL_PRIM           APE_REPOR_DISTR_FACTU.VAL_PRIM%TYPE,
  v_DES_ESTA           APE_REPOR_DISTR_FACTU.DES_ESTA%TYPE,
  v_TIP_SOLI           APE_REPOR_DISTR_FACTU.TIP_SOLI%TYPE,
  v_VAL_IMPO_FLET_TOTA APE_REPOR_DISTR_FACTU.VAL_IMPO_FLET_TOTA%TYPE,
  v_VAL_IMPO_IMPU_TOTA APE_REPOR_DISTR_FACTU.VAL_IMPO_IMPU_TOTA%TYPE,
  v_VAL_PREC_NETO_TOTA APE_REPOR_DISTR_FACTU.VAL_PREC_NETO_TOTA%TYPE,
  v_FEC_SIGU_FACT      VARCHAR2(10),
  v_FEC_SIGU_ENTR      VARCHAR2(10),
  v_TIP_BOLE           APE_REPOR_DISTR_FACTU.TIP_BOLE%TYPE,
  v_FEC_ANTE_FACT      VARCHAR2(10),
  v_FEC_ANTE_ENTR      VARCHAR2(10),
  v_DES_DEPA           APE_REPOR_DISTR_FACTU.DES_DEPA%TYPE,
  v_DES_CIUD           APE_REPOR_DISTR_FACTU.DES_CIUD%TYPE,
  v_DES_POBL           APE_REPOR_DISTR_FACTU.DES_POBL%TYPE,
  v_FEC_REAL_ENTR      VARCHAR2(10),
  v_HOR_FACT           APE_REPOR_DISTR_FACTU.HOR_FACT%TYPE,                              
  v_VAL_LATI           APE_REPOR_DISTR_FACTU.VAL_LATI%TYPE, 
  v_VAL_LONG           APE_REPOR_DISTR_FACTU.VAL_LONG%TYPE, 
  v_DES_NOVE           APE_REPOR_DISTR_FACTU.DES_NOVE%TYPE,
  v_IND_TRIU           APE_REPOR_DISTR_FACTU.IND_TRIU%TYPE,
  v_IND_PEDI_ANUL           APE_REPOR_DISTR_FACTU.IND_PEDI_ANUL%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  lnOidCodigoPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_COD_PERI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_REGI||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_ZONA||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_SECC ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_TERR||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CLIE||'")' ||','||
                    '=T("'||interfazRecord(x).v_NOM_COMP_TRAN ||'")' ||','||
                    '=T("'||interfazRecord(x).v_COD_CENT_ACOP||'")' ||','||
                    '=T("'||interfazRecord(x).v_NOM_CENT_ACOP ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_ENTR_ORIG||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_ENTR||'")' ||','||
                    '=T("'||interfazRecord(x).v_TURNO||'")' ||','||                    
                    '=T("'||interfazRecord(x).v_IND_REME||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_DOCU_IDEN||'")' ||','||
                    '"'||interfazRecord(x).v_NOM_CLIE||'"' ||','||
                    '=T("'||interfazRecord(x).v_FEC_FACT||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_BOLE||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_FACT||'")' ||','||
                    '"'||interfazRecord(x).v_DES_DIRE||'"' ||','||
                    '=T("'||interfazRecord(x).v_NUM_TELE||'")' ||','||
                    '=T("'||interfazRecord(x).v_NUM_CELU||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_PRIM||'")' ||','||
                    '=T("'||interfazRecord(x).v_DES_ESTA ||'")' ||','||
                    '=T("'||interfazRecord(x).v_TIP_SOLI ||'")' ||','||
                    '=T("'||trim(to_char(interfazRecord(x).v_VAL_IMPO_FLET_TOTA , '999999999999.99')) ||'")' ||','||
                    '=T("'||trim(to_char(interfazRecord(x).v_VAL_IMPO_IMPU_TOTA , '999999999999.99')) ||'")' ||','||
                    '=T("'||trim(to_char(interfazRecord(x).v_VAL_PREC_NETO_TOTA , '999999999999.99')) ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_SIGU_FACT||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_SIGU_ENTR||'")' ||','||
                    '=T("'||interfazRecord(x).v_TIP_BOLE ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_ANTE_FACT||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_ANTE_ENTR||'")' ||','||
                    '=T("'||interfazRecord(x).v_DES_DEPA ||'")' ||','||
                    '=T("'||interfazRecord(x).v_DES_CIUD ||'")' ||','||
                    '=T("'||interfazRecord(x).v_DES_POBL ||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_REAL_ENTR||'")' ||','||
                    '=T("'||interfazRecord(x).v_HOR_FACT||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_LATI||'")' ||','||
                    '=T("'||interfazRecord(x).v_VAL_LONG||'")' ||','||                                                            
                    '"'||interfazRecord(x).v_DES_NOVE ||'"' ||','||
                    '=T("'||interfazRecord(x).v_IND_TRIU ||'")' ||','||
                    '=T("'||interfazRecord(x).v_IND_PEDI_ANUL ||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_REPOR_DISTR_FACTU_REAL: '||ls_sqlerrm);
END APE_PR_REPOR_DISTR_FACTU_REAL;

/***************************************************************************
Descripcion       : Genera Hoja de Picado APE para Proceso de PRINT.
Fecha Creacion    : 27/01/2016
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE APE_PR_GENER_PICAD_PRINT(
  psCodigoPeriodo    VARCHAR2,
  psFechaFacturacion VARCHAR2,
  pscodigoproceso VARCHAR2,
  pscodigoPaquete  VARCHAR2,
  psusuario VARCHAR2
)
IS

  CURSOR c_consultoras IS
  SELECT
    y.cod_proc, 
    y.cod_paqu,
    x.cod_clie, 
    x.cod_regi, 
    x.cod_zona, 
    x.lin_arma, 
    x.tot_caja, 
    x.tip_serv, 
    x.num_pedi, 
    x.fec_fact, 
    x.cam_fact, 
    x.ind_cheq, 
    x.cen_dist, 
    x.usu_crea, 
    x.fec_crea
  FROM imp_tmp_print_picad x,
       imp_print_spool y
  WHERE y.COD_PROC = pscodigoproceso
    AND y.COD_PAQU = pscodigoPaquete
    AND y.COD_CAMP = psCodigoPeriodo
    AND y.FEC_FACT = to_date(psFechaFacturacion, 'dd/mm/yyyy')
    AND y.COD_CLIE = x.COD_CLIE
  --WHERE x.cod_clie = '0666245'
  ;
  
  TYPE consultorasTab         IS TABLE OF c_consultoras%ROWTYPE ;
  consultoraRecord            consultorasTab;
  
  CURSOR c_detalle(vsNumPedi VARCHAR2) IS
  SELECT 
    tip_serv, 
    num_pedi, 
    val_anaq, 
    nom_prod, 
    uni_emba, 
    num_caja, 
    tip_caja, 
    ord_lipi, 
    lin_emba, 
    est_trab, 
    cod_sap, 
    usu_crea, 
    fec_crea
  FROM imp_tmp_print_picad_detal x
  WHERE num_pedi = vsNumPedi
  ORDER BY num_caja, est_trab, ord_lipi;
  
  TYPE detalleTab             IS TABLE OF c_detalle%ROWTYPE ;
  detalleRecord               detalleTab;
  
  CURSOR c_intermedio IS
  SELECT 
    cod_clie, 
    num_pedi, 
    tip_serv, 
    tip_caja, 
    lin_emba, 
    num_caja, 
    num_pagi, 
    ord_regi, 
    ind_colu, 
    est_trab, 
    val_anaq, 
    nom_prod, 
    cod_sap, 
    uni_emba, 
    ind_regi
  FROM IMP_GTT_PRINT_PICAD_DETAL x
  ORDER BY x.num_caja, x.num_pagi, x.ind_colu, x.ord_regi;
  
  TYPE intermedioTab          IS TABLE OF c_intermedio%ROWTYPE ;
  intermedioRecord               intermedioTab;
  
  W_FILAS                     NUMBER := 5000 ;
  lnTotalCajas                imp_tmp_print_picad.tot_caja%TYPE;
  lnCajaActual                imp_tmp_print_picad_detal.num_caja%TYPE;
  lnCajaRegistro              imp_tmp_print_picad_detal.num_caja%TYPE;
  lsEstacionActual            imp_tmp_print_picad_detal.est_trab%TYPE;
  lsEstacionRegistro          imp_tmp_print_picad_detal.est_trab%TYPE;
  lnMaximoRegistroxColumna    NUMBER(12) := 11; 
  lnOidRegi                   NUMBER(12);
  
  INDICADOR_PRODUCTO          VARCHAR2(1) := 'P';
  INDICADOR_BLANCO            VARCHAR2(1) := 'B';
  INDICADOR_LINEA             VARCHAR2(1) := 'L';
  INDICADOR_CONTINUA          VARCHAR2(1) := 'C';
  INDICADOR_FIN               VARCHAR2(1) := 'F';
  lsIndiccadorRegistroPar     VARCHAR2(1);
  lnPagina                    NUMBER(12);
  COLUMNA_A                   VARCHAR2(1) := 'A';
  COLUMNA_B                   VARCHAR2(1) := 'B';
  lsColumna                   VARCHAR2(1);
  lbCambiarPagina             BOOLEAN;
  lnPaginaActual              NUMBER;
  lsScript                    VARCHAR2(100);
  lnX                         NUMBER;
BEGIN
  DELETE FROM imp_print_picad;
  DELETE FROM imp_print_picad_detal;
  DELETE FROM imp_gtt_print_picad_detal;
  
  OPEN c_consultoras;
  LOOP
   FETCH c_consultoras BULK COLLECT INTO consultoraRecord LIMIT W_FILAS;
   IF consultoraRecord.COUNT > 0 THEN
      FOR x IN consultoraRecord.FIRST .. consultoraRecord.LAST LOOP
          lnX := x;
          lnTotalCajas := consultoraRecord(x).tot_caja;
          lnOidRegi := 1;
          lnPagina := 1;
          lsColumna := COLUMNA_A;
          lbCambiarPagina := FALSE;
          
          /* Obteniendo detalle de tabla temporal TMP */
          OPEN c_detalle(consultoraRecord(x).num_pedi);
          LOOP
             FETCH c_detalle BULK COLLECT INTO detalleRecord LIMIT W_FILAS;
             IF detalleRecord.COUNT > 0 THEN
                FOR y IN detalleRecord.FIRST .. detalleRecord.LAST LOOP 
                    lbCambiarPagina := FALSE;
                    IF (y = 1) THEN
                        lnCajaActual := detalleRecord(y).num_caja;
                        lsEstacionActual := detalleRecord(y).est_trab;
                        lnOidRegi := 1;
                        lnPagina := 1;
                        lsColumna := COLUMNA_A;
                        
                    END IF;   
                    
                    /* Insertando Producto */   
                    IF lnOidRegi <= lnMaximoRegistroxColumna THEN             
                        INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                            cod_clie, 
                            num_pedi, 
                            tip_serv,
                            tip_caja, 
                            lin_emba, 
                            num_caja, 
                            NUM_PAGI,
                            ord_regi, 
                            IND_COLU,
                            est_trab, 
                            val_anaq, 
                            nom_prod, 
                            cod_sap, 
                            uni_emba, 
                            ind_regi
                            )
                        VALUES (
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).num_pedi,
                            consultoraRecord(x).tip_serv,
                            detalleRecord(y).tip_caja,
                            detalleRecord(y).lin_emba,
                            detalleRecord(y).num_caja,
                            lnPagina,
                            lnOidRegi,
                            lsColumna,
                            detalleRecord(y).est_trab,
                            detalleRecord(y).val_anaq,
                            detalleRecord(y).nom_prod,
                            detalleRecord(y).cod_sap,
                            detalleRecord(y).uni_emba,
                            INDICADOR_PRODUCTO
                         );
                        
                        lnOidRegi := lnOidRegi + 1;
                    END IF;
                    
                    /* Verificando el Siguiente Registro */
                    IF y = detalleRecord.LAST THEN
                       INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                INDICADOR_FIN
                             );
                    ELSE
                       
                       /* Verificando si cambio Caja */
                       IF lnCajaActual != detalleRecord(y + 1).num_caja THEN
                              
                            INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                INDICADOR_FIN
                             );
                               
                             lnOidRegi := 1;
                             lnPagina := 1;
                             lsColumna := COLUMNA_A;
                             lnCajaActual := detalleRecord(y + 1).num_caja;
                             lsEstacionActual := detalleRecord(y + 1).est_trab;
                       ELSE
                          /* No cambio Caja */
                          lsIndiccadorRegistroPar := INDICADOR_BLANCO;
                          
                          /* Verificando si cambio Estacion de Trabajo */
                          IF lsEstacionActual != detalleRecord(y + 1).est_trab THEN
                             lsIndiccadorRegistroPar := INDICADOR_LINEA;
                             lsEstacionActual := detalleRecord(y + 1).est_trab;
                          END IF;
                          
                          IF lnOidRegi <= lnMaximoRegistroxColumna THEN        
                            
                             INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                cod_clie, 
                                num_pedi, 
                                tip_serv,
                                tip_caja, 
                                lin_emba, 
                                num_caja, 
                                NUM_PAGI,
                                ord_regi, 
                                IND_COLU,
                                est_trab, 
                                val_anaq, 
                                nom_prod, 
                                cod_sap, 
                                uni_emba, 
                                ind_regi
                                )
                            VALUES (
                                consultoraRecord(x).cod_clie,
                                consultoraRecord(x).num_pedi,
                                consultoraRecord(x).tip_serv,
                                detalleRecord(y).tip_caja,
                                detalleRecord(y).lin_emba,
                                detalleRecord(y).num_caja,
                                lnPagina,
                                lnOidRegi,
                                lsColumna,
                                detalleRecord(y).est_trab,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                lsIndiccadorRegistroPar
                             );
                            lnOidRegi := lnOidRegi + 1;
                            IF lnOidRegi > lnMaximoRegistroxColumna THEN
                               lbCambiarPagina := TRUE;
                            END IF;
                            
                          ELSE
                            lbCambiarPagina := TRUE;  
                          END IF;
                          
                          /* Verificando posible cambio de pagina */
                          IF lbCambiarPagina THEN
                             lbCambiarPagina := FALSE;
                             IF lsColumna = COLUMNA_A THEN
                                lnOidRegi := 1;
                                lsColumna := COLUMNA_B;
                             ELSE
                                
                                 INSERT INTO IMP_GTT_PRINT_PICAD_DETAL(
                                    cod_clie, 
                                    num_pedi, 
                                    tip_serv,
                                    tip_caja, 
                                    lin_emba, 
                                    num_caja, 
                                    NUM_PAGI,
                                    ord_regi, 
                                    IND_COLU,
                                    est_trab, 
                                    val_anaq, 
                                    nom_prod, 
                                    cod_sap, 
                                    uni_emba, 
                                    ind_regi
                                    )
                                VALUES (
                                    consultoraRecord(x).cod_clie,
                                    consultoraRecord(x).num_pedi,
                                    consultoraRecord(x).tip_serv,
                                    detalleRecord(y).tip_caja,
                                    detalleRecord(y).lin_emba,
                                    detalleRecord(y).num_caja,
                                    lnPagina,
                                    lnOidRegi,
                                    lsColumna,
                                    detalleRecord(y).est_trab,
                                    NULL,
                                    NULL,
                                    NULL,
                                    NULL,
                                    INDICADOR_CONTINUA
                                 );
                                
                                lnOidRegi := 1;
                                lnPagina := lnPagina + 1;
                                lsColumna := COLUMNA_A;
                                
                                
                             END IF;  
                          END IF;
                      
                       END IF;
                        
                    END IF;
                 
                END LOOP;
             END IF;
             EXIT WHEN c_detalle%NOTFOUND;
          END LOOP;
          CLOSE c_detalle;
          
          /* Recorriendo Cursor Intermedio Generado previamente */
          OPEN c_intermedio;
          LOOP
             FETCH c_intermedio BULK COLLECT INTO intermedioRecord LIMIT W_FILAS;
             IF intermedioRecord.COUNT > 0 THEN
                lnCajaActual := -1;
                lnPaginaActual := -1;
                lnPagina := 0;
                FOR z IN intermedioRecord.FIRST .. intermedioRecord.LAST LOOP
                    IF lnCajaActual != intermedioRecord(z).NUM_CAJA OR lnPaginaActual != intermedioRecord(z).NUM_PAGI THEN
                       lnPagina := lnPagina + 1;
                       INSERT INTO imp_print_picad(
                          prsp_cod_proc, 
                          prsp_cod_clie, 
                          prsp_cod_paqu, 
                          num_pagi_pica, 
                          cod_zona, 
                          val_tota_pagi, 
                          val_tipo_caja, 
                          val_nume_caja, 
                          val_tota_caja, 
                          ind_cheq, 
                          val_zona_trab_01, 
                          val_zona_trab_02, 
                          val_zona_trab_03, 
                          val_line_emba, 
                          val_info_caja, 
                          usu_crea, 
                          fec_crea, 
                          usu_modi, 
                          fec_modi, 
                          ind_acti
                          )
                       VALUES (
                          consultoraRecord(x).cod_proc,
                          consultoraRecord(x).cod_clie,
                          consultoraRecord(x).cod_paqu,
                          lnPagina,
                          consultoraRecord(x).COD_ZONA,
                          NULL,
                          intermedioRecord(z).TIP_CAJA,
                          intermedioRecord(z).NUM_CAJA,
                          NULL,
                          consultoraRecord(x).IND_CHEQ,
                          NULL,
                          NULL,
                          NULL,
                          intermedioRecord(z).LIN_EMBA,
                          NULL,
                          USER,
                          SYSDATE,
                          NULL,
                          NULL,
                          '1'
                        );
                      lnCajaActual := intermedioRecord(z).NUM_CAJA;
                      lnPaginaActual := intermedioRecord(z).NUM_PAGI;
                        
                    END IF;
                    
                    lsScript := '';
                    IF intermedioRecord(z).IND_REGI = INDICADOR_LINEA THEN
                       lsScript := 'LINE';
                    END IF;
                    IF intermedioRecord(z).IND_REGI = INDICADOR_FIN THEN
                       lsScript := 'END';
                    END IF;
                    IF intermedioRecord(z).IND_REGI = INDICADOR_CONTINUA THEN
                       lsScript := 'CONTINUE';
                    END IF;
                    
                    IF intermedioRecord(z).IND_COLU = COLUMNA_A THEN
                       
                       INSERT INTO imp_print_picad_detal(
                            prsp_cod_proc, 
                            prsp_cod_clie, 
                            prsp_cod_paqu, 
                            prpi_num_pagi, 
                            oid_pica_deta, 
                            num_caja, 
                            ord_impr, 
                            val_anaq_a, 
                            val_prod_a, 
                            val_cant_a, 
                            val_esta_trab_a, 
                            val_scri_a, 
                            usu_crea, 
                            fec_crea,
                            IND_ACTI
                        )
                        VALUES (
                            consultoraRecord(x).cod_proc,
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).cod_paqu,
                            lnPagina,
                            intermedioRecord(z).ORD_REGI,
                            intermedioRecord(z).num_caja,
                            intermedioRecord(z).NUM_PAGI,
                            intermedioRecord(z).VAL_ANAQ,
                            intermedioRecord(z).NOM_PROD,
                            intermedioRecord(z).UNI_EMBA,
                            intermedioRecord(z).EST_TRAB,
                            lsScript,
                            USER,
                            sysdate,
                            '1'
                        );
                    ELSE
                      IF intermedioRecord(z).ORD_REGI <= lnMaximoRegistroxColumna THEN
                          UPDATE imp_print_picad_detal
                          SET
                                val_anaq_b = intermedioRecord(z).VAL_ANAQ, 
                                val_prod_b = intermedioRecord(z).NOM_PROD,
                                val_cant_b = intermedioRecord(z).UNI_EMBA,
                                val_esta_trab_b = intermedioRecord(z).EST_TRAB,
                                val_scri_b = lsScript
                          WHERE prsp_cod_proc = consultoraRecord(x).cod_proc 
                            AND prsp_cod_clie = consultoraRecord(x).cod_clie
                            AND prsp_cod_paqu = consultoraRecord(x).cod_paqu
                            AND prpi_num_pagi = lnPagina
                            AND oid_pica_deta = intermedioRecord(z).ORD_REGI;
                      ELSE
                         INSERT INTO imp_print_picad_detal(
                            prsp_cod_proc, 
                            prsp_cod_clie, 
                            prsp_cod_paqu, 
                            prpi_num_pagi, 
                            oid_pica_deta, 
                            num_caja, 
                            ord_impr, 
                            val_anaq_b, 
                            val_prod_b, 
                            val_cant_b, 
                            val_esta_trab_b, 
                            val_scri_b, 
                            usu_crea, 
                            fec_crea,
                            IND_ACTI
                        )
                        VALUES (
                            consultoraRecord(x).cod_proc,
                            consultoraRecord(x).cod_clie,
                            consultoraRecord(x).cod_paqu,
                            lnPagina,
                            intermedioRecord(z).ORD_REGI,
                            intermedioRecord(z).num_caja,
                            intermedioRecord(z).NUM_PAGI,
                            intermedioRecord(z).VAL_ANAQ,
                            intermedioRecord(z).NOM_PROD,
                            intermedioRecord(z).UNI_EMBA,
                            intermedioRecord(z).EST_TRAB,
                            lsScript,
                            USER,
                            sysdate,
                            '1'
                        );
                      END IF;
                    END IF;
                    
                END LOOP;
             END IF;
             EXIT WHEN c_intermedio%NOTFOUND;
          END LOOP;
          CLOSE c_intermedio;
          
          UPDATE imp_print_picad
          SET val_tota_pagi = lnPagina,
              val_tota_caja = lnCajaActual
          WHERE prsp_cod_proc = consultoraRecord(x).cod_proc
            AND prsp_cod_clie = consultoraRecord(x).cod_clie;
          
          
          DELETE FROM IMP_GTT_PRINT_PICAD_DETAL;
          COMMIT;
      END LOOP;
    END IF;
    EXIT WHEN c_consultoras%NOTFOUND;
 END LOOP;
 CLOSE c_consultoras;
 
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_GENER_PICAD_PRINT (x): '||lnX||' - '||ls_sqlerrm);
END APE_PR_GENER_PICAD_PRINT;

/***************************************************************************
Descripcion       : Genera data para el reporte de Inventario de Campo - RESUMEN
Fecha Creacion    : 11/02/2016
Autor             : Gonzalo Huertas
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE APE_PR_GENER_DATOS_INVEN_CARES(
  pscodigopais    VARCHAR2,
  pscodigousuario VARCHAR2,
  psfechainicio   VARCHAR2,
  psfechafin      VARCHAR2,
  pscentroacopio  VARCHAR2,
  pscompaniatrans VARCHAR2
) IS

  lsflagregioneszonas VARCHAR2(1);
  lnidperiodo         NUMBER;
  lnidperiodomenos1   NUMBER;


  rows NUMBER := 5000;
  i    BINARY_INTEGER := 0;

BEGIN

  SELECT decode(COUNT(1), 0, '0', '1')
    INTO lsflagregioneszonas
    FROM APE_REPOR_INVEN_CAMPO_ZONA;

  DELETE FROM APE_REPOR_INVEN_CAMPO_RESU WHERE usu_digi = pscodigousuario;

    INSERT INTO APE_REPOR_INVEN_CAMPO_RESU
       (CHEQUEOCAMPO,
        CHEQUEOAPE,
        ORIGEN,
        CANTIDAD,
        USU_DIGI)
        select distinct decode(psp.tip_cheq, null, psp.hor_cheq, psp.tip_cheq) CHEQUEOCAMPO,
                        psp.tip_cheq_inic CHEQUEOAPE,
                        decode(pedcheq.origen,
                               1,
                               'SSICC',
                               decode(pedcheq.origen, 2, 'APE', 'AMBOS')) origen,
                        count(*) cantidad,
                        pscodigousuario
          from ped_segui_pedid psp,
               (select OID_SOLI_CABE, sum(origen) origen
                  from (select PSC1.OID_SOLI_CABE, 2 origen
                          from APE_PEDID_CHEQU apc, ped_solic_cabec psc1
                         where apc.num_pedi = PSC1.VAL_NUME_SOLI
                           and psc1.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY') ---VarFecIni
                           and psc1.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY') ---VarFecFin
                        union
                        select PSC2.OID_SOLI_CABE, 1 origen
                          from ped_pedid_chequ ppc, ped_solic_cabec psc2
                         where PPC.VAL_NUME_SOLI = PSC2.VAL_NUME_SOLI
                           and psc2.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY') ---VarFecIni
                           and psc2.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY')) ---VarFecFin
                 group by OID_SOLI_CABE) pedcheq,
               ped_solic_cabec psc,
               V_MAE_CLIE_UNIDA_ADMIN mae
         where PSP.SOCA_OID_SOLI_CABE = pedcheq.OID_SOLI_CABE
           and psp.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
           and PSC.CLIE_OID_CLIE = mae.oid_clie
           AND psc.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY')
           AND psc.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY')
        AND (lsflagregioneszonas = '0' OR
             (lsflagregioneszonas = '1' AND
             mae.cod_zona IN
             (SELECT cod_zona FROM APE_REPOR_INVEN_CAMPO_ZONA)))
         AND (pscentroacopio IS NULL OR (pscentroacopio IS NOT NULL AND
             PSP.COD_CENT_ACOP = pscentroacopio))
         AND (pscompaniatrans IS NULL OR
             (pscompaniatrans IS NOT NULL AND
             PSP.COD_COMP_TRAN = pscompaniatrans))
         group by decode(psp.tip_cheq, null, psp.hor_cheq, psp.tip_cheq),
                  psp.tip_cheq_inic,
                  pedcheq.origen;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR APE_PR_GENER_DATOS_INVEN_CARES: ' ||
                            ls_sqlerrm);
END APE_PR_GENER_DATOS_INVEN_CARES;

/***************************************************************************
Descripcion       : Genera data para el reporte de Inventario de Campo - DETALLE
Fecha Creacion    : 11/02/2016
Autor             : Gonzalo Huertas
Parametros        :
  psCodigoPais    : Codigo Pais
  psCodigoUsuario : Codigo Usuario
  psFechaInicio   : Fecha Inicio Facturacion
  psFechaFin      : Fecha Fin Facturacion
  psCentroAcopio  : Centro de Acopio
  psCompaniaTrans : Compania de Transporte
***************************************************************************/
PROCEDURE APE_PR_GENER_DATOS_INVEN_CADET(
  pscodigopais    VARCHAR2,
  pscodigousuario VARCHAR2,
  psfechainicio   VARCHAR2,
  psfechafin      VARCHAR2,
  pscentroacopio  VARCHAR2,
  pscompaniatrans VARCHAR2
) IS

  lsflagregioneszonas VARCHAR2(1);
  lnidperiodo         NUMBER;
  lnidperiodomenos1   NUMBER;


  rows NUMBER := 5000;
  i    BINARY_INTEGER := 0;

BEGIN

  SELECT decode(COUNT(1), 0, '0', '1')
    INTO lsflagregioneszonas
    FROM APE_REPOR_INVEN_CAMPO_ZONA;

  DELETE FROM APE_REPOR_INVEN_CAMPO_DETA WHERE usu_digi = pscodigousuario;

    INSERT INTO APE_REPOR_INVEN_CAMPO_DETA
       (COD_COMP_TRAN,
       COD_CENT_ACOP,
       NOM_COMP_TRAN,
       NOM_CENT_ACOP,
       cod_regi,             
       des_regi,
       cod_zona,
       des_zona,
       cod_secc,
       des_secci,
       val_nume_soli,
       fec_fact,
       origen,
       tip_cheq,
       fec_cheq,
       hor_cheq,
       fec_entr,
       val_lati,
       val_long,
       cod_nove,
       fec_nove,
       des_obser,
       tip_cheq_inic,
       fec_cheq_inic,
       fec_tran,
       fec_indu,
       USU_DIGI)
       /**/
select PSP.COD_COMP_TRAN,
       PSP.COD_CENT_ACOP,
       PSP.NOM_COMP_TRAN,
       PSP.NOM_CENT_ACOP,
       mae.cod_regi,
       mae.des_regi,
       mae.cod_zona,
       mae.des_zona,
       mae.cod_secc,
       mae.des_secci,
       psc.val_nume_soli,
       psc.fec_fact,
       decode(pedcheq.origen,
              1,
              'SSICC',
              decode(pedcheq.origen, 2, 'APE', 'AMBOS')) origen,
       psp.tip_cheq,
       psp.fec_cheq,
       psp.hor_cheq,
       psp.fec_entr,
       psp.val_lati,
       psp.val_long,
       psp.cod_nove,
       psp.fec_nove,
       psp.des_obser,
       psp.tip_cheq_inic,
       psp.fec_cheq_inic,
       psp.fec_tran,
       psp.fec_indu,
       pscodigousuario
  from ped_segui_pedid psp,
       (select OID_SOLI_CABE, sum(origen) origen
          from (select PSC1.OID_SOLI_CABE, 2 origen
                  from APE_PEDID_CHEQU apc, ped_solic_cabec psc1
                 where apc.num_pedi = PSC1.VAL_NUME_SOLI
                   and psc1.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY') ---VarFecIni
                   and psc1.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY') ---VarFecFin
                union
                select PSC2.OID_SOLI_CABE, 1 origen
                  from ped_pedid_chequ ppc, ped_solic_cabec psc2
                 where PPC.VAL_NUME_SOLI = PSC2.VAL_NUME_SOLI
                   and psc2.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY') ---VarFecIni
                   and psc2.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY')) ---VarFecFin
         group by OID_SOLI_CABE) pedcheq,
       ped_solic_cabec psc,
       V_MAE_CLIE_UNIDA_ADMIN mae
 where PSP.SOCA_OID_SOLI_CABE = pedcheq.OID_SOLI_CABE
   and psp.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
   and PSC.CLIE_OID_CLIE = mae.oid_clie
   and psc.fec_fact >= to_date(psfechainicio, 'DD/MM/YYYY') ---VarFecIni
   and psc.fec_fact <= to_date(psfechafin, 'DD/MM/YYYY') ---VarFecFin
   AND (lsflagregioneszonas = '0' OR
             (lsflagregioneszonas = '1' AND
             mae.cod_zona IN
             (SELECT cod_zona FROM APE_REPOR_INVEN_CAMPO_ZONA)))
   AND (pscentroacopio IS NULL OR (pscentroacopio IS NOT NULL AND
             PSP.COD_CENT_ACOP = pscentroacopio))
         AND (pscompaniatrans IS NULL OR
             (pscompaniatrans IS NOT NULL AND
             PSP.COD_COMP_TRAN = pscompaniatrans));
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR APE_PR_GENER_DATOS_INVEN_CADET: ' ||
                            ls_sqlerrm);
END APE_PR_GENER_DATOS_INVEN_CADET;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Inventario Campo 
                    Resumen
Fecha Creacion    : 12/02/2016
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_INVEN_CAMPO_RESU(
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
    SELECT CHEQUEOCAMPO,
           CHEQUEOAPE,
           ORIGEN,
           CANTIDAD
    FROM APE_REPOR_INVEN_CAMPO_RESU
   WHERE USU_DIGI = psUsuario;

TYPE interfazTipo IS RECORD (
  v_CHEQUEOCAMPO  APE_REPOR_INVEN_CAMPO_RESU.CHEQUEOCAMPO%TYPE,
  v_CHEQUEOAPE    APE_REPOR_INVEN_CAMPO_RESU.CHEQUEOAPE%TYPE,
  v_ORIGEN        APE_REPOR_INVEN_CAMPO_RESU.ORIGEN%TYPE,
  v_CANTIDAD      APE_REPOR_INVEN_CAMPO_RESU.CANTIDAD%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_CHEQUEOCAMPO||'")' ||','||
                    '=T("'||interfazRecord(x).v_CHEQUEOAPE||'")' ||','||
                    '=T("'||interfazRecord(x).v_ORIGEN||'")' ||','||
                    '=T("'||interfazRecord(x).v_CANTIDAD ||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_REPOR_INVEN_CAMPO_RESU: '||ls_sqlerrm);
END APE_PR_REPOR_INVEN_CAMPO_RESU;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Inventario Campo 
                    Detalle
Fecha Creacion    : 12/02/2016
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE APE_PR_REPOR_INVEN_CAMPO_DETA(
    psUsuario                           VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
    SELECT 
       COD_COMP_TRAN,
       COD_CENT_ACOP,
       NOM_COMP_TRAN,
       NOM_CENT_ACOP,
       cod_regi,             
       des_regi,
       cod_zona,
       des_zona,
       cod_secc,
       des_secci,
       val_nume_soli,
       TO_CHAR(fec_fact, 'DD/MM/YYYY') fec_fact,
       origen,
       tip_cheq,
       TO_CHAR(fec_cheq, 'DD/MM/YYYY') fec_cheq,
       hor_cheq,
       TO_CHAR(fec_entr, 'DD/MM/YYYY') fec_entr,
       val_lati,
       val_long,
       cod_nove,
       TO_CHAR(fec_nove, 'DD/MM/YYYY') fec_nove,
       des_obser,
       tip_cheq_inic,
       TO_CHAR(fec_cheq_inic, 'DD/MM/YYYY') fec_cheq_inic,       
       TO_CHAR(fec_tran, 'DD/MM/YYYY') fec_tran,       
       TO_CHAR(fec_indu, 'DD/MM/YYYY') fec_indu
    FROM APE_REPOR_INVEN_CAMPO_DETA
   WHERE USU_DIGI = psUsuario;

TYPE interfazTipo IS RECORD (
    v_COD_COMP_TRAN         APE_REPOR_INVEN_CAMPO_DETA.COD_COMP_TRAN%TYPE, 
    v_COD_CENT_ACOP         APE_REPOR_INVEN_CAMPO_DETA.COD_CENT_ACOP%TYPE, 
    v_NOM_COMP_TRAN         APE_REPOR_INVEN_CAMPO_DETA.NOM_COMP_TRAN%TYPE, 
    v_NOM_CENT_ACOP         APE_REPOR_INVEN_CAMPO_DETA.NOM_CENT_ACOP%TYPE, 
    v_cod_regi              APE_REPOR_INVEN_CAMPO_DETA.cod_regi%TYPE,           
    v_des_regi              APE_REPOR_INVEN_CAMPO_DETA.des_regi%TYPE, 
    v_cod_zona              APE_REPOR_INVEN_CAMPO_DETA.cod_zona%TYPE, 
    v_des_zona              APE_REPOR_INVEN_CAMPO_DETA.des_zona%TYPE, 
    v_cod_secc              APE_REPOR_INVEN_CAMPO_DETA.cod_secc%TYPE, 
    v_des_secci             APE_REPOR_INVEN_CAMPO_DETA.des_secci%TYPE, 
    v_val_nume_soli         APE_REPOR_INVEN_CAMPO_DETA.val_nume_soli%TYPE, 
    v_fec_fact              APE_REPOR_INVEN_CAMPO_DETA.fec_fact%TYPE, 
    v_origen                APE_REPOR_INVEN_CAMPO_DETA.origen%TYPE, 
    v_tip_cheq              APE_REPOR_INVEN_CAMPO_DETA.tip_cheq%TYPE, 
    v_fec_cheq              APE_REPOR_INVEN_CAMPO_DETA.fec_cheq%TYPE, 
    v_hor_cheq              APE_REPOR_INVEN_CAMPO_DETA.hor_cheq%TYPE, 
    v_fec_entr              APE_REPOR_INVEN_CAMPO_DETA.fec_entr%TYPE, 
    v_val_lati              APE_REPOR_INVEN_CAMPO_DETA.val_lati%TYPE, 
    v_val_long              APE_REPOR_INVEN_CAMPO_DETA.val_long%TYPE, 
    v_cod_nove              APE_REPOR_INVEN_CAMPO_DETA.cod_nove%TYPE, 
    v_fec_nove              APE_REPOR_INVEN_CAMPO_DETA.fec_nove%TYPE, 
    v_des_obser             APE_REPOR_INVEN_CAMPO_DETA.des_obser%TYPE, 
    v_tip_cheq_inic         APE_REPOR_INVEN_CAMPO_DETA.tip_cheq_inic%TYPE, 
    v_fec_cheq_inic         APE_REPOR_INVEN_CAMPO_DETA.fec_cheq_inic%TYPE, 
    v_fec_tran              APE_REPOR_INVEN_CAMPO_DETA.fec_tran%TYPE, 
    v_fec_indu              APE_REPOR_INVEN_CAMPO_DETA.fec_indu%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_COD_COMP_TRAN||'")' ||','||
                      '=T("'||interfazRecord(x).v_COD_CENT_ACOP||'")' ||','||
                      '=T("'||interfazRecord(x).v_NOM_COMP_TRAN||'")' ||','||
                      '=T("'||interfazRecord(x).v_NOM_CENT_ACOP||'")' ||','||
                      '=T("'||interfazRecord(x).v_cod_regi||'")' ||','||
                      '=T("'||interfazRecord(x).v_des_regi||'")' ||','||
                      '=T("'||interfazRecord(x).v_cod_zona||'")' ||','||
                      '=T("'||interfazRecord(x).v_des_zona||'")' ||','||
                      '=T("'||interfazRecord(x).v_cod_secc||'")' ||','||
                      '=T("'||interfazRecord(x).v_des_secci||'")' ||','||
                      '=T("'||interfazRecord(x).v_val_nume_soli||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_fact||'")' ||','||
                      '=T("'||interfazRecord(x).v_origen||'")' ||','||
                      '=T("'||interfazRecord(x).v_tip_cheq||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_cheq||'")' ||','||
                      '=T("'||interfazRecord(x).v_hor_cheq||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_entr||'")' ||','||
                      '=T("'||interfazRecord(x).v_val_lati||'")' ||','||
                      '=T("'||interfazRecord(x).v_val_long||'")' ||','||
                      '=T("'||interfazRecord(x).v_cod_nove||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_nove||'")' ||','||
                      '=T("'||interfazRecord(x).v_des_obser||'")' ||','||
                      '=T("'||interfazRecord(x).v_tip_cheq_inic||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_cheq_inic||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_tran||'")' ||','||
                      '=T("'||interfazRecord(x).v_fec_indu||'")';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_PR_REPOR_INVEN_CAMPO_DETA: '||ls_sqlerrm);
END APE_PR_REPOR_INVEN_CAMPO_DETA;

END APE_PKG_PROCE;
/
