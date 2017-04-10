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
import pe.area51.proyecto.modelosbd.DatosPreventa;

/**
 * Created by Alexis P on 07/04/2017.
 */

public class ListadoPreventaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DatosPreventa> lista;

    public ListadoPreventaAdapter(Context context, ArrayList<DatosPreventa> lista) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_preventa, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.txtProduct.setText(lista.get(position).getProducto());
        viewHolder.txtPrecioUnit.setText(lista.get(position).getPrecio_uni());
        viewHolder.txtCant.setText(""+lista.get(position).getCant());
        viewHolder.txtPrecioTotal.setText(lista.get(position).getPrecio_tot());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txtProduct)
        TextView txtProduct;
        @BindView(R.id.txtPrecioUnit)
        TextView txtPrecioUnit;
        @BindView(R.id.txtCant)
        TextView txtCant;
        @BindView(R.id.txtPrecioTotal)
        TextView txtPrecioTotal;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
