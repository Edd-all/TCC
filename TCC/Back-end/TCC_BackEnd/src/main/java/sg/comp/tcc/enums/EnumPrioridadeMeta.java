package sg.comp.tcc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumPrioridadeMeta {
	ALTA ("A", "Alta"),
	MEDIA ("M", "Media"),
	BAIXA ("B", "Baixa");
	
	private String codigo;
	private String descricao;
	
	private EnumPrioridadeMeta(String codigo, String descricao) {
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
	public static EnumPrioridadeMeta doValor(String codigo) {
		if(codigo.equals("A")) {
			return ALTA;
		}else if(codigo.equals("M")) {
			return MEDIA;
		}else if(codigo.equals("B")) {
			return BAIXA;
		}else {
			return null;
		}
	}
}
