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
import pe.area51.proyecto.modelosbd.DatosTienda;

/**
 * Created by User on 23/03/2017.
 */

public class ListadoTiendaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DatosTienda> listaTienda;

    public ListadoTiendaAdapter(Context context, ArrayList<DatosTienda> listaTienda) {
        this.context = context;
        this.listaTienda = listaTienda;
    }

    @Override
    public int getCount() {
        return listaTienda.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTienda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaTienda.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listado_tienda, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.idTienda.setText(""+listaTienda.get(position).getId_tiend());
        viewHolder.nomTienda.setText(listaTienda.get(position).getNomTienda());
        viewHolder.DirecTienda.setText(listaTienda.get(position).getDirecTienda());
        viewHolder.TelefTienda.setText(listaTienda.get(position).getTelefTienda());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.nomTienda)
        TextView nomTienda;
        @BindView(R.id.DirecTienda)
        TextView DirecTienda;
        @BindView(R.id.TelefTienda)
        TextView TelefTienda;
        @BindView(R.id.idTienda)
        TextView idTienda;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
