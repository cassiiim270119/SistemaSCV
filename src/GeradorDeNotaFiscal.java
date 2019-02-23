import java.util.List;

public class GeradorDeNotaFiscal {
	//private final EnviadorDeEmail email;
	
	//private final NotaFiscalDao dao;
	
	private final List<AcaoAposGerarNota> acoes;
	
	
	public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes) {
		this.acoes = acoes;
	}

//	public GeradorDeNotaFiscal(EnviadorDeEmail email, NotaFiscalDao dao) {
//		this.email = email;
//		this.dao = dao;
//	}
	
	public NotaFiscal gera (Fatura fatura) {
		double valor = fatura.getValorMensal();
		NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobre0(valor));
		for(AcaoAposGerarNota acao: acoes) {
			acao.executa(nf);
		}
		return nf;
	}
	
	public List<AcaoAposGerarNota> getAcoes() {
		return acoes;
	}

	private double impostoSimplesSobre0(double valor) {
		return valor * 0.06;
	}
}
