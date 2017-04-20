package pe.area51.proyecto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.R;
import pe.area51.proyecto.modelosbd.DatosVenta;

/**
 * Created by Alexis P on 10/04/2017.
 */

public class ListadoVentaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DatosVenta> list;

    public ListadoVentaAdapter(Context context, ArrayList<DatosVenta> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tus_compras, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.txtTienda.setText(list.get(position).getId_tienda());
        viewHolder.txtTotal.setText("S/. "+list.get(position).getTotal_pago());
        viewHolder.idPre.setText(list.get(position).getId_preventa());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txtTienda)
        TextView txtTienda;
        @BindView(R.id.id_pre)
        TextView idPre;
        @BindView(R.id.txtTotal)
        TextView txtTotal;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
