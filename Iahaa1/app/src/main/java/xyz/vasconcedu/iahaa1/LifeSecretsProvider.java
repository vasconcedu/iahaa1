package xyz.vasconcedu.iahaa1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class LifeSecretsProvider extends ContentProvider {

    private SQLiteDatabase db;

    public LifeSecretsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('" + values.get("secret") + "');");
        return null;
    }

    @Override
    public boolean onCreate() {

        db = SQLiteDatabase.openOrCreateDatabase(getContext().getDatabasePath("Iahaa1_db").getAbsolutePath(), null);

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS life_secrets (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "secret TEXT NOT NULL" +
            ");"
        );

        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('Brush your teeth');");
        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('Go to bed early');");
        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('Eat vegetables');");
        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('Stay hydrated');");
        db.execSQL("INSERT INTO life_secrets (secret) VALUES ('Study hard');");

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.i("[Iahaa1]", "Entered query, fetching '" + selection + "'");

        return db.rawQuery("SELECT secret FROM life_secrets WHERE secret LIKE '%" + selection + "%';", null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
