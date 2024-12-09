package imt.spellboud.Utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GestionnaireLogs {

    private static GestionnaireLogs instance;
    private Logger logger;

    private GestionnaireLogs() {
        try {
            logger = Logger.getLogger("ApplicationLogger");

            FileHandler fileHandler = new FileHandler(Constante.FICHIER_LOGS, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation du FileHandler : " + e.getMessage());
        }
    }

    public static GestionnaireLogs getInstance() {
        if (instance == null) {
            instance = new GestionnaireLogs();
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

}
