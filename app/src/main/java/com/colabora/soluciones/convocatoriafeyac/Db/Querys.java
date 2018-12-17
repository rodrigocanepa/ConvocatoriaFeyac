package com.colabora.soluciones.convocatoriafeyac.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.CornerPathEffect;

import com.colabora.soluciones.convocatoriafeyac.Modelos.Cliente;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Cotizacion;

import java.util.ArrayList;
import java.util.List;

class CotizacionCursor extends CursorWrapper {
    public CotizacionCursor(Cursor cursor) {
        super(cursor);
    }

    public Cotizacion getCotizacion(){
        Cursor cursor = getWrappedCursor();
        return new Cotizacion(cursor.getInt(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.ID)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.FOLIO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.FECHA)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.SUBTOTAL)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.IVA)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.ENVIO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.DESCUENTO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.TOTAL)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.NOTAS_DESTINATARIO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.TERMINOS)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.NOMBRE_ENCARGADO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.CARGO_ENCARGADO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.NUMERO_ENCARGADO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.VENCIMIENTO)));
    }
}

class ClientesCursor extends CursorWrapper {
    public ClientesCursor(Cursor cursor) {
        super(cursor);
    }

    public Cliente getCliente(){
        Cursor cursor = getWrappedCursor();
        return new Cliente(cursor.getInt(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.ID)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.DESCRIPCION)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.CORREO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.DIRECCION)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.TELEFONO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.HORARIO)));
    }
}

class ProveedorCursor extends CursorWrapper {
    public ProveedorCursor(Cursor cursor) {
        super(cursor);
    }

    public Cliente getCliente(){
        Cursor cursor = getWrappedCursor();
        return new Cliente(cursor.getInt(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.ID)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.DESCRIPCION)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.CORREO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.DIRECCION)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.TELEFONO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ClientesTable.Columns.HORARIO)));
    }
}


public final class Querys {

    private DateBaseHelper dataBaseHelper;
    private SQLiteDatabase db;

    // CREAMOS EL CONSTRUCTOR, .GETWRITABLEDATABASE NOS PERMITIRA ESCRIBIR EN LA MISMA
    public Querys(Context context){
        dataBaseHelper = new DateBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
    }

    public List<Cotizacion> getAllCotizaciones(){
        ArrayList<Cotizacion> list = new ArrayList<Cotizacion>();

        CotizacionCursor cursor = new CotizacionCursor(db.rawQuery("SELECT * FROM " + DBSchema.CotizacionesTable.NAME, null));
        while (cursor.moveToNext()){
            list.add(cursor.getCotizacion());
        }
        cursor.close();

        return list;
    }

    public void insertCotizacion(Cotizacion cotizacion) {
        db.insert(
                DBSchema.CotizacionesTable.NAME,
                null,
                cotizacion.toContentValues());
    }

    public void deleteCotizacion(String cardID) {
        db.delete(
                DBSchema.CotizacionesTable.NAME,
                DBSchema.CotizacionesTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

}
