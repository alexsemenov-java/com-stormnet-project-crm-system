package com.stormnet.crm.system;

import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.impl.CommandFactory;
import com.stormnet.crm.system.web.socket.ResponseCode;
import com.stormnet.crm.system.web.socket.SocketJsonRequest;
import com.stormnet.crm.system.web.socket.SocketJsonResponse;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1111);

        System.out.println("Server started");

        while (true) {
            Socket clientSocket = serverSocket.accept();

            InputStream clientIs = clientSocket.getInputStream();
            JSONTokener tokener = new JSONTokener(clientIs);

            Request request = new SocketJsonRequest(tokener);
            Response response = new SocketJsonResponse();

            CommandFactory commandFactory = CommandFactory.get();
            Command command = commandFactory.getCommand(request.getCommandName());

            OutputStream clientOs = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(clientOs);
            JSONWriter jsonWriter = new JSONWriter(printWriter);
            response.setJsonWriter(jsonWriter);

            if (request.getCommandName().equals("")) {
                response.setResponseCode(ResponseCode.NotFoundCode);
                System.out.println("Command Not Found");

                response.getJsonWriter().object();
                buildHeadersSection(response);
                response.getJsonWriter().endObject();

                printWriter.close();
                clientSocket.close();
                continue;
            }

            System.out.println("Command " + request.getCommandName() + " received");

            if (command == null) {
                response.setResponseCode(ResponseCode.BadRequestCode);
                System.out.println("Unknown command");

                response.getJsonWriter().object();
                buildHeadersSection(response);
                response.getJsonWriter().endObject();

                printWriter.close();
                clientSocket.close();
                continue;
            }

            try {
                response.getJsonWriter().object();
                command.execute(request, response);

            } catch (Exception e) {
                System.out.println("Server Error!");

                response.setResponseCode(ResponseCode.ServerErrorCode);
                buildHeadersSection(response);
                response.getJsonWriter().endObject();

                printWriter.close();
                clientSocket.close();
                continue;
            }

            buildHeadersSection(response);
            response.getJsonWriter().endObject();

            printWriter.close();
            clientSocket.close();

            System.out.println("Command " + request.getCommandName() + " processed");
        }
    }

    private static void buildHeadersSection(Response response) {
        response.getJsonWriter().key("headers");

        response.getJsonWriter().object();
        response.getJsonWriter().key("status-code").value(response.getStatusCode());
        response.getJsonWriter().key("status-message").value(response.getStatusMessage());
        response.getJsonWriter().endObject();
    }
}