import java.io.*;

public class FileCopyProgram {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Nhập đường dẫn tập tin nguồn: ");
            String sourcePath = reader.readLine();

            System.out.print("Nhập đường dẫn tập tin đích: ");
            String targetPath = reader.readLine();

            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);

            if (!sourceFile.exists()) {
                System.out.println("Tệp nguồn không tồn tại.");
                return;
            }

            if (targetFile.exists()) {
                System.out.println("Tệp đích đã tồn tại.");
                return;
            }

            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(targetFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesCopied = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesCopied += bytesRead;
            }

            fis.close();
            fos.close();

            System.out.println("Sao chép tệp thành công.");
            System.out.println("Tổng số byte đã sao chép: " + totalBytesCopied);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}