CREATE TABLE "cotizaciones" ("id" INTEGER PRIMARY KEY AUTOINCREMENT,
                        "nombre" TEXT,
                        "folio" TEXT,
                        "fecha" TEXT,
                        "subtotal" TEXT,
                        "iva" TEXT,
                        "envio" TEXT,
                        "descuento" TEXT,
                        "total" TEXT,
                        "notasDestinatario" TEXT,
                        "terminos" TEXT,
                        "nombre_enc" TEXT,
                        "cargo_enc" TEXT,
                        "numero_enc" TEXT,
                        "vencimiento" TEXT,
                        "notasAdmin" TEXT);

CREATE TABLE "clientes" ("id" INTEGER PRIMARY KEY AUTOINCREMENT,
                                                 "nombre" TEXT,
                                                 "descripcion" TEXT,
                                                 "correo" TEXT,
                                                 "direccion" TEXT,
                                                 "telefono" TEXT,
                                                 "horario" TEXT);
CREATE TABLE "proveedores" ("id" INTEGER PRIMARY KEY AUTOINCREMENT,
                        "nombre" TEXT,
                        "descripcion" TEXT,
                        "correo" TEXT,
                        "direccion" TEXT,
                        "telefono" TEXT,
                        "horario" TEXT);

CREATE TABLE "conceptos" ("id" INTEGER PRIMARY KEY AUTOINCREMENT,
                        "nombre" TEXT,
                        "precio" TEXT);

CREATE TABLE "CotizacionesConceptos" ("id" INTEGER PRIMARY KEY AUTOINCREMENT,
                        "idCotizacion" TEXT,
                        "nombre" TEXT,
                        "cantidad" TEXT,
                        "precio" TEXT,
                        "importe" TEXT,
                        "iva" TEXT,
                        "iva_precio" TEXT);