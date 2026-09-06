package br.edu.fatecpg.rickandmortyapi.infrastructure.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Logger {

    private static Logger _instance;

    private Logger() {}

    public static Logger instance() {
        if (_instance == null) {
            _instance = new Logger();
        }

        return _instance;
    }

    private ZonedDateTime getTimestamp() {
        return ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
    }

    public void log(String message) {
        log(message, "registros.log");
    }

    public void log(String message, String fileName) {
        // Estrutura: [Timestamp] Mensagem
        ZonedDateTime timestamp = getTimestamp();

        try (
            FileWriter writer = new FileWriter(
                fileName,
                StandardCharsets.UTF_8,
                true
            )
        ) {
            writer.write("[%s] %s\n".formatted(timestamp.toString(), message));
        } catch (IOException e) {
            System.out.println("Não foi possível escrever um registro.");
        }
    }
}
