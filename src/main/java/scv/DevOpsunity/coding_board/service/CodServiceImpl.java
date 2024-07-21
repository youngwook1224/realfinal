package scv.DevOpsunity.coding_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.coding_board.dao.CodDAO;
import scv.DevOpsunity.coding_board.dto.CodDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CodService")
public class CodServiceImpl implements CodService {

    @Autowired
    private CodDAO codDAO;

    @Override
    public Map listArticles(Map<String , Integer> pagingMap) throws DataAccessException {
        Map articleMap = new HashMap();
        int section= pagingMap.get("section");
        int pageNum = pagingMap.get("pageNum");
        int count = (section-1)*100+(pageNum-1)*10;
        List<CodDTO> articlesList = codDAO.selectAllArticles(count);
        int totArticles = codDAO.selectToArticles();
        articleMap.put("articlesList", articlesList);
        articleMap.put("totArticles", totArticles);
       // articleMap.put("totArticles", 324);     // 이거 나중에 지우고 위에 주석 해제하세요
        return articleMap;
    }

    //여러개의 이미지 추가
    @Override
    public int addArticle(Map articleMap) throws DataAccessException {
        int articleNo = codDAO.getNewArticleNo();
        articleMap.put("articleNo",articleNo);
        codDAO.insertNewArticle(articleMap);
        return articleNo;
    }

    //여러개 이미지 상세 글보기
    @Override
    public Map viewArticle(int articleNo) throws DataAccessException {
        Map articleMap = new HashMap();
        CodDTO articleDTO = codDAO.selectArticle(articleNo);
        articleMap.put("article", articleDTO);
        return articleMap;
    }

    @Override
    public void modArticle(Map articleMap) throws DataAccessException {
        codDAO.updateArticle(articleMap);
    }

    @Override
    public void removeArticle(int articleNo) throws DataAccessException {
        codDAO.deleteArticle(articleNo);

    }

}



