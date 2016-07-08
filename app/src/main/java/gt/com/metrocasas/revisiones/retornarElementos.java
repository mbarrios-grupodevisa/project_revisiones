package gt.com.metrocasas.revisiones;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class RetornarElementos extends AsyncTask<String, Integer, String> {

    Context context;
    List<Elemento> listInternos;
    List<Elemento> listExternos;
    List<Elemento> listDespensa;
    List<Elemento> listLimpieza;
    List<Elemento> listConstruccion;
    ElementoAdapter iAdapter;
    ElementoAdapter eAdapter;
    ElementoAdapter dAdapter;
    ElementoAdapter lAdapter;
    ElementoAdapter cAdapter;
    ProgressBar progressBar;

    public RetornarElementos(Context context, ElementoAdapter iAdapter, ElementoAdapter eAdapter, ElementoAdapter dAdapter, ElementoAdapter lAdapter, ElementoAdapter cAdapter) {
        this.context = context;
        this.iAdapter = iAdapter;
        this.eAdapter = eAdapter;
        this.dAdapter = dAdapter;
        this.lAdapter = lAdapter;
        this.cAdapter = cAdapter;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            String proyecto = params[0];
            String link = "http://atreveteacrecer.metrocasas.com.gt/getProjectElements.php";
            String data = URLEncoder.encode("proyecto", "UTF-8") + "=" + URLEncoder.encode(proyecto, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (Exception e) {
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
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("elementos");
            if(jsonArray.length() > 0)
            {
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject objecto = jsonArray.getJSONObject(i);
                    if(objecto.getString("clasificacion").equals("Interno")) {
                        Elemento item = new Elemento(objecto.getString("proyecto"), objecto.getString("clasificacion"), objecto.getString("elemento"));
                        listInternos.add(item);
                    } else if(objecto.getString("clasificacion").equals("Externo")) {
                        Elemento item = new Elemento(objecto.getString("proyecto"), objecto.getString("clasificacion"), objecto.getString("elemento"));
                        listExternos.add(item);
                    } else if(objecto.getString("clasificacion").equals("Despensa")) {
                        Elemento item = new Elemento(objecto.getString("proyecto"), objecto.getString("clasificacion"), objecto.getString("elemento"));
                        listDespensa.add(item);
                    } else if(objecto.getString("clasificacion").equals("Limpieza")) {
                        Elemento item = new Elemento(objecto.getString("proyecto"), objecto.getString("clasificacion"), objecto.getString("elemento"));
                        listLimpieza.add(item);
                    } else if(objecto.getString("clasificacion").equals("Construccion")) {
                        Elemento item = new Elemento(objecto.getString("proyecto"), objecto.getString("clasificacion"), objecto.getString("elemento"));
                        listConstruccion.add(item);
                    }
                }
                iAdapter.notifyDataSetChanged();
                eAdapter.notifyDataSetChanged();
                dAdapter.notifyDataSetChanged();
                lAdapter.notifyDataSetChanged();
                cAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }
}