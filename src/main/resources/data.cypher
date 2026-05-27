// Limpieza de la base de datos
MATCH (n) DETACH DELETE n;

// Carga de temas
CREATE
  (:Tema {id: 1, nombre: "Salud", categoria: "salud"}),
  (:Tema {id: 2, nombre: "Politica", categoria: "politica"}),
  (:Tema {id: 3, nombre: "Tecnologia", categoria: "tecnologia"}),
  (:Tema {id: 4, nombre: "Entretenimiento", categoria: "entretenimiento"});

// Carga de fuentes
CREATE
  (:Fuente {id: 1, nombre: "Diario Horizonte", url: "https://www.diariohorizonte.test", confiable: true}),
  (:Fuente {id: 2, nombre: "Red Alerta Viral", url: "https://www.redalertaviral.test", confiable: false}),
  (:Fuente {id: 3, nombre: "Agencia Verifica", url: "https://www.agenciaverifica.test", confiable: true}),
  (:Fuente {id: 4, nombre: "Mundo Secreto News", url: "https://www.mundosecretonews.test", confiable: false}),
  (:Fuente {id: 5, nombre: "TecnoAnalisis", url: "https://www.tecnoanalisis.test", confiable: true});

// Carga de usuarios
CREATE
  (:Usuario {id: 1, nombre: "Valeria Rios", cantSeguidores: 90000, verificado: true, fechaRegistro: "2020-02-14"}),
  (:Usuario {id: 2, nombre: "Marcos Beltran", cantSeguidores: 64000, verificado: true, fechaRegistro: "2019-11-03"}),
  (:Usuario {id: 3, nombre: "Sofia Ledesma", cantSeguidores: 52000, verificado: true, fechaRegistro: "2021-01-22"}),
  (:Usuario {id: 4, nombre: "Nicolas Ferreyra", cantSeguidores: 41000, verificado: false, fechaRegistro: "2020-07-09"}),
  (:Usuario {id: 5, nombre: "Camila Duarte", cantSeguidores: 35000, verificado: true, fechaRegistro: "2018-05-18"}),
  (:Usuario {id: 6, nombre: "Tomas Ibarra", cantSeguidores: 28000, verificado: false, fechaRegistro: "2021-09-12"}),
  (:Usuario {id: 7, nombre: "Julieta Pardo", cantSeguidores: 21000, verificado: false, fechaRegistro: "2022-03-05"}),
  (:Usuario {id: 8, nombre: "Agustin Molina", cantSeguidores: 15000, verificado: false, fechaRegistro: "2022-06-19"}),
  (:Usuario {id: 9, nombre: "Rocio Campos", cantSeguidores: 12000, verificado: false, fechaRegistro: "2021-12-01"}),
  (:Usuario {id: 10, nombre: "Federico Sosa", cantSeguidores: 9500, verificado: false, fechaRegistro: "2023-01-11"}),
  (:Usuario {id: 11, nombre: "Micaela Torres", cantSeguidores: 7200, verificado: false, fechaRegistro: "2022-10-21"}),
  (:Usuario {id: 12, nombre: "Bruno Salvatierra", cantSeguidores: 5400, verificado: false, fechaRegistro: "2023-04-07"}),
  (:Usuario {id: 13, nombre: "Lucia Benitez", cantSeguidores: 3100, verificado: false, fechaRegistro: "2023-06-30"}),
  (:Usuario {id: 14, nombre: "Diego Arce", cantSeguidores: 2200, verificado: false, fechaRegistro: "2023-08-15"}),
  (:Usuario {id: 15, nombre: "Elena Costa", cantSeguidores: 1800, verificado: false, fechaRegistro: "2023-09-03"}),
  (:Usuario {id: 16, nombre: "Bot Eco Norte", cantSeguidores: 900, verificado: false, fechaRegistro: "2024-01-02"}),
  (:Usuario {id: 17, nombre: "Bot Pulso 24", cantSeguidores: 750, verificado: false, fechaRegistro: "2024-01-02"}),
  (:Usuario {id: 18, nombre: "Bot Tendencia YA", cantSeguidores: 680, verificado: false, fechaRegistro: "2024-01-03"}),
  (:Usuario {id: 19, nombre: "Ana Quiroga", cantSeguidores: 430, verificado: false, fechaRegistro: "2024-02-10"}),
  (:Usuario {id: 20, nombre: "Pablo Medina", cantSeguidores: 100, verificado: false, fechaRegistro: "2024-03-01"});

// Carga de publicaciones
CREATE
  (:Publicacion {id: 1, texto: "Un nuevo jugo casero cura la gripe en 24 horas, aseguran miles de usuarios", fechaCreacion: "2024-01-15", esVerificada: false, cantCompartidos: 12}),
  (:Publicacion {id: 2, texto: "El ministerio confirma calendario oficial de vacunacion para adultos mayores", fechaCreacion: "2024-01-18", esVerificada: true, cantCompartidos: 5}),
  (:Publicacion {id: 3, texto: "Documento filtrado afirma que se suspenderan las elecciones nacionales", fechaCreacion: "2024-02-03", esVerificada: false, cantCompartidos: 10}),
  (:Publicacion {id: 4, texto: "La camara electoral publica nuevas recomendaciones para votar", fechaCreacion: "2024-02-04", esVerificada: true, cantCompartidos: 4}),
  (:Publicacion {id: 5, texto: "Una aplicacion secreta permite leer mensajes privados sin permiso", fechaCreacion: "2024-02-20", esVerificada: false, cantCompartidos: 9}),
  (:Publicacion {id: 6, texto: "Investigadores presentan avance real en baterias de carga rapida", fechaCreacion: "2024-02-25", esVerificada: true, cantCompartidos: 6}),
  (:Publicacion {id: 7, texto: "Famosa actriz abandono el pais tras una denuncia inexistente", fechaCreacion: "2024-03-02", esVerificada: false, cantCompartidos: 7}),
  (:Publicacion {id: 8, texto: "Productora anuncia fechas oficiales de una gira internacional", fechaCreacion: "2024-03-05", esVerificada: true, cantCompartidos: 3}),
  (:Publicacion {id: 9, texto: "Una antena 5G estaria causando dolores de cabeza en todo un barrio", fechaCreacion: "2024-03-10", esVerificada: false, cantCompartidos: 8}),
  (:Publicacion {id: 10, texto: "Hospital publico incorpora nuevo equipo de diagnostico por imagenes", fechaCreacion: "2024-03-12", esVerificada: true, cantCompartidos: 4}),
  (:Publicacion {id: 11, texto: "Cadena viral dice que un banco regalara dinero por reenviar un enlace", fechaCreacion: "2024-03-18", esVerificada: false, cantCompartidos: 6}),
  (:Publicacion {id: 12, texto: "Empresa tecnologica confirma parche de seguridad para sus usuarios", fechaCreacion: "2024-03-21", esVerificada: true, cantCompartidos: 4}),
  (:Publicacion {id: 13, texto: "Audio anonimo asegura que habra cierre total de supermercados", fechaCreacion: "2024-04-02", esVerificada: false, cantCompartidos: 8}),
  (:Publicacion {id: 14, texto: "Organismo oficial publica indice mensual de precios", fechaCreacion: "2024-04-05", esVerificada: true, cantCompartidos: 3}),
  (:Publicacion {id: 15, texto: "Video editado muestra a un cantante anunciando un retiro falso", fechaCreacion: "2024-04-08", esVerificada: false, cantCompartidos: 5});

// Conexion de publicaciones con fuentes y temas
MATCH (p1:Publicacion {id: 1}), (p2:Publicacion {id: 2}), (p3:Publicacion {id: 3}), (p4:Publicacion {id: 4}), (p5:Publicacion {id: 5}), (p6:Publicacion {id: 6}), (p7:Publicacion {id: 7}), (p8:Publicacion {id: 8}), (p9:Publicacion {id: 9}), (p10:Publicacion {id: 10}), (p11:Publicacion {id: 11}), (p12:Publicacion {id: 12}), (p13:Publicacion {id: 13}), (p14:Publicacion {id: 14}), (p15:Publicacion {id: 15}),
      (f1:Fuente {id: 1}), (f2:Fuente {id: 2}), (f3:Fuente {id: 3}), (f4:Fuente {id: 4}), (f5:Fuente {id: 5}),
      (t1:Tema {id: 1}), (t2:Tema {id: 2}), (t3:Tema {id: 3}), (t4:Tema {id: 4})
CREATE
  (p1)-[:ORIGINADA_EN]->(f2), (p1)-[:TRATA_SOBRE]->(t1),
  (p2)-[:ORIGINADA_EN]->(f3), (p2)-[:TRATA_SOBRE]->(t1),
  (p3)-[:ORIGINADA_EN]->(f4), (p3)-[:TRATA_SOBRE]->(t2),
  (p4)-[:ORIGINADA_EN]->(f1), (p4)-[:TRATA_SOBRE]->(t2),
  (p5)-[:ORIGINADA_EN]->(f4), (p5)-[:TRATA_SOBRE]->(t3),
  (p6)-[:ORIGINADA_EN]->(f5), (p6)-[:TRATA_SOBRE]->(t3),
  (p7)-[:ORIGINADA_EN]->(f2), (p7)-[:TRATA_SOBRE]->(t4),
  (p8)-[:ORIGINADA_EN]->(f1), (p8)-[:TRATA_SOBRE]->(t4),
  (p9)-[:ORIGINADA_EN]->(f4), (p9)-[:TRATA_SOBRE]->(t3),
  (p10)-[:ORIGINADA_EN]->(f3), (p10)-[:TRATA_SOBRE]->(t1),
  (p11)-[:ORIGINADA_EN]->(f2), (p11)-[:TRATA_SOBRE]->(t3),
  (p12)-[:ORIGINADA_EN]->(f5), (p12)-[:TRATA_SOBRE]->(t3),
  (p13)-[:ORIGINADA_EN]->(f4), (p13)-[:TRATA_SOBRE]->(t2),
  (p14)-[:ORIGINADA_EN]->(f1), (p14)-[:TRATA_SOBRE]->(t2),
  (p15)-[:ORIGINADA_EN]->(f2), (p15)-[:TRATA_SOBRE]->(t4);

// Carga de relaciones SIGUE entre usuarios
MATCH (u1:Usuario {id: 1}), (u2:Usuario {id: 2}), (u3:Usuario {id: 3}), (u4:Usuario {id: 4}), (u5:Usuario {id: 5}), (u6:Usuario {id: 6}), (u7:Usuario {id: 7}), (u8:Usuario {id: 8}), (u9:Usuario {id: 9}), (u10:Usuario {id: 10}), (u11:Usuario {id: 11}), (u12:Usuario {id: 12}), (u13:Usuario {id: 13}), (u14:Usuario {id: 14}), (u15:Usuario {id: 15}), (u16:Usuario {id: 16}), (u17:Usuario {id: 17}), (u18:Usuario {id: 18}), (u19:Usuario {id: 19}), (u20:Usuario {id: 20})
CREATE
  (u4)-[:SIGUE]->(u1),
  (u6)-[:SIGUE]->(u4),
  (u8)-[:SIGUE]->(u6),
  (u10)-[:SIGUE]->(u8),
  (u12)-[:SIGUE]->(u10),
  (u14)-[:SIGUE]->(u12),
  (u5)-[:SIGUE]->(u2),
  (u7)-[:SIGUE]->(u5),
  (u9)-[:SIGUE]->(u7),
  (u11)-[:SIGUE]->(u9),
  (u13)-[:SIGUE]->(u11),
  (u15)-[:SIGUE]->(u13),
  (u16)-[:SIGUE]->(u2),
  (u17)-[:SIGUE]->(u2),
  (u18)-[:SIGUE]->(u2),
  (u19)-[:SIGUE]->(u15),
  (u20)-[:SIGUE]->(u19),
  (u3)-[:SIGUE]->(u1),
  (u10)-[:SIGUE]->(u3),
  (u12)-[:SIGUE]->(u3),
  (u14)-[:SIGUE]->(u4),
  (u15)-[:SIGUE]->(u5),
  (u16)-[:SIGUE]->(u17),
  (u17)-[:SIGUE]->(u18),
  (u18)-[:SIGUE]->(u16);

// Carga de relaciones COMPARTIO con cadenas de propagacion y patrones de bot
MATCH (u1:Usuario {id: 1}), (u2:Usuario {id: 2}), (u3:Usuario {id: 3}), (u4:Usuario {id: 4}), (u5:Usuario {id: 5}), (u6:Usuario {id: 6}), (u7:Usuario {id: 7}), (u8:Usuario {id: 8}), (u9:Usuario {id: 9}), (u10:Usuario {id: 10}), (u11:Usuario {id: 11}), (u12:Usuario {id: 12}), (u13:Usuario {id: 13}), (u14:Usuario {id: 14}), (u15:Usuario {id: 15}), (u16:Usuario {id: 16}), (u17:Usuario {id: 17}), (u18:Usuario {id: 18}), (u19:Usuario {id: 19}), (u20:Usuario {id: 20}),
      (p1:Publicacion {id: 1}), (p2:Publicacion {id: 2}), (p3:Publicacion {id: 3}), (p4:Publicacion {id: 4}), (p5:Publicacion {id: 5}), (p6:Publicacion {id: 6}), (p7:Publicacion {id: 7}), (p8:Publicacion {id: 8}), (p9:Publicacion {id: 9}), (p10:Publicacion {id: 10}), (p11:Publicacion {id: 11}), (p12:Publicacion {id: 12}), (p13:Publicacion {id: 13}), (p14:Publicacion {id: 14}), (p15:Publicacion {id: 15})
CREATE
  (u1)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:00:00", plataforma: "X"}]->(p1),
  (u4)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:08:12", plataforma: "X"}]->(p1),
  (u6)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:17:30", plataforma: "Facebook"}]->(p1),
  (u8)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:26:45", plataforma: "Instagram"}]->(p1),
  (u10)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:39:20", plataforma: "WhatsApp"}]->(p1),
  (u12)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:52:10", plataforma: "Facebook"}]->(p1),
  (u14)-[:COMPARTIO {fecha: "2024-01-15", hora: "10:08:55", plataforma: "X"}]->(p1),
  (u16)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:01:02", plataforma: "X"}]->(p1),
  (u17)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:01:09", plataforma: "X"}]->(p1),
  (u18)-[:COMPARTIO {fecha: "2024-01-15", hora: "09:01:17", plataforma: "X"}]->(p1),

  (u2)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:10:00", plataforma: "Facebook"}]->(p3),
  (u5)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:19:40", plataforma: "Facebook"}]->(p3),
  (u7)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:31:05", plataforma: "X"}]->(p3),
  (u9)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:44:22", plataforma: "WhatsApp"}]->(p3),
  (u11)-[:COMPARTIO {fecha: "2024-02-03", hora: "12:02:18", plataforma: "Instagram"}]->(p3),
  (u13)-[:COMPARTIO {fecha: "2024-02-03", hora: "12:20:09", plataforma: "Facebook"}]->(p3),
  (u15)-[:COMPARTIO {fecha: "2024-02-03", hora: "12:45:31", plataforma: "X"}]->(p3),
  (u16)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:10:06", plataforma: "X"}]->(p3),
  (u17)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:10:14", plataforma: "X"}]->(p3),
  (u18)-[:COMPARTIO {fecha: "2024-02-03", hora: "11:10:22", plataforma: "X"}]->(p3),

  (u3)-[:COMPARTIO {fecha: "2024-01-18", hora: "08:30:00", plataforma: "X"}]->(p2),
  (u5)-[:COMPARTIO {fecha: "2024-01-18", hora: "09:12:20", plataforma: "Facebook"}]->(p2),
  (u9)-[:COMPARTIO {fecha: "2024-01-18", hora: "10:04:11", plataforma: "Instagram"}]->(p2),
  (u11)-[:COMPARTIO {fecha: "2024-01-18", hora: "12:42:05", plataforma: "WhatsApp"}]->(p2),
  (u19)-[:COMPARTIO {fecha: "2024-01-18", hora: "18:15:30", plataforma: "Facebook"}]->(p2),

  (u1)-[:COMPARTIO {fecha: "2024-02-04", hora: "10:00:00", plataforma: "X"}]->(p4),
  (u3)-[:COMPARTIO {fecha: "2024-02-04", hora: "10:45:10", plataforma: "Facebook"}]->(p4),
  (u10)-[:COMPARTIO {fecha: "2024-02-04", hora: "12:17:21", plataforma: "WhatsApp"}]->(p4),
  (u12)-[:COMPARTIO {fecha: "2024-02-04", hora: "14:09:33", plataforma: "Instagram"}]->(p4),

  (u4)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:00:00", plataforma: "X"}]->(p5),
  (u8)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:06:10", plataforma: "X"}]->(p5),
  (u12)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:20:44", plataforma: "Facebook"}]->(p5),
  (u14)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:39:02", plataforma: "WhatsApp"}]->(p5),
  (u16)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:00:05", plataforma: "X"}]->(p5),
  (u17)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:00:11", plataforma: "X"}]->(p5),
  (u18)-[:COMPARTIO {fecha: "2024-02-20", hora: "15:00:19", plataforma: "X"}]->(p5),

  (u5)-[:COMPARTIO {fecha: "2024-02-25", hora: "09:20:00", plataforma: "LinkedIn"}]->(p6),
  (u6)-[:COMPARTIO {fecha: "2024-02-25", hora: "10:11:45", plataforma: "X"}]->(p6),
  (u7)-[:COMPARTIO {fecha: "2024-02-25", hora: "10:49:27", plataforma: "Facebook"}]->(p6),
  (u11)-[:COMPARTIO {fecha: "2024-02-25", hora: "13:25:50", plataforma: "LinkedIn"}]->(p6),

  (u6)-[:COMPARTIO {fecha: "2024-03-02", hora: "17:05:00", plataforma: "Instagram"}]->(p7),
  (u8)-[:COMPARTIO {fecha: "2024-03-02", hora: "17:12:41", plataforma: "X"}]->(p7),
  (u10)-[:COMPARTIO {fecha: "2024-03-02", hora: "17:26:12", plataforma: "Facebook"}]->(p7),
  (u13)-[:COMPARTIO {fecha: "2024-03-02", hora: "17:47:33", plataforma: "WhatsApp"}]->(p7),
  (u15)-[:COMPARTIO {fecha: "2024-03-02", hora: "18:08:01", plataforma: "Instagram"}]->(p7),

  (u1)-[:COMPARTIO {fecha: "2024-03-05", hora: "12:00:00", plataforma: "Instagram"}]->(p8),
  (u5)-[:COMPARTIO {fecha: "2024-03-05", hora: "13:18:10", plataforma: "X"}]->(p8),
  (u15)-[:COMPARTIO {fecha: "2024-03-05", hora: "19:20:11", plataforma: "Facebook"}]->(p8),

  (u2)-[:COMPARTIO {fecha: "2024-03-10", hora: "07:45:00", plataforma: "X"}]->(p9),
  (u4)-[:COMPARTIO {fecha: "2024-03-10", hora: "07:57:33", plataforma: "Facebook"}]->(p9),
  (u7)-[:COMPARTIO {fecha: "2024-03-10", hora: "08:15:10", plataforma: "WhatsApp"}]->(p9),
  (u9)-[:COMPARTIO {fecha: "2024-03-10", hora: "08:21:09", plataforma: "Instagram"}]->(p9),
  (u16)-[:COMPARTIO {fecha: "2024-03-10", hora: "07:45:07", plataforma: "X"}]->(p9),
  (u17)-[:COMPARTIO {fecha: "2024-03-10", hora: "07:45:13", plataforma: "X"}]->(p9),
  (u18)-[:COMPARTIO {fecha: "2024-03-10", hora: "07:45:21", plataforma: "X"}]->(p9),

  (u3)-[:COMPARTIO {fecha: "2024-03-12", hora: "14:30:00", plataforma: "Facebook"}]->(p10),
  (u11)-[:COMPARTIO {fecha: "2024-03-12", hora: "16:03:28", plataforma: "X"}]->(p10),
  (u13)-[:COMPARTIO {fecha: "2024-03-12", hora: "18:40:13", plataforma: "WhatsApp"}]->(p10),

  (u8)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:00:00", plataforma: "WhatsApp"}]->(p11),
  (u10)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:04:32", plataforma: "WhatsApp"}]->(p11),
  (u12)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:11:48", plataforma: "Facebook"}]->(p11),
  (u16)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:00:09", plataforma: "X"}]->(p11),
  (u17)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:00:16", plataforma: "X"}]->(p11),
  (u18)-[:COMPARTIO {fecha: "2024-03-18", hora: "20:00:25", plataforma: "X"}]->(p11),

  (u5)-[:COMPARTIO {fecha: "2024-03-21", hora: "11:35:00", plataforma: "LinkedIn"}]->(p12),
  (u6)-[:COMPARTIO {fecha: "2024-03-21", hora: "12:00:18", plataforma: "X"}]->(p12),
  (u20)-[:COMPARTIO {fecha: "2024-03-21", hora: "21:55:40", plataforma: "Facebook"}]->(p12),

  (u2)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:20:00", plataforma: "Facebook"}]->(p13),
  (u5)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:30:12", plataforma: "WhatsApp"}]->(p13),
  (u7)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:46:05", plataforma: "Facebook"}]->(p13),
  (u9)-[:COMPARTIO {fecha: "2024-04-02", hora: "07:05:30", plataforma: "Instagram"}]->(p13),
  (u16)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:20:06", plataforma: "X"}]->(p13),
  (u17)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:20:12", plataforma: "X"}]->(p13),
  (u18)-[:COMPARTIO {fecha: "2024-04-02", hora: "06:20:18", plataforma: "X"}]->(p13),

  (u1)-[:COMPARTIO {fecha: "2024-04-05", hora: "10:10:00", plataforma: "X"}]->(p14),
  (u3)-[:COMPARTIO {fecha: "2024-04-05", hora: "10:58:19", plataforma: "Facebook"}]->(p14),
  (u4)-[:COMPARTIO {fecha: "2024-04-05", hora: "12:44:01", plataforma: "LinkedIn"}]->(p14),

  (u6)-[:COMPARTIO {fecha: "2024-04-08", hora: "16:40:00", plataforma: "Instagram"}]->(p15),
  (u8)-[:COMPARTIO {fecha: "2024-04-08", hora: "16:52:22", plataforma: "X"}]->(p15),
  (u10)-[:COMPARTIO {fecha: "2024-04-08", hora: "17:10:34", plataforma: "Facebook"}]->(p15),
  (u15)-[:COMPARTIO {fecha: "2024-04-08", hora: "17:55:08", plataforma: "WhatsApp"}]->(p15);
