package com.vms.android.vancouvermetalshows;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateJson extends JSONObject {

    public JSONObject makeJSONObj (int id,String artist, String date, String venue, String sup_art, String tix, int res)
    {
        JSONObject jsonObject = new JSONObject();

        try
        {
            jsonObject.put("id", id);
            jsonObject.put("artist", artist);
            jsonObject.put("date", date);
            jsonObject.put("venue", venue);
            jsonObject.put("supporting_artists", sup_art);
            jsonObject.put("tickets", tix);
            jsonObject.put("resource", res);

        }catch(JSONException jsonException)
        {
            jsonException.printStackTrace();
        }

        return jsonObject;
    }
}
