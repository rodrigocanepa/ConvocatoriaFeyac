package com.colabora.soluciones.convocatoriafeyac.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.CornerPathEffect;

import com.colabora.soluciones.convocatoriafeyac.Modelos.Cliente;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Concepto;
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
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.VENCIMIENTO)),
                cursor.getString(cursor.getColumnIndex(DBSchema.CotizacionesTable.Columns.NOTAS_ADMIN)));
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

class ConceptopsCursor extends CursorWrapper {
    public ConceptopsCursor(Cursor cursor) {
        super(cursor);
    }

    public Concepto getConcepto(){
        Cursor cursor = getWrappedCursor();
        return new Concepto(cursor.getInt(cursor.getColumnIndex(DBSchema.ConceptopsTable.Columns.ID)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ConceptopsTable.Columns.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBSchema.ConceptopsTable.Columns.PRECIO)));
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

    public Cliente getClientePorFiltro(String idUser){

        String query = "SELECT * FROM " + DBSchema.ClientesTable.NAME + " where nombre like '%" + idUser + "%'";
        Cliente myUser = new Cliente();
        ClientesCursor cursor = new ClientesCursor(db.rawQuery(query, null));
        while (cursor.moveToNext()){
            myUser = cursor.getCliente();
        }
        cursor.close();

        return myUser;
    }

    public Cliente getProveedorPorFiltro(String idUser){

        String query = "SELECT * FROM " + DBSchema.ProveedoresTable.NAME + " where nombre like '%" + idUser + "%'";
        Cliente myUser = new Cliente();
        ClientesCursor cursor = new ClientesCursor(db.rawQuery(query, null));
        while (cursor.moveToNext()){
            myUser = cursor.getCliente();
        }
        cursor.close();

        return myUser;
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

    public List<Concepto> getAllConceptos(){
        ArrayList<Concepto> list = new ArrayList<Concepto>();

        ConceptopsCursor cursor = new ConceptopsCursor(db.rawQuery("SELECT * FROM " + DBSchema.ConceptopsTable.NAME, null));
        while (cursor.moveToNext()){
            list.add(cursor.getConcepto());
        }
        cursor.close();

        return list;
    }

    public List<Cliente> getAllClientes(){
        ArrayList<Cliente> list = new ArrayList<Cliente>();

        ClientesCursor cursor = new ClientesCursor(db.rawQuery("SELECT * FROM " + DBSchema.ClientesTable.NAME, null));
        while (cursor.moveToNext()){
            list.add(cursor.getCliente());
        }
        cursor.close();

        return list;
    }

    public List<Cliente> getAllProveedores(){
        ArrayList<Cliente> list = new ArrayList<Cliente>();

        ClientesCursor cursor = new ClientesCursor(db.rawQuery("SELECT * FROM " + DBSchema.ProveedoresTable.NAME, null));
        while (cursor.moveToNext()){
            list.add(cursor.getCliente());
        }
        cursor.close();

        return list;
    }

    public void insertClientes(Cliente cliente) {
        db.insert(
                DBSchema.ClientesTable.NAME,
                null,
                cliente.toContentValues());
    }

    public void insertProveedores(Cliente cliente) {
        db.insert(
                DBSchema.ProveedoresTable.NAME,
                null,
                cliente.toContentValues());
    }

    public void deleteCliente(String cardID) {
        db.delete(
                DBSchema.ClientesTable.NAME,
                DBSchema.ClientesTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void deleteProveedor(String cardID) {
        db.delete(
                DBSchema.ProveedoresTable.NAME,
                DBSchema.ProveedoresTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void insertCotizacion(Cotizacion cotizacion) {
        db.insert(
                DBSchema.CotizacionesTable.NAME,
                null,
                cotizacion.toContentValues());
    }

    public void insertConcepto(Concepto concepto) {
        db.insert(
                DBSchema.ConceptopsTable.NAME,
                null,
                concepto.toContentValues());
    }

    public void deleteCotizacion(String cardID) {
        db.delete(
                DBSchema.CotizacionesTable.NAME,
                DBSchema.CotizacionesTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void deleteConcepto(String cardID) {
        db.delete(
                DBSchema.ConceptopsTable.NAME,
                DBSchema.ConceptopsTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void updateCliente(Cliente cliente, String cardID) {
        db.update(
                DBSchema.ClientesTable.NAME,
                cliente.toContentValues(),
                DBSchema.ClientesTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void updateConcepto(Concepto concepto, String cardID) {
        db.update(
                DBSchema.ConceptopsTable.NAME,
                concepto.toContentValues(),
                DBSchema.ConceptopsTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }

    public void updateProveedor(Cliente cliente, String cardID) {
        db.update(
                DBSchema.ProveedoresTable.NAME,
                cliente.toContentValues(),
                DBSchema.ProveedoresTable.Columns.ID + " LIKE ?",
                new String[]{cardID});
    }
}
