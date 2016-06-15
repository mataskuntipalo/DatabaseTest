package com.example.test.testdb;


        import android.database.Cursor;
        import android.database.SQLException;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("amphur", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(MainActivity.this,
                                "code: " + c.getString(0) + "\n" +
                                        "name_th: " + c.getString(1) + "\n" +
                                        "name_en: " + c.getString(2) + "\n" +
                                        "province_code: " + c.getString(3) + "\n" +
                                        "nation_code:  " + c.getString(4),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });

    }


}
