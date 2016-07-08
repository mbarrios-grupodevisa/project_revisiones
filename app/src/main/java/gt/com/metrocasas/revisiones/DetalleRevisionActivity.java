package gt.com.metrocasas.revisiones;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 07/07/2016.
 */
public class DetalleRevisionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Elemento> listItemCI = new ArrayList<>();
    private RecyclerView recyclerViewCenacInterno;
    private ElementoAdapter aAdapter;
    private String proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detalle_revision);

        recyclerViewCenacInterno = (RecyclerView) findViewById(R.id.recycler_view_ci);

        aAdapter = new ElementoAdapter(listItemCI);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCenacInterno.setLayoutManager(mLayoutManager);
        recyclerViewCenacInterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacInterno.setAdapter(aAdapter);
        testValues();
    }

    @Override
    public void onClick(View view) {

    }

    private void testValues(){
        Elemento item =  new Elemento("VIVENTI","CENAC INTERNO","Folleto Actualizado");
        listItemCI.add(item);

        item =  new Elemento("VIVENTI","CENAC INTERNO","Tarjetas de Presentación");
        listItemCI.add(item);

        item =  new Elemento("VIVENTI","CENAC INTERNO","Teléfono");
        listItemCI.add(item);

        item =  new Elemento("VIVENTI","CENAC INTERNO","Hojas bond");
        listItemCI.add(item);
        aAdapter.notifyDataSetChanged();
    }
}
