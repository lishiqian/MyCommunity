package spring_test;

import com.lsq.community.po.Forum;
import com.lsq.community.po.User;
import com.lsq.community.service.ForumService;
import com.lsq.community.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TranscTest extends BaseJunit4Test {
    @Autowired
    private UserService userService;

    @Autowired
    private ForumService forumService;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚
    public void test(){
        List<Forum> forums = forumService.selectForumsByUserIdAndStatus(1, 1);
        for (Forum forum : forums) {
            User user = userService.selectUserById(forum.getUserId());
        }
    }

}
