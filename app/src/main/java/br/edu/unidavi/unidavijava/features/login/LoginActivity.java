package br.edu.unidavi.unidavijava.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.data.Session;
import br.edu.unidavi.unidavijava.features.home.MainActivity;

public class LoginActivity extends AppCompatActivity {

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


        Session session = new Session();
        EditText editTextEmail = findViewById(R.id.input_email);
        editTextEmail.setText(session.getEmailInSession(getApplicationContext()));

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
            Session session = new Session();
            session.saveEmailInSession(getApplicationContext(),emailValue);
            goToHome(emailValue);
        }else{
            Log.d("DEBUG", "NÃO DEU CERTO");
        }
    }


    private void goToHome(String emailValue){
        Intent intent = new Intent(this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        startActivity(intent);
//        finish();
//    }
}
