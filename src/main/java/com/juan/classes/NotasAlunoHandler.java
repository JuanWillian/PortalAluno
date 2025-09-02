package com.juan.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

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
        String[] uri = request.getRequestURI().split("/");
        if (uri.length < 3) {
            response.getWriter().println("O id do aluno não foi enviado");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        String idAluno = uri[3];
        Aluno aluno = procurarNotasAluno(idAluno);
        response.getWriter().println("As notas de "+aluno.getNome()+" foram: "+aluno.getNotas());
        baseRequest.setHandled(true);
    }

    public Aluno procurarNotasAluno(String idAluno) {
        File arquivo = criarArquivoCsv();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line = br.readLine();
            while (line != null) {
                String[] vet;
                vet = line.split(",");
                if (vet[0].equals(idAluno)) {
                    return new Aluno(Integer.parseInt(vet[0]), vet[1], Arrays.asList(Double.parseDouble(vet[2]), Double.parseDouble(vet[3])));
                }
                line = br.readLine();
            }

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
