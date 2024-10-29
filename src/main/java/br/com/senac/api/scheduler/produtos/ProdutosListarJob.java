package br.com.senac.api.scheduler.produtos;

import br.com.senac.api.useCases.produtos.ProdutosService;
import br.com.senac.api.useCases.produtos.domains.ProdutosResponseDom;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisallowConcurrentExecution
public class ProdutosListarJob implements Job {

    @Autowired
    private ProdutosService produtosService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<ProdutosResponseDom> produtosResponseDomList = produtosService.carregarProdutos();

        produtosResponseDomList
                .stream()
                .forEach(p -> logger.info("Id: {}, Nome: {}", p.getId(), p.getNome()));
    }
}
