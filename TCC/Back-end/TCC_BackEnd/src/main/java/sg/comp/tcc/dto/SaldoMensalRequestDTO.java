package sg.comp.tcc.dto;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


public class SaldoMensalRequestDTO {
	@Id
	private Long id;
	private Double valor;
	private LocalDate diaDoMes;
	@NotBlank
    private Long usuario;
	
	
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
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	
}
