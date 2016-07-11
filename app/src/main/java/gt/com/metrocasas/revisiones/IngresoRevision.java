package gt.com.metrocasas.revisiones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class IngresoRevision extends AsyncTask<String, Integer, String> {

    View v;
    Context context;
    String revisionid;
    List<Elemento> listElemento;

    public IngresoRevision(Context context, List<Elemento> listElemento, View v) {
        this.context = context;
        this.listElemento = listElemento;
        this.v = v;
    }

    @Override
    protected String doInBackground(String... params) {
        try
        {
            String user = params[0];
            String proyecto = params[1];
            String fechaRevision = params[2];

            String link = "http://atreveteacrecer.metrocasas.com.gt/insertRevision.php";
            String data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8")
                    + "&" + URLEncoder.encode("proyecto", "UTF-8") + "=" + URLEncoder.encode(proyecto, "UTF-8")
                    + "&" + URLEncoder.encode("fechaRevision", "UTF-8") + "=" + URLEncoder.encode(fechaRevision, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String line;
            if((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("revisiones");
            revisionid  = jsonArray.getJSONObject(0).getString("id");
            for (Elemento i:listElemento) {
                link = "http://atreveteacrecer.metrocasas.com.gt/insertDetalleRevision.php";
                data = URLEncoder.encode("revision_id", "UTF-8") + "=" + URLEncoder.encode(revisionid, "UTF-8")
                        + "&" + URLEncoder.encode("elemento_id", "UTF-8") + "=" + URLEncoder.encode(i.getId(), "UTF-8")
                        + "&" + URLEncoder.encode("estado", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(i.isEstado()), "UTF-8")
                        + "&" + URLEncoder.encode("imagen", "UTF-8") + "=" + URLEncoder.encode(i.getImagen(), "UTF-8")
                        + "&" + URLEncoder.encode("comentario", "UTF-8") + "=" + URLEncoder.encode(i.getComentario(), "UTF-8") ;

                url = new URL(link);
                conn = url.openConnection();
                conn.setDoOutput(true);

                wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                sb = new StringBuilder();
                if((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }
            }
            listElemento.clear();
            return sb.toString();
        }
        catch(Exception e)  {
            return e.toString();
        }
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("success")) {
            Snackbar.make(v, "¡Datos ingresados exitosamente!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Aceptar", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
        } else {
            Snackbar.make(v, "Ocurrió un error al cargar los datos", Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(Color.RED)
                    .setAction("Aceptar", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
        }
    }

}
