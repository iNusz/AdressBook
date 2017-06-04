package com.seunghoshin.android.contact_3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 6. 3..
 */

public class C_Adapter extends RecyclerView.Adapter<D_Holder>{

    List<A_Data> datas;

    public C_Adapter(List<A_Data> datas){
        this.datas = datas;
    }

    @Override
    public D_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new D_Holder(view);
    }

    @Override
    public void onBindViewHolder(D_Holder holder, int position) {
        A_Data data = datas.get(position);
        holder.setName(data.getName());
        holder.setTel(data.getTel());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
