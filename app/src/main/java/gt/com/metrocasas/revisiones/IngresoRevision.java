package gt.com.metrocasas.revisiones;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class IngresoRevision extends AsyncTask<String, Integer, String> {

    Context context;
    ProgressBar progreso;
    LinearLayout p;
    ScrollView q;
    public static String ERROR = "Error en los datos, revise";

    public IngresoRevision(Context context, ProgressBar progreso, LinearLayout p, ScrollView q) {
        this.context = context;
        this.progreso = progreso;
        this.p = p;
        this.q = q;
    }

    @Override
    protected String doInBackground(String... params) {
        try
        {
            String proyecto = params[0];
            String fechaRevision = params[1];
            String elemento = params[2];
            String estado = params[3];
            String foto = params[4];
            String comentario = params[5];

            String link = "http://atreveteacrecer.metrocasas.com.gt/insertRevision.php";
            String data = URLEncoder.encode("proyecto", "UTF-8") + "=" + URLEncoder.encode(proyecto, "UTF-8")
                    + "&" + URLEncoder.encode("fechaRevision", "UTF-8") + "=" + URLEncoder.encode(fechaRevision, "UTF-8")
                    + "&" + URLEncoder.encode("elemento", "UTF-8") + "=" + URLEncoder.encode(elemento, "UTF-8")
                    + "&" + URLEncoder.encode("estado", "UTF-8") + "=" + URLEncoder.encode(estado, "UTF-8")
                    + "&" + URLEncoder.encode("foto", "UTF-8") + "=" + URLEncoder.encode(foto, "UTF-8")
                    + "&" + URLEncoder.encode("comentario", "UTF-8") + "=" + URLEncoder.encode(comentario, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e)  {
            return ERROR;
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        this.progreso.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        Snackbar.make(q, "¡Datos Cargados Exitosamente!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Aceptar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        //Toast.makeText(this.context, "¡Datos Cargados Exitosamente!", Toast.LENGTH_LONG).show();
        p.setVisibility(View.GONE);
        q.setVisibility(View.VISIBLE);
    }

}
