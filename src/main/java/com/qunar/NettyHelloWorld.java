package com.qunar;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Date: 2014-02-24 17:22
 *
 * @author sen.chai
 */
public class NettyHelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(NettyHelloWorld.class);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("wo ca lei!");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = reader.readLine();
        while(line != null) {
            logger.debug("line: {}", line);
            line = reader.readLine();
        }
        socket.close();
    }
}
