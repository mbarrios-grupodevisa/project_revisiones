package gt.com.metrocasas.revisiones;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 05/07/2016.
 */
public class FragmentRevisionesList extends Fragment {

    private List<ItemRevision> listRevision = new ArrayList<>();
    private RecyclerView recyclerView;
    private RevisionAdapter rAdapter;
    private String proyecto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View partenView = inflater.inflate(R.layout.lista_revisiones, container, false);

        recyclerView = (RecyclerView) partenView.findViewById(R.id.recycler_view);

        rAdapter = new RevisionAdapter(listRevision);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        proyecto = getArguments().getString("proyecto");
        new RetornarRevisiones(getActivity(), rAdapter, listRevision).execute(proyecto);
        return partenView;
    }
}
