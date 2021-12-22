package br.com.usinasantafe.pco.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbpassageirovar")
public class PassageiroBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idPassageiro;
    @DatabaseField
    private String dthrViagemPassageiro;
    @DatabaseField
    private Long dthrLongViagemPassageiro;
    @DatabaseField
    private Long idEquipPassageiro;
    @DatabaseField
    private Long matricMotoPassageiro;
    @DatabaseField
    private Long idTurnoPassageiro;
    @DatabaseField
    private Long matricColabPassageiro;
    @DatabaseField
    private String dthrPassageiro;
    @DatabaseField
    private Long dthrLongPassageiro;
    @DatabaseField
    private Long idTrajetoPassageiro;
    @DatabaseField
    private Long statusPassageiro; // 1 - Sem Envio; 2 - Enviado

    public PassageiroBean() {
    }

    public Long getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(Long idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public String getDthrViagemPassageiro() {
        return dthrViagemPassageiro;
    }

    public void setDthrViagemPassageiro(String dthrViagemPassageiro) {
        this.dthrViagemPassageiro = dthrViagemPassageiro;
    }

    public Long getIdEquipPassageiro() {
        return idEquipPassageiro;
    }

    public void setIdEquipPassageiro(Long idEquipPassageiro) {
        this.idEquipPassageiro = idEquipPassageiro;
    }

    public Long getMatricMotoPassageiro() {
        return matricMotoPassageiro;
    }

    public void setMatricMotoPassageiro(Long matricMotoPassageiro) {
        this.matricMotoPassageiro = matricMotoPassageiro;
    }

    public Long getIdTurnoPassageiro() {
        return idTurnoPassageiro;
    }

    public void setIdTurnoPassageiro(Long idTurnoPassageiro) {
        this.idTurnoPassageiro = idTurnoPassageiro;
    }

    public Long getMatricColabPassageiro() {
        return matricColabPassageiro;
    }

    public void setMatricColabPassageiro(Long matricColabPassageiro) {
        this.matricColabPassageiro = matricColabPassageiro;
    }

    public String getDthrPassageiro() {
        return dthrPassageiro;
    }

    public void setDthrPassageiro(String dthrPassageiro) {
        this.dthrPassageiro = dthrPassageiro;
    }

    public Long getStatusPassageiro() {
        return statusPassageiro;
    }

    public void setStatusPassageiro(Long statusPassageiro) {
        this.statusPassageiro = statusPassageiro;
    }

    public Long getDthrLongViagemPassageiro() {
        return dthrLongViagemPassageiro;
    }

    public void setDthrLongViagemPassageiro(Long dthrLongViagemPassageiro) {
        this.dthrLongViagemPassageiro = dthrLongViagemPassageiro;
    }

    public Long getDthrLongPassageiro() {
        return dthrLongPassageiro;
    }

    public void setDthrLongPassageiro(Long dthrLongPassageiro) {
        this.dthrLongPassageiro = dthrLongPassageiro;
    }

    public Long getIdTrajetoPassageiro() {
        return idTrajetoPassageiro;
    }

    public void setIdTrajetoPassageiro(Long idTrajetoPassageiro) {
        this.idTrajetoPassageiro = idTrajetoPassageiro;
    }
}
