package org.codinglabs.rss2epub;

import java.io.File;

public class RssToEpub {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: RssToEpub config_file output_book");
            return;
        }

        File configFile = new File(args[0]);
        if (!configFile.isFile()) {
            System.out.println("\"" + args[0]
                    + "\" does not exist or is not a file");
            return;
        }

        BookMaker maker = new BookMaker();
        maker.make(args[0], args[1]);
        System.out.println("ok");
    }
}
