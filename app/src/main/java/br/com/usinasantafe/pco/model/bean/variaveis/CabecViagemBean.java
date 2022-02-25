package br.com.usinasantafe.pco.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pco.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecviagemvar")
public class CabecViagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecViagem;
    @DatabaseField
    private String dthrCabecViagem;
    @DatabaseField
    private Long dthrLongCabecViagem;
    @DatabaseField
    private Long idEquipCabecViagem;
    @DatabaseField
    private Long matricMotoCabecViagem;
    @DatabaseField
    private Long idTurnoCabecViagem;
    @DatabaseField
    private Long idTrajetoCabecViagem;
    @DatabaseField
    private Double hodometroInicialCabecViagem;
    @DatabaseField
    private Double hodometroFinalCabecViagem;
    @DatabaseField
    private Long statusCabecViagem;
    // 1 - Aberto;
    // 2 - Fechado;
    // 3 - Enviado

    public CabecViagemBean() {
    }

    public Long getIdCabecViagem() {
        return idCabecViagem;
    }

    public void setIdCabecViagem(Long idCabecViagem) {
        this.idCabecViagem = idCabecViagem;
    }

    public String getDthrCabecViagem() {
        return dthrCabecViagem;
    }

    public void setDthrCabecViagem(String dthrCabecViagem) {
        this.dthrCabecViagem = dthrCabecViagem;
    }

    public Long getDthrLongCabecViagem() {
        return dthrLongCabecViagem;
    }

    public void setDthrLongCabecViagem(Long dthrLongCabecViagem) {
        this.dthrLongCabecViagem = dthrLongCabecViagem;
    }

    public Long getIdEquipCabecViagem() {
        return idEquipCabecViagem;
    }

    public void setIdEquipCabecViagem(Long idEquipCabecViagem) {
        this.idEquipCabecViagem = idEquipCabecViagem;
    }

    public Long getMatricMotoCabecViagem() {
        return matricMotoCabecViagem;
    }

    public void setMatricMotoCabecViagem(Long matricMotoCabecViagem) {
        this.matricMotoCabecViagem = matricMotoCabecViagem;
    }

    public Long getIdTurnoCabecViagem() {
        return idTurnoCabecViagem;
    }

    public void setIdTurnoCabecViagem(Long idTurnoCabecViagem) {
        this.idTurnoCabecViagem = idTurnoCabecViagem;
    }

    public Long getIdTrajetoCabecViagem() {
        return idTrajetoCabecViagem;
    }

    public void setIdTrajetoCabecViagem(Long idTrajetoCabecViagem) {
        this.idTrajetoCabecViagem = idTrajetoCabecViagem;
    }

    public Long getStatusCabecViagem() {
        return statusCabecViagem;
    }

    public void setStatusCabecViagem(Long statusCabecViagem) {
        this.statusCabecViagem = statusCabecViagem;
    }

    public Double getHodometroInicialCabecViagem() {
        return hodometroInicialCabecViagem;
    }

    public void setHodometroInicialCabecViagem(Double hodometroInicialCabecViagem) {
        this.hodometroInicialCabecViagem = hodometroInicialCabecViagem;
    }

    public Double getHodometroFinalCabecViagem() {
        return hodometroFinalCabecViagem;
    }

    public void setHodometroFinalCabecViagem(Double hodometroFinalCabecViagem) {
        this.hodometroFinalCabecViagem = hodometroFinalCabecViagem;
    }
}
