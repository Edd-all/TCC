package sg.comp.tcc.dto;

import java.time.LocalDate;

import sg.comp.tcc.entity.LancamentoFinanceiro;
import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;

public class LancamentoFinanceiroResponseDTO {
	private Long id;
	private String nome;
	private String descricao;
	private Double valor;
	private LocalDate dataCriacao;										
	
	private EnumReceitaDespesa tipoLancamento; 
	
	private EnumTipoAgendamento tipoAgendamento;
	private LocalDate diaEspecifico ; 
	private EnumDiaSemana diaSemana;
	private int diaMes;
	
	private Usuario usuario;
	
	public LancamentoFinanceiroResponseDTO() {
	}

	public LancamentoFinanceiroResponseDTO(LancamentoFinanceiro lancamentoFinanceiro) {
		super();
		this.id = lancamentoFinanceiro.getId();
		this.nome = lancamentoFinanceiro.getNome();
		this.descricao = lancamentoFinanceiro.getDescricao();
		this.valor = lancamentoFinanceiro.getValor();
		this.dataCriacao = lancamentoFinanceiro.getDataCriacao();
		this.tipoLancamento = lancamentoFinanceiro.getTipoLancamento();
		
		
		this.tipoAgendamento = lancamentoFinanceiro.getTipoAgendamento();
		this.diaEspecifico = lancamentoFinanceiro.getDiaEspecifico();
		this.diaSemana = lancamentoFinanceiro.getDiaSemana();
		this.diaMes = lancamentoFinanceiro.getDiaMes();
		
		this.usuario = lancamentoFinanceiro.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public EnumReceitaDespesa getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(EnumReceitaDespesa tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	

	public EnumTipoAgendamento getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(EnumTipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public LocalDate getDiaEspecifico() {
		return diaEspecifico;
	}
	public void setDiaEspecifico(LocalDate diaEspecifico) {
		this.diaEspecifico = diaEspecifico;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
