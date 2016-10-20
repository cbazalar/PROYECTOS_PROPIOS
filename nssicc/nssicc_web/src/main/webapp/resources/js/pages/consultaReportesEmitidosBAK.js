Ext.require(['Ext.data.*']);
Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite', 'Ext.layout.container.Fit', 'Ext.window.MessageBox']);


function generardataReporteEmitidos(id) {
	Ext.onReady(function() {
		alert('555');
	    window.generateDataJsonReporteUsuario = function(id){
	        var data = [];
	        var dataJson = document.getElementById(id).value;
	        alert(dataJson);
	        var obj = jQuery.parseJSON(dataJson);
	        $.each(obj, function(i) {
	          codigoReporte = this['codigoReporte'] ;
	          duracionSegundos = this['duracionSegundos'] ;
	          data.push({
	                name: codigoReporte,
	                data1: duracionSegundos
	           });
            });
	    	return data;
	    };
	    
	    
	    window.datajsonReporteUsuario = Ext.create('Ext.data.JsonStore', {
	        fields: ['name', 'data1'],
	        data: generateDataJsonReporteUsuario(id)
	    });
	});

}

function generarReporteEmitidosBar() {
	alert('2222111');
	generardataReporteEmitidos('datajsonReporteUsuario');
	
	Ext.onReady(function () {
	    
	    Ext.chart.theme.White = Ext.extend(Ext.chart.theme.Base, {
	        constructor: function() {
	           Ext.chart.theme.White.superclass.constructor.call(this, {
	               axis: {
	                   stroke: 'rgb(8,69,148)',
	                   'stroke-width': 1
	               },
	               axisLabel: {
	                   fill: 'rgb(8,69,148)',
	                   font: '12px Arial',
	                   'font-family': '"Arial',
	                   spacing: 2,
	                   padding: 5,
	                   renderer: function(v) { return v; }
	               },
	               axisTitle: {
	                  font: 'bold 18px Arial'
	               }
	           });
	        }
	    });
	    var chart = Ext.create('Ext.chart.Chart', {
	            id: 'chartCmp',
	            xtype: 'chart',
	            animate: true,
	            shadow: true,
	            store: datajsonReporteUsuario,
	            axes: [{
	                type: 'Numeric',
	                position: 'bottom',
	                fields: ['data1'],
	                label: {
	                    renderer: Ext.util.Format.numberRenderer('0,0')
	                },
	                title: 'Duracion (Seg)', 
	                grid: true,
	                minimum: 0
	            }, {
	                type: 'Category',
	                position: 'left',
	                fields: ['name'],
	                title: 'Reporte' ,
	            }],
	            theme: 'White',
	            background: {
	              gradient: {
	                id: 'backgroundGradient',
	                angle: 45,
	                stops: {
	                  0: {
	                    color: '#ffffff'
	                  },
	                  100: {
	                    color: '#eaf1f8'
	                  }
	                }
	              }
	            },
	            series: [{
	                type: 'bar',
	                axis: 'bottom',
	                highlight: true,
	                tips: {
	                  trackMouse: true,
	                  width: 340,
	                  height: 32,
	                  renderer: function(storeItem, item) {
	                    this.setTitle(storeItem.get('name') + ' (' + storeItem.get('data1') + ' seg)');
	                  }
	                },
	                label: {
	                  display: 'insideEnd',
	                    field: 'data1',
	                    renderer: Ext.util.Format.numberRenderer('0'),
	                    orientation: 'horizontal',
	                    color: '#333',
	                  'text-anchor': 'middle'
	                },
	                xField: 'name',
	                yField: ['data1']
	            }]
	        });
	        
	    var win = Ext.create('Ext.Window', {
	        width: 800,
	        height: 600,
	        minHeight: 400,
	        minWidth: 550,
	        hidden: false,
	        maximizable: true,
	        modal: true,
	        title: 'Bar Chart',
	        renderTo: Ext.getBody(),
	        layout: 'fit',
	        tbar: [{
	            text: 'Grabar Chart',
	            handler: function() {
	                Ext.MessageBox.confirm('Confirmar descarga', 'Desea descargar BAR Chart en Imagen?', function(choice){
	                    if(choice == 'yes'){
	                        chart.save({
	                            type: 'image/png'
	                        });
	                    }
	                });
	            }
	        }],
	        items: chart
	    });
	});

}


function generarReporteEmitidosPie() {
	
	generardataReporteEmitidos('datajsonReporteUsuario');
	Ext.onReady(function () {
	
	    var donut = false,
	        chart = Ext.create('Ext.chart.Chart', {
	            xtype: 'chart',
	            id: 'chartCmp',
	            animate: true,
	            store: datajsonReporteUsuario,
	            shadow: true,
	            legend: {
	            	labelFont: '10px Arial',
	                position: 'right'
	            },
	            insetPadding: 60,
	            theme: 'Base:gradients',
	            series: [{
	                type: 'pie',
	                field: 'data1',
	                showInLegend: true,
	                donut: donut,
	                tips: {
	                  trackMouse: true,
	                  width: 340,
	                  height: 28,
	                  renderer: function(storeItem, item) {
	                    //calculate percentage.
	                    var total = 0;
	                    store1.each(function(rec) {
	                        total += rec.get('data1');
	                    });
	                    this.setTitle(storeItem.get('name'));
	                  }
	                },
	                highlight: {
	                  segment: {
	                    margin: 20
	                  }
	                },
	                label: {
	                    field: 'name',
	                    display: 'rotate',
	                    contrast: true,
	                    font: '14px Arial',
	                    renderer: function (label)  {
	                    	var cmp = Ext.getCmp('chartCmp'); 
	                    	var index = cmp.store.findExact('name', label); // the field containing the current label
	                    	var data = cmp.store.getAt(index).data;
	                    	return data.data1 + ' seg';
	                    }
	                }
	            }]
	        });
	
	    var win = Ext.create('Ext.Window', {
	        width: 800,
	        height: 600,
	        minHeight: 400,
	        minWidth: 550,
	        hidden: false,
	        maximizable: true,
	        modal: true,
	        title: 'Pie Chart',
	        renderTo: Ext.getBody(),
	        layout: 'fit',
	        tbar: [{
	            text: 'Grabar Chart',
	            handler: function() {
	                Ext.MessageBox.confirm('Confirmar descarga', 'Desea descargar BAR Chart en Imagen?', function(choice){
	                    if(choice == 'yes'){
	                        chart.save({
	                            type: 'image/png'
	                        });
	                    }
	                });
	            },
	        }, {
	                enableToggle: true,
	                pressed: false,
	                text: 'Donut',
	                toggleHandler: function(btn, pressed) {
	                    var chart = Ext.getCmp('chartCmp');
	                    chart.series.first().donut = pressed ? 35 : false;
	                    chart.refresh();
	                }
	        }],
	        items: chart
	    });
	   
	});

}

