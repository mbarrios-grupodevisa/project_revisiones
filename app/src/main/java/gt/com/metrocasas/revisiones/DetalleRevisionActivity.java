package gt.com.metrocasas.revisiones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 07/07/2016.
 */
public class DetalleRevisionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Elemento> listItemCE = new ArrayList<>();
    private RecyclerView recyclerViewCenacExterno;
    private ElementoAdapter aAdapterCE;

    private List<Elemento> listItemCI = new ArrayList<>();
    private RecyclerView recyclerViewCenacInterno;
    private ElementoAdapter aAdapterCI;

    private List<Elemento> listItemDespensa = new ArrayList<>();
    private RecyclerView recyclerViewDespensa;
    private ElementoAdapter aAdapterDespensa;

    private List<Elemento> listItemLimpieza = new ArrayList<>();
    private RecyclerView recyclerViewLimpieza;
    private ElementoAdapter aAdapterLimpieza;

    private List<Elemento> listItemCostrucion = new ArrayList<>();
    private RecyclerView recyclerViewConstruccion;
    private ElementoAdapter aAdapterConstruccion;

    private String proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detalle_revision);

        recyclerViewCenacInterno = (RecyclerView) findViewById(R.id.recycler_view_ci);
        recyclerViewCenacExterno = (RecyclerView) findViewById(R.id.recycler_view_ce);
        recyclerViewDespensa = (RecyclerView) findViewById(R.id.recycler_view_despensa);
        recyclerViewLimpieza = (RecyclerView) findViewById(R.id.recycler_view_limpieza);
        recyclerViewConstruccion = (RecyclerView) findViewById(R.id.recycler_view_construccion);

        aAdapterCI = new ElementoAdapter(listItemCI);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCenacInterno.setLayoutManager(mLayoutManager);
        recyclerViewCenacInterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacInterno.setAdapter(aAdapterCI);

        aAdapterCE = new ElementoAdapter(listItemCE);
        RecyclerView.LayoutManager mLayoutManagerCE = new LinearLayoutManager(this);
        recyclerViewCenacExterno.setLayoutManager(mLayoutManagerCE);
        recyclerViewCenacExterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacExterno.setAdapter(aAdapterCE);

        aAdapterDespensa = new ElementoAdapter(listItemDespensa);
        RecyclerView.LayoutManager mLayoutManagerDespensa = new LinearLayoutManager(this);
        recyclerViewDespensa.setLayoutManager(mLayoutManagerDespensa);
        recyclerViewDespensa.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDespensa.setAdapter(aAdapterDespensa);

        aAdapterLimpieza = new ElementoAdapter(listItemLimpieza);
        RecyclerView.LayoutManager mLayoutManagerLimpieza = new LinearLayoutManager(this);
        recyclerViewLimpieza.setLayoutManager(mLayoutManagerLimpieza);
        recyclerViewLimpieza.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLimpieza.setAdapter(aAdapterLimpieza);

        aAdapterConstruccion = new ElementoAdapter(listItemCostrucion);
        RecyclerView.LayoutManager mLayoutManagerConstruccion = new LinearLayoutManager(this);
        recyclerViewConstruccion.setLayoutManager(mLayoutManagerConstruccion);
        recyclerViewConstruccion.setItemAnimator(new DefaultItemAnimator());
        recyclerViewConstruccion.setAdapter(aAdapterConstruccion);

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

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Amenidades");
        listItemCE.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Información");
        listItemCE.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Estilo de VIda");
        listItemCE.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Amenidades");
        listItemLimpieza.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Información");
        listItemLimpieza.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Estilo de VIda");
        listItemLimpieza.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Amenidades");
        listItemDespensa.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Información");
        listItemDespensa.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Estilo de VIda");
        listItemDespensa.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Amenidades");
        listItemCostrucion.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Información");
        listItemCostrucion.add(item);

        item =  new Elemento("VIVENTI","CENAC EXTERNO","Valla Estilo de VIda");
        listItemCostrucion.add(item);

        aAdapterCI.notifyDataSetChanged();
        aAdapterCE.notifyDataSetChanged();
        aAdapterLimpieza.notifyDataSetChanged();
        aAdapterDespensa.notifyDataSetChanged();
        aAdapterConstruccion.notifyDataSetChanged();
    }
}
