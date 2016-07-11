package gt.com.metrocasas.revisiones;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static gt.com.metrocasas.revisiones.R.style.boton;


/**
 * Created by Usuario on 07/07/2016.
 */
public class ElementoAdapter extends RecyclerView.Adapter<ElementoAdapter.MyViewHolder> {

    private List<Elemento> listElemento;
    private Elemento object;
    private Activity actividad;

    public ElementoAdapter(List<Elemento> listElemento,Activity ac) {
        this.listElemento = listElemento;
        this.actividad = ac;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox estado;
        public TextView elemento;
        public Button agregar_comentario;

        public MyViewHolder(View view) {
            super(view);

            estado = (CheckBox) view.findViewById(R.id.element_checkBox);
            elemento = (TextView) view.findViewById(R.id.element_revision);
            agregar_comentario = (Button) view.findViewById(R.id.element_btn);

            agregar_comentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Agregar Comentario",Toast.LENGTH_LONG).show();
                }
            });

            estado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        agregar_comentario.setEnabled(false);
                        elemento.setPaintFlags(elemento.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        object.setEstado(true);
                    }else{
                        elemento.setPaintFlags(elemento.getPaintFlags() &~Paint.STRIKE_THRU_TEXT_FLAG);
                        agregar_comentario.setEnabled(true);
                        object.setEstado(false);
                    }
                }
            });

            agregar_comentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comentarioDialog comentario = new comentarioDialog();
                    comentario.setElement(object);
                    comentario.show(actividad.getFragmentManager(),"");

                    estado.setEnabled(false);
                    elemento.setPaintFlags(elemento.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
            });
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_elemento, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Elemento item = listElemento.get(position);
        holder.elemento.setText(item.getElemento());
        object = item;
    }

    @Override
    public int getItemCount() {
        return listElemento.size();
    }

    public List<Elemento> getListElemento() {
        return listElemento;
    }



}
