package br.com.usinasantafe.pco.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbturnoest")
public class TurnoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idRJornadaTurno;
    @DatabaseField
    private Long idJornada;
    @DatabaseField
    private Long idTurno;
    @DatabaseField
    private Long nroTurno;
    @DatabaseField
    private String descTurno;

    public TurnoBean() {
    }

    public Long getIdRJornadaTurno() {
        return idRJornadaTurno;
    }

    public void setIdRJornadaTurno(Long idRJornadaTurno) {
        this.idRJornadaTurno = idRJornadaTurno;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public Long getNroTurno() {
        return nroTurno;
    }

    public void setNroTurno(Long nroTurno) {
        this.nroTurno = nroTurno;
    }

    public String getDescTurno() {
        return descTurno;
    }

    public void setDescTurno(String descTurno) {
        this.descTurno = descTurno;
    }
}
