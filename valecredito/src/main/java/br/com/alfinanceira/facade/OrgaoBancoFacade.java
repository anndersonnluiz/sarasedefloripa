package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.OrgaoBancoDao;
import br.com.alfinanceira.model.OrgaoBanco;

public class OrgaoBancoFacade {

	OrgaoBancoDao orgaoBancoDao;
	
	public OrgaoBanco salvar(OrgaoBanco banco) {
		orgaoBancoDao = new OrgaoBancoDao();
		return orgaoBancoDao.salvar(banco);
	}

	public OrgaoBanco consultar(int idbanco) {
		orgaoBancoDao = new OrgaoBancoDao();
		return orgaoBancoDao.consultar(idbanco);
	}

	public List<OrgaoBanco> lista(String sql) {
		orgaoBancoDao = new OrgaoBancoDao();
		return orgaoBancoDao.lista(sql);
	}
}
