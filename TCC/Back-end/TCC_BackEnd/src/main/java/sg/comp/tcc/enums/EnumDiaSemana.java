package sg.comp.tcc.enums;

import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumDiaSemana {
	 	SEGUNDA_FEIRA(DayOfWeek.MONDAY),
	    TERCA_FEIRA(DayOfWeek.TUESDAY),
	    QUARTA_FEIRA(DayOfWeek.WEDNESDAY),
	    QUINTA_FEIRA(DayOfWeek.THURSDAY),
	    SEXTA_FEIRA(DayOfWeek.FRIDAY),
	    SABADO(DayOfWeek.SATURDAY),
	    DOMINGO(DayOfWeek.SUNDAY);

	    private final DayOfWeek dayOfWeek;

	    EnumDiaSemana(DayOfWeek dayOfWeek) {
	        this.dayOfWeek = dayOfWeek;
	    }
	    
	    public DayOfWeek getDayOfWeek() {
	        return dayOfWeek;
	    }

	    public static EnumDiaSemana fromDayOfWeek(DayOfWeek dayOfWeek) {
	        for (EnumDiaSemana enumDia : EnumDiaSemana.values()) {
	            if (enumDia.dayOfWeek == dayOfWeek) {
	                return enumDia;
	            }
	        }
	        throw new IllegalArgumentException("No matching EnumDiaSemana for DayOfWeek: " + dayOfWeek);
	    }
}
