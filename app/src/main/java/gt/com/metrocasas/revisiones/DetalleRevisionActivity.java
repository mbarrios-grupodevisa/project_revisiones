package gt.com.metrocasas.revisiones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleRevisionActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Elemento> listItemCE = new ArrayList<>();
    private RecyclerView recyclerViewCenacExterno;

    private List<Elemento> listItemCI = new ArrayList<>();
    private RecyclerView recyclerViewCenacInterno;

    private List<Elemento> listItemDespensa = new ArrayList<>();
    private RecyclerView recyclerViewDespensa;

    private List<Elemento> listItemLimpieza = new ArrayList<>();
    private RecyclerView recyclerViewLimpieza;

    private List<Elemento> listItemCostrucion = new ArrayList<>();
    private RecyclerView recyclerViewConstruccion;

    private String proyecto, user, fechaRevision, json;
    private View v;

    private static final int VIVENTI = 3;
    private static final int CASA_ASUNCION = 4;

    public final static String MY_ACCESS_KEY_ID = "";
    public final static String MY_SECRET_KEY = "";
    public final static String BUCKET_NAME = "projectsgtimages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detalle_revision);

        v = findViewById(R.id.detalle);
        proyecto = getIntent().getExtras().getString("proyecto");
        user = getIntent().getExtras().getString("id");
        fechaRevision = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss").format(new Date());
        this.setTitle(proyecto);

        recyclerViewCenacInterno = (RecyclerView) findViewById(R.id.recycler_view_ci);
        recyclerViewCenacExterno = (RecyclerView) findViewById(R.id.recycler_view_ce);
        recyclerViewDespensa = (RecyclerView) findViewById(R.id.recycler_view_despensa);
        recyclerViewLimpieza = (RecyclerView) findViewById(R.id.recycler_view_limpieza);
        recyclerViewConstruccion = (RecyclerView) findViewById(R.id.recycler_view_construccion);
        Button enviar_datos = (Button) findViewById(R.id.btn_enviar_datos);

        enviar_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadPictures();
            }
        });

        //<editor-fold desc="Ocultar CardViews">
        final TextView titulo_ci = (TextView) findViewById(R.id.text_view_title_cenac_interno);
        titulo_ci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewCenacInterno.getVisibility() == View.VISIBLE) {
                    recyclerViewCenacInterno.setVisibility(View.GONE);
                    titulo_ci.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_down,0);
                }else {
                    titulo_ci.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
                    recyclerViewCenacInterno.setVisibility(View.VISIBLE);
                }
            }
        });

        final TextView titulo_ce = (TextView) findViewById(R.id.text_view_title_cenac_externo);
        titulo_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewCenacExterno.getVisibility() == View.VISIBLE) {
                    recyclerViewCenacExterno.setVisibility(View.GONE);
                    titulo_ce.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_down,0);
                }else {
                    titulo_ce.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
                    recyclerViewCenacExterno.setVisibility(View.VISIBLE);

                }
            }
        });

        final TextView titulo_c = (TextView) findViewById(R.id.text_view_title_construccion);
        titulo_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewConstruccion.getVisibility() == View.VISIBLE) {
                    recyclerViewConstruccion.setVisibility(View.GONE);
                    titulo_c.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_down,0);
                }else {
                    titulo_c.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
                    recyclerViewConstruccion.setVisibility(View.VISIBLE);
                }
            }
        });

        final TextView titulo_d = (TextView) findViewById(R.id.text_view_title_despensa);
        titulo_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewDespensa.getVisibility() == View.VISIBLE) {
                    recyclerViewDespensa.setVisibility(View.GONE);
                    titulo_d.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_down,0);
                }else {
                    titulo_d.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
                    recyclerViewDespensa.setVisibility(View.VISIBLE);
                }
            }
        });

        final TextView titulo_l = (TextView) findViewById(R.id.text_view_title_limpieza);
        titulo_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewLimpieza.getVisibility() == View.VISIBLE) {
                    recyclerViewLimpieza.setVisibility(View.GONE);
                    titulo_l.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_down,0);
                }else {
                    titulo_l.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_up,0);
                    recyclerViewLimpieza.setVisibility(View.VISIBLE);
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="Adaptadores">
        ElementoAdapter aAdapterCI = new ElementoAdapter(listItemCI, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewCenacInterno.setLayoutManager(mLayoutManager);
        recyclerViewCenacInterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacInterno.setAdapter(aAdapterCI);

        ElementoAdapter aAdapterCE = new ElementoAdapter(listItemCE, this);
        RecyclerView.LayoutManager mLayoutManagerCE = new LinearLayoutManager(this);
        recyclerViewCenacExterno.setLayoutManager(mLayoutManagerCE);
        recyclerViewCenacExterno.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCenacExterno.setAdapter(aAdapterCE);

        ElementoAdapter aAdapterDespensa = new ElementoAdapter(listItemDespensa, this);
        RecyclerView.LayoutManager mLayoutManagerDespensa = new LinearLayoutManager(this);
        recyclerViewDespensa.setLayoutManager(mLayoutManagerDespensa);
        recyclerViewDespensa.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDespensa.setAdapter(aAdapterDespensa);

        ElementoAdapter aAdapterLimpieza = new ElementoAdapter(listItemLimpieza, this);
        RecyclerView.LayoutManager mLayoutManagerLimpieza = new LinearLayoutManager(this);
        recyclerViewLimpieza.setLayoutManager(mLayoutManagerLimpieza);
        recyclerViewLimpieza.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLimpieza.setAdapter(aAdapterLimpieza);

        ElementoAdapter aAdapterConstruccion = new ElementoAdapter(listItemCostrucion, this);
        RecyclerView.LayoutManager mLayoutManagerConstruccion = new LinearLayoutManager(this);
        recyclerViewConstruccion.setLayoutManager(mLayoutManagerConstruccion);
        recyclerViewConstruccion.setItemAnimator(new DefaultItemAnimator());
        recyclerViewConstruccion.setAdapter(aAdapterConstruccion);
        //</editor-fold>

        new GetElementos(this, aAdapterCI, aAdapterCE, aAdapterDespensa, aAdapterLimpieza, aAdapterConstruccion).execute(proyecto);
    }

    @Override
    public void onClick(View view) {

    }

    private boolean bandera = true;
    public void upLoadPictures() {
        bandera = true;
        List<Elemento> elements = getListElements();
        ArrayList<File> files = new ArrayList<>();
        for (Elemento e: elements) {
            if(!e.getImagePath().equals(""))
            files.add(new File(e.getImagePath()));
        }
        if (!files.isEmpty()) {
            for (File i : files) {
                AmazonS3 s3Client = new AmazonS3Client(new BasicAWSCredentials(MY_ACCESS_KEY_ID, MY_SECRET_KEY));
                TransferUtility transferUtility = new TransferUtility(s3Client, this);
                TransferObserver transferObserver = transferUtility.upload(BUCKET_NAME, "revisionesmerca/" + i.getName(), i);
                transferObserver.setTransferListener(new TransferListener() {
                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if (state == TransferState.COMPLETED) {
                            if (bandera) {
                                ingresarInformacion();
                                limpiarCampos();
                                bandera = false;
                            }
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                    }

                    @Override
                    public void onError(int id, Exception ex) {
                        Toast.makeText(DetalleRevisionActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            ingresarInformacion();
            limpiarCampos();
        }
    }

    public void ingresarInformacion() {
        json = getJSON(getListElements());
        new IngresoRevision(getApplicationContext(), v).execute(user, proyecto, fechaRevision, json);
        Intent databack = new Intent();
        databack.putExtra("fecha",fechaRevision);
        if(proyecto.equals("Viventi")) setResult(VIVENTI,databack);
        if(proyecto.equals("Casa Asuncion")) setResult(CASA_ASUNCION,databack);
    }

    public void limpiarCampos() {
        listItemCE = new ArrayList<>();
        listItemCI = new ArrayList<>();
        listItemDespensa = new ArrayList<>();
        listItemLimpieza = new ArrayList<>();
        listItemCostrucion = new ArrayList<>();
    }

    public List<Elemento> getListElements() {
        List<Elemento> list = new ArrayList<>();
        list.addAll(listItemCI);
        list.addAll(listItemCE);
        list.addAll(listItemDespensa);
        list.addAll(listItemLimpieza);
        list.addAll(listItemCostrucion);
        return list;
    }

    public String getJSON(List<Elemento> lista) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Elemento i:lista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("elemento_id", i.getId());
                jsonObject.put("estado", i.isEstado());
                jsonObject.put("imagen", i.getImagen());
                jsonObject.put("comentario", i.getComentario());
                jsonArray.put(jsonObject);
            }
            JSONObject studentsObj = new JSONObject();
            studentsObj.put("detalles", jsonArray);
            return studentsObj.toString();
        } catch (Exception e) {
            return "null";
        }
    }
}
