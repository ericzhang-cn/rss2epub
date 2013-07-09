package org.codinglabs.rss2epub;

import java.io.File;

import org.apache.log4j.Logger;

public class RssToEpub {
    private static Logger log = Logger.getLogger(RssToEpub.class);

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: RssToEpub config_file output_book");
            return;
        }

        File configFile = new File(args[0]);
        if (!configFile.isFile()) {
            log.error("\"" + args[0] + "\" does not exist or is not a file");
            return;
        }

        BookMaker maker = new BookMaker();
        maker.make(args[0], args[1]);
        log.info("Make " + args[1] + " Completed");
    }
}
