package com.developer.grebnev.aboutcompany;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.developer.grebnev.aboutcompany.adapter.EmployeeAdapter;
import com.developer.grebnev.aboutcompany.model.Company;
import com.developer.grebnev.aboutcompany.model.CompanyDetails;
import com.developer.grebnev.aboutcompany.network.RequestInterface;
import com.developer.grebnev.aboutcompany.network.RetrofitManager;
import com.developer.grebnev.aboutcompany.utils.InternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private View coordinator;
    private TextView tvNameCompany;
    private TextView tvAgeCompany;
    private TextView tvCompetenceCompany;
    private ListView lvEmployeesCompany;

    private CompanyDetails companyDetails;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
    }

    private void setUi() {
        // Initialize views
        coordinator = findViewById(R.id.coordinator);
        tvNameCompany = (TextView) findViewById(R.id.tv_name_company);
        tvAgeCompany = (TextView) findViewById(R.id.tv_age_company);
        tvCompetenceCompany = (TextView) findViewById(R.id.tv_competences_company);
        lvEmployeesCompany = (ListView) findViewById(R.id.lv_employees_company);

        final ProgressDialog dialog;
        // Check internet connection
        if (InternetConnection.isOnline(getApplicationContext())) {
            // Dialog for waiting
            dialog = new ProgressDialog(this);
            dialog.setTitle(getString(R.string.dialog_title));
            dialog.setMessage(getString(R.string.dialog_message));
            dialog.show();

            RequestInterface request = RetrofitManager.getRequestInterface();

            Call<Company> call = request.getJson();

            call.enqueue(new Callback<Company>() {
                @Override
                public void onResponse(Call<Company> call, Response<Company> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        companyDetails = response.body().getCompanyDetails();

                        tvNameCompany.setText(companyDetails.getName());
                        tvAgeCompany.setText(companyDetails.getAge() + getString(R.string.years_extension));
                        String tmpCompetence = new String();
                        for (String competence : companyDetails.getCompetences()) {
                            tmpCompetence = tmpCompetence + competence + " ";
                        }
                        tvCompetenceCompany.setText(tmpCompetence);

                        employeeAdapter = new EmployeeAdapter(MainActivity.this, companyDetails.getEmployees());
                        lvEmployeesCompany.setAdapter(employeeAdapter);
                    } else {
                        Snackbar.make(coordinator, R.string.no_file, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Company> call, Throwable t) {
                    dialog.dismiss();
                    Snackbar.make(coordinator, R.string.no_file, Snackbar.LENGTH_LONG).show();
                }
            });
        } else {
            Snackbar.make(coordinator, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
        }
    }
}
