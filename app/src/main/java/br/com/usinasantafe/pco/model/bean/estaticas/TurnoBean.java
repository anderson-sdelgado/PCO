package br.com.usinasantafe.pco.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbturnoest")
public class TurnoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTurno;
    @DatabaseField
    private String descrTurno;

    public TurnoBean() {
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public String getDescrTurno() {
        return descrTurno;
    }

    public void setDescrTurno(String descrTurno) {
        this.descrTurno = descrTurno;
    }
}
