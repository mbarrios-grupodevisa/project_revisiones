package gt.com.metrocasas.revisiones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Usuario on 12/07/2016.
 */
public class NuevoItemRevisionDialog extends DialogFragment{

    private Spinner lista_proyectos;
    private Spinner lista_clasificacion;
    private EditText revision;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

               // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View v = inflater.inflate(R.layout.dialog_ingresar_item_revision, null);

        lista_proyectos = (Spinner) v.findViewById(R.id.proyecto);
        lista_clasificacion = (Spinner) v.findViewById(R.id.clasificacion);
        revision = (EditText) v.findViewById(R.id.editText_revision);

        //String p = getArguments().getString("proyecto");
        //if(p != null)
        //{
        //    if(p.equals("Viventi")) lista_clasificacion.setSelection(0);
        //    if(p.equals("Casa Asunción")) lista_clasificacion.setSelection(1);
        //}

        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if(!revision.getText().toString().equals(""))
                        {
                            //Subir Datos
                            String proyecto = lista_proyectos.getSelectedItem().toString();
                            String clasificación = lista_clasificacion.getSelectedItem().toString();
                            String rev = revision.getText().toString();
                            new IngresoElemento(getActivity()).execute(proyecto,clasificación,rev);
                        }
                        else
                        {
                            Toast.makeText(v.getContext(),"Hay campos vacios",Toast.LENGTH_LONG).show();
                            NuevoItemRevisionDialog.this.getDialog().dismiss();
                        }
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NuevoItemRevisionDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();

    }

}