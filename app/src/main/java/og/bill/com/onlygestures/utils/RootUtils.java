package og.bill.com.onlygestures.utils;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bill on 2017/11/1.
 */

public class RootUtils {

    private static final String root = "su";

    private static final int CODE_DENY = 1;
    private static final int CODE_ALLOW = 0;

    public static void execCommandByRoot(@NonNull String command, ExecCommandCallback callback) {
        new Thread(() -> {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec(root);
                DataOutputStream dos = new DataOutputStream(process.getOutputStream());

                dos.writeBytes(command);
                dos.flush();
                dos.close();
                int waitCode = process.waitFor();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                if (waitCode == CODE_DENY) {
                    callback.onCommandDeny(reader.readLine());
                } else if (waitCode == CODE_ALLOW) {
                    callback.onCommandAllow(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onCommandDeny(e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                callback.onCommandDeny(e.getMessage());
            }
        }).start();

    }
}
