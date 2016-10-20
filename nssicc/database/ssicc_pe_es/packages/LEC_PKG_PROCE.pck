CREATE OR REPLACE PACKAGE LEC_PKG_PROCE IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  W_FILAS    NUMBER := 5000;

  /***************************************************************************
   Descripcion       : Valida Carga de Datos
    Fecha Creacion    : 02/09/2015
   Autor             : karina Valencia
  ***************************************************************************/
  PROCEDURE LEC_PR_VALID_CARGA_DATOS_MASIV(psCodigoPais      VARCHAR2,
                                           psNumeroCarga     NUMBER,
                                           pscodigoPrograma  VARCHAR2,
                                           pscodigoTipoCarga VARCHAR2,
                                           pscodigoPeriodo   VARCHAR2,
                                           pnIndicadorCarga  OUT VARCHAR2);

  /***************************************************************************
   Descripcion       : Actualiza Carga Masiva de Tarjetas
   Fecha Creacion    : 02/12/2014
   Autor             : Diego Torres L.
  ***************************************************************************/

  PROCEDURE LEC_PR_GRABA_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2);

  /***************************************************************************
   Descripcion       : Actualiza Asociaciï¿½n Masiva de Tarjetas
   Fecha Creacion    : 02/12/2014
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_ASOCI_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2);

  /***************************************************************************
   Descripcion       : Carga Nivel Exito
   Fecha Creacion    : 18/03/2015
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_NIVEL_LIDER(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2);

  /***************************************************************************
   Descripcion       : Carga Anulacion Masiva de Tarjetas
   Fecha Creacion    : 04/02/2015
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_ANULA_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2);

 /***************************************************************************
   Descripcion       : Carga Actualizacion Estatus Reenvio de Tarjeta
   Fecha Creacion    : 15/12/2015
   Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE LEC_PR_ACTUA_ESTAT_REENV_TARJE(
                                     psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) ;   

  /***************************************************************************
   Descripcion       : Actualiza Carga de Datos Brillantes
   Fecha Creacion    : 23/01/2014
   Autor             : henry paredes
  ***************************************************************************/

  PROCEDURE LEC_PR_GRABA_DATOS_BRILL(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2);

  /***************************************************************************
   Descripcion       :  Actualiza Carga de Datos objetivos pedidos
   Fecha Creacion    : 28/01/2014
   Autor             : henry paredes
  ***************************************************************************/

  PROCEDURE LEC_PR_GRABA_DATOS_OBJEC_PEDID(psCodigoPais       VARCHAR2,
                                           psNumeroCarga      NUMBER,
                                           pscodigoPrograma   VARCHAR2,
                                           pscodigoTipoCarga  VARCHAR2,
                                           pnCodigoUsuario    VARCHAR2,
                                           pnIndicadorProceso OUT VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Actualizar masivamente la clasificaciï¿½n correspondiente de las
                      lï¿½deres activas y las que dejan de ser activas en campaï¿½a anterior
  Fecha Creacion    : 20/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_CLASI_MASIV(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Permite actualizar la clasificaciï¿½n de la lï¿½der en una determinada campaï¿½a.
  Fecha Creacion    : 21/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psIndCruce       VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Permite calcular los objetivos para obtener Bonos.
  Fecha Creacion    : 27/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_BONO(psCodigoPais     VARCHAR2,
                                    psCodigoMarca    VARCHAR2,
                                    psCodigoCanal    VARCHAR2,
                                    psCampanaProceso VARCHAR2,
                                    psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Permite Calcular los objetivos para obtener Bonos por Lanzamiento Estratï¿½gico.
  Fecha Creacion    : 27/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_LANZM(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psTipoBono       VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Permite realizar el cï¿½lculo de objetivos de ciclo de vida 2/2, 3/3 y 4/4.
  Fecha Creacion    : 29/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_CICLO_VIDA(psCodigoPais     VARCHAR2,
                                          psCodigoMarca    VARCHAR2,
                                          psCodigoCanal    VARCHAR2,
                                          psTipoBono       VARCHAR2,
                                          psCampanaProceso VARCHAR2,
                                          psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
   Descripcion       : Permite calcular masivamente el Nivel de ï¿½xito de las Lï¿½deres.
   Fecha Creacion    : 30/01/2014
   Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_NIVEL_EXITO(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psCodigoRegion   VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /***************************************************************************
      Descripcion       : Calcular Resultados para Incentivos y Gestion Desempe?o
      Fecha Creacion    : 03/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_MASIV(psCodigoPais       VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     pscodigomarca      VARCHAR2,
                                     pscodigocanal      VARCHAR2,
                                     pscodigoperiodo    VARCHAR2,
                                     pstipoproceso      VARCHAR2,
                                     psCodigoUsuario    VARCHAR2,
                                     psFechaFacturacion VARCHAR2);

  /***************************************************************************
      Descripcion       : Calcular Resultados de Lider
      Fecha Creacion    : 03/02/2014
      Autor             : SB
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_LIDER_ACTUA(psCodigoPais      VARCHAR2,
                                           pscodigomarca     VARCHAR2,
                                           pscodigocanal     VARCHAR2,
                                           pscodigoperiodo   VARCHAR2,
                                           psCodPrograma     VARCHAR2,
                                           psCodigoLider     VARCHAR2,
                                           psCodigoRegion    VARCHAR2,
                                           pscodigoZona      VARCHAR2,
                                           psCodigoSeccion   VARCHAR2,
                                           psPedidoPersonal  VARCHAR2,
                                           psPedidoExigido   VARCHAR2,
                                           pnNroPedConReal   NUMBER,
                                           PnNroPedNoConReal NUMBER,
                                           pnTotaVentCata    NUMBER,
                                           pnNumeIngrSecc    NUMBER,
                                           pnNumeReinSecc    NUMBER,
                                           pnNumeEgreSecc    NUMBER,
                                           psCodigoUsuario   VARCHAR2);

  /*********************************************************
  Descripcion : Obtener las consultoras del Programa Reconocimiento de la secciï¿½n
  Fecha Creacion : 03/02/2014
  Autor : Henry Paredes
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_EXCLU(pscodigomarca   VARCHAR2,
                                  pscodigocanal   VARCHAR2,
                                  pscodigoperiodo VARCHAR2,
                                  psCodPrograma   VARCHAR2,
                                  psCodigoRegion  VARCHAR2,
                                  pscodigoZona    VARCHAR2,
                                  psCodigoSeccion VARCHAR2) RETURN NUMBER;

  /***************************************************************************
      Descripcion       : Calcular Nivel de ï¿½xito Lï¿½der.
      Fecha Creacion    : 03/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_NIVEL_LIDER(psCodigoPais     VARCHAR2,
                                     pscodigomarca    VARCHAR2,
                                     pscodigocanal    VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psTipoCalculo    VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psIndCruce       VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /*********************************************************
  Descripcion : Obtener pedidos Reales
  Fecha Creacion : 04/02/2014
  Autor : Henry Paredes
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_REAL(pscodigomarca   VARCHAR2,
                                 pscodigocanal   VARCHAR2,
                                 pscodigoperiodo VARCHAR2,
                                 psCodPrograma   VARCHAR2,
                                 psCodigoRegion  VARCHAR2,
                                 pscodigoZona    VARCHAR2,
                                 psCodigoSeccion VARCHAR2) RETURN NUMBER;

  /***************************************************************************
      Descripcion       : Proceso Calculo de Productividad
      Fecha Creacion    : 11/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_PRODU_LIDER(psCodigoPais     VARCHAR2,
                                     pscodigomarca    VARCHAR2,
                                     pscodigocanal    VARCHAR2,
                                     pscodigoperiodo  VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psCodigoRegion   VARCHAR2,
                                     pscodigoZona     VARCHAR2,
                                     psCodigoSeccion  VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);
  /***************************************************************************
      Descripcion       : Registrar Baja Lider
      Fecha Creacion    : 12/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_REGI_LIDER_BAJA(psCodigoPais     VARCHAR2,
                                   pscodigomarca    VARCHAR2,
                                   pscodigocanal    VARCHAR2,
                                   pscodigoperiodo  VARCHAR2,
                                   pstipoproceso    VARCHAR2,
                                   psCodigoRegion   VARCHAR2,
                                   psCodigoZona     VARCHAR2,
                                   psCodigoSecc     VARCHAR2,
                                   psCodigoLider    VARCHAR2,
                                   psMotivoBaja     NUMBER,
                                   psIndBaja        NUMBER,
                                   psCodigoPrograma VARCHAR2,
                                   psCodigoUsuario  VARCHAR2);

  /***************************************************************************
      Descripcion       : Proceso Calculo de Baja
      Fecha Creacion    : 11/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  /*        PROCEDURE LEC_PR_CALCU_LIDER_BAJA(psCodigoPais      VARCHAR2,
  pscodigomarca      VARCHAR2,
  pscodigocanal      VARCHAR2,
  pscodigoperiodo    VARCHAR2,
  pstipoproceso      VARCHAR2,
  psCodigoRegion     VARCHAR2,
  psCodigoUsuario    VARCHAR2
  );*/

  /***************************************************************************
      Descripcion       : Proceso Calculo de Baja (Modificado)
      Fecha Creacion    : 10/12/2014
      Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_LIDER_BAJA(psCodigoPais    VARCHAR2,
                                    pscodigomarca   VARCHAR2,
                                    pscodigocanal   VARCHAR2,
                                    pscodigoperiodo VARCHAR2,
                                    pstipoproceso   VARCHAR2,
                                    psCodigoRegion  VARCHAR2,
                                    psCodigoUsuario VARCHAR2);

  /*********************************************************
     Descripcion :  Obtener Consultora Excluida.
              Resultados:
                            - 0, No Existe Consultora
                            - 1, Existe Consultora
      Fecha Creacion : 25/02/2014
      Autor : Yahir Rivas L.
  *********************************************************/
  FUNCTION LEC_FN_OBTE_CONS_EXCL(psCodigoPais       VARCHAR2,
                                 psCampaniaProceso  VARCHAR2,
                                 psCodigoPrograma   VARCHAR2,
                                 psCodigoConsultora VARCHAR2) RETURN NUMBER;

  /***************************************************************************
      Descripcion       : Proceso Calcular Recuperaciï¿½n de consultoras.043
      Fecha Creacion    : 04/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RECUP_CONSU(psCodigoPais       VARCHAR2,
                                     pscodigomarca      VARCHAR2,
                                     pscodigocanal      VARCHAR2,
                                     psCodigoPrograma   VARCHAR2,
                                     psCampanaProceso   VARCHAR2,
                                     psCampanaCobranza  VARCHAR2,
                                     psCodigoConsultora VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     pscodigoZona       VARCHAR2,
                                     psCodigoSeccion    VARCHAR2,
                                     psCodigoLider      VARCHAR2,
                                     psCodigoUsuario    VARCHAR2,
                                     psCampanaAnterior  VARCHAR2,
                                     pnMonAbonoPdteClie number,
                                     pdFechaCierre      DATE,
                                     pdFechaCierreZona  DATE);

  /***************************************************************************
      Descripcion       : Proceso Calcular Recuperaciï¿½n Grupo.044
      Fecha Creacion    : 12/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_RECUP_GRUPO(psCodigoPais      VARCHAR2,
                                           psCodigoPrograma  VARCHAR2,
                                           psCampanaProceso  VARCHAR2,
                                           psCampanaCobranza VARCHAR2,
                                           psCodigoGrupoPago VARCHAR2,
                                           psCodigoUsuario   VARCHAR2);

  /***************************************************************************
      Descripcion       : Proceso Evaluar Resultado Secciï¿½n por Comisiï¿½n.045
      Fecha Creacion    : 10/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_SECCI_COMIS(psCodigoPais      VARCHAR2,
                                           psCodigoPrograma  VARCHAR2,
                                           psCodigoRegion    VARCHAR2,
                                           psCodigoZona      VARCHAR2, ---nuevo
                                           psCampanaCobranza VARCHAR2,
                                           psCodigoUsuario   VARCHAR2);

  /***************************************************************************
      Descripcion       : Proceso Cï¿½lculo de Ganancia Recuperaciï¿½n.046
      Fecha Creacion    : 11/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_GANAN_COMIS(pscodigopais      VARCHAR2,
                                     psCodigoPrograma  VARCHAR2,
                                     psCodigoRegion    VARCHAR2,
                                     psCodigoZona      VARCHAR2, ---nuevo
                                     psCampanaProceso  VARCHAR2,
                                     psCampanaCobranza VARCHAR2,
                                     psCodigoUsuario   VARCHAR2);

  /*********************************************************
     Descripcion :
        Obtener Nro de Pedidos Consecutivos/No consecutivos Reales de la Campaï¿½a
     Parametros :
          -   pscodigoperiodo : Codigo Campaï¿½a
          -   psCodigoRegion : Codigo Region
          -   psCodigoZona : Codigo Zona
          -   psCodigoSeccion : Codigo Seccion
          -   psTipoProceso : C = consecutivo;N = No consecuntivo

     Resultados: Cantidad de registros.
      Fecha Creacion : 05/03/2014
      Autor : Yahir Rivas L.
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_CONS_REAL(pscodigoPais     VARCHAR2,
                                      pscodigoperiodo  VARCHAR2,
                                      psCodigoPrograma VARCHAR2,
                                      psCodigoRegion   VARCHAR2,
                                      psCodigoZona     VARCHAR2,
                                      psCodigoSeccion  VARCHAR2,
                                      psTipoProceso    VARCHAR2,
                                      psIndReco        VARCHAR2)
    RETURN NUMBER;
  /***************************************************************************
      Descripcion       : Proceso Calcular Ganancia Lï¿½der por objetivo de Pedidos
      Fecha Creacion    : 05/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_GANAN_PEDID_LIDER(psCodigoPais     VARCHAR2,
                                           pscodigomarca    VARCHAR2,
                                           pscodigocanal    VARCHAR2,
                                           pscodigoperiodo  VARCHAR2,
                                           psCodigoPrograma VARCHAR2,
                                           psCodigoRegion   VARCHAR2,
                                           psCodigoZona     VARCHAR2,
                                           psCodigoSeccion  VARCHAR2,
                                           psCodigoLider    VARCHAR2,
                                           psCodigoUsuario  VARCHAR2,
                                           psTipoProceso    VARCHAR2);

  /***************************************************************************
      Descripcion       : Permite el cï¿½lculo de ganancia por objetivo de pedidos
      Fecha Creacion    : 17/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_GANAN_BONO_LIDER(psCodigoPais     VARCHAR2,
                                          pscodigomarca    VARCHAR2,
                                          pscodigocanal    VARCHAR2,
                                          pscodigoperiodo  VARCHAR2,
                                          psCodigoPrograma VARCHAR2,
                                          psCodigoRegion   VARCHAR2,
                                          pscodigoZona     VARCHAR2,
                                          psCodigoSeccion  VARCHAR2,
                                          psCodigoLider    VARCHAR2,
                                          psCodigoUsuario  VARCHAR2);

  /***************************************************************************
      Descripcion       : Generar Pago Regular
      Fecha Creacion    : 20/03/2014
      Autor             : Henry Paredes B.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_PAGO_REGUL(pscodigoPais      VARCHAR2,
                                    pscodigoPrograma  VARCHAR2,
                                    psCampanaProceso  VARCHAR2,
                                    psCampanaCobranza VARCHAR2,
                                    psCampanaBono     VARCHAR2,
                                    pscodigoTipoPago  VARCHAR2,
                                    psfechaProceso    VARCHAR2,
                                    psCodigoUsuario   VARCHAR2);
  /***************************************************************************
      Descripcion       : Generar Pago Adicional
      Fecha Creacion    : 26/03/2014
      Autor             : Henry Paredes B.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_PAGO_ADICI(pscodigoPais      VARCHAR2,
                                    pscodigoPrograma  VARCHAR2,
                                    psCampanaProceso  VARCHAR2,
                                    psCampanaCobranza VARCHAR2,
                                    psCampanaBono     VARCHAR2,
                                    pscodigoTipoPago  VARCHAR2,
                                    psfechaProceso    VARCHAR2,
                                    psNumCarga        VARCHAR2,
                                    psCodigoUsuario   VARCHAR2);
  /**************************************************************************
  Descripcion       : Valida que el producto exista en la Matriz de Precios

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    pnOidPais         :  oid Pais
    pnOidAcceso       :  oid Acceso
    pnOidSubAcceso    :  oid SubAcceso
    pnOidPeriodo      :  oid Periodo
    pnOidTipoOferta   :  oid Tipo Oferta
    pnOidCicloVida    :  oid Ciclo Vida
    pnOidFormaPago    :  oid Forma Pago
    pnOidFormaCobro   :  oid Forma Cobro
    pnPrecio          :  oid Precio

  Autor             : CSVD - FFVV

  ***************************************************************************/
  FUNCTION LEC_FN_VALID_PRODU(pnOidPais       NUMBER,
                              pnOidPeriodo    NUMBER,
                              pnOidTipoOferta NUMBER,
                              pnOidCicloVida  NUMBER,
                              pnOidFormaPago  NUMBER,
                              pnOidFormaCobro NUMBER,
                              psCodigoSAP     VARCHAR2,
                              pnPrecio        NUMBER) RETURN NUMBER;

  /***************************************************************************
      Descripcion       : Generar Cï¿½digo Venta Premio Canasta
      Fecha Creacion    : 21/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_CODIG_VENTA_PREMI(psCodigoPais    VARCHAR2,
                                           pscodigomarca   VARCHAR2,
                                           pscodigocanal   VARCHAR2,
                                           pscodigoperiodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Genera el codigo de venta.

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    psCodigoPais         :  Codigo Pais
    pscodigoperiodo       :  Codigo Periodo(Campaï¿½a)
    pnOidTipoOferta   :  oid Tipo Oferta
    pnOidCicloVida    :  oid Ciclo Vida
    psCodigoFormaPago    :  Codigo Forma Pago
    pnOidFormaCobro   :  oid Forma Cobro
    psCodigoSap       : Codigo SAP
    pnPrecioProd          :  Precio Producto

    Autor : Yahir Rivas L.

  ***************************************************************************/
  FUNCTION LEC_FN_GENER_CODIG_VENTA(psCodigoPais      VARCHAR2,
                                    pscodigoperiodo   VARCHAR2,
                                    pnOidTipoOferta   NUMBER,
                                    psCodigoFormaPago VARCHAR2,
                                    psCodigoSap       VARCHAR2,
                                    pnPrecioProd      NUMBER) RETURN VARCHAR2;

  /***********************************************************************************************
       Descripcion       : Permite generar Solicitudes Canasta Masivo.
       Fecha Creacion    : 24/03/2014
       Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_GENER_SOLIC_CANAS_MASIV(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           pscodigoperiodo VARCHAR2,
                                           psTipoProceso   VARCHAR2,
                                           psCodigoRegion  VARCHAR2,
                                           psCodigoUsuario VARCHAR2);

  /***************************************************************************
      Descripcion       : Permite Generar la Solicitud de Despacho de Canasta
                          para una lï¿½der
      Fecha Creacion    : 25/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_SOLIC_CANAS_LIDER(psCodigoPais     VARCHAR2,
                                           pscodigomarca    VARCHAR2,
                                           pscodigocanal    VARCHAR2,
                                           pscodigoperiodo  VARCHAR2,
                                           psCodigoPrograma VARCHAR2,
                                           psCodigoRegion   VARCHAR2,
                                           pscodigoZona     VARCHAR2,
                                           psCodigoSeccion  VARCHAR2,
                                           psCodigoLider    VARCHAR2,
                                           psCodigoUsuario  VARCHAR2);

  /**************************************************************************
    Descripcion       : Obtiene la venta de una secciï¿½n

    Fecha Creacion    : 04/07/2014
    Parametros Entrada:
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider

    Parametros Entrada:
        Retorna la venta de la secciï¿½n

    Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_VENT_SECC(psCampanaProceso     VARCHAR2,
                                 pnOidCampanyaProceso NUMBER,
                                 psCodigoRegion       VARCHAR2,
                                 psCodigoZona         VARCHAR2,
                                 psCodigoSeccion      VARCHAR2) RETURN NUMBER;

  /**************************************************************************
    Descripcion       : Obtiene el nivel de venta en base al valor de la venta

    Fecha Creacion    : 08/07/2014
    Parametros Entrada:
        psCodigoPais : Cï¿½digo del pais
        psCodigoPrograma : Cï¿½digo del programa
        pnVenta   :  Valor de la venta
        psCodNivelExitPedido    : Codigo de NivelExitPedido
    Parametros Salida:
        Retorna el nivel

    Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_NIVE_VENT(psCodigoPais         VARCHAR2,
                                 psCodigoPrograma     VARCHAR2,
                                 pnVenta              NUMBER,
                                 psCodNivelExitPedido VARCHAR2)
    RETURN VARCHAR2;

  /**************************************************************************
    Descripcion       : Obtiene la venta de retail

    Fecha Creacion    : 05/08/2014
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider

    Parametros Salida:
        Retorna la venta de retail

    Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_VENT_RTAL(psCampanaProceso     VARCHAR2,
                                 pnOidCampanyaProceso NUMBER,
                                 psCodigoRegion       VARCHAR2,
                                 psCodigoZona         VARCHAR2,
                                 psCodigoSeccion      VARCHAR2) RETURN NUMBER;

  /**************************************************************************
    Descripcion       : Obtiene la venta de retail

    Fecha Creacion    : 07/08/2014
    Parametros Entrada:
        psCodigoPais: Codigo del pais
        psCodigoPrograma: Cï¿½digo de programa,
        psCodigoLider: Cï¿½digo de lider
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider
        psCodigoUsuario: Usuario que invoca el proceso

    Parametros Salida:
        Retorna la venta de la secciï¿½n

    Autor : Carlos Bazalar

  ***************************************************************************/
  FUNCTION LEC_FN_OBTIE_VENTA_RETAI(psCodigoPais         VARCHAR2,
                                    psCodigoPrograma     VARCHAR2,
                                    psCodigoLider        VARCHAR2,
                                    psCampanaProceso     VARCHAR2,
                                    pnOidCampanyaProceso NUMBER,
                                    psCodigoRegion       VARCHAR2,
                                    psCodigoZona         VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoUsuario      VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
    Descripcion       : Realiza la Eliminacion de un Programa LEC

    Fecha Creacion    : 13/08/2014
    Parametros Entrada:
        psCodigoPais       : Codigo de Pais
        psCodigoPrograma   : Codigo de Programa
    Autor : Carlos Bazalar

  ***************************************************************************/
  PROCEDURE LEC_PR_DELET_PROGR(psCodigoPais     VARCHAR2,
                               psCodigoPrograma VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Proceso Actualizar Indicadores LET
  Fecha Creacion    : 16/09/2014
  Autor             : Ivan Tocto.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_INDIC_ACTIV(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Proceso de Generacion de datos para el reporte de Proyeccion
  Fecha Creacion    : 23/10/2014
  Autor             : Ivan Tocto.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_GENER_REPOR_PROYE(psCodigoPais      VARCHAR2,
                                     psCampanyaProceso VARCHAR2,
                                     psCondicionTramos VARCHAR2,
                                     psCodigoUsuario   VARCHAR2);

  /***********************************************************************************************
  Descripcion       : Obtiene la campaï¿½a de recuperaciï¿½n
  Fecha Creacion    : 29/10/2014
  Autor             : Ivan Tocto.
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_CAMPA_RECU(psCodigoPrograma    VARCHAR2,
                                  psCampanyaProceso   VARCHAR2,
                                  psCampanyaAnterior  VARCHAR2,
                                  psCampanyaSiguiente VARCHAR2,
                                  psFechaFactura      DATE,
                                  psFechaCierre       DATE,
                                  psIndicaCamp        VARCHAR2,
                                  PsTipoCampanya      VARCHAR2)
    RETURN VARCHAR2;

  /***********************************************************************************************
  Descripcion       : Obtiene la campaï¿½a de recuperaciï¿½n (Parï¿½metros reducidos)
  Fecha Creacion    : 03/11/2014
  Autor             : Carlos Mori
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_CAMPA_RECU(psCodigoPais      VARCHAR2,
                                  psCodigoPrograma  VARCHAR2,
                                  psCampanyaProceso VARCHAR2,
                                  psCodigoRegion    VARCHAR2,
                                  psCodigoZona      VARCHAR2,
                                  PsTipoCampanya    VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
   Descripcion       : Carga Bonos de Lanzamiento
   Fecha Creacion    : 03/08/2015
   Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_OBJET_BONO(psCodigoPais       VARCHAR2,
                                    psNumeroCarga      NUMBER,
                                    pscodigoPrograma   VARCHAR2,
                                    pscodigoTipoCarga  VARCHAR2,
                                    pnCodigoUsuario    VARCHAR2,
                                    pnIndicadorProceso OUT VARCHAR2);

  /***************************************************************************
   Descripcion       : Carga Bloqueo y Desploqueo de Pagos, Actualiza Montos Netos
   Fecha Creacion    : 08/09/2015
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_BLODE_ACTUA_MONTO(psCodigoPais       VARCHAR2,
                                           psNumeroCarga      NUMBER,
                                           pscodigoPrograma   VARCHAR2,
                                           pscodigoTipoCarga  VARCHAR2,
                                           pnCodigoUsuario    VARCHAR2,
                                           pnCodigoPeriodo    VARCHAR2,
                                           pnIndicadorProceso OUT VARCHAR2);

/***********************************************************************************************
  Descripcion       : Funcion que devuelve el porcentaje de comision para el reporte de Proyeccion
  Fecha Creacion    : 29/09/2015
  Autor             : Richard Argomedo.
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_PORC_COMI(psCodigoPais1    VARCHAR2,
                                 psLproCodProg      VARCHAR2,
                                 psCamrecu          VARCHAR2,
                                 psCodLide          VARCHAR2,
                                 psIndPediCons      VARCHAR2,                                 
                                 psCodigoPais       varchar2,
                                 psCondicionTramos  VARCHAR2,
                                 psLpctCodTram      NUMBER,
                                 psvalida           VARCHAR2, 
                                 psCodigoRegion     VARCHAR2,
                                 psCodigoZona       VARCHAR2,
                                 psCodigoSeccion    VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve Listado General de Ganancia Lideres
Fecha Creacion    : 11/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LEC_PR_OBTIE_GANAN_LIDER(
    psCodigoPais VARCHAR2
 );

/***************************************************************************
Descripcion       : Devuelve Listado General de Ventas Consultoras
Fecha Creacion    : 12/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LEC_PR_OBTIE_VENTA_CONSU(
    psCodigoPais VARCHAR2);
    
/***********************************************************************************************
  Descripcion       : Obtiene el Codigo SAP- Pago Socias Empresarias
  Fecha Creacion    : 10/12/2015
  Autor             : Karina Valencia
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_CODIG_SAP(psCodigoCliente    VARCHAR2)                  
    RETURN VARCHAR2;

/***************************************************************************
    Descripcion       : Proceso Calcular Recuperaciï¿½n.042
    Fecha Creacion    : 14/03/2014
    Autor             : Juan Altamirano
***************************************************************************/
PROCEDURE LEC_PR_CALCU_RECAU(
                             psCodigoPais        VARCHAR2,
                             psCodigoPrograma    VARCHAR2,
                             psCampannaProceso   VARCHAR2,
                             psFechaProceso      VARCHAR2,
                             psCampannaRecaudo   VARCHAR2,
                             psCodigoGrupoRegion VARCHAR2,
                             pnCodigoTramo       INTEGER,
                             psCodigoUsuario     VARCHAR2
                            );
/***********************************************************************************************
  Descripcion       : Genera información de recaudos de las consultoras en una campaña específica
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_BASE_RECUP
(
  psCodigoPais       VARCHAR2,
  psCodigoPrograma   VARCHAR2,
  psCampannaProceso  VARCHAR2,
  psCampannaRecaudo  VARCHAR2,
  pnOidCampannaRecaudo NUMBER,
  psFechaProceso     VARCHAR2,
  psCodigoRegion     VARCHAR2,
  psCodigoZona       VARCHAR2,
  psCodigoSeccion    VARCHAR2,
  pdFechaLimite      DATE,
  pdFechaLimiteExtra DATE,
  pnCodigoTramo      INTEGER,
  psCodigoUsuario    VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera información de Pedidos de las consultoras de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_PEDID_CAMPA
(
 psCodigoPais         VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 pnOidCampannaAnterior INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pdFechaLimite        DATE,
 psCamposDeducc       VARCHAR2,
 pnTasaImpuesto       NUMBER,
 psIndReversion       VARCHAR2,
 psCodigoUsuario      VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera información de Venta Retail a las consultoras de una campaña específica
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_VENTA_RETAI
(
 psCodigoPais      VARCHAR2,
 psCampannaRecaudo VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma  VARCHAR2,
 psCodigoRegion    VARCHAR2,
 psCodigoZona      VARCHAR2,
 psCodigoSeccion   VARCHAR2,
 pdFechaLimite     DATE,
 psCodigoUsuario   VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera información de Cuotas Flexipago de las consultoras de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_CUOTA_FLEXI
(
 psCodigoPais      VARCHAR2,
 psCampannaRecaudo VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma  VARCHAR2,
 psCodigoRegion    VARCHAR2,
 psCodigoZona      VARCHAR2,
 psCodigoSeccion   VARCHAR2,
 pdFechaLimite     DATE,
 psCodigoUsuario   VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera información Resumen de Recuperacióin de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_RESUM_RECUP
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 pnSecuenciaAmbitoGeo INTEGER,
 pnPorcenCobr         NUMBER,
 pnPorcenCobrMini     NUMBER,
 psCodigoUsuario      VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera información Resumen de Recuperacióin de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_CALCU_COMIS_CONSU
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 pnSecuenciaAmbitoGeo INTEGER,
 pnPorcenCobr         NUMBER,
 pnPorcenCobrMini     NUMBER,
 psCodigoUsuario      VARCHAR2
);

/***********************************************************************************************
  Descripcion       : Genera informacion de ganancias
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 14/02/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_GANAN_RECUP
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 psCodigoUsuario      VARCHAR2
);
END LEC_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY LEC_PKG_PROCE IS

  /***************************************************************************
      Descripcion       : Calcular Nivel de ï¿½xito Lï¿½der.
      Fecha Creacion    : 03/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_NIVEL_LIDER(psCodigoPais     VARCHAR2,
                                     pscodigomarca    VARCHAR2,
                                     pscodigocanal    VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psTipoCalculo    VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psIndCruce       VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS
    vnCampPromedio         LEC_SUBCL.NUM_CAMP%TYPE;
    vsCampInicPromPed      seg_perio_corpo.cod_peri%TYPE;
    vsNumCampNivel         LEC_PROGR.NUM_CAMP_MANT_NIVE%TYPE;
    vsCampAux              seg_perio_corpo.cod_peri%TYPE;
    vnNumCampTrans         LEC_PROGR.NUM_CAMP_MANT_NIVE%TYPE;
    vsCodNivelExitoActual  LEC_LIDER_NIVEL.LNIV_COD_NIVE%TYPE;
    vsCodClasifLider       LEC_LIDER_CLASI.lccl_cod_clas%TYPE;
    vsCodSubclLider        LEC_LIDER_CLASI.LSCL_COD_SUBC%TYPE;
    vsCampIniNivel         LEC_LIDER_NIVEL.cam_inic%TYPE;
    vsCampPedidos          seg_perio_corpo.cod_peri%TYPE;
    vsCodRegion            ZON_REGIO.COD_REGI%TYPE;
    vsCodZona              ZON_ZONA.COD_ZONA%TYPE;
    vsCodSecc              ZON_SECCI.COD_SECC%TYPE;
    vnpedidosreales        seg_perio_corpo.cod_peri%TYPE;
    vsCodNivelExitPedido   LEC_PROGR_NIVEL.lniv_cod_nive%TYPE;
    vsCodNivelCampa        LEC_PROGR_NIVEL.lniv_cod_nive%TYPE;
    vsCantObjetPedid       LEC_LIDER_SECCI_OBJET_PEDID.NUM_PEDI_OBJE_FINA%TYPE;
    vsObjetVenta           LEC_LIDER_SECCI_OBJET_PEDID.VAL_OBJE_VENT%TYPE;
    vsCodNivelObjet        LEC_PROGR_NIVEL.lniv_cod_nive%TYPE;
    vsCodNivelExitoNuevo   LEC_PROGR_NIVEL.lniv_cod_nive%TYPE;
    vsCampaSecci           seg_perio_corpo.cod_peri%TYPE;
    vsCampInicioEsc2       seg_perio_corpo.cod_peri%TYPE;
    vsCodPeriodoAnterior   seg_perio_corpo.cod_peri%TYPE;
    vsCodPeriodoAnterior2  seg_perio_corpo.cod_peri%TYPE;
    vsOidCampSecci         ZON_SECCI.PERD_OID_PERI_INIC%TYPE;
    vnOidCampAux           cra_perio.oid_peri%TYPE;
    vnOidCampAnt           cra_perio.oid_peri%TYPE;
    n                      NUMBER;
    nEstRein               NUMBER;
    vnNumPedSecc           NUMBER;
    nVecesMayor            NUMBER;
    nVecesMenor            NUMBER;
    vsNivelLid             NUMBER;
    nPesoLid               NUMBER;
    nPesoNivAct            NUMBER;
    nPesoNuevoNiv          NUMBER := 0;
    vsIndCamb              VARCHAR2(1);
    vsIndExigCurs          LEC_PROGR.IND_EXIG_CURS%TYPE;
    nIndCurso              NUMBER;
    vsExiCurso             VARCHAR2(1);
    vsIndExcl              LEC_PROGR_AMBIT_GEOGR.IND_EXCL%TYPE;
    nIndAprobCurso         NUMBER;
    vsExitPrgCurso         VARCHAR2(1);
    nIndVenta              VARCHAR2(1);
    nVentaSeccion          NUMBER;
    nVentaSeccionAnt       NUMBER;
    vsCodNivelExitVenta    VARCHAR2(2);
    vsCodNivelExitVentaAnt VARCHAR2(2);
    lnCount                NUMBER;
    nIndRetail             VARCHAR2(1);
    nVentaRetail           NUMBER;
    lbEncontro             BOOLEAN;
    lnContador             NUMBER;
    lsIndicadorExcl        LEC_PROGR_AMBIT_GEOGR.Ind_Excl%TYPE;
    vnSgteNiv              VARCHAR2(1);
    vnSgteNiv2             VARCHAR2(1);

    lsCodNivel   LEC_PROGR_CURSO.LNIV_COD_NIVE%type;
    nPesoCurso   NUMBER;
    lnMaximoPeso NUMBER;
  BEGIN

    BEGIN
      SELECT VAL_PARA
        INTO nIndRetail
        FROM BAS_PARAM_PAIS
       WHERE COD_SIST = 'LET'
         AND NOM_PARA = 'IndVentaRetail'
         AND IND_ACTI = '1'
         AND COD_PAIS = psCodigoPais;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        nIndRetail := '0';
    END;

    IF psTipoCalculo = '1' THEN
      vnOidCampAux         := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCampanaProceso);
      vsCodPeriodoAnterior := GEN_FN_CALCU_PERIO(psCampanaProceso, -1);
      vnOidCampAnt         := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(vsCodPeriodoAnterior);

      DELETE FROM LEC_LIDER_NIVEL a
       WHERE a.CAM_INIC = psCampanaProceso
         AND a.lpro_cod_prog = psCodigoPrograma
         AND a.COD_LIDE = psCodigoLider
         and nvl(a.ind_orig_calc, 'C') <> 'X';

      UPDATE LEC_LIDER_NIVEL a
         SET a.CAM_FIN = NULL
       WHERE ( a.CAM_FIN = GEN_FN_CALCU_PERIO(psCampanaProceso, -1) OR
               a.cam_fin = psCampanaProceso )
         AND a.IND_TIPO_NIVE = 'R'
         AND a.lpro_cod_prog = psCodigoPrograma
         AND a.COD_LIDE = psCodigoLider;
     ----  RCR  Cruce campaña     
      SELECT DECODE(COUNT(*), 0, '0', '1')
        INTO vnSgteNiv
      FROM LEC_LIDER_NIVEL a
      WHERE a.cam_inic = GEN_FN_CALCU_PERIO(psCampanaProceso, 1)
         AND a.IND_TIPO_NIVE = 'R'
         AND a.lpro_cod_prog = psCodigoPrograma
         AND a.COD_LIDE = psCodigoLider; 
      vnSgteNiv2 := vnSgteNiv;  
      IF  psIndCruce = '1' THEN
          vnSgteNiv := '1';
      END IF;  
         ---- Fin   RCR  Cruce campaña       
      SELECT DECODE(COUNT(*), 0, '0', '1')
        INTO nIndVenta
        FROM LEC_PROGR_NIVEL
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND (MON_MINI_VENT_CATA > 0 OR MON_MAXI_VENT_CATA > 0)
         AND IND_ACTI = 1;

      SELECT NUM_CAMP_MANT_NIVE
        INTO vsNumCampNivel
        FROM LEC_PROGR
       WHERE COD_PROG = psCodigoPrograma;

      ---- Calculo Campaï¿½a Inicio para cambio de nivel --
      vsCodPeriodoAnterior2 := GEN_FN_CALCU_PERIO(psCampanaProceso,
                                                  - (nvl(vsNumCampNivel, 0) - 1)); 
      SELECT GEN_FN_CALCU_PERIO(psCampanaProceso, - (vsNumCampNivel - 1))
        INTO vsCampAux
        FROM DUAL;

      BEGIN
        SELECT LLN.LNIV_COD_NIVE, LN.VAL_PESO_NIVE, CAM_INIC
          INTO vsCodNivelExitoActual, nPesoNivAct, vsCampIniNivel
          FROM LEC_LIDER_NIVEL LLN, LEC_NIVEL LN
         WHERE LLN.LNIV_COD_NIVE = LN.COD_NIVE
           AND LLN.COD_LIDE = psCodigoLider
           AND LLN.LPRO_COD_PROG = psCodigoPrograma
           AND psCampanaProceso >= LLN.CAM_INIC
           AND (psCampanaProceso <= LLN.CAM_FIN OR LLN.CAM_FIN IS NULL)
           AND LLN.IND_TIPO_NIVE = 'R';

        /*      vnNumCampTrans := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO_PAIS (psCodigoPais,
        vsCampIniNivel,
        psCampanaProceso) + 1;   */
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodNivelExitoActual := NULL;
          nPesoNivAct           := 0;
          vnNumCampTrans        := 1;
      END;

      SELECT LCCL_COD_CLAS, LSCL_COD_SUBC
        INTO vsCodClasifLider, vsCodSubclLider
        FROM LEC_LIDER_CLASI
       WHERE COD_LIDE = psCodigoLider
         AND psCampanaProceso >= CAM_INIC
         AND (psCampanaProceso <= CAM_FIN OR CAM_FIN IS NULL);

      ----  Se valida si es lider reingresante
      nEstRein := 0;

      BEGIN
        SELECT COD_REGI
          INTO vsCodRegion
          FROM ZON_HISTO_GEREN
         WHERE GERE = psCodigoLider
           AND vnOidCampAnt >= PERD_OID_PERI_DESD
           AND (vnOidCampAnt <= PERD_OID_PERI_HAST OR
               PERD_OID_PERI_HAST IS NULL)
           AND COD_SECC IS NOT NULL;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          nEstRein := 1;
      END;

      SELECT COD_REGI, COD_ZONA, COD_SECC
        INTO vsCodRegion, vsCodZona, vsCodSecc
        FROM ZON_HISTO_GEREN
       WHERE GERE = psCodigoLider
         AND vnOidCampAux >= PERD_OID_PERI_DESD
         AND (vnOidCampAux <= PERD_OID_PERI_HAST OR
             PERD_OID_PERI_HAST IS NULL)
         AND COD_SECC IS NOT NULL;

      nVentaRetail  := 0;
      nVentaSeccion := LEC_pkg_proce.LEC_FN_OBTE_VENT_SECC(psCampanaProceso,
                                                           vnOidCampAux,
                                                           vsCodRegion,
                                                           vsCodZona,
                                                           vsCodSecc);

      IF nIndRetail = '1' THEN
        nVentaRetail := LEC_pkg_proce.LEC_FN_OBTE_VENT_RTAL(psCampanaProceso,
                                                            vnOidCampAux,
                                                            vsCodRegion,
                                                            vsCodZona,
                                                            vsCodSecc);
      END IF;

      vsCampPedidos := psCampanaProceso;

      vnpedidosreales := LEC_pkg_proce.LEC_FN_OBTE_PEDI_REAL(pscodigomarca,
                                                             pscodigocanal,
                                                             vsCampPedidos,
                                                             psCodigoPrograma,
                                                             vsCodRegion,
                                                             vsCodZona,
                                                             vsCodSecc);

      BEGIN
        SELECT LNIV_COD_NIVE
          INTO vsCodNivelExitPedido
          FROM LEC_PROGR_NIVEL f
         WHERE vnpedidosreales >= NUM_MINI_PEDI
           AND NUM_MAXI_PEDI >= vnpedidosreales
           AND F.LPRO_COD_PROG = psCodigoPrograma
           AND IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodNivelExitPedido := '00';
      END;

      vsCodNivelCampa := vsCodNivelExitPedido;

      IF nIndVenta = '1' THEN
        -- Obtener el nivel de venta
        vsCodNivelExitVenta := LEC_pkg_proce.LEC_FN_OBTE_NIVE_VENT(psCodigoPais,
                                                                   psCodigoPrograma,
                                                                   nVentaSeccion +
                                                                   nvl(nVentaRetail,
                                                                       0),
                                                                   vsCodNivelExitPedido);

        IF vsCodNivelExitPedido = vsCodNivelExitVenta THEN
          vsCodNivelCampa := vsCodNivelExitPedido;
        ELSE

          SELECT T.COD_NIVE
            INTO vsCodNivelCampa
            FROM (SELECT COD_NIVE, VAL_PESO_NIVE
                    FROM LEC_NIVEL
                   WHERE (COD_NIVE = vsCodNivelExitPedido OR
                         COD_NIVE = vsCodNivelExitVenta)
                   ORDER BY VAL_PESO_NIVE ASC) T
           WHERE ROWNUM = 1;

        END IF;

      ELSE
        vsCodNivelCampa := vsCodNivelExitPedido;
      END IF;

      ------  Se crea nivel Proyectado
      INSERT INTO LEC_lider_nivel
        (pais_cod_pais,
         lpro_cod_prog,
         cod_lide,
         lniv_cod_nive,
         cam_inic,
         cam_fin,
         ind_tipo_nive,
         usu_crea,
         fec_crea,
         ind_acti,
         mon_vent_cata,
         mont_vent_rtal,
         ind_orig_calc)
      VALUES
        (psCodigoPais,
         psCodigoPrograma,
         psCodigoLider,
         vsCodNivelCampa,
         psCampanaProceso,
         psCampanaProceso,
         'P',
         psCodigoUsuario,
         SYSDATE,
         1,
         nVentaSeccion,
         nvl(nVentaRetail, 0),
         'C');

      --- Obtengo cuantas campaï¿½as la lï¿½der tiene nivel proyectado desde hace 2 campaï¿½as
      SELECT nvl(COUNT(1), 0)
        INTO vnNumCampTrans
        FROM LEC_LIDER_NIVEL NI
       WHERE ni.lpro_cod_prog = psCodigoPrograma
         AND ni.cod_lide = psCodigoLider
         AND ni.ind_tipo_nive = 'P'
         AND ni.cam_inic >= vsCodPeriodoAnterior2
         AND ni.cam_inic <= psCampanaProceso;

      ---------- nIndCurso se pone en 1 si hay q exigir el curso ---
      SELECT LP.IND_EXIG_CURS
        INTO vsIndExigCurs
        FROM LEC_PROGR LP
       WHERE LP.COD_PROG = psCodigoPrograma;

      nIndCurso := 0;
      IF vsIndExigCurs = 1 THEN
        lbEncontro := TRUE;
        BEGIN
          SELECT nvl(COUNT(1), 0)
            INTO vsExiCurso
            FROM LEC_PROGR_CAMPA_EXIGE LPCE
           WHERE LPCE.PAIS_COD_PAIS = psCodigoPais
             AND LPCE.LPRO_COD_PROG = psCodigoPrograma
             AND LPCE.IND_TIPO_EXIG = 'C'
             AND psCampanaProceso >= LPCE.CAM_INIC
             AND (LPCE.CAM_FIN is null OR psCampanaProceso <= LPCE.CAM_FIN)
             AND lpce.ind_acti = '1';
        EXCEPTION
          WHEN no_data_found THEN
            lbEncontro := FALSE;
            nIndCurso  := 0;
        END;
        IF vsExiCurso = 0 THEN
          lbEncontro := FALSE;
          nIndCurso  := 0;
        END IF;
        IF lbEncontro THEN
          SELECT nvl(COUNT(1), 0)
            INTO lnContador
            FROM LEC_PROGR_AMBIT_GEOGR LPAG
           WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
             AND LPAG.LPRO_COD_PROG = psCodigoPrograma
             AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
             AND LPAG.IND_ACTI = '1';

          IF lnContador = 0 THEN
            nIndCurso := 1;
          ELSE
            SELECT lpag.ind_excl
              INTO lsIndicadorExcl
              FROM LEC_PROGR_AMBIT_GEOGR LPAG
             WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
               AND LPAG.LPRO_COD_PROG = psCodigoPrograma
               AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
               AND LPAG.IND_ACTI = '1'
               AND rownum = 1;

            IF lsIndicadorExcl = '1' THEN
              nIndCurso := 1;
              SELECT nvl(COUNT(1), 0)
                INTO lnContador
                FROM LEC_PROGR_AMBIT_GEOGR LPAG
               WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                 AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                 AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                 AND lpag.ind_pais = '1'
                 AND LPAG.IND_ACTI = '1';
              IF lnContador > 0 THEN
                nIndCurso := 0;
              ELSE
                SELECT nvl(COUNT(1), 0)
                  INTO lnContador
                  FROM LEC_PROGR_AMBIT_GEOGR LPAG
                 WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                   AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                   AND LPAG.COD_REGI = vsCodRegion
                   AND LPAG.COD_ZONA IS NULL
                   AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                   AND LPAG.IND_ACTI = '1';
                IF lnContador > 0 THEN
                  nIndCurso := 0;
                ELSE
                  SELECT nvl(COUNT(1), 0)
                    INTO lnContador
                    FROM LEC_PROGR_AMBIT_GEOGR LPAG
                   WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                     AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                     AND LPAG.COD_REGI = vsCodRegion
                     AND LPAG.COD_ZONA = vsCodZona
                     AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                     AND LPAG.IND_ACTI = '1';
                  IF lnContador > 0 THEN
                    nIndCurso := 0;
                  END IF;
                END IF;
              END IF;
            ELSE
              nIndCurso := 0;
              SELECT nvl(COUNT(1), 0)
                INTO lnContador
                FROM LEC_PROGR_AMBIT_GEOGR LPAG
               WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                 AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                 AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                 AND lpag.ind_pais = '1'
                 AND LPAG.IND_ACTI = '1';
              IF lnContador > 0 THEN
                nIndCurso := 1;
              ELSE
                SELECT nvl(COUNT(1), 0)
                  INTO lnContador
                  FROM LEC_PROGR_AMBIT_GEOGR LPAG
                 WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                   AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                   AND LPAG.COD_REGI = vsCodRegion
                   AND LPAG.COD_ZONA IS NULL
                   AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                   AND LPAG.IND_ACTI = '1';
                IF lnContador > 0 THEN
                  nIndCurso := 1;
                ELSE
                  SELECT nvl(COUNT(1), 0)
                    INTO lnContador
                    FROM LEC_PROGR_AMBIT_GEOGR LPAG
                   WHERE LPAG.PAIS_COD_PAIS = psCodigoPais
                     AND LPAG.LPRO_COD_PROG = psCodigoPrograma
                     AND LPAG.COD_REGI = vsCodRegion
                     AND LPAG.COD_ZONA = vsCodZona
                     AND LPAG.LTUG_COD_TIPO_USO_GEOG = '02'
                     AND LPAG.IND_ACTI = '1';
                  IF lnContador > 0 THEN
                    nIndCurso := 1;
                  END IF;
                END IF;
              END IF;
            END IF;
          END IF;
        END IF;

      END IF;

      IF (vsCodClasifLider = '01' AND vsCodSubclLider = '01') OR
         nEstRein > 0 THEN
        BEGIN
          SELECT NUM_PEDI_OBJE_FINA, VAL_OBJE_VENT
            INTO vsCantObjetPedid, vsObjetVenta
            FROM LEC_LIDER_SECCI_OBJET_PEDID
           WHERE LPRO_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = psCodigoPrograma
             AND COD_REGI = vsCodRegion
             AND COD_ZONA = vsCodZona
             AND COD_SECC = vsCodSecc
             AND CAM_OBJE = psCampanaProceso;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCantObjetPedid := 0;
            vsObjetVenta     := 0;
        END;

        BEGIN
          SELECT LNIV_COD_NIVE
            INTO vsCodNivelObjet
            FROM LEC_PROGR_NIVEL
           WHERE vsCantObjetPedid >= NUM_MINI_PEDI
             AND NUM_MAXI_PEDI >= vsCantObjetPedid
             AND LPRO_COD_PROG = psCodigoPrograma
             AND IND_ACTI = 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            SELECT COD_NIVE
              INTO vsCodNivelObjet
              FROM LEC_NIVEL
             WHERE VAL_PESO_NIVE = 1;
        END;
        IF nIndVenta = '1' THEN
          vsCodNivelExitVentaAnt := LEC_pkg_proce.LEC_FN_OBTE_NIVE_VENT(psCodigoPais,
                                                                        psCodigoPrograma,
                                                                        nvl(vsObjetVenta,
                                                                            0),
                                                                        '00');
          IF vsCodNivelExitVentaAnt = '00' THEN
            SELECT COD_NIVE
              INTO vsCodNivelExitVentaAnt
              FROM LEC_NIVEL
             WHERE VAL_PESO_NIVE = 1;
          END IF;
        END IF;
        --               Obtiene campaï¿½a creaciï¿½n de secciï¿½n  ---
        SELECT ZS.PERD_OID_PERI_INIC
          INTO vsOidCampSecci
          FROM ZON_SECCI ZS, ZON_ZONA ZZ, ZON_REGIO ZR
         WHERE ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
           AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
           AND FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCampanaProceso) >=
               ZS.PERD_OID_PERI_INIC
           AND (FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCampanaProceso) <=
               ZS.PERD_OID_PERI_FINA OR ZS.PERD_OID_PERI_FINA IS NULL)
           AND ZR.COD_REGI = vsCodRegion
           AND ZZ.COD_ZONA = vsCodZona
           AND ZS.COD_SECC = vsCodSecc;

        vsCampaSecci := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio3(vsOidCampSecci);

        IF vsCampaSecci = psCampanaProceso THEN
          vsCodNivelExitoNuevo := vsCodNivelObjet;
        ELSE
          -- OBTENER PEDIDO REALES
          vnNumPedSecc := LEC_pkg_proce.LEC_FN_OBTE_PEDI_REAL(pscodigomarca,
                                                              pscodigocanal,
                                                              vsCodPeriodoAnterior,
                                                              psCodigoPrograma,
                                                              vsCodRegion,
                                                              vsCodZona,
                                                              vsCodSecc);

          BEGIN
            SELECT Lniv_cod_nive
              INTO vsCodNivelExitoNuevo
              FROM LEC_PROGR_NIVEL
             WHERE num_mini_pedi <= vnNumPedSecc
               AND num_maxi_pedi >= vnNumPedSecc
               AND LPRO_COD_PROG = psCodigoPrograma
               AND IND_ACTI = 1;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              SELECT COD_NIVE
                INTO vsCodNivelExitoNuevo
                FROM LEC_NIVEL
               WHERE VAL_PESO_NIVE = 1;
          END;

          IF nIndVenta = '1' THEN
            nVentaRetail := 0;
            -- Obtenemos la venta de la campaï¿½a anterior
            nVentaSeccionAnt := LEC_pkg_proce.LEC_FN_OBTE_VENT_SECC(gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                     psCampanaProceso,
                                                                                                     -1),
                                                                    gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                                                                 psCampanaProceso,
                                                                                                                                                 -1)),
                                                                    vsCodRegion,
                                                                    vsCodZona,
                                                                    vsCodSecc);

            IF nIndRetail = '1' THEN
              nVentaRetail := LEC_pkg_proce.LEC_FN_OBTE_VENT_RTAL(gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                   psCampanaProceso,
                                                                                                   -1),
                                                                  gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                                                               psCampanaProceso,
                                                                                                                                               -1)),
                                                                  vsCodRegion,
                                                                  vsCodZona,
                                                                  vsCodSecc);
            END IF;

            vsCodNivelExitVentaAnt := LEC_pkg_proce.LEC_FN_OBTE_NIVE_VENT(psCodigoPais,
                                                                          psCodigoPrograma,
                                                                          nvl(nVentaSeccionAnt,
                                                                              0) +
                                                                          nvl(nVentaRetail,
                                                                              0),
                                                                          '00');
            IF vsCodNivelExitVentaAnt = '00' THEN
              SELECT COD_NIVE
                INTO vsCodNivelExitVentaAnt
                FROM LEC_NIVEL
               WHERE VAL_PESO_NIVE = 1;
            END IF;
          END IF;

        END IF;
      END IF;
      -----     Si no tiene Nivel en campaï¿½a y es Proceso Diario ï¿½  es Nueva 1
      -----      Crea nivel de campaï¿½a
      IF -----vsCodNivelExitoActual IS NULL  AND(psTipoProceso NOT IN ('R','P' ) OR psTipoProceso IS NULL)  OR
       (vsCodClasifLider = '01' AND vsCodSubclLider = '01') OR nEstRein > 0 THEN
        --- ---
        IF nIndVenta = '1' THEN

          IF vsCodNivelExitVentaAnt = vsCodNivelExitoNuevo THEN
            vsCodNivelCampa := vsCodNivelExitoNuevo;
          ELSE

            SELECT T.COD_NIVE
              INTO vsCodNivelCampa
              FROM (SELECT COD_NIVE, VAL_PESO_NIVE
                      FROM LEC_NIVEL
                     WHERE (COD_NIVE = vsCodNivelExitVentaAnt OR
                           COD_NIVE = vsCodNivelExitoNuevo)
                     ORDER BY VAL_PESO_NIVE ASC) T
             WHERE ROWNUM = 1;

          END IF;

        ELSE
          vsCodNivelCampa := vsCodNivelExitoNuevo;
        END IF;
        --- ---
        IF vsCodNivelExitoActual IS NULL THEN
          INSERT INTO LEC_lider_nivel
            (pais_cod_pais,
             lpro_cod_prog,
             cod_lide,
             lniv_cod_nive,
             cam_inic,
             cam_fin,
             ind_tipo_nive,
             usu_crea,
             fec_crea,
             ind_acti,
             IND_ORIG_CALC)
          VALUES
            (psCodigoPais,
             psCodigoPrograma,
             psCodigoLider,
             vsCodNivelCampa,
             psCampanaProceso,
             decode(vnSgteNiv,1,psCampanaProceso,null),    ----- YO
             'R',
             psCodigoUsuario,
             SYSDATE,
             1,
             'C');

        END IF;

      END IF;

      IF ---- Si es Nueva 02 o Nueva 03  y es consecutiva o es Establecida consecutiva
       (vsCodClasifLider = '01' AND vsCodSubclLider <> '01' AND
       nEstRein = 0) OR (vsCodClasifLider <> '01' AND nEstRein = 0) THEN
        IF vnNumCampTrans < vsNumCampNivel OR vsNumCampNivel = 0 
         THEN
          IF vsCodNivelExitoActual IS NULL THEN
            vsCodNivelExitoNuevo := vsCodNivelCampa;
          END IF;
        ELSE
          IF vsNumCampNivel > 0 THEN
            
            vsCampAux        := GEN_FN_CALCU_PERIO(psCampanaProceso,
                                                   - (vsNumCampNivel - 1));
            vsCampInicioEsc2 := vsCampAux;
            nVecesMayor      := 0;
            nVecesMenor      := 0;
            n                := 0;

            BEGIN
              LOOP
                BEGIN
                  SELECT LN.lniv_cod_nive, VAL_PESO_NIVE
                    INTO vsNivelLid, nPesoLid
                    FROM LEC_LIDER_NIVEL LN, LEC_NIVEL NE
                   WHERE LN.COD_LIDE = psCodigoLider
                     AND LN.LPRO_COD_PROG = psCodigoPrograma
                     AND IND_TIPO_NIVE = 'P'
                     AND LN.LNIV_COD_NIVE = NE.COD_NIVE
                     AND vsCampAux >= CAM_INIC
                     AND (vsCampAux <= CAM_FIN OR CAM_FIN IS NULL);
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    vsNivelLid := 1;
                    nPesoLid   := 1;
                END;

                IF nPesoLid > nPesoNivAct THEN
                  nVecesMayor := nVecesMayor + 1;

                  IF nVecesMayor = 1 THEN
                    nPesoNuevoNiv := nPesoLid;
                  END IF;

                  IF nPesoLid < nPesoNuevoNiv THEN
                    nPesoNuevoNiv := nPesoLid;
                  END IF;
                ELSE
                  IF nPesoLid < nPesoNivAct THEN
                    nVecesMenor := nVecesMenor + 1;

                    IF nVecesMenor = 1 THEN
                      nPesoNuevoNiv := nPesoLid;
                    END IF;

                    IF nPesoLid > nPesoNuevoNiv THEN
                      nPesoNuevoNiv := nPesoLid;
                    END IF;
                  END IF;
                END IF;

                EXIT WHEN vsCampAux = psCampanaProceso;
                n         := n + 1;
                vsCampAux := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                              vsCampInicioEsc2,
                                                              n);
              END LOOP;
            END;

            IF nVecesMayor >= vsNumCampNivel THEN

              IF nIndCurso = 1 THEN
                nIndAprobCurso := 0;
                lbEncontro     := TRUE;
                BEGIN
                  /*   SELECT LNIV_COD_NIVE
                  INTO lsCodNivel
                  FROM LEC_PROGR_CURSO
                  WHERE COD_CONS_LIDE = psCodigoLider
                  AND  CAM_REGI = psCampanaProceso ;   */
                  SELECT T.LNIV_COD_NIVE
                    INTO lsCodNivel
                    FROM (SELECT LNIV_COD_NIVE
                            FROM LEC_PROGR_CURSO CU, LEC_NIVEL NI
                           WHERE COD_CONS_LIDE = psCodigoLider
                             AND CAM_REGI <= psCampanaProceso
                             AND LNIV_COD_NIVE = NI.COD_NIVE
                           ORDER BY NI.VAL_PESO_NIVE DESC) T
                   WHERE ROWNUM = 1;
                EXCEPTION
                  WHEN no_data_found THEN
                    lbEncontro := FALSE;
                END;
                IF lbEncontro THEN
                  SELECT VAL_PESO_NIVE
                    INTO nPesoCurso
                    FROM LEC_NIVEL LN
                   WHERE LN.COD_NIVE = lsCodNivel;

                  ---        nPesoCurso := nPesoCurso + 1;

                  SELECT max(VAL_PESO_NIVE)
                    INTO lnMaximoPeso
                    FROM LEC_NIVEL LN;

                  IF nPesoCurso > lnMaximoPeso THEN
                    nPesoCurso := lnMaximoPeso;
                  END IF;
                  IF nPesoCurso > nPesoNivAct THEN
                    nIndAprobCurso := 1;
                    IF nPesoCurso <> nPesoNuevoNiv THEN

                      IF nPesoNuevoNiv > nPesoCurso THEN
                        nPesoNuevoNiv := nPesoCurso;
                      END IF;
                    END IF;
                  END IF;
                END IF;

              END IF;

              IF nIndCurso = 0 OR (nIndCurso = 1 AND nIndAprobCurso = 1) OR
                 nPesoNuevoNiv = 1 THEN
                vsIndCamb := 'S';
                SELECT NE.COD_NIVE
                  INTO vsCodNivelExitoNuevo
                  FROM LEC_NIVEL NE
                 WHERE NE.VAL_PESO_NIVE = nPesoNuevoNiv;
              END IF;

            END IF;

            IF nVecesMenor >= vsNumCampNivel THEN
              vsIndCamb := 'B';
              SELECT NE.COD_NIVE
                INTO vsCodNivelExitoNuevo
                FROM LEC_NIVEL NE
               WHERE NE.VAL_PESO_NIVE = nPesoNuevoNiv;
            END IF;
          END IF;
        END IF; 
        ---------------------------
        IF (vsCodNivelExitoNuevo IS NOT NULL) THEN
          IF (vsCodNivelExitoActual IS NOT NULL) THEN
            IF ((vsCodNivelExitoNuevo <> vsCodNivelExitoActual)) THEN
              -- SI SON DIFERENTES Y EL ANTERIOR NO ES NULO
              --Cerrar el nivel de exito anterior
              UPDATE LEC_LIDER_NIVEL
                 SET CAM_FIN  = vsCodPeriodoAnterior,
                     USU_MODI = psCodigoUsuario,
                     FEC_MODI = SYSDATE
               WHERE COD_LIDE = psCodigoLider
                 AND CAM_FIN IS NULL
                 AND CAM_INIC < psCampanaProceso;

              -- Registro Nivel Exito Real
              INSERT INTO LEC_LIDER_NIVEL
                (pais_cod_pais,
                 LPRO_COD_PROG,
                 COD_LIDE,
                 lniv_cod_nive,
                 CAM_INIC,
                 IND_TIPO_NIVE,
                 CAM_FIN,
                 IND_ACTI,
                 IND_CAMB,
                 USU_CREA,
                 USU_MODI,
                 FEC_CREA,
                 FEC_MODI,
                 IND_ORIG_CALC)
              VALUES
                (psCodigoPais,
                 psCodigoPrograma,
                 psCodigoLider,
                 vsCodNivelExitoNuevo,
                 psCampanaProceso,
                 'R',
                 decode(vnSgteNiv,1,psCampanaProceso,null),    ----- YO
                 '1',
                 vsIndCamb,
                 psCodigoUsuario,
                 NULL,
                 SYSDATE,
                 NULL,
                 'C');
            END IF;
          ELSE
            -- Registro Nivel Exito Real
            /* DELETE FROM LEC_LIDER_NIVEL
            WHERE COD_LIDE = psCodigoLider
                  AND CAM_INIC = psCampanaProceso
                  AND IND_TIPO_NIVE = 'R'; */

            --   AND   IND_TIPO_NIVE ='R';
            INSERT INTO LEC_LIDER_NIVEL
              (pais_cod_pais,
               LPRO_COD_PROG,
               COD_LIDE,
               lniv_cod_nive,
               CAM_INIC,
               IND_TIPO_NIVE,
               CAM_FIN,
               IND_ACTI,
               USU_CREA,
               USU_MODI,
               FEC_CREA,
               FEC_MODI,
               IND_ORIG_CALC)
            VALUES
              (psCodigoPais,
               psCodigoPrograma,
               psCodigoLider,
               vsCodNivelExitoNuevo,
               psCampanaProceso,
               'R',
               decode(vnSgteNiv,1,psCampanaProceso,null),    ----- YO
               '1',
               psCodigoUsuario,
               NULL,
               SYSDATE,
               NULL,
               'C');
          END IF;
        END IF;
        -----   RCR Cruce de campaña 
        IF  vnSgteNiv2 = '1' THEN
           UPDATE LEC_LIDER_NIVEL
           SET cam_fin  = psCampanaProceso,
             usu_modi = psCodigoUsuario,
             fec_modi = SYSDATE
           WHERE cod_lide = psCodigoLider
           AND cam_fin  IS NULL
           AND cam_inic < psCampanaProceso
           AND LPRO_COD_PROG = psCodigoPrograma;            
        END IF;
        ---- Fin rcr
      END IF;
    ELSE
      -- INICIO DEL TIPO CALCULO  = 2
      UPDATE LEC_LIDER_NIVEL
         SET cam_fin  = psCampanaProceso,
             usu_modi = psCodigoUsuario,
             fec_modi = SYSDATE
       WHERE cod_lide = psCodigoLider
         AND psCampanaProceso >= cam_inic
         AND (psCampanaProceso <= cam_fin OR cam_fin IS NULL);
      -- FIN DEL PROCESO = 2

    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_NIVEL_LIDER: ' ||
                              ls_sqlerrm);
  END;

  /***************************************************************************
      Descripcion       : Valida Carga de Datos
      Fecha Creacion    : 02/09/2015
      Autor             : karina Valencia
  ***************************************************************************/
  PROCEDURE LEC_PR_VALID_CARGA_DATOS_MASIV(psCodigoPais      VARCHAR2,
                                           psNumeroCarga     NUMBER,
                                           pscodigoPrograma  VARCHAR2,
                                           pscodigoTipoCarga VARCHAR2,
                                           pscodigoPeriodo   VARCHAR2,
                                           pnIndicadorCarga  OUT VARCHAR2) IS
    CURSOR c_programas IS
      SELECT VAL_DATO_1,
             VAL_DATO_2,
             VAL_DATO_3,
             VAL_DATO_4,
             VAL_DATO_5,
             VAL_DATO_6,
             VAL_DATO_7,
             VAL_DATO_8,
             VAL_DATO_9,
             VAL_DATO_10,
             Num_Secu,
             Ind_Regi_Proc
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      valDato3         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE,
      valDato4         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE,
      valDato5         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE,
      valDato6         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE,
      valDato7         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE,
      valDato8         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE,
      valDato9         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE,
      valDato10        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE,
      numeroSecuencia  LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE,
      indicadorProceso LEC_PROGR_CARGA_DATO_MASIV.IND_REGI_PROC%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN     interfazProgramasTab;
    lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
    lnvalDato1          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    lnvalDato2          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE;
    lnvalDato3          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE;
    lnvalDato4          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE;
    lnvalDato5          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE;
    lnvalDato6          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE;
    lnvalDato7          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE;
    lnvalDato8          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE;
    lnvalDato9          LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE;
    lnvalDato10         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE;
    lnnumeroSecuencia   LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE;
    val_obse            LEC_PROGR_CARGA_DATO_MASIV.VAL_ERRO%TYPE;
    lnEstadoProceso     NUMBER(1);
    lnCantidad          NUMBER(2);
    lnTraslape          NUMBER(2);
    lnExiste            NUMBER(2);
    lnCaracteresTarjeta NUMBER(2);
    lnCodigoEstado      VARCHAR2(2);

    vsCodTipoMedic VARCHAR2(2);
    vsCodTipoBono  VARCHAR2(2);
    lnTmp          NUMBER;

  BEGIN
    pnIndicadorCarga := '1';
    --Recuperamos el oid Pais
    lnOidPais       := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnEstadoProceso := 0;
    val_obse        := ' ';

    --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnvalDato1        := interfazRecordN(x).valDato1;
          lnvalDato2        := interfazRecordN(x).valDato2;
          lnvalDato3        := interfazRecordN(x).valDato3;
          lnvalDato4        := interfazRecordN(x).valDato4;
          lnvalDato5        := interfazRecordN(x).valDato5;
          lnvalDato6        := interfazRecordN(x).valDato6;
          lnvalDato7        := interfazRecordN(x).valDato7;
          lnvalDato8        := interfazRecordN(x).valDato8;
          lnvalDato9        := interfazRecordN(x).valDato9;
          lnvalDato10       := interfazRecordN(x).valDato10;
          lnnumeroSecuencia := interfazRecordN(x).numeroSecuencia;
          val_obse          := ' ';
          lnEstadoProceso   := 0;
          lnTraslape        := 0;

          CASE pscodigoTipoCarga

          -- --------------------------------
          -- 01. Carga de Objetivo de pedidos
          -- --------------------------------

            WHEN '01' THEN

              BEGIN
                -- 01.1 Validar Datos Nulos
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM Lec_Progr_Carga_Dato_Masiv
                 WHERE num_carg = psNumeroCarga
                   AND (val_dato_1 IS NULL OR val_dato_2 IS NULL OR
                       val_dato_3 IS NULL OR val_dato_4 IS NULL OR
                       val_dato_5 IS NULL OR val_dato_6 IS NULL)
                   AND lnnumeroSecuencia = num_secu;

                IF lnEstadoProceso = 0 AND lnExiste != 0 THEN
                  val_obse        := 'FALTAN DATOS EN EL ARCHIVO EXCEL';
                  lnEstadoProceso := 1;
                END IF;

                -- 01.2 Validar UA Incorrecta
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM zon_secci zscc, zon_zona zzon, zon_regio zorg
                 WHERE zscc.zzon_oid_zona = zzon.oid_zona
                   AND zzon.zorg_oid_regi = zorg.oid_regi
                   AND zscc.ind_acti = 1
                   AND zzon.ind_acti = 1
                   AND zorg.ind_acti = 1
                   AND zorg.cod_regi || zzon.cod_zona || zscc.cod_secc =
                       lnvalDato1 || lnvalDato2 || lnvalDato3;

                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                  val_obse        := 'UNIDAD ADMINISTRATIVA INCORRECTA';
                  lnEstadoProceso := 1;
                END IF;

                -- 01.3 Validar Campaï¿½a
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM cra_perio c, seg_perio_corpo pc
                 WHERE c.peri_oid_peri = pc.oid_peri
                   AND pc.cod_peri = lnvalDato4;

                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                  val_obse        := 'CAMPAï¿½A NO EXISTE O NO TIENE FORMATO CORRECTO';
                  lnEstadoProceso := 1;
                END IF;

                -- 01.4 Validar Campaï¿½a Cerrada
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM FAC_PROGR_CIERR PC
                 WHERE PC.CAM_PROC >= lnvalDato4
                   AND PC.TIP_CIER = 'C'
                   AND PC.EST_CIER = 'P';

                IF lnEstadoProceso = 0 AND lnExiste != 0 THEN
                  val_obse        := 'CAMPAï¿½A DEBE SER MAYOR O IGUAL A LA ACTIVA';
                  lnEstadoProceso := 1;
                END IF;

                -- 01.5 Validar Objetivo de Pedidos
                SELECT CASE
                         WHEN (SELECT SUM(NVL(x.num_pedi, 0))
                                 FROM int_sab_venta_previ_zona x
                                WHERE x.cod_peri = lnvalDato4
                                  AND x.cod_regi = lnvalDato1
                                  AND x.cod_zona = lnvalDato2) >
                              (SELECT SUM(NVL(val_dato_5, 0))
                                 FROM lec_progr_carga_dato_masiv x
                                WHERE x.num_carg = psNumeroCarga
                                  AND x.cod_tipo_carg = '1'
                                  AND x.val_dato_1 = lnvalDato1
                                  AND x.val_dato_2 = lnvalDato2) THEN
                          0
                         ELSE
                          1
                       END
                  INTO lnExiste
                  FROM dual;

                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                  val_obse        := 'OBJETIVO DE PEDIDOS DEBE SER MAYOR O IGUAL A ESTIMADOS DE LA ZONA';
                  lnEstadoProceso := 1;
                END IF;

                -- 01.6 Validar Objetivo de Venta
                SELECT CASE
                         WHEN (SELECT SUM(NVL(x.val_vene, 0))
                                 FROM int_sab_venta_previ_zona x
                                WHERE x.cod_peri = lnvalDato4
                                  AND x.cod_regi = lnvalDato1
                                  AND x.cod_zona = lnvalDato2) >
                              (SELECT SUM(NVL(val_dato_6, 0))
                                 FROM lec_progr_carga_dato_masiv x
                                WHERE x.num_carg = psNumeroCarga
                                  AND x.cod_tipo_carg = '1'
                                  AND x.val_dato_1 = lnvalDato1
                                  AND x.val_dato_2 = lnvalDato2) THEN
                          0
                         ELSE
                          1
                       END
                  INTO lnExiste
                  FROM dual;

                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                  val_obse        := 'OBJETIVO DE VENTAS DEBE SER MAYOR O IGUAL A ESTIMADOS DE LA ZONA';
                  lnEstadoProceso := 1;
                END IF;
              END;

          -- ----------------------------------
          -- 02. Carga de Consultora Excluidas
          -- ----------------------------------

            WHEN '02' THEN

              --(0), Validamos si hay algun dato null
              select count(1)
                INTO lnExiste
                from Lec_Progr_Carga_Dato_Masiv
               where num_carg = psNumeroCarga
                 and (val_dato_1 is null or val_dato_2 is null or
                     val_dato_3 is null)
                 and lnnumeroSecuencia = num_secu;

              IF lnExiste >= 1 THEN
                val_obse        := 'FALTAN DATOS POR INGRESAR';
                lnEstadoProceso := 1;
              ELSE
                --(1), Validamos si existe el Codigo de Cliente
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM MAE_CLIEN a, MAE_CLIEN_DATOS_ADICI b
                 where a.oid_clie = b.clie_oid_clie
                   AND a.PAIS_OID_PAIS = lnOidPais
                   AND a.COD_CLIE = lnvalDato1
                   AND b.ind_acti = '1';
                IF lnExiste = 0 THEN
                  val_obse        := lnvalDato1 || ' CLIENTE NO ENCONTRADO';
                  lnEstadoProceso := 1;
                ELSE
                  --(2), Validamos si la campaï¿½ia inicial existe
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM SEG_PERIO_CORPO
                   WHERE COD_PERI = lnvalDato2;

                  IF lnExiste = 0 THEN
                    val_obse        := lnvalDato2 ||
                                       ' CAMPANA NO ENCONTRADA';
                    lnEstadoProceso := 1;
                  ELSE
                    --(3), Validamos si la campaï¿½ia final existe
                    SELECT COUNT(1)
                      INTO lnExiste
                      FROM SEG_PERIO_CORPO
                     WHERE COD_PERI = lnvalDato3;

                    IF lnExiste = 0 THEN
                      val_obse        := lnvalDato3 ||
                                         ' CAMPANA NO ENCONTRADA';
                      lnEstadoProceso := 1;
                    ELSE
                      --(4), Validamos que la campaï¿½ia inicial y final no se traslapen
                      IF lnvalDato2 > lnvalDato3 THEN
                        val_obse        := 'CAMPANA INICIAL DEBE SER MENOR A CAMPANA FINAL';
                        lnEstadoProceso := 1;
                      END IF;
                      --(5), Validamos que la campaï¿½ia inicial y final no se traslapen
                      SELECT COUNT(1)
                        INTO lnCantidad
                        from LEC_PROGR_LISTA_EXCLU
                       WHERE PAIS_COD_PAIS = psCodigoPais
                         AND LPRO_COD_PROG = pscodigoPrograma
                         AND COD_CONS = lnvalDato1
                         AND IND_ACTI = 1;

                      IF lnCantidad > 0 THEN
                        SELECT sum(CASE
                                     WHEN ((lnvalDato2 >= CAM_INIC_VIGE) AND (lnvalDato2 <= CAM_FIN_VIGE)) OR
                                          ((lnvalDato3 >= CAM_INIC_VIGE) AND (lnvalDato3 <= CAM_FIN_VIGE)) OR
                                          ((lnvalDato2 < CAM_INIC_VIGE) AND (lnvalDato3 > CAM_FIN_VIGE)) THEN
                                      1
                                     ELSE
                                      0
                                   END)
                          INTO lnTraslape
                          FROM LEC_PROGR_LISTA_EXCLU
                         WHERE PAIS_COD_PAIS = psCodigoPais
                           AND LPRO_COD_PROG = pscodigoPrograma
                           AND COD_CONS = lnvalDato1
                           AND IND_ACTI = 1;

                        IF lnTraslape IS NOT NULL AND lnTraslape > 0 THEN
                          val_obse        := lnvalDato1 ||
                                             ' CONSULTORA EXCLUIDA YA EXISTE';
                          lnEstadoProceso := 1;

                        END IF;
                      END IF;
                    END IF;
                  END IF;
                END IF;
              END IF;

          -- ----------------------------------
          -- 03. Carga de Nombramientos Masivos
          -- ----------------------------------

            WHEN '03' THEN
              --(0), Validamos si hay algun dato null
              select count(1)
                INTO lnExiste
                from Lec_Progr_Carga_Dato_Masiv
               where num_carg = psNumeroCarga
                 and lnnumeroSecuencia = num_secu
                 and (val_dato_1 is null or val_dato_2 is null or
                     val_dato_3 is null or val_dato_4 is null or
                     val_dato_5 is null or val_dato_6 is null or
                     val_dato_7 is null);

              IF lnExiste >= 1 THEN
                val_obse        := 'FALTAN DATOS POR INGRESAR';
                lnEstadoProceso := 1;
              ELSE
                --(1), Validamos si existe CLIENTE
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM MAE_CLIEN a, MAE_CLIEN_DATOS_ADICI b
                 where a.oid_clie = b.clie_oid_clie
                   AND a.PAIS_OID_PAIS = lnOidPais
                   AND a.COD_CLIE = lnvalDato1
                   AND b.ind_acti = '1';
                IF lnExiste = 0 THEN
                  val_obse        := 'CLIENTE NO ENCONTRADO';
                  lnEstadoProceso := 1;
                ELSE
                  --(2), Validamos si existe SECCION
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM zon_secci zscc, zon_zona zzon, zon_regio zorg
                   WHERE zscc.zzon_oid_zona = zzon.oid_zona
                     AND zzon.zorg_oid_regi = zorg.oid_regi
                     AND zorg.ind_acti = 1
                     AND zzon.ind_acti = 1
                     AND zscc.ind_acti = 1
                     AND zorg.cod_regi || zzon.cod_zona || zscc.cod_secc =
                         lnvalDato2 || lnvalDato3 || lnvalDato4;

                  IF lnExiste = 0 THEN
                    val_obse        := 'SECCION NO ENCONTRADA';
                    lnEstadoProceso := 1;
                  ELSE
                    --(2), verificar Campana cerrada
                    select count(1)
                      INTO lnExiste
                      from FAC_PROGR_CIERR
                     where TIP_CIER = 'C'
                       and EST_CIER = 'P'
                       and CAM_PROC = lnvalDato5;
                    IF lnExiste >= 1 THEN
                      val_obse        := 'CAMPANA YA ESTA CERRADA';
                      lnEstadoProceso := 1;
                    END IF;
                  END IF;
                END IF;
              END IF;

          -- ---------------------------------------
          -- 04. Carga Pago Adicional - Comisiones
          -- ---------------------------------------

            WHEN '04' THEN

              -- 04.1 Validar Datos Nulos
              SELECT COUNT(1)
                INTO lnExiste
                FROM lec_progr_carga_dato_masiv
               WHERE num_carg = psNumeroCarga
                 AND lnnumeroSecuencia = num_secu
                 AND (val_dato_1 IS NULL OR val_dato_2 IS NULL);

              IF lnExiste > 0 THEN
                val_obse        := 'SE ENCONTRï¿½ VALOR NULO EN LA FILA';
                lnEstadoProceso := 1;
              END IF;
              -- 04.2 Validar Lider
              SELECT COUNT(1)
                INTO lnExiste
                FROM zon_histo_geren hger, mae_clien clie
               WHERE hger.gere = clie.cod_clie
                 AND hger.gere = lnvalDato1
                 AND hger.cod_regi IS NOT NULL
                 AND hger.cod_zona IS NOT NULL
                 AND hger.cod_secc IS NOT NULL;

              IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                val_obse        := 'CODIGO DE LIDER NO EXISTE';
                lnEstadoProceso := 1;
              END IF;
              -- 04.3 Validar Monto
              IF lnEstadoProceso = 0 AND NVL(lnvalDato2, 0) <= 0 THEN
                val_obse        := 'MONTO NO Vï¿½LIDO';
                lnEstadoProceso := 1;
              END IF;
              -- 04.4 Validar si lider tiene codigo proveedor ï¿½ tarjeta de pago
          /*SELECT COUNT(1)
                                  INTO lnExiste
                                  FROM mae_clien_lider lide
                                 WHERE lide.cod_clid = lnvalDato1
                                   AND lide.cod_prov IS NOT NULL;

                                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                                   val_obse := 'LIDER NO TIENE CODIGO SAP O TARJETA PAGO';
                                   lnEstadoProceso :=1;
                                END IF;*/

          -- -----------------------------------------
          -- 05. Carga Pago Adicional - Concursos LET
          -- -----------------------------------------

            WHEN '05' THEN

              -- 05.1 Validar Datos Nulos
              SELECT COUNT(1)
                INTO lnExiste
                FROM lec_progr_carga_dato_masiv
               WHERE num_carg = psNumeroCarga
                 AND lnnumeroSecuencia = num_secu
                 AND (val_dato_1 IS NULL OR val_dato_2 IS NULL);

              IF lnExiste > 0 THEN
                val_obse        := 'SE ENCONTRï¿½ VALOR NULO EN LA FILA';
                lnEstadoProceso := 1;
              END IF;
              -- 05.2 Validar Lider
              SELECT COUNT(1)
                INTO lnExiste
                FROM zon_histo_geren hger, mae_clien clie
               WHERE hger.gere = clie.cod_clie
                 AND hger.gere = lnvalDato1
                 AND hger.cod_regi IS NOT NULL
                 AND hger.cod_zona IS NOT NULL
                 AND hger.cod_secc IS NOT NULL;

              IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                val_obse        := 'CODIGO DE LIDER NO EXISTE';
                lnEstadoProceso := 1;
              END IF;
              -- 05.3 Validar Monto
              IF lnEstadoProceso = 0 AND NVL(lnvalDato2, 0) <= 0 THEN
                val_obse        := 'MONTO NO Vï¿½LIDO';
                lnEstadoProceso := 1;
              END IF;
              -- 05.4 Validar si lider tiene codigo proveedor ï¿½ tarjeta de pago
          /*SELECT COUNT(1)
                                  INTO lnExiste
                                  FROM mae_clien_lider lide
                                 WHERE lide.cod_clid = lnvalDato1
                                   AND lide.cod_prov IS NOT NULL;

                                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                                   val_obse := 'LIDER NO TIENE CODIGO SAP O TARJETA PAGO';
                                   lnEstadoProceso :=1;
                                END IF;*/

          -- ---------------------------------------------
          -- 06. Carga Pago Adicional - Reconsideraciones
          -- ---------------------------------------------

            WHEN '06' THEN

              -- 06.1 Validar Datos Nulos
              SELECT COUNT(1)
                INTO lnExiste
                FROM lec_progr_carga_dato_masiv
               WHERE num_carg = psNumeroCarga
                 AND lnnumeroSecuencia = num_secu
                 AND (val_dato_1 IS NULL OR val_dato_2 IS NULL);

              IF lnExiste > 0 THEN
                val_obse        := 'SE ENCONTRï¿½ VALOR NULO EN LA FILA';
                lnEstadoProceso := 1;
              END IF;
              -- 06.2 Validar Lider
              SELECT COUNT(1)
                INTO lnExiste
                FROM zon_histo_geren hger, mae_clien clie
               WHERE hger.gere = clie.cod_clie
                 AND hger.gere = lnvalDato1
                 AND hger.cod_regi IS NOT NULL
                 AND hger.cod_zona IS NOT NULL
                 AND hger.cod_secc IS NOT NULL;

              IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                val_obse        := 'CODIGO DE LIDER NO EXISTE';
                lnEstadoProceso := 1;
              END IF;
              -- 06.3 Validar Monto
              IF lnEstadoProceso = 0 AND NVL(lnvalDato2, 0) <= 0 THEN
                val_obse        := 'MONTO NO Vï¿½LIDO';
                lnEstadoProceso := 1;
              END IF;
              -- 06.4 Validar si lider tiene codigo proveedor ï¿½ tarjeta de pago
          /*SELECT COUNT(1)
                                  INTO lnExiste
                                  FROM mae_clien_lider lide
                                 WHERE lide.cod_clid = lnvalDato1
                                   AND lide.cod_prov IS NOT NULL;

                                IF lnEstadoProceso = 0 AND lnExiste = 0 THEN
                                   val_obse := 'LIDER NO TIENE CODIGO SAP O TARJETA PAGO';
                                   lnEstadoProceso :=1;
                                END IF;*/

          -- ---------------------------
          -- 07. Carga Tarjetas de Pago
          -- ---------------------------
            WHEN '07' THEN
              -- i.    Validar Nï¿½mero tarjeta: Con el nï¿½mero de tarjeta acceder la Tabla Tarjeta de Pagos
              --De encontrar registro, serï¿½a un registro errado ya que la tarjeta ya estï¿½ registrada,
              --de lo contrario la validaciï¿½n es correcta.

              select count(*)
                INTO lnExiste
                from LEC_PROGR_CARGA_DATO_MASIV
               where val_dato_1 = lnvalDato1
                 and PAIS_COD_PAIS = psCodigoPais
                 AND LPRO_COD_PROG = pscodigoPrograma
                 and Ind_Regi_Proc = 0
                 AND COD_TIPO_CARG = pscodigoTipoCarga
                 AND NUM_CARG = psNumeroCarga;

              IF lnExiste = 2 THEN
                val_obse        := 'TARJETA DUPLICADA EN EXCEL';
                lnEstadoProceso := 1;
              ELSE

                SELECT COUNT(1)
                  INTO lnExiste
                  FROM LEC_TARJE_PAGOS
                 WHERE NUM_TARJ = lnvalDato1;

                IF lnExiste = 1 THEN
                  val_obse        := 'TARJETA YA REGISTRADA';
                  lnEstadoProceso := 1;
                ELSE
                  -- ii.    Validar que el nï¿½mero de tarjeta sea de 16 dï¿½gitos

                  IF LENGTH(lnvalDato1) <> 16 THEN
                    val_obse        := 'TARJETA DEBE TENER 16 DIGITOS';
                    lnEstadoProceso := 1;
                  END IF;

                END IF;

              END IF;

          -- -------------------------------
          -- 08. Asociaciï¿½n Masiva Tarjetas
          -- -------------------------------
            WHEN '08' THEN

              -- i.    Validar Nï¿½mero tarjeta: Con el nï¿½mero de tarjeta acceder la Tabla Tarjeta de Pagos

              SELECT COUNT(1)
                INTO lnExiste
                FROM LEC_TARJE_PAGOS
               WHERE NUM_TARJ = lnvalDato1;

              IF lnExiste = 1 THEN
                SELECT LEST_COD_ESTA
                  INTO lnCodigoEstado
                  FROM LEC_TARJE_PAGOS
                 WHERE NUM_TARJ = lnvalDato1;
                --De encontrar registro, El estatus de Trajeta debe ser 01
                IF lnCodigoEstado = '01' THEN

                  -- ii.    Validar que el nï¿½mero de tarjeta sea de 16 dï¿½gitos
                  IF LENGTH(lnvalDato1) <> 16 THEN
                    val_obse        := 'TARJETA DEBE TENER 16 DIGITOS';
                    lnEstadoProceso := 1;
                  END IF;

                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM ZON_HISTO_GEREN
                   WHERE COD_SECC IS NOT NULL
                     AND GERE = lnvalDato2;
                  -- iii.    Validar que el cï¿½digo de lï¿½der exista en Histï¿½rico responsables
                  --donde COD_SECC no es nulo.
                  IF lnExiste >= 1 THEN
                    SELECT COUNT(1)
                      INTO lnExiste
                      FROM LEC_TARJE_LIDER A
                     INNER JOIN LEC_TARJE_PAGOS B
                        ON A.LTPG_COD_TARJ = B.COD_TARJ
                     WHERE A.COD_LIDE = lnvalDato2
                       AND B.LEST_COD_ESTA IN ('01', '02');

                    -- iv.    Validar si la lï¿½der ya tiene registro en Tarjeta Lider con estado 01 ï¿½ 02,
                    --uniendo esta tabla con Estados Tarjeta y Tarjetas de Pago.
                    IF lnExiste = 1 THEN

                      --De existir registro, se tratarï¿½a de un registro errado, Lï¿½der ya
                      --tiene Tarjeta Asociada Activa.
                      val_obse        := 'LIDER YA TIENE TARJETA ASOCIADA ACTIVA';
                      lnEstadoProceso := 1;
                    END IF;

                  ELSE
                    val_obse        := 'LIDER NO EXISTE';
                    lnEstadoProceso := 1;
                  END IF;

                ELSE
                  -- rechazar registro indicando que tarjeta no estï¿½ Disponible.
                  val_obse        := 'TARJETA NO ESTA DISPONIBLE';
                  lnEstadoProceso := 1;
                END IF;
              ELSE
                -- De no encontrar registro, rechazar registro indicando que Nï¿½mero de Tarjeta
                --no estï¿½ registrado
                val_obse        := 'NUMERO DE TARJETA NO ESTA REGISTRADO';
                lnEstadoProceso := 1;
              END IF;

          -- ---------------------------------------
          -- 09. Carga de Anulaciï¿½n Masiva Tarjetas
          -- ---------------------------------------
            WHEN '09' THEN

              -- i.    Validar Nï¿½mero tarjeta: Con el nï¿½mero de tarjeta acceder la Tabla Tarjeta
              SELECT COUNT(1)
                INTO lnExiste
                FROM LEC_TARJE_PAGOS
               WHERE NUM_TARJ = lnvalDato1;

              IF lnExiste = 1 THEN
                SELECT LEST_COD_ESTA
                  INTO lnCodigoEstado
                  FROM LEC_TARJE_PAGOS
                 WHERE NUM_TARJ = lnvalDato1;
                --De encontrar registro, El estatus de Trajeta debe ser 01
                IF lnCodigoEstado <> '01' THEN
                  -- rechazar registro indicando que tarjeta no estï¿½ Disponible.
                  val_obse        := 'TARJETA NO ESTA CON ESTATUS DISPONIBLE';
                  lnEstadoProceso := 1;
                END IF;
              ELSE
                -- De no encontrar registro, rechazar registro indicando que Nï¿½mero de Tarjeta
                --no estï¿½ registrado
                val_obse        := 'NUMERO DE TARJETA NO ESTA REGISTRADO';
                lnEstadoProceso := 1;
              END IF;

          -- -----------------------------
          -- 15. Carga Bloqueo de Pagos
          -- -----------------------------
            WHEN '15' THEN
              --i. Validar que Campaï¿½a Proceso exista
              SELECT COUNT(1)
                INTO lnExiste
                FROM CRA_PERIO
               WHERE OID_PERI =
                     gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoPeriodo);

              IF lnExiste = 0 THEN
                val_obse        := 'CAMPAï¿½A PROCESO NO EXISTE';
                lnEstadoProceso := 1;
              ELSE
                --ii. Validar que exista Codigo Cliente en el maestro
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM MAE_CLIEN
                 WHERE COD_CLIE = lnvalDato1;

                IF lnExiste = 0 THEN
                  val_obse        := 'Cï¿½DIGO DE CONSULTORA NO EXISTE EN EL MAESTRO';
                  lnEstadoProceso := 1;
                ELSE
                  --iii. Validar que consultora a ser bloqueada tenga pago generado
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM LEC_LIDER_PAGO_COMIS
                   WHERE CAM_PROC = pscodigoPeriodo
                     AND IND_PROC_PAGO = '1'
                     AND COD_LIDE = lnvalDato1;

                  IF lnExiste = 0 THEN
                    val_obse        := 'CONSULTORA NO TIENE PAGO GENERADO';
                    lnEstadoProceso := 1;
                  END IF;
                END IF;
              END IF;

          -- -----------------------------
          -- 16. Carga Desbloqueo de Pagos
          -- -----------------------------
            WHEN '16' THEN
              --i. Validar que Campaï¿½a Proceso exista
              SELECT COUNT(1)
                INTO lnExiste
                FROM CRA_PERIO
               WHERE OID_PERI =
                     gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoPeriodo);

              IF lnExiste = 0 THEN
                val_obse        := 'CAMPAï¿½A PROCESO NO EXISTE';
                lnEstadoProceso := 1;
              ELSE
                --ii. Validar que exista Codigo Cliente en el maestro
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM MAE_CLIEN
                 WHERE COD_CLIE = lnvalDato1;

                IF lnExiste = 0 THEN
                  val_obse        := 'Cï¿½DIGO DE CONSULTORA NO EXISTE EN EL MAESTRO';
                  lnEstadoProceso := 1;
                ELSE
                  --iii. Validar que se desbloquee solo pagos bloqueados
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM LEC_LIDER_PAGO_COMIS
                   WHERE CAM_PROC = pscodigoPeriodo
                     AND IND_PROC_PAGO = '5'
                     AND COD_LIDE = lnvalDato1;

                  IF lnExiste = 0 THEN
                    val_obse        := 'CONSULTORA NO TIENE PAGO BLOQUEADO';
                    lnEstadoProceso := 1;
                  END IF;
                END IF;
              END IF;

          -- -----------------------------
          -- 17. Carga Actualizar Montos Netos
          -- -----------------------------
            WHEN '17' THEN
              --i. Validar que Campaï¿½a Proceso exista
              SELECT COUNT(1)
                INTO lnExiste
                FROM CRA_PERIO
               WHERE OID_PERI =
                     gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoPeriodo);

              IF lnExiste = 0 THEN
                val_obse        := 'CAMPAï¿½A PROCESO NO EXISTE';
                lnEstadoProceso := 1;
              ELSE
                --iii. Validar la existencia de Cï¿½digo SAP correspondiente al Cï¿½digo Cliente
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM MAE_CLIEN_DATOS_ADICI MCDA,
                       LEC_LIDER_PAGO_COMIS  LLPC
                 WHERE MCDA.CLIE_OID_CLIE =
                       gen_pkg_gener.gen_fn_devuelve_id_cliente(LLPC.COD_LIDE)
                   AND LLPC.NUM_SECU = lnvalDato1
                   AND MCDA.COD_PROV = lnvalDato2
                   AND LLPC.COD_SAP_CONS = lnvalDato2;

                IF lnExiste = 0 THEN
                  val_obse        := 'Cï¿½DIGO SAP NO EXISTE';
                  lnEstadoProceso := 1;
                ELSE
                  --iv. Validar la existencia de registros de pago
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM LEC_LIDER_PAGO_COMIS
                   WHERE CAM_PROC = pscodigoPeriodo
                     AND IND_PROC_PAGO = '3'
                     AND NUM_SECU = lnvalDato1;

                  IF lnExiste = 0 THEN
                    val_obse        := 'CONSULTORA NO TIENE PAGO ENVIADO';
                    lnEstadoProceso := 1;
                  END IF;
                END IF;
              END IF;
          
          -- -----------------------------
          -- 18. Carga Actualización Estatus por Reenvío Tarjeta
          -- -----------------------------
            WHEN '18' THEN
              --i. Validar que exista Nro de Tarjeta
              SELECT COUNT(1)
                INTO lnExiste
                FROM LEC_TARJE_PAGOS x
               WHERE x.NUM_TARJ = lnvalDato1;

              IF lnExiste = 0 THEN
                val_obse        := 'NUMERO DE TARJETA NO EXISTE';
                lnEstadoProceso := 1;
              ELSE
                --ii. Validar Estado Tarjeta 
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM LEC_TARJE_PAGOS x
                 WHERE x.NUM_TARJ = lnvalDato1
                   AND x.LEST_COD_ESTA = '03';

                IF lnExiste = 0 THEN
                  val_obse        := 'TARJETA SE ENCUENTRA EN ESTADO DIFERENTE A ENVIADO';
                  lnEstadoProceso := 1;
                
                END IF;
              END IF;
          
          -- -----------------------------
          -- 10. Carga Nivel Exito Masivo
          -- -----------------------------

            WHEN '10' THEN

              -- Validar que no exista mï¿½s de 1 registro para una misma lï¿½der, de lo contrario mostrar mensaje de error que cï¿½digo de lï¿½der estï¿½ duplicado.
              select count(*)
                INTO lnExiste
                from LEC_PROGR_CARGA_DATO_MASIV
               where val_dato_1 = lnvalDato1
                 and PAIS_COD_PAIS = psCodigoPais
                 AND LPRO_COD_PROG = pscodigoPrograma
                 and Ind_Regi_Proc = 0
                 AND COD_TIPO_CARG = pscodigoTipoCarga
                 AND NUM_CARG = psNumeroCarga;

              IF lnExiste = 2 THEN
                val_obse        := 'Cï¿½DIGO DE Lï¿½DER ESTï¿½ DUPLICADO';
                lnEstadoProceso := 1;
              ELSE
                -- Obtener la campaï¿½a activa de Control de Facturaciï¿½n donde sta_camp = 0 and  ind_camp_act = 1
                -- Accesar Histï¿½rico de responsables con Cï¿½digo de lï¿½der y que cod-regi,
                -- cod_zona y cod_secc is not null y que Periodo Desde y Periodo Hasta esten en el rango de oid de campaï¿½a activa.
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM ZON_HISTO_GEREN
                 WHERE GERE = lnvalDato1
                   AND COD_REGI IS NOT NULL
                   AND COD_ZONA IS NOT NULL
                   AND COD_SECC IS NOT NULL
                   AND (SELECT cra.OID_PERI
                          FROM BAS_CTRL_FACT   A,
                               SEG_PERIO_CORPO B,
                               CRA_PERIO       CRA
                         WHERE A.STA_CAMP = '0'
                           AND A.IND_CAMP_ACT = 1
                           AND B.COD_PERI = A.COD_PERI
                           AND B.OID_PERI = CRA.PERI_OID_PERI) BETWEEN
                       PERD_OID_PERI_DESD AND
                       NVL(PERD_OID_PERI_HAST,
                           (SELECT cra.OID_PERI
                              FROM BAS_CTRL_FACT   A,
                                   SEG_PERIO_CORPO B,
                                   CRA_PERIO       CRA
                             WHERE A.STA_CAMP = '0'
                               AND A.IND_CAMP_ACT = 1
                               AND B.COD_PERI = A.COD_PERI
                               AND B.COD_PERI = A.COD_PERI
                               AND B.OID_PERI = CRA.PERI_OID_PERI));

                IF lnExiste = 1 THEN
                  -- Validar que el cï¿½digo de nivel correspona al programa, con pscodigoPrograma
                  -- y cï¿½digo de nivel accesar la tabla Niveles Programa, de no encontrar registro,
                  -- rechazar registro indicando que nivel no corresponde al Programa
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM LEC_PROGR_NIVEL
                   WHERE LPRO_COD_PROG = pscodigoPrograma
                     AND LNIV_COD_NIVE = lnvalDato2;

                  IF lnExiste = 0 THEN
                    val_obse        := 'NIVEL NO CORRESPONDE AL PROGRAMA';
                    lnEstadoProceso := 1;
                  END IF;

                ELSE
                  -- De no encontrar registro, rechazar registro indicando que Cï¿½digo de lï¿½der no etï¿½ registrada en campaï¿½a
                  val_obse        := 'CODIGï¿½ DE LIDER NO ESTï¿½ REGISTRADO EN CAMPAï¿½A';
                  lnEstadoProceso := 1;
                END IF;
              END IF;

            ELSE

              IF pscodigoTipoCarga = '11' THEN
                vsCodTipoMedic := '01';
                vsCodTipoBono  := '01';
              ELSIF pscodigoTipoCarga = '12' THEN
                vsCodTipoMedic := '01';
                vsCodTipoBono  := '02';
              ELSIF pscodigoTipoCarga = '13' THEN
                vsCodTipoMedic := '02';
                vsCodTipoBono  := '03';
              ELSIF pscodigoTipoCarga = '14' THEN
                vsCodTipoMedic := '02';
                vsCodTipoBono  := '04';
              END IF;

              IF pscodigoTipoCarga = '11' OR pscodigoTipoCarga = '12' OR
                 pscodigoTipoCarga = '13' OR pscodigoTipoCarga = '14' THEN
                --i.    Validar que el Tipo de Mediciï¿½n estï¿½ registrada para la campaï¿½a.
                SELECT COUNT(1)
                  INTO lnExiste
                  FROM LEC_PROGR_BONO_LANZA
                 WHERE PAIS_COD_PAIS = psCodigoPais
                   AND LPRO_COD_PROG = psCodigoPrograma
                   AND LTME_COD_TIPO_MEDI = vsCodTipoMedic
                   AND LPBC_CAM_LANZ = lnvalDato1
                   AND NUM_LANZ = lnvalDato5;

                IF lnExiste = 0 THEN
                  val_obse        := 'Tipo de Medidiï¿½n no registrada para la campaï¿½a';
                  lnEstadoProceso := 1;
                ELSE
                  --ii.    Validar que el Producto estï¿½ registrada para el Tipo de Mediciï¿½n.
                  SELECT COUNT(1)
                    INTO lnExiste
                    FROM LEC_PROGR_BONO_LANZA_PRODU
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodigoPrograma
                     AND LPBC_CAM_LANZ = lnvalDato1
                     AND LPBL_NUM_LANZ = lnvalDato5
                     AND COD_SAP = lnvalDato7;

                  IF lnExiste = 0 THEN
                    val_obse        := 'Producto no registrado para el Tipo de Medidiï¿½n';
                    lnEstadoProceso := 1;
                  ELSE
                    --iii.    Validar que el Nivel estï¿½ registrada para el Tipo de Bono.
                    SELECT COUNT(1)
                      INTO lnExiste
                      FROM LEC_PROGR_BONO_NIVEL
                     WHERE PAIS_COD_PAIS = psCodigoPais
                       AND LPRO_COD_PROG = psCodigoPrograma
                       AND LTBO_COD_TIPO_BONO = vsCodTipoBono
                       AND LNIV_COD_NIVE = lnvalDato6
                       AND LPBC_CAM_LANZ = lnvalDato1
                       AND LPBL_NUM_LANZ = lnvalDato5;

                    IF lnExiste = 0 THEN
                      val_obse        := 'Nivel no registrado para el Tipo de Bono';
                      lnEstadoProceso := 1;
                    ELSE
                      -- iv.    Validar que el Objetivo sea numï¿½rico y > 0.
                      BEGIN
                        lnTmp    := TO_NUMBER(lnvalDato8, '999999999.99');
                        lnExiste := 1;
                      EXCEPTION
                        WHEN OTHERS THEN
                          lnExiste := 0;
                      END;

                      IF lnExiste = 0 THEN
                        val_obse        := 'Objetivo no es un nï¿½mero';
                        lnEstadoProceso := 1;
                      ELSIF TO_NUMBER(lnvalDato8, '999999999.99') <= 0 THEN
                        val_obse        := 'Objetivo debe de ser mayor a CERO';
                        lnEstadoProceso := 1;
                      ELSE
                        --v.    Validar Region + Zona + Secciï¿½n: Se relacionan las entidades Region, Zona y Secciï¿½n
                        SELECT COUNT(1)
                          INTO lnExiste
                          FROM ZON_REGIO ZR, ZON_ZONA ZZ, ZON_SECCI ZS
                         WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
                           AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
                           AND ZR.IND_ACTI = '1'
                           AND ZZ.IND_ACTI = '1'
                           AND ZS.IND_ACTI = '1'
                           AND ZR.COD_REGI = lnvalDato2
                           AND ZZ.COD_ZONA = lnvalDato3
                           AND ZS.COD_SECC = lnvalDato4;

                        IF lnExiste = 0 THEN
                          val_obse        := 'Secciï¿½n no existe';
                          lnEstadoProceso := 1;
                        ELSE
                          SELECT COUNT(1)
                            INTO lnExiste
                            FROM LEC_PROGR_BONO_NIVEL
                           WHERE PAIS_COD_PAIS = psCodigoPais
                             AND LPRO_COD_PROG = pscodigoPrograma
                             AND LPBC_CAM_LANZ = lnvalDato1
                             AND VAL_PORC_OBJE > 0;

                          IF lnExiste <> 0 THEN
                            val_obse        := 'Se ha parametrizado bonos de lanzamiento con % de exigencia para la campaï¿½a';
                            lnEstadoProceso := 1;
                          END IF;

                        END IF;

                      END IF;

                    END IF;

                  END IF;

                END IF;

              END IF;

          END CASE;

          IF val_obse <> ' ' then
            pnIndicadorCarga := 0;
            UPDATE Lec_Progr_Carga_Dato_Masiv
               SET Val_Erro = val_obse, ind_regi_erra = lnEstadoProceso
             WHERE Num_Carg = psNumeroCarga
               AND Num_Secu = lnnumeroSecuencia;
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;
    CLOSE c_programas;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_VALID_CARGA_DATOS_MASIV: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_VALID_CARGA_DATOS_MASIV;

  /***************************************************************************
   Descripcion       : Actualiza Carga Masiva de Tarjetas
   Fecha Creacion    : 02/12/2014
   Autor             : Diego Torres L.
  ***************************************************************************/
  PROCEDURE LEC_PR_GRABA_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS
  BEGIN
    pnIndicadorProceso := '1';

    INSERT INTO LEC_TARJE_PAGOS
      (COD_TARJ,
       NUM_TARJ,
       LEST_COD_ESTA,
       CAM_CREA,
       IND_ACTI,
       USU_CREA,
       FEC_CREA)
      SELECT LEC_SEC_TARJ_PAGO.nextval,
             X.VAL_DATO_1,
             '01',
             (SELECT cod_peri
                FROM BAS_CTRL_FACT
               WHERE STA_CAMP = '0'
                 AND IND_CAMP_ACT = 1),
             1,
             X.USU_CREA,
             X.FEC_CREA
        from LEC_PROGR_CARGA_DATO_MASIV X
       WHERE X.PAIS_COD_PAIS = psCodigoPais
         AND X.LPRO_COD_PROG = pscodigoPrograma
         and X.Ind_Regi_Proc = 0
         AND X.ind_regi_erra = 0
         AND X.COD_TIPO_CARG = pscodigoTipoCarga
         AND X.NUM_CARG = psNumeroCarga;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GRABA_TARJE_PAGOS: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_GRABA_TARJE_PAGOS;

  /***************************************************************************
   Descripcion       : Actualiza Asociaciï¿½n Masiva de Tarjetas
   Fecha Creacion    : 02/12/2014
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_ASOCI_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1, VAL_DATO_2, USU_CREA, FEC_CREA, Num_Secu
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND ind_regi_erra = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      usuCrea         LEC_PROGR_CARGA_DATO_MASIV.USU_CREA%TYPE,
      fecCrea         LEC_PROGR_CARGA_DATO_MASIV.FEC_CREA%TYPE,
      numeroSecuencia LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN interfazProgramasTab;

    lnvalDato1             LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    lnvalDato2             LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE;
    lnusuCrea              LEC_PROGR_CARGA_DATO_MASIV.USU_CREA%TYPE;
    lnfecCrea              LEC_PROGR_CARGA_DATO_MASIV.FEC_CREA%TYPE;
    lnnumeroSecuencia      LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE;
    lsCodigoMotivo         LEC_PROGR_CARGA_DATO_MASIV.VAL_ERRO%TYPE;
    lnExiste               NUMBER(2);
    lnCodigoEstado         VARCHAR2(2 BYTE);
    lnCodigoTarjeta        NUMBER(10);
    lnCodigoTarjetaAntiguo NUMBER(10);

  BEGIN
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnvalDato1         := interfazRecordN(x).valDato1;
          lnvalDato2         := interfazRecordN(x).valDato2;
          lnusuCrea          := interfazRecordN(x).usuCrea;
          lnfecCrea          := interfazRecordN(x).fecCrea;
          lnnumeroSecuencia  := interfazRecordN(x).numeroSecuencia;
          lsCodigoMotivo     := '';
          pnIndicadorProceso := '1';

          SELECT COD_TARJ
            INTO lnCodigoTarjeta
            FROM LEC_TARJE_PAGOS
           WHERE NUM_TARJ = lnvalDato1;

          SELECT COUNT(1)
            INTO lnExiste
            from LEC_TARJE_LIDER A
           INNER JOIN LEC_TARJE_PAGOS B
              ON A.LTPG_COD_TARJ = B.COD_TARJ
           WHERE A.COD_LIDE = lnvalDato2
             AND B.LEST_COD_ESTA = '03'
             AND A.IND_ACTI = 1;

          IF lnExiste > 0 THEN
            SELECT B.COD_TARJ
              INTO lnCodigoTarjetaAntiguo
              from LEC_TARJE_LIDER A
             INNER JOIN LEC_TARJE_PAGOS B
                ON A.LTPG_COD_TARJ = B.COD_TARJ
             WHERE A.COD_LIDE = lnvalDato2
               AND B.LEST_COD_ESTA = '03'
               AND A.IND_ACTI = 1;

            UPDATE LEC_TARJE_LIDER
               SET CAM_BLOQ =
                   (SELECT cod_peri
                      FROM BAS_CTRL_FACT
                     WHERE STA_CAMP = '0'
                       AND IND_CAMP_ACT = 1),
                   USU_BLOQ = pnCodigoUsuario,
                   FEC_BLOQ = SYSDATE,
                   usu_modi = pnCodigoUsuario,
                   fec_modi = SYSDATE
             WHERE LTPG_COD_TARJ = lnCodigoTarjetaAntiguo
               AND COD_LIDE = lnvalDato2;

            UPDATE LEC_TARJE_PAGOS
               SET LEST_COD_ESTA = '04'
             WHERE COD_TARJ = lnCodigoTarjetaAntiguo;

            update MAE_CLIEN_LIDER
               set NUM_LIAH = null
             where cod_clid = lnvalDato2;

          END IF;

          UPDATE LEC_TARJE_PAGOS
             SET LEST_COD_ESTA = '02'
           WHERE NUM_TARJ = lnvalDato1;

          INSERT INTO LEC_TARJE_LIDER
            (COD_LIDE,
             LTPG_COD_TARJ,
             CAM_CREA,
             USU_CREA,
             FEC_CREA,
             IND_ACTI)
          VALUES
            (lnvalDato2,
             lnCodigoTarjeta,
             (SELECT cod_peri
                FROM BAS_CTRL_FACT
               WHERE STA_CAMP = '0'
                 AND IND_CAMP_ACT = 1),
             pnCodigoUsuario,
             sysdate,
             1);

          update MAE_CLIEN_LIDER
             set NUM_LIAH = lnvalDato1
           where cod_clid = lnvalDato2;

        END LOOP;
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;

    --Actualiza la fila procesada
    UPDATE Lec_Progr_Carga_Dato_Masiv
       SET Val_Erro = lsCodigoMotivo, Ind_Regi_Proc = 1
     WHERE Num_Carg = psNumeroCarga
       AND Num_Secu = lnnumeroSecuencia;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ASOCI_TARJE_PAGOS: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_ASOCI_TARJE_PAGOS;

  /***************************************************************************
   Descripcion       : Carga Nivel Exito
   Fecha Creacion    : 18/03/2015
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_NIVEL_LIDER(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1, VAL_DATO_2, Num_Secu
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND ind_regi_erra = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      numeroSecuencia LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN interfazProgramasTab;

    lnvalDato1             LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    lnvalDato2             LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE;
    lnnumeroSecuencia      LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE;
    lsCodigoMotivo         LEC_PROGR_CARGA_DATO_MASIV.VAL_ERRO%TYPE;
    lnExiste               NUMBER(2);
    lnCodigoEstado         VARCHAR2(2 BYTE);
    lnCodigoTarjeta        NUMBER(10);
    lnCodigoTarjetaAntiguo NUMBER(10);

  BEGIN

    -- a.  Eliminar los registros de la entidad Nivel Lï¿½der correspondientes
    -- donde LPRO_COD_PROG = pscodigoPrograma

    delete from LEC_LIDER_NIVEL where LPRO_COD_PROG = pscodigoPrograma;

    -- b.  Insertar registro en la entidad Nivel Lï¿½der:

    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnvalDato1         := interfazRecordN(x).valDato1;
          lnvalDato2         := interfazRecordN(x).valDato2;
          lnnumeroSecuencia  := interfazRecordN(x).numeroSecuencia;
          lsCodigoMotivo     := '';
          pnIndicadorProceso := '1';

          insert into LEC_LIDER_NIVEL
            (PAIS_COD_PAIS,
             LPRO_COD_PROG,
             COD_LIDE,
             LNIV_COD_NIVE,
             CAM_INIC,
             IND_TIPO_NIVE,
             CAM_FIN,
             IND_CAMB,
             USU_CREA,
             FEC_CREA,
             IND_ACTI,
             IND_ORIG_CALC)
          values
            (psCodigoPais,
             pscodigoPrograma,
             lnvalDato1,
             lnvalDato2,
             (SELECT cod_peri
                FROM BAS_CTRL_FACT
               WHERE STA_CAMP = '0'
                 AND IND_CAMP_ACT = 1),
             'R',
             null,
             null,
             pnCodigoUsuario,
             sysdate,
             '1',
             'X');

        END LOOP;
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CARGA_NIVEL_LIDER: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_CARGA_NIVEL_LIDER;

  /***************************************************************************
   Descripcion       : Carga Anulacion Masiva de Tarjetas
   Fecha Creacion    : 04/02/2015
   Autor             : Diego Torres L.

  ***************************************************************************/
  PROCEDURE LEC_PR_ANULA_TARJE_PAGOS(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS
  BEGIN
    pnIndicadorProceso := '1';

    DELETE FROM LEC_TARJE_PAGOS
     WHERE NUM_TARJ IN (SELECT X.VAL_DATO_1
                          from LEC_PROGR_CARGA_DATO_MASIV X
                         WHERE X.PAIS_COD_PAIS = psCodigoPais
                           AND X.LPRO_COD_PROG = pscodigoPrograma
                           and X.Ind_Regi_Proc = 0
                           AND X.ind_regi_erra = 0
                           AND X.COD_TIPO_CARG = pscodigoTipoCarga
                           AND X.NUM_CARG = psNumeroCarga);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ANULA_TARJE_PAGOS: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_ANULA_TARJE_PAGOS;
  
   /***************************************************************************
   Descripcion       : Carga Actualizacion Estatus Reenvio de Tarjeta
   Fecha Creacion    : 15/12/2015
   Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE LEC_PR_ACTUA_ESTAT_REENV_TARJE(
                                     psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1 valDato1
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND ind_regi_erra = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramasTab IS TABLE OF c_programas%ROWTYPE;
    interfazRecordN interfazProgramasTab;
    lnvalDato1             LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    
  BEGIN
    pnIndicadorProceso := '1'; 
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT  INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN  
       FORALL i IN 1 .. interfazRecordN.count
          UPDATE LEC_TARJE_PAGOS x
          SET x.LEST_COD_ESTA = '02',
              x.USU_MODI = pnCodigoUsuario,
              x.FEC_MODI = SYSDATE
          WHERE x.NUM_TARJ = interfazRecordN(i).valDato1
            AND x.LEST_COD_ESTA = '03';
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ACTUA_ESTAT_REENV_TARJE: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_ACTUA_ESTAT_REENV_TARJE;
  
  
  /***************************************************************************
   Descripcion       : Actualiza Carga de Datos de Brillantes
   Fecha Creacion    : 23/01/2014
   Autor             : henry paredes

  ***************************************************************************/

  PROCEDURE LEC_PR_GRABA_DATOS_BRILL(psCodigoPais       VARCHAR2,
                                     psNumeroCarga      NUMBER,
                                     pscodigoPrograma   VARCHAR2,
                                     pscodigoTipoCarga  VARCHAR2,
                                     pnCodigoUsuario    VARCHAR2,
                                     pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1,
             VAL_DATO_2,
             VAL_DATO_3,
             VAL_DATO_4,
             VAL_DATO_5,
             VAL_DATO_6,
             VAL_DATO_7,
             VAL_DATO_8,
             VAL_DATO_9,
             VAL_DATO_10,
             Num_Secu,
             Ind_Regi_Proc
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND ind_regi_erra = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      valDato3         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE,
      valDato4         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE,
      valDato5         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE,
      valDato6         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE,
      valDato7         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE,
      valDato8         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE,
      valDato9         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE,
      valDato10        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE,
      numeroSecuencia  LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE,
      indicadorProceso LEC_PROGR_CARGA_DATO_MASIV.IND_REGI_PROC%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN   interfazProgramasTab;
    lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
    lnvalDato1        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    lnvalDato2        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE;
    lnvalDato3        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE;
    lnvalDato4        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE;
    lnvalDato5        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE;
    lnvalDato6        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE;
    lnvalDato7        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE;
    lnvalDato8        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE;
    lnvalDato9        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE;
    lnvalDato10       LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE;
    lnnumeroSecuencia LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE;
    lsCodigoMotivo    LEC_PROGR_CARGA_DATO_MASIV.VAL_ERRO%TYPE;
    lnEstadoProceso   NUMBER(1);
    lnCantidad        NUMBER(2);
    lnTraslape        NUMBER(2);
    lnExiste          NUMBER(2);

  BEGIN

    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnvalDato1         := interfazRecordN(x).valDato1;
          lnvalDato2         := interfazRecordN(x).valDato2;
          lnvalDato3         := interfazRecordN(x).valDato3;
          lnvalDato4         := interfazRecordN(x).valDato4;
          lnvalDato5         := interfazRecordN(x).valDato5;
          lnvalDato6         := interfazRecordN(x).valDato6;
          lnvalDato7         := interfazRecordN(x).valDato7;
          lnvalDato8         := interfazRecordN(x).valDato8;
          lnvalDato9         := interfazRecordN(x).valDato9;
          lnvalDato10        := interfazRecordN(x).valDato10;
          lnnumeroSecuencia  := interfazRecordN(x).numeroSecuencia;
          lsCodigoMotivo     := '';
          pnIndicadorProceso := '1';

          CASE pscodigoTipoCarga
            WHEN '02' THEN
              SELECT COUNT(1)
                INTO lnExiste
                FROM LEC_PROGR_LISTA_EXCLU
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND LPRO_COD_PROG = pscodigoPrograma
                 AND COD_CONS = lnvalDato1
                 AND IND_ACTI = 1;

              IF lnExiste > 0 THEN
                UPDATE LEC_PROGR_LISTA_EXCLU
                   SET CAM_FIN_VIGE  = lnvalDato3,
                       CAM_INIC_VIGE = lnvalDato2,
                       IND_ACTI      = 1,
                       usu_modi      = pnCodigoUsuario,
                       fec_modi      = SYSDATE
                 WHERE PAIS_COD_PAIS = psCodigoPais
                   AND LPRO_COD_PROG = pscodigoPrograma
                   AND COD_CONS = lnvalDato1
                   AND IND_ACTI = 1;
              END IF;

              IF lnExiste = 0 THEN
                INSERT INTO LEC_PROGR_LISTA_EXCLU
                  (PAIS_COD_PAIS,
                   LPRO_COD_PROG,
                   SEC_EXCL,
                   COD_CONS,
                   CAM_INIC_VIGE,
                   CAM_FIN_VIGE,
                   IND_ACTI,
                   USU_CREA,
                   FEC_CREA)
                VALUES
                  (psCodigoPais,
                   pscodigoPrograma,
                   LEC_SEQ_EXCL.NEXTVAL,
                   lnvalDato1,
                   lnvalDato2,
                   lnvalDato3,
                   1,
                   pnCodigoUsuario,
                   SYSDATE);
              END IF;
          END CASE;
        END LOOP;
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;
    CLOSE c_programas;

    --Actualiza la fila procesada
    UPDATE Lec_Progr_Carga_Dato_Masiv
       SET Val_Erro = lsCodigoMotivo, Ind_Regi_Proc = 1
     WHERE Num_Carg = psNumeroCarga
       AND Num_Secu = lnnumeroSecuencia;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GRABA_DATOS_BRILL: (' ||
                              lnvalDato1 || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END LEC_PR_GRABA_DATOS_BRILL;

  /***************************************************************************
   Descripcion       :  Actualiza Carga de Datos objetivos pedidos
   Fecha Creacion    : 28/01/2014
   Autor             : henry paredes
  ***************************************************************************/

  PROCEDURE LEC_PR_GRABA_DATOS_OBJEC_PEDID(psCodigoPais       VARCHAR2,
                                           psNumeroCarga      NUMBER,
                                           pscodigoPrograma   VARCHAR2,
                                           pscodigoTipoCarga  VARCHAR2,
                                           pnCodigoUsuario    VARCHAR2,
                                           pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1,
             VAL_DATO_2,
             VAL_DATO_3,
             VAL_DATO_4,
             VAL_DATO_5,
             VAL_DATO_6,
             VAL_DATO_7,
             VAL_DATO_8,
             VAL_DATO_9,
             VAL_DATO_10,
             Num_Secu,
             Ind_Regi_Proc
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         and Ind_Regi_Proc = 0
         AND ind_regi_erra = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      valDato3         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE,
      valDato4         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE,
      valDato5         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE,
      valDato6         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE,
      valDato7         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE,
      valDato8         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE,
      valDato9         LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE,
      valDato10        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE,
      numeroSecuencia  LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE,
      indicadorProceso LEC_PROGR_CARGA_DATO_MASIV.IND_REGI_PROC%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN   interfazProgramasTab;
    lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
    lnvalDato1        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE;
    lnvalDato2        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE;
    lnvalDato3        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE;
    lnvalDato4        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE;
    lnvalDato5        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE;
    lnvalDato6        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE;
    lnvalDato7        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_7%TYPE;
    lnvalDato8        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE;
    lnvalDato9        LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_9%TYPE;
    lnvalDato10       LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_10%TYPE;
    lnnumeroSecuencia LEC_PROGR_CARGA_DATO_MASIV.NUM_SECU%TYPE;
    lsCodigoMotivo    LEC_PROGR_CARGA_DATO_MASIV.VAL_ERRO%TYPE;
    lnEstadoProceso   NUMBER(1);
    lnCantidad        NUMBER(2);
    lnTraslape        NUMBER(2);
    lnExiste          NUMBER(2);

  BEGIN

    --(1) PROCESAMOS A LOS CLIENTES
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
          lnvalDato1         := interfazRecordN(x).valDato1;
          lnvalDato2         := interfazRecordN(x).valDato2;
          lnvalDato3         := interfazRecordN(x).valDato3;
          lnvalDato4         := interfazRecordN(x).valDato4;
          lnvalDato5         := interfazRecordN(x).valDato5;
          lnvalDato6         := interfazRecordN(x).valDato6;
          lnvalDato7         := interfazRecordN(x).valDato7;
          lnvalDato8         := interfazRecordN(x).valDato8;
          lnvalDato9         := interfazRecordN(x).valDato9;
          lnvalDato10        := interfazRecordN(x).valDato10;
          lnnumeroSecuencia  := interfazRecordN(x).numeroSecuencia;
          lsCodigoMotivo     := '';
          pnIndicadorProceso := '1';

          CASE pscodigoTipoCarga
            WHEN '01' THEN

              select count(1)
                into lnExiste
                from LEC_LIDER_SECCI_OBJET_PEDID
               where lpro_cod_pais = psCodigoPais
                 and lpro_cod_prog = pscodigoPrograma
                 and Cod_Regi = lnvalDato1
                 and cod_zona = lnvalDato2
                 and Cod_Secc = lnvalDato3
                 and cam_obje = lnvalDato4;

              if lnExiste = 1 then
                delete from LEC_LIDER_SECCI_OBJET_PEDID
                 where lpro_cod_pais = psCodigoPais
                   and lpro_cod_prog = pscodigoPrograma
                   and Cod_Regi = lnvalDato1
                   and cod_zona = lnvalDato2
                   and Cod_Secc = lnvalDato3
                   and cam_obje = lnvalDato4;
              end if;
              insert into LEC_LIDER_SECCI_OBJET_PEDID
                (lpro_cod_pais,
                 lpro_cod_prog,
                 Cod_Regi,
                 cod_zona,
                 Cod_Secc,
                 cam_obje,
                 num_pedi_obje_inic,
                 num_pedi_obje_fina,
                 val_obje_vent,
                 ind_orig_calc,
                 IND_PROC,
                 usu_crea,
                 Fec_crea,
                 ind_acti)
              VALUES
                (psCodigoPais,
                 pscodigoPrograma,
                 lnvalDato1,
                 lnvalDato2,
                 lnvalDato3,
                 lnvalDato4,
                 lnvalDato5,
                 lnvalDato5,
                 lnvalDato6,
                 'X',
                 'N',
                 pnCodigoUsuario,
                 SYSDATE,
                 1);
          END CASE;
        END LOOP;
      END IF;
      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;
    CLOSE c_programas;

    --Actualiza la fila procesada
    UPDATE Lec_Progr_Carga_Dato_Masiv
       SET Val_Erro = lsCodigoMotivo, Ind_Regi_Proc = 1
     WHERE Num_Carg = psNumeroCarga
       AND Num_Secu = lnnumeroSecuencia;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GRABA_DATOS_OBJEC_PEDID: (' ||
                              lnvalDato1 || ' - ' || ln_sqlcode || ')' ||
                              ls_sqlerrm);
  END LEC_PR_GRABA_DATOS_OBJEC_PEDID;

  /***********************************************************************************************
  Descripcion       : Actualizar masivamente la clasificaciï¿½n correspondiente de las
                      lï¿½deres activas y las que dejan de ser activas en campaï¿½a anterior
  Fecha Creacion    : 21/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_CLASI_MASIV(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS

    vsCampAnte SEG_PERIO_CORPO.COD_PERI%TYPE;

    CURSOR cGetLideres IS
      SELECT GERE
        FROM ZON_HISTO_GEREN
       WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) >=
             PERD_OID_PERI_DESD
         and (PERD_OID_PERI_HAST IS NULL OR GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) <=
              PERD_OID_PERI_HAST)
         AND COD_SECC IS NOT NULL
         AND PAIS_OID_PAIS =
             GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais)
         AND MARC_OID_MARC =
             GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca)
         AND CANA_OID_CANA =
             GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal);

    --Obtiene campanias anteriores.
    CURSOR cLideresPeriodoAnterior(oidPeriodo    CRA_PERIO.OID_PERI%TYPE,
                                   oidPeriodoAnt CRA_PERIO.OID_PERI%TYPE) IS
      SELECT ZHG.GERE
        FROM ZON_HISTO_GEREN ZHG
       WHERE oidPeriodoAnt >= ZHG.PERD_OID_PERI_DESD
         AND (oidPeriodoAnt <= ZHG.PERD_OID_PERI_HAST OR
             ZHG.PERD_OID_PERI_HAST is null)
         AND ZHG.COD_SECC IS NOT NULL
         AND ZHG.GERE NOT IN
             (SELECT AUX.GERE
                FROM ZON_HISTO_GEREN AUX
               WHERE oidPeriodo >= AUX.PERD_OID_PERI_DESD
                 AND (oidPeriodo <= AUX.PERD_OID_PERI_HAST or
                     AUX.PERD_OID_PERI_HAST is null)
                 AND AUX.COD_SECC IS NOT NULL);

    TYPE interfazLideres IS RECORD(
      codigoLider ZON_HISTO_GEREN.Gere%TYPE);

    TYPE interfazLideresTab IS TABLE OF interfazLideres;
    interfazLideresRecord interfazLideresTab;

    TYPE interfazLideresAnt IS RECORD(
      codigoLider ZON_HISTO_GEREN.Gere%TYPE);

    TYPE interfazLideresAntTab IS TABLE OF interfazLideresAnt;
    interfazLideresAntRecord interfazLideresAntTab;

    lsCodigoLider ZON_HISTO_GEREN.Gere%TYPE;
    lnFechaFact             DATE;
    lnExisteCampFact        NUMBER;
    vsCampCruce             seg_perio_corpo.cod_peri%type;
    vsMinCamp               seg_perio_corpo.cod_peri%type;
    lsIndCruce              varchar2(1);
    vsIndCruce              CRA_PERIO.Ind_Peri_Cruc%TYPE;

  BEGIN

    DELETE FROM LEC_LIDER_CLASI a WHERE a.CAM_INIC = psCampanaProceso;

    UPDATE LEC_LIDER_CLASI a
       SET a.CAM_FIN = NULL
     WHERE a.CAM_FIN = GEN_FN_CALCU_PERIO(psCampanaProceso, -1) OR 
        a.CAM_FIN = psCampanaProceso;   -----
     
    BEGIN
      SELECT   fec_proc          
        into  lnFechaFact
        FROM BAS_CTRL_FACT 
       WHERE STA_CAMP = '0'
         AND IND_CAMP_ACT = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lnFechaFact := null;
    END;

       SELECT COUNT(1)
       INTO lnExisteCampFact
       FROM CRA_PERIO
       WHERE lnFechaFact >= FEC_INIC
       AND lnFechaFact <= FEC_FINA;
       
       lsIndCruce := '0';

       IF lnExisteCampFact > 1 THEN
            SELECT MAX(COD_PERI), Min(COD_PERI)
            INTO vsCampCruce, vsMinCamp
            FROM CRA_PERIO cr, SEG_PERIO_CORPO sg
            WHERE lnFechaFact >= FEC_INIC
            AND lnFechaFact <= FEC_FINA
            AND cr.peri_oid_peri = sg.oid_peri ;
              
            SELECT CRA.IND_PERI_CRUC
            INTO vsIndCruce
            FROM CRA_PERIO CRA, SEG_PERIO_CORPO CP
            WHERE COD_PERI = vsMinCamp
            AND CRA.PERI_OID_PERI = CP.OID_PERI;
            
            IF  vsCampCruce =  psCampanaProceso AND vsIndCruce = 1 THEN
                 lsIndCruce := '1';
            END IF;
        END IF;
    /* Tipo Proceso :
            1 = Procesar Clasificaciï¿½n Lï¿½der Vigente(actualiza y crea)
    */
    OPEN cGetLideres;
    LOOP
      FETCH cGetLideres BULK COLLECT
        INTO interfazLideresRecord LIMIT W_FILAS;
      IF interfazLideresRecord.COUNT > 0 THEN

        FOR i IN interfazLideresRecord.FIRST .. interfazLideresRecord.LAST LOOP
          lsCodigoLider := interfazLideresRecord(i).codigoLider;

          LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais,
                                   psCodigoMarca,
                                   psCodigoCanal,
                                   '1',
                                   lsCodigoLider,
                                   psCampanaProceso,
                                   lsIndCruce,                                  
                                   psCodigoUsuario);
        END LOOP;
      END IF;
      EXIT WHEN cGetLideres%NOTFOUND;
    END LOOP;
    CLOSE cGetLideres;

    /* Tipo Proceso :
             2 = Procesar Cerrar Clasificaciï¿½n Lï¿½der Vigente
    */
    vsCampAnte := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                   psCampanaProceso,
                                                   -1);

    OPEN cLideresPeriodoAnterior(GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso),
                                 GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vsCampAnte));
    LOOP
      FETCH cLideresPeriodoAnterior BULK COLLECT
        INTO interfazLideresAntRecord LIMIT W_FILAS;
      IF interfazLideresAntRecord.COUNT > 0 THEN

        FOR i IN interfazLideresAntRecord.FIRST .. interfazLideresAntRecord.LAST LOOP
          lsCodigoLider := interfazLideresAntRecord(i).codigoLider;

          LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais,
                                   psCodigoMarca,
                                   psCodigoCanal,
                                   '2',
                                   lsCodigoLider,
                                   vsCampAnte,
                                   lsIndCruce,
                                   psCodigoUsuario);
        END LOOP;
      END IF;
      EXIT WHEN cLideresPeriodoAnterior%NOTFOUND;
    END LOOP;
    CLOSE cLideresPeriodoAnterior;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ACTUA_CLASI_MASIV: ' ||
                              ls_sqlerrm);
  END LEC_PR_ACTUA_CLASI_MASIV;

  /***********************************************************************************************
  Descripcion       : Permite actualizar la clasificaciï¿½n de la lï¿½der en una determinada campaï¿½a.
  Fecha Creacion    : 21/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psIndCruce       VARCHAR2, 
                                     psCodigoUsuario  VARCHAR2) IS

    lnCantRegistros     NUMBER(5) := 0;
    vnNroCampEst        NUMBER(10);
    vsCampInicial       SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCampAnterior      SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnIndExisteAnterior NUMBER(10);
    vnOidPais           SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca          SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal          SEG_CANAL.OID_CANA%TYPE;
    vnOidPeriodo        CRA_PERIO.OID_PERI%TYPE;
    vsClasifAnter       LEC_LIDER_CLASI.LCCL_COD_CLAS%TYPE;
    vsSubClasifAnter    LEC_LIDER_CLASI.LSCL_COD_SUBC%TYPE;
    vsCampanaInicio     LEC_LIDER_CLASI.CAM_INIC%TYPE;
    vsCampanaFin        LEC_LIDER_CLASI.CAM_FIN%TYPE;
    vnNroCamp           NUMBER(10);
    vsClasif            LEC_LIDER_CLASI.LCCL_COD_CLAS%TYPE;
    vsMinClasif         LEC_LIDER_CLASI.LCCL_COD_CLAS%TYPE;
    vsSubClasif         LEC_LIDER_CLASI.LSCL_COD_SUBC%TYPE;

    vnContador          NUMBER(10) := 1;
    vnIndExiste         NUMBER(2) := 0;
    vnMiniCamp          SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnAuxCamp           SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnDiferCamp         SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnSgteNiv           VARCHAR2(1);
    vnSgteNiv2          VARCHAR2(1);
    lsUltiCampa         NUMBER;
    vnNroCampNue        NUMBER;

  BEGIN
    vnOidPais  := GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais);
    vnOidMarca := GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca);
    vnOidCanal := GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal);

    BEGIN
      SELECT para.val_para
      into   lsUltiCampa
      FROM   BAS_PARAM_PAIS para
      WHERE  para.cod_pais = psCodigoPais
      AND    para.cod_sist = 'GEN'
      AND   upper(para.nom_para) = 'NUMCAMPANAS';
      EXCEPTION
          WHEN NO_DATA_FOUND THEN
      lsUltiCampa := 18;  
    END;
    -- INICIO DEL PROCESO = 1
    IF psTipoProceso = '1' THEN
      SELECT max(sc.num_camp)
      INTO   vnNroCampNue
      FROM   LEC_SUBCL SC
      WHERE  SC.LCCL_COD_CLAS = '01';

      DELETE FROM LEC_LIDER_CLASI a
       WHERE a.CAM_INIC = psCampanaProceso
         AND a.COD_LIDE = psCodigoLider;

      UPDATE LEC_LIDER_CLASI a
         SET a.CAM_FIN = NULL
       WHERE ( a.CAM_FIN = GEN_FN_CALCU_PERIO(psCampanaProceso, -1) OR
               a.cam_fin = psCampanaProceso )
         AND a.COD_LIDE = psCodigoLider;

      SELECT DECODE(COUNT(*), 0, '0', '1')
      INTO   vnSgteNiv
      FROM   LEC_LIDER_CLASI a
      WHERE  a.cam_inic = GEN_FN_CALCU_PERIO(psCampanaProceso, 1)
      AND    a.COD_LIDE = psCodigoLider; 
       
      vnSgteNiv2 := vnSgteNiv;   
      IF  psIndCruce = '1' THEN
          vnSgteNiv := '1';
      END IF;  


      -- Obtiene el maximo del numero de campaï¿½as de la subClasificacion
      SELECT MAX(num_camp) INTO vnNroCampEst FROM LEC_subcl;

      vsCampInicial       := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                              psCampanaProceso,
                                                              -lsUltiCampa);
      vsCampAnterior      := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                              psCampanaProceso,
                                                              -1);
      vnIndExisteAnterior := 0;

      BEGIN
        -- Si encuentra registro
        SELECT cl.lccl_cod_clas, lscl_cod_subc, cam_inic, cam_fin, num_camp
          INTO vsClasifAnter,
               vsSubClasifAnter,
               vsCampanaInicio,
               vsCampanaFin,
               vnNroCamp
          FROM LEC_LIDER_CLASI CL, LEC_SUBCL sb
         WHERE cod_lide = psCodigoLider
           AND vsCampAnterior >= cam_inic
           AND vsCampAnterior <= NVL(cam_fin, vsCampAnterior)
           AND CL.lccl_cod_clas = sb.lccl_cod_clas
           AND lscl_cod_subc = sb.cod_subc;

        vnIndExisteAnterior := 1; --vnIndExisteClasiAnterior
        
        vnNroCamp := vnNroCamp + 1;
        IF  ((vsClasifAnter='01' AND vnNroCamp >= 4) OR (vsClasifAnter='03' AND vnNroCamp >= 6))  THEN  --- minimas camp?as de establecidas
            BEGIN
              SELECT lccl_cod_clas, cod_subc
                INTO vsClasif, vsSubClasif
                FROM LEC_subcl -- SUB CLASIFICACION
               WHERE num_camp = vnNroCamp
                 AND LCCL_COD_CLAS = '02'
                 AND ind_acti = 1;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                -- Si no encuentra registro
                IF vnNroCamp > vnNroCampEst THEN
                  vsClasif    := '02';
                  vsSubClasif := '14';
                ELSE
                  vsClasif    := NULL;
                  vsSubClasif := NULL;
                END IF;
            END;
        ELSE
           BEGIN
           SELECT lccl_cod_clas, cod_subc
                INTO vsClasif, vsSubClasif
                FROM LEC_subcl -- SUB CLASIFICACION
               WHERE num_camp = vnNroCamp
               AND   lccl_cod_clas = vsClasifAnter
               AND   ind_acti = 1;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                 vsClasif    := NULL;
                 vsSubClasif := NULL;
            END;
        END IF;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          -- Si no encuentra registro
          vsClasifAnter    := NULL;
          vsSubClasifAnter := NULL;

          /* loop:
           Valida si lï¿½der existe en el Histï¿½rico de responsables en cada campaï¿½a,
           desde campaï¿½a de proceso -18 hasta campaï¿½a anterior a la de Proceso.
          */
          vnAuxCamp  := vsCampInicial;
          vnMiniCamp := NULL;

          WHILE vnAuxCamp < psCampanaProceso OR vnIndExiste = 0 LOOP
            SELECT COUNT(*)
              INTO lnCantRegistros
              FROM ZON_HISTO_GEREN
             WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vnAuxCamp) >=
                   PERD_OID_PERI_DESD
               AND (PERD_OID_PERI_HAST IS NULL OR GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vnAuxCamp) <=
                    PERD_OID_PERI_HAST)
               AND GERE = psCodigoLider
               AND COD_SECC IS NOT NULL
               AND PAIS_OID_PAIS = vnOidPais
               AND MARC_OID_MARC = vnOidMarca
               AND CANA_OID_CANA = vnOidCanal;

            -- Si encuentra registro
            IF lnCantRegistros > 0 THEN
              vnIndExiste := 1;
              vnMiniCamp  := vnAuxCamp;
              EXIT;
            ELSE
              ---   EXIT;
              vnAuxCamp := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            vnAuxCamp,
                                                            vnContador);
            END IF;
          END LOOP;

          -- Finaliza el loop, verifica si encontro registro.
          IF vnIndExiste = 0 OR vnMiniCamp = psCampanaProceso THEN
            -- No encuentro registro
            vsClasif    := '01';
            vsSubClasif := '01';
          ELSE
            -- Si encuentra registro
            vnDiferCamp := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO_PAIS(psCodigoPais,
                                                                       vnMiniCamp,
                                                                       psCampanaProceso) + 1;
            IF  vnDiferCamp <= vnNroCampNue   THEN
                BEGIN
                      -- Si encuentra registro
                      SELECT lccl_cod_clas, cod_subc
                        INTO vsClasif, vsSubClasif
                        FROM LEC_subcl -- SUB CLASIFICACION
                       WHERE num_camp = vnDiferCamp
                       AND   lccl_cod_clas = '01'
                       AND    IND_ACTI = 1;
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                        -- Si no encuentra registro
                          vsClasif    := null;
                          vsSubClasif := null;
                END;
             ELSE
                 vsClasif    := '03';
                 vsSubClasif := '01';    
                --- Obtengo su clasificación en la mínima campaña obtenida
             END IF;
          END IF;
      END;

      -- Si cumple, se cierra Clasificaciï¿½n Actual
      IF vnIndExisteAnterior = '1' AND
         (NVL(vsClasifAnter, '00') <> vsClasif OR
         NVL(vsSubClasifAnter, '00') <> vsSubClasif) THEN
        UPDATE LEC_LIDER_CLASI
           SET cam_fin  = vsCampAnterior,
               usu_modi = psCodigoUsuario,
               fec_modi = SYSDATE
         WHERE cod_lide = psCodigoLider
           AND cam_fin IS NULL;
      END IF;

      -- Si cumple, se crea Clasificaciï¿½n lider
      IF vsClasif IS NOT NULL AND
         (NVL(vsClasifAnter, '00') <> vsClasif OR
         NVL(vsSubClasifAnter, '00') <> vsSubClasif) THEN
        INSERT INTO LEC_LIDER_CLASI
          (LCCL_COD_CLAS,
           LSCL_COD_SUBC,
           COD_LIDE,
           CAM_INIC,
           CAM_FIN,
           IND_ORIG_CLAS,
           USU_CREA,
           FEC_CREA,
           IND_ACTI)
        VALUES
          (vsClasif,
           vsSubClasif,
           psCodigoLider,
           psCampanaProceso,
           decode(vnSgteNiv,1,psCampanaProceso,null), 
           'C',
           psCodigoUsuario,
           SYSDATE,
           '1');
      END IF;

      IF (vsClasifAnter = vsClasif AND vsSubClasifAnter = vsSubClasif AND
         vsCampanaFin = vsCampAnterior) THEN

        UPDATE LEC_LIDER_CLASI
           SET cam_fin  = NULL,
               usu_modi = psCodigoUsuario,
               fec_modi = SYSDATE
         WHERE cod_lide = psCodigoLider
           AND vsCampAnterior = cam_fin;
      END IF;
      IF  vnSgteNiv2 = '1' THEN
           UPDATE LEC_LIDER_CLASI
           SET cam_fin  = psCampanaProceso,
             usu_modi = psCodigoUsuario,
             fec_modi = SYSDATE
           WHERE cod_lide = psCodigoLider
           AND cam_fin  IS NULL
           AND cam_inic < psCampanaProceso;
      END IF;

    ELSIF psTipoProceso = '2' THEN

      UPDATE LEC_LIDER_CLASI
         SET cam_fin  = psCampanaProceso,
             usu_modi = psCodigoUsuario,
             fec_modi = SYSDATE
       WHERE cod_lide = psCodigoLider
         AND psCampanaProceso >= cam_inic
         AND (psCampanaProceso <= cam_fin OR cam_fin IS NULL);

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ACTUA_CLASI_LIDER: ' ||
                              ls_sqlerrm);

  END LEC_PR_ACTUA_CLASI_LIDER;

  /***********************************************************************************************
  Descripcion       : Permite calcular los objetivos para obtener Bonos.
  Fecha Creacion    : 27/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_BONO(psCodigoPais     VARCHAR2,
                                    psCodigoMarca    VARCHAR2,
                                    psCodigoCanal    VARCHAR2,
                                    psCampanaProceso VARCHAR2,
                                    psCodigoUsuario  VARCHAR2) IS

    vsCodProg     LEC_PROGR.COD_PROG%TYPE;
    vsCodTipoBono LEC_PROGR_BONO.LTBO_COD_TIPO_BONO%TYPE;

    ----------------------------------------------------

    CURSOR cGetTipoBonos(codProg LEC_PROGR.COD_PROG%TYPE) IS
      SELECT LTBO_COD_TIPO_BONO
        INTO vsCodTipoBono
        FROM LEC_PROGR_BONO
       WHERE IND_ACTI = '1'
         AND PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = codProg;

  BEGIN

    SELECT COD_PROG
      INTO vsCodProg
      FROM LEC_PROGR
     WHERE psCampanaProceso >= CAM_INIC
       AND (CAM_FIN is null OR psCampanaProceso <= CAM_FIN)
       AND IND_ACTI = '1'
       AND PAIS_COD_PAIS = psCodigoPais;

    ----------------------------------------------------------
    OPEN cGetTipoBonos(vsCodProg);
    LOOP
      FETCH cGetTipoBonos
        INTO vsCodTipoBono;
      EXIT WHEN cGetTipoBonos%NOTFOUND;

      IF vsCodTipoBono = '01' OR vsCodTipoBono = '02' OR
         vsCodTipoBono = '03' OR vsCodTipoBono = '04' THEN

        LEC_PR_CALCU_OBJET_LANZM(psCodigoPais,
                                 psCodigoMarca,
                                 psCodigoCanal,
                                 vsCodProg,
                                 vsCodTipoBono,
                                 psCampanaProceso,
                                 psCodigoUsuario);

        -- Eliminado CMM 04.03.15 No aplica para el nuevo esquema bonos ccvv.

        /*ELSIF vsCodTipoBono='05' OR vsCodTipoBono='06' OR vsCodTipoBono='07' THEN

        LEC_PR_CALCU_OBJET_CICLO_VIDA(psCodigoPais,
            psCodigoMarca,
            psCodigoCanal,
            vsCodTipoBono,
            psCampanaProceso,
            psCodigoUsuario);*/

      END IF;

    END LOOP;
    CLOSE cGetTipoBonos;
    -----------------------------------------------------------
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_OBJET_BONO: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_OBJET_BONO;

  /***********************************************************************************************
  Descripcion       : Permite Calcular los objetivos para obtener Bonos por Lanzamiento Estratï¿½gico.
  Fecha Creacion    : 27/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_LANZM(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psTipoBono       VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS

    vsCampLanz         LEC_PROGR_BONO_CAMPA.CAM_LANZ%TYPE;
    vsCodProg          LEC_PROGR.COD_PROG%TYPE;
    vsCodRegi          LEC_LIDER_SECCI_OBJET_BONO.COD_REGI%TYPE;
    vsCodZona          LEC_LIDER_SECCI_OBJET_BONO.COD_ZONA%TYPE;
    vsCodSecc          LEC_LIDER_SECCI_OBJET_BONO.COD_SECC%TYPE;
    vnPorcObje         LEC_PROGR_BONO_NIVEL.Val_Porc_Obje%TYPE;
    vnObjFina          LEC_LIDER_SECCI_OBJET_PEDID.NUM_PEDI_OBJE_FINA%TYPE;
    vnObjBono          LEC_LIDER_SECCI_OBJET_BONO.VAL_OBJE_BONO%type;
    vnCodigoNivel      LEC_PROGR_BONO_NIVEL.LNIV_COD_NIVE%TYPE;
    vnSecuBonoNivel    LEC_PROGR_BONO_NIVEL.SEC_BONO_NIVE%TYPE;
    vnNumLanz          LEC_PROGR_BONO_LANZA.Num_Lanz%TYPE;
    vnSobreObjBono     LEC_LIDER_SECCI_OBJET_BONO.Val_Sobr_Obje_Bono%type;
    vnPorcSobreObjBono LEC_PROGR_BONO_NIVEL.Val_Porc_Sobr_Obje%TYPE;
    vnExitReg          varchar(1);
    vnCodTipoMedi      LEC_PROGR_BONO_LANZA.LTME_COD_TIPO_MEDI%TYPE;

    vsIndObjInfo LEC_LIDER_SECCI_OBJET_BONO.IND_OBJE_INFO%TYPE := '0';
    vsLider      ZON_HISTO_GEREN.Gere%TYPE;
    lnCantidad   NUMBER(2);

    -------------------------------------------------------------------------

    CURSOR cGetLanza IS
      select cam_lanz
        from LEC_PROGR_BONO_CAMPA
       where cam_lanz >= psCampanaProceso
         AND cam_lanz <= gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                          psCampanaProceso,
                                                          1)
         AND IND_ACTI = '1'
         AND PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma;

    TYPE interfazLanza IS RECORD(
      campLanza LEC_PROGR_BONO_CAMPA.CAM_LANZ%TYPE);

    TYPE interfazLanzaTab IS TABLE OF interfazLanza;
    interfazLanzaRecord interfazLanzaTab;
    --------------------------------------------------------------------------
    CURSOR cGetProgrLanza(vCampLanz LEC_PROGR_BONO_LANZA.LPBC_CAM_LANZ%TYPE) IS
      SELECT NUM_LANZ, LTME_COD_TIPO_MEDI
        FROM LEC_PROGR_BONO_LANZA
       WHERE LPRO_COD_PROG = psCodigoPrograma
         AND LPBC_CAM_LANZ = vCampLanz
         AND PAIS_COD_PAIS = psCodigoPais;

    TYPE interfazProgrLanza IS RECORD(
      numProgrLanza LEC_PROGR_BONO_LANZA.NUM_LANZ%TYPE,
      codTipoMedi   LEC_PROGR_BONO_LANZA.LTME_COD_TIPO_MEDI%TYPE);

    TYPE interfazProgrLanzaTab IS TABLE OF interfazProgrLanza;
    interfazProgrLanzaRecord interfazProgrLanzaTab;

    ---------------------------------------------------------------------------
    CURSOR cGetBonoNivel(vCampLanz LEC_PROGR_BONO_NIVEL.LPBC_CAM_LANZ%TYPE,
                         vNumLanz  LEC_PROGR_BONO_NIVEL.LPBL_NUM_LANZ%TYPE) IS
      SELECT VAL_PORC_OBJE,
             LNIV_COD_NIVE,
             SEC_BONO_NIVE,
             VAL_PORC_SOBR_OBJE
        FROM LEC_PROGR_BONO_NIVEL
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LTBO_COD_TIPO_BONO = psTipoBono
         AND LPRO_COD_PROG = psCodigoPrograma
         AND LPBC_CAM_LANZ = vCampLanz
         AND LPBL_NUM_LANZ = vNumLanz;

    TYPE interfazBonoNivel IS RECORD(
      valPorcObje     LEC_PROGR_BONO_NIVEL.VAL_PORC_OBJE%TYPE,
      codigoNivel     LEC_PROGR_BONO_NIVEL.LNIV_COD_NIVE%TYPE,
      secBonoNivel    LEC_PROGR_BONO_NIVEL.SEC_BONO_NIVE%TYPE,
      valPorcSobrObje LEC_PROGR_BONO_NIVEL.VAL_PORC_SOBR_OBJE%TYPE);

    TYPE interfazBonoNivelTab IS TABLE OF interfazBonoNivel;
    interfazBonoNivelRecord interfazBonoNivelTab;

    --------------------------------------------------------------------
    CURSOR cGetObjetPedid(vCampPedid LEC_PROGR_BONO_CAMPA.CAM_LANZ%TYPE) IS
      select COD_REGI, COD_ZONA, COD_SECC, NUM_PEDI_OBJE_FINA
        from LEC_LIDER_SECCI_OBJET_PEDID
       WHERE LPRO_COD_PROG = psCodigoPrograma
         AND LPRO_COD_PAIS = psCodigoPais
         AND CAM_OBJE = vCampPedid;

    TYPE interfazObjetPedid IS RECORD(
      codigoRegion    LEC_LIDER_SECCI_OBJET_PEDID.COD_REGI%TYPE,
      codigoZona      LEC_LIDER_SECCI_OBJET_PEDID.COD_ZONA%TYPE,
      codigoSeccion   LEC_LIDER_SECCI_OBJET_PEDID.COD_SECC%TYPE,
      numPedidObjFina LEC_LIDER_SECCI_OBJET_PEDID.NUM_PEDI_OBJE_FINA%TYPE);

    TYPE interfazObjetPedidTab IS TABLE OF interfazObjetPedid;
    interfazObjetPedidRecord interfazObjetPedidTab;

  BEGIN

    OPEN cGetLanza;
    LOOP
      FETCH cGetLanza BULK COLLECT
        INTO interfazLanzaRecord LIMIT W_FILAS;
      IF interfazLanzaRecord.COUNT > 0 THEN

        FOR i IN interfazLanzaRecord.FIRST .. interfazLanzaRecord.LAST LOOP
          vsCampLanz := interfazLanzaRecord(i).campLanza;

          SELECT COUNT(1)
            INTO lnCantidad
            FROM LEC_PROGR_BONO_NIVEL
           WHERE LPRO_COD_PROG = psCodigoPrograma
             AND LPBC_CAM_LANZ = vsCampLanz
             AND VAL_PORC_OBJE > 0;

          IF lnCantidad <> 0 THEN
            -- Eliminar de la tabla Objetivos para Bonos
            DELETE FROM LEC_LIDER_SECCI_OBJET_BONO
             WHERE LPRO_COD_PROG = psCodigoPrograma
               AND CAM_OBJE = vsCampLanz
               AND LTBO_COD_TIPO_BONO = psTipoBono;

            OPEN cGetProgrLanza(vsCampLanz);
            LOOP
              FETCH cGetProgrLanza BULK COLLECT
                INTO interfazProgrLanzaRecord LIMIT W_FILAS;
              IF interfazProgrLanzaRecord.COUNT > 0 THEN
                FOR q IN interfazProgrLanzaRecord.FIRST .. interfazProgrLanzaRecord.LAST LOOP
                  vnNumLanz     := interfazProgrLanzaRecord(q).numProgrLanza;
                  vnCodTipoMedi := interfazProgrLanzaRecord(q).codTipoMedi;
                  ----------------------------------------------------------------------------
                  OPEN cGetBonoNivel(vsCampLanz, vnNumLanz);
                  LOOP
                    FETCH cGetBonoNivel BULK COLLECT
                      INTO interfazBonoNivelRecord LIMIT W_FILAS;
                    IF interfazBonoNivelRecord.COUNT > 0 THEN

                      FOR j IN interfazBonoNivelRecord.FIRST .. interfazBonoNivelRecord.LAST LOOP
                        vnPorcObje         := interfazBonoNivelRecord(j)
                                              .valPorcObje;
                        vnCodigoNivel      := interfazBonoNivelRecord(j)
                                              .codigoNivel;
                        vnSecuBonoNivel    := interfazBonoNivelRecord(j)
                                              .secBonoNivel;
                        vnPorcSobreObjBono := interfazBonoNivelRecord(j)
                                              .valPorcSobrObje;
                        -----------------------------------------------------------------
                        OPEN cGetObjetPedid(vsCampLanz);
                        LOOP
                          FETCH cGetObjetPedid BULK COLLECT
                            INTO interfazObjetPedidRecord LIMIT W_FILAS;
                          IF interfazObjetPedidRecord.COUNT > 0 THEN
                            FOR z IN interfazObjetPedidRecord.FIRST .. interfazObjetPedidRecord.LAST LOOP
                              vsCodSecc := interfazObjetPedidRecord(z)
                                           .codigoSeccion;
                              vsCodRegi := interfazObjetPedidRecord(z)
                                           .codigoRegion;
                              vsCodZona := interfazObjetPedidRecord(z)
                                           .codigoZona;
                              vnObjFina := interfazObjetPedidRecord(z)
                                           .numPedidObjFina;

                              BEGIN
                                SELECT GERE
                                  into vsLider
                                  FROM ZON_HISTO_GEREN
                                 WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) >=
                                       PERD_OID_PERI_DESD
                                   and (PERD_OID_PERI_HAST IS NULL OR
                                        GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) <=
                                        PERD_OID_PERI_HAST)
                                   AND COD_SECC = vsCodSecc
                                   AND COD_REGI = vsCodRegi
                                   AND COD_ZONA = vsCodZona
                                   AND PAIS_OID_PAIS =
                                       GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais)
                                   AND MARC_OID_MARC =
                                       GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca)
                                   AND CANA_OID_CANA =
                                       GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal);

                                SELECT DECODE(COUNT(*), 0, 'F', 'T')
                                  INTO vnExitReg
                                  FROM LEC_LIDER_NIVEL LLN
                                 WHERE LLN.PAIS_COD_PAIS = psCodigoPais
                                   AND LLN.LPRO_COD_PROG = psCodigoPrograma
                                   AND LLN.COD_LIDE = vsLider
                                   AND LLN.LNIV_COD_NIVE = vnCodigoNivel
                                   AND LLN.IND_TIPO_NIVE = 'R'
                                   AND psCampanaProceso >= LLN.CAM_INIC
                                   AND (psCampanaProceso <= LLN.CAM_FIN OR
                                       LLN.CAM_FIN IS NULL);

                                IF vnExitReg = 'T' THEN
                                  vsIndObjInfo := '1';
                                ELSE
                                  vsIndObjInfo := '0';
                                END IF;

                              EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                  vsIndObjInfo := '0';
                              END;

                              IF vnCodTipoMedi = '01' THEN
                                vnObjBono := ROUND(NVL(vnObjFina, 0) *
                                                   (NVL(vnPorcObje, 0) / 100));
                              ELSE
                                vnObjBono := ROUND(NVL(vnObjFina, 0) *
                                                   NVL(vnPorcObje, 0));
                              END IF;

                              vnSobreObjBono := NULL; -- ROUND(NVL(vnObjFina,0) * ( NVL(vnPorcSobreObjBono,0) / 100 ));

                              INSERT INTO LEC_LIDER_SECCI_OBJET_BONO
                                (PAIS_COD_PAIS,
                                 LPRO_COD_PROG,
                                 COD_REGI,
                                 COD_ZONA,
                                 COD_SECC,
                                 CAM_OBJE,
                                 LTBO_COD_TIPO_BONO,
                                 LNIV_COD_NIVE,
                                 LPBN_SEC_BONO_NIVE,
                                 VAL_OBJE_BONO,
                                 VAL_SOBR_OBJE_BONO,
                                 IND_ORIG_CALC,
                                 IND_OBJE_INFO,
                                 USU_CREA,
                                 FEC_CREA,
                                 IND_ACTI)
                              VALUES
                                (psCodigoPais,
                                 psCodigoPrograma,
                                 vsCodRegi,
                                 vsCodZona,
                                 vsCodSecc,
                                 vsCampLanz,
                                 psTipoBono,
                                 vnCodigoNivel,
                                 vnSecuBonoNivel,
                                 vnObjBono,
                                 vnSobreObjBono,
                                 'C',
                                 vsIndObjInfo,
                                 psCodigoUsuario,
                                 SYSDATE,
                                 '1');

                            END LOOP;
                          END IF;
                          EXIT WHEN cGetObjetPedid%NOTFOUND;
                        END LOOP;
                        CLOSE cGetObjetPedid;
                        ------------------------------------------------------
                      END LOOP;
                    END IF;
                    EXIT WHEN cGetBonoNivel%NOTFOUND;
                  END LOOP;
                  CLOSE cGetBonoNivel;
                  -------------------------------------------------------
                END LOOP;
              END IF;
              EXIT WHEN cGetProgrLanza%NOTFOUND;
            END LOOP;
            CLOSE cGetProgrLanza;
          END IF;
          -------------------------------------------------------
        END LOOP;
      END IF;
      EXIT WHEN cGetLanza%NOTFOUND;
    END LOOP;
    CLOSE cGetLanza;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_OBJET_LANZM: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_OBJET_LANZM;

  /***********************************************************************************************
  Descripcion       : Permite realizar el cï¿½lculo de objetivos de ciclo de vida 2/2, 3/3 y 4/4.
  Fecha Creacion    : 29/01/2014
  Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_CALCU_OBJET_CICLO_VIDA(psCodigoPais     VARCHAR2,
                                          psCodigoMarca    VARCHAR2,
                                          psCodigoCanal    VARCHAR2,
                                          psTipoBono       VARCHAR2,
                                          psCampanaProceso VARCHAR2,
                                          psCodigoUsuario  VARCHAR2) IS

    vsCampanaObjetivo LEC_PROGR_BONO_CAMPA.CAM_LANZ%TYPE;
    vsCampanaIngreso  LEC_PROGR_BONO_CAMPA.CAM_LANZ%TYPE;
    vsCodidgoPrograma LEC_PROGR.COD_PROG%TYPE;

    vsCodRegi               ZON_REGIO.COD_REGI%TYPE;
    vsCodZona               ZON_ZONA.COD_ZONA%TYPE;
    vsCodSecc               ZON_SECCI.COD_SECC%TYPE;
    vnNumeroIngresosSeccion NUMBER(12);
    vnOidCampanaProceso     CRA_PERIO.OID_PERI%TYPE;
    vnOidCampanaIngreso     CRA_PERIO.OID_PERI%TYPE;
    vnMinimIngreso          LEC_PROGR_BONO_NIVEL.NUM_MINI_INGR%TYPE;
    vnMaximIngreso          LEC_PROGR_BONO_NIVEL.NUM_MAXI_INGR%TYPE;
    vnMayorIngreso          LEC_PROGR_BONO_NIVEL.NUM_MINI_INGR%TYPE;
    vnPorcRete              LEC_PROGR_BONO_NIVEL.VAL_PORC_RETE%TYPE;
    lnObjetivo              LEC_LIDER_SECCI_OBJET_BONO.VAL_OBJE_BONO%TYPE;
    vnObjeBono              LEC_LIDER_SECCI_OBJET_BONO.VAL_OBJE_BONO%TYPE;
    vnCodigoNivel           LEC_PROGR_BONO_NIVEL.LNIV_COD_NIVE%TYPE;
    vnSecuBonoNivel         LEC_PROGR_BONO_NIVEL.SEC_BONO_NIVE%TYPE;
    vnOrigCalc              LEC_LIDER_SECCI_OBJET_BONO.IND_ORIG_CALC%TYPE;
    vnExitReg               VARCHAR(1);
    vCountLoop              number := 0;

    vsIndObjInfo     LEC_LIDER_SECCI_OBJET_BONO.IND_OBJE_INFO%TYPE := '0';
    vsLider          ZON_HISTO_GEREN.Gere%TYPE;
    vnExitNivelLider VARCHAR(1);
    vnExitNl         VARCHAR(1);
    -------------------------------------------------------------------------
    -- Se Obtiene el universo de las consultoras
    CURSOR cUniversoConsultoras(oidCampanaProceso CRA_PERIO.OID_PERI%TYPE,
                                oidCampanaIngreso CRA_PERIO.OID_PERI%TYPE,
                                codigoPrograma    LEC_PROGR.COD_PROG%TYPE) IS

      SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC, COUNT(1)
        FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
             ZON_TERRI_ADMIN       ZTA,
             MAE_CLIEN_HISTO_ESTAT MCHE,
             MAE_ESTAT_CLIEN       MEC,
             ZON_SECCI             ZS,
             ZON_ZONA              ZZ,
             ZON_REGIO             ZR
       WHERE MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
         AND MCUA.CLIE_OID_CLIE = MCHE.CLIE_OID_CLIE
         AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
         AND ZS.ZZON_OID_ZONA = ZZ.OID_ZONA
         AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
         AND MCUA.PERD_OID_PERI_INI <= oidCampanaProceso
         AND (MCUA.PERD_OID_PERI_FIN >= oidCampanaProceso OR
             MCUA.PERD_OID_PERI_FIN IS NULL)
         AND MCHE.PERD_OID_PERI = oidCampanaIngreso
         AND MCHE.ESTA_OID_ESTA_CLIE = MEC.OID_ESTA_CLIE
         AND MEC.COD_ESTA_CLIE IN ('02', '08')
         AND 0 =
             LEC_FN_OBTE_CONS_EXCL(psCodigoPais,
                                   psCampanaProceso,
                                   codigoPrograma,
                                   GEN_PKG_GENER.GEN_FN_DEVUELVE_COD_CLIE(MCUA.CLIE_OID_CLIE))

       GROUP BY ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC;

    CURSOR cGetBonosNivel IS
      SELECT lpbn.num_mini_ingr,
             lpbn.num_maxi_ingr,
             lpbn.val_porc_rete,
             lpbn.lniv_cod_nive,
             lpbn.sec_bono_nive
        FROM lec_progr_bono_nivel lpbn
       WHERE lpbn.lpro_cod_prog = vsCodidgoPrograma
         AND lpbn.pais_cod_pais = psCodigoPais
         AND lpbn.ltbo_cod_tipo_bono = psTipoBono;

    --Cursor para universo de secciones
    CURSOR cUniversoSecciones(oidCampanaProceso CRA_PERIO.OID_PERI%TYPE) IS
      SELECT ZR.COD_REGI, ZZ.COD_ZONA, ZS.COD_SECC
        FROM ZON_SECCI ZS, ZON_ZONA ZZ, ZON_REGIO ZR
       WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
         AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
         AND ZR.PERD_OID_PERI_INIC <= oidCampanaProceso
         AND (ZR.PERD_OID_PERI_FINA >= oidCampanaProceso OR
             ZR.PERD_OID_PERI_FINA IS NULL)
         AND ZZ.PERD_OID_PERI_INIC <= oidCampanaProceso
         AND (ZZ.PERD_OID_PERI_FINA >= oidCampanaProceso OR
             ZZ.PERD_OID_PERI_FINA IS NULL)
         AND ZS.PERD_OID_PERI_INIC <= oidCampanaProceso
         AND (ZS.PERD_OID_PERI_FINA >= oidCampanaProceso OR
             ZS.PERD_OID_PERI_FINA IS NULL);

  BEGIN

    BEGIN

      vsCampanaObjetivo := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCampanaProceso,
                                                            1);

      SELECT COD_PROG
        INTO vsCodidgoPrograma
        FROM LEC_PROGR
       WHERE vsCampanaObjetivo >= CAM_INIC
         AND (CAM_FIN is null OR vsCampanaObjetivo <= CAM_FIN)
         AND IND_ACTI = '1'
         AND PAIS_COD_PAIS = psCodigoPais;

      vnExitReg  := 'T'; --true
      vCountLoop := 2;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        --No encuentra registro
        --Calcular Objetivos para la campaï¿½a Actual

        vsCampanaObjetivo := psCampanaProceso;

        BEGIN
          SELECT COD_PROG
            INTO vsCodidgoPrograma
            FROM LEC_PROGR
           WHERE vsCampanaObjetivo >= CAM_INIC
             AND (CAM_FIN is null OR vsCampanaObjetivo <= CAM_FIN)
             AND IND_ACTI = '1'
             AND PAIS_COD_PAIS = psCodigoPais;

          vnExitReg  := 'F';
          vCountLoop := 1;

        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodidgoPrograma := NULL;
        END;
    END;

    IF vsCodidgoPrograma IS NOT NULL THEN
      FOR i IN 1 .. vCountLoop LOOP

        IF i = 2 THEN
          --Calculo Objetivos para la campaï¿½a actual.
          vsCampanaObjetivo := psCampanaProceso;

          BEGIN
            SELECT COD_PROG
              INTO vsCodidgoPrograma
              FROM LEC_PROGR
             WHERE vsCampanaObjetivo >= CAM_INIC
               AND (CAM_FIN is null OR vsCampanaObjetivo <= CAM_FIN)
               AND IND_ACTI = '1'
               AND PAIS_COD_PAIS = psCodigoPais;

            vnExitReg := 'F';
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsCodidgoPrograma := NULL;
          END;
        END IF;

        IF vsCodidgoPrograma IS NOT NULL THEN
          --Eliminamos registros
          DELETE FROM LEC_LIDER_SECCI_OBJET_BONO
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = vsCodidgoPrograma
             AND LTBO_COD_TIPO_BONO = psTipoBono
             AND CAM_OBJE = vsCampanaObjetivo
             AND UPPER(IND_ORIG_CALC) = 'C';

          IF psTipoBono = '05' THEN
            --2/2    T = Existe Registro | F = No existe Resigro
            vsCampanaIngreso := CASE
                                  WHEN vnExitReg = 'T' THEN
                                   psCampanaProceso
                                  ELSE
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais, psCampanaProceso, -1)
                                END;
          ELSIF psTipoBono = '06' THEN
            --3/3
            vsCampanaIngreso := CASE
                                  WHEN vnExitReg = 'T' THEN
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais, psCampanaProceso, -1)
                                  ELSE
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais, psCampanaProceso, -2)
                                END;
          ELSIF psTipoBono = '07' THEN
            --4/4
            vsCampanaIngreso := CASE
                                  WHEN vnExitReg = 'T' THEN
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais, psCampanaProceso, -2)
                                  ELSE
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais, psCampanaProceso, -3)
                                END;
          END IF;

          -- INICIO : Calcular Objetivos Retenciï¿½n.
          vnOidCampanaProceso := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanaProceso);
          vnOidCampanaIngreso := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(vsCampanaIngreso);

          OPEN cUniversoConsultoras(vnOidCampanaProceso,
                                    vnOidCampanaIngreso,
                                    vsCodidgoPrograma);

          LOOP
            FETCH cUniversoConsultoras
              INTO vsCodRegi, vsCodZona, vsCodSecc, vnNumeroIngresosSeccion;
            EXIT WHEN cUniversoConsultoras%NOTFOUND;

            BEGIN
              SELECT GERE
                Into vsLider
                FROM ZON_HISTO_GEREN
               WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) >=
                     PERD_OID_PERI_DESD
                 and (PERD_OID_PERI_HAST IS NULL OR GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) <=
                      PERD_OID_PERI_HAST)
                 AND COD_SECC = vsCodSecc
                 AND COD_REGI = vsCodRegi
                 AND COD_ZONA = vsCodZona
                 AND PAIS_OID_PAIS =
                     GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais)
                 AND MARC_OID_MARC =
                     GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca)
                 AND CANA_OID_CANA =
                     GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal);

              vnExitNivelLider := 'T';
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsIndObjInfo     := '0';
                vnExitNivelLider := 'F';
            END;

            OPEN cGetBonosNivel;
            LOOP
              FETCH cGetBonosNivel
                INTO vnMinimIngreso,
                     vnMaximIngreso,
                     vnPorcRete,
                     vnCodigoNivel,
                     vnSecuBonoNivel;
              EXIT WHEN cGetBonosNivel%NOTFOUND;

              IF vnExitNivelLider = 'T' THEN
                SELECT DECODE(COUNT(*), 0, 'F', 'T')
                  INTO vnExitNl
                  FROM LEC_LIDER_NIVEL LLN
                 WHERE LLN.PAIS_COD_PAIS = psCodigoPais
                   AND LLN.LPRO_COD_PROG = vsCodidgoPrograma
                   AND LLN.COD_LIDE = vsLider
                   AND LLN.LNIV_COD_NIVE = vnCodigoNivel
                   AND LLN.IND_TIPO_NIVE = 'R'
                   AND psCampanaProceso >= LLN.CAM_INIC
                   AND (psCampanaProceso <= LLN.CAM_FIN OR
                       LLN.CAM_FIN IS NULL);

                IF vnExitNl = 'T' THEN
                  vsIndObjInfo := '1';
                ELSE
                  vsIndObjInfo := '0';
                END IF;
              END IF;

              --SE OBTIENE EL NUMERO MAYOR DE INGRESO
              IF vnNumeroIngresosSeccion > vnMinimIngreso THEN
                vnMayorIngreso := vnNumeroIngresosSeccion;
              ELSE
                vnMayorIngreso := vnMinimIngreso;
              END IF;

              lnObjetivo := NVL(vnMayorIngreso, 0) *
                            (NVL(vnPorcRete, 0) / 100);

              BEGIN

                select IND_ORIG_CALC
                  INTO vnOrigCalc
                  from LEC_LIDER_SECCI_OBJET_BONO
                 where lpro_cod_prog = vsCodidgoPrograma
                   and cod_regi = vsCodRegi
                   and cod_zona = vsCodZona
                   and cod_secc = vsCodSecc
                   and cam_obje = vsCampanaObjetivo
                   and ltbo_cod_tipo_bono = psTipoBono
                   and lniv_cod_nive = vnCodigoNivel
                   and lpbn_sec_bono_nive = vnSecuBonoNivel
                   AND pais_cod_pais = psCodigoPais;

                IF UPPER(vnOrigCalc) <> 'X' THEN

                  UPDATE LEC_LIDER_SECCI_OBJET_BONO
                     SET VAL_OBJE_BONO = lnObjetivo,
                         USU_MODI      = psCodigoUsuario,
                         FEC_CREA      = SYSDATE,
                         IND_OBJE_INFO = vsIndObjInfo
                   where lpro_cod_prog = vsCodidgoPrograma
                     and cod_regi = vsCodRegi
                     and cod_zona = vsCodZona
                     and cod_secc = vsCodSecc
                     and cam_obje = vsCampanaObjetivo
                     and ltbo_cod_tipo_bono = psTipoBono
                     and lniv_cod_nive = vnCodigoNivel
                     and lpbn_sec_bono_nive = vnSecuBonoNivel
                     AND pais_cod_pais = psCodigoPais;

                END IF;

              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  --No existe Registro

                  INSERT INTO LEC_LIDER_SECCI_OBJET_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     CAM_OBJE,
                     LTBO_COD_TIPO_BONO,
                     LNIV_COD_NIVE,
                     LPBN_SEC_BONO_NIVE,
                     VAL_OBJE_BONO,
                     IND_ORIG_CALC,
                     IND_OBJE_INFO,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (psCodigoPais,
                     vsCodidgoPrograma,
                     vsCodRegi,
                     vsCodZona,
                     vsCodSecc,
                     vsCampanaObjetivo,
                     psTipoBono,
                     vnCodigoNivel,
                     vnSecuBonoNivel,
                     lnObjetivo,
                     'C',
                     vsIndObjInfo,
                     psCodigoUsuario,
                     SYSDATE,
                     '1');
              END;

            END LOOP;
            CLOSE cGetBonosNivel;
          END LOOP;
          CLOSE cUniversoConsultoras;
          -- FIN : Calcular Objetivos Retenciï¿½n.

          OPEN cUniversoSecciones(vnOidCampanaProceso);

          LOOP
            FETCH cUniversoSecciones
              INTO vsCodRegi, vsCodZona, vsCodSecc;
            EXIT WHEN cUniversoSecciones%NOTFOUND;

            BEGIN
              SELECT GERE
                Into vsLider
                FROM ZON_HISTO_GEREN
               WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) >=
                     PERD_OID_PERI_DESD
                 and (PERD_OID_PERI_HAST IS NULL OR GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) <=
                      PERD_OID_PERI_HAST)
                 AND COD_SECC = vsCodSecc
                 AND COD_REGI = vsCodRegi
                 AND COD_ZONA = vsCodZona
                 AND PAIS_OID_PAIS =
                     GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais)
                 AND MARC_OID_MARC =
                     GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca)
                 AND CANA_OID_CANA =
                     GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal);

              vnExitNivelLider := 'T';
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsIndObjInfo     := '0';
                vnExitNivelLider := 'F';
            END;

            OPEN cGetBonosNivel;
            LOOP
              FETCH cGetBonosNivel
                INTO vnMinimIngreso,
                     vnMaximIngreso,
                     vnPorcRete,
                     vnCodigoNivel,
                     vnSecuBonoNivel;
              EXIT WHEN cGetBonosNivel%NOTFOUND;

              IF vnExitNivelLider = 'T' THEN
                SELECT DECODE(COUNT(*), 0, 'F', 'T')
                  INTO vnExitNl
                  FROM LEC_LIDER_NIVEL LLN
                 WHERE LLN.PAIS_COD_PAIS = psCodigoPais
                   AND LLN.LPRO_COD_PROG = vsCodidgoPrograma
                   AND LLN.COD_LIDE = vsLider
                   AND LLN.LNIV_COD_NIVE = vnCodigoNivel
                   AND LLN.IND_TIPO_NIVE = 'R'
                   AND psCampanaProceso >= LLN.CAM_INIC
                   AND (psCampanaProceso <= LLN.CAM_FIN OR
                       LLN.CAM_FIN IS NULL);

                IF vnExitNl = 'T' THEN
                  vsIndObjInfo := '1';
                ELSE
                  vsIndObjInfo := '0';
                END IF;
              END IF;

              lnObjetivo := NVL(vnMinimIngreso, 0) *
                            (NVL(vnPorcRete, 0) / 100);

              BEGIN

                select VAL_OBJE_BONO
                  INTO vnObjeBono
                  from LEC_LIDER_SECCI_OBJET_BONO
                 where cod_regi = vsCodRegi
                   and lpro_cod_prog = vsCodidgoPrograma
                   and cod_zona = vsCodZona
                   and cod_secc = vsCodSecc
                   and cam_obje = vsCampanaObjetivo
                   and ltbo_cod_tipo_bono = psTipoBono
                   and lniv_cod_nive = vnCodigoNivel
                   and lpbn_sec_bono_nive = vnSecuBonoNivel
                   AND pais_cod_pais = psCodigoPais;

                IF NVL(vnObjeBono, 0) = 0 THEN

                  UPDATE LEC_LIDER_SECCI_OBJET_BONO
                     SET VAL_OBJE_BONO = lnObjetivo,
                         USU_MODI      = psCodigoUsuario,
                         FEC_CREA      = SYSDATE,
                         IND_OBJE_INFO = vsIndObjInfo
                   where lpro_cod_prog = vsCodidgoPrograma
                     and cod_regi = vsCodRegi
                     and cod_zona = vsCodZona
                     and cod_secc = vsCodSecc
                     and cam_obje = vsCampanaObjetivo
                     and ltbo_cod_tipo_bono = psTipoBono
                     and lniv_cod_nive = vnCodigoNivel
                     and lpbn_sec_bono_nive = vnSecuBonoNivel
                     AND pais_cod_pais = psCodigoPais;

                END IF;

              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  --No existe Registro

                  INSERT INTO LEC_LIDER_SECCI_OBJET_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     CAM_OBJE,
                     LTBO_COD_TIPO_BONO,
                     LNIV_COD_NIVE,
                     LPBN_SEC_BONO_NIVE,
                     VAL_OBJE_BONO,
                     IND_ORIG_CALC,
                     IND_OBJE_INFO,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (psCodigoPais,
                     vsCodidgoPrograma,
                     vsCodRegi,
                     vsCodZona,
                     vsCodSecc,
                     vsCampanaObjetivo,
                     psTipoBono,
                     vnCodigoNivel,
                     vnSecuBonoNivel,
                     lnObjetivo,
                     'C',
                     vsIndObjInfo,
                     psCodigoUsuario,
                     SYSDATE,
                     '1');
              END;

            END LOOP;
            CLOSE cGetBonosNivel;

          END LOOP;
          CLOSE cUniversoSecciones;

        END IF;
      END LOOP;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_OBJET_CICLO_VIDA: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_OBJET_CICLO_VIDA;

  /***********************************************************************************************
   Descripcion       : Permite calcular masivamente el Nivel de ï¿½xito de las Lï¿½deres.
   Fecha Creacion    : 30/01/2014
   Autor             : Yahir Rivas L.
  ***********************************************************************************************/
 PROCEDURE LEC_PR_CALCU_NIVEL_EXITO(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psTipoProceso    VARCHAR2,
                                     psCodigoRegion   VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS

    CURSOR cGetLideres IS
      SELECT zhg.GERE,
             NVL((
                  SELECT lniv.ind_orig_calc
                    FROM lec_lider_nivel lniv
                   WHERE 1=1
                     AND lniv.pais_cod_pais = psCodigoPais
                     AND lniv.cod_lide = zhg.gere
                     AND lniv.cam_inic = psCampanaProceso
                     AND lniv.ind_tipo_nive = 'R'
                 ),'C') ind_orig_nive
        FROM ZON_HISTO_GEREN zhg
       WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) >=
             PERD_OID_PERI_DESD
         AND GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso) <=
             NVL(PERD_OID_PERI_HAST,
                 GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso))
         AND COD_SECC IS NOT NULL
         AND PAIS_OID_PAIS =
             GEN_PKG_GENER.gen_fn_devuelve_id_pais(psCodigoPais)
         AND MARC_OID_MARC =
             GEN_PKG_GENER.gen_fn_devuelve_id_marca(psCodigoMarca)
         AND CANA_OID_CANA =
             GEN_PKG_GENER.gen_fn_devuelve_id_canal(psCodigoCanal)
         AND (CASE
                WHEN psTipoProceso = 'R' THEN
                 COD_REGI
                ELSE
                 DECODE(psCodigoRegion, NULL, '1')
              END = DECODE(psCodigoRegion, NULL, '1', psCodigoRegion));

    TYPE interfazLideres IS RECORD(
      codigoLider ZON_HISTO_GEREN.Gere%TYPE,
      indOrigenNivel   LEC_LIDER_NIVEL.IND_ORIG_CALC%TYPE);

    TYPE interfazLideresTab IS TABLE OF interfazLideres;
    interfazLideresRecord interfazLideresTab;

    --Obtiene campanias anteriores.
    CURSOR cLideresPeriodoAnterior(oidPeriodo    CRA_PERIO.OID_PERI%TYPE,
                                   oidPeriodoAnt CRA_PERIO.OID_PERI%TYPE) IS
      SELECT ZHG.GERE
        FROM ZON_HISTO_GEREN ZHG
       WHERE oidPeriodoAnt >= ZHG.PERD_OID_PERI_DESD
         AND (oidPeriodoAnt <= ZHG.PERD_OID_PERI_HAST OR
             ZHG.PERD_OID_PERI_HAST is null)
         AND ZHG.COD_SECC IS NOT NULL
         AND ZHG.GERE NOT IN
             (SELECT AUX.GERE
                FROM ZON_HISTO_GEREN AUX
               WHERE oidPeriodo >= AUX.PERD_OID_PERI_DESD
                 AND (oidPeriodo <= AUX.PERD_OID_PERI_HAST or
                     AUX.PERD_OID_PERI_HAST is null)
                 AND AUX.COD_SECC IS NOT NULL)
         AND (CASE
               WHEN psTipoProceso = 'R' THEN
                ZHG.COD_REGI
               ELSE
                DECODE(psCodigoRegion, NULL, '1')
             END = DECODE(psCodigoRegion, NULL, '1', psCodigoRegion));

    TYPE interfazLideresAnt IS RECORD(
      codigoLider ZON_HISTO_GEREN.Gere%TYPE
      );

    TYPE interfazLideresAntTab IS TABLE OF interfazLideresAnt;
    interfazLideresAntRecord interfazLideresAntTab;

    lsCodigoLider ZON_HISTO_GEREN.Gere%TYPE;
    vsCodProg     LEC_PROGR.COD_PROG%TYPE;
    vsCampAnte    SEG_PERIO_CORPO.COD_PERI%TYPE;
    vsCampIni     LEC_PROGR.CAM_INIC%TYPE;
    lnFechaFact             DATE;
    lnExisteCampFact        NUMBER;
    vsCampCruce             seg_perio_corpo.cod_peri%type;
    vsMinCamp               seg_perio_corpo.cod_peri%type;
    lsIndCruce              varchar2(1);
    vsIndCruce              CRA_PERIO.Ind_Peri_Cruc%TYPE;
    
  BEGIN
    BEGIN
      SELECT fec_proc          
        INTO lnFechaFact
        FROM BAS_CTRL_FACT fact
       WHERE fact.cod_peri = psCampanaProceso
         /*AND STA_CAMP = '0'
         AND IND_CAMP_ACT = 1*/;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lnFechaFact := null;
    END;
    
    -- Borrar los Niveles reales y proyectados de las lideres en campaña actual siempre
    -- que no sean niveles que se cargaron por excel o manualmente.
    DELETE FROM LEC_LIDER_NIVEL a
     WHERE a.CAM_INIC = psCampanaProceso
       AND NVL( a.IND_ORIG_CALC, 'C' ) NOT IN ('X','M')
       ;

    BEGIN
       SELECT COD_PROG, CAM_INIC
        INTO vsCodProg, vsCampIni
        FROM LEC_PROGR
       WHERE psCampanaProceso BETWEEN CAM_INIC AND NVL(CAM_FIN,psCampanaProceso)
         AND IND_ACTI = '1'
         AND PAIS_COD_PAIS = psCodigoPais;
       
       -- Actualizar la campaña fin en NULL de los niveles reales de las lideres para
       -- aquellas cuya campaña fin sea la campaña anterior o la campaña actual, para
       -- el programa vigente.
       /*UPDATE LEC_LIDER_NIVEL a
         SET a.CAM_FIN = NULL
       WHERE ( a.CAM_FIN = GEN_FN_CALCU_PERIO(psCampanaProceso, -1) OR
               a.cam_fin = psCampanaProceso )
         AND a.lpro_cod_prog = vsCodProg
         AND a.IND_TIPO_NIVE = 'R';*/
       
       -- Verificar si existe mas de 1 campaña con traslape en la  fecha de facturacion
       SELECT COUNT(1)
         INTO lnExisteCampFact
         FROM CRA_PERIO
        WHERE lnFechaFact BETWEEN FEC_INIC AND FEC_FINA;
       
       lsIndCruce := '0';

       -- Si hay varias campañas con traslape en la fecha de facturacion, ejecuta lógica
       IF lnExisteCampFact > 1 THEN
            SELECT MAX(COD_PERI), MIN(COD_PERI)
            INTO vsCampCruce, vsMinCamp
            FROM CRA_PERIO cr, SEG_PERIO_CORPO sg
            WHERE lnFechaFact >= FEC_INIC
            AND lnFechaFact <= FEC_FINA
            AND cr.peri_oid_peri = sg.oid_peri ;
              
            SELECT CRA.IND_PERI_CRUC
            INTO vsIndCruce
            FROM CRA_PERIO CRA, SEG_PERIO_CORPO CP
            WHERE COD_PERI = vsMinCamp
            AND CRA.PERI_OID_PERI = CP.OID_PERI;
            
            IF  vsCampCruce =  psCampanaProceso AND vsIndCruce = 1 THEN
                 lsIndCruce := '1';
            END IF;
        END IF;
      
      -- Proceso para Lideres vigentes en campaña facturacion. El objetivo es calcular
      -- el Nivel que le corresponde a la Lider en la campaña.
      OPEN cGetLideres;
      LOOP
        FETCH cGetLideres BULK COLLECT
          INTO interfazLideresRecord LIMIT W_FILAS;
        IF interfazLideresRecord.COUNT > 0 THEN
          FOR i IN interfazLideresRecord.FIRST .. interfazLideresRecord.LAST LOOP
            lsCodigoLider := interfazLideresRecord(i).codigoLider;

            -- Calcula el nivel de la lider solo si el nivel no ha sido cargado manualmente.
            IF interfazLideresRecord(i).indOrigenNivel NOT IN ('X','M') THEN
                LEC_PR_CALCU_NIVEL_LIDER( psCodigoPais,
                                          psCodigoMarca,
                                          psCodigoCanal,
                                          vsCodProg,
                                          lsCodigoLider,
                                          psCampanaProceso,
                                          '1',
                                          psTipoProceso,
                                          lsIndCruce,
                                          psCodigoUsuario );
            END IF;

          END LOOP;
        END IF;
        EXIT WHEN cGetLideres%NOTFOUND;
      END LOOP;
      CLOSE cGetLideres;

      vsCampAnte := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,psCampanaProceso,-1);

      -- Proceso para Lideres vigentes en campaña anterior. El objetivo es cerrar la
      -- campaña fin del nivel de la Lider
      OPEN cLideresPeriodoAnterior(GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaProceso),
                                   GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vsCampAnte));
      LOOP
        FETCH cLideresPeriodoAnterior BULK COLLECT
          INTO interfazLideresAntRecord LIMIT W_FILAS;
        IF interfazLideresAntRecord.COUNT > 0 THEN

          FOR i IN interfazLideresAntRecord.FIRST .. interfazLideresAntRecord.LAST LOOP
            lsCodigoLider := interfazLideresAntRecord(i).codigoLider;

            LEC_PR_CALCU_NIVEL_LIDER( psCodigoPais,
                                      psCodigoMarca,
                                      psCodigoCanal,
                                      vsCodProg,
                                      lsCodigoLider,
                                      vsCampAnte,
                                      '2',
                                      psTipoProceso,
                                      lsIndCruce,
                                      psCodigoUsuario );
          END LOOP;
        END IF;
        EXIT WHEN cLideresPeriodoAnterior%NOTFOUND;
      END LOOP;
      CLOSE cLideresPeriodoAnterior;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCodProg := NULL;
    END;
    
    -- Borrar Niveles de Lideres con campaña de inicio en campaña anterior
    -- y que no figuren en la lista de Lideres activas en campaña anterior.
    -- El objetivo es eliminar los niveles de aquellas lideres inconsistentes que han
    -- creado registro de nivel de lider en la campaña anterior y que no figuran como
    -- Lider en el histórico de lideres.
    DELETE FROM LEC_LIDER_NIVEL a
     WHERE a.CAM_INIC = vsCampAnte
       AND a.cod_lide NOT IN
           (SELECT ZHG.GERE
              FROM ZON_HISTO_GEREN ZHG
             WHERE GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vsCampAnte) >=
                   ZHG.PERD_OID_PERI_DESD
               AND ((GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vsCampAnte)) <=
                    ZHG.PERD_OID_PERI_HAST OR ZHG.PERD_OID_PERI_HAST is null)
               AND ZHG.COD_SECC IS NOT NULL);

    --IF vsCampIni = vsCampFact THEN
    IF vsCampIni = psCampanaProceso THEN
      -- Actualiza los niveles reales con campaña anterior a todos los niveles que
      -- tienen campaña fin en nulo y cuyo codigo de programa no sea el vigente.
      UPDATE LEC_LIDER_NIVEL a
         SET a.CAM_FIN = vsCampAnte
       WHERE a.CAM_FIN IS NULL
         AND a.lpro_cod_prog <> vsCodProg
         AND a.IND_TIPO_NIVE = 'R';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_NIVEL_EXITO: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_NIVEL_EXITO;

  /***************************************************************************
  Descripcion       : Calcular Resultados para Incentivos y Gestion Desempeno
  Fecha Creacion    : 31/01/2014
  Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_MASIV(psCodigoPais       VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     pscodigomarca      VARCHAR2,
                                     pscodigocanal      VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     pstipoproceso      VARCHAR2,
                                     psCodigoUsuario    VARCHAR2,
                                     psFechaFacturacion VARCHAR2) IS

    lsCodiProg           varchar(100);
    pcCodigoRegion       ZON_REGIO.COD_REGI%TYPE;
    lnIndProgr           LEC_PROGR.IND_PROG_RECO%TYPE;
    lnOidPeriodo         cra_perio.oid_peri%TYPE;
    lnOidPeriodoAnterior cra_perio.oid_peri%TYPE;
    lnNumPedidosObjetivo NUMBER;
    lsIndicadorRE       NUMBER; -- Nuevo

  BEGIN
    -- Obtener datos del Programa Activo
    BEGIN
      SELECT COD_PROG, NVL(IND_PROG_RECO, 0)
        INTO lsCodiProg, lnIndProgr
        FROM LEC_PROGR
       WHERE IND_ACTI = '1'
         AND psCodigoPeriodo BETWEEN CAM_INIC AND
             NVL(CAM_FIN, psCodigoPeriodo);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsCodiProg := NULL;
        lnIndProgr := 0;
    END;
    -- Obtener indicador de Reversiï¿½n de Estatus
    BEGIN
         SELECT bpp.val_para
           INTO lsindicadorRE
           FROM bas_param_pais bpp
          WHERE bpp.cod_pais = pscodigopais
            AND bpp.cod_sist = 'MAE'
            AND UPPER(bpp.nom_para) = 'INDREVERSIONESTATUS'
            AND bpp.ind_acti = '1';
         EXCEPTION
           WHEN NO_DATA_FOUND
           THEN lsindicadorRE := '0';
    END; -- Nuevo

    -- Obtener Oid de la campaï¿½a actual
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    IF lsCodiProg IS NOT NULL THEN
      IF pstipoproceso = 'R' THEN
        pcCodigoRegion := psCodigoRegion;
      ELSE
        BEGIN
          pcCodigoRegion := NULL;
          -- Se limpian las tablas Resultados de las lideres no activas
          DELETE FROM LEC_LIDER_SECCI_RESUL r
           WHERE r.cam_resu = psCodigoPeriodo;
          DELETE FROM LEC_LIDER_SECCI_RESUL_BONO r
           WHERE r.cam_resu = psCodigoPeriodo;
          DELETE FROM LEC_LIDER_GANAN r WHERE r.cam_gana = psCodigoPeriodo;
          DELETE FROM LEC_LIDER_DESEM r
           WHERE r.lpec_cam_eval = psCodigoPeriodo;
          DELETE FROM LEC_LIDER_CANAS r WHERE r.cam_refe = psCodigoPeriodo;
        END;
      END IF;

      -- Obtener objetivo pedidos de la secciï¿½n
      SELECT COUNT(1)
        INTO lnNumPedidosObjetivo
        FROM LEC_LIDER_SECCI_OBJET_PEDID sop
       WHERE sop.IND_ACTI = 1
         AND sop.LPRO_COD_PROG = lsCodiProg
         AND sop.CAM_OBJE = psCodigoPeriodo
         AND sop.LPRO_COD_PAIS = psCodigoPais
         AND (pcCodigoRegion IS NULL OR sop.COD_REGI = pcCodigoRegion);

      --LIMPIAMOS TABLAS TEMPORALES DE CALCULO DE INGRESOS X LIDERES
      IF (lnNumPedidosObjetivo > 0) THEN
        EXECUTE IMMEDIATE 'TRUNCATE TABLE LEC_TMP_RESUL_LIDER_REAL1';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE LEC_TMP_RESUL_LIDER_REAL2';

        -- Obtiene Oid de campaï¿½a anterior
        lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                             psCodigoPeriodo,
                                                                                                             -1));

        -- Llena tabla temporal de calculo ingresos x secciï¿½n
        INSERT INTO LEC_TMP_RESUL_LIDER_REAL1
          SELECT ZORG.COD_REGI,
                 ZZON.COD_ZONA,
                 ZSCC.COD_SECC,
                 COUNT(DISTINCT mc.oid_clie)
            FROM PED_SOLIC_CABEC SC,
                 CRA_PERIO P,
                 SEG_PERIO_CORPO PC,
                 (SELECT X.VAL_I18N, TSP.OID_TIPO_SOLI_PAIS, TS.*
                    FROM PED_TIPO_SOLIC_PAIS TSP,
                         PED_TIPO_SOLIC      TS,
                         GEN_I18N_SICC_COMUN X
                   WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                     AND TS.COD_TIPO_SOLI = 'SOC'
                     AND X.ATTR_ENTI = 'PED_TIPO_SOLIC'
                     AND X.VAL_OID = TS.OID_TIPO_SOLI) TIPO_SOLI,
                 MAE_CLIEN MC,
                 MAE_CLIEN_UNIDA_ADMIN CUAD,
                 ZON_TERRI_ADMIN ZTAD,
                 ZON_SECCI ZSCC,
                 ZON_ZONA ZZON,
                 ZON_REGIO ZORG
           WHERE MC.OID_CLIE = SC.CLIE_OID_CLIE
             AND SC.TSPA_OID_TIPO_SOLI_PAIS = TIPO_SOLI.OID_TIPO_SOLI_PAIS
             AND TIPO_SOLI.COD_TIPO_SOLI = 'SOC'
             AND SC.FEC_FACT IS NOT NULL
             AND SC.PERD_OID_PERI = P.OID_PERI
             AND P.PERI_OID_PERI = PC.OID_PERI
             AND PC.COD_PERI = psCodigoPeriodo
             AND MC.OID_CLIE = CUAD.CLIE_OID_CLIE
             AND CUAD.PERD_OID_PERI_INI <= P.OID_PERI
             AND (CUAD.PERD_OID_PERI_FIN >= P.OID_PERI OR
                 CUAD.PERD_OID_PERI_FIN IS NULL)
             AND CUAD.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
             AND ZTAD.ZSCC_OID_SECC = ZSCC.OID_SECC
             AND ZSCC.ZZON_OID_ZONA = ZZON.OID_ZONA
             AND ZZON.ZORG_OID_REGI = ZORG.OID_REGI
             AND (pcCodigoRegion IS NULL OR ZORG.COD_REGI = pcCodigoRegion)
                --------   RCR  PER-SiCC-2015-0008   DM
             AND NOT EXISTS
           (SELECT NULL
                    FROM MAE_CLIEN_HISTO_ESTAT HE
                   WHERE HE.CLIE_OID_CLIE = MC.OID_CLIE
                     AND HE.PERD_OID_PERI <= lnOidPeriodoAnterior
                     AND (HE.PERD_OID_PERI_PERI_FIN >= lnOidPeriodoAnterior OR
                         HE.PERD_OID_PERI_PERI_FIN IS NULL)
                     AND HE.ESTA_OID_ESTA_CLIE IN (5, 4))
                -------   FIN RCR
             AND (lnIndProgr = 0 OR
                 (lnIndProgr = 1 AND NOT EXISTS
                  (SELECT NULL
                      FROM LEC_PROGR_LISTA_EXCLU LE
                     WHERE LE.LPRO_COD_PROG = lsCodiProg
                       AND LE.COD_CONS = MC.COD_CLIE
                       AND LE.CAM_INIC_VIGE <= psCodigoPeriodo
                       AND (LE.CAM_FIN_VIGE >= psCodigoPeriodo OR
                           LE.CAM_FIN_VIGE IS NULL))))
           GROUP BY ZORG.COD_REGI, ZZON.COD_ZONA, ZSCC.COD_SECC;

        INSERT INTO LEC_TMP_RESUL_LIDER_REAL2
          SELECT ZORG.COD_REGI,
                 ZZON.COD_ZONA,
                 ZSCC.COD_SECC,
                 COUNT(DISTINCT mc.oid_clie)
            FROM PED_SOLIC_CABEC SC,
                 CRA_PERIO P,
                 SEG_PERIO_CORPO PC,
                 (SELECT TSP.OID_TIPO_SOLI_PAIS, TS.COD_TIPO_SOLI
                    FROM PED_TIPO_SOLIC_PAIS TSP, PED_TIPO_SOLIC TS
                   WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                     AND TS.COD_TIPO_SOLI = 'SOC') TIPO_SOLI,
                 MAE_CLIEN MC,
                 MAE_CLIEN_UNIDA_ADMIN CUAD,
                 ZON_TERRI_ADMIN ZTAD,
                 ZON_SECCI ZSCC,
                 ZON_ZONA ZZON,
                 ZON_REGIO ZORG
           WHERE MC.OID_CLIE = SC.CLIE_OID_CLIE
             AND SC.TSPA_OID_TIPO_SOLI_PAIS = TIPO_SOLI.OID_TIPO_SOLI_PAIS
             AND TIPO_SOLI.COD_TIPO_SOLI = 'SOC'
             AND SC.FEC_FACT IS NOT NULL
             AND SC.PERD_OID_PERI = P.OID_PERI
             AND P.PERI_OID_PERI = PC.OID_PERI
             AND PC.COD_PERI = psCodigoPeriodo
             AND MC.OID_CLIE = CUAD.CLIE_OID_CLIE
             AND CUAD.PERD_OID_PERI_INI <= P.OID_PERI
             AND (CUAD.PERD_OID_PERI_FIN >= P.OID_PERI OR
                 CUAD.PERD_OID_PERI_FIN IS NULL)
             AND CUAD.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
             AND ZTAD.ZSCC_OID_SECC = ZSCC.OID_SECC
             AND ZSCC.ZZON_OID_ZONA = ZZON.OID_ZONA
             AND ZZON.ZORG_OID_REGI = ZORG.OID_REGI
             AND (pcCodigoRegion IS NULL OR ZORG.COD_REGI = pcCodigoRegion)
                --------   RCR  PER-SiCC-2015-0008   DM
             AND EXISTS
           (SELECT NULL
                    FROM MAE_CLIEN_HISTO_ESTAT HE
                   WHERE HE.CLIE_OID_CLIE = MC.OID_CLIE
                     AND HE.PERD_OID_PERI <= lnOidPeriodoAnterior
                     AND (HE.PERD_OID_PERI_PERI_FIN >= lnOidPeriodoAnterior OR
                         HE.PERD_OID_PERI_PERI_FIN IS NULL)
                     AND HE.ESTA_OID_ESTA_CLIE IN (5, 4))
                ------   FIN RCR
             AND (lnIndProgr = 0 OR
                 (lnIndProgr = 1 AND NOT EXISTS
                  (SELECT NULL
                      FROM LEC_PROGR_LISTA_EXCLU LE
                     WHERE LE.LPRO_COD_PROG = lsCodiProg
                       AND LE.COD_CONS = MC.COD_CLIE
                       AND LE.CAM_INIC_VIGE <= psCodigoPeriodo
                       AND (LE.CAM_FIN_VIGE >= psCodigoPeriodo OR
                           LE.CAM_FIN_VIGE IS NULL))))
           GROUP BY ZORG.COD_REGI, ZZON.COD_ZONA, ZSCC.COD_SECC;
      END IF;

      FOR REG IN (
                  SELECT g.GERE,
                         g.COD_REGI,
                         g.COD_ZONA,
                         g.COD_SECC,
                         (SELECT CASE
                                   WHEN COUNT(1) > 0 THEN
                                    '1'
                                   ELSE
                                    '0'
                                 END
                            FROM mae_clien           c,
                                 PED_SOLIC_CABEC     psc,
                                 PED_TIPO_SOLIC      pts,
                                 PED_TIPO_SOLIC_PAIS ptsp
                           WHERE c.oid_clie = psc.clie_oid_clie
                             AND psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                             AND psc.PERD_OID_PERI = lnOidPeriodo
                             AND psc.FEC_FACT IS NOT NULL
                             AND psc.IND_TS_NO_CONSO = 1
                             AND psc.IND_OC = 1
                             AND psc.IND_PEDI_PRUE = 0
                             AND pts.IND_DEVO = 0
                             AND pts.IND_ANUL = 0
                             AND c.cod_clie = g.GERE) IND_PEDI_PERS,
                         DECODE((SELECT sc.cod_clie
                                  FROM int_solic_conso_cabec sc
                                 WHERE sc.cod_peri = psCodigoPeriodo
                                   AND sc.cod_clie = g.GERE
                                   AND sc.tipo_soli = 'SOC'
                                   AND sc.ind_proc_gp2 = 1
                                   AND sc.ind_rece_web = 1
                                UNION
                                SELECT sc.cod_clie
                                  FROM ped_histo_solic_conso_cabec sc
                                 WHERE sc.cod_peri = psCodigoPeriodo
                                   AND sc.cod_clie = g.GERE
                                   AND sc.tipo_soli = 'SOC'
                                   AND sc.ind_proc_gp2 = 1
                                   AND sc.ind_rece_web = 1),
                                NULL,
                                '0',
                                '1') IND_PEDI_EXIG,
                         NVL((SELECT VAL_TOTA
                               FROM LEC_TMP_RESUL_LIDER_REAL1
                              WHERE COD_REGI = g.cod_regi
                                AND COD_ZONA = g.cod_zona
                                AND COD_SECC = g.cod_secc),
                             0) NRO_PEDI_CON_REAL,
                         NVL((SELECT VAL_TOTA
                               FROM LEC_TMP_RESUL_LIDER_REAL2
                              WHERE COD_REGI = g.cod_regi
                                AND COD_ZONA = g.cod_zona
                                AND COD_SECC = g.cod_secc),
                             0) NRO_PEDI_NO_CON_REAL,
                         (SELECT NVL(llni.mon_vent_cata, 0) + NVL(llni.mont_vent_rtal, 0)
                            FROM lec_lider_nivel llni
                           WHERE llni.pais_cod_pais = psCodigoPais
                             AND llni.lpro_cod_prog = lsCodiProg
                             AND llni.cod_lide = g.GERE
                             AND psCodigoPeriodo BETWEEN llni.cam_inic AND
                                 NVL(llni.cam_fin, psCodigoPeriodo)
                             AND llni.ind_tipo_nive = 'P') val_tota_vent_cata,
                         NVL(inre.val_nume_ingr_real, 0) val_nume_ingr_secc,
                         NVL(rein.val_nume_rein_real, 0) val_nume_rein_secc,
                         NVL(egre.val_nume_egre_real, 0) val_nume_egre_secc
                    FROM ZON_HISTO_GEREN g,
                       -----  Ingresos
                         (SELECT zorg.cod_regi,
                                 zzon.cod_zona,
                                 zscc.cod_secc,
                                 COUNT(*) val_nume_ingr_real
                            FROM mae_clien_histo_estat clhe,
                                 mae_clien             clie,
                                 (
                                  SELECT base.clie_oid_clie, COUNT(*) val_nume_pedi
                                    FROM (
                                          SELECT soca.clie_oid_clie,
                                                 (
                                                  SELECT COUNT(1)
                                                    FROM ped_solic_cabec     soca2,
                                                         ped_solic_cabec     cons2,
                                                         ped_tipo_solic_pais tspa2,
                                                         ped_tipo_solic      tsol2
                                                   WHERE 1 = 1
                                                     AND soca2.soca_oid_docu_refe = cons2.oid_soli_cabe
                                                     AND soca2.tspa_oid_tipo_soli_pais = tspa2.oid_tipo_soli_pais
                                                     AND tspa2.tsol_oid_tipo_soli = tsol2.oid_tipo_soli
                                                     AND tsol2.cod_tipo_soli IN ('SDAA', 'SDAN')
                                                     AND soca2.fec_fact IS NOT NULL
                                                     AND soca2.perd_oid_peri = lnOidPeriodo
                                                     AND soca2.clie_oid_clie = clie.oid_clie
                                                     AND soca2.soca_oid_docu_refe = soca.soca_oid_soli_cabe) ind_anul_pedi_camp
                                            FROM ped_solic_cabec     soca,
                                                 ped_solic_cabec     cons,
                                                 ped_tipo_solic_pais tspa,
                                                 ped_tipo_solic      tsol,
                                                 mae_clien           clie
                                           WHERE soca.clie_oid_clie = clie.oid_clie
                                             AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                             AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                             AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                             AND tsol.cod_tipo_soli = 'SOC'
                                             AND soca.perd_oid_peri = lnOidPeriodo
                                             AND soca.ind_oc = 1
                                             AND soca.grpr_oid_grup_proc = 5
                                         ) base
                                   WHERE (( lsIndicadorRE != 0 AND base.ind_anul_pedi_camp = 0 ) OR lsIndicadorRE = 0 )
                                   GROUP BY base.clie_oid_clie
                                 ) sca2,
                                 mae_clien_unida_admin cuad,
                                 zon_terri_admin       ztad,
                                 zon_secci             zscc,
                                 zon_zona              zzon,
                                 zon_regio             zorg
                           WHERE clhe.clie_oid_clie = clie.oid_clie
                             AND clhe.clie_oid_clie = sca2.clie_oid_clie
                             AND clhe.clie_oid_clie = cuad.clie_oid_clie
                             AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                             AND ztad.zscc_oid_secc = zscc.oid_secc
                             AND zscc.zzon_oid_zona = zzon.oid_zona
                             AND zzon.zorg_oid_regi = zorg.oid_regi
                             AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND
                                 NVL(cuad.perd_oid_peri_fin, lnOidPeriodo)
                             AND ((clhe.esta_oid_esta_clie IN (1, 7) AND
                                 lnOidPeriodo BETWEEN clhe.perd_oid_peri AND
                                 NVL(clhe.perd_oid_peri_peri_fin, lnOidPeriodo)) OR
                                 (clhe.esta_oid_esta_clie IN (2, 8) AND
                                 clhe.perd_oid_peri = lnOidPeriodo))
                             AND NOT EXISTS
                               (select distinct (lpex.cod_cons)
                                from lec_progr_lista_exclu lpex
                               where lpex.pais_cod_pais = pscodigopais
                               and psCodigoPeriodo between lpex.cam_inic_vige and   lpex.cam_fin_vige
                               and lpex.ind_acti = 1
                               and lpex.cod_cons = clie.cod_clie)
                           GROUP BY zorg.cod_regi, zzon.cod_zona, zscc.cod_secc ) inre,
                           -----  Reingresos
                          (SELECT zorg.cod_regi,
                                 zzon.cod_zona,
                                 zscc.cod_secc,
                                 COUNT(*) val_nume_rein_real
                            FROM mae_clien_histo_estat clhe,
                                 mae_clien             clie,
                                 (
                                  SELECT base.clie_oid_clie, COUNT(*) val_nume_pedi
                                    FROM (
                                          SELECT soca.clie_oid_clie,
                                                 (
                                                  SELECT COUNT(1)
                                                    FROM ped_solic_cabec     soca2,
                                                         ped_solic_cabec     cons2,
                                                         ped_tipo_solic_pais tspa2,
                                                         ped_tipo_solic      tsol2
                                                   WHERE 1 = 1
                                                     AND soca2.soca_oid_docu_refe = cons2.oid_soli_cabe
                                                     AND soca2.tspa_oid_tipo_soli_pais = tspa2.oid_tipo_soli_pais
                                                     AND tspa2.tsol_oid_tipo_soli = tsol2.oid_tipo_soli
                                                     AND tsol2.cod_tipo_soli IN ('SDAA', 'SDAN')
                                                     AND soca2.fec_fact IS NOT NULL
                                                     AND soca2.perd_oid_peri = lnOidPeriodo
                                                     AND soca2.clie_oid_clie = clie.oid_clie
                                                     AND soca2.soca_oid_docu_refe = soca.soca_oid_soli_cabe) ind_anul_pedi_camp
                                            FROM ped_solic_cabec     soca,
                                                 ped_solic_cabec     cons,
                                                 ped_tipo_solic_pais tspa,
                                                 ped_tipo_solic      tsol,
                                                 mae_clien           clie
                                           WHERE soca.clie_oid_clie = clie.oid_clie
                                             AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                             AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                             AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                             AND tsol.cod_tipo_soli = 'SOC'
                                             AND soca.perd_oid_peri = lnOidPeriodo
                                             AND soca.ind_oc = 1
                                             AND soca.grpr_oid_grup_proc = 5
                                         ) base
                          WHERE (( lsIndicadorRE != 0 AND base.ind_anul_pedi_camp = 0 ) OR lsIndicadorRE = 0 )
                          GROUP BY base.clie_oid_clie
                                 ) sca2,
                                 mae_clien_unida_admin cuad,
                                 zon_terri_admin       ztad,
                                 zon_secci             zscc,
                                 zon_zona              zzon,
                                 zon_regio             zorg
                           WHERE clhe.clie_oid_clie = clie.oid_clie
                             AND clhe.clie_oid_clie = sca2.clie_oid_clie
                             AND clhe.clie_oid_clie = cuad.clie_oid_clie
                             AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                             AND ztad.zscc_oid_secc = zscc.oid_secc
                             AND zscc.zzon_oid_zona = zzon.oid_zona
                             AND zzon.zorg_oid_regi = zorg.oid_regi
                             AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND
                                 NVL(cuad.perd_oid_peri_fin, lnOidPeriodo)
                             AND (clhe.esta_oid_esta_clie IN (5) AND
                                 lnOidPeriodoAnterior BETWEEN clhe.perd_oid_peri AND
                                 NVL(clhe.perd_oid_peri_peri_fin, lnOidPeriodoAnterior))
                                 and not exists
                               (select distinct (lpex.cod_cons)
                                from lec_progr_lista_exclu lpex
                               where lpex.pais_cod_pais = pscodigopais
                               and psCodigoPeriodo between lpex.cam_inic_vige and   lpex.cam_fin_vige
                               and lpex.ind_acti = 1
                               and lpex.cod_cons = clie.cod_clie)
                           GROUP BY zorg.cod_regi, zzon.cod_zona, zscc.cod_secc )  rein,
                      ------ Egresos
                        (SELECT zorg.cod_regi,
                                 zzon.cod_zona,
                                 zscc.cod_secc,
                                 COUNT(*) val_nume_egre_real
                            FROM mae_clien_histo_estat clhe,
                                 mae_clien             clie,
                                 (
                                  SELECT base.clie_oid_clie, COUNT(*) val_nume_pedi
                                    FROM (
                                          SELECT soca.clie_oid_clie,
                                                 (
                                                  SELECT COUNT(1)
                                                    FROM ped_solic_cabec     soca2,
                                                         ped_solic_cabec     cons2,
                                                         ped_tipo_solic_pais tspa2,
                                                         ped_tipo_solic      tsol2
                                                   WHERE 1 = 1
                                                     AND soca2.soca_oid_docu_refe = cons2.oid_soli_cabe
                                                     AND soca2.tspa_oid_tipo_soli_pais = tspa2.oid_tipo_soli_pais
                                                     AND tspa2.tsol_oid_tipo_soli = tsol2.oid_tipo_soli
                                                     AND tsol2.cod_tipo_soli IN ('SDAA', 'SDAN')
                                                     AND soca2.fec_fact IS NOT NULL
                                                     AND soca2.perd_oid_peri = lnOidPeriodo
                                                     AND soca2.clie_oid_clie = clie.oid_clie
                                                     AND soca2.soca_oid_docu_refe = soca.soca_oid_soli_cabe) ind_anul_pedi_camp
                                            FROM ped_solic_cabec     soca,
                                                 ped_solic_cabec     cons,
                                                 ped_tipo_solic_pais tspa,
                                                 ped_tipo_solic      tsol,
                                                 mae_clien           clie
                                           WHERE soca.clie_oid_clie = clie.oid_clie
                                             AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                                             AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                                             AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                                             AND tsol.cod_tipo_soli = 'SOC'
                                             AND soca.perd_oid_peri = lnOidPeriodo
                                             AND soca.ind_oc = 1
                                             AND soca.grpr_oid_grup_proc = 5
                                         ) base
                              WHERE (( lsIndicadorRE != 0 AND base.ind_anul_pedi_camp = 0 ) OR lsIndicadorRE = 0 )
                                   GROUP BY base.clie_oid_clie
                                 ) sca2,
                                 mae_clien_unida_admin cuad,
                                 zon_terri_admin       ztad,
                                 zon_secci             zscc,
                                 zon_zona              zzon,
                                 zon_regio             zorg
                           WHERE clhe.clie_oid_clie = clie.oid_clie
                             AND clhe.clie_oid_clie = sca2.clie_oid_clie (+)
                             AND clhe.clie_oid_clie = cuad.clie_oid_clie
                             AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                             AND ztad.zscc_oid_secc = zscc.oid_secc
                             AND zscc.zzon_oid_zona = zzon.oid_zona
                             AND zzon.zorg_oid_regi = zorg.oid_regi
                             AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND
                                 NVL(cuad.perd_oid_peri_fin, lnOidPeriodo)
                              AND (clhe.esta_oid_esta_clie IN (4) AND
                                 lnOidPeriodoAnterior = clhe.perd_oid_peri)
                              AND  sca2.clie_oid_clie is NULL
                              and not exists
                               (select distinct (lpex.cod_cons)
                                from lec_progr_lista_exclu lpex
                               where lpex.pais_cod_pais = pscodigopais
                              and psCodigoPeriodo between lpex.cam_inic_vige and   lpex.cam_fin_vige
                              and lpex.ind_acti = 1
                              and lpex.cod_cons = clie.cod_clie)
                           GROUP BY zorg.cod_regi, zzon.cod_zona, zscc.cod_secc )  egre
                   WHERE g.cod_regi IS NOT NULL
                     AND g.cod_zona IS NOT NULL
                     AND g.cod_secc IS NOT NULL
                        --
                     AND g.cod_regi = inre.cod_regi(+)
                     AND g.cod_zona = inre.cod_zona(+)
                     AND g.cod_secc = inre.cod_secc(+)
                     AND g.cod_zona = rein.cod_zona(+)
                     AND g.cod_secc = rein.cod_secc(+)
                     AND g.cod_regi = egre.cod_regi(+)
                     AND g.cod_zona = egre.cod_zona(+)
                     AND g.cod_secc = egre.cod_secc(+)
                     AND lnOidPeriodo BETWEEN g.PERD_OID_PERI_DESD AND
                         NVL(g.PERD_OID_PERI_HAST, lnOidPeriodo)
                     AND (pcCodigoRegion IS NULL OR
                         (g.COD_REGI = pcCodigoRegion AND EXISTS
                          (SELECT NULL
                              FROM lec_lider_nivel n
                             WHERE n.lpro_cod_prog = lsCodiProg
                               AND n.cod_lide = g.gere
                               AND n.ind_camb IS NOT NULL
                               AND n.cam_inic = psCodigoPeriodo)))
                 ) LOOP

        --Cï¿½lculo de Resultados de Lï¿½der

        LEC_PR_CALCU_RESUL_LIDER_ACTUA(psCodigoPais,
                                       pscodigomarca,
                                       pscodigocanal,
                                       psCodigoPeriodo,
                                       lsCodiProg,
                                       reg.gere,
                                       reg.cod_regi,
                                       reg.cod_zona,
                                       reg.cod_secc,
                                       reg.ind_pedi_pers,
                                       reg.ind_pedi_exig,
                                       reg.nro_pedi_con_real,
                                       reg.nro_pedi_no_con_real,
                                       reg.val_tota_vent_cata,
                                       reg.val_nume_ingr_secc,
                                       reg.val_nume_rein_secc,
                                       reg.val_nume_egre_secc,
                                       pscodigousuario);

        --Calculo de Ganancia Pedido
        LEC_PR_CALCU_GANAN_PEDID_LIDER(psCodigoPais,
                                       pscodigomarca,
                                       pscodigocanal,
                                       psCodigoPeriodo,
                                       lsCodiProg,
                                       reg.cod_regi,
                                       reg.cod_zona,
                                       reg.cod_secc,
                                       reg.gere,
                                       pscodigousuario,
                                       psTipoProceso);
        --Proceso Calculo Ganancia por Bonos
        LEC_PR_CALCU_GANAN_BONO_LIDER(psCodigoPais,
                                      pscodigomarca,
                                      pscodigocanal,
                                      psCodigoPeriodo,
                                      lsCodiProg,
                                      reg.cod_regi,
                                      reg.cod_zona,
                                      reg.cod_secc,
                                      reg.gere,
                                      pscodigousuario);

        --Cï¿½lculo de Productividad de Lï¿½der
        LEC_PR_CALCU_PRODU_LIDER(psCodigoPais,
                                 pscodigomarca,
                                 pscodigocanal,
                                 psCodigoPeriodo,
                                 lsCodiProg,
                                 reg.cod_regi,
                                 reg.cod_zona,
                                 reg.cod_secc,
                                 reg.gere,
                                 psCodigoUsuario);
      END LOOP;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_RESUL_MASIV: ' ||
                              ls_sqlerrm);
  END;

  /***************************************************************************
      Descripcion       : Calcular Resultados de Lider
      Fecha Creacion    : 31/01/2014
      Autor             : henry paredes
      Modificaciones    : Carlos Mori (07/08/2015)
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_LIDER_ACTUA(psCodigoPais      VARCHAR2,
                                           psCodigoMarca     VARCHAR2,
                                           psCodigoCanal     VARCHAR2,
                                           psCodigoPeriodo   VARCHAR2,
                                           psCodPrograma     VARCHAR2,
                                           psCodigoLider     VARCHAR2,
                                           psCodigoRegion    VARCHAR2,
                                           psCodigoZona      VARCHAR2,
                                           psCodigoSeccion   VARCHAR2,
                                           psPedidoPersonal  VARCHAR2,
                                           psPedidoExigido   VARCHAR2,
                                           pnNroPedConReal   NUMBER,
                                           PnNroPedNoConReal NUMBER,
                                           pnTotaVentCata    NUMBER,
                                           pnNumeIngrSecc    NUMBER,
                                           pnNumeReinSecc    NUMBER,
                                           pnNumeEgreSecc    NUMBER,
                                           psCodigoUsuario   VARCHAR2) IS
    vnOidZonaLider           ZON_ZONA.OID_ZONA%TYPE;
    vnOidSeccionLider        ZON_SECCI.OID_SECC%TYPE;
    vnOidTerrAdmiLider       ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
    vnNumeroPedido           INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnNumPedidosReales       INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnIndProgr               LEC_PROGR.IND_PROG_RECO%TYPE;
    vsCodEstadoObjetivo      LEC_ESTAD_OBJET.COD_ESTA_OBJE%TYPE;
    vsCodEstadoObjetivoIngr  LEC_ESTAD_OBJET.COD_ESTA_OBJE%TYPE; -- Nuevo
    vsCodEstadoObjetivoCapi  LEC_ESTAD_OBJET.Cod_Esta_Obje%TYPE;     -------PER-SICC-2015-0548
    vnNumPedidosObjetivo     LEC_LIDER_SECCI_OBJET_PEDID.NUM_PEDI_OBJE_FINA%TYPE;
    vsIndTipoMeta            LEC_PROGR_NIVEL.IND_TIPO_META%TYPE;
    vnObjetivoVenta          LEC_LIDER_SECCI_OBJET_PEDID.VAL_OBJE_VENT%TYPE;
    vnValToleVent            LEC_PROGR_NIVEL.VAL_MONT_TOLE_VENT%TYPE;
    vnMontoVentaReal         LEC_PROGR_NIVEL.VAL_MONT_TOLE_VENT%TYPE;
    vnNumTolePedi            lec_progr_nivel.tol_pedi%TYPE;
    vnOidPeriodo             cra_perio.oid_peri%TYPE;
    vnOidPeriodoAnterior     cra_perio.oid_peri%TYPE;
    vnOidPeriodoAnterior2    cra_perio.oid_peri%TYPE;
    vnOidPeriodoAnterior3    cra_perio.oid_peri%TYPE;
    vnNumActiSeccApta        LEC_PROGR.NUM_ACTI_SECC_APTA%TYPE;
    vnSumaPedidos            NUMBER := 0;
    vnpedidosexcluidas       NUMBER := 0;
    vnpedidosreales          NUMBER := 0;
    vnAcumzonaSeccion        NUMBER := 0;
    vnAcumZona               NUMBER := 0;
    vnNumRetencion33Actual   NUMBER := 0;
    vnNumRetencion33Ant1     NUMBER := 0;
    vnNumRetencion33Ant2     NUMBER := 0;
    vnNumRetencion33SubTotal NUMBER := 0;
    vnNumRetencion33Total    NUMBER := 0;
    vnNumRetencion44Actual   NUMBER := 0;
    vnNumRetencion44Ant1     NUMBER := 0;
    vnNumRetencion44Ant2     NUMBER := 0;
    vnNumRetencion44Ant3     NUMBER := 0;
    vnNumRetencion44SubTotal NUMBER := 0;
    vnNumRetencion44Total    NUMBER := 0;
    vnNumRetencion22Actual   NUMBER := 0;
    vnNumRetencion22Ant1     NUMBER := 0;
    vnNumRetencion22Ant2     NUMBER := 0;
    vnNumRetencion22SubTotal NUMBER := 0;
    vnNumRetencion22Total    NUMBER := 0;
    vnExisteReg              NUMBER := 0;
    vnIndSeccApta            NUMBER := 0;
    vnNroPedConReal          NUMBER(12) := 0;
    vnNroPedNoConReal        NUMBER(12) := 0;
    vnNumeMetaCapi           NUMBER(12) := 0;   --------PER-SICC-2015-0548
    vnIndCapi                NUMBER(1) := 0;   --------PER-SICC-2015-0548
    vnNumeRealCapi           NUMBER(12) := 0;   --------PER-SICC-2015-0548
    lnIndRet2de2             NUMBER(1) := 0;
    lnIndRet3de3             NUMBER(1) := 0;
    lnIndRet4de4             NUMBER(1) := 0;
    lnIndObtieneBono         NUMBER(1) := 0;

    vsCamIni             LEC_PROGR_CAMPA_EXIGE.CAM_INIC%TYPE;
    vsCodNivel           LEC_LIDER_NIVEL.LNIV_COD_NIVE%TYPE;
    vsCamIniNivel        LEC_LIDER_NIVEL.CAM_INIC%TYPE;
    vsConcZonRegiSeccAnt VARCHAR2(15);
    vsConcZonRegiSecc    VARCHAR2(15);
    vnIndRezon           NUMBER(1) := 0;
    vsNroCampNivel       VARCHAR2(10);
    vnNroMinimoPedido    Lec_lider_secci_resul.Num_Pedi%TYPE;

    vnSecBonoNivel   LEC_LIDER_SECCI_OBJET_BONO.LPBN_SEC_BONO_NIVE%type;
    vnSecBonoNivel2  LEC_LIDER_SECCI_OBJET_BONO.LPBN_SEC_BONO_NIVE%type;
    vnObjBono        LEC_LIDER_SECCI_OBJET_BONO.VAL_OBJE_BONO%type;
    vnSobrObjBono    LEC_LIDER_SECCI_OBJET_BONO.VAL_SOBR_OBJE_BONO%type;
    vnNumLanz        lec_progr_bono_lanza.num_lanz%TYPE;
    vsCodTipoMedi    LEC_PROGR_BONO_LANZA.LTME_COD_TIPO_MEDI%type;
    vnNroUndLanz     NUMBER(20) := 0;
    vnNroPedSecLanz  NUMBER(20) := 0;
    vsEstObjLanz     VARCHAR2(2) := '';
    vnNroIngrSecc22  NUMBER(20) := 0;
    vnNroIngrSecc33  NUMBER(20) := 0;
    vnNroIngrSecc44  NUMBER(20) := 0;
    vnNeoMiniIngr    LEC_PROGR_BONO_NIVEL.NUM_MINI_INGR%TYPE;
    vnNeoMaxiIngr    LEC_PROGR_BONO_NIVEL.NUM_MAXI_INGR%TYPE;
    vnNumMiniRete    LEC_PROGR_BONO_NIVEL.NUM_MINI_RETE%TYPE;
    vnNumMaxiRete    LEC_PROGR_BONO_NIVEL.NUM_MAXI_RETE%TYPE;
    vnRetenObj       LEC_LIDER_SECCI_OBJET_BONO.VAL_OBJE_BONO%TYPE;
    vsEstaObjRete22  VARCHAR2(2) := '';
    vsEstaObjRete33  VARCHAR2(2) := '';
    vsEstaObjRete44  VARCHAR2(2) := '';
    vsExitLiderNivel VARCHAR2(1);

    lnPesoNivel           lec_nivel.val_peso_nive%TYPE;
    lnMaxPesoNivel        lec_nivel.val_peso_nive%TYPE;
    lsCodNivel            lec_nivel.cod_nive%TYPE;
    vnTiemMaxCambNivel    LEC_PROGR_BONO_NIVEL.NUM_CAMP_MAXI_CAMB%TYPE;
    vnIncrMinPedi         LEC_PROGR_BONO_NIVEL.NUM_MINI_INCR_PEDI%TYPE;
    vsCampIniNivel        LEC_LIDER_NIVEL.CAM_INIC%TYPE;
    vsCampFinNivel        LEC_LIDER_NIVEL.CAM_FIN%TYPE;
    vnNroCampMismNivel    NUMBER(12) := 0;
    vnCreMinPedi          NUMBER(12) := 0;
    vnNroPediRealNivelIni LEC_LIDER_SECCI_RESUL.NUM_PEDI%TYPE;
    vnNroPediRealNivelFin LEC_LIDER_SECCI_RESUL.NUM_PEDI%TYPE;
    vsEstBonoCambNivelAce VARCHAR2(2) := '';
    lnPediPersonal        lec_lider_secci_resul.ind_pedi_pers_lide%TYPE;
    lnPediExigido         lec_lider_secci_resul.ind_pedi_web%TYPE;

    vnIndExiWeb       NUMBER := 0;
    vnIndFlagPedido   NUMBER := 0;
    vsIndExiPediLide  LEC_PROGR.IND_EXIG_PEDI_LIDE%type;
    vsSecAmbiGeog     LEC_PROGR_AMBIT_GEOGR.SEC_AMBI_GEOG%TYPE;
    vsIndPediPersLide LEC_LIDER_SECCI_RESUL.IND_PEDI_PERS_LIDE%type;
    vsIndPediWeb      LEC_LIDER_SECCI_RESUL.IND_PEDI_WEB%type;
    vsTipoOrigPedi    LEC_PROGR.Ind_Tipo_Orig_Pedi%type;
    vsNumMetaIngr     NUMBER := 0; -- Nuevo
    lsIndMetaIngr     LEC_PROGR.IND_DESE_META_INGR%TYPE; -- Nuevo

  BEGIN
    vnOidPeriodo          := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    vnOidPeriodoAnterior  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                          psCodigoPeriodo,
                                                                                                          -1));
    vnOidPeriodoAnterior2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                          psCodigoPeriodo,
                                                                                                          -2));
    vnOidPeriodoAnterior3 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                                                                          psCodigoPeriodo,
                                                                                                          -3));
    vnNumPedidosReales    := 0;

    BEGIN
      SELECT p.NUM_ACTI_SECC_APTA,
             NVL(p.IND_PROG_RECO, 0),
             p.IND_EXIG_PEDI_LIDE,
             p.IND_TIPO_ORIG_PEDI,
             NVL(p.ind_dese_meta_ingr, '0')
        INTO vnNumActiSeccApta,
             vnIndProgr,
             vsIndExiPediLide,
             vsTipoOrigPedi,
             lsIndMetaIngr -- Nuevo
        FROM LEC_PROGR p
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND COD_PROG = psCodPrograma
         AND psCodigoPeriodo >= CAM_INIC
         AND (psCodigoPeriodo <= CAM_FIN OR cam_fin IS NULL);

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vnNumActiSeccApta := NULL;
        vnIndProgr        := 0;
        vsIndExiPediLide  := '0';
        vsTipoOrigPedi    := NULL;
        lsIndMetaIngr     := '0';

    END;

    -- Se obtiene el Nivel de ï¿½xito de la Lï¿½der en la campaï¿½a de proceso

    BEGIN
      SELECT lln.lniv_cod_nive,
             lln.cam_inic,
             nvl(pn.ind_tipo_meta, 'P'),
             pn.num_meta_ingr, -- Nuevo
             nvl(pn.ind_cond_capi,0),    ------PER-SICC-2015-0548
             nvl(pn.num_meta_capi,0)     ------PER-SICC-2015-0548
        INTO vsCodNivel, vsCamIniNivel, vsIndTipoMeta, vsNumMetaIngr, -- Nuevo
        vnIndCapi, vnNumeMetaCapi    ------PER-SICC-2015-0548
        FROM lec_lider_nivel lln, lec_progr_nivel pn
       WHERE lln.PAIS_COD_PAIS = psCodigoPais
         AND lln.LPRO_COD_PROG = psCodPrograma
         AND pn.lpro_cod_prog(+) = lln.lpro_cod_prog
         AND pn.lniv_cod_nive(+) = lln.lniv_cod_nive
         AND COD_LIDE = psCodigoLider
         AND LLN.IND_TIPO_NIVE = 'R'
         AND psCodigoPeriodo >= CAM_INIC
         AND (psCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCodNivel    := NULL;
        vsCamIniNivel := NULL;
        vsIndTipoMeta := NULL;
        vsNumMetaIngr := 0;
        vnIndCapi  := 0;
        vnNumeMetaCapi := 0;
    END;

    -- OBTENER PEDIDO OBJETIVO
    BEGIN
      SELECT sop.num_pedi_obje_fina, sop.val_obje_vent
        INTO vnNumPedidosObjetivo, vnObjetivoVenta
        FROM Lec_Lider_Secci_Objet_Pedid sop
       WHERE sop.lpro_cod_pais = psCodigoPais
         AND sop.lpro_cod_prog = psCodPrograma
         AND sop.cod_regi = psCodigoRegion
         AND sop.cod_zona = pscodigoZona
         AND sop.cod_secc = psCodigoSeccion
         AND sop.cam_obje = psCodigoPeriodo
         AND sop.ind_acti = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vnNumPedidosObjetivo := NULL;
        vnObjetivoVenta      := NULL;
    END;

    --si hay objetivo de pedido entonces se calcula resultados objetivo pedido
    --IF vnNumPedidosObjetivo IS NOT NULL AND  vsCodNivel IS NOT NULL THEN
    IF ((vsIndTipoMeta = 'P' AND vnNumPedidosObjetivo IS NOT NULL) OR
       (vsIndTipoMeta = 'V' AND vnObjetivoVenta IS NOT NULL)) AND
       vsCodNivel IS NOT NULL THEN
      BEGIN
        -- OBTENER PEDIDO REALES
        --    vnpedidosreales := LEC_FN_OBTE_PEDI_REAL(pscodigomarca, pscodigocanal, psCodigoPeriodo, psCodPrograma, psCodigoRegion, pscodigoZona, psCodigoSeccion);

        --pedido personal, VIENE EL DATO DEL STORED PADRE
        lnPediPersonal := psPedidoPersonal;

        --pedido web, VIENE EL DATO DEL STORED PADRE
        lnPediExigido := psPedidoExigido;

        --El valor del indicador vnIndExiWeb, se insertara en la columna, IND_PEDI_CUMP_EXIG
        --de la tabla LEC_LIDER_SECCI_RESUL
        vnIndExiWeb := 0;

        IF (vsTipoOrigPedi = 'W') THEN
          --AMBITO GEOGRAFICO
          BEGIN
            SELECT SEC_AMBI_GEOG
              into vsSecAmbiGeog
              FROM LEC_PROGR_AMBIT_GEOGR
             WHERE LTUG_COD_TIPO_USO_GEOG = '03'
               AND IND_PAIS = '1'
               AND PAIS_COD_PAIS = psCodigoPais
               AND LPRO_COD_PROG = psCodPrograma;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsSecAmbiGeog := NULL;
          END;

          IF vsSecAmbiGeog IS NULL THEN
            BEGIN
              SELECT SEC_AMBI_GEOG
                into vsSecAmbiGeog
                FROM LEC_PROGR_AMBIT_GEOGR a
               WHERE a.LTUG_COD_TIPO_USO_GEOG = '03'
                 AND a.PAIS_COD_PAIS = psCodigoPais
                 AND a.LPRO_COD_PROG = psCodPrograma
                 AND a.cod_regi = psCodigoRegion
                 AND a.cod_zona is null;

            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsSecAmbiGeog := NULL;
            END;
          END IF;

          IF vsSecAmbiGeog IS NULL THEN
            BEGIN
              SELECT SEC_AMBI_GEOG
                INTO vsSecAmbiGeog
                FROM LEC_PROGR_AMBIT_GEOGR a
               WHERE a.LTUG_COD_TIPO_USO_GEOG = '03'
                 AND a.PAIS_COD_PAIS = psCodigoPais
                 AND a.LPRO_COD_PROG = psCodPrograma
                 AND a.cod_regi = psCodigoRegion
                 AND a.cod_zona = pscodigoZona;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsSecAmbiGeog := NULL;
            END;

          END IF;

          IF vsSecAmbiGeog IS NULL THEN
            vnIndExiWeb := 0;
          ELSE
            vnIndExiWeb := 1;
          END IF;

        ELSE
          vnIndExiWeb := 0;
        END IF;

        --Validacion de "IndFlagPedido" como condicion adicional para realizar las inserciones.
        vnIndFlagPedido := 0;
        IF (vsIndExiPediLide = '1') THEN
          IF (lnPediPersonal = '1') THEN
            IF (vnIndExiWeb = 1) THEN
              IF (lnPediExigido = '1') THEN
                vnIndFlagPedido := 1;
              ELSE
                vnIndFlagPedido := 0;
              END IF;

            ELSE
              vnIndFlagPedido := 1;
            END IF;

          ELSE
            vnIndFlagPedido := 0;
          END IF;

        ELSE
          vnIndFlagPedido := 1;
        END IF;

        -- Obtener Indicador Seccion Apta
        BEGIN

          VNACUMZONASECCION := gen_pkg_gener.gen_fn_devue_activas_seccion(psCodigoPeriodo,
                                                                          PSCODIGOREGION,
                                                                          PSCODIGOZONA,
                                                                          PSCODIGOSECCION);

          VNACUMZONASECCION := NVL(VNACUMZONASECCION, 0);
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            VNACUMZONASECCION := 0;

        END;

        IF vnAcumzonaSeccion >= vnNumActiSeccApta THEN
          vnIndSeccApta := 1;
        ELSE
          vnIndSeccApta := 0;
        END IF;

        --VIENE LOS DATOS DEL STORED PADRE

        vnNroPedConReal   := pnNroPedConReal;
        vnNroPedNoConReal := pnNroPedNoConReal;
        vnMontoVentaReal  := pnTotaVentCata;
        vnpedidosreales   := vnNroPedConReal + vnNroPedNoConReal;

        -- ===========================
        -- Evaluar Objetivo de Pedidos
        -- ===========================

        vsCodEstadoObjetivoIngr := NULL;
        IF lsIndMetaIngr = '1' THEN
          IF pnNumeIngrSecc >= vsNumMetaIngr THEN
            vsCodEstadoObjetivoIngr := '01'; -- Cumple objetivo
          ELSE
            vsCodEstadoObjetivoIngr := '03'; -- No cumple objetivo
          END IF;
        END IF; -- Nuevo

        vsCodEstadoObjetivoCapi := NULL;
        vnNumeRealCapi:= nvl(pnNumeIngrSecc,0) + nvl(pnNumeReinSecc,0) - nvl(pnNumeEgreSecc,0);    ------PER-SICC-2015-0548
        IF vnIndCapi  = 1 THEN     ------PER-SICC-2015-0548
          IF vnNumeRealCapi >= vnNumeMetaCapi THEN   ------PER-SICC-2015-0548
            vsCodEstadoObjetivoCapi := '01'; -- Cumple objetivo   ------PER-SICC-2015-0548
          ELSE
            vsCodEstadoObjetivoCapi := '03'; -- No cumple objetivo   ------PER-SICC-2015-0548
          END IF;    ------PER-SICC-2015-0548
        END IF;      ------PER-SICC-2015-0548


        IF (vsIndTipoMeta = 'P' AND vnPedidosReales >= vnNumPedidosObjetivo) OR
           (vsIndTipoMeta = 'V' AND vnMontoVentaReal >= vnObjetivoVenta) THEN

          vsCodEstadoObjetivo := '01';

        ELSE
          BEGIN
            -- Se obtiene el Numero de Pedidos Tolerancia
            BEGIN
              SELECT pn.tol_pedi, pn.val_mont_tole_vent
                INTO vnNumTolePedi, vnValToleVent
                FROM LEC_PROGR_NIVEL pn
               WHERE pn.pais_cod_pais = psCodigoPais
                 AND pn.lpro_cod_prog = psCodPrograma
                 AND pn.lniv_cod_nive = vsCodNivel
                 AND pn.ind_acti = 1;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vnNumTolePedi := 0;
                vnValToleVent := 0;
            END;
            IF (vsIndTipoMeta = 'P' AND
               (vnPedidosReales >= (vnNumPedidosObjetivo - vnNumTolePedi))) OR
               (vsIndTipoMeta = 'V' AND
               (vnMontoVentaReal >= (vnObjetivoVenta - vnValToleVent))) THEN
              vsCodEstadoObjetivo := '02';
            ELSE
              vsCodEstadoObjetivo := '03';
            END IF;
          END;
        END IF;

        -- verificar si existe, si eliminar y luego insertar

        SELECT COUNT(1)
          INTO vnExisteReg
          FROM LEC_LIDER_SECCI_RESUL r
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodPrograma
           AND (COD_LIDE = psCodigoLider OR
               (r.cod_regi = PSCODIGOREGION AND r.cod_zona = PSCODIGOZONA AND
               r.cod_secc = PSCODIGOSECCION))
           AND CAM_RESU = psCodigoPeriodo;

        IF vnExisteReg = 1 THEN
          DELETE FROM LEC_LIDER_SECCI_RESUL r
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = psCodPrograma
             AND (COD_LIDE = psCodigoLider OR (r.cod_regi = PSCODIGOREGION AND
                 r.cod_zona = PSCODIGOZONA AND
                 r.cod_secc = PSCODIGOSECCION))
             AND CAM_RESU = psCodigoPeriodo;
        END IF;

        INSERT INTO LEC_LIDER_SECCI_RESUL
          (PAIS_COD_PAIS,
           LPRO_COD_PROG,
           COD_REGI,
           COD_ZONA,
           COD_SECC,
           COD_LIDE,
           CAM_RESU,
           IND_SECC_APTA,
           IND_PEDI_PERS_LIDE,
           IND_PEDI_WEB,
           VAL_VENT_RECU,
           IND_PEDI_CUMP_EXIG,
           NUM_INGR_SECC,
           NUM_PEDI,
           NUM_PEDI_CONS,
           NUM_PEDI_NCON,
           NUM_REIN_SECC,     --------PER-SICC-2015-0548
           NUM_EGRE_SECC,    --------PER-SICC-2015-0548
           LEOB_COD_ESTA_OBJE,
           LEOB_COD_ESTA_OBJE_INGR, -- Nuevo
           LEOB_COD_ESTA_OBJE_CAPI,  --------PER-SICC-2015-0548
           USU_CREA,
           FEC_CREA,
           IND_ACTI)
        VALUES
          (PSCODIGOPAIS,
           PSCODPROGRAMA,
           PSCODIGOREGION,
           PSCODIGOZONA,
           PSCODIGOSECCION,
           PSCODIGOLIDER,
           psCodigoPeriodo,
           VNINDSECCAPTA,
           lnPediPersonal,
           lnPediExigido,
           0,
           vnIndFlagPedido,
           pnNumeIngrSecc,
           VNPEDIDOSREALES,
           vnNroPedConReal,
           vnNroPedNoConReal,
           pnNumeReinSecc,    -------PER-SICC-2015-0548
           pnNumeEgreSecc,    -------PER-SICC-2015-0548
           vsCodEstadoObjetivo,
           vsCodEstadoObjetivoIngr, -- Nuevo
           vsCodEstadoObjetivoCapi,  -------PER-SICC-2015-0548
           PSCODIGOUSUARIO,
           SYSDATE,
           '1');
      END;
    END IF;

    -- --------------------------
    -- RESULTADO DE BONOS 2 de 2
    -- --------------------------

    -- Verificar si Calculo Retencion 2de2 estï¿½ configurado en la parametrï¿½a del programa

    SELECT COUNT(1)
      INTO lnIndRet2de2
      FROM LEC_PROGR_BONO LPGB
     WHERE LPGB.PAIS_COD_PAIS = psCodigoPais
       AND LPGB.LPRO_COD_PROG = psCodPrograma
       AND LPGB.LTBO_COD_TIPO_BONO = '05';

    -- Si hay objetivo de retencion 2/2 se calcula los resultados

    IF lnIndRet2de2 = 1 AND vsCodNivel IS NOT NULL THEN
      BEGIN
        --obtener retencion 2 2
        FOR REG0 IN (SELECT mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE
                       FROM mae_clien             mc,
                            MAE_CLIEN_UNIDA_ADMIN mcua,
                            ZON_TERRI_ADMIN       zta,
                            ZON_SECCI             zs,
                            ZON_ZONA              zz,
                            ZON_REGIO             zr
                      WHERE mcua.CLIE_OID_CLIE IN
                            (SELECT mchs.CLIE_OID_CLIE
                               FROM MAE_CLIEN_HISTO_ESTAT mchs
                              WHERE (mchs.ESTA_OID_ESTA_CLIE = 2 OR
                                    mchs.ESTA_OID_ESTA_CLIE = 8)
                                AND mchs.PERD_OID_PERI = vnOidPeriodoAnterior)

                        AND vnOidPeriodo >= mcua.PERD_OID_PERI_INI
                        AND (vnOidPeriodo <= mcua.PERD_OID_PERI_FIN OR
                            mcua.PERD_OID_PERI_FIN IS NULL)
                        AND mc.oid_clie = mcua.clie_oid_clie
                        AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                        AND zta.ZSCC_OID_SECC = zs.OID_SECC
                        AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                        AND zz.ZORG_OID_REGI = zr.OID_REGI
                        AND zr.COD_REGI = psCodigoRegion
                        AND zz.COD_ZONA = pscodigoZona
                        AND zs.COD_SECC = psCodigoSeccion
                        AND (vnIndProgr = 0 OR
                            (vnIndProgr = 1 AND NOT EXISTS
                             (SELECT NULL
                                 FROM LEC_PROGR_LISTA_EXCLU LE
                                WHERE LE.LPRO_COD_PROG = psCodPrograma
                                  AND LE.COD_CONS = MC.COD_CLIE
                                  AND LE.CAM_INIC_VIGE <= psCodigoPeriodo
                                  AND (LE.CAM_FIN_VIGE >= psCodigoPeriodo OR
                                      LE.CAM_FIN_VIGE IS NULL))))) LOOP
          SELECT COUNT(1)
            INTO vnNumRetencion22Actual
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodo
             AND psc.CLIE_OID_CLIE = REG0.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion22Ant1
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior
             AND psc.CLIE_OID_CLIE = REG0.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          IF vnNumRetencion22Actual > 0 AND vnNumRetencion22Ant1 > 0 THEN
            vnNumRetencion22Total := vnNumRetencion22Total + 1;
          END IF;

          vnNroIngrSecc22 := vnNroIngrSecc22 + 1;

        END LOOP;

        -- Obtener las variables para el tipo de bono de la lï¿½der

        BEGIN
          SELECT lpbn.num_mini_ingr,
                 lpbn.num_maxi_ingr,
                 lpbn.num_mini_rete,
                 lpbn.num_maxi_rete
            INTO vnNeoMiniIngr, vnNeoMaxiIngr, vnNumMiniRete, vnNumMaxiRete
            FROM lec_progr_bono_nivel lpbn
           WHERE lpbn.pais_cod_pais = psCodigoPais
             AND lpbn.lpro_cod_prog = psCodPrograma
             AND lpbn.ltbo_cod_tipo_bono = '05'
             AND lpbn.lniv_cod_nive = vsCodNivel
             AND NVL(vnNumRetencion22Total, 0) BETWEEN lpbn.num_mini_rete AND
                 lpbn.num_maxi_rete
             AND NVL(vnNroIngrSecc22, 0) BETWEEN lpbn.num_mini_ingr AND
                 lpbn.num_maxi_ingr
             AND lpbn.ind_acti = '1';
          --
          lnIndObtieneBono := 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnNeoMiniIngr := 0;
            vnNeoMaxiIngr := 0;
            vnNumMiniRete := 0;
            vnNumMaxiRete := 0;
            --
            lnIndObtieneBono := 0;
        END;

        -- Calcular Estado de Bono de Retenciï¿½n 2/2
        IF ----(vnNroIngrSecc22 >= vnNeoMiniIngr AND vnNroIngrSecc22 <= vnNeoMaxiIngr) AND
         lnIndObtieneBono = 1 THEN
          vsEstaObjRete22 := '07';
        ELSE
          vsEstaObjRete22 := '08';
        END IF;

        -- Verificar si existe, eliminar y luego insertar
        DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodPrograma
           AND COD_LIDE = psCodigoLider
           AND CAM_RESU = psCodigoPeriodo
           AND LTBO_COD_TIPO_BONO = '05';

        --Insertar Resultado Bono Retencion 2/2
        INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
          (PAIS_COD_PAIS,
           LPRO_COD_PROG,
           COD_REGI,
           COD_ZONA,
           COD_SECC,
           COD_LIDE,
           CAM_RESU,
           LTBO_COD_TIPO_BONO,
           SEC_RESU_BONO,
           LPBC_CAM_LANZ,
           LPBL_NUM_LANZ,
           NUM_UNID_LANZ_REAL,
           NUM_PEDI_SECC_LANZ,
           NUM_INGR_SECC,
           VAL_RETE_SECC,
           NUM_CAMP_NIVEL,
           VAL_CREC_MINI_PEDI,
           LEOB_COD_ESTA_OBJE,
           USU_CREA,
           FEC_CREA,
           USU_MODI,
           FEC_MODI,
           IND_ACTI)
        VALUES
          (PSCODIGOPAIS,
           PSCODPROGRAMA,
           PSCODIGOREGION,
           PSCODIGOZONA,
           PSCODIGOSECCION,
           PSCODIGOLIDER,
           psCodigoPeriodo,
           '05',
           LEC_LRBO_SEQ.NEXTVAL,
           NULL,
           NULL,
           NULL,
           NULL,
           vnNroIngrSecc22,
           vnNumRetencion22Total,
           NULL,
           NULL,
           vsEstaObjRete22,
           PSCODIGOUSUARIO,
           SYSDATE,
           NULL,
           NULL,
           '1');
      END;
    END IF;

    -- -------------------------
    -- RESULTADO DE BONOS 3 de 3
    -- -------------------------

    -- Verificar si Calculo Retencion 3de3 estï¿½ configurado en la parametrï¿½a del programa

    SELECT COUNT(1)
      INTO lnIndRet3de3
      FROM LEC_PROGR_BONO LPGB
     WHERE LPGB.PAIS_COD_PAIS = psCodigoPais
       AND LPGB.LPRO_COD_PROG = psCodPrograma
       AND LPGB.LTBO_COD_TIPO_BONO = '06';

    -- Si tiene objetivo retencion 3/3 se calcula los resultados

    IF lnIndRet3de3 = 1 AND vsCodNivel IS NOT NULL THEN
      BEGIN
        --obtener retencion 3 3
        FOR REG1 IN (SELECT mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE
                       FROM mae_clien             mc,
                            MAE_CLIEN_UNIDA_ADMIN mcua,
                            ZON_TERRI_ADMIN       zta,
                            ZON_SECCI             zs,
                            ZON_ZONA              zz,
                            ZON_REGIO             zr
                      WHERE mcua.CLIE_OID_CLIE IN
                            (SELECT mchs.CLIE_OID_CLIE
                               FROM MAE_CLIEN_HISTO_ESTAT mchs
                              WHERE (mchs.ESTA_OID_ESTA_CLIE = 2 OR
                                    mchs.ESTA_OID_ESTA_CLIE = 8)
                                AND mchs.PERD_OID_PERI =
                                    vnOidPeriodoAnterior2)
                        AND vnOidPeriodo >= mcua.PERD_OID_PERI_INI
                        AND (vnOidPeriodo <= mcua.PERD_OID_PERI_FIN OR
                            mcua.PERD_OID_PERI_FIN IS NULL)
                        AND mc.oid_clie = mcua.clie_oid_clie
                        AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                        AND zta.ZSCC_OID_SECC = zs.OID_SECC
                        AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                        AND zz.ZORG_OID_REGI = zr.OID_REGI
                        AND zr.COD_REGI = psCodigoRegion
                        AND zz.COD_ZONA = pscodigoZona
                        AND zs.COD_SECC = psCodigoSeccion
                        AND (vnIndProgr = 0 OR
                            (vnIndProgr = 1 AND NOT EXISTS
                             (SELECT NULL
                                 FROM LEC_PROGR_LISTA_EXCLU LE
                                WHERE LE.LPRO_COD_PROG = psCodPrograma
                                  AND LE.COD_CONS = MC.COD_CLIE
                                  AND LE.CAM_INIC_VIGE <= psCodigoPeriodo
                                  AND (LE.CAM_FIN_VIGE >= psCodigoPeriodo OR
                                      LE.CAM_FIN_VIGE IS NULL))))) LOOP

          SELECT COUNT(1)
            INTO vnNumRetencion33Actual
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodo
             AND psc.CLIE_OID_CLIE = REG1.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion33Ant1
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior
             AND psc.CLIE_OID_CLIE = REG1.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion33Ant2
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior2
             AND psc.CLIE_OID_CLIE = REG1.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          IF vnNumRetencion33Actual > 0 AND vnNumRetencion33Ant1 > 0 AND
             vnNumRetencion33Ant2 > 0 THEN
            vnNumRetencion33Total := vnNumRetencion33Total + 1;
          END IF;

          vnNroIngrSecc33 := vnNroIngrSecc33 + 1;
        END LOOP;

        -- Obtener las variables para el tipo de bono de la lï¿½der

        BEGIN
          SELECT lpbn.num_mini_ingr,
                 lpbn.num_maxi_ingr,
                 lpbn.num_mini_rete,
                 lpbn.num_maxi_rete
            INTO vnNeoMiniIngr, vnNeoMaxiIngr, vnNumMiniRete, vnNumMaxiRete
            FROM lec_progr_bono_nivel lpbn
           WHERE lpbn.pais_cod_pais = psCodigoPais
             AND lpbn.lpro_cod_prog = psCodPrograma
             AND lpbn.ltbo_cod_tipo_bono = '06'
             AND lpbn.lniv_cod_nive = vsCodNivel
             AND NVL(vnNumRetencion33Total, 0) BETWEEN lpbn.num_mini_rete AND
                 lpbn.num_maxi_rete
             AND NVL(vnNroIngrSecc33, 0) BETWEEN lpbn.num_mini_ingr AND
                 lpbn.num_maxi_ingr
             AND lpbn.ind_acti = '1';
          --
          lnIndObtieneBono := 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnNeoMiniIngr := 0;
            vnNeoMaxiIngr := 0;
            vnNumMiniRete := 0;
            vnNumMaxiRete := 0;
            --
            lnIndObtieneBono := 0;
        END;

        -- Calcular Estado de Bono de Retenciï¿½n 3/3

        IF ---(vnNroIngrSecc33 >= vnNeoMiniIngr AND vnNroIngrSecc33 <= vnNeoMaxiIngr) AND
         lnIndObtieneBono = 1 THEN
          vsEstaObjRete33 := '07';
        ELSE
          vsEstaObjRete33 := '08';
        END IF;

        -- Verificar si existe, eliminar y luego insertar

        DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodPrograma
           AND COD_LIDE = psCodigoLider
           AND CAM_RESU = psCodigoPeriodo
           AND LTBO_COD_TIPO_BONO = '06';

        --Insertar Resultado Bono Retencion 3/3
        INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
          (PAIS_COD_PAIS,
           LPRO_COD_PROG,
           COD_REGI,
           COD_ZONA,
           COD_SECC,
           COD_LIDE,
           CAM_RESU,
           LTBO_COD_TIPO_BONO,
           SEC_RESU_BONO,
           LPBC_CAM_LANZ,
           LPBL_NUM_LANZ,
           NUM_UNID_LANZ_REAL,
           NUM_PEDI_SECC_LANZ,
           NUM_INGR_SECC,
           VAL_RETE_SECC,
           NUM_CAMP_NIVEL,
           VAL_CREC_MINI_PEDI,
           LEOB_COD_ESTA_OBJE,
           USU_CREA,
           FEC_CREA,
           USU_MODI,
           FEC_MODI,
           IND_ACTI)
        VALUES
          (PSCODIGOPAIS,
           PSCODPROGRAMA,
           PSCODIGOREGION,
           PSCODIGOZONA,
           PSCODIGOSECCION,
           PSCODIGOLIDER,
           psCodigoPeriodo,
           '06',
           LEC_LRBO_SEQ.NEXTVAL,
           NULL,
           NULL,
           NULL,
           NULL,
           vnNroIngrSecc33,
           vnNumRetencion33Total,
           NULL,
           NULL,
           vsEstaObjRete33,
           PSCODIGOUSUARIO,
           SYSDATE,
           NULL,
           NULL,
           '1');

      END;
    END IF;

    --RETENCION 4/4

    -- Verificar si Calculo Retencion 4/4 estï¿½ configurado en la parametrï¿½a del programa

    SELECT COUNT(1)
      INTO lnIndRet4de4
      FROM LEC_PROGR_BONO LPGB
     WHERE LPGB.PAIS_COD_PAIS = psCodigoPais
       AND LPGB.LPRO_COD_PROG = psCodPrograma
       AND LPGB.LTBO_COD_TIPO_BONO = '07';

    --si tiene objetivo retencion 4/4 se calcula los resultados

    IF lnIndRet4de4 = 1 AND vsCodNivel IS NOT NULL THEN

      BEGIN
        --obtener retencion 4 4
        FOR REG2 IN (SELECT mcua.OID_CLIE_UNID_ADMI, mcua.CLIE_OID_CLIE
                       FROM mae_clien             mc,
                            MAE_CLIEN_UNIDA_ADMIN mcua,
                            ZON_TERRI_ADMIN       zta,
                            ZON_SECCI             zs,
                            ZON_ZONA              zz,
                            ZON_REGIO             zr
                      WHERE mcua.CLIE_OID_CLIE IN
                            (SELECT mchs.CLIE_OID_CLIE
                               FROM MAE_CLIEN_HISTO_ESTAT mchs
                              WHERE (mchs.ESTA_OID_ESTA_CLIE = 2 OR
                                    mchs.ESTA_OID_ESTA_CLIE = 8)
                                AND mchs.PERD_OID_PERI =
                                    vnOidPeriodoAnterior3)
                        AND vnOidPeriodo >= mcua.PERD_OID_PERI_INI
                        AND (vnOidPeriodo <= mcua.PERD_OID_PERI_FIN OR
                            mcua.PERD_OID_PERI_FIN IS NULL)
                        AND mc.oid_clie = mcua.clie_oid_clie
                        AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
                        AND zta.ZSCC_OID_SECC = zs.OID_SECC
                        AND zs.ZZON_OID_ZONA = zz.OID_ZONA
                        AND zz.ZORG_OID_REGI = zr.OID_REGI
                        AND zr.COD_REGI = psCodigoRegion
                        AND zz.COD_ZONA = pscodigoZona
                        AND zs.COD_SECC = psCodigoSeccion
                        AND (vnIndProgr = 0 OR
                            (vnIndProgr = 1 AND NOT EXISTS
                             (SELECT NULL
                                 FROM LEC_PROGR_LISTA_EXCLU LE
                                WHERE LE.LPRO_COD_PROG = psCodPrograma
                                  AND LE.COD_CONS = MC.COD_CLIE
                                  AND LE.CAM_INIC_VIGE <= psCodigoPeriodo
                                  AND (LE.CAM_FIN_VIGE >= psCodigoPeriodo OR
                                      LE.CAM_FIN_VIGE IS NULL))))) LOOP

          SELECT COUNT(1)
            INTO vnNumRetencion44Actual
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodo
             AND psc.CLIE_OID_CLIE = REG2.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion44Ant1
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior
             AND psc.CLIE_OID_CLIE = REG2.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion44Ant2
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior2
             AND psc.CLIE_OID_CLIE = REG2.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          SELECT COUNT(1)
            INTO vnNumRetencion44Ant3
            FROM PED_SOLIC_CABEC     psc,
                 PED_TIPO_SOLIC      pts,
                 PED_TIPO_SOLIC_PAIS ptsp
           WHERE psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
             AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
             AND psc.PERD_OID_PERI = vnOidPeriodoAnterior3
             AND psc.CLIE_OID_CLIE = REG2.CLIE_OID_CLIE
             AND psc.FEC_FACT IS NOT NULL
             AND psc.IND_TS_NO_CONSO = 1
             AND psc.IND_OC = 1
             AND psc.IND_PEDI_PRUE = 0
             AND pts.IND_DEVO = 0
             AND pts.IND_ANUL = 0;

          IF vnNumRetencion44Actual > 0 AND vnNumRetencion44Ant1 > 0 AND
             vnNumRetencion44Ant2 > 0 AND vnNumRetencion44Ant3 > 0 THEN
            vnNumRetencion44Total := vnNumRetencion44Total + 1;
          END IF;

          vnNroIngrSecc44 := vnNroIngrSecc44 + 1;
        END LOOP;

        -- Obtener las variables para el tipo de bono de la lï¿½der

        BEGIN
          SELECT lpbn.num_mini_ingr,
                 lpbn.num_maxi_ingr,
                 lpbn.num_mini_rete,
                 lpbn.num_maxi_rete
            INTO vnNeoMiniIngr, vnNeoMaxiIngr, vnNumMiniRete, vnNumMaxiRete
            FROM lec_progr_bono_nivel lpbn
           WHERE lpbn.pais_cod_pais = psCodigoPais
             AND lpbn.lpro_cod_prog = psCodPrograma
             AND lpbn.ltbo_cod_tipo_bono = '07'
             AND lpbn.lniv_cod_nive = vsCodNivel
             AND NVL(vnNumRetencion44Total, 0) BETWEEN lpbn.num_mini_rete AND
                 lpbn.num_maxi_rete
             AND NVL(vnNroIngrSecc44, 0) BETWEEN lpbn.num_mini_ingr AND
                 lpbn.num_maxi_ingr
             AND lpbn.ind_acti = '1';
          --
          lnIndObtieneBono := 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnNeoMiniIngr := 0;
            vnNeoMaxiIngr := 0;
            vnNumMiniRete := 0;
            vnNumMaxiRete := 0;
            --
            lnIndObtieneBono := 0;
        END;

        -- Calcular Estado de Bono de Retenciï¿½n 4/4

        IF ----(vnNroIngrSecc44 >= vnNeoMiniIngr AND vnNroIngrSecc44 <= vnNeoMaxiIngr) AND
         lnIndObtieneBono = 1 THEN
          vsEstaObjRete44 := '07';
        ELSE
          vsEstaObjRete44 := '08';
        END IF;

        -- Verificar si existe, eliminar y luego insertar

        DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodPrograma
           AND COD_LIDE = psCodigoLider
           AND CAM_RESU = psCodigoPeriodo
           AND LTBO_COD_TIPO_BONO = '07';

        --Insertar Resultado Bono Retencion 4/4
        INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
          (PAIS_COD_PAIS,
           LPRO_COD_PROG,
           COD_REGI,
           COD_ZONA,
           COD_SECC,
           COD_LIDE,
           CAM_RESU,
           LTBO_COD_TIPO_BONO,
           SEC_RESU_BONO,
           LPBC_CAM_LANZ,
           LPBL_NUM_LANZ,
           NUM_UNID_LANZ_REAL,
           NUM_PEDI_SECC_LANZ,
           NUM_INGR_SECC,
           VAL_RETE_SECC,
           NUM_CAMP_NIVEL,
           VAL_CREC_MINI_PEDI,
           LEOB_COD_ESTA_OBJE,
           USU_CREA,
           FEC_CREA,
           USU_MODI,
           FEC_MODI,
           IND_ACTI)
        VALUES
          (PSCODIGOPAIS,
           PSCODPROGRAMA,
           PSCODIGOREGION,
           PSCODIGOZONA,
           PSCODIGOSECCION,
           PSCODIGOLIDER,
           psCodigoPeriodo,
           '07',
           LEC_LRBO_SEQ.NEXTVAL,
           NULL,
           NULL,
           NULL,
           NULL,
           vnNroIngrSecc44,
           vnNumRetencion44Total,
           NULL,
           NULL,
           vsEstaObjRete44,
           PSCODIGOUSUARIO,
           SYSDATE,
           NULL,
           NULL,
           '1');
      END;
    END IF;

    -- BONO DE LANZAMIENTO

    BEGIN

      FOR REG0 IN (SELECT LPNA.NUM_LANZ, LPNA.LTME_COD_TIPO_MEDI
                     FROM LEC_PROGR_BONO_LANZA LPNA
                    WHERE LPNA.PAIS_COD_PAIS = psCodigoPais
                      AND LPNA.LPRO_COD_PROG = psCodPrograma
                      AND LPNA.LPBC_CAM_LANZ = psCodigoPeriodo
                      AND LPNA.Ind_Acti = '1') LOOP

        IF REG0.NUM_LANZ IS NOT NULL THEN
          BEGIN
            IF REG0.LTME_COD_TIPO_MEDI = '01' THEN

              --obtener el Objetivo del bono Cumplimiento Penetracion 01
              BEGIN
                SELECT LSOB.LPBN_SEC_BONO_NIVE, LSOB.VAL_OBJE_BONO
                  INTO VNSECBONONIVEL, VNOBJBONO
                  FROM LEC_PROGR_BONO_NIVEL       BN,
                       LEC_LIDER_SECCI_OBJET_BONO LSOB
                 WHERE BN.PAIS_COD_PAIS = LSOB.PAIS_COD_PAIS
                   AND BN.LPRO_COD_PROG = LSOB.LPRO_COD_PROG
                   AND BN.LTBO_COD_TIPO_BONO = LSOB.LTBO_COD_TIPO_BONO
                   AND BN.LNIV_COD_NIVE = LSOB.LNIV_COD_NIVE
                   AND BN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND BN.LPBL_NUM_LANZ = REG0.NUM_LANZ
                   AND BN.SEC_BONO_NIVE = LSOB.LPBN_SEC_BONO_NIVE
                   AND LSOB.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND LSOB.LPRO_COD_PROG = PSCODPROGRAMA
                   AND LSOB.CAM_OBJE = psCodigoPeriodo
                   AND LSOB.COD_REGI = PSCODIGOREGION
                   AND LSOB.COD_ZONA = PSCODIGOZONA
                   AND LSOB.COD_SECC = PSCODIGOSECCION
                   AND LSOB.LTBO_COD_TIPO_BONO = '01'
                   AND LSOB.LNIV_COD_NIVE = VSCODNIVEL;

              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  VNSECBONONIVEL := NULL;
                  VNOBJBONO      := NULL;

              END;

              --obtener el Objetivo del bono Sobrecumplimiento Penetracion 02
              BEGIN
                SELECT LSOB.LPBN_SEC_BONO_NIVE, LSOB.VAL_OBJE_BONO
                  INTO VNSECBONONIVEL2, VNSOBROBJBONO
                  FROM LEC_PROGR_BONO_NIVEL       BN,
                       LEC_LIDER_SECCI_OBJET_BONO LSOB
                 WHERE BN.PAIS_COD_PAIS = LSOB.PAIS_COD_PAIS
                   AND BN.LPRO_COD_PROG = LSOB.LPRO_COD_PROG
                   AND BN.LTBO_COD_TIPO_BONO = LSOB.LTBO_COD_TIPO_BONO
                   AND BN.LNIV_COD_NIVE = LSOB.LNIV_COD_NIVE
                   AND BN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND BN.LPBL_NUM_LANZ = REG0.NUM_LANZ
                   AND BN.SEC_BONO_NIVE = LSOB.LPBN_SEC_BONO_NIVE
                   AND LSOB.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND LSOB.LPRO_COD_PROG = PSCODPROGRAMA
                   AND LSOB.CAM_OBJE = psCodigoPeriodo
                   AND LSOB.COD_REGI = PSCODIGOREGION
                   AND LSOB.COD_ZONA = PSCODIGOZONA
                   AND LSOB.COD_SECC = PSCODIGOSECCION
                   AND LSOB.LTBO_COD_TIPO_BONO = '02'
                   AND LSOB.LNIV_COD_NIVE = VSCODNIVEL;

              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  VNSECBONONIVEL2 := NULL;
                  VNSOBROBJBONO   := NULL;

              END;

              --IF VNSOBROBJBONO IS NOT NULL AND VNOBJBONO IS NOT NULL THEN
              IF VNSOBROBJBONO IS NOT NULL OR VNOBJBONO IS NOT NULL THEN

                -- Obtenemos el nï¿½mero de Pedidos Secciï¿½n Lanzamiento
                SELECT COUNT(DISTINCT sc.clie_oid_clie)
                  INTO vnNroPedSecLanz
                  FROM PED_SOLIC_CABEC       SC,
                       ped_tipo_solic_pais   tsp,
                       ped_tipo_solic        ts,
                       mae_clien_unida_admin cuad,
                       zon_terri_admin       ztad,
                       zon_secci             zscc,
                       zon_zona              zzon,
                       zon_regio             zorg,
                       mae_clien clie
                 WHERE PERD_OID_PERI = vnOidPeriodo
                   AND TSPA_OID_TIPO_SOLI_PAIS = tsp.oid_tipo_soli_pais
                   AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                   AND ts.cod_tipo_soli = 'SOC'
                   AND sc.esso_oid_esta_soli <> 4
                   AND sc.clie_oid_clie = cuad.clie_oid_clie
                   AND cuad.perd_oid_peri_ini <= vnOidPeriodo
                   AND (cuad.perd_oid_peri_fin >= vnOidPeriodo OR
                       cuad.perd_oid_peri_fin IS NULL)
                   AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                   AND ztad.zscc_oid_secc = zscc.oid_secc
                   AND zscc.zzon_oid_zona = zzon.oid_zona
                   AND zzon.zorg_oid_regi = zorg.oid_regi
                   AND zorg.cod_regi = psCodigoRegion
                   AND zzon.cod_zona = pscodigoZona
                   AND zscc.cod_secc = psCodigoSeccion
                   AND cuad.clie_oid_clie = clie.oid_clie
                   AND not exists
                             (select distinct (lpex.cod_cons)
                              from lec_progr_lista_exclu lpex
                              where lpex.pais_cod_pais = psCodigoPais
                              and psCodigoPeriodo between lpex.cam_inic_vige and   lpex.cam_fin_vige
                              and lpex.ind_acti = 1
                              and lpex.cod_cons = clie.cod_clie)
                   AND EXISTS
                 (SELECT NULL
                          FROM ped_solic_posic sp, mae_produ prod
                         WHERE SP.SOCA_OID_SOLI_CABE = SC.OID_SOLI_CABE
                           AND SP.ESPO_OID_ESTA_POSI <> 2
                           AND sp.prod_oid_prod = prod.oid_prod
                           AND prod.cod_sap IN
                               (SELECT LPBLP.COD_SAP
                                  FROM LEC_PROGR_BONO_LANZA_PRODU LPBLP
                                 WHERE LPBLP.PAIS_COD_PAIS = psCodigoPais
                                   AND LPBLP.LPRO_COD_PROG = psCodPrograma
                                   AND LPBLP.LPBC_CAM_LANZ = psCodigoPeriodo
                                   AND LPBLP.LPBL_NUM_LANZ = REG0.NUM_LANZ
                                   AND lpblp.ind_acti = '1'

                                ));

                --Calculamos el estado de Objetivo de Lanzamiento
                IF vnNroPedSecLanz >= vnSobrObjBono THEN
                  vsEstObjLanz := '05';

                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('01', '02');

                  --Insertar Resultado Bono Penetracion Sobre Exigencia 02
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '02',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     NULL,
                     vnNroPedSecLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');

                ELSIF vnNroPedSecLanz >= vnObjBono THEN
                  vsEstObjLanz := '04';
                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('01', '02');

                  --Insertar Resultado Bono Penetracion Cumplimiento 01
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '01',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     NULL,
                     vnNroPedSecLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                ELSE
                  vsEstObjLanz := '06';
                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('01', '02');

                  --Insertar Resultado Bono Penetracion Cumplimiento 01
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '01',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     NULL,
                     vnNroPedSecLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                END IF;

              END IF;
            ELSIF REG0.LTME_COD_TIPO_MEDI = '02' THEN

              --obtener el Objetivo del bono Cumplimiento PUP 03
              BEGIN
                SELECT LSOB.LPBN_SEC_BONO_NIVE, LSOB.VAL_OBJE_BONO
                  INTO VNSECBONONIVEL, VNOBJBONO
                  FROM LEC_PROGR_BONO_NIVEL       BN,
                       LEC_LIDER_SECCI_OBJET_BONO LSOB
                 WHERE BN.PAIS_COD_PAIS = LSOB.PAIS_COD_PAIS
                   AND BN.LPRO_COD_PROG = LSOB.LPRO_COD_PROG
                   AND BN.LTBO_COD_TIPO_BONO = LSOB.LTBO_COD_TIPO_BONO
                   AND BN.LNIV_COD_NIVE = LSOB.LNIV_COD_NIVE
                   AND BN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND BN.LPBL_NUM_LANZ = REG0.NUM_LANZ
                   AND BN.SEC_BONO_NIVE = LSOB.LPBN_SEC_BONO_NIVE
                   AND LSOB.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND LSOB.LPRO_COD_PROG = PSCODPROGRAMA
                   AND LSOB.CAM_OBJE = psCodigoPeriodo
                   AND LSOB.COD_REGI = PSCODIGOREGION
                   AND LSOB.COD_ZONA = PSCODIGOZONA
                   AND LSOB.COD_SECC = PSCODIGOSECCION
                   AND LSOB.LTBO_COD_TIPO_BONO = '03'
                   AND LSOB.LNIV_COD_NIVE = VSCODNIVEL;

              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  VNSECBONONIVEL := NULL;
                  VNOBJBONO      := NULL;

              END;

              --obtener el Objetivo del bono Sobrecumplimiento PUP 04
              BEGIN
                SELECT LSOB.LPBN_SEC_BONO_NIVE, LSOB.VAL_OBJE_BONO
                  INTO VNSECBONONIVEL2, VNSOBROBJBONO
                  FROM LEC_PROGR_BONO_NIVEL       BN,
                       LEC_LIDER_SECCI_OBJET_BONO LSOB
                 WHERE BN.PAIS_COD_PAIS = LSOB.PAIS_COD_PAIS
                   AND BN.LPRO_COD_PROG = LSOB.LPRO_COD_PROG
                   AND BN.LTBO_COD_TIPO_BONO = LSOB.LTBO_COD_TIPO_BONO
                   AND BN.LNIV_COD_NIVE = LSOB.LNIV_COD_NIVE
                   AND BN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND BN.LPBL_NUM_LANZ = REG0.NUM_LANZ
                   AND BN.SEC_BONO_NIVE = LSOB.LPBN_SEC_BONO_NIVE
                   AND LSOB.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND LSOB.LPRO_COD_PROG = PSCODPROGRAMA
                   AND LSOB.CAM_OBJE = psCodigoPeriodo
                   AND LSOB.COD_REGI = PSCODIGOREGION
                   AND LSOB.COD_ZONA = PSCODIGOZONA
                   AND LSOB.COD_SECC = PSCODIGOSECCION
                   AND LSOB.LTBO_COD_TIPO_BONO = '04'
                   AND LSOB.LNIV_COD_NIVE = VSCODNIVEL;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  VNSECBONONIVEL2 := NULL;
                  VNSOBROBJBONO   := NULL;

              END;

              --Si hay Valores para el objetivo se calcula los resultados
              --IF VNOBJBONO IS NOT NULL AND  VNSOBROBJBONO IS NOT NULL  THEN
              IF VNOBJBONO IS NOT NULL OR VNSOBROBJBONO IS NOT NULL THEN
                -- Obtenemos el Nï¿½mero de Unidades de Lanzamiento
                SELECT SUM(SP.NUM_UNID_POR_ATEN)
                  INTO VNNROUNDLANZ
                  FROM PED_SOLIC_CABEC       SC,
                       PED_SOLIC_POSIC       SP,
                       PED_TIPO_SOLIC_PAIS   TSP,
                       PED_TIPO_SOLIC        TS,
                       MAE_CLIEN_UNIDA_ADMIN CUAD,
                       ZON_TERRI_ADMIN       ZTAD,
                       ZON_SECCI             ZSCC,
                       ZON_ZONA              ZZON,
                       ZON_REGIO             ZORG,
                       MAE_PRODU             PROD,
                       MAE_CLIEN             CLIE
                 WHERE SP.SOCA_OID_SOLI_CABE = SC.OID_SOLI_CABE
                   AND PERD_OID_PERI = VNOIDPERIODO
                   AND TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                   AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                   AND TS.COD_TIPO_SOLI = 'SOC'
                   AND SP.ESPO_OID_ESTA_POSI <> 2
                   AND SC.ESSO_OID_ESTA_SOLI <> 4
                   AND SC.CLIE_OID_CLIE = CUAD.CLIE_OID_CLIE
                   AND cuad.perd_oid_peri_ini <= vnOidPeriodo
                   AND (cuad.perd_oid_peri_fin >= vnOidPeriodo OR
                       cuad.perd_oid_peri_fin IS NULL)
                   AND CUAD.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
                   AND ZTAD.ZSCC_OID_SECC = ZSCC.OID_SECC
                   AND ZSCC.ZZON_OID_ZONA = ZZON.OID_ZONA
                   AND ZZON.ZORG_OID_REGI = ZORG.OID_REGI
                   AND ZORG.COD_REGI = PSCODIGOREGION
                   AND ZZON.COD_ZONA = PSCODIGOZONA
                   AND ZSCC.COD_SECC = PSCODIGOSECCION
                   AND cuad.clie_oid_clie = clie.oid_clie
                   AND not exists
                             (select distinct (lpex.cod_cons)
                              from lec_progr_lista_exclu lpex
                              where lpex.pais_cod_pais = psCodigoPais
                              and psCodigoPeriodo between lpex.cam_inic_vige and   lpex.cam_fin_vige
                              and lpex.ind_acti = 1
                              and lpex.cod_cons = clie.cod_clie)
                   AND SP.PROD_OID_PROD = PROD.OID_PROD
                   AND PROD.COD_SAP IN
                       (SELECT LPBLP.COD_SAP
                          FROM LEC_PROGR_BONO_LANZA_PRODU LPBLP
                         WHERE LPBLP.PAIS_COD_PAIS = psCodigoPais
                           AND LPBLP.LPRO_COD_PROG = psCodPrograma
                           AND LPBLP.LPBC_CAM_LANZ = psCodigoPeriodo
                           AND LPBLP.LPBL_NUM_LANZ = REG0.NUM_LANZ
                           AND lpblp.ind_acti = '1');

                --Calculamos el estado de Objetivo de Lanzamiento
                IF vnNroUndLanz >= vnSobrObjBono THEN
                  vsEstObjLanz := '05';
                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('03', '04');

                  --Insertar Resultado Bono PUP Sobrecumplimiento 04
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '04',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     vnNroUndLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                ELSIF vnNroUndLanz >= vnObjBono THEN
                  vsEstObjLanz := '04';
                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('03', '04');

                  --Insertar Resultado Bono PUP cumplimiento 03
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '03',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     vnNroUndLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                ELSE
                  vsEstObjLanz := '06';
                  DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodPrograma
                     AND COD_LIDE = psCodigoLider
                     AND CAM_RESU = psCodigoPeriodo
                     AND LTBO_COD_TIPO_BONO IN ('03', '04');

                  --Insertar Resultado Bono PUP Sobrecumplimiento 04
                  INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_RESU,
                     LTBO_COD_TIPO_BONO,
                     SEC_RESU_BONO,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     NUM_UNID_LANZ_REAL,
                     NUM_PEDI_SECC_LANZ,
                     NUM_INGR_SECC,
                     VAL_RETE_SECC,
                     NUM_CAMP_NIVEL,
                     VAL_CREC_MINI_PEDI,
                     LEOB_COD_ESTA_OBJE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '03',
                     LEC_LRBO_SEQ.NEXTVAL,
                     psCodigoPeriodo,
                     REG0.NUM_LANZ,
                     vnNroUndLanz,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     NULL,
                     vsEstObjLanz,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                END IF;

              END IF;
            END IF;
          END;
        END IF;

      END LOOP;

    END;

    --Se obtiene la Campaï¿½a Inicio Ejecuciï¿½n Bono Nivel Acelerado
    BEGIN
      SELECT CAM_INIC
        INTO vsCamIni
        FROM LEC_PROGR_CAMPA_EXIGE ccn
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodPrograma
         AND IND_TIPO_EXIG = 'N'
         AND ccn.cam_inic <= psCodigoPeriodo
         AND (ccn.cam_fin >= psCodigoPeriodo OR ccn.cam_fin IS NULL);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsCamIni := NULL;
    END;

    IF vsCamIni IS NOT NULL THEN
      BEGIN
        --Calcular el Estado de Bono Cambio Nivel Acelerado
        BEGIN
          SELECT lln.lniv_cod_nive, n.val_peso_nive
            INTO lsCodNivel, lnPesoNivel
            FROM lec_lider_nivel LLN, lec_nivel n
           WHERE lln.lniv_cod_nive = n.cod_nive
             AND LLN.PAIS_COD_PAIS = PSCODIGOPAIS
             AND LLN.LPRO_COD_PROG = PSCODPROGRAMA
             AND LLN.COD_LIDE = PSCODIGOLIDER
             AND LLN.CAM_INIC = psCodigoPeriodo
             AND LLN.IND_TIPO_NIVE = 'R'
             AND LLN.IND_CAMB = 'S'
             AND EXISTS (SELECT NULL
                    FROM lec_progr_bono_nivel bn
                   WHERE bn.pais_cod_pais = lln.PAIS_COD_PAIS
                     AND bn.lpro_cod_prog = lln.lpro_COD_PROG
                     AND bn.ltbo_cod_tipo_bono = '08'
                     AND bn.lniv_cod_nive = lln.lniv_cod_nive
                     AND bn.ind_acti = '1');
        EXCEPTION
          WHEN no_data_found THEN
            lsCodNivel  := NULL;
            lnPesoNivel := NULL;
        END;

        BEGIN
          SELECT val_peso_nive
            INTO lnMaxpesonivel
            FROM (SELECT lln.lniv_cod_nive, n.val_peso_nive
                    FROM LEC_LIDER_NIVEL LLN, lec_nivel n
                   WHERE LLN.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LLN.LPRO_COD_PROG = PSCODPROGRAMA
                     AND LLN.COD_LIDE = PSCODIGOLIDER
                     AND LLN.LNIV_COD_NIVE = n.cod_nive
                     AND LLN.IND_TIPO_NIVE = 'R'
                     AND lln.cam_inic <> psCodigoPeriodo
                   ORDER BY n.val_peso_nive DESC)
           WHERE ROWNUM = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lnMaxpesonivel := NULL;
        END;

        --si subiï¿½ de nivel y si el nivel es mayor al maximo nivel alcanzando
        --se evalua el bono de cambio de nivel acelerado
        IF lsCodNivel IS NOT NULL AND lnPesoNivel > lnMaxpesonivel THEN
          BEGIN
            SELECT NUM_CAMP_MAXI_CAMB, NUM_MINI_INCR_PEDI
              INTO VNTIEMMAXCAMBNIVEL, VNINCRMINPEDI
              FROM LEC_PROGR_BONO_NIVEL LPBN
             WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
               AND LPBN.LPRO_COD_PROG = PSCODPROGRAMA
               AND LPBN.LTBO_COD_TIPO_BONO = '08'
               AND LPBN.LNIV_COD_NIVE = VSCODNIVEL;
          EXCEPTION
            WHEN no_data_found THEN
              VNTIEMMAXCAMBNIVEL := NULL;
              VNINCRMINPEDI      := NULL;
          END;

          BEGIN
            SELECT LLN.CAM_INIC, LLN.CAM_FIN
              INTO VSCAMPININIVEL, VSCAMPFINNIVEL
              FROM LEC_LIDER_NIVEL LLN
             WHERE LLN.PAIS_COD_PAIS = PSCODIGOPAIS
               AND LLN.LPRO_COD_PROG = PSCODPROGRAMA
               AND LLN.COD_LIDE = PSCODIGOLIDER
               AND LLN.CAM_INIC <=
                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                    psCodigoPeriodo,
                                                    -1)
               AND LLN.CAM_FIN >=
                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                    psCodigoPeriodo,
                                                    -1)
               AND LLN.IND_TIPO_NIVE = 'R';
          EXCEPTION
            WHEN no_data_found THEN
              VSCAMPININIVEL := NULL;
              VSCAMPFINNIVEL := NULL;
          END;

          --si la campaï¿½a inicio del nivel fue despues de la campaï¿½a de inicio de ejecuciï¿½n de bono
          IF vsCampIniNivel >= vsCamIni THEN

            vnNroCampMismNivel := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO_PAIS(psCodigoPais,
                                                                              vsCampIniNivel,
                                                                              vsCampFinNivel) + 1;

            --obtener el numero de pedidos en la campaï¿½a fin de nivel
            BEGIN
              SELECT LLSR.NUM_PEDI
                INTO VNNROPEDIREALNIVELFIN
                FROM LEC_LIDER_SECCI_RESUL LLSR
               WHERE LLSR.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND LLSR.LPRO_COD_PROG = PSCODPROGRAMA
                 AND LLSR.COD_LIDE = PSCODIGOLIDER
                 AND LLSR.CAM_RESU = psCodigoPeriodo;

            EXCEPTION
              WHEN no_data_found THEN
                VNNROPEDIREALNIVELFIN := 0;

            END;
            --obtener el nro de pedidos en la campaï¿½a de inicio del nivel
            BEGIN
              SELECT LLSR.NUM_PEDI
                INTO VNNROPEDIREALNIVELINI
                FROM LEC_LIDER_SECCI_RESUL LLSR
               WHERE LLSR.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND LLSR.LPRO_COD_PROG = PSCODPROGRAMA
                 AND LLSR.COD_LIDE = PSCODIGOLIDER
                 AND LLSR.CAM_RESU = VSCAMPININIVEL;
            EXCEPTION
              WHEN no_data_found THEN
                VNNROPEDIREALNIVELINI := 0;
            END;

            vnCreMinPedi := vnNroPediRealNivelFin - vnNroPediRealNivelIni;

          ELSE

            vnNroCampMismNivel := gen_pkg_gener.GEN_FN_DEVUE_DIFER_PERIO_PAIS(psCodigoPais,
                                                                              vsCamIni,
                                                                              vsCampFinNivel) + 1;

            --obtener nro de pedidos en la campaï¿½a fin de nivel
            BEGIN
              SELECT LLSR.NUM_PEDI
                INTO VNNROPEDIREALNIVELFIN
                FROM LEC_LIDER_SECCI_RESUL LLSR
               WHERE LLSR.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND LLSR.LPRO_COD_PROG = PSCODPROGRAMA
                 AND LLSR.COD_LIDE = PSCODIGOLIDER
                 AND LLSR.CAM_RESU = psCodigoPeriodo;
            EXCEPTION
              WHEN no_data_found THEN
                VNNROPEDIREALNIVELFIN := 0;
            END;

            --obtener el nro de pedidos en la campaï¿½a de inicio del nivel
            BEGIN
              SELECT LLSR.NUM_PEDI
                INTO VNNROPEDIREALNIVELINI
                FROM LEC_LIDER_SECCI_RESUL LLSR
               WHERE LLSR.PAIS_COD_PAIS = PSCODIGOPAIS
                 AND LLSR.LPRO_COD_PROG = PSCODPROGRAMA
                 AND LLSR.COD_LIDE = PSCODIGOLIDER
                 AND LLSR.CAM_RESU = vsCamIni;
            EXCEPTION
              WHEN no_data_found THEN
                VNNROPEDIREALNIVELINI := 0;
            END;

            vnCreMinPedi := vnNroPediRealNivelFin - VNNROPEDIREALNIVELINI;

          END IF;

          --se evalua el estado del bono por cambio de nivel acelerado
          IF vnNroCampMismNivel <= vnTiemMaxCambNivel AND
             (vnIncrMinPedi = 0 OR
             (vnIncrMinPedi > 0 AND vnCreMinPedi >= vnIncrMinPedi)) THEN
            vsEstBonoCambNivelAce := '09';
          ELSE
            vsEstBonoCambNivelAce := '10';
          END IF;

          DELETE FROM LEC_LIDER_SECCI_RESUL_BONO
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = psCodPrograma
             AND COD_LIDE = psCodigoLider
             AND CAM_RESU = psCodigoPeriodo
             AND LTBO_COD_TIPO_BONO = '08';

          --Insertar Resultado Bono Cambio Nivel Acelerado
          INSERT INTO LEC_LIDER_SECCI_RESUL_BONO
            (PAIS_COD_PAIS,
             LPRO_COD_PROG,
             COD_REGI,
             COD_ZONA,
             COD_SECC,
             COD_LIDE,
             CAM_RESU,
             LTBO_COD_TIPO_BONO,
             SEC_RESU_BONO,
             LPBC_CAM_LANZ,
             LPBL_NUM_LANZ,
             NUM_UNID_LANZ_REAL,
             NUM_PEDI_SECC_LANZ,
             NUM_INGR_SECC,
             VAL_RETE_SECC,
             NUM_CAMP_NIVEL,
             VAL_CREC_MINI_PEDI,
             LEOB_COD_ESTA_OBJE,
             USU_CREA,
             FEC_CREA,
             USU_MODI,
             FEC_MODI,
             IND_ACTI)
          VALUES
            (PSCODIGOPAIS,
             PSCODPROGRAMA,
             PSCODIGOREGION,
             PSCODIGOZONA,
             PSCODIGOSECCION,
             PSCODIGOLIDER,
             psCodigoPeriodo,
             '08',
             LEC_LRBO_SEQ.NEXTVAL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             vnNroCampMismNivel,
             vnCreMinPedi,
             vsEstBonoCambNivelAce,
             PSCODIGOUSUARIO,
             SYSDATE,
             NULL,
             NULL,
             '1');

        END IF;

      END;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_RESUL_LIDER_ACTUA: ' ||
                              ls_sqlerrm);
  END;

  /*********************************************************
  Descripcion : Obtener las consultoras del Programa Reconocimiento de la secciï¿½n
  Fecha Creacion : 03/02/2014
  Autor : Henry Paredes
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_EXCLU(pscodigomarca   VARCHAR2,
                                  pscodigocanal   VARCHAR2,
                                  psCodigoPeriodo VARCHAR2,
                                  psCodPrograma   VARCHAR2,
                                  psCodigoRegion  VARCHAR2,
                                  pscodigoZona    VARCHAR2,
                                  psCodigoSeccion VARCHAR2) RETURN NUMBER IS

    vntotal      NUMBER;
    vnOidCampAux cra_perio.oid_peri%TYPE;
  BEGIN

    vnOidCampAux := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPeriodo);

    SELECT count(distinct SC.CLIE_OID_CLIE)
      into vntotal
      FROM (SELECT C.*
              FROM ZON_TERRI_ADMIN       ZTA,
                   ZON_SECCI             ZC,
                   ZON_ZONA              ZZ,
                   ZON_REGIO             ZR,
                   MAE_CLIEN_UNIDA_ADMIN UC,
                   MAE_CLIEN             C,
                   LEC_PROGR_LISTA_EXCLU LE,
                   LEC_PROGR             LPRO
             WHERE ZTA.ZSCC_OID_SECC = ZC.OID_SECC
               AND ZC.ZZON_OID_ZONA = ZZ.OID_ZONA
               AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
               AND ZTA.OID_TERR_ADMI = UC.ZTAD_OID_TERR_ADMI
               AND LE.COD_CONS = C.COD_CLIE
               AND UC.CLIE_OID_CLIE = C.OID_CLIE
               AND C.OID_CLIE = UC.CLIE_OID_CLIE
               AND UC.PERD_OID_PERI_INI <= vnOidCampAux
               AND (PERD_OID_PERI_INI >= vnOidCampAux OR
                   PERD_OID_PERI_FIN IS NULL)
               AND ZZ.Cod_Zona = pscodigoZona
               AND zr.cod_regi = psCodigoRegion
               AND zc.cod_secc = psCodigoSeccion
               AND psCodigoPeriodo between LPRO.CAM_INIC and
                   nvl(LPRO.CAM_FIN, psCodigoPeriodo)
               AND LPRO.COD_PROG = LE.LPRO_COD_PROG
               AND LE.COD_CONS = C.COD_CLIE
               AND psCodigoPeriodo between LE.CAM_INIC_VIGE and
                   nvl(LE.CAM_FIN_VIGE, psCodigoPeriodo)) A,
           PED_SOLIC_CABEC SC,
           PED_TIPO_SOLIC TS,
           PED_TIPO_SOLIC_PAIS TSP
     WHERE A.OID_CLIE = SC.CLIE_OID_CLIE
       AND SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
       AND SC.PERD_OID_PERI = vnOidCampAux
       AND SC. FEC_FACT IS NOT NULL
       AND SC.IND_TS_NO_CONSO = 1
       AND SC.IND_OC = 1
       AND SC.IND_PEDI_PRUE = 0
       AND TS.OID_TIPO_SOLI = TSP.TSOL_OID_TIPO_SOLI
       AND TS.IND_DEVO = 0
       AND TS.IND_ANUL = 0;
    RETURN vntotal;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_FN_OBTE_PEDI_EXCLU: ' ||
                              ls_sqlerrm);
      RETURN '';
  END LEC_FN_OBTE_PEDI_EXCLU;

  /*********************************************************
  Descripcion : Obtener pedidos Reales
  Fecha Creacion : 04/02/2014
  Autor : Henry Paredes
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_REAL(pscodigomarca   VARCHAR2,
                                 pscodigocanal   VARCHAR2,
                                 psCodigoPeriodo VARCHAR2,
                                 psCodPrograma   VARCHAR2,
                                 psCodigoRegion  VARCHAR2,
                                 pscodigoZona    VARCHAR2,
                                 psCodigoSeccion VARCHAR2) RETURN NUMBER IS
    vnCodigoPrograma   LEC_PROGR.COD_PROG%TYPE;
    vnOidZonaLider     ZON_ZONA.OID_ZONA%TYPE;
    vnOidSeccionLider  ZON_SECCI.OID_SECC%TYPE;
    vnOidTerrAdmiLider ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
    vnNumeroPedido     INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnNumPedidosReales INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI%TYPE;
    vnIndProgr         LEC_PROGR.IND_PROG_RECO%TYPE;
    vnSumaPedidos      NUMBER := 0;
    vnpedidosexcluidas NUMBER := 0;
    vnpedidosreales    NUMBER := 0;
    vnAcumzonaSeccion  NUMBER := 0;
    vnAcumZona         NUMBER := 0;
    vnOidCampAux       cra_perio.oid_peri%TYPE;
  BEGIN

    vnOidCampAux := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodigoPeriodo);

    SELECT zz.OID_ZONA
      INTO vnOidZonaLider
      FROM ZON_ZONA zz
     WHERE zz.COD_ZONA = pscodigoZona;

    SELECT zs.OID_SECC
      INTO vnOidSeccionLider
      FROM ZON_SECCI zs
     WHERE zs.ZZON_OID_ZONA = vnOidZonaLider
       AND zs.COD_SECC = psCodigoSeccion
       AND (vnOidCampAux >= zs.PERD_OID_PERI_INIC or
           zs.PERD_OID_PERI_INIC is null)
       AND (vnOidCampAux <= zs.PERD_OID_PERI_FINA OR
           zs.PERD_OID_PERI_FINA IS NULL);

    --se obtiene el numero de pedidos de la seccion en la campaï¿½a de proceso
    SELECT sum(nvl(NUM_PEDI, 0))
      INTO vnSumaPedidos
      FROM INT_FUENT_VENTA_REAL_VACUM
     WHERE terr_oid_terr in
           (SELECT terr_oid_terr
              FROM ZON_TERRI_ADMIN zta
             WHERE zta.zscc_oid_secc = vnOidSeccionLider
               AND (vnOidCampAux >= PERD_OID_PERI_INIC or
                   PERD_OID_PERI_INIC is null)
               AND (vnOidCampAux <= PERD_OID_PERI_FINA OR
                   PERD_OID_PERI_FINA IS null))
       AND perd_oid_peri = vnOidCampAux;
    BEGIN
      SELECT c.IND_PROG_RECO
        INTO vnIndProgr
        FROM LEC_PROGR c
       WHERE c.cod_prog = psCodPrograma;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vnIndProgr := 0;
    END;

    IF vnIndProgr = 1 THEN
      vnpedidosexcluidas := LEC_FN_OBTE_PEDI_EXCLU(pscodigomarca,
                                                   pscodigocanal,
                                                   psCodigoPeriodo,
                                                   psCodPrograma,
                                                   psCodigoRegion,
                                                   pscodigoZona,
                                                   psCodigoSeccion);
    ELSE
      vnpedidosexcluidas := 0;
    END IF;

    vnpedidosreales := vnSumaPedidos - vnpedidosexcluidas;

    return vnpedidosreales;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_FN_OBTE_PEDI_REAL: ' || ls_sqlerrm);
      RETURN '';
  END LEC_FN_OBTE_PEDI_REAL;

  /***************************************************************************
      Descripcion       : Proceso Calculo de Productividad
      Fecha Creacion    : 11/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_PRODU_LIDER(psCodigoPais     VARCHAR2,
                                     pscodigomarca    VARCHAR2,
                                     pscodigocanal    VARCHAR2,
                                     psCodigoPeriodo  VARCHAR2,
                                     psCodigoPrograma VARCHAR2,
                                     psCodigoRegion   VARCHAR2,
                                     pscodigoZona     VARCHAR2,
                                     psCodigoSeccion  VARCHAR2,
                                     psCodigoLider    VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS
    vnCodigoEstadoObjetivo     LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;
    vnCodigoEstadoObjetivoIngr LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE; -- Nuevo
    vnCodigoEstadoObjetivoCapi LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;  ---PER-SICC-2015-0548
    vnEstadoDesemCamp          LEC_TIPO_DESEM.COD_TIPO_DESE%TYPE;
    vnCamEval                  LEC_PROGR_ETAPA_CAMPA.CAM_EVAL%TYPE;
    vnNumCamEval               LEC_PROGR.NUM_CAMP_EVAL%TYPE;
    vnCamInicEtap              varchar2(11);
    vsIndiDeseNue              LEC_PROGR.Ind_Dese_Nuev%TYPE;
    vnNroCampExisLid           number;
    vnCodEstDesem              LEC_PROGR_DESEM.Ltde_Cod_Tipo_Dese%TYPE;
    vnExiste                   number;
    vnDifCamp                  number;
    vndifer                    number;
    vnIndCapi                  number;
    vnIndIngr                  number;
    vsclasif                   Lec_Lider_Clasi.Lccl_Cod_Clas%TYPE;
    vsCampIni                  Lec_Lider_Clasi.Cam_Inic%TYPE;
    lsIndMetaIngrCap           LEC_PROGR.Ind_Dese_Ingr_Capi%TYPE;
    vnIndNueExi                LEC_PROGR.Ind_Nuev_Exit%Type;
  BEGIN

    BEGIN
      SELECT NVL(p.ind_dese_ingr_capi, '0'), nvl(p.ind_dese_meta_ingr,'0' ),  ------PER-SICC-2015-0548 ----
             NUM_CAMP_EVAL, IND_DESE_NUEV, nvl(p.IND_NUEV_EXIT,0)
        INTO lsIndMetaIngrCap, vnIndIngr, vnNumCamEval, vsIndiDeseNue, vnIndNueExi -- Nuevo
        FROM LEC_PROGR p
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND COD_PROG = psCodigoPrograma;        
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsIndMetaIngrCap := '0';
        vnIndIngr := '0';
    END;

    BEGIN
      SELECT LEOB_COD_ESTA_OBJE, LEOB_COD_ESTA_OBJE_INGR, LEOB_COD_ESTA_OBJE_CAPI   --- PER-SICC-2015-0548
        INTO vnCodigoEstadoObjetivo, vnCodigoEstadoObjetivoIngr, vnCodigoEstadoObjetivoCapi    ------PER-SICC-2015-0548
        FROM LEC_LIDER_SECCI_RESUL
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND COD_REGI = psCodigoRegion
         AND COD_ZONA = pscodigoZona
         AND COD_SECC = psCodigoSeccion
         AND COD_LIDE = psCodigoLider
         AND CAM_RESU = psCodigoPeriodo;
    EXCEPTION
      WHEN no_data_found THEN
        vnCodigoEstadoObjetivo     := NULL;
        vnCodigoEstadoObjetivoIngr := NULL;
        vnCodigoEstadoObjetivoCapi := NULL; 
    END;
    
    BEGIN
      SELECT nvl(pn.ind_cond_capi,0)    ------PER-SICC-2015-0548  todo
        INTO vnIndCapi   
        FROM lec_lider_nivel lln, lec_progr_nivel pn
       WHERE lln.PAIS_COD_PAIS = psCodigoPais
         AND lln.LPRO_COD_PROG = psCodigoPrograma
         AND pn.lpro_cod_prog(+) = lln.lpro_cod_prog
         AND pn.lniv_cod_nive(+) = lln.lniv_cod_nive
         AND COD_LIDE = psCodigoLider
         AND LLN.IND_TIPO_NIVE = 'R'
         AND psCodigoPeriodo >= CAM_INIC
         AND (psCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
          vnIndCapi := 0;
    END;

    DELETE FROM LEC_LIDER_DESEM x
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma
       AND LPEC_CAM_EVAL = psCodigoPeriodo
       AND COD_LIDE = psCodigoLider;

    IF vnCodigoEstadoObjetivo IS NOT NULL THEN

      IF (vnCodigoEstadoObjetivo = '01' AND lsIndMetaIngrCap = 0 )   
          OR           
         (vnCodigoEstadoObjetivo = '01' AND lsIndMetaIngrCap = '1' AND vnCodigoEstadoObjetivoIngr = '01' AND vnIndIngr = 1 )       
          OR 
         (vnCodigoEstadoObjetivo = '01' AND lsIndMetaIngrCap = '1' AND vnCodigoEstadoObjetivoCapi = '01' AND vnIndCapi = 1 )   THEN     --- PER-SICC-2015-0548
         vnEstadoDesemCamp := '01';
      ELSE
         vnEstadoDesemCamp := '02';
      END IF;

      INSERT INTO LEC_LIDER_DESEM
        (PAIS_COD_PAIS,
         LPRO_COD_PROG,
         LPEC_CAM_EVAL,
         COD_REGI,
         COD_ZONA,
         COD_SECC,
         COD_LIDE,
         IND_TIPO_DESE,
         LTDE_COD_TIPO_DESE,
         NUM_CAMP_EXIT,
         USU_CREA,
         FEC_CREA,
         IND_ACTI)
      VALUES
        (PSCODIGOPAIS,
         PSCODIGOPROGRAMA,
         psCodigoPeriodo,
         PSCODIGOREGION,
         PSCODIGOZONA,
         PSCODIGOSECCION,
         PSCODIGOLIDER,
         'C',
         vnEstadoDesemCamp,
         NULL,
         PSCODIGOUSUARIO,
         SYSDATE,
         '1');

      BEGIN
        SELECT CAM_EVAL
          INTO vnCamEval
          FROM LEC_PROGR_ETAPA_CAMPA
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodigoPrograma
           AND CAM_EVAL = psCodigoPeriodo;
       EXCEPTION
        WHEN no_data_found THEN
          vnCamEval    := NULL;
      END;

      IF vnCamEval IS NOT NULL THEN
        vnDifCamp := (vnNumCamEval - 1) * -1;

        IF vnCamEval = psCodigoPeriodo THEN

          SELECT GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais,
                                                  psCodigoPeriodo,
                                                  vnDifCamp)
            INTO vnCamInicEtap
            FROM dual;

          /*    DELETE FROM LEC_LIDER_DESEM x
          WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodigoPrograma
           AND LPEC_CAM_EVAL = vnCamEval
           AND x.ind_tipo_dese = 'E'
           AND COD_LIDE      = psCodigoLider;  */

          SELECT CL.LCCL_COD_CLAS, CL.CAM_INIC, sb.num_camp
            INTO vsClasif, vsCampIni, vnDifer
            FROM LEC_LIDER_CLASI CL, LEC_SUBCL sb
           WHERE CL.COD_LIDE = psCodigoLider
             AND psCodigoPeriodo >= CL.CAM_INIC
             AND (psCodigoPeriodo <= CL.CAM_FIN OR CL.CAM_FIN IS NULL)
             AND CL.lccl_cod_clas = sb.lccl_cod_clas
             AND lscl_cod_subc = sb.cod_subc;

         -- vnDifer := gen_pkg_gener.gen_fn_devue_difer_perio_PAIS(psCodigoPais,
          --                                                       vsCampIni,
          ---                                                       psCodigoPeriodo) + 1;

          IF vsClasif = '02' THEN

            IF vnDifer >= vnNumCamEval THEN
              --  Obtener Numero de campaï¿½as como Exitosa
              SELECT COUNT(*)
                INTO vnNroCampExisLid
                FROM LEC_LIDER_DESEM x
               WHERE PAIS_COD_PAIS = psCodigoPais
                 AND COD_LIDE = psCodigoLider
                 AND LPEC_CAM_EVAL >= vnCamInicEtap
                 AND LPEC_CAM_EVAL <= psCodigoPeriodo
                 AND IND_TIPO_DESE = 'C'
                 AND ltde_cod_tipo_dese = '01';
            END IF;
            IF vsIndiDeseNue = '1' THEN
              IF vnDifer <= 6 THEN
                vnDifer := 0;

                --  Obtener Numero de campaï¿½as como Exitosa Lider Nueva
                SELECT COUNT(*) A,
                       SUM((SELECT (CASE
                                    WHEN CL.LCCL_COD_CLAS = '01' AND vnIndNueExi = 1 THEN
                                     1
                                    ELSE
                                     CASE
                                       WHEN LD.LTDE_COD_TIPO_DESE = '01' THEN
                                        1
                                       ELSE
                                        0
                                     END
                                  END) CONT
                             FROM LEC_LIDER_CLASI CL
                            WHERE CL.COD_LIDE = psCodigoLider
                              AND LD.LPEC_CAM_EVAL >= CL.CAM_INIC
                              AND (LD.LPEC_CAM_EVAL <= CL.CAM_FIN OR
                                  CL.CAM_FIN IS NULL))) B
                  INTO vnDifer, vnNroCampExisLid
                  FROM LEC_LIDER_DESEM LD
                 WHERE LD.PAIS_COD_PAIS = psCodigoPais
                   AND LD.COD_LIDE = psCodigoLider
                   AND LD.LPEC_CAM_EVAL BETWEEN vnCamInicEtap AND
                       psCodigoPeriodo
                   AND LD.IND_TIPO_DESE = 'C';

              END IF;
            END IF;
            -- A este punto se recalculï¿½ vnDifer
            IF vnDifer >= vnNumCamEval THEN

              BEGIN
                SELECT LTDE_COD_TIPO_DESE
                  INTO vnCodEstDesem
                  FROM LEC_PROGR_DESEM
                 WHERE LPRO_COD_PROG = psCodigoPrograma
                   AND vnNroCampExisLid >= RAN_INIC
                   AND vnNroCampExisLid <= RAN_FIN;
              EXCEPTION
                WHEN no_data_found THEN
                  vnCodEstDesem := NULL;
              END;

              IF vnCodEstDesem IS NOT NULL THEN

                INSERT INTO LEC_LIDER_DESEM
                  (PAIS_COD_PAIS,
                   LPRO_COD_PROG,
                   LPEC_CAM_EVAL,
                   COD_REGI,
                   COD_ZONA,
                   COD_SECC,
                   COD_LIDE,
                   IND_TIPO_DESE,
                   LTDE_COD_TIPO_DESE,
                   NUM_CAMP_EXIT,
                   USU_CREA,
                   FEC_CREA,
                   IND_ACTI)
                VALUES
                  (PSCODIGOPAIS,
                   PSCODIGOPROGRAMA,
                   VNCAMEVAL,
                   PSCODIGOREGION,
                   PSCODIGOZONA,
                   PSCODIGOSECCION,
                   PSCODIGOLIDER,
                   'E',
                   VNCODESTDESEM,
                   VNNROCAMPEXISLID,
                   PSCODIGOUSUARIO,
                   SYSDATE,
                   '1');
              END IF;

            END IF;

          END IF;

        END IF;

      END IF;

    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_PRODU_LIDER: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_PRODU_LIDER;

  /***************************************************************************
      Descripcion       : Proceso Calculo de Baja
      Fecha Creacion    : 11/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  /*    PROCEDURE LEC_PR_CALCU_LIDER_BAJA(psCodigoPais      VARCHAR2,
                                       pscodigomarca      VARCHAR2,
                                       pscodigocanal      VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       pstipoproceso      VARCHAR2,
                                       psCodigoRegion     VARCHAR2,
                                       psCodigoUsuario    VARCHAR2
                                       )
      IS
       vnOidCampAux                 cra_perio.oid_peri%TYPE;
       vnNumCamEval                 LEC_PROGR.NUM_CAMP_EVAL%type;
       vnNumBajaAuto                LEC_PROGR.NUM_BAJA_AUTO%type;
       vnOidPeriodo                 CRA_PERIO.OID_PERI%TYPE;
       vsCodProg                    LEC_PROGR.COD_PROG%TYPE;
       vsCodGerente                 ZON_HISTO_GEREN.GERE%TYPE;
       vsCodRegion                  ZON_REGIO.COD_REGI%TYPE;
       vsCodZona                    ZON_ZONA.Cod_Zona%TYPE;
       vsCodSecc                    Zon_Secci.Cod_Secc%TYPE;
       vnOidCliente                 MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE%TYPE;
       vsEstaOidClie                MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
       vsMotivoBaja                 NUMBER;
       vsIndBaja                    NUMBER;
       vsNumLiderCrit               NUMBER;
       vsCampIni                    varchar2(6);
       vsCampFin                    varchar2(6);
       lnNumBaja                    NUMBER;
       lsNumPerioCriti              BAS_PARAM_PAIS.VAL_PARA%TYPE;
       lsCampEvalAnte               LEC_PROGR_ETAPA_CAMPA.CAM_EVAL%TYPE;

      BEGIN

      vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

       BEGIN
       SELECT COD_PROG,
              nvl(NUM_CAMP_EVAL,0),
              nvl(NUM_BAJA_AUTO,0)
         INTO vsCodProg,
              vnNumCamEval,
              vnNumBajaAuto
       FROM LEC_PROGR
       WHERE  psCodigoPeriodo  >= cam_inic
          AND (psCodigoPeriodo <= cam_fin  OR  cam_fin is null)
          AND IND_ACTI='1';

        EXCEPTION
           WHEN NO_DATA_FOUND THEN
            vsCodProg := NULL;
        END;

          BEGIN
              SELECT VAL_PARA INTO lsNumPerioCriti
              FROM BAS_PARAM_PAIS
              WHERE COD_PAIS = psCodigoPais
              AND COD_SIST = 'LET'
              AND NOM_PARA = 'numPerioCriti'
              AND IND_ACTI = '1';
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
              lsNumPerioCriti := NULL;
          END;

          -- busca la campaï¿½a inmediatamente anterior al de mayor valor y se guarda en la variable
          BEGIN
              SELECT X.CAM_EVAL
              INTO lsCampEvalAnte
              FROM (
                  SELECT ROWNUM NRO, CAM_EVAL
                  FROM (
                      SELECT CAM_EVAL
                      FROM LEC_PROGR_ETAPA_CAMPA
                      WHERE PAIS_COD_PAIS = psCodigoPais
                      AND LPRO_COD_PROG = vsCodProg
                      AND CAM_EVAL <= psCodigoPeriodo
                      ORDER BY CAM_EVAL DESC
                  ) T
              ) X
              WHERE X.NRO = 2;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
              lsCampEvalAnte := NULL;
          END;


      IF vsCodProg IS NOT NULL  THEN
         --EVALUACION BAJA POR EGRESO REGION
            IF pstipoproceso = 'R' THEN
            DELETE FROM LEC_LIDER_BAJA LB
                  WHERE LB.LPRO_COD_PROG = vsCodProg
                    AND LB.COD_REGI      = psCodigoRegion
                    AND LB.CAM_BAJA      = psCodigoPeriodo;
            FOR REG IN (SELECT DISTINCT(GERE),
                               COD_REGI,
                               COD_ZONA,
                               cod_secc
                          FROM ZON_HISTO_GEREN
                         WHERE vnOidPeriodo >= PERD_OID_PERI_DESD
                           AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                           AND COD_SECC IS NOT NULL
                           AND (psCodigoRegion is NULL OR COD_REGI = psCodigoRegion)
                        )
               LOOP
                   vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(REG.GERE);
                   BEGIN
                    SELECT ESTA_OID_ESTA_CLIE
                    INTO vsEstaOidClie
                    FROM MAE_CLIEN_HISTO_ESTAT
                    WHERE CLIE_OID_CLIE = vnOidCliente
                        AND vnOidPeriodo = PERD_OID_PERI;
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                         vsEstaOidClie := NULL;
                    END;

                      IF (vsEstaOidClie = 5 OR vsEstaOidClie = 7 ) THEN
                        vsMotivoBaja := 1;
                        vsIndBaja := 1;
                       --Obtener Baja Lider
                   LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                          pscodigomarca,
                                          pscodigocanal,
                                          psCodigoPeriodo,
                                          pstipoproceso,
                                          REG.COD_REGI,
                                          REG.COD_ZONA,
                                          REG.COD_SECC,
                                          REG.GERE,
                                          vsMotivoBaja,
                                          vsIndBaja,
                                          vsCodProg,
                                                psCodigoUsuario );
                END IF;
          END LOOP;
             ELSE
           DELETE FROM LEC_LIDER_BAJA LB
                 WHERE LB.LPRO_COD_PROG = vsCodProg
                   AND LB.CAM_BAJA      = psCodigoPeriodo;
           --EVALUACION BAJA POR EGRESO CAMPAï¿½A
           FOR REG IN (SELECT DISTINCT(GERE),
                                  COD_REGI,
                                  COD_ZONA,
                                  cod_secc
                             FROM ZON_HISTO_GEREN
                            WHERE vnOidPeriodo >= PERD_OID_PERI_DESD
                              AND (vnOidPeriodo <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL)
                              AND COD_SECC IS NOT NULL
                           )
               LOOP
                    vnOidCliente :=  Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(REG.GERE);
                    BEGIN
                     SELECT ESTA_OID_ESTA_CLIE
                     INTO vsEstaOidClie
                     FROM MAE_CLIEN_HISTO_ESTAT
                     WHERE CLIE_OID_CLIE = vnOidCliente
                              AND vnOidPeriodo = PERD_OID_PERI ;
                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                         vsEstaOidClie := NULL;
                    END;

                      IF (vsEstaOidClie = 5 OR vsEstaOidClie = 7) THEN
                      vsMotivoBaja := 1;
                      vsIndBaja := 1;
                       --Obtener Baja Lider
                      LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                             pscodigomarca,
                                             pscodigocanal,
                                             psCodigoPeriodo,
                                             pstipoproceso,
                                             REG.COD_REGI,
                                             REG.COD_ZONA,
                                             REG.COD_SECC,
                                             REG.GERE,
                                             vsMotivoBaja,
                                             vsIndBaja,
                                             vsCodProg,
                                                psCodigoUsuario );
                END IF;
          END LOOP;

           --EVALUACION BAJA POR DESEMPEï¿½O

                IF  vnNumCamEval > 0 THEN
          SELECT COUNT(1)
          INTO vsNumLiderCrit
           FROM LEC_LIDER_DESEM LD
          WHERE PAIS_COD_PAIS      = psCodigoPais
            AND LPRO_COD_PROG      = vsCodProg
            AND LPEC_CAM_EVAL      = psCodigoPeriodo
                      AND LTDE_COD_TIPO_DESE = '05'
            AND IND_TIPO_DESE      = 'E'
            AND NOT EXISTS ( SELECT NULL
                               FROM LEC_LIDER_BAJA B
                              WHERE B.PAIS_COD_PAIS = LD.PAIS_COD_PAIS
                                AND B.LPRO_COD_PROG = LD.LPRO_COD_PROG
                                AND B.COD_REGI      = LD.COD_REGI
                                AND B.COD_ZONA      = LD.COD_ZONA
                                AND B.COD_SECC      = LD.COD_SECC
                                AND B.COD_LIDE      = LD.COD_LIDE
                                AND B.CAM_BAJA      = LD.LPEC_CAM_EVAL
                                AND b.ltde_cod_tipo_desv <> 6
                            );

           IF vsNumLiderCrit <= vnNumBajaAuto AND vnNUmBajaAuto > 0 THEN
             FOR REG IN ( SELECT COD_LIDE,
                                 COD_REGI,
                                 COD_ZONA,
                                 COD_SECC
                            FROM LEC_LIDER_DESEM LD
                           WHERE PAIS_COD_PAIS      = psCodigoPais
                             AND LPRO_COD_PROG      = vsCodProg
                             AND LPEC_CAM_EVAL      = psCodigoPeriodo
                             AND LTDE_COD_TIPO_DESE = '03'
                             AND IND_TIPO_DESE      = 'E'
                             AND NOT EXISTS ( SELECT NULL
                                                FROM LEC_LIDER_BAJA B
                                               WHERE B.PAIS_COD_PAIS = LD.PAIS_COD_PAIS
                                                 AND B.LPRO_COD_PROG = LD.LPRO_COD_PROG
                                                 AND B.COD_REGI      = LD.COD_REGI
                                                 AND B.COD_ZONA      = LD.COD_ZONA
                                                 AND B.COD_SECC      = LD.COD_SECC
                                                 AND B.COD_LIDE      = LD.COD_LIDE
                                                 AND B.CAM_BAJA      = LD.LPEC_CAM_EVAL
                                                 AND b.ltde_cod_tipo_desv <> 6
                                            )
                         )
               LOOP
                vsMotivoBaja := 6;
                 vsIndBaja := 1;
                  --Obtener Baja Lider
                          LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                       pscodigomarca,
                                       pscodigocanal,
                                       psCodigoPeriodo,
                                       pstipoproceso,
                                       REG.COD_REGI,
                                       REG.COD_ZONA,
                                       REG.COD_SECC,
                                       REG.COD_LIDE,
                                       vsMotivoBaja,
                                       vsIndBaja,
                                       vsCodProg,
                                                          psCodigoUsuario );
           END LOOP;


        ELSE
             vsCampIni := Gen_Pkg_Gener.GEN_FN_PERIO_NSIGU(psCodigoPais,psCodigoPeriodo,-(vnNumCamEval-1));
          vsCampFin := psCodigoPeriodo;
             lnNumBaja := 0;

                         FOR REG IN (

                              SELECT
                              TAB.PAIS_COD_PAIS,
                              TAB.LPRO_COD_PROG,
                              TAB.COD_REGI,
                              TAB.COD_ZONA,
                              TAB.COD_SECC,
                              TAB.COD_LIDE,
                              TAB.IND_CRICA,
                              TAB.DIF_OBRE
                              FROM (
                                  SELECT
                                  LDS.PAIS_COD_PAIS,
                                  LDS.LPRO_COD_PROG,
                                  LDS.COD_REGI,
                                  LDS.COD_ZONA,
                                  LDS.COD_SECC,
                                  LDS.COD_LIDE,
                                  (
                                      SELECT
                                      (CASE COUNT(*) WHEN 0 THEN 0 ELSE 1 END)
                                      FROM LEC_LIDER_DESEM LDSA
                                      WHERE LDSA.PAIS_COD_PAIS = LDS.PAIS_COD_PAIS
                                      AND LDSA.LPRO_COD_PROG = LDS.LPRO_COD_PROG
                                      AND LDSA.LPEC_CAM_EVAL = vsCampIni
                                      AND LDSA.LTDE_COD_TIPO_DESE = LDS.LTDE_COD_TIPO_DESE
                                      AND LDSA.IND_TIPO_DESE = 'E'
                                  ) IND_CRICA,
                                  NVL((
                                      SELECT SUM(LSR.NUM_PEDI)
                                      FROM LEC_LIDER_SECCI_RESUL LSR
                                      WHERE LSR.PAIS_COD_PAIS = LDS.PAIS_COD_PAIS
                                      AND LSR.LPRO_COD_PROG = LDS.LPRO_COD_PROG
                                      AND LSR.COD_LIDE = LDS.COD_LIDE
                                      AND LSR.CAM_RESU BETWEEN vsCampIni AND vsCampFin
                                  ), 0) -
                                  NVL((
                                      SELECT SUM(LOP.NUM_PEDI_OBJE_FINA)
                                      FROM LEC_LIDER_SECCI_OBJET_PEDID LOP
                                      WHERE LOP.LPRO_COD_PAIS = LDS.PAIS_COD_PAIS
                                      AND LOP.LPRO_COD_PROG = LDS.LPRO_COD_PROG
                                      AND LOP.COD_REGI = LDS.COD_REGI
                                      AND LOP.COD_ZONA = LDS.COD_ZONA
                                      AND LOP.COD_SECC = LDS.COD_SECC
                                      AND LOP.CAM_OBJE BETWEEN vsCampIni AND psCodigoPeriodo
                                  ), 0) DIF_OBRE
                                  FROM LEC_LIDER_DESEM LDS
                                  WHERE LDS.PAIS_COD_PAIS = psCodigoPais
                                  AND LDS.LPRO_COD_PROG = vsCodProg
                                  AND LDS.LPEC_CAM_EVAL = psCodigoPeriodo
                                  AND LDS.LTDE_COD_TIPO_DESE = '05'
                                  AND LDS.IND_TIPO_DESE = 'C') TAB
                              ORDER BY TAB.IND_CRICA DESC, TAB.DIF_OBRE DESC)
           LOOP
                vsMotivoBaja := 6;
                 vsIndBaja := 1;
                --Registrar Baja Lider
                            LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                       pscodigomarca,
                                       pscodigocanal,
                                       psCodigoPeriodo,
                                       pstipoproceso,
                                       REG.COD_REGI,
                                       REG.COD_ZONA,
                                       REG.COD_SECC,
                                       REG.COD_LIDE,
                                       vsMotivoBaja,
                                       vsIndBaja,
                                       reg.lpro_cod_prog,
                                                            psCodigoUsuario );

                   lnNumBaja := lnNumBaja + 1;

                  EXIT WHEN lnNumBaja = vnNumBajaAuto;

           END LOOP;

         END IF;
                END IF;
         END IF;


       END IF;

         EXCEPTION
       WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR LEC_PR_CALCU_LIDER_BAJA: '||ls_sqlerrm);

      END LEC_PR_CALCU_LIDER_BAJA;
  */

  /***************************************************************************
      Descripcion       : Proceso Calculo de Baja (Modificado)
      Fecha Creacion    : 10/12/2014
      Autor             : Carlos Mori
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_LIDER_BAJA(psCodigoPais    VARCHAR2,
                                    pscodigomarca   VARCHAR2,
                                    pscodigocanal   VARCHAR2,
                                    psCodigoPeriodo VARCHAR2,
                                    pstipoproceso   VARCHAR2,
                                    psCodigoRegion  VARCHAR2,
                                    psCodigoUsuario VARCHAR2) IS

    lnNumCamEval         LEC_PROGR.NUM_CAMP_EVAL%type;
    lnNumBajaAuto        LEC_PROGR.NUM_BAJA_AUTO%type;
    vnOidPeriodo         CRA_PERIO.OID_PERI%TYPE;
    lsCodigoPrograma     LEC_PROGR.COD_PROG%TYPE;
    vsMotivoBaja         NUMBER;
    vsIndBaja            NUMBER;
    vsNumLiderCrit       NUMBER;
    lnNumPerioCriti      INTEGER;
    lsCampEvalAnte       LEC_PROGR_ETAPA_CAMPA.CAM_EVAL%TYPE;
    lnIndAplicaBajaDesem INTEGER;

    CURSOR c_lideres(vnOidCampanna NUMBER) IS
      SELECT gere.gere cod_lide,
             gere.cod_regi,
             gere.cod_zona,
             gere.cod_secc,
             peri.cod_peri cod_peri_ini,
             CASE
               WHEN gere.perd_oid_peri_hast IS NOT NULL THEN
                peri_2.cod_peri
               ELSE
                NULL
             END cod_peri_fin,
             clhe.esta_oid_esta_clie
        FROM zon_histo_geren       gere,
             cra_perio             perd,
             seg_perio_corpo       peri,
             cra_perio             perd_2,
             seg_perio_corpo       peri_2,
             mae_clien             clie,
             mae_clien_histo_estat clhe
       WHERE gere.gere = clie.cod_clie
         AND clie.oid_clie = clhe.clie_oid_clie
         AND gere.perd_oid_peri_desd = perd.oid_peri
         AND perd.peri_oid_peri = peri.oid_peri
         AND NVL(gere.perd_oid_peri_hast, vnOidCampanna) = perd_2.oid_peri
         AND perd_2.peri_oid_peri = peri_2.oid_peri
            --
         AND clhe.perd_oid_peri = vnOidCampanna
         AND clhe.esta_oid_esta_clie IN (5, 7)
         AND gere.cod_regi IS NOT NULL
         AND gere.cod_zona IS NOT NULL
         AND gere.cod_secc IS NOT NULL
         AND psCodigoPeriodo BETWEEN peri.cod_peri AND peri_2.cod_peri
         AND ((psTipoProceso = 'R' AND gere.cod_regi = psCodigoRegion) OR
             psTipoProceso = 'P');

    TYPE c_Lideres_t IS TABLE OF c_Lideres%ROWTYPE INDEX BY BINARY_INTEGER;
    regLider c_Lideres_t;

    CURSOR c_criticas(vnOidCampanna        NUMBER,
                      vsCampEvalAnte       VARCHAR2,
                      vsCodTipoDesemp      VARCHAR2,
                      vnNumPerioCriti      NUMBER,
                      vsTipoEvalua         VARCHAR2,
                      vnNumBajaAuto        NUMBER,
                      vsCodigoPrograma     VARCHAR2,
                      vnIndAplicaBajaDesem NUMBER) IS WITH t_criti AS(
      SELECT lide.cod_lide,
             lide.cod_regi,
             lide.cod_zona,
             lide.cod_secc,
             NVL((SELECT SUM(lisr.num_pedi)
                   FROM lec_lider_secci_resul lisr
                  WHERE lisr.pais_cod_pais = crit.pais_cod_pais
                    AND lisr.lpro_cod_prog = crit.lpro_cod_prog
                    AND lisr.cod_lide = crit.cod_lide
                    AND lisr.cam_resu BETWEEN vsCampEvalAnte AND
                        psCodigoPeriodo),
                 0) - NVL((SELECT SUM(lsop.num_pedi_obje_fina)
                            FROM lec_lider_secci_objet_pedid lsop
                           WHERE lsop.lpro_cod_pais = crit.pais_cod_pais
                             AND lsop.lpro_cod_prog = crit.lpro_cod_prog
                             AND lsop.cod_regi = lide.cod_regi
                             AND lsop.cod_zona = lide.cod_zona
                             AND lsop.cod_secc = lide.cod_secc
                             AND lsop.cam_obje BETWEEN vsCampEvalAnte AND
                                 psCodigoPeriodo),
                          0) val_dife_obje
        FROM (SELECT gere.gere cod_lide,
                     gere.cod_regi,
                     gere.cod_zona,
                     gere.cod_secc
                FROM zon_histo_geren gere,
                     cra_perio       perd,
                     seg_perio_corpo peri,
                     cra_perio       perd_2,
                     seg_perio_corpo peri_2
               WHERE gere.perd_oid_peri_desd = perd.oid_peri
                 AND perd.peri_oid_peri = peri.oid_peri
                 AND NVL(gere.perd_oid_peri_hast, vnOidCampanna) =
                     perd_2.oid_peri
                 AND perd_2.peri_oid_peri = peri_2.oid_peri
                 AND gere.cod_regi IS NOT NULL
                 AND gere.cod_zona IS NOT NULL
                 AND gere.cod_secc IS NOT NULL
                 AND psCodigoPeriodo BETWEEN peri.cod_peri AND
                     peri_2.cod_peri) lide,
             (SELECT ldet.pais_cod_pais,
                     ldet.lpro_cod_prog,
                     ldet.cod_lide,
                     COUNT(*) val_nume_etap_crit
                FROM lec_lider_desem ldet, ssicc_comun.lec_tipo_desem ltde
               WHERE ldet.pais_cod_pais = psCodigoPais
                 AND ldet.lpro_cod_prog = vsCodigoPrograma
                 AND ldet.lpec_cam_eval IN (vsCampEvalAnte, psCodigoPeriodo)
                 AND ldet.ltde_cod_tipo_dese = ltde.cod_tipo_dese(+)
                 AND ldet.ind_tipo_dese = vsTipoEvalua
                 AND ldet.ltde_cod_tipo_dese =
                     NVL(vsCodTipoDesemp, ldet.ltde_cod_tipo_dese)
               GROUP BY ldet.pais_cod_pais,
                        ldet.lpro_cod_prog,
                        ldet.cod_lide
              HAVING COUNT(*) = vnNumPerioCriti) crit
       WHERE lide.cod_lide = crit.cod_lide
       ORDER BY 5)
      SELECT *
        FROM t_criti
       WHERE val_dife_obje < 0
         AND ((psTipoProceso = 'P' AND vnIndAplicaBajaDesem = 1) OR
             psTipoProceso = 'R')
         AND ROWNUM < vnNumBajaAuto + 1;

    TYPE c_criticas_t IS TABLE OF c_criticas%ROWTYPE INDEX BY BINARY_INTEGER;
    regCritica c_criticas_t;

  BEGIN

    vnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    -- Obtiene el cï¿½digo del programa activo, valor campaï¿½as evaluar, valor numero bajas automï¿½ticas
    BEGIN
      SELECT cod_prog, NVL(num_camp_eval, 0), NVL(num_baja_auto, 0)
        INTO lsCodigoPrograma, lnNumCamEval, lnNumBajaAuto
        FROM lec_progr
       WHERE psCodigoPeriodo BETWEEN cam_inic AND
             NVL(cam_fin, psCodigoPeriodo)
         AND ind_acti = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsCodigoPrograma := NULL;
        lnNumCamEval     := 0;
        lnNumBajaAuto    := 0;
    END;

    IF lsCodigoPrograma IS NOT NULL THEN
      -- Obtiene el nï¿½mero de etapas crï¿½ticas consecutivas que debe tener una lider para evaluar desempeï¿½o.
      BEGIN
        SELECT TO_NUMBER(NVL(val_para, 0))
          INTO lnNumPerioCriti
          FROM bas_param_pais
         WHERE cod_pais = psCodigoPais
           AND cod_sist = 'LET'
           AND UPPER(nom_para) = 'NUMPERIOCRITI'
           AND ind_acti = '1';
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lnNumPerioCriti := 0;
      END;

      -- Obtiene indicador para definir si se aplica o no la baja por desempeï¿½o
      BEGIN
        SELECT TO_NUMBER(NVL(val_para, 0))
          INTO lnIndAplicaBajaDesem
          FROM bas_param_pais
         WHERE cod_pais = psCodigoPais
           AND cod_sist = 'LET'
           AND UPPER(nom_para) = 'INDAPLICABAJADESEM'
           AND ind_acti = '1';
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lnIndAplicaBajaDesem := 0;
      END;

      -- busca la campaï¿½a anterior al de mayor valor segï¿½n numero de periodos crï¿½ticos configurado
      BEGIN
        SELECT X.CAM_EVAL
          INTO lsCampEvalAnte
          FROM (SELECT ROWNUM NRO, CAM_EVAL
                  FROM (SELECT CAM_EVAL
                          FROM LEC_PROGR_ETAPA_CAMPA
                         WHERE PAIS_COD_PAIS = psCodigoPais
                           AND LPRO_COD_PROG = lsCodigoPrograma
                           AND CAM_EVAL <= psCodigoPeriodo
                         ORDER BY CAM_EVAL DESC) T) X
         WHERE X.NRO = lnNumPerioCriti;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          lsCampEvalAnte := NULL;
      END;

      -- Borra registros de bajas calculadas
      DELETE FROM lec_lider_baja libj
       WHERE libj.lpro_cod_prog = lsCodigoPrograma
         AND libj.cam_baja = psCodigoPeriodo
         AND ((psTipoProceso = 'R' AND libj.cod_regi = psCodigoRegion) OR
             psTipoProceso = 'P');
      -- -----------------------
      -- Baja Por Egreso
      -- -----------------------
      vsMotivoBaja := '1';
      vsIndBaja    := '1';
      OPEN c_Lideres(vnOidPeriodo);
      LOOP
        FETCH c_Lideres BULK COLLECT
          INTO regLider LIMIT W_FILAS;
        IF regLider.COUNT > 0 THEN
          FOR a IN regLider.FIRST .. regLider.LAST LOOP
            -- Dar de baja a las lï¿½deres con estatus de EGRESO o RETIRADA
            LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                   pscodigomarca,
                                   pscodigocanal,
                                   psCodigoPeriodo,
                                   pstipoproceso,
                                   regLider        (a).cod_regi,
                                   regLider        (a).cod_zona,
                                   regLider        (a).cod_secc,
                                   regLider        (a).cod_lide,
                                   vsMotivoBaja,
                                   vsIndBaja,
                                   lsCodigoPrograma,
                                   psCodigoUsuario);
          END LOOP;
        END IF;
        EXIT WHEN c_Lideres%NOTFOUND;
      END LOOP;
      CLOSE c_Lideres;

      -- --------------------------
      -- Baja Por Desempeï¿½o
      -- --------------------------
      vsMotivoBaja := '8';
      vsIndBaja    := '1';
      OPEN c_criticas(vnOidPeriodo,
                      lsCampEvalAnte,
                      '05',
                      lnNumPerioCriti,
                      'E',
                      lnNumBajaAuto,
                      lsCodigoPrograma,
                      lnIndAplicaBajaDesem);
      LOOP
        FETCH c_criticas BULK COLLECT
          INTO regCritica LIMIT W_FILAS;
        IF regCritica.COUNT > 0 THEN
          FOR a IN regCritica.FIRST .. regCritica.LAST LOOP
            -- Dar de baja a las lï¿½deres con baja productividad en las "n" ultimas etapas
            LEC_PR_REGI_LIDER_BAJA(psCodigoPais,
                                   pscodigomarca,
                                   pscodigocanal,
                                   psCodigoPeriodo,
                                   pstipoproceso,
                                   regCritica      (a).cod_regi,
                                   regCritica      (a).cod_zona,
                                   regCritica      (a).cod_secc,
                                   regCritica      (a).cod_lide,
                                   vsMotivoBaja,
                                   vsIndBaja,
                                   lsCodigoPrograma,
                                   psCodigoUsuario);
          END LOOP;
        END IF;
        EXIT WHEN c_criticas%NOTFOUND;
      END LOOP;
      CLOSE c_criticas;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_LIDER_BAJA: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_LIDER_BAJA;

  /***************************************************************************
      Descripcion       : Registrar Baja Lider
      Fecha Creacion    : 12/02/2014
      Autor             : henry paredes
  ***************************************************************************/
  PROCEDURE LEC_PR_REGI_LIDER_BAJA(psCodigoPais     VARCHAR2,
                                   pscodigomarca    VARCHAR2,
                                   pscodigocanal    VARCHAR2,
                                   psCodigoPeriodo  VARCHAR2,
                                   pstipoproceso    VARCHAR2,
                                   psCodigoRegion   VARCHAR2,
                                   psCodigoZona     VARCHAR2,
                                   psCodigoSecc     VARCHAR2,
                                   psCodigoLider    VARCHAR2,
                                   psMotivoBaja     NUMBER,
                                   psIndBaja        NUMBER,
                                   psCodigoPrograma VARCHAR2,
                                   psCodigoUsuario  VARCHAR2)

   IS
    vsRegiBajas   NUMBER;
    vnOidPeriodo  CRA_PERIO.OID_PERI%TYPE;
    vsInfEstaBaja VARCHAR(1);

  BEGIN
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    --Obtener Baja Lider
    BEGIN
      SELECT COUNT(1)
        INTO vsRegiBajas
        FROM LEC_LIDER_BAJA
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND COD_LIDE = psCodigoLider
         AND CAM_BAJA = psCodigoPeriodo;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsRegiBajas := 0;
    END;

    IF (vsRegiBajas <> 0) THEN
      DELETE FROM LEC_LIDER_BAJA
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND COD_LIDE = psCodigoLider
         AND CAM_BAJA = psCodigoPeriodo;
    END IF;

    IF pstipoproceso = 'R' THEN
      vsInfEstaBaja := 'I';
    ELSE
      vsInfEstaBaja := 'E';
    END IF;

    INSERT INTO LEC_LIDER_BAJA
      (PAIS_COD_PAIS,
       LPRO_COD_PROG,
       COD_REGI,
       COD_ZONA,
       COD_SECC,
       COD_LIDE,
       CAM_BAJA,
       LTDE_COD_TIPO_DESV,
       IND_ORIG_BAJA,
       IND_ESTA_BAJA,
       USU_CREA,
       FEC_CREA,
       USU_MODI,
       FEC_MODI,
       IND_ACTI)
    VALUES
      (psCodigoPais,
       psCodigoPrograma,
       psCodigoRegion,
       psCodigoZona,
       psCodigoSecc,
       psCodigoLider,
       psCodigoPeriodo,
       psMotivoBaja,
       pstipoproceso,
       vsInfEstaBaja,
       psCodigoUsuario,
       SYSDATE,
       NULL,
       NULL,
       '1');

    IF psTipoProceso = 'P' THEN
      --Cerramos la Historia
      UPDATE zon_histo_geren zhg
         SET zhg.fec_hast          =
             (SELECT bcf.fec_proc
                FROM bas_ctrl_fact bcf
               WHERE bcf.cod_peri = psCodigoPeriodo),
             zhg.perd_oid_peri_hast = vnOidPeriodo,
             zhg.ind_desv_auto      = 1,
             zhg.fec_modi           = SYSDATE,
             zhg.usu_modi           = psCodigoUsuario
       WHERE zhg.gere = psCodigoLider
         AND zhg.cod_secc IS NOT NULL
         AND vnOidPeriodo BETWEEN zhg.perd_oid_peri_desd AND
             nvl(zhg.perd_oid_peri_hast, vnOidPeriodo);

      --eliminamos la clasificaciï¿½n mae asignada
      DELETE FROM mae_clien_clasi cc
       WHERE cc.oid_clie_clas IN
             (SELECT mcc.oid_clie_clas
                FROM mae_clien            c,
                     mae_clien_tipo_subti mcts,
                     mae_clien_clasi      mcc,
                     mae_tipo_clasi_clien mtcc,
                     mae_clasi            mc
               WHERE c.oid_clie = mcts.clie_oid_clie
                 AND mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt
                 AND mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas
                 AND mcc.clas_oid_clas = mc.oid_clas
                 AND mtcc.sbti_oid_subt_clie = mcts.sbti_oid_subt_clie
                 AND mtcc.cod_tipo_clas = '01' -- Tipo Clasificacion Lider
                 AND mc.tccl_oid_tipo_clas = mtcc.oid_tipo_clas
                 AND mc.cod_clas = '01' -- Clasificacion Lider
                 AND c.cod_clie = psCodigoLider);
      --Eliminamos Lider de la Secciï¿½n
      UPDATE zon_secci zs
         SET zs.clie_oid_clie = NULL
       WHERE zs.zzon_oid_zona =
             (SELECT zz.oid_zona
                FROM zon_zona zz
               WHERE zz.cod_zona = psCodigoZona)
         AND zs.cod_secc = psCodigoSecc
         AND zs.clie_oid_clie =
             (SELECT c.oid_clie
                FROM mae_clien c
               WHERE c.cod_clie = psCodigoLider);
      --Actualizar Clasificaciï¿½n de Lï¿½der
      LEC_PKG_PROCE.LEC_PR_ACTUA_CLASI_LIDER(psCodigoPais,
                                             psCodigoMarca,
                                             psCodigoCanal,
                                             '2',
                                             psCodigoLider,
                                             psCodigoPeriodo,
                                             '0',
                                             psCodigoUsuario);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_REGI_LIDER_BAJA: ' ||
                              ls_sqlerrm);

  END LEC_PR_REGI_LIDER_BAJA;

  /*********************************************************
  Descripcion :  Obtener Consultora Excluida.
          Resultados:
                        - 0, No Existe Consultora
                        - 1, Existe Consultora
  Fecha Creacion : 25/02/2014
  Autor : Yahir Rivas L.
  *********************************************************/
  FUNCTION LEC_FN_OBTE_CONS_EXCL(psCodigoPais       VARCHAR2,
                                 psCampaniaProceso  VARCHAR2,
                                 psCodigoPrograma   VARCHAR2,
                                 psCodigoConsultora VARCHAR2) RETURN NUMBER IS

    vsCodCons LEC_PROGR_LISTA_EXCLU.Cod_Cons%type;
    vnResult  number;

  BEGIN
    BEGIN
      SELECT COD_CONS
        INTO VSCODCONS
        FROM LEC_PROGR_LISTA_EXCLU
       WHERE PSCAMPANIAPROCESO >= CAM_INIC_VIGE
         AND (PSCAMPANIAPROCESO <= CAM_FIN_VIGE OR CAM_FIN_VIGE IS NULL)
         AND IND_ACTI = '1'
         AND PAIS_COD_PAIS = PSCODIGOPAIS
         AND COD_CONS = PSCODIGOCONSULTORA
         AND LPRO_COD_PROG = PSCODIGOPROGRAMA;

      VNRESULT := 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        VNRESULT := 0;
    END;

    RETURN vnResult;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_FN_OBTE_CONS_EXCL: ' || ls_sqlerrm);
      RETURN - 1;

  END LEC_FN_OBTE_CONS_EXCL;
  /***************************************************************************
      Descripcion       : Proceso Calcular Recuperaciï¿½n de consultoras.043
      Fecha Creacion    : 04/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_RECUP_CONSU(psCodigoPais       VARCHAR2,
                                     pscodigomarca      VARCHAR2,
                                     pscodigocanal      VARCHAR2,
                                     psCodigoPrograma   VARCHAR2,
                                     psCampanaProceso   VARCHAR2,
                                     psCampanaCobranza  VARCHAR2,
                                     psCodigoConsultora VARCHAR2,
                                     psCodigoRegion     VARCHAR2,
                                     pscodigoZona       VARCHAR2,
                                     psCodigoSeccion    VARCHAR2,
                                     psCodigoLider      VARCHAR2,
                                     psCodigoUsuario    VARCHAR2,
                                     psCampanaAnterior  VARCHAR2,
                                     pnMonAbonoPdteClie NUMBER,
                                     pdFechaCierre      DATE,
                                     pdFechaCierreZona  DATE) IS

    vsCodTipMedCobr  LEC_PROGR_COBRA_OBJET.LTMC_COD_TIPO_MEDI_COBR%type;
    vsSecAmbiGeog    LEC_PROGR_AMBIT_GEOGR.SEC_AMBI_GEOG%type;
    vsNumDiasExtr    LEC_PROGR_COBRA_OBJET.NUM_DIAS_EXTR%type;
    vsIndFeriado     LEC_PROGR_COBRA_OBJET.Ind_Feri%type;
    vsFecCierr       FAC_PROGR_CIERR.FEC_CIER%TYPE;
    dFechLimite      FAC_PROGR_CIERR.FEC_CIER%type;
    dFechLimiteHabil FAC_PROGR_CIERR.FEC_CIER%type;
    dFechLimiteAnter FAC_PROGR_CIERR.FEC_CIER%type;
    dFechIniAbon     FAC_PROGR_CIERR.FEC_CIER%type;

    vnOidPais         NUMBER;
    vnOidMarca        NUMBER;
    vnOidCanal        NUMBER;
    vnOidPeriodo      NUMBER;
    vnOidCampAnterior NUMBER;
    vnOidCliente      NUMBER;
    vnEncontroReg     NUMBER;
    vnOidZona         ZON_ZONA.OID_ZONA%type;

    --Variables 1er cursor
    vsCod_Subp      CCC_SUBPR.COD_SUBP%TYPE;
    vsOid_Subp      CCC_SUBPR.OID_SUBP%TYPE;
    vsCod_Pro       CCC_PROCE.COD_PROC%TYPE;
    vsOidMoviCC     CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE;
    vsOidSoliCabe   CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE%TYPE;
    vsImpMovi       CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE;
    nMontoCargo     CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE;
    nMontoCargoTota CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE;
    nMontoCargoHis  CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE;

    dFec_Docu     CCC_MOVIM_CUENT_CORRI.FEC_DOCU%TYPE;
    vsOidPeri     CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI%TYPE;
    vsOidSubpUlti CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI%TYPE;
    vsFecUltiMovi CCC_MOVIM_CUENT_CORRI.FEC_ULTI_MOVI%TYPE;
    dFecPago      CCC_MOVIM_CUENT_CORRI.FEC_ULTI_MOVI%TYPE;

    vsImpPago        CCC_MOVIM_CUENT_CORRI.IMP_PAGO%TYPE;
    vsValUltiNumHist CCC_MOVIM_CUENT_CORRI.Val_Ulti_Nume_Hist%type;
    vsNumHist        CCC_MOVIM_CUENT_CORRI.Val_Ulti_Nume_Hist%type;
    vsOidEstaClie    MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%type;
    vsCodEstaClie    MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE;

    cIndicMovim VARCHAR2(1);

    dFec_Cargo DATE;

    nMontoReclamo     NUMBER := 0;
    nMontoReclamoTota NUMBER := 0;
    nMonAbonoPdte     NUMBER := 0;
    nMonAbonoPdteTota NUMBER := 0;
    nMonAbonoPdteClie NUMBER := 0;

    nValMontCDRS         NUMBER := 0;
    nValMontNCOM         NUMBER := 0;
    nValMontRecu         LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Recu%type;
    nValMontRecuTota     LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Recu_TOTA%type;
    nValMontRecaPediCons LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Reca_Pedi_Cons%type := 0;
    nValMontRecaPediNCon LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Reca_Pedi_Ncon%type := 0;
    vsIndPediCons        LEC_LIDER_SECCI_DETAL_RECUP.IND_PEDI_CONS%TYPE;

    nPagoTramo      NUMBER := 0;
    nPagoTramoTota  NUMBER := 0;
    nMontoDeduccion NUMBER;
    dFechaLimite    DATE;
    dFechaIniAbo    DATE;

    vsNombreCampo     varchar2(100);
    vsValorCampoX     CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE;
    vsOidPeriSoliCabe PED_SOLIC_CABEC.PERD_OID_PERI%type;
    vsNumeSolic       PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
    vsValTotaPagaLoca PED_SOLIC_CABEC.VAL_TOTA_PAGA_LOCA%TYPE;
    vsValImpuTotaLoca PED_SOLIC_CABEC.Val_Impo_Impu_Tota_Loca%type;

    vsValTasaImpu PED_SOLIC_CABEC.Val_Tasa_Impu%type;

    vnValorDeduccion NUMBER := 0;

    nPorcDeduccion NUMBER;
    nIndFlexipago  NUMBER;
    vsValCuot21    FLX_GENER_FINAN_CONSU_FLEXI.Val_Cuot_21di_Pedi_Vige%type;
    vnIndGeneFlex  FLX_GENER_FINAN_CONSU_FLEXI.Ind_Gene_Fina_Flex%type;
    vnCodMotiR     FLX_GENER_FINAN_CONSU_FLEXI.Cod_Moti_Rech%type;
    vnIndSegu      FLX_GENER_FINAN_CONSU_FLEXI.Ind_Segu_Pedi%type;

    vsFecInicCrono CRA_CRONO.Fec_Inic%type;

    vsOidActiv CRA_ACTIV.OID_ACTI%TYPE;
    vsCodActiv CRA_ACTIV.COD_ACTI%TYPE;

    vsIndTipoMov    LEC_TIPO_MOVIM.IND_TIPO_MOVI%TYPE;
    vsIndTipoMovHis LEC_TIPO_MOVIM.IND_TIPO_MOVI%TYPE;

    vsOTNumDias LEC_PROGR_COBRA_OBJET_TRAMO.NUM_DIAS%TYPE;
    nDiasTramo  LEC_PROGR_COBRA_OBJET_TRAMO.NUM_DIAS%TYPE;

    vsCodTramo   LEC_PROGR_COBRA_OBJET_TRAMO.LPCT_COD_TRAM%type;
    nCodigoTramo LEC_PROGR_COBRA_OBJET_TRAMO.LPCT_COD_TRAM%type;

    vsSubOidSub  CCC_HISTO_MOVIM_CC.Subp_Oid_Subp%type;
    vsFecMoviHis CCC_HISTO_MOVIM_CC.Fec_Movi%type;
    vsImpPagoHis CCC_HISTO_MOVIM_CC.Imp_Pago%type;

    vnExisteRecup       NUMBER;
    vnExisteDRecup      NUMBER;
    vnExisteRecupTramo  NUMBER;
    vnExisteDRecupTramo NUMBER;

    vnDRET_val_mont_abon      LEC_LIDER_SECCI_DRECU_TRAMO.Val_Mont_Abon%type;
    vnDRET_VAL_MONT_ABON_TOTA LEC_LIDER_SECCI_DRECU_TRAMO.Val_Mont_Abon_TOTA%type;

    x             NUMBER := 0;
    y             NUMBER := 0;
    ncodTramo     NUMBER;
    vConcat       varchar2(1000);
    vnNumDeci     NUMBER;
    nTramo        NUMBER;
    vsPeriodoFact BAS_CTRL_FACT.FEC_PROC%TYPE;
    vsPerioFactu  BAS_CTRL_FACT.COD_PERI%type;

    vsCampAnterior      BAS_CTRL_FACT.COD_PERI%TYPE;
    vnStateClient       NUMBER;
    nSecAmbito          NUMBER;
    encontroTramo       BOOLEAN;
    vnOidCampanaProceso NUMBER;
    vsIndComis          CHAR(1);
    
    nCodigoRango	      LEC_PROGR_NIVEL_RANGO.COD_RANG%TYPE;

    CURSOR c_cargosMovimientosCC(oidCliente NUMBER, oidPeriodo NUMBER) IS
      SELECT distinct b.cod_subp,
                      b.oid_subp,
                      c.cod_proc,
                      a.oid_movi_cc,
                      a.soca_oid_soli_cabe,
                      a.imp_movi,
                      a.fec_docu,
                      a.perd_oid_peri,
                      a.subp_oid_subp_ulti,
                      a.fec_ulti_movi,
                      a.imp_pago,
                      a.val_ulti_nume_hist
        FROM CCC_MOVIM_CUENT_CORRI a, CCC_SUBPR b, CCC_PROCE c
       WHERE a.subp_oid_subp_crea = b.oid_subp
         AND b.ccpr_oid_proc = c.oid_proc
         AND a.clie_oid_clie = oidCliente
         AND a.perd_oid_peri = oidPeriodo
         AND a.imp_movi > 0;

    CURSOR c_nombresColumnas(oid_Subp CCC_SUBPR.OID_SUBP%TYPE) IS
      SELECT distinct (e.nom_camp_pedi) AS NOM_CAMPO
        FROM CCC_SUBPR b, CCC_PROCE c, LEC_PROC_SUBPR d, LEC_TIPO_MOVIM e
       WHERE b.oid_subp = oid_Subp
         AND b.ccpr_oid_proc = c.oid_proc
         AND d.PAIS_COD_PAIS = e.PAIS_COD_PAIS
         AND d.LTMO_COD_MOVI = e.COD_MOVI
         AND c.cod_proc = d.cod_proc
         AND b.cod_subp = d.cod_subp
         AND e.IND_TIPO_MOVI = 'C'
         AND e.IND_DEDU = 1
         AND e.IND_ACTI = 1;

    CURSOR c_pcoTramos IS
      SELECT lpcot.COD_TIPO_TRAM
        FROM ssicc_comun.lec_tipo_tramo lpcot
       ORDER BY lpcot.COD_TIPO_TRAM;

    CURSOR c_historicoMVCC(oidMoviCC CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE) IS
      SELECT hcc.Subp_Oid_Subp, hcc.Fec_Movi, hcc.Imp_Pago, hcc.num_hist
        FROM CCC_HISTO_MOVIM_CC hcc
       WHERE hcc.Mvcc_Oid_Movi_Cc = oidMoviCC;

    TYPE valoresRec IS RECORD(
      codigoTramo    LEC_LIDER_SECCI_DRECU_TRAMO.LPCT_COD_TRAM%TYPE,
      pagoTramo      LEC_LIDER_SECCI_DRECU_TRAMO.VAL_MONT_ABON%TYPE,
      pagoTramoTota  LEC_LIDER_SECCI_DRECU_TRAMO.VAL_MONT_ABON_TOTA%TYPE,
      montoDeduccion NUMBER := 0,
      fechaLimite    LEC_LIDER_SECCI_DRECU_TRAMO.FEC_LIMI_ABON%TYPE,
      fechaIniAbono  LEC_LIDER_SECCI_DRECU_TRAMO.FEC_INI_ABON%TYPE,
      secAmbito      NUMBER);

    TYPE valoresRecTab IS TABLE OF valoresRec;
    valoresRecord1 valoresRecTab := valoresRecTab();
    valores_rec    valoresRec;

  BEGIN
    nMonAbonoPdteClie := pnMonAbonoPdteClie;
    vnOidPais         := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca        := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(pscodigomarca);
    vnOidCanal        := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(pscodigocanal);
    vnOidPeriodo      := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanaCobranza);
    vnOidCliente      := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoConsultora);
    vsCampAnterior    := psCampanaAnterior; --PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCampanaCobranza,vnOidPais,vnOidMarca,vnOidCanal,-1);
    vnOidCampAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampAnterior,
                                                                    vnOidMarca,
                                                                    vnOidCanal);

    vnOidCampanaProceso := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanaProceso);

    SELECT FEC_PROC
      INTO vsPeriodoFact
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1
       AND COD_PAIS = psCodigoPais;

    vsPerioFactu := psCampanaProceso;

    SELECT num_deci
      INTO vnNumDeci
      FROM seg_pais p, seg_moned m
     WHERE p.mone_oid_mone = m.oid_mone
       AND p.cod_pais = psCodigoPais;

    SELECT zz.oid_zona
      INTO vnOidZona
      FROM zon_zona zz
     WHERE zz.cod_zona = pscodigoZona;

    BEGIN
      SELECT SEC_AMBI_GEOG
        into vsSecAmbiGeog
        FROM LEC_PROGR_AMBIT_GEOGR
       WHERE LTUG_COD_TIPO_USO_GEOG = '01'
         AND IND_PAIS = '1'
         AND PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND IND_ACTI = 1;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vsSecAmbiGeog := NULL;
    END;

    IF vsSecAmbiGeog IS NULL THEN
      BEGIN
        SELECT SEC_AMBI_GEOG
          into vsSecAmbiGeog
          FROM LEC_PROGR_AMBIT_GEOGR a
         WHERE a.LTUG_COD_TIPO_USO_GEOG = '01'
           AND a.PAIS_COD_PAIS = psCodigoPais
           AND a.LPRO_COD_PROG = psCodigoPrograma
           AND a.cod_regi = psCodigoRegion
           AND a.cod_zona is null
           AND a.IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsSecAmbiGeog := NULL;
      END;
    END IF;

    IF vsSecAmbiGeog IS NULL THEN
      BEGIN
        SELECT SEC_AMBI_GEOG
          INTO vsSecAmbiGeog
          FROM LEC_PROGR_AMBIT_GEOGR a
         WHERE a.LTUG_COD_TIPO_USO_GEOG = '01'
           AND a.PAIS_COD_PAIS = psCodigoPais
           AND a.LPRO_COD_PROG = psCodigoPrograma
           AND a.cod_regi = psCodigoRegion
           AND a.cod_zona = pscodigoZona
           AND a.IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsSecAmbiGeog := NULL;
      END;

    END IF;

    --VARIABLES OBTENIDAS DE LA TABLA LEC_PROGR_COBRA_OBJET ->(PROGRAMA OBJ. COBRANZAS)
    IF (vsSecAmbiGeog IS NOT NULL) THEN

      SELECT cobObje.LTMC_COD_TIPO_MEDI_COBR,
             cobObje.NUM_DIAS_EXTR,
             prog.IND_FERI
        INTO vsCodTipMedCobr, vsNumDiasExtr, vsIndFeriado
        FROM LEC_PROGR_COBRA_OBJET cobObje, LEC_PROGR prog
       WHERE cobObje.Pais_Cod_Pais = prog.pais_cod_pais
         AND cobObje.Lpro_Cod_Prog = prog.cod_prog
         AND cobObje.LPRO_COD_PROG = psCodigoPrograma
         AND cobObje.PAIS_COD_PAIS = psCodigoPais
         AND cobObje.LTUG_COD_TIPO_USO_GEOG = '01'
         AND cobObje.LPAG_SEC_AMBI_GEOG = vsSecAmbiGeog
         AND cobObje.IND_ACTI = 1;

      IF vsCodTipMedCobr = '01' THEN
        /*BEGIN
          SELECT FEC_CIER INTO vsFecCierr
           FROM FAC_PROGR_CIERR
            WHERE cod_regi = psCodigoRegion
              AND cam_proc = GEN_FN_CALCU_PERIO(psCampanaCobranza, +1)
              AND tip_cier='R'
              AND est_cier='P';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
             vsFecCierr := NULL;
        END;*/

        vsFecCierr := pdFechaCierre;

        IF vsFecCierr IS NOT NULL THEN
          dFechLimite := vsFecCierr + vsNumDiasExtr;
        ELSE
          dFechLimite := vsPeriodoFact;
        END IF;

        SELECT cot.lpct_cod_tram, cot.lpag_sec_ambi_geog
          into nCodigoTramo, nSecAmbito
          FROM LEC_PROGR_COBRA_OBJET_TRAMO cot
         WHERE cot.pais_cod_pais = psCodigoPais
           AND cot.lpro_cod_prog = psCodigoPrograma
           AND cot.lpag_sec_ambi_geog = vsSecAmbiGeog
           AND cot.ltmc_cod_tipo_medi_cobr = '01'
           AND cot.ltug_cod_tipo_uso_geog = '01'
           AND COT.IND_ACTI = 1;

      ELSIF vsCodTipMedCobr = '03' THEN
        /*BEGIN
              SELECT FEC_CIER + vsNumDiasExtr INTO dFechLimite
              FROM FAC_PROGR_CIERR
              WHERE cod_regi = psCodigoRegion
              AND COD_ZONA = psCodigoZona
              AND cam_proc = GEN_FN_CALCU_PERIO(psCampanaCobranza, +1)
              AND tip_cier='Z'
              AND est_cier='P';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
             SELECT FEC_PROC
             INTO  dFechLimite
             FROM BAS_CTRL_FACT WHERE STA_CAMP = '0' AND IND_CAMP_ACT = '1';

        END;*/

        IF (pdFechaCierreZona IS NULL) THEN
          SELECT FEC_PROC
            INTO dFechLimite
            FROM BAS_CTRL_FACT
           WHERE STA_CAMP = '0'
             AND IND_CAMP_ACT = '1';
        ELSE
          dFechLimite := pdFechaCierreZona + vsNumDiasExtr;
        END IF;

        SELECT cot.lpct_cod_tram, cot.lpag_sec_ambi_geog ----do  tod select
          into nCodigoTramo, nSecAmbito
          FROM LEC_PROGR_COBRA_OBJET_TRAMO cot
         WHERE cot.pais_cod_pais = psCodigoPais
           AND cot.lpro_cod_prog = psCodigoPrograma
           AND cot.lpag_sec_ambi_geog = vsSecAmbiGeog
           AND cot.ltmc_cod_tipo_medi_cobr = '03'
           AND cot.ltug_cod_tipo_uso_geog = '01'
           AND COT.IND_ACTI = 1;

      END IF;

      --BUSCAMOS EL ESTAT_CLIEN EN EL HISTORICO
      /*SELECT COUNT(1) into vnStateClient
       FROM MAE_CLIEN_HISTO_ESTAT HE, MAE_ESTAT_CLIEN ME
      WHERE HE.CLIE_OID_CLIE = vnOidCliente
        AND vnOidPeriodo >= perd_oid_peri
        AND (vnOidPeriodo <= perd_oid_peri_peri_fin OR perd_oid_peri_peri_fin IS NULL)
        AND HE.ESTA_OID_ESTA_CLIE = ME.OID_ESTA_CLIE
        AND ME.COD_ESTA_CLIE IN ('01', '02', '03', '07', '08')
        AND NOT EXISTS
           (SELECT NULL
              FROM MAE_CLIEN_HISTO_ESTAT HE, MAE_ESTAT_CLIEN ME
             WHERE HE.CLIE_OID_CLIE = vnOidCliente
               AND vnOidCampAnterior >= perd_oid_peri
               AND (vnOidCampAnterior <= perd_oid_peri_peri_fin
                OR perd_oid_peri_peri_fin IS NULL)
               AND HE.ESTA_OID_ESTA_CLIE = ME.OID_ESTA_CLIE
               AND ME.COD_ESTA_CLIE   = '04' ); */

      SELECT COUNT(1)
        into vnStateClient
        FROM MAE_CLIEN_HISTO_ESTAT HE
       WHERE HE.CLIE_OID_CLIE = vnOidCliente
         AND HE.PERD_OID_PERI <= vnOidCampAnterior
         AND (HE.PERD_OID_PERI_PERI_FIN >= vnOidCampAnterior OR
             HE.PERD_OID_PERI_PERI_FIN IS NULL)
         AND HE.ESTA_OID_ESTA_CLIE IN (5, 4);

      IF (vnStateClient > 0) THEN
        vsIndPediCons := '0';
      ELSE
        vsIndPediCons := '1';
      END IF;

      --CURSOR MOV. CUENTA CORRIENTE- Se abre el cursor que trae el oidSoliCabecera
      OPEN c_cargosMovimientosCC(vnOidCliente, vnOidPeriodo);
      LOOP
        FETCH c_cargosMovimientosCC
          INTO vsCod_Subp,
               vsOid_Subp,
               vsCod_Pro,
               vsOidMoviCC,
               vsOidSoliCabe,
               vsImpMovi,
               dFec_Docu,
               vsOidPeri,
               vsOidSubpUlti,
               vsFecUltiMovi,
               vsImpPago,
               vsValUltiNumHist;
        EXIT WHEN c_cargosMovimientosCC%NOTFOUND;

        -- 12.    Validar si  el  proceso de creaciï¿½n y subproceso de creaciï¿½n  del cargo estï¿½ registrado
        -- en la tabla SubProcesos LEC, uniendo esta tabla con Tipos Movimiento con COD_MOVI  y  IND_TIPO_MOVI  = `Cï¿½
        -- y que IND_DEDU  =  0. De encontrar registro, mover 1 a IND-COMIS.
        SELECT DECODE(COUNT(1), 0, '0', '1')
          INTO vsIndComis
          FROM LEC_PROC_SUBPR d, LEC_TIPO_MOVIM e
         WHERE d.PAIS_COD_PAIS = e.PAIS_COD_PAIS
           AND d.LTMO_COD_MOVI = e.COD_MOVI
           AND d.cod_proc = vsCod_Pro
           AND d.cod_subp = vsCod_Subp
           AND e.IND_TIPO_MOVI = 'C'
           AND e.IND_DEDU = 0
           AND e.IND_ACTI = 1;

        -- 13.     Validar si  el  proceso de creaciï¿½n y subproceso de creaciï¿½n  del cargo estï¿½ registrados en la tabla
        -- SubProcesos LEC, uniendo esta tabla con Tipos Movimiento con COD_MOVI  y
        -- IND_TIPO_MOVI  = `Cï¿½    y que IND_DEDU  =  1 y tomar el campo NOM_CAMP-PEDI
        -- ( se podrï¿½n encontrar varios registros), estos serï¿½n los campos deducibles.
        /*SELECT E.NOM_CAMP_PEDI
        FROM  LEC_PROC_SUBPR d, LEC_TIPO_MOVIM e
        WHERE d.PAIS_COD_PAIS = e.PAIS_COD_PAIS
        AND d.LTMO_COD_MOVI = e.COD_MOVI
        AND d.cod_proc = vsCod_Pro
        AND d.cod_subp = vsCod_Subp
        AND e.IND_TIPO_MOVI = 'C'
        AND e.IND_DEDU = 1
        AND e.IND_ACTI = 1;*/

        --14. nValorDeducciï¿½n
        vnValorDeduccion  := 0;
        nMontoReclamo     := 0;
        nMontoReclamoTota := 0;
        nMonAbonoPdte     := 0;
        nMonAbonoPdteTota := 0;
        --             nMontoPago:=0;

        nPorcDeduccion    := 0;
        nMontoCargo       := 0;
        vsNumeSolic       := null;
        vsValTotaPagaLoca := 0;
        vsValImpuTotaLoca := 0;

        IF vsIndComis = '1' THEN
          -- Obtenenos los datos de la cabecera de pedido
          BEGIN
            SELECT psc.val_nume_soli,
                   psc.val_tota_paga_loca,
                   psc.val_impo_impu_tota_loca
              INTO vsNumeSolic, vsValTotaPagaLoca, vsValImpuTotaLoca
              FROM PED_SOLIC_CABEC psc
             WHERE psc.oid_soli_cabe = vsOidSoliCabe;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsNumeSolic       := null;
              vsValTotaPagaLoca := 0;
              vsValImpuTotaLoca := 0;
          END;

          --CURSOR PARA SUMATORIA DE COLUMNAS - Abrimos cursor que trae los nombres de las columnas
          OPEN c_nombresColumnas(vsOid_Subp);
          LOOP
            FETCH c_nombresColumnas
              INTO vsNombreCampo;
            EXIT WHEN c_nombresColumnas%NOTFOUND;

            IF (vsNombreCampo IS NOT NULL) THEN
              vConcat := 'SELECT ' || vsNombreCampo ||
                         ',perd_oid_peri, val_tasa_impu FROM PED_SOLIC_CABEC WHERE oid_soli_cabe =' ||
                         vsOidSoliCabe;
              EXECUTE IMMEDIATE vConcat
                INTO vsValorCampoX, vsOidPeriSoliCabe, vsValTasaImpu; --USING OUT vsValorCampoX;

              vnValorDeduccion := vnValorDeduccion + vsValorCampoX;
            END IF;

          END LOOP;
          CLOSE c_nombresColumnas;

          nPorcDeduccion := ROUND(vnValorDeduccion / vsValTotaPagaLoca, 6);

          IF (vsOidPeri <> vsOidPeriSoliCabe) THEN
            vnValorDeduccion := ROUND(vsImpMovi * nPorcDeduccion, 2);
          END IF;

        END IF;

        nMontoCargoTota := vsImpMovi;

        IF vsIndComis = '1' THEN
          nMontoCargo := ROUND(vsImpMovi - (vsImpMovi * nPorcDeduccion),
                               vnNumDeci);
        END IF;

        nIndFlexipago := 0;
        dFec_Cargo    := dFec_Docu;

        --Si el oidPeriodo del MovCuentaCorriente = oidPeriodo CabePedidos
        --Accesamos la tabla ProyeccionFlexipago
        IF vsOidPeriSoliCabe is not null THEN
          IF (vsOidPeri = vsOidPeriSoliCabe) THEN
            vnIndGeneFlex := null;
            vnCodMotiR    := null;
            vnIndSegu     := 0;

            IF vsNumeSolic IS NOT NULL AND vsIndComis = '1' THEN

              BEGIN
                SELECT flexi.val_cuot_21di_pedi_vige,
                       flexi.ind_gene_fina_flex,
                       flexi.cod_moti_rech,
                       flexi.ind_segu_pedi
                  into vsValCuot21, vnIndGeneFlex, vnCodMotiR, vnIndSegu
                  FROM FLX_GENER_FINAN_CONSU_FLEXI flexi
                 WHERE flexi.oid_peri = vsOidPeri
                   AND flexi.oid_clie = vnOidCliente
                   AND flexi.soca_oid_soli_cabe = vsOidSoliCabe
                      ---    AND flexi.ind_gene_fina_flex = 1
                   AND ROWNUM = 1;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  vsValCuot21 := NULL;
                  vnIndSegu   := 0;
              END;

              IF vnIndGeneFlex <> 1 AND nvl(vnIndSegu, 0) = '1' THEN
                nMontoCargo     := 0;
                nMontoCargoTota := 0;
                nIndFlexipago   := 2;
              END IF;
              IF NVL(vsValCuot21, 0) > 0 AND vnIndGeneFlex = 1 THEN
                nMontoCargo      := ROUND(vsValCuot21 /
                                          (1 + (vsValTasaImpu / 100)),
                                          vnNumDeci);
                nMontoCargoTota  := vsValCuot21;
                nPorcDeduccion   := ROUND(vsValImpuTotaLoca /
                                          vsValTotaPagaLoca,
                                          6);
                vnValorDeduccion := ROUND(vsValCuot21 * nPorcDeduccion, 2);
                nIndFlexipago    := 1;
              END IF;

            END IF;

          ELSE
            --obtener el oidActi de la tabla CRA_ACTIV para la actividad 'FA'
            BEGIN
              SELECT oid_acti, cod_acti
                into vsOidActiv, vsCodActiv
                FROM cra_activ
               WHERE marc_oid_marc = vnOidMarca
                 AND cana_oid_cana = vnOidCanal
                 AND pais_oid_pais = vnOidPais
                 AND cod_acti = 'FA';
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsOidActiv := NULL;
                vsCodActiv := NULL;
            END;

            --OBTENIENDO FEC_INIC DE LA TABLA CRA_CRONO
            IF (vsOidActiv IS NOT NULL) THEN
              SELECT fec_inic
                into vsFecInicCrono
                FROM cra_crono
               WHERE cact_oid_acti = vsOidActiv
                 AND zzon_oid_zona = vnOidZona
                 AND perd_oid_peri = vnOidPeriodo;

              dFec_Cargo := vsFecInicCrono;
            END IF;

          END IF;
        END IF;
        dFecPago    := vsFecUltiMovi;
        cIndicMovim := NULL;

        --20 VERIFICAR SI EL MOVIMIENTO ES PAGO O RECLAMO
        BEGIN
          SELECT distinct (e.ind_tipo_movi)
            INTO vsIndTipoMov
            FROM CCC_SUBPR      b,
                 CCC_PROCE      c,
                 LEC_PROC_SUBPR d,
                 LEC_TIPO_MOVIM e
           WHERE b.oid_subp = vsOidSubpUlti
             AND b.ccpr_oid_proc = c.oid_proc
             AND b.cod_subp = d.cod_subp
             AND c.cod_proc = d.cod_proc
             AND d.PAIS_COD_PAIS = e.PAIS_COD_PAIS
             AND d.LTMO_COD_MOVI = e.COD_MOVI
             AND e.ind_tipo_movi in ('R', 'P');
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsIndTipoMov := null;
        END;

        --Secciï¿½n  Evaluaciï¿½n Pagos
        --La primera vez que se llena el arreglo se evalua si en el movimiento hay vsImpPago > 0
        --y este monto es el que se agrega a la tabla temporal como nPagoTramo
        IF (vsIndTipoMov = 'P') THEN
          cIndicMovim := 'P';
          IF (vsCodTipMedCobr = '01' OR vsCodTipMedCobr = '03') THEN
            x := x + 1;
            IF (vsIndFeriado = 1) THEN
              dFechLimite := COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(dFechLimite,
                                                                    pscodigoZona);
            END IF;
            IF (dFecPago <= dFechLimite) THEN

              nPagoTramo      := 0;
              nMontoDeduccion := 0;

              IF vsIndComis = '1' THEN
                nPagoTramo      := ROUND(vsImpPago -
                                         (vsImpPago * nPorcDeduccion),
                                         vnNumDeci);
                nMontoDeduccion := ROUND(vsImpPago * nPorcDeduccion, 2);
              END IF;
              nPagoTramoTota := vsImpPago;

              --Tabla temporal valoresRecord

              valores_rec.codigoTramo    := nCodigoTramo;
              valores_rec.pagoTramo      := nPagoTramo;
              valores_rec.pagoTramoTota  := nPagoTramoTota;
              valores_rec.montoDeduccion := nMontoDeduccion;
              valores_rec.fechaLimite    := dFechLimite;
              ----  valores_rec.secAmbito      := NULL;
              valores_rec.secAmbito := nSecAmbito;
              valoresRecord1.extend;
              valoresRecord1(x) := valores_rec;
            ELSE
              x := x - 1;
            END IF;

          ELSIF (vsCodTipMedCobr = '02') THEN
            --INICIO cursor cobrObjeTramos
            nTramo        := 1;
            encontroTramo := FALSE;
            OPEN c_pcoTramos;
            LOOP
              FETCH c_pcoTramos
                INTO vsCodTramo;
              EXIT WHEN c_pcoTramos%NOTFOUND;
              y := y + 1;

              BEGIN
                SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                  INTO nSecAmbito, vsOTNumDias
                  FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                       LEC_PROGR_AMBIT_GEOGR       AGE
                 WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                   AND COT.LTUG_COD_TIPO_USO_GEOG =
                       AGE.LTUG_COD_TIPO_USO_GEOG
                   AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                   AND COT.PAIS_COD_PAIS = psCodigoPais
                   AND COT.LPRO_COD_PROG = psCodigoPrograma
                   AND cot.lpro_cod_prog = age.lpro_cod_prog
                   AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                   AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                   AND AGE.IND_PAIS = 1
                   AND COT.IND_ACTI = 1
                   AND COT.LPCT_COD_TRAM = vsCodTramo;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  nSecAmbito := NULL;
              END;

              IF nSecAmbito IS NULL THEN
                BEGIN
                  SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                    INTO nSecAmbito, vsOTNumDias
                    FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                         LEC_PROGR_AMBIT_GEOGR       AGE
                   WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                     AND COT.LTUG_COD_TIPO_USO_GEOG =
                         AGE.LTUG_COD_TIPO_USO_GEOG
                     AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                     AND cot.lpro_cod_prog = age.lpro_cod_prog
                     AND COT.PAIS_COD_PAIS = psCodigoPais
                     AND COT.LPRO_COD_PROG = psCodigoPrograma
                     AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                     AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                     AND AGE.IND_ACTI = 1
                     AND AGE.COD_REGI = psCodigoRegion
                     AND AGE.COD_ZONA = pscodigoZona
                     AND COT.LPCT_COD_TRAM = vsCodTramo;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    nSecAmbito := NULL;
                END;
              END IF;

              IF nSecAmbito IS NULL THEN
                BEGIN
                  SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                    INTO nSecAmbito, vsOTNumDias
                    FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                         LEC_PROGR_AMBIT_GEOGR       AGE
                   WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                     AND COT.LTUG_COD_TIPO_USO_GEOG =
                         AGE.LTUG_COD_TIPO_USO_GEOG
                     AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                     AND cot.lpro_cod_prog = age.lpro_cod_prog
                     AND COT.PAIS_COD_PAIS = psCodigoPais
                     AND COT.LPRO_COD_PROG = psCodigoPrograma
                     AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                     AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                     AND AGE.IND_ACTI = 1
                     AND AGE.COD_REGI = psCodigoRegion
                     AND COT.LPCT_COD_TRAM = vsCodTramo;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    nSecAmbito  := NULL;
                    vsOTNumDias := 0;
                END;
              END IF;

              nDiasTramo       := vsOTNumDias;
              dFechLimiteHabil := dFec_Cargo + nDiasTramo;
              nPagoTramo       := 0;
              nMontoDeduccion  := 0;

              IF (vsIndFeriado = 1) THEN
                dFechLimiteHabil := COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(dFechLimiteHabil,
                                                                           pscodigoZona);
              END IF;

              IF (nTramo = 1 AND dFecPago <= dFechLimiteHabil) OR
                 (nTramo > 1 AND dFecPago > dFechLimiteAnter AND
                 dFecPago <= dFechLimiteHabil) THEN

                nCodigoTramo := vsCodTramo;

                IF vsIndComis = '1' THEN
                  nPagoTramo      := ROUND(vsImpPago -
                                           (vsImpPago * nPorcDeduccion),
                                           vnNumDeci);
                  nMontoDeduccion := ROUND(vsImpPago * nPorcDeduccion, 2);
                END IF;

                nPagoTramoTota := vsImpPago;

                --Tabla temporal valoresRecord
                valores_rec.codigoTramo    := nCodigoTramo;
                valores_rec.pagoTramo      := nPagoTramo;
                valores_rec.pagoTramoTota  := nPagoTramoTota;
                valores_rec.montoDeduccion := nMontoDeduccion;
                valores_rec.fechaLimite    := dFechLimiteHabil;
                valores_rec.secAmbito      := nSecAmbito;
                --Se agrega fechaIniAbono al Arreglo de tramos
                IF (nTramo > 1) THEN
                  valores_rec.fechaIniAbono := dFechLimiteAnter + 1;
                ELSE
                  valores_rec.fechaIniAbono := null;
                END IF;

                valoresRecord1.extend;
                valoresRecord1(y) := valores_rec;
                encontroTramo := TRUE;
              ELSE
                y := y - 1;
              END IF;
              nTramo           := nTramo + 1;
              dFechLimiteAnter := dFechLimiteHabil;

              IF encontroTramo THEN
                EXIT;
              END IF;
            END LOOP;
            CLOSE c_pcoTramos; --FIN cursor cobrObjeTramos

          END IF;

          --Secciï¿½n  Acumula Reclamos
        ELSIF (vsIndTipoMov = 'R') THEN
          SELECT COUNT(1)
            INTO vnEncontroReg
            FROM ccc_aplic_abono_cargo a,
                 mae_clien             mc,
                 ccc_movim_cuent_corri mcc,
                 ccc_movim_cuent_corri mcr
           WHERE a.clie_oid_clie = mc.oid_clie
             AND a.clie_oid_clie = vnOidCliente
             AND a.mvcc_oid_movi_carg = mcc.oid_movi_cc
             AND mcc.oid_movi_cc = vsOidMoviCC
             AND mcc.val_ulti_nume_hist = a.val_nume_hist
             AND a.mvcc_oid_movi_abon = mcr.oid_movi_cc
             AND mcr.clie_oid_clie = a.clie_oid_clie
             AND mcr.subp_oid_subp_crea in
                 (SELECT CCSP.OID_SUBP
                    FROM lec_tipo_movim TM,
                         LEC_PROC_SUBPR SP,
                         CCC_SUBPR      CCSP,
                         CCC_PROCE      PR
                   WHERE TM.IND_TIPO_MOVI = 'A'
                     AND TM.COD_MOVI = SP.LTMO_COD_MOVI
                     AND SP.COD_PROC = PR.COD_PROC
                     AND PR.OID_PROC = CCSP.CCPR_OID_PROC
                     AND SP.COD_SUBP = CCSP.COD_SUBP);

          IF vnEncontroReg > 0 THEN
            cIndicMovim := 'R';

            IF vsIndComis = '1' THEN
              nMontoReclamo := ROUND(nMontoReclamo +
                                     (vsImpPago -
                                     (vsImpPago * nPorcDeduccion)),
                                     vnNumDeci);
            END IF;

            nMontoReclamoTota := nMontoReclamoTota + vsImpPago;

          END IF;
        END IF;

        --21

        --Secciï¿½n Evaluar Histï¿½rico Cuenta Corriente.
        IF (vsValUltiNumHist > 1) THEN
          cIndicMovim := NULL;
          OPEN c_historicoMVCC(vsOidMoviCC);
          LOOP
            FETCH c_historicoMVCC
              INTO vsSubOidSub, vsFecMoviHis, vsImpPagoHis, vsNumHist;
            EXIT WHEN c_historicoMVCC%NOTFOUND;

            BEGIN
              SELECT distinct (e.ind_tipo_movi)
                INTO vsIndTipoMovHis
                FROM CCC_SUBPR      b,
                     CCC_PROCE      c,
                     LEC_PROC_SUBPR d,
                     LEC_TIPO_MOVIM e
               WHERE b.oid_subp = vsSubOidSub
                 AND b.ccpr_oid_proc = c.oid_proc
                 AND c.cod_proc = d.cod_proc
                 AND b.cod_subp = d.cod_subp
                 AND d.PAIS_COD_PAIS = e.PAIS_COD_PAIS
                 AND d.LTMO_COD_MOVI = e.COD_MOVI
                 AND e.ind_tipo_movi in ('R', 'P');
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vsIndTipoMovHis := null;
            END;

            --Secciï¿½n  Evaluaciï¿½n Pagos para el HISTORICO(debe hacer los calculos,con los valores propios)
            --nMontoCargo := vsImpPagoHis;
            nMontoCargoHis := vsImpPagoHis;
            dFecPago       := vsFecMoviHis;
            IF (vsIndTipoMovHis = 'P') THEN
              cIndicMovim := 'P';
              IF (vsCodTipMedCobr = '01' or vsCodTipMedCobr = '03') THEN
                x := x + 1;
                IF (vsIndFeriado = 1) THEN
                  dFechLimite := COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(dFechLimite,
                                                                        pscodigoZona);
                END IF;
                nPagoTramo      := 0;
                nMontoDeduccion := 0;
                IF (dFecPago <= dFechLimite) THEN
                  IF vsIndComis = '1' THEN
                    nPagoTramo      := ROUND(nMontoCargoHis -
                                             (nMontoCargoHis *
                                             nPorcDeduccion),
                                             vnNumDeci);
                    nMontoDeduccion := ROUND(nMontoCargoHis *
                                             nPorcDeduccion,
                                             2);
                  END IF;
                  nPagoTramoTota := nMontoCargoHis;
                  --Tabla temporal valoresRecord
                  valores_rec.codigoTramo    := nCodigoTramo;
                  valores_rec.pagoTramo      := nPagoTramo;
                  valores_rec.pagoTramoTota  := nPagoTramoTota;
                  valores_rec.montoDeduccion := nMontoDeduccion;
                  valores_rec.fechaLimite    := dFechLimite;
                  valores_rec.secAmbito      := nSecAmbito;
                  valoresRecord1.extend;
                  valoresRecord1(x) := valores_rec;

                ELSE
                  x := x - 1;
                END IF;
              ELSIF (vsCodTipMedCobr = '02') THEN
                --INICIO cursor cobrObjeTramos
                nTramo        := 1;
                encontroTramo := FALSE;
                OPEN c_pcoTramos;
                LOOP
                  FETCH c_pcoTramos
                    INTO vsCodTramo;
                  EXIT WHEN c_pcoTramos%NOTFOUND;
                  y := y + 1;

                  BEGIN
                    SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                      INTO nSecAmbito, vsOTNumDias
                      FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                           LEC_PROGR_AMBIT_GEOGR       AGE
                     WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                       AND COT.LTUG_COD_TIPO_USO_GEOG =
                           AGE.LTUG_COD_TIPO_USO_GEOG
                       AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                       AND cot.lpro_cod_prog = age.lpro_cod_prog
                       AND COT.PAIS_COD_PAIS = psCodigoPais
                       AND COT.LPRO_COD_PROG = psCodigoPrograma
                       AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                       AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                       AND AGE.IND_PAIS = 1
                       AND COT.IND_ACTI = 1
                       AND COT.LPCT_COD_TRAM = vsCodTramo;
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                      nSecAmbito  := NULL;
                      vsOTNumDias := 0;
                  END;

                  IF nSecAmbito IS NULL THEN
                    BEGIN
                      SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                        INTO nSecAmbito, vsOTNumDias
                        FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                             LEC_PROGR_AMBIT_GEOGR       AGE
                       WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                         AND COT.LTUG_COD_TIPO_USO_GEOG =
                             AGE.LTUG_COD_TIPO_USO_GEOG
                         AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                         AND cot.lpro_cod_prog = age.lpro_cod_prog
                         AND COT.PAIS_COD_PAIS = psCodigoPais
                         AND COT.LPRO_COD_PROG = psCodigoPrograma
                         AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                         AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                         AND AGE.IND_ACTI = 1
                         AND AGE.COD_REGI = psCodigoRegion
                         AND AGE.COD_ZONA = pscodigoZona
                         AND COT.LPCT_COD_TRAM = vsCodTramo;

                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                        nSecAmbito := NULL;
                    END;
                  END IF;

                  IF nSecAmbito IS NULL THEN
                    BEGIN
                      SELECT COT.LPAG_SEC_AMBI_GEOG, COT.num_dias
                        INTO nSecAmbito, vsOTNumDias
                        FROM LEC_PROGR_COBRA_OBJET_TRAMO COT,
                             LEC_PROGR_AMBIT_GEOGR       AGE
                       WHERE COT.PAIS_COD_PAIS = AGE.PAIS_COD_PAIS
                         AND COT.LTUG_COD_TIPO_USO_GEOG =
                             AGE.LTUG_COD_TIPO_USO_GEOG
                         AND COT.LPAG_SEC_AMBI_GEOG = AGE.SEC_AMBI_GEOG
                         AND cot.lpro_cod_prog = age.lpro_cod_prog
                         AND COT.PAIS_COD_PAIS = psCodigoPais
                         AND COT.LPRO_COD_PROG = psCodigoPrograma
                         AND COT.LTUG_COD_TIPO_USO_GEOG = '01'
                         AND COT.LTMC_COD_TIPO_MEDI_COBR = vsCodTipMedCobr
                         AND AGE.IND_ACTI = 1
                         AND AGE.COD_REGI = psCodigoRegion
                         AND COT.LPCT_COD_TRAM = vsCodTramo;

                    EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                        nSecAmbito := NULL;
                    END;
                  END IF;

                  nDiasTramo       := vsOTNumDias;
                  dFechLimiteHabil := dFec_Cargo + nDiasTramo;
                  nPagoTramo       := 0;
                  nMontoDeduccion  := 0;

                  IF (vsIndFeriado = 1) THEN
                    dFechLimiteHabil := COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(dFechLimiteHabil,
                                                                               pscodigoZona);
                  END IF;

                  IF (nTramo = 1 AND dFecPago <= dFechLimiteHabil) OR
                     (nTramo > 1 AND dFecPago > dFechLimiteAnter AND
                     dFecPago <= dFechLimiteHabil) THEN

                    nCodigoTramo    := vsCodTramo;
                    nPagoTramo      := 0;
                    nMontoDeduccion := 0;

                    IF vsIndComis = '1' THEN
                      nPagoTramo      := ROUND(nMontoCargoHis -
                                               (nMontoCargoHis *
                                               nPorcDeduccion),
                                               vnNumDeci);
                      nMontoDeduccion := ROUND(nMontoCargoHis *
                                               nPorcDeduccion,
                                               2);
                    END IF;

                    nPagoTramoTota := nMontoCargoHis;

                    --Tabla temporal valoresRecord
                    valores_rec.codigoTramo    := nCodigoTramo;
                    valores_rec.pagoTramo      := nPagoTramo;
                    valores_rec.pagoTramoTota  := nPagoTramoTota;
                    valores_rec.montoDeduccion := nMontoDeduccion;
                    valores_rec.fechaLimite    := dFechLimiteHabil;
                    valores_rec.secAmbito      := nSecAmbito;
                    --Se agrega fechaIniAbono al Arreglo de tramos
                    IF (nTramo > 1) THEN
                      valores_rec.fechaIniAbono := dFechLimiteAnter + 1;
                    ELSE
                      valores_rec.fechaIniAbono := null;
                    END IF;

                    valoresRecord1.extend;
                    valoresRecord1(y) := valores_rec;
                    encontroTramo := TRUE;
                  ELSE
                    y := y - 1;
                  END IF;
                  nTramo           := nTramo + 1;
                  dFechLimiteAnter := dFechLimiteHabil;
                  IF encontroTramo THEN
                    EXIT;
                  END IF;
                END LOOP;
                CLOSE c_pcoTramos;

              END IF;

              --Secciï¿½n  Acumula Reclamos
            ELSIF (vsIndTipoMovHis = 'R') THEN

              SELECT COUNT(1)
                INTO vnEncontroReg
                FROM ccc_aplic_abono_cargo a,
                     mae_clien             mc,
                     CCC_HISTO_MOVIM_CC    mch,
                     ccc_movim_cuent_corri mcr
               WHERE a.clie_oid_clie = mc.oid_clie
                 AND a.clie_oid_clie = vnOidCliente
                 AND a.mvcc_oid_movi_carg = mch.mvcc_oid_movi_cc
                 and mch.mvcc_oid_movi_cc = vsOidMoviCC
                 and mch.num_hist = vsNumHist
                 and mch.num_hist = a.val_nume_hist
                 and a.mvcc_oid_movi_abon = mcr.oid_movi_cc
                 and mcr.clie_oid_clie = a.clie_oid_clie
                 and mcr.subp_oid_subp_crea in
                     (SELECT CCSP.OID_SUBP
                        FROM lec_tipo_movim TM,
                             LEC_PROC_SUBPR SP,
                             CCC_SUBPR      CCSP,
                             CCC_PROCE      PR
                       WHERE TM.IND_TIPO_MOVI = 'A'
                         AND TM.COD_MOVI = SP.LTMO_COD_MOVI
                         AND SP.COD_PROC = PR.COD_PROC
                         AND PR.OID_PROC = CCSP.CCPR_OID_PROC
                         AND SP.COD_SUBP = CCSP.COD_SUBP);

              IF vnEncontroReg > 0 THEN
                cIndicMovim := 'R';
                IF vsIndComis = '1' THEN
                  nMontoReclamo := ROUND(nMontoReclamo +
                                         (nMontoCargoHis -
                                         (nMontoCargoHis * nPorcDeduccion)),
                                         vnNumDeci);
                END IF;

                nMontoReclamoTota := nMontoReclamoTota + nMontoCargoHis;

              END IF;
            END IF;

          END LOOP;
          CLOSE c_historicoMVCC;

        END IF; --FIN 21

        --24.Coger el mayor monto entre nMontoReclamo y nMontoCargo
        IF (nMontoReclamo > nMontoCargo) THEN
          nMontoReclamo := nMontoCargo;
        END IF;
        IF (nMontoReclamoTota > nMontoCargoTota) THEN
          nMontoReclamoTota := nMontoCargoTota;
        END IF;
        -----  Consumo de Abonos en Proceso
        nMonAbonoPdteTota := 0;
        nMonAbonoPdte     := 0;
        IF nMonAbonoPdteClie > 0 THEN
          IF (nMontoCargoTota - nMontoReclamoTota) >= nMonAbonoPdteClie THEN
            nMonAbonoPdteTota := nMonAbonoPdteClie;
          ELSE
            nMonAbonoPdteTota := nMontoCargoTota - nMontoReclamoTota;
          END IF;
          IF vsIndComis = '1' THEN
            nMonAbonoPdte := ROUND((nMonAbonoPdteTota -
                                   (nMonAbonoPdteTota * nPorcDeduccion)),
                                   vnNumDeci);
          END IF;
          nMonAbonoPdteClie := nMonAbonoPdteClie - nMonAbonoPdteTota;
        END IF;

        --22 y 23.  De haber encontrado registros en Movimientos de Cuenta Corriente
        IF (nMontoCargoTota > 0) OR nIndFlexipago = 2 THEN

          BEGIN
            SELECT rownum
              INTO vnExisteRecup
              FROM LEC_LIDER_SECCI_RESUL_RECUP recup
             WHERE recup.pais_cod_pais = psCodigoPais
               AND recup.lpro_cod_prog = psCodigoPrograma
               AND recup.cod_regi = psCodigoRegion
               AND recup.cod_zona = pscodigoZona
               AND recup.cod_secc = psCodigoSeccion
               AND recup.cod_lide = psCodigoLider
               AND recup.cam_recu = psCampanaCobranza;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vnExisteRecup := NULL;
          END;

          IF (vnExisteRecup IS NOT NULL) THEN
            nPagoTramo := 0;
            --Actualizar registro
            UPDATE LEC_LIDER_SECCI_RESUL_RECUP recup
               SET recup.val_mont_carg           = recup.val_mont_carg +
                                                   nvl(nMontoCargo, 0),
                   recup.val_mont_cdrs           = recup.val_mont_cdrs +
                                                   nvl(nMontoReclamo, 0),
                   recup.val_mont_ncom           = recup.val_mont_ncom +
                                                   nvl(vnValorDeduccion, 0),
                   recup.VAL_MONT_CARG_TOTA      = recup.VAL_MONT_CARG_TOTA +
                                                   nvl(nMontoCargoTota, 0),
                   recup.VAL_MONT_CDRS_TOTA      = recup.VAL_MONT_CDRS_TOTA +
                                                   nvl(nMontoReclamoTota, 0),
                   recup.val_mont_abon_pdte      = recup.val_mont_abon_pdte +
                                                   nvl(nMonAbonoPdte, 0),
                   recup.val_mont_abon_pdte_tota = recup.val_mont_abon_pdte_tota +
                                                   nvl(nMonAbonoPdteTota, 0),
                   recup.usu_modi                = psCodigoUsuario,
                   recup.fec_modi                = sysdate
             WHERE recup.pais_cod_pais = psCodigoPais
               AND recup.lpro_cod_prog = psCodigoPrograma
               AND recup.cod_regi = psCodigoRegion
               AND recup.cod_zona = pscodigoZona
               AND recup.cod_secc = psCodigoSeccion
               AND recup.cod_lide = psCodigoLider
               AND recup.cam_recu = psCampanaCobranza;
          ELSE
            --Crear registro.
            INSERT INTO LEC_LIDER_SECCI_RESUL_RECUP
              (PAIS_COD_PAIS,
               LPRO_COD_PROG,
               COD_REGI,
               COD_ZONA,
               COD_SECC,
               COD_LIDE,
               CAM_RECU,
               VAL_MONT_CARG,
               VAL_MONT_CDRS,
               VAL_MONT_NCOM,
               VAL_MONT_CARG_TOTA,
               VAL_MONT_CDRS_TOTA,
               val_mont_abon_pdte,
               Val_Mont_Abon_Pdte_Tota,
               USU_CREA,
               FEC_CREA,
               USU_MODI,
               FEC_MODI,
               IND_ACTI,
               CAM_PROC,
               VAL_MONT_RTAL)
            VALUES
              (psCodigoPais,
               psCodigoPrograma,
               psCodigoRegion,
               pscodigoZona,
               psCodigoSeccion,
               psCodigoLider,
               psCampanaCobranza,
               nvl(nMontoCargo, 0),
               nvl(nMontoReclamo, 0),
               nvl(vnValorDeduccion, 0),
               nvl(nMontoCargoTota, 0),
               nvl(nMontoReclamoTota, 0),
               nvl(nMonAbonoPdte, 0),
               nvl(nMonAbonoPdteTota, 0),
               psCodigoUsuario,
               SYSDATE,
               NULL,
               NULL,
               '1',
               vsPerioFactu,
               0);

          END IF;

          --24.  Con psCodigoPais, psCodigoPrograma, psCodigoRegion, psCodigoZona, psCodigoSecciï¿½n,
          --psCodigoLider, psCodigoConsultora, psCampanaCobranza y MVCC_OID_MOVI_CC
          --accesar la tabla Lï¿½der Detalle Recuperaciï¿½n.

          BEGIN

            SELECT ROWNUM
              INTO vnExisteDRecup
              FROM LEC_LIDER_SECCI_DETAL_RECUP drecup
             WHERE drecup.pais_cod_pais = psCodigoPais
               AND drecup.lpro_cod_prog = psCodigoPrograma
               AND drecup.cod_regi = psCodigoRegion
               AND drecup.cod_zona = pscodigoZona
               AND drecup.cod_secc = psCodigoSeccion
               AND drecup.cod_lide = psCodigoLider
               AND drecup.cam_recu = psCampanaCobranza
               AND drecup.mvcc_oid_movi_cc = vsOidMoviCC;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vnExisteDRecup := NULL;
          END;

         --EVALUAR RANGO DE NIVELES
         BEGIN
           SELECT COD_RANG
             INTO nCodigoRango
             FROM LEC_PROGR_NIVEL_RANGO
            WHERE PAIS_COD_PAIS = psCodigoPais
              AND LPRO_COD_PROG = psCodigoPrograma
              AND VAL_MONT_MINI <= nMontoCargo
              AND VAL_MONT_MAXI >= nMontoCargo
              AND IND_ACTI = 1;
         EXCEPTION
           WHEN OTHERS THEN
             nCodigoRango := NULL;   
         END;

          IF (vnExisteDRecup IS NOT NULL) THEN
            --Actualizar registro
            UPDATE LEC_LIDER_SECCI_DETAL_RECUP drecup
               SET drecup.val_mont_carg           = drecup.val_mont_carg +
                                                    nvl(nMontoCargo, 0),
                   drecup.val_mont_cdrs           = drecup.val_mont_cdrs +
                                                    nvl(nMontoReclamo, 0),
                   drecup.val_mont_ncom           = drecup.val_mont_ncom +
                                                    nvl(vnValorDeduccion, 0),
                   drecup.val_mont_carg_tota      = drecup.val_mont_carg_tota +
                                                    nvl(nMontoCargoTota, 0),
                   drecup.val_mont_cdrs_tota      = drecup.val_mont_cdrs_tota +
                                                    nvl(nMontoReclamoTota, 0),
                   drecup.val_mont_abon_pdte      = drecup.val_mont_abon_pdte +
                                                    nvl(nMonAbonoPdte, 0),
                   drecup.val_mont_abon_pdte_tota = drecup.val_mont_abon_pdte_tota +
                                                    nvl(nMonAbonoPdteTota, 0),
                   drecup.val_porc_comi           = 0,
                   drecup.val_mont_rtal           = 0,
                   drecup.usu_modi                = psCodigoUsuario,
                   drecup.fec_modi                = sysdate
             WHERE drecup.pais_cod_pais = psCodigoPais
               AND drecup.lpro_cod_prog = psCodigoPrograma
               AND drecup.cod_regi = psCodigoRegion
               AND drecup.cod_zona = pscodigoZona
               AND drecup.cod_secc = psCodigoSeccion
               AND drecup.cod_lide = psCodigoLider
               AND drecup.cam_recu = psCampanaCobranza
               AND drecup.mvcc_oid_movi_cc = vsOidMoviCC;

          ELSE
            --Crear registro.
            INSERT INTO LEC_LIDER_SECCI_DETAL_RECUP
              (PAIS_COD_PAIS,
               LPRO_COD_PROG,
               COD_REGI,
               COD_ZONA,
               COD_SECC,
               COD_LIDE,
               CAM_RECU,
               COD_CONS,
               MVCC_OID_MOVI_CC,
               SOCA_OID_SOLI_CABE,
               VAL_NUME_SOLI,
               VAL_MONT_CARG,
               VAL_MONT_CDRS,
               VAL_MONT_NCOM,
               VAL_MONT_CARG_TOTA,
               VAL_MONT_CDRS_TOTA,
               val_mont_abon_pdte,
               Val_Mont_Abon_Pdte_Tota,
               POR_FACT_NCOM,
               FEC_CARG,
               IND_FLEX,
               IND_PEDI_CONS,
               VAL_PORC_COMI,
               USU_CREA,
               FEC_CREA,
               USU_MODI,
               FEC_MODI,
               IND_ACTI,
               val_mont_rtal,
               PNRA_COD_RANG)
            VALUES
              (psCodigoPais,
               psCodigoPrograma,
               psCodigoRegion,
               pscodigoZona,
               psCodigoSeccion,
               psCodigoLider,
               psCampanaCobranza,
               psCodigoConsultora,
               vsOidMoviCC,
               vsOidSoliCabe,
               vsNumeSolic,
               nvl(nMontoCargo, 0),
               nvl(nMontoReclamo, 0),
               nvl(vnValorDeduccion, 0),
               nvl(nMontoCargoTota, 0),
               nvl(nMontoReclamoTota, 0),
               nvl(nMonAbonoPdte, 0),
               nvl(nMonAbonoPdteTota, 0),
               nvl(nPorcDeduccion, 0),
               dFec_Cargo,
               nIndFlexipago,
               vsIndPediCons,
               0,
               psCodigoUsuario,
               SYSDATE,
               NULL,
               NULL,
               '1',
               0,
               nCodigoRango);

          END IF;

          --25.
          IF (valoresRecord1 IS NOT NULL AND valoresRecord1.COUNT > 0) THEN

            FOR i IN valoresRecord1.FIRST .. valoresRecord1.LAST LOOP

              ncodTramo      := valoresRecord1(i).codigoTramo;
              nPagoTramo     := valoresRecord1(i).pagoTramo;
              nPagoTramoTota := valoresRecord1(i).pagoTramoTota;
              dFechaLimite   := valoresRecord1(i).fechaLimite;
              dFechaIniAbo   := valoresRecord1(i).fechaIniAbono;
              nSecAmbito     := valoresRecord1(i).secAmbito;

              BEGIN
                SELECT ROWNUM,
                       recupTramo.Val_Mont_Recu,
                       recupTramo.Val_Mont_Reca_Pedi_Cons,
                       recupTramo.Val_Mont_Reca_Pedi_Ncon,
                       VAL_MONT_RECU_TOTA
                  INTO vnExisteRecupTramo,
                       nValMontRecu,
                       nValMontRecaPediCons,
                       nValMontRecaPediNCon,
                       nValMontRecuTota
                  FROM LEC_LIDER_SECCI_RECUP_TRAMO recupTramo
                 WHERE recupTramo.Pais_Cod_Pais = psCodigoPais
                   AND recupTramo.lpro_cod_prog = psCodigoPrograma
                   AND recupTramo.cod_regi = psCodigoRegion
                   AND recupTramo.cod_zona = pscodigoZona
                   AND recupTramo.cod_secc = psCodigoSeccion
                   AND recupTramo.cod_lide = psCodigoLider
                   AND recupTramo.cam_recu = psCampanaCobranza
                   AND recupTramo.Lpct_Cod_Tram = ncodTramo;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  vnExisteRecupTramo   := NULL;
                  nValMontRecu         := 0;
                  nValMontRecaPediCons := 0;
                  nValMontRecaPediNCon := 0;
                  nValMontRecuTota     := 0;

              END;

              IF (vnExisteRecupTramo IS NOT NULL) THEN
                nValMontRecu     := nValMontRecu + nPagoTramo;
                nValMontRecuTota := nValMontRecuTota + nPagoTramoTota;
                nValMontCDRS     := 0;
                nValMontNCOM     := 0;

                IF (vsIndPediCons = '1') THEN
                  nValMontRecaPediCons := nValMontRecaPediCons + nPagoTramo;
                ELSE
                  nValMontRecaPediNCon := nValMontRecaPediNCon + nPagoTramo;
                END IF;

                --Actualizar registro
                UPDATE LEC_LIDER_SECCI_RECUP_TRAMO recupTramo
                   SET recupTramo.Val_Mont_Recu           = nvl(nValMontRecu,
                                                                0),
                       recupTramo.Val_Mont_Reca_Pedi_Cons = nvl(nValMontRecaPediCons,
                                                                0),
                       recupTramo.Val_Mont_Reca_Pedi_Ncon = nvl(nValMontRecaPediNCon,
                                                                0),
                       recupTramo.VAL_MONT_RECU_TOTA      = nvl(nValMontRecuTota,
                                                                0),
                       recupTramo.usu_modi                = psCodigoUsuario,
                       recupTramo.fec_modi                = sysdate
                 WHERE recupTramo.Pais_Cod_Pais = psCodigoPais
                   AND recupTramo.lpro_cod_prog = psCodigoPrograma
                   AND recupTramo.cod_regi = psCodigoRegion
                   AND recupTramo.cod_zona = pscodigoZona
                   AND recupTramo.cod_secc = psCodigoSeccion
                   AND recupTramo.cod_lide = psCodigoLider
                   AND recupTramo.cam_recu = psCampanaCobranza
                   AND recupTramo.Lpct_Cod_Tram = ncodTramo;

              ELSE
                nValMontRecu     := nPagoTramo;
                nValMontCDRS     := 0;
                nValMontNCOM     := 0;
                nValMontRecuTota := nPagoTramoTota;

                IF (vsIndPediCons = '1') THEN
                  nValMontRecaPediCons := nPagoTramo;
                ELSE
                  nValMontRecaPediNCon := nPagoTramo;
                END IF;

                --Crear registro.
                INSERT INTO LEC_LIDER_SECCI_RECUP_TRAMO
                  (PAIS_COD_PAIS,
                   LPRO_COD_PROG,
                   COD_REGI,
                   COD_ZONA,
                   COD_SECC,
                   COD_LIDE,
                   CAM_RECU,
                   LPCT_COD_TRAM,
                   VAL_MONT_RECU,
                   VAL_MONT_RECA_PEDI_CONS,
                   VAL_MONT_RECA_PEDI_NCON,
                   VAL_MONT_RECU_TOTA,
                   USU_CREA,
                   FEC_CREA,
                   USU_MODI,
                   FEC_MODI,
                   IND_ACTI,
                   LPAG_SEC_AMBI_GEOG)
                VALUES
                  (psCodigoPais,
                   psCodigoPrograma,
                   psCodigoRegion,
                   pscodigoZona,
                   psCodigoSeccion,
                   psCodigoLider,
                   psCampanaCobranza,
                   ncodTramo,
                   nvl(nValMontRecu, 0),
                   nvl(nValMontRecaPediCons, 0),
                   nvl(nValMontRecaPediNCon, 0),
                   nvl(nValMontRecuTota, 0),
                   psCodigoUsuario,
                   SYSDATE,
                   NULL,
                   NULL,
                   '1',
                   nSecAmbito);

              END IF;

              --26.  Con psCodigoPais, psCodigoPrograma, psCodigoRegion, psCodigoZona,
              --psCodigoSecciï¿½n, psCodigoLider, psCodigoConsultora, psCampanaCobranza,
              --Cï¿½digo de Tramo y MVCC_OID_MOVI_CC accesar la tabla Lï¿½der Detalle Recuperaciï¿½n Tramo.

              BEGIN
                SELECT ROWNUM,
                       drecupTra.Val_Mont_Abon,
                       drecupTra.VAL_MONT_ABON_TOTA
                  INTO vnExisteDRecupTramo,
                       vnDRET_val_mont_abon,
                       vnDRET_VAL_MONT_ABON_TOTA
                  FROM LEC_LIDER_SECCI_DRECU_TRAMO drecupTra
                 WHERE drecupTra.pais_cod_pais = psCodigoPais
                   AND drecupTra.lpro_cod_prog = psCodigoPrograma
                   AND drecupTra.cod_regi = psCodigoRegion
                   AND drecupTra.cod_zona = pscodigoZona
                   AND drecupTra.cod_secc = psCodigoSeccion
                   AND drecupTra.cod_lide = psCodigoLider
                   AND drecupTra.Cod_Cons = psCodigoConsultora
                   AND drecupTra.Lpct_Cod_Tram = ncodTramo
                   AND drecupTra.cam_recu = psCampanaCobranza
                   AND drecupTra.Mvcc_Oid_Movi_Cc = vsOidMoviCC;
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  vnExisteDRecupTramo       := NULL;
                  vnDRET_val_mont_abon      := 0;
                  vnDRET_VAL_MONT_ABON_TOTA := 0;
              END;

              IF (vnExisteDRecupTramo IS NOT NULL) THEN
                vnDRET_val_mont_abon      := vnDRET_val_mont_abon +
                                             nPagoTramo;
                vnDRET_VAL_MONT_ABON_TOTA := vnDRET_VAL_MONT_ABON_TOTA +
                                             nPagoTramoTota;

                --Actualizar registro
                UPDATE LEC_LIDER_SECCI_DRECU_TRAMO drecupTra
                   SET drecupTra.val_mont_abon      = vnDRET_val_mont_abon,
                       drecupTra.VAL_MONT_ABON_TOTA = vnDRET_VAL_MONT_ABON_TOTA,
                       drecupTra.usu_modi           = psCodigoUsuario,
                       drecupTra.Fec_Ini_Abon       = dFechaIniAbo,
                       drecupTra.fec_modi           = sysdate
                 WHERE drecupTra.pais_cod_pais = psCodigoPais
                   AND drecupTra.lpro_cod_prog = psCodigoPrograma
                   AND drecupTra.cod_regi = psCodigoRegion
                   AND drecupTra.cod_zona = pscodigoZona
                   AND drecupTra.cod_secc = psCodigoSeccion
                   AND drecupTra.cod_lide = psCodigoLider
                   AND drecupTra.Cod_Cons = psCodigoConsultora
                   AND drecupTra.Lpct_Cod_Tram = ncodTramo
                   AND drecupTra.cam_recu = psCampanaCobranza
                   AND drecupTra.Mvcc_Oid_Movi_Cc = vsOidMoviCC;

              ELSE
                vnDRET_val_mont_abon      := nPagoTramo;
                vnDRET_VAL_MONT_ABON_TOTA := nPagoTramoTota;

                --Grabar en Lï¿½der detalle Recuperaciï¿½n
                INSERT INTO LEC_LIDER_SECCI_DRECU_TRAMO
                  (PAIS_COD_PAIS,
                   LPRO_COD_PROG,
                   COD_REGI,
                   COD_ZONA,
                   COD_SECC,
                   COD_LIDE,
                   CAM_RECU,
                   LPCT_COD_TRAM,
                   COD_CONS,
                   MVCC_OID_MOVI_CC,
                   FEC_LIMI_ABON,
                   VAL_MONT_ABON,
                   VAL_MONT_ABON_TOTA,
                   FEC_INI_ABON,
                   VAL_PORC_COMI,
                   USU_CREA,
                   FEC_CREA,
                   USU_MODI,
                   FEC_MODI,
                   IND_ACTI)
                VALUES
                  (psCodigoPais,
                   psCodigoPrograma,
                   psCodigoRegion,
                   pscodigoZona,
                   psCodigoSeccion,
                   psCodigoLider,
                   psCampanaCobranza,
                   ncodTramo,
                   psCodigoConsultora,
                   vsOidMoviCC,
                   dFechaLimite,
                   vnDRET_val_mont_abon,
                   vnDRET_VAL_MONT_ABON_TOTA,
                   dFechaIniAbo,
                   0,
                   psCodigoUsuario,
                   sysdate,
                   null,
                   null,
                   1);
              END IF;

            END LOOP; --fin Loop del Arreglo

            valoresRecord1.DELETE;
            x := 0;
            y := 0;
          END IF;

        END IF; --fiN indicadorMovimientoCC

      END LOOP;
      CLOSE c_cargosMovimientosCC; --FIN CURSOR INICIAL

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_RECUP_CONSU: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_RECUP_CONSU;

/***************************************************************************
       Descripcion       : Proceso Calcular Recuperaciï¿½n Grupo.044
       Fecha Creacion    : 12/03/2014
       Autor             : Juan Altamirano
  ****************************************************************************/
  PROCEDURE LEC_PR_CALCU_RESUL_RECUP_GRUPO(psCodigoPais      VARCHAR2,
                                           psCodigoPrograma  VARCHAR2,
                                           psCampanaProceso  VARCHAR2,
                                           psCampanaCobranza VARCHAR2,
                                           psCodigoGrupoPago VARCHAR2,
                                           psCodigoUsuario   VARCHAR2) IS

    vsOidmarca      seg_marca.oid_marc%TYPE;
    vsOidcanal      seg_canal.oid_cana%TYPE;
    vnOidPeriodo    cra_perio.oid_peri%TYPE;
    vsCodigoLider   mae_clien.cod_clie%TYPE;
    vsCodRegi       zon_regio.cod_regi%TYPE;
    vsCodZona       zon_zona.cod_zona%TYPE;
    vsCodSecc       zon_secci.cod_secc%TYPE;
    vsCodigoCliente mae_clien.cod_clie%TYPE;
    --
    pscodigomarca SEG_MARCA.COD_MARC%TYPE;
    pscodigocanal SEG_CANAL.COD_CANA%TYPE;
    psCodigoLider LEC_LIDER_NIVEL.COD_LIDE%TYPE;
    --
    lsCodigoRegion   zon_regio.cod_regi%TYPE;
    lsCodigoZona     zon_zona.cod_zona%TYPE;
    lsCampaBono      seg_perio_corpo.cod_peri%TYPE;
    lsCampaRecaudo   seg_perio_corpo.cod_peri%TYPE;
    lsCampaBase      seg_perio_corpo.cod_peri%TYPE;
    lsCodigoPrograma lec_progr.cod_prog%TYPE;
    lsIndCampaAnte   VARCHAR2(1);

    ldFecCierr        FAC_PROGR_CIERR.FEC_CIER%TYPE; --Ajuste Optimizacion
    ldFecCierrZona    FAC_PROGR_CIERR.FEC_CIER%TYPE; --Ajuste Optimizacion
    lsCampAnterior    BAS_CTRL_FACT.COD_PERI%TYPE; --Ajuste Optimizacion
    lnOidPais         NUMBER; --Ajuste Optimizacion
    lnOidMarca        NUMBER; --Ajuste Optimizacion
    lnOidCanal        NUMBER; --Ajuste Optimizacion
    vnMonAbonPdte     NUMBER;
    vnMonRecNoEx      NUMBER;
    vnMonAbonPdteClie NUMBER;

    lsGrupoPago VARCHAR2(1);

    CURSOR c_RegionZona(vnOidPeriodo NUMBER, vsCampaBase VARCHAR2) IS

    WITH regionLider AS(
      SELECT DISTINCT gere.cod_regi, gere.cod_zona
        FROM zon_histo_geren gere,
             cra_perio       perd_c,
             seg_perio_corpo peri_c,
             cra_perio       perd_d,
             seg_perio_corpo peri_d
       WHERE gere.perd_oid_peri_desd = perd_c.oid_peri
         AND perd_c.peri_oid_peri = peri_c.oid_peri
         AND NVL(gere.perd_oid_peri_hast, vnOidPeriodo) = perd_d.oid_peri
         AND perd_d.peri_oid_peri = peri_d.oid_peri
         AND gere.cod_regi IS NOT NULL
         AND gere.cod_zona IS NOT NULL
         AND gere.cod_secc IS NOT NULL
         AND vsCampaBase BETWEEN peri_c.cod_peri AND peri_d.cod_peri
         AND lsGrupoPago = 'Z'
         AND (psCodigoGrupoPago IS NULL OR
             (psCodigoGrupoPago IS NOT NULL AND
             gere.cod_zona IN
             (SELECT DGR.COD_REGI
                  FROM LEC_GRUPO_PROCE_RECAU       CGR,
                       LEC_GRUPO_PROCE_RECAU_REGIO DGR
                 WHERE CGR.PAIS_COD_PAIS = DGR.PAIS_COD_PAIS
                   AND CGR.COD_GRUP_REGI = DGR.LGPR_COD_GRUP_REGI
                   AND CGR.PAIS_COD_PAIS = psCodigoPais
                   AND CGR.COD_TIPO_GRUP = lsGrupoPago
                   AND (psCodigoGrupoPago IS NULL OR
                       (psCodigoGrupoPago IS NOT NULL AND
                       CGR.COD_GRUP_REGI = psCodigoGrupoPago))
                   AND CGR.IND_ACTI = '1'
                   AND DGR.IND_ACTI = '1')))
      UNION

      SELECT DISTINCT gere.cod_regi, null cod_zona
        FROM zon_histo_geren gere,
             cra_perio       perd_c,
             seg_perio_corpo peri_c,
             cra_perio       perd_d,
             seg_perio_corpo peri_d
       WHERE gere.perd_oid_peri_desd = perd_c.oid_peri
         AND perd_c.peri_oid_peri = peri_c.oid_peri
         AND NVL(gere.perd_oid_peri_hast, vnOidPeriodo) = perd_d.oid_peri
         AND perd_d.peri_oid_peri = peri_d.oid_peri
         AND gere.cod_regi IS NOT NULL
         AND gere.cod_zona IS NOT NULL
         AND gere.cod_secc IS NOT NULL
         AND vsCampaBase BETWEEN peri_c.cod_peri AND peri_d.cod_peri
         AND lsGrupoPago = 'R'
         AND (psCodigoGrupoPago IS NULL OR
             (psCodigoGrupoPago IS NOT NULL AND
             gere.cod_regi IN
             (SELECT SUBSTR(DGR.COD_REGI, 1, 2)
                  FROM LEC_GRUPO_PROCE_RECAU       CGR,
                       LEC_GRUPO_PROCE_RECAU_REGIO DGR
                 WHERE CGR.PAIS_COD_PAIS = DGR.PAIS_COD_PAIS
                   AND CGR.COD_GRUP_REGI = DGR.LGPR_COD_GRUP_REGI
                   AND CGR.PAIS_COD_PAIS = psCodigoPais
                   AND CGR.COD_TIPO_GRUP = lsGrupoPago
                   AND (psCodigoGrupoPago IS NULL OR
                       (psCodigoGrupoPago IS NOT NULL AND
                       CGR.COD_GRUP_REGI = psCodigoGrupoPago))
                   AND CGR.IND_ACTI = '1'
                   AND DGR.IND_ACTI = '1'))))
        SELECT regionLider.cod_regi,
         regionLider.cod_zona,
         CASE
           WHEN psCampanaCobranza IS NOT NULL THEN
            psCampanaProceso
           ELSE
            lec_pkg_proce.lec_fn_obte_campa_recu(psCodigoPais,
                                                 psCodigoPrograma,
                                                 psCampanaProceso,
                                                 regionLider.cod_regi,
                                                 regionLider.cod_zona,
                                                 'B')
         END val_camp_bono,
         CASE
           WHEN psCampanaCobranza IS NOT NULL THEN
            psCampanaCobranza
           ELSE
            lec_pkg_proce.lec_fn_obte_campa_recu(psCodigoPais,
                                                 psCodigoPrograma,
                                                 psCampanaProceso,
                                                 regionLider.cod_regi,
                                                 regionLider.cod_zona,
                                                 'R')
         END val_camp_reca,
         (SELECT prog.cod_prog
            FROM lec_progr prog
           WHERE (CASE
                   WHEN psCampanaCobranza IS NOT NULL THEN
                    psCampanaCobranza
                   ELSE
                    lec_pkg_proce.lec_fn_obte_campa_recu(psCodigoPais,
                                                         psCodigoPrograma,
                                                         psCampanaProceso,
                                                         regionLider.cod_regi,
                                                         regionLider.cod_zona,
                                                         'R')
                 END) BETWEEN prog.cam_inic AND
                 NVL(prog.cam_fin, psCampanaProceso)) cod_prog
          FROM regionLider
         ORDER BY regionLider.cod_regi;


    CURSOR c_regiRecup(vnOidPeriodo   NUMBER,
                       vsCodRegi      VARCHAR2,
                       vsCodZona      VARCHAR2,
                       vsCampaRecaudo VARCHAR2) IS
      SELECT lide.marc_oid_marc,
             lide.cana_oid_cana,
             lide.cod_regi,
             lide.cod_zona,
             lide.cod_secc,
             lide.gere cod_lide,
             clie.cod_clie,
             (SELECT sum(((nvl(VAL_PREC_CATA_UNIT_LOCA, 0) -
                         nvl(val_impo_desc_unit_loca, 0)) *
                         abs(nvl(num_unid_dema, 0))))
                FROM rec_cabec_recla     rcr,
                     rec_opera_recla     ror,
                     rec_solic_opera     rso,
                     ped_solic_Cabec     psc,
                     ped_solic_posic     psp,
                     ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts
               WHERE RCR.OID_CABE_RECL = ROR.CARE_OID_CABE_RECL
                 and ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
                 AND RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
                 AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                 AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.oid_tipo_soli_pais
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.cod_tipo_soli like 'SD%'
                 AND RCR.CLIE_OID_CLIE = clie.oid_clie
                 AND RCR.PERD_OID_PERI_DOCU_REFE = vnOidPeriodo
                 AND PSC.GRPR_OID_GRUP_PROC = 3
                 AND RCR.IND_ABON_PDTE = 1
                 AND psp.espo_oid_esta_posi <> 2) nMonAbonPdte,
             (select sum(((nvl(VAL_PREC_CATA_UNIT_LOCA, 0) -
                         nvl(val_impo_desc_unit_loca, 0)) *
                         abs(nvl(num_unid_dema, 0))))
                from rec_cabec_recla       rcr,
                     rec_opera_recla       ror,
                     rec_linea_opera_recla rlo,
                     ped_solic_posic       pos
               where RCR.OID_CABE_RECL = ROR.CARE_OID_CABE_RECL
                 and ROR.OID_OPER_RECL = RLO.OPRE_OID_OPER_RECL
                 and RLO.SOPO_OID_SOLI_POSI = POS.OID_SOLI_POSi
                 and ror.esop_oid_esta_oper in (4, 8) ---- eliminados  /  NO EXITOSOS (OTROS PAISES)  /  NO EXITOSOS (PE)
                 and RCR.CLIE_OID_CLIE = clie.oid_clie
                 and rcr.PERD_OID_PERI_DOCU_REFE = vnOidPeriodo
                 and RLO.TIMO_OID_TIPO_MOVI = 2) nMonRecNoEx
        FROM zon_secci zscc,
             zon_zona zzon,
             zon_regio zorg,
             cra_perio perd,
             seg_perio_corpo peri,
             cra_perio perd_b,
             seg_perio_corpo peri_b,
             (select gere.gere,
                     gere.cod_regi,
                     gere.cod_zona,
                     gere.cod_secc,
                     gere.perd_oid_peri_desd,
                     gere.perd_oid_peri_hast,
                     gere.marc_oid_marc,
                     gere.cana_oid_cana
                from zon_histo_geren gere,
                     cra_perio       perd_c,
                     seg_perio_corpo peri_c,
                     cra_perio       perd_d,
                     seg_perio_corpo peri_d
               where gere.perd_oid_peri_desd = perd_c.oid_peri
                 and perd_c.peri_oid_peri = peri_c.oid_peri
                 and nvl(gere.perd_oid_peri_hast, vnOidPeriodo) =
                     perd_d.oid_peri
                 and perd_d.peri_oid_peri = peri_d.oid_peri
                 and gere.cod_regi is not null
                 and gere.cod_zona is not null
                 and gere.cod_secc is not null
                 and vsCampaRecaudo between peri_c.cod_peri and
                     peri_d.cod_peri
                 and gere.cod_regi = vsCodRegi
                 and (vsCodZona is null or gere.cod_zona = vsCodZona)) lide,
             mae_clien_unida_admin cuad,
             mae_clien clie,
             mae_clien_datos_adici clda,
             zon_terri_admin ztad,
             zon_secci zscc_b,
             zon_zona zzon_b,
             zon_regio zorg_b
       where 1 = 1
         and zscc.zzon_oid_zona = zzon.oid_zona
         and zzon.zorg_oid_regi = zorg.oid_regi
         and zscc.perd_oid_peri_inic = perd.oid_peri
         and perd.peri_oid_peri = peri.oid_peri
         and nvl(zscc.perd_oid_peri_fina, vnOidPeriodo) = perd_b.oid_peri
         and perd_b.peri_oid_peri = peri_b.oid_peri
            --
         and clie.oid_clie = clda.clie_oid_clie
            --
         and cuad.clie_oid_clie = clie.oid_clie
         and cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         and ztad.zscc_oid_secc = zscc_b.oid_secc
         and zscc_b.zzon_oid_zona = zzon_b.oid_zona
         and zzon_b.zorg_oid_regi = zorg_b.oid_regi
         and zorg_b.cod_regi || zzon_b.cod_zona || zscc_b.cod_secc =
             zorg.cod_regi || zzon.cod_zona || zscc.cod_secc
            --
         and vsCampaRecaudo between peri.cod_peri and peri_b.cod_peri
         and vnOidPeriodo between cuad.perd_oid_peri_ini and
             nvl(cuad.perd_oid_peri_fin, vnOidPeriodo)
         and zorg.cod_regi || zzon.cod_zona || zscc.cod_secc =
             lide.cod_regi || lide.cod_zona || lide.cod_secc
            --
         and not exists
       (select distinct (lpex.cod_cons)
                from lec_progr_lista_exclu lpex
               where lpex.pais_cod_pais = pscodigopais
                    -- and lpex.lpro_cod_prog = pscodigoprograma
                 and vsCampaRecaudo between lpex.cam_inic_vige and
                     lpex.cam_fin_vige
                 and lpex.ind_acti = 1
                 and lpex.cod_cons = clie.cod_clie);
  BEGIN
    --Obtenemos los oid de Pais, Marca y Canal
    lnOidPais  := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    lnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

    BEGIN
      SELECT VAL_PARA
        INTO lsGrupoPago
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = psCodigoPais
         AND COD_SIST = 'LET'
         AND UPPER(NOM_PARA) = UPPER('indTipoGrupoPago')
         AND IND_ACTI = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsGrupoPago := NULL;
    END;

    -- Verifica si el procedimiento se ha llamado desde un proceso automï¿½tico
    IF psCampanaCobranza IS NULL THEN
      -- Obtiene valor del indicador de calculo de recuperacion en campaï¿½a X-n
      BEGIN
        SELECT val_para
          INTO lsIndCampaAnte
          FROM bas_param_pais
         WHERE cod_pais = psCodigoPais
           AND cod_sist = 'LET'
           AND UPPER(nom_para) = 'INDCAMPAANTE';
      EXCEPTION
        WHEN no_data_found THEN
          lsIndCampaAnte := '0';
      END;
      -- Obtiene campaï¿½a base para cargar las lideres que se van a procesar
      lsCampaBase := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                      psCampanaProceso,
                                                      - (to_number(lsIndCampaAnte)));
    ELSE
      lsCampaBase := psCampanaCobranza;
    END IF;
    -- Obtiene el oid del periodo de Cobranza
    vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCampaBase);
    -- ==============================================
    -- Se obtienen las regiones que se van a procesar
    -- ==============================================
    OPEN c_RegionZona(vnOidPeriodo, lsCampaBase);
    LOOP
      FETCH c_RegionZona
        INTO lsCodigoRegion,
             lsCodigoZona,
             lsCampaBono,
             lsCampaRecaudo,
             lsCodigoPrograma;
      EXIT WHEN c_RegionZona%NOTFOUND;

      -- Obtiene campaï¿½a anterior a campaï¿½a de recaudo
      lsCampAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCampaRecaudo,
                                                               lnOidPais,
                                                               lnOidMarca,
                                                               lnOidCanal,
                                                               -1);

      --Obtiene Fecha de Cierre Region
      BEGIN
        SELECT FEC_CIER
          INTO ldFecCierr
          FROM FAC_PROGR_CIERR
         WHERE cod_regi = lsCodigoRegion
           AND cam_proc = GEN_FN_CALCU_PERIO(lsCampaRecaudo, +1)
           AND tip_cier = 'R'
           AND est_cier = 'P';
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          ldFecCierr := NULL;
      END;

      --Obtiene Fecha de Cierre Zona
      BEGIN
        SELECT FEC_CIER
          INTO ldFecCierrZona
          FROM FAC_PROGR_CIERR
         WHERE cod_regi = lsCodigoRegion
           AND COD_ZONA = lsCodigoZona
           AND cam_proc = GEN_FN_CALCU_PERIO(lsCampaRecaudo, +1)
           AND tip_cier = 'Z'
           AND est_cier = 'P';
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          ldFecCierrZona := NULL;
      END;

      -- Borrar registros calculados en Detalle Recuperaciï¿½n por Tramos
      DELETE FROM lec_lider_secci_drecu_tramo ldrt
       WHERE ldrt.pais_cod_pais = psCodigoPais
         AND ldrt.lpro_cod_prog = lsCodigoPrograma --Modificado
         AND ldrt.cod_regi = lsCodigoRegion
         AND (lsCodigoZona IS NULL OR
             (lsCodigoZona IS NOT NULL AND ldrt.cod_zona = lsCodigoZona)) --- Yo
         AND ldrt.cam_recu = lsCampaRecaudo;
      -- Borrar registros calculados en Cabecera Recuperaciï¿½n por Tramos
      DELETE FROM lec_lider_secci_recup_tramo lrrt
       WHERE lrrt.pais_cod_pais = psCodigoPais
         AND lrrt.lpro_cod_prog = lsCodigoPrograma -- Modificado
         AND lrrt.cod_regi = lsCodigoRegion
         AND (lsCodigoZona IS NULL OR
             (lsCodigoZona IS NOT NULL AND lrrt.cod_zona = lsCodigoZona)) --- Yo
         AND lrrt.cam_recu = lsCampaRecaudo;
      -- Borrar registros Resumen Recuperaciï¿½n por consultora
      DELETE FROM lec_lider_secci_detal_recup lsdr
       WHERE lsdr.pais_cod_pais = psCodigoPais
         AND lsdr.lpro_cod_prog = lsCodigoPrograma -- Modificado
         AND lsdr.cod_regi = lsCodigoRegion
         AND (lsCodigoZona IS NULL OR
             (lsCodigoZona IS NOT NULL AND lsdr.cod_zona = lsCodigoZona)) --- Yo
         AND lsdr.cam_recu = lsCampaRecaudo;
      -- Borrar registros Resultados de Recuperaciï¿½n de la Secciï¿½n de la Lider
      DELETE FROM lec_lider_secci_resul_recup lsrr
       WHERE lsrr.pais_cod_pais = psCodigoPais
         AND lsrr.lpro_cod_prog = lsCodigoPrograma -- Modificado
         AND lsrr.cod_regi = lsCodigoRegion
         AND (lsCodigoZona IS NULL OR
             (lsCodigoZona IS NOT NULL AND lsrr.cod_zona = lsCodigoZona)) --- Yo
         AND lsrr.cam_recu = lsCampaRecaudo;
      -- =======================================================
      -- Se obtienen las consultoras de la lider para el proceso
      -- =======================================================
      OPEN c_regiRecup(vnOidPeriodo,
                       lsCodigoRegion,
                       lsCodigoZona,
                       lsCampaBase);
      LOOP
        FETCH c_regiRecup
          INTO vsOidmarca,
               vsOidcanal,
               vsCodRegi,
               vsCodZona,
               vsCodSecc,
               vsCodigoLider,
               vsCodigoCliente,
               vnMonAbonPdte,
               vnMonRecNoEx;
        EXIT WHEN c_regiRecup%NOTFOUND;
        -- Obtiene valores de la marca y canal
        SELECT m.cod_marc
          INTO pscodigomarca
          FROM seg_marca m
         WHERE m.oid_marc = vsOidmarca;
        SELECT c.cod_cana
          INTO psCodigoCanal
          FROM seg_canal c
         WHERE c.oid_cana = vsOIdCanal;
        vnMonAbonPdteClie := nvl(vnMonAbonPdte, 0) + nvl(vnMonRecNoEx, 0);
        -- Calcula la recuperaciï¿½n por consultora
        LEC_PR_CALCU_RECUP_CONSU(psCodigoPais,
                                 pscodigomarca,
                                 pscodigocanal,
                                 lsCodigoPrograma, -- Modificado
                                 lsCampaBono,
                                 lsCampaRecaudo,
                                 vsCodigoCliente,
                                 vsCodRegi,
                                 vsCodZona,
                                 vsCodSecc,
                                 vsCodigoLider,
                                 psCodigoUsuario,
                                 lsCampAnterior,
                                 vnMonAbonPdteClie,
                                 ldFecCierr,
                                 ldFecCierrZona);
      END LOOP;
      CLOSE c_regiRecup;

      -- Agrupa los valores de recuperaciï¿½n por secciï¿½n.
      LEC_PR_CALCU_RESUL_SECCI_COMIS(psCodigoPais,
                                     lsCodigoPrograma, -- Modificado
                                     lsCodigoRegion,
                                     lsCodigoZona,
                                     lsCampaRecaudo,
                                     psCodigoUsuario);
      -- Procesar cï¿½lculo de ganancias
      LEC_PR_CALCU_GANAN_COMIS(psCodigoPais,
                               lsCodigoPrograma, -- Modificado
                               lsCodigoRegion,
                               lsCodigoZona,
                               lsCampaBono,
                               lsCampaRecaudo,
                               psCodigoUsuario);
    END LOOP;
    CLOSE c_RegionZona;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'LEC_PR_CALCU_RESUL_RECUP_GRUPO: ' ||
                              ls_sqlerrm);
  END LEC_PR_CALCU_RESUL_RECUP_GRUPO;

/***************************************************************************
 Descripcion       : Proceso Evaluar Resultado Secciï¿½n por Comisiï¿½n.045
 Fecha Creacion    : 10/03/2014
 Autor             : Juan Altamirano
***************************************************************************/
PROCEDURE LEC_PR_CALCU_RESUL_SECCI_COMIS(psCodigoPais      VARCHAR2,
                                         psCodigoPrograma  VARCHAR2,
                                         psCodigoRegion    VARCHAR2,
                                         psCodigoZona      VARCHAR2,
                                         psCampanaCobranza VARCHAR2,
                                         psCodigoUsuario   VARCHAR2) IS

    vsCodRegi LEC_LIDER_SECCI_RESUL_RECUP.COD_REGI%type;
    vsCodZona LEC_LIDER_SECCI_RESUL_RECUP.COD_ZONA%type;
    vsCodSecc LEC_LIDER_SECCI_RESUL_RECUP.COD_SECC%type;
    vsCodLide LEC_LIDER_SECCI_RESUL_RECUP.COD_LIDE%type;

    vsCamRecu          LEC_LIDER_SECCI_RESUL_RECUP.CAM_RECU%type;
    vsValMontoCarg     LEC_LIDER_SECCI_RESUL_RECUP.VAL_MONT_CARG%TYPE;
    vsValMontoCargAcum LEC_LIDER_SECCI_RESUL_RECUP.VAL_MONT_CARG%TYPE := 0;

    vsValMontoCDRS LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Cdrs%TYPE;
    vsValMontoNCOM LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Ncom%TYPE;

    vsCodLideTramo    LEC_LIDER_SECCI_RECUP_TRAMO.COD_LIDE%TYPE;
    vsValMontRecu     LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Recu%type;
    vsValPorcRecuReal LEC_LIDER_SECCI_RECUP_TRAMO.Val_Porc_Recu_Real%type;
    vsCodTramo        LEC_LIDER_SECCI_RECUP_TRAMO.LPCT_COD_TRAM%TYPE;
    vsValMontRPCons   LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Reca_Pedi_Cons%type;
    vsValMontRPNCons  LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Reca_Pedi_Ncon%type;
    vsLeobCodEstaObje LEC_LIDER_SECCI_RECUP_TRAMO.Leob_Cod_Esta_Obje%type;
    vsIndMetaIngr     Lec_Progr.Ind_Dese_Meta_Ingr%TYPE;
    vnIndMetaCapi     Lec_Progr_nivel.Ind_Cond_Capi%TYPE;
    vEstIngre         LEC_LIDER_SECCI_RESUL.Leob_Cod_Esta_Obje_Ingr%type;
    vEstCapi          LEC_LIDER_SECCI_RESUL.Leob_Cod_Esta_Obje_Capi%type;
    vEstPedi          LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;

    vsValPorcCobr     LEC_PROGR_COBRA_OBJET_TRAMO.VAL_PORC_COBR%TYPE;
    vsValPorcMiniCobr LEC_PROGR_COBRA_OBJET_TRAMO.VAL_PORC_MINI_COBR%TYPE;
    vsNumPedi         LEC_LIDER_SECCI_RESUL.NUM_PEDI%TYPE;
    --  vsLeobCodEstaObje     LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;
    vsNumPediObjeFina LEC_LIDER_SECCI_OBJET_PEDID.NUM_PEDI_OBJE_FINA%TYPE;
    vsCodNivel        LEC_LIDER_NIVEL.Lniv_Cod_Nive%type;
    vsSecAmbiGeog     LEC_PROGR_AMBIT_GEOGR.SEC_AMBI_GEOG%type;
    vsTolPedi         LEC_PROGR_NIVEL.TOL_PEDI%type;
    nCObOk            NUMBER := 0;

    lnValor               NUMBER(12, 2) := 0;
    vnValMontRecuTota     LEC_LIDER_SECCI_RECUP_TRAMO.VAL_MONT_RECU_TOTA%TYPE;
    vnValMontCargTota     LEC_LIDER_SECCI_RESUL_RECUP.VAL_MONT_CARG_TOTA%TYPE;
    vnValMontCdrsTota     LEC_LIDER_SECCI_RESUL_RECUP.VAL_MONT_CDRS_TOTA%TYPE;
    vnValMontAbonPdte     LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Abon_Pdte%TYPE;
    vnValMontAbonPdteTota LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Abon_Pdte_TOta%TYPE;

    --cursor ResultadoRecuperacionLider/RecuperacionTramo
    CURSOR c_LS_ResulRecup IS
      SELECT RR.COD_REGI,
             RR.COD_ZONA,
             RR.COD_SECC,
             RR.COD_LIDE,
             RR.CAM_RECU,
             RR.VAL_MONT_CARG,
             RR.VAL_MONT_CDRS,
             RR.VAL_MONT_NCOM,
             RR.VAL_MONT_CARG_TOTA,
             RR.VAL_MONT_CDRS_TOTA,
             RR.Val_Mont_Abon_Pdte,
             RR.Val_Mont_Abon_Pdte_Tota
        FROM LEC_LIDER_SECCI_RESUL_RECUP RR
       WHERE RR.PAIS_COD_PAIS = psCodigoPais
         AND RR.LPRO_COD_PROG = psCodigoPrograma
         AND RR.CAM_RECU = psCampanaCobranza
         AND rr.cod_regi = psCodigoRegion
         AND (psCodigoZona IS NULL OR
             (psCodigoZona IS NOT NULL AND rr.cod_zona = psCodigoZona)); --- Yo

    CURSOR c_LS_RecupTramo(psPais     LEC_LIDER_SECCI_RECUP_TRAMO.Pais_Cod_Pais%type,
                           psPrograma LEC_LIDER_SECCI_RECUP_TRAMO.LPRO_COD_PROG%type,
                           psRegion   LEC_LIDER_SECCI_RECUP_TRAMO.COD_REGI%type,
                           psZona     LEC_LIDER_SECCI_RECUP_TRAMO.COD_ZONA%type,
                           psSeccion  LEC_LIDER_SECCI_RECUP_TRAMO.COD_SECC%type,
                           psLider    LEC_LIDER_SECCI_RECUP_TRAMO.COD_LIDE%type,
                           psPeriodo  LEC_LIDER_SECCI_RECUP_TRAMO.CAM_RECU%type) IS

      SELECT RT.LPCT_COD_TRAM,
             RT.COD_LIDE,
             RT.VAL_MONT_RECU,
             RT.VAL_PORC_RECU_REAL,
             RT.VAL_MONT_RECA_PEDI_CONS,
             RT.VAL_MONT_RECA_PEDI_NCON,
             RT.LEOB_COD_ESTA_OBJE,
             RT.LPAG_SEC_AMBI_GEOG,
             RT.VAL_MONT_RECU_TOTA
        FROM LEC_LIDER_SECCI_RECUP_TRAMO RT
       WHERE RT.PAIS_COD_PAIS = psPais
         AND RT.LPRO_COD_PROG = psPrograma
         AND RT.COD_REGI = psRegion
         AND RT.COD_ZONA = psZona
         AND RT.COD_SECC = psSeccion
         AND RT.COD_LIDE = psLider
         AND RT.CAM_RECU = psPeriodo
       ORDER BY RT.LPCT_COD_TRAM;

    lsIndicadorRetail VARCHAR2(100);
    lnSumaRetail      NUMBER;

    lsCodClienteRetail   MAE_CLIEN.COD_CLIE%TYPE;
    lnOidClienteRetail   MAE_CLIEN.OID_CLIE%TYPE;
    lnOidCampanaCobranza NUMBER;
  BEGIN
    BEGIN
      SELECT X.VAL_PARA
        INTO lsIndicadorRetail
        FROM bas_param_pais x
       WHERE x.cod_pais = psCodigoPais
         AND x.cod_sist = 'LET'
         AND X.NOM_PARA = 'IndVentaRetail';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsIndicadorRetail := '0';
    END;

    SELECT nvl(LP.IND_DESE_META_INGR,'0')
      INTO vsIndMetaIngr
      FROM LEC_PROGR LP
     WHERE LP.COD_PROG = psCodigoPrograma;

    lnOidCampanaCobranza := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCampanaCobranza);

    --Inicio Cursor
    OPEN c_LS_ResulRecup;
    LOOP
      FETCH c_LS_ResulRecup
        INTO vsCodRegi,
             vsCodZona,
             vsCodSecc,
             vsCodLide,
             vsCamRecu,
             vsValMontoCarg,
             vsValMontoCDRS,
             vsValMontoNCOM,
             vnValMontCargTota,
             vnValMontCdrsTota,
             vnValMontAbonPdte,
             vnValMontAbonPdteTota;
      EXIT WHEN c_LS_ResulRecup%NOTFOUND;

      lnSumaRetail := 0;
      IF lsIndicadorRetail = '1' THEN
        lnSumaRetail := LEC_FN_OBTIE_VENTA_RETAI(psCodigoPais,
                                                 psCodigoPrograma,
                                                 vsCodLide,
                                                 psCampanaCobranza,
                                                 lnOidCampanaCobranza,
                                                 vsCodRegi,
                                                 vsCodZona,
                                                 vsCodSecc,
                                                 psCodigoUsuario);

        UPDATE LEC_LIDER_SECCI_RESUL_RECUP rr
           SET rr.val_mont_rtal = nvl(lnSumaRetail, 0)
         WHERE rr.pais_cod_pais = psCodigoPais
           AND RR.LPRO_COD_PROG = psCodigoPrograma
           AND RR.COD_REGI = vsCodRegi
           AND RR.COD_ZONA = vsCodZona
           AND RR.COD_SECC = vsCodSecc
           AND RR.COD_LIDE = vsCodLide
           AND RR.CAM_RECU = psCampanaCobranza;

      END IF;

      --Sumatoria de vsValMontRecu
      vsValMontoCargAcum := 0;

      OPEN c_LS_RecupTramo(psCodigoPais,
                           psCodigoPrograma,
                           vsCodRegi,
                           vsCodZona,
                           vsCodSecc,
                           vsCodLide,
                           psCampanaCobranza);
      LOOP
        FETCH c_LS_RecupTramo
          INTO vsCodTramo,
               vsCodLideTramo,
               vsValMontRecu,
               vsValPorcRecuReal,
               vsValMontRPCons,
               vsValMontRPNCons,
               vsLeobCodEstaObje,
               vsSecAmbiGeog,
               vnValMontRecuTota;
        EXIT WHEN c_LS_RecupTramo%NOTFOUND;
        nCObOk := 0;
        IF vsCodTramo = 1 THEN
          vsValMontoCargAcum := vsValMontoCargAcum + vnValMontRecuTota +
                                NVL(lnSumaRetail, 0);
        ELSE
          vsValMontoCargAcum := vsValMontoCargAcum + vnValMontRecuTota;
        END IF;

        --vsValPorcRecuReal := ROUND(vsValMontoCargAcum/((vsValMontoCarg - vsValMontoCDRS) + NVL(lnSumaRetail,0)) * 100,2);

        lnValor := (vnValMontCargTota - (NVL(vnValMontCDRSTota, 0) +
                   NVL(vnValMontAbonPdteTota, 0))) + NVL(lnSumaRetail, 0);
        if (lnValor > 0) then
          vsValPorcRecuReal := ROUND(vsValMontoCargAcum / (lnValor) * 100,
                                     2);
        else
          vsValPorcRecuReal := 0;
        end if;

        IF (vsSecAmbiGeog IS NOT NULL) THEN

          SELECT PCOT.VAL_PORC_COBR, PCOT.VAL_PORC_MINI_COBR
            INTO vsValPorcCobr, vsValPorcMiniCobr
            FROM LEC_PROGR_COBRA_OBJET_TRAMO PCOT
           WHERE PCOT.PAIS_COD_PAIS = psCodigoPais
             AND PCOT.LPRO_COD_PROG = psCodigoPrograma
             AND PCOT.LPAG_SEC_AMBI_GEOG = vsSecAmbiGeog
             AND PCOT.LTUG_COD_TIPO_USO_GEOG = '01'
             AND PCOT.LPCT_COD_TRAM = vsCodTramo
             AND PCOT.IND_ACTI = 1;

          IF (vsValPorcRecuReal >= vsValPorcCobr) THEN
            nCObOk := 1;
          ELSIF (vsValPorcRecuReal >= vsValPorcMiniCobr) THEN
            nCObOk := 2;
          END IF;

          IF (nCObOk = 1 OR nCObOk = 2) THEN

            BEGIN
              SELECT LSR.LEOB_COD_ESTA_OBJE, LSR.Leob_Cod_Esta_Obje_Ingr,  LSR.Leob_Cod_Esta_Obje_Capi
                INTO vEstPedi, vEstIngre, vEstCapi
                FROM LEC_LIDER_SECCI_RESUL LSR
               WHERE LSR.PAIS_COD_PAIS = psCodigoPais
                 AND LSR.LPRO_COD_PROG = psCodigoPrograma
                 AND LSR.COD_REGI = vsCodRegi
                 AND LSR.COD_ZONA = vsCodZona
                 AND LSR.COD_SECC = vsCodSecc
                 AND LSR.COD_LIDE = vsCodLide
                 AND LSR.CAM_RESU = vsCamRecu;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                vEstPedi  := NULL;
                vEstIngre := NULL;
            END;
            
            BEGIN
              SELECT nvl(pn.ind_cond_capi,0)    ------PER-SICC-2015-0548  todo
                INTO vnIndMetaCapi   
                FROM lec_lider_nivel lln, lec_progr_nivel pn
               WHERE lln.PAIS_COD_PAIS = psCodigoPais
                 AND lln.LPRO_COD_PROG = psCodigoPrograma
                 AND pn.lpro_cod_prog(+) = lln.lpro_cod_prog
                 AND pn.lniv_cod_nive(+) = lln.lniv_cod_nive
                 AND COD_LIDE = vsCodLide
                 AND LLN.IND_TIPO_NIVE = 'R'
                 AND vsCamRecu >= CAM_INIC
                 AND (vsCamRecu <= CAM_FIN OR CAM_FIN IS NULL);
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  vnIndMetaCapi := 0;
             END;  
             
            IF (vEstPedi IS NOT NULL) THEN
              IF (vEstPedi = '01') THEN
                IF (nCObOk = 1) THEN
                  IF ( vnIndMetaCapi = 0  AND vsIndMetaIngr='0')  OR
                     ( vsIndMetaIngr = '1'  AND vEstIngre = '01') OR 
                     ( vnIndMetaCapi = 1    AND vEstCapi  = '01' ) THEN
                       vsLeobCodEstaObje := '01';
                  ELSE
                      vsLeobCodEstaObje := '02';
                  END IF;
                ELSE
                  vsLeobCodEstaObje := '02';
                END IF;
              ELSIF (vEstPedi = '02') THEN
                vsLeobCodEstaObje := '02';
              ELSIF (vEstPedi = '03') THEN
                vsLeobCodEstaObje := '03';
              END IF;

            END IF;

          ELSE
            vsLeobCodEstaObje := '03';
          END IF;
        END IF;

        ----ACTUALIZAR REGISTRO Y AUDITORIA
        UPDATE LEC_LIDER_SECCI_RECUP_TRAMO RT
           SET RT.LEOB_COD_ESTA_OBJE = vsLeobCodEstaObje,
               RT.VAL_PORC_RECU_REAL = vsValPorcRecuReal,
               RT.VAL_PORC_RECU_OBJE = vsValPorcCobr,
               RT.USU_MODI           = psCodigoUsuario,
               RT.FEC_MODI           = SYSDATE
         WHERE RT.PAIS_COD_PAIS = psCodigoPais
           AND RT.LPRO_COD_PROG = psCodigoPrograma
           AND RT.COD_REGI = vsCodRegi
           AND RT.COD_ZONA = vsCodZona
           AND RT.COD_SECC = vsCodSecc
           AND RT.COD_LIDE = vsCodLide
           AND RT.CAM_RECU = vsCamRecu
           AND RT.LPCT_COD_TRAM = vsCodTramo;

      END LOOP;
      CLOSE c_LS_RecupTramo;

    END LOOP;
    CLOSE c_LS_ResulRecup;
    --Fin Cursor

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_RESUL_SECCI_COMIS: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_RESUL_SECCI_COMIS;

  /***************************************************************************
      Descripcion       : Proceso Cï¿½lculo de Ganancia Recuperaciï¿½n.046
      Fecha Creacion    : 11/03/2014
      Autor             : Juan Altamirano
  ***************************************************************************/
  procedure LEC_PR_CALCU_GANAN_COMIS(pscodigopais      VARCHAR2,
                                     psCodigoPrograma  VARCHAR2,
                                     psCodigoRegion    VARCHAR2,
                                     psCodigoZona      VARCHAR2,
                                     psCampanaProceso  VARCHAR2,
                                     psCampanaCobranza VARCHAR2,
                                     psCodigoUsuario   VARCHAR2) IS

    vsCodNivel LEC_LIDER_NIVEL.LNIV_COD_NIVE%type;

    vsCodRegi     LEC_LIDER_SECCI_RESUL_RECUP.COD_REGI%TYPE;
    vsCodZona     LEC_LIDER_SECCI_RESUL_RECUP.COD_ZONA%TYPE;
    vsCodSecc     LEC_LIDER_SECCI_RESUL_RECUP.COD_SECC%TYPE;
    vsCodLide     LEC_LIDER_SECCI_RESUL_RECUP.COD_LIDE%TYPE;
    vsCamRecu     LEC_LIDER_SECCI_RESUL_RECUP.CAM_RECU%TYPE;
    vsValMontCarg LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Carg%TYPE;
    vsValMontCDRs LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Cdrs%TYPE;
    vsValMontNCOm LEC_LIDER_SECCI_RESUL_RECUP.Val_Mont_Ncom%TYPE;

    vsValMontRecu   LEC_LIDER_SECCI_RECUP_TRAMO.Val_Mont_Recu%type;
    vsValPorcRecuRe LEC_LIDER_SECCI_RECUP_TRAMO.Val_Porc_Recu_Real%type;
    vsCodTram       LEC_LIDER_SECCI_RECUP_TRAMO.LPCT_COD_TRAM%type;
    vsLeobCodEObje  LEC_LIDER_SECCI_RECUP_TRAMO.Leob_Cod_Esta_Obje%type;

    vsValMontRecaPediCons LEC_LIDER_SECCI_RECUP_TRAMO.VAL_MONT_RECA_PEDI_CONS%type;
    vsValMontRecaPediNcon LEC_LIDER_SECCI_RECUP_TRAMO.VAL_MONT_RECA_PEDI_NCON%type;

    vsCodTipoGana    LEC_LIDER_GANAN.LTGA_COD_TIPO_GANA%TYPE;
    vsNumCampGrac    LEC_PROGR.NUM_CAMP_GRAC%TYPE;
    vsCodigoClase    LEC_LIDER_CLASI.LCCL_COD_CLAS%type;
    vsCodigoSubClase LEC_LIDER_CLASI.LSCL_COD_SUBC%type;
    vsNumCamp        LEC_SUBCL.Num_Camp%type;

    --cursor ResultadoRecuperacionLider/RecuperacionTramo
    CURSOR c_rr_dr_rtLider IS
      SELECT RR.COD_REGI,
             RR.COD_ZONA,
             RR.COD_SECC,
             RR.COD_LIDE,
             RR.CAM_RECU,
             RR.VAL_MONT_CARG,
             RR.VAL_MONT_CDRS,
             RR.VAL_MONT_NCOM,
             RT.VAL_MONT_RECU,
             RT.VAL_PORC_RECU_REAL,
             RT.LPCT_COD_TRAM,
             RT.LEOB_COD_ESTA_OBJE,
             RT.VAL_MONT_RECA_PEDI_CONS,
             RT.VAL_MONT_RECA_PEDI_NCON,
             RR.VAL_MONT_RTAL
        FROM LEC_LIDER_SECCI_RESUL_RECUP RR, LEC_LIDER_SECCI_RECUP_TRAMO RT
       WHERE RR.PAIS_COD_PAIS = psCodigoPais
         AND RR.COD_REGI = RT.COD_REGI
         AND RR.COD_ZONA = RT.COD_ZONA
         AND RR.COD_SECC = RT.COD_SECC
         AND RR.COD_LIDE = RT.COD_LIDE
         AND RR.CAM_RECU = RT.CAM_RECU
         AND RR.LPRO_COD_PROG = psCodigoPrograma
         AND RR.CAM_RECU = psCampanaCobranza
         AND rr.cod_regi = psCodigoRegion
         AND (psCodigoZona IS NULL OR
             (psCodigoZona IS NOT NULL AND rr.cod_zona = psCodigoZona))
         AND RT.LEOB_COD_ESTA_OBJE IN ('01', '02');--- , '03');

    vsValPorcComiPediCons LEC_PROGR_NIVEL_TRAMO.VAL_PORC_COMI_PEDI_CONS%type;
    vsValPorcComiPediNCON LEC_PROGR_NIVEL_TRAMO.VAL_PORC_COMI_PEDI_NCON%type;
    vsValPorcComiTole     LEC_PROGR_NIVEL_TRAMO.VAL_PORC_COMI_TOLE%type;
    vsValRetail           LEC_LIDER_SECCI_RESUL_RECUP.VAL_MONT_RTAL%type;

    nPedConsecutiv   NUMBER := 0;
    nPedNoConsecutiv NUMBER := 0;
    nToleranPedidos  NUMBER := 0;
    vnMonPrem        LEC_PROGR_BONO_NIVEL.MON_PREM%TYPE := 0;

    vnMontGana        NUMBER := 0;
    vnMontGanaTotal   NUMBER := 0;
    vsPerioFactu      bas_ctrl_fact.cod_peri%type;
    vsIndPediCumpExig LEC_LIDER_SECCI_RESUL.IND_PEDI_CUMP_EXIG%type;
    vnNumDeci         NUMBER;
    nPedRetail        NUMBER;
  BEGIN

    DELETE FROM LEC_LIDER_GANAN LG
     WHERE lg.pais_cod_pais = pscodigopais
       and lg.lpro_cod_prog = pscodigoprograma
       and lg.cam_gana = psCampanaCobranza
       and lg.ltga_cod_tipo_gana in ('13', '14', '15', '16', '17', '18')
       and lg.cod_regi = psCodigoRegion
       and (psCodigoZona IS NULL OR
           (psCodigoZona IS NOT NULL AND lg.cod_zona = psCodigoZona)); 

    SELECT num_deci
      INTO vnNumDeci
      FROM seg_pais p, seg_moned m
     WHERE p.mone_oid_mone = m.oid_mone
       AND p.cod_pais = psCodigoPais;

    vsPerioFactu := psCampanaProceso;

    SELECT PR.NUM_CAMP_GRAC
      INTO vsNumCampGrac
      FROM LEC_PROGR PR
     WHERE PR.COD_PROG = psCodigoPrograma;

    --INICIO CURSOR c_liderSecciRR
    OPEN c_rr_dr_rtLider;
    LOOP
      FETCH c_rr_dr_rtLider
        INTO vsCodRegi,
             vsCodZona,
             vsCodSecc,
             vsCodLide,
             vsCamRecu,
             vsValMontCarg,
             vsValMontCDRs,
             vsValMontNCOm,
             vsValMontRecu,
             vsValPorcRecuRe,
             vsCodTram,
             vsLeobCodEObje,
             vsValMontRecaPediCons,
             vsValMontRecaPediNcon,
             vsValRetail;
      EXIT WHEN c_rr_dr_rtLider%NOTFOUND;
      nPedConsecutiv := 0;
      nPedNoConsecutiv := 0;
      nPedRetail := 0;
      vnMonPrem := 0;

      --ACCESAR NIVEL LIDER
      BEGIN
        SELECT LN.LNIV_COD_NIVE
          INTO vsCodNivel
          FROM LEC_LIDER_NIVEL LN
         WHERE LN.PAIS_COD_PAIS = psCodigoPais
           AND LN.LPRO_COD_PROG = psCodigoPrograma
           AND LN.COD_LIDE = vsCodLide
           AND psCampanaCobranza >= LN.CAM_INIC
           AND (psCampanaCobranza <= LN.CAM_FIN OR LN.CAM_FIN IS NULL)
           AND LN.IND_TIPO_NIVE = 'R';
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodNivel := '00';
          --RAISE_APPLICATION_ERROR(-20123, 'NO SE ENCONTRO CODIGO DE NIVEL LIDER');
        --EXIT;
      END;
      BEGIN
          SELECT C.COD_CLAS, SC.NUM_CAMP, lscl_cod_subc
                   INTO vsCodigoClase, vsNumCamp, vsCodigoSubClase
                    FROM LEC_LIDER_CLASI LC, LEC_CLASI C, LEC_SUBCL SC
                    WHERE LC.LCCL_COD_CLAS = SC.LCCL_COD_CLAS
                    AND LC.LSCL_COD_SUBC = SC.COD_SUBC
                    AND SC.LCCL_COD_CLAS = C.COD_CLAS
                    AND LC.CAM_INIC <= psCampanaCobranza
                    AND (LC.CAM_FIN >= psCampanaCobranza OR
                         LC.CAM_FIN IS NULL)
                    AND LC.COD_LIDE = vsCodLide;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vsCodigoClase    := NULL;
              vsCodigoSubClase := NULL;
      END;
      --Validacion de "vsIndPediCumpExig" como condicion adicional para realizar las inserciones.
      BEGIN
        SELECT NVL(LSR.IND_PEDI_CUMP_EXIG, '0')
          INTO vsIndPediCumpExig
          FROM LEC_LIDER_SECCI_RESUL LSR
         WHERE LSR.PAIS_COD_PAIS = psCodigoPais
           AND LSR.LPRO_COD_PROG = psCodigoPrograma
           AND LSR.COD_REGI = vsCodRegi
           AND LSR.COD_ZONA = vsCodZona
           AND LSR.COD_SECC = vsCodSecc
           AND LSR.COD_LIDE = vsCodLide
           AND LSR.CAM_RESU = vsCamRecu;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsIndPediCumpExig := '0';
      END;

      --SOLO SI CODNIVEL ES <> '00',  se realizan las inserciones.
      IF (vsCodNivel <> '00') AND vsIndPediCumpExig = 1  THEN
        --ACCESAR PROG NIVEL TRAMO
        BEGIN
          SELECT PNT.VAL_PORC_COMI_PEDI_CONS,
                 PNT.VAL_PORC_COMI_PEDI_NCON,
                 PNT.VAL_PORC_COMI_TOLE
            INTO vsValPorcComiPediCons,
                 vsValPorcComiPediNCON,
                 vsValPorcComiTole
            FROM LEC_PROGR_NIVEL_TRAMO PNT
           WHERE PNT.PAIS_COD_PAIS = psCodigoPais
             AND PNT.LPRO_COD_PROG = psCodigoPrograma
             AND PNT.LNIV_COD_NIVE = vsCodNivel
             AND PNT.COD_TRAM = vsCodTram;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsValPorcComiPediCons := 0;
            vsValPorcComiPediNCON := 0;
            vsValPorcComiTole     := 0;
        END;
        --3.3
        IF (vsLeobCodEObje = '01') THEN
           nPedConsecutiv   := ROUND(vsValMontRecaPediCons *
                                    (vsValPorcComiPediCons / 100),
                                    vnNumDeci);
           nPedNoConsecutiv := ROUND(vsValMontRecaPediNcon *
                                    (vsValPorcComiPediNCON / 100),
                                    vnNumDeci);
           nPedRetail       := ROUND(vsValRetail * (vsValPorcComiTole / 100),
                                    vnNumDeci);
           IF    (vsCodigoClase = '01' AND vsNumCamp <= vsNumCampGrac) THEN                     
               BEGIN
                  SELECT MON_PREM
                    INTO vnMonPrem
                    FROM LEC_PROGR_BONO_NIVEL
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodigoPrograma
                     AND LTBO_COD_TIPO_BONO = '09'
                     AND LNIV_COD_NIVE = vsCodNivel;
                   EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                        vnMonPrem := 0;
               END;
           END IF;
           IF  nPedConsecutiv + nPedNoConsecutiv >= vnMonPrem THEN  
               update LEC_LIDER_SECCI_DRECU_TRAMO tr
                 SET tr.val_porc_comi = vsValPorcComiPediCons
               WHERE TR.CAM_RECU = vsCamRecu
                 AND TR.COD_LIDE = vsCodLide
                 AND TR.LPCT_COD_TRAM = vsCodTram
                 AND TR.MVCC_OID_MOVI_CC in
                     (SELECT LSDR.MVCC_OID_MOVI_CC
                        FROM LEC_LIDER_SECCI_DETAL_RECUP LSDR
                       WHERE LSDR.LPRO_COD_PROG = psCodigoPrograma
                         AND LSDR.CAM_RECU = vsCamRecu
                         AND LSDR.COD_LIDE = vsCodLide
                         AND LSDR.IND_PEDI_CONS = 1);

              update LEC_LIDER_SECCI_DRECU_TRAMO tr
                 SET tr.val_porc_comi = vsValPorcComiPediNCON
               WHERE TR.CAM_RECU = vsCamRecu
                 AND TR.COD_LIDE = vsCodLide
                 AND TR.LPCT_COD_TRAM = vsCodTram
                 AND TR.MVCC_OID_MOVI_CC in
                     (SELECT LSDR.MVCC_OID_MOVI_CC
                        FROM LEC_LIDER_SECCI_DETAL_RECUP LSDR
                       WHERE LSDR.LPRO_COD_PROG = psCodigoPrograma
                         AND LSDR.CAM_RECU = vsCamRecu
                         AND LSDR.COD_LIDE = vsCodLide
                         AND LSDR.IND_PEDI_CONS = 0);

                IF (nPedConsecutiv > 0) THEN
                  vsCodTipoGana   := '14';
                  vnMontGana      := nPedConsecutiv;
                  vnMontGanaTotal := vnMontGanaTotal + vnMontGana;
                  INSERT INTO LEC_LIDER_GANAN
                    (SEC_LIDE_GANA,
                     PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     cod_regi,
                     cod_zona,
                     cod_secc,
                     cod_lide,
                     cam_gana,
                     IND_ESTA_PAGO_GANA,
                     LCPT_COD_TRAM,
                     LTGA_COD_TIPO_GANA,
                     MON_GANA,
                     CAM_REFE,
                     VAL_PORC_COMI,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (LEC_LLGA_SEQ.nextval,
                     psCodigoPais,
                     psCodigoPrograma,
                     vscodregi,
                     vscodzona,
                     vscodsecc,
                     vscodlide,
                     psCampanaCobranza,
                     'N',
                     vsCodTram,
                     vsCodTipoGana,
                     vnMontGana,
                     vsPerioFactu,
                     vsValPorcComiPediCons,
                     psCodigoUsuario,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                END IF;

                IF (nPedNoConsecutiv > 0) THEN
                  vsCodTipoGana   := '15';
                  vnMontGana      := nPedNoConsecutiv;
                  vnMontGanaTotal := vnMontGanaTotal + vnMontGana;
                  INSERT INTO LEC_LIDER_GANAN
                    (SEC_LIDE_GANA,
                     PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     IND_ESTA_PAGO_GANA,
                     LCPT_COD_TRAM,
                     LTGA_COD_TIPO_GANA,
                     MON_GANA,
                     CAM_REFE,
                     VAL_PORC_COMI,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (LEC_LLGA_SEQ.nextval,
                     psCodigoPais,
                     psCodigoPrograma,
                     vscodregi,
                     vscodzona,
                     vscodsecc,
                     vscodlide,
                     psCampanaCobranza,
                     'N',
                     vsCodTram,
                     vsCodTipoGana,
                     vnMontGana,
                     vsPerioFactu,
                     vsValPorcComiPediNCON,
                     psCodigoUsuario,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                END IF;
            ELSE
               -------   Inserto ganancia por bono Nuevas
               vsCodTipoGana   := '13';
               vnMontGana      := vnMonPrem;
               vnMontGanaTotal := vnMontGanaTotal + vnMonPrem;
               INSERT INTO LEC_LIDER_GANAN
                 (SEC_LIDE_GANA,
                  PAIS_COD_PAIS,
                  LPRO_COD_PROG,
                  cod_regi,
                  cod_zona,
                  cod_secc,
                  cod_lide,
                  cam_gana,
                  IND_ESTA_PAGO_GANA,
                  LCPT_COD_TRAM,
                  LTGA_COD_TIPO_GANA,
                  MON_GANA,
                  CAM_REFE,
                  VAL_PORC_COMI,
                  USU_CREA,
                  FEC_CREA,
                  USU_MODI,
                  FEC_MODI,
                  IND_ACTI)
               VALUES
                 (LEC_LLGA_SEQ.nextval,
                  psCodigoPais,
                  psCodigoPrograma,
                  vscodregi,
                  vscodzona,
                  vscodsecc,
                  vscodlide,
                  psCampanaCobranza,
                  'N',
                  vsCodTram,
                  vsCodTipoGana,
                  vnMontGana,
                  vsPerioFactu,
                  0,
                  psCodigoUsuario,
                  SYSDATE,
                  NULL,
                  NULL,
                  '1');    
            END IF;
            IF (nPedRetail > 0) THEN
              vsCodTipoGana   := '18';
              vnMontGana      := nPedRetail;
              vnMontGanaTotal := vnMontGanaTotal + vnMontGana;
              INSERT INTO LEC_LIDER_GANAN
                (SEC_LIDE_GANA,
                 PAIS_COD_PAIS,
                 LPRO_COD_PROG,
                 COD_REGI,
                 COD_ZONA,
                 COD_SECC,
                 COD_LIDE,
                 CAM_GANA,
                 IND_ESTA_PAGO_GANA,
                 LCPT_COD_TRAM,
                 LTGA_COD_TIPO_GANA,
                 MON_GANA,
                 CAM_REFE,
                 VAL_PORC_COMI,
                 USU_CREA,
                 FEC_CREA,
                 USU_MODI,
                 FEC_MODI,
                 IND_ACTI)
              VALUES
                (LEC_LLGA_SEQ.nextval,
                 psCodigoPais,
                 psCodigoPrograma,
                 vscodregi,
                 vscodzona,
                 vscodsecc,
                 vscodlide,
                 psCampanaCobranza,
                 'N',
                 vsCodTram,
                 vsCodTipoGana,
                 vnMontGana,
                 vsPerioFactu,
                 vsValPorcComiTole,
                 psCodigoUsuario,
                 SYSDATE,
                 NULL,
                 NULL,
                 '1');
            END IF;
      

        ELSIF (vsLeobCodEObje = '02') THEN
          nToleranPedidos := ROUND(vsValMontRecu *
                                   (vsValPorcComiTole / 100),
                                   vnNumDeci);
          nPedRetail      := ROUND(vsValRetail * (vsValPorcComiTole / 100),
                                   vnNumDeci);
          IF    (vsCodigoClase = '01' AND vsNumCamp <= vsNumCampGrac) THEN
               BEGIN
                  SELECT MON_PREM
                    INTO vnMonPrem
                    FROM LEC_PROGR_BONO_NIVEL
                   WHERE PAIS_COD_PAIS = psCodigoPais
                     AND LPRO_COD_PROG = psCodigoPrograma
                     AND LTBO_COD_TIPO_BONO = '09'
                     AND LNIV_COD_NIVE = vsCodNivel;
                   EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                        vnMonPrem := 0;
               END;
          END IF;                                   
          IF  nToleranPedidos >= vnMonPrem THEN
              update LEC_LIDER_SECCI_DRECU_TRAMO tr
                 SET tr.val_porc_comi = vsValPorcComiTole
               WHERE TR.CAM_RECU = vsCamRecu
                 AND TR.COD_LIDE = vsCodLide
                 and tr.lpct_cod_tram = vscodtram
                 AND TR.MVCC_OID_MOVI_CC in
                     (SELECT LSDR.MVCC_OID_MOVI_CC
                        FROM LEC_LIDER_SECCI_DETAL_RECUP LSDR
                       WHERE LSDR.LPRO_COD_PROG = psCodigoPrograma
                         AND LSDR.CAM_RECU = vsCamRecu
                         and lsdr.cod_lide = vscodlide
                      --AND LSDR.IND_PEDI_CONS = 1 -- CMM 5.6.14
                      );
          
                IF (nToleranPedidos > 0) THEN
                  vnMontGana      := nToleranPedidos;
                  vnMontGanaTotal := vnMontGanaTotal + vnMontGana;
                  vsCodTipoGana   := '17';
                  INSERT INTO LEC_LIDER_GANAN
                    (SEC_LIDE_GANA,
                     PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     IND_ESTA_PAGO_GANA,
                     LCPT_COD_TRAM,
                     LTGA_COD_TIPO_GANA,
                     MON_GANA,
                     CAM_REFE,
                     VAL_PORC_COMI,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (LEC_LLGA_SEQ.nextval,
                     psCodigoPais,
                     psCodigoPrograma,
                     vscodregi,
                     vscodzona,
                     vscodsecc,
                     vscodlide,
                     psCampanaCobranza,
                     'N',
                     vsCodTram,
                     vsCodTipoGana,
                     vnMontGana,
                     vsPerioFactu,
                     vsValPorcComiTole,
                     psCodigoUsuario,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1');
                END IF;
               ELSE
                  vnMontGana      := vnMonPrem;
                  vnMontGanaTotal := vnMontGanaTotal + vnMonPrem;
                  vsCodTipoGana   := '13';
                  INSERT INTO LEC_LIDER_GANAN
                    (SEC_LIDE_GANA,
                     PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     IND_ESTA_PAGO_GANA,
                     LCPT_COD_TRAM,
                     LTGA_COD_TIPO_GANA,
                     MON_GANA,
                     CAM_REFE,
                     VAL_PORC_COMI,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (LEC_LLGA_SEQ.nextval,
                     psCodigoPais,
                     psCodigoPrograma,
                     vscodregi,
                     vscodzona,
                     vscodsecc,
                     vscodlide,
                     psCampanaCobranza,
                     'N',
                     vsCodTram,
                     vsCodTipoGana,
                     vnMontGana,
                     vsPerioFactu,
                     vsValPorcComiTole,
                     psCodigoUsuario,
                     SYSDATE,
                     NULL,
                     NULL,
                     '1'); 
            END IF;
            IF (nPedRetail > 0) THEN
              vsCodTipoGana   := '18';
              vnMontGana      := nPedRetail;
              vnMontGanaTotal := vnMontGanaTotal + vnMontGana;
              INSERT INTO LEC_LIDER_GANAN
                (SEC_LIDE_GANA,
                 PAIS_COD_PAIS,
                 LPRO_COD_PROG,
                 COD_REGI,
                 COD_ZONA,
                 COD_SECC,
                 COD_LIDE,
                 CAM_GANA,
                 IND_ESTA_PAGO_GANA,
                 LCPT_COD_TRAM,
                 LTGA_COD_TIPO_GANA,
                 MON_GANA,
                 CAM_REFE,
                 VAL_PORC_COMI,
                 USU_CREA,
                 FEC_CREA,
                 USU_MODI,
                 FEC_MODI,
                 IND_ACTI)
              VALUES
                (LEC_LLGA_SEQ.nextval,
                 psCodigoPais,
                 psCodigoPrograma,
                 vscodregi,
                 vscodzona,
                 vscodsecc,
                 vscodlide,
                 psCampanaCobranza,
                 'N',
                 vsCodTram,
                 vsCodTipoGana,
                 vnMontGana,
                 vsPerioFactu,
                 vsValPorcComiTole,
                 psCodigoUsuario,
                 SYSDATE,
                 NULL,
                 NULL,
                 '1');
            END IF;
        END IF;

        --3.4
        --Una vez cargadas todas las ganancias de la lï¿½der, crear un nuevo registro en GananciaLider.
        IF (vnMontGanaTotal > 0) THEN
          vsCodTipoGana := '16';
          INSERT INTO LEC_LIDER_GANAN
            (SEC_LIDE_GANA,
             PAIS_COD_PAIS,
             LPRO_COD_PROG,
             cod_regi,
             cod_zona,
             cod_secc,
             cod_lide,
             cam_gana,
             IND_ESTA_PAGO_GANA,
             LCPT_COD_TRAM,
             LTGA_COD_TIPO_GANA,
             MON_GANA,
             CAM_REFE,
             USU_CREA,
             FEC_CREA,
             USU_MODI,
             FEC_MODI,
             IND_ACTI)
          VALUES
            (LEC_LLGA_SEQ.nextval,
             psCodigoPais,
             psCodigoPrograma,
             vscodregi,
             vscodzona,
             vscodsecc,
             vscodlide,
             psCampanaCobranza,
             'N',
             vsCodTram,
             vsCodTipoGana,
             vnMontGanaTotal,
             vsPerioFactu,
             psCodigoUsuario,
             SYSDATE,
             NULL,
             NULL,
             '1');
        END IF;

      END IF;

      nPedConsecutiv   := 0;
      nPedNoConsecutiv := 0;
      nToleranPedidos  := 0;
      vnMontGana       := 0;
      vnMontGanaTotal  := 0;

    END LOOP;
    CLOSE c_rr_dr_rtLider;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_GANAN_COMIS: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_GANAN_COMIS;

  /*********************************************************
     Descripcion :
        Obtener Nro de Pedidos Consecutivos/No consecutivos Reales de la Campaï¿½a
     Parametros :
          -   psCodigoPeriodo : Codigo Campaï¿½a
          -   psCodigoRegion : Codigo Region
          -   psCodigoZona : Codigo Zona
          -   psCodigoSeccion : Codigo Seccion
          -   psTipoProceso : C = consecutivo;N = No consecuntivo

     Resultados: Cantidad de registros.
      Fecha Creacion : 05/03/2014
      Autor : Yahir Rivas L.
  *********************************************************/
  FUNCTION LEC_FN_OBTE_PEDI_CONS_REAL(pscodigoPais     VARCHAR2,
                                      psCodigoPeriodo  VARCHAR2,
                                      psCodigoPrograma VARCHAR2,
                                      psCodigoRegion   VARCHAR2,
                                      psCodigoZona     VARCHAR2,
                                      psCodigoSeccion  VARCHAR2,
                                      psTipoProceso    VARCHAR2,
                                      psIndReco        VARCHAR2)
    RETURN NUMBER IS
    vnNroReg NUMBER(12);
  BEGIN
    IF psTipoProceso = 'C' THEN

      SELECT COUNT(DISTINCT mc.oid_clie)
        INTO VNNROREG
        FROM PED_SOLIC_CABEC SC,
             CRA_PERIO P,
             SEG_PERIO_CORPO PC,
             (SELECT X.VAL_I18N, TSP.OID_TIPO_SOLI_PAIS, TS.*
                FROM PED_TIPO_SOLIC_PAIS TSP,
                     PED_TIPO_SOLIC      TS,
                     GEN_I18N_SICC_COMUN X
               WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                 AND TS.COD_TIPO_SOLI = 'SOC'
                 AND X.ATTR_ENTI = 'PED_TIPO_SOLIC'
                 AND X.VAL_OID = TS.OID_TIPO_SOLI) TIPO_SOLI,
             MAE_CLIEN MC,
             MAE_CLIEN_UNIDA_ADMIN CUAD,
             ZON_TERRI_ADMIN ZTAD,
             ZON_SECCI ZSCC,
             ZON_ZONA ZZON,
             ZON_REGIO ZORG
       WHERE MC.OID_CLIE = SC.CLIE_OID_CLIE
         AND SC.TSPA_OID_TIPO_SOLI_PAIS = TIPO_SOLI.OID_TIPO_SOLI_PAIS
         AND TIPO_SOLI.COD_TIPO_SOLI = 'SOC'
         AND SC.FEC_FACT IS NOT NULL
         AND SC.PERD_OID_PERI = P.OID_PERI
         AND P.PERI_OID_PERI = PC.OID_PERI
         AND PC.COD_PERI = psCodigoPeriodo
         AND MC.OID_CLIE = CUAD.CLIE_OID_CLIE
         AND CUAD.PERD_OID_PERI_INI <= P.OID_PERI
         AND (CUAD.PERD_OID_PERI_FIN >= P.OID_PERI OR
             CUAD.PERD_OID_PERI_FIN IS NULL)
         AND CUAD.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
         AND ZTAD.ZSCC_OID_SECC = ZSCC.OID_SECC
         AND ZSCC.ZZON_OID_ZONA = ZZON.OID_ZONA
         AND ZZON.ZORG_OID_REGI = ZORG.OID_REGI
         AND ZORG.COD_REGI = PSCODIGOREGION
         AND ZZON.COD_ZONA = PSCODIGOZONA
         AND ZSCC.COD_SECC = PSCODIGOSECCION
         AND EXISTS (SELECT NULL
                FROM MAE_CLIEN_HISTO_ESTAT HE
               WHERE HE.CLIE_OID_CLIE = MC.OID_CLIE
                 AND HE.PERD_OID_PERI <= P.OID_PERI
                 AND (HE.PERD_OID_PERI_PERI_FIN >= P.OID_PERI OR
                     HE.PERD_OID_PERI_PERI_FIN IS NULL)
                 AND HE.ESTA_OID_ESTA_CLIE IN (1, 2, 3, 7, 8) --estados de pedido consecutivo
              )
         AND (psIndReco = 0 OR
             (psIndReco = 1 AND
             1 = LEC_FN_OBTE_CONS_EXCL(psCodigoPais,
                                         psCodigoPeriodo,
                                         psCodigoPrograma,
                                         mc.cod_clie)));

    ELSIF psTipoProceso = 'N' THEN

      SELECT COUNT(DISTINCT MC.OID_CLIE)
        INTO VNNROREG
        FROM PED_SOLIC_CABEC SC,
             CRA_PERIO P,
             SEG_PERIO_CORPO PC,
             (SELECT X.VAL_I18N, TSP.OID_TIPO_SOLI_PAIS, TS.*
                FROM PED_TIPO_SOLIC_PAIS TSP,
                     PED_TIPO_SOLIC      TS,
                     GEN_I18N_SICC_COMUN X
               WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                 AND TS.COD_TIPO_SOLI = 'SOC'
                 AND X.ATTR_ENTI = 'PED_TIPO_SOLIC'
                 AND X.VAL_OID = TS.OID_TIPO_SOLI) TIPO_SOLI,
             MAE_CLIEN MC,
             MAE_CLIEN_UNIDA_ADMIN CUAD,
             ZON_TERRI_ADMIN ZTAD,
             ZON_SECCI ZSCC,
             ZON_ZONA ZZON,
             ZON_REGIO ZORG
       WHERE MC.OID_CLIE = SC.CLIE_OID_CLIE
         AND SC.TSPA_OID_TIPO_SOLI_PAIS = TIPO_SOLI.OID_TIPO_SOLI_PAIS
         AND TIPO_SOLI.COD_TIPO_SOLI = 'SOC'
         AND SC.FEC_FACT IS NOT NULL
         AND SC.PERD_OID_PERI = P.OID_PERI
         AND P.PERI_OID_PERI = PC.OID_PERI
         AND PC.COD_PERI = psCodigoPeriodo
         AND MC.OID_CLIE = CUAD.CLIE_OID_CLIE
         AND CUAD.PERD_OID_PERI_INI <= P.OID_PERI
         AND (CUAD.PERD_OID_PERI_FIN >= P.OID_PERI OR
             CUAD.PERD_OID_PERI_FIN IS NULL)
         AND CUAD.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
         AND ZTAD.ZSCC_OID_SECC = ZSCC.OID_SECC
         AND ZSCC.ZZON_OID_ZONA = ZZON.OID_ZONA
         AND ZZON.ZORG_OID_REGI = ZORG.OID_REGI
         AND ZORG.COD_REGI = PSCODIGOREGION
         AND ZZON.COD_ZONA = PSCODIGOZONA
         AND ZSCC.COD_SECC = PSCODIGOSECCION
         AND EXISTS (SELECT NULL
                FROM MAE_CLIEN_HISTO_ESTAT HE
               WHERE HE.CLIE_OID_CLIE = MC.OID_CLIE
                 AND HE.PERD_OID_PERI <= P.OID_PERI
                 AND (HE.PERD_OID_PERI_PERI_FIN >= P.OID_PERI OR
                     HE.PERD_OID_PERI_PERI_FIN IS NULL)
                 AND HE.ESTA_OID_ESTA_CLIE IN (4, 5, 6) --estados de pedido NO consecutivo
              )
         AND (psIndReco = 0 OR
             (psIndReco = 1 AND
             1 = LEC_FN_OBTE_CONS_EXCL(psCodigoPais,
                                         psCodigoPeriodo,
                                         psCodigoPrograma,
                                         mc.cod_clie)));
    END IF;

    RETURN vnNroReg;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_FN_OBTE_PEDI_CONS_REAL: ' ||
                              ls_sqlerrm);
      RETURN - 1;
  END LEC_FN_OBTE_PEDI_CONS_REAL;

  /***************************************************************************
      Descripcion       : Proceso Calcular Ganancia Lï¿½der por objetivo de Pedidos
      Fecha Creacion    : 05/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_GANAN_PEDID_LIDER(psCodigoPais     VARCHAR2,
                                           pscodigomarca    VARCHAR2,
                                           pscodigocanal    VARCHAR2,
                                           psCodigoPeriodo  VARCHAR2,
                                           psCodigoPrograma VARCHAR2,
                                           psCodigoRegion   VARCHAR2,
                                           psCodigoZona     VARCHAR2,
                                           psCodigoSeccion  VARCHAR2,
                                           psCodigoLider    VARCHAR2,
                                           psCodigoUsuario  VARCHAR2,
                                           psTipoProceso    VARCHAR2) IS

    vsIndGanaPedi     LEC_PROGR.IND_GANA_PEDI%TYPE;
    vsIndCana         LEC_PROGR.IND_CANA%TYPE;
    vsIndExigPediLide lec_progr.ind_exig_pedi_lide%TYPE;
    vsIndOrigPediLide lec_progr.ind_tipo_orig_pedi%TYPE;
    vsIndMetaIngr     lec_progr.ind_dese_meta_ingr%TYPE;
    vsCodTipoComi     LEC_PROGR.LTCO_COD_TIPO_COMI%TYPE;
    vnNumPediReal     LEC_LIDER_SECCI_RESUL.NUM_PEDI%TYPE;
    vsCodEstaObj      LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;
    VSCODESTAOBJIngr  LEC_LIDER_SECCI_RESUL.Leob_Cod_Esta_Obje_Ingr%TYPE;
    VSCODESTAOBJCapi  LEC_LIDER_SECCI_RESUL.Leob_Cod_Esta_Obje_Capi%TYPE;
    vsIndPediPers     lec_lider_secci_resul.ind_pedi_pers_lide%TYPE;
    vsIndPediExig     lec_lider_secci_resul.ind_pedi_web%TYPE;
    vsIndPediCumpExig lec_lider_secci_resul.ind_pedi_cump_exig%TYPE;
    vnPedObjFin       Lec_Lider_Secci_Objet_Pedid.NUM_PEDI_OBJE_FINA%TYPE;
    vsCodNivel        LEC_LIDER_NIVEL.LNIV_COD_NIVE%TYPE;
    vnMonPediCons     LEC_PROGR_NIVEL.MON_PEDI_CONS%TYPE;
    vnMonPediNcon     LEC_PROGR_NIVEL.MON_PEDI_NCON%TYPE;
    vnTolPedi         LEC_PROGR_NIVEL.MON_TOLE%TYPE;
    vnIndMetaCapi     LEC_PROGR_NIVEL.Ind_Cond_Capi%TYPE;
    vnNroPedConReal   NUMBER(12) := 0;
    vnNroPedNoConReal NUMBER(12) := 0;
    vnMonPagPedCons   NUMBER(12, 2) := 0;
    vnMonPagPedNoCons NUMBER(12, 2) := 0;
    vnMonPagObjPed    NUMBER(12, 2) := 0;
    vnMonPagTota      NUMBER(12, 2) := 0;
    vnExitLideGana    VARCHAR2(1);
    vsTipoCanas       Lec_Progr_Canas_Premi.Ltcn_Cod_Tipo_Cana%TYPE;
    vsCodNivePedi     Lec_Progr_Canas_Premi.Lniv_Cod_Nive%TYPE;
    vnPosic           NUMBER;
    vsCodSap          LEC_PROGR_CANAS_DETAL.Cod_Sap%TYPE;
    vsCodVentFict     LEC_PROGR_CANAS_DETAL.Val_Codi_Vent%TYPE;
    vnOidProducto     MAE_PRODU.OID_PROD%TYPE;
    vnOidSolicCabec   PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;

    vnOidTipoSoliPais    ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
    vnFormaPagoEnv       PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
    vnClaseSolicEnv      PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
    vnOidAlmacEnv        PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
    vnTipoSoliCons       PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
    vnTipoDocum2         PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
    vnSubac              PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
    vnSocie              PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
    vnCodNivelExitoLider lec_lider_nivel.lniv_cod_nive%TYPE;
    vnCodigoPremio       lec_progr_canas_detal.val_codi_vent%TYPE;
    vnImpPrecPosi        PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;
    vnOidDetalleOferta   PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;
    vnNumeSoli           PED_NUMER_SOLIC.VAL_ULTI_NUME_SOLI%TYPE;
    vnFecFinPerio        CRA_PERIO.FEC_FINA%TYPE;
    vnOidEjecutiva       MAE_CLIEN.OID_CLIE%TYPE;
    vnOidFormPago        MAE_CLIEN.FOPA_OID_FORM_PAGO%TYPE;
    vnOidTipoDocu        MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
    vnOidClieDire        MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
    vnOidTerr            ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
    vnOidTerrAdmi        MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE;
    vnOidValorEstrGeop   ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
    vnOidZona            ZON_ZONA.OID_ZONA%TYPE;
    vnOidTipoCliente     MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
    vnOidSubTipoCliente  MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
    vnNumSoliInicio      NUMBER;
    vnNumSoliFormato     NUMBER;
    vnSecCana            LEC_PROGR_CANAS_PREMI.LPCN_SEC_CANA%TYPE;
    vnSecCanaPrem        LEC_PROGR_CANAS_PREMI.SEC_CANA_PREM%TYPE;
    vnSecCanaNuev        LEC_PROGR_CANAS_PREMI.LPCN_SEC_CANA%TYPE;
    vnSecCanaPremNuev    LEC_PROGR_CANAS_PREMI.SEC_CANA_PREM%TYPE;
    vnNumCampGrac        lec_progr.num_camp_grac%TYPE;
    vsCodClas            lec_clasi.cod_clas%TYPE;
    vnNumCampClas        lec_subcl.num_camp%TYPE;
    vnMonPagNueva        lec_progr_bono_nivel.mon_prem%TYPE := 0;
    VSCODTIPOPREM        lec_progr_bono_nivel.ltpr_cod_tipo_prem%TYPE;
    vnMonCanaNuev        LEC_PROGR_CANAS.VAL_CANA%type := 0;
    vnMonCana            LEC_PROGR_CANAS.VAL_CANA%type := 0;
    

    nLiderCanasSeq number(15);
    --Canastas configuradas Activas

    vnOidPais             SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca            SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal            SEG_CANAL.OID_CANA%TYPE;
    vsCampanaSiguiente    SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnOidPeriodoSiguiente SEG_PERIO_CORPO.OID_PERI%TYPE;
  BEGIN

    vnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

    vsCampanaSiguiente    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                    vnOidPais,
                                                                    vnOidMarca,
                                                                    vnOidCanal,
                                                                    1);
    vnOidPeriodoSiguiente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampanaSiguiente,
                                                                        vnOidMarca,
                                                                        vnOidCanal);

    SELECT IND_GANA_PEDI,
           IND_CANA,
           LTCO_COD_TIPO_COMI,
           nvl(lp.num_camp_grac, 0),
           lp.ind_exig_pedi_lide,
           lp.ind_tipo_orig_pedi,
           nvl(lp.ind_dese_meta_ingr,0)
      INTO VSINDGANAPEDI,
           VSINDCANA,
           VSCODTIPOCOMI,
           vnNumCampGrac,
           vsIndExigPediLide,
           vsIndOrigPediLide,
           vsIndMetaIngr
      FROM LEC_PROGR LP
     WHERE LP.PAIS_COD_PAIS = PSCODIGOPAIS
       AND LP.COD_PROG = PSCODIGOPROGRAMA;

    --obtener resultado de objetivo de pedidos
    BEGIN
      SELECT NUM_PEDI,
             LEOB_COD_ESTA_OBJE,
             lsr.num_pedi_cons,
             lsr.num_pedi_ncon,
             lsr.ind_pedi_pers_lide,
             lsr.ind_pedi_web,
             lsr.ind_pedi_cump_exig,
             lsr.leob_cod_esta_obje_ingr,
             lsr.leob_cod_esta_obje_capi
        INTO VNNUMPEDIREAL,
             VSCODESTAOBJ,
             vnNroPedConReal,
             vnNroPedNoConReal,
             vsIndPediPers,
             vsIndPediExig,
             vsIndPediCumpExig,
             VSCODESTAOBJIngr,
             VSCODESTAOBJCapi
        FROM LEC_LIDER_SECCI_RESUL LSR
       WHERE LSR.PAIS_COD_PAIS = PSCODIGOPAIS
         AND LSR.LPRO_COD_PROG = PSCODIGOPROGRAMA
         AND LSR.COD_REGI = PSCODIGOREGION
         AND LSR.COD_ZONA = PSCODIGOZONA
         AND LSR.COD_SECC = PSCODIGOSECCION
         AND LSR.COD_LIDE = PSCODIGOLIDER
         AND LSR.CAM_RESU = psCodigoPeriodo
         AND LSR.IND_ACTI = '1';
    EXCEPTION
      WHEN no_data_found THEN
        VNNUMPEDIREAL     := NULL;
        VSCODESTAOBJ      := NULL;
        vnNroPedConReal   := NULL;
        vnNroPedNoConReal := NULL;
        vsIndPediPers     := NULL;
        vsIndPediExig     := NULL;
        vsIndPediCumpExig := NULL;

    END;

    IF ((vsIndGanaPedi = '1' OR vsIndCana = '1') AND
       nvl(vsIndPediCumpExig, 0) = 1) THEN

      IF VSCODESTAOBJ IS NOT NULL THEN
        BEGIN
          --obtener el nivel de exito de la campaï¿½a de proceso
            BEGIN
            SELECT lln.lniv_cod_nive, nvl(pn.ind_cond_capi,0)  
            INTO vsCodNivel, vnIndMetaCapi
            FROM LEC_LIDER_NIVEL lln, lec_progr_nivel pn
            WHERE psCodigoPeriodo >= lln.cam_inic
             AND (lln.cam_fin IS NULL OR psCodigoPeriodo <= lln.cam_fin)
             AND lln.ind_tipo_nive = 'R'
             AND lln.pais_cod_pais = psCodigoPais
             AND lln.lpro_cod_prog = psCodigoPrograma
             AND lln.cod_lide = psCodigoLider
             AND pn.lpro_cod_prog(+) = lln.lpro_cod_prog
             AND pn.lniv_cod_nive(+) = lln.lniv_cod_nive
             AND lln.ind_acti = '1';
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  vsCodNivel := '00';
                  vnIndMetaCapi := 0;
            END;  
            BEGIN
                SELECT C.COD_CLAS, SC.NUM_CAMP
                    INTO VSCODCLAS, VNNUMCAMPCLAS
                    FROM LEC_LIDER_CLASI LC, LEC_CLASI C, LEC_SUBCL SC
                    WHERE LC.LCCL_COD_CLAS = SC.LCCL_COD_CLAS
                    AND LC.LSCL_COD_SUBC = SC.COD_SUBC
                    AND SC.LCCL_COD_CLAS = C.COD_CLAS
                    AND LC.CAM_INIC <= psCodigoPeriodo
                    AND (LC.CAM_FIN >= psCodigoPeriodo OR
                         LC.CAM_FIN IS NULL)
                    AND LC.COD_LIDE = PSCODIGOLIDER;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  VSCODCLAS := NULL;
            END;  
          --pago ganancia monto
          IF vsIndGanaPedi = '1' AND vsCodTipoComi = '01' THEN
                -----   Obtengo bono de Gracia Para Nuevas 
              IF VSCODCLAS = '01' AND VNNUMCAMPCLAS <= vnNumCampGrac THEN
                      --obtener el monto a pagar
                  BEGIN
                    SELECT NVL(LPBN.MON_PREM, 0), LPBN.LTPR_COD_TIPO_PREM
                      INTO VNMONPAGNUEVA, VSCODTIPOPREM
                      FROM LEC_PROGR_BONO_NIVEL LPBN
                     WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LPBN.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LPBN.LNIV_COD_NIVE = vsCodNivel
                       AND LPBN.LTBO_COD_TIPO_BONO = '09'
                       AND LPBN.IND_ACTI = '1';
                  EXCEPTION
                    WHEN no_data_found THEN
                      VNMONPAGNUEVA := 0;
                      VSCODTIPOPREM := NULL;
                  END;
              END IF;
           --Obtener parametria del nivel de exito
              BEGIN
                SELECT lpn.mon_pedi_cons, lpn.mon_pedi_ncon, lpn.mon_tole
                  INTO vnMonPediCons, vnMonPediNcon, vnTolPedi
                  FROM lec_progr_nivel lpn
                 WHERE lpn.pais_cod_pais = psCodigoPais
                   AND lpn.lpro_cod_prog = psCodigoPrograma
                   AND lpn.lniv_cod_nive = vsCodNivel
                   AND lpn.ind_acti = '1';
              EXCEPTION
                WHEN no_data_found THEN
                vnMonPediCons := NULL;
                vnMonPediNcon := NULL;
                vnTolPedi     := NULL;
              END;

            IF vnMonPediCons IS NOT NULL THEN
              --Verificar el estatus para calcular el Monto Objetivo a Pagar
              IF vsCodEstaObj = '01'    AND 
                  (  ( vnIndMetaCapi = 0  AND vsIndMetaIngr='0') OR
                  ( vsIndMetaIngr = '1'  AND VSCODESTAOBJIngr = '01') OR 
                  ( vnIndMetaCapi = 1    AND VSCODESTAOBJCapi= '01' ) ) THEN
                BEGIN
                  vnMonPagPedCons   := vnNroPedConReal * vnMonPediCons;
                  vnMonPagPedNoCons := vnNroPedNoConReal * vnMonPediNcon;
                  vnMonPagObjPed    := vnMonPagPedCons + vnMonPagPedNoCons;
                  vnMonPagTota      := vnMonPagObjPed;
                    --borrar tipo de ganancia Pedido Consecutivo No Consecutivo y Total
                  DELETE FROM LEC_LIDER_GANAN llg
                  WHERE llg.pais_cod_pais = psCodigoPais
                  AND llg.lpro_cod_prog = psCodigoPrograma
                  AND llg.cod_lide = psCodigoLider
                  AND llg.cam_gana = psCodigoPeriodo
                  AND llg.ltga_cod_tipo_gana IN
                       ('01', '02', '03', '04','13')
                  AND llg.ind_acti = '1';
                  IF  vnMonPagObjPed >= VNMONPAGNUEVA THEN
                  --Insertar Ganancia pedidos Consecutivos
                        INSERT INTO LEC_LIDER_GANAN
                          (PAIS_COD_PAIS,
                           LPRO_COD_PROG,
                           COD_REGI,
                           COD_ZONA,
                           COD_SECC,
                           COD_LIDE,
                           CAM_GANA,
                           LTGA_COD_TIPO_GANA,
                           SEC_LIDE_GANA,
                           MON_GANA,
                           IND_ESTA_PAGO_GANA,
                           CAM_REFE,
                           USU_CREA,
                           FEC_CREA,
                           IND_ACTI)
                        VALUES
                          (psCodigoPais,
                           psCodigoPrograma,
                           psCodigoRegion,
                           pscodigoZona,
                           psCodigoSeccion,
                           psCodigoLider,
                           psCodigoPeriodo,
                           '01',
                           LEC_LLGA_SEQ.NEXTVAL,
                           vnMonPagPedCons,
                           'N',
                           psCodigoPeriodo,
                           psCodigoUsuario,
                           SYSDATE,
                           '1');

                        --insertar Ganancia Pedidos No Consecutivos
                        INSERT INTO LEC_LIDER_GANAN
                          (PAIS_COD_PAIS,
                           LPRO_COD_PROG,
                           COD_REGI,
                           COD_ZONA,
                           COD_SECC,
                           COD_LIDE,
                           CAM_GANA,
                           LTGA_COD_TIPO_GANA,
                           SEC_LIDE_GANA,
                           MON_GANA,
                           IND_ESTA_PAGO_GANA,
                           CAM_REFE,
                           USU_CREA,
                           FEC_CREA,
                           IND_ACTI)
                        VALUES
                          (psCodigoPais,
                           psCodigoPrograma,
                           psCodigoRegion,
                           pscodigoZona,
                           psCodigoSeccion,
                           psCodigoLider,
                           psCodigoPeriodo,
                           '02',
                           LEC_LLGA_SEQ.NEXTVAL,
                           vnMonPagPedNoCons,
                           'N',
                           psCodigoPeriodo,
                           psCodigoUsuario,
                           SYSDATE,
                           '1');
                  ELSE
                     vnMonPagTota := vnMonPagNueva;
                     INSERT INTO LEC_LIDER_GANAN
                           (PAIS_COD_PAIS,
                           LPRO_COD_PROG,
                           COD_REGI,
                           COD_ZONA,
                           COD_SECC,
                           COD_LIDE,
                           CAM_GANA,
                           LTGA_COD_TIPO_GANA,
                           SEC_LIDE_GANA,
                           MON_GANA,
                           IND_ESTA_PAGO_GANA,
                           CAM_REFE,
                           USU_CREA,
                           FEC_CREA,
                           IND_ACTI)
                          VALUES
                          (psCodigoPais,
                           psCodigoPrograma,
                           psCodigoRegion,
                           pscodigoZona,
                           psCodigoSeccion,
                           psCodigoLider,
                           psCodigoPeriodo,
                           '13',
                           LEC_LLGA_SEQ.NEXTVAL,
                           vnMonPagNueva,
                           'N',
                           psCodigoPeriodo,
                           psCodigoUsuario,
                           SYSDATE,
                           '1');
                  END IF;
                  --Insertar Ganancia Total
                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (psCodigoPais,
                     psCodigoPrograma,
                     psCodigoRegion,
                     pscodigoZona,
                     psCodigoSeccion,
                     psCodigoLider,
                     psCodigoPeriodo,
                     '03',
                     LEC_LLGA_SEQ.NEXTVAL,
                     vnMonPagTota,
                     'N',
                     psCodigoPeriodo,
                     psCodigoUsuario,
                     SYSDATE,
                     '1');
               END;
              ELSIF  
                vsCodEstaObj = '02' OR vsCodEstaObj = '01' THEN
                BEGIN  
                  vnMonPagObjPed := vnNumPediReal * vnTolPedi;

                    --borrar tipo de ganancia Pedido Consecutivo No Consecutivo y Total
                  DELETE FROM LEC_LIDER_GANAN llg
                  WHERE llg.pais_cod_pais = psCodigoPais
                  AND llg.lpro_cod_prog = psCodigoPrograma
                  AND llg.cod_lide = psCodigoLider
                  AND llg.cam_gana = psCodigoPeriodo
                  AND llg.ltga_cod_tipo_gana IN
                      ('01', '02', '03', '04', '13')
                  AND llg.ind_acti = '1';
                  --insertar Ganancia Pedidos por Tolerancia
                  IF   vnMonPagObjPed >= vnMonPagNueva THEN
                       vnMonPagTota := vnMonPagObjPed;
                  ELSE
                       vnMonPagTota := vnMonPagNueva;
                  END IF;
                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (psCodigoPais,
                     psCodigoPrograma,
                     psCodigoRegion,
                     pscodigoZona,
                     psCodigoSeccion,
                     psCodigoLider,
                     psCodigoPeriodo,
                     '04',
                     LEC_LLGA_SEQ.NEXTVAL,
                     vnMonPagTota,
                     'N',
                     psCodigoPeriodo,
                     psCodigoUsuario,
                     SYSDATE,
                     '1');
                  --Insertar Ganancia Total

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (psCodigoPais,
                     psCodigoPrograma,
                     psCodigoRegion,
                     pscodigoZona,
                     psCodigoSeccion,
                     psCodigoLider,
                     psCodigoPeriodo,
                     '03',
                     LEC_LLGA_SEQ.NEXTVAL,
                     vnMonPagTota,
                     'N',
                     psCodigoPeriodo,
                     psCodigoUsuario,
                     SYSDATE,
                     '1');
                END; 
             END IF;  
           END IF;
          END IF;
       END;
      END IF;

      --pago canasta
      IF vsIndCana = '1' THEN

        IF vsCodEstaObj = '01' OR vsCodEstaObj = '02' THEN

          BEGIN
            --obtener canasta configurada para el objetivo de pedidos
            BEGIN
              SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                INTO vnSecCana, vnSecCanaPrem
                FROM lec_progr_canas_premi lcnp
               WHERE lcnp.lpcn_sec_cana IN
                     (SELECT lpcn.sec_cana
                        FROM lec_progr_canas lpcn
                       WHERE lpcn.pais_cod_pais = psCodigoPais
                         AND lpcn.lpro_cod_prog = psCodigoPrograma
                         AND lpcn.cam_acti = vsCampanaSiguiente
                         AND lpcn.ind_Acti = '1')
                 AND lcnp.ltcn_cod_tipo_cana = '01'
                 AND lcnp.lniv_cod_nive = vsCodNivel
                 AND lcnp.ind_acti = '1';
            EXCEPTION
              WHEN no_data_found THEN
                vnSecCana     := NULL;
                vnSecCanaPrem := NULL;
            END;
            IF  vnSecCana is not null THEN
                SELECT VAL_CANA   INTO vnMonCana
                FROM   lec_progr_canas lpcn 
                WHERE  lpcn.sec_cana = vnSecCana;
            END IF;
            vsTipoCanas := '01';
            IF VSCODCLAS = '01' AND VNNUMCAMPCLAS <= vnNumCampGrac THEN
                BEGIN
                  SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                    INTO vnSecCanaNuev, vnSecCanaPremNuev
                    FROM lec_progr_canas_premi lcnp
                   WHERE lcnp.lpcn_sec_cana IN
                         (SELECT lpcn.sec_cana
                            FROM lec_progr_canas lpcn
                           WHERE lpcn.pais_cod_pais = psCodigoPais
                             AND lpcn.lpro_cod_prog = psCodigoPrograma
                             AND lpcn.cam_acti = vsCampanaSiguiente
                             AND lpcn.ind_Acti = '1')
                     AND lcnp.ltcn_cod_tipo_cana = '10'
                     AND lcnp.lniv_cod_nive = vsCodNivel
                     AND lcnp.ind_acti = '1';
                EXCEPTION
                  WHEN no_data_found THEN
                    vnSecCanaNuev     := NULL;
                    vnSecCanaPremNuev := NULL;
                END;
                IF  vnSecCanaNuev is not null THEN
                    SELECT VAL_CANA   INTO vnMonCanaNuev
                    FROM   lec_progr_canas lpcn 
                    WHERE  lpcn.sec_cana = vnSecCanaNuev;
                END IF;
                IF  vnMonCanaNuev >= vnMonCana THEN
                    vnSecCanaPrem := vnSecCanaPremNuev;
                    vnSecCana := vnSecCanaNuev;
                    vsTipoCanas := '10';
                ELSE
                    vsTipoCanas := '01';
                END IF;
            END IF;
            
            IF vnSecCanaPrem IS NOT NULL THEN
              BEGIN
                --eliminar la canasta registrada
                DELETE FROM lec_lider_canas llc
                 WHERE llc.pais_cod_pais = psCodigoPais
                   AND llc.lpro_cod_prog = psCodigoPrograma
                   AND llc.cod_lide = psCodigoLider
                   AND llc.cam_cana =
                       gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                        psCodigoPeriodo,
                                                        1)
                   AND llc.ltcn_cod_tipo_cana in ( '01', '10');
                ---  AND llc.lpcn_sec_cana      = vnSecCana
                --- AND llc.lcnp_sec_cana_prem = vnSecCanaPrem;

                --insertar canasta
                INSERT INTO lec_lider_canas
                  (pais_cod_pais,
                   LPRO_COD_PROG,
                   COD_REGI,
                   COD_ZONA,
                   COD_SECC,
                   COD_LIDE,
                   CAM_CANA,
                   ltcn_cod_tipo_cana,
                   lpcn_sec_cana,
                   lcnp_sec_cana_prem,
                   SEC_LIDE_CANA,
                   IND_ESTA_DESP_CANA,
                   CAM_REFE,
                   USU_CREA,
                   FEC_CREA,
                   IND_ACTI)
                VALUES
                  (psCodigoPais,
                   psCodigoPrograma,
                   psCodigoRegion,
                   pscodigoZona,
                   psCodigoSeccion,
                   psCodigoLider,
                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                    psCodigoPeriodo,
                                                    1),
                   vsTipoCanas,
                   vnSecCana,
                   vnSecCanaPrem,
                   LEC_LLCA_SEQ.NEXTVAL,
                   'N',
                   psCodigoPeriodo,
                   psCodigoUsuario,
                   SYSDATE,
                   '1');
              END;
            END IF;

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              vnSecCana     := NULL;
              vnSecCanaPrem := NULL;
          END;

        END IF;

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_GANAN_PEDID_LIDER: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_GANAN_PEDID_LIDER;

  /***************************************************************************
      Descripcion       : Permite el cï¿½lculo de ganancia por objetivo de pedidos
      Fecha Creacion    : 17/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_CALCU_GANAN_BONO_LIDER(psCodigoPais     VARCHAR2,
                                          pscodigomarca    VARCHAR2,
                                          pscodigocanal    VARCHAR2,
                                          psCodigoPeriodo  VARCHAR2,
                                          psCodigoPrograma VARCHAR2,
                                          psCodigoRegion   VARCHAR2,
                                          pscodigoZona     VARCHAR2,
                                          psCodigoSeccion  VARCHAR2,
                                          psCodigoLider    VARCHAR2,
                                          psCodigoUsuario  VARCHAR2) IS

    vsIndCana         LEC_PROGR_BONO.Ind_Cana%TYPE;
    vsCodigoNivel     LEC_LIDER_NIVEL.LNIV_COD_NIVE%TYPE;
    vsCodTipoBono     LEC_LIDER_SECCI_RESUL_BONO.LTBO_COD_TIPO_BONO%TYPE;
    vnNumLanz         LEC_LIDER_SECCI_RESUL_BONO.Lpbl_Num_Lanz%TYPE;
    vsCodEstObj       LEC_LIDER_SECCI_RESUL_BONO.LEOB_COD_ESTA_OBJE%TYPE;
    vnMomPrem         LEC_PROGR_BONO_NIVEL.MON_PREM%TYPE;
    vsExitLidGana     varchar2(1);
    vnLiderGananSeq   number(15);
    vnMonPrem         LEC_PROGR_BONO_NIVEL.Mon_Prem%TYPE;
    vsCodTipoPrem     LEC_PROGR_BONO_NIVEL.Ltpr_Cod_Tipo_Prem%type;
    vnSecCana         LEC_PROGR_CANAS_PREMI.Lpcn_Sec_Cana%type;
    vnSecCanaPrem     LEC_PROGR_CANAS_PREMI.SEC_CANA_PREM%type;
    nLiderCanasSeq    number(15);
    vnNumeRete        lec_lider_secci_resul_bono.val_rete_secc%TYPE;
    vnNumeIngr        lec_lider_secci_resul_bono.num_ingr_secc%TYPE;
    vsIndPediCumpExig lec_lider_secci_resul.ind_pedi_cump_exig%TYPE;

    CURSOR cGetLanza IS
      SELECT LLSRB.LTBO_COD_TIPO_BONO,
             LLSRB.LPBL_NUM_LANZ,
             LLSRB.LEOB_COD_ESTA_OBJE
        FROM LEC_LIDER_SECCI_RESUL_BONO LLSRB
       WHERE LLSRB.PAIS_COD_PAIS = psCodigoPais
         AND LLSRB.LPRO_COD_PROG = psCodigoPrograma
         AND LLSRB.COD_REGI = psCodigoRegion
         AND LLSRB.COD_ZONA = pscodigoZona
         AND LLSRB.COD_SECC = psCodigoSeccion
         AND LLSRB.COD_LIDE = psCodigoLider
         AND LLSRB.CAM_RESU = psCodigoPeriodo
         AND LLSRB.LPBC_CAM_LANZ = psCodigoPeriodo
         AND LLSRB.LTBO_COD_TIPO_BONO IN ('01', '02', '03', '04');

    TYPE interfazLanza IS RECORD(
      codTipoBono LEC_LIDER_SECCI_RESUL_BONO.LTBO_COD_TIPO_BONO%TYPE,
      numLanz     LEC_LIDER_SECCI_RESUL_BONO.Lpbl_Num_Lanz%TYPE,
      codEstObj   LEC_LIDER_SECCI_RESUL_BONO.LEOB_COD_ESTA_OBJE%TYPE);

    TYPE interfazLanzaTab IS TABLE OF interfazLanza;
    interfazLanzaRecord interfazLanzaTab;

    CURSOR cGetCicloVida IS
      SELECT LLSRB.LTBO_COD_TIPO_BONO,
             LLSRB.LEOB_COD_ESTA_OBJE,
             LLSRB.VAL_RETE_SECC,
             LLSRB.NUM_INGR_SECC
        FROM LEC_LIDER_SECCI_RESUL_BONO LLSRB
       WHERE LLSRB.PAIS_COD_PAIS = psCodigoPais
         AND LLSRB.LPRO_COD_PROG = psCodigoPrograma
         AND LLSRB.COD_REGI = psCodigoRegion
         AND LLSRB.COD_ZONA = pscodigoZona
         AND LLSRB.COD_SECC = psCodigoSeccion
         AND LLSRB.COD_LIDE = psCodigoLider
         AND LLSRB.CAM_RESU = psCodigoPeriodo
         AND LLSRB.LTBO_COD_TIPO_BONO IN ('05', '06', '07');

    TYPE interfazCicloVida IS RECORD(
      codTipoBono LEC_LIDER_SECCI_RESUL_BONO.LTBO_COD_TIPO_BONO%TYPE,
      codEstObj   LEC_LIDER_SECCI_RESUL_BONO.LEOB_COD_ESTA_OBJE%TYPE,
      numRete     LEC_LIDER_SECCI_RESUL_BONO.VAL_RETE_SECC%TYPE,
      numIngr     LEC_LIDER_SECCI_RESUL_BONO.NUM_INGR_SECC%TYPE);

    TYPE interfazCicloVidaTab IS TABLE OF interfazCicloVida;
    interfazCicloVidaRecord interfazCicloVidaTab;

    CURSOR cGetCambNivAcele IS
      SELECT LLSRB.LTBO_COD_TIPO_BONO, LLSRB.LEOB_COD_ESTA_OBJE
        FROM LEC_LIDER_SECCI_RESUL_BONO LLSRB
       WHERE LLSRB.PAIS_COD_PAIS = psCodigoPais
         AND LLSRB.LPRO_COD_PROG = psCodigoPrograma
         AND LLSRB.COD_REGI = psCodigoRegion
         AND LLSRB.COD_ZONA = pscodigoZona
         AND LLSRB.COD_SECC = psCodigoSeccion
         AND LLSRB.COD_LIDE = psCodigoLider
         AND LLSRB.CAM_RESU = psCodigoPeriodo
         AND LLSRB.LTBO_COD_TIPO_BONO IN ('08');

    TYPE interfazCambNivAcele IS RECORD(
      codTipoBono LEC_LIDER_SECCI_RESUL_BONO.LTBO_COD_TIPO_BONO%TYPE,
      codEstObj   LEC_LIDER_SECCI_RESUL_BONO.LEOB_COD_ESTA_OBJE%TYPE);

    TYPE interfazCambNivAceleTab IS TABLE OF interfazCambNivAcele;
    interfazCambNivAceleRecord interfazCambNivAceleTab;

  BEGIN

    BEGIN
      SELECT LLN.LNIV_COD_NIVE
        INTO vsCodigoNivel
        FROM LEC_LIDER_NIVEL LLN, lec_progr_nivel pn
       WHERE LLN.PAIS_COD_PAIS = psCodigoPais
         AND LLN.LPRO_COD_PROG = psCodigoPrograma
         AND LLN.COD_LIDE = psCodigoLider
         AND lln.lpro_cod_prog = pn.lpro_cod_prog(+)
         AND lln.lniv_cod_nive = pn.lniv_cod_nive(+)
         AND psCodigoPeriodo >= LLN.CAM_INIC
         AND (psCodigoPeriodo <= LLN.CAM_FIN OR LLN.CAM_FIN IS NULL)
         AND LLN.IND_TIPO_NIVE = 'R';
    EXCEPTION
      WHEN no_data_found THEN
        vsCodigoNivel := NULL;
    END;

    --obtener resultado de objetivo de pedidos
    BEGIN
      SELECT lsr.ind_pedi_cump_exig
        INTO vsIndPediCumpExig
        FROM LEC_LIDER_SECCI_RESUL LSR
       WHERE LSR.PAIS_COD_PAIS = PSCODIGOPAIS
         AND LSR.LPRO_COD_PROG = PSCODIGOPROGRAMA
         AND LSR.COD_REGI = PSCODIGOREGION
         AND LSR.COD_ZONA = PSCODIGOZONA
         AND LSR.COD_SECC = PSCODIGOSECCION
         AND LSR.COD_LIDE = PSCODIGOLIDER
         AND LSR.CAM_RESU = psCodigoPeriodo
         AND LSR.IND_ACTI = '1';

    EXCEPTION
      WHEN no_data_found THEN
        vsIndPediCumpExig := NULL;
    END;

    IF vsCodigoNivel IS NOT NULL AND nvl(vsIndPediCumpExig, 0) = 1 THEN

      BEGIN

        --Obtener el resultado de Bono Lanzamiento Penetraciï¿½n Productos
        OPEN cGetLanza;
        LOOP
          FETCH cGetLanza BULK COLLECT
            INTO interfazLanzaRecord LIMIT 500; ----W_FILAS;
          IF interfazLanzaRecord.COUNT > 0 THEN
            FOR i IN interfazLanzaRecord.FIRST .. interfazLanzaRecord.LAST LOOP
              vsCodTipoBono := interfazLanzaRecord(i).codTipoBono;
              vnNumLanz     := interfazLanzaRecord(i).numLanz;
              vsCodEstObj   := interfazLanzaRecord(i).codEstObj;

              IF vsCodTipoBono = '02' AND vsCodEstObj = '05' THEN

                --Obtener Monto Ganancia de Bono Lanzamiento del Tipo de
                --Lanzamiento Estrat. Penetraciï¿½n Produc.Estart. Sobrecumplim. (02)
                SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                  INTO vnMomPrem, vsCodTipoPrem
                  FROM LEC_PROGR_BONO_NIVEL LPBN
                 WHERE LPBN.PAIS_COD_PAIS = psCodigoPais
                   AND LPBN.LPRO_COD_PROG = psCodigoPrograma
                   AND LPBN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND LPBN.LPBL_NUM_LANZ = vnNumLanz
                   AND LPBN.LNIV_COD_NIVE = vsCodigoNivel
                   AND LPBN.LTBO_COD_TIPO_BONO = vsCodTipoBono
                   AND LPBN.IND_ACTI = '1';

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO vsExitLidGana
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                     AND LLG.LPRO_COD_PROG = psCodigoPrograma
                     AND LLG.COD_REGI = psCodigoRegion
                     AND LLG.COD_ZONA = pscodigoZona
                     AND LLG.COD_SECC = psCodigoSeccion
                     AND LLG.COD_LIDE = psCodigoLider
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '06';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '06';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     LCPT_COD_TRAM,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS, --PAIS_COD_PAIS,
                     PSCODIGOPROGRAMA, --LPRO_COD_PROG,
                     PSCODIGOREGION, --COD_REGI,
                     PSCODIGOZONA, --COD_ZONA,
                     PSCODIGOSECCION, --COD_SECC,
                     PSCODIGOLIDER, --COD_LIDE,
                     psCodigoPeriodo, --CAM_GANA,
                     '06', --LTGA_COD_TIPO_GANA,
                     LEC_LLGA_SEQ.NEXTVAL, --SEC_LIDE_GANA,
                     NULL, --LCPT_COD_TRAM,
                     psCodigoPeriodo, --LPBC_CAM_LANZ,
                     vnNumLanz, --LPBL_NUM_LANZ,
                     VNMOMPREM, --MON_GANA,
                     'N', --IND_ESTA_PAGO_GANA,
                     psCodigoPeriodo, --CAM_REFE,
                     PSCODIGOUSUARIO, --USU_CREA,
                     SYSDATE, --FEC_CREA,
                     NULL, --USU_MODI,
                     NULL, --FEC_MODI,
                     '1' --IND_ACTI
                     );

                ELSIF vsCodTipoPrem = '02' THEN
                  --Canasta

                  BEGIN
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '03'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_acti = '1';

                    DELETE FROM lec_lider_canas llca
                     WHERE llca.pais_cod_pais = psCodigoPais
                       AND llca.lpro_cod_prog = psCodigoPrograma
                       AND llca.cod_lide = psCodigoLider
                       AND llca.cam_cana =
                           gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCodigoPeriodo,
                                                            1)
                       AND llca.ltcn_cod_tipo_cana = '03';
                    ---     AND llca.lpcn_sec_cana = vnSecCana
                    ---     AND llca.lcnp_sec_cana_prem = vnSecCanaPrem;

                    insert into lec_lider_canas
                      (pais_cod_pais,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       ltcn_cod_tipo_cana,
                       LPBC_CAM_LANZ,
                       LPBL_NUM_LANZ,
                       lpcn_sec_cana,
                       lcnp_sec_cana_prem,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    values
                      (psCodigoPais,
                       psCodigoPrograma,
                       psCodigoRegion,
                       pscodigoZona,
                       psCodigoSeccion,
                       psCodigoLider,
                       gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                        psCodigoPeriodo,
                                                        1),
                       '03',
                       psCodigoPeriodo,
                       vnNumLanz,
                       vnSecCana,
                       vnSecCanaPrem,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       psCodigoUsuario,
                       SYSDATE,
                       '1');

                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  end;
                END IF;
              ELSIF vsCodTipoBono = '01' AND vsCodEstObj = '04' THEN
                --Se Obtiene Monto Ganancia de Bono Lanzamiento
                -- del Tipo de Lanzamiento Estrat. Penetraciï¿½n Produc.Estrat. Cumplimiento (01)

                SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                  INTO vnMomPrem, vsCodTipoPrem
                  FROM LEC_PROGR_BONO_NIVEL LPBN
                 WHERE LPBN.PAIS_COD_PAIS = psCodigoPais
                   AND LPBN.LPRO_COD_PROG = psCodigoPrograma
                   AND LPBN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND LPBN.LPBL_NUM_LANZ = vnNumLanz
                   AND LPBN.LNIV_COD_NIVE = vsCodigoNivel
                   AND LPBN.LTBO_COD_TIPO_BONO = vsCodTipoBono
                   AND LPBN.IND_ACTI = '1';

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO vsExitLidGana
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                     AND LLG.LPRO_COD_PROG = psCodigoPrograma
                     AND LLG.COD_REGI = psCodigoRegion
                     AND LLG.COD_ZONA = pscodigoZona
                     AND LLG.COD_SECC = psCodigoSeccion
                     AND LLG.COD_LIDE = psCodigoLider
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '05';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '05';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     LCPT_COD_TRAM,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS, --PAIS_COD_PAIS,
                     PSCODIGOPROGRAMA, --LPRO_COD_PROG,
                     PSCODIGOREGION, --COD_REGI,
                     PSCODIGOZONA, --COD_ZONA,
                     PSCODIGOSECCION, --COD_SECC,
                     PSCODIGOLIDER, --COD_LIDE,
                     psCodigoPeriodo, --CAM_GANA,
                     '05', --LTGA_COD_TIPO_GANA,
                     LEC_LLGA_SEQ.NEXTVAL, --SEC_LIDE_GANA,
                     NULL, --LCPT_COD_TRAM,
                     psCodigoPeriodo, --LPBC_CAM_LANZ,
                     vnNumLanz, --LPBL_NUM_LANZ,
                     VNMOMPREM, --MON_GANA,
                     'N', --IND_ESTA_PAGO_GANA,
                     psCodigoPeriodo, --CAM_REFE,
                     PSCODIGOUSUARIO, --USU_CREA,
                     SYSDATE, --FEC_CREA,
                     NULL, --USU_MODI,
                     NULL, --FEC_MODI,
                     '1' --IND_ACTI
                     );

                ELSIF vsCodTipoPrem = '02' THEN
                  --Canasta

                  begin
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '02'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_acti = '1';

                    delete from lec_lider_canas llc
                     where llc.pais_cod_pais = psCodigoPais
                       and llc.lpro_cod_prog = psCodigoPrograma
                       and llc.cod_lide = psCodigoLider
                       and llc.cam_cana =
                           gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCodigoPeriodo,
                                                            1)
                       and llc.ltcn_cod_tipo_cana = '02';
                    ---   and llc.lpcn_sec_cana = vnSecCana
                    ---   and llc.lcnp_sec_cana_prem = vnSecCanaPrem;

                    insert into lec_lider_canas
                      (pais_cod_pais,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       ltcn_cod_tipo_cana,
                       LPBC_CAM_LANZ,
                       LPBL_NUM_LANZ,
                       lpcn_sec_cana,
                       lcnp_sec_cana_prem,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    values
                      (psCodigoPais,
                       psCodigoPrograma,
                       psCodigoRegion,
                       pscodigoZona,
                       psCodigoSeccion,
                       psCodigoLider,
                       gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                        psCodigoPeriodo,
                                                        1),
                       '02',
                       psCodigoPeriodo,
                       vnNumLanz,
                       vnSecCana,
                       vnSecCanaPrem,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       psCodigoUsuario,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  end;
                END IF;

              ELSIF vsCodTipoBono = '04' AND vsCodEstObj = '05' THEN

                /*Obtener Monto Ganancia de Bono Lanzamiento del
                Tipo de Lanzamiento Estrat. PUP SobreCumplim (04)
                */
                SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                  INTO vnMomPrem, vsCodTipoPrem
                  FROM LEC_PROGR_BONO_NIVEL LPBN
                 WHERE LPBN.PAIS_COD_PAIS = psCodigoPais
                   AND LPBN.LPRO_COD_PROG = psCodigoPrograma
                   AND LPBN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND LPBN.LPBL_NUM_LANZ = vnNumLanz
                   AND LPBN.LNIV_COD_NIVE = vsCodigoNivel
                   AND LPBN.LTBO_COD_TIPO_BONO = vsCodTipoBono
                   AND LPBN.IND_ACTI = '1';

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO vsExitLidGana
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                     AND LLG.LPRO_COD_PROG = psCodigoPrograma
                     AND LLG.COD_REGI = psCodigoRegion
                     AND LLG.COD_ZONA = pscodigoZona
                     AND LLG.COD_SECC = psCodigoSeccion
                     AND LLG.COD_LIDE = psCodigoLider
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '08';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '08';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     LCPT_COD_TRAM,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS, --PAIS_COD_PAIS,
                     PSCODIGOPROGRAMA, --LPRO_COD_PROG,
                     PSCODIGOREGION, --COD_REGI,
                     PSCODIGOZONA, --COD_ZONA,
                     PSCODIGOSECCION, --COD_SECC,
                     PSCODIGOLIDER, --COD_LIDE,
                     psCodigoPeriodo, --CAM_GANA,
                     '08', --LTGA_COD_TIPO_GANA,
                     LEC_LLGA_SEQ.NEXTVAL, --SEC_LIDE_GANA,
                     NULL, --LCPT_COD_TRAM,
                     psCodigoPeriodo, --LPBC_CAM_LANZ,
                     vnNumLanz, --LPBL_NUM_LANZ,
                     VNMOMPREM, --MON_GANA,
                     'N', --IND_ESTA_PAGO_GANA,
                     psCodigoPeriodo, --CAM_REFE,
                     PSCODIGOUSUARIO, --USU_CREA,
                     SYSDATE, --FEC_CREA,
                     NULL, --USU_MODI,
                     NULL, --FEC_MODI,
                     '1' --IND_ACTI
                     );

                ELSIF vsCodTipoPrem = '02' THEN

                  begin
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '05'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_acti = '1')
                       AND lcnp.ind_acti = '1';

                    delete from lec_lider_canas llc
                     where llc.pais_cod_pais = psCodigoPais
                       and llc.lpro_cod_prog = psCodigoPrograma
                       and llc.cod_lide = psCodigoLider
                       and llc.cam_cana =
                           gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCodigoPeriodo,
                                                            1)
                       and llc.ltcn_cod_tipo_cana = '05';
                    --   and llc.lpcn_sec_cana = vnSecCana
                    --   and llc.lcnp_sec_cana_prem = vnSecCanaPrem;

                    insert into lec_lider_canas
                      (pais_cod_pais,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       ltcn_cod_tipo_cana,
                       LPBC_CAM_LANZ,
                       LPBL_NUM_LANZ,
                       lpcn_sec_cana,
                       lcnp_sec_cana_prem,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    values
                      (psCodigoPais,
                       psCodigoPrograma,
                       psCodigoRegion,
                       pscodigoZona,
                       psCodigoSeccion,
                       psCodigoLider,
                       gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                        psCodigoPeriodo,
                                                        1),
                       '05',
                       psCodigoPeriodo,
                       vnNumLanz,
                       vnSecCana,
                       vnSecCanaPrem,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       psCodigoUsuario,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  end;

                END IF;
              ELSIF vsCodTipoBono = '03' AND vsCodEstObj = '05' THEN

                /*
                Se Obtiene Monto Ganancia de Bono Lanzamiento del Tipo de
                Lanzamiento Estrat. PUP Cumplim. (03)
                */
                SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                  INTO vnMomPrem, vsCodTipoPrem
                  FROM LEC_PROGR_BONO_NIVEL LPBN
                 WHERE LPBN.PAIS_COD_PAIS = psCodigoPais
                   AND LPBN.LPRO_COD_PROG = psCodigoPrograma
                   AND LPBN.LPBC_CAM_LANZ = psCodigoPeriodo
                   AND LPBN.LPBL_NUM_LANZ = vnNumLanz
                   AND LPBN.LNIV_COD_NIVE = vsCodigoNivel
                   AND LPBN.LTBO_COD_TIPO_BONO = vsCodTipoBono
                   AND LPBN.IND_ACTI = '1';

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO vsExitLidGana
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                     AND LLG.LPRO_COD_PROG = psCodigoPrograma
                     AND LLG.COD_REGI = psCodigoRegion
                     AND LLG.COD_ZONA = pscodigoZona
                     AND LLG.COD_SECC = psCodigoSeccion
                     AND LLG.COD_LIDE = psCodigoLider
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '07';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '07';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     LCPT_COD_TRAM,
                     LPBC_CAM_LANZ,
                     LPBL_NUM_LANZ,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     USU_MODI,
                     FEC_MODI,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS, --PAIS_COD_PAIS,
                     PSCODIGOPROGRAMA, --LPRO_COD_PROG,
                     PSCODIGOREGION, --COD_REGI,
                     PSCODIGOZONA, --COD_ZONA,
                     PSCODIGOSECCION, --COD_SECC,
                     PSCODIGOLIDER, --COD_LIDE,
                     psCodigoPeriodo, --CAM_GANA,
                     '07', --LTGA_COD_TIPO_GANA,
                     LEC_LLGA_SEQ.NEXTVAL, --SEC_LIDE_GANA,
                     NULL, --LCPT_COD_TRAM,
                     psCodigoPeriodo, --LPBC_CAM_LANZ,
                     vnNumLanz, --LPBL_NUM_LANZ,
                     VNMOMPREM, --MON_GANA,
                     'N', --IND_ESTA_PAGO_GANA,
                     psCodigoPeriodo, --CAM_REFE,
                     PSCODIGOUSUARIO, --USU_CREA,
                     SYSDATE, --FEC_CREA,
                     NULL, --USU_MODI,
                     NULL, --FEC_MODI,
                     '1' --IND_ACTI
                     );

                ELSIF vsCodTipoPrem = '02' THEN

                  begin
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '05'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_acti = '1')
                       AND lcnp.ind_acti = '1';

                    delete from lec_lider_canas llc
                     where llc.pais_cod_pais = psCodigoPais
                       and llc.lpro_cod_prog = psCodigoPrograma
                       and llc.cod_lide = psCodigoLider
                       and llc.cam_cana =
                           gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCodigoPeriodo,
                                                            1)
                       and llc.ltcn_cod_tipo_cana = '05';
                    --  and llc.lpcn_sec_cana = vnSecCana
                    --  and llc.lcnp_sec_cana_prem = vnSecCanaPrem;

                    insert into lec_lider_canas
                      (pais_cod_pais,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       ltcn_cod_tipo_cana,
                       LPBC_CAM_LANZ,
                       LPBL_NUM_LANZ,
                       lpcn_sec_cana,
                       lcnp_sec_cana_prem,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    values
                      (psCodigoPais,
                       psCodigoPrograma,
                       psCodigoRegion,
                       pscodigoZona,
                       psCodigoSeccion,
                       psCodigoLider,
                       gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                        psCodigoPeriodo,
                                                        1),
                       '05',
                       psCodigoPeriodo,
                       vnNumLanz,
                       vnSecCana,
                       vnSecCanaPrem,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       psCodigoUsuario,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  end;

                END IF;
              END IF;
            END LOOP;
          END IF;
          EXIT WHEN cGetLanza%NOTFOUND;
        END LOOP;
        CLOSE cGetLanza;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsIndCana := NULL;

      END;

      BEGIN

        /*     SELECT LPB.IND_CANA
         INTO vsIndCana
         FROM LEC_PROGR_BONO LPB
        WHERE LPB.PAIS_COD_PAIS      = psCodigoPais
          AND LPB.LPRO_COD_PROG      = psCodigoPrograma
          AND LPB.LTBO_COD_TIPO_BONO IN('05','06','07')
          AND LPB.IND_ACTI           = '1'
          AND ROWNUM                 = 1;*/

        --- Obtener el resultado de CICLO DE VIDA
        OPEN cGetCicloVida;
        LOOP
          FETCH cGetCicloVida BULK COLLECT
            INTO interfazCicloVidaRecord LIMIT 500; ----W_FILAS;
          IF interfazCicloVidaRecord.COUNT > 0 THEN
            FOR i IN interfazCicloVidaRecord.FIRST .. interfazCicloVidaRecord.LAST LOOP
              vsCodTipoBono := interfazCicloVidaRecord(i).codTipoBono;
              vsCodEstObj   := interfazCicloVidaRecord(i).codEstObj;
              vnNumeRete    := interfazCicloVidaRecord(i).NumRete;
              vnNumeIngr    := interfazCicloVidaRecord(i).numIngr;

              --Ciclo de Vida 2/2
              IF vsCodTipoBono = '05' AND vsCodEstObj = '07' THEN

                BEGIN
                  SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                    INTO VNMOMPREM, VSCODTIPOPREM
                    FROM LEC_PROGR_BONO_NIVEL LPBN
                   WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LPBN.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LPBN.LNIV_COD_NIVE = VSCODIGONIVEL
                     AND LPBN.LTBO_COD_TIPO_BONO = VSCODTIPOBONO
                     AND NVL(vnNumeRete, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Rete, 0) AND
                         NVL(LPBN.Num_Maxi_Rete, 0)
                     AND NVL(vnNumeIngr, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Ingr, 0) AND
                         NVL(LPBN.Num_Maxi_Ingr, 0)
                     AND LPBN.IND_ACTI = '1';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    VNMOMPREM     := 0;
                    VSCODTIPOPREM := '';
                END;

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO VSEXITLIDGANA
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LLG.COD_REGI = PSCODIGOREGION
                     AND LLG.COD_ZONA = PSCODIGOZONA
                     AND LLG.COD_SECC = PSCODIGOSECCION
                     AND LLG.COD_LIDE = PSCODIGOLIDER
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '09';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '09';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODIGOPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '09',
                     LEC_LLGA_SEQ.NEXTVAL,
                     vnMomPrem * vnNumeRete,
                     'N',
                     psCodigoPeriodo,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     '1');

                ELSIF vsCodTipoPrem = '02' THEN
                  BEGIN
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '06'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_Acti = '1';

                    DELETE FROM LEC_LIDER_CANAS LLC
                     WHERE LLC.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLC.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLC.COD_LIDE = PSCODIGOLIDER
                       AND LLC.CAM_CANA =
                           GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                            psCodigoPeriodo,
                                                            1)
                       AND LLC.LTCN_COD_TIPO_CANA = '06';
                    ---    AND LLC.LPCN_SEC_CANA = VNSECCANA
                    ---    AND LLC.LCNP_SEC_CANA_PREM = VNSECCANAPREM;

                    INSERT INTO LEC_LIDER_CANAS
                      (PAIS_COD_PAIS,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       LTCN_COD_TIPO_CANA,
                       LPCN_SEC_CANA,
                       LCNP_SEC_CANA_PREM,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    VALUES
                      (PSCODIGOPAIS,
                       PSCODIGOPROGRAMA,
                       PSCODIGOREGION,
                       PSCODIGOZONA,
                       PSCODIGOSECCION,
                       PSCODIGOLIDER,
                       GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                        psCodigoPeriodo,
                                                        1),
                       '06',
                       VNSECCANA,
                       VNSECCANAPREM,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       PSCODIGOUSUARIO,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  END;
                END IF;
                --Ciclo de Vida 3/3
              ELSIF vsCodTipoBono = '06' AND vsCodEstObj = '07' THEN
                BEGIN
                  SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                    INTO VNMOMPREM, VSCODTIPOPREM
                    FROM LEC_PROGR_BONO_NIVEL LPBN
                   WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LPBN.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LPBN.LNIV_COD_NIVE = VSCODIGONIVEL
                     AND LPBN.LTBO_COD_TIPO_BONO = VSCODTIPOBONO
                     AND NVL(vnNumeRete, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Rete, 0) AND
                         NVL(LPBN.Num_Maxi_Rete, 0)
                     AND NVL(vnNumeIngr, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Ingr, 0) AND
                         NVL(LPBN.Num_Maxi_Ingr, 0)
                     AND LPBN.IND_ACTI = '1';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    VNMOMPREM     := 0;
                    VSCODTIPOPREM := '';
                END;

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO VSEXITLIDGANA
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LLG.COD_REGI = PSCODIGOREGION
                     AND LLG.COD_ZONA = PSCODIGOZONA
                     AND LLG.COD_SECC = PSCODIGOSECCION
                     AND LLG.COD_LIDE = PSCODIGOLIDER
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '10';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLG.COD_LIDE = PSCODIGOLIDER
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '10';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODIGOPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '10',
                     LEC_LLGA_SEQ.NEXTVAL,
                     VNMOMPREM * vnNumeRete,
                     'N',
                     psCodigoPeriodo,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     '1');

                ELSIF vsCodTipoPrem = '02' THEN

                  begin
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '07'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_Acti = '1';

                    DELETE FROM LEC_LIDER_CANAS LLC
                     WHERE LLC.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLC.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLC.COD_LIDE = PSCODIGOLIDER
                       AND LLC.CAM_CANA =
                           GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                            psCodigoPeriodo,
                                                            1)
                       AND LLC.LTCN_COD_TIPO_CANA = '07';
                    --    AND LLC.LPCN_SEC_CANA = VNSECCANA
                    --    AND LLC.LCNP_SEC_CANA_PREM = VNSECCANAPREM;

                    INSERT INTO LEC_LIDER_CANAS
                      (PAIS_COD_PAIS,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       LTCN_COD_TIPO_CANA,
                       LPCN_SEC_CANA,
                       LCNP_SEC_CANA_PREM,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    VALUES
                      (PSCODIGOPAIS,
                       PSCODIGOPROGRAMA,
                       PSCODIGOREGION,
                       PSCODIGOZONA,
                       PSCODIGOSECCION,
                       PSCODIGOLIDER,
                       GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                        psCodigoPeriodo,
                                                        1),
                       '07',
                       VNSECCANA,
                       VNSECCANAPREM,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       PSCODIGOUSUARIO,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  END;

                END IF;
                --Ciclo de Vida 4/4
              ELSIF vsCodTipoBono = '07' AND vsCodEstObj = '07' THEN

                BEGIN
                  SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                    INTO VNMOMPREM, VSCODTIPOPREM
                    FROM LEC_PROGR_BONO_NIVEL LPBN
                   WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LPBN.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LPBN.LNIV_COD_NIVE = VSCODIGONIVEL
                     AND LPBN.LTBO_COD_TIPO_BONO = VSCODTIPOBONO
                     AND NVL(vnNumeRete, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Rete, 0) AND
                         NVL(LPBN.Num_Maxi_Rete, 0)
                     AND NVL(vnNumeIngr, 0) BETWEEN
                         NVL(LPBN.Num_Mini_Ingr, 0) AND
                         NVL(LPBN.Num_Maxi_Ingr, 0)
                     AND LPBN.IND_ACTI = '1';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    VNMOMPREM     := 0;
                    VSCODTIPOPREM := '';
                END;

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO VSEXITLIDGANA
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LLG.COD_REGI = PSCODIGOREGION
                     AND LLG.COD_ZONA = PSCODIGOZONA
                     AND LLG.COD_SECC = PSCODIGOSECCION
                     AND LLG.COD_LIDE = PSCODIGOLIDER
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '11';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = psCodigoPais
                       AND LLG.LPRO_COD_PROG = psCodigoPrograma
                       AND LLG.COD_LIDE = psCodigoLider
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '11';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODIGOPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '11',
                     LEC_LLGA_SEQ.NEXTVAL,
                     VNMOMPREM * vnNumeRete,
                     'N',
                     psCodigoPeriodo,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     '1');
                ELSIF vsCodTipoPrem = '02' THEN

                  BEGIN
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '08'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                               AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_Acti = '1';

                    DELETE FROM LEC_LIDER_CANAS LLC
                     WHERE LLC.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLC.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLC.COD_LIDE = PSCODIGOLIDER
                       AND LLC.CAM_CANA =
                           GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                            psCodigoPeriodo,
                                                            1)
                       AND LLC.LTCN_COD_TIPO_CANA = '08';
                    --  AND LLC.LPCN_SEC_CANA = VNSECCANA
                    --  AND LLC.LCNP_SEC_CANA_PREM = VNSECCANAPREM;

                    INSERT INTO LEC_LIDER_CANAS
                      (PAIS_COD_PAIS,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       LTCN_COD_TIPO_CANA,
                       LPCN_SEC_CANA,
                       LCNP_SEC_CANA_PREM,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    VALUES
                      (PSCODIGOPAIS,
                       PSCODIGOPROGRAMA,
                       PSCODIGOREGION,
                       PSCODIGOZONA,
                       PSCODIGOSECCION,
                       PSCODIGOLIDER,
                       GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                        psCodigoPeriodo,
                                                        1),
                       '08',
                       VNSECCANA,
                       VNSECCANAPREM,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       PSCODIGOUSUARIO,
                       SYSDATE,
                       '1');
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                      vnSecCana     := NULL;
                      vnSecCanaPrem := NULL;
                  END;
                END IF;
              END IF;
            END LOOP;
          END IF;
          EXIT WHEN cGetCicloVida%NOTFOUND;
        END LOOP;
        CLOSE cGetCicloVida;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsIndCana := NULL;
      END;

      BEGIN

        /* SELECT LPB.IND_CANA
         INTO VSINDCANA
         FROM LEC_PROGR_BONO LPB
        WHERE LPB.PAIS_COD_PAIS = PSCODIGOPAIS
          AND LPB.LPRO_COD_PROG = PSCODIGOPROGRAMA
          AND LPB.LTBO_COD_TIPO_BONO = '08'
          AND LPB.IND_ACTI = '1'
          AND ROWNUM = 1;*/

        -- Obtener el resultado de CAMBIO NIVEL ACELERADO
        OPEN cGetCambNivAcele;
        LOOP
          FETCH cGetCambNivAcele BULK COLLECT
            INTO interfazCambNivAceleRecord LIMIT 500; ----W_FILAS;
          IF interfazCambNivAceleRecord.COUNT > 0 THEN
            FOR i IN interfazCambNivAceleRecord.FIRST .. interfazCambNivAceleRecord.LAST LOOP
              vsCodTipoBono := interfazCambNivAceleRecord(i).codTipoBono;
              vsCodEstObj   := interfazCambNivAceleRecord(i).codEstObj;
              --Cambio de Nivel Acelerado
              IF vsCodTipoBono = '08' AND vsCodEstObj = '09' THEN

                SELECT LPBN.MON_PREM, LPBN.LTPR_COD_TIPO_PREM
                  INTO VNMOMPREM, VSCODTIPOPREM
                  FROM LEC_PROGR_BONO_NIVEL LPBN
                 WHERE LPBN.PAIS_COD_PAIS = PSCODIGOPAIS
                   AND LPBN.LPRO_COD_PROG = PSCODIGOPROGRAMA
                   AND LPBN.LNIV_COD_NIVE = VSCODIGONIVEL
                   AND LPBN.LTBO_COD_TIPO_BONO = VSCODTIPOBONO
                   AND LPBN.IND_ACTI = '1';

                IF vsCodTipoPrem = '01' AND vnMomPrem > 0 THEN

                  SELECT DECODE(COUNT(1), 0, 'F', 'T')
                    INTO VSEXITLIDGANA
                    FROM LEC_LIDER_GANAN LLG
                   WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                     AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                     AND LLG.COD_REGI = PSCODIGOREGION
                     AND LLG.COD_ZONA = PSCODIGOZONA
                     AND LLG.COD_SECC = PSCODIGOSECCION
                     AND LLG.COD_LIDE = PSCODIGOLIDER
                     AND LLG.CAM_GANA = psCodigoPeriodo
                     AND LLG.LTGA_COD_TIPO_GANA = '12';

                  IF vsExitLidGana = 'T' THEN
                    DELETE FROM LEC_LIDER_GANAN LLG
                     WHERE LLG.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLG.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLG.COD_LIDE = PSCODIGOLIDER
                       AND LLG.CAM_GANA = psCodigoPeriodo
                       AND LLG.LTGA_COD_TIPO_GANA = '12';
                  END IF;

                  INSERT INTO LEC_LIDER_GANAN
                    (PAIS_COD_PAIS,
                     LPRO_COD_PROG,
                     COD_REGI,
                     COD_ZONA,
                     COD_SECC,
                     COD_LIDE,
                     CAM_GANA,
                     LTGA_COD_TIPO_GANA,
                     SEC_LIDE_GANA,
                     MON_GANA,
                     IND_ESTA_PAGO_GANA,
                     CAM_REFE,
                     USU_CREA,
                     FEC_CREA,
                     IND_ACTI)
                  VALUES
                    (PSCODIGOPAIS,
                     PSCODIGOPROGRAMA,
                     PSCODIGOREGION,
                     PSCODIGOZONA,
                     PSCODIGOSECCION,
                     PSCODIGOLIDER,
                     psCodigoPeriodo,
                     '12',
                     LEC_LLGA_SEQ.NEXTVAL,
                     VNMOMPREM,
                     'N',
                     psCodigoPeriodo,
                     PSCODIGOUSUARIO,
                     SYSDATE,
                     '1');

                ELSIF vsCodTipoPrem = '02' THEN

                  begin
                    SELECT lcnp.lpcn_sec_cana, lcnp.sec_cana_prem
                      INTO vnSecCana, vnSecCanaPrem
                      FROM lec_progr_canas_premi lcnp
                     WHERE lcnp.ltcn_cod_tipo_cana = '09'
                       AND lcnp.lniv_cod_nive = vsCodigoNivel
                       AND lcnp.lpcn_sec_cana IN
                           (SELECT lpcn.sec_cana
                              FROM lec_progr_canas lpcn
                             WHERE lpcn.pais_cod_pais = psCodigoPais
                                  ---   AND lpcn.lpro_cod_prog = psCodigoPrograma
                               AND lpcn.cam_acti =
                                   GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                                    psCodigoPeriodo,
                                                                    1)
                               AND lpcn.ind_Acti = '1')
                       AND lcnp.ind_Acti = '1';

                    DELETE FROM LEC_LIDER_CANAS LLC
                     WHERE LLC.PAIS_COD_PAIS = PSCODIGOPAIS
                       AND LLC.LPRO_COD_PROG = PSCODIGOPROGRAMA
                       AND LLC.COD_LIDE = PSCODIGOLIDER
                       AND LLC.CAM_CANA =
                           GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                            psCodigoPeriodo,
                                                            1)
                       AND LLC.LTCN_COD_TIPO_CANA = '09';
                    -- AND LLC.LPCN_SEC_CANA = VNSECCANA
                    --  AND LLC.LCNP_SEC_CANA_PREM = VNSECCANAPREM;

                    INSERT INTO LEC_LIDER_CANAS
                      (PAIS_COD_PAIS,
                       LPRO_COD_PROG,
                       COD_REGI,
                       COD_ZONA,
                       COD_SECC,
                       COD_LIDE,
                       CAM_CANA,
                       LTCN_COD_TIPO_CANA,
                       LPCN_SEC_CANA,
                       LCNP_SEC_CANA_PREM,
                       SEC_LIDE_CANA,
                       IND_ESTA_DESP_CANA,
                       CAM_REFE,
                       USU_CREA,
                       FEC_CREA,
                       IND_ACTI)
                    VALUES
                      (PSCODIGOPAIS,
                       PSCODIGOPROGRAMA,
                       PSCODIGOREGION,
                       PSCODIGOZONA,
                       PSCODIGOSECCION,
                       PSCODIGOLIDER,
                       GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(PSCODIGOPAIS,
                                                        psCodigoPeriodo,
                                                        1),
                       '09',
                       VNSECCANA,
                       VNSECCANAPREM,
                       LEC_LLCA_SEQ.NEXTVAL,
                       'N',
                       psCodigoPeriodo,
                       PSCODIGOUSUARIO,
                       SYSDATE,
                       '1');
                  exception
                    when no_data_found then
                      vnSecCana     := null;
                      vnSecCanaPrem := null;
                  END;

                END IF;

              END IF;
            END LOOP;
          END IF;
          EXIT WHEN cGetCambNivAcele%NOTFOUND;
        END LOOP;
        CLOSE cGetCambNivAcele;

      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsIndCana := NULL;
      END;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CALCU_GANAN_BONO_LIDER: ' ||
                              ls_sqlerrm);

  END LEC_PR_CALCU_GANAN_BONO_LIDER;

  /***************************************************************************
  Descripcion       : Generar Pago Regular
  Fecha Creacion    : 20/03/2014
  Autor             : Henry Paredes B.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_PAGO_REGUL(pscodigoPais      VARCHAR2,
                                    pscodigoPrograma  VARCHAR2,
                                    psCampanaProceso  VARCHAR2,
                                    psCampanaCobranza VARCHAR2,
                                    psCampanaBono     VARCHAR2,
                                    pscodigoTipoPago  VARCHAR2,
                                    psfechaProceso    VARCHAR2,
                                    psCodigoUsuario   VARCHAR2) IS

    pnIndExigeCodigoSap NUMBER;
    pnSecuenciaDocu     NUMBER;
    lnCount             NUMBER;
    w_filas             NUMBER := 500;
    countRegistros      number;

    CURSOR c_pagos(vsindTipoGrupoPago VARCHAR2) IS

      SELECT lide.cod_lide,
             CASE
               WHEN lide.regionRecaudo IS NULL THEN
                lide.regionBono
               ELSE
                lide.regionRecaudo
             END cod_regi,
             CASE
               WHEN lide.zonaRecaudo IS NULL THEN
                lide.zonaBono
               ELSE
                lide.zonaRecaudo
             END cod_zona,
             CASE
               WHEN lide.seccionRecaudo IS NULL THEN
                lide.seccionBono
               ELSE
                lide.seccionRecaudo
             END cod_secc,
             gana.mon_gana_tota,
             gana.val_cuen_cont,
             gana.val_cent_cost,
             lide.cod_prov,
             gana.tex_pres
        FROM (SELECT gere.gere cod_lide,
                     clda.cod_prov,
                     MAX(CASE
                           WHEN psCampanaCobranza BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_regi
                           ELSE
                            NULL
                         END) regionRecaudo,
                     MAX(CASE
                           WHEN psCampanaCobranza BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_zona
                           ELSE
                            NULL
                         END) zonaRecaudo,
                     MAX(CASE
                           WHEN psCampanaCobranza BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_secc
                           ELSE
                            NULL
                         END) seccionRecaudo,
                     --
                     MAX(CASE
                           WHEN psCampanaBono BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_regi
                           ELSE
                            NULL
                         END) regionBono,
                     MAX(CASE
                           WHEN psCampanaBono BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_zona
                           ELSE
                            NULL
                         END) zonaBono,
                     MAX(CASE
                           WHEN psCampanaBono BETWEEN peri.cod_peri AND
                                peri2.cod_peri THEN
                            gere.cod_secc
                           ELSE
                            NULL
                         END) seccionBono
                FROM zon_histo_geren       gere,
                     cra_perio             perd,
                     seg_perio_corpo       peri,
                     cra_perio             perd2,
                     seg_perio_corpo       peri2,
                     mae_clien             clie,
                     mae_clien_datos_adici clda
               WHERE 1 = 1
                 AND gere.perd_oid_peri_desd = perd.oid_peri
                 AND perd.peri_oid_peri = peri.oid_peri
                 AND NVL(gere.perd_oid_peri_hast,
                         GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCampanaBono)) =
                     perd2.oid_peri
                 AND perd2.peri_oid_peri = peri2.oid_peri
                 AND gere.gere = clie.cod_clie
                 AND clie.oid_clie = clda.clie_oid_clie
                    --
                 AND gere.cod_regi IS NOT NULL
                 AND gere.cod_zona IS NOT NULL
                 AND gere.cod_secc IS NOT NULL
                 AND (psCampanaCobranza BETWEEN peri.cod_peri AND
                     peri2.cod_peri OR
                     psCampanaBono BETWEEN peri.cod_peri AND peri2.cod_peri)
               GROUP BY gere.gere, clda.cod_prov) lide,
             (SELECT llga.cod_lide,
                     ltpa.val_cuen_cont,
                     ltpa.val_cent_cost,
                     ltpa.tex_pres,
                     SUM(llga.mon_gana) mon_gana_tota
                FROM lec_lider_ganan llga,
                     lec_tipo_ganan  ltga,
                     lec_tipo_pago   ltpa
               WHERE llga.ltga_cod_tipo_gana = ltga.cod_tipo_gana
                 AND ltga.ltpa_cod_tipo_pago = ltpa.cod_tipo_pago
                 AND ((llga.cam_refe = psCampanaBono AND
                     llga.cam_gana = psCampanaBono) OR
                     (llga.cam_gana = psCampanaCobranza AND
                     llga.cam_refe > llga.cam_gana))
                 AND ((countRegistros > 0 AND
                     DECODE(vsindTipoGrupoPago,
                              'R',
                              cod_regi,
                              'Z',
                              cod_zona,
                              NULL) IN
                     (SELECT lgrr.cod_regi
                          FROM lec_grupo_proce_recau_regio lgrr
                         WHERE lgrr.pais_cod_pais = psCodigoPais
                           AND lgrr.lgpr_cod_grup_regi in
                               (select val_codi from MAE_GTT_GRUPUA)
                           AND lgrr.ind_acti = '1')) OR countRegistros = 0)
                 AND llga.ind_esta_pago_gana = 'N' -- No pagado
               GROUP BY llga.cod_lide,
                        ltpa.val_cuen_cont,
                        ltpa.val_cent_cost,
                        ltpa.tex_pres) gana
       WHERE lide.cod_lide = gana.cod_lide;

    TYPE c_pagos_t IS TABLE OF c_Pagos%ROWTYPE INDEX BY BINARY_INTEGER;
    RegPagos c_pagos_t;

    psindTipoGrupoPago VARCHAR2(1);

  BEGIN
    select count(1) into countRegistros from MAE_GTT_GRUPUA;
    -- Obtener Indicador de exigencia de codigo sap para pago.
    BEGIN
      SELECT VAL_PARA
        INTO pnIndExigeCodigoSap
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = pscodigoPais
         AND COD_SIST = 'LEC'
         AND UPPER(NOM_PARA) = 'INDEXIGECODIGOSAP'
         AND IND_ACTI = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL INDICADOR DE EXIGENCIA DE CODIGO SAP');
    END;

    -- Obtener secuencia de registro de pago
    BEGIN
      SELECT VAL_ULTI_NUME_SOLI
        INTO pnSecuenciaDocu
        FROM PED_NUMER_SOLIC
       WHERE COD_PAIS = pscodigoPais
         AND VAL_OPER = 'LEC001'
         AND VAL_ANIO = SUBSTR(psCampanaProceso, 3, 2);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL CONTADOR DEL Nï¿½MERO DE COMPROBANTE');
    END;

    -- Obtener indicador de Tipo de Grupo Pago
    BEGIN
      SELECT VAL_PARA
        INTO psindTipoGrupoPago
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = pscodigoPais
         AND COD_SIST = 'LET'
         AND UPPER(NOM_PARA) = 'INDTIPOGRUPOPAGO'
         AND IND_ACTI = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL INDICADOR DE TIPO DE GRUPO DE PAGO (R ï¿½ Z)');
    END;

    -- Contar registros que ya procesaron pagos de las zonas o regiones de los grupos de pago

    SELECT COUNT(*)
      INTO lnCount
      FROM lec_lider_pago_comis lcom
     WHERE lcom.pais_cod_pais = pscodigoPais
       AND lcom.lpro_cod_prog = pscodigoPrograma
       AND lcom.ltpa_cod_tipo_pago = pscodigoTipoPago
       AND lcom.cam_refe = psCampanaBono
       AND lcom.cam_reca = psCampanaCobranza
       AND TO_CHAR(lcom.fec_proc_pago, 'DD/MM/YYYY') < psFechaProceso
       AND ((countRegistros > 0 AND
           DECODE(psindTipoGrupoPago, 'R', cod_regi, 'Z', cod_zona, NULL) IN
           (SELECT lgrr.cod_regi
                FROM lec_grupo_proce_recau_regio lgrr
               WHERE lgrr.pais_cod_pais = psCodigoPais
                 AND lgrr.lgpr_cod_grup_regi in
                     (select val_codi from MAE_GTT_GRUPUA)
                 AND lgrr.ind_acti = '1')) OR countRegistros = 0)
       AND lcom.ind_proc_pago IN (1, 2);

    -- Validar si ya se procesaron pagos de las zonas o regiones de los grupos de pago

    IF lnCount > 0 THEN
      RAISE_APPLICATION_ERROR(-20123,
                              'SE ENCONTRARON PAGOS PROCESADOS Y/O CONTABILIZADOS ANTERIORMENTE PARA ESTE TIPO DE PAGO DEL GRUPO SELECCIONADO.');
    ELSE
      DELETE FROM LEC_LIDER_PAGO_COMIS
       WHERE PAIS_COD_PAIS = pscodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         AND LTPA_COD_TIPO_PAGO = pscodigoTipoPago
         AND CAM_REFE = psCampanaBono
         AND CAM_RECA = psCampanaCobranza
         AND TO_CHAR(FEC_PROC_PAGO, 'DD/MM/YYYY') >= psFechaProceso
         AND IND_PROC_PAGO = '1'
         AND ((countRegistros > 0 AND
             DECODE(psindTipoGrupoPago,
                      'R',
                      cod_regi,
                      'Z',
                      cod_zona,
                      NULL) IN
             (SELECT lgrr.cod_regi
                  FROM lec_grupo_proce_recau_regio lgrr
                 WHERE lgrr.pais_cod_pais = psCodigoPais
                   AND lgrr.lgpr_cod_grup_regi in
                       (select val_codi from MAE_GTT_GRUPUA)
                   AND lgrr.ind_acti = '1')) OR countRegistros = 0);
    END IF;

    -- PROCESO PRINCIPAL
    OPEN c_pagos(psindTipoGrupoPago);
    LOOP
      FETCH c_pagos BULK COLLECT
        INTO regPagos LIMIT w_filas;
      IF regPagos.COUNT > 0 THEN
        FOR x IN regPagos.FIRST .. regPagos.LAST LOOP

          IF (pnIndExigeCodigoSap = 1 AND regPagos(x).cod_prov IS NOT NULL) OR
             pnIndExigeCodigoSap = 0 THEN
            pnSecuenciaDocu := pnSecuenciaDocu + 1;

            INSERT INTO LEC_LIDER_PAGO_COMIS
              (pais_cod_pais,
               lpro_cod_prog,
               ltpa_cod_tipo_pago,
               num_secu,
               cod_lide,
               cod_regi,
               cod_zona,
               cod_secc,
               val_mont_brut,
               val_impu,
               val_mont_neto,
               fec_proc_pago,
               fec_proc_neto,
               fec_cont,
               cam_proc,
               cam_refe,
               cod_sap_cons,
               per_cont,
               tex_pago,
               num_cuen,
               cen_cost,
               ind_proc_pago,
               usu_crea,
               fec_crea,
               ind_acti,
               cam_reca,
               ltpg_num_tarj)
            VALUES
              (pscodigoPais,
               pscodigoPrograma,
               pscodigoTipoPago,
               pnSecuenciaDocu,
               regPagos(x).cod_lide,
               regPagos(x).cod_regi,
               regPagos(x).cod_zona,
               regPagos(x).cod_secc,
               regPagos(x).mon_gana_tota,
               NULL,
               NULL,
               TO_DATE(psfechaProceso, 'DD/MM/YYYY'),
               NULL,
               NULL,
               psCampanaProceso,
               psCampanaBono,
               regPagos(x).cod_prov,
               SUBSTR(psCampanaBono, 1, 4),
               regPagos(x).tex_pres,
               regPagos(x).val_cuen_cont,
               regPagos(x).val_cent_cost,
               1,
               pscodigousuario,
               SYSDATE,
               1,
               psCampanaCobranza,
               (select tp.num_tarj from lec_tarje_lider tl, lec_tarje_pagos tp 
               where tl.ltpg_cod_tarj=tp.cod_tarj and cod_lide=regPagos(x).cod_lide and cam_bloq is null and 
               tp.LEST_COD_ESTA='03'));

            -- Marca el registro de ganancias como procesado

            UPDATE lec_lider_ganan llga
               SET llga.ind_esta_pago_gana = 'S',
               llga.fec_modi = sysdate, llga.usu_modi = psCodigoUsuario
             WHERE llga.pais_cod_pais = psCodigoPais
               AND llga.lpro_cod_prog = psCodigoPrograma
               AND llga.cod_lide = regPagos(x).cod_lide
               AND llga.ind_esta_pago_gana = 'N'
               AND llga.ind_acti = '1'
               AND ((llga.cam_refe = psCampanaBono AND
                   llga.cam_gana = psCampanaBono) OR
                   (llga.cam_gana = psCampanaCobranza AND
                   llga.cam_refe > llga.cam_gana))
               AND llga.ltga_cod_tipo_gana IN
                   (SELECT ltga.cod_tipo_gana
                      FROM lec_tipo_ganan ltga, lec_tipo_pago ltpa
                     WHERE ltga.ltpa_cod_tipo_pago = ltpa.cod_tipo_pago
                       AND ltpa.cod_tipo_pago = pscodigotipopago)
               AND ((countRegistros > 0 AND
                   DECODE(psindTipoGrupoPago,
                            'R',
                            llga.cod_regi,
                            'Z',
                            llga.cod_zona,
                            NULL) IN
                   (SELECT lgrr.cod_regi
                        FROM lec_grupo_proce_recau_regio lgrr
                       WHERE lgrr.pais_cod_pais = psCodigoPais
                         AND lgrr.lgpr_cod_grup_regi in
                             (select val_codi from MAE_GTT_GRUPUA)
                         AND lgrr.ind_acti = '1')) OR countRegistros = 0);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_pagos%NOTFOUND;
    END LOOP;
    CLOSE c_pagos;

    -- Actualizar secuencia de registro de pago
    IF pnSecuenciaDocu > 0 THEN
      UPDATE ped_numer_solic
         SET val_ulti_nume_soli = pnSecuenciaDocu
       WHERE cod_pais = pscodigoPais
         AND val_oper = 'LEC001'
         AND val_anio = SUBSTR(psCampanaProceso, 3, 2);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_PAGO_REGUL: ' ||
                              ls_sqlerrm);

  END LEC_PR_GENER_PAGO_REGUL;

  /***************************************************************************
   Descripcion       : Generar Pago Adicional
   Fecha Creacion    : 20/03/2014
   Autor             : Henry Paredes B.
   Modificado por    : Carlos Mori (20.03.2015) - Cambio de lï¿½gica total
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_PAGO_ADICI(pscodigoPais      VARCHAR2,
                                    pscodigoPrograma  VARCHAR2,
                                    psCampanaProceso  VARCHAR2,
                                    psCampanaCobranza VARCHAR2,
                                    psCampanaBono     VARCHAR2,
                                    pscodigoTipoPago  VARCHAR2,
                                    psfechaProceso    VARCHAR2,
                                    psNumCarga        VARCHAR2,
                                    psCodigoUsuario   VARCHAR2) IS

    CURSOR c_Pagos(vnSecuencia         NUMBER,
                   vsCodTipoPago       VARCHAR2,
                   vsIndExigeCodigoSap VARCHAR2) IS
      SELECT psCodigoPais pais_cod_pais,
             psCodigoPrograma lpro_cod_prog,
             vsCodTipoPago ltpa_cod_tipo_pago,
             vnSecuencia + ROWNUM num_secu,
             hlid.cod_lide cod_lide,
             hlid.cod_regi cod_regi,
             hlid.cod_zona cod_zona,
             hlid.cod_secc cod_secc,
             TO_NUMBER(lcdm.val_dato_2, '999999999999.99') val_mont_brut,
             0 val_impu,
             0 val_mont_neto,
             SYSDATE fec_proc_pago,
             NULL fec_proc_neto,
             NULL fec_cont,
             psCampanaProceso cam_proc,
             psCampanaBono cam_refe,
             clda.cod_prov cod_sap_cons,
             SUBSTR(psCampanaProceso, 1, 4) per_cont,
             ltpa.tex_pres tex_pago,
             ltpa.val_cuen_cont num_cuen,
             ltpa.val_cent_cost cen_cost,
             '1' ind_proc_pago,
             psCodigoUsuario usu_crea,
             SYSDATE fec_crea,
             NULL usu_modi,
             NULL fec_modi,
             '1' ind_acti,
             psCampanaCobranza cam_reca,
             lcdm.num_secu num_secu_carg
        FROM lec_progr_carga_dato_masiv lcdm,
             mae_clien clie,
             mae_clien_datos_adici clda,
             lec_tipo_pago ltpa,
             (SELECT hger.gere cod_lide,
                     hger.cod_regi,
                     hger.cod_zona,
                     hger.cod_secc
                FROM zon_histo_geren hger
               WHERE hger.oid_hist_gere =
                     (SELECT MAX(x.oid_hist_gere)
                        FROM zon_histo_geren x
                       WHERE x.cod_regi IS NOT NULL
                         AND x.cod_zona IS NOT NULL
                         AND x.cod_secc IS NOT NULL
                         AND x.gere = hger.gere)) hlid
       WHERE lcdm.val_dato_1 = hlid.cod_lide
         AND lcdm.val_dato_1 = clie.cod_clie
         AND clie.oid_clie = clda.clie_oid_clie
            --
         AND lcdm.pais_cod_pais = psCodigoPais
         AND lcdm.lpro_cod_prog = psCodigoPrograma
         AND lcdm.cod_tipo_carg = pscodigoTipoPago
         AND lcdm.num_carg = psNumCarga
         AND (vsIndExigeCodigoSap = '0' OR
             (vsIndExigeCodigoSap = '1' AND clda.cod_prov IS NOT NULL))
         AND ltpa.cod_tipo_pago = vsCodTipoPago
         AND lcdm.ind_regi_erra = '0'
         AND lcdm.ind_regi_proc = '0';

    TYPE PagoRecTab IS TABLE OF c_Pagos%ROWTYPE INDEX BY BINARY_INTEGER;
    regPagos PagoRecTab;

    lnOidPeriodo        cra_perio.oid_peri%TYPE;
    lsCodTipoPago       lec_tipo_pago.cod_tipo_pago%type;
    lsPeriodoActual     seg_perio_corpo.cod_peri%type;
    psIndExigeCodigoSap VARCHAR2(1);
    psindTipoGrupoPago  VARCHAR2(1);
    pnSecuenciaDocu     NUMBER(12);
    lnCancelar          NUMBER(1) := 0;
    lnPagosNoProcesados NUMBER;
    w_filas             NUMBER(12) := 5000;

  BEGIN
    -- Obtener secuencia de registro de pago
    BEGIN
      SELECT VAL_ULTI_NUME_SOLI
        INTO pnSecuenciaDocu
        FROM PED_NUMER_SOLIC
       WHERE COD_PAIS = pscodigoPais
         AND VAL_OPER = 'LEC001'
         AND VAL_ANIO = SUBSTR(psCampanaProceso, 3, 2);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL CONTADOR DEL Nï¿½MERO DE COMPROBANTE');
    END;
    -- Obtener indicador de Tipo de Grupo Pago
    BEGIN
      SELECT VAL_PARA
        INTO psindTipoGrupoPago
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = pscodigoPais
         AND COD_SIST = 'LET'
         AND UPPER(NOM_PARA) = 'INDTIPOGRUPOPAGO'
         AND IND_ACTI = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL INDICADOR DE TIPO DE GRUPO DE PAGO (R ï¿½ Z)');
    END;
    -- Obtener Indicador de exigencia de codigo sap para pago.
    BEGIN
      SELECT VAL_PARA
        INTO psIndExigeCodigoSap
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = pscodigoPais
         AND COD_SIST = 'LEC'
         AND UPPER(NOM_PARA) = 'INDEXIGECODIGOSAP'
         AND IND_ACTI = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE VALOR PARA EL INDICADOR DE EXIGENCIA DE CODIGO SAP');
    END;
    -- Obtener el Cï¿½digo de Tipo de pago Homologado con la carga de Pago Adicional
    BEGIN
      SELECT tc.LTPA_COD_TIPO_PAGO
        INTO lsCodTipoPago
        FROM LEC_TIPO_CARGA tc
       WHERE tc.COD_TIPO_CARG = psCodigoTipoPago;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'NO EXISTE CODIGO DE PAGO PARA EL TIPO DE CARGA');
    END;
    -- Verificar si se estï¿½ cargando el mismo lote de pago adicional
    BEGIN
      SELECT COUNT(1)
        INTO lnPagosNoProcesados
        FROM lec_lider_pago_comis lcom
       WHERE lcom.pais_cod_pais = psCodigoPais
         AND lcom.lpro_cod_prog = psCodigoPrograma
         AND lcom.ltpa_cod_tipo_pago = lsCodTipoPago
         AND TO_CHAR(lcom.fec_proc_pago, 'DD/MM/YYYY') = psfechaProceso
         AND lcom.ind_proc_pago = '1';
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lnPagosNoProcesados := 0;
    END;
    -- Borrar pagos adicionales que aï¿½n no han sido procesados
    IF lnPagosNoProcesados > 0 THEN
      DELETE FROM lec_lider_pago_comis lcom
       WHERE lcom.pais_cod_pais = pscodigoPais
         AND lcom.lpro_cod_prog = pscodigoPrograma
         AND lcom.ltpa_cod_tipo_pago = lsCodTipoPago
         AND lcom.ind_proc_pago = '1'
         AND TO_CHAR(lcom.fec_proc_pago, 'DD/MM/YYYY') = psfechaProceso;
    END IF;
    -- PROCESO PRINCIPAL
    OPEN c_pagos(pnSecuenciaDocu, lsCodTipoPago, psIndExigeCodigoSap);
    LOOP
      FETCH c_pagos BULK COLLECT
        INTO regPagos LIMIT w_filas;
      IF regPagos.COUNT > 0 THEN
        FOR x IN regPagos.FIRST .. regPagos.LAST LOOP
          pnSecuenciaDocu := pnSecuenciaDocu + 1;
          -- Grabar registro procesado
          INSERT INTO lec_lider_pago_comis
            (pais_cod_pais,
             lpro_cod_prog,
             ltpa_cod_tipo_pago,
             num_secu,
             cod_lide,
             cod_regi,
             cod_zona,
             cod_secc,
             val_mont_brut,
             val_impu,
             val_mont_neto,
             fec_proc_pago,
             fec_proc_neto,
             fec_cont,
             cam_proc,
             cam_refe,
             cod_sap_cons,
             per_cont,
             tex_pago,
             num_cuen,
             cen_cost,
             ind_proc_pago,
             usu_crea,
             fec_crea,
             ind_acti,
             cam_reca,
             ltpg_num_tarj)
          VALUES
            (regPagos(x).pais_cod_pais,
             regPagos(x).lpro_cod_prog,
             regPagos(x).ltpa_cod_tipo_pago,
             regPagos(x).num_secu,
             regPagos(x).cod_lide,
             regPagos(x).cod_regi,
             regPagos(x).cod_zona,
             regPagos(x).cod_secc,
             regPagos(x).val_mont_brut,
             regPagos(x).val_impu,
             regPagos(x).val_mont_neto,
             regPagos(x).fec_proc_pago,
             regPagos(x).fec_proc_neto,
             regPagos(x).fec_cont,
             regPagos(x).cam_proc,
             regPagos(x).cam_refe,
             regPagos(x).cod_sap_cons,
             regPagos(x).per_cont,
             regPagos(x).tex_pago,
             regPagos(x).num_cuen,
             regPagos(x).cen_cost,
             regPagos(x).ind_proc_pago,
             regPagos(x).usu_crea,
             regPagos(x).fec_crea,
             regPagos(x).ind_acti,
             regPagos(x).cam_reca,
             (select tp.num_tarj from lec_tarje_lider tl, lec_tarje_pagos tp 
             where tl.ltpg_cod_tarj=tp.cod_tarj and cod_lide=regPagos(x).cod_lide and cam_bloq is null 
             and tp.LEST_COD_ESTA='03' ));
          -- Actualizar registro de cargas
          UPDATE lec_progr_carga_dato_masiv lcdm
             SET lcdm.ind_regi_proc = '1'
           WHERE lcdm.num_carg = psNumCarga
             AND lcdm.num_secu = regPagos(x).num_secu_carg;
        END LOOP;
      END IF;
      EXIT WHEN c_pagos%NOTFOUND;
    END LOOP;
    CLOSE c_pagos;
    -- Actualizar secuencia de registro de pago
    IF pnSecuenciaDocu > 0 THEN
      UPDATE ped_numer_solic
         SET val_ulti_nume_soli = pnSecuenciaDocu
       WHERE cod_pais = pscodigoPais
         AND val_oper = 'LEC001'
         AND val_anio = SUBSTR(psCampanaProceso, 3, 2);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_PAGO_ADICI: ' ||
                              ls_sqlerrm);
  END LEC_PR_GENER_PAGO_ADICI;

  /**************************************************************************
  Descripcion       : Valida que el producto exista en la Matriz de Precios

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    pnOidPais         :  oid Pais
    pnOidAcceso       :  oid Acceso
    pnOidSubAcceso    :  oid SubAcceso
    pnOidPeriodo      :  oid Periodo
    pnOidTipoOferta   :  oid Tipo Oferta
    pnOidCicloVida    :  oid Ciclo Vida
    pnOidFormaPago    :  oid Forma Pago
    pnOidFormaCobro   :  oid Forma Cobro
    pnPrecio          :  oid Precio

  Autor             : CSVD - FFVV

  ***************************************************************************/
  FUNCTION LEC_FN_VALID_PRODU(pnOidPais       NUMBER,
                              pnOidPeriodo    NUMBER,
                              pnOidTipoOferta NUMBER,
                              pnOidCicloVida  NUMBER,
                              pnOidFormaPago  NUMBER,
                              pnOidFormaCobro NUMBER,
                              psCodigoSAP     VARCHAR2,
                              pnPrecio        NUMBER) RETURN NUMBER IS
    lnOidMatriz   PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
    lnOidProducto MAE_PRODU.OID_PROD%TYPE;
    lnOidDetOfer  PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;

    lnPrecioCatalogo NUMBER;
    lnPrecioContable NUMBER;
  BEGIN
    --Se verifica que se tenga la Cabecera de Matriz
    BEGIN
      SELECT OID_CABE
        INTO lnOidMatriz
        FROM PRE_MATRI_FACTU_CABEC
       WHERE PERD_OID_PERI = pnOidPeriodo;
    EXCEPTION
      WHEN OTHERS THEN
        lnOidMatriz := NULL;
    END;

    --Se verifica que se encuentra en el Maestro de Materiales
    BEGIN
      SELECT OID_PROD
        INTO lnOidProducto
        FROM MAE_PRODU
       WHERE COD_SAP = psCodigoSAP;
    EXCEPTION
      WHEN OTHERS THEN
        lnOidProducto := NULL;
    END;

    lnOidDetOfer := NULL;

    IF ((lnOidMatriz IS NOT NULL) AND (lnOidProducto IS NOT NULL)) THEN
      FOR x IN (SELECT DET.VAL_CODI_VENT,
                       DET.OID_DETA_OFER,
                       DET.FOPA_OID_FORM_PAGO,
                       DET.IMP_PREC_CATA,
                       DET.IMP_PREC_POSI
                  FROM PRE_OFERT_DETAL DET, PRE_OFERT OFE
                 WHERE OFE.MFCA_OID_CABE = lnOidMatriz
                      /*AND OFE.ACCE_OID_ACCE = pnOidAcceso
                      AND OFE.SBAC_OID_SBAC = pnOidSubAcceso*/
                   AND OFE.OID_OFER = DET.OFER_OID_OFER
                   AND DET.PROD_OID_PROD = lnOidProducto
                   AND DET.TOFE_OID_TIPO_OFER = pnOidTipoOferta
                   AND DET.CIVI_OID_CICLO_VIDA = pnOidCicloVida) LOOP

        IF ((pnOidFormaPago IS NULL AND x.FOPA_OID_FORM_PAGO IS NULL) OR
           (pnOidFormaPago = x.FOPA_OID_FORM_PAGO)) THEN
          IF (pnOidFormaCobro = 1) THEN
            --Gratis
            IF (pnPrecio = x.IMP_PREC_POSI) THEN
              RETURN x.OID_DETA_OFER;
            END IF;
          ELSE
            IF (pnPrecio = x.IMP_PREC_CATA) THEN
              RETURN x.OID_DETA_OFER;
            END IF;
          END IF;
        END IF;

      END LOOP;

      IF (pnOidFormaCobro = 1) THEN
        --GRATIS
        lnPrecioCatalogo := 0;
        lnPrecioContable := pnPrecio;
      ELSE
        lnPrecioCatalogo := pnPrecio;
        lnPrecioContable := 0;
      END IF;

      --Si no se encontro se solicita a Matriz de precios (caso de uso 'Insertar producto en matriz')
      lnOidDetOfer := PED_PKG_CUADR_OFERT.PED_FN_INSER_OFER_MAV(lnOidProducto,
                                                                pnOidTipoOferta,
                                                                lnPrecioCatalogo,
                                                                lnPrecioContable,
                                                                pnOidFormaPago,
                                                                pnOidPeriodo);

      IF (lnOidDetOfer = 0) THEN
        --Hubo un error al crear el codigo de Venta
        lnOidDetOfer := NULL;
      END IF;
    END IF;

    RETURN lnOidDetOfer;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;

  END LEC_FN_VALID_PRODU;

  /***************************************************************************
       Descripcion       : Generar Cï¿½digo Venta Premio Canasta
       Fecha Creacion    : 21/03/2014
       Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_CODIG_VENTA_PREMI(psCodigoPais    VARCHAR2,
                                           pscodigomarca   VARCHAR2,
                                           pscodigocanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS

    vsCodProg      LEC_PROGR.COD_PROG%TYPE;
    vCodigoVenta   NUMBER(20);
    vsExitCanaMatr varchar2(1);
    vnSecCana      LEC_PROGR_CANAS_PREMI.lpcn_sec_cana%TYPE;
    vsCodSap       LEC_PROGR_CANAS_DETAL.COD_SAP%TYPE;
    vnOidTipoOfer  LEC_PROGR_CANAS_DETAL.TOFE_OID_TIPO_OFER%TYPE;
    vsCodFormPago  LEC_PROGR_CANAS_DETAL.Cod_Form_Pago%TYPE;
    vsPrecProd     LEC_PROGR_CANAS_DETAL.Val_Prec_Prod%TYPE;

    cursor cCodProg is
    /*  SELECT COD_PROG
           FROM LEC_PROGR
          WHERE psCodigoPeriodo >= CAM_INIC
            AND (CAM_FIN is null OR psCodigoPeriodo <= CAM_FIN)
            AND PAIS_COD_PAIS = psCodigoPais;  */

      SELECT lca.lpro_cod_prog
        FROM lec_lider_canas lca
       WHERE lca.cam_cana = psCodigoPeriodo
         AND ROWNUM = 1;

    cursor cCanasta(codProgr LEC_PROGR.COD_PROG%TYPE) is
      SELECT lpcn.sec_cana
        FROM lec_progr_canas lpcn
       WHERE lpcn.pais_cod_pais = psCodigoPais
         AND lpcn.lpro_cod_prog = codProgr
         AND lpcn.cam_acti = psCodigoPeriodo;

    cursor cDetalleCanasta(codProgr LEC_PROGR.COD_PROG%TYPE,
                           SecCanas LEC_PROGR_CANAS_PREMI.LPCN_SEC_CANA%TYPE) is
      SELECT lpcd.cod_sap,
             lpcd.tofe_oid_tipo_ofer,
             lpcd.cod_form_pago,
             lpcd.val_prec_prod
        FROM lec_progr_canas_detal lpcd
       WHERE lpcd.lpcn_sec_cana IN SecCanas; /* ( SELECT lpcn.sec_cana
                                        FROM lec_progr_canas lpcn
                                       WHERE lpcn.pais_cod_pais = psCodigoPais
                                         AND lpcn.lpro_cod_prog = codProgr
                                         AND lpcn.cam_acti = psCodigoPeriodo );*/

  BEGIN

    OPEN cCodProg;
    LOOP
      FETCH cCodProg
        INTO vsCodProg;
      EXIT WHEN cCodProg%NOTFOUND;
      -----------------------------------------------
      OPEN cCanasta(vsCodProg);
      LOOP
        FETCH cCanasta
          INTO vnSecCana;
        EXIT WHEN cCanasta%NOTFOUND;
        -----------------------------------------
        OPEN cDetalleCanasta(vsCodProg, vnSecCana);
        LOOP
          FETCH cDetalleCanasta
            INTO vsCodSap, vnOidTipoOfer, vsCodFormPago, vsPrecProd;
          EXIT WHEN cDetalleCanasta%NOTFOUND;

          vCodigoVenta := LEC_FN_GENER_CODIG_VENTA(psCodigoPais,
                                                   psCodigoPeriodo,
                                                   vnOidTipoOfer,
                                                   vsCodFormPago,
                                                   vsCodSap,
                                                   vsPrecProd);

          UPDATE LEC_PROGR_CANAS_DETAL
             SET VAL_CODI_VENT = vCodigoVenta,
                 USU_MODI      = psCodigoUsuario,
                 FEC_MODI      = SYSDATE
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = vsCodProg
             AND Lpcn_Sec_Cana = vnSecCana
             AND COD_SAP = vsCodSap;

          SELECT DECODE(COUNT(1), 0, 'F', 'T')
            INTO vsExitCanaMatr
            FROM LEC_PROGR_CANAS_MATRI LPCM
           WHERE LPCM.PAIS_COD_PAIS = psCodigoPais
             AND LPCM.LPRO_COD_PROG = vsCodProg
             AND LPCM.LPCN_SEC_CANA = vnSecCana
             AND LPCM.COD_SAP = vsCodSap
             AND LPCM.CAM_MATR = psCodigoPeriodo
             AND LPCM.VAL_CODI_VENT = vCodigoVenta;

          IF vsExitCanaMatr = 'T' THEN

            DELETE FROM LEC_PROGR_CANAS_MATRI LPCM
             WHERE LPCM.PAIS_COD_PAIS = psCodigoPais
               AND LPCM.LPRO_COD_PROG = vsCodProg
               AND LPCM.Lpcn_Sec_Cana = vnSecCana
               AND LPCM.COD_SAP = vsCodSap
               AND LPCM.CAM_MATR = psCodigoPeriodo
               AND LPCM.VAL_CODI_VENT = vCodigoVenta;

          END IF;

          INSERT INTO LEC_PROGR_CANAS_MATRI
            (PAIS_COD_PAIS,
             LPRO_COD_PROG,
             Lpcn_Sec_Cana,
             COD_SAP,
             CAM_MATR,
             VAL_CODI_VENT,
             OFDE_OID_DETA_OFER,
             USU_CREA,
             FEC_CREA,
             IND_ACTI)
          VALUES
            (psCodigoPais,
             vsCodProg,
             vnSecCana,
             vsCodSap,
             psCodigoPeriodo,
             vCodigoVenta,
             null,
             psCodigoUsuario,
             sysdate,
             '1');

        END LOOP;
        CLOSE cDetalleCanasta;
        --------------------------------------------
      END LOOP;
      CLOSE cCanasta;
      ------------------------------------------
    END LOOP;
    CLOSE cCodProg;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_CODIG_VENTA_PREMI: ' ||
                              ls_sqlerrm);

  END LEC_PR_GENER_CODIG_VENTA_PREMI;

  /**************************************************************************
  Descripcion       : Genera el codigo de venta.

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    psCodigoPais         :  Codigo Pais
    psCodigoPeriodo       :  Codigo Periodo(Campaï¿½a)
    pnOidTipoOferta   :  oid Tipo Oferta
    pnOidCicloVida    :  oid Ciclo Vida
    psCodigoFormaPago    :  Codigo Forma Pago
    pnOidFormaCobro   :  oid Forma Cobro
    psCodigoSap       : Codigo SAP
    pnPrecioProd          :  Precio Producto

    Autor : Yahir Rivas L.

  ***************************************************************************/
  FUNCTION LEC_FN_GENER_CODIG_VENTA(psCodigoPais      VARCHAR2,
                                    psCodigoPeriodo   VARCHAR2,
                                    pnOidTipoOferta   NUMBER,
                                    psCodigoFormaPago VARCHAR2,
                                    psCodigoSap       VARCHAR2,
                                    pnPrecioProd      NUMBER) RETURN VARCHAR2 IS

    lsCodigoVenta  PRE_OFERT_DETAL.Val_Codi_Vent%TYPE;
    lnOidDetOfer   PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;
    lnOidPeriodo   CRA_PERIO.OID_PERI%type;
    lnOidpais      seg_pais.oid_pais%TYPE;
    lnOidFormaPago bel_forma_pago.oid_form_pago%TYPE;

    not_found_codVent EXCEPTION;
  BEGIN

    lnOidPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);
    lnOidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

    SELECT fp.oid_form_pago
      into lnOidFormaPago
      FROM bel_forma_pago fp
     WHERE fp.cod_form_pago = psCodigoFormaPago
       AND fp.pais_oid_pais = lnOidpais;
    --Recuperamos el Oid Detalle Oferta asociado al Producto, para ello invocamos
    --a la funcion de Validar Productos
    lnOidDetOfer := LEC_PKG_PROCE.LEC_FN_VALID_PRODU(lnOidPais,
                                                     lnOidPeriodo,
                                                     pnOidTipoOferta,
                                                     4,
                                                     lnOidFormaPago,
                                                     1,
                                                     psCodigoSap,
                                                     pnPrecioProd);

    IF lnOidDetOfer IS NULL THEN
      RAISE not_found_codVent;
    END IF;

    IF (lnOidDetOfer IS NOT NULL) THEN
      --Obtenemos el oid Matriz Facturacion
      SELECT D.VAL_CODI_VENT
        INTO lsCodigoVenta
        FROM PRE_OFERT_DETAL       D,
             PRE_CATAL             C,
             PRE_OFERT             O,
             PRE_MATRI_FACTU_CABEC M,
             PRE_MATRI_FACTU       MF
       WHERE D.OCAT_OID_CATAL = C.OID_CATA(+)
         AND M.PERD_OID_PERI = lnOidPeriodo
         AND M.OID_CABE = O.MFCA_OID_CABE
         AND O.OID_OFER = D.OFER_OID_OFER
         AND M.OID_CABE = O.MFCA_OID_CABE
         AND MF.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
         AND MF.MFCA_OID_CABE = M.OID_CABE
         AND D.OID_DETA_OFER = lnOidDetOfer;

    end if;

    RETURN lsCodigoVenta;

  EXCEPTION
    WHEN not_found_codVent THEN
      raise_application_error(-20001,
                              'Error en la generaciï¿½n del cï¿½digo venta');

  END LEC_FN_GENER_CODIG_VENTA;
  /***********************************************************************************************
       Descripcion       : Permite generar Solicitudes Canasta Masivo.
       Fecha Creacion    : 24/03/2014
       Autor             : Yahir Rivas L.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_GENER_SOLIC_CANAS_MASIV(psCodigoPais    VARCHAR2,
                                           psCodigoMarca   VARCHAR2,
                                           psCodigoCanal   VARCHAR2,
                                           psCodigoPeriodo VARCHAR2,
                                           psTipoProceso   VARCHAR2,
                                           psCodigoRegion  VARCHAR2,
                                           psCodigoUsuario VARCHAR2) IS

    vsCodProg        LEC_PROGR.COD_PROG%TYPE;
    vsLider          ZON_HISTO_GEREN.Gere%type;
    vsCodRegi        ZON_HISTO_GEREN.COD_REGI%TYPE;
    vsCodZona        ZON_HISTO_GEREN.Cod_Zona%TYPE;
    vsCodSecc        ZON_HISTO_GEREN.Cod_Secc%TYPE;
    vsExitLiderCanas varchar2(1);

    cursor cCodProg is
    /*  SELECT COD_PROG
           FROM LEC_PROGR
          WHERE psCodigoPeriodo >= CAM_INIC
            AND (CAM_FIN is null OR psCodigoPeriodo <= CAM_FIN)
            AND IND_ACTI = '1'
            AND PAIS_COD_PAIS = psCodigoPais;  */
      SELECT lca.lpro_cod_prog
        FROM lec_lider_canas lca
       WHERE lca.cam_cana = psCodigoPeriodo
         AND ROWNUM = 1;

    CURSOR cGetLideres IS
      SELECT DISTINCT lc.cod_lide, lc.cod_regi, lc.cod_zona, lc.cod_secc
        FROM lec_lider_canas lc
       WHERE lc.cam_cana = psCodigoPeriodo
         AND lc.ind_esta_desp_cana = 'N';

  BEGIN

    OPEN cCodProg;
    LOOP
      FETCH cCodProg
        INTO vsCodProg;
      EXIT WHEN cCodProg%NOTFOUND;
      -------------------------------------------------------------------
      OPEN cGetLideres;
      LOOP
        FETCH cGetLideres
          INTO vsLider, vsCodRegi, vsCodZona, vsCodSecc;
        EXIT WHEN cGetLideres%NOTFOUND;

        select DECODE(count(1), 0, 'F', 'T')
          INTO vsExitLiderCanas
          from LEC_LIDER_CANAS LLC
         WHERE LLC.PAIS_COD_PAIS = psCodigoPais
           AND LLC.LPRO_COD_PROG = vsCodProg
           AND LLC.COD_REGI = vsCodRegi
           AND LLC.COD_ZONA = vsCodZona
           AND LLC.COD_SECC = vsCodSecc
           AND LLC.COD_LIDE = vsLider
           AND LLC.CAM_CANA = psCodigoPeriodo
           AND LLC.IND_ESTA_DESP_CANA = 'N'
           AND LLC.IND_ACTI = '1';

        IF vsExitLiderCanas = 'T' THEN
          --CU 051 Generar Solicitud Canasta Lï¿½der
          LEC_PR_GENER_SOLIC_CANAS_LIDER(psCodigoPais,
                                         pscodigomarca,
                                         pscodigocanal,
                                         psCodigoPeriodo,
                                         vsCodProg,
                                         vsCodRegi,
                                         vsCodZona,
                                         vsCodSecc,
                                         vsLider,
                                         psCodigoUsuario);
        END IF;
      END LOOP;
      CLOSE cGetLideres;
      --------------------------------------------------------
    END LOOP;
    CLOSE cCodProg;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_SOLIC_CANAS_MASIV: ' ||
                              ls_sqlerrm);

  END LEC_PR_GENER_SOLIC_CANAS_MASIV;

  /***************************************************************************
      Descripcion       : Permite Generar la Solicitud de Despacho de Canasta
                          para una lï¿½der
      Fecha Creacion    : 25/03/2014
      Autor             : Yahir Rivas L.
  ***************************************************************************/
  PROCEDURE LEC_PR_GENER_SOLIC_CANAS_LIDER(psCodigoPais     VARCHAR2,
                                           pscodigomarca    VARCHAR2,
                                           pscodigocanal    VARCHAR2,
                                           psCodigoPeriodo  VARCHAR2,
                                           psCodigoPrograma VARCHAR2,
                                           psCodigoRegion   VARCHAR2,
                                           pscodigoZona     VARCHAR2,
                                           psCodigoSeccion  VARCHAR2,
                                           psCodigoLider    VARCHAR2,
                                           psCodigoUsuario  VARCHAR2) IS

    vsCodVentFict LEC_PROGR_CANAS_DETAL.Val_Codi_Vent%TYPE;
    vsCodProg     LEC_PROGR.COD_PROG%TYPE;
    vnCantVenta   number(20) := 0;

    cursor cCanstaPendDespa is
      SELECT m.val_codi_vent, COUNT(*)
        FROM lec_lider_canas       c,
             lec_progr_canas_premi cp,
             lec_progr_canas       ca,
             lec_progr_canas_detal m
       WHERE c.pais_cod_pais = cp.pais_cod_pais
         AND c.lpro_cod_prog = cp.lpro_cod_prog
         AND c.lpcn_sec_cana = cp.lpcn_sec_cana
         AND c.lcnp_sec_cana_prem = cp.sec_cana_prem
         AND cp.pais_cod_pais = ca.pais_cod_pais
         AND cp.lpro_cod_prog = ca.lpro_cod_prog
         AND cp.lpcn_sec_cana = ca.sec_cana
         AND ca.pais_cod_pais = m.pais_cod_pais
         AND ca.lpro_cod_prog = m.lpro_cod_prog
         AND ca.sec_cana = m.lpcn_sec_cana
         AND c.lpro_cod_prog = psCodigoPrograma
         AND c.cod_regi = psCodigoRegion
         AND c.cod_zona = pscodigoZona
         AND c.cod_secc = psCodigoSeccion
         AND c.cod_lide = psCodigoLider
         AND c.cam_cana = psCodigoPeriodo
            --AND ca.ind_acti          = '1'
         AND ca.cam_acti = psCodigoPeriodo
         AND c.ind_esta_desp_cana = 'N'
       GROUP BY m.val_codi_vent;

    vnOidPais  SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal SEG_CANAL.OID_CANA%TYPE;

    vnFecFinPerio         CRA_PERIO.FEC_FINA%TYPE;
    vnOidTipoSoliPais     LET_PARAM_GENER.OID_TIPO_SOLI%TYPE;
    vnOidSolicCabec       PED_SOLIC_CABEC.OID_SOLI_CABE%type;
    vnFormaPagoEnv        PED_TIPO_SOLIC_PAIS.FOPA_OID_FORM_PAGO%TYPE;
    vnClaseSolicEnv       PED_CLASE_SOLIC.OID_CLAS_SOLI%TYPE;
    vnOidAlmacEnv         PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE;
    vnTipoSoliCons        PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE;
    vnTipoDocum2          PED_TIPO_SOLIC_PAIS.TIDO_OID_TIPO_DOCU%TYPE;
    vnSubac               PED_TIPO_SOLIC.SBAC_OID_SBAC%TYPE;
    vnSocie               PED_TIPO_SOLIC_PAIS.SOCI_OID_SOCI%TYPE;
    vnOidEjecutiva        MAE_CLIEN.OID_CLIE%TYPE;
    vnOidFormPago         MAE_CLIEN.FOPA_OID_FORM_PAGO%TYPE;
    vnOidTipoCliente      MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
    vnOidSubTipoCliente   MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT%TYPE;
    vnOidClieDire         MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
    vnOidTipoDocu         MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
    vnOidTerr             ZON_TERRI_ADMIN.TERR_OID_TERR%TYPE;
    vnOidTerrAdmi         MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE;
    vnOidValorEstrGeop    ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
    vnOidZona             ZON_ZONA.OID_ZONA%TYPE;
    vnNumSoliInicio       NUMBER;
    vnNumSoliFormato      NUMBER;
    vsCampanaSiguiente    SEG_PERIO_CORPO.COD_PERI%TYPE;
    vnOidPeriodoSiguiente SEG_PERIO_CORPO.OID_PERI%TYPE;
    vnOidProducto         MAE_PRODU.OID_PROD%type;
    vnImpPrecPosi         PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;
    vnOidDetalleOferta    PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;
    vnPosic               NUMBER;
    vnOidPeriodo          SEG_PERIO_CORPO.OID_PERI%TYPE;
    vnIndDespacho         NUMBER;
    vnoidTipoCambio       pre_matri_factu_cabec.val_tipo_camb%TYPE;

  BEGIN
    vnOidPeriodo          := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    vnOidPais             := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca            := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal            := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
    vsCampanaSiguiente    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                    vnOidPais,
                                                                    vnOidMarca,
                                                                    vnOidCanal,
                                                                    1);
    vnOidPeriodoSiguiente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(vsCampanaSiguiente,
                                                                        vnOidMarca,
                                                                        vnOidCanal);

    vnPosic := 1;

    OPEN cCanstaPendDespa;
    LOOP
      FETCH cCanstaPendDespa
        INTO vsCodVentFict, vnCantVenta;
      EXIT WHEN cCanstaPendDespa%NOTFOUND;
      IF vnPosic = 1 THEN
        --Se registra una vez la cabecera.
        SELECT TO_DATE((TO_CHAR(FEC_FINA, 'DD/MM/YYYY')), 'DD/MM/YYYY')
          INTO vnFecFinPerio
          FROM CRA_PERIO
         WHERE OID_PERI = vnOidPeriodo;

        BEGIN
          SELECT tsp.oid_tipo_soli_pais
            INTO vnOidTipoSoliPais
            FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
           WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
             AND ts.cod_tipo_soli = 'IPLC';
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnOidTipoSoliPais := 0;
        END;

        --Secciï¿½n Crea Solicitud  Cabecera
        SELECT PED_SOCA_SEQ.NEXTVAL INTO vnOidSolicCabec FROM DUAL;

        BEGIN
          SELECT a.FOPA_OID_FORM_PAGO,
                 d.OID_CLAS_SOLI,
                 a.ALMC_OID_ALMA,
                 a.TSOL_OID_TIPO_CONS,
                 a.TIDO_OID_TIPO_DOCU,
                 c.SBAC_OID_SBAC,
                 a.SOCI_OID_SOCI
            INTO vnFormaPagoEnv,
                 vnClaseSolicEnv,
                 vnOidAlmacEnv,
                 vnTipoSoliCons,
                 vnTipoDocum2,
                 vnSubac,
                 vnSocie
            FROM PED_TIPO_SOLIC_PAIS a, PED_TIPO_SOLIC c, PED_CLASE_SOLIC d
           WHERE a.OID_TIPO_SOLI_PAIS = vnOidTipoSoliPais
             AND a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
             AND c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnFormaPagoEnv  := NULL;
            vnClaseSolicEnv := NULL;
            vnOidAlmacEnv   := NULL;
            vnTipoSoliCons  := NULL;
            vnTipoDocum2    := NULL;
            vnSubac         := NULL;
            vnSocie         := NULL;
        END;

        SELECT OID_CLIE, FOPA_OID_FORM_PAGO
          INTO vnOidEjecutiva, vnOidFormPago
          FROM MAE_CLIEN
         WHERE PAIS_OID_PAIS = vnOidPais
           AND COD_CLIE = psCodigoLider;

        IF vnFormaPagoEnv IS NULL THEN
          IF vnOidFormPago IS NOT NULL THEN
            vnFormaPagoEnv := vnOidFormPago;
          ELSE
            SELECT fopa_oid_form_pago
              INTO vnFormaPagoEnv
              FROM seg_pais a
             WHERE a.oid_pais = vnOidPais;
          END IF;
        END IF;

        BEGIN
          SELECT val_tipo_camb
            INTO vnoidTipoCambio
            FROM pre_matri_factu_cabec a
           WHERE a.perd_oid_peri = vnOidPeriodo;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vnoidTipoCambio := NULL;
        END;

        SELECT TICL_OID_TIPO_CLIE, SBTI_OID_SUBT_CLIE
          INTO vnOidTipoCliente, vnOidSubTipoCliente
          FROM MAE_CLIEN_TIPO_SUBTI
         WHERE CLIE_OID_CLIE = vnOidEjecutiva
           AND IND_PPAL = 1;

        SELECT OID_CLIE_DIRE
          INTO vnOidClieDire
          FROM MAE_CLIEN_DIREC
         WHERE CLIE_OID_CLIE = vnOidEjecutiva
           AND IND_DIRE_PPAL = 1
           AND IND_ELIM = 0;

        SELECT TDOC_OID_TIPO_DOCU
          INTO vnOidTipoDocu
          FROM MAE_CLIEN_IDENT
         WHERE CLIE_OID_CLIE = vnOidEjecutiva
           AND VAL_IDEN_DOCU_PRIN = 1;

        SELECT ztad.TERR_OID_TERR,
               mcua.ZTAD_OID_TERR_ADMI,
               scc.zzon_oid_zona,
               trr.vepo_oid_valo_estr_geop
          INTO vnOidTerr, vnOidTerrAdmi, vnOidZona, vnOidValorEstrGeop
          FROM MAE_CLIEN_UNIDA_ADMIN mcua,
               ZON_TERRI_ADMIN       ztad,
               ZON_SECCI             scc,
               ZON_TERRI             trr
         WHERE ztad.PAIS_OID_PAIS = vnOidPais
           AND mcua.Ztad_Oid_Terr_Admi = ztad.oid_terr_admi
           AND mcua.CLIE_OID_CLIE = vnOidEjecutiva
           AND mcua.IND_ACTI = 1
           AND ztad.zscc_oid_secc = scc.oid_secc
           AND ztad.terr_oid_terr = trr.oid_terr;

        vnNumSoliInicio  := LET_PKG_PROCE.LET_FN_RESRV_SECUE_NSOLI(psCodigoPais,
                                                                   'PED001',
                                                                   'GZ',
                                                                   '000',
                                                                   psCodigoCanal,
                                                                   1);
        vnNumSoliFormato := TO_CHAR(SYSDATE, 'yy') ||
                            LPAD(vnNumSoliInicio + 1, 8, '0');

        INSERT INTO PED_SOLIC_CABEC
          (OID_SOLI_CABE,
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
           SBAC_OID_SBAC,
           TERR_OID_TERR,
           ZZON_OID_ZONA,
           VAL_NUME_SOLI,
           FEC_CRON,
           IND_PERM_UNIO_SOL,
           NUM_DOCU_ORIG,
           IND_TS_NO_CONSO,
           PAIS_OID_PAIS,
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
           ICTP_OID_TIPO_PROG,
           VAL_TIPO_CAMB,
           IND_OC)
        VALUES
          (vnOidSolicCabec,
           vnFecFinPerio,
           vnOidTipoSoliPais,
           1,
           vnOidAlmacEnv,
           1,
           vnOidTipoCliente,
           vnOidPeriodo,
           vnOidEjecutiva,
           vnOidEjecutiva,
           vnOidEjecutiva,
           vnOidEjecutiva,
           vnOidClieDire,
           vnOidTipoDocu,
           vnSocie,
           vnSubac,
           vnOidTerr,
           vnOidZona,
           vnNumSoliFormato,
           TO_DATE((TO_CHAR(SYSDATE, 'dd/MM/yyyy')), 'dd/MM/yyyy'),
           1,
           NULL,
           1,
           vnOidPais,
           30,
           vnOidValorEstrGeop,
           1,
           NULL,
           3,
           vnOidSubTipoCliente,
           vnTipoSoliCons,
           vnFormaPagoEnv,
           vnClaseSolicEnv,
           vnOidTerrAdmi,
           21,
           1,
           NULL,
           NULL,
           vnoidTipoCambio,
           0);

      END IF;

      SELECT ofedet.oid_deta_ofer, ofedet.imp_prec_posi, prod.oid_prod
        INTO vnOidDetalleOferta, vnImpPrecPosi, vnOidProducto
        FROM pre_ofert             ofe,
             pre_ofert_detal       ofedet,
             pre_matri_factu       mf,
             pre_matri_factu_cabec mfc,
             mae_produ             prod
       WHERE mfc.perd_oid_peri = vnOidPeriodo
         AND mf.mfca_oid_cabe = mfc.oid_cabe
         AND ofe.mfca_oid_cabe = mfc.oid_cabe
         AND ofe.oid_ofer = ofedet.ofer_oid_ofer
         AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
         AND ofedet.val_codi_vent in (vsCodVentFict) --codigos de venta de Parametrï¿½a
         AND ofedet.prod_oid_prod = prod.oid_prod;

      INSERT INTO PED_SOLIC_POSIC
        (OID_SOLI_POSI,
         COD_POSI,
         NUM_UNID_DEMA,
         NUM_UNID_POR_ATEN,
         NUM_UNID_COMPR,
         NUM_UNID_DEMA_REAL,
         VAL_TASA_IMPU,
         SOCA_OID_SOLI_CABE,
         TPOS_OID_TIPO_POSI,
         PROD_OID_PROD,
         VAL_PREC_CATA_UNIT_LOCA,
         VAL_PREC_CONT_UNIT_LOCA,
         VAL_PREC_CATA_UNIT_DOCU,
         VAL_PREC_CONTA_UNIT_DOCU,
         ESPO_OID_ESTA_POSI,
         STPO_OID_SUBT_POSI,
         VAL_CODI_VENT,
         OFDE_OID_DETA_OFER)
      VALUES
        (PED_SOPO_SEQ.NEXTVAL,
         vnPosic,
         vnCantVenta,
         vnCantVenta,
         vnCantVenta,
         vnCantVenta,
         0,
         vnOidSolicCabec,
         9,
         vnOidProducto,
         0,
         vnImpPrecPosi,
         0,
         vnImpPrecPosi,
         4,
         13,
         vsCodVentFict,
         vnOidDetalleOferta);

      vnPosic := vnPosic + 1;

    END LOOP;
    CLOSE cCanstaPendDespa;

    SELECT COUNT(*)
      INTO vnIndDespacho
      FROM ped_solic_cabec sc
     WHERE sc.tspa_oid_tipo_soli_pais = vnOidTipoSoliPais
       AND sc.clie_oid_clie = vnOidEjecutiva
       AND sc.perd_oid_peri = vnOidPeriodo;

    IF vnIndDespacho > 0 THEN

      --actualizando lider canasta
      UPDATE lec_lider_canas c
         SET c.soca_oid_soli_cabe = vnOidSolicCabec,
             c.ind_esta_desp_cana = 'S',
             c.usu_modi           = psCodigoUsuario,
             c.fec_modi           = SYSDATE
       WHERE c.pais_cod_pais = psCodigoPais
         AND c.lpro_cod_prog = psCodigoPrograma
         AND c.cod_regi = psCodigoRegion
         AND c.cod_zona = pscodigoZona
         AND c.cod_secc = psCodigoSeccion
         AND c.cod_lide = psCodigoLider
         AND c.cam_cana = psCodigoPeriodo
         AND c.ind_acti = '1'
         AND c.ind_esta_desp_cana = 'N';

    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_SOLIC_CANAS_LIDER: ' ||
                              ls_sqlerrm);

  END LEC_PR_GENER_SOLIC_CANAS_LIDER;

  /**************************************************************************
    Descripcion       : Obtiene la venta de una secciï¿½n

    Fecha Creacion    : 04/07/2014
    Parametros Entrada:
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider

    Parametros Salida:
        Retorna la venta de la secciï¿½n

    Autor : Ivan Tocto Jaimes

  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_VENT_SECC(psCampanaProceso     VARCHAR2,
                                 pnOidCampanyaProceso NUMBER,
                                 psCodigoRegion       VARCHAR2,
                                 psCodigoZona         VARCHAR2,
                                 psCodigoSeccion      VARCHAR2) RETURN NUMBER IS

    nVenta NUMBER;

  BEGIN

    SELECT ROUND(SUM(NVL(ACC.VAL_MONT_TOTA, 0)))
      INTO nVenta
      FROM PED_SOLIC_CABEC_ACUM2 ACC
     WHERE ACC.PERD_OID_PERI = pnOidCampanyaProceso
       AND ACC.CLIE_OID_CLIE NOT IN
           (SELECT (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = COD_CONS)
              FROM LEC_PROGR_LISTA_EXCLU
             WHERE psCampanaProceso >= CAM_INIC_VIGE
               AND (psCampanaProceso <= CAM_FIN_VIGE OR CAM_FIN_VIGE IS NULL))
       AND ACC.CLIE_OID_CLIE IN
           (SELECT CUA.CLIE_OID_CLIE
              FROM MAE_CLIEN_UNIDA_ADMIN CUA,
                   ZON_TERRI_ADMIN       ZTA,
                   ZON_SECCI             ZSE,
                   ZON_ZONA              ZZO,
                   ZON_REGIO             ZRE
             WHERE CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
               AND ZTA.ZSCC_OID_SECC = ZSE.OID_SECC
               AND ZSE.ZZON_OID_ZONA = ZZO.OID_ZONA
               AND ZZO.ZORG_OID_REGI = ZRE.OID_REGI
               AND pnOidCampanyaProceso >= CUA.PERD_OID_PERI_INI
               AND (pnOidCampanyaProceso <= CUA.PERD_OID_PERI_FIN OR
                   CUA.PERD_OID_PERI_FIN IS NULL)
               AND ZSE.COD_SECC = psCodigoSeccion
               AND ZZO.COD_ZONA = psCodigoZona
               AND ZRE.COD_REGI = psCodigoRegion);

    RETURN nVenta;

  END LEC_FN_OBTE_VENT_SECC;

  /**************************************************************************
    Descripcion       : Obtiene el nivel de venta en base al valor de la venta

    Fecha Creacion    : 08/07/2014
    Parametros Entrada:
        psCodigoPais : Cï¿½digo del pais
        psCodigoPrograma : Cï¿½digo del programa
        pnVenta   :  Valor de la venta
        psCodNivelExitPedido    : Codigo de NivelExitPedido
    Parametros Salida:
        Retorna el nivel

    Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_NIVE_VENT(psCodigoPais         VARCHAR2,
                                 psCodigoPrograma     VARCHAR2,
                                 pnVenta              NUMBER,
                                 psCodNivelExitPedido VARCHAR2)
    RETURN VARCHAR2 IS
    vsCodigoNivel VARCHAR2(2);
    lnCount       NUMBER;
  BEGIN

    IF pnVenta > 0 THEN
      BEGIN
        SELECT LNIV_COD_NIVE
          INTO vsCodigoNivel
          FROM LEC_PROGR_NIVEL
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodigoPrograma
           AND ((pnVenta >= MON_MINI_VENT_CATA AND
               pnVenta <= MON_MAXI_VENT_CATA AND MON_MINI_VENT_CATA > 0 AND
               MON_MAXI_VENT_CATA > 0) OR
               (pnVenta <= MON_MAXI_VENT_CATA AND MON_MINI_VENT_CATA = 0))
           AND IND_ACTI = 1;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          vsCodigoNivel := '00';
      END;
    ELSE
      vsCodigoNivel := '00';
    END IF;
    IF vsCodigoNivel = '00' THEN
      SELECT COUNT(1)
        INTO lnCount
        FROM LEC_PROGR_NIVEL
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = psCodigoPrograma
         AND MON_MINI_VENT_CATA = 0
         AND MON_MAXI_VENT_CATA = 0
         AND IND_ACTI = 1;

      IF lnCount > 0 THEN
        ----vsCodigoNivel := psCodNivelExitPedido;
        BEGIN
          SELECT PN.LNIV_COD_NIVE
            INTO vsCodigoNivel
            FROM LEC_PROGR_NIVEL PN, LEC_NIVEL NI
           WHERE PN.PAIS_COD_PAIS = psCodigoPais
             AND PN.LPRO_COD_PROG = psCodigoPrograma
             AND PN.LNIV_COD_NIVE = NI.COD_NIVE
             AND NI.VAL_PESO_NIVE =
                 (SELECT MAX(NI.VAL_PESO_NIVE)
                    FROM LEC_PROGR_NIVEL PN, LEC_NIVEL NI
                   WHERE PN.PAIS_COD_PAIS = psCodigoPais
                     AND PN.LPRO_COD_PROG = psCodigoPrograma
                     AND PN.MON_MINI_VENT_CATA = 0
                     AND PN.MON_MAXI_VENT_CATA = 0
                     AND PN.IND_ACTI = 1
                     AND PN.LNIV_COD_NIVE = NI.COD_NIVE
                     AND NI.IND_ACTI = 1)
             AND PN.IND_ACTI = 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            vsCodigoNivel := '00';
        END;
      ELSE
        vsCodigoNivel := '00';
      END IF;

    END IF;

    RETURN vsCodigoNivel;

  END LEC_FN_OBTE_NIVE_VENT;

  /**************************************************************************
    Descripcion       : Obtiene la venta de retail

    Fecha Creacion    : 05/08/2014
    Parametros Entrada:
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider

    Parametros Salida:
        Retorna la venta de la secciï¿½n

    Autor : Ivan Tocto Jaimes

  ***************************************************************************/
  FUNCTION LEC_FN_OBTE_VENT_RTAL(psCampanaProceso     VARCHAR2,
                                 pnOidCampanyaProceso NUMBER,
                                 psCodigoRegion       VARCHAR2,
                                 psCodigoZona         VARCHAR2,
                                 psCodigoSeccion      VARCHAR2) RETURN NUMBER IS

    nVenta NUMBER;

  BEGIN

    SELECT ROUND(SUM(NVL(RD.VAL_MONT_CATA * RD.Uni_Vend, 0)))
      INTO nVenta
      FROM RET_VENTA_CABEC RT, RET_VENTA_DETAL RD
     WHERE RT.COD_PAIS = RD.COD_PAIS
       AND RT.COD_SBAC = RD.COD_SBAC
       AND RT.COD_TIPO_DOCU = RD.COD_TIPO_DOCU
       AND RT.NUM_DOCU_RETA = RD.NUM_DOCU_RETA
       AND RT.FEC_ENVI = RD.FEC_ENVI
       AND RT.COD_TIPO_DOCU = 'F'
       AND RT.CAM_PROC = psCampanaProceso
       AND (RT.IND_ANUL <> 'A' OR RT.IND_ANUL IS NULL)
       AND RT.TIP_CLIE = 'CO'
       AND RT.VAL_CUEN_CONSU NOT IN
           (SELECT COD_CONS
              FROM LEC_PROGR_LISTA_EXCLU
             WHERE psCampanaProceso >= CAM_INIC_VIGE
               AND (psCampanaProceso <= CAM_FIN_VIGE or CAM_FIN_VIGE is null))
       AND RT.VAL_CUEN_CONSU IN
           (SELECT (SELECT MA.COD_CLIE
                      FROM MAE_CLIEN MA
                     WHERE MA.OID_CLIE = CUA.CLIE_OID_CLIE)
              FROM MAE_CLIEN_UNIDA_ADMIN CUA,
                   ZON_TERRI_ADMIN       ZTA,
                   ZON_SECCI             ZSE,
                   ZON_ZONA              ZZO,
                   ZON_REGIO             ZRE
             WHERE CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
               AND ZTA.ZSCC_OID_SECC = ZSE.OID_SECC
               AND ZSE.ZZON_OID_ZONA = ZZO.OID_ZONA
               AND ZZO.ZORG_OID_REGI = ZRE.OID_REGI
               AND pnOidCampanyaProceso >= CUA.PERD_OID_PERI_INI
               AND (pnOidCampanyaProceso <= CUA.PERD_OID_PERI_FIN OR
                   CUA.PERD_OID_PERI_FIN IS NULL)
               AND ZSE.COD_SECC = psCodigoSeccion
               AND ZZO.COD_ZONA = psCodigoZona
               AND ZRE.COD_REGI = psCodigoRegion);

    RETURN nVenta;

  END LEC_FN_OBTE_VENT_RTAL;

  /**************************************************************************
    Descripcion       : Obtiene la venta de retail

    Fecha Creacion    : 07/08/2014
    Parametros Entrada:
        psCodigoPais: Codigo del pais
        psCodigoPrograma: Cï¿½digo de programa,
        psCodigoLider: Cï¿½digo de lider
        psCampanaProceso    : Campaï¿½a de proceso
        pnOidCampanyaProceso   :  oid de la campaï¿½a de proceso
        psCodigoRegion     : Codigo de Region de la Lider
        psCodigoZona       : Codigo de Zona de la Lider
        psCodigoSeccion    : Codigo de Seccion de la Lider
        psCodigoUsuario: Usuario que invoca el proceso

    Parametros Salida:
        Retorna la venta de la secciï¿½n

    Autor : Carlos Bazalar

  ***************************************************************************/
  FUNCTION LEC_FN_OBTIE_VENTA_RETAI(psCodigoPais         VARCHAR2,
                                    psCodigoPrograma     VARCHAR2,
                                    psCodigoLider        VARCHAR2,
                                    psCampanaProceso     VARCHAR2,
                                    pnOidCampanyaProceso NUMBER,
                                    psCodigoRegion       VARCHAR2,
                                    psCodigoZona         VARCHAR2,
                                    psCodigoSeccion      VARCHAR2,
                                    psCodigoUsuario      VARCHAR2)
    RETURN NUMBER IS

    CURSOR cVentasRetail IS
      SELECT RT.COD_TIPO_DOCU,
             RD.TIPO_TRAN_RET,
             RT.VAL_CUEN_CONSU,
             RD.VAL_MONT_DSCT,
             RD.MON_IMPU,
             RD.MON_DEVU
        FROM RET_VENTA_CABEC RT, RET_VENTA_DETAL RD
       WHERE RT.COD_PAIS = RD.COD_PAIS
         AND RT.COD_SBAC = RD.COD_SBAC
         AND RT.COD_TIPO_DOCU = RD.COD_TIPO_DOCU
         AND RT.NUM_DOCU_RETA = RD.NUM_DOCU_RETA
         AND RT.FEC_ENVI = RD.FEC_ENVI
         AND RT.CAM_PROC = psCampanaProceso
         AND (RT.IND_ANUL <> 'A' OR RT.IND_ANUL IS NULL)
         AND RT.TIP_CLIE = 'CO'
         AND RT.VAL_CUEN_CONSU NOT IN
             (SELECT COD_CONS
                FROM LEC_PROGR_LISTA_EXCLU
               WHERE psCampanaProceso >= CAM_INIC_VIGE
                 AND (psCampanaProceso <= CAM_FIN_VIGE or
                     CAM_FIN_VIGE is null))
         AND RT.VAL_CUEN_CONSU IN
             (SELECT (SELECT MA.COD_CLIE
                        FROM MAE_CLIEN MA
                       WHERE MA.OID_CLIE = CUA.CLIE_OID_CLIE)
                FROM MAE_CLIEN_UNIDA_ADMIN CUA,
                     ZON_TERRI_ADMIN       ZTA,
                     ZON_SECCI             ZSE,
                     ZON_ZONA              ZZO,
                     ZON_REGIO             ZRE
               WHERE CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                 AND ZTA.ZSCC_OID_SECC = ZSE.OID_SECC
                 AND ZSE.ZZON_OID_ZONA = ZZO.OID_ZONA
                 AND ZZO.ZORG_OID_REGI = ZRE.OID_REGI
                 AND pnOidCampanyaProceso >= CUA.PERD_OID_PERI_INI
                 AND (pnOidCampanyaProceso <= CUA.PERD_OID_PERI_FIN OR
                     CUA.PERD_OID_PERI_FIN IS NULL)
                 AND ZSE.COD_SECC = psCodigoSeccion
                 AND ZZO.COD_ZONA = psCodigoZona
                 AND ZRE.COD_REGI = psCodigoRegion);

    lsCodTipoDocu  RET_VENTA_CABEC.COD_TIPO_DOCU%TYPE;
    lsTipoTranRet  RET_VENTA_DETAL.TIPO_TRAN_RET%TYPE;
    lsValCuenConsu RET_VENTA_CABEC.VAL_CUEN_CONSU%TYPE;
    lnValMontDsct  RET_VENTA_DETAL.VAL_MONT_DSCT%TYPE;
    lnMonImpu      RET_VENTA_DETAL.MON_IMPU%TYPE;
    lnMonDevu      RET_VENTA_DETAL.MON_DEVU%TYPE;

    lnCount           NUMBER;
    lnValMontRtal     LEC_LIDER_SECCI_DETAL_RECUP.VAL_MONT_RTAL%TYPE;
    lnVentaRetail     LEC_LIDER_SECCI_DETAL_RECUP.VAL_MONT_RTAL%TYPE;
    vnOidCampAnterior NUMBER;
    vnStateClient     NUMBER;
    vnOidCliente      NUMBER;
    vsIndPediCons     LEC_LIDER_SECCI_DETAL_RECUP.IND_PEDI_CONS%TYPE;

  BEGIN
    lnValMontRtal     := 0;
    lnVentaRetail     := 0;
    vnOidCampAnterior := gen_pkg_gener.gen_fn_devuelve_id_nante_campa(pnOidCampanyaProceso,
                                                                      1);

    OPEN cVentasRetail;
    LOOP
      FETCH cVentasRetail
        INTO lsCodTipoDocu,
             lsTipoTranRet,
             lsValCuenConsu,
             lnValMontDsct,
             lnMonImpu,
             lnMonDevu;
      EXIT WHEN cVentasRetail%NOTFOUND;

      --Buscamos los datos en la tabla de DetalleLideresRecuperacion
      vnOidCliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(lsValCuenConsu);
      SELECT COUNT(1)
        into vnStateClient
        FROM MAE_CLIEN_HISTO_ESTAT HE, MAE_ESTAT_CLIEN ME
       WHERE HE.CLIE_OID_CLIE = vnOidCliente
         AND pnOidCampanyaProceso >= perd_oid_peri
         AND (pnOidCampanyaProceso <= perd_oid_peri_peri_fin OR
             perd_oid_peri_peri_fin IS NULL)
         AND HE.ESTA_OID_ESTA_CLIE = ME.OID_ESTA_CLIE
         AND ME.COD_ESTA_CLIE IN ('01', '02', '03', '07', '08')
         AND NOT EXISTS
       (SELECT NULL
                FROM MAE_CLIEN_HISTO_ESTAT HE, MAE_ESTAT_CLIEN ME
               WHERE HE.CLIE_OID_CLIE = vnOidCliente
                 AND vnOidCampAnterior >= perd_oid_peri
                 AND (vnOidCampAnterior <= perd_oid_peri_peri_fin OR
                     perd_oid_peri_peri_fin IS NULL)
                 AND HE.ESTA_OID_ESTA_CLIE = ME.OID_ESTA_CLIE
                 AND ME.COD_ESTA_CLIE = '04');

      IF (vnStateClient > 0) THEN
        vsIndPediCons := '1';
      ELSE
        vsIndPediCons := '0';
      END IF;
      SELECT COUNT(1)
        INTO lnCount
        FROM LEC_LIDER_SECCI_DETAL_RECUP DR
       WHERE DR.PAIS_COD_PAIS = psCodigoPais
         AND DR.LPRO_COD_PROG = psCodigoPrograma
         AND DR.CAM_RECU = psCampanaProceso
         AND DR.COD_REGI = psCodigoRegion
         AND DR.COD_ZONA = psCodigoZona
         AND DR.COD_SECC = psCodigoSeccion
         AND DR.COD_LIDE = psCodigoLider
         AND DR.COD_CONS = lsValCuenConsu
         AND DR.MVCC_OID_MOVI_CC = 0;

      IF lnCount = 0 THEN

        IF lsCodTipoDocu = 'F' THEN
          lnValMontRtal := lnValMontDsct - lnMonImpu;
          lnVentaRetail := lnVentaRetail + (lnValMontDsct - lnMonImpu);
        ELSE
          IF (lsCodTipoDocu = 'N' AND
             (lsTipoTranRet = 'RR' OR lsTipoTranRet = 'RD')) THEN
            lnValMontRtal := (lnMonDevu - lnMonImpu) * -1;
            lnVentaRetail := lnVentaRetail - (lnMonDevu - lnMonImpu);
          END IF;
        END IF;

        -- insertar registro
        INSERT INTO LEC_LIDER_SECCI_DETAL_RECUP
          (PAIS_COD_PAIS,
           LPRO_COD_PROG,
           COD_REGI,
           COD_ZONA,
           COD_SECC,
           COD_LIDE,
           CAM_RECU,
           COD_CONS,
           MVCC_OID_MOVI_CC,
           VAL_MONT_CARG_TOTA,
           VAL_MONT_RTAL,
           IND_PEDI_CONS,
           USU_CREA,
           FEC_CREA,
           IND_ACTI)
        VALUES
          (psCodigoPais,
           psCodigoPrograma,
           psCodigoRegion,
           psCodigoZona,
           psCodigoSeccion,
           psCodigoLider,
           psCampanaProceso,
           lsValCuenConsu,
           0,
           0,
           lnValMontRtal,
           vsIndPediCons,
           psCodigoUsuario,
           SYSDATE,
           '1');
        -- --
      ELSE

        IF lsCodTipoDocu = 'F' THEN
          lnValMontRtal := lnValMontRtal + (lnValMontDsct - lnMonImpu);
          lnVentaRetail := lnVentaRetail + (lnValMontDsct - lnMonImpu);
        ELSE
          IF (lsCodTipoDocu = 'N' AND
             (lsTipoTranRet = 'RR' OR lsTipoTranRet = 'RD')) THEN
            lnValMontRtal := lnValMontRtal - (lnMonDevu - lnMonImpu);
            lnVentaRetail := lnVentaRetail - (lnMonDevu - lnMonImpu);
          END IF;
        END IF;

        -- Actualizar
        UPDATE LEC_LIDER_SECCI_DETAL_RECUP
           SET VAL_MONT_CARG_TOTA = 0,
               VAL_MONT_RTAL      = lnValMontRtal,
               USU_MODI           = psCodigoUsuario,
               FEC_MODI           = SYSDATE
         WHERE PAIS_COD_PAIS = psCodigoPais
           AND LPRO_COD_PROG = psCodigoPrograma
           AND COD_REGI = psCodigoRegion
           AND COD_ZONA = psCodigoZona
           AND COD_SECC = psCodigoSeccion
           AND COD_LIDE = psCodigoLider
           AND CAM_RECU = psCampanaProceso
           AND COD_CONS = lsValCuenConsu
           AND MVCC_OID_MOVI_CC = 0;
        -- --
      END IF;

    END LOOP;
    CLOSE cVentasRetail;

    RETURN lnVentaRetail;

  END LEC_FN_OBTIE_VENTA_RETAI;

  /**************************************************************************
    Descripcion       : Realiza la Eliminacion de un Programa LEC

    Fecha Creacion    : 13/08/2014
    Parametros Entrada:
        psCodigoPais       : Codigo de Pais
        psCodigoPrograma   : Codigo de Programa
    Autor : Carlos Bazalar

  ***************************************************************************/
  PROCEDURE LEC_PR_DELET_PROGR(psCodigoPais     VARCHAR2,
                               psCodigoPrograma VARCHAR2) IS
  BEGIN

    DELETE FROM LEC_PROGR_RANKI_NIVEL
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_RANKI
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_canas_premi
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_canas_detal
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_canas
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_etapa_campa
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_desem
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_nivel_tramo
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_bono_nivel
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_campa_exige
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_bono_lanza_produ
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_bono_lanza
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM lec_progr_bono_campa
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_COBRA_OBJET_TRAMO
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_COBRA_OBJET
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_AMBIT_GEOGR
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_COBRA_TRAMO
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_NIVEL
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR_BONO
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND LPRO_COD_PROG = psCodigoPrograma;

    DELETE FROM LEC_PROGR
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND COD_PROG = psCodigoPrograma;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_DELET_PROGR: ' || ls_sqlerrm);
  END LEC_PR_DELET_PROGR;

  /***********************************************************************************************
  Descripcion       : Proceso Actualizar Indicadores LET
  Fecha Creacion    : 16/09/2014
  Autor             : Ivan Tocto.
  ***********************************************************************************************/
  PROCEDURE LEC_PR_ACTUA_INDIC_ACTIV(psCodigoPais     VARCHAR2,
                                     psCodigoMarca    VARCHAR2,
                                     psCodigoCanal    VARCHAR2,
                                     psCampanaProceso VARCHAR2,
                                     psCodigoUsuario  VARCHAR2) IS

    lsCodPeriAnterior SEG_PERIO_CORPO.COD_PERI%TYPE;
    lnIdPais          SEG_PAIS.OID_PAIS%TYPE;
    lnIdCanal         SEG_CANAL.OID_CANA%TYPE;
    lnIdMarca         SEG_MARCA.OID_MARC%TYPE;

  BEGIN

    lnIdPais          := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
    lnIdCanal         := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);
    lnIdMarca         := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);
    lsCodPeriAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCampanaProceso,
                                                                lnIdPais,
                                                                lnIdMarca,
                                                                lnIdCanal,
                                                                -1);

    update LEC_PROGR_CANAS LPC
       set lpc.ind_acti = '0',
           LPC.USU_MODI = psCodigoUsuario,
           lpc.fec_modi = sysdate
     where lpc.cam_regi = lsCodPeriAnterior
       and lpc.pais_cod_pais = psCodigoPais;

    update LEC_PROGR_CANAS_DETAL det
       set det.ind_acti = '0',
           det.usu_modi = psCodigoUsuario,
           det.fec_modi = sysdate
     where (det.pais_cod_pais, det.lpro_cod_prog, det.lpcn_sec_cana) in
           (select lpc.pais_cod_pais, lpc.lpro_cod_prog, lpc.sec_cana
              from LEC_PROGR_CANAS LPC
             where lpc.cam_regi = lsCodPeriAnterior
               and lpc.pais_cod_pais = psCodigoPais);

    update LEC_PROGR_CANAS_PREMI LPCR
       set lpcr.ind_acti = '0',
           lpcr.usu_modi = psCodigoUsuario,
           lpcr.fec_modi = sysdate
     where (lpcr.pais_cod_pais, lpcr.lpro_cod_prog, lpcr.lpcn_sec_cana) in
           (select det.pais_cod_pais, det.lpro_cod_prog, det.lpcn_sec_cana
              from LEC_PROGR_CANAS_DETAL det
             where (det.pais_cod_pais, det.lpro_cod_prog, det.lpcn_sec_cana) in
                   (select lpc.pais_cod_pais, lpc.lpro_cod_prog, lpc.sec_cana
                      from LEC_PROGR_CANAS LPC
                     where lpc.cam_regi = lsCodPeriAnterior
                       and lpc.pais_cod_pais = psCodigoPais));

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_ACTUA_INDIC_ACTIV: ' ||
                              ls_sqlerrm);
  END LEC_PR_ACTUA_INDIC_ACTIV;

  /***********************************************************************************************
  Descripcion       : Obtiene la campaï¿½a de recuperaciï¿½n
  Fecha Creacion    : 29/10/2014
  Autor             : Ivan Tocto.
  Parametros        : psCodigoPais,psCampanyaProceso, lsCampanyaAnterior, lsCamp, lsGrupo, DR.COD_REGI,'R'
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_CAMPA_RECU(psCodigoPrograma    VARCHAR2,
                                  psCampanyaProceso   VARCHAR2,
                                  psCampanyaAnterior  VARCHAR2,
                                  psCampanyaSiguiente VARCHAR2,
                                  psFechaFactura      DATE,
                                  psFechaCierre       DATE,
                                  psIndicaCamp        VARCHAR2,
                                  PsTipoCampanya      VARCHAR2)
    RETURN VARCHAR2 IS
    lsCampRecu      VARCHAR2(6);
    lsCampBono      VARCHAR2(6);
    lsNumDiasExtras LEC_PROGR_COBRA_OBJET.NUM_DIAS_EXTR%TYPE;
    lsFechaCierre   DATE;

  BEGIN
    -- Inicializar valores de campaï¿½a de bono y recaudo
    lsCampRecu := psCampanyaAnterior;
    lsCampBono := psCampanyaProceso;

    IF (NVL(psIndicaCamp, '0') = '1') THEN
      IF psFechaCierre IS NOT NULL THEN
        --Obtener los dias extras
        BEGIN
          SELECT NUM_DIAS_EXTR
            INTO lsNumDiasExtras
            FROM LEC_PROGR_COBRA_OBJET
           WHERE LPRO_COD_PROG = psCodigoPrograma
             AND IND_ACTI = 1
             AND ROWNUM = 1;
          IF lsNumDiasExtras IS NULL THEN
            lsNumDiasExtras := 0;
          END IF;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            lsNumDiasExtras := 0;
        END;
        lsFechaCierre := psFechaCierre + lsNumDiasExtras;
        IF lsFechaCierre < psFechaFactura THEN
          lsCampRecu := psCampanyaProceso;
          lsCampBono := psCampanyaSiguiente;
        END IF;
      END IF;
    END IF;

    IF PsTipoCampanya = 'R' THEN
      RETURN lsCampRecu;
    ELSIF PsTipoCampanya = 'B' THEN
      RETURN lsCampBono;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;

  END LEC_FN_OBTE_CAMPA_RECU;

  /***********************************************************************************************
  Descripcion       : Proceso de Generacion de datos para el reporte de Proyeccion
  Fecha Creacion    : 23/10/2014
  Autor             : Ivan Tocto.
  ***********************************************************************************************/
   PROCEDURE LEC_PR_GENER_REPOR_PROYE(psCodigoPais      VARCHAR2,
                                        psCampanyaProceso VARCHAR2,
                                        psCondicionTramos VARCHAR2,
                                        psCodigoUsuario VARCHAR2) IS

        lsFlagRegionesZonas VARCHAR2(1);
        lsGrupo BAS_PARAM_PAIS.VAL_PARA%TYPE;
        lsCamp BAS_PARAM_PAIS.VAL_PARA%TYPE;
        ---
        lnoidtiposolisoc ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
        vnOidCampAux  cra_perio.oid_peri%type;
        dsFecFact     ped_solic_cabec.fec_fact%type;
        lsCampanyaAnterior VARCHAR2(6);
        lsCampanyaSiguiente VARCHAR2(6);

    leoEstaObje         LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;
    leobEstaObjeIngr    LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE_INGR%TYPE;
    leobEstaObjeCapi    LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE_CAPI%TYPE;

    BEGIN

        --Verificamos si existen zonas/Regiones
        SELECT DECODE(COUNT(*), 0, '0', '1')
        INTO lsFlagRegionesZonas
      FROM MAE_GTT_REZOC
     WHERE VAL_TIPO = 'R';

        DELETE REP_LEC_PROYE_PPREG WHERE COD_USUA = psCodigoUsuario;
        DELETE REP_LEC_PROYE WHERE COD_USUA = psCodigoUsuario;
    DELETE REP_LEC_PROYE_DETAL WHERE COD_USUA = psCodigoUsuario;
    DELETE REP_LEC_PROYE_DETAL WHERE COD_USUA IS NULL;

        --Obtenemos la parametria
        BEGIN
      SELECT VAL_PARA
        INTO lsGrupo
            FROM BAS_PARAM_PAIS
            WHERE COD_PAIS = psCodigoPais
            AND COD_SIST = 'LET'
            AND UPPER(nom_para) = 'INDTIPOGRUPOPAGO'
            AND IND_ACTI = '1';
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
              lsGrupo := '0';
        END;

        BEGIN
      SELECT VAL_PARA
        INTO lsCamp
            FROM BAS_PARAM_PAIS
            WHERE COD_PAIS = psCodigoPais
            AND COD_SIST = 'LET'
            AND UPPER(nom_para) = 'INDCAMPAANTE'
            AND IND_ACTI = '1';
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
              lsCamp := '0';
        END;

--Obtener Periodo Anterior o Siguiente
    lsCampanyaAnterior  := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCampanyaProceso,
                                                            -TO_NUMBER(lsCamp));
    lsCampanyaSiguiente := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCampanyaProceso,
                                                            1);

-- Obtener Tipo Solicitud
      SELECT tsp.oid_tipo_soli_pais
      INTO lnoidtiposolisoc
      FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND ts.cod_tipo_soli = 'SOC';
--Oid de la Campaña Proceso
     vnOidCampAux := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO (psCampanyaProceso);
--fecha Max Facturacion
    SELECT MAX(fec_fact)
      into dsFecFact
     FROM   PED_SOLIC_CABEC PSC
     WHERE  PSC.FEC_FACT is not null
     AND    PSC.tspa_oid_tipo_soli_pais = lnoidtiposolisoc
     AND    PSC.Perd_Oid_Peri = vnOidCampAux;


        -- CARGAMOS A UNA TBLA LOS DATOS DE LAS REGIONES QUE YA PROCESARON PAGOS
        IF lsGrupo = 'R' THEN
      INSERT INTO REP_LEC_PROYE_PPREG
        (COD_REGI, FEC_CIER, COD_USUA)
        SELECT DISTINCT ZR.COD_REGI,
               (SELECT FEC_CIER
                  FROM FAC_PROGR_CIERR
                 WHERE COD_REGI = ZR.COD_REGI
                 AND CAM_PROC = psCampanyaProceso
                 AND TIP_CIER ='R'
                 AND EST_CIER = 'P'
                   AND ROWNUM = 1) FEC_CIER,
            psCodigoUsuario
          FROM ZON_REGIO ZR, ZON_ZONA ZZ,MAE_GTT_REZOC GT
          WHERE ZZ.ZORG_OID_REGI = ZR.OID_REGI
          AND ZZ.COD_ZONA = GT.VAL_CODI;
        ELSIF lsGrupo = 'Z' THEN
            INSERT INTO REP_LEC_PROYE_PPREG(COD_ZONA, FEC_CIER, COD_USUA)
            SELECT
            ZZ.COD_ZONA,
            ( SELECT FEC_CIER FROM FAC_PROGR_CIERR
                 WHERE COD_REGI = ZR.COD_REGI
                 AND COD_ZONA = ZZ.COD_ZONA
                 AND CAM_PROC = psCampanyaProceso
                 AND TIP_CIER ='Z'
                 AND EST_CIER = 'P'
                 AND ROWNUM = 1
                ) FEC_CIER,
                psCodigoUsuario
            FROM ZON_ZONA ZZ,ZON_REGIO ZR, MAE_GTT_REZOC GT
            WHERE ZZ.ZORG_OID_REGI = ZR.OID_REGI
              AND ZZ.COD_ZONA = GT.VAL_CODI;
        ELSE
      INSERT INTO REP_LEC_PROYE_PPREG
        (COD_REGI, FEC_CIER, COD_USUA)
        SELECT ZR.COD_REGI,
               (SELECT FEC_CIER
                  FROM FAC_PROGR_CIERR
                 WHERE COD_REGI = ZR.COD_REGI
                 AND CAM_PROC = psCampanyaProceso
                 AND TIP_CIER ='R'
                 AND EST_CIER = 'P'
                   AND ROWNUM = 1) FEC_CIER,
                psCodigoUsuario
            FROM ZON_REGIO ZR;
        END IF;
        -- -----------------------

    INSERT INTO REP_LEC_PROYE_DETAL
                (COD_REGI,
                COD_ZONA,
                CAM_RECU,
                COD_SECC,
                COD_LIDE,
                NIV_EXIT,
                COD_TERR,
                COD_CONS,
                NUM_DOCU_IDEN,
                OID_CLIE,
                NOM_CLIE,
                MIN_FLEX,
                MON_TOTA,
                REC_TOTA,
                MON_NETO,
                SAL_CAMP_ANTE,
                NET_RECU,
                POR_COMI,
                PED_CONS,
                COD_USUA)
       SELECT DR.COD_REGI,
                DR.COD_ZONA,
                DR.CAM_RECU,
                DR.COD_SECC,
                DR.COD_LIDE,
                (SELECT NI.DES_NIVE
                    FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                    WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                    AND NL.COD_LIDE = DR.COD_LIDE
                    AND DR.CAM_RECU >= NL.CAM_INIC
                    AND (DR.CAM_RECU <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                    AND NL.IND_ACTI = '1'
                    AND NL.IND_TIPO_NIVE = 'R'
                    AND NL.LPRO_COD_PROG = DR.LPRO_COD_PROG
                  AND NL.PAIS_COD_PAIS = psCodigoPais) NIV_EXIT,
                ZTE.COD_TERR,
                DR.COD_CONS,
                MID.NUM_DOCU_IDEN,
                MCL.OID_CLIE,
                MCL.VAL_NOM1||' '||MCL.VAL_APE1||' '||MCL.VAL_APE2 NOM_CLIE,
                NVL((SELECT NVL(VAL_CUOT_21DI_PEDI_VIGE, 0)
                    FROM FLX_GENER_FINAN_CONSU_FLEXI FLX
                WHERE FLX.COD_PERI = DR.CAM_RECU
                AND FLX.COD_CLIE = DR.COD_CONS
                     AND FLX.SOCA_OID_SOLI_CABE = DR.SOCA_OID_SOLI_CABE),
                  0) MIN_FLEX,
              NVL(DR.VAL_MONT_CARG_TOTA, 0) -
              (NVL(DR.VAL_MONT_CDRS_TOTA, 0) +
               NVL(DR.VAL_MONT_ABON_PDTE_TOTA, 0)) MON_TOTA,
                0 REC_TOTA,
              (NVL(VAL_MONT_CARG, 0) -
              (NVL(DR.VAL_MONT_CDRS, 0) + NVL(DR.VAL_MONT_ABON_PDTE, 0))) MON_NETO,
                MCL.SAL_DEUD_ANTE SAL_CAMP_ANTE,
                0 NET_RECU,
                ----- 0 POR_COMI,
              -- funcion llamada
              LEC_PKG_PROCE.LEC_FN_OBTE_PORC_COMI(
              DR.PAIS_COD_PAIS, DR.LPRO_COD_PROG, DR.CAM_RECU,
              DR.COD_LIDE, DR.IND_PEDI_CONS, psCodigoPais, psCondicionTramos,0,'1',
              DR.COD_REGI,DR.COD_ZONA,DR.COD_SECC) POR_COMI,
                ----********
              DECODE(DR.IND_PEDI_CONS, '1', 'SI', 'NO') PED_CONS,
              psCodigoUsuario
         FROM LEC_LIDER_SECCI_DETAL_RECUP DR,
                    MAE_CLIEN MCL,
                    MAE_CLIEN_IDENT MID,
                    MAE_CLIEN_UNIDA_ADMIN CUA,
                    ZON_TERRI_ADMIN ZTA,
                    ZON_TERRI ZTE,
                    REP_LEC_PROYE_PPREG PPR
                WHERE DR.PAIS_COD_PAIS = psCodigoPais
                AND MCL.COD_CLIE = DR.COD_CONS
                AND MCL.OID_CLIE = CUA.CLIE_OID_CLIE
                AND MCL.OID_CLIE = MID.CLIE_OID_CLIE
                AND CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                AND ZTA.TERR_OID_TERR = ZTE.OID_TERR
                AND (DR.COD_REGI = PPR.COD_REGI OR DR.COD_ZONA = PPR.COD_ZONA)
                AND PPR.COD_USUA = psCodigoUsuario
                AND MID.VAL_IDEN_DOCU_PRIN = 1
          AND (SELECT CP.OID_PERI
                                  FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                                 WHERE SP.COD_PERI =   DR.CAM_RECU
                  AND SP.OID_PERI = CP.PERI_OID_PERI) >=
              CUA.PERD_OID_PERI_INI
                AND ((SELECT CP.OID_PERI
                                   FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                                  WHERE SP.COD_PERI = DR.CAM_RECU
                                    AND SP.OID_PERI = CP.PERI_OID_PERI) <=
              CUA.PERD_OID_PERI_FIN OR CUA.PERD_OID_PERI_FIN IS NULL)
          AND DR.CAM_RECU = DECODE(PPR.FEC_CIER, NULL, lsCampanyaAnterior, LEC_FN_OBTE_CAMPA_RECU(DR.LPRO_COD_PROG,
                                                   psCampanyaProceso,
                                                   lsCampanyaAnterior,
                                                   lsCampanyaSiguiente,
                                                   dsFecFact,
                                                   PPR.FEC_CIER,
                                                   lsCamp,
                                                   'R'))
          AND ((lsFlagRegionesZonas = '1' AND
              DR.COD_ZONA IN
              (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR
              lsFlagRegionesZonas = '0')
          AND NOT EXISTS
        (SELECT 1
                    FROM LEC_LIDER_SECCI_DRECU_TRAMO SDT
                    WHERE SDT.MVCC_OID_MOVI_CC = DR.MVCC_OID_MOVI_CC
                    AND SDT.COD_LIDE = DR.COD_LIDE
                    AND SDT.CAM_RECU = DR.CAM_RECU
                    AND SDT.COD_CONS = DR.COD_CONS
                    AND SDT.LPCT_COD_TRAM = psCondicionTramos)
                UNION ALL
       SELECT DR.COD_REGI,
                DR.COD_ZONA,
                DR.CAM_RECU,
                DR.COD_SECC,
                DR.COD_LIDE,
                (SELECT NI.DES_NIVE
                    FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                    WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                    AND NL.COD_LIDE = DR.COD_LIDE
                    AND DR.CAM_RECU >= NL.CAM_INIC
                    AND (DR.CAM_RECU <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                    AND NL.IND_ACTI = '1'
                    AND NL.IND_TIPO_NIVE = 'R'
                    AND NL.LPRO_COD_PROG = DR.LPRO_COD_PROG
                  AND NL.PAIS_COD_PAIS = psCodigoPais) NIV_EXIT,
                ZTE.COD_TERR,
                DRT.COD_CONS,
                MID.NUM_DOCU_IDEN,
                MCL.OID_CLIE,
                MCL.VAL_NOM1||' '||MCL.VAL_APE1||' '||MCL.VAL_APE2 NOM_CLIE,
                NVL((SELECT NVL(VAL_CUOT_21DI_PEDI_VIGE, 0)
                    FROM FLX_GENER_FINAN_CONSU_FLEXI FLX
                WHERE FLX.COD_PERI = DR.CAM_RECU
                AND FLX.COD_CLIE = DR.COD_CONS
                     AND FLX.SOCA_OID_SOLI_CABE = DR.SOCA_OID_SOLI_CABE),
                  0) MIN_FLEX,
              NVL(DR.VAL_MONT_CARG_TOTA, 0) -
              (NVL(DR.VAL_MONT_CDRS_TOTA, 0) +
               NVL(DR.VAL_MONT_ABON_PDTE_TOTA, 0)) MON_TOTA,
                NVL(DRT.VAL_MONT_ABON_TOTA, 0) REC_TOTA,
              (NVL(VAL_MONT_CARG, 0) -
              (NVL(DR.VAL_MONT_CDRS, 0) + NVL(DR.VAL_MONT_ABON_PDTE, 0))) MON_NETO,
                MCL.SAL_DEUD_ANTE SAL_CAMP_ANTE,
                DRT.VAL_MONT_ABON NET_RECU,

              --funcion
               LEC_PKG_PROCE.LEC_FN_OBTE_PORC_COMI(
              DR.PAIS_COD_PAIS, DR.LPRO_COD_PROG, DR.CAM_RECU,
              DR.COD_LIDE, DR.IND_PEDI_CONS, psCodigoPais,'', DRT.LPCT_COD_TRAM,'2',
              DR.COD_REGI,DR.COD_ZONA,DR.COD_SECC) POR_COMI,

              DECODE(DR.IND_PEDI_CONS, '1', 'SI', 'NO') PED_CONS,
              psCodigoUsuario
         FROM LEC_LIDER_SECCI_DETAL_RECUP DR,
                    LEC_LIDER_SECCI_DRECU_TRAMO DRT,
                    MAE_CLIEN MCL,
                    MAE_CLIEN_IDENT MID,
                    MAE_CLIEN_UNIDA_ADMIN CUA,
                    ZON_TERRI_ADMIN ZTA,
                    ZON_TERRI ZTE,
                    REP_LEC_PROYE_PPREG PPR
                WHERE DR.PAIS_COD_PAIS = DRT.PAIS_COD_PAIS
                AND DR.CAM_RECU = DRT.CAM_RECU
                AND DR.COD_LIDE = DRT.COD_LIDE
                AND DR.COD_CONS = DRT.COD_CONS
                AND DR.MVCC_OID_MOVI_CC = DRT.MVCC_OID_MOVI_CC
                AND MCL.COD_CLIE = DR.COD_CONS
                AND MCL.OID_CLIE = CUA.CLIE_OID_CLIE
                AND MCL.OID_CLIE = MID.CLIE_OID_CLIE
                AND CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
                AND ZTA.TERR_OID_TERR = ZTE.OID_TERR
                AND (DRT.COD_REGI = PPR.COD_REGI OR DRT.COD_ZONA = PPR.COD_ZONA)
                AND PPR.COD_USUA = psCodigoUsuario
                AND MID.VAL_IDEN_DOCU_PRIN = 1
          AND (SELECT CP.OID_PERI
                                  FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                                 WHERE SP.COD_PERI =   DR.CAM_RECU
                  AND SP.OID_PERI = CP.PERI_OID_PERI) >=
              CUA.PERD_OID_PERI_INI
                AND ((SELECT CP.OID_PERI
                                   FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                                  WHERE SP.COD_PERI = DR.CAM_RECU
                                    AND SP.OID_PERI = CP.PERI_OID_PERI) <=
              CUA.PERD_OID_PERI_FIN OR CUA.PERD_OID_PERI_FIN IS NULL)
                AND DR.PAIS_COD_PAIS = psCodigoPais
          AND DR.CAM_RECU = DECODE(PPR.FEC_CIER, NULL, lsCampanyaAnterior, LEC_FN_OBTE_CAMPA_RECU(DR.LPRO_COD_PROG,
                                                   psCampanyaProceso,
                                                   lsCampanyaAnterior,
                                                   lsCampanyaSiguiente,
                                                   dsFecFact,
                                                   PPR.FEC_CIER,
                                                   lsCamp,
                                                   'R'))
                AND DRT.LPCT_COD_TRAM = psCondicionTramos
          AND ((lsFlagRegionesZonas = '1' AND
              DRT.COD_ZONA IN
              (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR
              lsFlagRegionesZonas = '0');

    commit;

      INSERT INTO REP_LEC_PROYE
      (COD_REGI,
       COD_ZONA,
       CAM_RECU,
       COD_SECC,
       COD_LIDE,
       NOM_LIDE,
       NIV_EXIT,
       COD_TERR,
       COD_CONS,
       NUM_DOCU_IDEN,
       NOM_CLIE,
       MIN_FLEX,
       MON_TOTA,
       VENT_RETA,
       REC_TOTA,
       MON_NETO,
       SAL_CAMP_ANTE,
       SAL_ACTU,
       NET_RECU,
       POR_COMI,
       VAL_INGR,
       PED_CONS,
       COD_USUA)
      SELECT COD_REGI,
                COD_ZONA,
                CAM_RECU,
                COD_SECC,
                COD_LIDE,
                GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(COD_LIDE, 'NOM_CLIE') NOM_LIDE,
                NIV_EXIT,
                COD_TERR,
                COD_CONS,
                NUM_DOCU_IDEN,
                NOM_CLIE,
                SUM(MIN_FLEX) MIN_FLEX,
                SUM(MON_TOTA) MON_TOTA,
             (SELECT NVL(SUM(NVL(LSDR.VAL_MONT_RTAL, 0)), 0)
                    FROM LEC_LIDER_SECCI_DETAL_RECUP LSDR
                    WHERE LSDR.PAIS_COD_PAIS =  psCodigoPais
                 AND LSDR.CAM_RECU = REP_LEC_PROYE_DETAL.CAM_RECU
                 AND LSDR.COD_CONS = REP_LEC_PROYE_DETAL.COD_CONS
                    AND LSDR.MVCC_OID_MOVI_CC  = 0) VENT_RETA,
                SUM(REC_TOTA) REC_TOTA,
                SUM(MON_NETO) MON_NETO,
             (SELECT SUM(MCC.IMP_PEND)
                    FROM CCC_MOVIM_CUENT_CORRI MCC
               WHERE MCC.PERD_OID_PERI <
                     (SELECT CP.OID_PERI
                                  FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                       WHERE SP.COD_PERI = CAM_RECU
                                   AND SP.OID_PERI = CP.PERI_OID_PERI)
                 AND MCC.CLIE_OID_CLIE = OID_CLIE) SAL_CAMP_ANTE,
             (SELECT SUM(MCC.IMP_PEND)
                    FROM CCC_MOVIM_CUENT_CORRI MCC
               WHERE MCC.PERD_OID_PERI =
                     (SELECT CP.OID_PERI
                                  FROM CRA_PERIO CP, SEG_PERIO_CORPO SP
                       WHERE SP.COD_PERI = CAM_RECU
                                   AND SP.OID_PERI = CP.PERI_OID_PERI)
                 AND MCC.CLIE_OID_CLIE = OID_CLIE) SAL_ACTU,
                SUM(NET_RECU) NET_RECU,
             SUM(POR_COMI) / DECODE(COUNT(CASE
                                            WHEN POR_COMI > 0 THEN
                                             1
                                          END),
                                    0,
                                    1,
                                    COUNT(CASE
                                            WHEN POR_COMI > 0 THEN
                                             1
                                          END)) POR_COMI,
             (SUM(NET_RECU) *
             (SUM(POR_COMI) / DECODE(COUNT(CASE
                                              WHEN POR_COMI > 0 THEN
                                               1
                                            END),
                                      0,
                                      1,
                                      COUNT(CASE
                                              WHEN POR_COMI > 0 THEN
                                               1
                                            END)))) / 100 VAL_INGR,
                PED_CONS,
                psCodigoUsuario
        FROM REP_LEC_PROYE_DETAL
        WHERE COD_USUA = psCodigoUsuario
       GROUP BY COD_REGI,
                COD_ZONA,
                CAM_RECU,
                COD_SECC,
                COD_LIDE,
                NIV_EXIT,
                COD_TERR,
                COD_CONS,
                OID_CLIE,
                NUM_DOCU_IDEN,
                NOM_CLIE,
                PED_CONS;
          commit;

    EXCEPTION
        WHEN OTHERS THEN
            ln_sqlcode := SQLCODE;
            ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_REPOR_PROYE: ' ||
                              ls_sqlerrm);

    END LEC_PR_GENER_REPOR_PROYE;    
    
  /***********************************************************************************************
  Descripcion       : Obtiene la campaï¿½a de recuperaciï¿½n (Parï¿½metros reducidos)
  Fecha Creacion    : 03/11/2014
  Autor             : Carlos Mori
  Parametros        : psCodigoPais,psCodigoPrograma,psCampanyaProceso, psCodigoRegion,
                      psCodigoZona,psTipoCampanya ("B","R")
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_CAMPA_RECU(psCodigoPais      VARCHAR2,
                                  psCodigoPrograma  VARCHAR2,
                                  psCampanyaProceso VARCHAR2,
                                  psCodigoRegion    VARCHAR2,
                                  psCodigoZona      VARCHAR2,
                                  PsTipoCampanya    VARCHAR2) RETURN VARCHAR2 IS
    lsCampBono          VARCHAR2(6);
    lsCampRecu          VARCHAR2(6);
    lsIndCampaAnte      VARCHAR2(1);
    lsIndTipoGrupoPago  VARCHAR2(1);
    lsNumDiasExtras     LEC_PROGR_COBRA_OBJET.NUM_DIAS_EXTR%TYPE;
    lsFechaCierre       FAC_PROGR_CIERR.FEC_CIER%TYPE;
    lnoidtiposolisoc    ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
    vnOidCampAux        cra_perio.oid_peri%type;
    dsFecFact           ped_solic_cabec.fec_fact%type;
    resta               NUMBER;
    lsCampanyaAnterior  VARCHAR2(6);
    lsCampanyaSiguiente VARCHAR2(6);
  BEGIN

    -- Obtener Tipo Solicitud
    SELECT tsp.oid_tipo_soli_pais
      INTO lnoidtiposolisoc
      FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND ts.cod_tipo_soli = 'SOC';

    vnOidCampAux := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCampanyaProceso);

    SELECT MAX(fec_fact)
      into dsFecFact
      FROM PED_SOLIC_CABEC PSC
     WHERE PSC.FEC_FACT is not null
       AND PSC.tspa_oid_tipo_soli_pais = lnoidtiposolisoc
       AND PSC.Perd_Oid_Peri = vnOidCampAux;

    -- Obtener valor del indicador de calculo de recuperacion en campaï¿½a X-n
    BEGIN
      SELECT val_para
        INTO lsIndCampaAnte
        FROM bas_param_pais
       WHERE cod_pais = psCodigoPais
         AND cod_sist = 'LET'
         AND UPPER(nom_para) = 'INDCAMPAANTE';
    EXCEPTION
      WHEN no_data_found THEN
        lsIndCampaAnte := '0';
    END;
    -- Obtener valor del indicador de Tipo Grupo Pago
    BEGIN
      SELECT val_para
        INTO lsIndTipoGrupoPago
        FROM bas_param_pais
       WHERE cod_pais = psCodigoPais
         AND cod_sist = 'LET'
         AND UPPER(nom_para) = 'INDTIPOGRUPOPAGO';
    EXCEPTION
      WHEN no_data_found THEN
        lsIndTipoGrupoPago := '0';
    END;

    -- Inicializar valores de campaï¿½a de bono y recaudo
    resta               := TO_NUMBER(lsIndCampaAnte);
    lsCampanyaAnterior  := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCampanyaProceso,
                                                            -resta);
    lsCampanyaSiguiente := gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,
                                                            psCampanyaProceso,
                                                            1);

    lsCampRecu := lsCampanyaAnterior;
    lsCampBono := psCampanyaProceso;

    --Obtener los dias extras
    BEGIN
      SELECT NUM_DIAS_EXTR
        INTO lsNumDiasExtras
        FROM LEC_PROGR_COBRA_OBJET
       WHERE LPRO_COD_PROG = psCodigoPrograma
         AND IND_ACTI = 1
         AND ROWNUM = 1;
      IF lsNumDiasExtras IS NULL THEN
        lsNumDiasExtras := 0;
      END IF;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        lsNumDiasExtras := 0;
    END;

    IF (NVL(lsIndCampaAnte, '0') = '1') THEN
      IF (lsIndTipoGrupoPago = 'R') THEN
        BEGIN
          SELECT FEC_CIER
            INTO lsFechaCierre
            FROM FAC_PROGR_CIERR
           WHERE COD_REGI = psCodigoRegion
             AND CAM_PROC = psCampanyaProceso
             AND TIP_CIER = 'R'
             AND EST_CIER = 'P'
             AND ROWNUM = 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            lsFechaCierre := NULL;
        END;

        IF lsFechaCierre IS NOT NULL THEN
          lsFechaCierre := lsFechaCierre + lsNumDiasExtras;
          IF lsFechaCierre < dsFecFact THEN
            lsCampRecu := psCampanyaProceso;
            lsCampBono := lsCampanyaSiguiente;
          END IF;
        END IF;
      ELSIF (lsIndTipoGrupoPago = 'Z') THEN
        BEGIN
          SELECT FEC_CIER
            INTO lsFechaCierre
            FROM FAC_PROGR_CIERR
           WHERE COD_REGI = psCodigoRegion
             AND COD_ZONA = psCodigoZona
             AND CAM_PROC = psCampanyaProceso
             AND TIP_CIER = 'Z'
             AND EST_CIER = 'P'
             AND ROWNUM = 1;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            lsFechaCierre := NULL;
        END;

        IF lsFechaCierre IS NOT NULL THEN
          lsFechaCierre := lsFechaCierre + lsNumDiasExtras;
          IF lsFechaCierre < dsFecFact THEN
            lsCampRecu := psCampanyaProceso;
            lsCampBono := lsCampanyaSiguiente;
          END IF;
        END IF;
      END IF;
    END IF;
    IF PsTipoCampanya = 'R' THEN
      RETURN lsCampRecu;
    ELSIF PsTipoCampanya = 'B' THEN
      RETURN lsCampBono;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;

  END LEC_FN_OBTE_CAMPA_RECU;

  /***************************************************************************
   Descripcion       : Carga Bonos de Lanzamiento
   Fecha Creacion    : 03/08/2015
   Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_OBJET_BONO(psCodigoPais       VARCHAR2,
                                    psNumeroCarga      NUMBER,
                                    pscodigoPrograma   VARCHAR2,
                                    pscodigoTipoCarga  VARCHAR2,
                                    pnCodigoUsuario    VARCHAR2,
                                    pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1,
             VAL_DATO_2,
             VAL_DATO_3,
             VAL_DATO_4,
             VAL_DATO_5,
             VAL_DATO_6,
             VAL_DATO_8
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         AND IND_REGI_PROC = 0
         AND IND_REGI_ERRA = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      valDato3 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE,
      valDato4 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_4%TYPE,
      valDato5 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_5%TYPE,
      valDato6 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_6%TYPE,
      valDato8 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_8%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN interfazProgramasTab;

    vsCodTipoMedic VARCHAR2(2);
    vsCodTipoBono  VARCHAR2(2);
    vnSecBono      LEC_PROGR_BONO_NIVEL.SEC_BONO_NIVE%TYPE;
    lnExiste       NUMBER;

  BEGIN

    IF pscodigoTipoCarga = '11' THEN
      vsCodTipoMedic := '01';
      vsCodTipoBono  := '01';
    ELSIF pscodigoTipoCarga = '12' THEN
      vsCodTipoMedic := '01';
      vsCodTipoBono  := '02';
    ELSIF pscodigoTipoCarga = '13' THEN
      vsCodTipoMedic := '02';
      vsCodTipoBono  := '03';
    ELSIF pscodigoTipoCarga = '14' THEN
      vsCodTipoMedic := '02';
      vsCodTipoBono  := '04';
    END IF;

    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;

      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          pnIndicadorProceso := '1';

          SELECT SEC_BONO_NIVE
            INTO vnSecBono
            FROM LEC_PROGR_BONO_NIVEL
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = psCodigoPrograma
             AND LTBO_COD_TIPO_BONO = vsCodTipoBono
             AND LNIV_COD_NIVE = interfazRecordN(x).valDato6
             AND LPBC_CAM_LANZ = interfazRecordN(x).valDato1
             AND LPBL_NUM_LANZ = interfazRecordN(x).valDato5;

          SELECT COUNT(1)
            INTO lnExiste
            FROM LEC_LIDER_SECCI_OBJET_BONO
           WHERE PAIS_COD_PAIS = psCodigoPais
             AND LPRO_COD_PROG = psCodigoPrograma
             AND COD_REGI = interfazRecordN(x).valDato2
             AND COD_ZONA = interfazRecordN(x).valDato3
             AND COD_SECC = interfazRecordN(x).valDato4
             AND LTBO_COD_TIPO_BONO = vsCodTipoBono
             AND LNIV_COD_NIVE = interfazRecordN(x).valDato6
             AND LPBN_SEC_BONO_NIVE = vnSecBono
             AND CAM_OBJE = interfazRecordN(x).valDato1;

          IF lnExiste = 1 THEN
            UPDATE LEC_LIDER_SECCI_OBJET_BONO
               SET VAL_OBJE_BONO = TO_NUMBER(interfazRecordN(x).valDato8,
                                             '9999999999.99'),
                   USU_MODI      = pnCodigoUsuario,
                   FEC_MODI      = SYSDATE,
                   IND_ORIG_CALC = 'X'
             WHERE PAIS_COD_PAIS = psCodigoPais
               AND LPRO_COD_PROG = psCodigoPrograma
               AND COD_REGI = interfazRecordN(x).valDato2
               AND COD_ZONA = interfazRecordN(x).valDato3
               AND COD_SECC = interfazRecordN(x).valDato4
               AND LTBO_COD_TIPO_BONO = vsCodTipoBono
               AND LNIV_COD_NIVE = interfazRecordN(x).valDato6
               AND LPBN_SEC_BONO_NIVE = vnSecBono
               AND CAM_OBJE = interfazRecordN(x).valDato1;
          ELSE
            INSERT INTO LEC_LIDER_SECCI_OBJET_BONO
              (PAIS_COD_PAIS,
               LPRO_COD_PROG,
               COD_REGI,
               COD_ZONA,
               COD_SECC,
               LTBO_COD_TIPO_BONO,
               LNIV_COD_NIVE,
               LPBN_SEC_BONO_NIVE,
               CAM_OBJE,
               VAL_OBJE_BONO,
               IND_ORIG_CALC,
               IND_ACTI,
               USU_CREA,
               FEC_CREA)
            VALUES
              (psCodigoPais,
               psCodigoPrograma,
               interfazRecordN(x).valDato2,
               interfazRecordN(x).valDato3,
               interfazRecordN(x).valDato4,
               vsCodTipoBono,
               interfazRecordN(x).valDato6,
               vnSecBono,
               interfazRecordN(x).valDato1,
               TO_NUMBER(interfazRecordN(x).valDato8, '9999999999.99'),
               'X',
               '1',
               pnCodigoUsuario,
               SYSDATE);
          END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CARGA_OBJET_BONO: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_CARGA_OBJET_BONO;
  /***************************************************************************
   Descripcion       : Carga Bloqueo y Desploqueo de Pagos, Actualiza Montos Netos
   Fecha Creacion    : 08/09/2015
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE LEC_PR_CARGA_BLODE_ACTUA_MONTO(psCodigoPais       VARCHAR2,
                                           psNumeroCarga      NUMBER,
                                           pscodigoPrograma   VARCHAR2,
                                           pscodigoTipoCarga  VARCHAR2,
                                           pnCodigoUsuario    VARCHAR2,
                                           pnCodigoPeriodo    VARCHAR2,
                                           pnIndicadorProceso OUT VARCHAR2) IS

    CURSOR c_programas IS
      SELECT VAL_DATO_1, VAL_DATO_2, VAL_DATO_3
        FROM LEC_PROGR_CARGA_DATO_MASIV
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND LPRO_COD_PROG = pscodigoPrograma
         AND IND_REGI_PROC = 0
         AND IND_REGI_ERRA = 0
         AND COD_TIPO_CARG = pscodigoTipoCarga
         AND NUM_CARG = psNumeroCarga;

    TYPE interfazProgramas IS RECORD(
      valDato1 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_1%TYPE,
      valDato2 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_2%TYPE,
      valDato3 LEC_PROGR_CARGA_DATO_MASIV.VAL_DATO_3%TYPE);

    TYPE interfazProgramasTab IS TABLE OF interfazProgramas;
    interfazRecordN interfazProgramasTab;

    lnExiste NUMBER;

  BEGIN
    OPEN c_programas;
    LOOP
      FETCH c_programas BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;

      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          pnIndicadorProceso := '1';

          IF pscodigoTipoCarga = '15' THEN
            UPDATE LEC_LIDER_PAGO_COMIS
               SET IND_PROC_PAGO      = '5',
                   FEC_BLOQ_PAGO      = SYSDATE,
                   MOBP_COD_MOTI_BLOQ = interfazRecordN(x).valDato2,
                   USU_MODI           = pnCodigoUsuario,
                   FEC_MODI           = SYSDATE
             WHERE CAM_PROC = pnCodigoPeriodo
               AND IND_PROC_PAGO = '1'
               AND COD_LIDE = interfazRecordN(x).valDato1;
          END IF;

          IF pscodigoTipoCarga = '16' THEN
            UPDATE LEC_LIDER_PAGO_COMIS
               SET IND_PROC_PAGO = '1',
                   FEC_DESB_PAGO = SYSDATE,
                   USU_MODI      = pnCodigoUsuario,
                   FEC_MODI      = SYSDATE
             WHERE CAM_PROC = pnCodigoPeriodo
               AND IND_PROC_PAGO = '5'
               AND COD_LIDE = interfazRecordN(x).valDato1;
          END IF;

          IF pscodigoTipoCarga = '17' THEN
            UPDATE LEC_LIDER_PAGO_COMIS
               SET VAL_MONT_NETO = TO_NUMBER(interfazRecordN(x).valDato3,
                                             '9999999999.99'),
                   IND_PROC_PAGO = '2',
                   USU_MODI      = pnCodigoUsuario,
                   FEC_MODI      = SYSDATE
             WHERE NUM_SECU = interfazRecordN(x).valDato1
               AND COD_SAP_CONS = interfazRecordN(x).valDato2;
          END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_programas%NOTFOUND;
    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode         := SQLCODE;
      ls_sqlerrm         := substr(sqlerrm, 1, 250);
      pnIndicadorProceso := '0';
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_CARGA_BLODE_ACTUA_MONTO: (' ||
                              ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_PR_CARGA_BLODE_ACTUA_MONTO;
  
  /***********************************************************************************************
  Descripcion       : Funcion que devuelve el porcentaje de comision para el reporte de Proyeccion
  Fecha Creacion    : 29/09/2015
  Autor             : Richard Argomedo.
  ***********************************************************************************************/
  FUNCTION LEC_FN_OBTE_PORC_COMI(psCodigoPais1    VARCHAR2,
                                 psLproCodProg      VARCHAR2,
                                 psCamrecu          VARCHAR2,
                                 psCodLide          VARCHAR2,
                                 psIndPediCons      VARCHAR2,                                 
                                 psCodigoPais       varchar2,
                                 psCondicionTramos  VARCHAR2,
                                 psLpctCodTram      NUMBER,
                                 psvalida           VARCHAR2,
                                 psCodigoRegion     VARCHAR2,
                                 psCodigoZona       VARCHAR2,
                                 psCodigoSeccion    VARCHAR2) RETURN NUMBER IS
  
   leoEstaObje         LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE%TYPE;
   leobEstaObjeIngr    LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE_INGR%TYPE;
   leobEstaObjeCapi    LEC_LIDER_SECCI_RESUL.LEOB_COD_ESTA_OBJE_CAPI%TYPE;
   resultado           NUMBER;
  BEGIN
    
  BEGIN
   SELECT NVL(LSR.LEOB_COD_ESTA_OBJE,0),
            NVL(LSR.LEOB_COD_ESTA_OBJE_INGR,0),
           NVL(LSR.LEOB_COD_ESTA_OBJE_CAPI,0) 
           INTO leoEstaObje,
           leobEstaObjeIngr,
           leobEstaObjeCapi
        FROM LEC_LIDER_SECCI_RESUL LSR
        WHERE LSR.PAIS_COD_PAIS = psCodigoPais
        AND LSR.LPRO_COD_PROG = psLproCodProg
        AND LSR.CAM_RESU = psCamrecu
        AND LSR.COD_LIDE = psCodLide
        AND LSR.COD_REGI = psCodigoRegion
        AND LSR.COD_ZONA = psCodigoZona
        AND LSR.COD_SECC = psCodigoSeccion;   
   EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   leoEstaObje := 0;
                   leobEstaObjeIngr := 0;
                   leobEstaObjeCapi := 0;
            END;  
    
     IF(psvalida = '1')THEN
        IF ((leoEstaObje = '01') AND (leobEstaObjeIngr = '01' OR leobEstaObjeCapi = '01')) THEN
          IF (psIndPediCons = '1')THEN
            BEGIN
              SELECT NT.VAL_PORC_COMI_PEDI_CONS
              INTO RESULTADO
              FROM LEC_PROGR_NIVEL_TRAMO NT
              WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
               AND NT.LPRO_COD_PROG = PSLPROCODPROG
               AND NT.LNIV_COD_NIVE =
                   (SELECT NI.COD_NIVE
                      FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                     WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                       AND NL.COD_LIDE = PSCODLIDE
                       AND PSCAMRECU >= NL.CAM_INIC
                       AND (PSCAMRECU <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                       AND NL.IND_ACTI = '1'
                       AND NL.IND_TIPO_NIVE = 'R'
                       AND NL.LPRO_COD_PROG = PSLPROCODPROG
                       AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
               AND NT.COD_TRAM = PSCONDICIONTRAMOS;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   RESULTADO := 0;
            END;  
          ELSE
            BEGIN
              SELECT NT.VAL_PORC_COMI_PEDI_NCON
              INTO RESULTADO
              FROM LEC_PROGR_NIVEL_TRAMO NT
              WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
               AND NT.LPRO_COD_PROG = PSLPROCODPROG
               AND NT.LNIV_COD_NIVE =
                   (SELECT NI.COD_NIVE
                      FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                     WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                       AND NL.COD_LIDE = PSCODLIDE
                       AND psCamrecu >= NL.CAM_INIC
                       AND (psCamrecu <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                       AND NL.IND_ACTI = '1'
                       AND NL.IND_TIPO_NIVE = 'R'
                       AND NL.LPRO_COD_PROG = PSLPROCODPROG
                       AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
               AND NT.COD_TRAM = PSCONDICIONTRAMOS;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   RESULTADO := 0;
            END;
          END IF; 
           
        ELSIF ((leoEstaObje = '02') OR (leobEstaObjeIngr = '03' AND leobEstaObjeCapi = '03')) THEN
         BEGIN
           SELECT NT.VAL_PORC_COMI_TOLE
           INTO RESULTADO
           FROM LEC_PROGR_NIVEL_TRAMO NT
           WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
            AND NT.LPRO_COD_PROG = PSLPROCODPROG
            AND NT.LNIV_COD_NIVE =
                (SELECT NI.COD_NIVE
                   FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                  WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                    AND NL.COD_LIDE = PSCODLIDE
                    AND psCamrecu >= NL.CAM_INIC
                    AND (psCamrecu <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                    AND NL.IND_ACTI = '1'
                    AND NL.IND_TIPO_NIVE = 'R'
                    AND NL.LPRO_COD_PROG = PSLPROCODPROG
                    AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
            AND NT.COD_TRAM = PSCONDICIONTRAMOS;
         EXCEPTION
           WHEN NO_DATA_FOUND THEN
             RESULTADO := 0;
         END;
        ELSIF  leoEstaObje = '03' THEN
          resultado := 0;
        ELSE 
          resultado := 0;
        END IF;
     ELSIF(psvalida = '2') THEN
       IF ((leoEstaObje = '01') AND (leobEstaObjeIngr = '01' OR leobEstaObjeCapi = '01')) THEN
          IF (psIndPediCons = '1')THEN
            BEGIN
              SELECT NT.VAL_PORC_COMI_PEDI_CONS
              INTO RESULTADO
              FROM LEC_PROGR_NIVEL_TRAMO NT
              WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
               AND NT.LPRO_COD_PROG = PSLPROCODPROG
               AND NT.LNIV_COD_NIVE =
                   (SELECT NI.COD_NIVE
                      FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                     WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                       AND NL.COD_LIDE = PSCODLIDE
                       AND PSCAMRECU >= NL.CAM_INIC
                       AND (PSCAMRECU <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                       AND NL.IND_ACTI = '1'
                       AND NL.IND_TIPO_NIVE = 'R'
                       AND NL.LPRO_COD_PROG = PSLPROCODPROG
                       AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
               AND NT.COD_TRAM = psLpctCodTram;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   RESULTADO := 0;
            END;
          ELSE
            BEGIN
              SELECT NT.VAL_PORC_COMI_PEDI_NCON
              INTO RESULTADO
              FROM LEC_PROGR_NIVEL_TRAMO NT
              WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
               AND NT.LPRO_COD_PROG = PSLPROCODPROG
               AND NT.LNIV_COD_NIVE =
                   (SELECT NI.COD_NIVE
                      FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                     WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                       AND NL.COD_LIDE = PSCODLIDE
                       AND psCamrecu >= NL.CAM_INIC
                       AND (psCamrecu <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                       AND NL.IND_ACTI = '1'
                       AND NL.IND_TIPO_NIVE = 'R'
                       AND NL.LPRO_COD_PROG = PSLPROCODPROG
                       AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
               AND NT.COD_TRAM = psLpctCodTram;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                RESULTADO := 0;
            END;   
          END IF; 
           
        ELSIF ((leoEstaObje = '02') OR (leobEstaObjeIngr = '03' AND leobEstaObjeCapi = '03')) THEN
          BEGIN
            SELECT NT.VAL_PORC_COMI_TOLE
            INTO RESULTADO
            FROM LEC_PROGR_NIVEL_TRAMO NT
            WHERE NT.PAIS_COD_PAIS = PSCODIGOPAIS1
              AND NT.LPRO_COD_PROG = PSLPROCODPROG
              AND NT.LNIV_COD_NIVE =
                  (SELECT NI.COD_NIVE
                     FROM LEC_LIDER_NIVEL NL, LEC_NIVEL NI
                    WHERE NL.LNIV_COD_NIVE = NI.COD_NIVE
                      AND NL.COD_LIDE = PSCODLIDE
                      AND psCamrecu >= NL.CAM_INIC
                      AND (psCamrecu <= NL.CAM_FIN OR NL.CAM_FIN IS NULL)
                      AND NL.IND_ACTI = '1'
                      AND NL.IND_TIPO_NIVE = 'R'
                      AND NL.LPRO_COD_PROG = PSLPROCODPROG
                      AND NL.PAIS_COD_PAIS = PSCODIGOPAIS)
              AND NT.COD_TRAM = psLpctCodTram;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              RESULTADO := 0;
          END;
        ELSIF  leoEstaObje = '03' THEN
          resultado := 0;
        ELSE 
          resultado := 0;
        END IF;
     END IF;    
        
     RETURN resultado;      
  EXCEPTION 
	WHEN  OTHERS  THEN 
    ln_sqlcode         := SQLCODE;
    ls_sqlerrm         := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 
      'ERROR LEC_FN_OBTE_PORC_COMI: (' ||
      ln_sqlcode || ')' || ls_sqlerrm);
  END LEC_FN_OBTE_PORC_COMI;

/**************************************************************************
   Descripcion        : Devuelve Ganancia en base a Consultora y Campaña.
   Fecha Creacion     : 11/11/2015
   Autor              : Carlos Bazalar
  ***************************************************************************/
FUNCTION LEC_FN_DEVUE_GANAN_CONSU(psCodigoPais      VARCHAR2,
                                  psCodigoCliente   VARCHAR2,
                                  psCodigoPeriodo   VARCHAR2)
RETURN NUMBER 
IS
    lnMontoGanancia NUMBER(12,2);
BEGIN
   lnMontoGanancia := 0.00;
   SELECT (nvl(sum(x.val_mont_abon * x.val_porc_comi), 0.00) / 100)
   INTO lnMontoGanancia
   FROM LEC_LIDER_SECCI_DRECU_TRAMO x
   WHERE x.pais_cod_pais = psCodigoPais
     AND x.cod_cons = psCodigoCliente
     AND x.cam_recu = psCodigoPeriodo
     AND X.LPCT_COD_TRAM = 1;

   RETURN lnMontoGanancia;

EXCEPTION
WHEN OTHERS THEN
     RETURN 0.00;
END LEC_FN_DEVUE_GANAN_CONSU;                            

/***************************************************************************
Descripcion       : Devuelve Listado General de Ganancia Lideres
Fecha Creacion    : 11/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LEC_PR_OBTIE_GANAN_LIDER(
    psCodigoPais VARCHAR2)
IS
  CURSOR cConsultoras(vsTipoGrupo VARCHAR2) IS
      SELECT cod_cons
      FROM LEC_TMP_OBTIE_GANAN_LIDER x
      WHERE x.tip_grup = vsTipoGrupo;
  TYPE consultorasTab IS TABLE OF cConsultoras%ROWTYPE;
  consultorasRegistro consultorasTab;
   
  CURSOR cConsultorasGTT IS
      SELECT cod_cons
      FROM LEC_GTT_OBTIE_GANAN_LIDER x;
  TYPE consultorasTabGTT IS TABLE OF cConsultorasGTT%ROWTYPE;
  consultorasRegistroGTT consultorasTabGTT;
  
  CURSOR cConsultorasTotal IS
      SELECT cod_cons,
             MON_GANA_1ERA,
             MON_GANA_2DA,
             MON_GANA_3ERA,
             MON_GANA_4TA,
             MON_GANA_5TA,
             MON_GANA_6TA
      FROM LEC_TMP_OBTIE_GANAN_LIDER x;
  TYPE consultorasTabTotal IS TABLE OF cConsultorasTotal%ROWTYPE;
  consultorasRegistroTotal consultorasTabTotal;
  
  lsPeriodo            BAS_CTRL_FACT.Cod_Peri%TYPE;
  lsCampAnter          BAS_CTRL_FACT.Cod_Peri%TYPE;
  lsCampAnter2         BAS_CTRL_FACT.Cod_Peri%TYPE;
   
  lnIdPais             NUMBER;
  lnIdCanal            NUMBER;
  lnIdMarca            NUMBER;
  lsCodigoPrograma     LEC_PROGR.Cod_Prog%TYPE;
  lsTipoMedicion       LEC_PROGR_COBRA_OBJET.LTMC_COD_TIPO_MEDI_COBR%TYPE;
  lsCampAnterMenos6    BAS_CTRL_FACT.Cod_Peri%TYPE;
  lsCampAnter2Menos6   BAS_CTRL_FACT.Cod_Peri%TYPE;
  lsCampCursor         BAS_CTRL_FACT.Cod_Peri%TYPE;
  lnMontoGanancia      NUMBER(12,2);
  lbEncontro           BOOLEAN;
  lsCodigoConsultora   LEC_TMP_OBTIE_GANAN_LIDER.COD_CONS%TYPE;
  lncontador           NUMBER(2);
BEGIN
  /* obteniendo periodo actual */
  BEGIN
    SELECT COD_PERI
    INTO lsPeriodo 
    FROM BAS_CTRL_FACT x
    WHERE x.STA_CAMP = '0'
      AND x.IND_CAMP_ACT = '1';
  EXCEPTION
    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20002,'No existe Campaña Activa');
  END;
  
  /* obteniendos ids */
  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
  
  lsCampAnter        := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lsCampAnter2       := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -2);
  lsCampAnterMenos6  := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -5);
  lsCampAnter2Menos6 := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -5);
  
  /* obtener programa activo */
  BEGIN
    SELECT COD_PROG
    INTO lsCodigoPrograma
    FROM LEC_PROGR x
    WHERE lsPeriodo >= cam_inic
     and lsPeriodo <=  nvl(cam_fin,lsPeriodo);
  EXCEPTION
  WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20002,'No existe Programa Activo');
  END;
  
  /* obtener tipo medicion */
  BEGIN
     SELECT x.LTMC_COD_TIPO_MEDI_COBR
     INTO lsTipoMedicion
     FROM LEC_PROGR_COBRA_OBJET x
     WHERE x.PAIS_COD_PAIS = psCodigoPais
       AND x.LPRO_COD_PROG = lsCodigoPrograma
       AND X.IND_ACTI = '1'
       AND rownum = 1;
  EXCEPTION
  WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20002,'No existe Tipo de Medición para Programa Activo');
  END;
  
  EXECUTE IMMEDIATE 'truncate table LEC_TMP_OBTIE_GANAN_LIDER';
  DELETE FROM LEC_GTT_OBTIE_GANAN_LIDER;
  
  IF lsTipoMedicion = '03' THEN
     INSERT INTO LEC_TMP_OBTIE_GANAN_LIDER(
         COD_CONS
     )
     SELECT DISTINCT COD_CONS
     FROM LEC_LIDER_SECCI_DETAL_RECUP X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND x.LPRO_COD_PROG = lsCodigoPrograma
       AND X.CAM_RECU <= lsCampAnter
       AND x.CAM_RECU >= lsCampAnterMenos6
       AND x.COD_ZONA IN (SELECT Y.COD_ZONA 
                          FROM FAC_PROGR_CIERR Y
                          WHERE Y.COD_PAIS = X.PAIS_COD_PAIS 
                            AND Y.TIP_CIER = 'Z'
                            AND Y.EST_CIER = 'P'
                            AND Y.CAM_PROC = lsPeriodo);
  END IF;
  
  IF lsTipoMedicion = '01' THEN
     INSERT INTO LEC_TMP_OBTIE_GANAN_LIDER(
         COD_CONS
     )
     SELECT DISTINCT COD_CONS
     FROM LEC_LIDER_SECCI_DETAL_RECUP X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND x.LPRO_COD_PROG = lsCodigoPrograma
       AND X.CAM_RECU <= lsCampAnter
       AND x.CAM_RECU >= lsCampAnterMenos6
       AND x.COD_REGI IN (SELECT Y.COD_REGI
                          FROM FAC_PROGR_CIERR Y
                          WHERE Y.COD_PAIS = X.PAIS_COD_PAIS 
                            AND Y.TIP_CIER = 'R'
                            AND Y.EST_CIER = 'P'
                            AND Y.CAM_PROC = lsPeriodo);
  END IF;
  
  UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
  SET x.tip_grup = '1',
      x.cod_peri = lsCampAnter;
  COMMIT;
  
  /* Listado Consultoras (Resultado 1) */
  OPEN cConsultoras('1');
  LOOP
    FETCH cConsultoras BULK COLLECT INTO consultorasRegistro LIMIT W_FILAS;
    IF consultorasRegistro.COUNT > 0 THEN
       FOR i IN consultorasRegistro.FIRST .. consultorasRegistro.LAST LOOP
         
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampAnter);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_1era = lnMontoGanancia,
               x.cod_peri_1era = lsCampAnter
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -1);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_2da = lnMontoGanancia,
               x.cod_peri_2da = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;  
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -2);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_3era = lnMontoGanancia,
               x.cod_peri_3era = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons; 
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -3);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_4ta = lnMontoGanancia,
               x.cod_peri_4ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;   
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -4);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_5ta = lnMontoGanancia,
               x.cod_peri_5ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;  
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -5);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_6ta = lnMontoGanancia,
               x.cod_peri_6ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;   
                                  
       END LOOP;
    END IF;
    EXIT WHEN cConsultoras%NOTFOUND;
  END LOOP;
  CLOSE cConsultoras;
  COMMIT;
  
  IF lsTipoMedicion = '03' THEN
     INSERT INTO LEC_GTT_OBTIE_GANAN_LIDER(
         COD_CONS
     )
     SELECT DISTINCT COD_CONS
     FROM LEC_LIDER_SECCI_DETAL_RECUP X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND X.LPRO_COD_PROG = lsCodigoPrograma
       AND X.CAM_RECU <= lsCampAnter2
       AND X.CAM_RECU >= lsCampAnter2Menos6
       AND X.COD_ZONA IN (SELECT DISTINCT cod_zona
                          FROM LEC_LIDER_SECCI_RESUL_RECUP Z
                          WHERE Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                            AND Z.LPRO_COD_PROG = X.LPRO_COD_PROG
                            AND Z.CAM_RECU = X.CAM_RECU
                            AND Z.COD_ZONA NOT IN (
                                            SELECT Y.COD_ZONA 
                                            FROM FAC_PROGR_CIERR Y
                                            WHERE Y.COD_PAIS = X.PAIS_COD_PAIS 
                                              AND Y.TIP_CIER = 'Z'
                                              AND Y.EST_CIER = 'P'
                                              AND Y.CAM_PROC = lsPeriodo)
                            );
  END IF;
  
  IF lsTipoMedicion = '01' THEN
     INSERT INTO LEC_GTT_OBTIE_GANAN_LIDER(
         COD_CONS
     )
     SELECT DISTINCT COD_CONS
     FROM LEC_LIDER_SECCI_DETAL_RECUP X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND X.LPRO_COD_PROG = lsCodigoPrograma
       AND X.CAM_RECU <= lsCampAnter2
       AND X.CAM_RECU >= lsCampAnter2Menos6
       AND X.COD_REGI IN (SELECT DISTINCT COD_REGI
                          FROM LEC_LIDER_SECCI_RESUL_RECUP Z
                          WHERE Z.PAIS_COD_PAIS = X.PAIS_COD_PAIS
                            AND Z.LPRO_COD_PROG = X.LPRO_COD_PROG
                            AND Z.CAM_RECU = X.CAM_RECU
                            AND Z.COD_REGI NOT IN (
                                            SELECT Y.COD_REGI
                                            FROM FAC_PROGR_CIERR Y
                                            WHERE Y.COD_PAIS = X.PAIS_COD_PAIS 
                                              AND Y.TIP_CIER = 'R'
                                              AND Y.EST_CIER = 'P'
                                              AND Y.CAM_PROC = lsPeriodo)
                            );
  END IF;
  
  IF lsTipoMedicion = '02' THEN
     INSERT INTO LEC_GTT_OBTIE_GANAN_LIDER(
         COD_CONS
     )
     SELECT DISTINCT COD_CONS
     FROM LEC_LIDER_SECCI_DETAL_RECUP X
     WHERE X.PAIS_COD_PAIS = psCodigoPais
       AND X.LPRO_COD_PROG = lsCodigoPrograma
       AND X.CAM_RECU <= lsCampAnter2
       AND X.CAM_RECU >= lsCampAnter2Menos6;
  END IF;
  
  /* Actualizando Consultoras (Resultado 2) */
  OPEN cConsultorasGTT;
  LOOP
    FETCH cConsultorasGTT BULK COLLECT INTO consultorasRegistroGTT LIMIT W_FILAS;
    IF consultorasRegistroGTT.COUNT > 0 THEN
       FOR i IN consultorasRegistroGTT.FIRST .. consultorasRegistroGTT.LAST LOOP
           lbEncontro := TRUE;
           BEGIN
             SELECT x.cod_cons
             INTO lsCodigoConsultora
             FROM LEC_TMP_OBTIE_GANAN_LIDER X
             WHERE X.COD_CONS = consultorasRegistroGTT(i).cod_cons;
           EXCEPTION 
           WHEN no_data_found THEN
                lbEncontro := FALSE;
           END;
           
           IF (NOT lbEncontro) THEN
              INSERT INTO LEC_TMP_OBTIE_GANAN_LIDER(COD_CONS, COD_PERI, TIP_GRUP)
              VALUES (consultorasRegistroGTT(i).cod_cons,
                      lsCampAnter2,
                      '2');
           END IF;
       
       END LOOP;
    END IF;
    EXIT WHEN cConsultorasGTT%NOTFOUND;
  END LOOP;
  CLOSE cConsultorasGTT;
  
  COMMIT;
  
  /* Listado Consultoras (Resultado 2) */
  OPEN cConsultoras('2');
  LOOP
    FETCH cConsultoras BULK COLLECT INTO consultorasRegistro LIMIT W_FILAS;
    IF consultorasRegistro.COUNT > 0 THEN
       FOR i IN consultorasRegistro.FIRST .. consultorasRegistro.LAST LOOP
         
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampAnter2);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_1era = lnMontoGanancia,
               x.cod_peri_1era = lsCampAnter2
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -1);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_2da = lnMontoGanancia,
               x.cod_peri_2da = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;  
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -2);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_3era = lnMontoGanancia,
               x.cod_peri_3era = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons; 
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -3);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_4ta = lnMontoGanancia,
               x.cod_peri_4ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;   
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -4);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_5ta = lnMontoGanancia,
               x.cod_peri_5ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;  
           
           lsCampCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter2, lnIdPais, lnIdMarca, lnIdCanal, -5);
           lnMontoGanancia := LEC_FN_DEVUE_GANAN_CONSU(
                                  psCodigoPais,
                                  consultorasRegistro(i).cod_cons,
                                  lsCampCursor);
           UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
           SET x.mon_gana_6ta = lnMontoGanancia,
               x.cod_peri_6ta = lsCampCursor
           WHERE x.cod_cons = consultorasRegistro(i).cod_cons;   
                                  
       END LOOP;
    END IF;
    EXIT WHEN cConsultoras%NOTFOUND;
  END LOOP;
  CLOSE cConsultoras;
  
  COMMIT;
  
  /* Actualizando Promedios */
  OPEN cConsultorasTotal;
  LOOP
    FETCH cConsultorasTotal BULK COLLECT INTO consultorasRegistroTotal LIMIT W_FILAS;
    IF consultorasRegistroTotal.COUNT > 0 THEN
       FOR i IN consultorasRegistroTotal.FIRST .. consultorasRegistroTotal.LAST LOOP
           lncontador := 0;
           IF (consultorasRegistroTotal(i).mon_gana_1era > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF (consultorasRegistroTotal(i).mon_gana_2da > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF (consultorasRegistroTotal(i).mon_gana_3era > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF (consultorasRegistroTotal(i).mon_gana_4ta > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF (consultorasRegistroTotal(i).mon_gana_5ta > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF (consultorasRegistroTotal(i).mon_gana_6ta > 0) THEN
             lncontador := lncontador + 1;
           END IF;
           IF lncontador > 0 THEN
              UPDATE LEC_TMP_OBTIE_GANAN_LIDER x
              SET x.mon_prom = (x.mon_gana_1era + x.mon_gana_2da + 
                                x.mon_gana_3era + x.mon_gana_4ta + 
                                x.mon_gana_5ta + x.mon_gana_6ta) / lncontador
              WHERE COD_CONS = consultorasRegistroTotal(i).cod_cons;
           END IF;
       END LOOP;
    END IF;
    EXIT WHEN cConsultorasTotal%NOTFOUND;
  END LOOP;
  CLOSE cConsultorasTotal;
   
  COMMIT;
  
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LEC_PR_OBTIE_GANAN_LIDER: '||ls_sqlerrm);
END LEC_PR_OBTIE_GANAN_LIDER ;

/**************************************************************************
   Descripcion        : Devuelve Ganancia en base x Venta Consultora.
   Fecha Creacion     : 12/11/2015
   Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais NUMBER,
                                  psOidCliente   NUMBER,
                                  psOidPeriodo   NUMBER)
RETURN NUMBER 
IS
    lnMontoGanancia NUMBER(12,2);
BEGIN
   lnMontoGanancia := 0.00;
   SELECT nvl(sum(VAL_PREC_NETO_TOTA_LOCA), 0.00)
   INTO lnMontoGanancia
   FROM PED_SOLIC_CABEC x
   WHERE x.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais
     AND x.clie_oid_clie = psOidCliente
     AND x.perd_oid_peri = psOidPeriodo
     AND EXISTS (SELECT y.oid_soli_cabe
                 FROM PED_SOLIC_CABEC y
                 WHERE y.oid_soli_cabe = x.soca_oid_soli_cabe
                  AND y.ESSO_OID_ESTA_SOLI <> 4);

   RETURN lnMontoGanancia;

EXCEPTION
WHEN OTHERS THEN
     RETURN 0.00;
END LEC_FN_DEVUE_VENTA_CONSU;        


/***************************************************************************
Descripcion       : Devuelve Listado General de Ventas Consultoras
Fecha Creacion    : 12/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE LEC_PR_OBTIE_VENTA_CONSU(
    psCodigoPais VARCHAR2)
IS
  CURSOR cConsultoras IS
      SELECT x.clie_oid_clie
      FROM LEC_TMP_OBTIE_VENTA_CONSU x;
  TYPE consultorasTab IS TABLE OF cConsultoras%ROWTYPE;
  consultorasRegistro consultorasTab;
  
 
  lsPeriodo            BAS_CTRL_FACT.Cod_Peri%TYPE;
  lnOidPeriodo         NUMBER;
  lsCampAnter          BAS_CTRL_FACT.Cod_Peri%TYPE;
  lnOidCampAnter       NUMBER;
   
  lnIdPais             NUMBER;
  lnIdCanal            NUMBER;
  lnIdMarca            NUMBER;
  lnOidTipoSoliPais    PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
  lsCampAnterMenos7    BAS_CTRL_FACT.Cod_Peri%TYPE;
  lnOidCampAnterMenos7 NUMBER;
  lnOidCliente         mae_clien.oid_clie%TYPE;
  lsCodigoCliente      mae_clien.cod_clie%TYPE;
  lbencontro           BOOLEAN;
  lsTipogrupo          LEC_TMP_OBTIE_VENTA_CONSU.Tip_Grup%TYPE;
  lsCampaCursor        LEC_TMP_OBTIE_VENTA_CONSU.cod_peri%TYPE;
  lsCampaCursorIni     LEC_TMP_OBTIE_VENTA_CONSU.cod_peri%TYPE;
  lnOidPeriodoCursor   NUMBER;
  lnMonto              NUMBER(12,2);
  lnMontoTotal         NUMBER(12,2);
  lncontador           NUMBER(2);
BEGIN
  /* obteniendo periodo actual */
  BEGIN
    SELECT COD_PERI
    INTO lsPeriodo
    FROM BAS_CTRL_FACT x
    WHERE x.STA_CAMP = '0'
      AND x.IND_CAMP_ACT = '1';
  EXCEPTION
    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20002,'No existe Campaña Activa');
  END;
  
  /* obteniendos ids */
  lnOidPeriodo  := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsPeriodo);
  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
  
  lsCampAnter        := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lsCampAnterMenos7  := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampAnter, lnIdPais, lnIdMarca, lnIdCanal, -7);
  lnOidCampAnter     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampAnter);
  lnOidCampAnterMenos7 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampAnterMenos7);
  
  /* obtener tipo solicitud */
  BEGIN
    SELECT oid_tipo_soli_pais
    INTO lnOidTipoSoliPais
    FROM PED_TIPO_SOLIC_PAIS x,
         PED_TIPO_SOLIC y
    WHERE y.oid_tipo_soli = x.tsol_oid_tipo_soli
      AND y.cod_tipo_soli = 'SOC';
  EXCEPTION
  WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20002,'No existe Programa Activo');
  END;
   
  EXECUTE IMMEDIATE 'truncate table LEC_TMP_OBTIE_VENTA_CONSU';
  
  INSERT INTO LEC_TMP_OBTIE_VENTA_CONSU(
       CLIE_OID_CLIE
  )
  SELECT DISTINCT CLIE_OID_CLIE
  FROM ped_solic_cabec_acum2 X
  WHERE X.PERD_OID_PERI <= lnOidPeriodo
    AND X.PERD_OID_PERI >= lnOidCampAnterMenos7;
  
  COMMIT;
  
  
  /* Listado Consultoras  */
  OPEN cConsultoras;
  LOOP
    FETCH cConsultoras BULK COLLECT INTO consultorasRegistro LIMIT W_FILAS;
    IF consultorasRegistro.COUNT > 0 THEN
       FOR i IN consultorasRegistro.FIRST .. consultorasRegistro.LAST LOOP
           lbencontro := TRUE;
           BEGIN
            SELECT x.clie_oid_clie
            INTO lnOidCliente
            FROM PED_SOLIC_CABEC x
            WHERE x.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais
              AND x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie
              AND x.fec_fact IS NOT NULL
              AND x.perd_oid_peri = lnOidPeriodo
              AND rownum = 1;
           
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
               lbencontro := FALSE;
           END;    
           
           SELECT x.cod_clie
           INTO lsCodigoCliente
           FROM mae_clien x
           WHERE x.oid_clie = consultorasRegistro(i).clie_oid_clie;
           
           /* Actualizando Tipo */
           IF lbencontro THEN
              lsTipogrupo := '1';
              lsCampaCursor := lsPeriodo;
           ELSE
              lsTipogrupo := '2';
              lsCampaCursor := lsCampAnter;
           END IF;    
           
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.cod_peri = lsCampaCursor,
               x.tip_grup = lsTipogrupo,
               x.cod_cons = lsCodigoCliente
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;    
           
           /* Actualizando Ventas */
           lsCampaCursorIni := lsCampaCursor;
           lnMontoTotal := 0;
           lncontador := 0;
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor);
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF;
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_1era = lnMonto,
               x.cod_peri_1era = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;                      
           
           
           lsCampaCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampaCursorIni, lnIdPais, lnIdMarca, lnIdCanal, -1);
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor);  
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF; 
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_2da = lnMonto,
               x.cod_peri_2da = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;    
           
           lsCampaCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampaCursorIni, lnIdPais, lnIdMarca, lnIdCanal, -2);
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor); 
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF;  
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_3era = lnMonto,
               x.cod_peri_3era = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;  
           
           lsCampaCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampaCursorIni, lnIdPais, lnIdMarca, lnIdCanal, -3);
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor);
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF;   
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_4ta = lnMonto,
               x.cod_peri_4ta = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;    
           
           lsCampaCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampaCursorIni, lnIdPais, lnIdMarca, lnIdCanal, -4);
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor);
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF;   
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_5ta = lnMonto,
               x.cod_peri_5ta = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie;    
           
           lsCampaCursor := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCampaCursorIni, lnIdPais, lnIdMarca, lnIdCanal, -5);
           lnOidPeriodoCursor := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampaCursor);
           lnMonto := LEC_FN_DEVUE_VENTA_CONSU(lnOidTipoSoliPais,
                                  consultorasRegistro(i).clie_oid_clie,
                                  lnOidPeriodoCursor); 
           lnMontoTotal := lnMontoTotal + lnMonto;
           IF (lnMonto > 0) THEN
              lncontador := lncontador + 1;
           END IF;  
           UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
           SET x.mon_vent_6ta = lnMonto,
               x.cod_peri_6ta = lsCampaCursor
           WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie; 
           
           /* Actualizando Promedio */
           IF (lncontador > 0) THEN
              UPDATE LEC_TMP_OBTIE_VENTA_CONSU x
              SET x.mon_prom = lnMontoTotal / lncontador
              WHERE x.clie_oid_clie = consultorasRegistro(i).clie_oid_clie; 
           END IF;
       END LOOP;
    END IF;
    EXIT WHEN cConsultoras%NOTFOUND;
  END LOOP;
  CLOSE cConsultoras;
  
  COMMIT;
  
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR LEC_PR_OBTIE_VENTA_CONSU: '||ls_sqlerrm);
END LEC_PR_OBTIE_VENTA_CONSU ;

/***********************************************************************************************
  Descripcion       : Obtiene el Codigo SAP- Pago Socias Empresarias
  Fecha Creacion    : 10/12/2015
  Autor             : Karina Valencia
  ***********************************************************************************************/
FUNCTION LEC_FN_OBTE_CODIG_SAP(psCodigoCliente VARCHAR2)                                
RETURN VARCHAR2 
IS
    lnCodigoSap VARCHAR2(25);
    lnCodInicio VARCHAR2(25);
    lnCodFinal VARCHAR2(25);
BEGIN
   
  BEGIN
    select cod_prov
      into lnCodInicio
      from mae_clien_lider
     where cod_clid = psCodigoCliente;
  EXCEPTION
    WHEN OTHERS THEN
      lnCodInicio := NULL;
  END;    
  --EVALUAR COD SAP
  IF(lnCodInicio IS NOT NULL)THEN
     lnCodigoSap:=lnCodInicio;
  ELSE
    BEGIN
    select cod_prov
      INTO lnCodFinal
      from mae_clien_datos_adici
     where clie_oid_clie =
           (select oid_clie from mae_clien where cod_clie = psCodigoCliente);
    EXCEPTION
    WHEN OTHERS THEN
      lnCodFinal := NULL;
    END;  
     
     IF(lnCodFinal IS NOT NULL)THEN
     lnCodigoSap:=lnCodFinal;     
     ELSE lnCodigoSap:=NULL;
     END IF;       
  END IF; 
  
RETURN lnCodigoSap;

EXCEPTION
WHEN OTHERS THEN
     RETURN NULL;
END LEC_FN_OBTE_CODIG_SAP; 

/***************************************************************************
Descripcion       : Calcular Recuperacion de las consultoras en una campaña.
                    Procedimiento integrador.
Fecha Creacion    : 01/02/2016
Autor             : Carlos Mori
***************************************************************************/
PROCEDURE LEC_PR_CALCU_RECAU(
                             psCodigoPais        VARCHAR2,
                             psCodigoPrograma    VARCHAR2,
                             psCampannaProceso   VARCHAR2,
                             psFechaProceso      VARCHAR2,
                             psCampannaRecaudo   VARCHAR2,
                             psCodigoGrupoRegion VARCHAR2,
                             pnCodigoTramo       INTEGER,
                             psCodigoUsuario     VARCHAR2
                            )
IS

-- Curso Base para la ejecución de los procesos anidados
CURSOR c_Base( vnOidCampReca NUMBER, vsCampannaRecaudo VARCHAR2, vsCampannaCierre VARCHAR2  )
IS

WITH temp AS (
              SELECT psCodigoPais pais_cod_pais,
                     grup.cod_grup,
                     zorg.cod_regi,
                     zzon.cod_zona,
                     zscc.cod_secc
                FROM mae_clien_unida_admin cuad,
                     mae_clien_datos_adici clda,
                     zon_terri_admin ztad,
                     zon_secci zscc,
                     zon_zona zzon,
                     zon_regio zorg,
                     (
                      SELECT lgrr.pais_cod_pais, lgrr.cod_regi, lgrr.lgpr_cod_grup_regi cod_grup
                        FROM lec_grupo_proce_recau_regio lgrr,
                             lec_grupo_proce_recau lgpr
                       WHERE 1=1
                         AND lgrr.pais_cod_pais = lgpr.pais_cod_pais
                         AND lgrr.lgpr_cod_grup_regi = lgpr.cod_grup_regi
                         AND lgrr.lgpr_cod_grup_regi = psCodigoGrupoRegion
                     ) grup
               WHERE 1=1
                 AND cuad.clie_oid_clie = clda.clie_oid_clie
                 AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 --
                 AND vnOidCampReca BETWEEN cuad.perd_oid_peri_ini AND NVL( cuad.perd_oid_peri_fin, vnOidCampReca )
                 AND zorg.ind_cier = '1'
                 AND zorg.ind_ofic IS NULL
                 AND zorg.cod_regi = grup.cod_regi(+)
               GROUP BY grup.cod_grup,
                        zorg.cod_regi,
                        zzon.cod_zona,
                        zscc.cod_secc
             )
SELECT temp.pais_cod_pais,
       temp.cod_grup,
       temp.cod_regi,
       temp.cod_zona,
       temp.cod_secc,
       lide.cod_lide,
       NVL((
             SELECT lniv.lniv_cod_nive
               FROM lec_lider_nivel lniv
              WHERE lniv.cod_lide = lide.cod_lide
                AND psCampannaProceso BETWEEN lniv.cam_inic AND NVL( lniv.cam_fin, psCampannaProceso )
                AND lniv.ind_tipo_nive = 'R'
           ),'01') cod_nive_reca,
       --dcob.lcot.pais_cod_pais,
       dcob.lpro_cod_prog,
       dcob.lpag_sec_ambi_geog,
       dcob.ltug_cod_tipo_uso_geog,
       dcob.ltmc_cod_tipo_medi_cobr,
       dcob.lpct_cod_tram,
       dcob.ind_pais,
       dcob.cod_regi cod_regi_cobr,
       dcob.cod_zona cod_zona_cobr,
       dcob.cam_regi,
       dcob.num_dias,
       dcob.num_dias_extr,
       dcob.val_porc_cobr,
       dcob.val_porc_mini_cobr,
       NVL(cier.fec_cier,TRUNC(SYSDATE)) fec_cier,
       CASE
          WHEN cier.fec_cier IS NOT NULL THEN
               ( cier.fec_cier + NVL(dcob.num_dias_extr,0))
          ELSE
               NVL(cier.fec_cier,TRUNC(SYSDATE))
       END fec_cier_extr
  FROM temp,
       (
        SELECT hger.cod_regi, hger.cod_zona, hger.cod_secc, hger.gere cod_lide
          FROM zon_histo_geren hger
         WHERE 1=1
           AND hger.cod_secc IS NOT NULL
           AND vnOidCampReca BETWEEN hger.perd_oid_peri_desd AND NVL( hger.perd_oid_peri_hast, vnOidCampReca )
       ) lide,
       (
        SELECT lcot.pais_cod_pais,
               lcot.lpro_cod_prog,
               lcot.lpag_sec_ambi_geog,
               lcot.ltug_cod_tipo_uso_geog,
               lcot.ltmc_cod_tipo_medi_cobr,
               lcot.lpct_cod_tram,
               lpag.ind_pais,
               lpag.cod_regi,
               lpag.cod_zona,
               lpag.cam_regi,
               lcot.num_dias,
               lcot.num_dias_extr,
               lcot.val_porc_cobr,
               lcot.val_porc_mini_cobr
          FROM lec_progr_cobra_objet_tramo lcot,
               lec_progr_cobra_objet lpco,
               lec_progr_ambit_geogr lpag
         WHERE 1=1
           AND lcot.pais_cod_pais = lpco.pais_cod_pais
           AND lcot.lpro_cod_prog = lpco.lpro_cod_prog
           AND lcot.lpag_sec_ambi_geog = lpco.lpag_sec_ambi_geog
           AND lcot.ltug_cod_tipo_uso_geog = lpco.ltug_cod_tipo_uso_geog
           AND lcot.ltmc_cod_tipo_medi_cobr = lpco.ltmc_cod_tipo_medi_cobr
           --
           AND lpco.pais_cod_pais = lpag.pais_cod_pais
           AND lpco.lpro_cod_prog = lpag.lpro_cod_prog
           AND lpco.ltug_cod_tipo_uso_geog = lpag.ltug_cod_tipo_uso_geog
           AND lpco.lpag_sec_ambi_geog = lpag.sec_ambi_geog
           --
           AND lpag.ltug_cod_tipo_uso_geog = '01'
           AND lpag.pais_cod_pais = psCodigoPais
           AND lpag.lpro_cod_prog = psCodigoPrograma
           AND lpag.ind_acti = 1
           AND lcot.lpct_cod_tram = pnCodigoTramo
       ) dcob,
       (
        SELECT pcie.cod_pais,
               pcie.cod_regi,
               pcie.cod_zona,
               pcie.fec_cier
          FROM fac_progr_cierr pcie
         WHERE pcie.cod_pais = psCodigoPais
           AND pcie.cam_proc = vsCampannaCierre
           AND pcie.est_cier = 'P'
           AND pcie.tip_cier IN ('Z','R')
       ) cier
 WHERE temp.cod_regi = lide.cod_regi(+)
   AND temp.cod_zona = lide.cod_zona(+)
   AND temp.cod_secc = lide.cod_secc(+)
   --
   AND temp.pais_cod_pais = dcob.pais_cod_pais(+)
   AND temp.cod_regi = NVL(dcob.cod_regi(+),temp.cod_regi)
   AND temp.cod_zona = NVL(dcob.cod_zona(+),temp.cod_zona)
   --
   AND temp.pais_cod_pais = cier.cod_pais(+)
   AND temp.cod_regi = cier.cod_regi(+)
   AND temp.cod_zona = cier.cod_zona(+)
   --
   AND lide.cod_lide IS NOT NULL -- Solo secciones con líderes nombradas en la campaña recaudo
   AND NVL(temp.cod_grup,'X') = NVL(psCodigoGrupoRegion,'X')
 ORDER BY 1,2,3;

TYPE c_Base_t IS TABLE OF c_Base%ROWTYPE INDEX BY BINARY_INTEGER;
RegBase c_Base_t;

CURSOR c_CamposDeducc IS
SELECT ltmo.nom_camp_pedi
  FROM lec_tipo_movim ltmo
 WHERE ltmo.ind_dedu = '1'
     ;

-- Variables
lnOidCampProc     CRA_PERIO.Oid_Peri%TYPE := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCampannaProceso );
lnOidCampannaAnte CRA_PERIO.Oid_Peri%TYPE;
lnOidCampReca     CRA_PERIO.Oid_Peri%TYPE;
lsNumCampAnte     BAS_PARAM_PAIS.VAL_PARA%TYPE;
lsCampannaRecaudo SEG_PERIO_CORPO.COD_PERI%TYPE;
lsCampannaCierre  SEG_PERIO_CORPO.COD_PERI%TYPE;
lnIndVentaRetail  INTEGER;
lnExisteDeducc    INTEGER:= 0;
lsCamposDeducc    VARCHAR2(500):= NULL;
lsNombreCampo     VARCHAR2(100):= NULL;
lnTasaImpuesto    PED_TASA_IMPUE.Val_Tasa_Impu%TYPE;
lsIndReversion    BAS_PARAM_PAIS.VAL_PARA%TYPE;

BEGIN

    -- Obtener el monto del impuesto de venta del pais
    BEGIN
        SELECT val_tasa_impu
          INTO lnTasaImpuesto
          FROM ped_tasa_impue taim
         WHERE val_indi_impu IN ('IVA','IGV');
    EXCEPTION WHEN NO_DATA_FOUND THEN
        lnTasaImpuesto := '0';
    END;

    -- Obtener el factor para calcular campanna de recaudo
    BEGIN
        SELECT val_para
          INTO lsNumCampAnte
          FROM bas_param_pais
         WHERE cod_pais = psCodigoPais
           AND cod_sist = 'LET'
           AND UPPER( nom_para ) = 'INDCAMPAANTE';
    EXCEPTION WHEN NO_DATA_FOUND THEN
        lsNumCampAnte := '0';
    END;

    -- Obtener el indicador venta retail
    BEGIN
        SELECT VAL_PARA
          INTO lnIndVentaRetail
          FROM BAS_PARAM_PAIS
         WHERE COD_SIST = 'LET'
           AND UPPER(NOM_PARA) = 'INDVENTARETAIL'
           AND IND_ACTI = '1'
           AND COD_PAIS = psCodigoPais;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        lnIndVentaRetail := '0';
    END;

    -- Obtener indicador de Reversión de Estatus
    BEGIN
         SELECT bpp.val_para
           INTO lsIndReversion
           FROM bas_param_pais bpp
          WHERE bpp.cod_pais = pscodigopais
            AND bpp.cod_sist = 'MAE'
            AND UPPER(bpp.nom_para) = 'INDREVERSIONESTATUS'
            AND bpp.ind_acti = '1';
    EXCEPTION WHEN NO_DATA_FOUND THEN
         lsIndReversion := '0';
    END;
    -- Verifica si aplica factor de deduccion a la cuenta corriente
    SELECT COUNT(1)
      INTO lnExisteDeducc
      FROM lec_tipo_movim ltmo
     WHERE ltmo.ind_dedu = '1'
         ;
    IF lnExisteDeducc > 0 THEN
        OPEN c_CamposDeducc;
        LOOP
           FETCH c_CamposDeducc INTO lsNombreCampo;
           EXIT WHEN c_CamposDeducc%NOTFOUND;
           lsCamposDeducc := lsCamposDeducc || CASE WHEN lsCamposDeducc IS NULL THEN lsNombreCampo ELSE '+' || lsNombreCampo END;
        END LOOP;
        CLOSE c_CamposDeducc;
        lsCamposDeducc := 'SELECT ' || lsCamposDeducc || ' FROM PED_SOLIC_CABEC WHERE oid_soli_cabe = ';
    END IF;

    -- Calcula campanna de recaudo
    IF psCampannaRecaudo IS NULL THEN
       lsCampannaRecaudo := GEN_FN_CALCU_PERIO( psCampannaProceso, TO_NUMBER( lsNumCampAnte ) * -1 );
    ELSE
       lsCampannaRecaudo := psCampannaRecaudo;
    END IF;

    lnOidCampReca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( lsCampannaRecaudo );

    -- Calcula Campanna de Cierre
    lsCampannaCierre := GEN_PKG_GENER.GEN_FN_PERIO_NSIGU(psCodigoPais, lsCampannaRecaudo, 1);

    -- Calcula Campanna Anterior al Recaudo
    lnOidCampannaAnte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( GEN_PKG_GENER.GEN_FN_PERIO_NSIGU( psCodigoPais,lsCampannaRecaudo,-1) );

    -- Se Procesan las campañas correspondientes
    OPEN c_Base( lnOidCampReca, lsCampannaRecaudo, lsCampannaCierre );
         LOOP
            FETCH c_Base BULK COLLECT INTO regBase;
            IF regBase.COUNT > 0 THEN
               FOR p IN regBase.FIRST .. regBase.LAST LOOP
                   -- Generar información de pedidos
                   lec_pkg_proce.lec_pr_gener_pedid_campa(pscodigopais => pscodigopais,
                                                          pscampannarecaudo => lsCampannaRecaudo,
                                                          pnOidcampannarecaudo => lnOidCampReca,
                                                          pnOidCampannaAnterior => lnOidCampannaAnte,
                                                          pscodigoprograma => psCodigoPrograma,
                                                          pscodigoregion => regBase(p).cod_regi,
                                                          pscodigozona => regBase(p).cod_zona,
                                                          pscodigoseccion => regBase(p).cod_secc,
                                                          pdfechalimite => regBase(p).fec_cier_extr,
                                                          psCamposDeducc => lsCamposDeducc,
                                                          pnTasaImpuesto => lnTasaImpuesto,
                                                          psIndReversion => lsIndReversion,
                                                          pscodigousuario => pscodigousuario);

                   -- Generar información de recaudos
                   LEC_PKG_PROCE.lec_pr_gener_base_recup(pscodigopais => psCodigoPais,
                                                         pscodigoprograma => psCodigoPrograma,
                                                         pscampannaproceso => psCampannaProceso,
                                                         pscampannarecaudo => lsCampannaRecaudo,
                                                         pnoidcampannarecaudo => lnOidCampReca,
                                                         psfechaproceso => psFechaProceso,
                                                         pscodigoregion => regBase(p).cod_regi,
                                                         pscodigozona => regBase(p).cod_zona,
                                                         pscodigoseccion => regBase(p).cod_secc,
                                                         pdfechalimite => regBase(p).fec_cier,
                                                         pdfechalimiteextra => regBase(p).fec_cier_extr,
                                                         pncodigotramo => pnCodigoTramo,
                                                         pscodigousuario => psCodigoUsuario);

                   -- Generar información de venta retail
                   IF lnIndVentaRetail = '1' THEN
                      lec_pkg_proce.lec_pr_gener_venta_retai(pscodigopais => psCodigoPais,
                                                             pscampannarecaudo => lsCampannaRecaudo,
                                                             pnoidcampannarecaudo => lnOidCampReca,
                                                             pscodigoprograma => psCodigoPrograma,
                                                             pscodigoregion => regBase(p).cod_regi,
                                                             pscodigozona => regBase(p).cod_zona,
                                                             pscodigoseccion => regBase(p).cod_secc,
                                                             pdfechalimite => regBase(p).fec_cier_extr,
                                                             pscodigousuario => psCodigoUsuario);
                   END IF;

                   -- Generar información de flexipagos
                   lec_pkg_proce.lec_pr_gener_cuota_flexi(pscodigopais => pscodigopais,
                                                          pscampannarecaudo => lsCampannaRecaudo,
                                                          pnoidcampannarecaudo => lnOidCampReca,
                                                          pscodigoprograma => psCodigoPrograma,
                                                          pscodigoregion => regBase(p).cod_regi,
                                                          pscodigozona => regBase(p).cod_zona,
                                                          pscodigoseccion => regBase(p).cod_secc,
                                                          pdfechalimite => regBase(p).fec_cier_extr,
                                                          pscodigousuario => pscodigousuario);

                   -- Calcular resumen de recaudos
                   lec_pkg_proce.lec_pr_gener_resum_recup(pscodigopais => pscodigopais,
                                                          psCampannaProceso => psCampannaProceso,
                                                          pscampannarecaudo => lsCampannaRecaudo,
                                                          pnoidcampannarecaudo => lnOidCampReca,
                                                          pscodigoprograma => psCodigoPrograma,
                                                          pscodigoregion => regBase(p).cod_regi,
                                                          pscodigozona => regBase(p).cod_zona,
                                                          pscodigoseccion => regBase(p).cod_secc,
                                                          pnCodigoTramo => pnCodigoTramo,
                                                          pnSecuenciaAmbitoGeo => regBase(p).lpag_sec_ambi_geog,
                                                          pnPorcenCobr => regBase(p).val_porc_cobr,
                                                          pnPorcenCobrMini => regBase(p).val_porc_mini_cobr,
                                                          pscodigousuario => pscodigousuario);

                   -- Calcular comision
                   lec_pkg_proce.lec_pr_calcu_comis_consu(pscodigopais => pscodigopais,
                                                          psCampannaProceso => psCampannaProceso,
                                                          pscampannarecaudo => lsCampannaRecaudo,
                                                          pnoidcampannarecaudo => lnOidCampReca,
                                                          pscodigoprograma => psCodigoPrograma,
                                                          pscodigoregion => regBase(p).cod_regi,
                                                          pscodigozona => regBase(p).cod_zona,
                                                          pscodigoseccion => regBase(p).cod_secc,
                                                          pnCodigoTramo => pnCodigoTramo,
                                                          pnSecuenciaAmbitoGeo => regBase(p).lpag_sec_ambi_geog,
                                                          pnPorcenCobr => regBase(p).val_porc_cobr,
                                                          pnPorcenCobrMini => regBase(p).val_porc_mini_cobr,
                                                          pscodigousuario => pscodigousuario);

                   -- Generar registros de ganancias por recuperacion
                   lec_pkg_proce.lec_pr_gener_ganan_Recup(pscodigopais => pscodigopais,
                                                          psCampannaProceso => psCampannaProceso,
                                                          pscampannarecaudo => lsCampannaRecaudo,
                                                          pnoidcampannarecaudo => lnOidCampReca,
                                                          pscodigoprograma => psCodigoPrograma,
                                                          pscodigoregion => regBase(p).cod_regi,
                                                          pscodigozona => regBase(p).cod_zona,
                                                          pscodigoseccion => regBase(p).cod_secc,
                                                          pnCodigoTramo => pnCodigoTramo,
                                                          pscodigousuario => pscodigousuario);
               END LOOP;
            END IF;
            EXIT WHEN c_Base%NOTFOUND;
         END LOOP;
    CLOSE c_Base;
EXCEPTION WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,'ERROR LEC_PR_CALCU_RECAU: ' || ls_sqlerrm);
END LEC_PR_CALCU_RECAU;

/***********************************************************************************************
  Descripcion       : Genera información de recaudos de las consultoras en una campaña específica
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_BASE_RECUP
(
  psCodigoPais         VARCHAR2,
  psCodigoPrograma     VARCHAR2,
  psCampannaProceso    VARCHAR2,
  psCampannaRecaudo    VARCHAR2,
  pnOidCampannaRecaudo NUMBER,
  psFechaProceso       VARCHAR2,
  psCodigoRegion       VARCHAR2,
  psCodigoZona         VARCHAR2,
  psCodigoSeccion      VARCHAR2,
  pdFechaLimite        DATE,
  pdFechaLimiteExtra DATE,
  pnCodigoTramo      INTEGER,
  psCodigoUsuario    VARCHAR2
)
IS

-- Cursor base para ultimas transacciones

CURSOR c_Base( vnOidCampReca NUMBER )
IS

SELECT mvcc.clie_oid_clie,
       clie.cod_clie
  FROM ccc_movim_cuent_corri mvcc,
       mae_clien clie,
       ccc_subpr subp,
       ccc_proce ccpr,
       mae_clien_unida_admin cuad,
       zon_terri_admin ztad,
       zon_secci zscc,
       zon_zona zzon,
       zon_regio zorg,
       (
        SELECT a.cod_proc, a.cod_subp, a.ltmo_cod_movi, b.des_movi, a.ind_acti,
               b.nom_camp_pedi, b.ind_tipo_movi, b.ind_dedu, b.ind_acti
          FROM lec_proc_subpr a,
               lec_tipo_movim b
         WHERE 1=1
           AND a.pais_cod_pais = b.pais_cod_pais
           AND a.ltmo_cod_movi = b.cod_movi
           AND NVL(b.ind_dedu,'0') = '0'
           AND b.ind_tipo_movi = 'C'
       ) com,
       (
        SELECT lpex.cod_cons
          FROM lec_progr_lista_exclu lpex
         WHERE psCampannaRecaudo BETWEEN lpex.cam_inic_vige AND lpex.cam_fin_vige
           AND lpex.ind_acti = '1'
         GROUP BY lpex.cod_cons
       ) excl
 WHERE 1=1
   AND mvcc.clie_oid_clie = clie.oid_clie
   AND mvcc.subp_oid_subp_crea = subp.oid_subp
   AND subp.ccpr_oid_proc = ccpr.oid_proc
   AND ccpr.cod_proc = com.cod_proc
   AND subp.cod_subp = com.cod_subp
   AND clie.cod_clie = excl.cod_cons(+)
   --
   AND mvcc.clie_oid_clie = cuad.clie_oid_clie
   AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
   AND ztad.zscc_oid_secc = zscc.oid_secc
   AND zscc.zzon_oid_zona = zzon.oid_zona
   AND zzon.zorg_oid_regi = zorg.oid_regi
   AND vnOidCampReca BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,vnOidCampReca)
   --
   AND excl.cod_cons IS NULL
   AND mvcc.perd_oid_peri = vnOidCampReca
   AND zorg.cod_regi = NVL(psCodigoRegion,zorg.cod_regi)
   AND zzon.cod_zona = NVL(psCodigoZona,zzon.cod_zona)
   AND zscc.cod_secc = NVL(psCodigoSeccion,zscc.cod_secc)
   --
   AND mvcc.imp_movi > 0
 GROUP BY mvcc.clie_oid_clie, clie.cod_clie;

TYPE c_Base_t IS TABLE OF c_Base%ROWTYPE INDEX BY BINARY_INTEGER;
RegBase c_Base_t;

CURSOR c_Movim( vnOidClie NUMBER, vnOidCampaFact NUMBER, vsTipoMovim VARCHAR2 ) IS
 SELECT mvcc.oid_movi_cc,
        ccpr.cod_proc,
        subp.cod_subp,
        com.ind_tipo_movi tip_comi_crea,
        com2.ind_tipo_movi tip_comi_ulti,
        mvcc.fec_docu,
        mvcc.fec_ulti_movi,
        mvcc.imp_movi,
        mvcc.imp_pago,
        mvcc.soca_oid_soli_cabe
   FROM ccc_movim_cuent_corri mvcc,
        lec_consu_pedid_campa lcpc,
        ccc_subpr subp,
        ccc_proce ccpr,
        (
          SELECT a.cod_proc, a.cod_subp, b.ind_tipo_movi
            FROM lec_proc_subpr a,
                 lec_tipo_movim b
           WHERE 1 = 1
             AND a.pais_cod_pais = b.pais_cod_pais
             AND a.ltmo_cod_movi = b.cod_movi
             AND NVL(b.ind_dedu,'0') = '0'
             AND b.ind_tipo_movi IN ('C')
        ) com,
        ccc_subpr subp2,
        ccc_proce ccpr2,
        (
          SELECT a.cod_proc, a.cod_subp, b.ind_tipo_movi
            FROM lec_proc_subpr a,
                 lec_tipo_movim b
           WHERE 1 = 1
             AND a.pais_cod_pais = b.pais_cod_pais
             AND a.ltmo_cod_movi = b.cod_movi
             AND NVL(b.ind_dedu,'0') = '0'
             AND b.ind_tipo_movi IN ('C','P','R')
        ) com2
  WHERE 1=1
    AND mvcc.subp_oid_subp_crea = subp.oid_subp
    AND subp.ccpr_oid_proc = ccpr.oid_proc
    AND ccpr.cod_proc = com.cod_proc
    AND subp.cod_subp = com.cod_subp
    --
    AND mvcc.subp_oid_subp_ulti = subp2.oid_subp
    AND subp2.ccpr_oid_proc = ccpr2.oid_proc
    AND ccpr2.cod_proc = com2.cod_proc
    AND subp2.cod_subp = com2.cod_subp
    --
    AND mvcc.soca_oid_soli_cabe = lcpc.soca_oid_soli_cabe(+)
    --
    AND mvcc.clie_oid_clie = vnOidClie
    AND mvcc.perd_oid_peri = vnOidCampaFact
    AND mvcc.imp_movi > 0;

TYPE c_Movim_t IS TABLE OF c_Movim%ROWTYPE INDEX BY BINARY_INTEGER;
regMovim c_Movim_t;

CURSOR c_Histo( vnOidMovi NUMBER, vdFechaLimite DATE, vsTipoMovim VARCHAR2 ) IS
SELECT com.ind_tipo_movi,
       ccpr.cod_proc,
       subp.cod_subp,
       SUM( hmcc.imp_movi ) imp_movi,
       SUM( hmcc.imp_pago ) imp_pago
  FROM ccc_histo_movim_cc hmcc,
       ccc_subpr subp,
       ccc_proce ccpr,
       (
         SELECT a.cod_proc, a.cod_subp, b.ind_tipo_movi
           FROM lec_proc_subpr a,
                lec_tipo_movim b
          WHERE 1 = 1
            AND a.pais_cod_pais = b.pais_cod_pais
            AND a.ltmo_cod_movi = b.cod_movi
            AND NVL(b.ind_dedu,'0') = '0'
            AND b.ind_tipo_movi = vsTipoMovim
       ) com
 WHERE 1=1
   AND hmcc.subp_oid_subp = subp.oid_subp
   AND subp.ccpr_oid_proc = ccpr.oid_proc
   AND ccpr.cod_proc = com.cod_proc
   AND subp.cod_subp = com.cod_subp
   --
   AND hmcc.mvcc_oid_movi_cc = vnOidMovi
   AND hmcc.fec_movi <= vdFechaLimite
 GROUP BY com.ind_tipo_movi,
          ccpr.cod_proc,
          subp.cod_subp;

TYPE c_Histo_t IS TABLE OF c_Histo%ROWTYPE INDEX BY BINARY_INTEGER;
regHisto c_Histo_t;

-- Variables
lnSumaCargos NUMBER(12,2) :=0;
lnSumaAbonos NUMBER(12,2) :=0;
lnSumaCDRS   NUMBER(12,2) :=0;
lnPorcentaje NUMBER(12,2) :=0;

BEGIN
   -- Procesa consultoras de la seccion
   OPEN c_Base( pnOidCampannaRecaudo );
        LOOP
        FETCH c_Base BULK COLLECT INTO regBase LIMIT w_filas;
              IF regBase.COUNT > 0 THEN
                 FOR x IN regBase.FIRST .. regBase.LAST LOOP

                     -- Procesa movimientos de cuenta corriente de la consultora
                     OPEN c_Movim( regBase(x).clie_oid_clie, pnOidCampannaRecaudo, 'C' );
                          LOOP
                          FETCH c_Movim BULK COLLECT INTO regMovim LIMIT w_filas;
                                IF regMovim.COUNT > 0 THEN
                                   FOR y IN regMovim.FIRST .. regMovim.LAST LOOP

                                       -- Se suman los cargos
                                       IF regMovim(y).tip_comi_crea = 'C' THEN
                                           lnSumaCargos := lnSumaCargos + regMovim(y).imp_movi;
                                       END IF;

                                       -- Se suman los abonos
                                       IF regMovim(y).tip_comi_ulti = 'P' AND regMovim(y).fec_ulti_movi <= pdFechaLimiteExtra THEN
                                          lnSumaAbonos := lnSumaAbonos + regMovim(y).imp_pago;
                                       END IF;

                                       -- Se suman los reclamos
                                       IF regMovim(y).tip_comi_ulti = 'R' AND regMovim(y).fec_ulti_movi <= pdFechaLimiteExtra THEN
                                          lnSumaCDRS := lnSumaCDRS + regMovim(y).imp_pago;
                                       END IF;

                                       -- Se suman los abonos del histórico
                                       OPEN c_Histo( regMovim(y).oid_movi_cc, pdFechaLimiteExtra, 'P' );
                                            LOOP
                                            FETCH c_Histo BULK COLLECT INTO regHisto LIMIT w_filas;
                                                  IF regHisto.COUNT > 0 THEN
                                                     FOR z IN regHisto.FIRST .. regHisto.LAST LOOP
                                                         lnSumaAbonos := lnSumaAbonos + regHisto(z).imp_pago;
                                                     END LOOP;
                                                  END IF;
                                            EXIT WHEN c_Histo%NOTFOUND;
                                            END LOOP;
                                       CLOSE c_Histo;

                                       -- Se suman los reclamos del histórico
                                       OPEN c_Histo( regMovim(y).oid_movi_cc, pdFechaLimiteExtra, 'R' );
                                            LOOP
                                            FETCH c_Histo BULK COLLECT INTO regHisto LIMIT w_filas;
                                                  IF regHisto.COUNT > 0 THEN
                                                     FOR a IN regHisto.FIRST .. regHisto.LAST LOOP
                                                         lnSumaCDRS := lnSumaCDRS + regHisto(a).imp_pago;
                                                     END LOOP;
                                                  END IF;
                                            EXIT WHEN c_Histo%NOTFOUND;
                                            END LOOP;
                                       CLOSE c_Histo;

                                       -- Actualiza valores de cargos y abonos
                                       --lnSumaCargos := lnSumaCargos - lnSumaCDRS;
                                       --lnSumaAbonos := lnSumaAbonos;

                                       -- Se graba el registro de la consultora
                                       lnPorcentaje := CASE WHEN ( lnSumaCargos - lnSumaCDRS ) > 0 THEN
                                                                 ROUND((lnSumaAbonos/( lnSumaCargos - lnSumaCDRS ) )*100,2)
                                                            ELSE 0
                                                       END;
                                       -- Guardar datos procesados
                                       IF lnSumaCargos >= 0 THEN
                                          BEGIN
                                               INSERT INTO LEC_BASE_RECUP_CONSU
                                               SELECT psCodigoPais pais_cod_pais,
                                                      psCampannaRecaudo cam_reca,
                                                      regBase(x).cod_clie cod_cons,
                                                      regMovim(y).oid_movi_cc mvcc_oid_movi_cc,
                                                      pnCodigoTramo lpct_cod_tram,
                                                      psCampannaProceso cam_proc,
                                                      lnSumaCargos val_mont_carg,
                                                      lnSumaAbonos val_mont_abon,
                                                      lnSumaCDRS val_mont_cdrs,
                                                      0 val_mont_ncom,
                                                      0 val_mont_carg_neto,
                                                      0 val_mont_abon_neto,
                                                      regMovim(y).soca_oid_soli_cabe,
                                                      NULL fec_inic_abon,
                                                      pdFechaLimite fec_limi_abon,
                                                      psCodigoRegion cod_regi,
                                                      psCodigoZona cod_zona,
                                                      psCodigoSeccion cod_secc,
                                                      'N' ind_pedi_cons,
                                                      'N' ind_cuot_flex,
                                                      psCodigoPrograma lpro_cod_prog,
                                                      psCodigoUsuario usu_crea,
                                                      SYSDATE fec_crea,
                                                      NULL usu_modi,
                                                      NULL fec_modi,
                                                      1 ind_acti
                                                 FROM DUAL;
                                          EXCEPTION WHEN dup_val_on_index THEN
                                               UPDATE LEC_BASE_RECUP_CONSU lbrc
                                                  SET lbrc.lpct_cod_tram = pnCodigoTramo,
                                                      lbrc.val_mont_carg = lnSumaCargos,
                                                      lbrc.val_mont_abon = lnSumaAbonos,
                                                      lbrc.val_mont_cdrs = lnSumaCDRS,
                                                      lbrc.soca_oid_soli_cabe = regMovim(y).soca_oid_soli_cabe,
                                                      lbrc.fec_inic_abon = NULL,
                                                      lbrc.fec_limi_abon = pdFechaLimite,
                                                      lbrc.cod_regi = psCodigoRegion,
                                                      lbrc.cod_zona = psCodigoZona,
                                                      lbrc.cod_secc = psCodigoSeccion,
                                                      lbrc.ind_pedi_cons = 'N',
                                                      lbrc.ind_cuot_flex = 'N',
                                                      lbrc.lpro_cod_prog = psCodigoPrograma,
                                                      lbrc.usu_modi = psCodigoUsuario,
                                                      lbrc.fec_modi = SYSDATE
                                                WHERE 1=1
                                                  AND lbrc.pais_cod_pais = psCodigoPais
                                                  AND lbrc.cam_reca = psCampannaRecaudo
                                                  AND lbrc.cod_cons = regBase(x).cod_clie
                                                  AND lbrc.mvcc_oid_movi_cc = regMovim(y).oid_movi_cc
                                                  AND lbrc.lpct_cod_tram = pnCodigoTramo
                                                  AND TRUNC(lbrc.fec_limi_abon) <= pdFechaLimiteExtra
                                                    ;
                                          END;
                                       END IF;

                                       -- Inicializar variables de acumulación
                                       lnSumaCargos :=0;
                                       lnSumaAbonos :=0;
                                       lnSumaCDRS   :=0;
                                       lnPorcentaje :=0;
                                   END LOOP;
                                END IF;
                                EXIT WHEN c_Movim%NOTFOUND;
                          END LOOP;
                     CLOSE c_Movim;
                 END LOOP;
              END IF;
              EXIT WHEN c_Base%NOTFOUND;
        END LOOP;
     CLOSE c_Base;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_GENER_BASE_RECUP: '||ls_sqlerrm);
END LEC_PR_GENER_BASE_RECUP;

/***********************************************************************************************
  Descripcion       : Genera información de Pedidos de las consultoras de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_PEDID_CAMPA
(
 psCodigoPais         VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 pnOidCampannaAnterior INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pdFechaLimite        DATE,
 psCamposDeducc       VARCHAR2,
 pnTasaImpuesto       NUMBER,
 psIndReversion       VARCHAR2,
 psCodigoUsuario      VARCHAR2
) IS

-- Curso Base para la ejecución de los pedidos
CURSOR c_Pedidos
IS
WITH temp AS
     (
      SELECT psCodigoPais PAIS_COD_PAIS,
             clie.cod_clie COD_CONS,
             psCampannaRecaudo CAM_PROC,
             soca.val_nume_soli VAL_NUME_SOLI,
             zorg.cod_regi COD_REGI,
             zzon.cod_zona COD_ZONA,
             zscc.cod_secc COD_SECC,
             soca.soca_oid_soli_cabe SOCA_OID_SOLI_CABE,
             (
              SELECT SUM( CASE
                              WHEN sopo.espo_oid_esta_posi <> 2 THEN
                                   NVL(sopo.num_unid_dema_real,0) * NVL(sopo.val_prec_cata_unit_loca,0)
                              ELSE 0
                              END
                         )
                FROM ped_solic_posic sopo
               WHERE sopo.soca_oid_soli_cabe = soca.oid_soli_cabe
             ) VAL_MONT_CATA,
             soca.val_tota_paga_loca VAL_MONT_FACT,
             0 VAL_MONT_FLEX,
             0 VAL_MONT_NCOM,
             CASE
               WHEN NVL(clhe.esta_oid_esta_clie,1) IN (4,5) THEN 'N'
               ELSE 'S'
             END IND_PEDI_CONS,
             CASE WHEN (
                        SELECT COUNT(1)
                          FROM ped_solic_cabec     soca2,
                               ped_solic_cabec     cons2,
                               ped_tipo_solic_pais tspa2,
                               ped_tipo_solic      tsol2
                         WHERE 1 = 1
                           AND soca2.soca_oid_docu_refe = cons2.oid_soli_cabe
                           AND soca2.tspa_oid_tipo_soli_pais = tspa2.oid_tipo_soli_pais
                           AND tspa2.tsol_oid_tipo_soli = tsol2.oid_tipo_soli
                           AND tsol2.cod_tipo_soli IN ('SDAA', 'SDAN')
                           AND soca2.fec_fact IS NOT NULL
                           AND soca2.perd_oid_peri = pnOidCampannaRecaudo
                           AND soca2.clie_oid_clie = clie.oid_clie
                           AND soca2.soca_oid_docu_refe = soca.soca_oid_soli_cabe
                        ) > 0 THEN 'S' ELSE 'N' END IND_PEDI_ANUL,
             LPAD(NVL(clhe.esta_oid_esta_clie,'01'),2,'0') COD_ESTA_CLIE,
             (
               SELECT pnra.cod_rang
                 FROM lec_progr_nivel_rango pnra
                WHERE 1=1
                  AND pnra.lpro_cod_prog = psCodigoPrograma
                  AND soca.val_prec_cata_tota_docu BETWEEN pnra.val_mont_mini
                                                       AND pnra.val_mont_maxi
                  AND psCampannaRecaudo BETWEEN pnra.cam_inic
                                            AND NVL(pnra.cam_fina,psCampannaRecaudo)
                  AND pnra.ind_acti =1
             ) PNRA_COD_RANG,
             psCodigoUsuario USU_CREA,
             SYSDATE FEC_CREA,
             NULL USU_MODI,
             NULL FEC_MODI
        FROM ped_solic_cabec soca,
             ped_solic_cabec cons,
             ped_tipo_solic_pais tspa,
             ped_tipo_solic tsol,
             zon_terri_admin ztad,
             zon_secci zscc,
             zon_zona zzon,
             zon_regio zorg,
             mae_clien_histo_estat clhe,
             mae_clien_unida_admin cuad,
             mae_clien clie,
             (
              SELECT lpex.cod_cons
                FROM lec_progr_lista_exclu lpex
               WHERE psCampannaRecaudo BETWEEN lpex.cam_inic_vige AND lpex.cam_fin_vige
                 AND lpex.ind_acti = '1'
               GROUP BY lpex.cod_cons
             ) excl
       WHERE 1=1
         AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
         AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
         AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
         AND soca.clie_oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
         AND soca.clie_oid_clie = clhe.clie_oid_clie(+)
         AND soca.clie_oid_clie = clie.oid_clie
         AND clie.cod_clie = excl.cod_cons(+)
         --
         AND soca.perd_oid_peri = pnOidCampannaRecaudo
         AND soca.fec_fact IS NOT NULL
         AND soca.grpr_oid_grup_proc = 5
         --
         AND excl.cod_cons IS NULL
         AND tsol.cod_tipo_soli IN ('SOC')
         AND pnOidCampannaRecaudo BETWEEN cuad.perd_oid_peri_ini AND NVL( cuad.perd_oid_peri_fin, pnOidCampannaRecaudo )
         AND pnOidCampannaAnterior BETWEEN clhe.perd_oid_peri(+) AND NVL( clhe.perd_oid_peri_peri_fin(+), pnOidCampannaAnterior )
         --
         AND zorg.cod_regi = NVL(psCodigoRegion,zorg.cod_regi)
         AND zzon.cod_zona = NVL(psCodigoZona,zzon.cod_zona )
         AND zscc.cod_secc = NVL(psCodigoSeccion,zscc.cod_secc )
     )
SELECT temp.*
  FROM temp
 WHERE (
        (psIndReversion <> '0' AND temp.ind_pedi_anul = 'N') OR
         psIndReversion = '0'
       );

TYPE c_Pedidos_t IS TABLE OF c_Pedidos%ROWTYPE INDEX BY BINARY_INTEGER;
RegPedidos c_Pedidos_t;

-- Variables
lsScriptDeducc     VARCHAR2(500);
lnMontoDeducc      NUMBER(12,2):=0;
lnMontoDemandaCata NUMBER(12,2):=0;

BEGIN
   -- Procesa Pedidos
    OPEN c_Pedidos;
         LOOP
            FETCH c_Pedidos BULK COLLECT INTO regPedidos;
            IF regPedidos.COUNT > 0 THEN
               FOR p IN regPedidos.FIRST .. regPedidos.LAST LOOP

                     -- Calcular Monto no Comisionable
                     IF( psCamposDeducc IS NOT NULL )THEN

                        lsScriptDeducc := psCamposDeducc
                                          || TRIM( TO_CHAR( regPedidos(p).soca_oid_soli_cabe,'999999999999' ) );
                        EXECUTE IMMEDIATE lsScriptDeducc INTO lnMontoDeducc;
                     END IF;

                     BEGIN
                          INSERT INTO lec_consu_pedid_campa lcpc
                          SELECT psCodigoPais PAIS_COD_PAIS,
                                 psCampannaRecaudo CAM_RECA,
                                 regPedidos(p).cod_cons COD_CONS,
                                 regPedidos(p).soca_oid_soli_cabe SOCA_OID_SOLI_CABE,
                                 regPedidos(p).cod_regi COD_REGI,
                                 regPedidos(p).cod_zona COD_ZONA,
                                 regPedidos(p).cod_secc COD_SECC,
                                 regPedidos(p).val_mont_cata VAL_MONT_CATA,
                                 regPedidos(p).val_mont_fact VAL_MONT_FACT,
                                 regPedidos(p).val_mont_flex VAL_MONT_FLEX,
                                 lnMontoDeducc VAL_MONT_NCOM,
                                 regPedidos(p).ind_pedi_cons IND_PEDI_CONS,
                                 regPedidos(p).ind_pedi_anul IND_PEDI_ANUL,
                                 regPedidos(p).cod_esta_clie COD_ESTA_CLIE,
                                 psCodigoPrograma LPRO_COD_PROG,
                                 regPedidos(p).pnra_cod_rang PNRA_COD_RANG,
                                 psCodigoUsuario USU_CREA,
                                 SYSDATE FEC_CREA,
                                 NULL USU_MODI,
                                 NULL FEC_MODI,
                                 '1' IND_ACTI
                            FROM DUAL;
                     EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                       UPDATE lec_consu_pedid_campa lcpc
                          SET lcpc.cod_regi = regPedidos(p).cod_regi,
                              lcpc.cod_zona = regPedidos(p).cod_zona,
                              lcpc.cod_secc = regPedidos(p).cod_secc,
                              lcpc.val_mont_cata = regPedidos(p).val_mont_cata,
                              lcpc.val_mont_fact = regPedidos(p).val_mont_fact,
                              lcpc.val_mont_flex = regPedidos(p).val_mont_flex,
                              lcpc.val_mont_ncom = lnMontoDeducc,
                              lcpc.ind_pedi_cons = regPedidos(p).ind_pedi_cons,
                              lcpc.ind_pedi_anul = regPedidos(p).ind_pedi_anul,
                              lcpc.cod_esta_clie = regPedidos(p).cod_esta_clie,
                              lcpc.lpro_cod_prog = psCodigoPrograma,
                              lcpc.pnra_cod_rang = regPedidos(p).pnra_cod_rang,
                              lcpc.usu_modi = psCodigoUsuario,
                              lcpc.fec_modi = SYSDATE,
                              lcpc.ind_acti = '1'
                        WHERE lcpc.pais_cod_pais = psCodigoPais
                          AND lcpc.cam_reca = psCampannaRecaudo
                          AND lcpc.cod_cons = regPedidos(p).cod_cons
                          AND lcpc.soca_oid_soli_cabe = regPedidos(p).soca_oid_soli_cabe
                            ;
                      END;
               END LOOP;
            END IF;
            EXIT WHEN c_Pedidos%NOTFOUND;
         END LOOP;
    CLOSE c_Pedidos;
    -- Actualizar el rango nivel de todas las consultoras de la seccion
    FOR r IN (
              SELECT lcpc.pais_cod_pais,
                     lcpc.cam_reca,
                     lcpc.cod_regi,
                     lcpc.cod_zona,
                     lcpc.cod_secc,
                     lcpc.cod_cons,
                     lcpc.ind_pedi_cons,
                     SUM( lcpc.val_mont_cata ) val_mont_cata
                FROM lec_consu_pedid_campa lcpc
               WHERE lcpc.pais_cod_pais = psCodigoPais
                 AND lcpc.cam_reca = psCampannaRecaudo
                 AND lcpc.cod_regi = psCodigoRegion
                 AND lcpc.cod_zona = psCodigoZona
                 AND lcpc.cod_secc = psCodigoSeccion
               GROUP BY lcpc.pais_cod_pais,
                        lcpc.cam_reca,
                        lcpc.cod_regi,
                        lcpc.cod_zona,
                        lcpc.cod_secc,
                        lcpc.cod_cons,
                        lcpc.ind_pedi_cons
             )
    LOOP
       UPDATE lec_consu_pedid_campa x
          SET x.pnra_cod_rang = (
                                  SELECT pnra.cod_rang
                                    FROM lec_progr_nivel_rango pnra
                                   WHERE 1=1
                                     AND pnra.lpro_cod_prog = psCodigoPrograma
                                     AND r.val_mont_cata BETWEEN pnra.val_mont_mini
                                                             AND pnra.val_mont_maxi
                                     AND r.cam_reca BETWEEN pnra.cam_inic
                                                        AND NVL(pnra.cam_fina,r.cam_reca)
                                     AND pnra.ind_acti =1
                                 )
        WHERE x.pais_cod_pais = r.pais_cod_pais
          AND x.cam_reca = r.cam_reca
          AND x.cod_regi = r.cod_regi
          AND x.cod_zona = r.cod_zona
          AND x.cod_secc = r.cod_secc
          AND x.cod_cons = r.cod_cons;
    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR LEC_PR_GENER_PEDID_CAMPA: ' || ls_sqlerrm);
  END LEC_PR_GENER_PEDID_CAMPA;


/***********************************************************************************************
  Descripcion       : Genera información de Venta Retail a las consultoras de una campaña específica
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_VENTA_RETAI
(
 psCodigoPais      VARCHAR2,
 psCampannaRecaudo VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma  VARCHAR2,
 psCodigoRegion    VARCHAR2,
 psCodigoZona      VARCHAR2,
 psCodigoSeccion   VARCHAR2,
 pdFechaLimite     DATE,
 psCodigoUsuario   VARCHAR2
)
IS

CURSOR c_VentaRetail
IS
WITH temp AS
     (
      SELECT veca.val_cuen_consu cod_cons,
             SUM (
                   CASE
                      WHEN veca.cod_tipo_docu = 'F' THEN
                           ( vede.val_mont_cata * vede.uni_vend )
                      ELSE
                           0
                   END
                 )  val_mont_vent_cata,
             SUM (
                   CASE
                      WHEN veca.cod_tipo_docu = 'F' THEN
                           vede.val_mont_dsct
                      ELSE
                           vede.mon_devu * -1
                   END
                 )  val_mont_vent_fact,
             SUM (
                   CASE
                      WHEN veca.cod_tipo_docu = 'F' THEN
                           ( vede.val_mont_dsct - vede.mon_impu )
                      ELSE
                           ( vede.mon_devu - vede.mon_impu ) * -1
                   END
                 )  val_mont_vent_base,
             zorg.cod_regi,
             zzon.cod_zona,
             zscc.cod_secc,
             MIN( veca.fec_envi ) fec_inic_vent,
             MAX( veca.fec_envi ) fec_fina_vent
        FROM ret_venta_cabec veca,
             ret_venta_detal vede,
             mae_clien clie,
             mae_clien_unida_admin cuad,
             zon_terri_admin ztad,
             zon_secci zscc,
             zon_zona zzon,
             zon_regio zorg,
             (
              SELECT lpex.cod_cons
                FROM lec_progr_lista_exclu lpex
               WHERE psCampannaRecaudo BETWEEN lpex.cam_inic_vige AND lpex.cam_fin_vige
                 AND lpex.ind_acti = '1'
               GROUP BY lpex.cod_cons
             ) excl
       WHERE 1=1
         AND veca.cod_pais = vede.cod_pais
         AND veca.cod_sbac = vede.cod_sbac
         AND veca.cod_tipo_docu = vede.cod_tipo_docu
         AND veca.num_docu_reta = vede.num_docu_reta
         AND veca.fec_envi = vede.fec_envi
         AND veca.val_cuen_consu = clie.cod_clie
         AND clie.oid_clie = cuad.clie_oid_clie
         AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona
         AND zzon.zorg_oid_regi = zorg.oid_regi
         AND veca.val_cuen_consu = excl.cod_cons(+)
         --
         AND excl.cod_cons IS NULL
         AND veca.cam_proc = psCampannaRecaudo
         AND veca.cod_tipo_docu = 'F'
         AND ( veca.ind_anul != 'A' OR veca.ind_anul IS NULL )
         AND veca.tip_clie = 'CO'
         --
         AND pnOidCampannaRecaudo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,pnOidCampannaRecaudo)
         AND excl.cod_cons IS NULL
         AND veca.fec_envi <= pdFechaLimite
         --
         AND zorg.cod_regi = NVL(psCodigoRegion,zorg.cod_regi)
         AND zzon.cod_zona = NVL(psCodigoZona,zzon.cod_zona )
         AND zscc.cod_secc = NVL(psCodigoSeccion,zscc.cod_secc )
       GROUP BY zorg.cod_regi,
                zzon.cod_zona,
                zscc.cod_secc,
                veca.val_cuen_consu
     )
SELECT temp.cod_cons,
       temp.val_mont_vent_cata,
       temp.val_mont_vent_fact,
       temp.val_mont_vent_base,
       temp.cod_regi,
       temp.cod_zona,
       temp.cod_secc,
       temp.fec_inic_vent,
       temp.fec_fina_vent
  FROM temp;

TYPE c_VentaRetail_t IS TABLE OF c_VentaRetail%ROWTYPE INDEX BY BINARY_INTEGER;
RegRetail c_VentaRetail_t;

-- Variables
vnOidCampReca CRA_PERIO.Pais_Oid_Pais%TYPE:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

BEGIN

    OPEN c_VentaRetail;
         LOOP
            FETCH c_VentaRetail BULK COLLECT INTO RegRetail;
            IF RegRetail.COUNT > 0 THEN
               FOR p IN RegRetail.FIRST .. RegRetail.LAST LOOP
                     BEGIN
                          INSERT INTO lec_consu_venta_retai lcvr
                          VALUES (
                                  psCodigoPais,
                                  psCampannaRecaudo,
                                  regRetail(p).cod_cons,
                                  regRetail(p).val_mont_vent_cata,
                                  regRetail(p).val_mont_vent_fact,
                                  regRetail(p).val_mont_vent_base,
                                  psCodigoPrograma,
                                  regRetail(p).cod_regi,
                                  regRetail(p).cod_zona,
                                  regRetail(p).cod_secc,
                                  regRetail(p).fec_inic_vent,
                                  regRetail(p).fec_fina_vent,
                                  psCodigoUsuario,
                                  SYSDATE,
                                  NULL,
                                  NULL,
                                  '1'
                                 );
                     EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                       UPDATE lec_consu_venta_retai lcvr
                          SET lcvr.val_mont_vent_cata = regRetail(p).val_mont_vent_cata,
                              lcvr.val_mont_vent_fact = regRetail(p).val_mont_vent_fact,
                              lcvr.val_mont_vent_base = regRetail(p).val_mont_vent_base,
                              lcvr.lpro_cod_prog = psCodigoPrograma,
                              lcvr.cod_regi = regRetail(p).cod_regi,
                              lcvr.cod_zona = regRetail(p).cod_zona,
                              lcvr.cod_secc = regRetail(p).cod_secc,
                              lcvr.fec_inic_vent = regRetail(p).fec_inic_vent,
                              lcvr.fec_fina_vent = regRetail(p).fec_fina_vent,
                              lcvr.usu_modi = psCodigoUsuario,
                              lcvr.fec_modi = SYSDATE,
                              lcvr.ind_acti = '1'
                        WHERE lcvr.pais_cod_pais = psCodigoPais
                          AND lcvr.cam_reca = psCampannaRecaudo
                          AND lcvr.cod_cons = regRetail(p).cod_cons;
                      END;
               END LOOP;
            END IF;
            EXIT WHEN c_VentaRetail%NOTFOUND;
         END LOOP;
    CLOSE c_VentaRetail;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_GENER_VENTA_RETAI: '||ls_sqlerrm);
END LEC_PR_GENER_VENTA_RETAI;

/***********************************************************************************************
  Descripcion       : Genera información de Cuotas Flexipago de las consultoras de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_CUOTA_FLEXI
(
 psCodigoPais      VARCHAR2,
 psCampannaRecaudo VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma  VARCHAR2,
 psCodigoRegion    VARCHAR2,
 psCodigoZona      VARCHAR2,
 psCodigoSeccion   VARCHAR2,
 pdFechaLimite     DATE,
 psCodigoUsuario   VARCHAR2
) IS

CURSOR c_Flexi
IS
SELECT lcpc.pais_cod_pais,
       lcpc.cam_reca,
       lcpc.cod_cons,
       lcpc.cod_regi,
       lcpc.cod_zona,
       lcpc.cod_secc,
       SUM( lcpc.val_mont_fact ) val_mont_vent_fact_acum,
       MAX(NVL(fgfc.val_cuot_21di_pedi_vige,0)) val_mont_flex
  FROM lec_consu_pedid_campa lcpc,
       flx_gener_finan_consu_flexi fgfc
 WHERE 1=1
   AND lcpc.cam_reca = fgfc.cod_peri
   AND lcpc.cod_cons = fgfc.cod_clie
   --
   AND lcpc.pais_cod_pais = psCodigoPais
   AND lcpc.cam_reca = psCampannaRecaudo
   AND lcpc.cod_regi = NVL(psCodigoRegion,lcpc.cod_regi)
   AND lcpc.cod_zona = NVL(psCodigoZona,lcpc.cod_zona )
   AND lcpc.cod_secc = NVL(psCodigoSeccion,lcpc.cod_secc )
 GROUP BY lcpc.pais_cod_pais,
          lcpc.cam_reca,
          lcpc.cod_cons,
          lcpc.cod_regi,
          lcpc.cod_zona,
          lcpc.cod_secc;

TYPE c_Flexi_t IS TABLE OF c_Flexi%ROWTYPE INDEX BY BINARY_INTEGER;
RegFlexi c_Flexi_t;

-- Variables

BEGIN
    OPEN c_Flexi;
         LOOP
            FETCH c_Flexi BULK COLLECT INTO RegFlexi;
            IF RegFlexi.COUNT > 0 THEN
               FOR p IN RegFlexi.FIRST .. RegFlexi.LAST LOOP
                   UPDATE lec_consu_pedid_campa lcpc
                      SET lcpc.val_mont_flex = lcpc.val_mont_fact *
                                               ( regFlexi(p).val_mont_flex /
                                                 regFlexi(p).val_mont_vent_fact_acum ),
                          lcpc.usu_modi = psCodigoUsuario,
                          lcpc.fec_modi = SYSDATE
                    WHERE lcpc.pais_cod_pais = regFlexi(p).pais_cod_pais
                      AND lcpc.cam_reca = regFlexi(p).cam_reca
                      AND lcpc.cod_cons = regFlexi(p).cod_cons;
               END LOOP;
            END IF;
            EXIT WHEN c_Flexi%NOTFOUND;
         END LOOP;
    CLOSE c_Flexi;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_GENER_CUOTA_FLEXI: '||ls_sqlerrm);
END LEC_PR_GENER_CUOTA_FLEXI;

/***********************************************************************************************
  Descripcion       : Genera información Resumen de Recuperacióin de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_RESUM_RECUP
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 pnSecuenciaAmbitoGeo INTEGER,
 pnPorcenCobr         NUMBER,
 pnPorcenCobrMini     NUMBER,
 psCodigoUsuario      VARCHAR2
)
IS

CURSOR c_Resum
IS
WITH temp AS
     (
      SELECT lcpc.pais_cod_pais,
             lcpc.cam_reca,
             lcpc.cod_regi,
             lcpc.cod_zona,
             lcpc.cod_secc,
             lide.cod_lide,
             lide.lniv_cod_nive,
             lcpc.cod_cons,
             pnCodigoTramo lpct_cod_tram,
             MAX(lbrc.fec_inic_abon) fec_inic_abon,
             MAX(lbrc.fec_limi_abon) fec_limi_abon,
             MAX(lcpc.ind_pedi_cons) ind_pedi_cons,
             MAX(lcpc.pnra_cod_rang) pnra_cod_rang,
             SUM(NVL(lbrc.val_mont_carg,0)) val_mont_carg,
             SUM(lcpc.val_mont_fact) val_mont_fact,
             SUM(lcpc.val_mont_cata) val_mont_cata,
             SUM(lcpc.val_mont_flex) val_mont_flex,
             SUM(lcpc.val_mont_ncom) val_mont_ncom,
             ROUND( ( SUM(lcpc.val_mont_ncom) / SUM(NVL(lcpc.val_mont_fact,1)) ) ,6) val_porc_fact_ncom,
             SUM(NVL(lbrc.val_mont_cdrs,0)) val_mont_cdrs,
             SUM(NVL(lbrc.val_mont_abon,0)) val_mont_abon
        FROM lec_base_recup_consu lbrc,
             lec_consu_pedid_campa lcpc,
             (
              SELECT hger.cod_regi,
                     hger.cod_zona,
                     hger.cod_secc,
                     hger.gere cod_lide,
                     NVL((
                          SELECT llni.lniv_cod_nive
                            FROM lec_lider_nivel llni
                           WHERE llni.ind_tipo_nive = 'R'
                             AND psCampannaProceso BETWEEN llni.cam_inic AND NVL(llni.cam_fin,psCampannaProceso)
                             AND llni.cod_lide = hger.gere
                         ),'01') lniv_cod_nive
                FROM zon_histo_geren hger
               WHERE 1=1
                 AND hger.cod_secc IS NOT NULL
                 AND pnOidCampannaRecaudo BETWEEN hger.perd_oid_peri_desd AND NVL( hger.perd_oid_peri_hast, pnOidCampannaRecaudo )
                 AND hger.cod_regi = NVL(psCodigoRegion,hger.cod_regi)
                 AND hger.cod_zona = NVL(psCodigoZona,hger.cod_zona)
                 AND hger.cod_secc = NVL(psCodigoSeccion,hger.cod_secc)
             ) lide
               WHERE 1=1
         AND lide.cod_regi = lcpc.cod_regi
         AND lide.cod_zona = lcpc.cod_zona
         AND lide.cod_secc = lcpc.cod_secc
         --
         AND lcpc.pais_cod_pais = lbrc.pais_cod_pais(+)
         AND lcpc.cam_reca = lbrc.cam_reca(+)
         AND lcpc.cod_cons = lbrc.cod_cons(+)
         AND lcpc.soca_oid_soli_cabe = lbrc.soca_oid_soli_cabe(+)
         --
         AND lbrc.lpct_cod_tram(+) = pnCodigoTramo
      GROUP BY lcpc.pais_cod_pais,
               lcpc.cam_reca,
               lcpc.cod_regi,
               lcpc.cod_zona,
               lcpc.cod_secc,
               lide.cod_lide,
               lide.lniv_cod_nive,
               lcpc.cod_cons
     )
SELECT temp.PAIS_COD_PAIS,
       temp.CAM_RECA,
       temp.COD_LIDE,
       temp.LPCT_COD_TRAM,
       temp.PNRA_COD_RANG,
       --
       temp.COD_REGI,
       temp.COD_ZONA,
       temp.COD_SECC,
       temp.LNIV_COD_NIVE,
       pnSecuenciaAmbitoGeo LPAG_SEC_AMBI_GEOG,
       --
       MAX(temp.FEC_INIC_ABON) FEC_INIC_ABON,
       MAX(temp.FEC_LIMI_ABON) FEC_FINA_ABON,
       --
       pnPorcenCobr VAL_PORC_META_RECUP,
       pnPorcenCobr VAL_PORC_META_RECUP_MINI,
       --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN
                    temp.VAL_MONT_ABON - ( temp.VAL_MONT_ABON * temp.VAL_PORC_FACT_NCOM )
               ELSE 0
            END
           ) VAL_MONT_COBR_NETO_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN
                    temp.VAL_MONT_ABON - ( temp.VAL_MONT_ABON * temp.VAL_PORC_FACT_NCOM )
               ELSE 0
            END
           ) VAL_MONT_COBR_NETO_NCON,
       --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN
                    temp.VAL_MONT_CATA
               ELSE 0
            END
           ) VAL_MONT_VENT_CATA_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN
                    temp.VAL_MONT_CATA
               ELSE 0
            END
           ) VAL_MONT_VENT_CATA_NCON,
       --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN
                    temp.VAL_MONT_FACT
               ELSE 0
            END
           ) VAL_MONT_VENT_FACT_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN
                    temp.VAL_MONT_FACT
               ELSE 0
            END
           ) VAL_MONT_VENT_FACT_NCON,
       --
       0 VAL_MONT_VENT_RETA_CONS,
       0 VAL_MONT_VENT_RETA_NCON,
       --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN
                    ( DECODE(NVL(temp.VAL_MONT_FLEX,0),0,temp.VAL_MONT_FACT,temp.VAL_MONT_FLEX) - NVL(temp.VAL_MONT_CDRS,0) ) -
                    ( DECODE(NVL(temp.VAL_MONT_FLEX,0),0,temp.VAL_MONT_FACT,temp.VAL_MONT_FLEX) * temp.VAL_PORC_FACT_NCOM )
               ELSE 0
            END
           ) VAL_MONT_VENT_NETA_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN
                    ( DECODE(NVL(temp.VAL_MONT_FLEX,0),0,temp.VAL_MONT_FACT,temp.VAL_MONT_FLEX) - NVL(temp.VAL_MONT_CDRS,0) ) -
                    ( DECODE(NVL(temp.VAL_MONT_FLEX,0),0,temp.VAL_MONT_FACT,temp.VAL_MONT_FLEX) * temp.VAL_PORC_FACT_NCOM )
               ELSE 0
            END
           ) VAL_MONT_VENT_NETA_NCON,
         --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN
                    temp.VAL_MONT_ABON
               ELSE 0
            END
           ) VAL_MONT_COBR_BRUT_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN
                    temp.VAL_MONT_ABON
               ELSE 0
            END
           ) VAL_MONT_COBR_BRUT_NCON,
         --
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'S' THEN 1
               ELSE 0
            END
           ) VAL_NUME_PEDI_CONS,
       SUM( CASE
               WHEN temp.IND_PEDI_CONS = 'N' THEN 1
               ELSE 0
            END
           ) VAL_NUME_PEDI_NCON/*,
       '03' LEOB_COD_ESTA_OBJE*/
  FROM temp
 GROUP BY temp.PAIS_COD_PAIS,
          temp.CAM_RECA,
          temp.COD_LIDE,
          temp.LPCT_COD_TRAM,
          temp.PNRA_COD_RANG,
          temp.COD_REGI,
          temp.COD_ZONA,
          temp.COD_SECC,
          temp.LNIV_COD_NIVE;

TYPE c_Resum_t IS TABLE OF c_Resum%ROWTYPE INDEX BY BINARY_INTEGER;
RegResum c_Resum_t;

-- Variables
lnTotalCargoBase  NUMBER(12,2):=0;
lnTotalAbonoBase  NUMBER(12,2):=0;
lnFactorDeduccion NUMBER(12,2):=0;

BEGIN
    OPEN c_Resum;
         LOOP
            FETCH c_Resum BULK COLLECT INTO RegResum;
            IF RegResum.COUNT > 0 THEN
               FOR a IN RegResum.FIRST .. RegResum.LAST LOOP
                     BEGIN
                          INSERT INTO lec_lider_resum_recup lirr
                          SELECT regResum(a).PAIS_COD_PAIS,
                                 regResum(a).CAM_RECA,
                                 regResum(a).COD_LIDE,
                                 regResum(a).LPCT_COD_TRAM,
                                 regResum(a).PNRA_COD_RANG,
                                 regResum(a).COD_REGI,
                                 regResum(a).COD_ZONA,
                                 regResum(a).COD_SECC,
                                 regResum(a).LNIV_COD_NIVE,
                                 regResum(a).LPAG_SEC_AMBI_GEOG,
                                 regResum(a).FEC_INIC_ABON,
                                 regResum(a).FEC_FINA_ABON,
                                 --
                                 regResum(a).VAL_PORC_META_RECUP,
                                 regResum(a).VAL_PORC_META_RECUP_MINI,
                                 regResum(a).VAL_MONT_COBR_NETO_CONS,
                                 regResum(a).VAL_MONT_COBR_NETO_NCON,
                                 regResum(a).VAL_MONT_VENT_CATA_CONS,
                                 regResum(a).VAL_MONT_VENT_CATA_NCON,
                                 regResum(a).VAL_MONT_VENT_FACT_CONS,
                                 regResum(a).VAL_MONT_VENT_FACT_NCON,
                                 regResum(a).VAL_MONT_VENT_RETA_CONS,
                                 regResum(a).VAL_MONT_VENT_RETA_NCON,
                                 regResum(a).VAL_MONT_VENT_NETA_CONS,
                                 regResum(a).VAL_MONT_VENT_NETA_NCON,
                                 regResum(a).VAL_MONT_COBR_BRUT_CONS,
                                 regResum(a).VAL_MONT_COBR_BRUT_NCON,
                                 regResum(a).VAL_NUME_PEDI_CONS,
                                 regResum(a).VAL_NUME_PEDI_NCON,
                                 0 VAL_PORC_RECU,
                                 0 VAL_PORC_COMI_CONS,
                                 0 VAL_PORC_COMI_NCON,
                                 0 VAL_PORC_COMI_TOLE,
                                 0 VAL_IMPO_COMI_BASE_CONS,
                                 0 VAL_IMPO_COMI_BASE_NCON,
                                 0 VAL_IMPO_COMI_BASE_RETA,
                                 psCampannaProceso CAM_PROC,
                                 '03', --regResum(a).LEOB_COD_ESTA_OBJE,
                                 psCodigoPrograma LPRO_COD_PROG,
                                 psCodigoUsuario USU_CREA,
                                 SYSDATE FEC_CREA,
                                 NULL USU_MODI,
                                 NULL FEC_MODI
                            FROM DUAL;
                     EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                       UPDATE lec_lider_resum_recup lirr
                          SET lirr.VAL_PORC_META_RECUP = regResum(a).VAL_PORC_META_RECUP,
                              lirr.VAL_PORC_META_RECUP_MINI = regResum(a).VAL_PORC_META_RECUP_MINI,
                              lirr.VAL_MONT_COBR_NETO_CONS = regResum(a).VAL_MONT_COBR_NETO_CONS,
                              lirr.VAL_MONT_COBR_NETO_NCON = regResum(a).VAL_MONT_COBR_NETO_NCON,
                              lirr.VAL_MONT_VENT_CATA_CONS = regResum(a).VAL_MONT_VENT_CATA_CONS,
                              lirr.VAL_MONT_VENT_CATA_NCON = regResum(a).VAL_MONT_VENT_CATA_NCON,
                              lirr.VAL_MONT_VENT_FACT_CONS = regResum(a).VAL_MONT_VENT_FACT_CONS,
                              lirr.VAL_MONT_VENT_FACT_NCON = regResum(a).VAL_MONT_VENT_FACT_NCON,
                              lirr.VAL_MONT_VENT_RETA_CONS = regResum(a).VAL_MONT_VENT_RETA_CONS,
                              lirr.VAL_MONT_VENT_RETA_NCON = regResum(a).VAL_MONT_VENT_RETA_NCON,
                              lirr.VAL_MONT_VENT_NETA_CONS = regResum(a).VAL_MONT_VENT_NETA_CONS,
                              lirr.VAL_MONT_VENT_NETA_NCON = regResum(a).VAL_MONT_VENT_NETA_NCON,
                              lirr.VAL_MONT_COBR_BRUT_CONS = regResum(a).VAL_MONT_COBR_BRUT_CONS,
                              lirr.VAL_MONT_COBR_BRUT_NCON = regResum(a).VAL_MONT_COBR_BRUT_NCON,
                              lirr.VAL_NUME_PEDI_CONS = regResum(a).VAL_NUME_PEDI_CONS,
                              lirr.VAL_NUME_PEDI_NCON = regResum(a).VAL_NUME_PEDI_NCON,
                              lirr.VAL_PORC_RECU = 0,
                              lirr.VAL_PORC_COMI_CONS = 0,
                              lirr.VAL_PORC_COMI_NCON = 0,
                              lirr.VAL_PORC_COMI_TOLE = 0,
                              lirr.VAL_IMPO_COMI_BASE_CONS = 0,
                              lirr.VAL_IMPO_COMI_BASE_NCON = 0,
                              lirr.VAL_IMPO_COMI_BASE_RETA = 0,
                              lirr.LEOB_COD_ESTA_OBJE = '03', --regResum(a).LEOB_COD_ESTA_OBJE,
                              lirr.usu_modi = psCodigoUsuario,
                              lirr.fec_modi = SYSDATE
                        WHERE lirr.pais_cod_pais = regResum(a).pais_cod_pais
                          AND lirr.cam_reca = regResum(a).cam_reca
                          AND lirr.cod_lide = regResum(a).cod_lide
                          AND lirr.lpct_cod_tram = regResum(a).lpct_cod_tram
                          AND lirr.pnra_cod_rang = regResum(a).pnra_cod_rang;
                      END;
               END LOOP;
            END IF;
            EXIT WHEN c_Resum%NOTFOUND;
         END LOOP;
    CLOSE c_Resum;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_GENER_RESUM_RECUP: '||ls_sqlerrm);
END LEC_PR_GENER_RESUM_RECUP;


/***********************************************************************************************
  Descripcion       : Genera información Resumen de Recuperacióin de la campaña
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 30/01/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_CALCU_COMIS_CONSU
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 pnSecuenciaAmbitoGeo INTEGER,
 pnPorcenCobr         NUMBER,
 pnPorcenCobrMini     NUMBER,
 psCodigoUsuario      VARCHAR2
)
IS

CURSOR c_BaseComis
IS
SELECT lbrc.pais_cod_pais,
       lbrc.cam_reca,
       lbrc.cod_lide,
       lbrc.lpct_cod_tram,
       lbrc.pnra_cod_rang,
       lbrc.lniv_cod_nive,
         --
       lbrc.val_porc_meta_recup,
       lbrc.val_porc_meta_recup_mini,
         --
       pntr.val_porc_comi_pedi_cons,
       pntr.val_porc_comi_pedi_ncon,
       pntr.val_porc_comi_tole,
       --
       lisr.leob_cod_esta_obje,
       lisr.leob_cod_esta_obje_ingr,
       lisr.leob_cod_esta_obje_capi,
       --
       rcob.val_mont_vent_neta,
       rcob.val_mont_cobr_neto,
       rcob.val_mont_cobr_neto_cons,
       rcob.val_mont_cobr_neto_ncon,
       rcob.val_mont_vent_reta_cons,
       rcob.val_mont_vent_reta_ncon
  FROM lec_lider_resum_recup lbrc,
       lec_progr_nivel_tramo pntr,
       lec_lider_secci_resul lisr,
       (
        SELECT x.pais_cod_pais,
               x.cam_reca,
               x.cod_lide,
               SUM( x.val_mont_vent_neta_cons + x.val_mont_vent_neta_ncon ) val_mont_vent_neta,
               SUM( x.val_mont_cobr_neto_cons + x.val_mont_cobr_neto_ncon ) val_mont_cobr_neto,
               SUM( x.val_mont_cobr_neto_cons ) val_mont_cobr_neto_cons,
               SUM( x.val_mont_cobr_neto_ncon ) val_mont_cobr_neto_ncon,
               SUM( x.val_mont_vent_reta_cons ) val_mont_vent_reta_cons,
               SUM( x.val_mont_vent_reta_ncon ) val_mont_vent_reta_ncon
          FROM lec_lider_resum_recup x
         WHERE x.pais_cod_pais = psCodigoPais
           AND x.cam_reca = psCampannaRecaudo
           AND x.cod_regi = NVL(psCodigoRegion,x.cod_regi)
           AND x.cod_zona = NVL(psCodigoZona,x.cod_zona)
           AND x.cod_secc = NVL(psCodigoSeccion,x.cod_secc)
         GROUP BY x.pais_cod_pais,
                  x.cam_reca,
                  x.cod_lide
       ) rcob
 WHERE 1=1
   AND lbrc.pais_cod_pais = pntr.pais_cod_pais(+)
   AND lbrc.lpro_cod_prog = pntr.lpro_cod_prog(+)
   AND lbrc.lniv_cod_nive = pntr.lniv_cod_nive(+)
   AND lbrc.lpct_cod_tram = pntr.cod_tram(+)
   AND lbrc.pnra_cod_rang = pntr.pnra_cod_rang(+)
       --
   AND lbrc.pais_cod_pais = lisr.pais_cod_pais(+)
   AND lbrc.lpro_cod_prog = lisr.lpro_cod_prog(+)
   AND lbrc.cod_regi = lisr.cod_regi(+)
   AND lbrc.cod_zona = lisr.cod_zona(+)
   AND lbrc.cod_secc = lisr.cod_secc(+)
   AND lbrc.cod_lide = lisr.cod_lide(+)
   AND lbrc.cam_reca = lisr.cam_resu(+)
       --
   AND lbrc.pais_cod_pais = rcob.pais_cod_pais
   AND lbrc.cam_reca = rcob.cam_reca
   AND lbrc.cod_lide = rcob.cod_lide
       --
   AND lbrc.pais_cod_pais = psCodigoPais
   AND lbrc.cam_reca = psCampannaRecaudo
   AND lbrc.lpct_cod_tram = pnCodigoTramo
       --
   AND lbrc.cod_regi = NVL(psCodigoRegion,lbrc.cod_regi)
   AND lbrc.cod_zona = NVL(psCodigoZona,lbrc.cod_zona)
   AND lbrc.cod_secc = NVL(psCodigoSeccion,lbrc.cod_secc)
     ;

TYPE c_BaseComis_t IS TABLE OF c_BaseComis%ROWTYPE INDEX BY BINARY_INTEGER;
regComis c_BaseComis_t;

-- Variables
lnPorcentajeRecup     NUMBER(12,4) := 0.00;
lnTipoComision        INTEGER := 0;
lnMontoImponible      NUMBER(12,2) := 0;
lnMontoComisionCONS   NUMBER(12,2) := 0;
lnMontoComisionNCON   NUMBER(12,2) := 0;
lnMontoComisionTOLE   NUMBER(12,2) := 0;
lnMontoComisionRETA   NUMBER(12,2) := 0;
lsEstadoObjetivo      VARCHAR2(2);

BEGIN
    OPEN c_BaseComis;
         LOOP
            FETCH c_BaseComis BULK COLLECT INTO regComis;
            IF regComis.COUNT > 0 THEN
               FOR a IN regComis.FIRST .. regComis.LAST LOOP
                   -- Calcular valores
                   lnPorcentajeRecup := ( regComis(a).val_mont_cobr_neto /
                                          regComis(a).val_mont_vent_neta ) * 100;

                   -- Si cumple objetivo de pedidos y venta
                   IF regComis(a).leob_cod_esta_obje = '01' THEN
                      -- Verifica si cumple objetivo de recuperación
                      IF lnPorcentajeRecup >= regComis(a).val_porc_meta_recup THEN
                         -- Verifica si cumple objetivo de ingresos
                         IF NVL(regComis(a).leob_cod_esta_obje_ingr,'00') = '01' THEN
                            lnTipoComision := 1; -- Comision por Cumplimiento
                         ELSE
                            -- Verifica si cumple objetivos de capitalizacion
                            IF NVL(regComis(a).leob_cod_esta_obje_capi,'00') = '01' THEN
                                  lnTipoComision := 1; -- Comision por Cumplimiento
                            ELSE
                               lnTipoComision := 2; -- Comision por Tolerancia
                            END IF;
                         END IF;
                      ELSE
                         -- Verifica si supera el % mínimo exigido
                         IF lnPorcentajeRecup >= regComis(a).val_porc_meta_recup_mini THEN
                            lnTipoComision := 2; -- Comision por Tolerancia
                         ELSE
                            lnTipoComision := 3; -- Comision por NO cumplimiento
                         END IF;
                      END IF;
                   -- Cumple objetivo de pedidos y ventas con TOLERANCIA
                   ELSIF regComis(a).leob_cod_esta_obje = '02' THEN
                      -- Verifica si cumple objetivo de recuperación
                      IF lnPorcentajeRecup >= regComis(a).val_porc_meta_recup THEN
                         lnTipoComision := 2; -- Comision por Tolerancia
                      ELSE
                         -- Verifica si supera el % mínimo exigido
                         IF lnPorcentajeRecup >= regComis(a).val_porc_meta_recup_mini THEN
                            lnTipoComision := 2; -- Comision por Tolerancia
                         ELSE
                            lnTipoComision := 3; -- Comision por NO cumplimiento
                         END IF;
                      END IF;
                   -- No cumple objetivo de pedidos y ventas
                   ELSIF regComis(a).leob_cod_esta_obje = '03' THEN
                      lnTipoComision := 3; -- Comision por NO cumplimiento
                   ELSE
                      lnTipoComision := 3; -- Comision por NO cumplimiento
                   END IF;

                   -- ==============================================
                   -- CALCULAR MONTO COMISION DE LA SOCIA EMPRESARIA
                   -- ==============================================

                   -- Si el tipo de comision es por cumplimiento
                   IF lnTipoComision = 1 THEN
                      -- Calcular comision por venta de pedidos consecutivos
                      lnMontoComisionCONS := ( regComis(a).val_mont_cobr_neto_cons * ( regComis(a).val_porc_comi_pedi_cons / 100 ) );
                      -- Calcular comision por venta de pedidos no consecutivos
                      lnMontoComisionNCON := ( regComis(a).val_mont_cobr_neto_ncon * ( regComis(a).val_porc_comi_pedi_ncon / 100 ) );
                      -- Calcular comision por venta retail
                      lnMontoComisionRETA := ( ( regComis(a).val_mont_vent_reta_cons + regComis(a).val_mont_vent_reta_ncon ) * ( regComis(a).val_porc_comi_tole / 100 ) );
                      -- Actualizar codigo de estado del objetivo
                      lsEstadoObjetivo := '01';
                   END IF;

                   -- Si el tipo de comision es por tolerancia
                   IF lnTipoComision = 2 THEN
                      -- Calcular comision por venta de pedidos consecutivos
                      lnMontoComisionCONS := ( regComis(a).val_mont_cobr_neto_cons * ( regComis(a).val_porc_comi_tole / 100 ) );
                      -- Calcular comision por venta de pedidos no consecutivos
                      lnMontoComisionNCON := ( regComis(a).val_mont_cobr_neto_ncon * ( regComis(a).val_porc_comi_tole / 100 ) );
                      -- Calcular comision por venta retail
                      lnMontoComisionRETA := ( ( regComis(a).val_mont_vent_reta_cons + regComis(a).val_mont_vent_reta_ncon ) * ( regComis(a).val_porc_comi_tole / 100 ) );
                      -- Actualizar codigo de estado del objetivo
                      lsEstadoObjetivo := '02';
                   END IF;

                   -- Si el tipo de comision es por no cumplimiento
                   IF lnTipoComision = 3 THEN
                      -- Calcular comision por venta de pedidos consecutivos
                      lnMontoComisionCONS := 0;
                      -- Calcular comision por venta de pedidos no consecutivos
                      lnMontoComisionNCON := 0;
                      -- Calcular comision por venta retail
                      lnMontoComisionRETA := 0;
                      -- Actualizar codigo de estado del objetivo
                      lsEstadoObjetivo := '03';
                   END IF;

                   -- Grabar los valores calculados
                   IF lnTipoComision IN (1,2,3) THEN
                       UPDATE lec_lider_resum_recup lirr
                          SET lirr.val_porc_recu = lnPorcentajeRecup,
                              lirr.val_porc_comi_cons = regComis(a).val_porc_comi_pedi_cons,
                              lirr.val_porc_comi_ncon = regComis(a).val_porc_comi_pedi_ncon,
                              lirr.val_porc_comi_tole = regComis(a).val_porc_comi_tole,
                              lirr.val_impo_comi_base_cons = lnMontoComisionCONS,
                              lirr.val_impo_comi_base_ncon = lnMontoComisionNCON,
                              lirr.val_impo_comi_base_reta = lnMontoComisionRETA,
                              lirr.leob_cod_esta_obje = lsEstadoObjetivo
                        WHERE 1=1
                          AND lirr.pais_cod_pais = regComis(a).pais_cod_pais
                          AND lirr.cam_reca = regComis(a).cam_reca
                          AND lirr.cod_lide = regComis(a).cod_lide
                          AND lirr.lpct_cod_tram = regComis(a).lpct_cod_tram
                          AND lirr.pnra_cod_rang = regComis(a).pnra_cod_rang ;
                   END IF;

                   -- Inicializar valores
                   lnPorcentajeRecup    := 0;
                   lnTipoComision       := 0;
                   lnMontoImponible     := 0;
                   lnMontoComisionCONS  := 0;
                   lnMontoComisionNCON  := 0;
                   lnMontoComisionTOLE  := 0;
                   lnMontoComisionRETA  := 0;
                   lsEstadoObjetivo     := NULL;
               END LOOP;
            END IF;
            EXIT WHEN c_BaseComis%NOTFOUND;
         END LOOP;
    CLOSE c_BaseComis;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_CALCU_COMIS_CONSU: '||ls_sqlerrm);
END LEC_PR_CALCU_COMIS_CONSU;


/***********************************************************************************************
  Descripcion       : Genera informacion de ganancias
                      Para el nuevo modelo de recuperación LET.
  Fecha Creacion    : 14/02/2016
  Autor             : Carlos Mori
  ***********************************************************************************************/
PROCEDURE LEC_PR_GENER_GANAN_RECUP
(
 psCodigoPais         VARCHAR2,
 psCampannaProceso    VARCHAR2,
 psCampannaRecaudo    VARCHAR2,
 pnOidCampannaRecaudo INTEGER,
 psCodigoPrograma     VARCHAR2,
 psCodigoRegion       VARCHAR2,
 psCodigoZona         VARCHAR2,
 psCodigoSeccion      VARCHAR2,
 pnCodigoTramo        INTEGER,
 psCodigoUsuario      VARCHAR2
)
IS
CURSOR c_Ganan
IS
SELECT lbrc.pais_cod_pais,
       lbrc.cam_reca,
       lbrc.cod_lide,
       lbrc.cod_regi,
       lbrc.cod_zona,
       lbrc.cod_secc,
       lbrc.lpct_cod_tram,
       lbrc.lpro_cod_prog,
       MAX( lbrc.val_porc_comi_cons ) val_porc_comi_cons,
       MAX( lbrc.val_porc_comi_ncon ) val_porc_comi_ncon,
       MAX( lbrc.val_porc_comi_tole ) val_porc_comi_tole,
       SUM( CASE WHEN lbrc.leob_cod_esta_obje = '01' THEN lbrc.val_impo_comi_base_cons ELSE 0 END ) val_tota_gana_cons,
       SUM( CASE WHEN lbrc.leob_cod_esta_obje = '01' THEN lbrc.val_impo_comi_base_ncon ELSE 0 END ) val_tota_gana_ncon,
       SUM( CASE WHEN lbrc.leob_cod_esta_obje = '02' THEN lbrc.val_impo_comi_base_cons ELSE 0 END ) val_tota_gana_tole,
       SUM( lbrc.val_impo_comi_base_reta ) val_tota_gana_reta
  FROM lec_lider_resum_recup lbrc
 WHERE lbrc.pais_cod_pais = psCodigoPais
   AND lbrc.cam_reca = psCampannaRecaudo
   AND lbrc.cod_regi = NVL(psCodigoRegion,lbrc.cod_regi)
   AND lbrc.cod_zona = NVL(psCodigoZona,lbrc.cod_zona)
   AND lbrc.cod_secc = NVL(psCodigoSeccion,lbrc.cod_secc)
 GROUP BY lbrc.pais_cod_pais,
          lbrc.cam_reca,
          lbrc.cod_lide,
          lbrc.cod_regi,
          lbrc.cod_zona,
          lbrc.cod_secc,
          lbrc.lpct_cod_tram,
          lbrc.lpro_cod_prog;

TYPE c_Ganan_t IS TABLE OF c_Ganan%ROWTYPE INDEX BY BINARY_INTEGER;
RegGanan c_Ganan_t;

-- Variables
lnTotalGananRecup NUMBER(12,2):=0;

BEGIN
    OPEN c_Ganan;
         LOOP
            FETCH c_Ganan BULK COLLECT INTO RegGanan;
            IF RegGanan.COUNT > 0 THEN
               FOR a IN RegGanan.FIRST .. RegGanan.LAST LOOP
                   -- Borrar datos de ganancia por recuperacion previamente calculados
                   DELETE FROM lec_lider_ganan llga
                    WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                      AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                      AND llga.COD_LIDE = regGanan(a).COD_LIDE
                      AND llga.CAM_GANA = regGanan(a).CAM_RECA
                      AND llga.LTGA_COD_TIPO_GANA IN ('14','15','16','17','18')
                      AND llga.Ind_Esta_Pago_Gana = 'N';
                   
                   -- Grabar ganancia por pedidos Consecutivos
                   IF regGanan(a).val_tota_gana_cons > 0 THEN
                       BEGIN
                            INSERT INTO lec_lider_ganan llga
                            SELECT regGanan(a).PAIS_COD_PAIS,
                                   regGanan(a).lpro_cod_prog LPRO_COD_PROG,
                                   regGanan(a).COD_REGI,
                                   regGanan(a).COD_ZONA,
                                   regGanan(a).COD_SECC,
                                   regGanan(a).COD_LIDE,
                                   regGanan(a).cam_reca CAM_GANA,
                                   '14' LTGA_COD_TIPO_GANA,
                                   LEC_LLGA_SEQ.nextval SEC_LIDE_GANA,
                                   regGanan(a).LPCT_COD_TRAM,
                                   NULL LPBC_CAM_LANZ,
                                   NULL LPBL_NUM_LANZ,
                                   regGanan(a).val_tota_gana_cons MON_GANA,
                                   'N' IND_ESTA_PAGO_GANA,
                                   psCampannaProceso CAM_REFE,
                                   psCodigoUsuario USU_CREA,
                                   SYSDATE FEC_CREA,
                                   NULL USU_MODI,
                                   NULL FEC_MODI,
                                   '1' IND_ACTI,
                                   regGanan(a).val_porc_comi_cons VAL_PORC_COMI
                              FROM DUAL;
                       EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                         UPDATE lec_lider_ganan llga
                            SET llga.mon_gana = regGanan(a).val_tota_gana_cons,
                                llga.val_porc_comi = regGanan(a).val_porc_comi_cons,
                                llga.usu_modi = psCodigoUsuario,
                                llga.fec_modi = SYSDATE
                          WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                            AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                            AND llga.COD_REGI = regGanan(a).COD_REGI
                            AND llga.COD_ZONA = regGanan(a).COD_ZONA
                            AND llga.COD_SECC = regGanan(a).COD_SECC
                            AND llga.COD_LIDE = regGanan(a).COD_LIDE
                            AND llga.CAM_GANA = regGanan(a).CAM_RECA
                            AND llga.LTGA_COD_TIPO_GANA = '14'
                            AND llga.Ind_Esta_Pago_Gana = 'N';
                        END;
                   END IF;

                   -- Grabar ganancia por pedidos NO Consecutivos
                   IF regGanan(a).val_tota_gana_ncon > 0 THEN
                       BEGIN
                            INSERT INTO lec_lider_ganan llga
                            SELECT regGanan(a).PAIS_COD_PAIS,
                                   regGanan(a).lpro_cod_prog LPRO_COD_PROG,
                                   regGanan(a).COD_REGI,
                                   regGanan(a).COD_ZONA,
                                   regGanan(a).COD_SECC,
                                   regGanan(a).COD_LIDE,
                                   regGanan(a).cam_reca CAM_GANA,
                                   '15' LTGA_COD_TIPO_GANA,
                                   LEC_LLGA_SEQ.nextval SEC_LIDE_GANA,
                                   regGanan(a).LPCT_COD_TRAM,
                                   NULL LPBC_CAM_LANZ,
                                   NULL LPBL_NUM_LANZ,
                                   regGanan(a).val_tota_gana_ncon MON_GANA,
                                   'N' IND_ESTA_PAGO_GANA,
                                   psCampannaProceso CAM_REFE,
                                   psCodigoUsuario USU_CREA,
                                   SYSDATE FEC_CREA,
                                   NULL USU_MODI,
                                   NULL FEC_MODI,
                                   '1' IND_ACTI,
                                   regGanan(a).val_porc_comi_ncon VAL_PORC_COMI
                              FROM DUAL;
                       EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                         UPDATE lec_lider_ganan llga
                            SET llga.mon_gana = regGanan(a).val_tota_gana_ncon,
                                llga.val_porc_comi = regGanan(a).val_porc_comi_ncon,
                                llga.usu_modi = psCodigoUsuario,
                                llga.fec_modi = SYSDATE
                          WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                            AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                            AND llga.COD_REGI = regGanan(a).COD_REGI
                            AND llga.COD_ZONA = regGanan(a).COD_ZONA
                            AND llga.COD_SECC = regGanan(a).COD_SECC
                            AND llga.COD_LIDE = regGanan(a).COD_LIDE
                            AND llga.CAM_GANA = regGanan(a).CAM_RECA
                            AND llga.LTGA_COD_TIPO_GANA = '15'
                            AND llga.Ind_Esta_Pago_Gana = 'N';
                        END;
                   END IF;

                   -- Grabar ganancia por Tolerancia
                   IF regGanan(a).val_tota_gana_tole > 0 THEN
                       BEGIN
                            INSERT INTO lec_lider_ganan llga
                            SELECT regGanan(a).PAIS_COD_PAIS,
                                   regGanan(a).lpro_cod_prog LPRO_COD_PROG,
                                   regGanan(a).COD_REGI,
                                   regGanan(a).COD_ZONA,
                                   regGanan(a).COD_SECC,
                                   regGanan(a).COD_LIDE,
                                   regGanan(a).cam_reca CAM_GANA,
                                   '17' LTGA_COD_TIPO_GANA,
                                   LEC_LLGA_SEQ.nextval SEC_LIDE_GANA,
                                   regGanan(a).LPCT_COD_TRAM,
                                   NULL LPBC_CAM_LANZ,
                                   NULL LPBL_NUM_LANZ,
                                   regGanan(a).val_tota_gana_tole MON_GANA,
                                   'N' IND_ESTA_PAGO_GANA,
                                   psCampannaProceso CAM_REFE,
                                   psCodigoUsuario USU_CREA,
                                   SYSDATE FEC_CREA,
                                   NULL USU_MODI,
                                   NULL FEC_MODI,
                                   '1' IND_ACTI,
                                   regGanan(a).val_porc_comi_tole VAL_PORC_COMI
                              FROM DUAL;
                       EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                         UPDATE lec_lider_ganan llga
                            SET llga.mon_gana = regGanan(a).val_tota_gana_tole,
                                llga.val_porc_comi = regGanan(a).val_porc_comi_tole,
                                llga.usu_modi = psCodigoUsuario,
                                llga.fec_modi = SYSDATE
                          WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                            AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                            AND llga.COD_REGI = regGanan(a).COD_REGI
                            AND llga.COD_ZONA = regGanan(a).COD_ZONA
                            AND llga.COD_SECC = regGanan(a).COD_SECC
                            AND llga.COD_LIDE = regGanan(a).COD_LIDE
                            AND llga.CAM_GANA = regGanan(a).CAM_RECA
                            AND llga.LTGA_COD_TIPO_GANA = '17'
                            AND llga.Ind_Esta_Pago_Gana = 'N';
                        END;
                   END IF;

                   -- Grabar ganancia por Venta Retail
                   IF regGanan(a).val_tota_gana_reta > 0 THEN
                       BEGIN
                            INSERT INTO lec_lider_ganan llga
                            SELECT regGanan(a).PAIS_COD_PAIS,
                                   regGanan(a).lpro_cod_prog LPRO_COD_PROG,
                                   regGanan(a).COD_REGI,
                                   regGanan(a).COD_ZONA,
                                   regGanan(a).COD_SECC,
                                   regGanan(a).COD_LIDE,
                                   regGanan(a).cam_reca CAM_GANA,
                                   '18' LTGA_COD_TIPO_GANA,
                                   LEC_LLGA_SEQ.nextval SEC_LIDE_GANA,
                                   regGanan(a).LPCT_COD_TRAM,
                                   NULL LPBC_CAM_LANZ,
                                   NULL LPBL_NUM_LANZ,
                                   regGanan(a).val_tota_gana_reta MON_GANA,
                                   'N' IND_ESTA_PAGO_GANA,
                                   psCampannaProceso CAM_REFE,
                                   psCodigoUsuario USU_CREA,
                                   SYSDATE FEC_CREA,
                                   NULL USU_MODI,
                                   NULL FEC_MODI,
                                   '1' IND_ACTI,
                                   regGanan(a).val_porc_comi_tole VAL_PORC_COMI
                              FROM DUAL;
                       EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                         UPDATE lec_lider_ganan llga
                            SET llga.mon_gana = regGanan(a).val_tota_gana_reta,
                                llga.val_porc_comi = regGanan(a).val_porc_comi_tole,
                                llga.usu_modi = psCodigoUsuario,
                                llga.fec_modi = SYSDATE
                          WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                            AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                            AND llga.COD_REGI = regGanan(a).COD_REGI
                            AND llga.COD_ZONA = regGanan(a).COD_ZONA
                            AND llga.COD_SECC = regGanan(a).COD_SECC
                            AND llga.COD_LIDE = regGanan(a).COD_LIDE
                            AND llga.CAM_GANA = regGanan(a).CAM_RECA
                            AND llga.LTGA_COD_TIPO_GANA = '18'
                            AND llga.Ind_Esta_Pago_Gana = 'N';
                        END;
                   END IF;

                   -- Grabar total ganancias por recuperacion
                   IF (
                        regGanan(a).val_tota_gana_cons + 
                        regGanan(a).val_tota_gana_ncon +
                        regGanan(a).val_tota_gana_tole +
                        regGanan(a).val_tota_gana_reta
                      ) > 0 THEN
                       BEGIN
                            INSERT INTO lec_lider_ganan llga
                            SELECT regGanan(a).PAIS_COD_PAIS,
                                   regGanan(a).lpro_cod_prog LPRO_COD_PROG,
                                   regGanan(a).COD_REGI,
                                   regGanan(a).COD_ZONA,
                                   regGanan(a).COD_SECC,
                                   regGanan(a).COD_LIDE,
                                   regGanan(a).cam_reca CAM_GANA,
                                   '16' LTGA_COD_TIPO_GANA,
                                   LEC_LLGA_SEQ.nextval SEC_LIDE_GANA,
                                   regGanan(a).LPCT_COD_TRAM,
                                   NULL LPBC_CAM_LANZ,
                                   NULL LPBL_NUM_LANZ,
                                   (
                                     regGanan(a).val_tota_gana_cons + 
                                     regGanan(a).val_tota_gana_ncon +
                                     regGanan(a).val_tota_gana_tole +
                                     regGanan(a).val_tota_gana_reta
                                   ) MON_GANA,
                                   'N' IND_ESTA_PAGO_GANA,
                                   psCampannaProceso CAM_REFE,
                                   psCodigoUsuario USU_CREA,
                                   SYSDATE FEC_CREA,
                                   NULL USU_MODI,
                                   NULL FEC_MODI,
                                   '1' IND_ACTI,
                                   regGanan(a).val_porc_comi_tole VAL_PORC_COMI
                              FROM DUAL;
                       EXCEPTION WHEN DUP_VAL_ON_INDEX THEN
                         UPDATE lec_lider_ganan llga
                            SET llga.mon_gana = (
                                                  regGanan(a).val_tota_gana_cons + 
                                                  regGanan(a).val_tota_gana_ncon +
                                                  regGanan(a).val_tota_gana_tole +
                                                  regGanan(a).val_tota_gana_reta
                                                ),
                                llga.val_porc_comi = 0,
                                llga.usu_modi = psCodigoUsuario,
                                llga.fec_modi = SYSDATE
                          WHERE llga.PAIS_COD_PAIS = regGanan(a).PAIS_COD_PAIS
                            AND llga.LPRO_COD_PROG = regGanan(a).lpro_cod_prog
                            AND llga.COD_REGI = regGanan(a).COD_REGI
                            AND llga.COD_ZONA = regGanan(a).COD_ZONA
                            AND llga.COD_SECC = regGanan(a).COD_SECC
                            AND llga.COD_LIDE = regGanan(a).COD_LIDE
                            AND llga.CAM_GANA = regGanan(a).CAM_RECA
                            AND llga.LTGA_COD_TIPO_GANA = '16'
                            AND llga.Ind_Esta_Pago_Gana = 'N';
                        END;
                   END IF;
               END LOOP;
            END IF;
            EXIT WHEN c_Ganan%NOTFOUND;
         END LOOP;
    CLOSE c_Ganan;
  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'LEC_PR_GENER_GANAN_RECUP: '||ls_sqlerrm);
END LEC_PR_GENER_GANAN_RECUP;

END LEC_PKG_PROCE;
/
