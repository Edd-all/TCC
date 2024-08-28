package sg.comp.tcc.dto;

import java.time.LocalDate;


import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;

public class AgendamentoRequestDTO {
	
	EnumTipoAgendamento tipoAgendamento;
	EnumReceitaDespesa tipoLancamento;
	
	String descricao;
	Double valor;
	
	LocalDate data; //dia de hoje
	EnumDiaSemana diaSemana;
	int diaMes;
	
	Long lancamentoFinanceiro;
	
	public EnumTipoAgendamento getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(EnumTipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public EnumReceitaDespesa getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(EnumReceitaDespesa tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public EnumDiaSemana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(EnumDiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}
	public int getDiaMes() {
		return diaMes;
	}
	public void setDiaMes(int diaMes) {
		this.diaMes = diaMes;
	}
	
	
	public Long getLancamentoFinanceiro() {
		return lancamentoFinanceiro;
	}
	public void setLancamentoFinanceiro(Long lancamentoFinanceiro) {
		this.lancamentoFinanceiro = lancamentoFinanceiro;
	}
	
	@Override
	public String toString() {
		return "AgendamentoRequestDTO [ tipoAgendamento=" + tipoAgendamento + ", tipoLancamento="
				+ tipoLancamento + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + ", diaSemana="
				+ diaSemana + ", diaMes=" + diaMes + ", lancamentoFinanceiro=" + lancamentoFinanceiro + "]";
	}
	
	
}
