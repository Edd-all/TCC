package sg.comp.tcc.dto;

import java.time.LocalDate;

import sg.comp.tcc.entity.Agendamento;
import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;

public class AgendamentoResponseDTO {
	
	private Long id;
	
	EnumTipoAgendamento tipoAgendamento;
	EnumReceitaDespesa tipoLancamento;
	
	String descricao;
	Double valor;
	
	LocalDate data; //dia especifico
	String diaSemana = "Indefinido";
	int diaMes;
	
	LancamentoFinanceiro lancamento;
	
	public AgendamentoResponseDTO() {

	}

	public AgendamentoResponseDTO(Agendamento agendamento) {
		super();
		this.id = agendamento.getId();
		this.tipoAgendamento = agendamento.getTipoAgendamento();
		this.tipoLancamento = agendamento.getTipoLancamento();
		this.descricao = agendamento.getDescricao();
		this.valor = agendamento.getValor();
		this.data = agendamento.getData();
		
		if(agendamento.getDiaSemana() != null) {
			this.diaSemana = agendamento.getDiaSemana().toString();
		}
		
		this.diaMes = agendamento.getDiaMes();
		this.lancamento = agendamento.getLancamentoFinanceiro();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(int diaMes) {
		this.diaMes = diaMes;
	}

	public LancamentoFinanceiro getLancamento() {
		return lancamento;
	}

	public void setLancamento(LancamentoFinanceiro lancamento) {
		this.lancamento = lancamento;
	}
	
	
}
