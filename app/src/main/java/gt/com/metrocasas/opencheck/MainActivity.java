package gt.com.metrocasas.opencheck;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;
    private Toolbar toolbar;
    public String userid, proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Viventi");
        proyecto = "Viventi";
        setSupportActionBar(toolbar);
        userid = getIntent().getExtras().getString("id");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setFragment(1);
        hello();
    }

    private void hello() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences("User",0);
        String firstName = settings.getString("firstname",null);
        String lastName = settings.getString("lastname",null);
        Snackbar.make(toolbar,"Bienvenido(a) " + firstName + " "+lastName, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            NuevoItemRevisionDialog nird = new NuevoItemRevisionDialog();
            Bundle bundle = new Bundle();
            bundle.putString("proyecto", proyecto);
            nird.setArguments(bundle);
            nird.show(this.getFragmentManager(),"");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.viventi) {
            proyecto = "Viventi";
            Fragment fragment = new FragmentRevisionesList();
            Bundle args = new Bundle();
            args.putString("proyecto", "Viventi");
            args.putString("id", userid);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            toolbar.setTitle("Viventi");
        } else if (id == R.id.casa_asuncion) {
            proyecto = "Casa Asunción";
            Fragment fragment = new FragmentRevisionesList();
            Bundle args = new Bundle();
            args.putString("proyecto", "Casa Asunción");
            args.putString("id", userid);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            toolbar.setTitle("Casa Asunción");
        }else if (id == R.id.logout)
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_lock_lock)
                    .setTitle("Cerrar Sesión")
                    .setMessage("¿Está seguro que desea cerrar su sesión y salir?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences settings = getApplicationContext().getSharedPreferences("User",0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("id",null);
                            editor.putString("firstname",null);
                            editor.apply();

                            finish();

                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://gt.com.metrocasas.opencheck/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://gt.com.metrocasas.opencheck/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void setFragment(int id) {
        if (id == 1) {
            Fragment fragment = new FragmentRevisionesList();
            Bundle args = new Bundle();
            args.putString("proyecto", "Viventi");
            args.putString("id", userid);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            toolbar.setTitle("Viventi");
        } else if (id == 2) {
            Fragment fragment = new FragmentRevisionesList();
            Bundle args = new Bundle();
            args.putString("proyecto", "Casa Asuncion");
            args.putString("id", userid);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            toolbar.setTitle("Casa Asunción");
        }
    }
}
