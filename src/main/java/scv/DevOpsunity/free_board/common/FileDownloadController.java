package scv.DevOpsunity.free_board.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class FileDownloadController {
    //글 상세보기(viewArticle)에서 이미지 보게끔 다운로드 시키는 서블릿

    private static String ARTICLE_IMG_REPO="F:\\projectWorkspace\\fileupload";

    @GetMapping("/down.do")
    public void fileDown(@RequestParam("freeArticleNo") String freeArticleNo,
                         @RequestParam("freeImageFileName") String freeImageFileName,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = ARTICLE_IMG_REPO + "\\" + freeArticleNo + "\\" + freeImageFileName;
        File imageFile = new File(path);

        if (imageFile.exists()) {
            String mimeType = request.getServletContext().getMimeType(imageFile.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + freeImageFileName + "\"");
            response.setContentLength((int) imageFile.length());

            try (FileInputStream fis = new FileInputStream(imageFile);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("File path: " + path);
            System.out.println("File exists: " + imageFile.exists());
            System.out.println("freeArticleNo: " + freeArticleNo);
            System.out.println("freeImageFileName: " + freeImageFileName);
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
        }
    }

}
