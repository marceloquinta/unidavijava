package br.edu.unidavi.unidavijava.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unidavi.unidavijava.R;
import br.edu.unidavi.unidavijava.model.Meme;

/**
 * Created by marceloquinta on 03/03/2018.
 */

public class WebTaskMemes extends WebTaskBase {

    private static final String SERVICE_NAME = "/memes";
    private String token;

    public WebTaskMemes(Context context, String token) {
        super(context, SERVICE_NAME);
        this.token = token;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> myMap = new HashMap<>();
        myMap.put("token", token);

        JSONObject requestJson = new JSONObject(myMap);

        return requestJson.toString();
    }

    @Override
    public void handleResponse(String response) {

        List<Meme> memeList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject memeJSON = (JSONObject)
                        jsonArray.get(index);
                Meme myMeme = new Meme();
                myMeme.setName(memeJSON.getString("name"));
                myMeme.setUrl(memeJSON.getString("url"));
                myMeme.setPhotoUrl(memeJSON.getString("image"));
                memeList.add(myMeme);
            }

            EventBus.getDefault().post(memeList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(
                    R.string.error_request)));
        }
    }


}
