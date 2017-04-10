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
import pe.area51.proyecto.modelosbd.DatosProducto;

/**
 * Created by Alexis P on 05/04/2017.
 */

public class ListadoProductoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DatosProducto> lista;

    public ListadoProductoAdapter(Context context, ArrayList<DatosProducto> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tienda_producto, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)convertView.getTag();
        viewHolder.tvProducto.setText(lista.get(position).getProducto());
        viewHolder.tvPrecio.setText("S/. "+lista.get(position).getPrecio());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvProducto)
        TextView tvProducto;
        @BindView(R.id.tvPrecio)
        TextView tvPrecio;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
