package sg.comp.tcc.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoScheduler {

    @Autowired
    private LancamentoFincanceiroService lancamentoService;

    @Scheduled(cron = "0 52 0 * * ?")
    public void run() {
    	System.out.println("executou");
        lancamentoService.executaAgendamentos(LocalDate.now());
    }
}

