package br.com.usinasantafe.pco.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.variaveis.LogErroBean;

/**
 * Created by anderson on 08/03/2018.
 */
public class AdapterListErro extends BaseAdapter {

    private List<LogErroBean> itens;
    private LayoutInflater layoutInflater;
    private TextView textViewErroId;
    private TextView textViewErroAparelho;
    private TextView textViewErroDthr;
    private TextView textViewErroDescr;

    public AdapterListErro(Context context, List itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_log_erro, null);
        textViewErroId = view.findViewById(R.id.textViewErroId);
        textViewErroAparelho = view.findViewById(R.id.textViewErroAparelho);
        textViewErroDthr = view.findViewById(R.id.textViewErroDthr);
        textViewErroDescr = view.findViewById(R.id.textViewErroDescr);

        LogErroBean logErroBean = (LogErroBean) itens.get(position);

        textViewErroId.setText("ID = " + logErroBean.getIdLogErro());
        textViewErroAparelho.setText("NRO APARELHO = " + logErroBean.getNroAparelho());
        textViewErroDthr.setText("DTHR = " + logErroBean.getDthr());
        textViewErroDescr.setText(logErroBean.getException());

        return view;
    }

}
