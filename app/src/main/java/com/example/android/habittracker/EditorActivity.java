package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

/**
 * Created by Lariveg on 22/06/2017.
 */

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the habit name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the habit's  frequency
     */
    private EditText mFrequencyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_habit_name);
        mFrequencyEditText = (EditText) findViewById(R.id.edit_habit_frequency);

    }


    private void insertHabit() {
        //Read form input fields
        //Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String frequencyString = mFrequencyEditText.getText().toString().trim();

        try {
            int frequency = Integer.parseInt(frequencyString);

            //Create database helper
            HabitDbHelper mDbHelper = new HabitDbHelper(this);

            //Gets the database in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            //Create a ContentValues object where column names are the keys
            //and habit attributes from the editor are values.
            ContentValues values = new ContentValues();
            values.put(HabitEntry.COLUMN_HABIT_NAME, nameString);
            values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, frequency);

            //Insert a new row for a habit in the database, returning the ID of that new row.
            long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please insert a number for frequency", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                //Save habit to database
                insertHabit();
                //Exit activity
                finish();
                return true;
            case android.R.id.home:
                // Navigate back to parent activity (MainActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
