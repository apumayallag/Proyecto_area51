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
import pe.area51.proyecto.modelosbd.DatosComprobante;

/**
 * Created by User on 22/03/2017.
 */

public class ComprobanteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DatosComprobante> listaCompro;

    public ComprobanteAdapter(Context context, ArrayList<DatosComprobante> listaCompro) {
        this.context = context;
        this.listaCompro = listaCompro;
    }

    @Override
    public int getCount() {
        return listaCompro.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCompro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCompro.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lista_compro, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tvIdUser.setText(listaCompro.get(position).getId_user());
        viewHolder.tvTipo.setText(listaCompro.get(position).getTipo());
        viewHolder.tvRazon.setText(listaCompro.get(position).getNomRaz());
        viewHolder.tvDirec.setText(listaCompro.get(position).getDirec());
        viewHolder.tvDoc.setText(listaCompro.get(position).getDniRuc());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvIdUser)
        TextView tvIdUser;
        @BindView(R.id.tvTipo)
        TextView tvTipo;
        @BindView(R.id.tvRazon)
        TextView tvRazon;
        @BindView(R.id.tvDirec)
        TextView tvDirec;
        @BindView(R.id.tvDoc)
        TextView tvDoc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
