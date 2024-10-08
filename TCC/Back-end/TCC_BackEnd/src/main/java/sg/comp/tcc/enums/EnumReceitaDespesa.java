package sg.comp.tcc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumReceitaDespesa {
	RECEITA ("R", "Receita"),
	DESPESA ("D", "Despesa");
	
	private String codigo;
	private String descricao;
	
	private EnumReceitaDespesa(String codigo, String descricao) {
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
	public static EnumReceitaDespesa doValor(String codigo) {
		if(codigo.equals("R")) {
			return RECEITA;
		}else if(codigo.equals("D")) {
			return DESPESA;
		}else {
			return null;
		}
	}
	
}
