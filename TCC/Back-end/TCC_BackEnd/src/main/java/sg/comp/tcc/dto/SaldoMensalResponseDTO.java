package sg.comp.tcc.dto;

import java.time.LocalDate;

import sg.comp.tcc.entity.SaldoMensal;
import sg.comp.tcc.entity.Usuario;

public class SaldoMensalResponseDTO {
	private Long id;
	private Double valor;
	private LocalDate diaDoMes;
	private Usuario usuario;
	
	public SaldoMensalResponseDTO() {
	}

	public SaldoMensalResponseDTO(SaldoMensal saldoMensal) {
		this.id = saldoMensal.getId();
		this.valor = saldoMensal.getValor();
		this.diaDoMes = saldoMensal.getDiaDoMes();
		this.usuario = saldoMensal.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDiaDoMes() {
		return diaDoMes;
	}

	public void setDiaDoMes(LocalDate diaDoMes) {
		this.diaDoMes = diaDoMes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
