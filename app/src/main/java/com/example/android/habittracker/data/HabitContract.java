package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Lariveg on 22/06/2017.
 */

public final class HabitContract {

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_FREQUENCY = "frequency";
    }

}
