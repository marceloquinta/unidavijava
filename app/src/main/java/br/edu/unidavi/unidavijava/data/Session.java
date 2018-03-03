package br.edu.unidavi.unidavijava.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marceloquinta on 02/03/2018.
 */

public class Session {

    private final String FIELD_USERNAME = "username";
    private final String PREF_NAME = "session";

    public void saveEmailInSession(Context context,
                                   String emailValue){

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, emailValue);
        editor.commit();
    }

    public String getEmailInSession(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,
                        Context.MODE_PRIVATE);
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }

}
