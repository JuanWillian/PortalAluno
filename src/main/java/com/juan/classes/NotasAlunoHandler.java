package com.juan.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NotasAlunoHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("entrou no handler");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        String path = request.getRequestURI();
        String[] partes = path.split("/");
        String idAluno = partes.length > 3 ? partes[3] : null;
//        response.getWriter().print("HelloWorld");
        List<Aluno> list = procurarNotasAluno(idAluno);
        response.getWriter().println(list.stream().map(Aluno::getNota));
        baseRequest.setHandled(true);
    }

    public List<Aluno> procurarNotasAluno(String idAluno) {
        File arquivo = criarArquivoCsv();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            List<Aluno> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] vet;
                vet = line.split(",");
                if (vet[0].equals(idAluno)) {
                    list.add(new Aluno(Integer.parseInt(vet[0]), vet[1], Double.parseDouble(vet[2])));
                }
                line = br.readLine();
            }

            return list;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    //criar pra diminuir a qtd de codigo que vai ter caso eu queira ler mais arquivos
//	public void lerArquivos() {
//		
//	}

    public File criarArquivoCsv() {
        //talvez possa virar atributo ou algo assim
        File caminhoBase = new File(File.separator + "home" + File.separator + "juan" + File.separator + "Documentos");
        if (caminhoBase.mkdirs()) System.out.println("Criado");
        ;
        File subpasta = new File(File.separator + "PortalAluno" + File.separator + "Alunos" + File.separator + "Notas");
        if (subpasta.mkdirs()) System.out.println("CRIADO");
        return new File(caminhoBase + "" + subpasta + File.separator + "notas.csv");

    }

}
