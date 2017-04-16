/**
 * Created by Grebnev on 13.04.2017.
 */
package com.developer.grebnev.aboutcompany.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create Retrofit Builder,
 * contact with root url,
 * add converter Gson
 */
public class RetrofitManager {
    /**
     * Root URL for connect
     */
    private static final String URL = "http://www.mocky.io/";

    /**
     *
     * @return Get retrofit instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *
     * @return Get interface fot GET request
     */
    public static RequestInterface getRequestInterface() {
        return getRetrofitInstance().create(RequestInterface.class);
    }

}
