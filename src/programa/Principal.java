package programa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelos.Funcionario;

public class Principal {

	public static void main(String[] args) {

		List<Funcionario> funcionarios = new ArrayList<>();

		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9835.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1583.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		DateTimeFormatter formato_data = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat formato_salario = new DecimalFormat("#,##0.00");

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getNome().equals("João")) {
				funcionarios.remove(funcionario);
				break;
			}
		}

		for (Funcionario funcionario : funcionarios) {
			String dataFormatada = funcionario.getData_nascimento().format(formato_data);
			String salarioFormatado = formato_salario.format(funcionario.getSalario());

			System.out.println("Nome: " + funcionario.getNome() + ", Data de Nascimento: " + dataFormatada + ", Salario: "
					+ salarioFormatado + ", Função: " + funcionario.getFuncao());
		}

		for (Funcionario funcionario : funcionarios) {
			BigDecimal salario_atual = funcionario.getSalario();
			BigDecimal aumento_salarial = salario_atual.multiply(new BigDecimal(".10"));
			BigDecimal salario_apos_aumento = salario_atual.add(aumento_salarial);
			funcionario.setSalario(salario_apos_aumento);
		}

		Map<String, List<Funcionario>> funcionarios_por_funcao = new HashMap<>();
		for (Funcionario funcionario : funcionarios) {
			String funcao_funcionario = funcionario.getFuncao();
			List<Funcionario> funcionarios_da_funcao = funcionarios_por_funcao.get(funcao_funcionario);
			if (funcionarios_da_funcao == null) {
				funcionarios_da_funcao = new ArrayList<>();
				funcionarios_por_funcao.put(funcao_funcionario, funcionarios_da_funcao);
			}
			funcionarios_da_funcao.add(funcionario);
		}
		
        for (Map.Entry<String, List<Funcionario>> entry : funcionarios_por_funcao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("  Nome: " + funcionario.getNome() + ", Data de Nascimento: " + funcionario.getData_nascimento() + ", Salário: " + funcionario.getSalario());
            }
        }
        
        System.out.println("Aniversariantes em Outubro e Dezembro:");
        for (Funcionario funcionario : funcionarios) {
        	Month mes_nascimento = funcionario.getData_nascimento().getMonth();
        	if (mes_nascimento == Month.OCTOBER || mes_nascimento == Month.DECEMBER){
        		System.out.println("  Nome: " + funcionario.getNome() + ", Data de Nascimento: " + funcionario.getData_nascimento() + ", Salário: " + funcionario.getSalario());
			}
		}

	}

}
