/**
 * Created by Grebnev on 13.04.2017.
 */
package com.developer.grebnev.aboutcompany.network;

import com.developer.grebnev.aboutcompany.model.Company;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface containing GET request
 */
public interface RequestInterface {

    /**
     *
     * @return On url we get Company in the form Json
     */
    @GET("/v2/56fa31e0110000f920a72134")
    Call<Company> getJson();
}
