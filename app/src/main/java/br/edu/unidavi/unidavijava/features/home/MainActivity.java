package br.edu.unidavi.unidavijava.features.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.data.Session;
import br.edu.unidavi.unidavijava.model.Meme;
import br.edu.unidavi.unidavijava.web.WebTaskMemes;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MemeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE", "CREATE");
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_memes);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MemeAdapter(new ArrayList<Meme>(),this);
        recyclerView.setAdapter(adapter);
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

        if(memeList.size() == 0){
            findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            findViewById(R.id.recycler_memes).setVisibility(View.GONE);
        }else{
            findViewById(R.id.recycler_memes).setVisibility(View.VISIBLE);
            findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapter.memeList = memeList;
            adapter.notifyDataSetChanged();
        }

    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(findViewById(R.id.container), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }

}
