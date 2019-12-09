package com.example.hmnotes;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Provides methods for contacting the server. Used starting in Checkpoint 2.
 * <p>
 * STOP! Do not modify this file. Changes will be overwritten during official grading.
 */
public final class WebApi {

    /** Tag for logged messages. */
    private static final String TAG = "WebApi";

    /** The HTTP status code for Bad Request. */
    private static final int HTTP_BAD_REQUEST = 400;

    /** Timeout (milliseconds) for connecting to a game websocket. */
    private static final int WEBSOCKET_CONNECTION_TIMEOUT = 5000;

    /** Interval (milliseconds) at which to make sure the websocket is still connected. */
    private static final int WEBSOCKET_PING_INTERVAL = 60000;

    /** The Volley request queue for the application, or null if the queue hasn't been set up yet. */
    private static RequestQueue requestQueue;

    /** The Gson parser used to parse response JSON. */
    private static JsonParser jsonParser = new JsonParser();

    /** Private constructor to prevent creating instances. */
    private WebApi() { }

    /**
     * Starts an HTTP GET request.
     * @param context an Android context
     * @param url the URL to contact
     * @param listener callback to run with response data
     * @param errorListener callback to run if an error occurs
     */
    public static void startRequest(final Context context, final String url,
                                    final Response.Listener<JsonObject> listener, final Response.ErrorListener errorListener) {
        startRequest(context, url, Request.Method.GET, null, listener, errorListener);
    }

    /**
     * Starts a network request with a JSON object as the payload.
     * @param context an Android context
     * @param url the URL to contact
     * @param method the HTTP method (e.g. GET or POST)
     * @param body the JSON object to include in the body
     * @param listener callback to run with response data
     * @param errorListener callback to run if an error occurs
     */
    public static void startRequest(final Context context, final String url, final int method, final JsonElement body,
                                    final Response.Listener<JsonObject> listener, final Response.ErrorListener errorListener) {
        if (requestQueue == null) {
            Log.i(TAG, "Creating request queue");
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.e(TAG, "startRequest called before Firebase Authentication login");
            throw new IllegalStateException("No user is logged in");
        }
        Response.Listener<String> serverResponseListener = stringResponse -> {
            if (stringResponse == null || stringResponse.isEmpty()) {
                Log.i(TAG, "Delivering empty response from " + url);
                listener.onResponse(null);
            } else {
                Log.i(TAG, "Delivering parsed response from " + url);
                listener.onResponse(jsonParser.parse(stringResponse).getAsJsonObject());
            }
        };
        Response.ErrorListener serverErrorListener = error -> {
            if (error.networkResponse != null && error.networkResponse.data != null
                    && error.networkResponse.statusCode == HTTP_BAD_REQUEST) {
                String responseData = new String(error.networkResponse.data);
                try {
                    JsonObject errObject = jsonParser.parse(responseData).getAsJsonObject();
                    Log.i(TAG, "Delivering application-level error from " + url);
                    errorListener.onErrorResponse(new VolleyError(errObject.get("error").getAsString()));
                } catch (Exception e) {
                    Log.i(TAG, "Delivering 400 error from " + url);
                    errorListener.onErrorResponse(error);
                }
            } else {
                Log.i(TAG, "Delivering Volley error response from " + url);
                errorListener.onErrorResponse(error);
            }
        };
        user.getIdToken(false).addOnSuccessListener(result ->
                requestQueue.add(new StringRequest(method, url, serverResponseListener, serverErrorListener) {
                    {
                        Log.i(TAG, "startRequest creating Volley request (received Firebase ID token)");
                    }
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if (body == null) {
                            return super.getBody();
                        } else {
                            return body.toString().getBytes();
                        }
                    }
                    @Override
                    public String getBodyContentType() {
                        if (body == null) {
                            return super.getBodyContentType();
                        } else {
                            return "application/json";
                        }
                    }
                    @Override
                    public Map<String, String> getHeaders() {
                        return Collections.singletonMap("Firebase-Token", result.getToken());
                    }
                })
        ).addOnFailureListener(e -> errorListener.onErrorResponse(new VolleyError(e)));
        Log.i(TAG, "startRequest started getIdToken");
    }

    public Response.Listener<String> responseListener(String )
    Response.Listener<String> serverResponseListener = stringResponse -> {
        if (stringResponse == null || stringResponse.isEmpty()) {
            Log.i(TAG, "Delivering empty response from " + url);
            listener.onResponse(null);
        } else {
            Log.i(TAG, "Delivering parsed response from " + url);
            listener.onResponse(jsonParser.parse(stringResponse).getAsJsonObject());
        }
    };



}
