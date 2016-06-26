package com.school.amit.schoolapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by amit on 25/3/15.
 */
public class CustomAdapter extends ArrayAdapter<RowItem> {

    Context context;

    public CustomAdapter(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView TextView;
        Button txtPhone1;
        Button txtPhone2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtPhone2 = (Button) convertView.findViewById(R.id.phone2);
            holder.txtPhone1 = (Button) convertView.findViewById(R.id.phone1);
            holder.TextView = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtPhone2.setText(rowItem.getphone2());
        holder.txtPhone1.setText(rowItem.getphone1());
        holder.TextView.setText(rowItem.getname());

        final ViewHolder finalHolder = holder;
        holder.txtPhone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + finalHolder.txtPhone1.getText().toString()));
                getContext().startActivity(intent);
            }
        });

        holder.txtPhone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + finalHolder.txtPhone2.getText().toString()));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }



}
