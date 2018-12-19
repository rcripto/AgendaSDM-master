package br.edu.ifspsaocarlos.agenda.data;
import android.content.Context;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    static final String DATABASE_TABLE = "contatos";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_CELLPHONE = "cellPhone";
    static final String KEY_EMAIL = "email";
    static final String KEY_ANIVERSARIO = "aniversario";
    static final String KEY_FAVORITE = "favorite";

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_CREATE = "CREATE TABLE "+ DATABASE_TABLE +" (" +
            KEY_ID  +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, "  +
            KEY_EMAIL + " TEXT," +
            KEY_FAVORITE + " INTEGER, " +
            KEY_CELLPHONE + " TEXT, " +
            KEY_ANIVERSARIO + " TEXT);";

    SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion <= 2) {
            String upgradeDatabase = "ALTER TABLE " + DATABASE_TABLE + " ADD COLUMN " + KEY_FAVORITE + " INTEGER";
            database.execSQL(upgradeDatabase);
        }

        if (oldVersion <= 3) {
            String upgradeDatabase = "ALTER TABLE " + DATABASE_TABLE + " ADD COLUMN " + KEY_CELLPHONE + "  TEXT";
            database.execSQL(upgradeDatabase);
        }

        if (oldVersion <= 4) {
            String upgradeDatabase = "ALTER TABLE " + DATABASE_TABLE + " ADD COLUMN " + KEY_ANIVERSARIO + " TEXT";
            database.execSQL(upgradeDatabase);
        }
    }
}

