package com.seunghoshin.android.contact_3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SeungHoShin on 2017. 6. 3..
 */

public class D_Holder extends RecyclerView.ViewHolder{
    private TextView txtTel, txtName;

    public D_Holder(View itemView) {
        super(itemView);
        txtTel = (TextView) itemView.findViewById(R.id.txtTel);
        txtName = (TextView) itemView.findViewById(R.id.txtName);

    }

    public void setTel(String value){
        txtTel.setText(value);
    }
    public String getTel(){
        return txtTel.getText().toString();
    }

    public void setName(String value){
        txtName.setText(value);
    }
    public String getName(){
        return txtName.getText().toString();
    }
}
