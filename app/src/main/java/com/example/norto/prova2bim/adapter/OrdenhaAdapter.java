package com.example.norto.prova2bim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.norto.prova2bim.models.Ordenha;
import com.example.norto.prova2bim.R;

import java.util.List;

public class OrdenhaAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<Ordenha> ordenhaList;

    public OrdenhaAdapter(Context context, List<Ordenha> ordenhaList) {
        this.ordenhaList = ordenhaList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return ordenhaList.size();
    }

    @Override
    public Object getItem(int position) {
        return ordenhaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            Ordenha ordenha = ordenhaList.get(position);
            convertView =  inflater.inflate(R.layout.item_ordenha, null);
            ((TextView) convertView.findViewById(R.id.tvIdMatriz)).setText(String.valueOf(ordenha.getIdentifcador()));
            ((TextView) convertView.findViewById(R.id.tvDescMatriz)).setText(ordenha.getMatriz().getDescricao());
            ((TextView) convertView.findViewById(R.id.tvQntNumeroMatriz)).setText(String.valueOf(ordenha.getQntLitros()));
            ((TextView) convertView.findViewById(R.id.tvDtHoraMatriz)).setText(String.valueOf(ordenha.getDtHora()));
        } catch (Exception E) {
            E.getMessage();
        }

        return convertView;

    }
}
