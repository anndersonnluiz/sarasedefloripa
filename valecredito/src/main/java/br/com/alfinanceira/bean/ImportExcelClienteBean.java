package br.com.alfinanceira.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.LeitorExcel;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.ListenerException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.PlanilhaNaoEncontradaException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.gramatica.impl.ParseException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.listener.ColunaListener;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.util.LinhaColunaListenerVo;

public class ImportExcelClienteBean {

	public List<ClienteBean> importar(InputStream is) {
		LeitorExcel leitor = null;

		// Lista que ir� guardar os dados da planilha
		final List<ClienteBean> listaClienteBean = new LinkedList<ClienteBean>();

		leitor = new LeitorExcel("[*,*]", 1, is, null,

				new ColunaListener() {
					ClienteBean clienteVO = null;

					@Override
					public boolean lendoColuna(int linha, int coluna, @SuppressWarnings("rawtypes") Map dadosColuna)
							throws ListenerException {
						LinhaColunaListenerVo voAtual = (LinhaColunaListenerVo) dadosColuna
								.get(ColunaListener.CHAVE_VO_COLUNA);
						if (linha > 1) { // Pula primeira linha pois � a linha que possui o t�tulo
							switch (coluna) {
							case 1: // Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String cpf = "" +  voAtual.getCelulaAtual().getNumericCellValue();
								clienteVO.setCpf(cpf);
								break;
							case 2: // Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String nome = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setNome(nome);
								break;
//							case 4:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								Date nascimento = voAtual.getCelulaAtual().getDateCellValue();
//								clienteVO.setNascimento(nascimento);
//								break;
//	//						case 9:// Coluna
////								if (clienteVO == null) {
////									clienteVO = new ClienteBean();
////								}
////								Double valorsalario = voAtual.getCelulaAtual().getNumericCellValue();
////								clienteVO.setValorsalario(valorsalario.floatValue());
////								break;
////							case 10:// Coluna
////								if (clienteVO == null) {
////									clienteVO = new ClienteBean();
////								}
////								Double margem = voAtual.getCelulaAtual().getNumericCellValue();
////								clienteVO.setMargem(margem.floatValue());
////								;
////								break;
//							case 26:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String ufestado = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setUfestado(ufestado);
//								break;
//							case 27:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String cidade = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setCidade(cidade);
//							case 28:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String bairro = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setBairro(bairro);
//								break;
//							case 29:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String cep = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setCep(cep);
//								break;
							case 28:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String endereco = "" +  voAtual.getCelulaAtual().getNumericCellValue();
								clienteVO.setEndereco(endereco);
								break;
							case 29:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String telefoneCelular = "" +  voAtual.getCelulaAtual().getNumericCellValue();
								clienteVO.setTelefonecelular(telefoneCelular);

								listaClienteBean.add(clienteVO);
								clienteVO = null;
								break;
							case 30:// Coluna
								if (clienteVO == null) {
								clienteVO = new ClienteBean();
								}
								String telefoneResidencial = "" +  voAtual.getCelulaAtual().getNumericCellValue();
								clienteVO.setTelefoneresidencial(telefoneResidencial);

								listaClienteBean.add(clienteVO);
								clienteVO = null;
								break;
							case 31:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String telefoneComercial = "" + voAtual.getCelulaAtual().getNumericCellValue();
								clienteVO.setTelefonecomercial(telefoneComercial);

								listaClienteBean.add(clienteVO);
								clienteVO = null;
//							case 34:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String telefoneSecundario = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setTelefonesecundario(telefoneSecundario);
//
//								listaClienteBean.add(clienteVO);
//								clienteVO = null;
//								break;
//							case 35:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String email = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setEmail(email);
//
//								listaClienteBean.add(clienteVO);
//								clienteVO = null;
//								break;
//							case 36:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								Double cdbanco = voAtual.getCelulaAtual().getNumericCellValue();
//								clienteVO.setCdbanco(cdbanco.intValue());
//
//								listaClienteBean.add(clienteVO);
//								clienteVO = null;
//								break;
//							case 37:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String agencia = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setAgencia(agencia);
//
//								listaClienteBean.add(clienteVO);
//								clienteVO = null;
//								break;
//							case 38:// Coluna
//								if (clienteVO == null) {
//									clienteVO = new ClienteBean();
//								}
//								String conta = voAtual.getCelulaAtual().getStringCellValue();
//								clienteVO.setConta(conta);
//
//								listaClienteBean.add(clienteVO);
//								clienteVO = null;
							}
						}
 
						return true;

					}
				});

		try {
			leitor.processarLeituraPlanilha();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (PlanilhaNaoEncontradaException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ListenerException e) {
			e.printStackTrace();
		}

		return listaClienteBean;
	}
}
