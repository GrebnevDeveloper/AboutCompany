/**
 * Created by Grebnev on 13.04.2017.
 */
package com.developer.grebnev.aboutcompany.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("company")
    @Expose
    private CompanyDetails companyDetails;

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

}
