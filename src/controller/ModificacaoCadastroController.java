package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import br.edu.fateczl.Lista;
import model.Cliente;

public class ModificacaoCadastroController {

	private void novoArquivo(Lista<Cliente> l, String nomeArquivo) throws Exception {
		String path = "C:" + File.separator + "Temp" + File.separator;
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(path, nomeArquivo);
		int tamanho = l.size();
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < tamanho; i++) {
			Cliente linha = l.get(i);
			buffer.append(linha.getCPF()).append(";").append(linha.getNome()).append(";").append(linha.getIdade())
					.append(";").append(linha.getLimiteCredito());

			buffer.append("\r\n");
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(arq));
		writer.write(buffer.toString());
		writer.close();

	}

	public void novoCadastro(int idadeMin, int idadeMax, Double limiteCredito) throws Exception {
		Lista<Cliente> lista = new Lista<>();
		String path = "C:" + File.separator + "Temp" + File.separator;
		File arq = new File(path, "Cadastro.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] split = linha.split(";");
				Cliente c = new Cliente(split[0], split[1], split[2], split[3]);
				if (Integer.parseInt(c.getIdade()) >= idadeMin && Integer.parseInt(c.getIdade()) <= idadeMax
						&& Double.parseDouble(c.getLimiteCredito().trim().replace(",", ".").replaceAll("[^\\d.]",
								"")) > limiteCredito) {
					lista.addLast(c);
				}
				linha = buffer.readLine();
				;
			}

			novoArquivo(lista, "Idade " + idadeMin + " - " + idadeMax + " - Limite " + limiteCredito + ".csv");
			buffer.close();
		}

	}
}
