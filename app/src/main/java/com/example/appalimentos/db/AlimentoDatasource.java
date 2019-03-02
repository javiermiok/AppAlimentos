package com.example.appalimentos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appalimentos.model.Alimento;

public class AlimentoDatasource {

    private AlimentoSQLiteHelper ash;

    /*--------------------------------          CONSTRUCTOR           ----------------------------*/
    public AlimentoDatasource(Context contexto) {
        ash = new AlimentoSQLiteHelper(contexto);
    }

    /*--------------------------------          ACCESO DB          ----------------------------*/
    public SQLiteDatabase openReadable() {
        return ash.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return ash.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    /*--------------------------------           CONSULTA           ----------------------------*/
    public Alimento consultarAlimento(String nombre) {
        SQLiteDatabase sdb = openReadable();

        String select = "SELECT * FROM " + AlimentoContract.AlimentoEntity.TABLE_NAME +
                " WHERE " + AlimentoContract.AlimentoEntity.COLUMN_NAME+ " = ?";
        String[] args = {nombre};

        Cursor cursor = sdb.rawQuery(select, args);

        Alimento alimento = null;
        int id;
        String tipo;
        String origen;
        String nutrientes;
        String funcion;

        if(cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(AlimentoContract.AlimentoEntity.COLUMN_ID));
            tipo= cursor.getString(cursor.getColumnIndex(AlimentoContract.AlimentoEntity.COLUMN_TYPE));
            origen = cursor.getString(cursor.getColumnIndex(AlimentoContract.AlimentoEntity.COLUMN_ORIGIN));
            nutrientes = cursor.getString(cursor.getColumnIndex(AlimentoContract.AlimentoEntity.COLUMN_NUTRIENTS));
            funcion = cursor.getString(cursor.getColumnIndex(AlimentoContract.AlimentoEntity.COLUMN_FUNCTION));
            alimento = new Alimento(id, nombre, tipo, origen, nutrientes, funcion);
        }

        cursor.close();
        sdb.close();
        return  alimento;

    }


    /*--------------------------------             ALTA             ----------------------------*/
    public long insertarAlimento(Alimento alimento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(AlimentoContract.AlimentoEntity.COLUMN_NAME, alimento.getNombre());
        cv.put(AlimentoContract.AlimentoEntity.COLUMN_TYPE, alimento.getTipo());
        cv.put(AlimentoContract.AlimentoEntity.COLUMN_ORIGIN, alimento.getOrigen());
        cv.put(AlimentoContract.AlimentoEntity.COLUMN_NUTRIENTS, alimento.getNutrientes());
        cv.put(AlimentoContract.AlimentoEntity.COLUMN_FUNCTION, alimento.getFuncion());

        long id = sdb.insert(AlimentoContract.AlimentoEntity.TABLE_NAME, null, cv);

        if(id != -1) {
            sdb.setTransactionSuccessful();
        }

        sdb.endTransaction();
        close(sdb);
        return id;
    }
    /*--------------------------------            ELIMINAR          ----------------------------*/
    public int borrarAlimento(int idAlimento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        String clausulaWhere = AlimentoContract.AlimentoEntity.COLUMN_ID + " = " + idAlimento;
        int i = sdb.delete(AlimentoContract.AlimentoEntity.TABLE_NAME,
                clausulaWhere, null);

        if (i == 1) {
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);
        return i;
    }
}
