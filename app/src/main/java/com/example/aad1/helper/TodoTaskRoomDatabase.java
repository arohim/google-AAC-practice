package com.example.aad1.helper;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.aad1.model.TodoTask;
import com.example.aad1.model.TodoTaskDAO;

import static com.example.aad1.model.TodoTask.NO_DUE_DATE;

@Database(entities = {TodoTask.class}, version = 1, exportSchema = false)
public abstract class TodoTaskRoomDatabase extends RoomDatabase {

    public abstract TodoTaskDAO todoTaskDAO();

    private static TodoTaskRoomDatabase INSTANCE;

    public static TodoTaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodoTaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoTaskRoomDatabase.class, "todo_task_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TodoTaskDAO mDao;

        TodoTask[] todoTasks = {
                new TodoTask("name", "description", 1, NO_DUE_DATE, 0),
                new TodoTask("name2", "description2", 2, 1564825993, 1),
                new TodoTask("name3", "description3", 3, 1564825993, 0),
                new TodoTask("name4", "description4", 1, NO_DUE_DATE, 0),
                new TodoTask("name5", "description5", 2, 1577836800, 1),
        };

        public PopulateDbAsync(TodoTaskRoomDatabase db) {
            mDao = db.todoTaskDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= todoTasks.length - 1; i++) {
                mDao.insert(todoTasks[i]);
            }
            return null;
        }

    }

}
