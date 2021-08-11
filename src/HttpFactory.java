import java.io.*;

public abstract class HttpFactory {

    public static byte[] makeHttp(String string) {

        StringBuilder http = new StringBuilder();
        String extension = "html";
        if (string.contains(".")) {
            extension = string.split("\\.")[1];
        }

        File file = new File("www" + string);
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            if(extension.equals("png")) {
                http.append("HTTP/1.0 200 Document Follows\n" +
                        "Content-Type: image/" + extension + "\n" +
                        "Content-Length: " + file.length() + "\n" +
                        "\n");
            } else {
                http.append("HTTP/1.0 200 Document Follows\n" +
                        "Content-Type: text/" + extension + "; charset=UTF-8\n" +
                        "Content-Length: " + file.length() + "\n" +
                        "\n");
            }

            //return each byte of the http
            return http.toString().getBytes();

        } catch (FileNotFoundException e) {

            file = new File("www/404.html");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                http.append("HTTP/1.0 404 Not Found\n" +
                        "Content-Type: text/html; charset=UTF-8\n" +
                        "Content-Length: " + file.length() + "\n" +
                        "\n");

                return http.toString().getBytes();

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
                return null;
            }
        }
    }

    public static byte[] sendFile(String string) {

        File file = new File("www" + string);
        byte[] byteFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteFile);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            file = new File("www/404.html");
            byteFile = new byte[(int) file.length()];

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(byteFile);
                fileInputStream.close();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteFile;

    }

}
