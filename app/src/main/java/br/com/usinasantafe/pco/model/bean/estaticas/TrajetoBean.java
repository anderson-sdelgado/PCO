package br.com.usinasantafe.pco.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbtrajetoest")
public class TrajetoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTrajeto;
    @DatabaseField
    private String descrTrajeto;

    public TrajetoBean() {
    }

    public Long getIdTrajeto() {
        return idTrajeto;
    }

    public void setIdTrajeto(Long idTrajeto) {
        this.idTrajeto = idTrajeto;
    }

    public String getDescrTrajeto() {
        return descrTrajeto;
    }

    public void setDescrTrajeto(String descrTrajeto) {
        this.descrTrajeto = descrTrajeto;
    }
}
