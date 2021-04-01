package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.web.common.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private Map<String, Command> commands = new HashMap<>();

    public static CommandFactory get() {
        return instance;
    }

    public CommandFactory() {
        commands.put("get-all-acts", new GetAllActsCommand());
        commands.put("save-act", new SaveActCommand());
        commands.put("get-act", new GetActCommand());
        commands.put("delete-act", new DeleteActCommand());
        commands.put("update-act", new UpdateActCommand());

        commands.put("get-client-by-login-and-password", new GetClientByLoginPasswordCommand());
        commands.put("get-all-clients", new GetAllClientsCommand());
        commands.put("save-client", new SaveClientCommand());
        commands.put("get-client", new GetClientCommand());
        commands.put("delete-client", new DeleteClientCommand());
        commands.put("update-client", new UpdateClientCommand());

        commands.put("get-manager-by-login-and-password", new GetManagerByLoginPasswordCommand());
        commands.put("get-all-managers", new GetAllManagersCommand());
        commands.put("save-manager", new SaveManagerCommand());
        commands.put("get-manager", new GetManagerCommand());
        commands.put("delete-manager", new DeleteManagerCommand());
        commands.put("update-manager", new UpdateManagerCommand());

        commands.put("get-admin-by-login-and-password", new GetAdminByLoginPasswordCommand());
        commands.put("get-all-admins", new GetAllAdminsCommand());
        commands.put("save-admin", new SaveAdminCommand());
        commands.put("get-admin", new GetAdminCommand());
        commands.put("delete-admin", new DeleteAdminCommand());
        commands.put("update-admin", new UpdateAdminCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
