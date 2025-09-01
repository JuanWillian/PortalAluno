package com.juan.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NotasAlunoHandler extends AbstractHandler{
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("entrou no handler");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		String path = request.getRequestURI(); 
		String[] partes = path.split("/");
		String idAluno = partes.length > 3 ? partes[3] : null;
		response.getWriter().print("HelloWorld");
		Map<String, Double> map = procurarNotasAluno(idAluno);
		response.getWriter().print(map.get(idAluno));
		baseRequest.setHandled(true);
	}
	
	public Map<String, Double> procurarNotasAluno(String idAluno) {
		File file = new File(criarSubpastas()+File.separator+"notas.csv");
		if(file.mkdirs()) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			Map<String, Double> map = new HashMap<>();
			String line = br.readLine();
			while(line != null) {
				if(line.split(";").equals(idAluno)) {
					String[] vet = line.split(";");
					map.put(vet[0], Double.parseDouble(vet[1]));
					System.out.println(line);
				}
			line = br.readLine();
			return map;
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}}
		return null;
	
	}
	
	
	//criar pra diminuir a qtd de codigo que vai ter caso eu queira ler mais arquivos
//	public void lerArquivos() {
//		
//	}
	
	public File criarSubpastas() {
		return new File("Alunos"+File.separator+"Notas");
	}
	
}
