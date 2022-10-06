package br.com.usinasantafe.pco.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbpassageiroviagemvar")
public class PassageiroViagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idPassageiroViagem;
    @DatabaseField
    private Long idCabecPassageiroViagem;
    @DatabaseField
    private Long matricColabPassageiroViagem;
    @DatabaseField
    private String dthrPassageiroViagem;
    @DatabaseField
    private Long dthrLongPassageiroViagem;
    @DatabaseField
    private Long tipoPassageiroViagem; // 1 - Leitor; 2 - Manual
    @DatabaseField
    private Long statusPassageiroViagem; // 1 - Sem Envio; 2 - Enviado

    public PassageiroViagemBean() {
    }

    public Long getIdPassageiroViagem() {
        return idPassageiroViagem;
    }

    public void setIdPassageiroViagem(Long idPassageiroViagem) {
        this.idPassageiroViagem = idPassageiroViagem;
    }

    public Long getIdCabecPassageiroViagem() {
        return idCabecPassageiroViagem;
    }

    public void setIdCabecPassageiroViagem(Long idCabecPassageiroViagem) {
        this.idCabecPassageiroViagem = idCabecPassageiroViagem;
    }

    public Long getMatricColabPassageiroViagem() {
        return matricColabPassageiroViagem;
    }

    public void setMatricColabPassageiroViagem(Long matricColabPassageiroViagem) {
        this.matricColabPassageiroViagem = matricColabPassageiroViagem;
    }

    public String getDthrPassageiroViagem() {
        return dthrPassageiroViagem;
    }

    public void setDthrPassageiroViagem(String dthrPassageiroViagem) {
        this.dthrPassageiroViagem = dthrPassageiroViagem;
    }

    public Long getDthrLongPassageiroViagem() {
        return dthrLongPassageiroViagem;
    }

    public void setDthrLongPassageiroViagem(Long dthrLongPassageiroViagem) {
        this.dthrLongPassageiroViagem = dthrLongPassageiroViagem;
    }

    public Long getStatusPassageiroViagem() {
        return statusPassageiroViagem;
    }

    public void setStatusPassageiroViagem(Long statusPassageiroViagem) {
        this.statusPassageiroViagem = statusPassageiroViagem;
    }

    public Long getTipoPassageiroViagem() {
        return tipoPassageiroViagem;
    }

    public void setTipoPassageiroViagem(Long tipoPassageiroViagem) {
        this.tipoPassageiroViagem = tipoPassageiroViagem;
    }
}
