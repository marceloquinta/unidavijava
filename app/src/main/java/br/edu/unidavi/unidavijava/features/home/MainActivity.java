package br.edu.unidavi.unidavijava.features.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.features.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE", "CREATE");
        setContentView(R.layout.activity_main);

        String username =
                getIntent().getStringExtra(
                        LoginActivity.FIELD_USERNAME);

        TextView textViewUsername = findViewById(R.id.label_name);
        textViewUsername.setText(username);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE", "START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE", "RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE", "PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE", "STOP");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFECYCLE", "RESTART");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE", "DESTROY");
    }
}
