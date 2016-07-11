package gt.com.metrocasas.revisiones;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FragmentRevisionesList extends Fragment {

    private static final int ACTIVITY_REQUEST = 1;
    private SwipeRefreshLayout swipeContainer;
    private List<ItemRevision> listRevision = new ArrayList<>();
    private RevisionAdapter rAdapter;
    private String proyecto, userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View partenView = inflater.inflate(R.layout.lista_revisiones, container, false);
        userid = getArguments().getString("id");
        swipeContainer = (SwipeRefreshLayout)partenView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listRevision.clear();
                rAdapter.notifyDataSetChanged();
                new GetRevisiones(getActivity(),rAdapter,listRevision,swipeContainer).execute(proyecto);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        RecyclerView recyclerView = (RecyclerView) partenView.findViewById(R.id.recycler_view);
        FloatingActionButton fab = (FloatingActionButton) partenView.findViewById(R.id.btn_nuevo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(partenView.getContext(), DetalleRevisionActivity.class);
                intent.putExtra("proyecto",proyecto);
                intent.putExtra("id", userid);
                startActivityForResult(intent,ACTIVITY_REQUEST);
            }
        });

        rAdapter = new RevisionAdapter(listRevision);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        proyecto = getArguments().getString("proyecto");
        new GetRevisiones(getActivity(),rAdapter,listRevision,swipeContainer).execute(proyecto);
        return partenView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
