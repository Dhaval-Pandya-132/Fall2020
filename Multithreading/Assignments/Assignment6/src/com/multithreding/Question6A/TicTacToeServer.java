package com.multithreding.Question6A;

import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TicTacToeServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(58901)) {
            System.out.println("Tic Tac Toe Server is Running...");
            ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), 'X'));
                pool.execute(game.new Player(listener.accept(), 'O'));
            }
        }
    }

}
