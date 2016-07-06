package gt.com.metrocasas.revisiones;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class retornarRevisiones extends AsyncTask<String, Integer, String> {

    View v;
    Context context;
    private String json;
    public static String ERROR = "Error en los datos, revise";
    private RevisionAdapter rAdapter;
    private List<ItemRevision> listRevision = new ArrayList<>();

    public retornarRevisiones(Context context, RevisionAdapter rAdapter, List<ItemRevision> listRevision) {
        this.context = context;
        this.rAdapter = rAdapter;
        this.listRevision = listRevision;
    }
    @Override
    protected String doInBackground(String... params) {
        try {

            String proyecto = params[0];
            String link = "http://atreveteacrecer.metrocasas.com.gt/readRevisiones.php";
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
            JSONArray jsonArray = jsonObject.getJSONArray("revisiones");
            if(jsonArray.length() > 0)
            {
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject objecto = jsonArray.getJSONObject(i);
                    String fecha = objecto.getString("fechaRevision");
                    ItemRevision item = new ItemRevision(fecha);
                    listRevision.add(item);
                    rAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            ItemRevision item = new ItemRevision("ERROR");
            listRevision.add(item);
            rAdapter.notifyDataSetChanged();
        }
    }
}
