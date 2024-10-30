package sg.comp.tcc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumTipoAgendamento {
	DIAESPECIFICO("D","DiaEspecifico"), 
	DIASEMANA ("S", "DiaSemana"),
	DIAMES ("M", "DiaMes");
	
	private String codigo;
	private String descricao;
	
	private EnumTipoAgendamento(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	@JsonValue
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonCreator
	public static EnumTipoAgendamento doValor(String codigo) {
		if(codigo.equals("D")) {
			return DIAESPECIFICO;
		}else if(codigo.equals("S")) {
			return DIASEMANA;
		}else if(codigo.equals("M")) {
			return DIAMES;
		}else {
			return null;
		}
	}
}
