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
    private String dthrInicialCabecViagem;
    @DatabaseField
    private Long dthrInicialLongCabecViagem;
    @DatabaseField
    private String dthrFinalCabecViagem;
    @DatabaseField
    private Long dthrFinalLongCabecViagem;
    @DatabaseField
    private Long nroEquipCabecViagem;
    @DatabaseField
    private Long matricMotoCabecViagem;
    @DatabaseField
    private Long idTurnoCabecViagem;
    @DatabaseField
    private Long idTrajetoCabecViagem;
    @DatabaseField
    private String descrTrajetoCabecViagem;
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

    public String getDthrInicialCabecViagem() {
        return dthrInicialCabecViagem;
    }

    public void setDthrInicialCabecViagem(String dthrInicialCabecViagem) {
        this.dthrInicialCabecViagem = dthrInicialCabecViagem;
    }

    public Long getDthrInicialLongCabecViagem() {
        return dthrInicialLongCabecViagem;
    }

    public void setDthrInicialLongCabecViagem(Long dthrInicialLongCabecViagem) {
        this.dthrInicialLongCabecViagem = dthrInicialLongCabecViagem;
    }

    public String getDthrFinalCabecViagem() {
        return dthrFinalCabecViagem;
    }

    public void setDthrFinalCabecViagem(String dthrFinalCabecViagem) {
        this.dthrFinalCabecViagem = dthrFinalCabecViagem;
    }

    public Long getDthrFinalLongCabecViagem() {
        return dthrFinalLongCabecViagem;
    }

    public void setDthrFinalLongCabecViagem(Long dthrFinalLongCabecViagem) {
        this.dthrFinalLongCabecViagem = dthrFinalLongCabecViagem;
    }

    public Long getNroEquipCabecViagem() {
        return nroEquipCabecViagem;
    }

    public void setNroEquipCabecViagem(Long nroEquipCabecViagem) {
        this.nroEquipCabecViagem = nroEquipCabecViagem;
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

    public String getDescrTrajetoCabecViagem() {
        return descrTrajetoCabecViagem;
    }

    public void setDescrTrajetoCabecViagem(String descrTrajetoCabecViagem) {
        this.descrTrajetoCabecViagem = descrTrajetoCabecViagem;
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

    public Long getStatusCabecViagem() {
        return statusCabecViagem;
    }

    public void setStatusCabecViagem(Long statusCabecViagem) {
        this.statusCabecViagem = statusCabecViagem;
    }
}
