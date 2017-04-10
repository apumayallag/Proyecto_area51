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
import pe.area51.proyecto.modelosbd.DatosLogin;

/**
 * Created by User on 15/03/2017.
 */

public class DatoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DatosLogin> lista;

    public DatoAdapter(Context context, ArrayList<DatosLogin> lista) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)convertView.getTag();
        viewHolder.tvNombre.setText(lista.get(position).getNomb());
        viewHolder.tvApellido.setText(lista.get(position).getApel());
        viewHolder.tvCorreo.setText(lista.get(position).getCorreo());
        viewHolder.tvTelefono.setText(lista.get(position).getTelf());
        viewHolder.tvUsuario.setText(lista.get(position).getUser());
        viewHolder.tvPass.setText(lista.get(position).getPass());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvApellido)
        TextView tvApellido;
        @BindView(R.id.tvCorreo)
        TextView tvCorreo;
        @BindView(R.id.tvTelefono)
        TextView tvTelefono;
        @BindView(R.id.tvUsuario)
        TextView tvUsuario;
        @BindView(R.id.tvPass)
        TextView tvPass;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
