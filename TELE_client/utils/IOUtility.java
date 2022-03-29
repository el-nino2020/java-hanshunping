package TELE_client.utils;//Utils.java

import java.io.*;

public class IOUtility {
    private IOUtility() {
    }

    public static byte[] inputStreamToByteArray(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        int readLen;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while ((readLen = is.read(buffer)) != -1) {
            baos.write(buffer, 0, readLen);
        }
        byte[] ans = baos.toByteArray();
        baos.close();//不要忘了关闭流对象

        return ans;
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }
}
