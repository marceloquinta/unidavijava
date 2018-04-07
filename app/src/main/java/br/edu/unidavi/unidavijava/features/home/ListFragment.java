package br.edu.unidavi.unidavijava.features.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.data.Session;
import br.edu.unidavi.unidavijava.model.Meme;
import br.edu.unidavi.unidavijava.web.WebTaskMemes;


public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MemeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_list,
                container, false);


        recyclerView = view.findViewById(R.id.recycler_memes);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new MemeAdapter(
                new ArrayList<Meme>(),getActivity());
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("LIFECYCLE", "START");

        Session mySession = new Session(getActivity());
        WebTaskMemes taskMemes = new WebTaskMemes(getActivity(),
                mySession.getTokenInSession());
        taskMemes.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.d("LIFECYCLE", "STOP");
    }

    @Subscribe
    public void onEvent(List<Meme> memeList){

        if(memeList.size() == 0){
            getView().findViewById(R.id.container_empty).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.recycler_memes).setVisibility(View.GONE);
        }else{
            getView().findViewById(R.id.recycler_memes).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.container_empty).setVisibility(View.GONE);
            adapter.setMemeList(memeList);
            adapter.notifyDataSetChanged();
        }

    }

    @Subscribe
    public void onEvent(Error error){
        Snackbar.make(getView(), error.getMessage(),
                Snackbar.LENGTH_LONG).show();
    }


}
