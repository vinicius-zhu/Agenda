package br.edu.ifspsaocarlos.agenda.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "agenda.db";
    static final String DATABASE_TABLE = "contatos";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_EMAIL = "email";
    static final String KEY_FAVORITE = "favorito";
    static final String KEY_FONE2 = "fone2";
    static final String KEY_ANIVERSARIO = "aniversario";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_CREATE = "CREATE TABLE "+ DATABASE_TABLE +" (" +
            KEY_ID  +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, "  +
            KEY_EMAIL + " TEXT, " +
            KEY_FAVORITE + " INTEGER(1), " + // v2
            KEY_FONE2 + "TEXT, "+ //v3
            KEY_ANIVERSARIO + "TEXT);"; //v4


    SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int    newVersion) {
        switch(newVersion)
        {
            case 1:
                break;
            case 2:
                switch(oldVersion)
                {
                    case 1:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_FAVORITE + " INTEGER(1)");
                        database.execSQL("UPDATE  " + DATABASE_TABLE + " SET " + KEY_FAVORITE + " = 0");
                    default:
                        break;
                }
                break;
            case 3:
                switch(oldVersion)
                {
                    case 1:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_FAVORITE + " INTEGER(1)");
                        database.execSQL("UPDATE  " + DATABASE_TABLE + " SET " + KEY_FAVORITE + " = 0");
                    case 2:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_FONE2 + " TEXT");
                    default:
                        break;
                }
                break;
            case 4:
                switch(oldVersion)
                {
                    case 1:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_FAVORITE + " INTEGER(1)");
                        database.execSQL("UPDATE  " + DATABASE_TABLE + " SET " + KEY_FAVORITE + " = 0");
                    case 2:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_FONE2 + " TEXT");
                    case 3:
                        database.execSQL("ALTER TABLE " + DATABASE_TABLE + " ADD " + KEY_ANIVERSARIO + " TEXT");
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}

