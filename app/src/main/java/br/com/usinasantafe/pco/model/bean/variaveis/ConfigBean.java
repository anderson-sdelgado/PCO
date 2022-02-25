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
    private Long tipoEquipConfig; // 1 - PRÓPRIO; 2 - TERCEIRO;
    @DatabaseField
    private Long nroAparelhoConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long idEquipConfig;
    @DatabaseField
    private String dthrServConfig;
    @DatabaseField
    private Long difDthrConfig;
    @DatabaseField
    private Long lotacaoMaxConfig;
    @DatabaseField
    private Double hodometroConfig;
    @DatabaseField
    private Long posicaoTela;
    // 1 - Tela Inicial
    // 2 - Tela Passageiro
    // 3 - Verificando Motorista
    // 4 - Verificando Colaborador
    // 5 - Leitura Lista Passageiro
    // 6 - Leitura Msg Passageiro
    // 7 - Configuracao;
    // 8 - Log Menu Inicial;
    // 9 - Log Lista de Bag;
    // 10 - Finalizar Viagem

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

    public String getDthrServConfig() {
        return dthrServConfig;
    }

    public void setDthrServConfig(String dthrServConfig) {
        this.dthrServConfig = dthrServConfig;
    }

    public Long getDifDthrConfig() {
        return difDthrConfig;
    }

    public void setDifDthrConfig(Long difDthrConfig) {
        this.difDthrConfig = difDthrConfig;
    }

    public Long getTipoEquipConfig() {
        return tipoEquipConfig;
    }

    public void setTipoEquipConfig(Long tipoEquipConfig) {
        this.tipoEquipConfig = tipoEquipConfig;
    }

    public Long getNroAparelhoConfig() {
        return nroAparelhoConfig;
    }

    public void setNroAparelhoConfig(Long nroAparelhoConfig) {
        this.nroAparelhoConfig = nroAparelhoConfig;
    }

    public Long getLotacaoMaxConfig() {
        return lotacaoMaxConfig;
    }

    public void setLotacaoMaxConfig(Long lotacaoMaxConfig) {
        this.lotacaoMaxConfig = lotacaoMaxConfig;
    }

    public Long getPosicaoTela() {
        return posicaoTela;
    }

    public void setPosicaoTela(Long posicaoTela) {
        this.posicaoTela = posicaoTela;
    }

    public Double getHodometroConfig() {
        return hodometroConfig;
    }

    public void setHodometroConfig(Double hodometroConfig) {
        this.hodometroConfig = hodometroConfig;
    }
}
