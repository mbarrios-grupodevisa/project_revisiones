package gt.com.metrocasas.revisiones;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class comentarioDialog extends DialogFragment {

    private EditText comentario;
    private ImageView imagen;
    OnSimpleDialogListener listener;
    Elemento item;
    private String pictureImagePath = "";
    public final static int RESULT_CAMERA = 1;
    File imgFile = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View v = inflater.inflate(R.layout.dialog_comentario, null);

        comentario = (EditText) v.findViewById(R.id.editText_comentario);
        imagen = (ImageView)v.findViewById(R.id.Foto);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });


        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                            listener.onPossitiveButtonClick(null);
                            if(!comentario.getText().toString().equals("") || !imgFile.exists())
                            {
                                item.setComentario(comentario.getText().toString());
                                item.setImagen(imgFile.getName());
                                item.setImagePath(imgFile.getAbsolutePath());
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

    public void takePicture() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Revision" + timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(pictureImagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, RESULT_CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CAMERA) {
            imgFile = new  File(pictureImagePath);
            if(imgFile.exists()){
                imagen.setBackground(Drawable.createFromPath(imgFile.getAbsolutePath()));
                imagen.setImageResource(0);
            }
        }
    }
}
