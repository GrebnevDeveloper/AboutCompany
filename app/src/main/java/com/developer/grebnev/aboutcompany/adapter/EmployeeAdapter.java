/**
 * Created by Grebnev on 13.04.2017.
 */
package com.developer.grebnev.aboutcompany.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.grebnev.aboutcompany.utils.EmployeeComparator;
import com.developer.grebnev.aboutcompany.R;
import com.developer.grebnev.aboutcompany.model.Employee;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for ListView containing list objects Employee
 */
public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private LayoutInflater inflater;
    List<Employee> employees;
    Context context;

    /**
     * Constructor for adapter, here is the sorting process
     * @param context
     * @param objects list Employee
     */
    public EmployeeAdapter(@NonNull Context context, @NonNull List<Employee> objects) {
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        Collections.sort(objects, new EmployeeComparator());
        this.employees = objects;
    }

    /**
     *
     * @param position in ListView
     * @return return object Employee
     */
    @Nullable
    @Override
    public Employee getItem(int position) {
        return employees.get(position);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return Get filled item ListView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = inflater.inflate(R.layout.model_employee, parent, false);
        viewHolder = ViewHolder.create((LinearLayout) view);

        Employee item = getItem(position);

        viewHolder.tvName.setText(item.getName());
        viewHolder.tvPhone.setText(context.getString(R.string.phone_extension) + item.getPhoneNumber());
        String tmpSkills = new String();
        for (String skill : item.getSkills()) {
            tmpSkills = tmpSkills + skill + " ";
        }
        viewHolder.tvSkills.setText(context.getString(R.string.skills_extension) + tmpSkills);

        return viewHolder.linearLayout;
    }

    /**
     * Template for creating a custom ListView item
     */
    private static class ViewHolder {
        LinearLayout linearLayout;
        TextView tvName;
        TextView tvPhone;
        TextView tvSkills;

        public ViewHolder(LinearLayout linearLayout, TextView tvName, TextView tvPhone, TextView tvSkills) {
            this.linearLayout = linearLayout;
            this.tvName = tvName;
            this.tvPhone = tvPhone;
            this.tvSkills = tvSkills;
        }

        public static ViewHolder create(LinearLayout linearLayout) {
            TextView tvName = (TextView) linearLayout.findViewById(R.id.tv_name_employee);
            TextView tvPhone = (TextView) linearLayout.findViewById(R.id.tv_phone_employee);
            TextView tvSkills = (TextView) linearLayout.findViewById(R.id.tv_skills_employee);
            return new ViewHolder(linearLayout, tvName, tvPhone, tvSkills);
        }
    }
}
