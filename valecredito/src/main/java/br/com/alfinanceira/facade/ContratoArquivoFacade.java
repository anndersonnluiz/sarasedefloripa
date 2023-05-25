package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.ContratoArquivoDao;
import br.com.alfinanceira.model.Contratoarquivo;

public class ContratoArquivoFacade {

	ContratoArquivoDao contratoArquivoDao;
	
	public Contratoarquivo salvar(Contratoarquivo contratoarquivo) {
		contratoArquivoDao = new ContratoArquivoDao();
		return contratoArquivoDao.salvar(contratoarquivo);
	}


	public List<Contratoarquivo> lista(String sql) {
		contratoArquivoDao = new ContratoArquivoDao();
		return contratoArquivoDao.listar(sql);
	}
	
	
	public void excluir(int idcontratoarquivo) {
		contratoArquivoDao = new ContratoArquivoDao();
		contratoArquivoDao.excluir(idcontratoarquivo);
	}
}
