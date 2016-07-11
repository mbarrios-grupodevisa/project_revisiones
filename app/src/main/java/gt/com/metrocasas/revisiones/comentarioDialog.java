package gt.com.metrocasas.revisiones;

import android.app.Activity;
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
 * Created by Usuario on 08/07/2016.
 */
public class comentarioDialog extends DialogFragment {

    private EditText comentario;
    OnSimpleDialogListener listener;
    Elemento item;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View v = inflater.inflate(R.layout.dialog_comentario, null);


        comentario = (EditText) v.findViewById(R.id.editText_comentario);


        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                            listener.onPossitiveButtonClick(null);
                            if(!comentario.getText().toString().equals(""))
                            {
                                item.setComentario(comentario.getText().toString());
                                Toast.makeText(v.getContext(),"Ingreso Comentario",Toast.LENGTH_LONG).show();

                            }else{
                                comentarioDialog.this.getDialog().dismiss();
                            }

                    }
                })
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onNegativeButtonClick();
                        comentarioDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnSimpleDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSetTitleListener");

        }
    }

    public void setElement(Elemento e){
        this.item = e;
    }
}
