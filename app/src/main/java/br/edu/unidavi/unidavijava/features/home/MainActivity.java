package br.edu.unidavi.unidavijava.features.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.data.Session;
import br.edu.unidavi.unidavijava.model.Meme;
import br.edu.unidavi.unidavijava.web.WebTaskMemes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE", "CREATE");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("LIFECYCLE", "START");

        Session mySession = new Session(this);
        WebTaskMemes taskMemes = new WebTaskMemes(this,
                mySession.getTokenInSession());
        taskMemes.execute();
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
        EventBus.getDefault().unregister(this);
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

    @Subscribe
    public void onEvent(List<Meme> memeList){
        Log.d("DEBUG", memeList.size() +
                " memes existentes");
    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(findViewById(R.id.container), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

}
