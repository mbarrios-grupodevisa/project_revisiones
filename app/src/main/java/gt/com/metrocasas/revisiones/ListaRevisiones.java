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
public class ListaRevisiones extends Fragment {

    private List<ItemRevision> listRevision = new ArrayList<>();
    private RecyclerView recyclerView;
    private RevisionAdapter rAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View partenView = inflater.inflate(R.layout.lista_revisiones, container, false);

        recyclerView = (RecyclerView) partenView.findViewById(R.id.recycler_view);

        rAdapter = new RevisionAdapter(listRevision);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        prepareMovieData();

        return partenView;
    }

    private void prepareMovieData() {

        ItemRevision item = new ItemRevision("REVISIÓN 01/01/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/01/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/02/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/03/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/04/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/05/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/06/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/08/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/09/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/10/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/11/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/12/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/01/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/02/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/03/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/04/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/05/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/06/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/08/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/09/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/10/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/11/16");
        listRevision.add(item);

        item = new ItemRevision("REVISIÓN 01/12/16");
        listRevision.add(item);

        rAdapter.notifyDataSetChanged();
    }
}
