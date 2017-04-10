package pe.area51.proyecto.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.area51.proyecto.R;

/**
 * Created by User on 13/03/2017.
 */

public class Fragment0 extends Fragment {
    public Fragment0() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment0, container, false);
        return rootView;
    }
}
