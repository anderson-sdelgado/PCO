package br.com.usinasantafe.pco.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbmotoest")
public class MotoristaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long matricMoto;
    @DatabaseField
    private String nomeMoto;

    public MotoristaBean() {
    }

    public Long getMatricMoto() {
        return matricMoto;
    }

    public void setMatricMoto(Long matricMoto) {
        this.matricMoto = matricMoto;
    }

    public String getNomeMoto() {
        return nomeMoto;
    }

    public void setNomeMoto(String nomeMoto) {
        this.nomeMoto = nomeMoto;
    }
}
