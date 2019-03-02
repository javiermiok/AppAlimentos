package com.example.appalimentos.db;

import android.provider.BaseColumns;

public class AlimentoContract {

    public static abstract class AlimentoEntity implements BaseColumns {

        public static final String TABLE_NAME = "ALIMENTOS";

        public static final String COLUMN_ID = BaseColumns._ID;

        public static final String COLUMN_NAME= "NOMBRE";
        public static final String COLUMN_TYPE = "TIPO";
        public static final String COLUMN_ORIGIN = "ORIGEN";
        public static final String COLUMN_NUTRIENTS = "NUTRIENTES";
        public static final String COLUMN_FUNCTION = "FUNCION";
    }

}
