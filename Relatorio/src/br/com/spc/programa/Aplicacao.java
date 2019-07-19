package br.com.spc.programa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.spc.entity.Pessoa;
import br.com.spc.helper.Helper;

public class Aplicacao {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		listarPessoas(em);
	}

	private static void listarPessoas(EntityManager em) {
		Helper dao = new Helper(em);
		List<Pessoa> pessoas = dao.listarPessoas();
		int qtdPacientes = 0;
		double mediaIdade = 0;
		double idadeSoma = 0;
		int qtdHomens = 0;
		double alturaMin = 1.60;
		double alturaMax = 1.70;
		int pesoAcima = 70;
		int qtdMulherAlturaMinMax = 0;
		int idadeMin = 18;
		int idadeMax = 25;
		int qtdPessoaIdadeMinMax = 0;
		double alturaMenor = 99.99;
		int idadeMaior = 0;
		List<String> nomeMulher = new ArrayList<>();
		List<String> nomePaciente = new ArrayList<>();
		for(Pessoa pessoa : pessoas){
			if(pessoa.getSexo().equals("M")){
				idadeSoma = idadeSoma + pessoa.getIdade();
				qtdHomens++;
			}else if(pessoa.getSexo().equals("F")){
				if((pessoa.getAltura() >= alturaMin && pessoa.getAltura() <= alturaMax) && pessoa.getPeso() > pesoAcima){
					qtdMulherAlturaMinMax++;
				}else if(pessoa.getAltura() < alturaMenor){
					alturaMenor = pessoa.getAltura();
					nomeMulher.add(pessoa.getNome());
				}
			}
			if(pessoa.getIdade() >= idadeMin && pessoa.getIdade() <= idadeMax){
				qtdPessoaIdadeMinMax++;
			}
			
			if(pessoa.getIdade() > idadeMaior){
				nomePaciente.clear();
				idadeMaior = pessoa.getIdade();
				nomePaciente.add(pessoa.getNome());
			}
			qtdPacientes++;
		}
		mediaIdade = (idadeSoma/qtdHomens);
		System.out.println("Quantidade de pacientes: " + qtdPacientes);
		System.out.println("Idade média dos homens: " + mediaIdade);
		System.out.println("Quantidade de mulheres com altura entre 1,60 e 1,70 e peso acima de 70kg: " + qtdMulherAlturaMinMax);
		System.out.println("Quantidade de pessoas com idade entre 18 e 25 anos: " + qtdPessoaIdadeMinMax);
		System.out.println("Nome do paciente mais velho: " + nomePaciente);
		System.out.println("Nome da mulher mais baixa: " + nomeMulher);
	}

}
