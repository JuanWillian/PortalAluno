package com.juan.main;

import java.util.logging.Handler;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletHandler;

import com.juan.classes.MediaAlunoHandler;
import com.juan.classes.NotasAlunoHandler;

public class Main {

	public static void main(String[] args) {
		Server server = new Server(8080);
		ContextHandlerCollection handlers = new ContextHandlerCollection();
		ContextHandler contextNotasAluno = new ContextHandler();
		contextNotasAluno.setContextPath("/Aluno/");
		contextNotasAluno.setHandler(new NotasAlunoHandler());
		ContextHandler contextMediaAluno = new ContextHandler();
		contextMediaAluno.setHandler(new MediaAlunoHandler())
		handlers.addHandler(contextNotasAluno);
		handlers.addHandler(contextMediaAluno);
		server.setHandler(handlers);
		try {
			server.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			server.join();
			System.out.println("rodando o servidor");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
