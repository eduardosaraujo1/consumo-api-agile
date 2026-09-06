package br.edu.fatecpg.rickandmortyapi.infrastructure.tui;

public final class Ansi {

    private Ansi() {}

    public static final String RESET = "\u001B[0m";

    public enum Foreground {
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String code;

        Foreground(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public enum Background {
        BLACK("\u001B[40m"),
        RED("\u001B[41m"),
        GREEN("\u001B[42m"),
        YELLOW("\u001B[43m"),
        BLUE("\u001B[44m"),
        PURPLE("\u001B[45m"),
        CYAN("\u001B[46m"),
        WHITE("\u001B[47m");

        private final String code;

        Background(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public static String colorize(
        String str,
        Ansi.Background bgColor,
        Ansi.Foreground fgColor
    ) {
        return fgColor.toString() + bgColor.toString() + str + RESET;
    }

    public static String colorize(
        String str,
        Ansi.Foreground fgColor,
        Ansi.Background bgColor
    ) {
        return colorize(str, bgColor, fgColor);
    }

    public static String colorize(String str, Ansi.Foreground fgColor) {
        return fgColor + str + RESET;
    }

    public static String colorize(String str, Ansi.Background bgColor) {
        return bgColor + str + RESET;
    }
}
