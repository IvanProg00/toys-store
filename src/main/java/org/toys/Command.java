package org.toys;

public enum Command {
    SHOW_ALL(1),
    ADD_TOY(2),
    WIN_TOY(3),
    SHOW_WON_TOYS(4);

    private final Integer command;

    Command(int command) {
        this.command = command;
    }

    public Integer getCommand() {
        return command;
    }
}
