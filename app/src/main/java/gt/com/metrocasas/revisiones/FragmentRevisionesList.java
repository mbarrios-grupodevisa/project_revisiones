package gt.com.metrocasas.revisiones;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 05/07/2016.
 */
public class FragmentRevisionesList extends Fragment {

    private static final int ACTIVITY_REQUEST = 1;
    private static final int RESULT_OK = 1;
    private List<ItemRevision> listRevision = new ArrayList<>();
    private RecyclerView recyclerView;
    private RevisionAdapter rAdapter;
    private String proyecto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View partenView = inflater.inflate(R.layout.lista_revisiones, container, false);

        recyclerView = (RecyclerView) partenView.findViewById(R.id.recycler_view);
        FloatingActionButton fab = (FloatingActionButton) partenView.findViewById(R.id.btn_nuevo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(partenView.getContext(), DetalleRevisionActivity.class);
                intent.putExtra("proyecto",proyecto);
                startActivityForResult(intent,ACTIVITY_REQUEST);
                //startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
            }
        });

        rAdapter = new RevisionAdapter(listRevision);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        proyecto = getArguments().getString("proyecto");
        new GetRevisiones(getActivity(),rAdapter,listRevision).execute(proyecto);
        //new RetornarRevisiones(getActivity(), rAdapter, listRevision).execute(proyecto);
        return partenView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACTIVITY_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getActivity(),"Enrique" ,Toast.LENGTH_LONG).show();
            }
        }
    }
}
