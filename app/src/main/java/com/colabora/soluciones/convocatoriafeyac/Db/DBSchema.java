package com.colabora.soluciones.convocatoriafeyac.Db;

public final class DBSchema {

    public static final class CotizacionesTable{
        public static final String NAME = "cotizaciones";

        public static final class Columns {
            public static final String ID = "id";
            public static final String FOLIO = "folio";
            public static final String FECHA = "fecha";
            public static final String SUBTOTAL = "subtotal";
            public static final String IVA = "iva";
            public static final String ENVIO = "envio";
            public static final String DESCUENTO = "descuento";
            public static final String TOTAL = "total";
            public static final String NOTAS_DESTINATARIO = "notasDestinatario";
            public static final String TERMINOS = "terminos";
            public static final String NOMBRE_ENCARGADO = "nombre_enc";
            public static final String CARGO_ENCARGADO = "cargo_enc";
            public static final String NUMERO_ENCARGADO = "numero_enc";
            public static final String VENCIMIENTO = "vencimiento";
            public static final String NOTAS_ADMIN = "notasAdmin";

        }
    }

    public static final class ClientesTable{
        public static final String NAME = "clientes";

        public static final class Columns {
            public static final String ID = "id";
            public static final String NOMBRE = "nombre";
            public static final String DESCRIPCION = "descripcion";
            public static final String CORREO = "correo";
            public static final String DIRECCION = "direccion";
            public static final String TELEFONO = "telefono";
            public static final String HORARIO = "horario";
        }
    }

    public static final class ProveedoresTable{
        public static final String NAME = "proveedores";

        public static final class Columns {
            public static final String ID = "id";
            public static final String NOMBRE = "nombre";
            public static final String DESCRIPCION = "descripcion";
            public static final String CORREO = "correo";
            public static final String DIRECCION = "direccion";
            public static final String TELEFONO = "telefono";
            public static final String HORARIO = "horario";
        }
    }
}
