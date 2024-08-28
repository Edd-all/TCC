package sg.comp.tcc.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sg.comp.tcc.dto.AgendamentoRequestDTO;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;

@Entity
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	EnumTipoAgendamento tipoAgendamento;
	EnumReceitaDespesa tipoLancamento;
	
	String descricao;
	Double valor;
	
	LocalDate data ; //dia especifico
	EnumDiaSemana diaSemana;
	int diaMes;
	
	 @ManyToOne
	 @JoinColumn(name = "lancamento_id")
	 private LancamentoFinanceiro lancamentoFinanceiro;
	

	 public Agendamento() {
			super();
		}
	 
	 
	public Agendamento(AgendamentoRequestDTO agendamentoRequest) {
		super();
		this.tipoAgendamento = agendamentoRequest.getTipoAgendamento();
		this.tipoLancamento = agendamentoRequest.getTipoLancamento();
		this.descricao = agendamentoRequest.getDescricao();
		this.valor = agendamentoRequest.getValor();
		this.data = agendamentoRequest.getData();
		this.diaSemana = agendamentoRequest.getDiaSemana();
		this.diaMes = agendamentoRequest.getDiaMes();
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


	public LancamentoFinanceiro getLancamentoFinanceiro() {
		return lancamentoFinanceiro;
	}


	public void setLancamentoFinanceiro(LancamentoFinanceiro lancamentoFinanceiro) {
		this.lancamentoFinanceiro = lancamentoFinanceiro;
	}

	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", tipoAgendamento=" + tipoAgendamento + ", tipoLancamento=" + tipoLancamento
				+ ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + ", diaSemana=" + diaSemana
				+ ", diaMes=" + diaMes + "]";
	}

	
	
}
