package sg.comp.tcc.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sg.comp.tcc.enums.EnumDiaSemana;
import sg.comp.tcc.enums.EnumReceitaDespesa;
import sg.comp.tcc.enums.EnumTipoAgendamento;

@Entity
public class LancamentoFinanceiro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; 
	private String descricao;
	private Double valor;
	private LocalDate dataCriacao = LocalDate.now();
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumReceitaDespesa tipoLancamento; 		//receita ou despesa
	
	
	private EnumTipoAgendamento tipoAgendamento;
	private LocalDate diaEspecifico ; 
	private EnumDiaSemana diaSemana;
	private int diaMes;
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
		
	public LancamentoFinanceiro(String nome, String descricao,Double valor, LocalDate dataCriacao,
			EnumReceitaDespesa tipoLancamento, EnumTipoAgendamento tipoAgendamento, 
			LocalDate diaEspecifico , EnumDiaSemana diaSemana, int diaMes,Usuario usuario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.dataCriacao = (dataCriacao != null) ? dataCriacao : LocalDate.now();
		this.tipoLancamento = tipoLancamento;
		
		this.tipoAgendamento = tipoAgendamento;
		this.diaEspecifico = diaEspecifico;
		this.diaSemana = diaSemana;
		this.diaMes = diaMes;
		
		this.usuario = usuario;
	}
	public LancamentoFinanceiro() {
		
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
		if (this.tipoLancamento == EnumReceitaDespesa.DESPESA) {
            return -Math.abs(this.valor); // Garante que o valor seja negativo para despesas
        }
        return Math.abs(this.valor); // Garante que o valor seja positivo para receitas
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
