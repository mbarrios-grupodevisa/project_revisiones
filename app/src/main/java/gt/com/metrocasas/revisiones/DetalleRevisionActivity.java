package gt.com.metrocasas.revisiones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleRevisionActivity extends AppCompatActivity implements View.OnClickListener, OnSimpleDialogListener{

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

    private String proyecto, user, fechaRevision;
    private View v;
    private Button enviar_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detalle_revision);

        v = findViewById(R.id.detalle);
        proyecto = getIntent().getExtras().getString("proyecto");
        user = getIntent().getExtras().getString("id");
        fechaRevision = new SimpleDateFormat("dd/MMMM/yyyy - HH:mm:ss").format(new Date());

        recyclerViewCenacInterno = (RecyclerView) findViewById(R.id.recycler_view_ci);
        recyclerViewCenacExterno = (RecyclerView) findViewById(R.id.recycler_view_ce);
        recyclerViewDespensa = (RecyclerView) findViewById(R.id.recycler_view_despensa);
        recyclerViewLimpieza = (RecyclerView) findViewById(R.id.recycler_view_limpieza);
        recyclerViewConstruccion = (RecyclerView) findViewById(R.id.recycler_view_construccion);
        enviar_datos = (Button) findViewById(R.id.btn_enviar_datos);

        enviar_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Envio de Datos
                new IngresoRevision(getApplicationContext(), getListElements(), v).execute(user, proyecto, fechaRevision);
            }
        });

        aAdapterCI = new ElementoAdapter(listItemCI,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCenacInterno.setLayoutManager(mLayoutManager);
        recyclerViewCenacInterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacInterno.setAdapter(aAdapterCI);

        aAdapterCE = new ElementoAdapter(listItemCE,this);
        RecyclerView.LayoutManager mLayoutManagerCE = new LinearLayoutManager(this);
        recyclerViewCenacExterno.setLayoutManager(mLayoutManagerCE);
        recyclerViewCenacExterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacExterno.setAdapter(aAdapterCE);

        aAdapterDespensa = new ElementoAdapter(listItemDespensa,this);
        RecyclerView.LayoutManager mLayoutManagerDespensa = new LinearLayoutManager(this);
        recyclerViewDespensa.setLayoutManager(mLayoutManagerDespensa);
        recyclerViewDespensa.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDespensa.setAdapter(aAdapterDespensa);

        aAdapterLimpieza = new ElementoAdapter(listItemLimpieza,this);
        RecyclerView.LayoutManager mLayoutManagerLimpieza = new LinearLayoutManager(this);
        recyclerViewLimpieza.setLayoutManager(mLayoutManagerLimpieza);
        recyclerViewLimpieza.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLimpieza.setAdapter(aAdapterLimpieza);

        aAdapterConstruccion = new ElementoAdapter(listItemCostrucion,this);
        RecyclerView.LayoutManager mLayoutManagerConstruccion = new LinearLayoutManager(this);
        recyclerViewConstruccion.setLayoutManager(mLayoutManagerConstruccion);
        recyclerViewConstruccion.setItemAnimator(new DefaultItemAnimator());
        recyclerViewConstruccion.setAdapter(aAdapterConstruccion);

        new GetElementos(this,aAdapterCI,aAdapterCE,aAdapterDespensa,aAdapterLimpieza,aAdapterConstruccion).execute(proyecto);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPossitiveButtonClick(Elemento apto) {
    }

    @Override
    public void onNegativeButtonClick() {
    }

    public List<Elemento> getListElements()
    {
        List<Elemento> list = new ArrayList<>();
        list.addAll(listItemCI);
        list.addAll(listItemCE);
        list.addAll(listItemDespensa);
        list.addAll(listItemLimpieza);
        list.addAll(listItemCostrucion);
        return list;
    }
}
