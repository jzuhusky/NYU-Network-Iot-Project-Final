package pl.llp.aircasting.storage.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ags on 05/07/12 at 15:07
 */
public interface ReadOnlyDatabaseTask<T> extends DatabaseTask<T>
{
  T execute(SQLiteDatabase readOnlyDatabase);
}
