package br.com.usinasantafe.pco.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
    @DatabaseField
    private Long idEquipConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long matricMotoConfig;
    @DatabaseField
    private Long idTurnoConfig;
    @DatabaseField
    private String dtrhViagemConfig;

    public ConfigBean() {
    }

    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public Long getIdEquipConfig() {
        return idEquipConfig;
    }

    public void setIdEquipConfig(Long idEquipConfig) {
        this.idEquipConfig = idEquipConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public Long getMatricMotoConfig() {
        return matricMotoConfig;
    }

    public void setMatricMotoConfig(Long matricMotoConfig) {
        this.matricMotoConfig = matricMotoConfig;
    }

    public Long getIdTurnoConfig() {
        return idTurnoConfig;
    }

    public void setIdTurnoConfig(Long idTurnoConfig) {
        this.idTurnoConfig = idTurnoConfig;
    }

    public String getDtrhViagemConfig() {
        return dtrhViagemConfig;
    }

    public void setDtrhViagemConfig(String dtrhViagemConfig) {
        this.dtrhViagemConfig = dtrhViagemConfig;
    }
}
