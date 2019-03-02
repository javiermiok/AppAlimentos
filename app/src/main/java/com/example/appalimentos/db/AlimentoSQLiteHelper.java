package com.example.appalimentos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.appalimentos.model.Alimento;

import java.util.ArrayList;

public class AlimentoSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "AlimentosDB";
    static final int DATABASE_VERSION = 1;
    ArrayList<Alimento> datosIniciales;

    static final String CREATE_TABLE_ALIMENTOS=
            "CREATE TABLE "+ AlimentoContract.AlimentoEntity.TABLE_NAME+ "( "+
                    AlimentoContract.AlimentoEntity.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    AlimentoContract.AlimentoEntity.COLUMN_NAME+" TEXT NOT NULL," +
                    AlimentoContract.AlimentoEntity.COLUMN_TYPE+" TEXT NOT NULL," +
                    AlimentoContract.AlimentoEntity.COLUMN_ORIGIN +" TEXT NOT NULL," +
                    AlimentoContract.AlimentoEntity.COLUMN_NUTRIENTS +" TEXT NOT NULL," +
                    AlimentoContract.AlimentoEntity.COLUMN_FUNCTION +" TEXT NOT NULL);";

    public AlimentoSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALIMENTOS);
        iniciarDatos();
        cargarDatos(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AlimentoContract.AlimentoEntity.TABLE_NAME);
        onCreate(db);
    }

    private void iniciarDatos() {
        datosIniciales = new ArrayList<Alimento>();

        datosIniciales.add(new Alimento("arroz", "Cereales", "Vegetal", "Carbohidratos", "Energética"));
        datosIniciales.add(new Alimento("pasta","Cereales","Vegetal","Carbohidratos","Energética"));
        datosIniciales.add(new Alimento("pan","Cereales","Vegetal","Carbohidratos","Energética"));
        datosIniciales.add(new Alimento("leche","Lácteos","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("queso","Lácteos","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("yogurt","Lácteos","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("huevos","Huevos","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("azúcar","Azúcares","Vegetal","Carbohidratos","Energética"));
        datosIniciales.add(new Alimento("chocolate","Azúcares","Vegetal","Carbohidratos","Energética"));
        datosIniciales.add(new Alimento("aceite oliva","Aceites","Vegetal","Lípidos","Energética"));
        datosIniciales.add(new Alimento("berenjena","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("calabacín","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("tomate","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("zanahoria","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("patata","Verduras y Hortalizas","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        datosIniciales.add(new Alimento("garbanzos","Legumbres","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        datosIniciales.add(new Alimento("lentejas","Legumbres","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        datosIniciales.add(new Alimento("naranja","Frutas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("plátano","Frutas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("manzana","Frutas","Vegetal","Vitaminas","Reguladora"));
        datosIniciales.add(new Alimento("almendra","Frutos secos","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        datosIniciales.add(new Alimento("nuez","Frutos secos","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        datosIniciales.add(new Alimento("jamón","Carne","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("conejo","Carne","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("pollo","Carne","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("bacalao","Pescado","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("merluza","Pescado","Animal","Proteínas animales","Plástica"));
        datosIniciales.add(new Alimento("salmón","Pescado","Animal","Proteínas animales","Plástica"));

    }

    private void cargarDatos(SQLiteDatabase db) {
        db.beginTransaction();

        ContentValues cv = null;

        for (Alimento alimento: datosIniciales) {
            cv = new ContentValues();
            cv.put(AlimentoContract.AlimentoEntity.COLUMN_NAME, alimento.getNombre());
            cv.put(AlimentoContract.AlimentoEntity.COLUMN_TYPE, alimento.getTipo());
            cv.put(AlimentoContract.AlimentoEntity.COLUMN_ORIGIN, alimento.getOrigen());
            cv.put(AlimentoContract.AlimentoEntity.COLUMN_NUTRIENTS, alimento.getNutrientes());
            cv.put(AlimentoContract.AlimentoEntity.COLUMN_FUNCTION, alimento.getFuncion());
            db.insert(AlimentoContract.AlimentoEntity.TABLE_NAME, null, cv);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
