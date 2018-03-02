package br.edu.unidavi.unidavijava.features.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.unidavi.unidavijava.features.home.MainActivity;
import br.edu.unidavi.unidavijava.R;

public class LoginActivity extends AppCompatActivity {

    public static final String FIELD_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tryLogin();
                    }
                }
        );

        EditText editTextEmail = findViewById(R.id.input_email);
        editTextEmail.setText(getEmailInSession());

        EditText editTextPassword = findViewById(R.id.input_password);
        editTextPassword.requestFocus();
    }

    public void tryLogin(){
        EditText inputEmail = findViewById(R.id.input_email);
        String emailValue = inputEmail.getText().toString();

        EditText inputPassword = findViewById(R.id.input_password);
        String passwordValue = inputPassword.
                getText().toString();

        if("teste".equals(emailValue) &&
                "teste".equals(passwordValue)){
            saveEmailInSession(emailValue);
            goToHome(emailValue);
        }else{
            Log.d("DEBUG", "NÃO DEU CERTO");
        }
    }

    private void saveEmailInSession(String emailValue) {
        SharedPreferences sharedPreferences =
                getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, emailValue);
        editor.commit();
    }

    private String getEmailInSession(){
        SharedPreferences sharedPreferences =
                getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }

    private void goToHome(String emailValue){
        Intent intent = new Intent(this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
