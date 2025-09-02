package com.juan.classes;

import java.io.IOException;
import java.util.stream.DoubleStream;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MediaAlunoHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("application/json");
        String[] uri = request.getRequestURI().split("/");
        if (uri.length < 3) {
            response.getWriter().println("O id do aluno não foi enviado");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        String idAluno = uri[3];
        response.getWriter().print("A media do aluno foi:" + calcularMediaAluno(idAluno));
        baseRequest.setHandled(true);

    }

    public Double calcularMediaAluno(String idAluno) {
        NotasAlunoHandler handlerNotas = new NotasAlunoHandler();
        Aluno aluno = handlerNotas.procurarNotasAluno(idAluno);
        DoubleStream doubleStream = aluno.getNotas().stream().mapToDouble(Double::doubleValue);
        return doubleStream.average().orElse(0.0);
    }

}
